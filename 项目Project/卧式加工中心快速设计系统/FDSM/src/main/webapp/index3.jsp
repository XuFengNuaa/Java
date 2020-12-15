<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<% 
	pageContext.setAttribute("APP_PATH",request.getContextPath());

%>
<html>
<head>
<style>
.n{TEXT-DECORATION:none} 
</style>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>首页</title>
</head>
<link rel="stylesheet" type="text/css" href="${APP_PATH}/Extjs/resources/css/ext-all.css"/></link>
 <link rel="stylesheet" type="text/css" href="${APP_PATH}/Extjs/resources/css/xtheme-gray.css"/></link>
 <link rel="stylesheet" type="text/css" href="${APP_PATH}/resource/css/cls.css"/></link>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-base.js"> </script>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-all.js"></script>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-lang-zh_CN.js"></script>
 <script type="text/javascript">var loginName='admin';</script>
 <script type="text/javascript">var role='admin';</script>
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

<div id="menu"></div>
<div id="north">
	<table id="header" style="display:none" width="100%" height="100%" background="/gsde/web/inc/resources/images/custom/headback.jpg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td width=450 background="/gsde/web/inc/resources/images/custom/head.jpg">&nbsp;</td><td valign="center" align="right"><span id="loginInf" style="font-size:12px;color:#ffffff">&nbsp;</span></td></tr></table>
</div>
<div id="center" >
	<iframe id="mainFrame" name="mainFrame" class="mainFrame" width="100%" height="100%" frameborder="0" src=""></iframe>
</div>

<div id="silu"><button id="taskbtn" onclick="show_pop()" style="display:none">显示任务</button></div> 
<div id="winpop"  class = "scrollbar" style="border:1px solid #9EB7F9;" ></div> 
<div  id="content" class = "content" style="border:0px solid #9EB7F9;display:none"></div> 

<div id="bscinf_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="quota_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="quotaquery_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="userinf_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="usermanage_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="divinput" style="position:absolute;top:750px;left:350px;display:none;z-index:100">
<INPUT TYPE="text" NAME="divput" id="divput" class='rename-input'><input type="button" value="确定" class="rename-button" onclick="rename()"><input type="button" value="取消" class="rename-button" onclick="cancelRename()">
</div>
<script type="text/javascript" src="${APP_PATH}/js/0main_menu/main_menu.js"></script>
<body oncontextmenu="return false">
</body>



</html>

