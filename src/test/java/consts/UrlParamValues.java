package consts;

import java.util.Map;

public class UrlParamValues {

    public static final String VALID_KEY = "73f7a097a4567b388231c8ea06e7866d";
    public static final String VALID_TOKEN = "ATTA188495fbbe30a3764bb69bd7965c1be94e2af93f400f94aad463c50e5dff418705084EF1";
    public static final Map<String, String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
    );
    public static final Map<String, String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", "73f7a097a4567b388231c8ea06e7866d",
            "token", "018459018349936a264bdcb07b41fa4b538b501ef504fee5d07ea9f86b953e9"
    );
    public static final String EXISTING_BOARD_ID = "64799a8e4853e983424c00d3";
    public static final String USER_NAME = "sachinkujur";
}
