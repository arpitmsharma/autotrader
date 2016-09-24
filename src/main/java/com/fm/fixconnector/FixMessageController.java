package com.fm.fixconnector;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Producer;
import org.apache.camel.component.quickfixj.MessagePredicate;
import org.apache.camel.component.quickfixj.QuickfixjProducer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.fm.fixconnector.nordea.MarketData;

import quickfix.Message;
import quickfix.SessionID;
import quickfix.field.MsgType;

public class FixMessageController  extends MessageController{
	
	MessageRepo messageRepo;
	String senderCompId;
	String targetCompId;
	String messageVersion;
	public MessageRepo getMessageRepo() {
		return messageRepo;
	}

	public void setMessageRepo(MessageRepo messageRepo) {
		this.messageRepo = messageRepo;
	}

	public String getSenderCompId() {
		return senderCompId;
	}

	public void setSenderCompId(String senderCompId) {
		this.senderCompId = senderCompId;
	}

	public String getTargetCompId() {
		return targetCompId;
	}

	public void setTargetCompId(String targetCompId) {
		this.targetCompId = targetCompId;
	}

	public String getMessageVersion() {
		return messageVersion;
	}

	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}

	public void init(){
		super.init();
		exchange.setProperty(QuickfixjProducer.CORRELATION_CRITERIA_KEY, new MessagePredicate(
   			 new SessionID(messageVersion,targetCompId,senderCompId), MsgType.LOGON));
    }
	
	public void sendUserRequestMessage(Map<Integer,String> valueMap){
		
		Message message = message=messageRepo.getUserRequest(valueMap);
		sendMessage(message); 
	}
	public void sendPriceRequestMessage(Map<Integer,String> valueMap){
		
		Message message =messageRepo.getPriceStreamMessage(valueMap);
		sendMessage(message); 
	}

	public void SendMessage(Message  message){
//		Message	message=messageRepo.getPlaceOrderMessage(valueMap);
		sendMessage(message);
	}
		
}
