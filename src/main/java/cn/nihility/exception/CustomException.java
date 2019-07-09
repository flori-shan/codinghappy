package cn.nihility.exception;

/**
 * @author muscari
 * @date 2019-07-09 00:40
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException() {
    }

}
