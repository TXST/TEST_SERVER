package com.TXST.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ShopCartServlet
 */
@WebServlet("/ShopCartServlet")
public class ShopCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		
		Enumeration<String> names = request.getParameterNames();
		
		String path = request.getContextPath();
		
		
		while(names.hasMoreElements()) {
			
//			if (names.nextElement().equals("book")) {
//				
//				System.out.println();
//				
//			}
//			PrintWriter out = response.getWriter();
//			out.println(names.nextElement());
			
			System.out.println(names.nextElement());
		}
		request.setAttribute("add", "ok");
		response.setHeader("refresh", "0;URL = "+path+"/07/shop.jsp");
		
	}

}
