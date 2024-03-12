package com.epam.resource;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.epam.exception.NoWeatherDataAvailableException;
import com.epam.model.WeatherResponseModel;
import com.epam.service.WeatherService;
import com.epam.util.JsonParser;
import com.epam.util.WeatherResponseConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/weather")
public class WeatherResource {

	@Inject
	@RestClient
	WeatherService weatherService;
	private static final Logger LOG = Logger.getLogger(WeatherResource.class);

	@ConfigProperty(name = "appId")
	String appid;

	@GET
	@Path("/lat/{latitude}/lon/{longitude}")
	@Produces(MediaType.APPLICATION_JSON)
	public WeatherResponseModel getByCoordinates(@PathParam("latitude") String latitude,
			@PathParam("longitude") String longitude) throws NoWeatherDataAvailableException {

		Object resSourceJsonString = weatherService.getByCoordinates(latitude, longitude, appid);
		LOG.info("resSourceJsonString getByCoordinates " + resSourceJsonString);

		JsonNode node = null;
		try {
			node = JsonParser.parse(resSourceJsonString.toString());
		} catch (JsonMappingException e) {
			LOG.info("JsonMappingException " + e.getMessage());
		} catch (JsonProcessingException e) {
			LOG.info("JsonProcessingException " + e.getMessage());
		}

		int statusCode = 0;
		String error_message = null;
		if (node != null) {
			statusCode = node.get("cod").asInt();
			error_message = node.get("message").asText();
		}

		LOG.info("status code " + statusCode);
		LOG.info("Error message " + error_message);

		if (statusCode == 401 || statusCode == 400) {

			throw new NoWeatherDataAvailableException("Error thrown with message " + error_message);
		}

		WeatherResponseModel weatherRes = WeatherResponseConverter.parseJsonSource(resSourceJsonString.toString());

		LOG.info("weatherRes " + weatherRes);

		return weatherRes;

	}
	
	@GET
	@Path("/city/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getByCity(@PathParam("name") String city) throws NoWeatherDataAvailableException {

		return weatherService.getByCity(city, appid);
	}
}