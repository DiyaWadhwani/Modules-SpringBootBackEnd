package com.wolken.wolkenapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolken.wolkenapp.dto.LoginDTO;
import com.wolken.wolkenapp.entity.EmployeeEntity;
import com.wolken.wolkenapp.service.EmployeeService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@GetMapping("/getEmpByID/{empID}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable int empID) {
		return employeeService.validateAndFindByID(empID);
	}
	
	@PostMapping("/createEmployee")
	public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employeeEntity) {
		
		EmployeeEntity empEntity = new EmployeeEntity();
		
		try {
			logger.info("\nEntering try block to register employee\n");
			empEntity = employeeService.validateAndCreateEmployee(employeeEntity);
			logger.info("New user registered : "+empEntity);
		}
		catch(NullPointerException e) {
			logger.warn(e+" exception thrown");
			e.toString();
		}
		if(empEntity != null) {
			return new ResponseEntity<EmployeeEntity>(empEntity, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<EmployeeEntity>(empEntity, HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@PutMapping("/updateEmpByID/{empID}")
	public ResponseEntity<EmployeeEntity> updateEmployeeById(@PathVariable int empID, @RequestBody EmployeeEntity employeeEntity ) {
		
		return employeeService.validateAndUpdateEmployee(empID, employeeEntity);
	}
	
	@PostMapping("/loginEmployee")
	public ResponseEntity<LoginDTO> loginEmployee(@RequestBody LoginDTO loginDTO) {
		
		LoginDTO logDTO = null;
			
		try {
			logDTO = employeeService.validateAndLoginEmployee(loginDTO);
		}
		catch(NullPointerException e) {
			logger.warn(e+" exception thrown");
			e.toString();
		}
		if(logDTO != null) {
			return new ResponseEntity<LoginDTO>(logDTO,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<LoginDTO>(logDTO, HttpStatus.FAILED_DEPENDENCY);
		}
	}
}