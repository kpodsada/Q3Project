package com.animal.hospital;

public class IllegalEmailException extends Exception{
	public IllegalEmailException() {
		super("Wrong email format.");
	}
}
