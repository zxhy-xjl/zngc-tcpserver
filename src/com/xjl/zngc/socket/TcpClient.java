package com.xjl.zngc.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
	public static void main(String[] args) {
		Socket s = null;
		DataInputStream dataIn = null;
		DataOutputStream dataOut = null;
		try {
			s = new Socket("localhost", 9900);
			dataIn = new DataInputStream(s.getInputStream());
			dataOut = new DataOutputStream(s.getOutputStream());
			System.out.println("����������ӳɹ�");
			Scanner keyIn = new Scanner(System.in);
			while (keyIn.hasNext()) {
				String c = keyIn.nextLine();
				System.out.println("����:"+c);
				if (c.length() == 0) {
					continue;
				}
				dataOut.writeBytes(c+"\n");
				System.out.println("����������:" + readLine(dataIn));
			}
			dataIn.close();
			dataOut.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String readLine(DataInputStream dataIn) throws IOException {
		byte[] b = new byte[1024];
		int i =0;
		while (true) {
			
			Byte c = dataIn.readByte();
			if (c == 10) {
				System.out.println();
				String content = "";
				content = new String(b,0,i);
				System.out.println("��ӡǰ���ȡ��������:"+content);
				i = 0;
				return content;
			} else {
				System.out.print(""+c);
				b[i] = c.byteValue();
				i++;
			}
		}
	}
}
