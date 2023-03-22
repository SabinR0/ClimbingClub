package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.ProjectType;
import com.Fortech.Project.Gym.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectId(Long projectId);

    Project findByProjectName(String name);

    List<Project> findAllByOrderByDifficulty();

    List<Project> findAllByProjectType(ProjectType projectType);




}
