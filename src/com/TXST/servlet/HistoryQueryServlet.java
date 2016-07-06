package com.TXST.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import test_Dao.Envir_InfoDaoImpl;
import test_Dao.UserDaoImpl;
import test_entity.Environment_Info;
import test_entity.User;
import test_util.ChangeToJson;
import test_util.ConnectionFactory;

/**
 * Servlet implementation class HistoryQueryServlet
 */
public class HistoryQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryQueryServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("HistoryQueryServlet~~~");

		if (request.getParameter("command").equals("tem") || request.getParameter("command").equals("hum")) {

			PrintWriter out = response.getWriter();
			// String time = request.getParameter("time").replace("/", "-") +":00";

			Connection connection = ConnectionFactory.getConnection();
			Envir_InfoDaoImpl eInfoDao = new Envir_InfoDaoImpl();
			Environment_Info eInfo = new Environment_Info();
//			Map<String, Object> map = new HashMap<String, Object>();
			JSONObject json = new JSONObject();

			eInfoDao.setTable(request.getParameter("command"));
			System.out.println(request.getParameter("command"));
			System.out.println(request.getParameter("endtime"));
			System.out.println(request.getParameter("endtime").equals(""));
			if (request.getParameter("endtime").equals("")) {
				
				eInfo.setTime(new Timestamp(System.currentTimeMillis()));
				// System.out.println(new Timestamp(System.currentTimeMillis()));
			} else {

				eInfo.setTime(Timestamp.valueOf(request.getParameter("endtime") + " 00:00:00"));
			}
//				eInfo.setTime(Timestamp.valueOf("2016-05-18 00:00:00"));
			eInfo.setUsername("TXST");
			try {
				ResultSet rSet = eInfoDao.select7DaysValue(connection, eInfo);
				while (rSet.next()) {

					System.out.println(rSet.getDate("dat"));
					// System.out.println(rSet.getFloat("avg(value)"));
					System.out.println(rSet.getInt("avg(value)"));
					json.accumulate("dat", rSet.getDate("dat").toString());
					json.accumulate("avgValue", rSet.getInt("avg(value)"));

//					map.put("dat", rSet.getDate("dat"));
//					map.put("avgValue", rSet.getInt("avg(value)"));
				}
//				String jString = ChangeToJson.mapToJson(map);
//				System.out.println(jString);
//				String[] result = { "{dat:'2016-04-29',avgValue:'29'}", 
//						"{dat:'2016-04-28',avgValue:'28'}",
//						"{dat:'2016-04-25',avgValue:'25'}" };
//				json.put("list", result);
				out.println(json);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.flush();
			out.close();
		}
	}

}
