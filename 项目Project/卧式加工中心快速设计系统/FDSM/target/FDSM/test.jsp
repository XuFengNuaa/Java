<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<% 
	String path = request.getContextPath();
	pageContext.setAttribute("APP_PATH",path);
%>
 <link rel="stylesheet" type="text/css" href="${APP_PATH}/Extjs/resources/css/ext-all.css"/></link>
 <link rel="stylesheet" type="text/css" href="${APP_PATH}/Extjs/resources/css/xtheme-gray.css"/></link>
 <link rel="stylesheet" type="text/css" href="${APP_PATH}/resource/css/cls.css"/></link>
 <script type="text/javascript">var path='<%=path %>';</script>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-base.js"> </script>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-all.js"></script>
 <script type="text/javascript" src="${APP_PATH}/Extjs/adapter/ext/ext-lang-zh_CN.js"></script>
 <script type="text/javascript" src="${APP_PATH}/js/0main_menu/login.js"></script>
 <script type="text/javascript">
 Ext.onReady(function(){
 
 			 var view_name = {xtype:'textfield',id:"view_name",fieldLabel:"用户名",name:'username', width:115};
 			 var view_pwd = {xtype:'textfield',id:"view_pwd",fieldLabel:"密码",name:'pwd', width:115};
 			 var view_age = {xtype:'textfield',id:"view_age",fieldLabel:"年龄",name:'age', width:115};
 			 var view_gender={xtype:'textfield',id:"view_gender",fieldLabel:"性别",name:'gender', width:115};
 			 
 			 var column00 = {layout: 'form',items: [view_name,view_age]};
 			 var column01 = {layout: 'form',items: [view_pwd,view_gender]};
 			 
 			 var view_form = new Ext.form.FormPanel({
 			 	id:"view_form",
 			 	border : false,
 			 	layout:'column',
 			 	width:380,
 			 	height:110,
 			 	frame:true,
 			 	labelWidth: 50,
 			 	labelAlign:"right",
 			 	items:[column00,column01],
 			 	buttonAlign:'center',
 				buttons : [{ text:"确定",handler:function(){
 						view_win.close();
 					}}]   });
 				
 				var view_win = new Ext.Window({
 					title:"信息预览",
 					height:135,
 					width:380,
 					items:view_form,
 					resizable:false,
 					draggable:false,
 					closable:false  });
 					view_win.show(); 
 				
 	}
 		
 		
 		
 	)
 
 
 
 </script>
</head>
<body>
<div ></div>
</body>
</html>