<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<%@page import="com.nuaa.sys.util.Logger"%>
<%@ page import="com.nuaa.sys.util.AppInsFactory,com.nuaa.app.Login"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>零件工艺管理</title>
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<%@ taglib uri="/WEB-INF/ext-taglibs.tld" prefix="ext"%>

</head>
<body oncontextmenu="return false">
<script type="text/javascript">
var Stringquery = [];
Stringquery[0] = ["dalei_sim","xiaolei_sim","daihao_sim","mingcheng_sim","tuhao_sim"];
Stringquery[1] = ["案例大类","案例小类","案例名称","零件名称","零件编号"];

var Stringorder = [];
Stringorder[0] = ["daihao_sim","mingcheng_sim","tuhao_sim","dalei_sim","xiaolei_sim"];
Stringorder[1] = ["案例大类","案例名称","零件编号","零件名称","案例小类"];

</script>

<div id="querydiv"></div>
<div id="restable"></div>
<div id="formpaneldiv"></div>

<script type="text/javascript" src="shoujuan.js"></script>


</body>
</html>