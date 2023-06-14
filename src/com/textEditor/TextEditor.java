package com.textEditor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class TextEditor implements ActionListener {
	//declare a frame
	JFrame frame;
	
	//declare menubar
	JMenuBar menuBar;
	
	//declare menu
	JMenu file, edit;
	
	//declare menuItems for file
	JMenuItem newFile, openFile, saveFile;
	
	//declare menuItems for edit
	JMenuItem cut, copy, paste, selectAll, close;
	
	//declare text editor
	JTextArea textarea;
	
	public TextEditor() {
		//initialize frame
		frame = new JFrame();
		
		//initialize menuBar
		menuBar = new JMenuBar();
		
		// initialize text area
		textarea = new JTextArea();
		
		// initialize menu
		file = new JMenu("File");
		edit = new JMenu("Edit");
		
		//initialize file menu items
		newFile = new JMenuItem("New Window");
		openFile = new JMenuItem("Open File");
		saveFile = new JMenuItem("Save File");
		
		newFile.addActionListener(this);
		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		
		//add file menuItems to file menu
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);
		
		// initialize edit menu items
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		selectAll = new JMenuItem("Select All");
		close = new JMenuItem("Close");
		
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectAll.addActionListener(this);
		close.addActionListener(this);
		
		//add edit menuItems to edit menu;
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(selectAll);
		edit.add(close);
		
		// set menu to menuBar
		menuBar.add(file);
		menuBar.add(edit);
		
		//set menuBar to frame
		frame.setJMenuBar(menuBar);
		//create panel
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		
		//add text area to panel;
		panel.add(textarea, BorderLayout.CENTER);
		
		//create JScrollPane
		JScrollPane pane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//add pane to panel
		panel.add(pane);
		
		//add panel to frame;
		frame.add(panel);
		//modify/set frame
		frame.setBounds(100, 100,400, 400);
		frame.setVisible(true);
		frame.setLayout(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cut) {
			//perform cut on text
			textarea.cut();
		}
		
		if(e.getSource() == copy) {
			//perform copy on text
			textarea.copy();
		}
		
		if(e.getSource() == paste) {
			//perform cut on paste
			textarea.paste();
		}
		
		if(e.getSource() == selectAll) {
			//perform select all on text
			textarea.selectAll();
		}
		
		if(e.getSource() == close) {
			//perform cut on text
			System.exit(0);
		}
		
		if(e.getSource() == openFile) {
			//Need to open existing file
			// create file chooser
			JFileChooser fileChooser = new JFileChooser("C:"); // C: is current directory
			
			int  chooseOption = fileChooser.showOpenDialog(null);
			
			if(chooseOption == fileChooser.APPROVE_OPTION) {
				// Getting selected file
				File file = fileChooser.getSelectedFile();
				// Get selected file path
				String path = file.getAbsolutePath();
				
				//copying data from selected file to text area
				try {
					FileReader fr = new FileReader(path);
					BufferedReader br = new BufferedReader(fr);
					String intermediate="", output ="";
					
					while((intermediate = br.readLine()) != null ) {
						output += intermediate + "\n";
					}
					
					textarea.setText(output);
				} catch(IOException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				} 
			
			}
		}
		
		if(e.getSource() == saveFile) {
			// need to save file
			//create file chooser to get save file dialogue box
			JFileChooser chooser = new JFileChooser("C:");
			//to get what option chosen either save or cancel
			int getOption = chooser.showSaveDialog(null);
			
			if(getOption == chooser.APPROVE_OPTION) {
				
				File file = new File(chooser.getSelectedFile().getAbsolutePath() + ".txt");
				try {
					FileWriter writer = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(writer);
					//write content of text area to file
					textarea.write(bw);
					bw.close();
				}catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}
		
		if(e.getSource() == newFile) {
			TextEditor te = new TextEditor();
		}
	}
	
	
	public static void main(String[] args) {
		TextEditor newTextEditor = new TextEditor();
	}

	

}
