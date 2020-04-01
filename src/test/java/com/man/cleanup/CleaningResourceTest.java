package com.man.cleanup;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CleaningResourceTest {

    @Test
    public void testGETEndpoint() {
        given().when().get("/cleaning").then().contentType(ContentType.JSON).statusCode(200)
                .body(CoreMatchers.notNullValue());
    }

    @Test
    public void testGET1Endpoint() {
        given().when().param("id", 1l).get("/cleaning").then().contentType(ContentType.JSON).statusCode(200)
                .body(CoreMatchers.notNullValue());
    }

    @Test
    public void testPOSTEndepoint() {
        String json = " { " + "\"name\" : \"TESTE\"," + "\"guidelines\" : \"\"," + "\"frequencys\" : \"MANUAL\","
                + "\"nextDate\" : \"2020-05-03\"," + "\"estimateTime\" : \"01:00:00\"" + " }";

        given().body(json).contentType(ContentType.JSON).post("/cleaning").then().statusCode(201)
                .contentType(ContentType.JSON).body("id", CoreMatchers.notNullValue());
    }
}