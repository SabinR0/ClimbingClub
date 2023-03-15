package com.Fortech.Project.Gym.controller;


import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.ProjectAvailability;
import com.Fortech.Project.Gym.enums.ProjectColor;
import com.Fortech.Project.Gym.enums.ProjectType;
import com.Fortech.Project.Gym.model.Project;
import com.Fortech.Project.Gym.model.request.NewProjectRequest;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import com.Fortech.Project.Gym.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ClimberRepository climberRepository;
    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    @Autowired
    public ProjectController(ClimberRepository climberRepository, UserRepository userRepository, ProjectRepository projectRepository, ProjectService projectService) {
        this.climberRepository = climberRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    @GetMapping(path = "/projects")
    public List<Project> getProjects() {
        return projectService.getProjects();
    }


    @GetMapping(path = "/{projectId}")
    public Project getProjectById(@PathVariable Integer projectId){
        return projectService.findByProjectId(projectId);
        }

    @GetMapping(path = "/by-name")
    public Project getProjectByName(@RequestParam("projectName") String projectName){
        return projectService.findByProjectName(projectName);
    }

    @GetMapping(path = "/sorted-by-difficulty")
    public List<Project> getProjectsByDifficulty(){
        return projectService.sortByDifficulty();
    }

    @GetMapping(path = "/by-type")
    public List<Project> getProjectsByType(@RequestParam("type") ProjectType type){
        return projectService.findByProjectType(type);
    }

    @DeleteMapping (path = "/delete/{projectId}")
    public void deleteProjectById(@PathVariable("projectId") Integer projectId){
          projectService.deleteProjectByProjectId(projectId);
    }

    @PostMapping(path = "/new")
    public Project createProject(@RequestBody NewProjectRequest newProjectRequest)
    {
        return projectService.createNewProject(newProjectRequest);

    }

    @PostMapping(path = "/update-project-status/{projectId}")
    public Project updateProjectStatus(@PathVariable Integer projectId, @RequestBody Map<String, String> newProjectStatus) {
        ProjectAvailability projectAvailability = ProjectAvailability.valueOf(newProjectStatus.get("newProjectStatus"));
        return projectService.updateProjectStatus(projectId, projectAvailability);
    }

}
