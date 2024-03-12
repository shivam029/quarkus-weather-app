package com.epam;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.epam.model.WeatherResponseModel;
import com.epam.util.WeatherResponseConverter;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class WeatherResourceTest {
	
	@Test
    public void testGetWeatherWithLatitudeAndLongitudeEndpoint() {
       
        given()
        .when()
        .get("/weather/lat/44.34/lon/10.99") 
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("latitude", is(44.34F))
        .body("longitude", is(10.99F))
        .body("temp", greaterThan(0.0F))
        .body("pressure", greaterThan(0.0F))
        .body("humidity", greaterThan(0.0F));
        
       }
    
    @Test
    public void testParseJsonSourceMethod_WithNullInput() {
    	
    	WeatherResponseModel result = WeatherResponseConverter.parseJsonSource(null);
       
        assertEquals("Error" ,result.getMessage());
    }
    
    @Test
    public void testGetWeatherWithCityNameEndpoint() {
       
        given()
        .when()
        .get("/weather/city/paris") 
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("latitude", greaterThan(0.0F))
        .body("longitude", greaterThan(0.0F))
        .body("temp", greaterThan(0.0F));
        
       }
    
    
   
    
   
}
