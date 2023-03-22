package com.Fortech.Project.Gym.service;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.enums.Skill;
import com.Fortech.Project.Gym.model.*;
import com.Fortech.Project.Gym.model.request.ProjectSentRequest;
import com.Fortech.Project.Gym.payload.request.SignupRequest;
import com.Fortech.Project.Gym.repository.ClimberProjectDetailsRepository;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationShutdownHandlers;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.Fortech.Project.Gym.model.Climber.calculateTotalFlashed;
import static com.Fortech.Project.Gym.model.ClimberProjectDetails.detailsSet;
import static com.Fortech.Project.Gym.model.Project.calculateClimbersRating;

@Service
public class ClimberService {
    private final ClimberRepository climberRepository;
    private final ProjectRepository projectRepository;

    private final ClimberProjectDetailsRepository climberProjectDetailsRepository;

    @Autowired
    public ClimberService(ClimberRepository climberRepository, ProjectRepository projectRepository, ClimberProjectDetailsRepository climberProjectDetailsRepository) {
        this.climberRepository = climberRepository;
        this.projectRepository = projectRepository;
        this.climberProjectDetailsRepository = climberProjectDetailsRepository;
    }

    public List<Climber> getClimbers() {
        return climberRepository.findAll();
    }

    public List<Climber> sortClimbersByTotalPoints() {
        return climberRepository.findAll(Sort.by(Sort.Direction.DESC, "totalPoints"));
    }

    public Climber getClimberByUserId(Long userId) {
        return climberRepository.findByUserId(userId);
    }

    public void deleteClimberByUserId(Long userId) {
        climberRepository.deleteClimberByUserId(userId);
    }


    public Climber completeNewProject(ProjectSentRequest projectSentRequest) {
        Climber climber = climberRepository.findByClimberId(projectSentRequest.getClimberId());
        if (climber == null) {
            throw new ResourceNotFoundException("Climber not found");
        }

        Project project = projectRepository.findByProjectId(projectSentRequest.getProjectId());
        if (project == null) {
            throw new ResourceNotFoundException("Project not found");
        }

        ClimberProjectKey climberProjectKey = new ClimberProjectKey(climber.getClimberId(),project.getProjectId());

        ClimberProjectDetails climberProjectDetails = ClimberProjectDetails.builder()
                .climberProjectKey(climberProjectKey)
                .climber(climber)
                .project(project)
                .climbersRating(projectSentRequest.getPersonalRating())
                .flashStatus(projectSentRequest.getFlashStatus())
                .build();

        detailsSet.add(climberProjectDetails);
        climberProjectDetailsRepository.save(climberProjectDetails);
        project.setClimbersRating(calculateClimbersRating(detailsSet,project));
        climber.setTotalFlash(calculateTotalFlashed(detailsSet,climber));
        projectRepository.save(project);
        climber.addProject(project);
        climber.recalculateStats();
        climberRepository.save(climber);

        return climber;
    }




    public void createClimberStats(User user, SignupRequest signUpRequest) {
        Climber climber = Climber.builder()
                .userId(user.getUserId())
                .gender(signUpRequest.getGender())
                .totalClimbs(0)
                .skillLevel(Skill.BEGINNER)
                .totalFlash(0)
                .totalPoints(0)
                .boulderGrade(Difficulty.V0)
                .topRopeGrade(Difficulty.V0)
                .build();
        climberRepository.save(climber);
    }

    public List<Climber> findAllByGenderOrderByTotalPointsDesc(Gender gender) {
        return climberRepository.findAllByGenderOrderByTotalPointsDesc(gender);
    }


}


