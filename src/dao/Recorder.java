package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class Recorder {
	private final static String URL="jdbc:mysql://localhost:3306/chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private final static String USER_NAME="root";
	private final static String PASSWORD="391231";
	public static Connection linkedDataBase() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public static void closeDataBase(Connection connection) {
		try {
			if(connection!=null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean updateDataBase(Connection connection,String sql,Object[]parameters) {
		int rows=0;
		QueryRunner qr=new QueryRunner();
		try {
			rows=qr.update(connection, sql, parameters);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDataBase(connection);
		}
		return !(rows==0);
	}
}