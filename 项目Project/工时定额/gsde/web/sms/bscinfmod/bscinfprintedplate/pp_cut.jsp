<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

</head>
<body>
<script type="text/javascript">


//检索类型选择
var Stringquery = [];
Stringquery[0] = ["zhijing","ap","ae","vf","vc","zzzs"];
Stringquery[1] = ["刀具直径","切深","切宽","进给速度","切削速度","主轴转速"];

//排序选择
var Stringorder = [];
Stringorder[0] = ["zhijing","ap","ae","vf","vc","zzzs"];
Stringorder[1] = ["刀具直径","切深","切宽","进给速度","切削速度","主轴转速"];
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
<script type="text/javascript" src="pp_cut.js"></script>
</body>
</html>