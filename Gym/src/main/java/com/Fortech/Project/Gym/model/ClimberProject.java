package com.Fortech.Project.Gym.model;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

//import jdk.internal.util.StaticProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="climber_project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClimberProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "climber_project_id")
    private Integer climberProjectId;

    @ManyToOne
    @JoinColumn(name = "climber_id")
    private Climber climberId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project projectId;

    @Column(name = "send_date")
    private Date sendDate;

    @Column(name = "flash_status")
    private Boolean flashStatus;


}