<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

</head>
<body>
<script type="text/javascript">


//检索类型选择
var Stringquery = [];
Stringquery[0] = ["dalei","xiaolei"];
Stringquery[1] = ["零件大类","零件小类"];

//排序选择
var Stringorder = [];
Stringorder[0] =  ["dalei","xiaolei"];
Stringorder[1] = ["零件大类","零件小类"];
</script>

<div id="querydiv"></div>


<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
%>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<script type="text/javascript" src="parttype.js"></script>
</body>
</html>