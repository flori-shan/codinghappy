package cn.nihility.designpattern.factorypattern;

/**
 * 抽象工厂类
 * @author yzx
 *
 */
public abstract class AbstractPCFactory {
	public abstract Mouse createMouse();
	public abstract KeyBoard createKeyBoard();
}
