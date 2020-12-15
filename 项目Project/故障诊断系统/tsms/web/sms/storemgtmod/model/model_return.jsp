<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<%@page import="com.nuaa.sys.util.Logger"%>
<%@ page import="com.nuaa.sys.util.AppInsFactory,com.nuaa.app.Login"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
%>
<title>专家打分</title>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
  
</head>
<body oncontextmenu="return false">
<script type="text/javascript">
</script>

<div id="partdescdiv"></div>
<div id="selectdiv"></div>
<div id="featurediv"></div>
<div id="griddiv"></div>
<div id="gridsdiv"></div>
<div id="querydiv"></div>

<script type="text/javascript" src="model_return.js"></script>
<link type="text/css" rel="stylesheet" href="demo/css/application.css">
  <script type="text/javascript" src="demo/js/jquery.min.js"></script>
  <script type="text/javascript" src="lib/jquery.raty.min.js"></script>
<script type="text/javascript">$('#star').raty({path:'lib/img'});</script>

</body>
</html>