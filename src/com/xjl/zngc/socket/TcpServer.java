package com.xjl.zngc.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TcpServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9900);
		Socket s = null;
		DataOutputStream dataOut = null;
		DataInputStream dataIn = null;
		while (true) {
			try {
				s = server.accept();
				dataOut = new DataOutputStream(s.getOutputStream());
				dataIn = new DataInputStream(s.getInputStream());
				byte[] b = new byte[1024];
				int i =0;
				while (true) {
					
					Byte c = dataIn.readByte();
					if (c == 10) {
						System.out.println();
						String content = "";
						content = new String(b,0,i);
						System.out.println("打印前面读取到的内容:"+content);
						i = 0;
						if (content.startsWith("init:")) {
							dataOut.writeBytes("init:ok\n");
						} else {
							dataOut.writeBytes(content+":ok\n");
						}
						dataOut.flush();
					} else {
						System.out.print(""+c);
						b[i] = c.byteValue();
						i++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				dataOut.close();
				dataIn.close();
				s.close();
				break;
			}
		}
		
	}
}
