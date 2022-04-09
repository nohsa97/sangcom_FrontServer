package capstone.sangcom.controller.login;

import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.dto.login.RegisterDTO;
import capstone.sangcom.response.login.LoginResponse;
import capstone.sangcom.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private static final int ACCESS_TOKEN_MAX_AGE = 60 * 60 * 2;
    private static final int REFRESH_TOKEN_MAX_AGE = 60 * 60 * 24 * 14;

    private LoginService loginService;


    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginDTO loginInfo){
        return "login/index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginInfo,
                        HttpServletRequest request,
                        HttpServletResponse response){
        LoginResponse result = loginService.login(loginInfo);
        if(result.isSuccess()) {
            Cookie access = new Cookie("access", result.getToken().getAccess_token());
            Cookie refresh = new Cookie("refresh", result.getToken().getRefresh_token());

            access.setMaxAge(ACCESS_TOKEN_MAX_AGE);
            access.setPath("/");
            access.setHttpOnly(true);

            refresh.setMaxAge(REFRESH_TOKEN_MAX_AGE);
            refresh.setPath("/");
            refresh.setHttpOnly(true);

            response.addCookie(access);
            response.addCookie(refresh);

            return "redirect:/";
        }else
            return "login/index";
    }

    @GetMapping("/register")
    public String registerForm(){
        return "login/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDTO registerDTO){
        if(loginService.register(registerDTO))
            return "redirect:/";
        else
            return "login/register";
    }
}
