package cn.nihility.entity;

import lombok.*;

/**
 * 登录的实体
 * @author muscari
 * @date 2019-07-08 23:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String userName;
    private String password;

}
