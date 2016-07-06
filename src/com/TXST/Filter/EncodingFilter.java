package com.TXST.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


//编码
public class EncodingFilter implements Filter {

	
	private String charEncoding = null;
	
    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    	
    	
    }
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		charEncoding = fConfig.getInitParameter("ecoding");
		if (charEncoding == null) {
			throw new ServletException("without ecoding config");
		}
	}
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		if (!charEncoding.equals(request.getCharacterEncoding())) {
			
			request.setCharacterEncoding(charEncoding);
		}
		
		response.setCharacterEncoding(charEncoding);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
