package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.factorypattern.AbstractPCFactory;
import cn.nihility.designpattern.factorypattern.DellPCFactory;
import cn.nihility.designpattern.factorypattern.LenovoPCFactory;
import cn.nihility.designpattern.factorypattern.Mouse;
import cn.nihility.designpattern.factorypattern.MouseEnum;
import cn.nihility.designpattern.factorypattern.MouseFactory;
import cn.nihility.designpattern.factorypattern.MouseSimpleFactory;

/**
 * 工厂模式测试
 * @author yzx
 *
 */
public class FactoryTest {
	
	/**
	 * 简单工厂方法，一个类仅生产一类别的产品
	 */
	@Test
	public void testSimpleFactory() {
		Mouse m1 = MouseSimpleFactory.createMouse(MouseEnum.DELL);
		m1.whoami();
		
		Mouse m2 = MouseSimpleFactory.createMouse(MouseEnum.LENOVO);
		m2.whoami();
		
	}
	
	/**
	 * 工厂模式类，一个工厂类，调用具体的生产工厂生产产品
	 */
	@Test
	public void testFactory() {
		Mouse lenovoMouse = MouseFactory.createMouse(MouseEnum.LENOVO);
		lenovoMouse.whoami();
		
		Mouse dellMosue = MouseFactory.createMouse(MouseEnum.DELL);
		dellMosue.whoami();
	}
	
	/**
	 * 抽象类工厂模式
	 */
	@Test
	public void testAbstractFactory() {
		AbstractPCFactory abstractPCFactory =  new DellPCFactory();
		abstractPCFactory.createKeyBoard().whoami();
		abstractPCFactory.createMouse().whoami();
		
		AbstractPCFactory lenovoPCFactory =  new LenovoPCFactory();
		lenovoPCFactory.createKeyBoard().whoami();
		lenovoPCFactory.createMouse().whoami();
	}

}
