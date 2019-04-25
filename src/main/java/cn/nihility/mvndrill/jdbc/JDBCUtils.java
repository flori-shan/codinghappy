package cn.nihility.mvndrill.jdbc;

import cn.nihility.mvndrill.utils.LogbackUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUtils.class);

	public static Connection getConnection() {
		/**
		 * String murl =
		 * "jdbc:mysql://localhost:3306/goods?useUncode=true&characterEncoding=UTF8";
		 * String driver = "com.mysql.jdbc.Driver";
		 */
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "oracle";
		String driverClass = "oracle.jdbc.driver.OracleDriver";
		Connection conn = null;

		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
            ex.printStackTrace();
        }

        return conn;
	}

	public static Connection getConnection(String dbType) {

		long start = System.currentTimeMillis();
		String url;
		String user;
		String password;
		String className;
		Connection conn = null;
		
		Properties properties = new Properties();

		try {
			properties.load(JDBCUtils.class.getResourceAsStream("/mybatis/db.properties"));
			if ("mysql".equals(dbType)) {
				url = properties.getProperty("mysql.url");
				user = properties.getProperty("mysql.user");
				password = properties.getProperty("mysql.password");
				className = properties.getProperty("mysql.driverClass");
				Class.forName(className);
				conn = DriverManager.getConnection(url, user, password);
			} else if ("oracle".equals(dbType)) {
				url = properties.getProperty("oracle.url");
				user = properties.getProperty("oracle.user");
				password = properties.getProperty("oracle.password");
				className = properties.getProperty("oracle.driverClass");
				Class.forName(className);
				conn = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception ex) {
			LogbackUtil.loggerError(JDBCUtils.class, "Get {} connection error, msg = {}.", dbType, ex);
		}
		LogbackUtil.loggerInfo(JDBCUtils.class, "Get {} connection duration time = {} ms", dbType,
				(System.currentTimeMillis() - start));
		return conn;

	}

	public static DruidDataSource getDruidDataSource(String dbName) throws IOException {

		String path =  JDBCUtils.class.getResource("/pool/druid.properties").getFile();
		LOGGER.debug("HikariCP get connection config find path = {}", path);

		String classLoaderPath = JDBCUtils.class.getClassLoader().getResource("pool/druid.properties").getFile();
		LOGGER.debug("HikariCP get connection config classloader find path = {}", classLoaderPath);

		FileInputStream fileInputStream = new FileInputStream(path);
		Properties properties = new Properties();
		properties.load(fileInputStream);

		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUsername(properties.getProperty(dbName+".user"));
		druidDataSource.setUrl(properties.getProperty(dbName+".url"));
		druidDataSource.setPassword(properties.getProperty(dbName+".password"));
		druidDataSource.setDriverClassName(properties.getProperty(dbName+".driverClass"));

		druidDataSource.setInitialSize(16);
		druidDataSource.setMaxActive(16);
		druidDataSource.setMinIdle(8);
		druidDataSource.setMaxWait(60000);
//		druidDataSource.setConnectionProperties(properties.getProperty("connectionProperties"));

		return druidDataSource;
	}

	public static HikariDataSource getHikariDataSource(String dbName) throws IOException {

		String path =  JDBCUtils.class.getResource("/pool/hikari.properties").getFile();
		LOGGER.debug("HikariCP get connection config find path = {}", path);

		String classLoaderPath = JDBCUtils.class.getClassLoader().getResource("pool/hikari.properties").getFile();
		LOGGER.debug("HikariCP get connection config classloader find path = {}", classLoaderPath);

        /* /D:/eclipse/aws/mvndrill/target/classes/cn/nihility/dbpool/
        path = JDBCUtils.class.getResource("").getPath();*/
        /*  /D:/eclipse/aws/mvndrill/target/classes/
        classLoaderPath = JDBCUtils.class.getClassLoader().getResource("").getFile();*/

		FileInputStream fileInputStream = new FileInputStream(path);
		Properties properties = new Properties();
		properties.load(fileInputStream);

		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setUsername(properties.getProperty(dbName+".user"));
		hikariDataSource.setJdbcUrl(properties.getProperty(dbName+".url"));
		hikariDataSource.setPassword(properties.getProperty(dbName+".password"));
		hikariDataSource.setDriverClassName(properties.getProperty(dbName+".driverClass"));

		hikariDataSource.setAutoCommit(false);
		hikariDataSource.setConnectionTimeout(30000);
		hikariDataSource.setIdleTimeout(60000);
		hikariDataSource.setMaxLifetime(1800000);
//		hikariDataSource.setMaximumPoolSize(16);

		return hikariDataSource;
	}

	public static void release(Connection conn, PreparedStatement stat, ResultSet result) {
		if (null != result) {
			try {
				result.close();
			} catch (SQLException e) {
				LogbackUtil.loggerError(JDBCUtils.class, "Release resources error, msg = {}", e);
			}
		}

		if (null != stat) {
			try {
				stat.close();
			} catch (SQLException e) {
				LogbackUtil.loggerError(JDBCUtils.class, "Release resources error, msg = {}", e);
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LogbackUtil.loggerError(JDBCUtils.class, "Release resources error, msg = {}", e);
			}
		}
	}

}
