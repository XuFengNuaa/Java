<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/casting/extjs/resources/css/ext-all.css"/></link>
<link rel="stylesheet" type="text/css" href="/casting/extjs/add.css" />
<script type="text/javascript" src="/casting/extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="/casting/extjs/ext-all.js"></script>
<script type="text/javascript" src="/casting/extjs/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">

Ext.onReady(function(){
	var userrole = '<%=(String)session.getAttribute("Userrole")%>';
	if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权访问此页。');return;}
	//Ext.Msg.alert('测试','测试');
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
    
    var store =new Ext.data.JsonStore({ 
        fields : [ 'password', 'username','userrole','stuff_num','phonenumber','remark'],
            url : '/casting/readall.do'         
    });  
    var pageSize=20;
	store.load({params: { start: 0, limit: pageSize}});
    
    var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({	      			
    			id:'filetable',
    		    loadMask:true,
    		    enableColumnMove:false,
    		    sm:sm, //多选框checkbox
			    stateId:'filetable',
			    viewConfig:{
			    	forceFit: true,//列宽度自适应
			    	scrollOffset: 0//去除最右边空白
			    	},
    	        columns:[sm,
    	        	new Ext.grid.RowNumberer({header:"序号",width:40,align:'center'}),
    	        	{
    		            header: "角色",
    		            dataIndex: "userrole",
    		            width:100,
    		            //sortable: true
    	        	},{
    		            header: "工号",
    		            dataIndex: "stuff_num",
    		            width:100,
    		            sortable: true
    	        	},{
    		            header: "用户姓名",
    		            dataIndex: "username",
    		            width:100,
    		            sortable: true
    	        	},{
    		            header: "联系电话",
    		            dataIndex: "phonenumber",
    		            width:150,
    		            //sortable: true
    		        }, {
    		            header: "备注",
    		            dataIndex: "remark",
    		            width:350,
    		            //sortable: true
    		        }],
    		        store:store  	       
    });
    
    new Ext.Viewport({
		layout:'border',
		autoHeight:false,
		items:[{
				region:'center',
				layout:'fit',				
				title:'用户查询结果',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:['-',
							{ 								
					            id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn,
					            iconCls:"update"
					        },'-',{ 								
					            id:'del',
					            text:"删除", 
					            handler: delFn,
					            iconCls:"delete"
				            },'-']
		        }),
		        items:grid
		}] 
	});	

   
    
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
	    		{
	    		 fieldLabel:"工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号",xtype: 'textfield',
	    		 width:210,name:"stuff_num",id:"stuff_num",style : "background: #C1C1C1;", readOnly: true},
	            {
	    		 fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",xtype: 'textfield',
		         width:210,name:"username",id:"username",style : "background: #C1C1C1;", readOnly: true},
	            {
		         fieldLabel:"联&nbsp;系&nbsp;电&nbsp;话",xtype: 'textfield',width:210,name:"phonenumber",
	             id:"phonenumber",style : "background: #C1C1C1;", readOnly: true},
		        {
	             fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',
		         width:210,name:"password",id:"password",inputType:'password',
		         style : "background: #C1C1C1;", readOnly: true},
	            
				   new Ext.form.RadioGroup({  
					    fieldLabel: '用&nbsp;户&nbsp;角&nbsp;色',  
					    width: 210,  
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
       			 width:210,height:100,name:"remark",id:"remark",allowBlank:true
	       		}]
	});
	
	var win = new Ext.Window({
		id:'win',
		modal:true,
	    width:375,
	    height:320,
	    closeAction:'hide',
	    plain: true,
	    title:"<p align='center'>用户信息修改</p>",
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
	            handler:editFn
	        	}]
	   	
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
    	var record =sm.getSelections();
    	var stuff_num=record[0].json.stuff_num;
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

    function editwinFn(){
    	var record =sm.getSelections();// 返回值为 Record 类型            	
    	if(record.length==0){
            alert('提示:请先选择要修改的用户!');
            return;
        } 
        if(record.length!=1){
        	alert('提示:每次只能对一个用户进行修改!');
            return;
        }
        if(record[0].json.userrole=="系统管理员"){
        	alert('提示:此为系统管理员，不能进行修改!');
        	return false;
        }
        if(!win.isVisible()){
    		win.show();//修改窗口显示
    		win.center();
    		initializeFn();
    	}
    }
	   
  //用户修改函数
	function editFn(){					
		FormPanel.form.doAction('submit',{
		 	url:'/casting/updateuser.do',
		 	method:'post',
			success:function(form,action){							
				win.hide();	
				grid.getStore().load(); 
		 	},
			 //提交失败的回调函数
		 	failure:function(){
				alert('错误:服务器出现错误请稍后再试！');
		 	}
		});
				
	}

	//删除函数
	function delFn(){
		var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
	        alert('提示:请先选择要删除的用户!');
	        return;
		}
		if(record[0].json.userrole=="系统管理员"){
        	alert('提示:此为系统管理员，不能进行修改!');
        	return false;
        }
		var stuff_num="";
		if(record.length!=0) {
	       	Ext.MessageBox.confirm('提示','确认删除所选用户？',function(btn) {
				if(btn == 'yes') {
					for(var i=0;i<record.length;i++){
						if(i!=0){
							stuff_num+=",";
						}
						stuff_num+="'"+record[i].json.stuff_num+"'";
					}
		   			Ext.Ajax.request({
					   	url: '/casting/deluser.do',
					   	params: {stuff_num:stuff_num},
					   	success:function(result){
							if (result.responseText.indexOf("success") != -1){								
								alert('提示:所选用户已删除成功！');
								grid.getStore().load();//列表重新加载
							}else{
								alert('错误：不能删除自己的工号！');								
							}
					 	},
					   	failure: function(){
							alert('提示:服务器出现错误请稍后再试！');				   
					   	}
					});	
	            }
			});
	   	}		
	}
});

</script>
</head>
<body>

</body>
</html>