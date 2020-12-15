Ext.onReady(function(){
	Ext.QuickTips.init();//支持tips提示
	Ext.form.Field.prototype.msgTarget='side';//提示的方式，枚举值为"qtip","title","under","side"	
	var simple = new Ext.FormPanel({
        labelWidth: 75, // label settings here cascade unless overridden
        columnWidth:.90,
        layout:"form",
        hideLabels:false,
        buttonAlign:"center",
        border:false,
        labelAlign:"right",
         bodyStyle:'padding:15px 10px 10',
       // bodyStyle:'padding:20px 10px 10',
        items: [{fieldLabel:"工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号",xtype: 'textfield',width:180,name:"stuff_num",id:"stuff_num",allowBlank:false,blankText:"工号不能为空且66位以内，请填写!",regex:/^([A-Z]|[a-z]|[\d])*$/,regexText:'工号只能由共66位内字母、数字组成！'},
                {fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",xtype: 'textfield',width:180,name:"realname",id:"realname",allowBlank:false,blankText:"姓名不能为空且66位以内，请填写!",regex:/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/,regexText:'姓名只能由共66位内汉字、字母、数字组成！'},
                {fieldLabel:"联&nbsp;系&nbsp;电&nbsp;话",xtype: 'textfield',width:180,name:"group_num",id:"group_num",allowBlank:false,blankText:"电话不能为空且15位以内，请填写!",regex:/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/,regexText:'联系方式只能由数字组成！'},
                {fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',width:180,name:"password",id:"password",style : "background: #C1C1C1;",inputType:'password',value:'111111',readOnly: true},
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
							                 boxLabel:"普通用户",//显示在复选框右边的文字
							                 inputValue:'1',
							                 position: 'absolute',
							                 name:"User",
							                 id:"User",
							                style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"专家",//显示在复选框右边的文字
							                 inputValue:'3',
							                 position: 'absolute',
							                 name:"TeamLeader",
							                 id:'TeamLeader',
							                 style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"领导",//显示在复选框右边的文字
							                 inputValue:'4',
							                 position: 'absolute',
							                 name:"Boss",
							                 id:'Boss',
							                 style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"系统管理员",//显示在复选框右边的文字
							                 inputValue:'5',
							                 position: 'absolute',
							                 checked:false,
							                 name:"Sys",
							                 id:'Sys',
							                style:"margin-left:15px;margin-top:5px"
							             },{
							                 xtype:"checkbox",
							                 boxLabel:"程序员",//显示在复选框右边的文字
							                 inputValue:'6',
							                 position: 'absolute',
							                 checked:false,
							                 name:"Programmer",
							                 id:'Programmer',
							               style:"margin-left:15px;margin-top:5px"
							             },{
							            	 xtype:"checkbox",
							                 boxLabel:"技术人员",//显示在复选框右边的文字
							                 inputValue:'10',
							                 position: 'absolute',
							                 name:"waichang",
							                 id:'waichang',
							              style:"margin-left:15px;margin-top:5px"
							             }]
					         }]
		       		},
		       		{},
		       		{fieldLabel:"备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注",xtype: 'textarea',width:180,height:80,name:"remark",id:"remark",allowBlank:true}
        ]
    });


	var Panel=new Ext.Panel({
		width:400,
		height:400,
		frame:true,
		layout:"column",
		style:'margin:10% 20%',
		buttonAlign:"center",
		draggable:true,
		title:"<p align='center'>添加用户</p>",
		items:[simple,
			{
				columnWidth:.10,
				bodyStyle:'padding:25px 0px 0',
				html:'<font color="#FF0000">*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*</font>',
				border:false
			}
		],
                buttons: [
                {
					text: '保存',
					type: 'submit',
					Align:"center",
					//定义表单提交事件
					handler:function(){
						var stuff_numjs=Ext.get("stuff_num").dom.value;
						var stuff_numReg=/^([A-Z]|[a-z]|[\d])*$/;
						
						//var order_numjs=Ext.get("order_num").dom.value;
						//var order_numReg=/^([A-Z]|[a-z]|[\d])*$/;
						
						var realnamejs=Ext.get("realname").dom.value;
						var realnameReg=/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/;
						
						var group_numjs=Ext.get("group_num").dom.value;
						var group_numReg=/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/;
						
						var remarkjs=Ext.get("remark").dom.value;
						
						if(stuff_numjs==""){
							alert('错误：用户工号不能为空！');
						}else if(realnamejs==""){
							alert('错误：用户姓名不能为空！');
						}else if(group_numjs==""){
							alert('错误：用户地址不能为空！');
						}else if(stuff_numjs.length>66){
							alert('错误：用户工号长度不能超过66个字！');
						}else if(realnamejs.length>66){
							alert('错误：用户姓名长度不能超过66个字！');
						}else if(Ext.get("password").dom.value.length>66){
							alert('错误：用户密码长度不能超过66个字！');
			            }else if(remarkjs.length>66){
							alert('错误：备注长度不能超过66个字！');
						}else if(!Ext.getCmp('User').getValue()&&!Ext.getCmp('TeamLeader').getValue()&&!Ext.getCmp('Boss').getValue()&&!Ext.getCmp('Sys').getValue()&&!Ext.getCmp('Programmer').getValue()
								&&!Ext.getCmp('waichang').getValue()){
							alert('错误：请至少选择一个角色名称！');					
						}else if(stuff_numjs!="" && !stuff_numReg.test(stuff_numjs)){
								alert('错误：工号只能由字母、数字组成！');
							}else if(realnamejs!="" && !realnameReg.test(realnamejs)){
								alert('错误：姓名只能由汉字、字母、数字组成！');
							}else if(group_numjs!="" && !group_numReg.test(group_numjs)){
								alert('错误：组别只能由汉字、字母、数字组成！');
							}
							else{
								Ext.showProgressDialog("正在提交修改数据，请稍候...");
								simple.form.doAction('submit',{
											 	url:'logicusrmod.jsp',//文件路径
											 	method:'post',//提交方法post
										 		params:{type:'USRADD',role:'multiroles'},
												 //提交成功的回调函数
												success:function(form,action){
													if (action.result.msg=='ok'){
														Ext.hideProgressDialog();
														alert('添加成功！');
														simple.form.reset();
													}else{
														Ext.hideProgressDialog();
														alert(action.result.msg);
													}
											 	},
												 //提交失败的回调函数
											 	failure:function(form,action){
											 			Ext.hideProgressDialog();
														alert('错误：服务器出现错误请稍后再试！');
												}
								});
							}
						}	
					},
				{
					text: '重置',
					handler:function(){simple.form.reset();}//重置表单
				}]
	});
    Panel.render(document.body);
});