package com.Fortech.Project.Gym.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewFriendRequest {

    Long personId;
    Long friendId;

    int rating;
}
