package secondheadC.jdbc;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.UI.UserJframe;
import secondheadC.UI.panel.AllGoodsPanel;
import secondheadC.assemby.ImgLabel;
import secondheadC.thread.ImgThread;

public class AllGoodsJdbc {
	static PreparedStatement ps = null;
	private static ResultSet rs;

	// 锟斤拷锟斤拷锟斤拷锟斤拷锟狡凤拷锟斤拷菁锟斤拷锟�
	public static void getAllGoodsData() throws SQLException {

		ArrayList<GoodsInfo> goodsi = AllGoodsPanel.gi;

		goodsi.removeAll(goodsi);
		ArrayList<Object> al = new ArrayList<Object>();
		String sql;
		if (UserJframe.userac != null) {
			sql = "SELECT g.pkid,Title,SellReason,Price,Initialprice,Freight,ClassificationName,NAME,AccountNumber FROM	goodsinfo AS g INNER JOIN classificationofgoodsinfo AS c ON g.ClassificationId = c.pkid INNER JOIN china AS ch ON g.Location = ch.Id INNER JOIN userinfo AS u ON g.fk_user_id = u.pkid WHERE g.SalesStatus='未售' AND fk_user_id<>?";
			int pkid = AllGoodsJdbc.getuserpkid(UserJframe.userac);
			al.add(pkid);
		} else {
			sql = "SELECT g.pkid,Title,SellReason,Price,Initialprice,Freight,ClassificationName,NAME,AccountNumber FROM	goodsinfo AS g INNER JOIN classificationofgoodsinfo AS c ON g.ClassificationId = c.pkid INNER JOIN china AS ch ON g.Location = ch.Id INNER JOIN userinfo AS u ON g.fk_user_id = u.pkid WHERE g.SalesStatus='未售'";
		}
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		GoodsInfo gi;

		while (rs.next()) {
			gi = new GoodsInfo();
			gi.setPkid(rs.getInt(1));
			gi.setTitle(rs.getString(2));
			gi.setSellReason(rs.getString(3));
			gi.setPrice(rs.getDouble(4));
			gi.setInitialPrice(rs.getDouble(5));
			gi.setFreight(rs.getDouble(6));
			gi.setClassificationName(rs.getString(7));
			gi.setLocation(rs.getString(8));
			gi.setUserac(rs.getString(9));
			gi.setGoodsPictureName("imagec/hw.jpg");
			
			
			goodsi.add(gi);
		}

		rs.close();
		ps.close();

		String str;
		for (int i = 0; i < goodsi.size(); i++) {
			gi = goodsi.get(i);
			str = gi.getLocation();
			str = JdbcOperate.getCcity(str) + "-" + str;
			gi.setLocation(str);

		}

		AllGoodsJdbc.getGoodsIcon(goodsi, 200, 200);

	}

	// 锟斤拷锟斤拷锟斤拷锟斤拷锟狡吠计�
	public static void getGoodsIcon(ArrayList<GoodsInfo> goodsi, int j, int k) throws SQLException {
		GoodsInfo gi;
		ArrayList<Object> al;
		String url,sql = "SELECT GoodsPictureData,GoodsPictureName FROM goodspictureinfo AS gp INNER JOIN goodsinfo AS g ON g.pkid=gp.GoodsID WHERE g.pkid=? LIMIT 1";
		JLabel jl;
		ImageIcon usersicon;
		for (int i = 0; i < goodsi.size(); i++) {
			gi = goodsi.get(i);
			jl = new JLabel();
			al = new ArrayList<Object>();
			al.add(gi.getPkid());
			ps = JdbcUtils.queryrs(sql, al);
			rs = ps.executeQuery();
			while(rs.next()){
			url = "imagec/" + rs.getString("GoodsPictureName");
			gi.setGoodsPictureName(url);
			if (!(new File(url).exists())) {
				new ImgThread(rs.getBytes("GoodsPictureData"), jl, url, j, k).start();
				usersicon = new ImageIcon("imagec/hw.jpg");
			} else {
				usersicon = new ImageIcon(url);
			}
			usersicon.setImage(usersicon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			jl.setIcon(usersicon);
			gi.setGoodsPicture(jl);
			}
			rs.close();
			ps.close();

		}

	}

	// 锟斤拷锟斤拷一锟斤拷锟斤拷品
	public static void insertgoodsdata(GoodsInfo g, ArrayList<ImgLabel> jllist)
			throws SQLException, FileNotFoundException, IOException {
		String cityid;

		cityid = JdbcOperate.getcityid(g.getLocation());

		int cfi = 1;

		switch (g.getClassificationName()) {
		case "衣":
			cfi = 1;
			break;
		case "食":
			cfi = 2;
			break;
		case "住":
			cfi = 3;
			break;
		case "行":
			cfi = 4;
			break;
		case "其它":
			cfi = 5;
			break;

		}

		String sql = "INSERT INTO goodsinfo (Title, SellReason, Price, Initialprice, Freight, ClassificationId, CreateTime, ModifyTime, Location,fk_user_id,SalesStatus)VALUES(?,?,?,?,?,?,?,?,?,?,?);";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(g.getTitle());
		al.add(g.getSellReason());
		al.add(g.getPrice());
		al.add(g.getInitialPrice());
		al.add(g.getFreight());
		al.add(cfi);
		al.add(JdbcOperate.getdate());
		al.add(JdbcOperate.getdate());
		al.add(cityid);
		al.add(AllGoodsJdbc.getuserpkid(g.getUserac()));
		al.add("未售");

		JdbcUtils.updatpsrs(sql, al);

		int goodspkid = AllGoodsJdbc.getgoodspkid();

		AllGoodsJdbc.insertgoodspicture(jllist, goodspkid);

	}

	// 锟斤拷锟斤拷锟斤拷品图片
	public static void insertgoodspicture(ArrayList<ImgLabel> jllist, int goodspkid)
			throws FileNotFoundException, IOException, SQLException {
		for (int i = 0; i < jllist.size(); i++) {
			String url = jllist.get(i).url;

			String sql = "INSERT INTO goodspictureinfo (GoodsPictureData,GoodsPictureName,GoodsId) VALUES(?,?,?);";

			byte b[] = JdbcOperate.icontobyte(url, url.substring(url.length() - 3, url.length()));

			ArrayList<Object> a = new ArrayList<Object>();
			a.add(b);
			String n = new File(url).getName();
			a.add("goodsimg"+goodspkid + n);
			a.add(goodspkid);

			JdbcUtils.updatpsrs(sql, a);
		}
	}

	// 锟斤拷锟斤拷锟斤拷碌锟斤拷锟狡穚kid
	public static int getgoodspkid() throws SQLException {
		String sql = "SELECT pkid FROM goodsinfo ORDER BY pkid DESC LIMIT 1";

		ps = JdbcUtils.queryrs(sql, new ArrayList<Object>());

		rs = ps.executeQuery();

		rs.next();
		int goodspkid = rs.getInt(1);

		rs.close();

		ps.close();

		return goodspkid;
	}

	// 锟斤拷锟斤拷锟矫伙拷锟剿号伙拷锟絧kid
	public static int getuserpkid(String ac) throws SQLException {
		String sql = "SELECT pkid FROM userinfo WHERE AccountNumber=?";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(ac);

		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		rs.next();

		int userpkid = rs.getInt(1);

		rs.close();

		ps.close();

		return userpkid;
	}

	public static void Online(String ac) throws SQLException {
		String sql = "UPDATE userinfo SET OnlineStatus='在线' WHERE AccountNumber=?";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(ac);
		JdbcUtils.updatpsrs(sql, al);
	}
	
	public static void OffOnline(String ac) throws SQLException {
		String sql = "UPDATE userinfo SET OnlineStatus='不在线' WHERE AccountNumber=?";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(ac);
		JdbcUtils.updatpsrs(sql, al);
	}


}
