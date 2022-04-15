package capstone.sangcom.service.user;

import capstone.sangcom.dto.user.UserInfoEditDTO;
import capstone.sangcom.response.common.SuccessResponse;
import capstone.sangcom.response.user.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserResponse getUser(String token);
    public boolean updateUser(String token, UserInfoEditDTO updateUser);
    public SuccessResponse leaveUser(String token);
}
