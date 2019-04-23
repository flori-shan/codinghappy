package cn.nihility.mvndrill.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;

import com.zaxxer.hikari.HikariDataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import cn.nihility.mvndrill.utils.LogbackUtil;

public class JDBCTest {

	@Test
	public void testConnection() {

		Connection connection = JDBCUtils.getConnection("mysql");
		System.out.println(connection);
		Assert.assertNotNull(connection);

	}

	@Test
	public void testGetHikari() {

		Connection connection = null;
		try {
			HikariDataSource dataSource = JDBCUtils.getHikariDataSource("mysql");
			Assert.assertNotNull(dataSource);
			connection = dataSource.getConnection();
			Assert.assertNotNull(connection);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetConnection() {

		Connection connection = null;
		connection = JDBCUtils.getConnection("mysql");
		Assert.assertNotNull(connection);
		LogbackUtil.loggerDebug(JDBCTest.class, "Get mysql connection = {}", connection);
		JDBCUtils.release(connection, null, null);

		connection = null;
		connection = JDBCUtils.getConnection("oracle");
		Assert.assertNotNull(connection);
		LogbackUtil.loggerDebug(JDBCTest.class, "Get oracle connection = {}", connection);
		JDBCUtils.release(connection, null, null);

	}

	@Test
	public void testStatement() {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String select = "SELECT id, name, age, gender, email from test WHERE id = ?";

		try {
			connection = JDBCUtils.getConnection("oracle");
			statement = connection.prepareStatement(select);
			statement.setInt(1, 1);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				LogbackUtil.loggerInfo(JDBCTest.class, "id={}, name={}, age={}, gender={}, email={}",
						resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"),
						resultSet.getString("gender"), resultSet.getString("email"));
			}

		} catch (SQLException e) {
			LogbackUtil.loggerError(JDBCTest.class, "Execute error = {}", e);
		} finally {
			JDBCUtils.release(connection, statement, resultSet);
		}

	}
	
	@Test
	public void testMatadata() {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String select = "SELECT id, name, age, gender, email from mybatis_test WHERE id = ?";

		try {
			connection = JDBCUtils.getConnection("oracle");
			DatabaseMetaData metaData = connection.getMetaData();
			
			LogbackUtil.loggerInfo(this.getClass(), "Connection get Catalog = {}", connection.getCatalog());
			LogbackUtil.loggerInfo(this.getClass(), "Connection get TransactionIsolation = {}", connection.getTransactionIsolation());
			LogbackUtil.loggerInfo(this.getClass(), "Connection get ClientInfo = {}", connection.getClientInfo());
			
			LogbackUtil.loggerInfo(this.getClass(), "Connection DriverName = {}", metaData.getDriverName());
			LogbackUtil.loggerInfo(this.getClass(), "Connection URL = {}", metaData.getURL());
			LogbackUtil.loggerInfo(this.getClass(), "Connection UserName = {}", metaData.getUserName());
			LogbackUtil.loggerInfo(this.getClass(), "Connection Schemas = {}", metaData.getSchemas());

			/*ResultSet tableRS = metaData.getTables(null, null, null, new String[] {"TABLE"});
			while (tableRS.next()) {
				LoggerUtil.loggerInfo(this.getClass(), "Table info: table_name={}, table_type={}, table_category={}, table_remarks={}", 
						tableRS.getString("TABLE_NAME"), tableRS.getString("TABLE_TYPE"), 
						tableRS.getString("TABLE_CAT"), tableRS.getString("REMARKS"));
			}
			JDBCUtils.release(null, null, tableRS);*/
			
			statement = connection.prepareStatement(select);
			statement.setInt(1, 101);
			LogbackUtil.loggerInfo(this.getClass(), "--------------------------------");
			LogbackUtil.loggerInfo(this.getClass(), "PreparedStatement FetchSize = {}", statement.getFetchSize());
			LogbackUtil.loggerInfo(this.getClass(), "statPreparedStatementement LargeMaxRows = {}", statement.getLargeMaxRows());
			LogbackUtil.loggerInfo(this.getClass(), "PreparedStatement MaxRows = {}", statement.getMaxRows());
			
			resultSet = statement.executeQuery();
			
			LogbackUtil.loggerInfo(this.getClass(), "--------------------------------");
			
			ResultSetMetaData resultSetMetadata = resultSet.getMetaData();
			LogbackUtil.loggerInfo(this.getClass(), "ResultSetMetaData ColumnCount = {}", resultSetMetadata.getColumnCount());
			int maxColumnCount = resultSetMetadata.getColumnCount();
			
			for (int i = 1; i <= maxColumnCount; i++) {
				LogbackUtil.loggerInfo(this.getClass(), "ColumnName={}, ColumnType={}, ColumnClassName={}, ColumnLabel={}", 
						resultSetMetadata.getColumnName(i),  resultSetMetadata.getColumnType(i),
						resultSetMetadata.getColumnClassName(i), resultSetMetadata.getColumnLabel(i));
			}
			LogbackUtil.loggerInfo(this.getClass(), "--------------------------------");
			
			JSONArray resultArray = new JSONArray();
			JSONArray resultArrayData = new JSONArray();
			JSONObject resultObj = null;
			JSONArray rowArrayData = null;
			int index;
			while (resultSet.next()) {
				resultObj = new JSONObject();
				rowArrayData = new JSONArray();
				for (index = 1; index <= maxColumnCount; index++) {
					resultObj.put(resultSetMetadata.getColumnName(index).toLowerCase(), resultSet.getObject(index));
					rowArrayData.put(resultSet.getObject(index));
				}
				resultArray.put(resultObj);
				resultArrayData.put(rowArrayData);
				
				/*LoggerUtil.loggerInfo(JDBCTest.class, "id={}, name={}, age={}, gender={}, email={}",
						resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"),
						resultSet.getString("gender"), resultSet.getString("email"));*/
			}
			LogbackUtil.loggerInfo(this.getClass(), "resultSet : {}", resultArray);
			LogbackUtil.loggerInfo(this.getClass(), "resultSet : {}", resultArrayData);

		} catch (SQLException e) {
			LogbackUtil.loggerError(JDBCTest.class, "Execute error = {}", e);
		} finally {
			JDBCUtils.release(connection, statement, resultSet);
		}
		
	}

	@Test
	public void testBatchInsert() {
		
		long start = System.currentTimeMillis();
		long partial = System.currentTimeMillis();
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Savepoint savepoint = null;
		int saveCount = 10000;

		String insert = "insert into mybatis_test(id, name, age, gender, email, test_address) values (MYBATISTEST_SEQUENCE.Nextval,?,?,?,?,?)";
//		String insert = "insert into mybatis_test(name, age, gender, email, test_address) values (?,?,?,?,?)";
		try {
			connection = JDBCUtils.getConnection("oracle");
			boolean autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);
			savepoint = connection.setSavepoint();
			statement = connection.prepareStatement(insert);
			
			Random random = new Random(System.currentTimeMillis());
			String uuid;
			int index;
			for (index = 1; index <= saveCount; index++) {
				uuid = UUID.randomUUID().toString().substring(0, 8);
				statement.setString(1, uuid);
				statement.setInt(2, (random.nextInt(10) + 10));
				statement.setString(3, (index % 3 == 0 ? "F" : "M"));
				statement.setString(4, uuid+"@test.com");
				statement.setString(5, uuid+"地址");
				statement.addBatch();
				
				if (index % 1000 == 0 || index == saveCount) {
					statement.executeBatch();
					connection.commit();
					statement.clearBatch();
					LogbackUtil.loggerInfo(this.getClass(), "Batch execute duration time = {}", (System.currentTimeMillis() -partial));
					partial = System.currentTimeMillis();
				}
			}
			
			connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			LogbackUtil.loggerError(JDBCTest.class, "Execute error = {}", e);
			try {
				if (null != savepoint) {
					connection.rollback(savepoint);
				} else {
					connection.rollback();
				}
			} catch (SQLException e1) {
				LogbackUtil.loggerError(JDBCTest.class, "Execute rollback error = {}", e1);
			}
		} finally {
			JDBCUtils.release(connection, statement, resultSet);
		}
		LogbackUtil.loggerInfo(this.getClass(), "Execute all statement duration = {}", (System.currentTimeMillis() - start));
	}
	
	@Test
	public void testGetInsertGenerateKey() {
		
		long start = System.currentTimeMillis();
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

//		String insert = "insert into test(id, name, age, gender, email) values (TEST_SEQUENCE.Nextval,?,?,?,?)";
		String insert = "insert into test(name, age, gender, email) values (?,?,?,?)";
		try {
			connection = JDBCUtils.getConnection("mysql");
			connection.setAutoCommit(false);
			
			/*useGeneratedKeys 要求数据库本身具备主键自动增长的功能  Statement.RETURN_GENERATED_KEYS */
			/* oracle 写法:
			 * statement = connection.prepareStatement(insert, new String[] {"id"});
			 * resultSet = statement.getGeneratedKeys();
			 * resultSet.getInt(1)
			 * 
			 * mysql :
			 * statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			 * resultSet = statement.getGeneratedKeys();
			 * resultSet.getInt(1)
			 * */
			statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			
			Random random = new Random(System.currentTimeMillis());
			String uuid = UUID.randomUUID().toString().substring(0, 10);
			statement.setString(1, uuid);
			statement.setInt(2, (random.nextInt(10) + 10));
			statement.setString(3, "F");
			statement.setString(4, uuid+"@test.com");
			statement.executeUpdate();
			LogbackUtil.loggerInfo(this.getClass(), "Execute duration time = {}", (System.currentTimeMillis() -start));
		
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
                LogbackUtil.loggerInfo(this.getClass(), "数据主键 = {}", resultSet.getInt(1));   
            }
			
			connection.commit();
		} catch (SQLException e) {
			LogbackUtil.loggerError(JDBCTest.class, "Execute error = {}", e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				LogbackUtil.loggerError(JDBCTest.class, "Execute rollback error = {}", e1);
			}
		} finally {
			JDBCUtils.release(connection, statement, resultSet);
		}
		LogbackUtil.loggerInfo(this.getClass(), "Execute all statement duration = {}", (System.currentTimeMillis() - start));
	
	}
	
}
