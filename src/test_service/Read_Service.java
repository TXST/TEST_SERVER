package test_service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.junit.Test;

import test_Dao.Envir_InfoDaoImpl;
import test_entity.Environment_Info;
import test_entity.User;
import test_util.ConnectionFactory;

public class Read_Service {
	
	
	private String[] usernames ;
	
	
	@Test
	public void read() throws IOException {
		
		FileReader fReader = new FileReader("E:\\test.txt");
		BufferedReader bReader = new BufferedReader(fReader);
		
		Environment_Info eInfo = new Environment_Info();
		Envir_InfoDaoImpl eInfoImpl = new Envir_InfoDaoImpl();
		String line = null;
		String regex = "\\+";		//+、*、|、\、?、^等符号在正则中有相应的不同意义,一般用\\或[]转义
		
		String username = null;
		String password = null;
		CheckUser_Service service = new CheckUser_Service();
		User user = new User();
		Connection connection = ConnectionFactory.getConnection();
		
		int count = 0;

		while((line = bReader.readLine())!=null){
			if(line.startsWith("username")){			//line.substring(0, 4).equals("name")
														//判断数据行是否是有用数据
				String[] info_temps = line.split(regex);
				username = info_temps[0].replace("username:", "");
				password = info_temps[1].replace("password:", "");
				
				user.setUsername(username);
				user.setPassword(password);
				if(service.check(user, connection)){		//判断数据权限
				
					eInfoImpl.setTable(info_temps[2].replace("sensor:", ""));
					//System.out.println(info_temps[2].replace("sensor:", ""));
					eInfo.setUsername(username);
					//System.out.println(username);
					eInfo.setName(info_temps[3].replace("name:", ""));
					//System.out.println(info_temps[3].replace("name:", ""));
					try {
						eInfo.setValue(Integer.parseInt(info_temps[4].replace("value:", "")));
						//System.out.println(Integer.parseInt(info_temps[4].replace("value:", "")));
						eInfo.setTime(Timestamp.valueOf(info_temps[5].replace("time:", "")));
						//System.out.println(info_temps[5].replace("time:", ""));
					} catch (Exception e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
						continue;			//数据格式不正确则抛弃此条数据
					}
					try {					//此条数据插入数据库
						
						eInfoImpl.save(connection, eInfo);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
					
//				for(String info_temp :info_temps){			//比较自由的格式
//					
//					if(info_temp.startsWith("name")){
//						tem_Info.setName(info_temp.replace("name:", ""));
//						//System.out.print(info_temp.replace("name:", ""));
//					}
//					if(info_temp.startsWith("value")){
//						tem_Info.setValue(info_temp.replace("value:", ""));
//						//System.out.print(info_temp.replace("value:", ""));
//					}
//					if(info_temp.startsWith("info")){
//						tem_Info.setInfo(info_temp.replace("info:", ""));
//						//System.out.print(info_temp.replace("info:", ""));
//					}
//					if(info_temp.startsWith("time")){
//						tem_Info.setTime(info_temp.replace("time:", ""));
//						//System.out.print(info_temp.replace("time:", ""));
//					}
//				}
					
					//System.out.print("--"+info_temp+"--");
//				System.out.println(line);
				
					count ++;
			}
		}
		System.out.println("插入了"+count+"条数据");
		try {
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bReader.close();
		fReader.close();
			
	}

}
