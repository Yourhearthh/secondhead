package b.Jdbc;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import b.lianxi.UserInfo;

public class JdbcOperate {
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement ps = null;
	static String sql = null;

	static {
		conn = JdbcUtils.getConnection();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//把用户输入的注册信息输入到用户表里
	public static void insertuserdata(UserInfo u) throws SQLException, FileNotFoundException, IOException {
		String c[] = u.getCityName().split("-"),city,url=u.getHeadPortraitPath(),date;

		sql = "SELECT id FROM china WHERE pid in(SELECT id FROM china WHERE Name='" + c[0] + "') AND Name='" + c[1]
				+ "';";

		System.out.println("sql:"+sql);
		ResultSet rs = stmt.executeQuery(sql);

		rs.next();
		
		city=rs.getString(1);
		
		rs.close();
		
		Calendar ca = Calendar.getInstance();
		 
        int year = ca.get(Calendar.YEAR);  
        int month = ca.get(Calendar.MONTH) + 1;  
        int day = ca.get(Calendar.DATE); 
        date=year+"-"+month+"-"+day;
        
		
		byte b[] = JdbcOperate.icontobyte(url, url.substring(url.length() - 3, url.length()));

		sql="INSERT INTO userinfo(AccountNumber,PassWords,Nickname,Sex,Birthday,ResidentCity,PhoneNumber,HeadPortraitData,HeadPortraitName,RegisterTime,OnlineStatus,Balance) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		
		ps = conn.prepareStatement(sql);

		ps.setString(1,u.getAccountNumber());

		ps.setString(2,u.getPassWords());

		ps.setString(3, u.getNickname());
		
		ps.setString(4,u.getSex());
		
		ps.setString(5, u.getBirthday());
		
		ps.setInt(6, Integer.valueOf(city));
		
		ps.setString(7, u.getPhoneNumber());
		
		ps.setBytes(8, b);
		
		ps.setString(9,"TX"+u.getAccountNumber()+(new File(url).getName()));
		
		ps.setString(10,date);		
		ps.setString(11,"不在线");
		ps.setDouble(12, 0);

		ps.executeUpdate();
		
		ps.close();

	}
	//图片转成二进制
	private static byte[] icontobyte(String url, String substring) throws FileNotFoundException, IOException {
		//String URL="F://图片";//本地图片URL
		BufferedImage image = ImageIO.read(new FileInputStream(url)); //用ImageIO将本地图片文件转换成虚拟图片信息
		ByteArrayOutputStream bos = new ByteArrayOutputStream();//字节输出流
		ImageIO.write(image, "png", bos); //将虚拟图片信息写入到字节输出流中
		byte[] b = bos.toByteArray();
		return b;
	}

	//登录
		public static int UserInfo(UserInfo u) throws SQLException{
			UserInfo resultUser=null;
			//String sql="select * from userinfo where AccountNumber=? and PassWords=?";
			String sql="select userinfo.AccountNumber,userinfo.PassWords from userinfo where AccountNumber=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getAccountNumber());
			//pstmt.setString(2, u.getPassWords());
			ResultSet rs = pstmt.executeQuery();
			String user=null;
			String p = null;
			if(rs.next()){
				user=rs.getString("AccountNumber");
				p = rs.getString("PassWords");
			}
			if(!u.getAccountNumber().equals(user)){
				return 0;
			}
			
			if((u.getPassWords().equals(p))){
				return 1;
			}else{
	            return 2;
			}
			
		}


	//获得对应省份和直辖市的下属城市和区
	public static void getAllCityS(JComboBox<String> jcb, String city) throws SQLException {
		if(city.equals("")){
			return;
		}
		sql = "SELECT Name FROM china WHERE pid in(SELECT id FROM china WHERE Name='" + city + "');";
		ResultSet rs = stmt.executeQuery(sql);
		jcb.removeAllItems();
		while (rs.next()) {
			jcb.addItem(rs.getString(1));
		}
		rs.close();
	}
	
    //省份直辖市
	public static void getAllCityC(JComboBox<String> jcb) throws SQLException {
		sql = "SELECT Name FROM china WHERE Pid=0 AND id<>0";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			jcb.addItem(rs.getString(1));
		}
		rs.close();
	}
	
	//判断账号是否存在
		public static boolean getAccountNumbe(String number) throws SQLException{
			
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT AccountNumber");
			sb.append(" FROM userinfo");
			sb.append(" WHERE AccountNumber=?");
			sb.append(";");
			System.out.println(number);
			ps=JdbcUtils.getConnection().prepareStatement(sb.toString());
			ps.setString(1,number);
			
			ResultSet rs=ps.executeQuery();
			return rs.next();
			
		}

}
