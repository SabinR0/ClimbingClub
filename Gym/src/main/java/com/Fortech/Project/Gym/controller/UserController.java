package com.Fortech.Project.Gym.controller;


import com.Fortech.Project.Gym.model.User;
import com.Fortech.Project.Gym.model.request.NewUserRequest;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import com.Fortech.Project.Gym.service.ClimberService;
import com.Fortech.Project.Gym.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class  UserController {

    private final ClimberRepository climberRepository;
    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ClimberService climberService;
    @Autowired
    public UserController(ClimberRepository climberRepository, UserRepository userRepository, ProjectRepository projectRepository, UserService userService, ClimberService climberService) {
        this.climberRepository = climberRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.climberService = climberService;
    }

@Transactional
@DeleteMapping(path = "/delete/{userId}")
    public void deleteUserByUserId(@PathVariable Long userId){
        climberService.deleteClimberByUserId(userId);
        userService.deleteUserByUserId(userId);
}

//@PostMapping(path = "/new")
//public User createNewUser(@RequestBody NewUserRequest newUserRequest)
//{
//    return userService.createNewUser(newUserRequest);
//}

}
