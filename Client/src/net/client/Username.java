package net.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Username implements ActionListener {

	/*
	 * Class that is first seen once application
	 * is open asks for username and keeps in 
	 * string variable that all other class constructors
	 * will pass through. Most are GUI elements
	 * 
	 */
	
	Font font = new Font("Open Sans", Font.PLAIN, 17);
	JLabel shit;
	JTextField Username;
	JButton button;
	String username = "";
	JFrame window;
	
	public Username() {
		
		window = new JFrame("Chat Application");
		window.setSize(800, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		window.setVisible(true);
		
		shit = new JLabel("Please enter a username so that the server and client can recognize you");
		shit.setLocation(140, 50);
		shit.setSize(550, 100);
		shit.setFont(font);
		
		Username = new JTextField(50);
		Username.setLocation(320, 120);
		Username.setSize(160, 30);
		Username.setFont(font);
		
		button = new JButton("Submit");
		button.setLocation(350, 170);
		button.setSize(100, 30);
		button.addActionListener(this);
		
		SwingUtilities.updateComponentTreeUI(window);
		
		window.add(shit);
		window.add(Username);
		window.add(button);
	}

	/*
	 * This method will perform if button
	 * is clicked. This just gets the username
	 * and sends the client to @see ClientOpen
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		username = Username.getText();
		
		window.remove(shit);
		window.remove(Username);
		window.remove(button);
		
		SwingUtilities.updateComponentTreeUI(window);
		
		new ClientOpen(window, username);
		
	}

}
