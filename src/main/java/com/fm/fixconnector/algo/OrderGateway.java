package com.fm.fixconnector.algo;

import java.util.Date;

import com.fm.fixconnector.MessageController;
import com.fm.fixconnector.domain.AbstractMarketData;
import com.fm.fixconnector.domain.ApplicationcOrder;
import com.fm.fixconnector.util.Constants;

public class OrderGateway {

	public void placeOrder(MessageController sourceFirst,MessageController sourceSecond,AbstractMarketData buymarketData,AbstractMarketData sellMarketData){
		sourceFirst.sendMessage(buymarketData);
		sourceSecond.sendMessage(sellMarketData);
	}
}
