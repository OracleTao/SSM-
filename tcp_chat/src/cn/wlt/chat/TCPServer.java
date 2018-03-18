package cn.wlt.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	//�˿�
	private int port;
	
	public TCPServer(int port) {
		super();
		this.port = port;
		Recevice r=new Recevice();
		//�����߳�
		Thread t= new Thread(r);
		//�����߳�
		t.start();
	}

	class Recevice implements Runnable{

		@Override
		public void run() {
			ServerSocket ss=null;
			Socket s=null;
			BufferedReader br=null;
			try {
				//�����������˵ķ����׽��� Socket
				ss=new ServerSocket(port);
				System.out.println("�������Ѿ�����...");
				//����Socket
			    s = ss.accept();
			    //��ÿͻ��˵�IP
			    InetAddress ia = s.getInetAddress();
			    //���������
			    InputStream in = s.getInputStream();
			    //�ֽ���ת�����ַ���
			    br=new BufferedReader(new InputStreamReader(in));
			    String line=null;
			    while((line=br.readLine())!=null){
			    	if("exit".equals(line)){
						System.out.println("��ӭʹ��...");
						break;
					}
			    	System.out.println(ia.getHostAddress()+"˵:"+line);
			    }
			    System.out.println("sssss");
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				
					try {
						if(br!=null)
						br.close();
						if(s!=null)
						s.close();
						if(ss!=null)
						ss.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}
		public static void main(String[] args) {
			new TCPServer(10000);
		}
}
