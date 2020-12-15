<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>铣削工艺数据库系统</title>
<link rel="stylesheet" type="text/css" href="/casting/extjs/resources/css/ext-all.css"/></link>
<link rel="stylesheet" type="text/css" href="/casting/extjs/add.css" />
<script type="text/javascript" src="/casting/extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="/casting/extjs/ext-all.js"></script>
<script type="text/javascript" src="/casting/extjs/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="/casting/jsp2/Function_menu.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	new Ext.Viewport({
		layout:'border',//Border布局将viewport面板分为东西南北中五个部分
		hideMode:'display',
	    	items:[
	    		new Ext.BoxComponent({
					region:'north',
					el:'mainframehead',
					height:40
				}),{
		    		title:'功能菜单',
		    		id:'Function_menu',	    		
		    		collapsible:true,
		    		split:true,
		    		region:'west',		
		            width: 200,
		            layout:'accordion',
		    		items:[panel1,panel2,panel3,panel4,panel5]
		    		},	 
			    				new Ext.TabPanel({								
									deferredRender:false,
									id:'tabpanel',
									region:'center',
									activeTab:0,
									items:[{	
											layout:'fit',
											title:'首页',
											id:'centertab',											
											Frame:true,
											contentEl:'contentIframe',
										}]
								})			    					    					    				    		
	    		]
	});	
});	
 
//登录界面---------------------------------------------------
function loginon(){	
	Ext.QuickTips.init();//支持tips提示
	Ext.form.Field.prototype.msgTarget='side';

	var loginonform=new Ext.form.FormPanel({
		labelWidth:75, // label settings here cascade unless overridden
	    columnWidth:.90,
	    layout:"form",
	    labelAlign:"right",
	    frame:true,
	    bodyStyle:'padding:20px 10px 20px',
		items:[
			{fieldLabel:"工&nbsp;&nbsp;&nbsp;&nbsp;号",xtype:'textfield',id:"stuff_numlogin",
    		 width:210,name:"stuff_numlogin",allowBlank:false,blankText:"工号不能为空!",
    		 regex:/^[0-9 a-z　A-Z]+$/,regexText:'只能输入字母或数字'},
    		{fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',id:"passwordlogin",
	         width:210,name:"passwordlogin",inputType:'password',allowBlank:false,blankText:"密码不能为空!",
	         regex:/^[0-9 a-z　A-Z]+$/,regexText:'只能输入字母或数字'}
		]
	});
	
	var loginonwin = new Ext.Window({
		modal:true,
	    width:375,
	    height:170,
	    closeAction:'close',
	    plain: true,
	    title:"",
	    buttonAlign:"center",
		bodyBorder:true,
	    items:loginonform,
	    buttons:[{
	            text:'登录',
	            handler:loginfn
	        	}]
	});
		loginonwin.show();//修改窗口显示


	function loginfn(){			
		var stuff_numlogin=Ext.getCmp('stuff_numlogin').getValue();
		var passwordlogin=Ext.getCmp('passwordlogin').getValue();
		Ext.Ajax.request({
		 	url:'/casting/loginon.do',
		 	method:'post',
		 	params:{stuff_numlogin:stuff_numlogin,
		 			passwordlogin:passwordlogin},
			success:function(result,request){	
				if(result.responseText == '2'){
					alert("该用户不存在！");
				}else if(result.responseText == '0'){
					alert("密码错误！");
				}else{	
					location.reload();
					//延迟1秒执行
					setTimeout(function(){ 
						alert("Hello");Ext.get("LOGIN").dom.innerHTML=result.responseText;}, 1000);
					
					
				}			
			},
	 		failure:function(){
				alert('提示:服务器出现错误请稍后再试！');
				}
	  	});	
		loginonwin.close();	
	}
		
}

//退出系统-----------------------------------------------
function loginout(){
	Ext.Ajax.request({
	 	url:'/casting/loginout.do',
	 	method:'post',
		success:function(result,request){
			alert('您已退出登录！'); 
			location.reload();
		},
 		failure:function(){
			alert('提示:服务器出现错误请稍后再试！');
			}
  	});	
	//Ext.get("LOGIN").dom.innerHTML="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录";
}
</script>

</head>
<body>
	<div id="contentIframe" >
		<iframe id="mainFrame" name="mainFrame" class="mainFrame" width="100%" height="90%" frameborder="0"></iframe>		
	</div>
	
	<div id="mainframehead">	
		<table id="mfb_table"  width="100%" height="90%" border="0" cellpadding="0" background="/casting/images/headback.jpg">
		<tr>
			<td width=680 height=50 background="/casting/images/qiexiao.png"/>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td valign="middle" >				
					<img id="roleimages" border=0 src="/casting/images/folder_user.png"/>
				<a href="javascript:loginon()" id="LOGIN">
					<font style="font-size:12px;color:#ffffff">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录  
				</font></a>
				<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
				<a href="javascript:loginout()" id="OUT">
					<font style="font-size:12px;color:#ffffff">
					<img border=0 src="/casting/images/logout.gif"/>退出系统</font>
				</a></td>
		</tr>
	</table>	
	</div>

</body>

<script type="text/javascript">
	var Username='<%=(String)session.getAttribute("Username")%>';
	if(Username != 'null'){Ext.get("LOGIN").dom.innerHTML=Username;}
</script>
</html>


<!-- $("#id1").html("关闭"); -->