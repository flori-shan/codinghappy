package cn.nihility.service;

import cn.nihility.entity.User;
import cn.nihility.jwt.JwtUtil;
import org.springframework.stereotype.Service;

/**
 * @author muscari
 * @date 2019-07-08 23:39
 */
@Service
public class TokenService {

    public String getToken(User user) {
        return JwtUtil.getToken(user);
    }

}
