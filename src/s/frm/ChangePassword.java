package s.frm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import s.listener.ChargePasswordOKlistener;


public class ChangePassword extends JDialog {
	private JButton btnOK = new JButton("确认");
	private JButton btnCannel = new JButton("取消");
	private JLabel labPassword = new JLabel("找回密码");
	private JLabel labAccNum = new JLabel("账号:");
	public JTextField txtAccNum = new JTextField();
	private JLabel labTelNum = new JLabel("电话号码:");
	public JTextField txtTelNum = new JTextField();
	private JLabel labFirstPassword = new JLabel("新密码：");
	public JPasswordField passFirstPassword = new JPasswordField();
	private JLabel labSecondPassword = new JLabel("确认密码：");
	public JPasswordField passSecondPassword = new JPasswordField();
	private JLabel txtErrorMsg = new JLabel();
	private Font font = new Font("微软雅黑", Font.BOLD, 20);
	private Font txtFont = new Font("微软雅黑", Font.PLAIN, 18);
	private ChangePassword it;

	public ChangePassword() {
		this.it = this;
		this.setUI();
		this.addListener();
		this.setVisible(true);
	}

	private JButton setButton(JButton btn) {
		Font font = new Font("幼圆", Font.BOLD, 15);
		btn.setSize(80, 30);
		btn.setBackground(Color.white);
		btn.setFocusable(false);
		btn.setFont(font);
		return btn;
	}

	private void addListener() {
		this.btnCannel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		this.btnOK.addActionListener(new ChargePasswordOKlistener(it));
	}

	public void setTxtErrorMsg(String str) {
		this.remove(this.txtErrorMsg);
		this.txtErrorMsg.setSize(300, 20);
		this.txtErrorMsg.setLocation(170, 296);
		this.txtErrorMsg.setFont(new Font("幼圆", Font.PLAIN, 16));
		this.txtErrorMsg.setForeground(Color.red);
		this.txtErrorMsg.setText(str);
		this.add(this.txtErrorMsg);
		this.repaint();
	}

	private void setUI() {
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);
		this.setSize(420, 400);
		this.setLocationRelativeTo(null);
		this.setTitle("找回密码");
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.labPassword.setSize(300, 30);
		this.labPassword.setLocation(150, 10);
		this.labPassword.setFont(new Font("微软雅黑", Font.BOLD, 30));
		this.add(this.labPassword);

		this.btnCannel.setSize(50, 50);
		this.btnCannel.setLocation(170, 325);
		this.add(this.setButton(this.btnCannel));

		this.btnOK.setSize(50, 50);
		this.btnOK.setLocation(270, 325);
		this.add(this.setButton(this.btnOK));
		// 账号
		this.labAccNum.setSize(300, 50);
		this.labAccNum.setLocation(100, 75);
		this.labAccNum.setFont(font);
		this.add(this.labAccNum);

		this.txtAccNum.setSize(180, 30);
		this.txtAccNum.setLocation(170, 85);
		this.txtAccNum.setFont(txtFont);
		this.add(this.txtAccNum);
		// 电话
		this.labTelNum.setSize(300, 50);
		this.labTelNum.setLocation(60, 130);
		this.labTelNum.setFont(font);
		this.add(this.labTelNum);

		this.txtTelNum.setSize(180, 30);
		this.txtTelNum.setLocation(170, 140);
		this.txtTelNum.setFont(txtFont);
		this.add(this.txtTelNum);
		// 新密码
		this.labFirstPassword.setSize(300, 50);
		this.labFirstPassword.setLocation(75, 190);
		this.labFirstPassword.setFont(font);
		this.add(this.labFirstPassword);

		this.passFirstPassword.setSize(180, 30);
		this.passFirstPassword.setLocation(170, 200);
		this.passFirstPassword.setFont(txtFont);
		this.add(this.passFirstPassword);
		// 确认密码
		this.labSecondPassword.setSize(300, 50);
		this.labSecondPassword.setLocation(55, 250);
		this.labSecondPassword.setFont(font);
		this.add(this.labSecondPassword);

		this.passSecondPassword.setSize(180, 30);
		this.passSecondPassword.setLocation(170, 260);
		this.passSecondPassword.setFont(txtFont);
		this.add(this.passSecondPassword);
	}

	public static void main(String[] args) {
		new ChangePassword();
	}
}
