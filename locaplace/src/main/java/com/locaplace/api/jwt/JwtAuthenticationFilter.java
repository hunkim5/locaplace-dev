package com.locaplace.api.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.locaplace.api.common.entity.user.JwtToken;
import com.locaplace.api.common.entity.user.User;
import com.locaplace.api.common.entity.user.UserDetail;
import com.locaplace.api.common.enums.Yn;
import com.locaplace.api.user.repository.UserDetailRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailRepository userDetailRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            String token = extractTokenFromRequest(request);
            log.info(">>>>token:{}",token);
            if (token != null) {
                log.debug("JWT token found in request: {}", token);
                Optional<JwtToken> jwtTokenOpt = jwtTokenService.findByToken(token);
                if (jwtTokenOpt.isPresent()) {
                    log.debug("Token found in database");
                    if (jwtTokenService.validateToken(token)) {
                        JwtToken jwtToken = jwtTokenOpt.get();
                        log.debug(jwtToken.toString());
                        User user = jwtToken.getUser();
                        UserDetail userDetail = userDetailRepository.findByUser(user);

                        if (userDetail != null) {
                            List<GrantedAuthority> authorities = new ArrayList<>();
                            authorities.add(new SimpleGrantedAuthority(
                                    user.getVerifiedYn() == Yn.Y ? "ROLE_VERIFIED" : "ROLE_UNVERIFIED"
                            ));
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(user, null, authorities);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            log.debug("Authentication set for user: {}", user.getEmail());
                        } else {
                            log.warn("User details not found for user: {}", user.getEmail());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User details not found");
                            return;
                        }
                    } else {
                        log.warn("Token validation failed");
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                        return;
                    }
                } else {
                    log.warn("Token not found in database");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not found");
                    return;
                }
            } else {
                log.debug("No token found in request");
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Error processing JWT token", e);
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error processing token: " + e.getMessage());
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}