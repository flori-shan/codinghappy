package cn.nihility.designpattern.factorypattern;

public class DellPCFactory extends AbstractPCFactory {

	@Override
	public Mouse createMouse() {
		DellMouseFactory dellMouseFactory = new DellMouseFactory();
		return dellMouseFactory.createMouse();
	}

	@Override
	public KeyBoard createKeyBoard() {
		DellKeyBoardFactroy dellKeyBoardFactroy = new DellKeyBoardFactroy();
		return dellKeyBoardFactroy.createKeyBoard();
	}

}
