package com.locaplace.api.user;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locaplace.api.common.entity.user.User;
import com.locaplace.api.common.entity.user.UserDetail;
import com.locaplace.api.common.entity.user.UserVerification;
import com.locaplace.api.common.enums.ErrorCode;
import com.locaplace.api.common.enums.Yn;
import com.locaplace.api.common.response.LocaException;
import com.locaplace.api.common.response.LocaResponse;
import com.locaplace.api.common.util.EntityMapper;
import com.locaplace.api.common.util.NicknameGenerator;
import com.locaplace.api.jwt.JwtTokenService;
import com.locaplace.api.user.repository.UserDetailRepository;
import com.locaplace.api.user.repository.UserRepository;
import com.locaplace.api.user.repository.UserVerificationRepository;
import com.locaplace.api.user.vo.ChangePasswordVo;
import com.locaplace.api.user.vo.UserAgreementVo;
import com.locaplace.api.user.vo.UserLoginVo;
import com.locaplace.api.user.vo.UserSignUpVo;
import com.locaplace.api.user.vo.UserUpdateVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final JavaMailSender mailSender;
	private final NicknameGenerator nicknameGenerator;

    private final JwtTokenService jwtTokenService;

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserVerificationRepository userVerificationRepository;

    @Value("${locaplace.endpoint}")
    private String endpoint;

    @Transactional
    @Async("asyncExecutor")
    public CompletableFuture<LocaResponse<Void>> signUp(UserSignUpVo vo) {

        Optional<User> existUser = findUserByEmail(vo.getEmail());
        if (existUser.isPresent()) {
            throw new LocaException(ErrorCode.DUPLICATED_EMAIL);
        }
        String encodedPassword = passwordEncoder.encode(vo.getPassword());
        User user = EntityMapper.toEntity(vo, User.class);
        //User 정보 생성
        user.setNickname(nicknameGenerator.generate());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        //UserDetail 정보 생성
        UserDetail userDetail = UserDetail.builder()
                .user(user)
                .userType("GUEST")
                .foreignYn(Yn.N)
                .build();
        userDetailRepository.save(userDetail);
        //인증 정보 생성
        UserVerification verification = UserVerification.builder()
                .user(user)
                .userDetail(userDetail)
                .build();
        userVerificationRepository.save(verification);
        //이메일 발송
        sendVerificationEmail(user.getEmail(), verification.getEmailVerificationToken());
        return CompletableFuture.completedFuture(LocaResponse.of(201, null, "USER_CREATED", "회원가입에 성공하였습니다."));
    }

    public LocaResponse<String> login(UserLoginVo vo) {
        User user = userRepository.findByEmail(vo.getEmail())
                .orElseThrow(() -> new LocaException(ErrorCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
            throw new LocaException(ErrorCode.INVALID_PASSWORD);
        }
        String token = jwtTokenService.generateToken(user);
        LocalDateTime expireDtm = LocalDateTime.now().plusSeconds(JwtTokenService.EXPIRATION_TIME);
        jwtTokenService.createToken(user, token, expireDtm);
        if (user.getNeedPasswordChangeYn() == Yn.Y) {
            return LocaResponse.of(400, token, "USER_004", "비밀번호를 변경 후 로그인할 수 있습니다.");
        }
        return LocaResponse.of(200, token, "PERMIT", "로그인 성공");
    }

    @Transactional
    public void verifyPasswordChangeMail(String token) {
        UserVerification userVerification = userVerificationRepository.findByPasswordChangeToken(token)
                .orElseThrow(() -> new LocaException(ErrorCode.VERIFICATION_INFO_NOT_FOUND));
        if (!(token.equals(userVerification.getPasswordChangeToken()))) {
            throw new LocaException(ErrorCode.INVALID_TOKEN);
        }
        userVerification.setPasswordChangeVerificationYn(Yn.Y);
        userVerificationRepository.save(userVerification);
    }

    @Transactional
    public void userVerificationByEmail(String token) {
        UserVerification verification = userVerificationRepository.findByEmailVerificationToken(token)
                .orElseThrow(() -> new LocaException(ErrorCode.INVALID_EMAIL_VERIFICATION_TOKEN));
        User user = verification.getUser();
        user.verify();
        userRepository.save(user);
    }

    @Transactional
    public void updatePassword(User user, ChangePasswordVo vo) {
        UserVerification userVerification = userVerificationRepository.findByUser(user);
        if (userVerification.getPasswordChangeVerificationYn().equals(Yn.N)) {
            throw new LocaException(ErrorCode.UNAUTHORIZED_ACCESS);
        }
        String encodedPassword = passwordEncoder.encode(vo.getPassword());
        user.setPassword(encodedPassword);
        user.setNeedPasswordChangeYn(Yn.N);
        userVerification.setPasswordChangeToken(null);
        userVerification.setPasswordChangeVerificationYn(Yn.N);
        userRepository.save(user);
    }

    @Transactional
    public UserUpdateVo updateInfo(User user, UserUpdateVo vo) {
        updateUserFields(user, vo);
        updateUserDetailFields(userDetailRepository.findByUser(user), vo);
        return vo;
    }

    @Transactional
    public UserAgreementVo updateAgreement(User user, UserAgreementVo vo) {
        Optional.ofNullable(vo.getLocationYn()).ifPresent(user::setLocationYn);
        Optional.ofNullable(vo.getMarketingYn()).ifPresent(user::setMarketingYn);
        userRepository.save(user);
        return vo;
    }

    private void updateUserFields(User user, UserUpdateVo vo) {
        Optional.ofNullable(vo.getNickname()).ifPresent(user::setNickname);
        Optional.ofNullable(vo.getName()).ifPresent(user::setName);
//        Optional.ofNullable(vo.getBirthDate()).ifPresent(user::setBirthDate);
        Optional.ofNullable(vo.getGender()).ifPresent(user::setGender);
        Optional.ofNullable(vo.getPhoneNumber()).ifPresent(user::setPhoneNumber);
        userRepository.save(user);
    }

    private void updateUserDetailFields(UserDetail userDetail, UserUpdateVo vo) {
        Optional.ofNullable(vo.getIntroduction()).ifPresent(userDetail::setIntroduction);
        userDetailRepository.save(userDetail);
    }

    /**
     * TODO
     * 이메일 발송 로직 자체를 모듈로 분리해야함
     * 비동기 처리해도 느림
     */
    @Async("asyncExecutor")
    public void sendVerificationEmail(String email, String token) {
        String subject = "Locaplace 회원가입 인증 메일";
        String content = endpoint + "/api/v1/user/verification?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Async("asyncExecutor")
    @Transactional
    public void sendPasswordChangeMail(User user) {
        UserVerification verification = userVerificationRepository.findByUser(user);

        String token = UUID.randomUUID().toString();
        verification.setPasswordChangeToken(token);
        verification.setPasswordChangeVerificationYn(Yn.Y);
        userVerificationRepository.save(verification);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Locaplace 비밀번호 초기화 이메일");
        message.setText(endpoint + "/api/v1/user/password/verification?token=" + token);

        mailSender.send(message);
    }

    /**
     * find methods
     */
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
