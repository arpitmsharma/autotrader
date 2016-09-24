package com.fm.fixconnector.seb;

import org.apache.camel.dataformat.bindy.annotation.KeyValuePairField;
import org.apache.camel.dataformat.bindy.annotation.Message;

import com.fm.fixconnector.domain.AbstractMarketData;

@Message(keyValuePairSeparator = "=", pairSeparator = "\u0001", type = "FIX", version = "4.3")
public class QuoteData implements AbstractMarketData {

	@KeyValuePairField(tag = 117)
	private String quoteId;

	private double price;

	@KeyValuePairField(tag = 132)
	private double bidPrice;

	@KeyValuePairField(tag = 133)
	private double offerPrice;

	@KeyValuePairField(tag = 54)
	private String priceType;

	@KeyValuePairField(tag = 15)
	private String currency;

	@KeyValuePairField(tag = 55)
	private String symbol;

	@KeyValuePairField(tag = 38)
	private int quantity;

	public double getPrice() {
		if ("1".equalsIgnoreCase(priceType))
			return getOfferPrice();
		else
			return getBidPrice();
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
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
		if (getPrice() != 0) {
			builder.append("price=");
			builder.append(getPrice());
			builder.append(", ");
		}
		if (bidPrice != 0) {
			builder.append("bid price=");
			builder.append(bidPrice);
			builder.append(", ");
		}
		if (offerPrice != 0) {
			builder.append("offer price=");
			builder.append(offerPrice);
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

}
