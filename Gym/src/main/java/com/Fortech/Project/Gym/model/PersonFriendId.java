package com.Fortech.Project.Gym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode

@Embeddable
class PersonFriendId implements Serializable {

    @Column(name = "person_id")
    Long personId;

    @Column(name = "friend_id")
    Long friendId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}