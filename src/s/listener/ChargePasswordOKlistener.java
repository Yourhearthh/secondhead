package s.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import s.Util.DbUtil;
import s.frm.ChangePassword;





public class ChargePasswordOKlistener implements ActionListener {
	private ChangePassword it;
	private DbUtil dbUtil;
	private String sql = "SELECT * FROM userinfo WHERE ";
	private StringBuffer sb = new StringBuffer();

	public ChargePasswordOKlistener(ChangePassword it) {
		this.it = it;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			dbUtil = DbUtil.getInstance();
			sb.setLength(0);
			sb.append(sql);
			sb.append(" AccountNumber=?");
			sb.append(" ;");
			ResultSet rs = dbUtil.doQuery(sb.toString(), it.txtAccNum.getText());
			if (rs.next()) {
				sb.setLength(0);
				sb.append(sql);
				sb.append(" AccountNumber=? AND PhoneNumber=?");
				sb.append(" ;");
				ResultSet rs1 = dbUtil.doQuery(sb.toString(), it.txtAccNum.getText(), it.txtTelNum.getText());
				if (rs1.next()) {
					if (it.passFirstPassword.getText().length() > 5 && it.passFirstPassword.getText().length() < 26
							&& it.passSecondPassword.getText() != null) {
						if (it.passSecondPassword.getText().equals(it.passFirstPassword.getText())) {
							sb.setLength(0);
							sb.append(" UPDATE userinfo");
							sb.append(" SET PassWords=?");
							sb.append(" WHERE");
							sb.append(" AccountNumber=?");
							sb.append(" ;");
							dbUtil.doUpdate(sb.toString(), it.passSecondPassword.getText(), it.txtAccNum.getText());
							JOptionPane.showMessageDialog(null, "密码找回成功！");
							it.dispose();
						} else {
							it.setTxtErrorMsg("两次密码不匹配！");
						}
					} else {
						it.setTxtErrorMsg("密码长度应在6—25位之间！");
					}
				} else {
					it.setTxtErrorMsg("账号与绑定号码不匹配！");
				}
			} else {
				it.setTxtErrorMsg("账号不存在！");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

}
