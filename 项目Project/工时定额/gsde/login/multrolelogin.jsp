<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ include file="/web/inc/jsp/head_ext.jsp" %>
<title><%=GuiStr.SystemTitle%></title>
</head>
<body bgcolor="#288AC9" scroll=no>
<div id="north" style="width:100%;height:100%">

</div>
</body>
<script>
	var viewport = new Ext.Viewport({
					       layout:'border',	                                                                      
					       items:[new Ext.BoxComponent({								            
							                    el:'north',			                   							             
							                    autoWidth:true	
					             })]
	       })	
</script>
</html>