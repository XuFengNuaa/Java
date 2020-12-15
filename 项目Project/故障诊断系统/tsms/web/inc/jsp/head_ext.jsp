<%@ page errorPage="/system/file/errorpage.jsp" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.nuaa.app.GuiStr,com.nuaa.app.RoleLevel"%>
<!-- Ext -->
<link rel="stylesheet" type="text/css" href="/tsms/web/inc/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="/tsms/web/inc/resources/css/add.css" />
<script type="text/javascript" src="/tsms/web/inc/resources/js/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/ext-all.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/ext-cg.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/ext.search.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/calendar.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/calendar-zh.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/calendar-setup.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/Ext.ux.ToastWindow.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/Ext.ux.InlineToolbarTabPanel.js"></script>
<link rel="stylesheet" type="text/css" href="/tsms/web/inc/resources/UploadDialog/css/Ext.ux.UploadDialog.css" />
<script type='text/javascript' src='/tsms/web/inc/resources/UploadDialog/Ext.ux.UploadDialog.js' ></script>
<script type='text/javascript' src='/tsms/web/inc/resources/UploadDialog/Ext.ux.UploadDialog.packed.js' ></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/keyboard.js"></script>
<link rel="stylesheet" type="text/css" href="/tsms/web/inc/resources/css/win_keyboard.css" />

<script type="text/javascript" src="/tsms/web/inc/resources/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/tsms/web/inc/resources/js/jquery.keypad.js"></script>
<link rel="stylesheet" type="text/css" href="/tsms/web/inc/resources/css/jquery.keypad.css" />

<%
	String rolename=(String)session.getAttribute("rolename");
	String username=(String)session.getAttribute("username");
	String userid=(String)session.getAttribute("userid");
	String userlevel=(String)session.getAttribute("userlevel");
	String multrole=(String)session.getAttribute("multrole");
%>
<script type="text/javascript">
var rolename="<%=rolename%>";
var username="<%=username%>";
var userid="<%=userid%>";
var userlevel="<%=userlevel%>";
var multrole="<%=multrole%>";

//状态对应级别 此处配置级别
var RoleLevel={};//用户级别
RoleLevel.UserLev=1;//用户
RoleLevel.StoreManagerLevel=2;//库房管理员
RoleLevel.TeamLeaderLevel=3;//专家
RoleLevel.BossLevel=4;//领导
RoleLevel.SysLevel=5;//系统管理员
RoleLevel.SuperLevel=0;//超级管理员
RoleLevel.ProgrammerLevel=6;//程序员
RoleLevel.PrepareLevel=7;//生产准备组
RoleLevel.zkLevel=8;//总库管理员
RoleLevel.fkLevel=9;//分库管理员
RoleLevel.wcLevel=10;//技术人员
RoleLevel.mjLevel=11;//模具管理员
RoleLevel.ybLevel=12;//仪表管理员
//

function getlengthB(str){
	return str.replace(/[^\x00-\xff]/g,"**").length;
}
function test(){
	alert("test alert");
}
//是否是一般用户
function isUser(){
	return userlevel == RoleLevel.UserLev ? true : false;
}

//是否是库房管理员
function isStoreManager(){
	return userlevel == RoleLevel.StoreManagerLevel ? true : false;
}

//是否是班组长
function isTeamLeader(){
	return userlevel == RoleLevel.TeamLeaderLevel ? true : false;
}

//是否是领导
function isBoss(){
	return userlevel == RoleLevel.BossLevel ? true : false;
}

//是否是系统管理员
function isSys(){
	return userlevel == RoleLevel.SysLevel ? true : false;
}

//是否是程序员
function isPro() {
	return userlevel == RoleLevel.ProgrammerLevel ? true : false;
}

//是否是超级管理员
function isSuper(){
	return userlevel == RoleLevel.SuperLevel ? true : false;
}

//是否是生产准备组
function isPre() {
	return userlevel == RoleLevel.PrepareLevel ? true : false;
}
//是否是总库管理员
function iszk() {
	return userlevel == RoleLevel.zkLevel ? true : false;
}
//是否分库管理员
function isfk() {
	return userlevel == RoleLevel.fkLevel ? true : false;
}
//是否技术人员
function iswc() {
	return userlevel == RoleLevel.wcLevel ? true : false;
}
//是否是模具管理员
function ismj() {
	return userlevel == RoleLevel.mjLevel ? true : false;
}
//是否是仪表管理员
function isyb() {
	return userlevel == RoleLevel.ybLevel ? true : false;
}
/* //set  CookieProvider 对应后面stateId：filetable
Ext.state.Manager.setProvider(new Ext.state.CookieProvider({
	expires: new Date(new Date().getTime()+(1000*60*60*24*365))}
)); */
</script>
<%
	//不允许浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	session.setMaxInactiveInterval(-1);
%>
<!-- END Ext -->