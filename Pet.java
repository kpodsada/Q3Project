package com.animal.hospital;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Pet implements Boardable{
	private String name;
	private String ownerName;
	private String color;
	private String ownerEmail;
	
	private Calendar boardStart;
	private Calendar boardEnd;
	/*
	 *	1: male
	 *	2: female
	 *	-1: neutered
	 *	-2: spayed 
	 *	
	 *	0: hermaphrodite (or alien gender of no earthly definition)
	 */
	protected int gender;

	public Pet(String name, String ownerName, String ownerEmail, String color) throws IllegalDateException, IllegalEmailException {
		if(!ownerEmail.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) throw new IllegalEmailException();

		this.name = name;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.color = color;
		this.gender = 0;
		this.boardStart = new GregorianCalendar();
		this.boardEnd = new GregorianCalendar();
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int genderId) {
		this.gender = genderId;
	}

	public String getPetName() {
		return name;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getColor() {
		return color;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	@Override
	public String toString() {
		return "Pet [name=" + name + ", ownerName=" + ownerName + ", color=" + color + ", ownerEmail=" + ownerEmail
				+ ", gender=" + gender + "]";
	}

	@Override
	public void setBoardStart(int month, int day, int year) throws IllegalDateException {
		if(year > 2017 || year < 2006) throw new IllegalDateException();
		boardStart.set(year, month, day);
	}

	@Override
	public void setBoardEnd(int month, int day, int year) {
		boardEnd.set(year, month, day);
	}

	@Override
	public boolean boarding(int month, int day, int year) {
		Calendar today = new GregorianCalendar();
		today.set(year, month, day);
		
		return today.compareTo(boardStart) > 0 && today.compareTo(boardEnd) < 0;
	}
	

}
