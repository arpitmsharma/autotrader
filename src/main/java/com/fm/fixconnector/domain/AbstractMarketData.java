package com.fm.fixconnector.domain;

public interface  AbstractMarketData {

	public  double getPrice();
	public  String getPriceType();
	public  String getCurrency();
	public  String getSymbol();
	public  String getQuoteId();
	public  int getQuantity();
	public  void setQuantity(int quantity);
	
}
