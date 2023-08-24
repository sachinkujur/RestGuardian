import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class GetBoardsTest extends BaseTest {

    @Test
    public void checkGetBoards() {
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("member", "sachinkujur")
                .get("/1/members/{member}/boards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }

    @Test
    public void checkGetBoard() {
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