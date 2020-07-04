package secondheadC.assemby;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ImgLabel extends JLabel {


	public String url;

	public ImgLabel(String url) {
		this.url = url;
		ImageIcon icon = new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		
		this.setIcon(icon);
	}
}
