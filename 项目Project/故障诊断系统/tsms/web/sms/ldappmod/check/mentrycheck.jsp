<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>量具入库查看</title>
		<%@ include file="/web/inc/jsp/head_ext.jsp" %>
	</head>
	<body scroll="no" oncontextmenu="return false">
		<script type="text/javascript">
		</script>
		<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Expires","0");
		%>
		<div id="querydiv"></div>
		<div id="document.body"></div>
		<script type="text/javascript" src="mentrycheck.js"></script>
	</body>
</html>