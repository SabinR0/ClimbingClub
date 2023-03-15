package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ClimberProjectRepository extends JpaRepository<ClimberProject, Integer> {


    @Query("SELECT cp.projectId FROM ClimberProject cp WHERE cp.climberId = :climber")
    Set<Project> findProjectsByClimberId(@Param("climber") Climber climber);

    @Query("SELECT cp.climberId FROM ClimberProject cp WHERE cp.projectId = :project")
    Set<Climber> findClimbersByProjectId(@Param("project") Project projectId);

    Set<ClimberProject> findByClimberIdUserId(Integer userId);
}
