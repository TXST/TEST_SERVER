package com.TXST.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//权限过滤
public class PermissionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PermissionFilter() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		
		String servletPath = request2.getServletPath();
		
		HttpSession session = request2.getSession();
		String flag = (String) session.getAttribute("flag");
		
		System.out.println("permissionFilter:"+servletPath);
//		System.out.println("permissionFilter:"+flag);
		
		if (servletPath.equals("/06/hello.jsp") && (flag == null ||!flag.equals("login_success"))) {
			String forward = "/06/login.jsp";
			request2.setAttribute("return_url", servletPath);
			RequestDispatcher rDispatcher = request2.getRequestDispatcher(forward);
			rDispatcher.forward(request2, response2);
		} 
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
