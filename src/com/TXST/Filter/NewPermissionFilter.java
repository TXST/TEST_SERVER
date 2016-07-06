package com.TXST.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class NewPermissionFilter
 */
//@WebFilter("/NewPermissionFilter")
public class NewPermissionFilter implements Filter {
	

	private FilterConfig config ;
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		config  = fConfig;
	}
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		config = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String loginPath = config.getInitParameter("loginPath");
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpSession hSession = hRequest.getSession();
		String urlPath = hRequest.getServletPath();
		System.out.println("urlPath:"+urlPath);
		System.out.println("username:"+hSession.getAttribute("username"));
		System.out.println(!urlPath.equals(loginPath));
		if (hSession.getAttribute("username") == null 
				&& !urlPath.equals(loginPath)) {
			request.setAttribute("tip", "这位同学，你还没有登录呢~~");
			System.out.println("并没有登录-_-");
//			request.getRequestDispatcher(loginPath).forward(request, response);
			System.out.println(((HttpServletRequest)request).getContextPath());
			((HttpServletResponse)response).setHeader("Cache-Control", "no-cache,no-store");
			((HttpServletResponse)response).setHeader("Pragma", "no-cache");
			((HttpServletResponse)response).setDateHeader("EXpires", 0);
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/TXST/login.html");
			
		}else {
			
			chain.doFilter(request, response);
		}
		
	}

}
