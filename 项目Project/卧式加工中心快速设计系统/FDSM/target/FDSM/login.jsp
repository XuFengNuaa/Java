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
</head>
<body style="background-image: url('111.png');background-size:100% 100% ; background-attachment: fixed">
<div id=loginForm ></div>
</body>
</html>