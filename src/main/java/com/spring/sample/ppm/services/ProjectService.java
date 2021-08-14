package com.spring.sample.ppm.services;

import com.spring.sample.ppm.domain.Project;
import com.spring.sample.ppm.exceptions.ProjectIdentifierException;
import com.spring.sample.ppm.repositiries.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	/**
	 * Save a new project or updated an existed project
	 *
	 * @param project the request project
	 * @return the new or updated project
	 */
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception ex) {
			String message = "Project ID: " + project.getProjectIdentifier().toUpperCase() + " already existed!";
			throw new ProjectIdentifierException(message);
		}
	}

	/**
	 * Retrieves a project by its identifier
	 *
	 * @param projectId the project identifier
	 * @return the project
	 */
	public Project findByProjectId(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			String message = "Project ID: " + projectId.toUpperCase() + " does not exist!";
			throw new ProjectIdentifierException(message);
		}
		return project;
	}

	/**
	 * Returns all instances of the project.
	 *
	 * @return all projects
	 */
	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}


	/**
	 * Deletes the project with the given project identifier.
	 *
	 * @param projectId must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
	 */
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			String message = "Project ID: " + projectId.toUpperCase() + " does not exist!";
			throw new ProjectIdentifierException(message);
		}
		projectRepository.delete(project);
	}

	/**
	 * Updated an existed project
	 *
	 * @param project the request project to update
	 * @return the updated project
	 */
	public Project updateProject(Project project) {
		Project currentProject = this.findByProjectId(project.getProjectIdentifier().toUpperCase());
		currentProject.setProjectName(project.getProjectName());
		currentProject.setDescription(project.getDescription());
		currentProject.setStartDate(project.getStartDate());
		currentProject.setEndDate(project.getEndDate());
		return projectRepository.save(currentProject);
	}

}
