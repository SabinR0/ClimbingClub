package com.Fortech.Project.Gym.model;

import com.Fortech.Project.Gym.enums.ERole;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;


    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}