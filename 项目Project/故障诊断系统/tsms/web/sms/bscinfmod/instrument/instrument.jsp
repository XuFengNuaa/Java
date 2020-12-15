<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

</head>
<body>
<script type="text/javascript">


//检索类型选择
var Stringquery = [];
Stringquery[0] = ["enterprisenum","name","classname","state","modelnum","outnum","nowclass"];
Stringquery[1] = ["企业编号","名称","类别","状态","型号","出厂编号","现场类别"];

//排序选择
var Stringorder = [];
Stringorder[0] = ["enterprisenum","name","classname","state","modelnum","outnum","nowclass"];
Stringorder[1] = ["企业编号","名称","类别","状态","型号","出厂编号","现场类别"];
</script>

<div id="querydiv"></div>


<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
%>

<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<script type="text/javascript" src="instrument.js"></script>
</body>
</html>