package s.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import s.Util.DbUtil;
import s.jpanel.PanGoodInfo;
import s.listener.BtnOKKShoppingListener;
import secondheadC.Entity.VO.GoodsInfo;


public class SettlementCar extends JDialog {
	private List<GoodsInfo> listGoods;
	private JPanel panBtn = new JPanel();
	private JPanel panAddress = new JPanel();
	private JScrollPane jsp = new JScrollPane();
	private int userId;
	public JTextArea txtAddress = new JTextArea();
	private DbUtil dbUtil;
	private StringBuffer sb = new StringBuffer();
	private JButton btnOKK = new JButton("结算");
	private JButton btnclosee = new JButton("取消");
	private JLabel labPrice = new JLabel("¥0.00");
	private SettlementCar it;
	private double totalPrice;
	private boolean loop = true;

	public SettlementCar(ArrayList<GoodsInfo> listGoods, String userNum, double totalPrice) {
		this.listGoods = listGoods;
		this.totalPrice = totalPrice;
		this.it = this;

		try {
			dbUtil = DbUtil.getInstance();
			String sql = "SELECT pkid FROM userinfo WHERE AccountNumber=?";
			ResultSet rs = dbUtil.doQuery(sql, userNum);
			rs.next();
			this.userId = rs.getInt(1);
			this.setUI();
			this.labPrice.setText(String.valueOf(totalPrice));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		this.setVisible(true);
	}

	private void setTxtAddress() {
		try {
			dbUtil = DbUtil.getInstance();
			sb.setLength(0);
			sb.append(" SELECT");
			sb.append("  ConsigneeName,PhoneNumber");
			sb.append(" ,sheng_name,shi_name,qu_name");
			sb.append(" ,DetailedAddress,DefaultAddress");
			sb.append(" FROM receivingaddressinfo");
			sb.append(" WHERE fk_user_id=" + this.userId);
			ResultSet set = dbUtil.doQuery(sb.toString());
			this.txtAddress.setText("");
			while (set.next()) {
				if (set.getString(7).equals("1")) {
					this.loop = false;
					this.txtAddress.setText(set.getString(1) + "," + set.getString(2) + "\n" + set.getString(3)
							+ set.getString(4) + set.getString(5) + set.getString(6));
				} else if (loop == true) {
					this.txtAddress.setText(set.getString(1) + "," + set.getString(2) + "\n" + set.getString(3)
							+ set.getString(4) + set.getString(5) + set.getString(6));
				}
			}
			set.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setPanAddress() {
		JLabel labAdd = new JLabel("收货地址:");

		this.setTxtAddress();
		this.txtAddress.setEnabled(false);
		this.txtAddress.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.txtAddress.setForeground(Color.black);
		this.txtAddress.setSize(360, 60);
		this.txtAddress.setLocation(90, 5);
		this.panAddress.add(txtAddress);

		labAdd.setSize(120, 40);
		labAdd.setLocation(2, 0);
		labAdd.setFont(new Font("幼圆", Font.BOLD, 16));
		labAdd.setForeground(Color.gray);
		panAddress.add(labAdd);

		this.panAddress.setSize(450, 160);
		this.panAddress.setLocation(0, 0);
		this.panAddress.setLayout(null);
		this.panAddress.setBackground(Color.white);
		this.add(panAddress, BorderLayout.CENTER);
	}

	private void setJsp() {
		JPanel panGoodsInfo = new JPanel();
		for (int i = 0; i < listGoods.size(); i++) {
			PanGoodInfo panGoodInfo = new PanGoodInfo(listGoods.get(i));
			panGoodsInfo.add(panGoodInfo);
		}

		panGoodsInfo.setLayout(new BoxLayout(panGoodsInfo, BoxLayout.Y_AXIS));
		panGoodsInfo.setBackground(Color.white);
		this.jsp.getViewport().add(panGoodsInfo);
		this.jsp.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.lightGray));
		this.jsp.setSize(438, 500);
		this.jsp.setLocation(4, 70);
		this.add(this.jsp);
	}

	private void addListener() {
		this.btnclosee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		this.txtAddress.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(1);
				new AddressManagement(userId);
				setTxtAddress();
				repainting();
			}
		});

		this.btnOKK.addActionListener(new BtnOKKShoppingListener(listGoods, this.userId, this.totalPrice, it));
	}

	private void repainting() {
		this.repaint();
	}

	private void setPanBtn() {
		JLabel lab = new JLabel("实付款:");
		lab.setSize(80, 40);
		lab.setLocation(15, 18);
		lab.setFont(new Font("微软雅黑", Font.BOLD, 20));
		labPrice.setSize(100, 40);
		labPrice.setLocation(95, 19);
		labPrice.setFont(new Font("微软雅黑", Font.BOLD, 16));
		labPrice.setForeground(Color.red);
		this.panBtn.add(lab);
		this.panBtn.add(labPrice);
		this.panBtn.add(this.setButton(this.btnOKK));
		this.panBtn.add(this.setButton(this.btnclosee));
		this.btnOKK.setLocation(360, 20);
		this.btnclosee.setLocation(270, 20);
		this.panBtn.setSize(450, 50);
		this.panBtn.setLocation(0, 560);
		this.panBtn.setLayout(null);
		this.panBtn.setBackground(Color.white);
		this.add(panBtn);
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
		this.setSize(450, 650);
		this.setLocationRelativeTo(null);
		this.setTitle("下单");
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setJsp();
		setPanAddress();
		setPanBtn();
		addListener();
	}

	// public static void main(String[] args) {
	// SettlementCar buyIt = new SettlementCar(25143518, 1000.90);
	// buyIt.
	// }
}
