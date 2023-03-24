package com.Fortech.Project.Gym.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



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

    @Column(name = "phone_number")
    private String phoneNumber;

    private String country;

    private String city;



//    @OneToMany(mappedBy="club",fetch=FetchType.EAGER)
//    @JsonIgnore
//    private Set<Project> projects = new HashSet<>();

}
