package com.Fortech.Project.Gym.model;

import com.Fortech.Project.Gym.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "project_name")
    private String projectName;
    @Enumerated(EnumType.ORDINAL)
    private Difficulty difficulty;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "project_type")
    private ProjectType projectType;
    @Column(name = "project_description")
    private String projectDescription;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "project_status")
    private ProjectAvailability projectStatus;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "project_color")
    private ProjectColor projectColor;
    @Column(name = "number_of_points")
    private Integer numberOfPoints;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "climbers_rating")
    private Difficulty climbersRating;
    @Column(name = "x_coordinate")
    private Long xCoordinate;
    @Column(name = "y_coordinate")
    private Long yCoordinate;
    @JsonIgnore
    @ManyToMany(mappedBy = "projectsSent", fetch = FetchType.EAGER)
    private Set<Climber> toppedBy = new HashSet<>();

    public Project(String projectName, ProjectType type, Difficulty difficulty, Integer numberOfPoints) {
        this.projectName = projectName;
        this.projectType = type;
        this.difficulty = difficulty;
        this.numberOfPoints = numberOfPoints;
    }

    //    @ManyToOne
//    @JoinColumn(name="club_id")
//    private Club club;
//climberProjectDetails
    @JsonIgnore
    @OneToMany(mappedBy = "project")
    Set<ClimberProjectDetails> attributedClimbers = new HashSet<>();

    //
    public static Difficulty calculateClimbersRating(Set<ClimberProjectDetails> detailsSet, Project project) {
        double totalRating = 0;
        int numRatings = 0;
        for (ClimberProjectDetails item : detailsSet) {
            if (item.getProject().getProjectId().equals(project.getProjectId()) && item.getClimbersRating() != null) {
                totalRating += item.getClimbersRating().ordinal();
                numRatings++;
            }
        }
        double meanRating = totalRating / numRatings;
        int meanRatingFloor = (int) Math.floor(meanRating);

        return switch (meanRatingFloor) {
            case 0 -> Difficulty.V0;
            case 1 -> Difficulty.V1;
            case 2 -> Difficulty.V2;
            case 3 -> Difficulty.V3;
            case 4 -> Difficulty.V4;
            case 5 -> Difficulty.V5;
            case 6 -> Difficulty.V6;
            case 7 -> Difficulty.V7;
            case 8 -> Difficulty.V8;
            case 9 -> Difficulty.V9;
            case 10 -> Difficulty.V10;
            case 11 -> Difficulty.V11;
            case 12 -> Difficulty.V12;
            case 13 -> Difficulty.V13;
            case 14 -> Difficulty.V14;
            case 15 -> Difficulty.V15;
            case 16 -> Difficulty.V16;
            case 17 -> Difficulty.V17;
            case 18 -> Difficulty.V18;
            case 19 -> Difficulty.V19;
            default -> Difficulty.V20;
        };

    }


}
