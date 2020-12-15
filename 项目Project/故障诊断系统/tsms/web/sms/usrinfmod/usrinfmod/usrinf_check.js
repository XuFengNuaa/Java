
Ext.onReady(function(){
var simple = new Ext.FormPanel({
         title:"<p align='center'>个人信息查看</p>",
         layout:"form",
         id:'simpleinfo',
         hideLabels:false,
         labelAlign:"right",
         labelWidth: 75,
         buttonAlign:"center",
         frame:true,
         bodyStyle:'padding:15px 10px 0',
        style:'margin:10% 20%',
         width:455,
         autuHeight:true,
         defaults: {width: 180},
         defaultType: 'textfield',
         waitTitle:"请稍候",
		waitMsg:"正在查询数据，请稍候。。。",
        items: [{fieldLabel:"工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号",labelStyle:"margin-top:8px;margin-left:20px",style:"margin-top:5px",name:"stuff_name",id:"stuff_num",readOnly: true},
                {fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",labelStyle:"margin-top:8px;margin-left:20px",style:"margin-top:5px",name:"realname",id:"realname",readOnly: true},
                {fieldLabel:"联&nbsp;系&nbsp;电&nbsp;话",labelStyle:"margin-top:8px;margin-left:20px",style:"margin-top:5px",name:"group_num",id:"group_num",readOnly: true},
               // {fieldLabel:"条&nbsp;形&nbsp;码",labelStyle:"margin-top:8px;margin-left:20px",style:"margin-top:5px",name:"order_num",id:"order_num",readOnly: true},
	   			{
		            xtype:'fieldset',
		            title: '用户角色<font color="#FF0000">*&nbsp;*</font>',
		            labelWidth:5,
		            width:315,
		            autoHeight:true,
					         items:[{
					               layout:'table',
					               columnWidth:.92,
					               defaults: {bodyStyle:'padding:20px'},
								   layoutConfig: {columns:3},
								   anchor:'100%-43',
								   items:[{
							                 xtype:"checkbox",
							                 boxLabel:"一般用户",//显示在复选框右边的文字
							                 inputValue:'1',
							                 name:"User",
							                 id:"User",
							                 style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"专家",//显示在复选框右边的文字
							                 inputValue:'3',
							                 name:"TeamLeader",
							                 id:'TeamLeader',
							                 style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"领导",//显示在复选框右边的文字
							                 inputValue:'4',
							                 name:"Boss",
							                 id:'Boss',
							                 style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"系统管理员",//显示在复选框右边的文字
							                 inputValue:'5',
							                 checked:false,
							                 name:"Sys",
							                 id:'Sys',
							                 style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"程序员",//显示在复选框右边的文字
							                 inputValue:'6',
							                 checked:false,
							                 name:"Programmer",
							                 id:'Programmer',
							                 style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"技术人员",//显示在复选框右边的文字
							                 inputValue:'10',
							                 checked:false,
							                 name:"waichang",
							                 id:'waichang',
							                 style:"margin-left:15px;margin-top:5px"
							             }]
					        
					     }]
		       		},
		       		{fieldLabel:"备&nbsp;&nbsp;&nbsp;注",xtype: 'textarea',labelStyle:"margin-top:8px;margin-left:20px",style:"margin-top:5px",width:230,height:60,name:"remark",id:"remark",readOnly: true}
        ]
    });
    simple.render(document.body);
    Ext.Ajax.request({
		 	url:'logicusrinfmod.jsp',//文件路径
		 	method:'post',//提交方法post
	 		params:{type:'USRVIEW'},
	 		success: successFn,
		 	failure:function(){
				alert('提示：服务器出现错误请稍后再试！');
		 	}
	  	});
	  	simple.show();
	  	
    
    //初始化成功回调函数
	function successFn(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);
		
		Ext.getCmp("stuff_num").setValue(objtp.stuff_num);
		Ext.getCmp("realname").setValue(objtp.realname);
		Ext.getCmp("group_num").setValue(objtp.group_num);
		Ext.getCmp("remark").setValue(objtp.remark);
		
		var rolenumber = parseInt(objtp.role);
		
	 	Ext.getCmp('User').setValue('false');
		Ext.getCmp('TeamLeader').setValue('false');
		Ext.getCmp('Boss').setValue('false');
		//Ext.getCmp('StoreManager').setValue('false');
		Ext.getCmp('Sys').setValue('false');
		Ext.getCmp('Programmer').setValue('false');
		//Ext.getCmp('zongku').setValue('false');
		//Ext.getCmp('fenku').setValue('false');
		Ext.getCmp('waichang').setValue('false');
		//Ext.getCmp('mj').setValue('false');
		//Ext.getCmp('yb').setValue('false');
		var usrlevels = new String(objtp.usrlevels);
		
		
		if(usrlevels.indexOf("1") != -1){
			Ext.getCmp("User").setValue('true');
		}
		if(usrlevels.indexOf("3") != -1){
			Ext.getCmp("TeamLeader").setValue('true');
		}
		if(usrlevels.indexOf("4") != -1){
			Ext.getCmp("Boss").setValue('true');
		}
		if(usrlevels.indexOf("5") != -1){
			Ext.getCmp("Sys").setValue('true');
		}
		if(usrlevels.indexOf("6") != -1){
			Ext.getCmp("Programmer").setValue('true');
		}
		if(usrlevels.indexOf("10") != -1){
			Ext.getCmp("waichang").setValue('true');

		}
	}
    

});