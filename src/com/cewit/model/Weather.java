package com.cewit.model;

import java.sql.Timestamp;

public class Weather {

	private int rowNo;
	private String city;
	private String state;
	private Timestamp timestamp;
	private String description;

	public int getRowNo() {
		return rowNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
