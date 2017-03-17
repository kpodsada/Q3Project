package com.animal.hospital;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddFrame extends JFrame{
	
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 250;
	private static final int DEFAULT_X = 500;
	private static final int DEFAULT_Y = 200;
	
	private Container contents;
	private JPanel inputPanel;
	private JPanel buttonPanel;
	
	private JLabel petType;
	private JComboBox<String> petTypeField;
	private JLabel name;
	private JTextField nameField;
	private JLabel owner;
	private JTextField ownerField;
	private JLabel ownerEmail;
	private JTextField ownerEmailField;
	private JLabel color;
	private JTextField colorField;
	private JLabel gender;
	private JComboBox<String> genderField;
	
	private JLabel size;
	private JComboBox<String> sizeField;
	private JLabel hair;
	private JTextField hairField;
	private JLabel feathersClipped;
	private JComboBox<Boolean> feathersClippedField;
	
	private String petSelected;
	
	
	public AddFrame(){
		//plumbing
		super("ADD");
		super.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		super.setLocation(DEFAULT_X, DEFAULT_Y);
				
		contents = super.getContentPane();
		contents.setLayout(new BorderLayout());
		
		//initializing
		inputPanel = new JPanel(new GridLayout(0, 2, 1, 1));
		buttonPanel = new JPanel();
		
		addButton("Add", buttonPanel, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String owner = ownerField.getText();
				String email = ownerEmailField.getText();
				String color = colorField.getText();
				String genderRaw = (String)genderField.getSelectedItem();
				int gender = 0;
				if (genderRaw.equals("Male")){
					gender = 1;
				} else if (genderRaw.equals("Female")){
					gender = 2;
				} else if (genderRaw.equals("Neutered")){
					gender = -1;
				} else if (genderRaw.equals("Spayed")){
					gender = -2;
				}
				
				String type = (String)petTypeField.getSelectedItem();
				Pet rufus = null;
				try{
					
					if (type.equals("Dog")){
						String size = (String)sizeField.getSelectedItem();
						rufus = new Dog(name, owner, email, color, size);				
					} else if (type.equals("Cat")){
						String hair = hairField.getText();
						rufus = new Cat(name, owner, email, color, hair);
					} else if (type.equals("Bird")){
						boolean clipped = (boolean)feathersClippedField.getSelectedItem();
						rufus = new Bird(name, owner, email, color);
						((Bird) rufus).setClipped(clipped);
					}
					rufus.setGender(gender);
					
					AnimalHospitalFrame.hospital.addPet(rufus);
					AnimalHospitalFrame.updateHospital();
				
				} catch (IllegalEmailException e1) {
					ErrorFrame error = new ErrorFrame("You messed up the email.");
					error.setVisible(true);
					
				} catch (IllegalDateException e2){
					ErrorFrame error = new ErrorFrame("You messed up the date.");
					error.setVisible(true);
				}
				close();				
			}			
		});
		
		petType = new JLabel("Pet Type: ", SwingConstants.CENTER);
		String[] pets = {"Dog", "Cat", "Bird"};
		petTypeField = new JComboBox(pets);
		petSelected = "Dog";
		petTypeField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				petSelected = (String)petTypeField.getSelectedItem();
				update(petSelected);
			}
			
		});
		
		name = new JLabel("Name: ", SwingConstants.CENTER);
		nameField = new JTextField();
		owner = new JLabel("Owner: ", SwingConstants.CENTER);
		ownerField = new JTextField();
		ownerEmail = new JLabel("Email: ", SwingConstants.CENTER);
		ownerEmailField = new JTextField();
		color = new JLabel("Color: ", SwingConstants.CENTER);
		colorField = new JTextField();
		gender = new JLabel("Gender: ", SwingConstants.CENTER);
		String[] genders = {"Male", "Female", "Neutered", "Spayed"};
		genderField = new JComboBox(genders);
		
		size = new JLabel("Size: ", SwingConstants.CENTER);
		String[] sizes = {"small", "medium", "large"};
		sizeField = new JComboBox(sizes);
		hair = new JLabel("Hair: ", SwingConstants.CENTER);
		hairField = new JTextField();
		feathersClipped = new JLabel("Feathers clipped: ", SwingConstants.CENTER);
		Boolean[] isClipped = {true, false};
		feathersClippedField = new JComboBox(isClipped);
		
		//building
		
		inputPanel.add(petType);
		inputPanel.add(petTypeField);
		inputPanel.add(name);
		inputPanel.add(nameField);
		inputPanel.add(owner);
		inputPanel.add(ownerField);
		inputPanel.add(ownerEmail);
		inputPanel.add(ownerEmailField);
		inputPanel.add(color);
		inputPanel.add(colorField);
		inputPanel.add(gender);
		inputPanel.add(genderField);
		inputPanel.add(size);
		inputPanel.add(sizeField);
		
		
		contents.add(inputPanel, BorderLayout.CENTER);
		contents.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void addButton(String name, Container contents, ActionListener listener){
		JButton button = new JButton(name);
		button.addActionListener(listener);
		contents.add(button);
	}
	
	private void close(){
		this.setVisible(false);
	}
	
	private void update(String animal){
		
		inputPanel.remove(inputPanel.getComponentCount() - 1);
		inputPanel.remove(inputPanel.getComponentCount() - 1);
		
		if (animal.equals("Dog")){
			inputPanel.add(size);
			inputPanel.add(sizeField);
		} else if (animal.equals("Cat")){
			inputPanel.add(hair);
			inputPanel.add(hairField);
		} else if (animal.equals("Bird")){
			inputPanel.add(feathersClipped);
			inputPanel.add(feathersClippedField);
		}
		inputPanel.revalidate();
		inputPanel.repaint();
	}

}
