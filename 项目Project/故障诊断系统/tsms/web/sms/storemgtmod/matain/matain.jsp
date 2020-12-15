<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<%@page import="com.nuaa.sys.util.Logger"%>
<%@ page import="com.nuaa.sys.util.AppInsFactory,com.nuaa.app.Login"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>设计</title>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<%@ taglib uri="/WEB-INF/ext-taglibs.tld" prefix="ext"%>

</head>
<body oncontextmenu="return false">
<script type="text/javascript">
var Stringquery = [];
Stringquery[0] = ["dalei_sim","xiaolei_sim","daihao_sim","mingcheng_sim","tuhao_sim"];
Stringquery[1] = ["整机","部件","零件编码","零件名称","图号"];

var Stringorder = [];
Stringorder[0] = ["dalei_sim","xiaolei_sim","daihao_sim","mingcheng_sim","tuhao_sim"];
Stringorder[1] = ["整机","部件","零件编码","零件名称","图号",];

</script>

<div id="querydiv"></div>
<div id="restable"></div>
<div id="formpaneldiv"></div>
<script type="text/javascript" src="matain.js"></script>


</body>
</html>