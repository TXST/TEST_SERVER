package com.TXST.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TXST.entity.*;

public interface UserDao {
	
	public void save(Connection connection,User user)throws SQLException;
	public void update(Connection connection,Long id,User user)throws SQLException;
	public void delete(Connection connection,User user)throws SQLException;
	public ResultSet select(Connection connection,User user)throws SQLException;
}
