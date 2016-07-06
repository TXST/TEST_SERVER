<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
session.invalidate();
request.setAttribute("flag", "login_out");
response.setHeader("refresh", "0;URL = index.jsp");
%>