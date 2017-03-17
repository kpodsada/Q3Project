package com.animal.hospital;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ImportFrame extends JFrame{

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 150;
	private static final int DEFAULT_X = 500;
	private static final int DEFAULT_Y = 200;
	
	private Container contents;
	private JTextField text;
	private JPanel buttonPanel;
	
	public ImportFrame(){
		
		//plumbing
		super("IMPORT");
		super.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		super.setLocation(DEFAULT_X, DEFAULT_Y);
		
		contents = super.getContentPane();
		contents.setLayout(new BorderLayout());
		
		//Initializing
		buttonPanel = new JPanel();
		addButton("Import", buttonPanel, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent event) {
				String path = text.getText();
				try {
					AnimalHospitalFrame.hospital.readFile(path);
				} catch (FileNotFoundException e) {
					ErrorFrame error = new ErrorFrame("You messed up the file path");
					error.setVisible(true);
				} catch (IOException e) {
					ErrorFrame error = new ErrorFrame("You did something atrocious, probably");
					error.setVisible(true);
				}
				AnimalHospitalFrame.updateHospital();
				close();
			}
			
		});
		
		text = new JTextField("Path to file");
		text.selectAll();
		text.setFont(new Font("Serif", Font.PLAIN, 14));
		text.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String path = text.getText();
				try {
					AnimalHospitalFrame.hospital.readFile(path);
				} catch (FileNotFoundException e1) {
					ErrorFrame error = new ErrorFrame("You messed up the file path");
					error.setVisible(true);
				} catch (IOException e2) {
					ErrorFrame error = new ErrorFrame("You did something atrocious, probably");
					error.setVisible(true);
				}
				AnimalHospitalFrame.updateHospital();
				close();
			}
			
		});
		
		//building
		contents.add(text, BorderLayout.CENTER);
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
