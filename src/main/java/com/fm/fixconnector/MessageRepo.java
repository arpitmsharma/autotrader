package com.fm.fixconnector;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.fm.fixconnector.domain.AbstractMarketData;
import com.fm.fixconnector.nordea.MarketData;
import com.fm.fixconnector.seb.QuoteData;
import com.fm.fixconnector.util.Constants;

import quickfix.DefaultMessageFactory;
import quickfix.Group;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.field.MsgType;


public class MessageRepo {
	
	private String quoteId;
	
	protected String messageVersion="FIXT.1.1";
	String senderCompId;
	String targetCompId;
	
	
	protected quickfix.MessageFactory factory=new DefaultMessageFactory();
	
	public  void setQuoteId(QuoteData md) {
			this.quoteId = md.getQuoteId();
	}
	public String getQuoteId() {
		return quoteId;
	}
	
	public String getMessageVersion() {
		return messageVersion;
	}
	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}
	public String getSenderCompId() {
		return senderCompId;
	}
	public void setSenderCompId(String senderCompId) {
		this.senderCompId = senderCompId;
	}
	public String getTargetCompId() {
		return targetCompId;
	}
	public void setTargetCompId(String targetCompId) {
		this.targetCompId = targetCompId;
	}
	public  Message getPriceStreamMessage(Map<Integer,String> valueMap){
		Message message=factory.create(messageVersion, MsgType.MARKET_DATA_REQUEST);
		message.getHeader().setString(49, senderCompId);
		message.getHeader().setString(56, targetCompId);
		
	//	message.setString(553, "FIX2D65");
		message.setString(553, valueMap.get(553));
		//message.setString(262, "4959894");
		//String str=new String(valueMap.get(55));
		message.setString(262,/*str.replace("/", "")+*/String.valueOf(getRandomNo()));	
		int[] ord={55,15,64};
		Group group=new Group(146, 64,ord);
	//	group.setString(55, "NOK/SEK");
		group.setString(55, valueMap.get(55));
		//group.setString(29998, "TIBTEST_2FIXRFSD");
	//	group.setString(15, "NOK");
		//group.setString(64, "1D");
		group.setString(15, valueMap.get(15));
		group.setString(64, valueMap.get(64));
		message.addGroup(group);
		Group group2=new Group(267, 269);
		
		//group2.setInt(269, 0);
		group2.setInt(269,  Integer.parseInt(valueMap.get(269).split(",")[0]));
		group2.setInt(269,  Integer.parseInt(valueMap.get(269).split(",")[1]));
		//group2.setInt(269, 1);
		message.addGroup(group2);
	/*	message.setInt(263, 1);
		message.setInt(265, 0);
		message.setInt(264, 0);
*/		
		message.setInt(263, Integer.parseInt(valueMap.get(263)));
		message.setInt(265, Integer.parseInt(valueMap.get(265)));
		message.setInt(264, Integer.parseInt(valueMap.get(264)));
		return message;
	}
	public Message getUserRequest(Map<Integer,String> valueMap){
		
		Message message=factory.create(messageVersion, MsgType.USER_REQUEST);
		message.getHeader().setString(49, senderCompId);
		message.getHeader().setString(56,targetCompId);
		
		//message.setString(553, "FIX2D65");
	//	message.setString(554, "Cxw83jbr");
		//message.setString(923, "abc443");
		//message.setInt(924, 1);
		
		message.setString(553, valueMap.get(553));
		message.setString(554, valueMap.get(554));
		message.setString(923, String.valueOf(getRandomNo()));
		message.setString(924, valueMap.get(924));
		return message;
		
	}
	
	
	public  Message getOrderMessage(Map<Integer,String> valueMap,AbstractMarketData marketData){
		
		Message message=factory.create(messageVersion, MsgType.ORDER_SINGLE);
		/*message.getHeader().setString(49, "FIX2D65-T");
		message.getHeader().setString(56, "NORDEA");
		*/
		message.getHeader().setString(49, senderCompId);
		message.getHeader().setString(56, targetCompId);
		message.setString(553, valueMap.get("553"));
		message.setString(11, String.valueOf(getRandomNo()));
		message.setString(64, valueMap.get("64"));
		message.setString(117, valueMap.get("117"));
		message.setString(40, valueMap.get("40"));
		message.setString(54, valueMap.get("54"));
		message.setString(38, valueMap.get("38"));
		message.setString(6950, valueMap.get("6950"));
		return message;
	}
	
	public int getRandomNo(){
		Random r=new Random();
		int no= r.nextInt();
		if(no<0){
			no=no*-1;	
		}
		return no;
	}
	

	
}
