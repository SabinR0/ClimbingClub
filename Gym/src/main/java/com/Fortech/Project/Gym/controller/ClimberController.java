package com.Fortech.Project.Gym.controller;

import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.request.ClimberProjectRequest;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import com.Fortech.Project.Gym.service.ClimberService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping(path = "/all")
    public List<Climber> getClimbers() {
        return climberService.getClimbers();
    }

    @GetMapping("/ladder")
    public List<Climber> getSortedClimbersByTotalPoints() {
        return climberService.sortClimbersByTotalPoints();
    }

    @GetMapping("/{userId}")
    public Climber getClimberByUserId(@PathVariable Integer userId) {
        return climberService.getClimberByUserId(userId);
    }


    @PostMapping("/update-stats")
    Climber completeNewProject(@RequestBody ClimberProjectRequest climberProjectRequest){
        return climberService.completeNewProject(climberProjectRequest);

    }




//
//    @GetMapping("/{id}")
//    public Climber getClimber(@PathVariable Integer climberId) {
//        return climberRepository.findById(climberId).orElseThrow(RuntimeException::new);
//    }
//
//    @PostMapping
//    public ResponseEntity createClimber(@RequestBody Climber climber) throws URISyntaxException {
//        Climber savedClimber = climberRepository.save(climber);
//        return ResponseEntity.created(new URI("/clients/" + savedClimber.getClimberId())).body(savedClimber);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity updateClimber(@PathVariable Integer id, @RequestBody Climber climber) {
//        Climber currentClient = climberRepository.findById(id).orElseThrow(RuntimeException::new);
//        currentClient.setTotalClimbs(climber.getTotalClimbs());
//        currentClient = climberRepository.save(climber);
//
//        return ResponseEntity.ok(currentClient);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteClimber(@PathVariable Integer climberId) {
//        climberRepository.deleteById(climberId);
//        return ResponseEntity.ok().build();
//    }


}
