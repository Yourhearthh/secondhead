package secondheadC.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * JDBC�����࣬��ȥ���ݿ����Ӻ��ͷ�����
 */
public class JdbcUtils {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	// ������������ȡ���ݿ�������Ϣ
	static {
		try {
			// ���������ļ�
			Properties properties = new Properties();
			InputStream in = JdbcUtils.class.getResourceAsStream("DB.properties");
			properties.load(in);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			// ��������
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * ��ȡ���ݿ�����
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updatpsrs(String sql, ArrayList<Object> al) throws SQLException {

		PreparedStatement ps = JdbcUtils.getConnection().prepareStatement(sql);
		
		for (int i = 0; i < al.size(); i++) {
			ps.setObject((i + 1), al.get(i));

		}

		ps.executeUpdate();

		ps.close();

	}

	public synchronized static PreparedStatement queryrs(String sql, ArrayList<Object> al) throws SQLException {
		PreparedStatement ps = JdbcUtils.getConnection().prepareStatement(sql);

		for (int i = 0; i < al.size(); i++) {
			ps.setObject(i + 1, al.get(i));

		}

		return ps;

	}


	/**
	 * �ͷ���Դ
	 * 
	 * @param connection
	 * @param preparedStatement
	 * @param resultSet
	 */
	public static void releaseDB(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void releaseDB(Connection connection, Statement stmt, ResultSet resultSet) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}