package com.Fortech.Project.Gym.repository;
import com.Fortech.Project.Gym.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {

    Club findClubByClubId(Long clubId);
}
