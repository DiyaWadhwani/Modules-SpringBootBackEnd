package com.wolken.wolkenapp.exception;

public class ContactException extends Exception {
	
	@Override
	public String toString() {
		return "Invalid entry - Contact must be atleast 10 characters long";
	}

}