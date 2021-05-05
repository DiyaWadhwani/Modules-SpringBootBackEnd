package com.wolken.wolkenapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wolken.wolkenapp.dto.LoginDTO;
import com.wolken.wolkenapp.entity.EmployeeEntity;
import com.wolken.wolkenapp.exception.CompanyException;
import com.wolken.wolkenapp.exception.ContactException;
import com.wolken.wolkenapp.exception.DesignationException;
import com.wolken.wolkenapp.exception.EmailException;
import com.wolken.wolkenapp.exception.FirstNameException;
import com.wolken.wolkenapp.exception.LastNameException;
import com.wolken.wolkenapp.exception.PasswordException;
import com.wolken.wolkenapp.exception.ResourceNotFoundException;
import com.wolken.wolkenapp.exception.UserExistsException;
import com.wolken.wolkenapp.exception.UsernameException;
import com.wolken.wolkenapp.repository.EmpRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmpRepo empRepo;

	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	public LoginDTO validateAndLoginEmployee(LoginDTO loginDTO) {
		
		logger.info("Emmployee login");
		LoginDTO dto = null;
		
		try {
			if(loginDTO.getEmpUname().length() < 3 || loginDTO.getEmpUname().length() > 25) {
				throw new UsernameException();
			}
			if(loginDTO.getEmpPassword().length() < 6) {
				throw new PasswordException();
			}
			else {
				EmployeeEntity employeeEntity = empRepo.findByEmpUname(loginDTO.getEmpUname());
				if(employeeEntity != null) {
					
					if (employeeEntity.getEmpPassword().equals(loginDTO.getEmpPassword())) {
						logger.info("Passwords match. Login successful !!");
						dto = loginDTO;
					}
					else {
//						loginDTO.setEmpUname(null);
//						loginDTO.setEmpPassword(null);
						dto = null;
					}
				}
				else {
//					loginDTO.setEmpUname(null);
//					loginDTO.setEmpPassword(null);
					dto = null;
				}
			}
		}
		catch(UsernameException | PasswordException e) {
			logger.warn(e + " exception Thrown");
			e.toString();
			logger.error("Failed to Login !");
		}
		
		return dto;
	}

	public ResponseEntity<EmployeeEntity> validateAndFindByID(int empID) {

		logger.info("Retrieve employee by ID");

		EmployeeEntity employeeEntity = empRepo.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + empID + " does not exist"));

		logger.info("Entity found using ID : " + empID + " => " + employeeEntity);
		return ResponseEntity.ok(employeeEntity);
	}

	public EmployeeEntity validateAndCreateEmployee(EmployeeEntity employeeEntity) {
		
		
		
		EmployeeEntity entity = null;
		
		try {
			logger.info("Entered try block in service method to register employee");
			logger.info("Checking if passed entity is null");
			if (employeeEntity == null) {
				logger.warn("Entity passed is null !!");
				throw new NullPointerException();
			}
			logger.info("passed entity is not null");
			logger.info("Checking if user exists");
			entity = empRepo.findByEmpUname(employeeEntity.getEmpUname());
			logger.info("Find by username : "+entity);
			if(entity == null) {
				logger.info("No entry with given username found");
				entity = empRepo.findByEmpFname(employeeEntity.getEmpFname());
				logger.info("Find by firstname : "+entity);
				if(entity == null) {
					logger.info("No entry with given firstname found");
					entity = empRepo.findByEmpLname(employeeEntity.getEmpLname());
					logger.info("Find by lastname : "+entity);
					if(entity != null) {
						logger.info("Entry with given lastname found, checking if username or fullname match");
						if(entity.getEmpUname().equals(employeeEntity.getEmpUname()) || (
								entity.getEmpFname().equals(employeeEntity.getEmpFname()) &&
								entity.getEmpLname().equals(employeeEntity.getEmpLname()))) {
							logger.info("Either username or fullname already exists");
							logger.warn("User already exists");
							logger.error("Registration failed");
							entity = null;
//							return entity;
							throw new UserExistsException();
							}
					}
				}
			}
			else if(entity!=null) {
				if(entity.getEmpUname().equals(employeeEntity.getEmpUname()) && (
						entity.getEmpFname().equals(employeeEntity.getEmpFname()) ||
						entity.getEmpLname().equals(employeeEntity.getEmpLname()))) {
					logger.warn("User already exists");
					logger.error("Registration failed");
					entity = null;
//					return entity;
					throw new UserExistsException();
					}
			}
			logger.info("User does not exist");
			logger.info("Validating First name");
			if (employeeEntity.getEmpFname().length() < 3 || employeeEntity.getEmpFname().length() > 25) {
				throw new FirstNameException();
			}
			logger.info("Validating Last name");
			if(employeeEntity.getEmpLname().length() > 30) {
				throw new LastNameException();
			}
			logger.info("Validating Username");
			if (employeeEntity.getEmpUname().length() < 3 || employeeEntity.getEmpUname().length() > 25) {
				throw new UsernameException();
			}
			logger.info("Validating Email");
			if (employeeEntity.getEmpEmail().length() < 10) {
				throw new EmailException();
			}
			logger.info("Validating Contact");
			if (String.valueOf(employeeEntity.getEmpContact()).length() < 10) {
				throw new ContactException();
			}
			logger.info("Validating Company");
			if (employeeEntity.getEmpCompany().length() < 3) {
				throw new CompanyException();
			}
			logger.info("Validating Designation");
			if (employeeEntity.getEmpDesignation().length() < 3) {
				throw new DesignationException();
			}
			logger.info("Validating Password");
			if (employeeEntity.getEmpPassword().length() < 6 || employeeEntity.getEmpPassword().length() > 25) {
				throw new PasswordException();
			}
			else {
				logger.info("Validation complete, adding to db");
				logger.info("Entity " + employeeEntity + " added into database");
				entity = empRepo.save(employeeEntity);
			}
		} catch (NullPointerException | FirstNameException | LastNameException | UsernameException | EmailException | ContactException
				| CompanyException | DesignationException | PasswordException | UserExistsException e) {
			
			logger.warn(e + " exception Thrown");
			e.toString();
			logger.error("Failed to add entity " + employeeEntity + " to the database");
		}
		return entity;
	}

	public ResponseEntity<EmployeeEntity> validateAndUpdateEmployee(int empID, EmployeeEntity employeeEntity) {

		logger.info("Update employee by ID");

		EmployeeEntity empEntity = empRepo.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + empID + " does not exist"));

		logger.info("Entity found with ID : " + empID + " => " + employeeEntity);

		// don't reset the ID
//		empEntity.setEmpID(employeeEntity.getEmpID());

		try {

			if (empEntity == null) {
				logger.warn("Entity passed is null !!");
				throw new NullPointerException();
			}
			if (empEntity.getEmpFname().length() < 3 || empEntity.getEmpFname().length() > 15) {
				throw new FirstNameException();
			}
			if(empEntity.getEmpLname().length() > 30) {
				throw new LastNameException();
			}
			if (empEntity.getEmpUname().length() < 3 || empEntity.getEmpUname().length() > 25) {
				throw new UsernameException();
			}
			if (empEntity.getEmpEmail().length() < 10) {
				throw new EmailException();
			}
			if (String.valueOf(empEntity.getEmpContact()).length() < 10) {
				throw new ContactException();
			}
			if (empEntity.getEmpCompany().length() < 3) {
				throw new CompanyException();
			}
			if (employeeEntity.getEmpDesignation().length() < 3) {
				throw new DesignationException();
			}
			if (empEntity.getEmpPassword().length() < 6) {
				throw new PasswordException();
			} else {
				empEntity.setEmpFname(employeeEntity.getEmpFname());
				empEntity.setEmpLname(employeeEntity.getEmpLname());
				empEntity.setEmpUname(employeeEntity.getEmpUname());
				empEntity.setEmpContact(employeeEntity.getEmpContact());
				empEntity.setEmpCompany(employeeEntity.getEmpCompany());
				empEntity.setEmpDesignation(employeeEntity.getEmpDesignation());
				empEntity.setEmpEmail(employeeEntity.getEmpEmail());
				empEntity.setEmpPassword(employeeEntity.getEmpPassword());
				logger.info("Entity " + employeeEntity + " added into database");
			}
		} catch (NullPointerException | FirstNameException | LastNameException | UsernameException | EmailException | ContactException | CompanyException | DesignationException | PasswordException e) {
			logger.warn(e + " exception Thrown");
			e.toString();
			logger.error("Failed to add entity " + employeeEntity + " to the database");
		}

		EmployeeEntity updatedEmp = empRepo.save(empEntity);

		logger.info("Saved into database : " + updatedEmp);

		logger.info("New updated entity : " + empEntity);

		return ResponseEntity.ok(updatedEmp);
	}
}
