package com.wolken.wolkenapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
	
	private int empID;
	private String empFname;
	private String empLname;
	private String empUname;
	private String empEmail;
	private long empContact;
	private String empCompany;
	private String empDesignation;
	private String empPassword;
	private String empConfirmPassword;

}
