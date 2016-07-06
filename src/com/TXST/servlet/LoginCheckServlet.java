package com.TXST.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test_entity.User;
import test_service.Alarm_Service;
import test_service.CheckUser_Service;
import test_util.ConnectionFactory;
import test_util.LoginInit;

/**
 * Servlet implementation class LoginCheckServlet
 */
// @WebServlet("/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheckServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("连接到login check了呢~~~");
		response.setHeader("Access-Control-Allow-Origin", "*");

		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemotePort());

		String username = request.getParameter("username");
		
		PrintWriter out = response.getWriter();
		User user = new User();
		user.setUsername(username);
		user.setPassword(request.getParameter("password"));
		Connection connection = ConnectionFactory.getConnection();
		CheckUser_Service service = new CheckUser_Service();
		if (service.check(user, connection)) {
			request.getSession().setAttribute("username", username);
			Thread thread = new Thread(new LoginInit("TXST"));
			thread.start();
			if (request.getParameter("client").equals("Android")) {
				
//				Thread thread1 = new Thread(new Alarm_Service(request.getRemoteAddr(), 12589, "TXST"));
				Thread thread1 = new Thread(new Alarm_Service());
				thread1.start();
			}
			out.print("login success");
		} else {
			out.print("login failed");
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
