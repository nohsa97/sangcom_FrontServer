package capstone.sangcom.controller;

import capstone.sangcom.service.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    private final LoginService loginService;

    public IndexController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index(){
        return "main";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie access = new Cookie("access", null);
        Cookie refresh = new Cookie("refresh", null);

        access.setMaxAge(0);
        refresh.setMaxAge(0);

        response.addCookie(access);
        response.addCookie(refresh);

        return "redirect:/login";
    }
}
