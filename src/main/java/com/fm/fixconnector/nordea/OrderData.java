package com.fm.fixconnector.nordea;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.KeyValuePairField;
import org.apache.camel.dataformat.bindy.annotation.Link;
import org.apache.camel.dataformat.bindy.annotation.Message;
import org.apache.camel.dataformat.bindy.annotation.Section;

@CsvRecord(separator = ",")
public class OrderData {
	
	@DataField(pos = 1)
	private String price;
	
	@DataField(pos = 2)
	private String quoteId;

	@DataField(pos = 3)
	private String currencyPair;
	
	@DataField(pos = 4)
	private String userName;
	
	@DataField(pos = 5)
	private String slippage;
	
	
	@DataField(pos = 6)
	private String orderType;
	
	@DataField(pos = 7)
	private String orderSide;
	
	@DataField(pos = 8)
	private String currency;
	
	@DataField(pos = 9)
	private String orderQty;
	
	@DataField(pos = 10)
	private String settleDate;
	
	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair.replace("-", "/");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSlippage() {
		return slippage;
	}

	public void setSlippage(String slippage) {
		this.slippage = slippage;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderSide() {
		return orderSide;
	}

	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public Map<Integer,Object> asMap(){
	Map<Integer, Object> map=new HashMap<Integer, Object>();
	map.put(553, userName);
	map.put(55, currencyPair);
	map.put(15, currency);
	map.put(64, settleDate);
	map.put(117, quoteId);
	map.put(40, orderType);
	map.put(54, orderSide);
	map.put(38, orderQty);
	map.put(44, price);
	map.put(6950, slippage);
	return map;
		
	}
	
	private boolean isNotNull(Object obj){
		if(obj!=null)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "OrderData [price=" + price + ", quoteId=" + quoteId
				+ ", currencyPair=" + currencyPair + ", userName=" + userName
				+ ", slippage=" + slippage + ", orderType=" + orderType
				+ ", orderSide=" + orderSide + ", currency=" + currency
				+ ", orderQty=" + orderQty + ", settleDate=" + settleDate + "]";
	}
	
	
	
}
