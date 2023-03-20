package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.enums.Skill;
import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ClimberRepository extends JpaRepository<Climber, Long> {
    Climber findByClimberId(Long climberId);

    Climber findBySkillLevel(Skill skillLevel);

    Climber findByUserId(Long userId);

    void deleteClimberByUserId(Long userId);


    List<Climber> findAllByGenderOrderByTotalPointsDesc(Gender gender);

    Climber findByClimberId(Climber climberId);

    Integer findClimberByClimberId(Climber climberId);


}
