package com.Fortech.Project.Gym.model.request;
import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClimberProjectRequest {


    Integer projectId;
    Integer climberId;
    java.sql.Date sendDate;

    Boolean flashStatus;


}
