package secondheadC.UI.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.assemby.GoodPanel;
import secondheadC.thread.GetAllGoodsThread;

@SuppressWarnings("serial")
public class AllGoodsPanel extends JPanel {
	public static ArrayList<GoodsInfo> gi = new ArrayList<GoodsInfo>();
	public static ArrayList<GoodPanel> giplist = new ArrayList<GoodPanel>();
	public static JPanel goodsdatapanel;

	public AllGoodsPanel() {
		this.setBounds(0, 0, 883, 479);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));

		setpanel();

	}

	private void setpanel() {
		goodsdatapanel = new JPanel();
		goodsdatapanel.setBounds(0, 0, 887, 479);
		goodsdatapanel.setBackground(Color.WHITE);

		JLabel jl = new JLabel("暂无商品数据", JLabel.CENTER);
		jl.setFont(new Font("宋体", Font.BOLD, 40));
		jl.setForeground(Color.RED);
		goodsdatapanel.add(jl);

		goodsdatapanel.setLayout(new GridLayout(0,4));

		JScrollPane jsp = new JScrollPane(goodsdatapanel);
		jsp.setBounds(0, 0, 887, 479);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(20);

		new GetAllGoodsThread().start();

		this.add(jsp);

	}

}
