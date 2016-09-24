package com.fm.fixconnector;


import java.util.concurrent.BlockingQueue;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

public class MessageConsumer {
	static final Logger logger = Logger.getLogger(MessageConsumer.class);

	private BlockingQueue<String> queue;
	
	public BlockingQueue<String> getQueue() {
		return queue;
	}
	public void setQueue(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	public void consumeMessage(Exchange exchange) {
	    logger.info("Putting Message on Queue"+exchange.getIn().getBody());
	    try {
			queue.put(exchange.getIn().getBody().toString());
			logger.info("Message has been put on Queue"+exchange.getIn().getBody());
	    }catch (InterruptedException e) {
		logger.error(e);
		e.printStackTrace();
		}
	   }
	 }