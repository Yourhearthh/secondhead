package s.jpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import secondheadC.Entity.VO.GoodsInfo;


public class PanGoodInfo extends JPanel {
	private static final long serialVersionUID = 1L;

	GoodsInfo goodsInfo;

	public PanGoodInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
		this.setPanGoodInfo();
	}

	public PanGoodInfo() {
		// this.goodsInfo = goodsInfo;
		this.setPanGoodInfo();
	}

	private void setPanGoodInfo() {
		// JLabel labTitle = new JLabel("123");
		// labTitle.setSize(300, 40);
		// labTitle.setLocation(120, 0);
		// labTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		// this.add(labTitle);
		//
		// JLabel labPrice = new JLabel("¥" + 1111111);
		// labPrice.setSize(120, 40);
		// labPrice.setLocation(120, 60);
		// labPrice.setFont(new Font("微软雅黑", Font.BOLD, 20));
		// labPrice.setForeground(Color.red);
		// this.add(labPrice);
		//
		// JLabel labFreight = new JLabel("¥" + 11111111);
		// labFreight.setSize(120, 40);
		// labFreight.setLocation(250, 60);
		// labFreight.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		// labFreight.setForeground(Color.gray);
		// this.add(labFreight);
		//
		// ImageIcon image = new ImageIcon("imagec\\1.jpg");
		// image.setImage(image.getImage().getScaledInstance(90, 90,
		// Image.SCALE_DEFAULT));
		// JLabel labImg = new JLabel(image);
		// labImg.setSize(90, 90);
		// labImg.setLocation(15, 5);
		// this.add(labImg);

		JLabel labTitle = new JLabel(goodsInfo.getTitle());
		labTitle.setSize(300, 40);
		labTitle.setLocation(120, 0);
		labTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		this.add(labTitle);

		JLabel labPrice = new JLabel("¥原价：" + String.valueOf(goodsInfo.getPrice()));
		labPrice.setSize(150, 40);
		labPrice.setLocation(120, 60);
		labPrice.setFont(new Font("微软雅黑", Font.BOLD, 20));
		labPrice.setForeground(Color.red);
		this.add(labPrice);

		JLabel labFreight = new JLabel("¥运费：" + goodsInfo.getFreight());
		labFreight.setSize(150, 40);
		labFreight.setLocation(280, 60);
		labFreight.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		labFreight.setForeground(Color.gray);
		this.add(labFreight);

		ImageIcon image = new ImageIcon(goodsInfo.getGoodsPictureName());
		image.setImage(image.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JLabel labImg = new JLabel(image);
		labImg.setSize(90, 90);
		labImg.setLocation(15, 5);
		this.add(labImg);

		this.setLayout(null);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		this.setPreferredSize(new Dimension(420, 100));
		this.setBackground(Color.white);
	}
}
