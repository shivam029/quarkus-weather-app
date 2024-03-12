package com.epam;



import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class WeatherResourceTest {

    @Test
    public void testGetWeatherEndpoint() {
        given()
          .when().get("/weather/lat/44.34/lon/10.99")
          .then()
             .statusCode(200);
             
    }
}
