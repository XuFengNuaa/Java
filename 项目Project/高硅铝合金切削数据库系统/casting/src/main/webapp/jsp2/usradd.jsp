<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
		Ext.QuickTips.init();//支持tips提示
		Ext.form.Field.prototype.msgTarget='side';//提示的方式，枚举值为"qtip","title","under","side"	
		
		var FormPanel = new Ext.form.FormPanel({
		    labelWidth:75, // label settings here cascade unless overridden
		    columnWidth:.90,
		    layout:"form",
		    hideLabels:false,
		    border:false,
		    labelAlign:"right",
		    bodyStyle:'padding:40px 10px 10',
		    items: [
		    		{
		    		 fieldLabel:"工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号",xtype: 'textfield',
		    		 width:210,name:"addstuff_num",id:"addstuff_num", allowBlank:false,blankText:"工号不能为空!",
		    		 regex:/^[0-9 a-z　A-Z]+$/,regexText:'只能输入字母或数字'},{xtype:'spacer',height:10},
		            {
		    		 fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",xtype: 'textfield',
			         width:210,name:"addusername",id:"addusername",allowBlank:false,blankText:"姓名不能为空!",
			         regex:/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/,regexText:'只能输入汉字、字母或数字！'},{xtype:'spacer',height:10},
		            {
			         fieldLabel:"联&nbsp;系&nbsp;电&nbsp;话",xtype: 'textfield',width:210,name:"addphonenumber",
		             id:"addphonenumber",allowBlank:false,blankText:"联系电话不能为空!",
		             regex:/^[0-9]+$/,regexText:'只能输入数字！'},{xtype:'spacer',height:10},
			        {
		             fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',
			         width:210,name:"addpassword",id:"addpassword",value:'111111',inputType:'password', 
			         allowBlank:false,blankText:"密码不能为空!"},
			         {html:"<p align='center'>初始密码为：111111</p>"},{xtype:'spacer',height:10},
				   new Ext.form.RadioGroup({  
					    fieldLabel: '用&nbsp;户&nbsp;角&nbsp;色',  
					    width: 210,  
					    items:[{     
					            name: 'addrole',  
					            boxLabel: '技术管理员',  
					            checked: true  
					        },
					        {     
					            name: 'addrole',  
					            boxLabel: '系统管理员'
					        }]   
					}),
		       		{
	       			 fieldLabel:"备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注",xtype:'textarea',
	       			 width:210,height:100,name:"addremark",id:"addremark",allowBlank:true
		       		}]
		});
		
		var win = new Ext.Window({
			id:'addwin',
			modal:true,
		    width:375,
		    height:380,
		    closeAction:'hide',
		    plain: true,
		    title:"<p align='center'>添加新用户</p>",
		    buttonAlign:"center",
			bodyBorder:true,
		    items:[
		    	new Ext.Panel({
		    		layout:'column',
		    		frame:true,
		    		height:340,
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
		            handler:saveFn
		        	}]
		   	
		});
	
		if(!win.isVisible()){
			win.show();//修改窗口显示
			win.center();
		}
		
		function saveFn(){			
			if(Ext.get("addusername").dom.value=="") alert('提示:用户姓名不能为空！');
			if(Ext.get("addusername").dom.value.length>16) alert('提示:用户姓名长度不能超过16个字！');
			if(Ext.get("addphonenumber").dom.value=="") alert('提示:联系电话不能为空！');
			if(Ext.get("addpassword").dom.value=="") alert('提示:密码不能为空！');		
		    if(Ext.get("addremark").dom.value.length>50) alert('提示:备注长度不能超过50个字！');
		    
		    FormPanel.form.doAction('submit',{
			 	url:'/casting/saveuser.do',
			 	method:'post',
				success:function(form,action){
					win.hide();
					alert('保存成功！');
					FormPanel.form.reset();
					win.show();
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