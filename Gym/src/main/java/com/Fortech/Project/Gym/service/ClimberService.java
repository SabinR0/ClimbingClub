package com.Fortech.Project.Gym.service;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.Gender;
import com.Fortech.Project.Gym.enums.ProjectType;
import com.Fortech.Project.Gym.enums.Skill;
import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.Project;
import com.Fortech.Project.Gym.model.User;
import com.Fortech.Project.Gym.model.request.ClimberProjectRequest;
import com.Fortech.Project.Gym.model.request.NewUserRequest;
import com.Fortech.Project.Gym.payload.request.SignupRequest;
import com.Fortech.Project.Gym.repository.ClimberProjectRepository;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ClimberService {
    private final ClimberRepository climberRepository;
    private final ProjectRepository projectRepository;
    private  final ClimberProjectRepository climberProjectRepository;

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

    public Climber getClimberByUserId(Long userId) {
        return climberRepository.findByUserId(userId);
    }

    public void deleteClimberByUserId(Long userId) {
        climberRepository.deleteClimberByUserId(userId);
    }


    public Climber completeNewProject(ClimberProjectRequest climberProjectRequest) {
        Climber climber = climberRepository.findByClimberId(climberProjectRequest.getClimberId());
        if (climber == null) {
            throw new ResourceNotFoundException("Climber not found");
        }

        Project project = projectRepository.findByProjectId(climberProjectRequest.getProjectId());
        if (project == null) {
            throw new ResourceNotFoundException("Project not found");
        }

        ClimberProject climberProject = ClimberProject.builder()
                .climberId(climber)
                .projectId(project)
                .flashStatus(climberProjectRequest.getFlashStatus())
                .build();

      //  climber.addProjectSent(climberProject);
        climberProjectRepository.save(climberProject);
        climber.recalculateStats();


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


