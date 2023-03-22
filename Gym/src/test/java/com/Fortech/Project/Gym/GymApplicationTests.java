package com.Fortech.Project.Gym;

import com.Fortech.Project.Gym.enums.Difficulty;
import com.Fortech.Project.Gym.enums.Skill;
import com.Fortech.Project.Gym.enums.ProjectType;
import com.Fortech.Project.Gym.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.Fortech.Project.Gym.model.Climber.*;

@SpringBootTest
class GymApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void testCalculateBoulderGrade() {
		Set<Project> completedProjects = new HashSet<>();

		Project project1 = new Project("Project 1", ProjectType.TOP_ROPE_CLIMBING, Difficulty.V19, 100);
		Project project2 = new Project("Project 2", ProjectType.SPEED_CLIMBING, Difficulty.V4,231);
		Project project3 = new Project("Project 3", ProjectType.BOULDERING, Difficulty.V0,77);
		Project project4 = new Project("Project 4", ProjectType.BOULDERING, Difficulty.V11,65);

		completedProjects.add(project1);
		completedProjects.add(project2);
		completedProjects.add(project3);
		completedProjects.add(project4);

		Difficulty result = calculateBoulderGrade(completedProjects);
		Assertions.assertEquals(Difficulty.V11, result, "Expected difficulty grade - V11");
	}


	@Test
	public void testCalculateSkillLevelAllProjects() {
		Set<Project> completedProjects = new HashSet<>();
		Project project1 = new Project("Project 1", ProjectType.TOP_ROPE_CLIMBING, Difficulty.V0,22);
//		Project project2 = new Project("Project 2", ProjectType.SPEED_CLIMBING, Difficulty.V1);
//		Project project3 = new Project("Project 3", ProjectType.BOULDERING, Difficulty.V2);
//		Project project4 = new Project("Project 4", ProjectType.BOULDERING, Difficulty.V3);
//		Project project5 = new Project("Project 5", ProjectType.TOP_ROPE_CLIMBING, Difficulty.V4);
		Project project6 = new Project("Project 6", ProjectType.SPEED_CLIMBING, Difficulty.V5,42);
//		Project project7 = new Project("Project 7", ProjectType.BOULDERING, Difficulty.V6);
//		Project project8 = new Project("Project 8", ProjectType.BOULDERING, Difficulty.V7);
//		Project project9 = new Project("Project 9", ProjectType.TOP_ROPE_CLIMBING, Difficulty.V8);
		Project project10 = new Project("Project 10", ProjectType.SPEED_CLIMBING, Difficulty.V9,64);
//		Project project11 = new Project("Project 11", ProjectType.BOULDERING, Difficulty.V10);
		Project project12 = new Project("Project 12", ProjectType.BOULDERING, Difficulty.V11,412);

		completedProjects.add(project1);
//		completedProjects.add(project2);
//		completedProjects.add(project3);
//		completedProjects.add(project4);
//		completedProjects.add(project5);
		completedProjects.add(project6);
//		completedProjects.add(project7);
//		completedProjects.add(project8);
//		completedProjects.add(project9);
		completedProjects.add(project10);
//		completedProjects.add(project11);
		completedProjects.add(project12);

		Skill skillLevel = calculateSkillLevel(completedProjects);
		Assertions.assertEquals(Skill.HIGH_ADV, skillLevel, "Expected skill grade HIGH_ADV");
	}

	@Test
	public void testCalculatePoints() {
		Set<Project> completedProjects = new HashSet<>();

		Project project1 = new Project("Project 1", ProjectType.TOP_ROPE_CLIMBING, Difficulty.V19, 100);
		Project project2 = new Project("Project 2", ProjectType.SPEED_CLIMBING, Difficulty.V4,231);
		Project project3 = new Project("Project 3", ProjectType.BOULDERING, Difficulty.V0,77);
		Project project4 = new Project("Project 4", ProjectType.BOULDERING, Difficulty.V11,65);

		completedProjects.add(project1);
		completedProjects.add(project2);
		completedProjects.add(project3);
		completedProjects.add(project4);

		Integer result = calculatePoints(completedProjects);
		Assertions.assertEquals(473, result, "Expected 473");
	}
}


