Ext.ns("nuaa");
nuaa.SwPanel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:510,
 			height:200,
 			frame:true,
 			labelWidth: 80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.SwWin = Ext.extend(Ext.Window,{
			modal:true,
			height:225,
 			width:510,
 			resizable:false,
 			draggable:false});
//-------------------------------------------------------------------
function screw_mod(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
		var screw_input ={xtype:'textfield',id:"screw_inputext",fieldLabel:"输入参数  ",name:'screw_inputext', width:200};
		var screw_button={xtype:"button",text:"查 询",listeners:{click:querySw}}
		var screw_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"screw_select_combo",
			    fieldLabel:"查询类型  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "screw_comboStore",
			        fields: [ 'selectId','selectText'],
			        data: [["sw", '型号']]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var screw_rank_combo = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    triggerAction: 'all',
			    editable:false,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "screw_rankComboStore",
			        fields: [
			            'rankId','rankText'],
			        data: [[1, '快移速度'], [2, '电机功率'],[3,'精度等级']]
			    }),
			    valueField: 'rankId',
			    displayField: 'rankText',
			    emptyText:'---请选择---'});
		
		var screw_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"screw_sousuo",
              	width:150,               
                items: [{ name: 'sousuo4', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo4', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column40 = { columnWidth: .33,layout: 'form',items: [screw_input]};
		var column41 = { columnWidth: .3,layout: 'form',items: [screw_select_combo]};
       	var column42 = { columnWidth: .3,layout: 'form',items: [screw_rank_combo]};
       	var column43 = { columnWidth: .3,layout: 'form',items: [screw_sousuo]};
       	
		var screw_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:[column40,screw_button]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',
					items:[column41,column42]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:column43}]
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	//var smodel = new Ext.grid.RowSelectionModel({singleSelect:true}) //只能选一个
	var screw_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true})
	var screw_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[screw_smodel,
			{header : "ID", dataIndex : "swid",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'xinghao',align:'center',width:115,sortable: true},
	//		{header:'所在轴向',dataIndex:'axis',width:80,align:'center'},
			{header:'精度等级',dataIndex:'jdlevel',width:80,align:'center'},
			{header:'导程(mm)',dataIndex:'daochen',width:70,align:'center'},
			{header:'公称直径(mm)',dataIndex:'gcd',width:90,align:'center'},
			{header:'滚珠直径(mm)',dataIndex:'gunzhud',width:100,align:'center'},
			{header:'轴向刚度(N/m)',dataIndex:'zhougd',width:100,align:'center',sortable: true},
			{header:'额定动载荷(N)',dataIndex:'eddn',width:100,align:'center',sortable: true},
			{header:'额定静载荷(N)',dataIndex:'edjn',width:100,align:'center',sortable: true},
			{header:'电机功率(kw)',dataIndex:'power',width:100,align:'center',sortable: true},
			{header:'电机种类',dataIndex:'djtype',width:150,align:'center',sortable: true}
		//	{header:'机床型号',dataIndex:'course',width:100,align:'center',sortable: true}
			]});
	
			
			var msgTip4;          // 一定要定义在使用前，且定义为全局变量
			var pageN4 = true;
    var screw_store=new Ext.data.JsonStore({  
		id:"screw_store",
		url:"querySw.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
    	listeners:{
             beforeload:function(){
             	if(pageN4){
                   msgTip4 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["swid","xinghao","jdlevel","daochen","gcd","gunzhud",
				"zhougd","eddn","edjn","power","djtype"]});
	
	var screw_doc;
	if(role =="design"){
		screw_doc =['-','-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewSw}]
	}else{
		screw_doc =[{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addSw},'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delSw},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",handler:editSw},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewSw},'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win04 = new Ext.Window({
							//	modal:true,
							    width:360,
							    height:253,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/5screw.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win04.close();
											}}]
							});
							
							if(!win04.isVisible()){
						  			 win04.show();//修改窗口显示
						  			 win04.center();
						  		}
								
				}}
			]	
	}
	
	var screw_tbar=new Ext.Toolbar({
		items:screw_doc
	});
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-170);
	var screw_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:screw_store,
		viewConfig:{
	    	scrollOffset: 0
	    	},
		colModel:screw_columns,
		sm: screw_smodel,
		tbar: screw_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:screw_store,
					pageSize:14, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//-----------------------------button_function---------------------------------------
		 var xinghao = {xtype:'textfield',id:"xinghao",fieldLabel:"型号",name:'xinghao',emptyText:'e.g: NSK/EM5020-6E', width:120,allowBlank: false};
		 var jdlevel={xtype:'textfield',id:"jdlevel",fieldLabel:"精度等级",name:'jdlevel',regex:/[Cc][1-9]{1}$/,regexText:'输入错误',emptyText:'e.g: C3', width:100,allowBlank: false};
		 var daochen={xtype:'numberfield',id:"daochen",fieldLabel:"导程",name:'daochen',unitText:"mm", width:115,allowBlank: false};
		 var gcd={xtype:'numberfield',id:"gcd",fieldLabel:"公称直径",name:'gcd',unitText:"mm",width:115,allowBlank: false};
		 var gunzhud={xtype:'numberfield',id:"gunzhud",fieldLabel:"滚珠直径",name:'gunzhud',unitText:"mm", width:123,allowBlank: false};
		 var zhougd={xtype:'numberfield',id:"zhougd",fieldLabel:"轴向刚度",name:'zhougd',unitText:"N/m", width:130,allowBlank: false};
		 var eddn={xtype:'numberfield',id:"eddn",fieldLabel:"额定动载荷",name:'eddn',unitText:"N",width:115,allowBlank: false};
		 var edjn = {xtype:'numberfield',id:"edjn",fieldLabel:"额定静载荷",name:'edjn',unitText:"N", width:115,allowBlank: false};
		 var power = {xtype:'textfield',id:"power",fieldLabel:"电机功率",name:'power',unitText:"kw", width:120,allowBlank: false};
		 var djtype = {xtype:'textfield',id:"djtype",fieldLabel:"电机种类",name:'djtype',emptyText:'e.g: 1FK7105-2AF71-1RG1', width:140,allowBlank: false};
	//	 var course={xtype:'textfield',id:"course",fieldLabel:"机床型号",name:'course', width:100,allowBlank: false,emptyText:'e.g: HE100A'};
		 
		 var column02 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"swid",hidden:true},xinghao,jdlevel,daochen,gcd,gunzhud]};
 		 var column03 = {layout: 'form',columnWidth:.5,items: [zhougd,eddn,edjn,power,djtype]};
 		 
// query----------------------------------------------
function querySw(){
		var screw_inputext = Ext.getCmp("screw_inputext").getValue();
		var selectextId =Ext.getCmp("screw_select_combo").getValue();
		var screw_sousuo = Ext.getCmp("screw_sousuo").getValue();
		
		if(screw_sousuo != null){	
			screw_sousuo = screw_sousuo.getRawValue();
		}else{
			screw_sousuo = "wanquan";
		}
		if(screw_inputext == undefined){
			screw_inputext = ""
		}
		
		if(screw_inputext !=""){
			if(selectextId ==null || selectextId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:16px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:170});
					return; }}
		screw_store.load({params:{inputext:screw_inputext, selectextId:selectextId,sousuo:screw_sousuo,start:0,limit:14},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip4.hide();  
										  	pageN4 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}
		})     
};
//-----add添加-----------------------------------
function addSw(){
		  var addSw_form = new nuaa.SwPanel({
		  		items:[column02,column03],
		 	    buttons : [{ text:"确定",handler:function(){if(addSw_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","添加的数据不符合要求"); 
 							return;}
 						addSw_form.getForm().submit({
 								url:"addSw.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
										screw_store.reload(); //刷新store
										msgTip4.hide(); 
										addSw_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addSw_win.close();   }}]
 																
		  });
		  var addSw_win = new nuaa.SwWin({
 					title:"信息添加", items:addSw_form });
 		  addSw_win.show()		
}
//-----delete删除----------------------------------
function delSw(){
		  var row =screw_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>')
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var did=row[0].get("swid");
								Ext.Ajax.request({
									url:"delSw.do",
									params:{swid:did},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										} else {
												Ext.Msg.alert("失败",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										}
									}
								})
								screw_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editSw(){
		  var editSw_form = new nuaa.SwPanel({
		  		items:[column02,column03],
 				buttons : [{ text:"确定",handler:function(){if(editSw_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">修改的数据不符合要求</span>'); 
 							return;}
 				Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
					if(btn=="yes"){
 						editSw_form.getForm().submit({
 								url:"updateSw.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:240})
										screw_store.reload(); //刷新store
										editSw_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 			 		}
 				})		
 			}
 		},
 						   { text:"取消",handler:function(){
 									editSw_win.close();
 																}}]   });
 					
			var editSw_win = new nuaa.SwWin({
 					title:"信息编辑", items:editSw_form});
 			
 			var row = screw_grid.getSelectionModel().getSelections();
 		    var record =screw_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		 editSw_win.show(); 
 		  		 editSw_form.getForm().loadRecord(record);
 	 }				
}
//----view_预览------------------------------------
function viewSw(){
		  var viewSw_form = new nuaa.SwPanel({
		  		items:[column02,column03],
 				buttons : [{ text:"确定",handler:function(){
 						viewSw_win.close();
 												}}]   });
		/**	Ext.getCmp("same_name").setReadOnly(true);
			Ext.getCmp("same_pwd").setReadOnly(true);
			Ext.getCmp("same_age").setReadOnly(true);
			Ext.getCmp("same_gender").setReadOnly(true);**/
			
			var viewSw_win = new nuaa.SwWin({
 					title:"信息预览",items:viewSw_form });
		
 		    var row = screw_grid.getSelectionModel().getSelections();
 		    var record =screw_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		  viewSw_win.show();
 		  		  viewSw_form.getForm().loadRecord(record);}	
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
if(Ext.getCmp("screwmod") != undefined){	
		tool_tab.setActiveTab(Ext.getCmp("screwmod"));
				return;}
		var tabPage = tool_tab.add({
		title:"滚珠丝杠",
		id:"screwmod",
		iconCls:'aboutUs',
		closable : true,
		items:[screw_jiansuo,screw_grid]});
		
		tool_tab.setActiveTab(tabPage);
//-----------页面用到的方法-----------------------------------------------------
function toolSelect(){
		var inputext4 = Ext.getCmp("screw_inputext").getValue();
		var selectextId4 =Ext.getCmp("screw_select_combo").getValue();
		var selectext4 =Ext.getCmp("screw_select_combo").getRawValue();
		var sousuo4 = Ext.getCmp("screw_sousuo").getValue().inputValue;
		alert(inputext4 + selectextId4 +selectext4 +sousuo4 ); 
	}	
};