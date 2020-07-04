package s.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import s.Util.DbUtil;


public class AddToShoppingCar implements ActionListener {
	private int goodsID;
	private Integer userID = null;

	public AddToShoppingCar(int id, Integer userId) {
		this.goodsID = id;
		this.userID = userId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (userID != null) {
			try {
				DbUtil dbUtil = DbUtil.getInstance();
				StringBuffer sb = new StringBuffer();
				sb.append(" SELECT * FROM");
				sb.append(" shoppingcartinfo   ");
				sb.append(" WHERE GoodsId =?");
				sb.append(" AND fk_user_id =?");
				sb.append(";");
				ResultSet rs = dbUtil.doQuery(sb.toString(), this.goodsID, this.userID);
				if (!rs.next()) {
					sb.setLength(0);
					sb.append(" INSERT INTO shoppingcartinfo");
					sb.append(" (GoodsId,fk_user_id)");
					sb.append(" VALUES(");
					sb.append(" ?,?");
					sb.append(" )");
					dbUtil.doUpdate(sb.toString(), this.goodsID, this.userID);
					JOptionPane.showMessageDialog(null, "添加成功！");
				} else {
					JOptionPane.showMessageDialog(null, "重复添加！");
				}

			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "请先登录或注册！");
		}
	}
}
