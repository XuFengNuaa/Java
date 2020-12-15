<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ page import="com.nuaa.sys.util.Logger"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>出错页面</title>
</head>
<body bgcolor="black" text="#FFFFFF">  
<h1>出错信息</h1>
<br>
<pre><%= exception.toString() %>
<%
exception.printStackTrace();
%>
</pre>
<%

Logger.debug("出错信息:"+exception.toString());
%>
</body>  
</html>