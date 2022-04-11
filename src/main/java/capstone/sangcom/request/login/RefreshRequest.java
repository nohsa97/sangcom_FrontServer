package capstone.sangcom.request.login;

import lombok.Data;

@Data
public class RefreshRequest {
    private final String token;
}
