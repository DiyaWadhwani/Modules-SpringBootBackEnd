package com.wolken.wolkenapp.exception;

public class UsernameException extends Exception {
	
	@Override
	public String toString() {
		return "Invalid entry - Username must be 3-25 characters long";
	}

}