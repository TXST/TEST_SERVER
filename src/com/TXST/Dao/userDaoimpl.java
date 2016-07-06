package com.TXST.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TXST.entity.User;

public class userDaoimpl implements UserDao {
	
//	private String table;
//	
//	public String getTable() {
//		return table;
//	}
//
//	public void setTable(String table) {
//		this.table = table;
//	}

	@Override
	public void save(Connection connection, User user) throws SQLException {
		// TODO Auto-generated method stub
		//String sql = "insert into "+table+" (name,password,email) values(?,?,?)";
		String sql = "insert into tbl_user (name,password,email) values(?,?,?)";
		java.sql.PreparedStatement preparedStatement = connection.prepareCall(sql);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.execute();

	}

	@Override
	public void update(Connection connection, Long id, User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update tbl_user set name = ?,password = ?,email = ? where id = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setLong(4, id);
		preparedStatement.execute();

	}

	@Override
	public void delete(Connection connection, User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from tbl_user where id = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, user.getId());
		preparedStatement.execute();

	}

	@Override
	public ResultSet select(Connection connection, User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from tbl_user where name = ? and password = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getPassword());
		
		return preparedStatement.executeQuery();
	}

}
