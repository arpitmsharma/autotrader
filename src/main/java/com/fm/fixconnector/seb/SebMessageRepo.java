package com.fm.fixconnector.seb;

import java.util.Date;
import java.util.Map;

import quickfix.Group;
import quickfix.Message;
import quickfix.field.MsgType;

import com.fm.fixconnector.MessageRepo;
import com.fm.fixconnector.domain.AbstractMarketData;
import com.fm.fixconnector.util.Constants;

public class SebMessageRepo extends MessageRepo {

	public Message getQuoteMessage(Map<Integer, String> valueMap) {

		Message message = factory.create(getMessageVersion(),
				MsgType.QUOTE_REQUEST);
		message.getHeader().setString(49, getSenderCompId());
		message.getHeader().setString(50, valueMap.get(50));
		message.getHeader().setString(56, getTargetCompId());
		message.setString(131, "" + getRandomNo());
		message.setString(1, valueMap.get(1));
		message.setString(57, valueMap.get(57));
		int a[] = { 55, 54, 38, 60, 15 };
		Group group = new Group(146, 15, a);
		group.setString(55, valueMap.get(55));
		group.setString(54, valueMap.get(54));
		group.setString(38, valueMap.get(38));
		Date d = new Date();
		group.setUtcTimeStamp(60, d);
		group.setString(15, valueMap.get(15));
		message.addGroup(group);
		message.setString(263, valueMap.get(263));
		return message;
	}

	// TO DO Message should be created from xml only

	public Message getQuoteOrderMessage(Map<Integer, String> valueMap,
			AbstractMarketData marketData) {

		Message message = factory.create(getMessageVersion(), // Order Type=D
				MsgType.ORDER_SINGLE);
		message.getHeader().setString(49, getSenderCompId()); // SenderCompId
		message.getHeader().setString(50, "SW-VCGtest"); // SenderSubId
		message.getHeader().setString(56, getTargetCompId()); // TargetCompId
		message.setString(11, "" + valueMap.get(Constants.ID)); // Unique orderId
		message.setString(1, valueMap.get("1")); // account
		message.setString(21, valueMap.get("21")); // Handle Instrucutions- Automated private,
									// no broker
		message.setString(57, valueMap.get("57"));
		message.setString(55, valueMap.get("55"));
		message.setString(54, valueMap.get("54"));
		message.setString(38, valueMap.get("38"));
		// message.setString(44, "1.8382");
		message.setString(40, valueMap.get("40")); // orderType= 1 market Order
		message.setString(59, valueMap.get("59")); // Time in Force= Immediate Or Cancel
		message.setString(64, valueMap.get("64")); // FutSettDate
		message.setString(117, getQuoteId());
		message.setString(15, valueMap.get("15")); // Currency
		Date d = new Date();
		message.setUtcTimeStamp(60, d);
		return message;
	}
}
