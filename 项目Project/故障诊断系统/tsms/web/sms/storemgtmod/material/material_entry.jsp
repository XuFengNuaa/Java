<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.nuaa.sys.util.Logger"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
</head>
<body>
<body>
<div id="toolbar"></div>
<div id="querydiv"></div>

<%
String realname = (String)session.getAttribute("username");
%>
<script>
var realname = '<%=realname%>';
</script>
<script type="text/javascript" src="material_entry.js"></script>
</body>
</html>