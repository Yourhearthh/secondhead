package secondheadC.UI.dialog;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.assemby.GBtn;
import secondheadC.assemby.MyPanel;
import secondheadC.jdbc.MyJdbc;

@SuppressWarnings("serial")
public class MyDialog extends JDialog {

	public MyDialog(int getuserpkid, String str) throws SQLException {
		super((JFrame) null, str, true);
		ArrayList<GoodsInfo> al = null;
		
		if ("我买到的".equals(str)) {
			al=MyJdbc.getMyBuyGoods(getuserpkid);
		}
		if ("我卖出的".equals(str)) {
			al=MyJdbc.getMySoldGoods(getuserpkid);
		}
		if ("我发布的".equals(str)) {
			al=MyJdbc.getMyReleasedGoods(getuserpkid);
		}
		this.setSize(520, 600);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("imagec/LOGO.jpg"));
		this.setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		MyPanel gcp;

		JPanel jp = new JPanel();
		jp.setBackground(Color.WHITE);
		jp.setBounds(0, 0, 450, 600);
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

		JScrollPane jsp = new JScrollPane(jp);
		jsp.setBounds(0, 0, 500, 600);
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		this.add(jsp);

		for (int i = 0; i < al.size(); i++) {
			gcp = new MyPanel(al.get(i));
			if(gcp.gi.getSalesStatus()!=null&&gcp.gi.getSalesStatus().equals("未售")){
				gcp.btn.setVisible(true);
				gcp.btn.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if(e.getClickCount()==1&&e.getButton()==MouseEvent.BUTTON1){
							GBtn g=(GBtn)e.getComponent();
							try {
								new ModifyingCommodityInformationDialog(g.gi);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
						}
					}
					
				});
				
				
				
				
				
			}
			jp.add(gcp);

		}

		this.setVisible(true);
	}

}
