package com.codeaholic;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.text.IsEmptyString.emptyString;

@QuarkusTest
class EntryResourceTest {

    @Test
    void testRelations() {
        Response response = given()
                .when()
                .get("/entries")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();
        assertThat(response.jsonPath().getList("slug")).containsExactlyInAnyOrder("root", "js", "euler");
    }
}
