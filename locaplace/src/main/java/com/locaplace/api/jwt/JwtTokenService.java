package com.locaplace.api.jwt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locaplace.api.common.entity.user.JwtToken;
import com.locaplace.api.common.entity.user.User;
import com.locaplace.api.common.enums.ErrorCode;
import com.locaplace.api.common.response.LocaException;
import com.locaplace.api.user.repository.JwtTokenRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtTokenService {

    private final JwtTokenRepository jwtTokenRepository;
    @Getter
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    public static final long EXPIRATION_TIME = 86400; //24시간
    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtTokenService(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    @Transactional
    public void createToken(User user, String token, LocalDateTime expireDtm) {
        String tokenHash = hashToken(token);
        Optional<JwtToken> existingToken = jwtTokenRepository.findByUser(user);

        JwtToken jwtToken;
        if (existingToken.isPresent()) {
            jwtToken = existingToken.get();
        } else {
            jwtToken = new JwtToken();
            jwtToken.setUser(user);
        }
        jwtToken.setTokenHash(tokenHash);
        jwtToken.setExpireDtm(expireDtm);
        jwtTokenRepository.save(jwtToken);
    }

    public Optional<JwtToken> findByToken(String token) {
        String tokenHash = hashToken(token);
        return jwtTokenRepository.findByTokenHash(tokenHash);
    }

    @Transactional
    public void deleteExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        List<JwtToken> expiredTokenList = jwtTokenRepository.findByExpireDtmBefore(now);
        jwtTokenRepository.deleteAll(expiredTokenList);
    }

    public boolean validateToken(String token) {
        Optional<JwtToken> jwtTokenOpt = findByToken(token);
        if (jwtTokenOpt.isPresent()) {
            JwtToken jwtToken = jwtTokenOpt.get();
            return jwtToken.getExpireDtm().isAfter(LocalDateTime.now());
        }
        return false;
    }

    public String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new LocaException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}