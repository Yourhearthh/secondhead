package s.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import secondheadC.UI.UserJframe;

public class DbUtil {
	private String forName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://"+UserJframe.ip+":3306/secondhand?characterEncoding=utf-8";
	private String user = "root";
	private String passWord = "";
	private static DbUtil dbUtil;
	private Connection conn;

	private DbUtil() throws ClassNotFoundException {
		// 加载驱动
		Class.forName(forName);
	}

	public static DbUtil getInstance() throws ClassNotFoundException {
		if (DbUtil.dbUtil == null) {
			DbUtil.dbUtil = new DbUtil();
		}
		return dbUtil;
	}

	private Connection getConnection() throws SQLException {
		// 获取连接对象 coon
		if (this.conn == null) {
			this.conn = DriverManager.getConnection(this.url, this.user, this.passWord);
		}
		return this.conn;
	}

	private PreparedStatement getPreparedStatement(String sql) throws SQLException {
		// 获取执行对象 pstat
		return this.getConnection().prepareStatement(sql);
	}

	private PreparedStatement paramHandle(String sql, Object[] param) throws SQLException {
		return this.paramHandle(sql, Arrays.asList(param));
	}

	private PreparedStatement paramHandle(String sql, List<Object> param) throws SQLException {
		// 设置问号参数 pstat.set~
		PreparedStatement pstat = this.getPreparedStatement(sql);
		if (param != null) {
			for (int i = 0; i < param.size(); i++) {
				pstat.setObject(i + 1, param.get(i));
			}
		}
		return pstat;
	}

	public ResultSet doQuery(String sql, Object... param) throws SQLException {
		return this.paramHandle(sql, param).executeQuery();
	}

	public void doUpdate(String sql, Object... param) throws SQLException {
		this.paramHandle(sql, param).executeUpdate();
		this.paramHandle(sql, param).close();
	}

	public void close() {
		try {
			this.conn.close();
			this.conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			DbUtil dbUtil = DbUtil.getInstance();
			String str = "SELECT * FROM receivingaddressinfo WHERE pkid=11";
			ResultSet set = dbUtil.doQuery(str);
			while (set.next()) {
				System.out.println(set.getObject(1));
				System.out.println(set.getObject(2));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
