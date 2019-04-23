package cn.nihility.designpattern.command;

public class SellStock implements Order {

	@Override
	public void execute() {
		abcStock.sell();
	}

	private Stock abcStock;

	public SellStock(Stock abcStock) {
		this.abcStock = abcStock;
	}

}
