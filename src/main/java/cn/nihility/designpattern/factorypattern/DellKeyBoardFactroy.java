package cn.nihility.designpattern.factorypattern;

public class DellKeyBoardFactroy {
	
	public KeyBoard createKeyBoard() {
		return new DellKeyBoard();
	}

}
