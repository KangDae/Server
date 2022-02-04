package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class server {
	public static void main(String[] args) {

		int port = 9500;

		String txt = "";
		String fileName = "C:\\test11.txt";

		try {
			
			BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));

			try (ServerSocket serverSocket = new ServerSocket(port)) {
				System.out.println("Server is listening on port " + port);

				while (true) {
					Socket socket = serverSocket.accept();
					System.out.println("[ " + socket.getInetAddress() + " ] client connected");
					OutputStream output = socket.getOutputStream();
					PrintWriter writer = new PrintWriter(output, true);
					writer.println(new Date().toString());
					InputStream input = socket.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(input));
					System.out.println("###### msg : " + reader.readLine());
					fw.write(reader.readLine()+"\n");
					fw.flush();
				}
			} catch (IOException ex) {
				System.out.println("Server exception: " + ex.getMessage());
				ex.printStackTrace();
			}
			
			
			

			// 객체 닫기
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
