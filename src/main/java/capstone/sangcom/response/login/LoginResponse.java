package capstone.sangcom.response.login;

import lombok.Data;

@Data
public class LoginResponse{
    private boolean success;
    private TokenResponse token;
    private String role;

    public LoginResponse(){}
}
