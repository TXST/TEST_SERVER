package com.TXST.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TXST.cookies.MyConstant;
import com.TXST.entity.User;
import com.TXST.service.checkUserService;


public class LoginServlet06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet06() {
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
//		doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String return_url = request.getParameter("return_url");
		String check = request.getParameter("remember");
		
		System.out.println("username===>"+username);
		System.out.println("password===>"+password);
		System.out.println("return_url===>"+return_url);
		System.out.println("check===>"+check);
		
		String forward = null;
		RequestDispatcher rDispatcher = null;
			
			User user = new User();
			user.setName(username);
			user.setPassword(password);
			checkUserService cUserService = new checkUserService();
		
			
	//***********************************************************************************//
			
			if (check != null) {
				Cookie cookie = new Cookie(MyConstant.USERNAME, username);
				Cookie cookie2 = new Cookie(MyConstant.PASSWORD, password);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(Integer.MAX_VALUE);
				cookie2.setPath(request.getContextPath());
				cookie2.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(cookie);
				response.addCookie(cookie2);
			}
			
			
			if (cUserService.check(user)) {
				
				request.getSession().setAttribute("flag", "login_success");
				if (return_url!= null) {
					forward = return_url;
					
				} else {
					forward = "/06/index.jsp";

				}
				
			} else {
				request.setAttribute("flag", "login_error");
				
				request.getSession().setAttribute("msg", "用户名或密码错误");
				forward = "/06/login.jsp";

			}
		
		rDispatcher = request.getRequestDispatcher(forward);
		rDispatcher.forward(request, response);		
		
	}

}
