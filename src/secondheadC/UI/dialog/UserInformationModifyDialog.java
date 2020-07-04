package secondheadC.UI.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import secondheadC.Entity.VO.UserInfo;
import secondheadC.Event.Dialog.UserInformationModifyDialogEvent;
import secondheadC.jdbc.JdbcOperate;

@SuppressWarnings("serial")
public class UserInformationModifyDialog extends JDialog {

	public UserInfo userinfo;
	public JComboBox<String> cdatac;
	public JComboBox<String> cdatas;
	public JTextField bddata;
	public JTextField pndata;
	public ImageIcon icon;
	public JButton setheadimg;
	public JButton confirm;
	public JButton cancel;
	public JTextField nndata;
	public JComboBox<String> sexdata;
	public JLabel headimg;
	public JTextField sexjtf;
	public JTextField jtfc;
	public JTextField jtfs;
	public JButton eixt;
	public JButton xgzl;
	public JButton bdbtn;

	public UserInformationModifyDialog(secondheadC.Entity.VO.UserInfo u) throws SQLException {
		this(null, "用户信息", true);
		userinfo = u;
		this.setSize(400, 350);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("imagec/LOGO.jpg"));
		this.setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);

		setJL();

		new UserInformationModifyDialogEvent(this);

		this.setVisible(true);
	}

	private void setJL() throws SQLException {

		JLabel ac = new JLabel("账号：");
		ac.setBounds(20, 20, 70, 20);
		JLabel acdata = new JLabel(userinfo.getAccountNumber());
		acdata.setBounds(100, 20, 70, 20);

		JLabel nn = new JLabel("昵称：");
		nn.setBounds(20, 50, 70, 20);
		nndata = new JTextField(userinfo.getNickname());
		nndata.setBounds(100, 50, 100, 20);
		nndata.setEditable(false);

		JLabel sex = new JLabel("性别：");
		sex.setBounds(20, 80, 70, 20);
		sexdata = new JComboBox<String>();
		sexdata.addItem("男");
		sexdata.addItem("女");
		sexdata.setSelectedItem(userinfo.getSex());
		sexdata.setBounds(100, 80, 70, 20);
		sexdata.setVisible(false);

		sexjtf = new JTextField(userinfo.getSex());
		sexjtf.setBounds(100, 80, 70, 20);
		sexjtf.setEditable(false);

		JLabel bd = new JLabel("生日：");
		bd.setBounds(20, 110, 70, 20);
		bddata = new JTextField(userinfo.getBirthday());
		bddata.setBounds(100, 110, 100, 20);
		bddata.setEditable(false);
		
		bdbtn = new JButton("插入日期");
		bdbtn.setBounds(200, 110, 80, 20);
		bdbtn.setVisible(false);
		this.add(bdbtn);

		JLabel c = new JLabel("城市：");
		c.setBounds(20, 140, 70, 20);
		cdatac = new JComboBox<String>();
		JdbcOperate.getAllCityC(cdatac);
		cdatac.setSelectedItem(JdbcOperate.getCcity(userinfo.getCityName()));
		cdatac.setBounds(100, 140, 70, 20);
		cdatac.setVisible(false);

		jtfc = new JTextField(JdbcOperate.getCcity(userinfo.getCityName()));
		jtfc.setBounds(100, 140, 70, 20);
		jtfc.setEditable(false);

		cdatas = new JComboBox<String>();
		JdbcOperate.getAllCitys(cdatas, String.valueOf(cdatac.getSelectedItem()));
		cdatas.setSelectedItem(userinfo.getCityName());
		cdatas.setBounds(180, 140, 70, 20);
		cdatas.setVisible(false);

		jtfs = new JTextField(userinfo.getCityName());
		jtfs.setBounds(180, 140, 70, 20);
		jtfs.setEditable(false);

		JLabel pn = new JLabel("电话：");
		pn.setBounds(20, 170, 70, 20);
		pndata = new JTextField(userinfo.getPhoneNumber());
		pndata.setBounds(100, 170, 100, 20);
		pndata.setEditable(false);

		icon = new ImageIcon(userinfo.getHeadPortraitPath());
		icon.setImage(icon.getImage().getScaledInstance(80, 100, Image.SCALE_DEFAULT));
		headimg = new JLabel(icon);
		headimg.setBounds(280, 20, 80, 100);
		headimg.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));

		setheadimg = new JButton("选择头像");
		setheadimg.setVisible(false);
		setheadimg.setToolTipText("选择一个图片文件作为头像");
		setheadimg.setBounds(275, 130, 90, 20);

		ImageIcon xgzlimg = new ImageIcon("imagec/修改.png");
		xgzlimg.setImage(xgzlimg.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		xgzl = new JButton();
		xgzl.setBounds(320, 130, 30, 30);
		xgzl.setIcon(xgzlimg);
		xgzl.setToolTipText("修改资料");
		this.add(xgzl);

		confirm = new JButton("确认");
		confirm.setBounds(240, 280, 60, 20);
		confirm.setVisible(false);

		eixt = new JButton("退出登录");
		eixt.setBounds(150, 250, 100, 50);
		this.add(eixt);

		cancel = new JButton("取消");
		cancel.setBounds(310, 280, 60, 20);
		cancel.setVisible(false);

		this.add(ac);
		this.add(nn);
		this.add(sex);
		this.add(bd);
		this.add(c);
		this.add(pn);
		this.add(acdata);
		this.add(nndata);
		this.add(sexdata);
		this.add(sexjtf);
		this.add(bddata);
		this.add(cdatac);
		this.add(jtfc);
		this.add(cdatas);
		this.add(jtfs);
		this.add(pndata);
		this.add(headimg);
		this.add(setheadimg);
		this.add(confirm);
		this.add(cancel);

	}

	public UserInformationModifyDialog(Frame object, String string, boolean b) {
		super(object, string, b);
	}

}
