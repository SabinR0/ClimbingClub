package com.Fortech.Project.Gym.controller;


import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.ProjectAvailability;
import com.Fortech.Project.Gym.enums.ProjectColor;
import com.Fortech.Project.Gym.enums.ProjectType;
import com.Fortech.Project.Gym.model.Climber;
//import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.Project;
import com.Fortech.Project.Gym.model.request.NewProjectRequest;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import com.Fortech.Project.Gym.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping(path = "/all")
    public List<Project> getProjects() {
        return projectService.getProjects();
    }


    @GetMapping(path = "/{projectId}")
    public Project getProjectById(@PathVariable Long projectId){
        return projectService.findByProjectId(projectId);
        }

    @GetMapping(path = "/by-name")
    public Project getProjectByName(@RequestParam(value = "projectName", required = true) String projectName){
        return projectService.findByProjectName(projectName);
    }

    @GetMapping(path = "/sorted-by-difficulty")
    public List<Project> getProjectsByDifficulty(){
        return projectService.sortByDifficulty();
    }

    @GetMapping(path = "/by-type")
    public List<Project> getProjectsByType(@RequestParam(value = "type") ProjectType type){
        return projectService.findByProjectType(type);
    }

    @DeleteMapping (path = "/delete/{projectId}")
    public void deleteProjectById(@PathVariable("projectId") Long projectId){
          projectService.deleteProjectByProjectId(projectId);
    }

    @PostMapping(path = "/new")
    public Project createProject(@RequestBody NewProjectRequest newProjectRequest)
    {
        return projectService.createNewProject(newProjectRequest);

    }

    @PostMapping(path = "/update-project-status/{projectId}")
    public Project updateProjectStatus(@PathVariable Long projectId, @RequestBody Map<String, String> newProjectStatus) {
        ProjectAvailability projectAvailability = ProjectAvailability.valueOf(newProjectStatus.get("newProjectStatus"));
        return projectService.updateProjectStatus(projectId, projectAvailability);
    }

//    @GetMapping("/{projectId}/toppedBy")
//    public Set<ClimberProject> getProjects(@PathVariable Long projectId) {
//        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId));
//        return project.getToppedBy();
//    }

}
