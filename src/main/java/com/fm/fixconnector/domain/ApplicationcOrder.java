package com.fm.fixconnector.domain;

import java.util.Date;

public class ApplicationcOrder {

	double price;
	AbstractMarketData marketData;
	String orderSide;
	int quanitiy;
	Date validTime;
	
	
	public ApplicationcOrder(double price, AbstractMarketData marketData,
			String orderSide, int quanitiy, Date validTime) {
		super();
		this.price = price;
		this.marketData = marketData;
		this.orderSide = orderSide;
		this.quanitiy = quanitiy;
		this.validTime = validTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public AbstractMarketData getMarketData() {
		return marketData;
	}
	public void setMarketData(AbstractMarketData marketData) {
		this.marketData = marketData;
	}
	public String getOrderSide() {
		return orderSide;
	}
	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}
	public int getQuanitiy() {
		return quanitiy;
	}
	public void setQuanitiy(int quanitiy) {
		this.quanitiy = quanitiy;
	}
	public Date getValidTime() {
		return validTime;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	
	
}
