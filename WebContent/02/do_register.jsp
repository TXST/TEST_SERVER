<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>do_register</title>
</head>
<body>


<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	out.println(username);
	out.println("<br/>");
	out.println(password);
%>	

<br/>

<jsp:useBean id="user"  class="com.TXST.entity.TestEntity"></jsp:useBean>
<jsp:setProperty property="username" name="user"/>
<jsp:setProperty property="password" name="user"/>

<jsp:getProperty property="username" name="user"/>
<jsp:getProperty property="password" name="user"/>
<br/>


</body>
</html>