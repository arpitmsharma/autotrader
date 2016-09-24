package com.fm.test;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Producer;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.quickfixj.MessagePredicate;
import org.apache.camel.component.quickfixj.QuickfixjProducer;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fm.fixconnector.MessageController;

import quickfix.SessionID;
import quickfix.field.MsgType;
import quickfix.field.OrderID;

public class ApplicationLauncher {
    
	public static void main(String args[]){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("camelContext.xml");
		MessageController messageController=(MessageController)context.getBean("directController");
		messageController.sendMessage("5545,ab34");
		try{
			/*CamelContext camelContext=(CamelContext) context.getBean("quickfixjContext");*/
			Thread.sleep(1111111);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
    
    

}
