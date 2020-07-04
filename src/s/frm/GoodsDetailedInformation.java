package s.frm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import s.Util.DbUtil;
import s.Util.ImgThread;
import s.listener.AddToShoppingCar;
import s.listener.BtnBuyListener;
import s.listener.BtnMsgListener;



public class GoodsDetailedInformation extends JDialog {

	private JPanel panSeller = new JPanel();
	private JScrollPane jspExhibition = new JScrollPane();
	private JPanel panBtn = new JPanel();
	private int id;
	private DbUtil dbUtil;
	private StringBuffer sb = new StringBuffer();
	private String url = "imagec" + File.separator;
	private String tranUrl = null;
	private JButton btnBuy = new JButton("立即购买");
	private JButton btnAddCar = new JButton("加入购物车");
	private JButton btnMsg = new JButton("联系卖家");
	private Integer userId;
	private int userpkid;
	private String userac;
	private String nickName;

	// 传入商品ID
	public GoodsDetailedInformation(int goodId, Integer userId) {
		this.id = goodId;
		this.userId = userId;
		this.setUI();
		this.addListener();
		this.setVisible(true);
	}

	private void setPanSeller() {
		JLabel labHeadPortrait = new JLabel();
		JLabel labNickName = new JLabel("昵称");
		JLabel labDateAndAdd = new JLabel("2019/08/14 发布于长沙市");
		try {
			dbUtil = DbUtil.getInstance();
			sb.setLength(0);
			sb.append(" SELECT HeadPortraitData");
			sb.append(" ,Nickname,name,ModifyTime,HeadPortraitName,userinfo.pkid,userinfo.AccountNumber");
			sb.append(" FROM goodsinfo");
			sb.append(" INNER JOIN userinfo");
			sb.append(" ON goodsinfo.fk_user_id=userinfo.pkid");
			sb.append(" INNER JOIN china");
			sb.append(" ON ResidentCity=id");
			sb.append(" WHERE goodsinfo.pkid=" + this.id);
			ResultSet set = dbUtil.doQuery(sb.toString());
			while (set.next()) {
				new ImgThread(set.getBytes(1), labHeadPortrait, this.url + set.getString(5), 50, 50).start();
				nickName=set.getString(2);
				labNickName.setText(nickName);
				labDateAndAdd.setText(set.getString(4) + " 发布于" + set.getString(3));
				userpkid=set.getInt(6);
				userac=set.getString(7);
			}
			set.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		labHeadPortrait.setSize(55, 55);
		labHeadPortrait.setLocation(15, 5);
		this.panSeller.add(labHeadPortrait);

		labNickName.setSize(150, 40);
		labNickName.setLocation(80, 1);
		labNickName.setFont(new Font("微软雅黑", Font.BOLD, 20));
		this.panSeller.add(labNickName);

		labDateAndAdd.setSize(200, 60);
		labDateAndAdd.setLocation(80, 21);
		labDateAndAdd.setForeground(Color.gray);
		labDateAndAdd.setFont(new Font("幼圆", Font.PLAIN, 15));
		this.panSeller.add(labDateAndAdd);

		this.panSeller.setLayout(null);
		this.panSeller.setSize(500, 60);
		this.panSeller.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		this.panSeller.setBackground(Color.white);
		this.add(panSeller);
	}

	private void setEspExhibition() {
		JPanel panMsg = new JPanel();
		panMsg.setLayout(new BoxLayout(panMsg, BoxLayout.Y_AXIS));
		panMsg.setBackground(Color.white);

		ArrayList<JLabel> Lablist = new ArrayList<JLabel>();
		JTextArea SellMsg = new JTextArea();
		SellMsg.setSize(360, 145);
		SellMsg.setLocation(18, 95);
		SellMsg.setLineWrap(true);
		SellMsg.setEditable(false);
		SellMsg.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		try {
			dbUtil = DbUtil.getInstance();
			sb.setLength(0);
			sb.append(" SELECT");
			sb.append("  Price,Initialprice");
			sb.append(" ,Freight,Title");
			sb.append(" ,SellReason");
			sb.append(" FROM");
			sb.append(" goodsinfo");
			sb.append(" WHERE");
			sb.append(" pkid=" + this.id);
			ResultSet set = dbUtil.doQuery(sb.toString());
			while (set.next()) {
				Lablist.add(new JLabel("¥" + set.getString(1)));
				Lablist.add(new JLabel("原价:¥" + set.getString(2)));
				Lablist.add(new JLabel("邮费:¥" + set.getString(3)));
				SellMsg.setText(set.getString(4) + "\n" + set.getString(5));
			}
			set.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		Lablist.get(0).setSize(120, 40);
		Lablist.get(0).setLocation(20, 60);
		Lablist.get(0).setFont(new Font("微软雅黑", Font.BOLD, 20));
		Lablist.get(0).setForeground(Color.red);
		this.add(Lablist.get(0));

		Lablist.get(1).setSize(120, 40);
		Lablist.get(1).setLocation(135, 60);
		Lablist.get(1).setFont(new Font("微软雅黑", Font.PLAIN, 15));
		Lablist.get(1).setForeground(Color.gray);
		this.add(Lablist.get(1));

		Lablist.get(2).setSize(110, 40);
		Lablist.get(2).setLocation(270, 60);
		Lablist.get(2).setFont(new Font("微软雅黑", Font.PLAIN, 13));
		Lablist.get(2).setForeground(Color.gray);
		this.add(Lablist.get(2));
		this.add(SellMsg);

		try {
			dbUtil = DbUtil.getInstance();
			sb.setLength(0);
			sb.append(" SELECT");
			sb.append(" GoodsPictureData,GoodsPictureName");
			sb.append(" FROM");
			sb.append(" goodspictureinfo");
			sb.append(" WHERE");
			sb.append(" GoodsId=" + this.id);
			ResultSet set = dbUtil.doQuery(sb.toString());
			while (set.next()) {
				Lablist.add(new JLabel());
				if (tranUrl == null) {
					tranUrl = this.url + set.getString(2);
				}
				new ImgThread(set.getBytes(1), Lablist.get(Lablist.size() - 1), this.url + set.getString(2), 360, 300)
						.start();
				Lablist.get(Lablist.size() - 1).setPreferredSize(new Dimension(360, 300));
				panMsg.add(Lablist.get(Lablist.size() - 1));
			}
			set.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.jspExhibition.getVerticalScrollBar().setUnitIncrement(20);
		this.jspExhibition.getViewport().add(panMsg);
		this.jspExhibition.setBorder(new MatteBorder(1, 0, 1, 0, Color.lightGray));
		this.jspExhibition.setSize(380, 280);
		this.jspExhibition.setLocation(10, 240);
		this.add(jspExhibition);

	}

	private void setPanBtn() {
		this.panBtn.add(this.setButton(this.btnBuy));
		this.panBtn.add(this.setButton(this.btnAddCar));
		this.panBtn.add(this.setButton(this.btnMsg));
		this.btnBuy.setLocation(268, 25);
		this.btnAddCar.setLocation(134, 25);
		this.btnAddCar.setSize(120, 40);
		this.btnMsg.setLocation(8, 25);
		this.panBtn.setSize(430, 80);
		this.panBtn.setLocation(0, 500);
		this.panBtn.setLayout(null);
		this.panBtn.setBackground(Color.white);
		this.add(panBtn);
	}

	private JButton setButton(JButton btn) {
		Font font = new Font("幼圆", Font.BOLD, 15);
		btn.setSize(110, 40);
		btn.setBackground(Color.white);
		btn.setFocusable(false);
		btn.setFont(font);
		return btn;
	}

	private void addListener() {
		this.btnAddCar.addActionListener(new AddToShoppingCar(this.id, this.userId));
		this.btnBuy.addActionListener(new BtnBuyListener(this.id, this.userId, this.tranUrl));
		// FIXME 发送消息
		 this.btnMsg.addActionListener(new BtnMsgListener(userpkid,userac,nickName));
	}

	private void setUI() {
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);
		this.setSize(400, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("商品详情");
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPanSeller();
		this.setPanBtn();
		this.setEspExhibition();
	}

	public static void main(String[] args) {
		new GoodsDetailedInformation(15, 1);
	}

}
