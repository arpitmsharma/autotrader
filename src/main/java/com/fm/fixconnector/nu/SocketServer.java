package com.fm.fixconnector.nu;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread{
	  static Socket connectionSocket;
	public static void main(String args[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
         connectionSocket = welcomeSocket.accept();
        (new SocketServer()).start();
         connectionSocket.setKeepAlive(true);   
    	System.out.println("Client conencted");
    	int i=0;
    	while(true) {
        
           DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
           for(i=0;i<10;i++){
        	   outToClient.writeBytes("Hi i ma server sent to client"+i+"\n");   
        	   outToClient.flush();
        	   
           }
           Thread.sleep(100000);
        //   outToClient.close();
        }
    }
	public void run(){
		  BufferedReader inFromClient = null;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			  while(true){
		        String	 modifiedSentence = inFromClient.readLine();
		             System.out.println(modifiedSentence); 	
		        }       
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
