package com.animal.hospital;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AnimalHospital {
	private ArrayList<Pet> pets;
	
	public AnimalHospital() {
		pets = new ArrayList<>();	
	}

	public void readFile(String inputFile) throws FileNotFoundException, IOException{
		pets.clear();
		try (BufferedReader in = new BufferedReader(new FileReader(inputFile))){
			while(!in.readLine().equals("END")) {
				String type = in.readLine();
				String petName = in.readLine();
				String ownerName = in.readLine();
				String ownerEmail = in.readLine();
				String petColor = in.readLine();
				String gender = in.readLine();
				
				//quick and dirty fix
				if (gender == null){
					break;
				}

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
					ErrorFrame error = new ErrorFrame("You messed up the dates in your file");
					error.setVisible(true);
				}
				catch(IllegalEmailException e) {
					ErrorFrame error = new ErrorFrame("You messed up the emails in your file");
					error.setVisible(true);
				}
			}
		}
		
	}
	
	public void writeFile(String path) throws FileNotFoundException, IOException{
		try (BufferedWriter out = new BufferedWriter(new FileWriter(path))){
			for(Pet rufus : pets){
				out.write(rufus.getClass().getSimpleName().toUpperCase() + "\n");
				out.write(rufus.getPetName() + "\n");
				out.write(rufus.getOwnerName() + "\n");
				out.write(rufus.getOwnerEmail() + "\n");
				out.write(rufus.getColor() + "\n");
				switch (rufus.getGender()){
					case 1 : out.write("male\n"); break;
					case 2 : out.write("female\n"); break;
					case -1 : out.write("neutered\n"); break;
					case -2 : out.write("spayed\n"); break;
					
					default : out.write("hermaphrodite");
				}
				
				if (rufus instanceof Dog){
					out.write(((Dog)rufus).getSize() + "\n");
				} else if (rufus instanceof Cat){
					out.write(((Cat)rufus).getHairLength() + "\n");
				} else if (rufus instanceof Bird){
					out.write(((Bird)rufus).clipped() + "\n");
				}
				
				if(pets.indexOf(rufus) != pets.size() - 1){
					out.write("\n");
				}
			}
			
			out.write("END");
		}
		
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
	
	public Pet getPetInfoByName(String name) {
		for(Pet p : pets) {
			if(name.equals(p.getPetName())) {
				return p;
			}
		}
		
		return null;
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
	
	public void addPet(Pet pet){
		pets.add(pet);
	}
	
	public String formattedOutput(){
		String output = "PETS IN THE ANIMAL HOSPITAL:\n"
						+"(^_^)-------------------(^_^)\n";
		
		for (Pet pet : pets){
			output += "Type: " + pet.getClass().getSimpleName() + "\n";
			output += "Owner: " + pet.getOwnerName() + "\n";
			output += "Email: " + pet.getOwnerEmail() + "\n";
			output += "Color: " + pet.getColor() + "\n";
			output += "Gender: ";
			switch(pet.getGender()){
				case 1: output += "male"; break;
				case 2: output += "female"; break;
				case -1: output += "neutered"; break;
				case -2: output += "spayed"; break;
				
				default: output += "hermaphrodite";
			} output += "\n";
			
			if (pet instanceof Dog){
				output += "Size: " + ((Dog)pet).getSize() + "\n";
			} else if (pet instanceof Cat){
				output += "Hair: " + ((Cat)pet).getHairLength() + "\n";
			} else if (pet instanceof Bird){
				output += "Feathers clipped: " + ((Bird)pet).clipped() + "\n";
			}
			
			output += "\n";			
		}
		
		return output;
	}
		
}
