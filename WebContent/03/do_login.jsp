<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String username = request.getParameter("username");
String password = request.getParameter("password");
out.print(username);
out.println();
out.print(password);
if(username.equals("qiao") && password.equals("qiao")){
	session.setAttribute("username", username);
	response.setHeader("refresh", "0;URL = welcome.jsp");
}

%>