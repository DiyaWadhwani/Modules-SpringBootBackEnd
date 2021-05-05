package com.wolken.wolkenapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wolken.wolkenapp.dto.EmployeeDTO;
import com.wolken.wolkenapp.entity.EmployeeEntity;

public interface EmpRepo extends JpaRepository<EmployeeEntity, Integer> {
	
	public EmployeeEntity findByEmpUname(String empUname);
	public EmployeeEntity findByEmpFname(String empFname);
	public EmployeeEntity findByEmpLname(String empLname);
}
