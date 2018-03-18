package cn.wlt.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	//端口
	private int port;
	
	public TCPServer(int port) {
		super();
		this.port = port;
		Recevice r=new Recevice();
		//创建线程
		Thread t= new Thread(r);
		//启动线程
		t.start();
	}

	class Recevice implements Runnable{

		@Override
		public void run() {
			ServerSocket ss=null;
			Socket s=null;
			BufferedReader br=null;
			try {
				//创建服务器端的服务套接字 Socket
				ss=new ServerSocket(port);
				System.out.println("服务器已经启动...");
				//接收Socket
			    s = ss.accept();
			    //获得客户端的IP
			    InetAddress ia = s.getInetAddress();
			    //获得输入流
			    InputStream in = s.getInputStream();
			    //字节流转换城字符流
			    br=new BufferedReader(new InputStreamReader(in));
			    String line=null;
			    while((line=br.readLine())!=null){
			    	if("exit".equals(line)){
						System.out.println("欢迎使用...");
						break;
					}
			    	System.out.println(ia.getHostAddress()+"说:"+line);
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
