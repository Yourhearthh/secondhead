package secondheadC.UI.Chat;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUIChatC extends JDialog {

	public JTextArea viewText,sendText;
	public JButton send,log,clear,shake;
	public JTextField jt;

	public static void main(String[] args) {
//		GUIChatC gc = new GUIChatC();
//		gc.sendText.dispatchEvent(new FocusEvent(gc.sendText,FocusEvent.FOCUS_GAINED,true));
//		gc.sendText.requestFocusInWindow();
	}
	
	public GUIChatC(String userac, String nickName){
		super((JFrame)null,"与"+nickName+"的聊天",true);
		this.setSize(400,600);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("images/LOGO.jpg"));
		this.setLocationRelativeTo(null);
		setspanel();
		setcpanel();
		
		
		new EventC(this,userac,nickName);
		this.setVisible(true);
	}

	private void setcpanel() {
		JPanel c=new JPanel();
		c.setLayout(new BorderLayout());
		viewText = new JTextArea();
		viewText.setLineWrap(true);
		viewText.setEditable(false);
		JScrollPane viewTextjsp=new JScrollPane(viewText);
		sendText = new JTextArea(5,10);
		sendText.setLineWrap(true);
		JScrollPane sendTextjsp=new JScrollPane(sendText);
		
		c.add(viewTextjsp,BorderLayout.CENTER);
		c.add(sendTextjsp,BorderLayout.SOUTH);
		
		this.add(c,BorderLayout.CENTER);
		
	}

	private void setspanel() {
		JPanel s=new JPanel();
		send = new JButton("发送");
		send.setToolTipText("Alt+Enter");
		log = new JButton("记录");
		log.setToolTipText("Alt+L");
		clear = new JButton("清屏");
		clear.setToolTipText("Alt+C");
		shake = new JButton("震动");
		shake.setToolTipText("Alt+S");

		s.add(send);
		s.add(log);
		s.add(clear);
		s.add(shake);
		
		this.add(s,BorderLayout.SOUTH);
		
		
	}

}
