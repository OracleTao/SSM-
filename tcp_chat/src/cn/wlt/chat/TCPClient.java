package cn.wlt.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	private String ip;
	private int port;

	public TCPClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
		Sender s=new Sender();
		Thread t=new Thread(s);
		t.start();
	}

	class Sender implements Runnable {

		@Override
		public void run() {
			Socket s = null;
			BufferedReader br = null;
			BufferedWriter bw = null;
			try {
				s = new Socket(ip, port);
				// �ӿ���̨����
				br = new BufferedReader(new InputStreamReader(System.in));
				// ������ͨ��
				OutputStream out = s.getOutputStream();
				// ���������
				bw = new BufferedWriter(new OutputStreamWriter(out));
				String line = null;
				while ((line = br.readLine()) != null) {
					if ("exit".equals(line)) {
						System.out.println("��ӭʹ��...");
						break;
					}
					System.out.println("��˵:\r\n" + line);
					bw.write(line);
					bw.newLine();
					// �建��
					bw.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (bw != null)
						bw.close();
					if (br != null)
						br.close();
					if (s != null)
						s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public static void main(String[] args) {
		try {
			new TCPClient(InetAddress.getLocalHost().getHostName(), 20000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
