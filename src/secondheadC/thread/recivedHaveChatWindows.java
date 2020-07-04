package secondheadC.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import secondheadC.UI.UserJframe;
import secondheadC.UI.Chat.GUIChatC;
import secondheadC.jdbc.JdbcOperate;

public class recivedHaveChatWindows extends Thread {

	@SuppressWarnings({ "resource", "static-access" })
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(7788);
			Socket s;
			while (true) {
				s = ss.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintStream ps = new PrintStream(s.getOutputStream());
				if (br.readLine().equals("HaveChatWindow")) {
					String goalac = br.readLine();
					System.out.println(goalac);
					if (!haveChatWindow(goalac)) {
						System.out.println("窗口不存在");
						System.out.println("目标ac："+goalac);
						UserJframe.chatwindowlist.add(goalac);
						new GUIChatC(goalac, JdbcOperate.getnickName(goalac));
					} else {
						System.out.println("窗口已存在");
					}
//					ps.println("windowok");
				}
				br.close();
				ps.close();
				s.close();
			}
		} catch (IOException | SQLException e) {
			JOptionPane jop = new JOptionPane("QQ");
			jop.showMessageDialog(null, "该程序已启动，请勿 重复打开");
			System.exit(0);
		}
	}
	
	
	boolean haveChatWindow(String goalIP) {
		for (int i = 0; i < UserJframe.chatwindowlist.size(); i++) {
			if (UserJframe.chatwindowlist.get(i).equals(goalIP)) {
				return true;
			}
		}
		return false;
	
}


}

