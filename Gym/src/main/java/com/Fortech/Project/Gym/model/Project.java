package com.Fortech.Project.Gym.model;
import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.ProjectAvailability;
import com.Fortech.Project.Gym.enums.ProjectColor;
import com.Fortech.Project.Gym.enums.ProjectType;
import jakarta.persistence.*;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name ="project_name")
    private String projectName;
    @Enumerated(EnumType.ORDINAL)
    @Column(name ="difficulty")
    private Difficulty difficulty;
    @Enumerated(EnumType.ORDINAL)
    @Column(name ="project_type")
    private ProjectType projectType;
    @Column(name ="project_description")
    private String projectDescription;
    @Enumerated(EnumType.ORDINAL)
    @Column(name ="project_status")
    private ProjectAvailability projectStatus;
    @Enumerated(EnumType.ORDINAL)
    @Column(name ="project_color")
    private ProjectColor projectColor;
    @Column(name ="number_of_points")
    private Integer numberOfPoints;

    @OneToMany(mappedBy = "projectId")
    Set<ClimberProject> projecstsSent;


}