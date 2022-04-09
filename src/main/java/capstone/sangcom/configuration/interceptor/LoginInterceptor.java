package capstone.sangcom.configuration.interceptor;

import capstone.sangcom.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.StringTokenizer;

@Component
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

        StringTokenizer stk;
        String[] cookieValues = StringUtils.split(cookie, ";");

        stk = new StringTokenizer(cookieValues[0], "=");
        stk.nextToken();
        String access = stk.nextToken();

        stk = new StringTokenizer(cookieValues[1], "=");
        stk.nextToken();
        String refresh = stk.nextToken();


        boolean flag = loginService.checkLogin(access, refresh);
        System.out.println(flag);
        if(flag)
            return true;
        else{
            response.sendRedirect("/login");
            return false;
        }
    }
}
