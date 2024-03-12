package com.epam.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.epam.model.OpenWeatherMapResponse;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/data/2.5/")
@RegisterRestClient(configKey="weather-api")
public interface WeatherService {

   @GET
   @Path("/weather")
   OpenWeatherMapResponse getByCity(@QueryParam("q") String city, @QueryParam("appid") String appId);
   
   @GET
   @Path("/weather")
   OpenWeatherMapResponse getByCoordinates(@QueryParam("lat") String latitude,@QueryParam("lon") String longitude, @QueryParam("appid") String appId);
}
