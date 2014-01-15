package com.cewit.app;

import java.sql.Connection;
import java.util.ArrayList;

import com.cewit.dao.Database;
import com.cewit.dao.WeatherDb;
import com.cewit.model.Weather;

public class WeatherAppHandler {

	public static WeatherAppHandler weatherHandlerInstance = null;

	public ArrayList<Weather> getFeeds() throws Exception {
		ArrayList<Weather> feeds = null;
		try {
			Database dbase = new Database();
			Connection connection = dbase.getConnection();
			WeatherDb weatherDb = new WeatherDb();
			feeds = weatherDb.getFeeds(connection);

		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}

	public void insertFeed(Weather weather) throws Exception {
		try {
			Database dbase = new Database();
			Connection connection = dbase.getConnection();
			WeatherDb weatherDb = new WeatherDb();
			weatherDb.insertFeed(connection, weather);

		} catch (Exception e) {
			throw e;
		}
	}

	public static WeatherAppHandler getInstance() {
		if (weatherHandlerInstance == null) {
			weatherHandlerInstance = new WeatherAppHandler();
		}
		return weatherHandlerInstance;
	}
}