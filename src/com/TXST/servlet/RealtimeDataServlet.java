package com.TXST.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TXST.util.ReceiveDataSocket;

import test_Dao.Envir_InfoDaoImpl;
import test_entity.Environment_Info;
import test_util.ConnectionFactory;
import test_util.Pocket;

/**
 * Servlet implementation class TestServlet
 */
// @WebServlet("/TestServlet")
public class RealtimeDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private ReceiveDataSocket socketTest;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RealtimeDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		System.out.println(Thread.activeCount());
		System.out.println(Thread.currentThread());
		socketTest = new ReceiveDataSocket();
//		socketTest.receive();

		thread = new Thread(socketTest);
		thread.start();

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		// thread.stop();
		System.out.println("destroyed");
	}

	// @Override
	// protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
	// throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// //super.service(arg0, arg1);
	// System.out.println("service");
	// }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		System.out.println("doget");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		// response.getOutputStream().write("success".getBytes());
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
		System.out.println("连接到服务器了呢~~~");
		PrintWriter out = response.getWriter();

		if (request.getParameter("command").equals("realtimeData")) {

			String temValue = ""+Pocket.getTemValue();
			String humValue = ""+Pocket.getHumValue();
			String realtimeData = socketTest.getRealTimeData();
			// String realtimeData = socketTest.getRealTimeData("TXST");
//			String realtimeData = "username:TXST+password:TXST1234+sensor:tem+name:tem1+value:18" + "+temValue="
//					+ temValue + "+humValue=" + humValue;
			out.println(realtimeData);
			
		} else {

			String time = request.getParameter("time").replace("/", "-") + ":00";
			System.out.println(Timestamp.valueOf(time));

			Connection connection = ConnectionFactory.getConnection();
			Envir_InfoDaoImpl eInfoDao = new Envir_InfoDaoImpl();
			Environment_Info eInfo = new Environment_Info();
			int temValue = 0;
			int humValue = 0;

			// temperature
			eInfoDao.setTable("tem");
			eInfo.setTime(Timestamp.valueOf(time));
			eInfo.setUsername("TXST");
			try {
				ResultSet rSet = eInfoDao.selectHourValue(connection, eInfo);
				while (rSet.next()) {
					temValue = rSet.getInt("avg(value)");
					System.out.println(temValue);
					System.out.println(rSet.getFloat("avg(value)"));
				}
				// out.println(temValue);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// huminity
			eInfoDao.setTable("hum");
			try {
				ResultSet rSet = eInfoDao.selectHourValue(connection, eInfo);
				while (rSet.next()) {
					humValue = rSet.getInt("avg(value)");
					System.out.println(humValue);
					System.out.println(rSet.getFloat("avg(value)"));
				}
				// out.println(humValue);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("temValue:" + temValue + "+humValue:" + humValue);
		}

		out.flush();
		out.close();
	}

}
