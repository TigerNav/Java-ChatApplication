package net.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ClientOpen {

	/*
	 * Start of the client class and the GUI.
	 * Initialized the java socket and connects
	 * to the server if its online.
	 * If the server is offline then it keep looping
	 * through the catch statement  
	 * until the server is running.
	 * Once connected to the server it will remove GUI
	 * components and break out of the loop to the next
	 * class 	@see ChatRoom
	 */
	
	Socket s;
	JFrame window;
	OfflineServer Offlineserver;
	boolean ExceptionLoop;
	public static DataInputStream din;
	public static DataOutputStream dout;
	
	public ClientOpen(JFrame window, String username) {
		
		
		ExceptionLoop = true;
		
		Offlineserver = new OfflineServer(window);
		
	while(ExceptionLoop) {
			
		try { 
			
			s = new Socket("DESKTOP-MLT9Q2B", 4999);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF(username);
			SwingUtilities.updateComponentTreeUI(window);
			Offlineserver.remove();
			
			break;
			
		
		}
		catch(Exception e) 
		{
			
			SwingUtilities.updateComponentTreeUI(window);
			Offlineserver.add();
			
			try {
				TimeUnit.MILLISECONDS.sleep(3);
				continue;
				
			} catch (InterruptedException e1) 
			{e1.printStackTrace();}
		
			
		}
	}
		new ChatRoom(window, username);
	}

}
