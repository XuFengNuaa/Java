<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.nuaa.sys.util.base.SessionKeys,java.util.*"%>
<html>
<head>
<title>出错页面</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/css.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="middle"><table width="507"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="540" height="296" valign="middle" background="/epstar/system/images/error_di.gif"><table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="48%" height="131" valign="bottom"><div align="center"></div></td>
            <td width="52%" valign="bottom" class="big">&nbsp;</td>
          </tr>
          <tr>
            <td valign="middle" width="63"><div align="center"><img src="/epstar/system/images/icon.gif" width="63" height="67"></div></td>
           	<%
		      		List errs = (List)request.getAttribute(SessionKeys.REQUEST_ERROR_MSG);
		      		String strMsg ="";
		      		for (int i = 0; i < errs.size(); i++){
		      			String msg = (String)errs.get(i);
		      			strMsg = strMsg +(i+1)+".&nbsp&nbsp;&nbsp;&nbsp" + msg + "<br>";
		      		}

		      		if (strMsg.indexOf("拒绝访问") != -1){
		      			strMsg = "你可能没有权限访问！<br>请与系统管理员联系。";
		      		}
		      	if (errs.size() > 0){
      			%>
            	<td nowrap valign="middle" class="big"><%= strMsg %></td>
        	<%}else{
        	%>
        		<td nowrap valign="middle" class="big">页面出现异常，请与管理员联系！</td>
        	<%} session.invalidate();%>
          </tr>
          <tr>
            <td valign="middle">&nbsp;</td>
            <td valign="middle" class="big">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>