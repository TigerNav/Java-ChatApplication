package net.server;

public class MainServer {
	
	/*
	 * Main entry to server
	 * Once again i dont like
	 * having many things in
	 * the main @see OpenServer
	 * 
	 */
	
	static OpenServer server;
	
	public static void main(String[] args) {
	
		server = new OpenServer();
	
		server.ServerUpdate();
		
	}

}
