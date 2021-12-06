package net.client;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OfflineServer {

	JFrame window;
	JLabel Offline;
	JLabel Searching;
	Font font;
	
	/*
	 * Just GUI assets for the offline
	 * state of the screen
	 */
	
	public OfflineServer(JFrame window) {
		this.window = window;
		font = new Font("Open Sans", Font.PLAIN, 17);
		
		Offline = new JLabel("Server status : Offline");
		Offline.setLocation(300, 50);
		Offline.setSize(200, 100);
		Offline.setFont(font);
		
		Searching = new JLabel("Please wait until the server is online");
		Searching.setLocation(290, 100);
		Searching.setSize(300, 100);
		Searching.setFont(font);
		
	}
	
	public void add() {
		window.add(Offline);
		window.add(Searching);
	}
	
	public void remove() {
		window.remove(Offline);
		window.remove(Searching);
		
	}

}
