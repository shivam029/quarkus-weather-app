package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

	 @JsonProperty("coord")
    private Coord coord;
	 
	@JsonProperty("weather")
    private Weather[] weather;
	
    private String base;
    
    @JsonProperty("main")
    private Main main;
    
    @JsonProperty("visibility")
    private int visibility;
    
    private Wind wind;
    private Clouds clouds;

    @JsonProperty("dt")
    private long dateTime;

    @JsonIgnoreProperties("sys")
    private Sys sys;
    
    private int timezone;
    private int id;
    private String name;
    private int cod;

    // Constructors, getters, and setters

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public static class Coord {
        private double lon;
        private double lat;

        // Constructors, getters, and setters

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }

    public static class Weather {
        
    	@JsonProperty("id")
    	private int id;
    	
    	@JsonProperty("main")
        private String main;
    	
    	@JsonProperty("description")
        private String description;
    	
    	@JsonProperty("icon")
        private String icon;

        // Constructors, getters, and setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    @JsonIgnoreProperties
    public static class Main {
    	
    	@JsonProperty("temp")
        private double temp;
    	
    	@JsonProperty("pressure")
        private int pressure;
    	
    	@JsonProperty("humidity")
        private int humidity;
        
    	@JsonProperty("temp_min")
        private double temp_min;
        
    	@JsonProperty("temp_max")
        private double temp_max;
    	
    	@JsonProperty("grnd_level")
    	private double grnd_level;
    	
    	@JsonProperty("sea_level")
    	private double sea_level;
    	
    	@JsonProperty("feels_like")
    	private double feels_like;

        // Constructors, getters, and setters

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

		public double getGrnd_level() {
			return grnd_level;
		}

		public void setGrnd_level(double grnd_level) {
			this.grnd_level = grnd_level;
		}

		public double getSea_level() {
			return sea_level;
		}

		public void setSea_level(double sea_level) {
			this.sea_level = sea_level;
		}

		public double getFeels_like() {
			return feels_like;
		}

		public void setFeels_like(double feels_like) {
			this.feels_like = feels_like;
		}
        
        
    }

    @JsonIgnoreType
    public static class Wind {
        private double speed;
        private int deg;

        // Constructors, getters, and setters

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }
    }

    @JsonIgnoreType
    public static class Clouds {
        private int all;

        // Constructors, getters, and setters

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }

    @JsonIgnoreType
    public static class Sys {
    	
    	@JsonIgnoreProperties("type")
        private int type;
    	
    	@JsonIgnoreProperties("id")
        private int id;
    	
    	@JsonIgnoreProperties("message")
        private double message;
    	
    	@JsonIgnoreProperties("country")
    	private String country;
    	
    	@JsonIgnoreProperties("sunrise")
        private long sunrise;
    	
    	@JsonIgnoreProperties("sunset")
        private long sunset;

        // Constructors, getters, and setters

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMessage() {
            return message;
        }

        public void setMessage(double message) {
            this.message = message;
        }

        
        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }
    }
}
