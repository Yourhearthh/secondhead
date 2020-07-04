package secondheadC.assemby;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import secondheadC.Entity.VO.GoodsInfo;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {

	public GoodsInfo gi;
	public GBtn btn;

	public MyPanel(GoodsInfo gi) {
		this.gi = gi;
		
//		GridBagLayout gb = new GridBagLayout();
//		this.setLayout(gb);
		this.setSize(450, 100);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
				
		JLabel img=gi.getGoodsPicture();
		this.add(img);
		
		JTextArea jta=new JTextArea(gi.getTitle());
		jta.setLineWrap(true);
		jta.setEditable(false);
		jta.setSize(150, 90);
		this.add(jta);
			
		JLabel money=new JLabel("£¤"+String.valueOf(gi.getPrice()+gi.getFreight()));
		money.setSize(100, 60);
		money.setForeground(Color.RED);
		this.add(money);
		
		btn = new GBtn(gi,"±à¼­");
		btn.setSize(100, 50);
		this.add(btn);
		
		
		
	}

}
