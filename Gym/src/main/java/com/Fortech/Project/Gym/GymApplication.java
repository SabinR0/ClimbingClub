package com.Fortech.Project.Gym;

import com.Fortech.Project.Gym.controller.ClimberController;
import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.repository.ClimberRepository;

import com.Fortech.Project.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import java.util.TimeZone;
@SpringBootApplication

@RestController

public class GymApplication {



    public static void main(String[] args) {
        SpringApplication.run(GymApplication.class, args);
    }


    }



