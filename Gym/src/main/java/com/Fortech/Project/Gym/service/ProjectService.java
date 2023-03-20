package com.Fortech.Project.Gym.service;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.ProjectAvailability;
import com.Fortech.Project.Gym.enums.ProjectType;
import com.Fortech.Project.Gym.exceptions.ProjectNotFoundException;
import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.Project;
import com.Fortech.Project.Gym.model.request.NewProjectRequest;
import com.Fortech.Project.Gym.repository.ClimberProjectRepository;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service

public class ProjectService {

    private final ClimberRepository climberRepository;

    private final ProjectRepository projectRepository;

    private final ClimberProjectRepository climberProjectRepository;

    @Autowired
    public ProjectService(ClimberRepository climberRepository, ProjectRepository projectRepository, ClimberProjectRepository climberProjectRepository) {
        this.climberRepository = climberRepository;
        this.projectRepository = projectRepository;
        this.climberProjectRepository = climberProjectRepository;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project findByProjectId(Long projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    public Project findByProjectName(String projectName) {
        return projectRepository.findByProjectName(projectName);
    }

    public List<Project> sortByDifficulty() {
        return projectRepository.findAllByOrderByDifficulty();
    }

    public List<Project> findByProjectType(ProjectType projectType) {
        return projectRepository.findAllByProjectType(projectType);
    }
    //@Transactional
    public void deleteProjectByProjectId(Long projectId) {
         projectRepository.deleteById(projectId);
    }

    public Project updateProjectStatus(Long projectId, ProjectAvailability newStatus) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID " + projectId));
        project.setProjectStatus(newStatus);
        projectRepository.save(project);
        return project;
    }



    public Project createNewProject(NewProjectRequest newProjectRequest) {
        Project newProject = Project.builder()
                .projectName(newProjectRequest.getProjectName())
                .difficulty(newProjectRequest.getDifficulty())
                .projectType(newProjectRequest.getProjectType())
                .projectDescription(newProjectRequest.getProjectDescription())
                .projectStatus(newProjectRequest.getProjectStatus())
                .projectColor(newProjectRequest.getProjectColor())
                .numberOfPoints(newProjectRequest.getNumberOfPoints())
                .climbersRating(Difficulty.V0)
                .yCoordinate(newProjectRequest.getYCoordinate())
                .xCoordinate(newProjectRequest.getXCoordinate())
                .build();
        return projectRepository.save(newProject);
    }



}
