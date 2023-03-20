package com.Fortech.Project.Gym.model;

import com.Fortech.Project.Gym.enums.Difficulty;
import jakarta.persistence.*;

//import jdk.internal.util.StaticProperty;
import lombok.*;

import java.util.Collection;


@Entity
@Table(name="climber_project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ClimberProject   {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "climber_project_id")
    private Integer climberProjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "climber_id")
    private Climber climberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project projectId;


    @Column(name = "flash_status")
    private Boolean flashStatus;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "personal_rating")
    private Difficulty personalRating;

    public ClimberProject(Climber climber, Project project) {
        this.climberId = climber;
        this.projectId = project;

    }

    public Integer getClimberProjectId() {
        return climberProjectId;
    }


    public Project getProjectId() {
        return projectId;
    }


    public boolean isFlashed() {
        return true;
    }


}