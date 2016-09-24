package com.fm.fixconnector.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fm.fixconnector.database.GenericDAO;
import com.fm.fixconnector.domain.AbstractTradeCapture;

//Provide jmx interface
public class Admin {

	GenericDAO dao;
	
	public String getAllTrades(){
	return getHtmlForTrades(dao.getAllObject());
	}
	
	public String getAllTradesForType(String type){
		return getHtmlForTrades(dao.getAllObjectWithTradeType(type));
	}
	
	public String getAllTradesBetweenDate(Date d1,Date d2){
		
		return getHtmlForTrades(filterEligibleTrade((List<AbstractTradeCapture>)dao.getAllObject(), d1, d2));
	}
	
	public String getAllTradesBetweenDateForType(Date d1,Date d2,String type){
		return getHtmlForTrades(filterEligibleTrade((List<AbstractTradeCapture>)dao.getAllObjectWithTradeType(type), d1, d2));
	}
	
	private String getHtmlForTrades(Object object){
		StringBuffer result=new StringBuffer();
		List<AbstractTradeCapture> trades=(List<AbstractTradeCapture>)object;
		result.append("<table>");
		for(AbstractTradeCapture tradeCapture:trades){
			result.append("<tr>");
//			result.append("<td>");result.append(tradeCapture.getClientOrderId());result.append("</td>");
//			result.append("<td>");result.append(tradeCapture.getAvgPx()result.append("</td>");
//			result.append("<td>");result.append(tradeCapture.getQuantity());result.append("</td>");
//			result.append("<td>");result.append(tradeCapture.getTradeDate());result.append("</td>");
//			result.append("<td>");result.append("");result.append("</td>");
			result.append("</tr>");
		}
		result.append("</table>");
		return result.toString();
	}
	private List filterEligibleTrade(List<AbstractTradeCapture> trades,Date d1,Date d2){ 
		List eligibleTrade=new ArrayList<AbstractTradeCapture>();
		for(AbstractTradeCapture tradeCapture:trades){
//			if(tradeCapture.getTradeDate().after(d1) && tradeCapture.getTradeDate().before(d2)){
//				eligibleTrade.add(tradeCapture);
//			}
			}
		return eligibleTrade;
		}
}
