package com.wolken.wolkenapp.exception;

public class DesignationException extends Exception {

	@Override
	public String toString() {
		return "Invalid entry - Designation must be atleast 3 characters long";
	}
}
