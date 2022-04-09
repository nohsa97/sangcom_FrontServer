package capstone.sangcom.configuration;

import capstone.sangcom.configuration.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private LoginInterceptor loginInterceptor;

    public InterceptorConfig(LoginInterceptor loginInterceptor){
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "find/pw", "find/id")
                .excludePathPatterns("/js/**", "/css/**", "/image/**", "/icon/**")
                .order(1);
    }
}
