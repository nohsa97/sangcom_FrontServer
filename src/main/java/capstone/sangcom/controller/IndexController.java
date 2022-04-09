package capstone.sangcom.controller;

import capstone.sangcom.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private LoginService loginService;

    public IndexController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index(){
        return "main";
    }
}
