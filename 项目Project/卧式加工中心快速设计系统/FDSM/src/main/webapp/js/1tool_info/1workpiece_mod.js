Ext.ns("nuaa");
nuaa.workpiecePanel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:660,
 			height:175,
 			frame:true,
 			labelWidth: 80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.workpieceWin = Ext.extend(Ext.Window,{
			modal:true,
			height:210,
 			width:660,
 			resizable:false,
 			draggable:false});
//-------------------------------------------------------------------
function workpiece_mod(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var wp_input ={xtype:'textfield',id:"wp_inputext",fieldLabel:"输入参数  ",name:'wp_inputext', width:200};
		var wp_button={xtype:"button",text:"查 询",handler:query};
		var wp_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"wp_select_combo",
			    fieldLabel:"查询类型  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "wp_select_combo",
			        fields: [ 'selectId','selectText'],
			        data: [["wp", '零件型号'], ["jc", '机床型号']]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var wp_rank = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    //typeAhead: true, 输入自动匹配
				//selectOnFocus:true,
			    triggerAction: 'all',
			    editable:false,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "rank_combo",
			        fields: [
			            'rankId','rankText'],
			        data: [[1, '型号'], [2, '尺寸大小'],[3, '承受载荷']]
			    }),
			    valueField: 'rankId',
			    displayField: 'rankText',
			    emptyText:'---请选择---'});
		
		var wp_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"wp_sousuo",
              	width:150,               
              	//style: 'padding-top:3px;height:17px;',
                items: [{ name: 'sousuo', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column0 = { columnWidth: .33,layout: 'form',items: [wp_input]};
		var column1 = { columnWidth: .25,layout: 'form',items: [wp_select_combo]};
       	var column2 = { columnWidth: .25,layout: 'form',items: [wp_rank]};
       	var column3 = { columnWidth: .3,layout: 'form',items: [wp_sousuo]};
       	var column4 = { columnWidth: .2,layout: 'form',items: [wp_button]};
       	
		var wp_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
		//	columnWidth: .85,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:[column0,column4]},{layout: 'column',
					bodyStyle:'padding:10px 10px 10px 100px',items:[column1,column2]},
						{layout: 'column',bodyStyle:'padding:0px 10px 10px 100px',items:column3}]
	});
	
/*	var wp1Panel = new Ext.Panel({
						//layout:'column',
						columnWidth: .15,
						height:170,
						frame:true,
						html:'<img src="resource/picture/workpiece.png"></img>'
				 })*/
//------定义查询结果表格-----------------------------------------------------------------------
	//var smodel = new Ext.grid.RowSelectionModel({singleSelect:true}) //只能选一个
	var wp_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var wp_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[wp_smodel,
			{header : "ID", dataIndex : "workid",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'type',align:'center',width:115,sortable: true},
			
			{header:'转台最大直径(D)',dataIndex:'zhuantaiMaxd',width:120,align:'center'},
		//	{header:'转台高度(mm)',dataIndex:'zhuantaiH',width:115,align:'center'},
			{header:'工件直径(mm)',dataIndex:'gongjianMaxd',width:115,align:'center'},
			{header:'工件高度(mm)',dataIndex:'gongjianH',width:115,align:'center'},
			{header:'转台分度',dataIndex:'fendu',width:100,align:'center'},
			{header:'承受载荷(Kg)',dataIndex:'zaihe',width:115,align:'center'},
			{header:'切削力矩(kg/cm^2)',dataIndex:'cuttingF',width:117,align:'center'},
			{header:'减速比',dataIndex:'reRatio',width:90,align:'center'},
			{header:'最高转速(r/min)',dataIndex:'maxSpeed',width:115,align:'center'},
			{header:'机床型号',dataIndex:'course',width:115,align:'center'},
			{header:'平置高度(OH)',dataIndex:'overHeight',width:115,align:'center'}]});
	
	var msgTip;          // 一定要定义在使用前，且定义为全局变量
	var pageN = true;
    var wp_store=new Ext.data.JsonStore({  
    	id:"wp_store",
		url:"queryWp.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN){
                   msgTip = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["workid","type","zhuantaiMaxd","zhuantaiH","gongjianMaxd","gongjianH","fendu","zaihe","cuttingF","reRatio",
		"maxSpeed","overHeight","course"]});
	
	var wp_doc;
	if(role =="design"){
		wp_doc =['-','-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewWp}]
	}else{
		wp_doc =[{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addWp},'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delWp},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",handler:editWp},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewWp},'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win0 = new Ext.Window({
							//	modal:true,
							    width:370,
							    height:405,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/1workpiece.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win0.close();
											}}]
							});
							
							if(!win0.isVisible()){
						  			 win0.show();//修改窗口显示
						  			 win0.center();
						  		}
								
				}}
			]	
	}
		
	var wp_tbar=new Ext.Toolbar({
		items:wp_doc
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-170);
	//	alert(Y);
	var wp_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:wp_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:wp_columns,
		sm: wp_smodel,
		tbar: wp_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:wp_store,
					pageSize:14, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//-----------------------------button_function---------------------------------------
		 var type = {xtype:'textfield',id:"type",fieldLabel:"型号",name:'type', width:102,
		 					allowBlank: false,emptyText:'e.g: HMC-630ZH9'};
		 var zhuantaiMaxd = {xtype:'numberfield',id:"zhuantaiMaxd",fieldLabel:"转台直径",name:'zhuantaiMaxd',unitText:"mm",width:115,allowBlank: false};
		 var zhuantaiH = {xtype:'numberfield',id:"zhuantaiH",fieldLabel:"转台高度",name:'zhuantaiH',unitText:"mm", width:115,allowBlank: false};
		 var gongjianMaxd={xtype:'numberfield',id:"gongjianMaxd",fieldLabel:"工件直径",name:'gongjianMaxd',unitText:"mm", width:115,allowBlank: false};
		 var gongjianH={xtype:'numberfield',id:"gongjianH",fieldLabel:"工件高度",name:'gongjianH', unitText:"mm",width:115,allowBlank: false};
		 var overHeight={xtype:'numberfield',id:"overHeight",fieldLabel:"平置高度",name:'overHeight',unitText:"mm", width:115,allowBlank: false};
		 var fendu={xtype:'combo',id:"fendu",fieldLabel:"转台分度",name:'fendu', width:102,triggerAction: 'all',
						    editable:false,
						    mode: 'local',
							store:new Ext.data.ArrayStore({
									        id: "fendu_combo",
									        fields: ['fdId','fdText'],         
									        data: [[1, '1°*360'], [0.001, '0.001°*360000'],[3, '0.001/1']]
									    }), 
						    valueField: 'fdId',
						    displayField: 'fdText'};
		 var reRatio={xtype:'textfield',id:"reRatio",fieldLabel:"减速比",name:'reRatio', width:102,allowBlank: false,emptyText:'e.g: 1:90'};
		 var zaihe={xtype:'numberfield',id:"zaihe",fieldLabel:"承受载荷",name:'zaihe',unitText:"Mpa", width:115,allowBlank: false};
		 var maxSpeed={xtype:'numberfield',id:"maxSpeed",fieldLabel:"最高转速",name:'maxSpeed',unitText:"r/min", width:115,allowBlank: false};
		 var cuttingF={xtype:'numberfield',id:"cuttingF",fieldLabel:"切削力矩",name:'cuttingF',unitText:"kg/cm^2",width:115,allowBlank: false};
		 var course={xtype:'textfield',id:"course",fieldLabel:"机床型号",name:'course', width:106,allowBlank: false,emptyText:'e.g: HE100A'};
		 
		 var column02 = {layout: 'form',items: [{xtype:"numberfield",name:"workid",hidden:true},type,zhuantaiMaxd,zhuantaiH,gongjianMaxd]};
 		 var column03 = {layout: 'form',items: [gongjianH,overHeight,fendu,reRatio]};
 		 var column04 = {layout: 'form',items: [zaihe,maxSpeed,cuttingF,course]};
 		 
// query----------------------------------------------
function query(){
		var inputext = Ext.getCmp("wp_inputext").getValue()
		var selectextId =Ext.getCmp("wp_select_combo").getValue();
		var sousuo = Ext.getCmp("wp_sousuo").getValue();
		if(sousuo != null){	
			sousuo = sousuo.getRawValue();
		}else{
			sousuo = "wanquan";
		}
		if(inputext == undefined){
			inputext = ""
		}
		if(inputext !=""){
			if(selectextId ==null || selectextId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:16px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:165});
					return; }}
		wp_store.load({params:{inputext:inputext, selectextId:selectextId,sousuo:sousuo,start:0,limit:14},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip.hide();  
										  	pageN = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}
		})     
};
//-----add添加-----------------------------------
function addWp(){
		  var addWp_form = new nuaa.workpiecePanel({
		  		items:[column02,column03,column04],
		 	    buttons : [{ text:"确定",handler:function(){if(addWp_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","添加的数据不符合要求"); 
 							return;}
 						addWp_form.getForm().submit({
 								url:"addWp.do",
 								params: {
			        					fendu: Ext.getCmp("fendu").getValue()
			   						 },	
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:260})
										wp_store.reload(); //刷新store
										add_win.close();
										msgTip.hide();},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									add_win.close();   }}]
 																
		  });
		  var add_win = new nuaa.workpieceWin({
 					title:"信息添加", items:addWp_form });
 		  add_win.show()		
}
//-----delete删除----------------------------------
function delWp(){
		  var row =wp_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告","至少选择一条信息进行操作")
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var did=row[0].get("workid");
								Ext.Ajax.request({
									url:"delWp.do",
									params:{workid:did},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",jsonStr.msg)
										} else {
												Ext.Msg.alert("失败",jsonStr.msg)
										}
									}
								})
								wp_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editWp(){
		  var editWp_form = new nuaa.workpiecePanel({
		  		items:[column02,column03,column04],
 				buttons : [{ text:"确定",handler:function(){if(editWp_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","修改的数据不符合要求"); 
 							return;}
 				Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
					if(btn=="yes"){
 						editWp_form.getForm().submit({
 								url:"updateWp.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:210})
										wp_store.reload(); //刷新store
										edit_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
					}
 				})
 			}},
 						   { text:"取消",handler:function(){
 									edit_win.close();
 																}}]   });
 					
			var edit_win = new nuaa.workpieceWin({
 					title:"信息编辑", items:editWp_form});
 			
 			var row = wp_grid.getSelectionModel().getSelections();
 		    var record =wp_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告","至少选择一条信息进行操作");}else{
 		  		 edit_win.show(); 
 		  		 editWp_form.getForm().loadRecord(record);
 	 }				
}
//----view_预览------------------------------------
function viewWp(){
		  var viewWp_form = new nuaa.workpiecePanel({
		  		items:[column02,column03,column04],
 				buttons : [{ text:"确定",handler:function(){
 						view_win.close();
 												}}]   });
		/**	Ext.getCmp("same_name").setReadOnly(true);
			Ext.getCmp("same_pwd").setReadOnly(true);
			Ext.getCmp("same_age").setReadOnly(true);
			Ext.getCmp("same_gender").setReadOnly(true);**/
			
			var view_win = new nuaa.workpieceWin({
 					title:"信息预览",items:viewWp_form });
		
 		    var row = wp_grid.getSelectionModel().getSelections();
 		    var record =wp_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告","至少选择一条信息进行操作");}else{
 		  		  view_win.show();
 		  		  viewWp_form.getForm().loadRecord(record);}	
}
//----add tabpanel-  判断页面是否已存在----------------------------------------------------------		 
if( Ext.getCmp("toolMod") != undefined){	
				tabPanel.setActiveTab(Ext.getCmp("toolMod"));
		}else{			
				var toolPage = tabPanel.add({
					title:"零件信息管理",
					id:"toolMod",
					layout:"fit",
					closable : false,//允许关闭
					items: tool_tab
				}).show();		
				//tabPanel.setActiveTab(toolPage);
		}

if(Ext.getCmp("workpiecemod") != undefined){	
		tool_tab.setActiveTab(Ext.getCmp("workpiecemod"));
		return;
}
		var tabPage = tool_tab.add({//动态添加tab页
		title:"回转工作台",
		iconCls:'aboutUs',
		id:"workpiecemod",
		closable : true,//允许关闭    
		items:[wp_jiansuo,wp_grid]}).show();
	//	items:[{layout: 'column',items:[wp_jiansuo,wp1Panel]},wp_grid]}).show();
	//	Ext.getCmp("tool_tab").setActiveTab(tabPage);//设置当前tab页
	//	tool_tab.activate(tabPage);
   
}