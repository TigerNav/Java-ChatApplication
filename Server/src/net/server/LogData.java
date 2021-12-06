package net.server;

import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LogData implements KeyListener{

	/*
	 * Basically the class for the Server's GUI.
	 * Will use TextArea to display all information
	 * that is being received to the server
	 * Example:
	 * 		Client joined
	 * 		Client left
	 * 		ClientA : Said this
	 * 		Server ping (millisecond)
	 * 		Packets being sent at that moment
	 *		IP of client and server
	 */
	
	JFrame window;
	static JTextArea LogInfo;
	JButton Submit;
	JTextField Commands;
	JButton Close;
	JScrollPane scroll;
	ParseCommands parseCommands;
	Date date = new Date();
	
	public LogData() {
		window = new JFrame("Server Log");
		window.setSize(800, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LogInfo = new JTextArea();
		LogInfo.setSize(700, 300);
		LogInfo.setLocation(0, 20);
		LogInfo.setEditable(false);
		
		scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(0, 20, 700, 300);
		scroll.getViewport().add(LogInfo);
		
		Commands = new JTextField();
		Commands.setSize(600, 30);
		Commands.setLocation(0, 330);
		Commands.addKeyListener(this);
		
		Submit = new JButton("Submit");
		Submit.setSize(80, 30);
		Submit.setLocation(610, 330);
		
		parseCommands = new ParseCommands(LogInfo);
		
		Submit.addActionListener(new ActionListener() {
			
			/*
			 * On Button click it will post message
			 * First writes to log @see WriteToLog()
			 * Then sets the textfield text to nothing.
			 * Aswell as on window close you get a notepad
			 * file with all the ServerLog Info
			 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				command();
			}});
		
		window.setLayout(null);
		window.setVisible(true);		
		window.add(Commands);
		window.add(Submit);
		window.add(scroll);
		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
				
				File file = new File("D:/Programming shit i did/Java projects/Networking/LogData - " +LocalDate.now() +" Time - "
						+ LocalTime.now().getHour() +"-"+ LocalTime.now().getMinute()+"-"+LocalTime.now().getSecond() +".txt");
				
				FileWriter note = new FileWriter(file);
				note.write(LogInfo.getText());
				note.close();
				}catch(Exception e1){e1.printStackTrace();}
				
			}
		});
		
	}
	
	/*
	 * Call this to write anything in the server log
	 * Gets all previous text, adds line, then last
	 * String is the shit you want to write
	 */
	
	public void command() {
		for(ServerThread shid : OpenServer.thread) {
			parseCommands.Parse(Commands.getText(), shid, shid.username);
			
		}
			Commands.setText("");
	}
	
	public static void WriteToLog(String Text) {
		LogInfo.setText(LogInfo.getText()+  '\n' + Text);
	}
	
	public void UpdateLog(Socket s, DataInputStream din,DataOutputStream dout,ServerSocket Server) {
		try {
		
			
			
		}catch(Exception e) 
		{e.printStackTrace();}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ENTER) 
			command();	
		
	}
}
