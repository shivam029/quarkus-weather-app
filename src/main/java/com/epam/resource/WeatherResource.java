package com.epam.resource;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.epam.exception.NoWeatherDataAvailableException;
import com.epam.model.OpenWeatherMapResponse;
import com.epam.model.WeatherResponseModel;
import com.epam.service.WeatherService;
import com.epam.util.WeatherResponseConverter;
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

		OpenWeatherMapResponse responseSourceJson = weatherService.getByCoordinates(latitude, longitude, appid);
		
		LOG.info("status_code:"+responseSourceJson.getCod());
		
		if (responseSourceJson.getCod() == 401 || responseSourceJson.getCod() == 400) {

			throw new NoWeatherDataAvailableException("Error occurred during OpenweatherMap API fetch with status code" + responseSourceJson.getCod());
		}

		WeatherResponseModel weatherRes = WeatherResponseConverter.parseJsonSource(responseSourceJson);

		return weatherRes;

	}
	
	@GET
	@Path("/city/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public WeatherResponseModel getByCity(@PathParam("name") String city) throws NoWeatherDataAvailableException {

		OpenWeatherMapResponse responseSourceJson = weatherService.getByCity(city, appid);
		
		WeatherResponseModel weatherRes = WeatherResponseConverter.parseJsonSource(responseSourceJson);
		
		return weatherRes;
	}
}