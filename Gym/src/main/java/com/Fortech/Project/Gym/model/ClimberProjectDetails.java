package com.Fortech.Project.Gym.model;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "climber_project_details")
public class ClimberProjectDetails {

    @EmbeddedId
    ClimberProjectKey climberProjectKey;
    @JsonIgnore
    @ManyToOne
    @MapsId("climberId")
    @JoinColumn(name = "climber_id")
    private Climber climber;
    @JsonIgnore
    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "climber_rating")
    private Difficulty climbersRating;

    @Column(name = "flash_status")
    private Boolean flashStatus;

    public Boolean getFlashStatus() {
        return flashStatus;
    }

    public static Set<ClimberProjectDetails> detailsSet = new HashSet<>();

}
