package com.Fortech.Project.Gym.service;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.User;
import com.Fortech.Project.Gym.model.request.NewUserRequest;
import com.Fortech.Project.Gym.repository.ClimberProjectRepository;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Fortech.Project.Gym.enums.Skill;

import java.util.List;


@Service
public class UserService {
    private final ClimberRepository climberRepository;

    private final ProjectRepository projectRepository;

    private final ClimberProjectRepository climberProjectRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserService(ClimberRepository climberRepository, ProjectRepository projectRepository, ClimberProjectRepository climberProjectRepository, UserRepository userRepository) {
        this.climberRepository = climberRepository;
        this.projectRepository = projectRepository;
        this.climberProjectRepository = climberProjectRepository;
        this.userRepository = userRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
        climberRepository.deleteById(userId);
    }

    public User createNewUser(NewUserRequest newUserRequest) {
        User newUser = User.builder()
                .userName(newUserRequest.getUserName())
                .email(newUserRequest.getEmail())
                .firstName(newUserRequest.getFirstName())
                .lastName(newUserRequest.getLastName())
                .password(newUserRequest.getPassword())
                .role(newUserRequest.getRole())
                .build();

        User savedUser = userRepository.save(newUser);

        Climber climber = Climber.builder()
                .userId(newUser.getUserId())
                .gender(newUserRequest.getGender())
                .totalClimbs(0)
                .skillLevel(Skill.BEGINNER)
                .totalFlash(0)
                .totalPoints(0)
                .boulderGrade(Difficulty.V0)
                .topRopeGrade(Difficulty.V0)
                .build();
        climberRepository.save(climber);

        return savedUser;
    }

    public void deleteUserByUserId(Integer userId){
        userRepository.deleteUserByUserId(userId);
    }
}



