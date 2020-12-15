<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%-- <%@ page import="com.nuaa.sys.util.Logger"%> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
</head>
<body>
<script type="text/javascript">

//检索类型选择
var Stringquery = [];
Stringquery[0] = ["mnum","name"];
Stringquery[1] = ["设备名称","设备编号"];

//状态选择
var Stringstage = [];
Stringstage[0] = ["all","passing","passed","rejected"];
Stringstage[1] = ["所有","待批准","已批准","已驳回"];
Stringstage[2] = ["all","null","passed","rejected"];

//排序选择
var Stringorder = [];
Stringorder[0] = ["mnum","name"];
Stringorder[1] = ["设备名称","设备编号"];
</script>

<div id="querydiv"></div>

<script type="text/javascript" src="model_out.js"></script>

</body>
</html>