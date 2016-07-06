package test_Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import test_entity.ThresholdTab;
import test_util.ConnectionFactory;

public class ThresholdDao {


	public void update(Connection connection, ThresholdTab tab) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update threshold set value = ? where username = ? and sensor = ? and name = ?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setInt(1, tab.getValue());
		pStatement.setString(2, tab.getUsername());
		pStatement.setString(3, tab.getSensor());
		pStatement.setString(4, tab.getName());
		pStatement.execute();
	}


	public ResultSet select(Connection connection, ThresholdTab tab) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select value from threshold where username = ? and sensor = ? and name = ?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, tab.getUsername());
		pStatement.setString(2, tab.getSensor());
		pStatement.setString(3, tab.getName());
		return pStatement.executeQuery();
	}
	
}
