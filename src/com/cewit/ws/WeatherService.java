package com.cewit.ws;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cewit.app.WeatherAppHandler;
import com.cewit.model.Weather;
import com.google.gson.Gson;

@Path("/ws")
public class WeatherService {

	@GET
	@Path("/getWeatherReport")
	@Produces(MediaType.APPLICATION_JSON)
	public String feed() {
		StringBuilder feeds = null;
		try {
			ArrayList<Weather> feedData = null;
			WeatherAppHandler handler = WeatherAppHandler.getInstance();
			feedData = handler.getFeeds();
			Gson gson = new Gson();
			// System.out.println(gson.toJson(feedData));
			// feeds = gson.toJson(feedData);

			int count = 1;
			feeds = new StringBuilder("Row\t\tCity\t\t\tState\t\t\tTimestamp\t\t\tDescription\n\n");
			for (Weather weather : feedData) {
				feeds.append(count + "\t\t" + weather.getCity() + "\t\t"
						+ weather.getState() + "\t\t" + weather.getTimestamp() + "\t\t" + weather.getDescription());
				feeds.append("\n");
				count++;
			}

		} catch (Exception e) {
			System.out.println("Error occurred!");
		}
		return feeds.toString();
	}

	@GET
	@Path("/shutdown")
	public void shutdown() {

	}

	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/putWeatherReport/{city}/{state}/{description}")
	public String insertFeed(@PathParam("city") String city, @PathParam("state") String state,
			@PathParam("description") String description) {
		Weather weather = new Weather();
		try {
			WeatherAppHandler handler = WeatherAppHandler.getInstance();

			weather.setCity(city);
			weather.setState(state);
			weather.setTimestamp(new Timestamp(new java.util.Date().getTime()));
			weather.setDescription(description);

			handler.insertFeed(weather);
		} catch (Exception e) {
			System.out.println("Error occurred");
		}
		return weather.toString();
	}
}
