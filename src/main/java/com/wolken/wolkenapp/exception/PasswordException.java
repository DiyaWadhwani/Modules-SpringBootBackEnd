package com.wolken.wolkenapp.exception;

public class PasswordException extends Exception {

	@Override
	public String toString() {
		return "Invalid entry - Password must be atleast 6 characters long";
	}
	
}
