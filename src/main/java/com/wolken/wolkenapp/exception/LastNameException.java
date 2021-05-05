package com.wolken.wolkenapp.exception;

public class LastNameException extends Exception {
	
	@Override
	public String toString() {
		return "Invalid entry - Last Name must be 0-30 characters long";
	}


}
