package secondheadC.UI.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.Event.panel.ShoppingCartPanelEvent;
import secondheadC.assemby.GoodCarPanel;

@SuppressWarnings("serial")
public class ShoppingCartPanel extends JPanel {

	public static JPanel goodscarpanel;
	public static ArrayList<GoodsInfo> gi = new ArrayList<GoodsInfo>();
	public static ArrayList<GoodCarPanel> gipl =new ArrayList<GoodCarPanel>();
	public JButton btn;
	public static JLabel money;
	public static JRadioButton allSeleced;
	public static JLabel count;
	public JLabel manage;
	public JPanel jp2;
	public JButton del;
	public JLabel clear;

	public ShoppingCartPanel() {
		this.setBounds(0, 0, 883, 479);
//		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		
		setdata();
		
		new ShoppingCartPanelEvent(this);
	}

	private void setdata() {

		JPanel jp1=new JPanel();
		jp1.setBackground(Color.WHITE);
		jp1.setLayout(null);
		jp1.setBounds(100, 0, 660, 55);
		this.add(jp1);
		
		ImageIcon carimg=new ImageIcon("imagec/购物车red.png");
		carimg.setImage(carimg.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
		
		
		JLabel title=new JLabel("购物车");
		title.setIcon(carimg);
		title.setForeground(Color.RED);
		title.setFont(new Font("微软雅黑",Font.BOLD,18));
		title.setBounds(10, 5, 150, 30);
		jp1.add(title);
		
		
		ImageIcon manageimg=new ImageIcon("imagec/管理.png");
		manageimg.setImage(manageimg.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
		
		manage = new JLabel("管理");
		manage.setIcon(manageimg);
		manage.setFont(new Font("微软雅黑",Font.BOLD,18));
		manage.setBounds(580, 5, 80, 30);
		jp1.add(manage);
		
		count = new JLabel("共有0件宝贝");
		count.setBounds(10, 35, 80, 20);
		jp1.add(count);
		
		JPanel jp=new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setBounds(100, 430, 660, 48);
		this.add(jp);
		
		allSeleced = new JRadioButton("全选");
		allSeleced.setBackground(Color.WHITE);
		allSeleced.setForeground(Color.GRAY);
		allSeleced.setBounds(10, 5, 50, 30);
		jp.add(allSeleced);

		jp2 = new JPanel();
		jp2.setLayout(null);
		jp2.setBackground(Color.WHITE);
		jp2.setBounds(420, 5, 140, 30);
		jp.add(jp2);
		
		JLabel hj=new JLabel("合计：",JLabel.RIGHT);
		hj.setBounds(0, 0, 50, 30);
		jp2.add(hj);
		
		money = new JLabel("￥0",JLabel.LEFT);
		money.setBounds(50, 0, 90, 30);
		money.setForeground(Color.RED);
		jp2.add(money);
		
		
		
		btn = new JButton("结算");
		btn.setBounds(570, 5, 80, 30);
		jp.add(btn);
		
		del = new JButton("删除");
		del.setBounds(570, 5, 80, 30);
		del.setVisible(false);
		jp.add(del);
		
		clear = new JLabel("清理失效");
		clear.setBounds(510, 5, 50, 30);
		clear.setVisible(false);
		jp.add(clear);
		
		goodscarpanel = new JPanel();
		goodscarpanel.setLayout(new BoxLayout(goodscarpanel, BoxLayout.Y_AXIS));
		
		
		JScrollPane jsp=new JScrollPane(goodscarpanel);
		jsp.setBounds(100, 55, 660, 375);
		jsp.getViewport().setBackground(Color.WHITE);
		jsp.getVerticalScrollBar().setUnitIncrement(20);

		this.add(jsp);
		
		
		
		
	}
}
