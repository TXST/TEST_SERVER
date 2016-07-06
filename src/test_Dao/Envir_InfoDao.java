package test_Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import test_entity.Environment_Info;

public interface Envir_InfoDao {
	
	public void save(Connection connection,Environment_Info environment_Info)throws SQLException;
	public void update(Connection connection,Long id,Environment_Info environment_Info)throws SQLException;
	public void delete(Connection connection,Environment_Info environment_Info)throws SQLException;
	public ResultSet select(Connection connection,Environment_Info environment_Info)throws SQLException;
	public ResultSet selectHourValue(Connection connection, Environment_Info environment_Info) throws SQLException;
	public ResultSet selectDayValue(Connection connection, Environment_Info environment_Info) throws SQLException;
	public ResultSet select7DaysValue(Connection connection, Environment_Info environment_Info) throws SQLException;

}
