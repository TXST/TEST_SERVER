package test_util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

import org.junit.Test;

public class TransServer {

	@Test
	public void receive() throws IOException {

		ServerSocket ss = new ServerSocket(10007);

		Socket s = ss.accept();

		// String ip = s.getInetAddress().getHostAddress();
		String client = s.getRemoteSocketAddress().toString();
		System.out.println(client + "......connected");

		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

		// new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);

//		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		
		
		String line = null;
//		
		while((line=bufr.readLine())!=null){
			
			if("over".equals(line))
				break;
			
			out.println(line);
			
		}

//		String line = null;
//
//		FileWriter fWriter = new FileWriter("E:\\test.txt", true);
//
//		while ((line = bufIn.readLine()) != null) { // readLine()只有在数据流发生异常或者另一端被close()掉时，才会返回null值。
//													// 如果不指定buffer大小，则readLine()使用的buffer有8192个字符。
//													// 在达到buffer大小之前，只有遇到"/r"、"/n"、"/r/n"才会返回。
//			System.out.println(line);
//			fWriter.write(line + "\r\n");
//			fWriter.flush();
//		}
//		System.out.println("over");
//		fWriter.close();
		out.close();
		s.close();
		ss.close();

	}

}
