package com.Fortech.Project.Gym.service;

import com.Fortech.Project.Gym.model.Climber;
import com.Fortech.Project.Gym.model.ClimberProject;
import com.Fortech.Project.Gym.model.Project;
import com.Fortech.Project.Gym.repository.ClimberProjectRepository;
import com.Fortech.Project.Gym.repository.ClimberRepository;
import com.Fortech.Project.Gym.repository.ProjectRepository;
import com.Fortech.Project.Gym.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClimberProjectService {
    private final ClimberRepository climberRepository;

    private final ProjectRepository projectRepository;

    private final ClimberProjectRepository climberProjectRepository;

    private final UserRepository userRepository;

    @Autowired
    public ClimberProjectService(ClimberRepository climberRepository, ProjectRepository projectRepository, ClimberProjectRepository climberProjectRepository, UserRepository userRepository) {
        this.climberRepository = climberRepository;
        this.projectRepository = projectRepository;
        this.climberProjectRepository = climberProjectRepository;
        this.userRepository = userRepository;
    }

//    public void markProjectFlashed(Long climberId, Long projectId, Boolean flashed) {
//        ClimberProject climberProject = climberProjectRepository.findByClimberIdAndProjectId(climberId, projectId);
//        if (climberProject == null) {
//            Climber climber = new Climber();
//            climber.setId(climberId);
//            Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
//            climberProject = new ClimberProject();
//            climberProject.setClimber(climber);
//            climberProject.setProject(project);
//        }
//        climberProject.setFlashed(flashed);
//        climberProjectRepository.save(climberProject);



    public Set <Climber> findClimbersByProjectId(Project projectId){
        return climberProjectRepository.findClimbersByProjectId(projectId);
    }
    public Set <Project> findProjectsByClimberId(Climber climberId){
        return climberProjectRepository.findProjectsByClimberId(climberId);
    }


    public Set<ClimberProject> getProjectsSentByUserId(Integer userId) {
        return climberProjectRepository.findByClimberIdUserId(userId);
    }
   }
