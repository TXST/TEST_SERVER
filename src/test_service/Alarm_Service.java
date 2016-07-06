package test_service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import test_util.Pocket;

public class Alarm_Service implements Runnable {

	private String return_ip;
	private int return_port;
	private String return_username;
	private Boolean humAlarmFlag = false;
	private Boolean temAlarmFlag = false;
	private Boolean runningFlag = true;

//	public Alarm_Service(String ip, int port, String username) {
//		// TODO Auto-generated constructor stub
//		return_ip = ip;
//		return_port = port;
//		return_username = username;
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("报警线程启动啦~~");
		Socket socket = null;
		PrintWriter out = null;
		try {
//			socket = new Socket(return_ip, return_port);
			ServerSocket ss = new ServerSocket(10007);

			socket = ss.accept();
			String client = socket.getRemoteSocketAddress().toString();
			System.out.println(client + "......connected");
			out = new PrintWriter(socket.getOutputStream(), true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (runningFlag) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String realtimeData = Pocket.getRealtimeData();
//			System.out.println(realtimeData);
			if (realtimeData != null) {

				String[] strings = realtimeData.split("\\+");
//				System.out.println(strings[4].replace("value:", ""));
				if (strings[3].replace("name:", "").equals("tem1")) {
					if (Integer.parseInt(strings[4].replace("value:", "")) > Pocket
							.getTemValue()) {
						temAlarmFlag = true;
					}else {
						temAlarmFlag = false;
					}
				}
				if (strings[3].replace("name:", "").equals("hum1")) {
					if (Integer.parseInt(strings[4].replace("value:", "")) > Pocket
							.getHumValue()) {
						humAlarmFlag = true;
					}else {
						humAlarmFlag = false;
					}
				}
//				System.out.println(humAlarmFlag);
				if (humAlarmFlag == true || temAlarmFlag == true) {

					out.println("alarm");
					System.out.println("alarm");
					out.flush();
				}
			}

		}

		if (out != null) {
			out.close();
		}

		if (socket != null) {

			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
