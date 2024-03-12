package com.epam.util;

import org.jboss.logging.Logger;
import com.epam.model.OpenWeatherMapResponse;
import com.epam.model.WeatherResponseModel;
import com.epam.resource.WeatherResource;

public class WeatherResponseConverter {

	private static final Logger LOG = Logger.getLogger(WeatherResource.class);

	public static WeatherResponseModel parseJsonSource(OpenWeatherMapResponse openWeatherMapResponse) {

		WeatherResponseModel wres = new WeatherResponseModel();
		
		if(openWeatherMapResponse == null || openWeatherMapResponse.getCod()!=200) {
			wres.setMessage("Error");
			return wres;
		}
		
		wres.setTemp(openWeatherMapResponse.getMain().getTemp());
		wres.setPressure(openWeatherMapResponse.getMain().getPressure());
		wres.setHumidity(openWeatherMapResponse.getMain().getHumidity());
		wres.setTemp_min(openWeatherMapResponse.getMain().getTempMin());
		wres.setTemp_max(openWeatherMapResponse.getMain().getTempMax());
		wres.setGrnd_level(openWeatherMapResponse.getMain().getGroundLevel());
		wres.setSea_level(openWeatherMapResponse.getMain().getSeaLevel());
		wres.setFeels_like(openWeatherMapResponse.getMain().getFeelsLike());
		wres.setLatitude(openWeatherMapResponse.getCoord().getLat());
		wres.setLongitude(openWeatherMapResponse.getCoord().getLon());
		wres.setStatuscode(openWeatherMapResponse.getCod());
		wres.setMessage("Success");

		LOG.info(wres);
		
		return wres;
	}

	
}
