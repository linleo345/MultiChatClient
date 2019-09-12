package com.linleo345.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class InputThread extends Thread	{
	
	private PrintWriter msgToSend;
	@SuppressWarnings("unused")
	private Socket server;
	//private boolean disconnected;
	Scanner consoleReader;
	
	InputThread(PrintWriter pw, Socket s)
	{
		msgToSend = pw;
		server = s;
		consoleReader = new Scanner(System.in);
		
	}
	
	
	public void run()
	{
		
		
		while(!ClientMain.disconnected)
		{
			String msg = consoleReader.nextLine();
			
			msgToSend.println(ClientMain.name + ": " + msg);
			
			
		}
		//System.out.println("-----Client Disconnected------");
		
		
		
	}

}
