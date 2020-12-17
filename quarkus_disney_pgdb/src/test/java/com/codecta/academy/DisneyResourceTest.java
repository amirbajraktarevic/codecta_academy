package com.codecta.academy;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class DisneyResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/disney")
                .then()
                .statusCode(200)
                .body("welcome",  equalTo("Welcome Lands of Disneyland!"));
    }

}