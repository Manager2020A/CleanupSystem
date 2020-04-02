package com.man.cleanup;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class TaskResourceTest {

    @Test
    // Testa se retorna um json das tarefas (todas)
    public void testGETEndpoint() {
        given().when().get("/task").then().contentType(ContentType.JSON).statusCode(200)
                .body(CoreMatchers.notNullValue());
    }

    // Testa se retorna um json das tarefas (por id)
    @Test
    public void testGET1Endpoint() {
        given().when().param("id", 1l).get("/task").then().contentType(ContentType.JSON).statusCode(200)
                .body(CoreMatchers.notNullValue());
    }

    // Testa se esta salvando a tarefa e após deleta
    @Test
    public void testPOSTEndepoint() {
        String json = " { " + "\"name\" : \"TESTE\"," + "\"guidelines\" : \"\", \"active\" : \"true\"" + " }";

        JsonPath resp = given().body(json).contentType(ContentType.JSON).post("/task").then().statusCode(201)
                .contentType(ContentType.JSON).extract().jsonPath();

        Long id = resp.getLong("id ");

        assertTrue(id > 0);

        given().when().delete("/task/" + id).then().statusCode(204);
    }
}