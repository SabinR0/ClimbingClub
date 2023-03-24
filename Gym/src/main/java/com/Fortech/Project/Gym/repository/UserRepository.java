package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long userId);

    void deleteUserByUserId(Long userId);

    Optional<User> findByuserName(String username);

    Boolean existsByuserName(String username);

    Boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
