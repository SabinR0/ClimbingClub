package com.Fortech.Project.Gym.model.request;
import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClimberProjectRequest {


    Long projectId;
    Long climberId;

//    Difficulty personalRating;
//
//    Boolean flashStatus;
//
//    public Boolean getFlashStatus() {
//        return flashStatus;
//    }
}
