package cn.nihility.designpattern.factorypattern;

public class LenovoKeyBoardFactory {

	public KeyBoard createKeyBoard() {
		return new LenovoKeyBoard();
	}

}
