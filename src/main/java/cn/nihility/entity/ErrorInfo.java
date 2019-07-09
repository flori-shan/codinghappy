package cn.nihility.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一采用标准的 Error Info 来记载错误信息
 * @author muscari
 * @date 2019-07-09 00:09
 */
@Getter
@Setter
public class ErrorInfo {

    private String time;//发生时间
    private String url;//访问Url
    private String error;//错误类型
    private String stackTrace;//错误的堆栈轨迹
    private Integer statusCode;//状态码
    private String reasonPhrase;//状态码

}
