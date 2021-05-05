package com.wolken.wolkenapp.exception;

public class CompanyException extends Exception {

	@Override
	public String toString() {
		return "Invalid entry - Company name must be atleast 3 characters long";
	}
}
