package secondheadC.Event;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import b.Jdbc.JdbcUtils;
import b.Login.Login;
import secondheadC.UI.UserJframe;
import secondheadC.UI.dialog.AddGoodDialog;
import secondheadC.UI.dialog.UserInformationModifyDialog;
import secondheadC.UI.panel.AllGoodsPanel;
import secondheadC.UI.panel.ShoppingCartPanel;
import secondheadC.assemby.GoodPanel;
import secondheadC.jdbc.AllGoodsJdbc;
import secondheadC.jdbc.MyJdbc;
import secondheadC.thread.GetAllGoodsThread;
import secondheadC.thread.GetallGoodsCarThread;

public class UserJframeEvent {

	private UserJframe uj;
	private GoodPanel p;
	private JPanel gdp = AllGoodsPanel.goodsdatapanel;
	public static ArrayList<GoodPanel> gipl = AllGoodsPanel.giplist;

	public UserJframeEvent(UserJframe userJframe) {
		this.uj = userJframe;

		uj.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if (UserJframe.s != null) {
						UserJframe.s.close();
					}
					if (UserJframe.userac != null) {
						offline();
					}
					secondheadC.jdbc.JdbcUtils.getConnection().close();
					JdbcUtils.getConnection().close();
					System.exit(0);

				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}

		});

		UserJframe.m.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					new Login();
				}
			}
		});

		UserJframe.userimg.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (UserJframe.userac != null) {
					try {
						new UserInformationModifyDialog(UserJframe.u);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					new Login();
				}
			}

		});

		UserJframe.userac2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new UserInformationModifyDialog(UserJframe.u);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}

		});

		uj.allgoods.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserJframe.shoppingCarPanel.setVisible(false);
				UserJframe.allGoodsPanel.setVisible(true);
				UserJframe.myPanel.setVisible(false);
				findGoodsClass();

			}
		});

		uj.search.addMouseListener(new MouseAdapter() {

			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					if (uj.search.getText().equals("请输入想要搜索的商品")) {
						uj.search.setText("");
						uj.search.setForeground(Color.BLACK);
					}
				}
			}

		});

		uj.searchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserJframe.shoppingCarPanel.setVisible(false);
				UserJframe.allGoodsPanel.setVisible(true);
				UserJframe.myPanel.setVisible(false);
				searchGoods();

			}
		});

		uj.publishIdle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (UserJframe.userac != null) {
						new AddGoodDialog(UserJframe.userac);
					} else {
						JOptionPane.showMessageDialog(null, "未登录，请先登录", "提示：", JOptionPane.PLAIN_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		uj.shoppingCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (UserJframe.userac != null) {
					new GetallGoodsCarThread().start();
					UserJframe.shoppingCarPanel.setVisible(true);
					UserJframe.allGoodsPanel.setVisible(false);
					UserJframe.myPanel.setVisible(false);
					ShoppingCartPanel.allSeleced.setSelected(false);
					ShoppingCartPanel.money.setText("￥0");
				} else {
					JOptionPane.showMessageDialog(null, "未登录，请先登录", "提示：", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		uj.my.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (UserJframe.userac != null) {
					UserJframe.shoppingCarPanel.setVisible(false);
					UserJframe.allGoodsPanel.setVisible(false);
					UserJframe.myPanel.setVisible(true);

					try {
						DecimalFormat df = new DecimalFormat("");
						UserJframe.myPanel.jlal.get(0)
								.setText(df.format(Double.valueOf(MyJdbc.getBalance(UserJframe.userac))) + "元");
						UserJframe.myPanel.jlal.get(1)
								.setText(MyJdbc.getMyReleasedCount(AllGoodsJdbc.getuserpkid(UserJframe.userac)));
						UserJframe.myPanel.jlal.get(2)
								.setText(MyJdbc.getMySoldCount(AllGoodsJdbc.getuserpkid(UserJframe.userac)));
						UserJframe.myPanel.jlal.get(3)
								.setText(MyJdbc.getMyBuyCount(AllGoodsJdbc.getuserpkid(UserJframe.userac)));
						UserJframe.myPanel.jlal.get(4).setText(
								MyJdbc.getMyReceivingAddressCount(AllGoodsJdbc.getuserpkid(UserJframe.userac)));

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "未登录，请先登录", "提示：", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		UserJframe.usernn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					try {
						new UserInformationModifyDialog(UserJframe.u);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}

	protected void searchGoods() {
		if (uj.search.getText().equals("请输入想要搜索的商品") || uj.search.getText().length() == 0) {
			new GetAllGoodsThread().start();
			return;
		}
		String str = uj.search.getText();
		uj.allgoods.setSelectedIndex(0);
		gdp.removeAll();
		for (int i = 0; i < gipl.size(); i++) {
			p = gipl.get(i);
			if ((p.gi.getTitle().indexOf(str) != -1) || (p.gi.getSellReason().indexOf(str) != -1)) {
				gdp.add(p);
			}
		}
		gdp.updateUI();
	}

	protected void findGoodsClass() {
		String str = String.valueOf(uj.allgoods.getSelectedItem());
		gdp.removeAll();
		if (!str.equals("所有商品")) {
			for (int i = 0; i < gipl.size(); i++) {
				p = gipl.get(i);
				if (p.gi.getClassificationName().equals(str)) {
					gdp.add(p);
				}
			}
		} else {
			for (int i = 0; i < gipl.size(); i++) {
				p = gipl.get(i);
				gdp.add(p);
			}
		}
		gdp.updateUI();
	}

	@SuppressWarnings("resource")
	public static void offline() throws UnknownHostException, SQLException {
		try {
			Socket socket = new Socket(UserJframe.ip, 888);
			PrintStream ps = new PrintStream(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ps.println(UserJframe.userac);
			if (br.readLine().equals("ok")) {
				secondheadC.jdbc.JdbcOperate.OffOnline(UserJframe.userac);
			}
		} catch (IOException e) {
			System.out.println("下线服务器未在线");
		}

	}

}
