import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetCardsValidationTest {

    @BeforeAll
    public static void setBaseUrl() {
        RestAssured.baseURI = "https://api.trello.com";
    }

    private static RequestSpecification requestWithAuth() {
        return RestAssured.given()
                .queryParams(Map.of(
                        "key", "73f7a097a4567b388231c8ea06e7866d",
                        "token", "ATTA188495fbbe30a3764bb69bd7965c1be94e2af93f400f94aad463c50e5dff418705084EF1"
                ));
    }

    @Test
    public void checkGetCardWithInvalidId() {
        Response response = requestWithAuth()
                .pathParam("id", "invalid")
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetCardWithInvalidAuth() {
        Response response = RestAssured.given()
                .pathParam("id", "64de10a02fea9c85563a7038")
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
    }

    @Test
    public void checkGetCardWithAnotherUserCredentials() {
        Response response = RestAssured.given()
                .queryParams(Map.of(
                        "key", "73f7a097a4567b388231c8ea06e7866d",
                        "token", "018459018349936a264bdcb07b41fa4b538b501ef504fee5d07ea9f86b953e9\n"
                ))
                .pathParam("id", "64de10a02fea9c85563a7038")
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }

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
