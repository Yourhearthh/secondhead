package s.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import s.Util.DbUtil;
import s.frm.SettlementCar;
import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.thread.GetallGoodsCarThread;

public class BtnOKKShoppingListener implements ActionListener {

	List<GoodsInfo> listGoods;
	private int userId;
	private DbUtil dbUtil;
	private StringBuffer sb = new StringBuffer();
	private int sellerId;
	private SettlementCar it;
	private int goodId;
	private double totalPrice;
	private double userbalance;
	private double sellerbalance;
	private boolean goodsStatus = true;

	public BtnOKKShoppingListener(List<GoodsInfo> listGoods, int userId, double totalPrice, SettlementCar it) {
		this.listGoods = listGoods;
		this.userId = userId;
		this.userbalance = this.getUserBalance(this.userId);

		this.totalPrice = totalPrice;
		this.it = it;
	}

	private Double getUserBalance(Integer UserId) {
		try {
			dbUtil = DbUtil.getInstance();
			String sql = "SELECT balance FROM userinfo WHERE pkid=?;";
			ResultSet rs = dbUtil.doQuery(sql, UserId);
			rs.next();
			return rs.getDouble(1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setUserBalance(double balance, int userId) {
		try {
			dbUtil = DbUtil.getInstance();
			String sql = "UPDATE userinfo set balance=? WHERE pkid=?;";
			dbUtil.doUpdate(sql, balance, userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void getGoodstatus(int goodsId) {
		try {
			dbUtil = DbUtil.getInstance();
			String sql = "SELECT SalesStatus FROM goodsinfo WHERE pkid=?;";
			ResultSet rs = dbUtil.doQuery(sql, goodsId);
			rs.next();
			if ("已售".equals(rs.getString(1))) {
				this.goodsStatus = false;
			}
			;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < listGoods.size(); i++) {
			if (!this.goodsStatus) {
				break;

			}
			getGoodstatus(listGoods.get(i).getPkid());
		}

		if (!"".equals(it.txtAddress.getText())) {
			if (this.goodsStatus) {
				if (this.userbalance >= totalPrice) {
					for (int i = 0; i < listGoods.size(); i++) {
						operation(listGoods.get(i));
					}
					JOptionPane.showMessageDialog(null, "购买成功,等待卖家发货");
					this.it.dispose();
					this.setUserBalance(this.userbalance - this.totalPrice, this.userId);
					new GetallGoodsCarThread().start();
				} else {
					JOptionPane.showMessageDialog(null, "余额不足，请及时充值");
				}
			} else {
				JOptionPane.showMessageDialog(null, "您太慢啦！购物车中已有商品被卖出或下架了");
			}
		} else {
			JOptionPane.showMessageDialog(null, "请填写正确的收货地址！");
		}
	}

	private void operation(GoodsInfo goodsInfo) {
		try {
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			this.dbUtil = DbUtil.getInstance();
			this.goodId = goodsInfo.getPkid();

			String sql = "SELECT pkid FROM userinfo WHERE AccountNumber=?";
			ResultSet rs = dbUtil.doQuery(sql, goodsInfo.getUserac());
			rs.next();
			this.sellerId = rs.getInt(1);

			// 给商家入账
			this.sellerbalance = this.getUserBalance(this.sellerId);
			this.setUserBalance(this.sellerbalance + goodsInfo.getPrice() + goodsInfo.getFreight(), this.sellerId);

			// 交易表添加一天记录
			sb.setLength(0);
			sb.append("INSERT INTO transactioninfo	");
			sb.append("( TransactionTime,fk_goods_id");
			sb.append(",fk_Selleruser_id,fk_Buyeruser_id");
			sb.append(")");
			sb.append("VALUES(");
			sb.append("'" + date + "'");
			sb.append("," + this.goodId);
			sb.append("," + this.sellerId);
			sb.append("," + this.userId);
			sb.append(")");
			sb.append(";");
			dbUtil.doUpdate(sb.toString());

			// 商品设置为已售状态
			sb.setLength(0);
			sb.append(" UPDATE goodsinfo");
			sb.append(" SET SalesStatus='已售'");
			sb.append(" WHERE");
			sb.append(" pkid=?");
			sb.append(" ;");
			dbUtil.doUpdate(sb.toString(), this.goodId);

			// 购物车删除该物品
			sb.setLength(0);
			sb.append(" DELETE FROM shoppingcartinfo");
			sb.append(" WHERE");
			sb.append(" GoodsId=?");
			sb.append(" And fk_user_id=?");
			sb.append(" ;");
			dbUtil.doUpdate(sb.toString(), this.goodId, this.userId);

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

	}
}
