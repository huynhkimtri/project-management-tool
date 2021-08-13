package com.spring.sample.ppm.services;

import com.spring.sample.ppm.domain.Project;
import com.spring.sample.ppm.repositiries.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		return projectRepository.save(project);
	}

}
