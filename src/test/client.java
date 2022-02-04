package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	public static void main(String[] args) {
		
		
		int port = 9500;
		
		for (int i = 0; i < 10; i++) {
			try (Socket socket = new Socket("127.0.0.1", port)) {
				
				
				OutputStream out = socket.getOutputStream();
				
				PrintWriter PWout = new PrintWriter(socket.getOutputStream());
				
				
				String realStr = "메세지 보내기입니다//client";//메세지
				
				PWout.println(realStr);
				PWout.flush();
				
				
				out.write(realStr.getBytes());
				InputStream input = socket.getInputStream();
				
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				String time = reader.readLine();
				System.out.println(time);
				
				
			} catch (UnknownHostException ex) {
				System.out.println("Server not found: " + ex.getMessage());
			} catch (IOException ex) {
				System.out.println("I/O error: " + ex.getMessage());
			}
		}
	}
}
