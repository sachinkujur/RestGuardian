package arguments;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardIdValidationArgumentsProvide implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                new CardIdValidationArgumentsHolder(Map.of("id", "64799a8e4853e983424c00d3"),
                        "The requested resource was not found.",
                        404
                ),
                new CardIdValidationArgumentsHolder(Map.of("id", "invalid id"),
                        "invalid id",
                        400
                )
        ).map(Arguments::of);
    }
}
