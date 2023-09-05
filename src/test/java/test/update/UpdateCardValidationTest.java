package test.update;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

public class UpdateCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkUpdateCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .put(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkUpdateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParams("id", UrlParamValues.EXISTING_CARD_ID)
                .put(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void checkUpdateCardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParams("id", UrlParamValues.EXISTING_CARD_ID)
                .put(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}
