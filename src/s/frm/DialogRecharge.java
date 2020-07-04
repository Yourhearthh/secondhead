package s.frm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import s.Util.DbUtil;
import secondheadC.UI.UserJframe;
import secondheadC.jdbc.MyJdbc;


public class DialogRecharge extends JDialog {
	private JButton btnOK = new JButton("确认");
	private JButton btnCannel = new JButton("取消");
	private JLabel labRecharge = new JLabel("余额充值");
	private JLabel labAdd = new JLabel("充值金额：");
	private JLabel labBalance = new JLabel();
	private JTextField txtmoney = new JTextField();
	private DbUtil dbUtil;
	private Integer userId = null;
	private Double balance;
	private Font font = new Font("微软雅黑", Font.BOLD, 20);

	public DialogRecharge(Integer userId) {
		this.userId = userId;
		this.setUI();
		addListener();
		this.setVisible(true);
	}

	// 将余额显示
	private void setLabBalanceTxt() {
		DecimalFormat df = new DecimalFormat("");
		this.balance = getUserBalance(this.userId);
		this.labBalance.setText("当前余额：" + df.format(balance) + "元");
	}

	// 获取余额
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

	// 设置余额
	private void setUserBalance(double balance, Integer userId) {
		try {
			dbUtil = DbUtil.getInstance();
			String sql = "UPDATE userinfo set balance=? WHERE pkid=?;";
			dbUtil.doUpdate(sql, balance, userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void repainting() {
		this.repaint();
	}

	private void addListener() {
		this.btnCannel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		this.btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!"0".equals(txtmoney.getText())) {
					if (!"".equals(txtmoney.getText())) {
						if (Double.valueOf(txtmoney.getText()) > 0) {
							try {

								DecimalFormat df = new DecimalFormat("");
								if (df.format(balance + Double.valueOf(txtmoney.getText())).length() < 13) {
									balance += Double.valueOf(txtmoney.getText());
									setUserBalance(balance, userId);
									setLabBalanceTxt();
									UserJframe.myPanel.jlal.get(0).setText(df.format(Double.valueOf(MyJdbc.getBalance(UserJframe.userac))) + "元");
									JOptionPane.showMessageDialog(null, "充值成功");
									txtmoney.setText("");
									repainting();
								} else {
									JOptionPane.showMessageDialog(null, "余额最高上限为 999999999元");
								}
							} catch (NumberFormatException | SQLException e1) {
								JOptionPane.showMessageDialog(null, "请输入正确的充值金额");
							}
						} else {
							JOptionPane.showMessageDialog(null, "充值金额不能为负数");
						}
					} else {
						JOptionPane.showMessageDialog(null, "充值金额不能为空");
					}
				} else {
					JOptionPane.showMessageDialog(null, "充值金额不能为0");
				}
			}
		});
	}

	private JButton setButton(JButton btn) {
		Font font = new Font("幼圆", Font.BOLD, 15);
		btn.setSize(80, 30);
		btn.setBackground(Color.white);
		btn.setFocusable(false);
		btn.setFont(font);
		return btn;
	}

	private void setUI() {
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);
		this.setSize(430, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("余额充值");
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.labRecharge.setSize(300, 50);
		this.labRecharge.setLocation(150, 15);
		this.labRecharge.setFont(new Font("微软雅黑", Font.BOLD, 30));
		this.add(this.labRecharge);

		this.btnCannel.setSize(50, 50);
		this.btnCannel.setLocation(100, 220);
		this.add(this.setButton(this.btnCannel));

		this.btnOK.setSize(50, 50);
		this.btnOK.setLocation(230, 220);
		this.add(this.setButton(this.btnOK));

		this.labBalance.setSize(300, 50);
		this.labBalance.setLocation(90, 75);
		this.labBalance.setFont(font);
		this.setLabBalanceTxt();
		this.add(this.labBalance);

		this.labAdd.setSize(100, 50);
		this.labAdd.setLocation(90, 140);
		this.labAdd.setFont(font);
		this.setLabBalanceTxt();
		this.add(this.labAdd);

		this.txtmoney.setSize(140, 30);
		this.txtmoney.setLocation(193, 152);
		this.txtmoney.setFont(font);
		this.setLabBalanceTxt();
		this.add(this.txtmoney);
	}

	public static void main(String[] args) {
		new DialogRecharge(3);
	}
}
