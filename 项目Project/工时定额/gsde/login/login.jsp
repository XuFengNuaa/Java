<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<%
	if(userid==null){
		userid = request.getParameter("userid");
	}
%>
<title><%=GuiStr.SystemTitle%></title>
</head>
<body bgcolor="#288AC9" scroll=no>
<script type="text/javascript" src="/gsde/login/login.js"></script>
<link rel="stylesheet" type="text/css" href="/gsde/web/inc/resources/css/win_keyboard.css" />
<script type="text/javascript" src="/gsde/web/inc/resources/js/keyboard.js"></script>
<script type="text/javascript">
	userid="<%=userid%>";
	if(userid=="null"){
		userid="";
	}
</script>
<div id="bgdiv" class="example" style="width:100%;height:100%">
<table width="900" align="center" height="580" background="/gsde/web/inc/resources/images/custom/bg.jpg">
<tr>
	<td>&nbsp;</td>
</tr>
</table>
</div>
</body>
</html>