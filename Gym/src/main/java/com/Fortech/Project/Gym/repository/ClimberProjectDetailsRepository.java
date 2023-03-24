package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.ClimberProjectDetails;
import com.Fortech.Project.Gym.model.ClimberProjectKey;
import com.Fortech.Project.Gym.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClimberProjectDetailsRepository extends JpaRepository<ClimberProjectDetails, ClimberProjectKey> {

    List<ClimberProjectDetails> findByClimber(Climber climber);
    List<ClimberProjectDetails> findByProject(Project project);

}
