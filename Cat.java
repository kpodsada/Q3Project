package com.animal.hospital;

public class Cat extends Pet{
	private String hairLength;
	
	public Cat(String name, String ownerName, String ownerEmail, String color, String hairLength) throws IllegalEmailException, IllegalDateException{
		super(name, ownerName, ownerEmail, color);
		this.hairLength = hairLength;
	}
	
	public String getHairLength() {
		return hairLength;
	}
	
	@Override
	public String toString() {
		return "Cat [hair length=" + hairLength + " name=" + getPetName() + ", ownerName=" + getOwnerName() + ", color=" + getColor() + ", ownerEmail=" + getOwnerEmail()
				+ ", gender=" + getGender() + "]";
	}
}
