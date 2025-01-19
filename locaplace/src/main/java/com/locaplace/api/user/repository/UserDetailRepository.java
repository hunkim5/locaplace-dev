package com.locaplace.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locaplace.api.common.entity.user.User;
import com.locaplace.api.common.entity.user.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    UserDetail findByUser(User user);
}
