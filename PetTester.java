package com.animal.hospital;

import java.io.IOException;

public class PetTester {
	public static void main(String[] args) throws IOException {
		AnimalHospital hospital = new AnimalHospital("petData.txt");
		hospital.printCurrentBoarding();
	}
}
