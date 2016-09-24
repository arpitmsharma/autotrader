package com.fm.fixconnector.nu;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketClient extends Thread {
	static Socket clientSocket;
	public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
      

         clientSocket = new Socket("localhost", 7790);
        (new SocketClient()).start();
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // outToServer.close();
        /*while(true){
        	 modifiedSentence = inFromServer.readLine();
             System.out.println(modifiedSentence); 	
        }*/
      //  clientSocket.close();
    }

	public void run(){
		  BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		  try {
			DataOutputStream outToServer = new DataOutputStream(this.clientSocket.getOutputStream());
		//	 String sentence = inFromUser.readLine();
		    while(true){
		    	outToServer.writeBytes("0,65029307-1419002750718162ASK,USD-SEK,FIX2D65,0.000078,E,1,USD,10000,SPOT\n" );
			     outToServer.flush();
			     System.out.println("order sent"); 
			     try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		    }
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
