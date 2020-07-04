package b.Login;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lbltitle;
	public JLabel lbluser;
	public TextField txtuser;
	public JLabel lblpass;
	public JPasswordField txtpass;
	public JButton btnlogin;
	public JCheckBox boxremember;
	public JCheckBox boxauto;
	public boolean frm=true;
	private boolean boxautoVal;
	private String txtuserVal;
	private String txtpassVal;
	private AddListener listener;

	public Login() {
		super((JFrame)null,"登录",true);
		Dimension dim1 = new Dimension(215, 30);
		// 创建元素
		this.lbltitle=new JLabel("二手交易系统");
		lbltitle.setFont(new Font("宋体", Font.BOLD, 25));
		lbltitle.setIcon(new ImageIcon("imagec/title.png"));
		
		this.lbluser = new JLabel("注册账号");
		this.txtuser = new TextField("账号");
		this.txtuser.setColumns(15);

		this.lblpass = new JLabel("找回密码");
		this.txtpass = new JPasswordField(20);

		this.boxremember = new JCheckBox("记住密码");
		this.boxauto = new JCheckBox("自动登录");
		this.duqu();//读取
		this.btnlogin = new JButton("登录");
		this.btnlogin.setPreferredSize(dim1);
		this.btnlogin.setBackground(Color.WHITE);

		// 创建面板
		JPanel panMain = new JPanel();
		JPanel pantitle=new JPanel();
		JPanel panuser = new JPanel();
		JPanel panpass = new JPanel();
		JPanel panbox = new JPanel();
		JPanel panbtn = new JPanel();

		// 面板添加元素
		pantitle.add(lbltitle);
		panuser.add(this.txtuser);
		panuser.add(this.lbluser);
		panpass.add(this.txtpass);
		panpass.add(this.lblpass);
		panbox.add(this.boxauto);
		panbox.add(this.boxremember);
		panbtn.add(this.btnlogin);

		// 设置布局
		panMain.setLayout(new GridLayout(7, 1));
		panMain.add(pantitle);
		panMain.add(new JLabel());
		panMain.add(panuser);
		panMain.add(panpass);
		panMain.add(panbox);
		panMain.add(panbtn);
		panMain.add(new JLabel());// 加一个空行

		this.add(panMain, BorderLayout.CENTER);

		this.setTitle("登录");
		this.setSize(430, 330);
		this.setFont(new Font("宋体", Font.PLAIN, 14));
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLUE);
		this.listener=new AddListener(this);
		this.setVisible(true);
		
	}
	
	/**
	 * 读取文本保存的密码
	 */
	private void duqu() {
		try {
			//账号密码
			File file = new File("password\\pass.txt");
					FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			String str = bf.readLine();
			if(str!=null){
				String line[]=str.split("\r\n");
				txtuser.setText(line[0]);
				this.txtuserVal=line[0];
			}
			str = bf.readLine();
			if(str!=null){
				String line[]=str.split("\r\n");
				String pass = jm(line[0]);
				txtpass.setText(pass);
				this.txtpassVal=line[0];
			}

			bf.close();
			fr.close();
			
			//复选框读取状态
			File filebox = new File("password\\box.txt");
			FileReader frbox = new FileReader(filebox);
			BufferedReader bfbox = new BufferedReader(frbox);
			String strBox = bfbox.readLine();
			while(strBox!=null){
				String line[] = strBox.split("=");
				if("boxauto".equals(line[0])){
					this.boxauto.setSelected(Boolean.valueOf(line[1]));
					this.boxautoVal=Boolean.valueOf(line[1]);
					
				}if("boxremember".equals(line[0])){
					this.boxremember.setSelected(Boolean.valueOf(line[1]));
				}
				strBox=bfbox.readLine();
			}
			frbox.close();
			bfbox.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String jm(String string) {
		char str[]=string.toCharArray();
		for (int i = 0; i < str.length; i++) {
		str[i]-=1;
		}
		String pass = new String(str);
		
		return pass;
	}

}
