import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsValidationTest {

    private static RequestSpecification requestWithAuth() {
        return RestAssured.given()
                .queryParams(Map.of(
                        "key", "73f7a097a4567b388231c8ea06e7866d",
                        "token", "ATTA188495fbbe30a3764bb69bd7965c1be94e2af93f400f94aad463c50e5dff418705084EF1"
                ));
    }

    @BeforeAll
    public static void setBaseUrl() {
        RestAssured.baseURI = "https://api.trello.com";
    }

    @Test
    public void checkGetBoardWithInvalidId() {
        Response response = requestWithAuth()
                .pathParam("id", "invalid")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetBoardWithInvalidAuth() {
        Response response = RestAssured.given()
                .pathParam("id", "64799a8e4853e983424c00d3")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials() {
        Response response = RestAssured.given()
                .queryParams(Map.of(
                        "key", "73f7a097a4567b388231c8ea06e7866d",
                        "token", "018459018349936a264bdcb07b41fa4b538b501ef504fee5d07ea9f86b953e9\n"
                ))
                .pathParam("id", "64799a8e4853e983424c00d3")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}