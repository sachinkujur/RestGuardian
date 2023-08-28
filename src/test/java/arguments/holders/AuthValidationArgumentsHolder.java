package arguments.holders;

import java.util.Map;

public class AuthValidationArgumentsHolder {

    private final Map<String, String> authparams;

    public AuthValidationArgumentsHolder(Map<String, String> authparams) {
        this.authparams = authparams;
    }

    public Map<String, String> getAuthparams() {
        return authparams;
    }
}
