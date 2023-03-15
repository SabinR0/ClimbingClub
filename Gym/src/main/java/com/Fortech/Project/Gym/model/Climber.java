package com.Fortech.Project.Gym.model;

import com.Fortech.Project.Gym.enums.*;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.List;


@Entity
@Table(name = "climber")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Climber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "climber_id")
    private Integer climberId;
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
    @Column(name = "gender")
    private Gender gender;

    @JoinColumn(name = "user_id")
    private Integer userId;

    @OneToMany(mappedBy = "climberId")
    Set<ClimberProject> projectsSent;


    public Integer calculateTotalFlashed(Set<ClimberProject> completedProjects) {
        int totalFlashed = 0;
        for (ClimberProject item : completedProjects) {
            if (item.getFlashStatus()) {
                totalFlashed++;
            }
        }
        return totalFlashed;
    }

    Skill calculateSkillLevel(Set<ClimberProject> completedProjects) {
        double sumDifficulty = 0;
        for (ClimberProject item : completedProjects) {
            sumDifficulty += item.getProjectId().getDifficulty().ordinal();
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


    public static Difficulty calculateBoulderGrade(Set<ClimberProject> completedProjects) {

        Difficulty currentDifficulty = Difficulty.V0;

        for (ClimberProject item : completedProjects) {
            Difficulty projectDifficulty = item.getProjectId().getDifficulty();
            if (item.getProjectId().getProjectType().equals(ProjectType.BOULDERING) && currentDifficulty == Difficulty.V0 || projectDifficulty.compareTo(currentDifficulty) > 0) {
                currentDifficulty = projectDifficulty;
            }
        }

        return currentDifficulty;
    }

    public static Difficulty calculateTopRopeGrade(Set<ClimberProject> completedProjects) {

        Difficulty currentDifficulty = Difficulty.V0;

        for (ClimberProject item : completedProjects) {
            Difficulty projectDifficulty = item.getProjectId().getDifficulty();
            if (item.getProjectId().getProjectType().equals(ProjectType.TOP_ROPE_CLIMBING) && currentDifficulty == Difficulty.V0 || projectDifficulty.compareTo(currentDifficulty) > 0) {
                currentDifficulty = projectDifficulty;
            }
        }

        return currentDifficulty;
    }


    Integer calculatePoints(Set<ClimberProject> completedProjects) {
        int totalPoints = 0;
        for (ClimberProject item : completedProjects) {
            totalPoints += item.getProjectId().getNumberOfPoints();
        }
        return totalPoints;
    }


    public void recalculateStats() {
        this.totalClimbs = projectsSent.size();
        this.totalFlash = calculateTotalFlashed(projectsSent);
        this.totalPoints = calculatePoints(projectsSent);
        this.skillLevel = calculateSkillLevel(projectsSent);
        this.topRopeGrade = calculateTopRopeGrade(projectsSent);
        this.boulderGrade = calculateBoulderGrade(projectsSent);
    }

    public void addProjectsSent(ClimberProject climberProject) {
        projectsSent.add(climberProject);
    }
}
