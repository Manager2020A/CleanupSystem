package com.man.cleanup;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class TaskResourceTest {

    @Test
    public void testGETEndpoint() {
        given().when().get("/task").then().contentType(ContentType.JSON).statusCode(200)
                .body(CoreMatchers.notNullValue());
    }

    @Test
    public void testGET1Endpoint() {
        given().when().param("id", 1l).get("/task").then().contentType(ContentType.JSON).statusCode(200)
                .body(CoreMatchers.notNullValue());
    }

    @Test
    public void testPOSTEndepoint() {
        String json = " { " + "\"name\" : \"Lavar Chão\"," + "\"guidelines\" : \"\"" + " }";

        given().body(json).contentType(ContentType.JSON).post("/task").then().statusCode(201)
                .contentType(ContentType.JSON).body("id", CoreMatchers.notNullValue());
    }
}