<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

</head>
<body>
<script type="text/javascript">


//检索类型选择
var Stringquery = [];
Stringquery[0] = ["bianhao","xlh","company","mingcheng","leixing","xinghao"];
Stringquery[1] = ["刀具编号","刀具系列号","刀具厂商","刀具名称","刀具类型","刀具型号"];

//排序选择
var Stringorder = [];
Stringorder[0] = ["bianhao","xlh","company","mingcheng","leixing","xinghao"];
Stringorder[1] = ["刀具编号","刀具系列号","刀具厂商","刀具名称","刀具类型","刀具型号"];
</script>

<div id="querydiv"></div>


<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
%>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<script type="text/javascript" src="cutter.js"></script>
</body>
</html>