<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>
</head>
<body>
<%if(session.getAttribute("username")!=null){%>
welcome:<% out.print(session.getAttribute("username").toString()); %> 
<br>
	<a href="login_out.jsp">login out</a>
<% } else{ %>
please login first:
<a href="login.jsp">login</a>
<% }%>

<!--  
<%if(session.isNew()) {%>
welcome new qiaoqiao
<%} %>
-->

</body>
</html>