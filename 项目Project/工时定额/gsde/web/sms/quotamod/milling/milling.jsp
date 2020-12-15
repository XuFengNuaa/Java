<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/web/inc/jsp/head_ext.jsp"%>
</head>
<body>
<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires","0");
		%>
<div id="querydiv" ></div>
<div id="selectdiv" ></div>
<div id="simdiv" ></div>
<script type="text/javascript" src="milling.js"></script>
</body>
</html>