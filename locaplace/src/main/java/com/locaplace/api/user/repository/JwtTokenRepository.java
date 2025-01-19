package com.locaplace.api.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locaplace.api.common.entity.user.JwtToken;
import com.locaplace.api.common.entity.user.User;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByTokenHash(String token);

    Optional<JwtToken> findByUser(User user);

    List<JwtToken> findByExpireDtmBefore(LocalDateTime date);
}