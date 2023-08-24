import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class BaseTest {

    @BeforeAll
    public static void setBaseUrl() {
        RestAssured.baseURI = "https://api.trello.com";
    }

    protected static RequestSpecification requestWithAuth() {
        return requestWithoutAuth()
                .queryParams(Map.of(
                        "key", "73f7a097a4567b388231c8ea06e7866d",
                        "token", "ATTA188495fbbe30a3764bb69bd7965c1be94e2af93f400f94aad463c50e5dff418705084EF1"
                ));
    }

    protected static RequestSpecification requestWithoutAuth() {
        return RestAssured.given();
    }
}
