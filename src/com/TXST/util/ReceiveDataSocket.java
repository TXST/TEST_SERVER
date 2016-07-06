package com.TXST.util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

import org.junit.Test;

import test_util.Pocket;

public class ReceiveDataSocket implements Runnable{

	private String realtimeData = null;
	private BufferedReader bufr;
	private PrintWriter out;
	private ServerSocket ss;
	private Socket s;
	private BufferedReader bufIn;
	private FileWriter fWriter;
	private String regex;
	private String commandCopy;
	private String command;

//	public ReceiveDataSocket() {
//
//		try {
//			ss = new ServerSocket(10005);
//			System.out.println("接收线程启动~~");
//			s = ss.accept();
//
//			String ip = s.getInetAddress().getHostAddress();
//			System.out.println(ip + "......connected");
//
//			bufr = new BufferedReader(new InputStreamReader(System.in));
//			out = new PrintWriter(s.getOutputStream(), true);
//
//			bufIn = new BufferedReader(
//					new InputStreamReader(s.getInputStream()));
//			fWriter = new FileWriter("C:\\test.txt", true);
//			regex = "username[^password]+password.*";// "<img[^>]+>"
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public String getRealTimeData() {

		return realtimeData;
	}

	public String getRealTimeData(String username) {

		String realTimeDataCopy = realtimeData;
		String[] strings = realTimeDataCopy.split("\\+");
		if (strings[0].replace("username:", "").equals(username)) {
			return realTimeDataCopy;
		} else {
			return null;
		}
	}

	@Test
	public void receive() {
		
		try {
			ss = new ServerSocket(10005);
			System.out.println("接收线程启动~~");
			s = ss.accept();

			String ip = s.getInetAddress().getHostAddress();
			System.out.println(ip + "......connected");

			bufr = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(s.getOutputStream(), true);

			bufIn = new BufferedReader(
					new InputStreamReader(s.getInputStream()));
			fWriter = new FileWriter("C:\\test.txt", true);
			regex = "username[^password]+password.*";// "<img[^>]+>"
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("output thread running...");
				while (true) {
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//					command = Pocket.getCommand();
//					System.out.println(command);
//					System.out.println(commandCopy);
//					System.out.println((command = Pocket.getCommand()) != commandCopy);
					
					while ((command = Pocket.getCommand()) != commandCopy) {

						System.out.println(command);
						commandCopy = command;
						out.println(command);

					}
				}
				// String line;
				// try {
				// while ((line = bufr.readLine()) != null) {
				//
				// out.println(line);
				// }
				// } catch (Exception e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("input thread running...");
				try {
					while ((realtimeData = bufIn.readLine()) != null) {

						// System.out.println("test");
						// System.out.println(realtimeData.matches(regex));
						if (realtimeData.matches(regex)) { // 正则过滤，防止部分垃圾数据

							Pocket.setRealtimeData(realtimeData); // 存入pocket用于全局共享
							// System.out.println(Pocket.getRealtimeData());

							fWriter.write(realtimeData + "+time:"
									+ new Timestamp(System.currentTimeMillis())
									+ "\r\n");
							fWriter.flush();
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		//
		// while (true) {
		//
		// }

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("thread running...");
		this.receive();
	}

}
