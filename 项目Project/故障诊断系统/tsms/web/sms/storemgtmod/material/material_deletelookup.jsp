<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>材料报废</title>
		<%@ include file="/web/inc/jsp/head_ext.jsp" %>
	</head>
	<body scroll="no" oncontextmenu="return false">
		<!-- <script type="text/javascript">
		//查询选择=
		var Stringquery = [];
		Stringquery[0] = ["ordernum","mnum","company","spec","classname","name","valiadate",];
		Stringquery[1] = ["订单号","物资编码","厂商","规格","类别","名称","有效期"];
		//排序选择
		var Stringorder = [];
		Stringorder[0] = ["valiadate","ordernum","mnum","company","spec","classname","name"];
		Stringorder[1] = ["有效期","订单号","物资编码","厂商","规格","类别","名称"];
		</script> -->
		<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires","0");
		%>
	
		<div id="querydiv"></div>
		<div id=document.body></div>
		<script type="text/javascript" src="material_deletelookup.js"></script>
	</body>
</html>