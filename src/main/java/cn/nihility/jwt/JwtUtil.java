package cn.nihility.jwt;

import cn.nihility.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jwt 工具类
 * @author muscari
 * @date 2019-07-08 23:23
 */
public class JwtUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public static String getToken(User user) {
        String token = JWT.create().withAudience(user.getId())
                .withAudience(user.getUserName())
                .sign(Algorithm.HMAC256(user.getPassword()));
        LOGGER.debug("user[{}], token [{}]", user, token);
        return token;
    }

}
