package com.animal.hospital;

public class Pet {
	private String name;
	private String ownerName;
	private String color;
	private String ownerEmail;
	protected int gender;
	
	public Pet(String name, String ownerName, String ownerEmail, String color) {
		this.name = name;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.color = color;
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
	

}
