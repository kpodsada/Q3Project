package com.animal.hospital;

public class IllegalDateException extends Exception{
	public IllegalDateException() {
		super("Wrong date format.");
	}
}
