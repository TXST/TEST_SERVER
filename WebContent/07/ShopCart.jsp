<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的购物车</title>


<script type="text/javascript">

<!--

$(".add").click(function(){  
	$(this).prev().val(parseInt($(this).prev().val()) + 1);
	setTotal();
});

$(".min").click(function(){
	$(this).prev().val(parseInt($(this).prev().val()) - 1);
	setTotal();
});

-->

</script>



</head>
<body>


	<form action="<%= request.getContextPath() %>/PayServlet" method="post">
	
		<%String goodNum= "";
		
		
		
		
		%>
	
	
		<input type="button" class="min" name="" value="-">
		<input type="text" class="text_box" name="goodnum" 
		value="" style="width:25px;">							<!-- ${item.value.quantity } -->
		<input type="button" class="add" name="" value="+">
		<input type="submit" value="buy">
		
	</form>
	


</body>
</html>