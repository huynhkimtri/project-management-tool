package com.spring.sample.ppm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.sample.ppm.constants.AppConst;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Project name is required")
	private String projectName;

	@NotBlank(message = "Project Identifier is required")
	@Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
	@Column(updatable = false, unique = true)
	private String projectIdentifier;

	@NotBlank(message = "Project description is required")
	private String description;

	@JsonFormat(pattern = AppConst.DATE_TIME_FORMAT)
	private Date startDate;

	@JsonFormat(pattern = AppConst.DATE_TIME_FORMAT)
	private Date endDate;

	@JsonFormat(pattern = AppConst.DATE_TIME_FORMAT)
	private Date createdAt;

	@JsonFormat(pattern = AppConst.DATE_TIME_FORMAT)
	private Date updatedAt;

	public Project() {
		// default constructor
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
