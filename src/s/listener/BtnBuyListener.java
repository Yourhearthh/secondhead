package s.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import s.frm.BuyIt;


public class BtnBuyListener implements ActionListener {
	private int goodId;
	private Integer userId = null;
	private String tranUrl;

	public BtnBuyListener(int id, Integer userId, String tranUrl) {
		this.goodId = id;
		this.userId = userId;
		this.tranUrl = tranUrl;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.userId != null) {
			new BuyIt(this.goodId, this.userId, this.tranUrl);
		} else {
			JOptionPane.showMessageDialog(null, "请先登录或注册！");
		}

	}

}
