package com.Fortech.Project.Gym.controller;

import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.Project;
import com.Fortech.Project.Gym.repository.ClimberProjectRepository;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import com.Fortech.Project.Gym.service.ClimberProjectService;
import com.Fortech.Project.Gym.service.ClimberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ClimberProject")
public class ClimberProjectController {

    private final ClimberService climberService;
    private final ClimberRepository climberRepository;
    private final UserRepository userRepository;

    private final ClimberProjectRepository climberProjectRepository;
    private final ClimberProjectService climberProjectService;

    public ClimberProjectController(ClimberService climberService, ClimberRepository climberRepository, UserRepository userRepository, ClimberProjectRepository climberProjectRepository, ClimberProjectService climberProjectService) {
        this.climberService = climberService;
        this.climberRepository = climberRepository;
        this.userRepository = userRepository;
        this.climberProjectRepository = climberProjectRepository;
        this.climberProjectService = climberProjectService;
    }


    @GetMapping(path = "/{projectId}/climbers")
    public Set<Climber> findClimbersByProjectId(@PathVariable("projectId") Long projectId){
        Project project = new Project();
        project.setProjectId(projectId);
        return  climberProjectService.findClimbersByProjectId(project);
    }

    @GetMapping(path = "/{climberId}/projects")
    public Set<Project> getProjectsByClimberId(@PathVariable("climberId") Long climberId) {
        Climber climber = new Climber();
        climber.setClimberId(climberId);
        return climberProjectService.findProjectsByClimberId(climber);
    }


    @GetMapping("/test/{userId}")
    public Set<ClimberProject> getProjectsSentByUserId(@PathVariable Long userId) {
        return climberProjectService.getProjectsSentByUserId(userId);
    }

}
