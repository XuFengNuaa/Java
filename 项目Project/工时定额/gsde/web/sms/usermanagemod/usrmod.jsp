<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%> 
<%@page import="com.nuaa.sys.util.Logger"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>用户查询</title>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
</head>
<body oncontextmenu="return false">
<script type="text/javascript">
//查询选择=
var Stringquery = [];
Stringquery[0] = ["stuff_num","realname"];
Stringquery[1] = ["用户工号","用户姓名"];
//排序选择
var Stringorder = [];
Stringorder[0] = ["stuff_num","realname"];
Stringorder[1] = ["用户工号","用户姓名"];
</script>

<div id="querydiv"></div>
<div id="restable"></div>

<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
%>

<div id="edit-win" class="x-hidden">
    <div class="x-window-header">用户信息修改</div>
</div>
<script type="text/javascript" src="usrmod.js"></script>
</body>
</html>