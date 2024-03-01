package com.codeaholic;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.smallrye.mutiny.Uni;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class EntryResourceTest {

    @Test
    void testRelations() {
        Entry root = new Entry();
        root.setId(Long.valueOf(1));
        Uni<Entry> uni = Uni.createFrom().item(root);
        Uni<EntryDTO> entryDtoUni = uni.map(EntryDTO::ofEntry);
    }
}
