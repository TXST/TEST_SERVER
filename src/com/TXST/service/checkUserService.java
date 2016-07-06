package com.TXST.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TXST.Dao.*;
import com.TXST.entity.User;
import com.TXST.util.connectionFactory;

public class checkUserService {
	
	userDaoimpl userDao = new userDaoimpl();
	
	Connection connection = connectionFactory.getConnection();
	
	public boolean check(User user) {
		
		boolean b = false;
//		System.out.println(connection);
		try {
			ResultSet rSet = userDao.select(connection, user);
			if (rSet.next()) {				//结果集不为空   说明用户合法
				b = true;
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return b;
		
	}

}
