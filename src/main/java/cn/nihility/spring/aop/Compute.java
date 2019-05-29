package cn.nihility.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author muscari
 * @date 2019-05-29 18:12
 */
public class Compute {

    public int add(int e, int v) { return e + v; }

    public int multiply(int e, int v) { return e * v; }

    public int subtraction(int e, int v) { return e - v; }

    public int division(int e, int v) { return  e / v; }

}
