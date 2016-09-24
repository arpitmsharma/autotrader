package com.fm.fixconnector.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import com.fm.fixconnector.domain.AbstractMarketData;
import com.fm.fixconnector.domain.AbstractTradeCapture;

public class TradeReconcilation {

	ConcurrentHashMap<String,Object> activeTrades=new ConcurrentHashMap<String,Object>();
	Queue<AbstractMarketData> pendingOrder;
	
	public void beforePlacingTrade(String orderId,Object object){
		activeTrades.put(orderId, object);
	}
	public void OnComepleteTrade(AbstractTradeCapture tradeCapture){
		activeTrades.remove(tradeCapture.getClientOrderId());
	}
	public void OnPartiallyComepleteTrade(AbstractTradeCapture tradeCapture){
//		AbstractMarketData marketData=(AbstractMarketData)activeTrades.get(tradeCapture.getClientOrderId());
//		int quantity=marketData.getQuantity()-tradeCapture.getQuantity();
//		marketData.setQuantity(quantity);
//		pendingOrder.add(marketData);
//		activeTrades.remove(tradeCapture.getClientOrderId());
		
	}
}
