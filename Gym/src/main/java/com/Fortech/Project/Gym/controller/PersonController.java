package com.Fortech.Project.Gym.controller;


import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.Person;
import com.Fortech.Project.Gym.model.PersonFriend;
import com.Fortech.Project.Gym.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/friends")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/person/{personId}/friends")
    public Set<PersonFriend> getFriends(@PathVariable Long personId) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + personId));
        return person.getFriends();
    }

//    @PutMapping("/pleasework/{id}")
//    public ResponseEntity<Climber> addFriend(@PathVariable Long id) {
//        Person person = personRepository.findById(id).orElseThrow(RuntimeException::new);
//        currentClient.recalculateStats();
//        climberRepository.save(currentClient);
//
//        return ResponseEntity.ok(currentClient);
//    }
}
