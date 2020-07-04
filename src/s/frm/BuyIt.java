package s.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import s.Util.DbUtil;
import s.listener.BtnOKKListener;


public class BuyIt extends JDialog {
	private int goodId;
	private Integer userId = null;
	private int sellerId;
	private JPanel panMsg = new JPanel();
	private JPanel panAddress = new JPanel();
	private JPanel panBtn = new JPanel();
	private JButton btnOKK = new JButton("结算");
	private JButton btnclosee = new JButton("取消");
	private JLabel labPrice = new JLabel("¥1000");
	private DbUtil dbUtil;
	private StringBuffer sb = new StringBuffer();
	private JLabel labFreight = new JLabel();
	private JButton btnAddMan = new JButton("地址管理");
	private String tranUrl;
	public JTextArea txtAddress = new JTextArea();
	private BuyIt it;
	private double totlePrice;
	private boolean loop = true;

	public BuyIt(int id, Integer userId, String tranUrl) {
		this.it = this;
		this.goodId = id;
		this.userId = userId;
		this.tranUrl = tranUrl;
		this.setUI();
		this.addListener();
		this.setVisible(true);
	}

	private void setPanMsg() {

		ArrayList<JLabel> Lablist = new ArrayList<JLabel>();
		try {
			dbUtil = DbUtil.getInstance();
			sb.setLength(0);
			sb.append(" SELECT");
			sb.append("  Price,Initialprice");
			sb.append(" ,Freight,Title,fk_user_id");
			sb.append(" FROM");
			sb.append(" goodsinfo");
			sb.append(" WHERE");
			sb.append(" pkid=" + this.goodId);
			ResultSet set = dbUtil.doQuery(sb.toString());
			while (set.next()) {
				Lablist.add(new JLabel("¥" + set.getString(1)));
				Lablist.add(new JLabel("原价:¥" + set.getString(2)));
				this.totlePrice = set.getDouble(1) + set.getDouble(3);
				this.labPrice.setText(String.valueOf(this.totlePrice));
				this.labFreight.setText("¥" + set.getString(3));
				Lablist.add(new JLabel(set.getString(4)));
				this.sellerId = set.getInt(5);
			}
			set.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		Lablist.get(2).setSize(300, 40);
		Lablist.get(2).setLocation(120, 0);
		Lablist.get(2).setFont(new Font("微软雅黑", Font.BOLD, 20));
		panMsg.add(Lablist.get(2));

		Lablist.get(0).setSize(120, 40);
		Lablist.get(0).setLocation(120, 60);
		Lablist.get(0).setFont(new Font("微软雅黑", Font.BOLD, 20));
		Lablist.get(0).setForeground(Color.red);
		panMsg.add(Lablist.get(0));

		Lablist.get(1).setSize(120, 40);
		Lablist.get(1).setLocation(230, 60);
		Lablist.get(1).setFont(new Font("微软雅黑", Font.PLAIN, 15));
		Lablist.get(1).setForeground(Color.gray);
		panMsg.add(Lablist.get(1));

		ImageIcon image = new ImageIcon(tranUrl);
		image.setImage(image.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JLabel labImg = new JLabel(image);
		labImg.setSize(90, 90);
		labImg.setLocation(15, 5);
		panMsg.add(labImg);

		this.panMsg.setLayout(null);
		this.panMsg.setSize(500, 100);
		this.panMsg.setBackground(Color.white);
		this.add(panMsg);
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
		JLabel labPrice = new JLabel("运费:");

		this.setTxtAddress();
		this.txtAddress.setEnabled(false);
		this.txtAddress.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.txtAddress.setSize(380, 60);
		this.txtAddress.setLocation(100, 28);
		this.panAddress.add(txtAddress);

		this.btnAddMan.setSize(120, 40);
		this.btnAddMan.setFocusable(false);
		this.btnAddMan.setBackground(Color.white);
		this.btnAddMan.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.btnAddMan.setFont(new Font("幼圆", Font.BOLD, 18));
		this.btnAddMan.setLocation(360, 88);
		panAddress.add(this.btnAddMan);

		labAdd.setSize(120, 40);
		labAdd.setLocation(10, 20);
		labAdd.setFont(new Font("幼圆", Font.BOLD, 16));
		labAdd.setForeground(Color.gray);
		panAddress.add(labAdd);

		labPrice.setSize(120, 40);
		labPrice.setLocation(10, 90);
		labPrice.setFont(new Font("幼圆", Font.BOLD, 16));
		labPrice.setForeground(Color.gray);
		panAddress.add(labPrice);

		this.labFreight.setSize(120, 40);
		this.labFreight.setLocation(100, 90);
		this.labFreight.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.labFreight.setForeground(Color.red);
		panAddress.add(this.labFreight);

		this.panAddress.setSize(500, 160);
		this.panAddress.setLocation(0, 105);
		this.panAddress.setLayout(null);
		this.panAddress.setBackground(Color.white);
		this.add(panAddress, BorderLayout.CENTER);
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
		this.btnOKK.setLocation(395, 20);
		this.btnclosee.setLocation(300, 20);
		this.panBtn.setSize(500, 50);
		this.panBtn.setLocation(0, 260);
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

	private void addListener() {
		this.btnclosee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		this.btnAddMan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new AddressManagement(userId);
				setTxtAddress();
				repainting();
			}
		});

		this.btnOKK.addActionListener(new BtnOKKListener(this.goodId, this.userId, this.sellerId, this.totlePrice, it));
	}

	private void repainting() {
		this.repaint();
	}

	private void setUI() {
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);
		this.setSize(500, 350);
		this.setLocationRelativeTo(null);
		this.setTitle("下单");
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPanBtn();
		this.setPanMsg();
		this.setPanAddress();
	}

	public static void main(String[] args) {
		new BuyIt(17, 12, "imagec\\1.jpg");
	}

}
