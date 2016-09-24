package com.fm.fixconnector.util;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.fm.fixconnector.domain.AbstractMarketData;

public class OrderTestMailSender {
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private String mailFrom;
	private String templateLoc;
	private String toMail = "manu.arpit01@gmail.com";
	static final Logger logger = Logger.getLogger(OrderTestMailSender.class);

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

	public void sendMail(final AbstractMarketData bidPriceFromSource1,
			final AbstractMarketData sellPriceFromSource1,
			final AbstractMarketData bidPriceFromSource2,
			final AbstractMarketData sellPriceFromSource2, final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(toMail);
				message.setFrom(mailFrom); // could be parameterized...
				message.setSubject("Order Update");
				message.setBcc(mailFrom);
				Map model = new HashMap();
				model.put("bidPriceFromSource1", bidPriceFromSource1);
				model.put("sellPriceFromSource1", sellPriceFromSource1);
				model.put("bidPriceFromSource2", bidPriceFromSource2);
				model.put("sellPriceFromSource2", sellPriceFromSource2);
				model.put("text", text);
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, templateLoc, model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
		logger.info("OrderTestMail has been sent successfully");
	}
}
