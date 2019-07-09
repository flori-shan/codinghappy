package cn.nihility.service;

import cn.nihility.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author muscari
 * @date 2019-07-08 23:37
 */
@Service
public class UserService {

    public User findByUsername(User user) {
        return new User("1", "Luck", "Luck");
    }

    public User findById(String userId) {
        return new User(userId, "Luck", "Luck");
    }

}
