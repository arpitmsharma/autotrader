package com.fm.fixconnector.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.fm.fixconnector.domain.AbstractTradeCapture;
import com.fm.fixconnector.domain.TradeObject;
import com.mongodb.BasicDBObject;

public class MailSender {

	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private String mailFrom;
	private String templateLoc;
	private String toMail="manu.arpit01@gmail.com";
	static final Logger logger = Logger.getLogger(MailSender.class);
	
	public String getTemplateLoc() {
		return templateLoc;
	}

	public void setTemplateLoc(String templateLoc) {
		this.templateLoc = templateLoc;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setMailSender(JavaMailSender mailSender) {
	   this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
	   this.velocityEngine = velocityEngine;
	}
	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public void sendMail(final AbstractTradeCapture tradeObj){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(toMail);
	            message.setFrom(mailFrom); // could be parameterized...
	            message.setSubject("Your Wish Update");
	            message.setBcc(mailFrom);
	            Map model = new HashMap();
	            model.put("trade", tradeObj);
	            String text = VelocityEngineUtils.mergeTemplateIntoString(
	               velocityEngine, templateLoc, model);
	           message.setText(text, true);
	         }
	      };
	      this.mailSender.send(preparator);
	      logger.info("Mail has beensent successfuly");
	}
}
