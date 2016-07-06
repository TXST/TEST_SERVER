package test_Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import test_entity.User;


public class UserDaoImpl implements UserDao {
	
	
	@Override
	public void save(Connection connection, User user) throws SQLException {
		// TODO Auto-generated method stub
		//String sql = "insert into "+table+" (name,password,email) values(?,?,?)";
		String sql = "insert into users (username,password,email,phonenumber) values(?,?,?,?)";
		java.sql.PreparedStatement preparedStatement = connection.prepareCall(sql);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPhonenumber());
		preparedStatement.execute();

	}

	@Override
	public void update(Connection connection, Long id, User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update users set username = ?,password = ?,email = ?,phonenumber = ? where id = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPhonenumber());
		preparedStatement.setLong(5, id);
		preparedStatement.execute();

	}
	public void updateByUsername(Connection connection, User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update users set password = ?,email = ?,phonenumber = ? where username = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getPassword());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getPhonenumber());
		preparedStatement.setString(4, user.getUsername());
		preparedStatement.execute();
		
	}

	@Override
	public void delete(Connection connection, User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from users where id = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, user.getId());
		preparedStatement.execute();

	}

	@Override
	public ResultSet select(Connection connection, User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from users where username = ? and password = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		
		return preparedStatement.executeQuery();
	}
	public ResultSet selectByUsername(Connection connection, User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from users where username = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getUsername());
		
		return preparedStatement.executeQuery();
	}

}
