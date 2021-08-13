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

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception ex) {
			String message = new StringBuilder()
					.append("Project ID: ")
					.append(project.getProjectIdentifier())
					.append(" already existed!")
					.toString();
			throw new ProjectIdentifierException(message);
		}
	}

	public Project findByProjectId(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if (project == null) {
			String message = new StringBuilder()
					.append("Project ID: ")
					.append(projectId.toUpperCase())
					.append(" does not exist!")
					.toString();
			throw new ProjectIdentifierException(message);
		}
		return project;
	}

}
