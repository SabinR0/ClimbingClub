package com.Fortech.Project.Gym.model.request;
import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectSentRequest {


    Long projectId;
    Long climberId;

    Difficulty personalRating;
    Boolean flashStatus;

}
