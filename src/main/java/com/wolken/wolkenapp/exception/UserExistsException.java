package com.wolken.wolkenapp.exception;

public class UserExistsException extends Exception {
	
	@Override
	public String toString() {
		return "Invalid entry - User exists";
	}

}
