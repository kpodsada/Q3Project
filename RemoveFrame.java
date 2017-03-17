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

public class RemoveFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 100;
	private static final int DEFAULT_X = 500;
	private static final int DEFAULT_Y = 200;
	
	private Container contents;
	private JPanel buttonPanel;
	private JPanel boxPanel;
	
	private JComboBox<String> killList;
	
	public RemoveFrame(){
		//plumbing
		super("ADD");
		super.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		super.setLocation(DEFAULT_X, DEFAULT_Y);
				
		contents = super.getContentPane();
		contents.setLayout(new BorderLayout());
		
		//initializing
		boxPanel = new JPanel();
		buttonPanel = new JPanel();
		
		addButton("Kill", buttonPanel, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pet haplessSoul = AnimalHospitalFrame.hospital.
										getPetInfoByName((String)killList.getSelectedItem());
				AnimalHospitalFrame.hospital.getPets().remove(haplessSoul);
				AnimalHospitalFrame.updateHospital();
				close();
				
			}
			
		});
		
		int numSouls = AnimalHospitalFrame.hospital.getPets().size();
		String[] pets = new String[numSouls];
		for (int i = 0; i < numSouls; i++){
			pets[i] = AnimalHospitalFrame.hospital.getPets().get(i).getPetName();
		}
		
		killList = new JComboBox(pets);
		
		//building
		boxPanel.add(killList);
		contents.add(boxPanel, BorderLayout.CENTER);
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
}
