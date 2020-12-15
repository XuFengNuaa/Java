Ext.ns("nuaa");
nuaa.userPanel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:500,
 			height:150,
 			frame:true,
 			labelWidth: 80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.userWin = Ext.extend(Ext.Window,{
			modal:true,
			plain: true,
			height:175,
 			width:500,
 			resizable:false
 		//	draggable:false
 			});
//----------------------------------------------------
function user_info(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var user_select ={xtype:'textfield',id:"user_input",fieldLabel:"查询参数 ",name:'user_input', width:200};
		var user_button={xtype:"button",text:"查 询",listeners:{click:queryUser}}
		
		var user_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"user_select_combo",
			    fieldLabel:"查询类型 ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "select_combo",
			        fields: [ 'userId','userText'],
			        data: [["num", '工号'],["name", '用户名'], ["shiro", '角色'],["job", '部门']]
			    }),
			    valueField: 'userId',
			    displayField: 'userText',
			    emptyText:'---请选择---'});	
		var user_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"user_sousuo",
              	width:150,               
                items: [{ name: 'sousuo', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column40 = { columnWidth: .25,layout: 'form',items: [user_select]};
		var column41 = { columnWidth: .3,layout: 'form',items: [user_select_combo]};
       	var column42 = { columnWidth: .3,layout: 'form',items: [user_sousuo]};
       	
		var user_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:5px 10px 10px 10px',items:[column40,user_button]},{layout: 'column',
					bodyStyle:'padding:10px',items:[column41]},
						{layout: 'column',bodyStyle:'padding:10px',items:column42}]
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	var smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true})
	var user_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[smodel,
		//	{header : "ID", dataIndex : "did",align : 'center',sortable:true },//排序开启，正序和倒序
			 new Ext.grid.RowNumberer({header:'序号',width:35,align:'center'}),
			{header: "工号", dataIndex: "usernumber",width:100,sortable: true,align:'center'},
			{header:'用户姓名',dataIndex:'username',align:'center',sortable: true},
			{header:'角色',dataIndex:'role',width:90,align:'center'},
			{header:'年龄',dataIndex:'age',width:180,align:'center'},
			{header:'所在部门',dataIndex:'job',width:175,align:'center'},
			{header:'联系电话',dataIndex:'tel',width:175,align:'center'},
			{header:'备注',dataIndex:'decription',width:175,align:'center'}]});
	
	var msgTip42;          // 一定要定义在使用前，且定义为全局变量
	var pageN40 = true;
	
    var user_store =new Ext.data.JsonStore({  
		url:"getUser.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN40){
                   msgTip42 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["uid","usernumber","username","role","age","job","tel","decription"]});
		
		
	
	var user_tbar=new Ext.Toolbar({
		items:[//{xtype:'button',text:'增加',scope:this,iconCls:"add",listeners:{click:addInfo}},
					'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delInfo},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",listeners:{click:editInfo}},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",listeners:{click:viewInfo}}
			]
	});
	var viewPort =new Ext.Viewport();
	var Y = (viewPort.getSize().height-45-30-23-170);
	var user_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:user_store,
		viewConfig:{
	    	scrollOffset: 0
	    	},
		colModel:user_columns,
		sm: smodel,
		tbar: user_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:user_store,
					pageSize:10, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true		 
		})
	});		 
//-----------------------------button_function---------------------------------------
		 var same_number = {xtype:'textfield',id:"usernumber",fieldLabel:"工号",name:'usernumber', width:115,
		 					allowBlank: false,minLength:4,minLengthText:"用户名长度不能小于{0}",maxLength: 10,
					 		maxLengthText:"工号长度不能大于{0}"	};
		 var same_name = {xtype:'textfield',id:"username",fieldLabel:"用户名",name:'username', width:115,
		 					allowBlank: false,minLength:4,minLengthText:"用户名长度不能小于{0}",maxLength: 10,
					 		maxLengthText:"用户名长度不能大于{0}"	};
		 var user_role = new Ext.data.ArrayStore({
		 			id:"user_role",
			        fields: [ 'roleId','roleText'],
			        data: [["design", '设计员'],["manager", '管理员'], ["admin", '系统管理员']]});
		 var user_job = new Ext.data.ArrayStore({
			       	id:"user_job",
			       	fields: [ 'jobId','jobText'],
					data: [["1", '技术部'],["2", '研发部'], ["3", '市场部'],["4", '管理部']]});
		 var same_role = {xtype:'combo',id:"role",fieldLabel:"角色",name:'role',width:120, typeAhead: true,
		 				 triggerAction: 'all',
					   	 editable:false,
					   	 mode: 'local',
					  	 store:user_role, 
					  	 selectOnFocus:true,
					  	 valueField: 'roleId',
					   	 displayField:'roleText',
					   	 emptyText:'---请选择---'};
		 var same_job = {xtype:'combo',id:"job",fieldLabel:"所在部门",name:'job',width:120,triggerAction: 'all',
					   	 editable:false,
					   	 mode: 'local',
					  	 store:user_job, 
					  	 valueField: 'jobId',
					   	 displayField:'jobText',
					   	 emptyText:'---请选择---'};			 		
		 var same_age = {xtype:'numberfield',id:"age",fieldLabel:"年龄",name:'age', width:115,minValue:18,
                			maxValue: 65, allowBlank: false,minLengthText:"年龄不能小于{0}",maxLengthText:"年龄不能大于{0}"};
		 var same_tel={xtype:'numberfield',id:"tel",fieldLabel:"联系电话",name:'tel', width:115,
		 					allowBlank: false,maxLength:11,maxLengthText:"输入错误！"}; 							 		
		 var column042 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"uid",hidden:true},same_number,same_age,same_tel]};
 		 var column043 = {layout: 'form',columnWidth:.5,items: [same_name,same_role,same_job]};
// query----------------------------------------------
function queryUser(){
		var userInput = Ext.getCmp("user_input").getValue()
		var selectId =Ext.getCmp("user_select_combo").getValue();
		var userModel = Ext.getCmp("user_sousuo").getValue().getRawValue();
		
		if(userInput !=""){
			if(selectId ==null || selectId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:16px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:170});
					return; }}
		user_store.load({params:{userInput:userInput, selectId:selectId,userModel:userModel,start:0,limit:10},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip42.hide();  
										  	pageN40 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}
		}) 
};
//-----add添加-----------------------------------
/* function addInfo(){
		  var add_form = new nuaa.userPanel({
		  		items:[column042,column043],
		 	    buttons : [{ text:"确定",handler:function(){if(add_form.getForm().isValid()!=true){
 							alert("添加的数据不符合要求"); 
 							return;}
 						add_form.getForm().submit({
 								url:"addPerson.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:260})
										user_store.reload(); //刷新store
										msgTip40.hide(); 
										edit_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									add_win.close();
 																}}]
		  });
		  var add_win = new nuaa.userWin({
 					title:"信息添加", items:add_form });
 		  add_win.show()		
} */
//-----delete删除----------------------------------
function delInfo(){
		  var row =user_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>')
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:18px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var uid=row[0].get("uid");
								Ext.Ajax.request({
									url:"delUser.do",
									params:{uid:uid},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										} else {
												Ext.Msg.alert("失败",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										}
									}
								})
								user_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editInfo(){
		  var edit_form = new nuaa.userPanel({
		  		items:[column042,column043],
 				buttons : [{ text:"确定",handler:function(){if(edit_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">修改的数据不符合要求</span>'); 
 							return;}
 						edit_form.getForm().submit({
 								url:"updatePerson.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:210})
										user_store.reload(); //刷新store
										edit_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									edit_win.close();
 																}}]   });
 					
			var edit_win = new nuaa.userWin({
 					title:"信息编辑", items:edit_form  });
 			
 			var row = user_grid.getSelectionModel().getSelections();
 		    var record =user_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		 edit_win.show(); 
 		  		 edit_form.getForm().loadRecord(record);
 	 }				
}
//----view_预览------------------------------------
function viewInfo(){
		  var view_form = new nuaa.userPanel({
		  		items:[column042,column043],
 				buttons : [{ text:"确定",handler:function(){
 						view_win.close();
 												}}]   });
			var view_win = new nuaa.userWin({
 					title:"信息预览",items:view_form   });
		
 		    var row = user_grid.getSelectionModel().getSelections();
 		    var record =user_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告","至少选择一条信息进行操作");}else{
 		  		  view_win.show();
 		  		  view_form.getForm().loadRecord(record);}	
}
//----add tabpanel-  判断页面是否已存在----------------------------------------------------------
if(Ext.getCmp("user_info") != undefined){	
		tabPanel.setActiveTab(Ext.getCmp("user_info"));
				return;}
		var tabPage = tabPanel.add({//动态添加tab页
		title:"用户信息管理",
		id:"user_info",
		iconCls:'userMod',
		closable : true,//允许关闭
		items:[user_jiansuo,user_grid]})
		tabPanel.setActiveTab(tabPage);//设置当前tab页
}
//------------查询方法-----------------------------------------------