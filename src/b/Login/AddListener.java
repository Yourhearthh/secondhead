package b.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import b.Jdbc.JdbcOperate;
import b.entity.StringUtil;
import b.lianxi.Rigist;
import b.lianxi.UserInfo;
import s.frm.ChangePassword;
import secondheadC.UI.UserJframe;
import secondheadC.thread.GetAllGoodsThread;
import secondheadC.thread.GetUserDataThread;

public class AddListener {
	private Login login;
	private File fileTxt = new File(".\\password\\pass.txt");
	private File fileBox = new File(".\\password\\box.txt");
	public UserInfo u = new UserInfo();

	public AddListener(final Login login) {
		this.login = login;
		this.login.lbluser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Rigist();
			}
		});

		this.login.txtuser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(login.txtuser.getText().equals("账号")){
				login.txtuser.setText(null);
				}
			}
		});
		
		this.login.txtuser.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				login.txtpass.setText(null);
			}
		});

		this.login.btnlogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("登录");
				loginFrame();

			}

		});

		this.login.boxauto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (login.boxauto.isSelected()) {
					login.boxremember.setSelected(true);
				}

			}
		});
		
		login.lblpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ChangePassword();
			}
		});

	}

	/**
	 * 没有记住密码的时候，只保留上一次的账号记录
	 * 
	 * @throws IOException
	 * 
	 */
	public void fn() throws IOException {

		FileReader fr = new FileReader(fileTxt.getAbsolutePath());
		BufferedReader bf = new BufferedReader(fr);
		String str = bf.readLine();
		if (str != null) {
			login.txtpass.setText("");
		}

		FileWriter fwTxt = new FileWriter(fileTxt);
		fwTxt.write(login.txtuser.getText() + "\r\n");
		fwTxt.flush();
		fwTxt.close();
		fr.close();
		bf.close();

	}

	// 保存密码
		@SuppressWarnings("deprecation")
		private void saveFile() {
			try {
				FileWriter fwTxt = new FileWriter(fileTxt);
				String pass =zm( login.txtpass.getText());
				fwTxt.write(login.txtuser.getText() + "\r\n");
				fwTxt.write(pass + "\r\n");
				fwTxt.flush();
				fwTxt.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		private String zm(String pass) {
			char str[]=pass.toCharArray();
			System.out.println(str.length);
			for (int i = 0; i < str.length; i++) {
				str[i]+=1;
			}
			
			pass=new String(str);
			
			return pass;
		}

		
		
	// 保存自动登录和记住密码复选框的状态
	private void saveBox() {
		try {
			FileWriter fwBox;
			fwBox = new FileWriter(fileBox, false);
			fwBox.write(String.valueOf("boxremember=" + login.boxremember.isSelected() + "\r\n"));
			fwBox.write(String.valueOf("boxauto=" + login.boxauto.isSelected()) + "\r\n");
			fwBox.flush();
			fwBox.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	

	// 登录
		public void loginFrame() {
			String username = this.login.txtuser.getText();
			username=username.toLowerCase();
			String password = new String(this.login.txtpass.getPassword()); 
			if (StringUtil.isEmpty(username)) {
				JOptionPane.showMessageDialog(null, "用户名不能为空");
				return;
			}
			if (StringUtil.isEmpty(password)) {
				JOptionPane.showMessageDialog(null, "密码不能为空");
				return;
			}
			UserInfo userinfo = new UserInfo(username, password);
			int i;
			try {
				i = JdbcOperate.UserInfo(userinfo);
				if(i==0){
					JOptionPane.showMessageDialog(null, "账号错误");
				}
				if(i==1){
					JOptionPane.showMessageDialog(null, "登陆成功！");
					UserJframe.userac = username;
					secondheadC.jdbc.JdbcOperate.Online(UserJframe.userac);
					login.dispose();
					try {
						UserJframe.s = new Socket(UserJframe.ip, 666);
						PrintStream ps = new PrintStream(UserJframe.s.getOutputStream());
						ps.println(UserJframe.userac);
					} catch (IOException e) {
						System.out.println("上线服务器未在线");
					}
					new GetUserDataThread().start();
					new GetAllGoodsThread().start();

					if (login.boxremember.isSelected()) {
						saveFile();
				
					}else{
						try {
							fn();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					saveBox();
					UserJframe.userac=username;
					login.dispose();
					new GetUserDataThread().start();
					
				}else if(i==2){
					JOptionPane.showMessageDialog(null, "密码错误");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		

		}

}
