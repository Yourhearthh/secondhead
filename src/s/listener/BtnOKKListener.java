package s.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import s.Util.DbUtil;
import s.frm.BuyIt;
import secondheadC.thread.GetAllGoodsThread;


public class BtnOKKListener implements ActionListener {
	private int goodId;
	private Integer userId;
	private DbUtil dbUtil;
	private StringBuffer sb = new StringBuffer();
	private int sellerId;
	private BuyIt it;
	private double userbalance;
	private double sellerBalance;
	private double totalprice;
	private String goodsStatus;

	public BtnOKKListener(int goodId, Integer userId, int sellerId, double Totalprice, BuyIt it) {
		this.goodId = goodId;
		this.userId = userId;
		this.sellerId = sellerId;
		this.totalprice = Totalprice;
		this.it = it;
		System.out.println(this.userId + "\t买家ID");
		this.userbalance = this.getUserBalance(this.userId);
		this.sellerBalance = this.getUserBalance(this.sellerId);
		this.getGoodstatus(this.goodId);
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

	private void setUserBalance(double balance, Integer userId) {
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
			this.goodsStatus = rs.getString(1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!"".equals(it.txtAddress.getText())) {
			if ("未售".equals(this.goodsStatus)) {
				if (this.userbalance >= this.totalprice) {
					try {
						java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
						this.dbUtil = DbUtil.getInstance();
						// 交易表添加一条记录
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
						this.setUserBalance(this.userbalance - this.totalprice, this.userId);
						this.setUserBalance(this.sellerBalance + this.totalprice, this.sellerId);
						JOptionPane.showMessageDialog(null, "购买成功,等待卖家发货");
						new GetAllGoodsThread().start();
						this.it.dispose();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "余额不足，请及时充值");
				}
			} else {
				JOptionPane.showMessageDialog(null, "您来慢了，商品已卖出或卖家已下架！");
			}
		} else {
			JOptionPane.showMessageDialog(null, "请输入正确的地址");
		}
	}
}
