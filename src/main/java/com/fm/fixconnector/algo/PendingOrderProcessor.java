package com.fm.fixconnector.algo;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import com.fm.fixconnector.MessageController;
import com.fm.fixconnector.domain.AbstractMarketData;

public class PendingOrderProcessor extends Thread {

	private BlockingQueue<AbstractMarketData> pendingOrder;
	private Map<String,PriceMatcher> priceMatchers;
	private AbstractMarketData marketData;
	private MessageController messageController;
	
	public void run(){
		
		marketData = getMarketData();
		while(marketData!=null){
			PriceMatcher priceMatcher=priceMatchers.get(marketData.getSymbol());
			messageController=priceMatcher.getBestPriceMessageController(marketData.getPriceType());
			messageController.sendMessage(marketData);
			marketData=getMarketData();
		}
	}

	private AbstractMarketData getMarketData() {
		try {
			marketData = pendingOrder.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marketData;
	}
}
