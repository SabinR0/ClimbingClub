package com.Fortech.Project.Gym.model.request;
import com.Fortech.Project.Gym.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectSentRequest {

    Long projectId;
    Long climberId;
    Boolean flashStatus;
    Difficulty personalRating;

}
