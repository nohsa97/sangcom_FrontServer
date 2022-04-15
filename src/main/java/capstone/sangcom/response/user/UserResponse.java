package capstone.sangcom.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {

    private boolean success;
    private User data;

    public UserResponse(){}
}
