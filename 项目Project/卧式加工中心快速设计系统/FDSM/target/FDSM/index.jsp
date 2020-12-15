<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	pageContext.setAttribute("APP_PATH",request.getContextPath());
	if(request.getSession().getAttribute("loginUsers")==null){
		response.sendRedirect("login.jsp");
		return;}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主 页</title>

 <link rel="stylesheet" type="text/css" href="${APP_PATH}/Extjs/resources/css/ext-all.css"/></link>
 <link rel="stylesheet" type="text/css" href="${APP_PATH}/Extjs/resources/css/xtheme-gray.css"/></link>
 <link rel="stylesheet" type="text/css" href="${APP_PATH}/resource/css/cls.css"/></link>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-base.js"> </script>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-all.js"></script>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-lang-zh_CN.js"></script>
 <script type="text/javascript">var loginName='${loginUsers.username}';</script>
 <script type="text/javascript">var role='${loginUsers.role}';</script>
<!-- <script type="text/javascript">var loginName2='${loginUsers.username}';</script> --> 
<script type="text/javascript" src="${APP_PATH}/js/2case_info/9result_param.js"></script>
<script type="text/javascript" src="${APP_PATH}/js/2case_info/8workbed_param.js"></script>
<script type="text/javascript" src="${APP_PATH}/js/2case_info/7slideway_param.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/6screw_param.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/5upright_param.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/4atc_param.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/3spindle_param.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/2wkpiece_param.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/1wh_param.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/0whole_reason.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/upWgt.js"></script>
  <script type="text/javascript" src="${APP_PATH}/js/2case_info/bedWgt.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/WgtReason.js"></script>
 
 <script type="text/javascript" src="${APP_PATH}/js/2case_info/Weight.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/3person_info/person_edit.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/4user_info/user_add.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/4user_info/user_info.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/1tool_info/7workbed_mod.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/1tool_info/6upright_mod.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/1tool_info/5slideway_mod.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/1tool_info/4screw_mod.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/1tool_info/3atc_mod.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/1tool_info/2spindle_mod.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/1tool_info/1workpiece_mod.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/0main_menu/UnitText.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/0main_menu/tree_tabpanel.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/0main_menu/main_menu.js"></script>

</head>
<body>
</body>
</html>