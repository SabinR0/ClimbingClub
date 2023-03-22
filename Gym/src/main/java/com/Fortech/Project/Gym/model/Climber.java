package com.Fortech.Project.Gym.model;

import com.Fortech.Project.Gym.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.*;

import lombok.*;
import org.hibernate.Hibernate;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "climber")
public class Climber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "climber_id")
    private Long climberId;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_level")
    private Skill skillLevel;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "top_rope_grade")
    private Difficulty topRopeGrade;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "boulder_grade")
    private Difficulty boulderGrade;
    @Column(name = "total_climbs")
    private Integer totalClimbs;
    @Column(name = "total_flash")
    private Integer totalFlash;
    @Column(name = "total_points")
    private Integer totalPoints;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @JoinColumn(name = "user_id")
    private Long userId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "climber_project",
            joinColumns = {@JoinColumn(name = "climber_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )

    private Set<Project> projectsSent = new HashSet<>();

    public void addProject(Project project) {
        projectsSent.add(project);

    }
//climberProjectDetails
    @JsonIgnore
    @OneToMany(mappedBy = "climber")
    Set<ClimberProjectDetails> projectsCompleted = new HashSet<>();
    public void addProjectCompleted(ClimberProjectDetails project) {
        projectsCompleted.add(project);

    }

    public static Integer calculateTotalFlashed(Set<ClimberProjectDetails> detailsSet, Climber climber) {
        int totalFlashed = 0;
        for (ClimberProjectDetails item : detailsSet) {
            if (item.getClimber().getClimberId().equals(climber.getClimberId()) && item.getFlashStatus() != null && item.getFlashStatus()) {
                totalFlashed++;
            }
        }
        return totalFlashed;
    }


    public static Skill calculateSkillLevel(Set<Project> completedProjects) {
        double sumDifficulty = 0;
        for (Project item : completedProjects) {
            sumDifficulty += item.getDifficulty().ordinal();
        }

        double meanDifficulty = sumDifficulty / completedProjects.size();

        int meanDifficultyFloor = (int) Math.floor(meanDifficulty);
        return switch (meanDifficultyFloor) {
            case 0 -> Skill.BEGINNER;
            case 1 -> Skill.NOVICE;
            case 2 -> Skill.INTERMEDIATE;
            case 3 -> Skill.ADV_BEG;
            case 4 -> Skill.INT_ADV;
            case 5 -> Skill.ADVANCED;
            case 6 -> Skill.HIGH_ADV;
            case 7 -> Skill.EXPERT;
            case 8 -> Skill.ELITE;
            case 9 -> Skill.MASTER;
            case 10 -> Skill.PRODIGY;
            case 11 -> Skill.VIRTUOSO;
            case 12 -> Skill.PHENOMENAL;
            case 13 -> Skill.EXCEPTIONAL;
            case 14 -> Skill.EXTRAORDINARY;
            case 15 -> Skill.INCREDIBLE;
            case 16 -> Skill.SUPERHUMAN;
            case 17 -> Skill.LEGENDARY;
            case 18 -> Skill.MYTHICAL;
            case 19 -> Skill.GODLIKE;
            default -> Skill.OTHERWORLDLY;
        };
    }


    public static Difficulty calculateBoulderGrade(Set<Project> completedProjects) {

        Difficulty currentDifficulty = Difficulty.V0;

        for (Project item : completedProjects) {
            Difficulty projectDifficulty = item.getDifficulty();
            if (item.getProjectType().equals(ProjectType.BOULDERING) && projectDifficulty.compareTo(currentDifficulty) > 0) {
                currentDifficulty = projectDifficulty;
            } else {
                continue;
            }
        }

        return currentDifficulty;
    }

    public static Difficulty calculateTopRopeGrade(Set<Project> completedProjects) {

        Difficulty currentDifficulty = Difficulty.V0;

        for (Project item : completedProjects) {
            Difficulty projectDifficulty = item.getDifficulty();
            if (item.getProjectType().equals(ProjectType.TOP_ROPE_CLIMBING) && projectDifficulty.compareTo(currentDifficulty) > 0) {
                currentDifficulty = projectDifficulty;
            } else {
                continue;
            }
        }

        return currentDifficulty;
    }


    public static Integer calculatePoints(Set<Project> completedProjects) {
        int totalPoints = 0;
        for (Project item : completedProjects) {
            totalPoints += item.getNumberOfPoints();
        }
        return totalPoints;
    }


    public void recalculateStats() {


        this.totalClimbs = projectsSent.size();
        // this.totalFlash = calculateTotalFlashed(projectsSent);
        this.totalPoints = calculatePoints(projectsSent);
        this.skillLevel = calculateSkillLevel(projectsSent);
        this.topRopeGrade = calculateTopRopeGrade(projectsSent);
        this.boulderGrade = calculateBoulderGrade(projectsSent);
    }


}
