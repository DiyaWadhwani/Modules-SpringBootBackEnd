package com.wolken.wolkenapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wolken.wolkenapp.entity.JobEntity;
import com.wolken.wolkenapp.exception.DesignationException;
import com.wolken.wolkenapp.exception.ExperienceException;
import com.wolken.wolkenapp.exception.ResourceNotFoundException;
import com.wolken.wolkenapp.exception.SalaryException;
import com.wolken.wolkenapp.exception.WorkHoursException;
import com.wolken.wolkenapp.repository.JobRepo;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobRepo jobRepo;

	Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

	public List<JobEntity> validateAndFindAll() {

		return jobRepo.findAll();
	}

	public ResponseEntity<JobEntity> validateAndFindJobByID(int jobID) {

		logger.info("Retrieve employee by ID");

		JobEntity jobEntity = jobRepo.findById(jobID)
				.orElseThrow(() -> new ResourceNotFoundException("Job with ID " + jobID + " does not exist"));

		logger.info("Entity found using ID : " + jobID + " => " + jobEntity);
		return ResponseEntity.ok(jobEntity);
	}

	public JobEntity validateAndSave(JobEntity jobEntity) {

		JobEntity entity = null;

		try {

			if (jobEntity == null) {
				logger.warn("Entity passed is null !!");
				throw new NullPointerException();
			}
			if (jobEntity.getJobExperience().length() < 7) {
				throw new ExperienceException();
			}
			if (jobEntity.getJobDesignation().length() < 3) {
				throw new DesignationException();
			}
			if (jobEntity.getJobSalary() < 1000) {
				throw new SalaryException();
			}
			if (jobEntity.getJobWorkHours() < 3) {
				throw new WorkHoursException();
			} else {
				entity = jobRepo.save(jobEntity);
			}
		} catch (NullPointerException | ExperienceException | DesignationException | SalaryException
				| WorkHoursException e) {
			logger.warn(e + " exception Thrown");
			e.toString();
			logger.error("Failed to add entity " + jobEntity + " to the database");
		}

		return entity;
	}

	public ResponseEntity<JobEntity> validateAndUpdateJob(int jobID, JobEntity jobEntity) {

		JobEntity entity = jobRepo.findById(jobID)
				.orElseThrow(() -> new ResourceNotFoundException("Job with ID " + jobID + " does not exist"));
//		dto.setJobID(jobDTO.getJobID());

		try {

			if (entity == null) {
				logger.warn("Entity passed is null !!");
				throw new NullPointerException();
			}
			if (entity.getJobExperience().length() < 7) {
				throw new ExperienceException();
			}
			if (entity.getJobDesignation().length() < 3) {
				throw new DesignationException();
			}
			if (entity.getJobSalary() < 1000) {
				throw new SalaryException();
			}
			if (entity.getJobWorkHours() < 3) {
				throw new WorkHoursException();
			} else {
				
				entity.setJobDesignation(jobEntity.getJobDesignation());
				entity.setJobExperience(jobEntity.getJobExperience());
				entity.setJobSalary(jobEntity.getJobSalary());
				entity.setJobWorkHours(jobEntity.getJobWorkHours());
			}
		} catch (NullPointerException | ExperienceException | DesignationException | SalaryException
				| WorkHoursException e) {
			logger.warn(e + " exception Thrown");
			e.toString();
			logger.error("Failed to add entity " + jobEntity + " to the database");
		}

		JobEntity updatedJob = jobRepo.save(entity);
		
		logger.info("Saved into database : " + updatedJob);

		logger.info("New updated entity : " + entity);
		
		return ResponseEntity.ok(updatedJob);
	}

	public ResponseEntity<Map<String, Boolean>> validateAndDeleteJob(int jobID) {

		JobEntity jobEntity = jobRepo.findById(jobID)
				.orElseThrow(() -> new ResourceNotFoundException("Job with ID " + jobID + " does not exist"));
		jobRepo.delete(jobEntity);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
