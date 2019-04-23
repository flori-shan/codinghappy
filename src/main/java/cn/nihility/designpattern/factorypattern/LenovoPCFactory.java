package cn.nihility.designpattern.factorypattern;

public class LenovoPCFactory extends AbstractPCFactory {

	@Override
	public Mouse createMouse() {
		LenovoMouseFactory lenovoMouseFactory = new LenovoMouseFactory();
		return lenovoMouseFactory.createMouse();
	}

	@Override
	public KeyBoard createKeyBoard() {
		LenovoKeyBoardFactory lenovoKeyBoardFactory = new LenovoKeyBoardFactory();
		return lenovoKeyBoardFactory.createKeyBoard();
	}

}
