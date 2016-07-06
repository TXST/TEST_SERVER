package com.TXST.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectionFactory {
	
	private static String driver ;
	private static String dburl ;
	private static String username ;
	private static String password ;
	
	private static final connectionFactory connectionFactory = new connectionFactory();
	
	private static Connection connection;
	
	static{
		
		Properties properties = new Properties();
		InputStream inputStream = connectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver = properties.getProperty("driver");
		dburl = properties.getProperty("dburl");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
	}
	
	public connectionFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static connectionFactory getInstance() {
		
		return connectionFactory;		
	}
	
	public static Connection getConnection() {
		
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
