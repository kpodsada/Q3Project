package com.animal.hospital;

import java.io.IOException;

public class PetTester {
	public static void main(String[] args) throws IOException {
		AnimalHospital vet = new AnimalHospital();
		
		AnimalHospitalFrame frame = new AnimalHospitalFrame(vet);
		frame.start();
	}
}
