package com.epam.util;

import org.jboss.logging.Logger;

import com.epam.model.WeatherResponse;
import com.epam.model.WeatherResponse.Main;
import com.epam.model.WeatherResponseModel;
import com.epam.resource.WeatherResource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WeatherResponseConverter {

	private static final Logger LOG = Logger.getLogger(WeatherResource.class);

	public static Main convertJsonToMainWeatherDetails(String jsonResponse) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED, true);
			objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION, true);
			
			WeatherResponse weatherResponse = objectMapper.readValue(jsonResponse, WeatherResponse.class);

			LOG.info(" getTemp " + weatherResponse.getMain().getTemp());

			Main mainRes = new Main();
			mainRes.setTemp(weatherResponse.getMain().getTemp());
			mainRes.setTemp_max(weatherResponse.getMain().getTemp_max());
			mainRes.setHumidity(weatherResponse.getMain().getHumidity());
			mainRes.setPressure(weatherResponse.getMain().getPressure());
			return mainRes;
		} catch (Exception e) {

			LOG.info("Inside catch ");
			e.printStackTrace();
			return null;
		}
	}

	public static WeatherResponseModel parseJsonSource(String nonStandardJson) {

		String formatJosn = nonStandardJson.substring(nonStandardJson.indexOf("temp="),
				nonStandardJson.indexOf("clouds") - 3);

		LOG.info("Formatted Json String  " + formatJosn);

		WeatherResponseModel wres = new WeatherResponseModel();

		String[] res = formatJosn.split(",");
		for (String myStr : res) {
			String[] finalStr = myStr.split("=");

			if (finalStr[0].strip().toString().equals("temp")) {
				wres.setTemp(Double.parseDouble(finalStr[1]));
			}
			if (finalStr[0].strip().toString().equals("pressure")) {
				wres.setPressure(Double.parseDouble(finalStr[1]));
			}
			if (finalStr[0].strip().toString().equals("humidity")) {
				wres.setHumidity(Double.parseDouble(finalStr[1]));
			}
			if (finalStr[0].strip().toString().equals("temp_min")) {
				wres.setTemp_min(Double.parseDouble(finalStr[1]));
			}
			if (finalStr[0].strip().toString().equals("temp_max")) {
				wres.setTemp_max(Double.parseDouble(finalStr[1]));
			}
			if (finalStr[0].strip().toString().equals("grnd_level")) {
				wres.setGrnd_level(Double.parseDouble(finalStr[1]));
			}
			if (finalStr[0].strip().toString().equals("sea_level")) {
				wres.setSea_level(Double.parseDouble(finalStr[1]));
			}
			if (finalStr[0].strip().toString().equals("feels_like")) {
				wres.setFeels_like(Double.parseDouble(finalStr[1]));
			}
		}

		return wres;
	}

	public static String parseJson(String jsonString) {

		String modifiedJsonString = null;
		try {
			// Parse JSON string into a JsonNode
			try {
				JsonNode node = JsonParser.parse(jsonString);

				LOG.info("visibility " + node.get("visibility").asLong());
				LOG.info("timezone " + node.get("timezone").asLong());

			} catch (Exception e) {
				LOG.info("Insied the new catch ");
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modifiedJsonString;

	}

	public static String removeUnwantedField(String jsonString) {

		String modifiedJsonString = null;
		try {

			// Parse JSON string into a JsonNode
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED, true);
			objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION, true);
			objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);

			JsonNode jsonNode = objectMapper.readTree(jsonString);

			// Remove the "country" field
			if (jsonNode.isObject() && jsonNode.has("sys")) {
				((ObjectNode) jsonNode).remove("sys");
			}

			// Remove the "weather" field
			if (jsonNode.isObject() && jsonNode.has("weather")) {
				((ObjectNode) jsonNode).remove("weather");
			}

			// Convert the modified JsonNode back to a JSON string
			modifiedJsonString = objectMapper.writeValueAsString(jsonNode);

			// Print the modified JSON string

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modifiedJsonString;

	}

	private static String addDoubleQuotes(String json) {
		// Add double quotes to keys and values using regular expressions
		LOG.info("Inside addDoubleQuotessss ");
		String quotedJson = json.replaceAll("(\\b\\w+\\b)", "\"$1\"");

		return quotedJson;
	}
}
