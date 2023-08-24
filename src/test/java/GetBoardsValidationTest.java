import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsValidationTest extends BaseTest {

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
        Response response = requestWithoutAuth()
                .pathParam("id", "64799a8e4853e983424c00d3")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
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