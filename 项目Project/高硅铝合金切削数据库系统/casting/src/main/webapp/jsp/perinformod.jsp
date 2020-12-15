<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/casting/extjs/resources/css/ext-all.css"/></link>
<script type="text/javascript" src="/casting/extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="/casting/extjs/ext-all.js"></script>
<script type="text/javascript" src="/casting/extjs/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">

Ext.onReady(function(){
	var stuff_num='<%=(String)session.getAttribute("Stuff_num")%>';
	//因为stuff_num会返回‘null’字符！！！！！！
	if(stuff_num=='null'){alert('未登录！');return;}
	//Ext.Msg.alert('测试','测试');
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	
  //修改用户信息窗口
	var FormPanel = new Ext.form.FormPanel({
	    labelWidth:75, // label settings here cascade unless overridden
	    columnWidth:.90,
	    layout:"form",
	    hideLabels:false,
	    border:false,
	    labelAlign:"right",
	    bodyStyle:'padding:40px 10px 10',
	    items: [
	    		{	fieldLabel:"工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号",xtype: 'textfield',
	    			width:210,name:"stuff_num",id:"stuff_num",style : "background: #C1C1C1;", 
	    			allowBlank:false,readOnly: true},
	            {	fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",xtype: 'textfield',
		            width:210,name:"username",id:"username",allowBlank:false,blankText:"姓名不能为空!",
		            regex:/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/,regexText:'只能输入汉字、字母或数字！'},
	            {	fieldLabel:"联&nbsp;系&nbsp;电&nbsp;话",xtype: 'textfield',regex:/^[0-9]+$/,regexText:'只能输入数字！',
	            	width:210,name:"phonenumber",id:"phonenumber",allowBlank:false,blankText:"联系电话不能为空!"},
		        {	fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',
		            width:210,name:"password",id:"password", inputType:'password',allowBlank:false,blankText:"密码不能为空!"},
	       		{
	       			fieldLabel:"备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注",xtype:'textarea',
	       			width:210,height:100,name:"remark",id:"remark",allowBlank:true
	       		}]
	});
	
	var win = new Ext.Window({
		id:'win',
		modal:true,
	    width:375,
	    height:285,
	    closeAction:'hide',
	    plain: true,
	    title:"<p align='center'>用户信息修改</p>",
	    buttonAlign:"center",
		bodyBorder:true,
	    items:[
	    	new Ext.Panel({
	    		layout:'column',
	    		frame:true,
	    		items:[FormPanel,
	                {
						columnWidth:.10,
						bodyStyle:'padding:20px 10px 10',
						html:'<font color="#FF0000">*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*</font>',
						border:false
					}
	    		  ]
	    })],
	    buttons:[{
	            text:'保存',
	            handler:editFn
	        	}]
	   	
	});
	
	function successFn(result,request){
    	var objtp=Ext.util.JSON.decode(result.responseText);
    	FormPanel.form.findField('stuff_num').setValue(objtp.stuff_num); 
    	FormPanel.form.findField('username').setValue(objtp.username);
    	FormPanel.form.findField('phonenumber').setValue(objtp.phonenumber);
    	FormPanel.form.findField('remark').setValue(objtp.remark);
    	FormPanel.form.findField('password').setValue(objtp.password);
    }
    
	//初始化赋值
    function initializeFn(){
    	var load=Ext.Ajax.request({
    	 	url:'/casting/readuser.do',
    	 	method:'post',
     		params:{stuff_num:stuff_num},//传递stuff_num参数
    		success:successFn,
     		failure:function(){
    			alert('提示:服务器出现错误请稍后再试！');
    			}
      	});	
    }
	
    if(!win.isVisible()){
   		win.show();//修改窗口显示
   		win.center();
   		initializeFn();
   	}

	   
  //用户修改函数
	function editFn(){					
		if(Ext.get("username").dom.value=="") alert('提示:用户姓名不能为空！');
		if(Ext.get("username").dom.value.length>16) alert('提示:用户姓名长度不能超过16个字！');
		if(Ext.get("phonenumber").dom.value=="") alert('提示:联系电话不能为空！');
		if(Ext.get("password").dom.value=="") alert('提示:密码不能为空！');		
	    if(Ext.get("remark").dom.value.length>50) alert('提示:备注长度不能超过50个字！');			
		FormPanel.form.doAction('submit',{
		 	url:'/casting/personupdateuser.do',
		 	method:'post',
			success:function(form,action){							
				win.hide();	
				alert('修改成功！');
		 	},
			 //提交失败的回调函数
		 	failure:function(){
				alert('错误:服务器出现错误请稍后再试！');
		 	}
		});
				
	} 

});

</script>
</head>
<body>

</body>
</html>