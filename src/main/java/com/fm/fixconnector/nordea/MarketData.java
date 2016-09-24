package com.fm.fixconnector.nordea;

import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.KeyValuePairField;
import org.apache.camel.dataformat.bindy.annotation.Link;
import org.apache.camel.dataformat.bindy.annotation.Message;
import org.apache.camel.dataformat.bindy.annotation.Section;

import com.fm.fixconnector.domain.AbstractMarketData;

@Message(keyValuePairSeparator = "=", pairSeparator = "\u0001", type = "FIX", version = "4.1")
public class MarketData implements AbstractMarketData {

	/*
	 * @Link private Header header;
	 * 
	 * @Link private Trailer trailer;
	 */
	@KeyValuePairField(tag = 278)
	private String quoteId;

	@KeyValuePairField(tag = 270)
	private double price;

	@KeyValuePairField(tag = 269)
	private String priceType;
	
	@KeyValuePairField(tag = 15)
	private String currency;
	
	@KeyValuePairField(tag = 55)
	private String symbol;

//	@KeyValuePairField(tag = 271) //check
	private int quantity;

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getCurrency() {
		return currency;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MarketData [");
		if (price != 0) {
			builder.append("price=");
			builder.append(price);
			builder.append(", ");
		}
		if (quoteId != null) {
			builder.append("quoteId=");
			builder.append(quoteId);
			builder.append(", ");
		}
		if (priceType != null) {
			builder.append("priceType=");
			builder.append(priceType);
		}
		builder.append("]");
		return builder.toString();
	}

	
	/*
	 * public Header getHeader() { return header; }
	 * 
	 * public void setHeader(Header header) { this.header = header; }
	 * 
	 * public Trailer getTrailer() { return trailer; }
	 * 
	 * public void setTrailer(Trailer trailer) { this.trailer = trailer; }
	 */

}
