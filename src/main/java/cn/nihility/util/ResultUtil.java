package cn.nihility.util;

import cn.nihility.entity.Result;

/**
 * 返回格式工具
 * @author muscari
 * @date 2019-07-08 23:54
 */
public class ResultUtil {

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
