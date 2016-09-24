package com.fm.fixconnector.util;

import java.util.Map;
import java.util.Random;

public class RandomNumberGenerator {

	public void assignNo(Map<String,String> valueMap){
		Random r=new Random();
		int no= r.nextInt();
		if(no<0){
			no=no*-1;	
		
		valueMap.put(Constants.ID, String.valueOf(no));
	}
	}
	}
