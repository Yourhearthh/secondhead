package secondheadC.thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import s.frm.GoodsDetailedInformation;
import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.UI.UserJframe;
import secondheadC.UI.panel.ShoppingCartPanel;
import secondheadC.assemby.GoodCarPanel;
import secondheadC.jdbc.AllGoodsJdbc;
import secondheadC.jdbc.ShoppingCartJdbc;

public class GetallGoodsCarThread extends Thread {
	private JPanel gcp = ShoppingCartPanel.goodscarpanel;
	private ArrayList<GoodsInfo> goodsi = ShoppingCartPanel.gi;
	public static ArrayList<GoodCarPanel> gipl = ShoppingCartPanel.gipl;
	JLabel m = ShoppingCartPanel.money;
	private double sum;

	@Override
	public void run() {
		int num = 0;

		try {
			num = ShoppingCartJdbc.getAllGoodsCarNum(UserJframe.userac);
			ShoppingCartJdbc.getAllGoodsCarData(UserJframe.userac);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ShoppingCartPanel.count.setText("¹²ÓÐ" + num + "¼þ±¦±´");
		ShoppingCartPanel.count.updateUI();

		gcp.removeAll();

		gipl.removeAll(gipl);

		GoodCarPanel gp;
		for (int i = 0; i < goodsi.size(); i++) {
			gp = new GoodCarPanel(goodsi.get(i));
			gcp.add(gp);
			gipl.add(gp);
		}

		gcp.updateUI();

		setGoodCarPanelEvent();

		// UserManagementPanel.search.doClick();
	}

	private void setGoodCarPanelEvent() {
		sum = 0;
		BigDecimal num = new BigDecimal("0");
		for (int i = 0; i < gipl.size(); i++) {
			GoodsInfo g = gipl.get(i).gi;
			BigDecimal num2 = new BigDecimal(g.getPrice() + g.getFreight() + "");
			num = num.add(num2);
		}
		sum = Double.valueOf(num + "");

		for (int i = 0; i < gipl.size(); i++) {
			final GoodCarPanel p = gipl.get(i);
			p.seleced.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String s;
					if (p.seleced.isSelected()) {
						BigDecimal num1 = new BigDecimal(m.getText().substring(1, m.getText().length()));
						BigDecimal num2 = new BigDecimal(p.gi.getPrice() + p.gi.getFreight() + "");
						s = num1.add(num2) + "";
						m.setText("£¤" + s);
					} else {
						BigDecimal num1 = new BigDecimal(m.getText().substring(1, m.getText().length()));
						BigDecimal num2 = new BigDecimal(p.gi.getPrice() + p.gi.getFreight() + "");
						s = num1.subtract(num2) + "";
						m.setText("£¤" + s);
					}
					if (Double.valueOf(s) == sum) {
						ShoppingCartPanel.allSeleced.setSelected(true);
					} else {
						ShoppingCartPanel.allSeleced.setSelected(false);
					}

				}
			});

			p.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					try {
						new GoodsDetailedInformation(p.gi.getPkid(),AllGoodsJdbc.getuserpkid(UserJframe.userac));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			});

		}

	}

}
