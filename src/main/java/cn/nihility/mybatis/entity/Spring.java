package cn.nihility.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author muscari
 * @date 2019-06-19 16:09
 */
@Getter
@Setter
@ToString
public class Spring {
    private Integer id;
    private String name;
    private Date createDate;
}
