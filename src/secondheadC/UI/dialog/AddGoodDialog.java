package secondheadC.UI.dialog;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import secondheadC.Event.Dialog.AddGoodDialogEvent;
import secondheadC.assemby.ImgLabel;
import secondheadC.jdbc.JdbcOperate;

@SuppressWarnings("serial")
public class AddGoodDialog extends JDialog {

	public String userac;
	public JTextField tdata;
	public JTextArea srdata;
	public JTextField pdata;
	public JTextField ipdata;
	public JTextField fdata;
	public JComboBox<String> lbdata;
	public JComboBox<String> cdatac;
	public JComboBox<String> cdatas;
	public JButton setgoodimg;
	public JPanel imgpanel;
	public JButton confirm;
	public JButton cancel;
	public ArrayList<ImgLabel> jllist=new ArrayList<ImgLabel>();

	public AddGoodDialog(String userac) throws SQLException {
		super((JFrame)null,"发布一件闲置物品",true);
		this.userac = userac;
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("imagec/LOGO.jpg"));
		this.setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		
		setJL();
		
		new AddGoodDialogEvent(this);
		this.setVisible(true);
	}
	
	private void setJL() throws SQLException {
		
		JLabel t=new JLabel("标题：");
		t.setBounds(20, 20, 70, 20);
		tdata = new JTextField("请输入不超过30位的标题");
		tdata.setForeground(Color.GRAY);
		tdata.setBounds(100, 20, 300, 20);

		JLabel sr=new JLabel("商品描述：");
		sr.setBounds(20, 50, 70, 20);
		srdata = new JTextArea("请输入不超过200位的商品描述",10,5);
		srdata.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		srdata.setLineWrap(true);
		srdata.setForeground(Color.GRAY);
		srdata.setBounds(100, 50, 300, 100);
		

		JLabel P=new JLabel("价格：");
		P.setBounds(20, 160, 70, 20);
		pdata = new JTextField("请输入不超过1亿的金额");
		pdata.setForeground(Color.GRAY);
		pdata.setBounds(100, 160, 170, 20);

		JLabel ip=new JLabel("原价：");
		ip.setBounds(20, 190, 70, 20);
		ipdata = new JTextField("请输入不超过1亿的金额");
		ipdata.setForeground(Color.GRAY);
		ipdata.setBounds(100, 190, 170, 20);
		

		JLabel f=new JLabel("邮费：");
		f.setBounds(20, 220, 70, 20);
		fdata = new JTextField("请输入不超过1亿的金额");
		fdata.setForeground(Color.GRAY);
		fdata.setBounds(100, 220, 170, 20);
		
		JLabel lb=new JLabel("类别：");
		lb.setBounds(20, 250, 70, 20);
		lbdata = new JComboBox<String>();
		lbdata.setBounds(100,250, 70, 20);
		lbdata.addItem("衣");
		lbdata.addItem("食");
		lbdata.addItem("住");
		lbdata.addItem("行");
		lbdata.addItem("其它");

		JLabel c=new JLabel("城市：");
		c.setBounds(20, 280, 70, 20);
		cdatac = new JComboBox<String>();
		JdbcOperate.getAllCityC(cdatac);
		cdatac.setBounds(100, 280, 70, 20);
		
		cdatas = new JComboBox<String>();
		JdbcOperate.getAllCitys(cdatas,String.valueOf(cdatac.getSelectedItem()));
		cdatas.setBounds(180, 280, 70, 20);
		
		setgoodimg = new JButton("选择商品图片");
		setgoodimg.setToolTipText("选择一个图片文件作为商品图片");
		setgoodimg.setBounds(20,310, 110, 20);

		imgpanel = new JPanel();
		
		JScrollPane jsp=new JScrollPane(imgpanel);
		jsp.setBounds(0, 330, 500, 190);
		this.add(jsp);
		
		
		
		confirm = new JButton("确认");
		confirm.setBounds(340,530, 60, 20);
		
		cancel = new JButton("取消");
		cancel.setBounds(410,530, 60, 20);

		this.add(t);
		this.add(sr);
		this.add(P);
		this.add(ip);
		this.add(f);
		this.add(c);
		this.add(lb);
		this.add(tdata);
		this.add(srdata);
		this.add(pdata);
		this.add(ipdata);
		this.add(fdata);
		this.add(cdatac);
		this.add(cdatas);
		this.add(lbdata);
		this.add(setgoodimg);
		this.add(confirm);
		this.add(cancel);
			
	}


}
