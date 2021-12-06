package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{

	/*
	 * Server Thread will allow multiple
	 * users to join while 1 server remains online
	 * Will be the class to take input and
	 * display output to the client via 
	 * @see java.io.DataInputStream;
	 * @see java.io.DataOutputStream;
	 * 
	 */
	
	ServerSocket Server;
	long ClientID;
	public DataInputStream din;
	public DataOutputStream dout;
	LogData Log;
	Socket ClientSocket;
	String username;
	File file;
	
	boolean IntroMessage;
	
	public ServerThread(ServerSocket Server, Socket ClientSocket,LogData Log) {
		this.ClientSocket = ClientSocket;
		this.Server = Server; 
		this.Log = Log;
		IntroMessage = true;
		
		
	}
	
	/*
	 * If a client joins then it will notify the
	 * server log about it. Updates the server log
	 * if any clients messaged to the chat. The Arraylist
	 * @see OpenServer we loop through the array since we
	 * need to loop through all the clients that are present.
	 * When we do we send a message to them using the writeUTF
	 * method
	 */
	
	public void run() {
		try {		
			din = new DataInputStream(ClientSocket.getInputStream());			
			dout = new DataOutputStream(ClientSocket.getOutputStream());
			
			
		}
		catch(Exception e) {e.printStackTrace();}
		
		String UTFInput;
		
		while(true) {
			try {
					
				
				if(ClientSocket.getInetAddress().isReachable(0) && IntroMessage) {
					username = din.readUTF();
					LogData.WriteToLog(username + " joined the chat room");
					IntroMessage = false;
				}
				
				UTFInput = din.readUTF();
		
				LogData.WriteToLog(UTFInput);
						
				for(ServerThread shid : OpenServer.thread) 	
					shid.dout.writeUTF(UTFInput);
					
				
				
			}catch(Exception e) {}
		
			
			}	
	}
}


