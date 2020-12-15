Ext.onReady(function(){	
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="side";  //校验功能
	
	var loginfrom =new Ext.form.FormPanel({
		 id:"loginfrom",
		 border : false,
		 renderTo:"loginForm",
		 width:300,
		 height:150,
		 frame:true,//颜色填充效果
		 monitorValid:true,  //监听button （1） 不满足条件不能登录
		 labelAlign:"right",
		 fileUpload:true,
		 items : [{
					xtype : "textfield",
					width : 125,
					fieldLabel : "用户名",
					style:'background-image:url('+path+'/resource/image/keyboard.png); background-repeat:no-repeat; padding-left:20px;',
					id:"username",
					name : "username",
					minLength:4,
					minLengthText:"用户名长度不能小于{0}",
					maxLength: 10,
					maxLengthText:"用户名长度不能大于{0}"	
				}
			,  {
					xtype : "textfield",
					width : 125,
					fieldLabel : "密码",
					style:'background-image:url('+path+'/resource/image/keyboard.png); background-repeat:no-repeat; padding-left:20px;',
					id:"password",
					inputType : "password",
					name : "password",
				 	allowBlank:false,
					blankText:"密码不能为空"
				},{
					xtype : "textfield",
					width:80,
					style:'background-image:url('+path+'/resource/image/keyboard.png); background-repeat:no-repeat; padding-left:20px;',
					id:"randCode",
					name:"randCode",
					fieldLabel : "验证码",
				 	allowBlank:false,
					blankText:"验证码不能为空"
					}
		],
		 buttonAlign : 'center',
		 buttons : [{
						text:"登录",
						iconCls:"login",
						formBind:true, //（2）
						handler: login
					}, {
							text : "重置",
							iconCls:"loginOut",
							handler : function() {
							loginfrom.form.reset();
									}
								}] 
});
	    var r = Ext.getDom("randCode");//获得验证码节点  记得 alert
	 	var rp= Ext.get(r.parentNode);
	 	rp.createChild({tag:"img",src:"image.jsp",align:"absbottom"});

		var win = new Ext.Window({
			width:300,
			height:185,
			title:"用户登录",
			items: loginfrom,
			resizable:false,
			draggable:false,
			closable:false
		});
	 	win.show();
	 	
function login(){
				var username=Ext.getCmp('username').getValue();
				var password=Ext.getCmp('password').getValue();// 得到form中的信息 需要使用getForm方法！ 表单提交
				var randCode=Ext.getCmp('randCode').getValue();
				Ext.Ajax.request({
							url : "login.do",
							method:'post',
		 					params:{username:username,password:password,randCode:randCode},
							waitMsg:"请稍等，登录进行中",
							callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.MessageBox.show({
													title:"成功",msg:'<span style="font-size:16px;">'+jsonStr.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:260});
													window.location.href="index.jsp"
										} else {
												Ext.MessageBox.show({
													title:"失败",msg:'<span style="font-size:14px;">'+jsonStr.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.ERROR,width:250
												})
										}
									}
	                    	
			})
}
});


