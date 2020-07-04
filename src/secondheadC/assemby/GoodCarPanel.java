package secondheadC.assemby;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import secondheadC.Entity.VO.GoodsInfo;

@SuppressWarnings("serial")
public class GoodCarPanel extends JPanel {

		public GoodsInfo gi;
		public JRadioButton seleced;

		public GoodCarPanel(GoodsInfo gi) {
			this.gi = gi;
			
			GridBagLayout gb = new GridBagLayout();
			this.setLayout(gb);
			this.setSize(660, 150);
			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			
			seleced = new JRadioButton();
			seleced.setBackground(Color.WHITE);
			seleced.setSize(50, 150);
			this.add(seleced);
			
			JLabel img=gi.getGoodsPicture();
			this.add(img);
			
			JTextArea jta=new JTextArea(gi.getTitle());
			jta.setLineWrap(true);
			jta.setEditable(false);
			jta.setSize(300, 90);
			this.add(jta);
			
//			BigDecimal num1=new BigDecimal(String.valueOf(gi.getPrice()));
//			BigDecimal num2=new BigDecimal(String.valueOf(gi.getFreight()));
			
			
			JLabel money=new JLabel("гд"+String.valueOf(gi.getPrice()+gi.getFreight()));
			money.setSize(160, 60);
			money.setForeground(Color.RED);
			this.add(money);
			
			
			
		}
	
}
