package net.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class OpenServer {
	
	

	ServerSocket Server;	
	Socket socket;
	LogData Log;
	ServerThread serverThread;
	static ArrayList<ServerThread> thread = new ArrayList<ServerThread>();
	
	/*
	 * Server's main entry. This class deals with
	 * Putting together other classes. This class
	 * also deals with very important logic like
	 * Send messages to all clients.
	 * Starts up Server and GUI for the server log.
	 * 
	 */
	
	public OpenServer() {
		
		try {
		
			Log = new LogData();
			Server = new ServerSocket(4999);		
				
		}catch(Exception e) 
		{e.printStackTrace();}	
		
	}
	
	/*
	 * This method is responsible for accepting
	 * clients so they can connect to the server,
	 * starting the server thread @see ServerThread
	 * The array list we create will add the serverThread
	 * instance we created. We add this instance of the 
	 * ServerThread since this instance is basically all
	 * the clients that joined, this array list will be important
	 * in the next class @see ServerThread
	 */
	
	public void ServerUpdate() {
		
		while(true) {
			try {		
				
				socket = Server.accept();			
				
			}catch(Exception e) 
			{e.printStackTrace();}
			
			
			
			serverThread = new ServerThread(Server, socket, Log);
			thread.add(serverThread);
			serverThread.start();
			
			
			
			}
			
		}
		
	}
