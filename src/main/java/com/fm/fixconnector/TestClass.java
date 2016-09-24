package com.fm.fixconnector;

public class TestClass {

	String globalVar="";
	public static void main(String args[]){
		while(true){
			System.out.println("I am Running .......");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String callMe(String var){
		
		globalVar=globalVar+var;
		
		return "Hi I m called with" +globalVar;
	}
	
	public String callMeStatic(String var){
		return "Hi I m called with static" +var;
	}
	public void TestClass(){
		
	}
}
