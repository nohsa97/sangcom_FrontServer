package capstone.sangcom.controller.user;

import capstone.sangcom.response.user.UserResponse;
import capstone.sangcom.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String UserInfoForm(Model model,
                               @CookieValue(value = "access", required = true) Cookie cookie) {

        UserResponse user = userService.getUser(cookie.getValue());
        model.addAttribute("user", user.getData());

        return "/user/myPage";
    }

    @GetMapping("/privacy")
    public String privacyForm() {
        return "user/privacy";
    }
}

