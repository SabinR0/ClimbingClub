package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(Integer userId);
    Optional<User> findUserByEmail(String email);

    void deleteUserByUserId(Integer userId);
}