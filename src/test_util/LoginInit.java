package test_util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import test_Dao.ThresholdDao;
import test_entity.ThresholdTab;

public class LoginInit implements Runnable {
	
	private String username;
	
	public LoginInit(String username) {
		// TODO Auto-generated constructor stub
		this.username = username;
		System.out.println("login init");
	}

//	protected void GetThreshold(String username) {
//
//		new Thread(new Runnable() {
//
//			public void run() {
//
//			}
//		});
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		ThresholdTab tab = new ThresholdTab();
		ThresholdDao tabDao = new ThresholdDao();
		System.out.println(username);				//这句没有打印出来

		tab.setUsername(username);
		tab.setSensor("tem");
		tab.setName("tem1");
		// tab.setValue(30);
		try {
			// tabDao.update(connection, tab);
			ResultSet rSet = tabDao.select(connection, tab);
			while (rSet.next()) {
				System.out.println(rSet.getInt("value"));
				Pocket.setTemValue(rSet.getInt("value"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		tab.setSensor("hum");
		tab.setName("hum1");
		try {
			ResultSet rSet = tabDao.select(connection, tab);
			while (rSet.next()) {
				System.out.println(rSet.getInt("value"));
				Pocket.setHumValue(rSet.getInt("value"));
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
