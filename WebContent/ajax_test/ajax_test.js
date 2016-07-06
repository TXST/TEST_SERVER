function ajaxFunction(){

	var xmlHttp;
	try{
		xmlHttp = new XMLHttpRequest();
		
	}catch(e){
		
		try{
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			
		}catch(e){
			
			try{
				
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
				
			}
		}
	}
	return xmlHttp;
}

window.onload = function(){
	
	document.getElementById("ok").onclick = function(){
		
		var divcheck = document.getElementById("divcheck");
		var xmlHttp = ajaxFunction();
		xmlHttp.onreadystatechange = function(){
			
			if(xmlHttp.readyState == 1){
				
				divcheck.innerHTML = "<img src='./1.jpg' >正在与服务器取得连接"
				alert("1");
			}
			else if(xmlHttp.readyState == 2){
				
				divcheck.innerHTML = "<img src='./4.jpg' >正在向服务器发送请求"
				alert("2");
			}
			else if(xmlHttp.readyState == 3){
				
				divcheck.innerHTML = "<img src='./6.jpg' >服务器正在处理数据"
				alert("3");
			}
			else if(xmlHttp.readyState == 4){
				
				alert("4");
				if(xmlHttp.status == 200 || xmlHttp.status == 304){
					
					var data = xmlHttp.responseText;
					divcheck.innerHTML = data;
					alert("200");
					
				}
			}
			else{
				
				divcheck.innerHTML = "服务器无响应可能是挂了"
			}
		}
		xmlHttp.open("POST","/JSP_TEST/LoadServlet",true);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttp.send(null);
	}
}




