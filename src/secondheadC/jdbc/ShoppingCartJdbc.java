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
import secondheadC.UI.panel.ShoppingCartPanel;
import secondheadC.thread.ImgThread;

public class ShoppingCartJdbc {
	static PreparedStatement ps = null;
	private static ResultSet rs;

	//获得购物车商品数据
	public static void getAllGoodsCarData(String userac) throws SQLException {
		int pkid=AllGoodsJdbc.getuserpkid(userac);
		

		ArrayList<GoodsInfo> goodsi = ShoppingCartPanel.gi;

		goodsi.removeAll(goodsi);

		String sql = "SELECT g.pkid,Title,SellReason,Price,Initialprice,Freight,ClassificationName,NAME,AccountNumber,SalesStatus FROM	goodsinfo AS g INNER JOIN classificationofgoodsinfo AS c ON g.ClassificationId = c.pkid INNER JOIN china AS ch ON g.Location = ch.Id INNER JOIN userinfo AS u ON g.fk_user_id = u.pkid WHERE g.pkid in( SELECT GoodsId FROM shoppingcartinfo AS s WHERE s.fk_user_id=?)";
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(pkid);
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
			gi.setSalesStatus(rs.getString(10));

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

		ShoppingCartJdbc.getGoodsIcon1(goodsi);
		
		
	}
	
	// 获得所有购物车商品图片
		public static void getGoodsIcon1(ArrayList<GoodsInfo> goodsi) throws SQLException {
			GoodsInfo gi;
			ArrayList<Object> al;
			String url,
					sql = "SELECT GoodsPictureData,GoodsPictureName FROM goodspictureinfo AS gp INNER JOIN goodsinfo AS g ON g.pkid=gp.GoodsID WHERE g.pkid=? LIMIT 1";
			JLabel jl;
			ImageIcon usersicon;
			for (int i = 0; i < goodsi.size(); i++) {
				gi = goodsi.get(i);
				jl = new JLabel();
				al = new ArrayList<Object>();
				al.add(gi.getPkid());
				ps = JdbcUtils.queryrs(sql, al);
				rs = ps.executeQuery();
				rs.next();
				url = "imagec/" + rs.getString(2);
				gi.setGoodsPictureName(url);
				if (!(new File(url).exists())) {
					new ImgThread(rs.getBytes(1), jl, url, 150, 150).start();
					usersicon = new ImageIcon("imagec/hw.jpg");
				} else {
					usersicon = new ImageIcon(url);
				}
				usersicon.setImage(usersicon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				jl.setIcon(usersicon);
				gi.setGoodsPicture(jl);
				rs.close();
				ps.close();

			}

		}

		public static int getAllGoodsCarNum(String userac) throws SQLException {
			int pkid=AllGoodsJdbc.getuserpkid(userac);
			
			String sql = "SELECT COUNT(*) FROM goodsinfo AS g INNER JOIN classificationofgoodsinfo AS c ON g.ClassificationId = c.pkid INNER JOIN china AS ch ON g.Location = ch.Id INNER JOIN userinfo AS u ON g.fk_user_id = u.pkid WHERE g.pkid in(SELECT GoodsId FROM shoppingcartinfo AS s WHERE s.fk_user_id=?)";	
			
			ArrayList<Object> al = new ArrayList<Object>();
			al.add(pkid);
			ps = JdbcUtils.queryrs(sql, al);

			rs = ps.executeQuery();
			rs.next();
			
			int i=rs.getInt(1);
			
			return i;
		}

		public static void delcar(int goodpkid, String userac) throws SQLException {
			int userpkid=AllGoodsJdbc.getuserpkid(userac);
			String sql = "DELETE FROM shoppingcartinfo WHERE GoodsId=? AND fk_user_id=?";
		
			ArrayList<Object> al = new ArrayList<Object>();
			al.add(goodpkid);
			al.add(userpkid);
			JdbcUtils.updatpsrs(sql, al);
			
		}


}
