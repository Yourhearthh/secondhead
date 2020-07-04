package secondheadC.Event.Dialog;

import java.awt.Color;
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
import secondheadC.UI.UserJframe;
import secondheadC.UI.dialog.AddGoodDialog;
import secondheadC.assemby.ImgLabel;
import secondheadC.jdbc.AllGoodsJdbc;
import secondheadC.jdbc.JdbcOperate;
import secondheadC.jdbc.MyJdbc;

public class AddGoodDialogEvent {

	private AddGoodDialog agd;

	public AddGoodDialogEvent(AddGoodDialog addGoodDialog) {
		this.agd = addGoodDialog;
		
		

		agd.tdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					if (agd.tdata.getText().equals("请输入不超过30位的标题")) {
						agd.tdata.setText("");
						agd.tdata.setForeground(Color.BLACK);
					}
				}
			}
		});
		
		agd.srdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					if (agd.srdata.getText().equals("请输入不超过200位的商品描述")) {
						agd.srdata.setText("");
						agd.srdata.setForeground(Color.BLACK);
					}
				}
			}
		});

		agd.pdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					if (agd.pdata.getText().equals("请输入不超过1亿的金额")) {
						agd.pdata.setText("");
						agd.pdata.setForeground(Color.BLACK);
					}
				}
			}
		});


		agd.ipdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					if (agd.ipdata.getText().equals("请输入不超过1亿的金额")) {
						agd.ipdata.setText("");
						agd.ipdata.setForeground(Color.BLACK);
					}
				}
			}
		});

		agd.fdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					if (agd.fdata.getText().equals("请输入不超过1亿的金额")) {
						agd.fdata.setText("");
						agd.fdata.setForeground(Color.BLACK);
					}
				}
			}
		});
		
		agd.setgoodimg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selecedimg();

			}
		});
		
		agd.cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				agd.dispose();
				
			}
		});

		agd.cdatac.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JdbcOperate.getAllCitys(agd.cdatas, String.valueOf(agd.cdatac.getSelectedItem()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		agd.confirm.addActionListener(new ActionListener() {

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
		FileNameExtensionFilter fef1 = new FileNameExtensionFilter("PNG文件(*.png)", "png");
		FileNameExtensionFilter fef2 = new FileNameExtensionFilter("JPG文件(*.jpg)", "jpg");
		jfc.setFileFilter(fef1);
		jfc.setFileFilter(fef2);
		int i = jfc.showDialog(agd, "选择一张图片");
		jfc.setVisible(true);

		if (i == 0) {
			String url = jfc.getSelectedFile().getPath();
			
			ImgLabel jl=new ImgLabel(url);
			jl.setToolTipText("右击删除该图片");
			agd.imgpanel.add(jl);
			agd.jllist.add(jl);
			agd.imgpanel.updateUI();
			
			jl.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(e.getButton()==MouseEvent.BUTTON3){
						ImgLabel l=(ImgLabel)e.getComponent();
						agd.imgpanel.remove(l);
						agd.jllist.remove(l);
						agd.imgpanel.updateUI();
					}
					
					
				}
				
			});
		}

	}
	

	protected void addGoodsconfirm() throws SQLException, FileNotFoundException, IOException {

		GoodsInfo g = new GoodsInfo();
		g.setTitle(agd.tdata.getText());
		g.setSellReason(agd.srdata.getText());
		try {
			g.setPrice(Double.valueOf(agd.pdata.getText()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "价格输入错误，请重新输入", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		try {
			g.setInitialPrice(Double.valueOf(agd.ipdata.getText()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "原价输入错误，请重新输入", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		try {
			g.setFreight(Double.valueOf(agd.fdata.getText()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "邮费输入错误，请重新输入", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		g.setClassificationName(String.valueOf(agd.lbdata.getSelectedItem()));
		g.setLocation(String.valueOf(agd.cdatac.getSelectedItem())+"-"+String.valueOf(agd.cdatas.getSelectedItem()));
		g.setUserac(agd.userac);
		
		
		

		if (g.getTitle().length() > 30) {
			JOptionPane.showMessageDialog(null, "标题位数超过30位，请重新输入", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getTitle().length() == 0) {
			JOptionPane.showMessageDialog(null, "标题不能为空，请输入", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getSellReason().length() > 200) {
			JOptionPane.showMessageDialog(null, "商品描述超过200位，请重新输入", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getSellReason().length() == 0) {
			JOptionPane.showMessageDialog(null, "商品描述不能为空，请输入", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getPrice()<0||g.getPrice()>=100000000) {
			JOptionPane.showMessageDialog(null, "请输入正确的价格", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getInitialPrice()<0||g.getInitialPrice()>=100000000) {
			JOptionPane.showMessageDialog(null, "请输入正确的原价", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (g.getFreight()<0||g.getFreight()>=100000000) {
			JOptionPane.showMessageDialog(null, "请输入正确的邮费", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		
		if(agd.jllist.size()==0){
			JOptionPane.showMessageDialog(null, "请插入至少一张图片", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}
				
		AllGoodsJdbc.insertgoodsdata(g,agd.jllist);
		UserJframe.myPanel.jlal.get(1)
		.setText(MyJdbc.getMyReleasedCount(AllGoodsJdbc.getuserpkid(UserJframe.userac)));

		agd.dispose();

	}

}
