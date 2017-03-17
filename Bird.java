package com.animal.hospital;

public class Bird extends Pet{

	private boolean featherClipped;
	
	public Bird(String name, String ownerName, String ownerEmail, String color) throws IllegalEmailException, IllegalDateException{
		super(name, ownerName, ownerEmail, color);
	}
	
	public void setClipped(boolean clipped){
		featherClipped = clipped;
	}
	
	public boolean clipped(){
		return featherClipped;
	}
	
	public String toString(){
		return "Bird [ featherClipped=" + clipped() + ", name=" + getPetName() + ", ownerName=" + getOwnerName() +
			"ownerEmail=" + getOwnerEmail() + ", color=" + getColor() + "]";

	}
}
