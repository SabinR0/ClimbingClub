package com.Fortech.Project.Gym.controller;

import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.model.*;
import com.Fortech.Project.Gym.model.request.ProjectSentRequest;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import com.Fortech.Project.Gym.service.ClimberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/climbers")
public class ClimberController {

    private final ClimberService climberService;
    private final ClimberRepository climberRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClimberController(ClimberService climberService, ClimberRepository climberRepository, UserRepository userRepository) {
        this.climberService = climberService;
        this.climberRepository = climberRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<Climber> getClimbers() {
        return climberService.getClimbers();
    }

    @GetMapping("/ladder")
    public List<Climber> getSortedClimbersByTotalPoints() {
        return climberService.sortClimbersByTotalPoints();
    }

    @GetMapping("/{userId}")
    public Climber getClimberByUserId(@PathVariable Long userId) {
        return climberService.getClimberByUserId(userId);
    }


    @PostMapping("/update-stats")
    Climber completeNewProject(@RequestBody ProjectSentRequest climberProjectRequest){
        return climberService.completeNewProject(climberProjectRequest);

    }

    @GetMapping(path = "/by-gender")
    public List<Climber> findAllByGenderOrderByTotalPointsDesc(@RequestParam(value = "gender") Gender gender){
        return climberService.findAllByGenderOrderByTotalPointsDesc(gender);
    }


    @PutMapping("/pleasework/{id}")
    public ResponseEntity<Climber> updateClimber(@PathVariable Long id) {
        Climber currentClient = climberRepository.findById(id).orElseThrow(RuntimeException::new);
       // currentClient.recalculateStats();
        climberRepository.save(currentClient);

        return ResponseEntity.ok(currentClient);
    }


//    @GetMapping("/{climberId}/projects")
//    public Set<ClimberProject> getProjects(@PathVariable Long climberId) {
//        Climber climber = climberRepository.findById(climberId).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + climberId));
//        return climber.getProjectsSent();
//    }


}
