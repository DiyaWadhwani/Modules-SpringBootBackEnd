package com.wolken.wolkenapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "job_details")
public class JobEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int jobID;
	
	@Column(name = "designation")
	private String jobDesignation;
	
	@Column(name = "experience")
	private String jobExperience;
	
	@Column(name = "salary")
	private float jobSalary;
	
	@Column(name = "work_hours")
	private int jobWorkHours;

}
