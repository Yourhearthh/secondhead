package secondheadC.assemby;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import secondheadC.Entity.VO.GoodsInfo;

@SuppressWarnings("serial")
public class GoodPanel extends JPanel {
	
	public GoodsInfo gi;

	public GoodPanel(GoodsInfo gi) {
		this.gi = gi;
		
		this.setSize(200, 250);
		this.setBackground(Color.WHITE);
		GridBagLayout gb = new GridBagLayout();
		this.setLayout(gb);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		
		ImageIcon ii = new ImageIcon(gi.getGoodsPictureName());
		ii.setImage(ii.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		JLabel goodimg=new JLabel(ii);
		goodimg.setBounds(0,0,200,200);
		grid(gbc,0,0);
		gb.setConstraints(goodimg, gbc);
		this.add(goodimg);
		
		JTextArea t=new JTextArea(gi.getTitle());
		t.setBounds(0,200, 200,30);
		t.setEditable(false);
		t.setLineWrap(true);
		grid(gbc,0,20);
		gb.setConstraints(t, gbc);
		this.add(t);
		
				
		JLabel p=new JLabel("гд"+String.valueOf(gi.getPrice()+gi.getFreight()),JLabel.LEFT);
		p.setBounds(0,230, 100, 20);
		p.setForeground(Color.RED);
		grid(gbc,0,23);
		gb.setConstraints(p, gbc);
		this.add(p);
		
		JLabel l=new JLabel(gi.getLocation(),JLabel.RIGHT);
		l.setBounds(100,230, 100, 20);
		l.setForeground(Color.GRAY);
		grid(gbc,0,23);
		gb.setConstraints(l, gbc);
		this.add(l);
		
		
	}

	private void grid(GridBagConstraints gbc, int i, int j) {
		gbc.gridx=i;
		gbc.gridy=j;
		
		
	}

}
