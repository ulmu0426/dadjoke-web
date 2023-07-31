package com.ulmu.dadjoketest.repository;

import com.ulmu.dadjoketest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);   //유저 닉네임으로 찾음
}
