package com.wolken.wolkenapp.exception;

public class ExperienceException extends Exception {

	@Override
	public String toString() {
		return "Invalid Entry - Experience must be of format : (int) months/ (int) years";
	}
	
}
