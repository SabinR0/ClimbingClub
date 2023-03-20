package com.Fortech.Project.Gym.model;
import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.ProjectAvailability;
import com.Fortech.Project.Gym.enums.ProjectColor;
import com.Fortech.Project.Gym.enums.ProjectType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(name="project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Project  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Long projectId;
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
    @Enumerated(EnumType.ORDINAL)
    @Column(name ="climbers_rating")
    private Difficulty climbersRating;

    @Column(name ="x_coordinate")
    private Long xCoordinate;
    @Column(name ="y_coordinate")
    private Long yCoordinate;
    @JsonIgnore
    @OneToMany(mappedBy = "projectId",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    Set<ClimberProject> toppedBy = new HashSet<>();


    public void addClimber(ClimberProject climber) {
        toppedBy.add(climber);
       // climber.getProjectsSent().add(this);
    }
    public Set<ClimberProject> getToppedBy() {
        return toppedBy;

    }
    public static Difficulty calculateClimbersRating(Set<ClimberProject> climberProjects) {
        Difficulty currentDifficulty = Difficulty.V0;
        for (ClimberProject item : climberProjects) {
            Difficulty projectDifficulty = item.getProjectId().getClimbersRating();
            if (item.getProjectId().getProjectType().equals(ProjectType.BOULDERING) && projectDifficulty.compareTo(currentDifficulty) > 0) {
                currentDifficulty = projectDifficulty;
            }
        }
        return currentDifficulty;
    }
}
