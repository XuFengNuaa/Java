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
	if(stuff_num=='null'){alert('未登录！');return;}//因为stuff_num会返回‘null’字符！！！！！！
    //用户信息窗口
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
	    			width:210,name:"stuff_num",id:"stuff_num",readOnly: true},
	            {	fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",xtype: 'textfield',
		            width:210,name:"username",id:"username",readOnly: true},
	            {	fieldLabel:"联&nbsp;系&nbsp;电&nbsp;话",xtype: 'textfield',
	            	width:210,name:"phonenumber",id:"phonenumber",readOnly: true},
		        {	fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',
		            width:210,name:"password",id:"password",readOnly: true},
		            
				   new Ext.form.RadioGroup({  
					    fieldLabel: '用&nbsp;户&nbsp;角&nbsp;色',  
					    width: 210,
					    readOnly: true,
					    items:[{     
					            id:"u",  
					            name: 'userrole',  
					            inputValue: '一般用户',  
					            boxLabel: '一般用户',   
					        }, {  
					            id:"m",  
					            name: 'userrole',  
					            inputValue: '管理员',  
					            boxLabel: '管理员'  
					        }]   
					}),
	       		{
	       			fieldLabel:"备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注",xtype:'textarea',
	       			width:210,height:100,name:"remark",id:"remark",readOnly: true
	       		}]
	});
	
	var win = new Ext.Window({
		modal:true,
	    width:375,
	    height:300,
	    plain: true,
	    title:"<p align='center'>用户信息查询</p>",
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
	});
	
	function successFn(result,request){
    	var objtp=Ext.util.JSON.decode(result.responseText);
    	FormPanel.form.findField('stuff_num').setValue(objtp.stuff_num); 
    	FormPanel.form.findField('userrole').setValue(objtp.userrole);
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
 
});

</script>
</head>
<body>

</body>
</html>