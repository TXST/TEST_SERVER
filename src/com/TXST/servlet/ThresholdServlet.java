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
import test_Dao.ThresholdDao;
import test_entity.ThresholdTab;
import test_util.ConnectionFactory;
import test_util.Pocket;

/**
 * Servlet implementation class ThresholdServlet
 */
//@WebServlet("/ThresholdServlet")
public class ThresholdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThresholdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("command"));

		PrintWriter out = response.getWriter();
		Connection connection = ConnectionFactory.getConnection();
		ThresholdTab tab = new ThresholdTab();
		ThresholdDao tabDao = new ThresholdDao();

		if (request.getParameter("command").equals("threshold_edit")) {	//编辑
			
//			System.out.println("update");
			System.out.println(request.getParameter("tem_threshold"));
			tab.setUsername("TXST");
			tab.setSensor("tem");
			tab.setName("tem1");
		    tab.setValue(Integer.valueOf(request.getParameter("tem_threshold")));
			try {
				tabDao.update(connection, tab);
				Pocket.setTemValue(Integer.valueOf(request.getParameter("tem_threshold")));
				out.print("update tem success&");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println(request.getParameter("hum_threshold"));
			tab.setSensor("hum");
			tab.setName("hum1");
			tab.setValue(Integer.valueOf(request.getParameter("hum_threshold")));
			try {
				tabDao.update(connection, tab);
				Pocket.setHumValue(Integer.valueOf(request.getParameter("hum_threshold")));
				out.print("update hum success");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {													//查询
			
			JSONObject json = new JSONObject();
			tab.setUsername("TXST");
			tab.setSensor("tem");
			tab.setName("tem1");
			try {
				ResultSet rSet = tabDao.select(connection, tab);
				while (rSet.next()) {
					System.out.println(rSet.getInt("value"));
					json.accumulate("temValue", rSet.getInt("value"));
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
					json.accumulate("humValue", rSet.getInt("value"));
				}
				out.print(json);
			} catch (SQLException e) {
				e.printStackTrace();
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
