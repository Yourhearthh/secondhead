package secondheadC.UI.dialog;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.Event.Dialog.ModifyingCommodityInformationDialogEvent;
import secondheadC.assemby.ImgLabel;
import secondheadC.jdbc.JdbcOperate;
import secondheadC.jdbc.MyJdbc;

@SuppressWarnings("serial")
public class ModifyingCommodityInformationDialog extends JDialog {

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
	public ArrayList<ImgLabel> jllist = new ArrayList<ImgLabel>();
	public GoodsInfo gi;
	public ArrayList<String> stral;

	public ModifyingCommodityInformationDialog(GoodsInfo gi) throws SQLException {
		super((JFrame) null, "发布一件闲置物品", true);
		this.gi = gi;
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("imagec/LOGO.jpg"));
		this.setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);

		setJL();

		new ModifyingCommodityInformationDialogEvent(this);
		this.setVisible(true);
	}

	private void setJL() throws SQLException {

		JLabel t = new JLabel("标题：");
		t.setBounds(20, 20, 70, 20);
		tdata = new JTextField(gi.getTitle());
		tdata.setBounds(100, 20, 300, 20);

		JLabel sr = new JLabel("商品描述：");
		sr.setBounds(20, 50, 70, 20);
		srdata = new JTextArea(gi.getSellReason(), 10, 5);
		srdata.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		srdata.setLineWrap(true);
		srdata.setBounds(100, 50, 300, 100);

		JLabel P = new JLabel("价格：");
		P.setBounds(20, 160, 70, 20);
		pdata = new JTextField(gi.getPrice() + "");
		pdata.setBounds(100, 160, 170, 20);

		JLabel ip = new JLabel("原价：");
		ip.setBounds(20, 190, 70, 20);
		ipdata = new JTextField(gi.getInitialPrice() + "");
		ipdata.setBounds(100, 190, 170, 20);

		JLabel f = new JLabel("邮费：");
		f.setBounds(20, 220, 70, 20);
		fdata = new JTextField(gi.getFreight() + "");
		fdata.setBounds(100, 220, 170, 20);

		JLabel lb = new JLabel("类别：");
		lb.setBounds(20, 250, 70, 20);
		lbdata = new JComboBox<String>();
		lbdata.setBounds(100, 250, 70, 20);
		lbdata.addItem("衣");
		lbdata.addItem("食");
		lbdata.addItem("住");
		lbdata.addItem("行");
		lbdata.addItem("其它");
		lbdata.setSelectedItem(gi.getClassificationName());

		JLabel c = new JLabel("城市：");
		c.setBounds(20, 280, 70, 20);
		cdatac = new JComboBox<String>();
		JdbcOperate.getAllCityC(cdatac);
		cdatac.setBounds(100, 280, 70, 20);
		cdatac.setSelectedItem(JdbcOperate.getCcity(gi.getLocation()));

		cdatas = new JComboBox<String>();
		JdbcOperate.getAllCitys(cdatas, String.valueOf(cdatac.getSelectedItem()));
		cdatas.setBounds(180, 280, 70, 20);
		cdatas.setSelectedItem(gi.getLocation());

		setgoodimg = new JButton("选择商品图片");
		setgoodimg.setToolTipText("选择一个图片文件作为商品图片");
		setgoodimg.setBounds(20, 310, 110, 20);

		imgpanel = new JPanel();

		JScrollPane jsp = new JScrollPane(imgpanel);
		jsp.setBounds(0, 330, 500, 190);
		this.add(jsp);

		stral = MyJdbc.getgoodimg(gi.getPkid());
		for (int i = 0; i < stral.size(); i++) {
			ImgLabel jl = new ImgLabel(stral.get(i));
			jl.setToolTipText("右击删除该图片");
			imgpanel.add(jl);
			jllist.add(jl);
			imgpanel.updateUI();

			jl.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						ImgLabel l = (ImgLabel) e.getComponent();
						imgpanel.remove(l);
						jllist.remove(l);
						imgpanel.updateUI();
					}

				}
			});
		}

		confirm = new JButton("确认");
		confirm.setBounds(340, 530, 60, 20);

		cancel = new JButton("取消");
		cancel.setBounds(410, 530, 60, 20);

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
