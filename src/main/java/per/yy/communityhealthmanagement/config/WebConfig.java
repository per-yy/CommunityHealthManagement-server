package per.yy.communityhealthmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import per.yy.communityhealthmanagement.interceptor.Interceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Interceptor Interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(Interceptor).addPathPatterns("/userInfo/*","/resident/**");
    }
}
