package secondheadC.jdbc;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import secondheadC.Entity.VO.UserInfo;
import secondheadC.thread.ImgThread;

public class JdbcOperate {
	static PreparedStatement ps = null;
	private static ResultSet rs;

	// �����û��˺Ż�ø��û���Ϣ
	static public UserInfo getUserInfo(String userac) throws SQLException {
		String sql = "SELECT u.HeadPortraitData,u.HeadPortraitName,u.AccountNumber,u.Nickname,u.PassWords,u.Sex,u.Birthday,c.Name,u.PhoneNumber FROM userinfo AS u INNER JOIN china AS c ON u.ResidentCity=c.Id WHERE u.AccountNumber=?";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userac);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();
		rs.next();
		UserInfo user = new UserInfo();
		JLabel jl=new JLabel();
		ImageIcon usersicon;
		String url = "imagec/" + rs.getString(2);
		user.setHeadPortraitPath(url);
		if (!(new File(url).exists())) {
			new ImgThread(rs.getBytes(1), jl, url, 60, 80).start();
			usersicon = new ImageIcon("imagec/hs.jpg");
		} else {
			usersicon = new ImageIcon(url);
		}
		usersicon.setImage(usersicon.getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
		jl.setIcon(usersicon);
		user.setHeadPortrait(jl);
		user.setAccountNumber(rs.getString(3));
		user.setNickname(rs.getString(4));
		user.setPassWords(rs.getString(5));
		user.setSex(rs.getString(6));
		user.setBirthday(rs.getString(7));
		user.setCityName(rs.getString(8));
		user.setPhoneNumber(rs.getString(9));

		rs.close();
		ps.close();

		return user;
	}

	// ���ʡ��������м����������˵� ���ʡ���������м����н������˵� ���ӿհ�
	public static void getAllCitys(JComboBox<String> jcb, String city) throws SQLException {
		if (city.equals("")) {
			return;
		}

		String sql = "SELECT Name FROM china WHERE pid in(SELECT id FROM china WHERE Name=?);";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(city);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		jcb.removeAllItems();
		while (rs.next()) {
			jcb.addItem(rs.getString(1));
		}

		rs.close();
		ps.close();
	}

	// �������ʡ������������˵�
	public static void getAllCityC(JComboBox<String> jcb) throws SQLException {
		String sql = "SELECT Name FROM china WHERE Pid=0 AND id<>0";

		ps = JdbcUtils.queryrs(sql, new ArrayList<Object>());

		rs = ps.executeQuery();

		while (rs.next()) {
			jcb.addItem(rs.getString(1));
		}

		rs.close();
		ps.close();

	}

	// �����м����л��ʡ��
	public static String getCcity(String cityName) throws SQLException {
		String sql = "SELECT Name FROM china WHERE id in(SELECT pid FROM china WHERE Name=?) AND pid=0";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(cityName);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		rs.next();

		String str = rs.getString(1);

		rs.close();
		ps.close();

		return str;
	}

	// ͼƬתbyte����
	public static byte[] icontobyte(String URL, String s) throws FileNotFoundException, IOException {// ����ͼƬURL

		BufferedImage image = ImageIO.read(new FileInputStream(URL)); // ��ImageIO������ͼƬ�ļ�ת��������ͼƬ��Ϣ

		ByteArrayOutputStream bos = new ByteArrayOutputStream();// �ֽ������

		ImageIO.write(image, s, bos); // ������ͼƬ��Ϣд�뵽�ֽ��������

		byte[] b = bos.toByteArray();

		bos.close();

		return b;
	}

	// ���һ�������������Գ����ַ�������
	public static ArrayList<String> getAllCityStrlist(String city) throws SQLException {

		String sql = "SELECT Name FROM china WHERE pid in(SELECT id FROM china WHERE Name=?);";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(city);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		ArrayList<String> al1 = new ArrayList<String>();

		while (rs.next()) {
			al1.add(rs.getString(1));

		}

		rs.close();
		ps.close();

		return al1;
	}

	// ����ʡ��-���������id
	public static String getcityid(String str) throws SQLException {
		String c[] = str.split("-"), cityid;

		String sql = "SELECT id FROM china WHERE pid in(SELECT id FROM china WHERE Name=?) AND Name=?;";

		ArrayList<Object> al = new ArrayList<Object>();

		al.add(c[0]);
		al.add(c[1]);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		rs.next();

		cityid = rs.getString(1);

		rs.close();

		ps.close();

		return cityid;

	}

	// ��õ�ǰ������
	public static String getdate() {
		Calendar ca = Calendar.getInstance();

		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		String date = year + "-" + month + "-" + day;
		return date;
	}

	// ��������û��˺�
	public static void getAllUserAc(JComboBox<String> fbacdata) throws SQLException {
		String sql = "SELECT AccountNumber FROM userinfo;";

		ps = JdbcUtils.queryrs(sql, new ArrayList<Object>());

		rs = ps.executeQuery();

		while (rs.next()) {
			fbacdata.addItem(rs.getString(1));
		}
		rs.close();
		ps.close();

	}
	
	// �����û�����
		public static void updatepw(String accountNumber, String text) throws SQLException {
			String sql = "UPDATE userinfo SET passwords=? WHERE AccountNumber=?;";

			ArrayList<Object> al = new ArrayList<Object>();
			al.add(text);
			al.add(accountNumber);

			JdbcUtils.updatpsrs(sql, al);

		}

		// �����û��ǳ�
		public static void updatenn(String accountNumber, String text) throws SQLException {
			String sql = "UPDATE userinfo SET Nickname=? WHERE AccountNumber=?;";

			ArrayList<Object> al = new ArrayList<Object>();
			al.add(text);
			al.add(accountNumber);

			JdbcUtils.updatpsrs(sql, al);
		}

		// �����û��Ա�
		public static void updatesex(String accountNumber, String text) throws SQLException {
			String sql = "UPDATE userinfo SET Sex=? WHERE AccountNumber=?;";

			ArrayList<Object> al = new ArrayList<Object>();
			al.add(text);
			al.add(accountNumber);

			JdbcUtils.updatpsrs(sql, al);

		}

		// �����û�����
		public static void updatebd(String accountNumber, String text) throws SQLException {
			String sql = "UPDATE userinfo SET Birthday=? WHERE AccountNumber=?;";

			ArrayList<Object> al = new ArrayList<Object>();
			al.add(text);
			al.add(accountNumber);

			JdbcUtils.updatpsrs(sql, al);

		}

		// �޸��û�����
		public static void updatecity(String accountNumber, String c, String s) throws SQLException {
			String sql = "SELECT id FROM china WHERE pid in(SELECT id FROM china WHERE Name=?) AND Name=?;";
			ArrayList<Object> al1 = new ArrayList<Object>();
			al1.add(c);
			al1.add(s);
			ps = JdbcUtils.queryrs(sql, al1);

			rs = ps.executeQuery();

			rs.next();
			int i = rs.getInt(1);
			rs.close();
			ps.close();

			sql = "UPDATE userinfo SET ResidentCity=? WHERE AccountNumber=?;";

			ArrayList<Object> al = new ArrayList<Object>();
			al.add(i);
			al.add(accountNumber);

			JdbcUtils.updatpsrs(sql, al);

		}

		// �޸��û��ֻ�����
		public static void updatepn(String accountNumber, String text) throws SQLException {
			String sql = "UPDATE userinfo SET PhoneNumber=? WHERE AccountNumber=?;";

			ArrayList<Object> al = new ArrayList<Object>();
			al.add(text);
			al.add(accountNumber);

			JdbcUtils.updatpsrs(sql, al);

		}

		// �޸��û�ͷ��
		public static void updatehp(String accountNumber, String url)
				throws SQLException, FileNotFoundException, IOException {
			byte b[] = JdbcOperate.icontobyte(url, url.substring(url.length() - 3, url.length()));

			String sql = "UPDATE userinfo SET HeadPortraitData=?,HeadPortraitName=? WHERE AccountNumber=?;";

			ArrayList<Object> al = new ArrayList<Object>();
			al.add(b);
			al.add("TX"+new File(url).getName());
			al.add(accountNumber);

			JdbcUtils.updatpsrs(sql, al);

		}
		


		public static void Online(String ac) throws SQLException {
			String sql = "UPDATE userinfo SET OnlineStatus='����' WHERE AccountNumber=?";
			ArrayList<Object> al = new ArrayList<Object>();
			al.add(ac);
			JdbcUtils.updatpsrs(sql, al);
		}
		
		public static void OffOnline(String ac) throws SQLException {
			String sql = "UPDATE userinfo SET OnlineStatus='������' WHERE AccountNumber=?";
			ArrayList<Object> al = new ArrayList<Object>();
			al.add(ac);
			JdbcUtils.updatpsrs(sql, al);
		}
		
		public static String getnickName(String ac) throws SQLException{
			String sql = "SELECT Nickname FROM userinfo WHERE AccountNumber=?";
			ArrayList<Object> al = new ArrayList<Object>();
			al.add(ac);
			ps=JdbcUtils.queryrs(sql, al);
			rs=ps.executeQuery();
			rs.next();
			String nickName=rs.getString(1);
			rs.close();
			ps.close();
			
			return nickName;
		}

}
