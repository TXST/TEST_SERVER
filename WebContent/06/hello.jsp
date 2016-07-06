<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hello</title>
</head>
<body>

<%//if(session.getAttribute("username")!=null){%>
welcome:<% //out.print(session.getAttribute("username").toString()); %> 
<br>
	<a href="<%=request.getContextPath() %>/06/index.jsp">return</a>
<%// } %>

</body>
</html>