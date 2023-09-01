package arguments.holders;

import java.util.Map;

public class AuthValidationArgumentsHolder {

    private final Map<String, String> authparams;

    private final String errorMessage;

    public AuthValidationArgumentsHolder(Map<String, String> authparams, String errorMessage) {
        this.authparams = authparams;
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getAuthparams() {
        return authparams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
