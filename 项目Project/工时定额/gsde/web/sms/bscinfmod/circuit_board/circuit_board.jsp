<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

</head>
<body>
<script type="text/javascript">


//检索类型选择
var Stringquery = [];
Stringquery[0] = ["name","company_type","work_time"];
Stringquery[1] = ["名称","厂商","工时"];

//排序选择
var Stringorder = [];
Stringorder[0] = ["name","company_type","work_time"];
Stringorder[1] = ["名称","厂商","工时"];
</script>

<div id="querydiv"></div>


<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
%>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<script type="text/javascript" src="circuit_board.js"></script>
</body>
</html>