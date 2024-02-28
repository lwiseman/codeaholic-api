package com.codeaholic;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EntryResourceTest {
    @Test
    void testEntryEndpoint() {
        given()
          .when().get("/entries/1")
          .then()
             .statusCode(200)
             .body(contains("{"));
    }

}
