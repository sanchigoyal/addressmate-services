package com.services.addressmate.bean;

public class TransactionType {
	private String type;
	private String symbol;
	
	public TransactionType(String type, String symbol){
		this.type = type;
		this.symbol = symbol;
	}
	
	public TransactionType(){
		
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
}
