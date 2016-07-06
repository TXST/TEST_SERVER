package com.TXST.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import com.TXST.Dao.userDaoimpl;
import com.TXST.entity.User;
import com.TXST.util.connectionFactory;

public class jdbcTest {
	
	
//	public static Connection getConnection() {
//		
//		Connection conn = null;
//		
//		Long starTime = System.currentTimeMillis();
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_db", "root", "TXST1234");
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Long endTime = System.currentTimeMillis();
//		Long spendTime = endTime - starTime;
//		
//		System.out.println("连接耗时"+spendTime+"ms");
//		
//		return conn;
//	}
//	
	public static void select() {
		
		String sql = "select * from tbl_user";
		String sql1 = "select * from tbl_address";
		Connection conn = connectionFactory.getConnection();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				
//				System.out.println(rs.getInt("id"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getString("password"));
//				System.out.println(rs.getString("email"));
				
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
				
				System.out.println();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				
				if(rs!=null) {rs.close();}
				if(st!=null) {st.close();}
				if(conn!=null) {conn.close();}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}
//	
	public static void update() {
		
		String sql = "update tbl_user set id = '4' where id = '23'";
		String sql1 = "update tbl_address set country = 'China' where country = 'china'";
		Connection conn = connectionFactory.getConnection();
		Statement st = null;

		try {
			st = conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("updata"+count+"条记录");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				
				st.close();
				conn.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}
//	
//	public static void insert() {
//		
//		String sql = "insert into tbl_address (city,country,user_id) values ('shanghai','China',3)";
//		Connection conn = getConnection();
//		Statement st = null;
//
//		try {
//			st = conn.createStatement();
//			int count = st.executeUpdate(sql);
//			System.out.println("insert"+count+"条记录");
//				
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				
//				st.close();
//				conn.close();
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
	public static void delete() {
		
		String sql = "delete from tbl_user where id > 4";
		Connection conn = connectionFactory.getConnection();
		Statement st = null;

		try {
			st = conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("delete"+count+"条记录");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				
				st.close();
				conn.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void insertIntoUser(Connection conn) throws SQLException {
		
		String sql = "insert into tbl_user (name,password,email) values ('nyl','1234','1234.org')";
		
			Statement statement = conn.createStatement();
			int count = statement.executeUpdate(sql);
			System.out.println("insert"+count+"条记录");
		
	}
	public static void insertIntoAddress(Connection conn) throws SQLException {
		
		String sql = "insert into tbl_address (id,city,country,user_id) values (1,'hebei','china',5)";
		
			Statement statement = conn.createStatement();
			int count = statement.executeUpdate(sql);
			System.out.println("insert"+count+"条记录");
		
	}
	
	public static void transactionTest() {
		
		Long start1 = System.currentTimeMillis();
//		connectionFactory conn =connectionFactory.getInstance();
//		connectionFactory conn =new connectionFactory();
		Long start2 = System.currentTimeMillis();
//		Connection connection = conn.getConnection();
		Connection connection = connectionFactory.getConnection();
		Long end = System.currentTimeMillis();
		
		System.out.println("link to database cost :"+(end-start2)+"ms");
		System.out.println("总耗时:"+(end-start1)+"ms");
		
		try {
			connection.setAutoCommit(false);
			insertIntoUser(connection);
			insertIntoAddress(connection);
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL exception");
			e.printStackTrace();
			try {
				connection.rollback();
				System.out.println("rollback!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
//				System.out.println("rollback false");
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
//	@Test
	public static void Dao_test() {
		
		Connection connection = connectionFactory.getConnection();
		userDaoimpl uDaoimpl = new userDaoimpl();
//		uDaoimpl.setTable("tbl_user");
		User user = new User();
//		user.setId((long) 4);
		user.setName("jiMaster");
		user.setPassword("1234");
		user.setEmail("jimaster.com");
		try {
			uDaoimpl.save(connection, user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		update();
//		insert();
//		delete();
//		transactionTest();
		
		Dao_test();
		select();
		
		
	}
		

}
