package capstone.sangcom.service;

import lombok.Data;

@Data
public class LoginResponse{
    private boolean success;
    private Token token;
    private String role;

    public LoginResponse(){}

    public static class Token{
        private String access_token;
        private String refresh_token;

        public Token(){}
    }
}
