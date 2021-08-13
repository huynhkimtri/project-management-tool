package com.spring.sample.ppm.web;

import com.spring.sample.ppm.domain.Project;
import com.spring.sample.ppm.services.ProjectService;
import com.spring.sample.ppm.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ValidationError validationError;

	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errors = validationError.mapValidationService(result);
		if (Objects.nonNull(errors)) {
			return errors;
		}
		Project newProject = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<>(newProject, HttpStatus.CREATED);
	}
}
