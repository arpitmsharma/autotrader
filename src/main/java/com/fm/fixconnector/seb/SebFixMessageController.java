package com.fm.fixconnector.seb;

import java.util.Map;

import quickfix.Message;

import com.fm.fixconnector.FixMessageController;
import com.fm.fixconnector.MessageRepo;

public class SebFixMessageController extends FixMessageController {

	public void SendQuoteRequestMessage(Map<Integer, String> valueMap) {
		SebMessageRepo messageRepo = (SebMessageRepo) getMessageRepo();
		Message message = messageRepo.getQuoteMessage(valueMap);
		sendMessage(message);
	}

	boolean flag = false;

	public void SendQuoteOrderMessage(Map<Integer, String> valueMap) {
		SebMessageRepo messageRepo = (SebMessageRepo) getMessageRepo();
//		Message message = messageRepo.getQuoteOrderMessage(valueMap);
//		if (flag == true)
//			sendMessage(message);
//		flag=false;
	}

}
