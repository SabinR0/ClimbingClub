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
public interface ClimberRepository extends JpaRepository<Climber, Integer> {
        Climber findByClimberId(Integer climberId);
        Climber findBySkillLevel(Skill skillLevel);
        Climber findByUserId(Integer userId);
        void deleteClimberByUserId(Integer userId);


    Climber findByClimberId(Climber climberId);

    Integer findClimberByClimberId(Climber climberId);


}
