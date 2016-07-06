package com.TXST.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test_Dao.UserDaoImpl;
import test_entity.User;
import test_service.CheckUser_Service;
import test_util.ConnectionFactory;

/**
 * Servlet implementation class RegisterServlet
 */
// @WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		// doPost(request, response);
		System.out.println("do get");
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
		System.out.println("连接到register servlet了呢~~~");
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("command"));

		PrintWriter out = response.getWriter();
		User user = new User();
		user.setUsername(request.getParameter("username"));
		Connection connection = ConnectionFactory.getConnection();

		CheckUser_Service service = new CheckUser_Service();		//不论何种请求，先检验用户名
		if (!service.usernameIsExist(user, connection)) {
			
			if (request.getParameter("command").equals("check")) {	//如果是检验用户名请求

				out.print("ok");
			} else {												//如果是注册请求

				user.setPassword(request.getParameter("password"));
				user.setEmail(request.getParameter("email"));
				user.setPhonenumber(request.getParameter("phonenumber"));

				UserDaoImpl uImpl = new UserDaoImpl();
				try {

					uImpl.save(connection, user);
					out.print("register success");
					request.getSession().setAttribute("username", request.getParameter("username"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					out.print("register exception");
					e1.printStackTrace();
				}

			}

		}else {						//检验未通过（用户名已占用）
			
			out.print("used");
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
