<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<%@page import="com.nuaa.sys.util.Logger"%>
<%@ page import="com.nuaa.sys.util.AppInsFactory,com.nuaa.app.Login"%>
<html>
<head>
<style>
.n{TEXT-DECORATION:none} 
</style>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<title><%=GuiStr.SystemTitle%></title>
</head>
<%

String condition="and 1=1 order by isfolder desc,menuname";
%>

<script type="text/javascript">
var urlMap=[];

var Tree=Ext.tree;

//基本信息管理模块
var bscinf_doc = [{id:'bsmilling', text: '铣加工', children:[
                          		{id:'machine', text:'机床', leaf:true},
                          		{id:'cutter', text:'刀具', leaf:true},
                          		{id:'material', text:'工件材料', leaf:true}]}, 
                  {id: 'bscinfprintedplate', text: '印制板', children:[
		     			    	{id:'pp_cut', text:'机械加工参数', leaf:true}]},
		     	  {id: 'electronicassembly', text: '板级电路', children:[
		     			    	{id:'bc_circuit_board', text:'元器件', leaf:true},
		     			    	{id:'bc_cleaning', text:'清洗', leaf:true}]}		     			    	
				 ];
				  		
var bscinf_root = new Ext.tree.AsyncTreeNode({
	text:'按类型',       
    draggable:false,
    id:'bscinf_tree_root',
	expanded:true,
    children:bscinf_doc
});

var bscinf_panel = new Ext.tree.TreePanel({
	id: 'bscinf_tree',
	title: '基本信息管理',
	iconCls:'assign',
	animate: true,
	collapsible: true,
	rootVisible: true,
	autoScroll: true,
	border: false,
	root: bscinf_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//切削参数管理模块
var cutpara_doc = [{id:'cutpara', text: '切削参数', leaf:true}/* ,
                 {id:'tezhengcutpara', text: '特征切削参数', leaf:true} */
				 ];
				  		
var cutpara_root = new Ext.tree.AsyncTreeNode({
	text:'铣加工',       
    draggable:false,
    id:'cutpara_tree_root',
	expanded:true,
    children:cutpara_doc
});

var cutpara_panel = new Ext.tree.TreePanel({
	id: 'cutpara_tree',
	title: '切削参数管理',
	iconCls:'assign',
	animate: true,
	collapsible: true,
	rootVisible: true,
	autoScroll: true,
	border: false,
	root: cutpara_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//工时定额模块
var quota_doc = [{id:'milling', text: '壳体零件', leaf:true},
                 {id:'printedplate', text: '印制板', leaf:true},
				{id:'circuit_board', text: '板级电路', leaf:true}
				 ];
				  		
var quota_root = new Ext.tree.AsyncTreeNode({
	text:'按类型',       
    draggable:false,
    id:'quota_tree_root',
	expanded:true,
    children:quota_doc
});

var quota_panel = new Ext.tree.TreePanel({
	id: 'quota_tree',
	title: '工时定额',
	iconCls:'assign',
	animate: true,
	collapsible: true,
	rootVisible: true,
	autoScroll: true,
	border: false,
	root: quota_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//定额历史查询模块
var quotaquery_doc = [{id:'millingquery', text: '壳体零件', leaf:true},
                 	  {id:'printedplatequery', text: '印制板', leaf:true}
					  ];
				  		
var quotaquery_root = new Ext.tree.AsyncTreeNode({
	text:'按类型',       
    draggable:false,
    id:'quotaquery_tree_root',
	expanded:true,
    children:quotaquery_doc
});

var quotaquery_panel = new Ext.tree.TreePanel({
	id: 'quotaquery_tree',
	title: '定额历史查询',
	iconCls:'assign',
	animate: true,
	collapsible: true,
	rootVisible: true,
	autoScroll: true,
	border: false,
	root: quotaquery_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//零件工时实例规则维护模块
var quotamgtmod_doc = [{id:'gzmilling', text: '铣加工', children:[
                                    {id:'leibie', text: '零件类别管理', leaf:true},
				                 	{id:'shili', text: '零件工时实例管理', leaf:true},
				                 	{id:'qzdf', text: '专家权重打分', leaf:true},
				                 	{id:'qzwh', text: '专家权重维护', leaf:true},
				                 	{id:'quanzhong', text: '专家权重维护', leaf:true}]},
                 	   {id:'gzpp', text: '印制板', children:[
                 	                {id:'zhimuban', text: '制模板', leaf:true},
                 	                {id:'beiliao', text: '备料', leaf:true},
                 	   				{id:'zhidingweikong', text: '制定位孔', leaf:true},
                 	   			    {id:'shukongzuankong', text: '数控钻孔', leaf:true},
	                 	   			{id:'tuxingzhuanyi', text: '图形转移', leaf:true},
	             	   				{id:'shike', text: '蚀刻', leaf:true},
	             	   			    {id:'tufu', text: '涂覆', leaf:true},
		             	   			{id:'cengya', text: '层压', leaf:true},
		         	   				{id:'kongjinshuhua', text: '孔金属化', leaf:true},
		         	   			    {id:'zhiyangban', text: '制样板', leaf:true},
			         	   			{id:'zhizifu', text: '制字符', leaf:true},
			     	   				{id:'refengpingzheng', text: '热风平整', leaf:true},
                 	   				{id:'baofeng', text: '包封', leaf:true}]}
					  ];
				  		
var quotamgtmod_root = new Ext.tree.AsyncTreeNode({
	text:'零件工时规则管理',       
    draggable:false,
    id:'quotamgtmod_tree_root',
	expanded:true,
    children:quotamgtmod_doc
});

var quotamgtmod_panel = new Ext.tree.TreePanel({
	id: 'quotamgtmod_tree',
	title: '零件工时规则管理',
	iconCls:'assign',
	animate: true,
	collapsible: true,
	rootVisible: true,
	autoScroll: true,
	border: false,
	root: quotamgtmod_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//个人信息管理模块
var userinf_doc = [{id:'userinf_check',text:'个人信息查看',leaf:true},
                   {id:'userinf_pwdedit',text:'个人密码修改',leaf:true}
				  ];
				  		
var userinf_root = new Ext.tree.AsyncTreeNode({
	text:'个人信息管理',       
    draggable:false,
    id:'userinf_tree_root',
	expanded:true,
    children:userinf_doc
});

var userinf_panel = new Ext.tree.TreePanel({
	id: 'userinf_tree',
	title: '个人信息管理',
	iconCls:'usrinf',
	animate: true,
	collapsible: true,
	rootVisible: true,
	autoScroll: true,
	border: false,
	root: userinf_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//用户管理模块
var usermanage_doc = [{id:'usermanage_add', text:'添加新用户', leaf:true},
                      {id:'usermanage_add1', text:'添加新用户', leaf:true},
                  {id:'usermanage_mod', text:'修改/删除用户信息', leaf:true}
                  ]
                  
var usermanage_root = new Ext.tree.AsyncTreeNode({
	text:'',       
    draggable:false,
    id:'usermanage_tree_root',
	expanded:true,
    children:usermanage_doc
});
var usermanage_panel = new Ext.tree.TreePanel({
	title:'用户管理',  
    id:'usermanage_manage',
    iconCls:'group',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: usermanage_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//基本信息管理模块
urlMap["machine"]="web/sms/bscinfmod/bscinfmilling/machine.jsp";
urlMap["cutter"]="web/sms/bscinfmod/bscinfmilling/cutter.jsp";
urlMap["material"]="web/sms/bscinfmod/bscinfmilling/material.jsp";
urlMap["pp_cut"]="web/sms/bscinfmod/bscinfprintedplate/pp_cut.jsp";
urlMap["bscinfmilling"]="web/sms/bscinfmod/bscinfmilling/bscinfmilling.jsp";
urlMap["bc_circuit_board"]="web/sms/bscinfmod/circuit_board/circuit_board.jsp";
urlMap["bc_cleaning"]="web/sms/bscinfmod/circuit_board/cleaning.jsp";
//切削参数管理模块
urlMap["cutpara"]="web/sms/cutparamod/cutpara.jsp";
urlMap["tezhengcutpara"]="web/sms/cutparamod/tezhengcutpara.jsp";

//工时定额模块
urlMap["milling"]="web/sms/quotamod/milling/milling.jsp";
urlMap["printedplate"]="web/sms/quotamod/printedplate/printedplate.jsp";
urlMap["circuit_board"]="web/sms/quotamod/circuit_board/circuit_board.jsp";
//定额历史查询模块
urlMap["millingquery"]="web/sms/quotaquerymod/millingquery/millingquery.jsp";
urlMap["printedplatequery"]="web/sms/quotaquerymod/printedplatequery/printedplatequery.jsp";

//零件工时规则维护模块
urlMap["leibie"]="web/sms/quotamgtmod/leibie/parttype.jsp";
urlMap["shili"]="web/sms/quotamgtmod/shili/partprocess.jsp";
urlMap["qzdf"]="web/sms/quotamgtmod/quanzhong/expertscores.jsp";
urlMap["qzwh"]="web/sms/quotamgtmod/quanzhong/setweightmod.jsp";
urlMap["quanzhong"]="web/sms/quotamgtmod/quanzhong/weight.jsp";

urlMap["zhimuban"]="web/sms/quotamgtmod/yinzhiban/zhimuban/zhimuban.jsp";
urlMap["beiliao"]="web/sms/quotamgtmod/yinzhiban/beiliao/beiliao.jsp";
urlMap["zhidingweikong"]="web/sms/quotamgtmod/yinzhiban/zhidingweikong/zhidingweikong.jsp";
urlMap["shukongzuankong"]="web/sms/quotamgtmod/yinzhiban/shukongzuankong/shukongzuankong.jsp";
urlMap["tuxingzhuanyi"]="web/sms/quotamgtmod/yinzhiban/tuxingzhuanyi/tuxingzhuanyi.jsp";
urlMap["shike"]="web/sms/quotamgtmod/yinzhiban/shike/shike.jsp";
urlMap["tufu"]="web/sms/quotamgtmod/yinzhiban/tufu/tufu.jsp";
urlMap["cengya"]="web/sms/quotamgtmod/yinzhiban/cengya/cengya.jsp";
urlMap["kongjinshuhua"]="web/sms/quotamgtmod/yinzhiban/kongjinshuhua/kongjinshuhua.jsp";
urlMap["zhiyangban"]="web/sms/quotamgtmod/yinzhiban/zhiyangban/zhiyangban.jsp";
urlMap["zhizifu"]="web/sms/quotamgtmod/yinzhiban/zhizifu/zhizifu.jsp";
urlMap["refengpingzheng"]="web/sms/quotamgtmod/yinzhiban/refengpingzheng/refengpingzheng.jsp";
urlMap["baofeng"]="web/sms/quotamgtmod/yinzhiban/baofeng/baofeng.jsp";

//个人信息管理模块
urlMap["userinf_pwdedit"]="web/sms/userinfmod/userinf/usreditpwd.jsp";
urlMap["userinf_check"]="web/sms/userinfmod/userinf/usrinf_check.jsp";

//用户管理模块
urlMap["usermanage_add"]="web/sms/usermanagemod/usradd.jsp";
urlMap["usermanage_add1"]="web/sms/usermanagemod/usradd1.jsp";
urlMap["usermanage_mod"]="web/sms/usermanagemod/usrmod.jsp";

</script>
<div id="menu"></div>
<div id="north">
	<table id="header" style="display:none" width="100%" height="100%" background="/gsde/web/inc/resources/images/custom/headback.jpg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td width=450 background="/gsde/web/inc/resources/images/custom/head.jpg">&nbsp;</td><td valign="center" align="right"><span id="loginInf" style="font-size:12px;color:#ffffff">&nbsp;</span></td></tr></table>
</div>
<div id="center" >
	<iframe id="mainFrame" name="mainFrame" class="mainFrame" width="100%" height="100%" frameborder="0" src=""></iframe>
</div>

<div id="silu"><button id="taskbtn" onclick="show_pop()" style="display:none">显示任务</button></div> 
<div id="winpop"  class = "scrollbar" style="border:1px solid #9EB7F9;" onMouseOut="hideContent();"></div> 
<div  id="content" class = "content" style="border:0px solid #9EB7F9;display:none"></div> 

<div id="bscinf_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="quota_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="quotaquery_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="userinf_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="usermanage_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="divinput" style="position:absolute;top:750px;left:350px;display:none;z-index:100">
<INPUT TYPE="text" NAME="divput" id="divput" class='rename-input'><input type="button" value="确定" class="rename-button" onclick="rename()"><input type="button" value="取消" class="rename-button" onclick="cancelRename()">
</div>
<script type="text/javascript" src="/gsde/index.js"></script>
<body oncontextmenu="return false">
</body>



</html>

