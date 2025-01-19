package com.locaplace.api.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.locaplace.api.common.annotation.PlareController;
import com.locaplace.api.common.entity.user.User;
import com.locaplace.api.common.response.LocaException;
import com.locaplace.api.common.response.LocaResponse;
import com.locaplace.api.common.response.apidoc.common.DocCommonResponse;
import com.locaplace.api.common.response.apidoc.user.DocUserReponse;
import com.locaplace.api.user.vo.ChangePasswordVo;
import com.locaplace.api.user.vo.UserAgreementVo;
import com.locaplace.api.user.vo.UserLoginVo;
import com.locaplace.api.user.vo.UserSignUpVo;
import com.locaplace.api.user.vo.UserUpdateVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PlareController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "message에 jwt token값을 반환",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.LOGIN_SUCCESS)
                    )),
            @ApiResponse(responseCode = "404", description = "요청한 메일주소의 회원이 없을 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.USER_NOT_FOUND)
                    )),
            @ApiResponse(responseCode = "401", description = "요청한 이메일 주소로 유저 정보는 있으나 비밀번호가 틀렸을 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.INVALID_PASSWORD)
                    )),
            @ApiResponse(responseCode = "400", description = "정보는 맞으나 비밀번호 변경이 필요할 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.INVALID_PASSWORD)
                    ))
    })
    @GetMapping("/user")
    @Operation(summary = "유저 로그인", description = "email, password 정보로 로그인 후 jwt token 발급")
    public LocaResponse<String> login(@Parameter(description = "로그인 Vo", required = true) UserLoginVo vo) {
        log.info(vo.toString());
        return userService.login(vo);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.SIGNUP_SUCCESS)
                    )),
            @ApiResponse(responseCode = "409", description = "요청한 이메일 주소로 이미 회원 정보가 있을 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.DUPLICATED_EMAIL)
                    ))
    })

    @PostMapping("/user")
    @Operation(summary = "유저 회원가입", description = "email, password, name, gender값으로 회원가입")
    public CompletableFuture<LocaResponse<Void>> signUp(@RequestBody @Parameter(description = "회원가입 Vo", required = true) UserSignUpVo vo) {
        return userService.signUp(vo);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.EMAIL_VERIFICATION_SUCCESS)
                    )),
            @ApiResponse(responseCode = "422", description = "이메일 인증 토큰이 만료됐을 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocCommonResponse.INVALID_TOKEN)
                    ))
    })
    @GetMapping("/user/verification")
    @Operation(summary = "유저 이메일 인증", description = "계정 사용 등록을(user_detail.verified_yn) 위한 이메일 인증")
    public LocaResponse<Object> verifyEmail(@RequestParam("token") String token) {
        userService.userVerificationByEmail(token);
        return LocaResponse.of(200, null, "USER_VERIFIED", "이메일 인증이 완료되었습니다.");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 이메일 전송 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocCommonResponse.SEND_EMAIL_SUCCESS)
                    )),
            @ApiResponse(responseCode = "401", description = "주어진 userNid로 유저를 찾을 수 없을 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.USER_NOT_FOUND)
                    ))
    })

    /*
     * TODO
     * 토큰 유효기간 설정
     */
    @PostMapping("/user/password")
    @SecurityRequirement(name = "bearer-token")
    @Operation(summary = "비밀번호 변경 메일 발송", description = "비밀번호 변경 메일을 발송합니다.")
    public LocaResponse<Object> sendChangePasswordMail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        userService.sendPasswordChangeMail(user);
        return LocaResponse.of(200, null, "SEND_SUCCESS", "이메일 전송에 성공하였습니다.");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 이메일 인증 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocCommonResponse.VALID_TOKEN)
                    )),
            @ApiResponse(responseCode = "404", description = "주어진 token으로 유저를 찾을 수 없을 때(or 토큰이 만료되었을 때)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.USER_NOT_FOUND)
                    )),
            @ApiResponse(responseCode = "401", description = "이메일로 발송한 토큰과 DB의 토큰이 다를 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocCommonResponse.INVALID_TOKEN)
                    ))
    })
    @GetMapping("/user/password/verification")
    @Operation(summary = "비밀번호 변경 메일 발송 확인", description = "비밀번호 변경 전 사용자를 확인합니다.")
    public LocaResponse<Object> verifyChangePasswordMail(
            @Parameter(description = "비밀번호 변경 이메일 발송을 위해 생성된 token", required = true) @RequestParam(name = "token") String token) {
        userService.verifyPasswordChangeMail(token);
        return LocaResponse.of(200, null, "VERIFY_SUCCESS", "인증에 성공하였습니다.");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 이메일 인증 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.PASSWORD_CHANGE_SUCCESS)
                    )),
            @ApiResponse(responseCode = "404", description = "주어진 userNid로 유저를 찾을 수 없을 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.USER_NOT_FOUND)
                    ))
    })
    @PutMapping("/user/password")
    @SecurityRequirement(name = "bearer-token")
    @Operation(summary = "비밀번호 변경", description = "비밀번호를 변경합니다.")
    public LocaResponse<Object> updatePassword(@RequestBody @Parameter(description = "비밀번호 변경 Vo", required = true) ChangePasswordVo vo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        userService.updatePassword(user, vo);
        return LocaResponse.of(200, null, "PASSWORD_CHANGE_SUCCESS", "패스워드 변경에 성공하였습니다.");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 변경 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.NICKNAME_CHANGE_SUCCESS)
                    )),
            @ApiResponse(responseCode = "404", description = "주어진 userNid로 유저를 찾을 수 없을 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.USER_NOT_FOUND)
                    ))
    })
    @PutMapping("/user")
    @SecurityRequirement(name = "bearer-token")
    @Operation(summary = "유저 정보 변경", description = "유저 정보 변경 API")
    public LocaResponse<Object> updateInfo(@RequestBody @Parameter(description = "유저 정보 변경 Vo", required = true) UserUpdateVo vo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return LocaResponse.of(200, userService.updateInfo(user, vo), "NICKNAME_CHANGE_SUCCESS", "유저 정보 변경에 성공하였습니다.");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "민감정보이용 동의여부 업데이트 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.PASSWORD_CHANGE_SUCCESS)
                    )),
            @ApiResponse(responseCode = "401", description = "주어진 userNid로 유저를 찾을 수 없을 때",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocaException.class),
                            examples = @ExampleObject(value = DocUserReponse.USER_NOT_FOUND)
                    ))
    })
    @PutMapping("/user/agreement")
    @SecurityRequirement(name = "bearer-token")
    @Operation(summary = "민감정보이용 동의여부 업데이트", description = "민감정보이용 동의여부 업데이트 API")
    public LocaResponse<Object> updateUserAgreement(@RequestBody @Parameter(description = "민감정보이용 동의 여부 Vo", required = true) UserAgreementVo vo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return LocaResponse.of(200, userService.updateAgreement(user, vo), "AGREEMENT_UPDATE_SUCCESS", "민감정보이용 동의 항목을 업데이트하였습니다.");
    }
}
