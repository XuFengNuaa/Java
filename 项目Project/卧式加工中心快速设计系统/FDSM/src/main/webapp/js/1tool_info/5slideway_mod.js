Ext.ns("nuaa");
nuaa.SdwayPanel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:520,
 			height:200,
 			frame:true,
 			labelWidth: 80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.SdwayWin = Ext.extend(Ext.Window,{
				modal:true,
			height:225,
 			width:520,
 			resizable:false,
 			draggable:false});
//-------------------------------------------------------------------
function slideway_mod(){
	
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var slideway_input ={xtype:'textfield',id:"slideway_inputext",fieldLabel:"输入参数  ",name:'slideway_inputext', width:200};
		var slideway_button={xtype:"button",text:"查 询",listeners:{click:querySdway}}
		var slideway_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"slideway_select_combo",
			    fieldLabel:"查询类型  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "slideway_comboStore",
			        fields: [ 'selectId','selectText'],
			        data: [["sdway", '零件型号'], ["axis", '所在轴向'],["jd","精度等级"],["jc","机床型号"]]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var slideway_rank_combo = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    triggerAction: 'all',
			    editable:false,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "slideway_rankComboStore",
			        fields: [
			            'rankId','rankText'],
			        data: [[1, '精度等级'], [2, '定位精度'],[3, '重复定位精度']]
			    }),
			    valueField: 'rankId',
			    displayField: 'rankText',
			    emptyText:'---请选择---'});
		
		var slideway_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"slideway_sousuo",
              	width:150,               
                items: [{ name: 'sousuo5', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo5', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column50 = { columnWidth: .33,layout: 'form',items: [slideway_input]};
		var column51 = { columnWidth: .3,layout: 'form',items: [slideway_select_combo]};
       	var column52 = { columnWidth: .3,layout: 'form',items: [slideway_rank_combo]};
       	var column53 = { columnWidth: .3,layout: 'form',items: [slideway_sousuo]};
       	
		var slideway_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:[column50,slideway_button]},
					{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:[column51,column52]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:column53}]
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	//var smodel = new Ext.grid.RowSelectionModel({singleSelect:true}) //只能选一个
	var slideway_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true})
	var slideway_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[slideway_smodel,
			{header : "ID", dataIndex : "daoguiId",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'xinghao',align:'center',width:115,sortable: true},
		//	{header:'所在轴向',dataIndex:'zhou',width:80,align:'center'},
			{header:'精度等级(G)',dataIndex:'jdlevel',width:95,align:'center'},
			{header:'线轨宽度(D)',dataIndex:'width',width:95,align:'center'},
			{header:'线轨长度(L)',dataIndex:'length',width:110,align:'center'},
			{header:'aL(mm)',dataIndex:'aL',width:100,align:'center'},
			{header:'aR(mm)',dataIndex:'aR',width:100,align:'center'},
			{header:'滑块数量(W)',dataIndex:'hks',width:95,align:'center'},
			{header:'预压等级',dataIndex:'yuya',width:100,align:'center'},
			{header:'定位精度(mm)',dataIndex:'dwjd',width:100,align:'center',sortable: true},
			{header:'重复定位精度(mm)',dataIndex:'agdwjd',width:120,align:'center',sortable: true},
			{header:'机床型号',dataIndex:'course',width:100,align:'center',sortable: true}]});
	
				var msgTip5;          // 一定要定义在使用前，且定义为全局变量
			var pageN5 = true;
    var slideway_store=new Ext.data.JsonStore({  
		id:"slideway_store",
		url:"querySdway.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
 		listeners:{
             beforeload:function(){
             	if(pageN5){
                   msgTip5 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["daoguiId","xinghao","width","jdlevel","aL","aR","length","hks","yuya","dwjd","agdwjd","course"]});
	
	var slideway_doc;
	if(role =="design"){
		slideway_doc =['-','-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewSdway}]
	}else{
		slideway_doc =[{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addSdway},'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delSdway},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",handler:editSdway},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewSdway},'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win05 = new Ext.Window({
							//	modal:true,
							    width:535,
							    height:357,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/6slideway.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win05.close();
											}}]
							});
							
							if(!win05.isVisible()){
						  			 win05.show();//修改窗口显示
						  			 win05.center();
						  		}
								
				}}
			]	
	}
	var slideway_tbar=new Ext.Toolbar({
		items:slideway_doc
	});
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-170);
	var slideway_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:slideway_store,
		viewConfig:{
	    	scrollOffset: 0
	    	},
		colModel:slideway_columns,
		sm: slideway_smodel,
		tbar: slideway_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:slideway_store,
					pageSize:14, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//-----------------------------button_function---------------------------------------
		 var xinghao = {xtype:'textfield',id:"xinghao",fieldLabel:"型号",name:'xinghao',emptyText:'e.g: NSK/EM5020-6E', width:100,allowBlank: false};
		// var zhou={xtype:'textfield',id:"zhou",fieldLabel:"所在轴向",name:'zhou',emptyText:'e.g: X/Y/Z',width:100,allowBlank: false};
		 var jdlevel={xtype:'textfield',id:"jdlevel",fieldLabel:"精度等级",name:'jdlevel',regex:/^[Gg][1-9]{1}$/,regexText:'输入错误',emptyText:'e.g: G2', width:100,allowBlank: false};
		 var length={xtype:'numberfield',id:"length",fieldLabel:"线轨长度",name:'length',labelStyle: 'width:111px',unitText:"mm",width:120,allowBlank: false};
		 var width={xtype:'numberfield',id:"width",fieldLabel:"线轨宽度",name:'width',unitText:"mm",width:113,allowBlank: false};
		 var hks={xtype:'textfield',id:"hks",fieldLabel:"滑块数量",name:'hks',regex:/^[Ww][1-9]{1}$/,regexText:'输入错误', width:100,allowBlank: false};
		 var yuya={xtype:'textfield',id:"yuya",fieldLabel:"预压等级",name:'yuya',regex:/^[Vv][1-9]{1}$/,regexText:'输入错误',labelStyle: 'width:111px',width:105,allowBlank: false};
		 var dwjd = {xtype:'numberfield',id:"dwjd",fieldLabel:"定位精度",name:'dwjd',labelStyle: 'width:111px',unitText:"mm", width:120,allowBlank: false};
		 var agdwjd = {xtype:'numberfield',id:"agdwjd",fieldLabel:"重复定位精度",name:'agdwjd',labelStyle: 'width:111px',unitText:"mm",emptyText:'e.g: 1FK7105-2AF71-1RG1', width:120,allowBlank: false};
		 var course={xtype:'textfield',id:"course",fieldLabel:"机床型号",name:'course', width:100,allowBlank: false,emptyText:'e.g: HE100A'};
		 
		 var column052 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"daoguiId",hidden:true},xinghao,width,jdlevel,hks,course]};
 		 var column053 = {layout: 'form',columnWidth:.5,items: [length,yuya,dwjd,agdwjd]};
 		 
// query----------------------------------------------
function querySdway(){
		var slideway_inputext = Ext.getCmp("slideway_inputext").getValue()
		var selectextId =Ext.getCmp("slideway_select_combo").getValue();
		var slideway_sousuo = Ext.getCmp("slideway_sousuo").getValue();
		
		if(slideway_sousuo != null){	
			slideway_sousuo = slideway_sousuo.getRawValue();
		}else{
			slideway_sousuo = "wanquan";
		}
		if(slideway_inputext == undefined){
			slideway_inputext = ""
		}
		
		if(slideway_inputext !=""){
			if(selectextId ==null || selectextId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:16px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:170});
					return; }}
		slideway_store.load({params:{inputext:slideway_inputext, selectextId:selectextId,sousuo:slideway_sousuo,start:0,limit:14},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip5.hide();  
										  	pageN5 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}
		})     
};
//-----add添加-----------------------------------
function addSdway(){
		  var addSdway_form = new nuaa.SdwayPanel({
		  		items:[column052,column053],
		 	    buttons : [{ text:"确定",handler:function(){if(addSdway_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","添加的数据不符合要求"); 
 							return;}
 						addSdway_form.getForm().submit({
 								url:"addSdway.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
										slideway_store.reload(); //刷新store
										msgTip5.hide(); 
									addSdway_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addSdway_win.close();   	}}]
 																
		  });
		  var addSdway_win = new nuaa.SdwayWin({
 					title:"信息添加", items:addSdway_form });
 		 	 addSdway_win.show()		
}
//-----delete删除----------------------------------
function delSdway(){
		  var row =slideway_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>')
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var did=row[0].get("daoguiId");
								Ext.Ajax.request({
									url:"delSdway.do",
									params:{daoguiId:did},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										} else {
												Ext.Msg.alert("失败",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										}
									}
								})
								slideway_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editSdway(){
		  var editSdway_form = new nuaa.SdwayPanel({
		  		items:[column052,column053],
 				buttons : [{ text:"确定",handler:function(){if(editSdway_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">修改的数据不符合要求</span>'); 
 							return;}
 				Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
					if(btn=="yes"){
 						editSdway_form.getForm().submit({
 								url:"updateSdway.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:240})
										slideway_store.reload(); //刷新store
										editSdway_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
					}
 				})}},
 						   { text:"取消",handler:function(){
 									editSdway_win.close();
 																}}]   });
 					
			var editSdway_win = new nuaa.SdwayWin({
 					title:"信息编辑", items:editSdway_form});
 			
 			var row = slideway_grid.getSelectionModel().getSelections();
 		    var record =slideway_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		 editSdway_win.show(); 
 		  		 editSdway_form.getForm().loadRecord(record);
 	 }				
}
//----view_预览------------------------------------
function viewSdway(){
		  var viewSdway_form = new nuaa.SdwayPanel({
		  		items:[column052,column053],
 				buttons : [{ text:"确定",handler:function(){
 						viewSdway_win.close();
 												}}]   });
		/**	Ext.getCmp("same_name").setReadOnly(true);
			Ext.getCmp("same_pwd").setReadOnly(true);
			Ext.getCmp("same_age").setReadOnly(true);
			Ext.getCmp("same_gender").setReadOnly(true);**/
			
			var viewSdway_win = new nuaa.SdwayWin({
 					title:"信息预览",items:viewSdway_form });
		
 		    var row = slideway_grid.getSelectionModel().getSelections();
 		    var record =slideway_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		  viewSdway_win.show();
 		  		  viewSdway_form.getForm().loadRecord(record);}	
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
if(Ext.getCmp("slidewaymod") != undefined){	
		tool_tab.setActiveTab(Ext.getCmp("slidewaymod"));
				return;}
		var tabPage = tool_tab.add({
		title:"线性导轨",
		id:"slidewaymod",
		iconCls:'aboutUs',
		closable : true,
		items:[slideway_jiansuo,slideway_grid]});
		
		tool_tab.setActiveTab(tabPage);	
};