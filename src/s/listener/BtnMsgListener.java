package s.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import secondheadC.UI.UserJframe;
import secondheadC.UI.Chat.GUIChatC;
import secondheadC.jdbc.JdbcUtils;

public class BtnMsgListener implements ActionListener {

	private int userpkid;
	private String userac;
	private String nickName;

	public BtnMsgListener(int userpkid, String userac, String nickName) {
		this.userpkid = userpkid;
		this.userac = userac;
		this.nickName = nickName;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(UserJframe.userac==null){
			JOptionPane.showMessageDialog(null, "请先登录或注册！");
			return;
		}
		if(isonline(userpkid))	{
			JOptionPane.showMessageDialog(null, "对方不在线，请稍后联系");
			return;
		}else{
			UserJframe.chatwindowlist.add(userac);
			new GUIChatC(userac,nickName);
			
			
			
			
		}
		
	}

	private boolean isonline(int userpkid2) {
		String sql = "SELECT OnlineStatus FROM userinfo WHERE pkid=?";
		ArrayList<Object> al=new ArrayList<Object>();
		al.add(userpkid2);
		try {
			PreparedStatement ps = JdbcUtils.queryrs(sql, al);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String str = rs.getString(1);
			rs.close();
			ps.close();
			System.out.println(str);
			if(str.equals("在线")){
				return false;
			}
			if(str.equals("不在线")){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	

}
