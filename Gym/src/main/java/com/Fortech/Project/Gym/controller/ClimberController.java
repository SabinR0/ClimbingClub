package com.Fortech.Project.Gym.controller;

import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.model.*;
import com.Fortech.Project.Gym.model.request.ProjectSentRequest;
import com.Fortech.Project.Gym.service.ClimberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/climbers")
public class ClimberController {

    private final ClimberService climberService;


    @Autowired
    public ClimberController(ClimberService climberService) {
        this.climberService = climberService;

    }

    @GetMapping("/all")
    public ResponseEntity<List<Climber>> getClimbers() {
        List<Climber> climbers = climberService.getClimbers();
        if (climbers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(climbers);
        }
    }


    @GetMapping("/ladder")
    public ResponseEntity<?> getSortedClimbersByTotalPoints() {
        List<Climber> climbers = climberService.sortClimbersByTotalPoints();

        if (climbers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No climbers found.");
        }

        return ResponseEntity.ok(climbers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Climber> getClimberByUserId(@PathVariable Long userId) {
        Climber climber = climberService.getClimberByUserId(userId);
        if (climber == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(climber);
    }


    @PostMapping("/updated-stats")
    Climber sendProject(@RequestBody ProjectSentRequest projectSentRequest) {
        return climberService.completeNewProject(projectSentRequest);

    }

    @GetMapping(path = "/by-gender")
    public ResponseEntity<List<Climber>> findAllByGenderOrderByTotalPointsDesc(@RequestParam(value = "gender") Gender gender) {
        List<Climber> climbers = climberService.findAllByGenderOrderByTotalPointsDesc(gender);

        if (climbers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(climbers);
    }


}
