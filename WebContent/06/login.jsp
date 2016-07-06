<%@page import="com.TXST.cookies.MyConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆page</title>

<style type="text/css">
	body{
		color: #000;
		font-size:14px;
		margin: 20px auto;
	}
</style>

<script type="text/javascript">

function check(form) {
	
	if (document.forms.loginForm.username.value == "") {
		
		alert("请输入用户名");
		document.forms.loginForm.username.focus();
		return false;
	} 
	if (document.forms.loginForm.password.value == "") {
		
		alert("请输入密码");
		document.forms.loginForm.password.focus();
		return false;
	} 

}
</script>

</head>

<body>
	<form action="<%= request.getContextPath() %>/LoginServlet06" method="post" name="loginForm">
	
	
		<%
		String username = "";
		String password = "";
		Cookie cookies[] = request.getCookies();
		for(int i = 0;cookies!=null && i < cookies.length;i++){
			Cookie c=cookies[i];
			if(MyConstant.USERNAME.equals(c.getName())){
				username=c.getValue();
			}
			if(MyConstant.PASSWORD.equals(c.getName())){
				password=c.getValue();
			}
		}
		%>
		
		<%if(request.getAttribute("return_url")!=null) {%>
		<input type="hidden" name="return_url" value="<%=request.getAttribute("return_url")%>">
		<%} %>
		
		用户名：<input type="text" name="username" value=<%=username %>><br><br>
		密码：<input type="password" name="password" value=<%=password %>><br><br>
		<%if(request.getSession().getAttribute("msg")!=null) {%>
		<%=request.getSession().getAttribute("msg").toString() %>
		<%} %><br>
		<input type="checkbox" name="remember" checked="checked">记住用户名和密码<br>
		<input type="submit" value="login" onclick="return check(this);">
		<input type="reset" value="reset">

	</form>
</body>
</html>