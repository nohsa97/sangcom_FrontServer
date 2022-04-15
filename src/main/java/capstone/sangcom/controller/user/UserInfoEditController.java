package capstone.sangcom.controller.user;

import capstone.sangcom.dto.user.UserInfoEditDTO;
import capstone.sangcom.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/user/edit")
public class UserInfoEditController {

    private final UserService userService;

    public UserInfoEditController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/profile")
//    public String profileEditForm() {
//        return "user/changProfile";
//    }

    @GetMapping("/password")
    public String editPasswordForm() {
        return "user/changePassword";
    }

    @GetMapping("/info")
    public String editUserInfoForm() {
        return "user/changeUserInfo";
    }

    @PostMapping("/info")
    public String editUserInfo(@ModelAttribute UserInfoEditDTO userInfo) {

//        userService.editUser(userInfo);
        return "user/changeUserInfo";
    }



    @GetMapping("/leave")
    public String unregister(@CookieValue(value = "access") Cookie cookie){
//        userService.leave(cookie.getValue());
        return "redirect:/login";
    }
}
