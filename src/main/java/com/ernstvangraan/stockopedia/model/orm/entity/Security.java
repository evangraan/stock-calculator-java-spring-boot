package com.ernstvangraan.stockopedia.model.orm.entity;

import java.util.HashMap;

public class Security {
	private int id;
	private String symbol;
	private HashMap<String, Float> attributes = new HashMap<String, Float>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public Float getAttribute(String key) {
		return attributes.getOrDefault(key, null);
	}
	
	public void addAttribute(String key, Float value) {
		attributes.put(key, value);
	}
	
	public HashMap<String, Float> getAttributes(){
		return attributes;
	}
}
