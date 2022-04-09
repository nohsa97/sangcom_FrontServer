package capstone.sangcom.service;

import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.dto.login.RegisterDTO;
import capstone.sangcom.response.login.LoginResponse;

public interface LoginService {
    public LoginResponse login(LoginDTO loginInfo);
    public boolean checkLogin(String access, String refresh);
    public boolean findPassword(FindPasswordDTO findPasswordDTO);
    public boolean register(RegisterDTO registerDTO);
}
