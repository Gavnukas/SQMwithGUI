package chatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ClientListener implements Runnable {
	private String fag;
	protected Socket serverSocket = null;
	protected String mMsgFromServer;
	private BufferedReader derp = null;
	
	public ClientListener( BufferedReader br) {
		
		this.derp = br;
	}
	public void checkMessage (String string){
		fag = string;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			ArrayList<String> list= new ArrayList<String>();
            while ((mMsgFromServer = derp.readLine()) != null) {
              
            	switch(mMsgFromServer.substring(0, 4)){
               case "LIST":
            	   list.removeAll(list);
            	   System.out.println(mMsgFromServer);
            	   fag = mMsgFromServer.substring(5);
            	   
            	   for(String val: fag.split(",")){
            		  
            		   if(val.matches(" ")){
            		   }else{
            			   list.add(val.replace(" ", ""));
            			   
            		   }
            		  
            	   }
            	   break;
               default:
            	   System.out.println(mMsgFromServer);
               }
            	
               
               
                
            }
		}catch(Exception e){
			System.out.println("Error reading socket");
		}
		
	}

}
