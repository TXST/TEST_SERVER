package test_service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import test_Dao.UserDaoImpl;
import test_entity.User;

public class CheckUser_Service {
	
	public boolean check(User user,Connection connection){
		
		UserDaoImpl uImpl = new UserDaoImpl();
		//Connection connection = ConnectionFactory.getConnection();		
		try {
			
			ResultSet rSet = uImpl.select(connection, user);
			if(rSet.next())
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public boolean usernameIsExist(User user,Connection connection){
		
		UserDaoImpl uImpl = new UserDaoImpl();
		//Connection connection = ConnectionFactory.getConnection();		
		try {
			
			ResultSet rSet = uImpl.selectByUsername(connection, user);
			if(rSet.next())
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

}
