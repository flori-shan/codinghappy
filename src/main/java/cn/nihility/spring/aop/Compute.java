package cn.nihility.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author muscari
 * @date 2019-05-29 18:12
 */
@Component
public class Compute {

    public Integer add(int e, int v) { return e + v; }

    public Integer multiply(int e, int v) { return  e * v; }

    public Integer subtraction(int e, int v) { return e - v; }

    public Integer division(int e, int v) { try {return e / v;} catch (ArithmeticException ex) { throw ex; } }

}
