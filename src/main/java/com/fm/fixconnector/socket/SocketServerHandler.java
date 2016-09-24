package com.fm.fixconnector.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.fm.fixconnector.MessageConsumer;

public class SocketServerHandler {

	static final Logger logger = Logger.getLogger(SocketServerHandler.class);

	private BlockingQueue<String> queue;

	private boolean publisherServer=true;
	
	public boolean isPublisherServer() {
		return publisherServer;
	}
	public void setPublisherServer(boolean publisherServer) {
		this.publisherServer = publisherServer;
	}
	
	public BlockingQueue<String> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	private int publisherPort;

	public void setPublisherPort(int publisherPort) {
		this.publisherPort = publisherPort;
	}

	public int getPublisherPort() {
		return publisherPort;
	}
	
	

	public void init() throws Exception {
		logger.info("Starting Publisher on port" + publisherPort);
		Thread t = new Thread() {

			public void run() {
				ServerSocket welcomeSocket = null;
				try {
					welcomeSocket = new ServerSocket(publisherPort);
				} catch (IOException e) {
					logger.info("Unable to create Socket..");
					e.printStackTrace();
				}
				Socket connectionSocket = null;
				DataOutputStream outToClient = null;

				try {
					connectionSocket = welcomeSocket.accept();
					connectionSocket.setKeepAlive(true);
					if(isPublisherServer()){
						(new DataPublisherClient(queue, connectionSocket)).start();
					}else{
						(new DataRecevierClient(queue, connectionSocket)).start();
					}
				} catch (IOException e) {
					logger.info("Error is server.. ");
					e.printStackTrace();
				}

			}
		};
		t.start();
		logger.info("Publisher thread started ...");
	}

}
