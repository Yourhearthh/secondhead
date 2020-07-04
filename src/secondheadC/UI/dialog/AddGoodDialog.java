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
		super((JFrame)null,"����һ��������Ʒ",true);
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
		
		JLabel t=new JLabel("���⣺");
		t.setBounds(20, 20, 70, 20);
		tdata = new JTextField("�����벻����30λ�ı���");
		tdata.setForeground(Color.GRAY);
		tdata.setBounds(100, 20, 300, 20);

		JLabel sr=new JLabel("��Ʒ������");
		sr.setBounds(20, 50, 70, 20);
		srdata = new JTextArea("�����벻����200λ����Ʒ����",10,5);
		srdata.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		srdata.setLineWrap(true);
		srdata.setForeground(Color.GRAY);
		srdata.setBounds(100, 50, 300, 100);
		

		JLabel P=new JLabel("�۸�");
		P.setBounds(20, 160, 70, 20);
		pdata = new JTextField("�����벻����1�ڵĽ��");
		pdata.setForeground(Color.GRAY);
		pdata.setBounds(100, 160, 170, 20);

		JLabel ip=new JLabel("ԭ�ۣ�");
		ip.setBounds(20, 190, 70, 20);
		ipdata = new JTextField("�����벻����1�ڵĽ��");
		ipdata.setForeground(Color.GRAY);
		ipdata.setBounds(100, 190, 170, 20);
		

		JLabel f=new JLabel("�ʷѣ�");
		f.setBounds(20, 220, 70, 20);
		fdata = new JTextField("�����벻����1�ڵĽ��");
		fdata.setForeground(Color.GRAY);
		fdata.setBounds(100, 220, 170, 20);
		
		JLabel lb=new JLabel("���");
		lb.setBounds(20, 250, 70, 20);
		lbdata = new JComboBox<String>();
		lbdata.setBounds(100,250, 70, 20);
		lbdata.addItem("��");
		lbdata.addItem("ʳ");
		lbdata.addItem("ס");
		lbdata.addItem("��");
		lbdata.addItem("����");

		JLabel c=new JLabel("���У�");
		c.setBounds(20, 280, 70, 20);
		cdatac = new JComboBox<String>();
		JdbcOperate.getAllCityC(cdatac);
		cdatac.setBounds(100, 280, 70, 20);
		
		cdatas = new JComboBox<String>();
		JdbcOperate.getAllCitys(cdatas,String.valueOf(cdatac.getSelectedItem()));
		cdatas.setBounds(180, 280, 70, 20);
		
		setgoodimg = new JButton("ѡ����ƷͼƬ");
		setgoodimg.setToolTipText("ѡ��һ��ͼƬ�ļ���Ϊ��ƷͼƬ");
		setgoodimg.setBounds(20,310, 110, 20);

		imgpanel = new JPanel();
		
		JScrollPane jsp=new JScrollPane(imgpanel);
		jsp.setBounds(0, 330, 500, 190);
		this.add(jsp);
		
		
		
		confirm = new JButton("ȷ��");
		confirm.setBounds(340,530, 60, 20);
		
		cancel = new JButton("ȡ��");
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
