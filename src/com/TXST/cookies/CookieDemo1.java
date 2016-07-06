package com.TXST.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieDemo1
 */
public class CookieDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
//		doGet(request, response);
		
		
		response.setContentType("text/html;charset=UTF-8");		//"text/html; charset=UTF-8"
		PrintWriter out  = response.getWriter();		
		out.println("您的上次访问时间是：");
		
		Cookie cookies[] = request.getCookies();
		for (int i = 0;cookies!=null && i < cookies.length; i++) {
			
			Cookie cookie = cookies[i];
			if (MyConstant.Last_ACCESS_TIME.equals(cookie.getName())) {
				
				long value = Long.parseLong(cookie.getValue());
				out.print( new Date(value).toString());
//				out.println(cookie.getValue());
			}
			
		}
		
		Cookie c = new Cookie(MyConstant.Last_ACCESS_TIME, System.currentTimeMillis()+"");
		c.setPath(request.getContextPath());
		c.setMaxAge(10*24*60*60);
		response.addCookie(c);
	}

}
