Ext.ns("nuaa");
nuaa.AtcPanel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:550,
 			height:250,
 			frame:true,
 			labelWidth: 80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.AtcWin = Ext.extend(Ext.Window,{
				modal:true,
			height:275,
 			width:550,
 			resizable:false,
 			draggable:false});
//-------------------------------------------------------------------
function atc_mod(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
		var atc_input ={xtype:'textfield',id:"atc_inputext",fieldLabel:"输入参数  ",name:'atc_inputext', width:200};
		var atc_button={xtype:"button",text:"查 询",listeners:{click:queryAtc}}
		var atc_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"atc_select_combo",
			    fieldLabel:"查询类型  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "atc_comboStore",
			        fields: [ 'selectId','selectText'],
			        data: [["jc", '机床型号'], ["kind", '刀库类型']]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var atc_rank_combo = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    triggerAction: 'all',
			    editable:false,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "atc_rankComboStore",
			        fields: [
			            'rankId','rankText'],
			        data: [[1, '刀库容量'], [2, '换刀时间']]
			    }),
			    valueField: 'rankId',
			    displayField: 'rankText',
			    emptyText:'---请选择---'});
		
		var atc_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"atc_sousuo",
              	width:150,               
                items: [{ name: 'sousuo3', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo3', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column30 = { columnWidth: .33,layout: 'form',items: [atc_input]};
		var column31 = { columnWidth: .3,layout: 'form',items: [atc_select_combo]};
       	var column32 = { columnWidth: .3,layout: 'form',items: [atc_rank_combo]};
       	var column33 = { columnWidth: .3,layout: 'form',items: [atc_sousuo]};
       	
		var atc_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:[column30,atc_button]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',
					items:[column31,column32]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:column33}]
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	//var smodel = new Ext.grid.RowSelectionModel({singleSelect:true}) //只能选一个
	var atc_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true})
	var atc_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[atc_smodel,
			{header: "ID", dataIndex : "toolid",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'tooldaihao',align:'center',width:115,sortable: true},
			{header:'刀库类型',dataIndex:'xingshi',width:100,align:'center'},
			{header:'驱动方式',dataIndex:'qudong',width:100,align:'center'},
			{header:'刀柄型式',dataIndex:'daobing',width:100,align:'center'},
			{header:'刀库容量(把）',dataIndex:'capacity',width:100,align:'center'},
			{header:'最大刀具直径（mm)',dataIndex:'maxd',width:120,align:'center'},
			{header:'最大刀具直径（相邻无刀具）',dataIndex:'maxdNull',width:160,align:'center'},
			{header:'刀具长度(mm)',dataIndex:'length',width:105,align:'center'},
			{header:'刀具重量(kg)',dataIndex:'tWeight',width:100,align:'center'},
			{header:'换刀时间(s)',dataIndex:'tooltime',width:100,align:'center'},
			{header:'钻孔直径',dataIndex:'zuanhole',width:100,align:'center'},
			{header:'攻丝螺纹孔',dataIndex:'gongsi',width:100,align:'center'},
			{header:'铣削面积(cm^3/min)',dataIndex:'xixue',width:115,align:'center'},
			{header:'机床型号',dataIndex:'course',width:100,align:'center'}]});
	
		var msgTip3;          // 一定要定义在使用前，且定义为全局变量
		var pageN3 = true;
    var atc_store=new Ext.data.JsonStore({  
		id:"atc_store",
		url:"queryAtc.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN3){
                   msgTip3 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                  msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["toolid","tooldaihao","xingshi","qudong","daobing","capacity","maxd","maxdNull",
			"length","tWeight","tooltime","zuanhole","gongsi","xixue","course"]});
	
	var atc_doc;
	if(role =="design"){
		atc_doc =['-','-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewAtc}]
	}else{
		atc_doc =[{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addAtc},'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delAtc},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",handler:editAtc},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewAtc},'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win03 = new Ext.Window({
							//	modal:true,
							    width:208,
							    height:380,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/3atc.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win03.close();
											}}]
							});
							
							if(!win03.isVisible()){
						  			 win03.show();//修改窗口显示
						  			 win03.center();
						  		}
								
				}}
			]	
	}
	
	var atc_tbar=new Ext.Toolbar({
		items: atc_doc
	});
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-170);
	var atc_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:atc_store,
		viewConfig:{
	    	scrollOffset: 0
	    	},
		colModel:atc_columns,
		sm: atc_smodel,
		tbar: atc_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:atc_store,
					pageSize:14, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//-----------------------------button_function---------------------------------------
//		 var xingshi = {xtype:'textfield',id:"xingshi",fieldLabel:"刀库类型",name:'xingshi', width:100,allowBlank: false};
//		 var qudong={xtype:'textfield',id:"qudong",fieldLabel:"驱动方式",name:'qudong',labelStyle: 'width:111px',width:100,allowBlank: false};
		 var daobing={xtype:'textfield',id:"daobing",fieldLabel:"刀柄型式",name:'daobing', labelStyle: 'width:111px',emptyText:'e.g: HSK-100A',width:100,allowBlank: false};
		 var capacity={xtype:'numberfield',id:"capacity",fieldLabel:"刀库容量",name:'capacity',unitText:"把", width:115,allowBlank: false};
		 var maxd={xtype:'numberfield',id:"maxd",fieldLabel:"最大刀具直径",name:'maxd',labelStyle: 'width:111px',unitText:"mm", width:115,allowBlank: false};
		 var maxdNull={xtype:'numberfield',id:"maxdNull",fieldLabel:"最大刀具直径（相邻无刀具）",name:'maxdNull',unitText:"mm", labelStyle: 'width:111px',width:115,allowBlank: false};
		 var length={xtype:'numberfield',id:"length",fieldLabel:"刀具长度",name:'length',unitText:"mm", width:115,allowBlank: false};
		 var tWeight={xtype:'numberfield',id:"tWeight",fieldLabel:"刀具重量",name:'tWeight',labelStyle: 'width:111px',unitText:"r/min", width:130,allowBlank: false};
		 var tooltime={xtype:'numberfield',id:"tooltime",fieldLabel:"换刀时间",name:'tooltime',unitText:"s",width:115,allowBlank: false};
		 var zuanhole = {xtype:'numberfield',id:"zuanhole",fieldLabel:"钻孔直径",name:'zuanhole',unitText:"mm", width:115,allowBlank: false};
		 var gongsi = {xtype:'numberfield',id:"gongsi",fieldLabel:"攻丝螺纹孔",name:'gongsi',labelStyle: 'width:111px',unitText:"mm", width:115,allowBlank: false};
		 var xixue = {xtype:'numberfield',id:"xixue",fieldLabel:"铣削面积",name:'xixue',unitText:"cm^3/min", width:155,allowBlank: false};
		 var course={xtype:'textfield',id:"course",fieldLabel:"机床型号",name:'course', width:106,allowBlank: false,emptyText:'e.g: HE100A'};
		 
		 var xingshi={xtype:'combo',id:"xingshi",fieldLabel:"刀库类型",name:'xingshi', width:102,triggerAction: 'all',
						    editable:false,
						    mode: 'local',
							store:new Ext.data.ArrayStore({
									        id: "xingshi_combo",
									        fields: ['atcId','atcText'],         
									        data: [[1, '凸轮快换刀库'], [2, '液压刀库']]
									    }), 
						    valueField: 'atcId',
						    displayField: 'atcText'};
		 var qudong={xtype:'combo',id:"qudong",fieldLabel:"驱动方式",name:'qudong',labelStyle: 'width:111px', width:102,triggerAction: 'all',
						    editable:false,
						    mode: 'local',
							store:new Ext.data.ArrayStore({
									        id: "qudong_combo",
									        fields: ['qdId','qdText'],         
									        data: [[1, '伺服驱动'], [2, '油压驱动']]
									    }), 
						    valueField: 'qdId',
						    displayField: 'qdText'};
		 var column02 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"toolid",hidden:true},xingshi,capacity,length,zuanhole,tooltime,xixue,course]};
 		 var column03 = {layout: 'form',columnWidth:.5,items: [qudong,daobing,maxd,maxdNull,tWeight,gongsi]};
 		 
// query----------------------------------------------
function queryAtc(){
		var atc_inputext = Ext.getCmp("atc_inputext").getValue()
		var selectextId =Ext.getCmp("atc_select_combo").getValue();
		var atc_sousuo = Ext.getCmp("atc_sousuo").getValue();
		if(atc_sousuo != null){	
			atc_sousuo = atc_sousuo.getRawValue();
		}else{
			atc_sousuo = "wanquan";
		}
		if(atc_inputext == undefined){
			atc_inputext = ""
		}
		
		if(atc_inputext !=""){
			if(selectextId ==null || selectextId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:16px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:165});
					return; }}
		atc_store.load({params:{inputext:atc_inputext, selectextId:selectextId,sousuo:atc_sousuo,start:0,limit:14},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip3.hide();  
										  	pageN3 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}
		})     
};
//-----add添加-----------------------------------
function addAtc(){
		  var addAtc_form = new nuaa.AtcPanel({
		  		items:[column02,column03],
		 	    buttons : [{ text:"确定",handler:function(){if(addAtc_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","添加的数据不符合要求"); 
 							return;}
 						addAtc_form.getForm().submit({
 								url:"addAtc.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
										atc_store.reload(); //刷新store
											msgTip3.hide();
										addAtc_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addAtc_win.close();   }}]
 																
		  });
		  var addAtc_win = new nuaa.AtcWin({
 					title:"信息添加", items:addAtc_form });
 		  addAtc_win.show()		
}
//-----delete删除----------------------------------
function delAtc(){
		  var row =atc_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告","至少选择一条信息进行操作")
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var did=row[0].get("toolid");
								Ext.Ajax.request({
									url:"delAtc.do",
									params:{toolid:did},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",jsonStr.msg)
										} else {
												Ext.Msg.alert("失败",jsonStr.msg)
										}
									}
								})
								atc_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editAtc(){
		  var editAtc_form = new nuaa.AtcPanel({
		  		items:[column02,column03],
 				buttons : [{ text:"确定",handler:function(){if(editAtc_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","修改的数据不符合要求"); 
 							return;}
 			Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
					if(btn=="yes"){
 						editAtc_form.getForm().submit({
 								url:"updateAtc.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:230})
										atc_store.reload(); //刷新store
										editAtc_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
					}
 				})}},
 						   { text:"取消",handler:function(){
 									editAtc_win.close();
 																}}]   });
 					
			var editAtc_win = new nuaa.AtcWin({
 					title:"信息编辑", items:editAtc_form});
 			
 			var row = atc_grid.getSelectionModel().getSelections();
 		    var record =atc_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告","至少选择一条信息进行操作");}else{
 		  		 editAtc_win.show(); 
 		  		 editAtc_form.getForm().loadRecord(record);
 	 }				
}
//----view_预览------------------------------------
function viewAtc(){
		  var viewAtc_form = new nuaa.AtcPanel({
		  		items:[column02,column03],
 				buttons : [{ text:"确定",handler:function(){
 						viewAtc_win.close();
 												}}]   });
		/**	Ext.getCmp("same_name").setReadOnly(true);
			Ext.getCmp("same_pwd").setReadOnly(true);
			Ext.getCmp("same_age").setReadOnly(true);
			Ext.getCmp("same_gender").setReadOnly(true);**/
			
			var viewAtc_win = new nuaa.AtcWin({
 					title:"信息预览",items:viewAtc_form });
		
 		    var row = atc_grid.getSelectionModel().getSelections();
 		    var record =atc_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告","至少选择一条信息进行操作");}else{
 		  		  viewAtc_win.show();
 		  		  viewAtc_form.getForm().loadRecord(record);}	
}
//----add-tabpanel-判断页面是否已存在----------------------------------------------------------

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
if(Ext.getCmp("atcmod") != undefined){	
		tool_tab.setActiveTab(Ext.getCmp("atcmod"));
				return;}
		var tabPage = tool_tab.add({
		title:"刀库",
		id:"atcmod",
		iconCls:'aboutUs',
		closable : true,
		items:[atc_jiansuo,atc_grid]});
		
		tool_tab.setActiveTab(tabPage);
};