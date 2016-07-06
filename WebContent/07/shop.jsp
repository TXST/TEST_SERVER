<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择购买物品</title>

<script type="text/javascript">



</script>
</head>
<body>
	
	<form action="<%= request.getContextPath() %>/ShopCartServlet" method="post">
	
		book <input type="submit" name="book" value="加入购物车"><br>
		computer <input type="submit" name="computer" value="加入购物车"><br>
		ipad <input type="submit" name="ipad" value="加入购物车"><hr>
		<a href="<%= request.getContextPath() %>/07/ShopCart.jsp">我的购物车</a>
	
	</form>	
		
	
</body>
</html>