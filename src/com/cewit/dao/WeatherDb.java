package com.cewit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.cewit.model.Weather;

public class WeatherDb {

	public ArrayList<Weather> getFeeds(Connection connection) throws Exception {
		ArrayList<Weather> weatherData = new ArrayList<Weather>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM weather");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Weather weatherObject = new Weather();
				weatherObject.setCity(rs.getString("city"));
				weatherObject.setState(rs.getString("state"));
				weatherObject.setTimestamp(rs.getTimestamp("time"));
				weatherObject.setDescription(rs.getString("description"));
				weatherData.add(weatherObject);
			}
			return weatherData;
		} catch (Exception e) {
			throw e;
		}
	}

	public void insertFeed(Connection connection, Weather weatherObject) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement("Insert into weather (city, state, time, description) values(?, ?, ?, ?)");
			Timestamp currentDate = new Timestamp(new java.util.Date().getTime());

			ps.setString(1, weatherObject.getCity());
			ps.setString(2, weatherObject.getState());
			ps.setTimestamp(3, currentDate);
			ps.setString(4, weatherObject.getDescription());
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
}
