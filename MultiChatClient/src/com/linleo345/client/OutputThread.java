package com.linleo345.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class OutputThread extends Thread{
	
	private BufferedReader clientMessage;
	@SuppressWarnings("unused")
	private Socket server;
	//private boolean disconnected;
	
	OutputThread(BufferedReader br, Socket s)
	{
		clientMessage = br;
		server = s;
		
	}
	
	public void run()
	{
		try {
			
			
			while(!ClientMain.disconnected)
			{
				String MSG = clientMessage.readLine();
				if(MSG == null)
				{
					ClientMain.disconnected = true;
					break;
				}
				System.out.println(MSG);
				

				
			}
			System.out.println("--------  Server Closed (please close client) :(  --------");
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
