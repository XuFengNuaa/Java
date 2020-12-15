<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

</head>
<body>
<script type="text/javascript">


//检索类型选择
var Stringquery = [];
Stringquery[0] = ["bianhao","xinghao"];
Stringquery[1] = ["机床编号","机床型号"];

//排序选择
var Stringorder = [];
Stringorder[0] = ["bianhao","xinghao"];
Stringorder[1] = ["机床编号","机床型号"];
</script>

<div id="querydiv"></div>


<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
%>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<script type="text/javascript" src="machine.js"></script>
</body>
</html>