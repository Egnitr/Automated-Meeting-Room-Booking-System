package com.hsbc.system.exceptions;

public class UserNotExistsException extends Exception {

	@Override
	public String toString() {
		return "User not Exists";
	}
}
