package com.Fortech.Project.Gym.model;

import jakarta.persistence.*;
@Table(name = "friend_person")
@Entity
public
class PersonFriend {

    @EmbeddedId
    PersonFriendId id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    Person person;

    @ManyToOne
    @MapsId("friendId")
    @JoinColumn(name = "friend_id")
    Friend friend;
    @Column(name = "rating")
    int rating;

    public PersonFriend(Person person, Friend friend) {
        this.person = person;
        this.friend = friend;
    }

    // standard constructors, getters, and setters
}
