package com.wolken.wolkenapp.exception;

public class FirstNameException extends Exception {
	
	@Override
	public String toString() {
		return "Invalid entry - First Name must be 3-25 characters long";
	}

}
