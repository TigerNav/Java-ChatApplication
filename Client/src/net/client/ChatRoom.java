package net.client;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatRoom implements ActionListener, KeyListener {
	
	/*
	 * When there is no error in the connect
	 * phase then this class comes into play.
	 * Most of this class is just GUI elements.
	 * 
	 */
	
	JTextArea Chat;
	JButton Submit;
	JTextField Message;
	JButton Close;
	JScrollPane scroll;
	String username;
	String msgToSend;
	String GetServerMessage;
	
	public ChatRoom(JFrame window, String username) {
		this.username = username;
		
		Chat = new JTextArea();
		Chat.setSize(700, 300);
		Chat.setLocation(0, 20);
		Chat.setEditable(false);
		
		scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(0, 20, 700, 300);
		scroll.getViewport().add(Chat);
		
		Message = new JTextField();
		Message.setSize(600, 30);
		Message.setLocation(0, 330);
		Message.addKeyListener(this);
		
		Submit = new JButton("Submit");
		Submit.setSize(80, 30);
		Submit.setLocation(610, 330);
		Submit.addActionListener(this);

		window.add(scroll);
		window.add(Message);
		window.add(Submit);
		

				
		listenForMessage();
	}
	
	/*
	 * The listenForMessage method runs a new
	 * thread so it can keep running for the
	 * while loop. Inside the while loop it
	 * checks if it gets a message from the server
	 * and it updates the Chat TextArea with 
	 * a new message from the new server. Starts
	 * once method is called
	 */
	
	public void listenForMessage() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {				
					try {						
						GetServerMessage = ClientOpen.din.readUTF();
						Chat.setText(Chat.getText() + '\n' + GetServerMessage);						
					}catch(Exception e) {}
				}
			}
		}
		).start();
	}

	
	/*
	 * Method performed once Submit button is clicked.
	 * Quick run down :
	 * Sends textfield message to server
	 * 
	 */
	
	private void SendMessage() {
		msgToSend = username + " : " + Message.getText();
		try {
			ClientOpen.dout.writeUTF(msgToSend);
			ClientOpen.dout.flush();
		} catch (IOException e1){e1.printStackTrace();}
		Message.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SendMessage();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ENTER) 
			 SendMessage();
		
		
	}
}
