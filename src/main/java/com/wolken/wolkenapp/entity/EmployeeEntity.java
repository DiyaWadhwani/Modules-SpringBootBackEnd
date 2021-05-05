package com.wolken.wolkenapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee_details")

@Getter
@Setter
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int empID;
	
	@Column(name = "first_name")
	private String empFname;
	
	@Column(name = "last_name")
	private String empLname;
	
	@Column(name = "username")
	private String empUname;
	
	@Column(name = "email_id")
	private String empEmail;
	
	@Column(name = "contact")
	private long empContact;
	
	@Column(name = "company")
	private String empCompany;
	
	@Column(name = "designation")
	private String empDesignation;
	
	@Column(name = "password")
	private String empPassword;

}
