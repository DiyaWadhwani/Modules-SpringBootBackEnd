package com.wolken.wolkenapp.exception;

public class NameException extends Exception {
	
	@Override
	public String toString() {
		return "Invalid entry - First Name must be 3-15 characters long";
	}

}
