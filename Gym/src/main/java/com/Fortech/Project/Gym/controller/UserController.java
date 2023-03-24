package com.Fortech.Project.Gym.controller;

import com.Fortech.Project.Gym.service.ClimberService;
import com.Fortech.Project.Gym.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final ClimberService climberService;

    @Autowired
    public UserController(UserService userService, ClimberService climberService) {
        this.userService = userService;
        this.climberService = climberService;
    }

    @Transactional
    @DeleteMapping(path = "/delete/{userId}")
    public void deleteUserByUserId(@PathVariable Long userId) {
        climberService.deleteClimberByUserId(userId);
        userService.deleteUserByUserId(userId);
    }


}
