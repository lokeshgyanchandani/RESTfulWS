package com.cewit.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class WeatherClient {
	public static void main(String args[]) {

		new WeatherThread("Stony Brook", "New York").start();
		//System.out.println("Client Response \n" + getClientResponse(resource));
		//System.out.println("Response \n" + getResponse(resource) + "\n\n");
	}
}

class WeatherThread extends Thread {
	public static final String BASE_URI = "http://localhost:11258/CEWIT_Assignment";
	public static final String PATH_GET_WEATHER = "/ws/getWeatherReport";
	public static final String PATH_PUT_WEATHER = "/ws/putWeatherReport";

	public static final String OPEN_WEATHER_API_BASE_URI = "http://api.openweathermap.org/data/2.5/weather";

	WebResource resource = null;
	volatile boolean flag = true;

	private String city, state;

	public WeatherThread(String city, String state) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		resource = client.resource(BASE_URI);

		this.city = city;
		this.state = state;
	}

	public void run() {
		while (flag) {
			String weatherAPILocationPath = OPEN_WEATHER_API_BASE_URI + "?q=" + "Stony Brook";
			JSONObject json = readJsonFromUrl(weatherAPILocationPath);
			System.out.println(putResponse(resource, city + "/" + state + "/" + parseWeather(json)));
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String getClientResponse(WebResource resource) {
		String response = resource.path("rest").path(PATH_GET_WEATHER)
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).toString();
		return response;
	}

	private static String getResponse(WebResource resource) {
		String response = resource.path("rest").path(PATH_GET_WEATHER)
				.accept(MediaType.APPLICATION_JSON).get(String.class).toString();
		return response;
	}

	private static String putResponse(WebResource resource, String path) {
		String response = resource.path("rest").path(PATH_PUT_WEATHER + "/" + path)
				.accept(MediaType.TEXT_PLAIN).put(String.class);
		return response;
	}

	public static JSONObject readJsonFromUrl(String url) {
		InputStream is = null;
		JSONObject json = null;
		try {
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			json = new JSONObject(jsonText);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	private static String parseWeather(JSONObject json) {
		String weatherDescription = null;
		try {
			JSONArray weatherArray = (JSONArray) json.get("weather");
			weatherDescription = (String) weatherArray.getJSONObject(0).get("description");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weatherDescription;
	}

	private static String readAll(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		int line;
		while ((line = reader.read()) != -1) {
			sb.append((char) line);
		}
		return sb.toString();
	}
}
