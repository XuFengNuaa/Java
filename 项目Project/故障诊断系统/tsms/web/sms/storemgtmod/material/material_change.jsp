<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/web/inc/jsp/head_ext.jsp"%>
</head>
<body>
<script type="text/javascript">
var Stringquery = [];
Stringquery[0] = ["creator","createtime"];
Stringquery[1] = ["专家姓名","创建时间"];

var Stringstage = [];   //用于配置文件状态的下拉列表
Stringstage[0] = ["all","edit","check","audit","normalize","ratify"];
Stringstage[1] = ["所有","编辑中","申请校对","申请审查","申请标准化","申请批准"];
Stringstage[2]=  ["22","0","3","6","9","12"];

var Stringorder = [];
Stringorder[0] = ["creator","createtime"];
Stringorder[1] = ["专家姓名","创建时间"];
</script>
<div id="querydiv" ></div>
<div id="selectdiv" ></div>
<script type="text/javascript" src="material_change.js"></script>
</body>
</html>