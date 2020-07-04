package secondheadC.assemby;

import javax.swing.JButton;

import secondheadC.Entity.VO.GoodsInfo;

@SuppressWarnings("serial")
public class GBtn extends JButton {

	public GoodsInfo gi;

	public GBtn(GoodsInfo gi, String str) {
		super(str);
		this.gi = gi;
		this.setVisible(false);
	}

}
