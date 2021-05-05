package com.wolken.wolkenapp.exception;

public class EmailException extends Exception {

	@Override
	public String toString() {
		return "Invalid entry - Email must be atleast 10 characters long";
	}
}
