package capstone.sangcom.service;

import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.dto.login.RegisterDTO;
import capstone.sangcom.request.login.RefreshRequest;
import capstone.sangcom.response.login.LoginResponse;
import capstone.sangcom.response.login.SuccessResponse;
import capstone.sangcom.response.login.TokenResponse;
import capstone.sangcom.template.ApiTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class LoginServiceImpl implements LoginService{

    private final RestTemplate restTemplate;
    private final ApiTemplate apiTemplate;

    public LoginServiceImpl(RestTemplate restTemplate, ApiTemplate apiTemplate){
        this.restTemplate = restTemplate;
        this.apiTemplate = apiTemplate;
    }

    @Override
    public LoginResponse login(LoginDTO loginInfo){
        LoginResponse result = apiTemplate.postExecute("/user/login", loginInfo, "null", LoginResponse.class);
        System.out.println(result.isSuccess());
        return result;
    }

    @Override
    public boolean checkLogin(String access, String refresh){
        SuccessResponse result = apiTemplate.getExecute("/user/login/check", access, SuccessResponse.class);

        return result.isSuccess();
    }

    @Override
    public String getAccessToken(String refresh){
        TokenResponse result = apiTemplate.postExecute("/auth/refresh", new RefreshRequest(refresh), "null", TokenResponse.class);

        return result.getAccess_token();
    }

    @Override
    public boolean findPassword(FindPasswordDTO findPasswordDTO) {

        SuccessResponse result = apiTemplate.postExecute("/user/password/find", findPasswordDTO, "null", SuccessResponse.class);

        return result.isSuccess();
    }

    @Override
    public boolean register(RegisterDTO registerDTO) {
        SuccessResponse result = apiTemplate.postExecute("/user/register", registerDTO, "null", SuccessResponse.class);

        return result.isSuccess();
    }
}
