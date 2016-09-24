package com.fm.fixconnector;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Producer;
import org.apache.camel.component.quickfixj.MessagePredicate;
import org.apache.camel.component.quickfixj.QuickfixjProducer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import quickfix.SessionID;
import quickfix.field.MsgType;

public class MessageController implements IMessageController {

	ApplicationContext context;
	CamelContext camelContext;
	Exchange exchange;
	String gatewayUri = "";
	Producer producer = null;
	String contextUri = "";

	public String getContextUri() {
		return contextUri;
	}

	public void setContextUri(String contextUri) {
		this.contextUri = contextUri;
	}

	public String getGatewayUri() {
		return gatewayUri;
	}

	public void setGatewayUri(String gatewayUri) {
		this.gatewayUri = gatewayUri;
	}

	public void init() {
		this.camelContext = (CamelContext) context.getBean(contextUri);
		Endpoint gatewayEndpoint = camelContext.getEndpoint(gatewayUri);
		try {
			producer = gatewayEndpoint.createProducer();
			exchange = producer.createExchange(ExchangePattern.InOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Object message) {
		exchange.getIn().setBody(message);
		try {
			producer.process(exchange);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

}
