package com.Fortech.Project.Gym.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Table(name = "friend")
@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "friend")
    private Set<PersonFriend> friends = new HashSet<>();

    public Set<PersonFriend> getFriends() {
        return friends;
    }

    public void setFriends(Set<PersonFriend> friends) {
        this.friends = friends;
    }
}
