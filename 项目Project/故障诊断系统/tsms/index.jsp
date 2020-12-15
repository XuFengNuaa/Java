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



  function get_task(){	
    var	sUrl = '/tsms/web/sms/bscinfmod/task_inf/logic_task_inf.jsp';
	var	parms = {type:'queryTaskList',userlevel : userlevel,userid:userid};
	var objtp = Ext.util.JSON.decode(callService(sUrl,parms));
	var task_html = objtp.html;
	return task_html;	
	}

var pop_window = new Ext.ux.ToastWindow();
  
 function task_window(){       
    
    var task_string = get_task();     
	if(!pop_window.isVisible()){	
	   
		pop_window = new Ext.ux.ToastWindow({
	  	id:'toast_window',
	  	title: '<h1 align="center"> 个人待办事项</h1>',
	 	 html: '',
	  	iconCls: 'error'
		}); 		
	  	pop_window.show(document); 	 
	  	Ext.getCmp('toast_window').setMessage(task_string);     
	}else{
	    pop_window.hide(); 	 
	}
	      
}

var Tree=Ext.tree;
//基本信息管理树
if(isUser()){
var bscinf_doc =  [{id:'guide', text: '说明',  leaf:true}    
               // {id:'inventory_control', text: '库存管理' , leaf:true  }
                ]
}else if(isBoss()){
	var bscinf_doc =  [{id:'guide', text: '说明',  leaf:true},
	                   {id:'component_code', text: '零件编码',  leaf:true},
	                   {id:'bom', text: 'bom表', 
	           children:[{id:'design', text:'设计', leaf:true},
	             	{id:'manufacture', text:'制造', leaf:true},
	                {id:'maintainance', text:'维护', leaf:true}]} 
	                ]
}else{
	var bscinf_doc =  [{id:'guide', text: '说明',  leaf:true},
	                   {id:'component_code', text: '零件编码',  leaf:true},
	                   {id:'bom', text: 'bom表', 
	           children:[{id:'design', text:'设计', leaf:true},
	             	{id:'manufacture', text:'制造', leaf:true},
	                {id:'maintainance', text:'维护', leaf:true}]} 
	                ]
}
var bscinf_root = new Ext.tree.AsyncTreeNode({
	text:'按类型',       
    draggable:false,
    id:'bscinf_tree_root',
	expanded:true,
    children:bscinf_doc
});
var bscinf_panel = new Ext.tree.TreePanel({
	//el: 'bscinf_tree',
	title: '基本信息管理',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: bscinf_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//故障案例库
if(isUser()){
	var storemgt_doc = [
{id: 'huizong1', text: '故障汇总', 
	children:[{   id: 'rezhazong',text: '热轧',
		children:[{id:'putrezha',text:'普通热轧机',leaf:true},
		          {id:'gaosrezha',text:'高速热轧机',leaf:true},
		          {id:'putsgrezha',text:'普通三辊热轧机',leaf:true}
		          ,{id:'sgunrezha',text:'S辊（三辊）热轧机',leaf:true}
		          ,{id:'sgunyxrezha',text:'S辊Y型热轧机',leaf:true},
		          {id:'sigunrezha',text:'四辊热轧机',leaf:true}]} ,
	          {   id: 'shoujuan',text: '收卷',children:[{id:'sgshoujuan',text:'三辊普通收卷机',leaf:true},
	         								          {id:'putshoujuan',text:'普通收卷机',leaf:true},
	        								          {id:'gaosshoujuan',text:'高速收卷机',leaf:true}
	        								          ]},
	          {   id: 'fenqie',text: '分切',children:[{id:'putfenqie',text:'普通分切',leaf:true},
		        								          {id:'gaosfenqie',text:'高速分切',leaf:true}
		        								          ]},
	          {   id: 'qita',text: '其他',children:[{ id:'tangguang',text: '烫光机',leaf:true}, 
	                                                   { id:'yahua',text: '压花机组',leaf:true},
	                                                   { id:'chubu',text: '储布架',leaf:true},
	                                                   { id:'bozhou',text: '拨轴机',leaf:true},
	                                                   { id:'qslixian',text: '亲水离线',leaf:true},
	                                                   { id:'fuhexian',text: '复合线',leaf:true},
	                                                   { id:'chenwang',text: '成网机',leaf:true},
	                                                   { id:'hongxiang',text: '烘箱',leaf:true},
	                                                   { id:'tangpin',text: '烫平机',leaf:true},
	                                                   { id:'lenque',text: '冷却机',leaf:true},
	                                                   { id:'tuijuan',text: '退卷机',leaf:true},
	                                                   { id:'sgwfangjuan',text: '双工位放卷机',leaf:true}]}]},
{id:'application', text:'在线申请',
children:[{   id: 'rezha2',text: '申请答疑',leaf:true} ]} 
						             
						             ];
}else{
		var storemgt_doc = [{id:'trouble_previous', text:'已有案例',
		    children:[{   id: 'rezha1',text: '热轧',leaf:true},
				        {id: 'shoujuan1', text: '收卷', leaf:true},
						{id: 'fenqie1', text: '分切', leaf:true}]},
						//{id:'huizong',text:'故障汇总',leaf:true},
						{id: 'huizong1', text: '故障汇总', 
							children:[{   id: 'rezhazong',text: '热轧',
								children:[{id:'putrezha',text:'普通热轧机',leaf:true},
								          {id:'gaosrezha',text:'高速热轧机',leaf:true},
								          {id:'putsgrezha',text:'普通三辊热轧机',leaf:true}
								          ,{id:'sgunrezha',text:'S辊（三辊）热轧机',leaf:true}
								          ,{id:'sgunyxrezha',text:'S辊Y型热轧机',leaf:true},
								          {id:'sigunrezha',text:'四辊热轧机',leaf:true}]} ,
							          {   id: 'shoujuan',text: '收卷',children:[{id:'sgshoujuan',text:'三辊普通收卷机',leaf:true},
							         								          {id:'putshoujuan',text:'普通收卷机',leaf:true},
							        								          {id:'gaosshoujuan',text:'高速收卷机',leaf:true}
							        								          ]},
							          {   id: 'fenqie',text: '分切',children:[{id:'putfenqie',text:'普通分切',leaf:true},
								        								          {id:'gaosfenqie',text:'高速分切',leaf:true}
								        								          ]},
							          {   id: 'qita',text: '其他',children:[{ id:'tangguang',text: '烫光机',leaf:true}, 
							                                                   { id:'yahua',text: '压花机组',leaf:true},
							                                                   { id:'chubu',text: '储布架',leaf:true},
							                                                   { id:'bozhou',text: '拨轴机',leaf:true},
							                                                   { id:'qslixian',text: '亲水离线',leaf:true},
							                                                   { id:'fuhexian',text: '复合线',leaf:true},
							                                                   { id:'chenwang',text: '成网机',leaf:true},
							                                                   { id:'hongxiang',text: '烘箱',leaf:true},
							                                                   { id:'tangpin',text: '烫平机',leaf:true},
							                                                   { id:'lenque',text: '冷却机',leaf:true},
							                                                   { id:'tuijuan',text: '退卷机',leaf:true},
							                                                   { id:'sgwfangjuan',text: '双工位放卷机',leaf:true}]}]},
	                   {id:'application', text:'在线申请',
				    children:[{   id: 'rezha2',text: '申请答疑',leaf:true} ]}];
	
}
    var storemgt_root = new Ext.tree.AsyncTreeNode({
    	text:'',       
        draggable:false,//可拖动
        id:'storemgt_tree_root',
    	expanded:true,
        children:storemgt_doc
    });
    var storemgt_panel = new Ext.tree.TreePanel({
    	id: 'storemgt_tree',
    	title: '故障案例库',
    	iconCls:'assign',
    	animate: true,
    	collapsible: true,
    	rootVisible: false,
    	autoScroll: true,
    	border: false,
    	root: storemgt_root,
    	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
    });

//在线专家诊断
var gldapp_doc = [{id:'expert_type',text:'在线诊断',leaf:true}/*,
			      {  id:'knowledge',   text:'知识库', leaf:true
				  }*/];
var gldapp_root = new Ext.tree.AsyncTreeNode({
	text:'',       
    draggable:false,
    id:'gldapp_tree_root',
	expanded:true,
    children:gldapp_doc
});
var gldapp_panel = new Ext.tree.TreePanel({
	title:'在线专家诊断',  
    id:'gldapp_manage',
    iconCls:'teamlead',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: gldapp_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});
//领导审查模块
var leader_doc = [{id:'leader_news',text:'领导资讯',leaf:true}/*,
{  id:'knowledge',   text:'知识库', leaf:true
}*/];
var leader_root = new Ext.tree.AsyncTreeNode({
text:'',       
draggable:false,
id:'leader_tree_root',
expanded:true,
children:leader_doc
});
var leader_panel = new Ext.tree.TreePanel({
title:'领导审查',  
id:'leader_manage',
iconCls:'users',
animate: true,
collapsible: true,
rootVisible: false,
autoScroll: true,
border: false,
root: leader_root,
loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});

//个人信息管理树
var usrinf_doc_leader = [{
	id:'usrinf_m_leader', 
	text:'个人信息管理',
	children:[
		  		{
					id:'usrinf_check_leader',
					text:'个人信息查看',
					leaf:true
				},{
					id:'usrinf_pwdedit_leader',
					text:'个人密码修改',
					leaf:true
				}
	]
	}];
var usrinf_root_leader = new Ext.tree.AsyncTreeNode({
	text:'',       
    draggable:false,
    id:'usrinf_tree_root_leader',
	expanded:true,
    children:usrinf_doc_leader
});
 var usrinf_panel_leader = new Ext.tree.TreePanel({
	title:'个人信息管理',  
    id:'usrinf_manage_leader',
    iconCls:'usrinf',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: usrinf_root_leader,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});  

//用户管理树
var usrmgt_doc = [{id:'usrmgt_add', text:'添加新用户', leaf:true},
                  {id:'usrmgt_mod', text:'修改/删除用户信息', leaf:true}]
var usrmgt_root = new Ext.tree.AsyncTreeNode({
	text:'',       
    draggable:false,
    id:'usrmgt_tree_root',
	expanded:true,
    children:usrmgt_doc
});
var usrmgt_panel = new Ext.tree.TreePanel({
	title:'用户管理', 
    id:'usrmgt_manage',
    iconCls:'group',
	animate: true,
	collapsible: true,
	collapsed: false,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: usrmgt_root,
	loader: new Tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
});


//基本信息模块
urlMap["guide"]="web/sms/bscinfmod/gen_tool/sum.jsp";
urlMap["component_code"]="web/sms/bscinfmod/cutter/cutter.jsp";
//urlMap["inventory_control"]="web/sms/storestatmod/storestatmod_c/tabpan.jsp";//库存管理
urlMap["design"]="web/sms/storemgtmod/material/material_out.jsp";
urlMap["manufacture"]="web/sms/storemgtmod/manufacture/manufacture.jsp";
urlMap["maintainance"]="web/sms/storemgtmod/matain/matain.jsp";
//故障案例库
urlMap["rezha1"]="web/sms/storemgtmod/m_check/m_delete.jsp";
urlMap["shoujuan1"]="web/sms/storemgtmod/shoujuan/shoujuan.jsp";
urlMap["fenqie1"]="web/sms/storemgtmod/fenqie/fenqie.jsp";
//urlMap["huizong"]="web/sms/storemgtmod/huizong/huizong.jsp";//故障汇总
//分切
urlMap["putrezha"]="web/sms/storemgtmod/huizong/putrezha.jsp";
urlMap["gaosrezha"]="web/sms/storemgtmod/huizong/gaosrezha.jsp";
urlMap["putsgrezha"]="web/sms/storemgtmod/huizong/putsgrezha.jsp";
urlMap["sgunrezha"]="web/sms/storemgtmod/huizong/sgunrezha.jsp";
urlMap["sgunyxrezha"]="web/sms/storemgtmod/huizong/sgunyxrezha.jsp";
urlMap["sigunrezha"]="web/sms/storemgtmod/huizong/sigunrezha.jsp";
//收卷
urlMap["sgshoujuan"]="web/sms/storemgtmod/huizong/sgshoujuan.jsp";
urlMap["putshoujuan"]="web/sms/storemgtmod/huizong/putshoujuan.jsp";
urlMap["gaosshoujuan"]="web/sms/storemgtmod/huizong/gaosshoujuan.jsp";
//分切
urlMap["putfenqie"]="web/sms/storemgtmod/huizong/putfenqie.jsp";
urlMap["gaosfenqie"]="web/sms/storemgtmod/huizong/gaosfenqie.jsp";
//其他
urlMap["tangguang"]="web/sms/storemgtmod/huizong/tangguang.jsp";
urlMap["yahua"]="web/sms/storemgtmod/huizong/yahua.jsp";
urlMap["chubu"]="web/sms/storemgtmod/huizong/chubu.jsp";
urlMap["bozhou"]="web/sms/storemgtmod/huizong/bozhou.jsp";
urlMap["qslixian"]="web/sms/storemgtmod/huizong/qslixian.jsp";
urlMap["fuhexian"]="web/sms/storemgtmod/huizong/fuhexian.jsp";
urlMap["chenwang"]="web/sms/storemgtmod/huizong/chenwang.jsp";
urlMap["hongxiang"]="web/sms/storemgtmod/huizong/hongxiang.jsp";
urlMap["tangpin"]="web/sms/storemgtmod/huizong/tangpin.jsp";
urlMap["lenque"]="web/sms/storemgtmod/huizong/lenque.jsp";
urlMap["tuijuan"]="web/sms/storemgtmod/huizong/tuijuan.jsp";
urlMap["sgwfangjuan"]="web/sms/storemgtmod/huizong/sgwfangjuan.jsp";

urlMap["rezha2"]="web/sms/storemgtmod/cut_tool/pa_c_tab.jsp";

//在线专家诊断
urlMap["expert_type"] ="web/sms/ldappmod/check/entrycheck.jsp";
//
urlMap["leader_news"]="web/sms/storestatmod/storestatemod_material/material_warn.jsp";
//
urlMap["usrinf_pwdedit_leader"]="web/sms/usrinfmod/usrinfmod/usreditpwd.jsp";
urlMap["usrinf_check_leader"]="web/sms/usrinfmod/usrinfmod/usrinf_check.jsp";

//用户管理模块
urlMap["usrmgt_add"]="web/sms/usrmgtmod/usradd.jsp";
urlMap["usrmgt_mod"]="web/sms/usrmgtmod/usrmod.jsp";
</script>
<div id="menu"></div>
<div id="north">
	<table id="header" style="display:none" width="100%" height="100%" background="/tsms/web/inc/resources/images/custom/headback.jpg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td width=450 background="/tsms/web/inc/resources/images/custom/head.jpg">&nbsp;</td><td valign="center" align="right"><span id="loginInf" style="font-size:12px;color:#ffffff">&nbsp;</span><span><a href="javascript:task_window()" ><font style="font-size:12px;color:#ffffff"><img border=0 src="/tsms/web/inc/resources/images/custom/information.gif" valign=center>个人待办事项</font></a></span><img id="roleimages" border=0 src="/tsms/web/inc/resources/images/custom/folder_user.png" valign=center><a href="javascript:showrole()" id="rolechoose">&nbsp;<font style="font-size:12px;color:#ffffff">角色选择</font></a><a href="login/logout.jsp"><font style="font-size:12px;color:#ffffff"><img border=0 src="/tsms/web/inc/resources/images/custom/logout.gif" valign=center>退出系统</font></a>&nbsp;&nbsp;<span id="help" style="font-size:12px;color:#ffffff">&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr></table>
</div>
<div id="center" >
	<iframe id="mainFrame" name="mainFrame" class="mainFrame" width="100%" height="100%" frameborder="0" src=""></iframe>
</div>

<div id="silu"><button id="taskbtn" onclick="show_pop()" style="display:none">显示任务</button></div> 
<div id="winpop"  class = "scrollbar" style="border:1px solid #9EB7F9;" onMouseOut="hideContent();"></div> 
<div  id="content" class = "content" style="border:0px solid #9EB7F9;display:none"></div> 

<div id="bscinf_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="storemgt_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="gldapp_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="Idapp_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="storestat_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="usrinf_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="usrmgt_tree" style="overflow:auto;height:100%;width:100%;"></div>
<div id="divinput" style="position:absolute;top:750px;left:350px;display:none;z-index:100">
<INPUT TYPE="text" NAME="divput" id="divput" class='rename-input'><input type="button" value="确定" class="rename-button" onclick="rename()"><input type="button" value="取消" class="rename-button" onclick="cancelRename()">
</div>
<script type="text/javascript" src="/tsms/index.js"></script>
<body oncontextmenu="return false">
</body>



</html>

