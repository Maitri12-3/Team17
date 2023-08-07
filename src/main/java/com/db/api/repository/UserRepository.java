package com.db.api.repository;

import com.db.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
