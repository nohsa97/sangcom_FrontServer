package capstone.sangcom.response.login;

import lombok.Data;

@Data
public class LoginResponse{
    private boolean success;
    private Token token;
    private String role;

    public LoginResponse(){}

    @Data
    public static class Token{
        private String access_token;
        private String refresh_token;

        public Token(){}
    }
}
