package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class OnlineServer {

	private ServerSocket ss;
	private HashMap<String, String> hm = new HashMap<String, String>();

	public static void main(String[] args) {
		new OnlineServer();
	}

	public OnlineServer() {

		try {
			ss = new ServerSocket(666);
			new recivedOffLine().start();
			new GetIp().start();
			new relaymessage().start();
			System.out.println("启动上线服务器——端口号：666");
			while (true) {
				reciveASocket(ss.accept());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void reciveASocket(Socket s) throws IOException {
		System.out.println(s.getInetAddress().getHostAddress() + "已连接，端口为:" + s.getPort());
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String str = br.readLine();
		System.out.println(str + "上线了");
		hm.put(str, s.getInetAddress().getHostAddress());
		br.close();
	}

	private class recivedOffLine extends Thread {

		@SuppressWarnings("resource")
		public void run() {
			try {
				ServerSocket ss = new ServerSocket(888);
				Socket socket;
				while (true) {
					socket = ss.accept();
					PrintStream ps = new PrintStream(socket.getOutputStream());
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String str = br.readLine();
					System.out.println(str + "下线了");
					hm.remove(str);
					ps.println("ok");

					ps.close();
					br.close();
					socket.close();

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private class GetIp extends Thread {

		@SuppressWarnings("resource")
		public void run() {
			try {
				ServerSocket ss = new ServerSocket(567);
				Socket socket;
				while (true) {
					socket = ss.accept();
					PrintStream ps = new PrintStream(socket.getOutputStream());
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String str = br.readLine();
					System.out.println("查找账号" + str+"IP");
					String ip = findIP(str);
					System.out.println("IP"+ip);
					ps.println("ok");
					if(ip!=null){
						ps.println(ip);
					}
					ps.close();
					br.close();
					socket.close();

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@SuppressWarnings("rawtypes")
	public String findIP(String str) {
		Iterator<Entry<String, String>> it = hm.entrySet().iterator();
		String ip=null;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println(str+"账号："+e.getKey()+"IP地址"+e.getValue());
			if (e.getKey().equals(str)) {
				ip=(String) e.getValue();
				break;
			}
		}
		return ip;
	}

	private class relaymessage extends Thread {

		@SuppressWarnings("resource")
		public void run() {
			try {
				ServerSocket ss = new ServerSocket(7878);
				Socket socket;
				while (true) {
					try {
						socket = ss.accept();
						PrintStream ps = new PrintStream(socket.getOutputStream());
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String goalac;
						if (br.readLine().equals("to")) {
							ps.println("ok");
							goalac = br.readLine();
							System.out.println(goalac);
							if (hm.get(goalac) != null) {
								ps.println("ip在线");
								String selfac = br.readLine();
								System.out.println("目标ip:"+hm.get(goalac));
								Socket s = new Socket(hm.get(goalac), 7788);
								PrintStream sps = new PrintStream(s.getOutputStream());
								BufferedReader sbr = new BufferedReader(new InputStreamReader(s.getInputStream()));
								sps.println("HaveChatWindow");
								sps.println(selfac);
//								if (sbr.readLine().equals("windowok")) {
								sleep(200);
									ps.println("ChatWindowOK");
//								}
								sps.close();
								sbr.close();
								s.close();
							} else {
								ps.println("ip已下线");

							}
						}
						ps.close();
						br.close();
						// socket.close();
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	
	
}
