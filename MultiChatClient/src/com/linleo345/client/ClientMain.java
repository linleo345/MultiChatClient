package com.linleo345.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.linleo345.client.InputThread;
import com.linleo345.client.OutputThread;

public class ClientMain {
	
	private static PrintWriter sendToClient;
	private static BufferedReader readFromClient;
	private static OutputThread output;
	private static InputThread input;
	public static boolean disconnected;
	
	public static String name;
	
	public static void main(String args[]) throws IOException
	{
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		System.out.println("Your username(could be repetitive): ");
		name = s.nextLine();
		
		System.out.println("Gimme your desired IP(hostname): ");
		String hostname = s.nextLine();
		System.out.println("Gimme your desired port: ");
		int port = s.nextInt();
		
		
		
		try{
			Socket sa = new Socket(hostname, port);
			if(!sa.isClosed() && sa.isConnected())
			{
				String ip=(((InetSocketAddress) sa.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
				
				System.out.println("connected to " + ip);
				
			}
			sendToClient = new PrintWriter(sa.getOutputStream(), true);
	        readFromClient = new BufferedReader(new InputStreamReader(sa.getInputStream()));
	        
	        output = new OutputThread(readFromClient, sa);
	        input = new InputThread(sendToClient, sa);
	        
	        disconnected = false;
	        
	        output.start();
	        input.start();
			
			
		} 
		catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostname);
            System.exit(1);
        } 
		
		
	}

}



