package secondheadC.thread;

import java.awt.Image;

import javax.swing.ImageIcon;

import secondheadC.UI.UserJframe;
import secondheadC.jdbc.JdbcOperate;

public class GetUserDataThread extends Thread {
	
	@Override
	public void run() {

		if (UserJframe.userac != null) {
			try {
				UserJframe.u = JdbcOperate.getUserInfo(UserJframe.userac);
			} catch (Exception e) {
				System.out.println("加载用户信息中");
			}
			ImageIcon hsimg = new ImageIcon(UserJframe.u.getHeadPortraitPath());
			hsimg.setImage(hsimg.getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));

			UserJframe.userimg.setIcon(hsimg);

			UserJframe.usernn.setText(UserJframe.u.getNickname());
			
			UserJframe.userac2.setText(UserJframe.u.getAccountNumber());

			UserJframe.usernn.setVisible(true);
			UserJframe.userac2.setVisible(true);
			UserJframe.m.setVisible(false);
		} else {

			ImageIcon hsimg = new ImageIcon("imagec/hs.jpg");
			hsimg.setImage(hsimg.getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));

			UserJframe.userimg.setIcon(hsimg);
			UserJframe.usernn.setVisible(false);
			UserJframe.userac2.setVisible(false);
			UserJframe.m.setVisible(true);

		}
		
		UserJframe.shoppingCarPanel.setVisible(false);
		UserJframe.allGoodsPanel.setVisible(true);
		UserJframe.myPanel.setVisible(false);
		
		
	}

}
