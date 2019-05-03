package com.hsbc.test;

import java.time.LocalDateTime;

public class MyTrade {
	private int id;
	private String type;
	private String time;
	private Double amout;
	
	public MyTrade(int id,String lst) {
		String lst2[] = lst.split(",");
		this.type =lst2[0];
		this.time =lst2[1];
		this.amout =Double.valueOf(lst2[2]);
		this.id=id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getAmout() {
		return amout;
	}
	public void setAmout(Double amout) {
		this.amout = amout;
	}
	
	

}
