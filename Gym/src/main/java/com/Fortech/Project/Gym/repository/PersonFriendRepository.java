package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.model.PersonFriend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonFriendRepository extends JpaRepository<PersonFriend, Long> {
    // add any custom query methods here
}
