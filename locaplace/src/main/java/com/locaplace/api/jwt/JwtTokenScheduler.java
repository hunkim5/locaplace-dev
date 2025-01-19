package com.locaplace.api.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenScheduler {
    private final JwtTokenService jwtTokenService;

    @Scheduled(cron = "0 0 * * * *")
    public void cleanupExpiredTokens(){
        jwtTokenService.deleteExpiredTokens();
    }
}