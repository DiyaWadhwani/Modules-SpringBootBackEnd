package com.wolken.wolkenapp.exception;

public class WorkHoursException extends Exception {

	@Override
	public String toString() {
		return "Invalid entry - Work hours must be more than 3";
	}
}
