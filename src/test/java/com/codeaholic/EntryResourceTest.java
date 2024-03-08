package com.codeaholic;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class EntryResourceTest {

//    @Inject
//    io.vertx.mutiny.pgclient.PgPool client;

//    @Inject
//    UserTransaction userTransaction;

//    @Inject
//    EntityManager em;

    @Test
//    @Transactional
    void testRelations() {
        Response response = given()
                .when()
                .get("/entries")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();
        assertThat(response.jsonPath().getList("slug")).contains("resume");
//        assertThat(response.jsonPath().getList("slug")).containsExactlyInAnyOrder("root", "js", "euler");
    }
}
