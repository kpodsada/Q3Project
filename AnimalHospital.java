package com.animal.hospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AnimalHospital {
	private ArrayList<Pet> pets;

	
	public AnimalHospital(String inputFile) throws IOException {
		pets = new ArrayList<>();
	
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		
		
		while(!in.readLine().equals("END")) {
			String type = in.readLine();
			String petName = in.readLine();
			String ownerName = in.readLine();
			String ownerEmail = in.readLine();
			String petColor = in.readLine();
			String gender = in.readLine();
			int g = 0;
			
			switch(gender) {
				case "female" : g = 2;
				break;
				case "male" : g = 1;
				break;
				case "spayed" : g = -2;
				break;
				case "neutered" : g = -1;
				break;	
			}
			
			try {
				switch(type) {
				case "DOG" : Dog dog = new Dog(petName, ownerName, ownerEmail, petColor, in.readLine());
					dog.setGender(g);
					pets.add(dog);
				break;
				case "CAT" : Cat cat = new Cat(petName, ownerName, ownerEmail, petColor, in.readLine());
					cat.setGender(g);
					pets.add(cat);
				break;
				case "BIRD" : Bird bird = new Bird(petName, ownerName, ownerEmail, petColor);
					bird.setGender(g);
					pets.add(bird);
				break;
				}
			}
			catch(IllegalDateException e) {
				e.getMessage();
			}
			catch(IllegalEmailException e) {
				e.getMessage();
			}
		}
		in.close();
	}

	public ArrayList<Pet> getPets() {
		return pets;
	}	
	
	public void printPetInfoByName(String name) {
		for(Pet p : pets) {
			if(name.equals(p.getPetName())) {
				System.out.println(p.toString());
			}
		}	
	}
	
	public void printPetInfoByOwner(String ownerName) {
		for(Pet p : pets) {
			if(ownerName.equals(p.getOwnerName())) {
				System.out.println(p.toString());
			}
		}	
	}
	
	public void printPetsBoarding(int month, int day, int year) {
		for(Pet p : pets) {
			if(p.boarding(month, day, year)) System.out.println(p.toString());
		}
	}
	
	public void printCurrentBoarding() {
		for(Pet p : pets) {
			if(p.boarding(Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.YEAR)) System.out.println(p.toString());
		}
		
	}
		
}
