package capstone.sangcom.configuration.interceptor;

import capstone.sangcom.controller.login.LoginController;
import capstone.sangcom.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.StringTokenizer;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private LoginService loginService;

    public LoginInterceptor(LoginService loginService){
        this.loginService = loginService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookie = request.getHeader("cookie");
        if(cookie == null) {
            response.sendRedirect("/login");
            return false;
        }

        String access,refresh;
        StringTokenizer stk;
        String[] cookieValues = StringUtils.split(cookie, ";");
        if(cookieValues != null) {

            stk = new StringTokenizer(cookieValues[0], "=");
            stk.nextToken();
            access = stk.nextToken();

            stk = new StringTokenizer(cookieValues[1], "=");
            stk.nextToken();
            refresh = stk.nextToken();

            boolean flag = loginService.checkLogin(access, refresh);
            System.out.println(flag);
            if(flag)
                return true;
            else{
                response.sendRedirect("/login");
                return false;
            }
        }else{
            stk = new StringTokenizer(cookie, "=");
            stk.nextToken();
            refresh = stk.nextToken();

            access = loginService.getAccessToken(refresh);

            Cookie accessCookie = new Cookie("access", access);

            accessCookie.setPath("/");
            accessCookie.setMaxAge(LoginController.ACCESS_TOKEN_MAX_AGE);
            accessCookie.setHttpOnly(true);

            response.addCookie(accessCookie);
            response.sendRedirect("/");

            return false;
        }
    }
}
