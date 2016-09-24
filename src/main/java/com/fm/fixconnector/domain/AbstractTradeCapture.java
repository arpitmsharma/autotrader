package com.fm.fixconnector.domain;

import java.util.Date;

public interface AbstractTradeCapture {
	public String getMarketOrderId();

	public String getClientOrderId();

	public String getExecId();

	public String getExecTransType();

	public String getOrderStatus();

	public String getAccount();

	public String getSymbol();

	public String getCurrency();

	public String getCumQty();

	public String getAvgPx();

	public String getSide();

	public Date getTransactitionTime();

}
