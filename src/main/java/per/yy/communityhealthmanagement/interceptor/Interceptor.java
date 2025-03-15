package per.yy.communityhealthmanagement.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import per.yy.communityhealthmanagement.utils.JwtUtil;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.Map;

@Component
public class Interceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("token");
        //验证jwt
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //存储信息到threadLocal中
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除threadLocal中的用户信息
        ThreadLocalUtil.remove();
    }
}
