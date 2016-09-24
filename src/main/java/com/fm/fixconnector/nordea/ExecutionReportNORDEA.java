package com.fm.fixconnector.nordea;

import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.KeyValuePairField;
import org.apache.camel.dataformat.bindy.annotation.Message;

import com.fm.fixconnector.domain.AbstractTradeCapture;

@Message(keyValuePairSeparator = "=", pairSeparator = "\u0001", type = "FIX", version = "4.1")
public class ExecutionReportNORDEA implements AbstractTradeCapture {

	@KeyValuePairField(tag = 37)
	private String marketOrderId;

	@KeyValuePairField(tag = 11)
	private String clientOrderId;

	@KeyValuePairField(tag = 17)
	private String execId;

	@KeyValuePairField(tag = 20)
	private String execTransType;

	@KeyValuePairField(tag = 39)
	private String orderStatus;

	@KeyValuePairField(tag = 103)
	private String orderRejReason;

	@KeyValuePairField(tag = 1)
	private String account;

	@KeyValuePairField(tag = 55)
	private String symbol;

	@KeyValuePairField(tag = 40)
	private String orderType;

	@KeyValuePairField(tag = 15)
	private String currency;

	@KeyValuePairField(tag = 14)
	private String cumQty;

	@KeyValuePairField(tag = 6)
	private String avgPx;

	@KeyValuePairField(tag = 54)
	private String side;

	@KeyValuePairField(tag = 60)
	private Date transactitionTime;

	public String getMarketOrderId() {
		return marketOrderId;
	}

	public void setMarketOrderId(String marketOrderId) {
		this.marketOrderId = marketOrderId;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String getExecId() {
		return execId;
	}

	public void setExecId(String execId) {
		this.execId = execId;
	}

	public String getExecTransType() {
		return execTransType;
	}

	public void setExecTransType(String execTransType) {
		this.execTransType = execTransType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderRejReason() {
		return orderRejReason;
	}

	public void setOrderRejReason(String orderRejReason) {
		this.orderRejReason = orderRejReason;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCumQty() {
		return cumQty;
	}

	public void setCumQty(String cumQty) {
		this.cumQty = cumQty;
	}

	public String getAvgPx() {
		return avgPx;
	}

	public void setAvgPx(String avgPx) {
		this.avgPx = avgPx;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Date getTransactitionTime() {
		return transactitionTime;
	}

	public void setTransactitionTime(Date transactitionTime) {
		this.transactitionTime = transactitionTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExecutionReport [");
		if (marketOrderId != null) {
			builder.append("marketOrderId=");
			builder.append(marketOrderId);
			builder.append(", ");
		}
		if (clientOrderId != null) {
			builder.append("clientOrderId=");
			builder.append(clientOrderId);
			builder.append(", ");
		}
		if (orderStatus != null) {
			builder.append("execId=");
			builder.append(execId);
			builder.append(", ");
		}
		if (orderStatus != null) {
			builder.append("execTransType=");
			builder.append(execTransType);
			builder.append(", ");
		}
		if (orderStatus != null) {
			builder.append("account=");
			builder.append(account);
			builder.append(", ");
		}
		if (orderType != null) {
			builder.append("orderType=");
			builder.append(orderType);
			builder.append(", ");
		}
		if (orderStatus != null) {
			builder.append("orderStatus=");
			builder.append(orderStatus);
			builder.append(", ");
		}
		if (orderRejReason != null) {
			builder.append("orderRejReason=");
			builder.append(orderRejReason);
			builder.append(", ");
		}
		if (orderStatus != null) {
			builder.append("cumQty=");
			builder.append(cumQty);
			builder.append(", ");
		}
		if (orderStatus != null) {
			builder.append("AvgPrice=");
			builder.append(avgPx);
			builder.append(", ");
		}
		if (symbol != null) {
			builder.append("symbol=");
			builder.append(symbol);
			builder.append(", ");
		}
		if (currency != null) {
			builder.append("currecny=");
			builder.append(currency);
			builder.append(", ");
		}
		if (side != null) {
			builder.append("side=");
			builder.append(side);
			builder.append(", ");
		}
		if (transactitionTime != null) {
			builder.append("transactitionTime=");
			builder.append(transactitionTime);
		}
		builder.append("]");
		return builder.toString();
	}

}
