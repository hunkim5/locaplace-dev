package com.locaplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.locaplace.api.jwt.JwtAuthenticationFilter;
import com.locaplace.api.jwt.JwtTokenService;
import com.locaplace.api.user.repository.UserDetailRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtTokenService jwtTokenService;
    private final UserDetailRepository userDetailRepository;

    public SecurityConfig(JwtTokenService jwtTokenService, UserDetailRepository userDetailRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailRepository = userDetailRepository;
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                //jwt token이 없어도 됨
                                "/error",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/api-docs/**",
                                "/test/**",
                                "/api/v1/user",
                                "/api/v1/user/verification",
                                "/api/v1/user/password/verification"
                        ).permitAll()
                        .requestMatchers(
                                //jwt token이 필요한 endpoint
                                "/api/v1/user/password",
                                "/api/v1/user/nickname",
                                "/api/v1/user/agreement",
                                "/api/v1/user/introduction",
                                "/api/v1/verification/**"
                        ).authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenService, userDetailRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
