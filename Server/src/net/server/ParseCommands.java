package net.server;

import java.io.IOException;
import javax.swing.JTextArea;

public class ParseCommands {

	/*
	 * 
	 * This class with handle all the
	 * Parsing of the commands.
	 * Gets the string from the textfield @see LogData , 
	 * Checks it with the commands strings @see Commands,
	 * Then executes its respective method
	 * 
	 */
	
	ServerThread thread;
	JTextArea LogInfo;
	String username;
	boolean CheckIfRight;
	
	public ParseCommands(JTextArea LogInfo) {
		
		this.LogInfo = LogInfo;
	}
	
	public void Parse(String shit, ServerThread thread, String username) {
		this.thread = thread;
		this.username = username;
		
		System.out.println(thread.ClientSocket.getPort());
		
		
		if(shit.equals(Commands.CLIENTID)) {
			CheckIfRight = true;
			ClientID();
		}
		if(shit.equals(Commands.CLIENTIP)) {
			CheckIfRight = true;
			ClientIp();
		}
		if(shit.equals(Commands.CLOSE)) {
			CheckIfRight = true;
			CloseServer();
		}
		if(shit.equals(Commands.PORT)) {
			CheckIfRight = true;
			ClientPort();
		}
		else {
			if(!CheckIfRight)
				CannotFindCommand(shit);
		}
		
		CheckIfRight = false;
		
	}
	
	private void ClientID() {
		long id = thread.getId();
		LogData.WriteToLog(username+" Identification code : "+id);
	}
	
	private void ClientIp() {	
		LogData.WriteToLog(username +" Ip Address : " + thread.ClientSocket.getRemoteSocketAddress());	
	}
	
	private void ClientPort() {
		LogData.WriteToLog(username +"Port Number : " + thread.ClientSocket.getPort());
	}
	
	private void CloseServer() {
		try {
			thread.Server.close();
		} catch (IOException e) 
		{e.printStackTrace();}
		
	}
	
	private void CannotFindCommand(String shit) {
		LogData.WriteToLog("ERROR : COULD NOT PARSE COMMAND SINCE " + shit + " DOESENT EXIST IN THE COMMAND LIST");
	}
}
