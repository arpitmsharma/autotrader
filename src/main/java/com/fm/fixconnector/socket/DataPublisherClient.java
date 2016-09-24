package com.fm.fixconnector.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class DataPublisherClient extends Thread {

	static final Logger logger = Logger.getLogger(DataPublisherClient.class);
	
	private BlockingQueue<String> queue;
	
	private  Socket connectionSocket =null;
	private  DataOutputStream outToClient=null;

	
	public DataPublisherClient(BlockingQueue<String> queue,
			Socket connectionSocket) {
		super();
		this.queue = queue;
		this.connectionSocket = connectionSocket;
	}

	public void run(){
	   try{
	        logger.info("Client Thread Started ... ");
	        outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	    	 String data=queue.take();
	    	 logger.info("Publishing inital data "+data);
	    	 while(StringUtils.hasText(data)) {
	        	   outToClient.writeBytes(data);   
	        	   outToClient.flush();
	        	   data=queue.take();
	        	logger.info("This data will be published next "+data);
	        }
	    	 
	    	   }catch(IOException ioException){
	    		   logger.error(ioException);
	    		   logger.info("IO Exception occured may bre remote socket closed starting new thread for new client");
	    		   closeNetwork(connectionSocket, outToClient);
	    		   
	    	   }
	    	catch(Exception exception){
	    		   logger.error(exception);
	    		   exception.printStackTrace();
	    		   closeNetwork(connectionSocket, outToClient);
	    		   
	    	   }
	    	   }

			private void closeNetwork(
					Socket connectionSocket, DataOutputStream outToClient) {
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