package cn.nihility.interceptor;

import cn.nihility.annotation.PassToken;
import cn.nihility.annotation.UserLoginToken;
import cn.nihility.entity.User;
import cn.nihility.exception.CustomException;
import cn.nihility.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器拦截Token
 * @author muscari
 * @date 2019-07-08 23:27
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");// 从 http 取出 token
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 检查是否有 PassToken 注解
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查是否需要用户权限注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken loginToken = method.getAnnotation(UserLoginToken.class);
            if (loginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new CustomException("无 Token, 请重新登录");
                }
                // 获取 userID
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException ex) {
                    throw new CustomException("401");
                }
                User user = userService.findById(userId);
                if (null == user) {
                    throw new CustomException("用户不存在,请重新登录");
                }
                // 验证 token
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    verifier.verify(token);
                } catch (JWTVerificationException ex) {
                    throw new CustomException("401");
                }
            }
            return true;
        }
        return true;
    }
}
