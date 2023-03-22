package com.Fortech.Project.Gym.model;

import java.util.*;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.enums.Skill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "club_name")
    private String clubName;

    private String country;

    private String city;
    @Column(name = "phone_number")
    private String phoneNumber;


//    @OneToMany(mappedBy="club",fetch=FetchType.EAGER)
//    @JsonIgnore
//    private Set<Project> projects = new HashSet<>();

}
