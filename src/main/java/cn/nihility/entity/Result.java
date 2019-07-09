package cn.nihility.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一请求封装
 * @author muscari
 * @date 2019-07-08 23:53
 */
@Getter
@Setter
public class Result {

    private Integer code;
    private String msg;
    private Object data;

}
