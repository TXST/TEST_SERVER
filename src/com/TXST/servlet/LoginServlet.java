package com.TXST.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TXST.entity.User;
import com.TXST.service.checkUserService;


public class LoginServlet extends HttpServlet {
	
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username===>"+username);
		System.out.println("password===>"+password);

//		System.out.println(request.getRequestURI());
//		System.out.println(request.getRequestURL());
//		System.out.println(request.getContextPath());
		
		String forward = null;
		RequestDispatcher rDispatcher = null;
		
//		if (username=="" || password=="") {
//			
//			request.setAttribute("msg", "用户名或密码为空");
//			forward = "/05/error.jsp";
//			
//		} else {
			
			User user = new User();
			user.setName(username);
			user.setPassword(password);
			checkUserService cUserService = new checkUserService();
			
			if (cUserService.check(user)) {
				
				forward = "/05/success.jsp";
				
			} else {
				
				request.setAttribute("msg", "用户名或密码错误");
				forward = "/05/error.jsp";

			}

		//}
		
		rDispatcher = request.getRequestDispatcher(forward);
		rDispatcher.forward(request, response);
		
		
/**********************************************************************************/
		/*
		
		if (username.equals("qiao") && password.equals("qiao")) {
			
			String forward = "/05/success.jsp";
			RequestDispatcher rDispatcher = request.getRequestDispatcher(forward);
			rDispatcher.forward(request, response);
			
//			response.sendRedirect(request.getContextPath()+"/05/success.jsp");
			
		} else {
			
			String forward = "/05/error.jsp";
			RequestDispatcher rDispatcher = request.getRequestDispatcher(forward);
			rDispatcher.forward(request, response);
			
//			response.sendRedirect(request.getContextPath()+"/05/error.jsp");

		}
		
		*/
		
/***************************************************************************************/
		
	}

	
	
}
