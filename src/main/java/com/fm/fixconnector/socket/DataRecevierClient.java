package com.fm.fixconnector.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class DataRecevierClient extends Thread {

	static final Logger logger = Logger.getLogger(DataRecevierClient.class);
	
	private BlockingQueue<String> queue;
	
	private  Socket connectionSocket =null;
	BufferedReader inFromClient = null;
	
	public DataRecevierClient(BlockingQueue<String> queue,
			Socket connectionSocket) {
		super();
		this.queue = queue;
		this.connectionSocket = connectionSocket;
	}

	public void run(){
	   try{
	        logger.info("Client recevier Thread Started ... ");
	        
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			String clientData = inFromClient.readLine();
			while(StringUtils.hasText(clientData)){
				queue.put(clientData);
				clientData = inFromClient.readLine();
			}
			
	    	   }catch(IOException ioException){
	    		   logger.error(ioException);
	    		   logger.info("IO Exception occured may bre remote socket closed starting new thread for new client");
	    		   closeNetwork(connectionSocket, inFromClient);
	    		   
	    	   }
	    	catch(Exception exception){
	    		   logger.error(exception);
	    		   exception.printStackTrace();
	    		   closeNetwork(connectionSocket, inFromClient);
	    		   
	    	   }
	    	   }

			private void closeNetwork(
					Socket connectionSocket, BufferedReader outToClient) {
				try {
					if(outToClient!=null)
					outToClient.close();
					if(connectionSocket!=null)
					connectionSocket.close();
	    		   } catch (IOException e) {
					
					e.printStackTrace();
				}

			}
}