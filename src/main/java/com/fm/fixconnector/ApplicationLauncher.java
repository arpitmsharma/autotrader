package com.fm.fixconnector;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ApplicationLauncher {

	public ApplicationLauncher() {
		
	}
	ApplicationContext context=null;
	public void start(){
		context = new ClassPathXmlApplicationContext("commonCamelContext.xml");
	}
	public ApplicationContext getApplicationContext(){
		return context;
	}
	public Object getBeanObj(String beanName){
		return context.getBean(beanName);
	}
}
