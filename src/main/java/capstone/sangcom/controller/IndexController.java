package capstone.sangcom.controller;

import capstone.sangcom.dto.LoginDTO;
import capstone.sangcom.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginDTO loginInfo){
        return "login/index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginInfo){
        if(loginService.login(loginInfo).isSuccess())
            return "redirect:/";
        else
            return "login/index";
    }

}
