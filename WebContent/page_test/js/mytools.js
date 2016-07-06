// 创建Ajax的核心对象(不兼容IE6)
function getXhr(){
	var xhr = null;//定义核心对象
	if(window.XMLHttpRequest){//其他浏览器
		xhr = new XMLHttpRequest();
	}else{//IE浏览器
		xhr = new ActiveXObject("Microsoft.XMLHttp");
	}
	return xhr;
}
// 创建Ajax的核心对象(兼容IE6)
function getXhr2(){
	var xhr = null;
	try{//其他浏览器
		xhr = new XMLHttpRequest();
	}catch(e){//IE浏览器
		try{//IE7+
			xhr = new ActiveXObject("Microsoft.XMLHttp");
		}catch(e){//IE6
			xhr = new ActiveXObject("Msxml2.XMLHttp");
		}
	}
	return xhr;
}
// 创建XML解析器
function getXml(xmlString){
	var xmlDoc = null;//定义XML DOM对象
	if(window.DOMParser){//其他浏览器
	    var parser = new DOMParser();//创建解析器
		xmlDoc = parser.parseFromString(xmlString,"text/xml");//利用解析器进行解析
	}else{//IE浏览器
	    xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async = false;
		xmlDoc.loadXML(xmlString);
	}
	return xmlDoc;
}