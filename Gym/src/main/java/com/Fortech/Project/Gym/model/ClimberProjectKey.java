package com.Fortech.Project.Gym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ClimberProjectKey implements Serializable {

    @Column (name = "climber_id")
    Long climberId;

   @Column(name = "project_id")
    Long projectId;
}
