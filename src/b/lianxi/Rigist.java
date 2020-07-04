package b.lianxi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import b.Jdbc.JdbcOperate;
import b.Jdbc.JdbcUtils;
import secondheadC.UI.dialog.CreatCalendar;



public class Rigist extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JdbcUtils jdbc=new JdbcUtils();

	public JTextField txtrigist;
	public JTextField txtname;
	public JTextField txtuser;
	public JTextField txtpass;
	public JRadioButton rdbman;
	public JRadioButton rdbwoman;
	public JTextField txtbirth;
	public JTextField txtphone;
	public JButton btnimage;
	public JButton btnsubmit;
	public String filepath = "F://EclipseImage";
	public Font font = new Font("YaHei Consolas Hybrid", Font.PLAIN, 30);
	public JLabel lblimas;
	public String initimage = "imagec/imas.png";
	public UserInfo u = new UserInfo();

	private JComboBox<String> boxsheng;

	private JComboBox<String> boxshi;
	private JButton btnbir;//选择日期

	public Rigist() {
		super((JFrame)null,"注册",true);
		// 创建元素
		this.txtrigist = new JTextField("注册信息");
		this.txtrigist.setFont(font);
		this.txtrigist.setEditable(false);
		JLabel lbluser = new JLabel("账户");
		this.txtuser = new JTextField(20);
		JLabel lblpass = new JLabel("密码");
		this.txtpass = new JTextField(20);
		JLabel lblname = new JLabel("昵称");
		this.txtname = new JTextField(20);
		JLabel lblsex = new JLabel("性别");
		this.rdbman = new JRadioButton("男");
		this.rdbman.setSelected(true);
		this.rdbwoman = new JRadioButton("女");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbman);
		bg.add(rdbwoman);
		JLabel lblbirth = new JLabel("生日");
		this.txtbirth = new JTextField(11);
		this.txtbirth.setEditable(false);
	    btnbir = new JButton("请选择日期");
		JLabel lbladdress = new JLabel("城市           ");
		boxsheng = new JComboBox<String>();
		try {
			JdbcOperate.getAllCityC(boxsheng);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		boxshi = new JComboBox<String>();
		try {
			JdbcOperate.getAllCityS(boxshi, String.valueOf(boxsheng.getSelectedItem()));
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		JLabel lblphone = new JLabel("电话");
		this.txtphone = new JTextField(20);
		JLabel lblimage = new JLabel("头像");
		this.lblimas = new JLabel();
		lblimas.setSize(50, 50);
		ImageIcon icon = new ImageIcon(this.initimage);// 创建图片对象
		icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		lblimas.setIcon(icon);

		this.btnimage = new JButton("选择图片");// 选择图片
		this.btnsubmit = new JButton("提交注册");
		Dimension dim1 = new Dimension(200, 30);
		this.btnsubmit.setPreferredSize(dim1);
		this.btnsubmit.setBackground(Color.WHITE);

		// 增加面板
		JPanel panMain = new JPanel();
		JPanel panrigist = new JPanel();
		JPanel panuser = new JPanel();
		JPanel panpass = new JPanel();
		JPanel panname = new JPanel();
		JPanel panrad = new JPanel();
		JPanel panbirth = new JPanel();
		JPanel panchoice = new JPanel();
		JPanel panphone = new JPanel();
		final JPanel panima = new JPanel();
		JPanel panimage = new JPanel();
		JPanel pansubmit = new JPanel();

		// 添加元素
		panrigist.add(txtrigist);
		panuser.add(lbluser);
		panuser.add(txtuser);
		panpass.add(lblpass);
		panpass.add(txtpass);
		panname.add(lblname);
		panname.add(txtname);
		panrad.add(lblsex);
		panrad.add(rdbman);
		panrad.add(rdbwoman);
		panbirth.add(lblbirth);
		panbirth.add(txtbirth);
		panbirth.add(btnbir);
		panchoice.add(lbladdress);
		panchoice.add(boxsheng);
		panchoice.add(boxshi);
		panphone.add(lblphone);
		panphone.add(txtphone);
		panima.add(lblimage);
		panima.add(lblimas);
		panimage.add(btnimage);
		pansubmit.add(btnsubmit);

		// 设置布局
		panMain.setLayout(new GridLayout(11, 1));
		panMain.add(panrigist);
		panMain.add(panuser);
		panMain.add(panpass);
		panMain.add(panname);
		panMain.add(panrad);
		panMain.add(panbirth);
		panMain.add(panchoice);
		panMain.add(panphone);
		panMain.add(panima);
		panMain.add(panimage);
		panMain.add(pansubmit);

		panMain.setBackground(Color.WHITE);
		panrigist.setBackground(Color.WHITE);
		panuser.setBackground(Color.WHITE);
		panpass.setBackground(Color.WHITE);
		panname.setBackground(Color.WHITE);
		panrad.setBackground(Color.WHITE);
		panbirth.setBackground(Color.WHITE);
		panchoice.setBackground(Color.WHITE);
		panphone.setBackground(Color.WHITE);
		panima.setBackground(Color.WHITE);
		panimage.setBackground(Color.WHITE);
		pansubmit.setBackground(Color.WHITE);

		this.add(panMain, BorderLayout.CENTER);
		this.setSize(570, 590);
		this.setLocationRelativeTo(null);

		boxsheng.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					JdbcOperate.getAllCityS(boxshi, String.valueOf(boxsheng.getSelectedItem()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		this.btnimage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser("D:\\Image");
				int returnValue = chooser.showOpenDialog(panima);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					initimage = file.getPath();
					// 加载图片
					ImageIcon icon = new ImageIcon(file.getPath());// 创建图片对象
					icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
					lblimas.setIcon(icon);

				}
			}

		});

		this.btnbir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setBirthday();
				
			}
		});
		
		
		this.btnsubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					ModifyUser();
					dispose();

				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		this.setVisible(true);

	}
	
	//设置生日提交
	private void setBirthday() {
		CreatCalendar cc=new CreatCalendar(txtbirth);
		
	}

	// 提交的方法
	public void ModifyUser() throws FileNotFoundException, SQLException, IOException {
		if (this.txtuser.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "账号不能为空");
			return;
		}

		if (this.txtpass.getText().length() < 6) {
			JOptionPane.showMessageDialog(null, "输入密码不得低于6位");
//			return;
		}

		if (this.txtname.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "昵称不能为空");
			return;
		}
		
		if(this.txtbirth.getText().length()==0){
			JOptionPane.showMessageDialog(null, "生日不能为空");
			return;
		}

		if (this.txtphone.getText().length() != 11) {
			JOptionPane.showMessageDialog(null, "手机号码格式不对");
			return;
		}

		String usernum=txtuser.getText();
		System.out.println(usernum);
		boolean loop=JdbcOperate.getAccountNumbe(usernum);
		if (loop) {
			JOptionPane.showMessageDialog(null, "账号已存在");
			return;
		} else {
			u.setAccountNumber(usernum);
		}
		u.setPassWords(txtpass.getText());
		u.setNickname(txtname.getText());
		String sex;
		if (rdbman.isSelected()) {
			sex = " 男";
		} else {
			sex = "女";
		}
		u.setSex(sex);
		u.setBirthday(txtbirth.getText());
		String city;
		city = (String) boxsheng.getSelectedItem() + "-" + boxshi.getSelectedItem();
		u.setCityName(city);
		u.setPhoneNumber(txtphone.getText());
		u.setHeadPortraitPath(initimage);
		JdbcOperate.insertuserdata(u);
		JOptionPane.showMessageDialog(null, "提交成功");
	}

	
}
