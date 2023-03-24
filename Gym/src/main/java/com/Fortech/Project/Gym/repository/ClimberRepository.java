package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.enums.Skill;
import com.Fortech.Project.Gym.model.Climber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClimberRepository extends JpaRepository<Climber, Long> {
    Climber findByClimberId(Long climberId);
    Climber findByUserId(Long userId);
    void deleteClimberByUserId(Long userId);
    List<Climber> findAllByGenderOrderByTotalPointsDesc(Gender gender);

    Climber findBySkillLevel(Skill skillLevel);



}
