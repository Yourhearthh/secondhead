package secondheadC.UI.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import secondheadC.Event.panel.MyPanelEvent;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {

	public ArrayList<JPanel> jpal;
	public ArrayList<JLabel> jlal;

	public MyPanel() {
		this.setBounds(0, 0, 883, 479);
		// this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));

		setdata();

		 new MyPanelEvent(this);

	}

	private void setdata() {
		JPanel toppanel = new JPanel();
		toppanel.setBackground(Color.WHITE);
		toppanel.setBounds(100, 0, 660, 476);
		toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.Y_AXIS));
		this.add(toppanel);
		
		
		ImageIcon ii[]=new ImageIcon[6];
		ii[0]=new ImageIcon("imagec/�ҵ�red.png");
		ii[0].setImage(ii[0].getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
		ii[1]=new ImageIcon("imagec/���.png");
		ii[1].setImage(ii[1].getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ii[2]=new ImageIcon("imagec/����.png");
		ii[2].setImage(ii[2].getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ii[3]=new ImageIcon("imagec/��.png");
		ii[3].setImage(ii[3].getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ii[4]=new ImageIcon("imagec/��.png");
		ii[4].setImage(ii[4].getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ii[5]=new ImageIcon("imagec/λ��.png");
		ii[5].setImage(ii[5].getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		
		String str[][] ={ { "�ҵ�", "���", "�ҷ�����", "��������", "���򵽵�","�ҵ��ջ���ַ" },{"0.00Ԫ","0","0","0","0"}};
		jpal = new ArrayList<JPanel>();
		jlal = new ArrayList<JLabel>();

		JPanel jp;
		JLabel jl;
		for (int i = 0; i < 6; i++) {
			jp = new JPanel();
			jp.setSize(660, 50);
			jp.setBackground(Color.WHITE);
			jp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			toppanel.add(jp);

			jl = new JLabel(str[0][i]);
			jl.setFont(new Font("΢���ź�", Font.BOLD, 20));
			jl.setIcon(ii[i]);
			
			if (i == 0) {
				jl.setForeground(Color.RED);
			}		
			
			jp.add(jl);
			
			if(i!=0){
				jp.setLayout(new GridLayout(0,2));
				jl = new JLabel(str[1][i-1],JLabel.RIGHT);
				jl.setFont(new Font("΢���ź�", Font.BOLD, 20));
								
				jp.add(jl);
				jlal.add(jl);
			}
			
			jpal.add(jp);
		}
		
	}

}
