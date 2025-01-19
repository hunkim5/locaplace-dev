package com.locaplace.api.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locaplace.api.common.entity.user.User;
import com.locaplace.api.common.entity.user.UserVerification;

@Repository
public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {

    Optional<UserVerification> findByEmailVerificationToken(String token);

    Optional<UserVerification> findByPasswordChangeToken(String token);

    UserVerification findByUser(User user);
}
