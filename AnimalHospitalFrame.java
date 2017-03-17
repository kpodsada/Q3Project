package com.animal.hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AnimalHospitalFrame extends JFrame{
	
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	private static final int DEFAULT_X = 300;
	private static final int DEFAULT_Y = 50;
	
	private Container contents;
	private JPanel buttonPanel;
	private static JTextArea text;
	private JScrollPane scroll;
	
	static AnimalHospital hospital;
	
	
	public AnimalHospitalFrame(AnimalHospital hosp){
		//plumbing
		super("MAGICAL ANIMAL HOSPITAL");
		super.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocation(DEFAULT_X, DEFAULT_Y);
		
		contents = super.getContentPane();
		contents.setLayout(new BorderLayout());
		hospital = hosp;
		
		//buttons
		buttonPanel = new JPanel();
		
		addButton("ADD", buttonPanel, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AddFrame addFrame = new AddFrame();
				addFrame.setVisible(true);
			}			
		});

		addButton("IMPORT", buttonPanel, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ImportFrame importFrame = new ImportFrame();
				importFrame.setVisible(true);				
				
			}			
		});
		
		addButton("EXPORT", buttonPanel, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ExportFrame exportFrame = new ExportFrame();
				exportFrame.setVisible(true);
			}			
		});
		
		addButton("REMOVE", buttonPanel, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveFrame removeFrame = new RemoveFrame();
				removeFrame.setVisible(true);
			}			
		});
				
		//text area
		text = new JTextArea("");
		text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		text.setEditable(false);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.GREEN);
		
		scroll = new JScrollPane(text);
		
		//building
		contents.add(buttonPanel, BorderLayout.SOUTH);
		contents.add(scroll, BorderLayout.CENTER);
		
	}
	
	public void start(){
		super.setVisible(true);
	}
	
	private void addButton(String name, Container contents, ActionListener listener){
		JButton button = new JButton(name);
		button.addActionListener(listener);
		contents.add(button);
	}
	
	public static void updateHospital(){
		text.setText(hospital.formattedOutput());
		if(hospital.getPets().size() == 0){
			text.setText(null);
		}
		text.repaint();
	}
	

}
