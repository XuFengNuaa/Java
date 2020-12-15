<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<%@page import="com.nuaa.sys.util.Logger"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title></title>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>

<%
String sclassname = "";

if(request.getParameter("sclassname")!=null){    //专业分类
	sclassname = request.getParameter("sclassname");
}

%>
<script>
var sclassname = "<%=sclassname%>";
</script>
</head>
<body>
<div id='center'></div>
<script type="text/javascript" src="entrycheck.js"></script>
</body>
</html>