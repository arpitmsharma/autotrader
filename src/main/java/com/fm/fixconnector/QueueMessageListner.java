package com.fm.fixconnector;


import java.util.concurrent.BlockingQueue;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class QueueMessageListner extends Thread {
	
	static final Logger logger = Logger.getLogger(QueueMessageListner.class);

	private BlockingQueue<String> queue;
	
	private IMessageController messageController;
	
	public BlockingQueue<String> getQueue() {
		return queue;
	}
	public void setQueue(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	
	public IMessageController getMessageController() {
		return messageController;
	}
	public void setMessageController(IMessageController messageController) {
		this.messageController = messageController;
	}
	public void init(){
		this.start();
	}
	public void run() {
	    logger.info("Starting Listner Thread... ");
	    try {
	    	 String data=queue.take();
	    	 logger.info("Publishing inital order "+data);
	    	 while(StringUtils.hasText(data)) {
	    		 messageController.sendMessage(data);
	    		 data=queue.take();
	    		 logger.info("This order will be published next "+data);
	        }
	    }catch (InterruptedException e) {
		logger.error(e);
		e.printStackTrace();
		}
	   }
	}