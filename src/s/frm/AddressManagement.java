package s.frm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import secondheadC.UI.UserJframe;
import secondheadC.jdbc.AllGoodsJdbc;
import secondheadC.jdbc.MyJdbc;

public class AddressManagement extends JDialog {
	private static final long serialVersionUID = 1L;
	private Font font = new Font("幼圆", Font.PLAIN, 20);
	private JLabel labAddMag = new JLabel("地址管理");
	// 每重构一次重新初始化值
	private ArrayList<Integer> pkid = new ArrayList<Integer>();

	private Connection conn;
	private JScrollPane panScr = new JScrollPane();
	private JPanel panAddMag = new JPanel();

	private JButton btnNew = new JButton("新增地址");
	private Dimension dimPanelSize = new Dimension(820, 50);
	private JPanel panHead;
	private Integer userId = null;

	public AddressManagement(Integer userId) {
		try {
			this.userId = userId;
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://"+UserJframe.ip+":3306/secondhand?characterEncoding=utf-8",
					"root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (userId != null) {
			this.setHead();
			this.setUI();
			this.setPanAddMag();
			this.addlistener();
		} else {
			JOptionPane.showMessageDialog(null, "请先登录或注册！");
		}
		this.setVisible(true);
	}

	private JLabel setLab(String str) {
		JLabel lab = new JLabel(str, JLabel.LEFT);
		lab.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lab.setSize(100, 30);
		return lab;
	}

	private void setHead() {
		this.panHead = new JPanel();
		panHead.setSize(850, 30);
		panHead.setBorder(new EmptyBorder(5, 5, 5, 5));
		panHead.setBackground(Color.white);
		JLabel labName = this.setLab("收货人");
		labName.setPreferredSize(new Dimension(150, 40));
		JLabel labArea = this.setLab("所在地区");
		labArea.setPreferredSize(new Dimension(120, 40));
		JLabel labadd = this.setLab("详细地址");
		labadd.setPreferredSize(new Dimension(145, 40));
		JLabel labNub = this.setLab("电话号码");
		labNub.setPreferredSize(new Dimension(145, 40));
		JLabel labSet = this.setLab("操作");
		labSet.setPreferredSize(new Dimension(200, 40));
		labName.setLocation(40, 10);
		labArea.setLocation(160, 10);
		labadd.setLocation(320, 10);
		labNub.setLocation(470, 10);
		labSet.setLocation(650, 10);
		panHead.add(labName);
		panHead.add(labArea);
		panHead.add(labadd);
		panHead.add(labNub);
		panHead.add(labSet);
		panHead.setPreferredSize(this.dimPanelSize);
		this.panAddMag.add(panHead);
	}

	private void setBody(String name, String area, String add, String nub, int def, final int index) {
		Font font = new Font("幼圆", Font.PLAIN, 16);
		JPanel panBody = new JPanel();
		panBody.setSize(850, 80);
		panBody.setBorder(new EmptyBorder(5, 5, 5, 5));
		panBody.setBackground(Color.WHITE);
		JLabel labName = new JLabel(name, JLabel.LEFT);
		labName.setPreferredSize(new Dimension(120, 40));
		JTextArea txtArea = new JTextArea(area);
		txtArea.setPreferredSize(new Dimension(120, 40));
		txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		JTextArea txtAdd = new JTextArea(add);
		txtAdd.setPreferredSize(new Dimension(120, 40));
		txtAdd.setLineWrap(true);
		txtAdd.setEditable(false);
		JLabel labNub = new JLabel(nub, JLabel.CENTER);
		labNub.setPreferredSize(new Dimension(150, 40));
		JButton btnChange = new JButton("修改");
		btnChange.setBackground(Color.white);
		btnChange.setFocusable(false);
		btnChange.setBorder(new EmptyBorder(0, 0, 0, 0));
		JButton btnDel = new JButton("删除");
		btnDel.setPreferredSize(new Dimension(100, 40));
		btnDel.setBackground(Color.white);
		btnDel.setFocusable(false);
		btnDel.setBorder(new EmptyBorder(0, 0, 0, 0));
		JButton btnSetDef = new JButton("设为默认");
		btnSetDef.setBackground(Color.white);
		btnSetDef.setFocusable(false);
		btnSetDef.setBorder(new LineBorder(Color.red));
		JLabel labDef = new JLabel("默认地址");

		labName.setFont(font);
		txtArea.setFont(font);
		txtAdd.setFont(font);
		labNub.setFont(font);
		btnChange.setFont(font);
		btnDel.setFont(font);
		btnSetDef.setFont(font);
		labDef.setFont(font);

		labName.setSize(100, 60);
		txtArea.setSize(120, 60);
		txtAdd.setSize(130, 60);
		labNub.setSize(100, 60);
		btnChange.setSize(50, 60);
		btnDel.setSize(50, 60);
		btnSetDef.setSize(80, 60);
		labDef.setSize(100, 60);

		labName.setLocation(40, 10);
		txtArea.setLocation(140, 10);
		txtAdd.setLocation(300, 10);
		labNub.setLocation(490, 10);
		btnChange.setLocation(630, 10);
		btnDel.setLocation(675, 10);
		btnSetDef.setLocation(750, 10);
		labDef.setLocation(750, 10);

		panBody.add(labName);
		panBody.add(txtArea);
		panBody.add(txtAdd);
		panBody.add(labNub);
		panBody.add(btnChange);
		panBody.add(btnDel);
		if (def == 0) {
			panBody.add(btnSetDef);
		} else {
			panBody.add(labDef);
		}
		panBody.setPreferredSize(this.dimPanelSize);
		this.panAddMag.add(panBody);

		// 给三个按钮添加监听
		btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateReceivingAddressFrm cad = new CreateReceivingAddressFrm(index, userId);
				updateRepaint();
			}
		});

		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "DELETE FROM receivingaddressinfo WHERE pkid=";
				try {
					PreparedStatement state = conn.prepareStatement(str + index + ";");
					state.executeUpdate();
					state.close();
					updateRepaint();
					UserJframe.myPanel.jlal.get(4).setText(MyJdbc.getMyReceivingAddressCount(AllGoodsJdbc.getuserpkid(UserJframe.userac)));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				updateRepaint();
			}
		});

		btnSetDef.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "UPDATE receivingaddressinfo SET DefaultAddress = 0 WHERE fk_user_id=";
				try {
					PreparedStatement state = conn.prepareStatement(str + userId + ";");
					state.executeUpdate();
					state.close();
					str = "UPDATE receivingaddressinfo SET DefaultAddress = 1 WHERE pkid = ";
					state = conn.prepareStatement(str + index + ";");
					state.executeUpdate();
					state.close();
					updateRepaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void updateRepaint() {
		panAddMag.removeAll();
		panAddMag.add(panHead);
		pkid.clear();
		setPanAddMag();
		this.repaint();
	}

	private void setPanAddMag() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");
		sb.append(" pkid");
		sb.append(" FROM");
		sb.append(" receivingaddressinfo");
		try {
			PreparedStatement state = conn.prepareStatement(sb.toString());
			ResultSet set = state.executeQuery();
			while (set.next()) {
				this.pkid.add(set.getInt(1));
			}
			set.close();
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < pkid.size(); i++) {
			sb.setLength(0);
			sb.append("SELECT");
			sb.append(" ConsigneeName");
			sb.append(",sheng_name");
			sb.append(",shi_name");
			sb.append(",qu_name");
			sb.append(",DetailedAddress");
			sb.append(",PhoneNumber");
			sb.append(",DefaultAddress");
			sb.append(" FROM");
			sb.append(" receivingaddressinfo");
			sb.append(" WHERE");
			sb.append(" pkid=" + pkid.get(i));
			sb.append(" and fk_user_id=" + this.userId);
			try {
				PreparedStatement state = conn.prepareStatement(sb.toString());
				ResultSet set = state.executeQuery();
				while (set.next()) {
					this.setBody(set.getString(1), set.getString(2) + set.getString(3) + set.getString(4),
							set.getString(5), set.getString(6), set.getInt(7), pkid.get(i));

				}
				set.close();
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void addlistener() {
		this.btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateReceivingAddressFrm cra = new CreateReceivingAddressFrm(userId);
				setPanAddMag();
				updateRepaint();
				try {
					UserJframe.myPanel.jlal.get(4).setText(MyJdbc.getMyReceivingAddressCount(AllGoodsJdbc.getuserpkid(UserJframe.userac)));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void setUI() {
		this.setTitle("地址管理");
		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLayout(null);

		this.labAddMag.setSize(150, 30);
		this.labAddMag.setLocation(370, 10);
		this.labAddMag.setFont(new Font("幼圆", Font.BOLD, 30));
		this.add(labAddMag);

		this.panScr.setSize(850, 350);
		this.panScr.setLocation(25, 55);
		this.panAddMag.setBackground(Color.white);
		this.panAddMag.setPreferredSize(this.dimPanelSize);
		this.panScr.getViewport().add(this.panAddMag);
		this.add(panScr);

		this.btnNew.setFont(font);
		this.btnNew.setSize(120, 40);
		this.btnNew.setLocation(380, 420);
		this.btnNew.setBackground(Color.white);
		this.btnNew.setFocusable(false);
		this.add(btnNew);
	}

	public static void main(String[] args) {
		new AddressManagement(1);
	}
}
