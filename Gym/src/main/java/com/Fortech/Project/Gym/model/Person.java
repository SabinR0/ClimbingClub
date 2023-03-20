package com.Fortech.Project.Gym.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Table(name = "person")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
    Set<PersonFriend> friends = new HashSet<>();

    // constructors, getters and setters


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<PersonFriend> getFriends() {
        Hibernate.initialize(friends);
        return friends;
    }

    public void addFriend(Friend friend) {
        PersonFriend personFriend = new PersonFriend(this, friend);
        friends.add(personFriend);
        friend.getFriends().add(personFriend);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriends(Set<PersonFriend> friends) {
        this.friends = friends;
    }
}
