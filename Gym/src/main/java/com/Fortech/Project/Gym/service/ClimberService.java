package com.Fortech.Project.Gym.service;

import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.enums.Skill;
import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.Project;
import com.Fortech.Project.Gym.model.User;
import com.Fortech.Project.Gym.model.request.ClimberProjectRequest;
import com.Fortech.Project.Gym.model.request.NewUserRequest;
import com.Fortech.Project.Gym.repository.ClimberProjectRepository;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClimberService {
    private final ClimberRepository climberRepository;
    private final ProjectRepository projectRepository;
    private final ClimberProjectRepository climberProjectRepository;

    @Autowired
    public ClimberService(ClimberRepository climberRepository, ProjectRepository projectRepository, ClimberProjectRepository climberProjectRepository) {
        this.climberRepository = climberRepository;
        this.projectRepository = projectRepository;
        this.climberProjectRepository = climberProjectRepository;
    }

    public List<Climber> getClimbers() {
        return climberRepository.findAll();
    }

    public List<Climber> sortClimbersByTotalPoints() {
        return climberRepository.findAll(Sort.by(Sort.Direction.DESC, "totalPoints"));
    }

    public Climber getClimberByUserId(Integer userId) {
        return climberRepository.findByUserId(userId);
    }

    public void deleteClimberByUserId(Integer userId) {
        climberRepository.deleteClimberByUserId(userId);
    }


    public Climber completeNewProject(ClimberProjectRequest climberProjectRequest) {

        Climber climber = climberRepository.findByClimberId(climberProjectRequest.getClimberId());
        Project project = projectRepository.findByProjectId(climberProjectRequest.getProjectId());
        ClimberProject climberProject = ClimberProject.builder()
                .climberId(climber)
                .projectId(project)
                .flashStatus(climberProjectRequest.getFlashStatus())
                .sendDate(climberProjectRequest.getSendDate())
                .build();
        climberProjectRepository.save(climberProject);

       // climber.addProjectsSent(climberProject);
        climber.recalculateStats();

        climberRepository.save(climber);

        return climber;


    }


}


//{
//        "climberId"  : {"climberId": 114},
//        "projectId"  : {"projectId": 352},
//        "flashStatus"  : 1,
//        "sendDate":"2022-09-11"
//
//        }