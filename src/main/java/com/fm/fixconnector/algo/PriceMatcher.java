package com.fm.fixconnector.algo;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.fm.fixconnector.MessageController;
import com.fm.fixconnector.domain.AbstractMarketData;
import com.fm.fixconnector.seb.QuoteData;
import com.fm.fixconnector.util.Constants;
import com.fm.fixconnector.util.OrderTestMailSender;

@ManagedResource
public class PriceMatcher {

	static final Logger logger = Logger.getLogger(PriceMatcher.class);
	// Source 1 is nordea
	// Source 2 is seb

	public OrderGateway orderGateway;

	private volatile AbstractMarketData bidPriceFromSource1;
	private volatile AbstractMarketData sellPriceFromSource1;

	private volatile AbstractMarketData bidPriceFromSource2;
	private volatile AbstractMarketData sellPriceFromSource2;

	private MessageController messageControllerSource1;
	private MessageController messageControllerSource2;

	private double multiplicationFactor = 1;
	private double differenceFactor = 0;

	public OrderTestMailSender orderTestMailSender;

	public OrderGateway getOrderGateway() {
		return orderGateway;
	}

	public void setOrderGateway(OrderGateway orderGateway) {
		this.orderGateway = orderGateway;
	}

	public MessageController getMessageControllerSource1() {
		return messageControllerSource1;
	}

	public void setMessageControllerSource1(
			MessageController messageControllerSource1) {
		this.messageControllerSource1 = messageControllerSource1;
	}

	public MessageController getMessageControllerSource2() {
		return messageControllerSource2;
	}

	public void setMessageControllerSource2(
			MessageController messageControllerSource2) {
		this.messageControllerSource2 = messageControllerSource2;
	}

	public double getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public void setMultiplicationFactor(double multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
	}

	public double getDifferenceFactor() {
		return differenceFactor;
	}

	public void setDifferenceFactor(double differenceFactor) {
		this.differenceFactor = differenceFactor;
	}

	public OrderTestMailSender getOrderTestMailSender() {
		return orderTestMailSender;
	}

	public void setOrderTestMailSender(OrderTestMailSender orderTestMailSender) {
		this.orderTestMailSender = orderTestMailSender;
	}

	public void onMessageFromSource1(AbstractMarketData marketData) {
		System.out.println("Price  from Source 1 " + marketData);
		if ("1".equalsIgnoreCase(marketData.getPriceType())) { // sell price in
																// marketData
			logger.info("Sell Price from source 1 " + marketData);
			sellPriceFromSource1 = marketData;
			if (bidPriceFromSource2 == null) {
				logger.error("Bid Price from Source 2 is Null");
			} else {
				if (bidPriceFromSource2.getPrice() <= sellPriceFromSource1
						.getPrice() * multiplicationFactor) {
					if (differenceFactor == 0
							|| (differenceFactor != 0 && sellPriceFromSource1
									.getPrice()
									- bidPriceFromSource2.getPrice() >= differenceFactor)) {
						String text = "Going to place bid order on source 2 :"
								+ bidPriceFromSource2.getPrice()
								+ " and sell Order On Source 1 :"
								+ sellPriceFromSource1.getPrice();
						logger.info(text);
						orderGateway.placeOrder(messageControllerSource2,
								messageControllerSource1, bidPriceFromSource2,
								sellPriceFromSource1);
						orderTestMailSender.sendMail(bidPriceFromSource1,
								sellPriceFromSource1, bidPriceFromSource2,
								sellPriceFromSource2, text);
					} else {
						logger.info("Difference Factor is mearge");
					}
				} else {
					logger.info("No order: Sell Price from Source 1 :"
							+ sellPriceFromSource1.getPrice()
							+ " is less than Bid Price from Source 2 :"
							+ bidPriceFromSource2.getPrice());
				}
			}
		}
		if ("0".equalsIgnoreCase(marketData.getPriceType())) { // bid price in
																// marketdata
			logger.info("Bid Price from source 1 " + marketData);
			bidPriceFromSource1 = marketData;
			if (sellPriceFromSource2 == null) {
				logger.error("Sell Price from Source 2 is Null");
			} else {
				if (bidPriceFromSource1.getPrice() <= sellPriceFromSource2
						.getPrice() * multiplicationFactor) {
					if (differenceFactor == 0
							|| (differenceFactor != 0 && sellPriceFromSource2
									.getPrice()
									- bidPriceFromSource1.getPrice() >= differenceFactor)) {
						String text = "Going to place bid order on source 1 :"
								+ bidPriceFromSource1.getPrice()
								+ " and sell Order On Source 2 :"
								+ sellPriceFromSource2.getPrice();
						logger.info(text);
						orderGateway.placeOrder(messageControllerSource1,
								messageControllerSource2, bidPriceFromSource1,
								sellPriceFromSource2);
						orderTestMailSender.sendMail(bidPriceFromSource1,
								sellPriceFromSource1, bidPriceFromSource2,
								sellPriceFromSource2, text);
					} else {
						logger.info("Difference Factor is mearge");
					}
				} else {
					logger.info("No order: Bid Price from Source 1 :"
							+ bidPriceFromSource1.getPrice()
							+ " is more than Sell Price from Source 2 :"
							+ sellPriceFromSource2.getPrice());
				}
			}

		}

	}

	public void onMessageFromSource2(Exchange exchange) {
		QuoteData marketData = (QuoteData) exchange.getIn().getBody();
		System.out.println("Price  from Source 2 " + marketData);
		if ("1".equalsIgnoreCase(marketData.getPriceType())) { // bid price in
																// marketData
			logger.info("Bid Price from source 2" + marketData);
			bidPriceFromSource2 = marketData;
			if (sellPriceFromSource1 == null) {
				logger.error("Sell Price from Source 1 is Null");
			} else {
				if (bidPriceFromSource2.getPrice() <= sellPriceFromSource1
						.getPrice() * multiplicationFactor) {
					if (differenceFactor == 0
							|| (differenceFactor != 0 && sellPriceFromSource1
									.getPrice()
									- bidPriceFromSource2.getPrice() >= differenceFactor)) {
						String text = "Going to place bid order on source 2 :"
								+ bidPriceFromSource2.getPrice()
								+ " and sell Order On Source 1 :"
								+ sellPriceFromSource1.getPrice();
						logger.info(text);
						orderGateway.placeOrder(messageControllerSource2,
								messageControllerSource1, bidPriceFromSource2,
								sellPriceFromSource1);
						orderTestMailSender.sendMail(bidPriceFromSource1,
								sellPriceFromSource1, bidPriceFromSource2,
								sellPriceFromSource2, text);
					} else {
						logger.info("Difference Factor is mearge");
					}
				} else {
					logger.info("No order: Bid Price from Source 2 :"
							+ bidPriceFromSource2.getPrice()
							+ " is more than Sell Price from Source 1 :"
							+ sellPriceFromSource1.getPrice());
				}
			}
		}
		if ("2".equalsIgnoreCase(marketData.getPriceType())) { // sell price in
																// market data
			logger.info("Sell Price from source 2 " + marketData);
			sellPriceFromSource2 = marketData;
			if (bidPriceFromSource1 == null) {
				logger.error("Bid Price from Source 1 is Null");
			} else {
				if (bidPriceFromSource1.getPrice() <= sellPriceFromSource2
						.getPrice() * multiplicationFactor) {
					if (differenceFactor == 0
							|| (differenceFactor != 0 && sellPriceFromSource2
									.getPrice()
									- bidPriceFromSource1.getPrice() >= differenceFactor)) {
						String text = "Going to place bid order on source 1 :"
								+ bidPriceFromSource1.getPrice()
								+ " and sell Order On Source 2 :"
								+ sellPriceFromSource2.getPrice();
						logger.info(text);
						orderGateway.placeOrder(messageControllerSource1,
								messageControllerSource2, bidPriceFromSource1,
								sellPriceFromSource2);
						orderTestMailSender.sendMail(bidPriceFromSource1,
								sellPriceFromSource1, bidPriceFromSource2,
								sellPriceFromSource2, text);
					} else {
						logger.info("Difference Factor is mearge");
					}
				} else {
					logger.info("No order: Sell Price from Source 2 "
							+ sellPriceFromSource2.getPrice()
							+ " is less than Bid Price from Source 1 "
							+ bidPriceFromSource1.getPrice());
				}
			}
		}
	}
	@ManagedOperation(description="To Get html Scrap data.")
	public void placeBidMessageonSource1(){
		orderGateway.placeOrder(messageControllerSource1,
				messageControllerSource2, bidPriceFromSource1,
				sellPriceFromSource2);
	}
	@ManagedOperation(description="To Get html Scrap data.")
	public void placeBidMessageonSource2(){
		orderGateway.placeOrder(messageControllerSource2,
				messageControllerSource1, bidPriceFromSource2,
				sellPriceFromSource1);
	}
	public MessageController getBestPriceMessageController(String priceType) {
		if (priceType.equalsIgnoreCase(Constants.BUY)) {
			if (bidPriceFromSource1.getPrice() > bidPriceFromSource2.getPrice()) {
				return messageControllerSource2;
			} else {
				return messageControllerSource1;
			}
		} else {
			if (sellPriceFromSource1.getPrice() > sellPriceFromSource2
					.getPrice()) {
				return messageControllerSource1;
			} else {
				return messageControllerSource2;
			}
		}
	}

}
