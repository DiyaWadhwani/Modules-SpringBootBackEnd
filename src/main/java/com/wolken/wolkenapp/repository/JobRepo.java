package com.wolken.wolkenapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wolken.wolkenapp.entity.JobEntity;

public interface JobRepo extends JpaRepository<JobEntity, Integer>{
	
	public void delete(JobEntity jobEntity);
}
