package secondheadC.UI.Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import secondheadC.UI.UserJframe;

public class EventC {
	GUIChatC gc;
	private DatagramSocket socket, ds;
	private BufferedWriter bw;
	String ip;
	PrintStream ps;
	BufferedReader br;
	private Socket sockets;
	private ArrayList<String> chatWindows = UserJframe.chatwindowlist;
	private String userac;
	private String nickName;

	public EventC(GUIChatC guiChatC, String userac, String nickName) {
		this.gc = guiChatC;
		this.userac = userac;
		this.nickName = nickName;
		getIP();

		gc.sendText.dispatchEvent(new FocusEvent(gc.sendText, FocusEvent.FOCUS_GAINED, true));
		gc.sendText.requestFocusInWindow();
		try {
			System.out.println("��ȡ��¼�ļ�");
			bw = new BufferedWriter(new FileWriter("ser/configC" + userac + ".ser", true));
			socket = new DatagramSocket();
			new Receive().start();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		gc.send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					send();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		// �رմ��尴ť
		gc.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					exitChatWindow();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// ������ť
		gc.clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gc.viewText.setText("");
				gc.sendText.dispatchEvent(new FocusEvent(gc.sendText, FocusEvent.FOCUS_GAINED, true));
				gc.sendText.requestFocusInWindow();

			}
		});

		// ��¼��ť
		gc.log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					logFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		// �𶯰�ť
		gc.shake.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					send(new byte[] { -1 });
					gc.sendText.dispatchEvent(new FocusEvent(gc.sendText, FocusEvent.FOCUS_GAINED, true));
					gc.sendText.requestFocusInWindow();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		// ��ݼ�
		gc.sendText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_ENTER) && e.isAltDown()) {
					gc.send.doClick();
				}

				if ((e.getKeyCode() == KeyEvent.VK_C) && e.isAltDown()) {

					gc.clear.doClick();
				}

				if ((e.getKeyCode() == KeyEvent.VK_L) && e.isAltDown()) {

					gc.log.doClick();
				}

				if ((e.getKeyCode() == KeyEvent.VK_S) && e.isAltDown()) {

					gc.shake.doClick();
				}
			}
		});

	}

	private void getIP() {
		try {
			Socket getIp = new Socket(UserJframe.ip, 567);
			PrintStream ps = new PrintStream(getIp.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(getIp.getInputStream()));
			System.out.println("�����" + userac);
			ps.println(userac);
			if ("ok".equals(br.readLine())) {
				this.ip = br.readLine();
				System.out.println("�Է�ip:" + ip);
			} else {
				System.out.println("�Է�������");
				exitChatWindow();
			}
			ps.close();
			br.close();
			getIp.close();
		} catch (IOException e3) {
			e3.printStackTrace();
		}

	}

	protected void exitChatWindow() throws IOException {
		removeIP(chatWindows);
		send(new byte[] { 0 });
		socket.close();
		ds.close();
		bw.flush();
		bw.close();
		gc.dispose();

	}

	protected void removeIP(ArrayList<String> chatWindows) {
		for (int i = 0; i < chatWindows.size(); i++) {
			if (chatWindows.get(i).equals(userac)) {
				chatWindows.remove(i);
				break;
			}
		}

	}

	// ��
	protected void shake() {
		int x = gc.getLocation().x, y = gc.getLocation().y;
		for (int i = 0; i < 4; i++) {
			try {
				gc.setLocation(x + 20, y + 20);
				Thread.sleep(20);
				gc.setLocation(x + 20, y - 20);
				Thread.sleep(20);
				gc.setLocation(x - 20, y + 20);
				Thread.sleep(20);
				gc.setLocation(x - 20, y - 20);
				Thread.sleep(20);
				gc.setLocation(x, y);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// ���ü�¼
	protected void logFile() throws IOException {
		bw.flush();
		FileInputStream fis = new FileInputStream("ser/configC" + userac + ".ser");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int len;
		byte arr[] = new byte[8192];
		while ((len = fis.read(arr)) != -1) {
			baos.write(arr, 0, len);
		}

		String str = baos.toString();
		gc.viewText.setText(str);

		fis.close();
		gc.sendText.dispatchEvent(new FocusEvent(gc.sendText, FocusEvent.FOCUS_GAINED, true));
		gc.sendText.requestFocusInWindow();

	}

	// ���Ͳ���
	private void send(byte arr[]) throws IOException {
		DatagramPacket packet = new DatagramPacket(arr, arr.length, InetAddress.getByName(ip), 999);
		socket.send(packet);
	}

	protected void send() throws UnknownHostException, IOException {
		String message = gc.sendText.getText(), time = getCurrentTime();
		if (chatWindow()) {
			if (!message.equals("")) {
				send(message.getBytes());
				String str = time + " �Ҷ�" + nickName + "˵��\r\n" + message + "\r\n\r\n";

				gc.viewText.append(str);
				bw.write(str);
				gc.sendText.setText("");
				gc.sendText.dispatchEvent(new FocusEvent(gc.sendText, FocusEvent.FOCUS_GAINED, true));
				gc.sendText.requestFocusInWindow();
			}
		}
	}

	private boolean chatWindow() throws IOException {
		sockets = new Socket(UserJframe.ip, 7878);
		ps = new PrintStream(sockets.getOutputStream());
		br = new BufferedReader(new InputStreamReader(sockets.getInputStream()));
		ps.println("to");
		if (br.readLine().equals("ok")) {
			ps.println(userac);
			if (br.readLine().equals("ip����")) {
				ps.println(UserJframe.userac);
				if (br.readLine().equals("ChatWindowOK")) {
					System.out.println("˫�������Ѵ򿪣��뿪ʼ����");
					return true;
				}
			} else if (br.readLine().equals("ip������")) {
				return false;
			}
		}
		return false;
	}

	// ��ȡ��ǰʱ��
	private String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("YYYY��MM��dd�� HH:mm:ss");

		return df.format(new Date());
	}

	// �����߳�
	private class Receive extends Thread {

		@SuppressWarnings("static-access")
		public void run() {
			try {
				ds = new DatagramSocket(999);
				DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
				while (true) {
					try {
						ds.receive(packet);
					} catch (Exception e1) {
						break;
					}
					byte[] arr = packet.getData();
					int len = packet.getLength();
					if (arr[0] == -1 && len == 1) {
						shake();
						continue;
					}
					if (arr[0] == 0 && len == 1) {
						JOptionPane jop = new JOptionPane("���ֽ���");
						jop.showMessageDialog(null, "�Է��ѹر����촰��");
						try {
							exitChatWindow();
						} catch (Exception e) {
							ds.close();
							break;
						}
						ds.close();
						break;
					}
					String message = new String(arr, 0, len), time = getCurrentTime();
					String str = time + " " + nickName + "����˵:\r\n" + message + "\r\n\r\n";
					gc.viewText.append(str);
					bw.write(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
