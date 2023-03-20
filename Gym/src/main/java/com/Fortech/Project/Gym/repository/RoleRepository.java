package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.enums.ERole;
import com.Fortech.Project.Gym.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
