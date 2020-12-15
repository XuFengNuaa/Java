<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

</head>
<body>
<script type="text/javascript">


//检索类型选择
var Stringquery = [];
Stringquery[0] = ["numn","cleaning_method","work_time"];
Stringquery[1] = ["编码","清洗工艺方法","工时"];

//排序选择
var Stringorder = [];
Stringorder[0] = ["numn","cleaning_method","work_time"];
Stringorder[1] = ["编码","清洗工艺方法","工时"];
</script>

<div id="querydiv"></div>


<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
%>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<script type="text/javascript" src="cleaning.js"></script>
</body>
</html>