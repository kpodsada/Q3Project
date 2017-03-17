package com.animal.hospital;

public class Dog extends Pet {
	private String size;
	
	public Dog(String name, String ownerName, String ownerEmail, String color, String size) throws IllegalEmailException, IllegalDateException{
		super(name, ownerName, ownerEmail, color);
		this.size = size;
	}
	
	public String getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return "Dog [size=" + size + " name=" + getPetName() + ", ownerName=" + getOwnerName() + ", color=" + getColor() + ", ownerEmail=" + getOwnerEmail()
				+ ", gender=" + getGender() + "]";
	}

	
}
