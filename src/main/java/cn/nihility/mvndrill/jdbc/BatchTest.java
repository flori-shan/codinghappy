package cn.nihility.mvndrill.jdbc;

public class BatchTest {

	public static void main(String[] args) {
		/*BatchInsertDemo bd = new BatchInsertDemo(100000, "partial");
		bd.start();*/
		
		
			BatchInsertDemo bd1 = new BatchInsertDemo(1, "batch");
			bd1.multDataInsert2();
			/*bd1.multDataInsert1();
			bd1.multDataInsert();*/
			
			
		
		
	}
	
}
