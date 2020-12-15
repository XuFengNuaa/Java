Ext.onReady(function(){
		var viewport =new Ext.Viewport({
			layout:"border",
			items : [{
				region : "north",
				height : 40,
				// 图片bodyStyle:'background-image: url(resource/image/head.png); background-repeat:no-repeat',
				items:[{
					buttons:[{
						text:'<span style="font-size:14px;">关于我们</span>',
						iconCls:'aboutUs'
				},{
						text:'<span style="font-size:14px;">退出</span>',
						iconCls:'loginOut',
						handler:function(){
								Ext.Ajax.request({
									url:"loginOut.do",
									method:'post',
									success:function(res){
										var result = Ext.util.JSON.decode(res.responseText);
										
									Ext.Msg.alert("成功",'<span style="font-size:14px;">'+result.msg+'</span>');
										window.location.href="login.jsp";
									}
							});
					}
				 }]
			  }]
			}, {
				region : "west",
				title : '<span style="font-size:14px;">功能菜单</span>',
				width : 200,
				collapsible:true,
				//split:true,
				layout:'accordion',  //折叠布局
				items:[tool_info_panel,case_info_panel,Personal_infor_panel,User_info_panel]
			}, {
				region : "center",
				id:"main",
				items:tabPanel,  //添加tabpanel
				layout:"fit"		 
			},{
				region:"south",
				border:false,
				height:20,
				tbar:['当前登录用户：【'+loginName+'】']
			}]
		});
		
	}) 