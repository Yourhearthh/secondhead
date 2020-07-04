package s.frm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import secondheadC.UI.UserJframe;

public class CreateReceivingAddressFrm extends JDialog {

	private Font font = new Font("幼圆", Font.PLAIN, 18);
	private Font txtFont = new Font("微软雅黑", Font.BOLD, 18);
	private JLabel labAdd = new JLabel("新增收货地址：");

	private Connection conn;
	private JLabel labAddMsg = new JLabel("* 地址信息：");
	private JLabel labSheng = new JLabel("省:");
	private JComboBox<String> cbbSheng = new JComboBox<String>();
	private JLabel labShi = new JLabel("市:");
	private JComboBox<String> cbbShi = new JComboBox<String>();
	private JLabel labQu = new JLabel("区:");
	private JComboBox<String> cbbQu = new JComboBox<String>();

	private JLabel labDetAdd = new JLabel("* 详细地址：");
	private JTextArea txtDetAdd = new JTextArea();

	private JLabel labName = new JLabel("* 收货人姓名：");
	private JTextField txtName = new JTextField();

	private JLabel labNub = new JLabel("* 手机号码：");
	private JTextField txtNub = new JTextField();

	private JCheckBox def = new JCheckBox("设置为默认收货地址");
	private JButton save = new JButton("保存");
	private JLabel errorMsg = null;
	private int defVal;
	private int index;
	private int userId;
	private boolean change = false;

	public CreateReceivingAddressFrm(int userId) {

		this.userId = userId;
		this.setUI();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://"+UserJframe.ip+":3306/secondhand?characterEncoding=utf-8",
					"root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		this.createCbbSheng();
		this.createCbbShi();
		this.createcbbQu();
		this.addlisetenner();
		this.setVisible(true);
	}

	public CreateReceivingAddressFrm(int index, int userId) {
		this.userId = userId;
		this.setUI();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://"+UserJframe.ip+":3306/secondhand?characterEncoding=utf-8",
					"root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		this.createCbbSheng();
		this.createCbbShi();
		this.createcbbQu();
		this.addlisetenner();

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");
		sb.append(" ConsigneeName");
		sb.append(" ,PhoneNumber");
		sb.append(" ,sheng_name");
		sb.append(" ,shi_name");
		sb.append(" ,qu_name");
		sb.append(" ,DetailedAddress");
		sb.append(" FROM");
		sb.append(" receivingaddressinfo");
		sb.append(" WHERE");
		sb.append(" pkid=" + index);
		try {
			PreparedStatement state = conn.prepareStatement(sb.toString());
			ResultSet set = state.executeQuery();
			while (set.next()) {
				this.txtName.setText(set.getString(1));
				this.txtNub.setText(set.getString(2));
				this.cbbSheng.setSelectedItem(set.getString(3));
				this.cbbShi.setSelectedItem(set.getString(4));
				this.cbbQu.setSelectedItem(set.getString(5));
				this.txtDetAdd.setText(set.getString(6));
			}
			set.close();
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.index = index;
		this.change = true;
		this.setVisible(true);
	}

	private void addlisetenner() {
		this.cbbSheng.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cbbShi.removeAllItems();
				createCbbShi();
				cbbQu.removeAllItems();
				createcbbQu();
			}
		});

		this.cbbShi.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cbbQu.removeAllItems();
				createcbbQu();
			}
		});

		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAddMsg();
			}

		});
	}

	private void saveAddMsg() {
		if (txtDetAdd.getText().equals("") || txtName.getText().equals("") || txtNub.getText().equals("")) {
			this.addErrorMsg("请把信息补充完整再选择保存");

		} else if (txtDetAdd.getText().length() < 5) {
			this.addErrorMsg("详细地址长度需要在5-120个字符之间");

		} else if (txtNub.getText().length() != 11) {
			this.addErrorMsg("手机号码格式不正确");

		} else if (def.isSelected()) {
			this.defVal = 1;
			StringBuffer sb = new StringBuffer();
			sb.append(" UPDATE");
			sb.append(" receivingaddressinfo");
			sb.append(" SET");
			sb.append(" DefaultAddress = 0");
			sb.append(" WHERE");
			sb.append(" DefaultAddress = 1 and fk_user_id=" + this.userId);
			sb.append(" ;");
			try {
				PreparedStatement pstat = conn.prepareStatement(sb.toString());
				pstat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (change == true) {
				this.changeDefAdd(this.index);
			} else {
				this.settingDefAdd();
			}
		} else {
			// 设置为非默认地址
			this.defVal = 0;
			if (change == true) {
				this.changeDefAdd(this.index);
			} else {
				this.settingDefAdd();
			}
		}
	}

	// 修改地址
	private void changeDefAdd(int index) {
		StringBuffer sb = new StringBuffer();
		sb.append(" UPDATE");
		sb.append(" receivingaddressinfo");
		sb.append(" SET");
		sb.append(" consigneeName='" + txtName.getText() + "'");
		sb.append(" ,phoneNumber=" + txtNub.getText());
		sb.append(" ,sheng_name='" + (String) cbbSheng.getSelectedItem() + "'");
		sb.append(" ,shi_name='" + (String) cbbShi.getSelectedItem() + "'");
		sb.append(" ,qu_name='" + (String) cbbQu.getSelectedItem() + "'");
		sb.append(" ,fk_user_id=" + this.userId);
		sb.append(" ,DetailedAddress='" + txtDetAdd.getText() + "'");
		sb.append(" ,DefaultAddress=" + this.defVal);
		sb.append(" WHERE");
		sb.append(" pkid = " + index);
		PreparedStatement pstat;
		try {
			pstat = conn.prepareStatement(sb.toString());
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			JOptionPane.showMessageDialog(this, "地址保存成功", "保存地址", JOptionPane.PLAIN_MESSAGE);
			Thread.sleep(500);
			this.dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 设置地址
	private void settingDefAdd() {
		StringBuffer sb = new StringBuffer();

		sb.append(" INSERT");
		sb.append(" INTO");
		sb.append(" receivingaddressinfo");
		sb.append(" (consigneeName");
		sb.append(" ,phoneNumber");
		sb.append(" ,sheng_name");
		sb.append(" ,shi_name");
		sb.append(" ,qu_name");
		sb.append(" ,fk_user_id");
		sb.append(" ,DetailedAddress");
		sb.append(" ,DefaultAddress");
		sb.append(" ) VALUES");
		sb.append(" ('" + txtName.getText() + "'");// 名字
		sb.append(" ," + txtNub.getText() + "");// 电话号码
		sb.append(" ,'" + (String) cbbSheng.getSelectedItem() + "'");// 省
		sb.append(" ,'" + (String) cbbShi.getSelectedItem() + "'");// 市
		sb.append(" ,'" + (String) cbbQu.getSelectedItem() + "'");// 区
		sb.append(" , " + this.userId);
		sb.append(" ,'" + txtDetAdd.getText() + "'");// 详细信息
		sb.append(" , " + this.defVal);
		sb.append(" )");
		sb.append(";");
		PreparedStatement pstat;
		try {
			pstat = conn.prepareStatement(sb.toString());
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			JOptionPane.showMessageDialog(this, "地址保存成功", "保存地址", JOptionPane.PLAIN_MESSAGE);
			Thread.sleep(500);
			this.dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void addErrorMsg(String str) {
		if (errorMsg != null) {
			this.remove(errorMsg);
		}
		this.errorMsg = new JLabel(str);
		errorMsg.setFont(font);
		errorMsg.setSize(350, 50);
		errorMsg.setLocation(330, 393);
		errorMsg.setForeground(Color.red);
		this.add(errorMsg);
		this.repaint();
	}

	private void createcbbQu() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");
		sb.append(" china.`Name`");
		sb.append(" FROM");
		sb.append(" china");
		sb.append(" WHERE");
		sb.append(" china.Pid=");
		sb.append(" (SELECT");
		sb.append(" china.Id");
		sb.append(" FROM");
		sb.append(" china");
		sb.append(" WHERE");
		sb.append(" china.`Name`='" + (String) cbbShi.getSelectedItem() + "'");
		sb.append(" )");
		sb.append(" ;");
		this.setList(sb, cbbQu);
	}

	private void createCbbShi() {
		if (((String) cbbSheng.getSelectedItem()).equals("北京市") || ((String) cbbSheng.getSelectedItem()).equals("天津市")
				|| ((String) cbbSheng.getSelectedItem()).equals("上海市")
				|| ((String) cbbSheng.getSelectedItem()).equals("重庆市")) {
			cbbShi.addItem((String) cbbSheng.getSelectedItem());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT");
			sb.append(" china.`Name`");
			sb.append(" FROM");
			sb.append(" china");
			sb.append(" WHERE");
			sb.append(" china.Pid=");
			sb.append(" (SELECT");
			sb.append(" china.Id");
			sb.append(" FROM");
			sb.append(" china");
			sb.append(" WHERE");
			sb.append(" china.`Name`='" + (String) cbbSheng.getSelectedItem() + "'");
			sb.append(" )");
			sb.append(" ;");
			this.setList(sb, cbbShi);
		}
	}

	private void createCbbSheng() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");
		sb.append(" china.`Name`");
		sb.append(" FROM");
		sb.append(" china");
		sb.append(" WHERE");
		sb.append(" china.Pid=0");
		sb.append(" and china.`Name`!='中国'");
		sb.append(";");
		this.setList(sb, cbbSheng);

	}

	public void setList(StringBuffer sb, JComboBox<String> cbb) {
		try {
			PreparedStatement pstat = conn.prepareStatement(sb.toString());
			ResultSet set = pstat.executeQuery();
			while (set.next()) {
				cbb.addItem(set.getString(1));
			}
			set.close();
			pstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setUI() {
		this.setTitle("创建地址");
		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLayout(null);

		// 省市区
		this.labAdd.setFont(font);
		this.labAdd.setSize(150, 50);
		this.labAdd.setLocation(150, 15);
		this.labAdd.setForeground(Color.red);
		this.add(labAdd);

		this.labAddMsg.setFont(font);
		this.labAddMsg.setSize(150, 50);
		this.labAddMsg.setLocation(130, 55);
		this.add(labAddMsg);

		this.labSheng.setFont(font);
		this.labSheng.setSize(150, 50);
		this.labSheng.setLocation(150, 90);
		this.add(labSheng);
		this.cbbSheng.setSize(120, 25);
		this.cbbSheng.setBackground(Color.white);
		this.cbbSheng.setLocation(190, 100);
		this.add(cbbSheng);

		this.labShi.setFont(font);
		this.labShi.setSize(150, 50);
		this.labShi.setLocation(350, 90);
		this.add(labShi);
		this.cbbShi.setSize(120, 25);
		this.cbbShi.setBackground(Color.white);
		this.cbbShi.setLocation(390, 100);
		this.add(cbbShi);

		this.labQu.setFont(font);
		this.labQu.setSize(150, 50);
		this.labQu.setLocation(550, 90);
		this.add(labQu);
		this.cbbQu.setSize(120, 25);
		this.cbbQu.setBackground(Color.white);
		this.cbbQu.setLocation(590, 100);
		this.add(cbbQu);

		// 详细地址
		this.labDetAdd.setFont(font);
		this.labDetAdd.setSize(150, 50);
		this.labDetAdd.setLocation(130, 140);
		this.add(labDetAdd);
		this.txtDetAdd.setSize(320, 54);
		this.txtDetAdd.setFont(txtFont);
		this.txtDetAdd.setLineWrap(true);
		this.txtDetAdd.setLocation(240, 155);
		this.txtDetAdd.setBorder(new LineBorder(Color.lightGray));
		this.add(txtDetAdd);

		// 名字
		this.labName.setFont(font);
		this.labName.setSize(150, 50);
		this.labName.setLocation(110, 230);
		this.add(labName);
		this.txtName.setSize(320, 30);
		this.txtName.setLocation(240, 240);
		this.txtName.setFont(txtFont);
		this.txtName.setBorder(new LineBorder(Color.lightGray));
		this.add(txtName);

		// 电话号码
		this.labNub.setFont(font);
		this.labNub.setSize(150, 50);
		this.labNub.setLocation(130, 290);
		this.add(labNub);
		this.txtNub.setFont(txtFont);
		this.txtNub.setSize(200, 30);
		this.txtNub.setLocation(240, 300);
		this.txtNub.setBorder(new LineBorder(Color.lightGray));
		this.add(txtNub);

		// 按钮和复选按钮
		this.def.setFont(font);
		this.def.setSize(250, 50);
		this.def.setLocation(240, 340);
		this.def.setBackground(Color.white);
		this.def.setFocusable(false);
		this.add(def);
		this.save.setFont(font);
		this.save.setSize(80, 35);
		this.save.setLocation(240, 400);
		this.save.setBackground(Color.white);
		this.save.setFocusable(false);
		this.add(save);

	}

	public static void main(String[] args) {
		CreateReceivingAddressFrm cra = new CreateReceivingAddressFrm(1);
	}

}
