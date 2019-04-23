package cn.nihility.mvndrill.rabbitmq;

import org.junit.Test;

public class RabbitMqTest {
	
	@Test
	public void testSequeueThread() {
		SequeueSend.longSend("rabbitmq thread message");
	}

}
