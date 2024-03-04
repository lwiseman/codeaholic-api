package com.codeaholic;

import com.codeaholic.Entry;

import java.util.ArrayList;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.hibernate.orm.PersistenceUnit;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.text.IsEmptyString.emptyString;

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
        assertThat(response.jsonPath().getList("slug")).containsExactlyInAnyOrder("root", "js", "euler");
	Entry test = new Entry();
	test.id = Long.valueOf(4);
	test.title = "Test";
	test.slug = "test";
	test.content = "...";
//        test.persist();	
//	em.persist(test);
    }
}
