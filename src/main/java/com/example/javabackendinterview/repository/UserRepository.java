package com.example.javabackendinterview.repository;

import com.example.javabackendinterview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Optional<User> findUserByName(String name);

    Boolean existsByName(String name);

    @Override
    Optional<User> findById(Long id);
}
