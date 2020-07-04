package secondheadC.thread;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImgThread extends Thread {
	
	private byte[] bytes;
	private JLabel jl;
	private String url;
	private int i;
	private int j;



	public ImgThread(byte[] bytes, JLabel jl, String url, int i, int j) {
		this.bytes = bytes;
		this.jl = jl;
		this.url = url;
		this.i = i;
		this.j = j;
	}



	@Override
	public void run() {
		System.out.println("����һ��ͼƬ");
		try {
			bytetoicon(bytes, url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon usersicon=new ImageIcon(url);
		usersicon.setImage(usersicon.getImage().getScaledInstance(i, j, Image.SCALE_DEFAULT));
		jl.setIcon(usersicon);
		
		System.out.println("������ɣ��Ѹ���");
		
		
	}
	


	// byte����תͼƬ
	static public void bytetoicon(byte[] b, String URL) throws IOException {// ��������ͼƬURL

		File file = new File(URL);

		FileOutputStream fos = new FileOutputStream(file);

		fos.write(b, 0, b.length);

		fos.flush();

		fos.close();
	}
}


