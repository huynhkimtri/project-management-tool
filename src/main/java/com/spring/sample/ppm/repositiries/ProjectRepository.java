package com.spring.sample.ppm.repositiries;

import com.spring.sample.ppm.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	/**
	 * Retrieves a project by its identifier.
	 *
	 * @param projectId must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal id} is {@literal null}.
	 */
	Project findByProjectIdentifier(String projectId);


	/**
	 * Returns all instances of the project.
	 *
	 * @return all projects
	 */
	@Override
	Iterable<Project> findAll();

}
