package cn.nihility.controller;

import cn.nihility.annotation.UserLoginToken;
import cn.nihility.entity.Result;
import cn.nihility.entity.User;
import cn.nihility.service.TokenService;
import cn.nihility.service.UserService;
import cn.nihility.util.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author muscari
 * @date 2019-07-09 00:47
 */
@RestController
@RequestMapping("/usr")
public class UserLoginController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping(path = {"/login"})
    public Result login(@RequestBody User user) {
        User userBean = userService.findByUsername(user);
        if (null == userBean) {
            return ResultUtil.error(-1, "登录失败,用户不存在.");
        } else {
            if (user.getPassword().equals(userBean.getPassword())) {
                String token = tokenService.getToken(userBean);
                JSONObject resultObj = new JSONObject();
                resultObj.put("token", token);
                resultObj.put("user", userBean);
                return ResultUtil.success(resultObj);
            } else {
                return ResultUtil.error(-1, "登录失败,密码错误");
            }
        }
    }

    @UserLoginToken
    @GetMapping(path = {"/msg"})
    public Result getMessage() {
        return ResultUtil.success("通过验证");
    }

}
