package cn.nihility.mvndrill.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class BatchInsertDemo extends Thread {
	
	private int loopCount = 0;
	private String batch;
	
	public BatchInsertDemo(int cnt, String batch) {
		this.loopCount = cnt;
		this.batch = batch;
	}
	
	@Override
	public void run() {
		
		if ("batch".equalsIgnoreCase(batch)) {
			multDataInsert1();
		} else if ("partial".equalsIgnoreCase(batch)) {
			multDataInsert();
		}
	}
	
	public void multDataInsert2() {
		long start = System.currentTimeMillis();
		Random random = new Random(System.currentTimeMillis());
		Connection conn = null;
		PreparedStatement insertState = null;
		int count = 0;

		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			insertState = conn.prepareStatement(
					"merge into emp_copy ec using (select ? ename, ? job, ? mgr, sysdate hiredate, ? sal, ? comm, ? deptno from dual) e on (nvl(ec.ename, 'null') = nvl(e.ename, 'null')) when matched then update set ec.job = e.job, ec.mgr = e.mgr, ec.hiredate = e.hiredate, ec.sal = e.sal, ec.comm = e.comm, ec.deptno = e.deptno when not matched then insert (empno, ename, job, mgr, hiredate, sal, comm, deptno) values (emp_copy_sequence.nextval, e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm, e.deptno)");
			
			for (int i = 0; i < loopCount; i++) {
				insertState.setString(1, getRandName());
				insertState.setString(2, UUID.randomUUID().toString().substring(0, 4));
				insertState.setInt(3, random.nextInt(1000));
				insertState.setInt(4, random.nextInt(1000));
				insertState.setInt(5, random.nextInt(1000));
				insertState.setInt(6, random.nextInt(100));
//				insertState.executeUpdate();
				insertState.addBatch();
				count += 1;
				if (count == 50000) {
					insertState.executeBatch();
					count = 0;
					conn.commit();
				}
			}
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				insertState.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCUtils.release(conn, insertState, null);
		}
		System.out.println("merge insert duration time " + (System.currentTimeMillis() - start));
	}
	
	private String getRandName() {
		return RandomName.getRandomName() + UUID.randomUUID().toString().substring(0, 6);
	}

	public void multDataInsert1() {
		long start = System.currentTimeMillis();
		Random random = new Random(System.currentTimeMillis());
		Connection conn = null;
		PreparedStatement insertState = null;
		int count = 0;

		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			insertState = conn.prepareStatement(
					"insert into emp_copy (empno, ename, job, mgr, hiredate, sal, comm, deptno) values (emp_copy_sequence.nextval,?,?,?,sysdate,?,?,?)");
			
			for (int i = 0; i < loopCount; i++) {
				insertState.setString(1, getRandName());
				insertState.setString(2, UUID.randomUUID().toString().substring(0, 4));
				insertState.setInt(3, random.nextInt(1000));
				insertState.setInt(4, random.nextInt(1000));
				insertState.setInt(5, random.nextInt(1000));
				insertState.setInt(6, random.nextInt(100));
				insertState.addBatch();
				count += 1;
				if (count == 50000) {
					insertState.executeBatch();
					count = 0;
					conn.commit();
				}
			}
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				insertState.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCUtils.release(conn, insertState, null);
		}
		System.out.println("batch inserst duration time " + (System.currentTimeMillis() - start));
	}

	public void multDataInsert() {
		long start = System.currentTimeMillis();
		Random random = new Random(System.currentTimeMillis());
		Connection conn = null;
		PreparedStatement insertState = null;
		int count = 0;

		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			insertState = conn.prepareStatement(
					"insert into emp_copy (empno, ename, job, mgr, hiredate, sal, comm, deptno) values (emp_copy_sequence.nextval,?,?,?,sysdate,?,?,?)");
			
			for (int i = 0; i < loopCount; i++) {
				insertState.setString(1, getRandName());
				insertState.setString(2, UUID.randomUUID().toString().substring(0, 4));
				insertState.setInt(3, random.nextInt(1000));
				insertState.setInt(4, random.nextInt(1000));
				insertState.setInt(5, random.nextInt(1000));
				insertState.setInt(6, random.nextInt(99));
				insertState.executeUpdate();
				count += 1;
				if (count == 50000) {
					count = 0;
					conn.commit();
				}
			}
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCUtils.release(conn, insertState, null);
		}
		System.out.println("partial inserst duration time " + (System.currentTimeMillis() - start));
	}

}
