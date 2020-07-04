package secondheadC.Event.panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import s.frm.AddressManagement;
import s.frm.DialogRecharge;
import secondheadC.UI.UserJframe;
import secondheadC.UI.dialog.MyDialog;
import secondheadC.UI.panel.MyPanel;
import secondheadC.jdbc.AllGoodsJdbc;

public class MyPanelEvent {

	private MyPanel mp;

	public MyPanelEvent(MyPanel myPanel) {
		this.mp = myPanel;

		mp.jpal.get(5).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount()==1&&e.getButton()==MouseEvent.BUTTON1){
					try {
						new AddressManagement(AllGoodsJdbc.getuserpkid(UserJframe.userac));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});

		mp.jpal.get(4).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new MyDialog(AllGoodsJdbc.getuserpkid(UserJframe.userac),"我买到的");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});

		mp.jpal.get(3).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new MyDialog(AllGoodsJdbc.getuserpkid(UserJframe.userac),"我卖出的");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		mp.jpal.get(2).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new MyDialog(AllGoodsJdbc.getuserpkid(UserJframe.userac),"我发布的");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		mp.jpal.get(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new DialogRecharge(AllGoodsJdbc.getuserpkid(UserJframe.userac));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
		
		
		
		
		
		
	}

}
