package com.zhitar.topjavagraduation.repository;

import com.zhitar.topjavagraduation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}