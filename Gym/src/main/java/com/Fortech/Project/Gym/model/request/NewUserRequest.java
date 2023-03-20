package com.Fortech.Project.Gym.model.request;

import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.enums.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class NewUserRequest {

    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private ERole ERole;


}
