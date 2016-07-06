package test_Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;

import test_entity.Environment_Info;
import test_util.ConnectionFactory;

public class Envir_InfoDaoImpl implements Envir_InfoDao {

	private String table;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public void save(Connection connection, Environment_Info environment_Info) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into " + table + " (username,name,value,time,info) values (?,?,?,?,?)";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, environment_Info.getUsername());
		pStatement.setString(2, environment_Info.getName());
		pStatement.setInt(3, environment_Info.getValue());
		pStatement.setTimestamp(4, environment_Info.getTime());
		pStatement.setString(5, environment_Info.getInfo());

		pStatement.execute();
	}

	@Override
	public void update(Connection connection, Long id, Environment_Info environment_Info) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update " + table + " set username=?,name=?,value = ?,time = ?,info=? where id = ?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, environment_Info.getUsername());
		pStatement.setString(2, environment_Info.getName());
		pStatement.setInt(3, environment_Info.getValue());
		pStatement.setTimestamp(4, environment_Info.getTime());
		pStatement.setString(5, environment_Info.getInfo());
		pStatement.setLong(6, id);
		pStatement.execute();
	}

	@Override
	public void delete(Connection connection, Environment_Info environment_Info) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from " + table + " where id = ?";
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, environment_Info.getId());
		preparedStatement.execute();
	}

	// 普通查询
	@Override
	public ResultSet select(Connection connection, Environment_Info environment_Info) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from " + table + " where time<?";
		// String sql = "select * from"+table+"where name=?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setTimestamp(1, environment_Info.getTime());
		return pStatement.executeQuery();
	}

	// 查询某时刻平均值 前一个SQL需要传入整点时刻 后一个不需要
	@Override
	public ResultSet selectHourValue(Connection connection, Environment_Info environment_Info) throws SQLException {
		// TODO Auto-generated method stub
		// String sql = "select avg(value) from "+table+" where username=? and
		// (time between (SELECT ? - INTERVAL 1 HOUR) and ?)";
		String sql = "select avg(value) from " + table
				+ " where username=? and (date_format(time ,'%Y-%m-%d %H ')=date_format(? ,'%Y-%m-%d %H '))";
		// SELECT '1997-12-31 23:00:00' - INTERVAL 1 HOUR;
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, environment_Info.getUsername());
		pStatement.setTimestamp(2, environment_Info.getTime());
		// pStatement.setTimestamp(3, environment_Info.getTime());
		return pStatement.executeQuery();
	}

	// 查询单日平均值 前一个SQL需要传入整点时刻 后一个不需要
	@Override
	public ResultSet selectDayValue(Connection connection, Environment_Info environment_Info) throws SQLException {
		// TODO Auto-generated method stub
		// String sql = "select avg(value) from "+table+" where (time between
		// (SELECT ? - INTERVAL 1 DAY) and ?)and username=?";
		String sql = "select avg(value) from " + table
				+ " where username=? and (date_format(time ,'%Y-%m-%d ')=date_format(? ,'%Y-%m-%d '))";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, environment_Info.getUsername());
		pStatement.setTimestamp(2, environment_Info.getTime());
		// pStatement.setTimestamp(3, environment_Info.getTime());
		return pStatement.executeQuery();
	}

	// 查询七天（传入时间之前的七天）每天的平均值
	@Override
	public ResultSet select7DaysValue(Connection connection, Environment_Info environment_Info) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select date_format(time, '%Y-%m-%d')as dat,avg(value) from " + table
				+ " where username=? and (time between date_format((SELECT ? - INTERVAL 7 DAY) , '%Y-%m-%d') and date_format(?, '%Y-%m-%d')) group by dat";

		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, environment_Info.getUsername());
		pStatement.setTimestamp(2, environment_Info.getTime());
		pStatement.setTimestamp(3, environment_Info.getTime());
		return pStatement.executeQuery();
	}

	@Test
	public void DB_test() {

		Connection connection = ConnectionFactory.getConnection();

		Envir_InfoDaoImpl eInfoDao = new Envir_InfoDaoImpl();
		eInfoDao.setTable("tem");
		Environment_Info eInfo = new Environment_Info();
		eInfo.setTime(Timestamp.valueOf("2016-4-29 17:00:00"));
		eInfo.setUsername("TXST");

		try {

			// ResultSet rSet = eInfoDao.selectHourValue(connection, eInfo);
			ResultSet rSet = eInfoDao.select7DaysValue(connection, eInfo);
			while (rSet.next()) {

				// System.out.println(rSet.getInt("avg(value)"));
				System.out.println(rSet.getDate("dat"));
				System.out.println(rSet.getFloat("avg(value)"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
