package secondheadC.Event.Dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import secondheadC.Entity.VO.GoodsInfo;
import secondheadC.UI.dialog.ModifyingCommodityInformationDialog;
import secondheadC.assemby.ImgLabel;
import secondheadC.jdbc.AllGoodsJdbc;
import secondheadC.jdbc.JdbcOperate;
import secondheadC.jdbc.MyJdbc;

public class ModifyingCommodityInformationDialogEvent {

	private ModifyingCommodityInformationDialog mcid;

	public ModifyingCommodityInformationDialogEvent(
			ModifyingCommodityInformationDialog modifyingCommodityInformationDialog) {
		this.mcid = modifyingCommodityInformationDialog;

		mcid.setgoodimg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selecedimg();

			}
		});

		mcid.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mcid.dispose();

			}
		});

		mcid.cdatac.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JdbcOperate.getAllCitys(mcid.cdatas, String.valueOf(mcid.cdatac.getSelectedItem()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		mcid.confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					addGoodsconfirm();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	protected void selecedimg() {
		JFileChooser jfc = new JFileChooser(new File("E:/"));
		jfc.setFileSelectionMode(2);
		FileNameExtensionFilter fef1 = new FileNameExtensionFilter("PNG�ļ�(*.png)", "png");
		FileNameExtensionFilter fef2 = new FileNameExtensionFilter("JPG�ļ�(*.jpg)", "jpg");
		jfc.setFileFilter(fef1);
		jfc.setFileFilter(fef2);
		int i = jfc.showDialog(mcid, "ѡ��һ��ͼƬ");
		jfc.setVisible(true);

		if (i == 0) {
			String url = jfc.getSelectedFile().getPath();

			ImgLabel jl = new ImgLabel(url);
			jl.setToolTipText("�һ�ɾ����ͼƬ");
			mcid.imgpanel.add(jl);
			mcid.jllist.add(jl);
			mcid.imgpanel.updateUI();

			jl.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						ImgLabel l = (ImgLabel) e.getComponent();
						mcid.imgpanel.remove(l);
						mcid.jllist.remove(l);
						mcid.imgpanel.updateUI();
					}

				}

			});
		}

	}

	protected void addGoodsconfirm() throws SQLException, FileNotFoundException, IOException {

		GoodsInfo g = new GoodsInfo();
		g.setTitle(mcid.tdata.getText());
		g.setSellReason(mcid.srdata.getText());
		try {
			g.setPrice(Double.valueOf(mcid.pdata.getText()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "�۸������������������", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		try {
			g.setInitialPrice(Double.valueOf(mcid.ipdata.getText()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ԭ�������������������", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		try {
			g.setFreight(Double.valueOf(mcid.fdata.getText()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "�ʷ������������������", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		g.setClassificationName(String.valueOf(mcid.lbdata.getSelectedItem()));
		g.setLocation(
				String.valueOf(mcid.cdatac.getSelectedItem()) + "-" + String.valueOf(mcid.cdatas.getSelectedItem()));

		if (g.getTitle().length() > 30) {
			JOptionPane.showMessageDialog(null, "����λ������30λ������������", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getTitle().length() == 0) {
			JOptionPane.showMessageDialog(null, "���ⲻ��Ϊ�գ�������", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getSellReason().length() > 200) {
			JOptionPane.showMessageDialog(null, "��Ʒ��������200λ������������", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getSellReason().length() == 0) {
			JOptionPane.showMessageDialog(null, "��Ʒ��������Ϊ�գ�������", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getPrice() < 0 || g.getPrice() >= 100000000) {
			JOptionPane.showMessageDialog(null, "��������ȷ�ļ۸�", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getInitialPrice() < 0 || g.getInitialPrice() >= 100000000) {
			JOptionPane.showMessageDialog(null, "��������ȷ��ԭ��", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getFreight() < 0 || g.getFreight() >= 100000000) {
			JOptionPane.showMessageDialog(null, "��������ȷ���ʷ�", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (mcid.jllist.size() == 0) {
			JOptionPane.showMessageDialog(null, "���������һ��ͼƬ", "��ʾ��", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (!g.getTitle().equals(mcid.gi.getTitle())) {
			MyJdbc.UpdataTitle(mcid.gi.getPkid(), g.getTitle());
		}
		if(!g.getSellReason().equals(mcid.gi.getSellReason())){
			MyJdbc.UpdataSellReason(mcid.gi.getPkid(), g.getSellReason());
		}
		if(g.getPrice()!=mcid.gi.getPrice()){
			MyJdbc.UpdataPrice(mcid.gi.getPkid(), g.getPrice());
		}
		if(g.getInitialPrice()!=mcid.gi.getInitialPrice()){
			MyJdbc.UpdataInitialPrice(mcid.gi.getPkid(), g.getInitialPrice());
		}
		if(g.getFreight()!=mcid.gi.getFreight()){
			MyJdbc.UpdataFreight(mcid.gi.getPkid(), g.getFreight());
		}
		if(!g.getClassificationName().equals(mcid.gi.getClassificationName())){
			MyJdbc.UpdataClassificationName(mcid.gi.getPkid(), g.getClassificationName());
		}
		String str = JdbcOperate.getCcity(mcid.gi.getLocation())+"-"+mcid.gi.getLocation();
		if(!str.equals(g.getLocation())){
			MyJdbc.UpdataLocation(mcid.gi.getPkid(), g.getLocation());
		}
		
		MyJdbc.UpdataModifyTime(mcid.gi.getPkid());
		
		MyJdbc.DelImg(mcid.gi.getPkid());
		AllGoodsJdbc.insertgoodspicture(mcid.jllist, mcid.gi.getPkid());
		
		
		

		mcid.dispose();

	}

}
