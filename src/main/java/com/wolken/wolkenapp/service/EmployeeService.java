package com.wolken.wolkenapp.service;

import org.springframework.http.ResponseEntity;

import com.wolken.wolkenapp.dto.LoginDTO;
import com.wolken.wolkenapp.entity.EmployeeEntity;

public interface EmployeeService {

	public LoginDTO validateAndLoginEmployee(LoginDTO loginDTO);
	
	public ResponseEntity<EmployeeEntity> validateAndFindByID(int empID);
	public EmployeeEntity validateAndCreateEmployee(EmployeeEntity employeeEntity);
	public ResponseEntity<EmployeeEntity> validateAndUpdateEmployee(int empID, EmployeeEntity employeeEntity);
}
