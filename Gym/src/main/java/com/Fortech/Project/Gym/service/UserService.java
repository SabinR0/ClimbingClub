package com.Fortech.Project.Gym.service;

import com.Fortech.Project.Gym.model.User;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final ClimberRepository climberRepository;
    private final UserRepository userRepository;


    @Autowired
    public UserService(ClimberRepository climberRepository, UserRepository userRepository) {
        this.climberRepository = climberRepository;

        this.userRepository = userRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        climberRepository.deleteById(userId);
    }


    public void deleteUserByUserId(Long userId) {
        userRepository.deleteUserByUserId(userId);
    }

}



