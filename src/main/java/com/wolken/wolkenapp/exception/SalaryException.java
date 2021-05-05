package com.wolken.wolkenapp.exception;

public class SalaryException extends Exception {

	@Override
	public String toString() {
		return "Invalid entry - Salary must be more than Rs.1000";
	}
}
