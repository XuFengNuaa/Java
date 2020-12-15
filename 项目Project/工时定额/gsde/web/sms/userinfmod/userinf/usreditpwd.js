Ext.onReady(function(){
	//校验密码一致性
	Ext.apply(Ext.form.VTypes,{
	    password:function(val,field){
	       if(field.confirmTo){
	           var pwd=Ext.get(field.confirmTo);
	           return (val==pwd.getValue());
	       }
	       return true;
	    }
	});
	Ext.QuickTips.init();//支持tips提示
	Ext.form.Field.prototype.msgTarget='side';//提示的方式，枚举值为"qtip","title","under","side"
	var simpleform=new Ext.FormPanel({
				columnWidth:.80,
				layout:"form",
				id:"form",
				defaults: {width: 230},
				bodyStyle:'padding:30px 10px 0',
				labelAlign:"right",
				border:false,
				items:[
	                   {xtype: 'textfield',fieldLabel:"旧&nbsp;密&nbsp;码",name:"pwd",id:"pwd",inputType:'password',allowBlank:false,blankText:"密码不能为空，请填写!"},
	                   {xtype: 'textfield',fieldLabel:"新&nbsp;密&nbsp;码",name:"pwd1",id:"pwd1",inputType:'password',allowBlank:false,blankText:"密码不能为空，请填写!",regex:/^([A-Z]|[a-z]|[\d])*$/,regexText:'密码只能由共100位内字母、数字组成！'},
	                   {xtype: 'textfield',fieldLabel:"确认密码",name:"pwd2",id:"pwd2",inputType:'password',allowBlank:false,blankText:"密码不能为空，请填写!",vtype:"password",vtypeText:"两次密码输入不一致！",confirmTo:"pwd1"}
	            ]
    });
	var simple=new Ext.Panel({
		//renderTo:"hello",
		width:480,
		height:200,
		frame:true,
		layout:"column",
		style:'margin:10% 20%',
		buttonAlign:"center",
		title:"<p align='center'>个人密码修改</p>",
		items:[simpleform,
			{
				columnWidth:.20,
				bodyStyle:'padding:35px 0px 0',
				html:'<font color="#FF0000">*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*</font>',
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
						var pwdjs=Ext.get("pwd1").dom.value;
						var pwdReg=/^([A-Z]|[a-z]|[\d])*$/;
						
						if(Ext.get("pwd").dom.value==""){
							alert('提示：旧密码不能为空！');
						}else if(Ext.get("pwd1").dom.value==""){
							alert('提示：新密码不能为空！');
						}else if(Ext.get("pwd2").dom.value==""){
							alert('提示：确认密码不能为空！');
						}else if(Ext.get("pwd2").dom.value!=Ext.get("pwd1").dom.value){
							alert('提示：两次密码输入不一致！');
						}else if(pwdjs.length>100){
							alert('提示：密码长度不能超过限制长度！');
						}
						else{
							if(pwdjs!="" && !pwdReg.test(pwdjs)){
								alert('提示：密码只能由共100位内字母、数字组成！');
							}else{	
								Ext.showProgressDialog("正在提交修改数据，请稍候...");
								simpleform.form.doAction('submit',{
										 	url:'logicusrinfmod.jsp',//文件路径
										 	method:'post',//提交方法post
									 		params:{type:'USREDITPWD'},
											 //提交成功的回调函数
											success:function(form,action){
												Ext.hideProgressDialog();
												if(action.result.wid!='旧密码错误'){									
													alert('提示：对个人信息的所有修改已完成！');
													simpleform.form.reset();
												}else{
													alert('提示：旧密码错误！');
												}
										 	},
											 //提交失败的回调函数
										 	failure:function(){
										 		Ext.hideProgressDialog();
												alert('提示：服务器出现错误请稍后再试！');
										 	}
								});
							}
						}
					}
				},
				{
					text: '重置',
					handler:function(){simpleform.form.reset();}//重置表单
				}] 
	});
	simple.render(document.body);
});

Ext.onReady(function(){
	/*if(isWorker()){
		Ext.getDom("pwd").focus();
		Ext.get("pwd").on("focus", function(){keyBoardInput = Ext.getDom("pwd");});
		Ext.get("pwd1").on("focus", function(){keyBoardInput = Ext.getDom("pwd1");});
		Ext.get("pwd2").on("focus", function(){keyBoardInput = Ext.getDom("pwd2");});
		keyBoard();
	}*/
});

