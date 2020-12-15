Ext.ns("nuaa");
nuaa.sd_Panel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:550,
 			height:235,
 			frame:true,
 			labelWidth:80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.sd_Win = Ext.extend(Ext.Window,{
				modal:true,
			height:260,
 			width:550,
 			resizable:false,
 			draggable:false});
//----------------------------------------------------------------
function spindle_mod(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var spindle_input ={xtype:'textfield',id:"spindle_inputext",fieldLabel:"输入参数  ",name:'spindle_inputext', width:200};
		var spindle_button={xtype:"button",text:"查 询",listeners:{click:querySd}}
		var spindle_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"spindle_select_combo",
			    fieldLabel:"查询类型  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "spindle_comboStore",
			        fields: [ 'selectId','selectText'],
			        data: [["sd", '零件型号'], ["jc", '机床型号'], ["jxsd", '机械主轴'], ["dsd", '电主轴']]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var spindle_rank_combo = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    triggerAction: 'all',
			    editable:false,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "spindle_rankComboStore",
			        fields: [
			            'rankId','rankText'],
			        data: [[1, '功率'], [2, '转速'],[3, '扭矩']]
			    }),
			    valueField: 'rankId',
			    displayField: 'rankText',
			    emptyText:'---请选择---'});
		
		var spindle_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"spindle_sousuo",
              	width:150,               
                items: [{ name: 'sousuo2', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo2', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column20 = { columnWidth: .33,layout: 'form',items: [spindle_input]};
		var column21 = { columnWidth: .25,layout: 'form',items: [spindle_select_combo]};
       	var column22 = { columnWidth: .25,layout: 'form',items: [spindle_rank_combo]};
       	var column23 = { columnWidth: .3,layout: 'form',items: [spindle_sousuo]};
       	
		var spindle_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',
				items:[column20,spindle_button]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:[column21,column22]},
						{layout: 'column',bodyStyle:'padding:0px 10px 10px 100px',items:column23}]
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	//var smodel = new Ext.grid.RowSelectionModel({singleSelect:true}) //只能选一个
	var spindle_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true})
	var spindle_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[spindle_smodel,
			{header : "ID", dataIndex : "sindleId",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'sdType',align:'center',width:115,sortable: true},
			{header:'主轴类型',dataIndex:'kinds',width:120,align:'center'},
			{header:'机床型号',dataIndex:'course',width:108,align:'center'},
			{header:'电机功率(kw)',dataIndex:'power',width:115,align:'center'},
			{header:'最高转速(rpm)',dataIndex:'maxSpeed',width:115,align:'center'},
			{header:'主轴扭矩（N.m）',dataIndex:'torsion',width:100,align:'center'},
			{header:'锥孔形式',dataIndex:'zhuikong',width:110,align:'center'},
			{header:'主轴箱X尺寸(Sd_X)',dataIndex:'xLength',width:117,align:'center'},
			{header:'主轴箱Y尺寸(Sd_Y)',dataIndex:'yLength',width:117,align:'center'},
			{header:'端面至工作台距离(mm)',dataIndex:'duanmianZhuantai',width:128,align:'center'},
			{header:'中心至工作台距离(mm)',dataIndex:'zhongxin',width:128,align:'center'}]});
		
		var msgTip2;          // 一定要定义在使用前，且定义为全局变量
		var pageN2 = true;
    var spindle_store=new Ext.data.JsonStore({  
		url:"querySd.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
  		listeners:{
             beforeload:function(){
             	if(pageN2){
                   msgTip2 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["sindleId","sdType","kinds","course","power","maxSpeed","torsion","zhuikong","xLength",
			"yLength","duanmianZhuantai","zhongxin"]});
	
	var sd_doc;
	if(role =="design"){
		sd_doc =['-','-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewSd}]
	}else{
		sd_doc =[{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addSd},'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delSd},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",handler:editSd},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewSd},'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win02 = new Ext.Window({
							//	modal:true,
							    width:390,
							    height:345,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/2spindle.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win02.close();
											}}]
							});
							
							if(!win02.isVisible()){
						  			 win02.show();//修改窗口显示
						  			 win02.center();
						  		}
								
				}}
			]	
	}
	
	var spindle_tbar=new Ext.Toolbar({
		items:sd_doc
	});
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-170);
		
	var spindle_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:spindle_store,
		viewConfig:{
	    	scrollOffset: 0
	    	},
		colModel:spindle_columns,
		sm: spindle_smodel,
		tbar: spindle_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:spindle_store,
					pageSize:14, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//-----------------------------button_function---------------------------------------
		 var sdType = {xtype:'textfield',id:"sdType",fieldLabel:"型号",name:'sdType', width:102,labelStyle: 'width:125px',
		 					allowBlank: false,emptyText:'e.g: 罗翌RB5169'};
	//	 var kinds = {xtype:'textfield',id:"kinds",fieldLabel:"主轴类型",labelStyle: 'width:125px',name:'kinds',width:102,allowBlank: false,emptyText:'e.g: 机械主轴'};
		 var course = {xtype:'textfield',id:"course",fieldLabel:"机床型号",labelStyle: 'width:125px',name:'course', width:102,allowBlank: false};
		 var power={xtype:'textfield',id:"power",fieldLabel:"电机功率",labelStyle: 'width:125px',name:'power',unitText:"kw", width:115,allowBlank: false,emptyText:'e.g: 22/26'};
		 var maxSpeed={xtype:'numberfield',id:"maxSpeed",fieldLabel:"最高转速",labelStyle: 'width:100px',name:'maxSpeed', unitText:"rpm",width:115,allowBlank: false};
		 var torsion={xtype:'textfield',id:"torsion",fieldLabel:"主轴扭矩",labelStyle: 'width:100px',name:'torsion',unitText:"N.m", width:115,allowBlank: false,emptyText:'e.g: 770/910'};
		 var zhuikong={xtype:'textfield',id:"zhuikong",fieldLabel:"锥孔形式",labelStyle: 'width:100px',name:'zhuikong', width:98,allowBlank: false,emptyText:'e.g: HSK-A100'};
		 var xLength={xtype:'numberfield',id:"xLength",fieldLabel:"主轴箱X尺寸",labelStyle: 'width:100px',name:'xLength',unitText:"mm", width:102,allowBlank: false};
		 var yLength={xtype:'numberfield',id:"yLength",fieldLabel:"主轴箱Y尺寸",name:'yLength',unitText:"mm",labelStyle: 'width:100px', width:119,allowBlank: false};
		 var duanmianZhuantai={xtype:'textfield',id:"duanmianZhuantai",fieldLabel:"端面至工作台距离",labelStyle: 'width:125px',name:'duanmianZhuantai',emptyText:'e.g: 120-1120',unitText:"mm", width:115,allowBlank: false};
		 var zhongxin={xtype:'textfield',id:"zhongxin",fieldLabel:"中心至工作台距离",name:'zhongxin',labelStyle: 'width:125px',unitText:"mm",emptyText:'e.g: 140/990',width:115,allowBlank: false};
		 var kinds={xtype:'combo',id:"kinds",fieldLabel:"主轴类型",name:'kinds',labelStyle: 'width:125px', width:102,triggerAction: 'all',
						    editable:false,
						    mode: 'local',
							store:new Ext.data.ArrayStore({
									        id: "kinds_combo",
									        fields: ['sdId','sdText'],         
									        data: [[1, '机械主轴'], [2, '电主轴']]
									    }), 
						    valueField: 'sdId',
						    displayField: 'sdText'};
		 
		 var column02 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"sindleId",hidden:true},sdType,kinds,course,power,duanmianZhuantai,zhongxin]};
 		 var column03 = {layout: 'form',columnWidth:.5,items: [maxSpeed,torsion,zhuikong,xLength,yLength]};
 	//	 var column04 = {layout: 'form',columnWidth:.6,items: [,]};
 		 
// query----------------------------------------------
function querySd(){
		var sd_inputext = Ext.getCmp("spindle_inputext").getValue()
		var sd_selectextId =Ext.getCmp("spindle_select_combo").getValue();
		var sd_sousuo = Ext.getCmp("spindle_sousuo").getValue();
		
		if(sd_sousuo != null){	
			sd_sousuo = sd_sousuo.getRawValue();
		}else{
			sousuo = "wanquan";
		}
		if(sd_inputext == undefined){
			sd_inputext = ""
		}
		
		if(sd_inputext !=""){
			if(sd_selectextId ==null || sd_selectextId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:15px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:155});
					return; }else if(sd_selectextId =="电主轴" || sd_selectextId =="机械主轴"){ sd_selectextId =""}}
		spindle_store.load({params:{inputext:sd_inputext,selectextId:sd_selectextId,sousuo:sd_sousuo,start:0,limit:14},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip2.hide();  
										  	pageN2 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				} });
}
//-----add添加-----------------------------------
function addSd(){
		  var addSd_form = new nuaa.sd_Panel({
		  		items:[column02,column03],
		 	    buttons : [{ text:"确定",handler:function(){if(addSd_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示！","添加的数据不符合要求"); 
 							return;}
 						addSd_form.getForm().submit({
 								url:"addSd.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:260})
										spindle_store.reload(); //刷新store
										addSd_win.close();
											msgTip2.hide();},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addSd_win.close();   }}]
 																
		  });
		  var addSd_win = new nuaa.sd_Win({
 					title:"信息添加", items:addSd_form });
 		  addSd_win.show()		
}
//-----delete删除----------------------------------
function delSd(){
		  var row =spindle_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告","至少选择一条信息进行操作")
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var did=row[0].get("sindleId");
								Ext.Ajax.request({
									url:"delSd.do",
									params:{sindleId:did},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",jsonStr.msg)
										} else {
												Ext.Msg.alert("失败",jsonStr.msg)
										}
									}
								})
								spindle_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editSd(){
		  var editSd_form = new nuaa.sd_Panel({
		  		items:[column02,column03],
 				buttons : [{ text:"确定",handler:function(){if(editSd_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","修改的数据不符合要求"); 
 							return;}
 				Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
					if(btn=="yes"){
 						editSd_form.getForm().submit({
 								url:"updateSd.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:210})
										spindle_store.reload(); //刷新store
										editSd_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
					}
 				})}},
 						   { text:"取消",handler:function(){
 									editSd_win.close();
 																}}]   });
 					
			var editSd_win = new nuaa.sd_Win({
 					title:"信息编辑", items:editSd_form});
 			
 			var row = spindle_grid.getSelectionModel().getSelections();
 		    var record =spindle_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告","至少选择一条信息进行操作");}else{
 		  		 editSd_win.show(); 
 		  		 editSd_form.getForm().loadRecord(record);
 	 }				
}
//----view_预览------------------------------------
function viewSd(){
		  var viewSd_form = new nuaa.sd_Panel({
		  		items:[column02,column03],
 				buttons : [{ text:"确定",handler:function(){
 						viewSd_win.close();
 												}}]   });
		/**	Ext.getCmp("same_name").setReadOnly(true);
			Ext.getCmp("same_pwd").setReadOnly(true);
			Ext.getCmp("same_age").setReadOnly(true);
			Ext.getCmp("same_gender").setReadOnly(true);**/
			
			var viewSd_win = new nuaa.sd_Win({
 					title:"信息预览",items:viewSd_form });
		
 		    var row = spindle_grid.getSelectionModel().getSelections();
 		    var record =spindle_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告","至少选择一条信息进行操作");}else{
 		  		  viewSd_win.show();
 		  		  viewSd_form.getForm().loadRecord(record);}	
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

if(Ext.getCmp("spindlemod") != undefined){	
		tool_tab.setActiveTab(Ext.getCmp("spindlemod"));
				return;}
				
		var tabPage = tool_tab.add({
		title:"主轴",
		id:"spindlemod",
		iconCls:'aboutUs',
		closable : true,
		items:[spindle_jiansuo,spindle_grid]});
		
		tool_tab.setActiveTab(tabPage);
};

