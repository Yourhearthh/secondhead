package secondheadC.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import secondheadC.Entity.VO.UserInfo;
import secondheadC.Event.UserJframeEvent;
import secondheadC.UI.panel.AllGoodsPanel;
import secondheadC.UI.panel.MyPanel;
import secondheadC.UI.panel.ShoppingCartPanel;
import secondheadC.jdbc.JdbcOperate;
import secondheadC.thread.recivedHaveChatWindows;

@SuppressWarnings("serial")
public class UserJframe extends JFrame {

	private static boolean boxautoVal;
	private static String userid;
	public static UserInfo u;
	public static String userac,ip="127.0.0.1";
	public static ShoppingCartPanel shoppingCarPanel;
	public static MyPanel myPanel;
	public static Socket s;
	public static AllGoodsPanel allGoodsPanel;
	public static JLabel usernn;
	public static JLabel userac2;
	public static JLabel userimg;
	public static JTextArea m;
	public static ArrayList<String> chatwindowlist = new ArrayList<String>();

	public JButton searchbtn;
	public JTextField search;
	public JButton publishIdle;
	public JComboBox<String> allgoods;
	public JButton shoppingCart;
	public JButton my;
	private JPanel datapanel;

	public static void main(String[] args) {
		
		try {
			duqu();
			String str = autologin();
			if (str != null) {
				try {
					s = new Socket(UserJframe.ip, 666);
					PrintStream ps = new PrintStream(s.getOutputStream());
					ps.println(str);
				} catch (Exception e) {
					System.out.println("上线服务器未在线");
				}
				secondheadC.jdbc.JdbcOperate.Online(UserJframe.userac);
			}
			new UserJframe(str);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public UserJframe(String userac) throws SQLException, IOException {
		new recivedHaveChatWindows().start();
		UserJframe.userac = userac;
		if (userac != null) {
			u = JdbcOperate.getUserInfo(userac);
		}

		this.setSize(1090, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("二手交易");
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("imagec/LOGO.jpg"));

		try

		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		settoppanel();

		setMenu();

		setAllGoodsPanel();

		new UserJframeEvent(this);

		this.setResizable(false);
		
		this.setVisible(true);

		setdatapanel();

	}

	private void setdatapanel() {

		shoppingCarPanel = new ShoppingCartPanel();
		shoppingCarPanel.setVisible(false);

		myPanel = new MyPanel();
		myPanel.setVisible(false);

		datapanel.add(shoppingCarPanel);
		datapanel.add(myPanel);
	}

	private void setAllGoodsPanel() {
		datapanel = new JPanel();
		datapanel.setBounds(200, 82, 900, 512);
		datapanel.setLayout(null);
		// datapanel.setBackground(Color.WHITE);

		allGoodsPanel = new AllGoodsPanel();
		datapanel.add(allGoodsPanel);

		this.add(datapanel);
	}

	private void settoppanel() throws SQLException {
		JPanel toppanel = new JPanel();
		toppanel.setBounds(0, 0, 1083, 82);
		toppanel.setLayout(null);
		toppanel.setBackground(Color.WHITE);
		toppanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		usernn = new JLabel();
		usernn.setBounds(80, 21, 80, 20);
		usernn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		usernn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
		toppanel.add(usernn);

		userac2 = new JLabel();
		userac2.setBounds(80, 51, 80, 20);
		userac2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		userac2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GREEN));
		toppanel.add(userac2);

		if (userac != null) {
			usernn.setText(u.getNickname());
			userac2.setText(u.getAccountNumber());
			userimg = u.getHeadPortrait();

		} else {

			ImageIcon hsimg = new ImageIcon("imagec/hs.jpg");
			hsimg.setImage(hsimg.getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));

			userimg = new JLabel(hsimg);
		}
		m = new JTextArea("请点击此处登录账号");
		m.setLineWrap(true);
		m.setEditable(false);
		m.setBounds(80, 31, 80, 40);
		m.setForeground(Color.RED);
		m.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		m.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		toppanel.add(m);
		if (userac != null) {
			m.setVisible(false);
		} else {
			usernn.setVisible(false);
			userac2.setVisible(false);
		}

		userimg.setBounds(15, 1, 60, 80);
		userimg.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.CYAN));
		toppanel.add(userimg);

		search = new JTextField("请输入想要搜索的商品");
		search.setForeground(Color.GRAY);
		search.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		search.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		search.setBounds(200, 21, 200, 40);
		toppanel.add(search);

		ImageIcon searchImg = new ImageIcon("imagec/放大镜.jpg");
		searchImg.setImage(searchImg.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		searchbtn = new JButton();
		searchbtn.setIcon(searchImg);
		searchbtn.setToolTipText("搜索");
		searchbtn.setOpaque(true);
		searchbtn.setBounds(400, 21, 40, 40);
		toppanel.add(searchbtn);

		publishIdle = new JButton("发布");
		publishIdle.setToolTipText("发布您的闲置物品");
		publishIdle.setBounds(900, 21, 100, 40);
		toppanel.add(publishIdle);

		this.add(toppanel);
	}

	private void setMenu() {
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 82, 200, 479);
		menuPanel.setLayout(null);
		menuPanel.setBackground(Color.WHITE);

		allgoods = new JComboBox<String>();
		allgoods.addItem("所有商品");
		allgoods.addItem("衣");
		allgoods.addItem("食");
		allgoods.addItem("住");
		allgoods.addItem("行");
		allgoods.addItem("其它");
		allgoods.setBounds(5, 10, 190, 50);
		menuPanel.add(allgoods);

		ImageIcon carimg = new ImageIcon("imagec/购物车.png");
		carimg.setImage(carimg.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		shoppingCart = new JButton("购物车");
		shoppingCart.setIcon(carimg);
		shoppingCart.setBounds(5, 70, 190, 50);
		menuPanel.add(shoppingCart);

		ImageIcon myimg = new ImageIcon("imagec/我的.png");
		myimg.setImage(myimg.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		my = new JButton("我的");
		my.setIcon(myimg);
		my.setBounds(5, 130, 190, 50);
		menuPanel.add(my);

		menuPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));

		this.add(menuPanel);
	}

	// 自动登录
	public static String autologin() {
		// System.out.println(txtuserVal+"\r\n"+txtpassVal+"\r\n"+boxautoVal);
		if (boxautoVal) {
			return UserJframe.userid;
		}
		return null;
	}

	/**
	 * 读取文本保存的密码
	 * @return 
	 */
	private static boolean duqu() {
		try {
			//账号密码
			File file = new File("password\\pass.txt");
					FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			String str = bf.readLine();
			if(str!=null){
				String line[]=str.split("\r\n");
				UserJframe.userid=line[0];
			}
			

			bf.close();
			fr.close();

			// 复选框读取状态
			File filebox = new File("password\\box.txt");
			FileReader frbox = new FileReader(filebox);
			BufferedReader bfbox = new BufferedReader(frbox);
			String strBox = bfbox.readLine();
			while (strBox != null) {
				String line[] = strBox.split("=");
				if ("boxauto".equals(line[0])) {
					boxautoVal = Boolean.valueOf(line[1]);

				}
				strBox = bfbox.readLine();
			}
			frbox.close();
			bfbox.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boxautoVal;
	}

	
}
