package test_util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class ConnectionFactory {
	
	private static String driver;
	private static String dburl;
	private static String username;
	private static String password;
	
	private static final ConnectionFactory connectionFactory = new ConnectionFactory();
	
	private static Connection connection;
	
	static{
		
		Properties properties = new Properties();
		InputStream  in = ConnectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
		try {
			
			properties.load(in);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		driver = properties.getProperty("driver");
		dburl = properties.getProperty("dburl");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
		
	}
	
	public static ConnectionFactory getInstance() {
		
		return connectionFactory;		
	}
	
	public static Connection getConnection(){
		
		try {
			
			Class.forName(driver);
			connection = DriverManager.getConnection(dburl,username,password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}

}
