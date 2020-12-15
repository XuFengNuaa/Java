Ext.ns("nuaa");
nuaa.WbPanel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:550,
 			height:350,
 			frame:true,
 			labelWidth: 80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.WbWin = Ext.extend(Ext.Window,{
				modal:true,
			height:375,
 			width:550,
 			resizable:false,
 			draggable:false});
//-------------------------------------------------------------------
function workbed_mod(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var workbed_input ={xtype:'textfield',id:"workbed_inputext",fieldLabel:"输入参数  ",name:'workbed_inputext', width:200};
		var workbed_button={xtype:"button",text:"查 询",listeners:{click:queryWb}}
		var workbed_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"workbed_select_combo",
			    fieldLabel:"查询类型  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "workbed_comboStore",
			        fields: [ 'selectId','selectText'],
			        data: [["jc", '机床型号']]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var workbed_rank_combo = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    triggerAction: 'all',
			    editable:false,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "workbed_rankComboStore",
			        fields: [
			            'rankId','rankText'],
			        data: [[1, '型号'], [2, '尺寸大小'],[3, '承受载荷']]
			    }),
			    valueField: 'rankId',
			    displayField: 'rankText',
			    emptyText:'---请选择---'});
		
		var workbed_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"workbed_sousuo",
              	width:150,               
                items: [{ name: 'sousuo7', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo7', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column70 = { columnWidth: .33,layout: 'form',items: [workbed_input]};
		var column71 = { columnWidth: .3,layout: 'form',items: [workbed_select_combo]};
       	var column72 = { columnWidth: .3,layout: 'form',items: [workbed_rank_combo]};
       	var column73 = { columnWidth: .3,layout: 'form',items: [workbed_sousuo]};
       	
		var workbed_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:[column70,workbed_button]},{layout: 'column',
					bodyStyle:'padding:10px 10px 10px 100px',items:[column71,column72]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:column73}]
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	//var smodel = new Ext.grid.RowSelectionModel({singleSelect:true}) //只能选一个
	var workbed_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true})
	var workbed_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[workbed_smodel,
			{header: "ID", dataIndex : "bedid",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'beddaihao',align:'center',width:118,sortable: true},
			{header:'结构',dataIndex:'structure',width:128,align:'center'},
			{header:'材料',dataIndex:'material',width:100,align:'center'},
			{header:'长度(L)',dataIndex:'length',width:80,align:'center'},
			{header:'宽度(K)',dataIndex:'width',width:80,align:'center'},
			{header:'高度(H)',dataIndex:'height',width:80,align:'center'},
			{header:'X行程(mm)',dataIndex:'xroute',width:80,align:'center'},
			{header:'Y行程(mm)',dataIndex:'yroute',width:80,align:'center'},
			{header:'Z行程(mm)',dataIndex:'zroute',width:110,align:'center'},
			{header:'Z安装间距(Z_D)',dataIndex:'zDistance',width:105,align:'center'},
			{header:'X安装间距(X_D)',dataIndex:'xDistance',width:100,align:'center'},
			
			{header:'槽宽(C_D)',dataIndex:'caokuan',width:115,align:'center'},
			{header:'槽深',dataIndex:'caoshen',width:100,align:'center'},
				{header:'槽长',dataIndex:'caolength',width:80,align:'center'},
			{header:'减重孔径',dataIndex:'holed',width:80,align:'center'},
			{header:'板筋分布',dataIndex:'rib',width:80,align:'center'},
			{header:'上宽侧横筋数',dataIndex:'kuanheng',width:80,align:'center'},
			{header:'上宽侧竖筋数',dataIndex:'kuanshu',width:80,align:'center'},
			{header:'下窄侧横筋数',dataIndex:'zhaiheng',width:80,align:'center'},
			{header:'下窄侧竖筋数',dataIndex:'zhaishu',width:80,align:'center'},
			{header:'板筋厚度(mm)',dataIndex:'ribthick',width:80,align:'center'},
		//	{header:'排屑器',dataIndex:'chip',width:80,align:'center'},
			{header:'机床型号',dataIndex:'course',width:100,align:'center'}]});
	
		var msgTip7;          // 一定要定义在使用前，且定义为全局变量
		var pageN7 = true;
    var workbed_store=new Ext.data.JsonStore({  
		id:"workbed_store",
		url:"queryWb.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
    	listeners:{
             beforeload:function(){
             	if(pageN7){
                   msgTip7 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                  msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["bedid","beddaihao","structure","material","length","width","height","xroute",
				"yroute","zroute","zDistance","xDistance","caoshen","caokuan","caolength","holed","rib",
				"kuanheng","kuanshu","zhaiheng","zhaishu","ribthick","chip","course"]});
	
	var workbed_doc;
	if(role =="design"){
		workbed_doc =['-','-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewWb}]
	}else{
		workbed_doc =[{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addWb},'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delWb},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",handler:editWb},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewWb},
			   '-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win08 = new Ext.Window({
							//	modal:true,
							    width:475,
							    height:437,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/7workbed.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win08.close();
											}}]
							});
							
							if(!win08.isVisible()){
						  			 win08.show();//修改窗口显示
						  			 win08.center();
						  		}
								
				}}
			]	
	}
	var workbed_tbar=new Ext.Toolbar({
		items: workbed_doc
	});
		
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-170);
	var workbed_grid =new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:workbed_store,
		viewConfig:{
	    	scrollOffset: 0
	    	},
		colModel:workbed_columns,
		sm: workbed_smodel,
		tbar: workbed_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:workbed_store,
					pageSize:14, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})	
	});
//-----------------------------button_function---------------------------------------
		 var structure = {xtype:'combo',id:"structure",fieldLabel:"结构",name:'structure', width:115,allowBlank: false,triggerAction: 'all',
									    editable:false,
									    mode: 'local',
									    store:new Ext.data.ArrayStore({
										        id: "structure_combo",
										        fields: [
										            'stId','stText'],
										        data: [[1, 'T型大跨距'], [2, 'T型中央排屑结构']]
										    }), 
									    valueField: 'stId',
									    displayField:'stText',
									    emptyText:'---请选择---'};
		 var material={xtype:'textfield',id:"material",fieldLabel:"材料",name:'material',labelStyle: 'width:100px',regex:/[Hh][Tt]\d\d\d/,regexText:'输入错误',width:100,allowBlank: false};
		 var length={xtype:'numberfield',id:"length",fieldLabel:"长度",name:'length',unitText:"mm",width:115,allowBlank: false};
		 var width={xtype:'numberfield',id:"width",fieldLabel:"宽度",name:'width',labelStyle: 'width:100px',unitText:"mm", width:115,allowBlank: false};
		 var height={xtype:'numberfield',id:"height",fieldLabel:"高度",name:'height',unitText:"mm", width:115,allowBlank: false};
		 var xroute={xtype:'numberfield',id:"xroute",fieldLabel:"X行程",name:'xroute',unitText:"mm",width:115,allowBlank: false};
		 var zroute={xtype:'numberfield',id:"zroute",fieldLabel:"Z行程",name:'zroute',unitText:"mm", width:115,allowBlank: false};
		 var zDistance={xtype:'numberfield',id:"zDistance",fieldLabel:"Z安装间距",name:'zDistance',labelStyle: 'width:100px',unitText:"mm", width:115,allowBlank: false};
		 var xDistance={xtype:'numberfield',id:"xDistance",fieldLabel:"X安装间距",name:'xDistance',labelStyle: 'width:100px',unitText:"mm",width:115,allowBlank: false};
		 var caoshen = {xtype:'numberfield',id:"caoshen",fieldLabel:"槽深",name:'caoshen',unitText:"mm", width:115,allowBlank: false};
		 var caokuan = {xtype:'numberfield',id:"caokuan",fieldLabel:"槽宽",name:'caokuan',unitText:"mm", width:115,allowBlank: false};
		 var caolength = {xtype:'numberfield',id:"caolength",fieldLabel:"槽长",name:'caolength',unitText:"mm", width:115,allowBlank: false};
		 var holed = {xtype:'numberfield',id:"holed",fieldLabel:"减重孔径",name:'holed', unitText:"mm",width:115,allowBlank: false};
		 var rib = {xtype:'textfield',id:"rib",fieldLabel:"板筋分布",name:'rib',labelStyle: 'width:100px', width:102,allowBlank: false};
		 var kuanheng = {xtype:'numberfield',id:"kuanheng",fieldLabel:"上宽侧横筋数",name:'kuanheng',labelStyle: 'width:100px', width:102,allowBlank: false};
		 var kuanshu = {xtype:'numberfield',id:"kuanshu",fieldLabel:"上宽侧竖筋数",name:'kuanshu', labelStyle: 'width:100px',width:102,allowBlank: false};
		 var zhaiheng = {xtype:'numberfield',id:"zhaiheng",fieldLabel:"下窄侧横筋数",name:'zhaiheng', labelStyle: 'width:100px',width:102,allowBlank: false};
		 var zhaishu = {xtype:'numberfield',id:"zhaishu",fieldLabel:"下窄侧竖筋数",name:'zhaishu',labelStyle: 'width:100px', width:102,allowBlank: false};
		 var ribthick = {xtype:'numberfield',id:"ribthick",fieldLabel:"板筋厚度",name:'ribthick',labelStyle: 'width:100px',unitText:"mm", width:115,allowBlank: false};
	//	 var chip = {xtype:'textfield',id:"chip",fieldLabel:"排屑器",name:'chip', width:100,allowBlank: false};
		 var course={xtype:'textfield',id:"course",fieldLabel:"机床型号",name:'course', width:102,allowBlank: false,emptyText:'e.g: HE100A'};
		 
		 var column072 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"bedid",hidden:true},structure,length,height,xroute,zroute,caoshen,caokuan,caolength,holed,course]};
 		 var column073 = {layout: 'form',columnWidth:.5,items: [material,width,zDistance,xDistance,rib,kuanheng,kuanshu,zhaiheng,zhaishu,ribthick]};
 		 
// query----------------------------------------------
function queryWb(){
		var workbed_inputext = Ext.getCmp("workbed_inputext").getValue()
		var selectextId =Ext.getCmp("workbed_select_combo").getValue();
		var workbed_sousuo = Ext.getCmp("workbed_sousuo").getValue();
		
		if(workbed_sousuo != null){	
			workbed_sousuo = workbed_sousuo.getRawValue();
		}else{
			workbed_sousuo = "wanquan";
		}
		if(workbed_inputext == undefined){
			workbed_inputext = ""
		}
		
		if(workbed_inputext !=""){
			if(selectextId ==null || selectextId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:16px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:165});
					return; }}
		workbed_store.load({params:{inputext:workbed_inputext, selectextId:selectextId,sousuo:workbed_sousuo,start:0,limit:14},
							callback: function(r,options,success){
								 if(success){
								  		if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip7.hide();  
										  	pageN7 = false; }
			         }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}
		})     
};
//-----add添加-----------------------------------
function addWb(){
		  var addWb_form = new nuaa.WbPanel({
		  		items:[column072,column073],
		 	    buttons : [{ text:"确定",handler:function(){if(addWb_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","添加的数据不符合要求"); 
 							return;}
 						addWb_form.getForm().submit({
 								url:"addWb.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
										workbed_store.reload(); //刷新store
										msgTip7.hide();
										addWb_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addWb_win.close();   }}]
 																
		  });
		  var addWb_win = new nuaa.WbWin({
 					title:"信息添加", items:addWb_form });
 		  addWb_win.show()		
}
//-----delete删除----------------------------------
function delWb(){
		  var row =workbed_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>')
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var did=row[0].get("bedid");
								Ext.Ajax.request({
									url:"delWb.do",
									params:{bedid:did},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",jsonStr.msg)
										} else {
												Ext.Msg.alert("失败",jsonStr.msg)
										}
									}
								})
								workbed_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editWb(){
		  var editWb_form = new nuaa.WbPanel({
		  		items:[column072,column073],
 				buttons : [{ text:"确定",handler:function(){if(editWb_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","修改的数据不符合要求"); 
 							return;}
 				Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
					if(btn=="yes"){
 						editWb_form.getForm().submit({
 								url:"updateWb.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:240})
										workbed_store.reload(); //刷新store
										editWb_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
					}
 				})}},
 						   { text:"取消",handler:function(){
 									editWb_win.close();
 																}}]   });
 					
			var editWb_win = new nuaa.WbWin({
 					title:"信息编辑", items:editWb_form});
 			
 			var row = workbed_grid.getSelectionModel().getSelections();
 		    var record =workbed_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		 editWb_win.show(); 
 		  		 editWb_form.getForm().loadRecord(record);
 	 }				
}
//----view_预览------------------------------------
function viewWb(){
		  var viewWb_form = new nuaa.WbPanel({
		  		items:[column072,column073],
 				buttons : [{ text:"确定",handler:function(){
 						viewWb_win.close();
 												}}]   });
		/**	Ext.getCmp("same_name").setReadOnly(true);
			Ext.getCmp("same_pwd").setReadOnly(true);
			Ext.getCmp("same_age").setReadOnly(true);
			Ext.getCmp("same_gender").setReadOnly(true);**/
			
			var viewWb_win = new nuaa.WbWin({
 					title:"信息预览",items:viewWb_form });
		
 		    var row = workbed_grid.getSelectionModel().getSelections();
 		    var record =workbed_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		  viewWb_win.show();
 		  		  viewWb_form.getForm().loadRecord(record);}	
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
if(Ext.getCmp("workbedmod") != undefined){	
		tool_tab.setActiveTab(Ext.getCmp("workbedmod"));
				return;}
		var tabPage = tool_tab.add({//动态添加tab页
		title:"床身",
		id:"workbedmod",
		iconCls:'aboutUs',
		closable : true,//允许关闭
		items:[workbed_jiansuo,workbed_grid]});
		
		tool_tab.setActiveTab(tabPage);//设置当前tab页
//-----------页面用到的方法-----------------------------------------------------
function toolSelect(){
		var inputext7 = Ext.getCmp("workbed_inputext").getValue();
		var selectextId7 =Ext.getCmp("workbed_select_combo").getValue();
		var selectext7 =Ext.getCmp("workbed_select_combo").getRawValue();
		var sousuo7 = Ext.getCmp("workbed_sousuo").getValue().inputValue;
		alert(inputext7 + selectextId7 +selectext7 +sousuo7 ); 
	}	
};

//------------查询方法-----------------------------------------------
