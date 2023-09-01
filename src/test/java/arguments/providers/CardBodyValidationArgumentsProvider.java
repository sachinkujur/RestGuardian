package arguments.providers;

import arguments.holders.CardBodyValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardBodyValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                new CardBodyValidationArgumentsHolder(
                        Map.of(
                                "name", "12345",
                                "idList", UrlParamValues.EXISTING_LIST_ID
                        ),
                        "invalid value for name"
                ),
                new CardBodyValidationArgumentsHolder(
                        Map.of(
                                "name", "new card"
                        ),
                        "invalid value for idList"
                ),
                new CardBodyValidationArgumentsHolder(
                        Map.of(
                                "name", "New card",
                                "idLsit", "invalid"
                        ),
                        "invalid value for idList"
                )
        ).map(Arguments::of);
    }
}
