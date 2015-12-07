package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Platform;

public class InputClass {
	private ServerListener sl;
	private PrintWriter out;
	private Main main;
	BufferedReader in;
	BufferedReader stdIn;
	
	public InputClass( Main main) {
		this.main = main;
		final String host = "localhost";
		final int portNumber = 9000;
		
		try {
			Socket echoSocket = new Socket(host, portNumber);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			 in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			 stdIn = new BufferedReader(new InputStreamReader(
					System.in));
			

			new Thread(new ServerListener()).start();
			
			
			// while ((userInput = stdIn.readLine()) != null) {
			//
			//
			// out.println(userInput);
			//
			// }

		} catch (Exception e) {

		}

	}
	public void sendMessage(String msg){
		out.println(msg);
	}
	public class ServerListener implements Runnable{
		String msgFromServer;
		
		public void run() {
			// TODO Auto-generated method stub
			try{
				
	            while ((msgFromServer = in.readLine()) != null) {
	           
	            main.setReply(msgFromServer);
	           
	               
	               
	                
	            }
			}catch(Exception e){
				System.out.println("Error reading socket");
			}
			
		}

	}
	

}
