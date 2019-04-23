package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.command.Broker;
import cn.nihility.designpattern.command.BuyStock;
import cn.nihility.designpattern.command.SellStock;
import cn.nihility.designpattern.command.Stock;

public class CommandTest {

	@Test
	public void testCommand() {
		Stock abcStock = new Stock();

		BuyStock buyStockOrder = new BuyStock(abcStock);
		SellStock sellStockOrder = new SellStock(abcStock);

		Broker broker = new Broker();
		broker.takeOrder(buyStockOrder);
		broker.takeOrder(sellStockOrder);

		broker.placeOrders();
	}

}
