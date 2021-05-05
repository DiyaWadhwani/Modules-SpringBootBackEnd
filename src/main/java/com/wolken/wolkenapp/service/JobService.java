package com.wolken.wolkenapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.wolken.wolkenapp.entity.JobEntity;

public interface JobService {

	public List<JobEntity> validateAndFindAll ();
	public ResponseEntity<JobEntity> validateAndFindJobByID(int jobID);
	public JobEntity validateAndSave(JobEntity jobEntity);
	public ResponseEntity<JobEntity> validateAndUpdateJob(int jobID, JobEntity jobEntity);
	public ResponseEntity<Map<String, Boolean>> validateAndDeleteJob(int jobID);
}
