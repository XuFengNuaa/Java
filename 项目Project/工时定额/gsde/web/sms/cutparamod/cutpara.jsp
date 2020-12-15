<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

</head>
<body>
<script type="text/javascript">


//检索类型选择
var Stringquery = [];
Stringquery[0] = ["jcxh","clph","company","xlh","zhijing","rjbj","ap","ae","bianhao"];
Stringquery[1] = ["机床型号","材料牌号","刀具厂商","刀具系列号","刀具直径","R角半径","切深","切宽","切削参数编号"];

//排序选择
var Stringorder = [];
Stringorder[0] = ["jcxh","clph","company","xlh","zhijing","rjbj","ap","ae","bianhao"];
Stringorder[1] = ["机床型号","材料牌号","刀具厂商","刀具系列号","刀具直径","R角半径","切深","切宽","切削参数编号"];
</script>

<div id="querydiv"></div>
<div id="jjjjj"></div>
<div id="jjjjjj"></div>
<div id="jjjjjjj"></div>
<div id="jjjjjjjjj"></div>
<div id="j"></div>
<div id="jj"></div>
<div id="xsl"></div>
<div id="ae"></div>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
%>

<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<script type="text/javascript" src="cutpara.js"></script>
</body>
</html>