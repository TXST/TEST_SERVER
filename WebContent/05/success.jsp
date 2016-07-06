<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>

<style type="text/css">

	body{
		color: #000;
		font-size:14px;
		margin: 20px auto;
	}
	
	#msg{
		text-align: center;
	}
</style>

</head>
<body>

<div id="msg">
	登录成功<br>
	用户名：<%=request.getParameter("username") %> <br>
	密码：<%=request.getParameter("password") %> <br>
	<a href="<%=request.getContextPath() %>/05/login.jsp">返回登录</a>

</div>

</body>
</html>