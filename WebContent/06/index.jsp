<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>

<a href="<%=request.getContextPath() %>/06/hello.jsp">想看先登录</a><br>

<% 
	String flag = ""; 
	Object object = request.getSession().getAttribute("flag");
	if(object!=null){
		flag = object.toString();
	}
	System.out.println(flag);
%>

<% if(flag.equals("login_success")){%>

<a href="<%=request.getContextPath() %>/06/login_out.jsp">退出</a>

<%}else { %>

<a href="<%=request.getContextPath() %>/06/login.jsp">登录</a>

<%} %>

</body>
</html>