package com.Fortech.Project.Gym.repository;

import com.Fortech.Project.Gym.model.Friend;
import com.Fortech.Project.Gym.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PersonRepository extends JpaRepository<Person, Long> {
}
