package com.Fortech.Project.Gym.model.request;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.ProjectAvailability;
import com.Fortech.Project.Gym.enums.ProjectColor;
import com.Fortech.Project.Gym.enums.ProjectType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor

public class NewProjectRequest {
    private String projectName;
    private String projectDescription;
    private Integer numberOfPoints;
    @Enumerated(EnumType.ORDINAL)
    private ProjectAvailability projectStatus;
    @Enumerated(EnumType.ORDINAL)
    private ProjectColor projectColor;
    @Enumerated(EnumType.ORDINAL)
    private Difficulty difficulty;
    @Enumerated(EnumType.ORDINAL)
    private ProjectType projectType;


}
