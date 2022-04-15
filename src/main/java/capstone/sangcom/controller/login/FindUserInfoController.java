package capstone.sangcom.controller.login;

import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.service.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/find")
public class FindUserInfoController {

    private LoginService loginService;

    public FindUserInfoController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/password")
    public String findPasswordForm(){
        return "login/findPW";
    }

    @PostMapping("/password")
    public String findPassword(@ModelAttribute FindPasswordDTO findPasswordDTO){
        if(loginService.findPassword(findPasswordDTO))
            return "redirect:/";
        else
            return "login/findPW";
    }
}
