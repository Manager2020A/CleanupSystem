package com.man.cleanup;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testGETEndpoint() {
        given().when().get("/product").then().contentType(ContentType.JSON).statusCode(200)
                .body(CoreMatchers.notNullValue());
    }

    @Test
    public void testGET1Endpoint() {
        given().when().param("id", 1l).get("/product").then().contentType(ContentType.JSON).statusCode(200)
                .body(CoreMatchers.notNullValue());
    }

    @Test
    public void testPOSTEndepoint() {
        String json = "{" +
            "\"name\" : \"Produto Teste\"," +
            "\"branding\" : \"Marca Teste\"," +
            "\"capacity\" : 123," +
            "\"active\" : false" +   
        "}";

        given().body(json).contentType(ContentType.JSON).post("/product").then().statusCode(201)
                .contentType(ContentType.JSON).body("id", CoreMatchers.notNullValue());
    }
}