package com.Fortech.Project.Gym.controller;

import com.Fortech.Project.Gym.enums.ProjectAvailability;
import com.Fortech.Project.Gym.enums.ProjectType;
import com.Fortech.Project.Gym.exceptions.EmptySetException;
import com.Fortech.Project.Gym.exceptions.ProjectNotFoundException;
import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.Project;
import com.Fortech.Project.Gym.model.request.NewProjectRequest;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import com.Fortech.Project.Gym.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/projects")
public class ProjectController {


    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, ProjectService projectService) {

        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    @GetMapping(path = "/all")
    public List<Project> getProjects() {
        List<Project> projects = projectService.getProjects();
        if (projects.isEmpty()) {
            throw new ResourceNotFoundException("No projects found");
        }
        return projects;
    }

    @GetMapping(path = "/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        Project project = projectService.findByProjectId(projectId);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }


    @GetMapping(path = "/by-name")
    public ResponseEntity<Project> getProjectByName(@RequestParam(value = "projectName") String projectName) {
        Project project = projectService.findByProjectName(projectName);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }

    @GetMapping(path = "/sorted-by-difficulty")
    public List<Project> getProjectsByDifficulty() {
        return projectService.sortByDifficulty();
    }

    @GetMapping(path = "/by-type")
    public ResponseEntity<List<Project>> getProjectsByType(@RequestParam(value = "type") String projectType) {
        try {
            ProjectType type = ProjectType.valueOf(projectType.toUpperCase());
            List<Project> projects = projectService.findByProjectType(type);
            if (projects.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(projects);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/delete/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable("projectId") Long projectId) {
        try {
            projectService.deleteProjectByProjectId(projectId);
            return ResponseEntity.ok("Project deleted successfully");
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(path = "/new")
    public ResponseEntity<Object> createProject(@RequestBody NewProjectRequest newProjectRequest) {
        String projectName = newProjectRequest.getProjectName();
        if (projectService.existsByProjectName(projectName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Project project = projectService.createNewProject(newProjectRequest);
        return ResponseEntity.ok(project);

    }

    @PostMapping(path = "/update-project-status/{projectId}")
    public Project updateProjectStatus(@PathVariable Long projectId, @RequestBody Map<String, String> newProjectStatus) {
        ProjectAvailability projectAvailability = ProjectAvailability.valueOf(newProjectStatus.get("newProjectStatus"));
        return projectService.updateProjectStatus(projectId, projectAvailability);
    }

    @GetMapping("/{projectId}/toppedBy")
    public Set<Climber> getProjects(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId));
        return project.getToppedBy();
    }


}
