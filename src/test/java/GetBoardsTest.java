import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsTest {

    private static RequestSpecification requestWithAuth() {
        return RestAssured.given()
                .queryParams(Map.of(
                        "key", "73f7a097a4567b388231c8ea06e7866d",
                        "token", "ATTA188495fbbe30a3764bb69bd7965c1be94e2af93f400f94aad463c50e5dff418705084EF1"
                ));
    }

    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.trello.com";
    }

    @Test
    public void checkGetBoards(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("member", "sachinkujur")
                .get("/1/members/{member}/boards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }

    @Test
    public void checkGetBoard(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", "64799a8e4853e983424c00d3")
                .get("/1/boards/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Test"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));
    }
}