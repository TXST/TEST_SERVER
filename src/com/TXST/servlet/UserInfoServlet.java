package com.TXST.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import test_Dao.UserDaoImpl;
import test_entity.User;
import test_util.ConnectionFactory;

/**
 * Servlet implementation class UserInfoServlet
 */
// @WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("command"));

		PrintWriter out = response.getWriter();
		User user = new User();
		user.setUsername(request.getParameter("username"));
		UserDaoImpl uImpl = new UserDaoImpl();
		Connection connection = ConnectionFactory.getConnection();

		if (request.getParameter("command").equals("user_edit")) {	//编辑
			
//			System.out.println("update");
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setPhonenumber(request.getParameter("phonenumber"));
			try {

				uImpl.updateByUsername(connection, user);
				out.print("update success");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("update Exception");
			}

		} else {													//查询

			JSONObject json = new JSONObject();
			try {

				ResultSet rSet = uImpl.selectByUsername(connection, user);
				while (rSet.next()) {

					System.out.println(rSet.getString("username"));
					System.out.println(rSet.getString("password"));
					System.out.println(rSet.getString("email"));
					System.out.println(rSet.getString("phonenumber"));

					json.accumulate("username", rSet.getString("username"));
					json.accumulate("password", rSet.getString("password"));
					json.accumulate("email", rSet.getString("email"));
					json.accumulate("phonenumber", rSet.getString("phonenumber"));
				}
				out.print(json);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("Exception");
			}

		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
	}

}
