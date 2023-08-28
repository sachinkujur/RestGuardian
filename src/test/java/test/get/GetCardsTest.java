package test.get;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

public class GetCardsTest extends BaseTest {

    @Test
    public void checkGetCards() {
        requestWithAuth()
                .pathParam("list_id", "64799a8e4853e983424c00da")
                .get("/1/lists/{list_id}/cards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }

    @Test
    public void checkGetCard() {
        requestWithAuth()
                .pathParam("id", "64de10a02fea9c85563a7038")
                .get("/1/cards/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("New card"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"));
    }
}
