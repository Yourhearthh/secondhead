package secondheadC.Event.Dialog;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import secondheadC.Event.UserJframeEvent;
import secondheadC.UI.UserJframe;
import secondheadC.UI.dialog.CreatCalendar;
import secondheadC.UI.dialog.UserInformationModifyDialog;
import secondheadC.jdbc.JdbcOperate;
import secondheadC.thread.GetAllGoodsThread;
import secondheadC.thread.GetUserDataThread;

public class UserInformationModifyDialogEvent {

	private UserInformationModifyDialog uimd;
	private String url;

	public UserInformationModifyDialogEvent(UserInformationModifyDialog userInformationModifyDialog) {
		this.uimd = userInformationModifyDialog;
		url = uimd.userinfo.getHeadPortraitPath();

		uimd.cdatac.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JdbcOperate.getAllCitys(uimd.cdatas, String.valueOf(uimd.cdatac.getSelectedItem()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		uimd.setheadimg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selecedimg();

			}
		});

		uimd.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				yc();
				uimd.icon = new ImageIcon(uimd.userinfo.getHeadPortraitPath());
				uimd.icon.setImage(uimd.icon.getImage().getScaledInstance(80, 100, Image.SCALE_DEFAULT));

				uimd.headimg.setIcon(uimd.icon);
				uimd.nndata.setText(uimd.userinfo.getNickname());
				uimd.pndata.setText(uimd.userinfo.getPhoneNumber());
				uimd.bddata.setText(uimd.userinfo.getBirthday());
			}
		});

		uimd.confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					userinformationmodify();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		uimd.bdbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreatCalendar(uimd.bddata);
				
			}
		});

		uimd.xgzl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uimd.nndata.setEditable(true);
				uimd.sexdata.setVisible(true);
				uimd.sexjtf.setVisible(false);
				uimd.bdbtn.setVisible(true);
				uimd.cdatac.setVisible(true);
				uimd.jtfc.setVisible(false);
				uimd.cdatas.setVisible(true);
				uimd.jtfs.setVisible(false);
				uimd.pndata.setEditable(true);
				uimd.setheadimg.setVisible(true);
				uimd.xgzl.setVisible(false);
				uimd.confirm.setVisible(true);
				uimd.cancel.setVisible(true);
				uimd.eixt.setVisible(false);
				
			}
		});
		
		uimd.eixt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UserJframeEvent.offline();
				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
				UserJframe.userac=null;
				UserJframe.u=null;
				new GetUserDataThread().start();
				new GetAllGoodsThread().start();
				uimd.dispose();
				
			}
		});

	}

	protected void yc() {
		uimd.nndata.setEditable(false);
		uimd.sexdata.setVisible(false);
		uimd.sexjtf.setVisible(true);
		uimd.bdbtn.setVisible(false);
		uimd.cdatac.setVisible(false);
		uimd.jtfc.setVisible(true);
		uimd.cdatas.setVisible(false);
		uimd.jtfs.setVisible(true);
		uimd.pndata.setEditable(false);
		uimd.setheadimg.setVisible(false);
		uimd.xgzl.setVisible(true);
		uimd.confirm.setVisible(false);
		uimd.cancel.setVisible(false);
		uimd.eixt.setVisible(true);
		
	}

	protected void userinformationmodify() throws SQLException, FileNotFoundException, IOException {

		if (uimd.nndata.getText().length() > 5) {
			JOptionPane.showMessageDialog(null, "昵称位数超过5位，请重新输入", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (uimd.pndata.getText().length() != 11) {
			JOptionPane.showMessageDialog(null, "请输入正确的电话号码", "提示：", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (!uimd.nndata.getText().equals(uimd.userinfo.getNickname())) {
			JdbcOperate.updatenn(uimd.userinfo.getAccountNumber(), uimd.nndata.getText());
		}

		if (!String.valueOf(uimd.sexdata.getSelectedItem()).equals(uimd.userinfo.getSex())) {
			JdbcOperate.updatesex(uimd.userinfo.getAccountNumber(), String.valueOf(uimd.sexdata.getSelectedItem()));
			 uimd.sexjtf.setText(String.valueOf(uimd.sexdata.getSelectedItem()));
		}

		if (!uimd.bddata.getText().equals(uimd.userinfo.getBirthday())) {
			JdbcOperate.updatebd(uimd.userinfo.getAccountNumber(), uimd.bddata.getText());
		}

		if (!String.valueOf(uimd.cdatas.getSelectedItem()).equals(uimd.userinfo.getCityName())) {
			JdbcOperate.updatecity(uimd.userinfo.getAccountNumber(), String.valueOf(uimd.cdatac.getSelectedItem()),
					String.valueOf(uimd.cdatas.getSelectedItem()));
			uimd.jtfc.setText(String.valueOf(uimd.cdatac.getSelectedItem()));
			uimd.jtfs.setText(String.valueOf(uimd.cdatas.getSelectedItem()));
		}

		if (!uimd.pndata.getText().equals(uimd.userinfo.getPhoneNumber())) {
			JdbcOperate.updatepn(uimd.userinfo.getAccountNumber(), uimd.pndata.getText());
		}

		if (!uimd.userinfo.getHeadPortraitPath().equals(url)) {
			JdbcOperate.updatehp(uimd.userinfo.getAccountNumber(), url);
		}

		 new GetUserDataThread().start();
		 
		 
		 yc();

	}

	protected void selecedimg() {
		JFileChooser jfc = new JFileChooser(new File("E:/"));
		jfc.setFileSelectionMode(2);
		FileNameExtensionFilter fef1 = new FileNameExtensionFilter("PNG文件(*.png)", "png");
		FileNameExtensionFilter fef2 = new FileNameExtensionFilter("JPG文件(*.jpg)", "jpg");
		jfc.setFileFilter(fef1);
		jfc.setFileFilter(fef2);
		int i = jfc.showDialog(uimd, "选择一张图片");
		jfc.setVisible(true);

		if (i == 0) {
			url = jfc.getSelectedFile().getPath();
			uimd.icon = new ImageIcon(url);
			uimd.icon.setImage(uimd.icon.getImage().getScaledInstance(80, 100, Image.SCALE_DEFAULT));

			uimd.headimg.setIcon(uimd.icon);
		}

	}

}
