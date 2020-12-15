Ext.ns("nuaa");
nuaa.WhPanel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:480,
 			height:165,
 			frame:true,
 			labelWidth: 80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.WhWin = Ext.extend(Ext.Window,{
			//	modal:true,
			height:195,
 			width:480,
 			resizable:false
 		//	draggable:false
 			});
//-------------------------------------------------------------------
function whole_mod(){
		var whole_input ={xtype:'textfield',id:"whole_inputext",fieldLabel:"输入参数  ",name:'whole_inputext', width:200};
		var whole_button={xtype:"button",text:"查 询",listeners:{click:queryWh}}
		var whole_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"whole_select_combo",
			    fieldLabel:"查询类型  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "whole_comboStore",
			        fields: [ 'selectId','selectText'],
			        data: [["jc", '机床型号']]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var whole_rank_combo = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    triggerAction: 'all',
			    editable:false,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "whole_rank_Store",
			        fields: [ 'rId','rText'],
			        data: [["1", '型号']]
			    }), 
			    valueField: 'rId',
			    displayField: 'rText',
			    emptyText:'---请选择---'});
		
		var whole_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"whole_sousuo",
              	width:150,               
                items: [{ name: 'sousuo4', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo4', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column200 = { columnWidth: .3,layout: 'form',items: [whole_input]};
		var column201 = { columnWidth: .3,layout: 'form',items: [whole_select_combo]};
       	var column202 = { columnWidth: .3,layout: 'form',items: [whole_rank_combo]};
       	var column203 = { columnWidth: .3,layout: 'form',items: [whole_sousuo]};
       	
		var whole_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 80px',items:[column200,whole_button]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 80px',
					items:[column201,column202]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 80px',items:column203}]
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	//var smodel = new Ext.grid.RowSelectionModel({singleSelect:true}) //只能选一个
	var whole_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true})
	var whole_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[whole_smodel,
			{header : "ID", dataIndex : "mtId",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'机床型号',dataIndex:'mtType',align:'center',width:115,sortable: true},
			{header:'回转工作台',dataIndex:'mtWp',width:120,align:'center'},
			{header:'主轴',dataIndex:'mtSpindle',width:150,align:'center'},
			{header:'刀库',dataIndex:'mtAtc',width:120,align:'center'},
			{header:'滚珠丝杠',dataIndex:'mtScrew',width:140,align:'center'},
			{header:'线性导轨',dataIndex:'mtSlideway',width:120,align:'center'},
			{header:'立柱',dataIndex:'mtUpright',width:120,align:'center'},
			{header:'床身',dataIndex:'mtWorkbed',width:120,align:'center',sortable: true}]});
	
			var msgTip20;          // 一定要定义在使用前，且定义为全局变量
			var pageN20 = true;
    var whole_store=new Ext.data.JsonStore({  
		id:"whole_store",
		url:"queryWh.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
    	listeners:{
             beforeload:function(){
             	if(pageN20){
                   msgTip20 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["mtId","mtType","mtWp","mtSpindle","mtAtc","mtScrew","mtSlideway","mtUpright","mtWorkbed"]});
	
	
	var whole_doc;
	if(role =="design"){
		whole_doc =['-','-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewWh}]
	}else{
		whole_doc =[{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addWh},'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delWh},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",handler:editWh},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewWh}
			]	
	}
	
	var whole_tbar=new Ext.Toolbar({
		items: whole_doc
	});
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-27-170-15);
	var whole_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:whole_store,
		viewConfig:{
	    	scrollOffset: 0
	    	},
		colModel:whole_columns,
		sm: whole_smodel,
		tbar: whole_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:whole_store,
					pageSize:5, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//-----------------------------button_function---------------------------------------
		 var mtType = {xtype:'textfield',id:"mtType",fieldLabel:"机床型号",name:'mtType',emptyText:'e.g: HE63A', width:120,allowBlank: false};
		 var mtWp={xtype:'textfield',id:"mtWp",fieldLabel:"回转工作台",name:'mtWp',width:120,allowBlank: false};
		 var mtSpindle={xtype:'textfield',id:"mtSpindle",fieldLabel:"主轴",name:'mtSpindle',width:120,allowBlank: false};
		 var mtAtc={xtype:'textfield',id:"mtAtc",fieldLabel:"刀库",name:'mtAtc', width:120,allowBlank: false};
		 var mtScrew={xtype:'textfield',id:"mtScrew",fieldLabel:"滚珠丝杠",name:'mtScrew', width:120,allowBlank: false};
		 var mtSlideway={xtype:'textfield',id:"mtSlideway",fieldLabel:"线性导轨",name:'mtSlideway',width:120,allowBlank: false};
		 var mtUpright={xtype:'textfield',id:"mtUpright",fieldLabel:"立柱",name:'mtUpright', width:120,allowBlank: false};
		 var mtWorkbed={xtype:'textfield',id:"mtWorkbed",fieldLabel:"床身",name:'mtWorkbed', width:120,allowBlank: false};
		 
		 var column204 = {layout: 'form',columnWidth:.45,items: [{xtype:"numberfield",name:"mtId",hidden:true},mtType,mtWp,mtSpindle,mtAtc]};
 		 var column205 = {layout: 'form',columnWidth:.45,items: [mtScrew,mtSlideway,mtUpright,mtWorkbed]};
 		 var whole_WpComboStore = new Ext.data.JsonStore({
			        url:"queryWpBox.do",
					root:"extend",   
			        fields: [ 'workid','type']});
		  var whole_SdComboStore = new Ext.data.JsonStore({
			        url:"querySdBox.do",
					root:"extend",   
			        fields: [ 'sindleId','sdType']});
		  var whole_AtcComboStore = new Ext.data.JsonStore({
			        url:"queryAtcBox.do",
					root:"extend",   
			        fields: [ 'toolid','tooldaihao']});
		  var whole_SwComboStore = new Ext.data.JsonStore({
			        url:"querySwBox.do",
					root:"extend",   
			        fields: [ 'swid','xinghao']});
		  var whole_SdWayComboStore = new Ext.data.JsonStore({
			        url:"querySdWayBox.do",
					root:"extend",   
			        fields: [ 'daoguiId','xinghao']});
		  var whole_UpComboStore = new Ext.data.JsonStore({
			        url:"queryUpBox.do",
					root:"extend",   
			        fields: [ 'uprightId','upDaihao']});
		  var whole_BdComboStore = new Ext.data.JsonStore({
			        url:"queryBdBox.do",
					root:"extend",   
			        fields: [ 'bedid','beddaihao']});
		  var mtType = {xtype:'textfield',id:"mtType",fieldLabel:"机床型号",name:'mtType',emptyText:'e.g: HE63A', width:120,allowBlank: false};
		 var mtWp1={xtype:'combo',id:"mtWp",fieldLabel:"回转工作台",name:'mtWp',width:120,triggerAction: 'all',
					    editable:false,
					    mode: 'local',
					    store:whole_WpComboStore, 
					    valueField: 'workid',
					    displayField: 'type',
					    emptyText:'---请选择---'};
		 var mtSpindle1={xtype:'combo',id:"mtSpindle",fieldLabel:"主轴",name:'mtSpindle',width:120,triggerAction: 'all',
					    editable:false,
					    mode: 'local',
					    store:whole_SdComboStore, 
					    valueField: 'sindleId',
					    displayField:'sdType',
					    emptyText:'---请选择---'};
		 var mtAtc1={xtype:'combo',id:"mtAtc",fieldLabel:"刀库",name:'mtAtc', width:120,triggerAction: 'all',
					    editable:false,
					    mode: 'local',
					    store:whole_AtcComboStore, 
					    valueField: 'toolid',
					    displayField: 'tooldaihao',
					    emptyText:'---请选择---'};
		 var mtScrew1={xtype:'combo',id:"mtScrew",fieldLabel:"滚珠丝杠",name:'mtScrew', width:120,triggerAction: 'all',
					    editable:false,
					    mode: 'local',
					    store:whole_SwComboStore, 
					    valueField: 'swid',
					    displayField:'xinghao',
					    emptyText:'---请选择---'};
		 var mtSlideway1={xtype:'combo',id:"mtSlideway",fieldLabel:"公称直径",name:'mtSlideway',width:120,triggerAction: 'all',
					    editable:false,
					    mode: 'local',
					    store:whole_SdWayComboStore, 
					    valueField: 'daoguiId',
					    displayField:'xinghao',
					    emptyText:'---请选择---'};
		 var mtUpright1={xtype:'combo',id:"mtUpright",fieldLabel:"立柱",name:'mtUpright', width:120,triggerAction: 'all',
					    editable:false,
					    mode: 'local',
					    store:whole_UpComboStore, 
					    valueField: 'uprightId',
					    displayField:'upDaihao',
					    emptyText:'---请选择---'};
		 var mtWorkbed1={xtype:'combo',id:"mtWorkbed",fieldLabel:"床身",name:'mtWorkbed', width:120,triggerAction: 'all',
					    editable:false,
					    mode: 'local',
					    store:whole_BdComboStore, 
					    valueField: 'bedid',
					    displayField:'beddaihao',
					    emptyText:'---请选择---'};
		 
		 var column206 = {layout: 'form',columnWidth:.45,items: [{xtype:"numberfield",name:"mtId",hidden:true},mtType,mtWp1,mtSpindle1,mtAtc1]};
 		 var column207 = {layout: 'form',columnWidth:.45,items: [mtScrew1,mtSlideway1,mtUpright1,mtWorkbed1]};
// query----------------------------------------------
function queryWh(){
		var inputext = Ext.getCmp("whole_inputext").getValue()
		var selectextId =Ext.getCmp("whole_select_combo").getValue();
		var sousuo = Ext.getCmp("whole_sousuo").getValue().getRawValue();
		if(inputext !=""){
			if(selectextId ==null || selectextId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:16px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:170});
					return; }}
		whole_store.load({params:{inputext:inputext, selectextId:selectextId,sousuo:sousuo,start:0,limit:5},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip20.hide();  
										  	pageN20 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}
		}) 
};
//-----add添加-----------------------------------
function addWh(){
		  var addWh_form = new nuaa.WhPanel({
		  		items:[column206,column207],
		 	    buttons : [{ text:"确定",handler:function(){if(addWh_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","添加的数据不符合要求"); 
 							return;}
 						addWh_form.getForm().submit({
 								url:"addWh.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
										whole_store.reload(); //刷新store
										msgTip20.hide(); 
										addWh_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addWh_win.close();   }}]
 																
		  });
		  var addWh_win = new nuaa.WhWin({
 					title:"信息添加", items:addWh_form });
 		  addWh_win.show();
 		  whole_WpComboStore.load();
 		  whole_SdComboStore.load();
 		  whole_AtcComboStore.load();
 		  whole_SwComboStore.load();
 		  whole_SdWayComboStore.load();
 		  whole_UpComboStore.load();
 		  whole_BdComboStore.load();		  
}
//-----delete删除----------------------------------
function delWh(){
		  var row =whole_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>')
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var did=row[0].get("mtId");
								Ext.Ajax.request({
									url:"delWh.do",
									params:{mtId:did},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										} else {
												Ext.Msg.alert("失败",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										}
									}
								})
								whole_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editWh(){
		  var editWh_form = new nuaa.WhPanel({
		  		items:[column206,column207],
 				buttons : [{ text:"确定",handler:function(){if(editWh_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">修改的数据不符合要求</span>'); 
 							return;}
 						editWh_form.getForm().submit({
 								url:"updateWh.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:240})
										whole_store.reload(); //刷新store
										editWh_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									editWh_win.close();
 																}}]   });
 					
			var editWh_win = new nuaa.WhWin({
 					title:"信息编辑", items:editWh_form});
 			
 			var row = whole_grid.getSelectionModel().getSelections();
 		    var record =whole_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		 editWh_win.show(); 
 		  		 editWh_form.getForm().loadRecord(record);
 		  		 whole_WpComboStore.load();
	 			  whole_SdComboStore.load();
	 			  whole_AtcComboStore.load();
	 			  whole_SwComboStore.load();
	 			  whole_SdWayComboStore.load();
	 			  whole_UpComboStore.load();
	 			  whole_BdComboStore.load();
 	 }				
}
//----view_预览------------------------------------
function viewWh(){
		  var viewWh_form = new nuaa.WhPanel({
		  		items:[column204,column205],
 				buttons : [{ text:"确定",handler:function(){
 						viewWh_win.close();
 												}}]   });
		/**	Ext.getCmp("same_name").setReadOnly(true);
			Ext.getCmp("same_pwd").setReadOnly(true);
			Ext.getCmp("same_age").setReadOnly(true);
			Ext.getCmp("same_gender").setReadOnly(true);**/
			
			var viewWh_win = new nuaa.WhWin({
 					title:"信息预览",items:viewWh_form });
		
 		    var row = whole_grid.getSelectionModel().getSelections();
 		    var record =whole_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		  viewWh_win.show();
 		  		  viewWh_form.getForm().loadRecord(record);}	
}
//----add tabpanel-  判断页面是否已存在----------------------------------------------------------
if( Ext.getCmp("caseMod") != undefined){	
				tabPanel.setActiveTab(Ext.getCmp("caseMod"));
		}else{			
				var casePage = tabPanel.add({
					title:"实例推理管理",
					id:"caseMod",
					layout:"fit",
					closable : false,//允许关闭
					items: case_tab
				}).show();		
				//tabPanel.setActiveTab(toolPage);
		}

if(Ext.getCmp("wholemod") != undefined){	
		case_tab.setActiveTab(Ext.getCmp("wholemod"));
				return;}
		var tabPage = case_tab.add({
		title:"整机汇总",
		id:"wholemod",
		iconCls:'aboutUs',
		closable : true,
		items:[whole_jiansuo,whole_grid]})
		
		case_tab.setActiveTab(tabPage);
}