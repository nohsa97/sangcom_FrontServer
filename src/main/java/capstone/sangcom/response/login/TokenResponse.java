package capstone.sangcom.response.login;

import lombok.Data;

@Data
public class TokenResponse {
    private String access_token;
    private String refresh_token;

    public TokenResponse(){}
}