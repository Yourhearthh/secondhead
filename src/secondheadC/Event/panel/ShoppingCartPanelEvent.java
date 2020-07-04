package secondheadC.Event.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import s.frm.SettlementCar;
import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.UI.UserJframe;
import secondheadC.UI.panel.ShoppingCartPanel;
import secondheadC.assemby.GoodCarPanel;
import secondheadC.jdbc.ShoppingCartJdbc;

public class ShoppingCartPanelEvent {

	private ShoppingCartPanel scp;

	public ShoppingCartPanelEvent(ShoppingCartPanel shoppingCartPanel) {
		this.scp = shoppingCartPanel;

		scp.manage.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					scp.jp2.setVisible(!scp.jp2.isVisible());
					scp.del.setVisible(!scp.del.isVisible());
					scp.btn.setVisible(!scp.btn.isVisible());
					scp.clear.setVisible(!scp.clear.isVisible());
				}
			}

		});

		scp.del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					delcar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		ShoppingCartPanel.allSeleced.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				allSeleced();

			}
		});

		scp.btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Settlement();

			}
		});

		scp.clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					GoodCarPanel g;
					if(ShoppingCartPanel.allSeleced.isSelected()){
						ShoppingCartPanel.allSeleced.doClick();
					}else{
						ShoppingCartPanel.allSeleced.doClick();
						ShoppingCartPanel.allSeleced.doClick();
					}
					for (int i = 0; i < ShoppingCartPanel.gipl.size(); i++) {
						g = ShoppingCartPanel.gipl.get(i);
						if ("已售".equals(g.gi.getSalesStatus())) {
							g.seleced.setSelected(true);;
							try {
								delcar();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}

		});
	}

	protected void Settlement() {
		GoodCarPanel g;
		ArrayList<GoodsInfo> al = new ArrayList<GoodsInfo>();
		for (int i = 0; i < ShoppingCartPanel.gipl.size(); i++) {
			g = ShoppingCartPanel.gipl.get(i);
			if (g.seleced.isSelected()) {
				if ("已售".equals(g.gi.getSalesStatus())) {
					JOptionPane.showMessageDialog(null, g.gi.getTitle() + "已售，请不要选择", "提示：", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				al.add(g.gi);
			}
		}
		 String s = ShoppingCartPanel.money.getText();
		 double m=Double.valueOf(s.substring(1,s.length()));
		 new SettlementCar(al,UserJframe.userac,m);

	}

	protected void allSeleced() {
		GoodCarPanel g;
		for (int i = 0; i < ShoppingCartPanel.gipl.size(); i++) {
			g = ShoppingCartPanel.gipl.get(i);
			if (ShoppingCartPanel.allSeleced.isSelected()) {
				if (!g.seleced.isSelected()) {
					g.seleced.setSelected(true);
				}
			} else {
				if (g.seleced.isSelected()) {
					g.seleced.doClick();
				}
			}
		}
		if (ShoppingCartPanel.allSeleced.isSelected()) {
			BigDecimal num=new BigDecimal("0");
			for (int i = 0; i < ShoppingCartPanel.gipl.size(); i++) {
				GoodsInfo gi = ShoppingCartPanel.gipl.get(i).gi;
				BigDecimal num2=new BigDecimal(gi.getPrice()+gi.getFreight()+"");
				num=num.add(num2);
			}
			ShoppingCartPanel.money.setText("￥" + num);
		}

	}

	protected void delcar() throws SQLException {
		GoodCarPanel g;
		for (int i = 0; i < ShoppingCartPanel.gipl.size(); i++) {
			g = ShoppingCartPanel.gipl.get(i);
			if (g.seleced.isSelected()) {
				ShoppingCartJdbc.delcar(g.gi.getPkid(), UserJframe.userac);
				ShoppingCartPanel.goodscarpanel.remove(g);
				ShoppingCartPanel.gipl.remove(g);
				ShoppingCartPanel.goodscarpanel.updateUI();
			}
		}
		ShoppingCartPanel.money.setText("￥0");
		int num = ShoppingCartJdbc.getAllGoodsCarNum(UserJframe.userac);
		ShoppingCartPanel.count.setText("共有" + num + "件宝贝");
		ShoppingCartPanel.count.updateUI();
	}

}
