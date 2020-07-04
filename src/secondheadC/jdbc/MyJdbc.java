package secondheadC.jdbc;

import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.thread.ImgThread;

public class MyJdbc {
	static PreparedStatement ps = null;
	private static ResultSet rs;

	// 我的余额
	public static String getBalance(String userac) throws SQLException {
		String sql = "SELECT Balance FROM userinfo WHERE AccountNumber=?";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userac);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		rs.next();
		String Balance = rs.getDouble(1) + "";

		rs.close();
		ps.close();

		return Balance;

	}

	// 我发布的
	public static String getMyReleasedCount(int userpkid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM goodsinfo WHERE fk_user_id=?";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userpkid);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		rs.next();

		String myReleasedCount = rs.getInt(1) + "";

		rs.close();
		ps.close();

		return myReleasedCount;
	}

	// 我卖出的
	public static String getMySoldCount(int userpkid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM goodsinfo WHERE fk_user_id=? AND SalesStatus='已售'";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userpkid);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		rs.next();

		String mySoldCount = rs.getInt(1) + "";

		rs.close();
		ps.close();

		return mySoldCount;
	}

	public static String getMyBuyCount(int userpkid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM transactioninfo WHERE fk_Buyeruser_id=?";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userpkid);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		rs.next();

		String myBuyCount = rs.getInt(1) + "";

		rs.close();
		ps.close();

		return myBuyCount;
	}

	public static String getMyReceivingAddressCount(int userpkid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM receivingaddressinfo WHERE fk_user_id=?";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userpkid);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		rs.next();

		String myReceivingAddressCount = rs.getInt(1) + "";

		rs.close();
		ps.close();

		return myReceivingAddressCount;
	}

	public static ArrayList<GoodsInfo> getMyBuyGoods(int userpkid) throws SQLException {
		ArrayList<GoodsInfo> gi = new ArrayList<GoodsInfo>();
		GoodsInfo g;
		String sql = "SELECT g.pkid,Title,SellReason,Price,Initialprice,Freight FROM transactioninfo AS t LEFT JOIN goodsinfo AS g ON g.pkid=t.fk_goods_id WHERE t.fk_Buyeruser_id=?";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userpkid);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		while (rs.next()) {
			g = new GoodsInfo();
			g.setPkid(rs.getInt(1));
			g.setTitle(rs.getString(2));
			g.setSellReason(rs.getString(3));
			g.setPrice(rs.getDouble(4));
			g.setInitialPrice(rs.getDouble(5));
			g.setFreight(rs.getDouble(6));
			gi.add(g);
		}
		rs.close();
		ps.close();

		AllGoodsJdbc.getGoodsIcon(gi, 100, 100);

		return gi;
	}

	public static ArrayList<GoodsInfo> getMySoldGoods(int userpkid) throws SQLException {
		ArrayList<GoodsInfo> gi = new ArrayList<GoodsInfo>();
		GoodsInfo g;
		String sql = "SELECT g.pkid,Title,SellReason,Price,Initialprice,Freight FROM transactioninfo AS t LEFT JOIN goodsinfo AS g ON g.pkid=t.fk_goods_id WHERE t.fk_Selleruser_id=?";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userpkid);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		while (rs.next()) {
			g = new GoodsInfo();
			g.setPkid(rs.getInt(1));
			g.setTitle(rs.getString(2));
			g.setSellReason(rs.getString(3));
			g.setPrice(rs.getDouble(4));
			g.setInitialPrice(rs.getDouble(5));
			g.setFreight(rs.getDouble(6));
			gi.add(g);
		}
		rs.close();
		ps.close();

		AllGoodsJdbc.getGoodsIcon(gi, 100, 100);

		return gi;
	}

	public static ArrayList<GoodsInfo> getMyReleasedGoods(int userpkid) throws SQLException {
		ArrayList<GoodsInfo> gi = new ArrayList<GoodsInfo>();
		GoodsInfo g;
		String sql = "SELECT g.pkid,Title,SellReason,Price,Initialprice,Freight,ClassificationName,NAME,AccountNumber,SalesStatus FROM	goodsinfo AS g INNER JOIN classificationofgoodsinfo AS c ON g.ClassificationId = c.pkid INNER JOIN china AS ch ON g.Location = ch.Id INNER JOIN userinfo AS u ON g.fk_user_id = u.pkid WHERE g.fk_user_id=?";

		ArrayList<Object> al = new ArrayList<Object>();
		al.add(userpkid);
		ps = JdbcUtils.queryrs(sql, al);

		rs = ps.executeQuery();

		while (rs.next()) {
			g = new GoodsInfo();
			g.setPkid(rs.getInt(1));
			g.setTitle(rs.getString(2));
			g.setSellReason(rs.getString(3));
			g.setPrice(rs.getDouble(4));
			g.setInitialPrice(rs.getDouble(5));
			g.setFreight(rs.getDouble(6));
			g.setClassificationName(rs.getString(7));
			g.setLocation(rs.getString(8));
			g.setUserac(rs.getString(9));
			g.setSalesStatus(rs.getString(10));
			gi.add(g);
		}
		rs.close();
		ps.close();

		AllGoodsJdbc.getGoodsIcon(gi, 100, 100);

		return gi;
	}

	public static ArrayList<String> getgoodimg(int pkid) throws SQLException {
		String sql = "SELECT GoodsPictureData,GoodsPictureName FROM goodspictureinfo WHERE GoodsId=?";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(pkid);
		ps = JdbcUtils.queryrs(sql, al);
		rs = ps.executeQuery();

		JLabel jl;
		String url;
		ImageIcon usersicon;
		ArrayList<String> stral = new ArrayList<String>();
		while (rs.next()) {
			jl = new JLabel();
			url = "imagec/" + rs.getString(2);
			if (!(new File(url).exists())) {
				new ImgThread(rs.getBytes(1), jl, url, 100, 100).start();
				usersicon = new ImageIcon("imagec/hw.jpg");
			} else {
				usersicon = new ImageIcon(url);
			}
			usersicon.setImage(usersicon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			jl.setIcon(usersicon);
			stral.add(url);
		}
		rs.close();
		ps.close();

		return stral;
	}

	public static void UpdataTitle(int pkid, String title) throws SQLException {
		String sql = "UPDATE goodsinfo SET Title=? WHERE (pkid=?)";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(title);
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
			
	}

	public static void UpdataSellReason(int pkid, String sellReason) throws SQLException {
		String sql = "UPDATE goodsinfo SET SellReason=? WHERE (pkid=?)";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(sellReason);
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
	}

	public static void UpdataPrice(int pkid, double price) throws SQLException {
		String sql = "UPDATE goodsinfo SET Price=? WHERE (pkid=?)";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(price);
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
	}

	public static void UpdataInitialPrice(int pkid, double initialPrice) throws SQLException {
		String sql = "UPDATE goodsinfo SET InitialPrice=? WHERE (pkid=?)";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(initialPrice);
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
	}

	public static void UpdataFreight(int pkid, double freight) throws SQLException {
		String sql = "UPDATE goodsinfo SET Freight=? WHERE (pkid=?)";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(freight);
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
	}

	public static void UpdataClassificationName(int pkid, String classificationName) throws SQLException {
		int cfi = 1;

		switch (classificationName) {
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
		
		String sql = "UPDATE goodsinfo SET ClassificationId=? WHERE (pkid=?)";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(cfi);
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
	}

	public static void UpdataLocation(int pkid, String location) throws SQLException {
		String sql = "UPDATE goodsinfo SET Location=? WHERE (pkid=?)";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(JdbcOperate.getcityid(location));
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
	}

	public static void DelImg(int pkid) throws SQLException {
		String sql = "DELETE FROM goodsPictureInfo WHERE GoodsId=?";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
	}

	public static void UpdataModifyTime(int pkid) throws SQLException {
		String sql = "UPDATE goodsinfo SET ModifyTime=? WHERE (pkid=?)";
		ArrayList<Object>al =new ArrayList<Object>();
		al.add(JdbcOperate.getdate());
		al.add(pkid);
		JdbcUtils.updatpsrs(sql, al);
	}

}
