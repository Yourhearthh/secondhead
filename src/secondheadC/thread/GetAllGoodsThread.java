package secondheadC.thread;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import s.frm.GoodsDetailedInformation;
import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.UI.UserJframe;
import secondheadC.UI.panel.AllGoodsPanel;
import secondheadC.assemby.GoodPanel;
import secondheadC.jdbc.AllGoodsJdbc;

public class GetAllGoodsThread extends Thread {
	ArrayList<GoodsInfo> gi = AllGoodsPanel.gi;
	ArrayList<GoodPanel> giplist = AllGoodsPanel.giplist;
	JPanel gdp = AllGoodsPanel.goodsdatapanel;

	@Override
	public void run() {

		try {
			sleep(800);
			AllGoodsJdbc.getAllGoodsData();
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}

		gdp.removeAll();

		giplist.removeAll(giplist);
		GoodPanel g;
		for (int i = 0; i < gi.size(); i++) {
			g = new GoodPanel(gi.get(i));
			gdp.add(g);
			giplist.add(g);
		}

		gdp.updateUI();

		setolLabelEvent();

	}

	private void setolLabelEvent() {
		for (int i = 0; i < giplist.size(); i++) {
			final GoodPanel g = giplist.get(i);

			g.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(e.getClickCount()==1&&e.getButton()==MouseEvent.BUTTON1){
						try {
							Integer pkid=null;
							if(UserJframe.userac!=null){
							pkid=AllGoodsJdbc.getuserpkid(UserJframe.userac);
							}
							new GoodsDetailedInformation(g.gi.getPkid(),pkid);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			});

		}

	}

}
