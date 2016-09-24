package com.fm.fixconnector;

import org.springframework.context.ApplicationContextAware;

import quickfix.Message;

public interface IMessageController extends  ApplicationContextAware{

	void sendMessage(Object message);
	
	void init();
}
