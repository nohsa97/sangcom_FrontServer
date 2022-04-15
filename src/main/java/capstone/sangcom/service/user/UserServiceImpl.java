package capstone.sangcom.service.user;

import capstone.sangcom.dto.user.UserInfoEditDTO;
import capstone.sangcom.response.common.SuccessResponse;
import capstone.sangcom.response.user.UserResponse;
import capstone.sangcom.template.ApiTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ApiTemplate apiTemplate;

    public UserServiceImpl(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }


    @Override
    public UserResponse getUser(String token) {
        UserResponse result = apiTemplate.getExecute("/auth/valid", token, UserResponse.class);

        return result;
    }

    @Override
    public boolean updateUser(String token, UserInfoEditDTO updateUser) {
        return false;
    }


    @Override
    public SuccessResponse leaveUser(String token) {
        return null;
    }
}
