Ext.ns("nuaa");
nuaa.UpPanel = Ext.extend(Ext.form.FormPanel,{
			border : false,
 			layout:'column',
 			width:550,
 			height:305,
 			frame:true,
 			labelWidth: 80,
 			labelAlign:"right",
 			buttonAlign:'center' });
nuaa.UpWin = Ext.extend(Ext.Window,{
				modal:true,
			height:330,
 			width:550,
 			resizable:false,
 			draggable:false});
//-------------------------------------------------------------------
function upright_mod(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var upright_input ={xtype:'textfield',id:"upright_inputext",fieldLabel:"输入参数  ",name:'upright_inputext', width:200};
		var upright_button={xtype:"button",text:"查 询",listeners:{click:queryUp}}
		var upright_select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    id:"upright_select_combo",
			    fieldLabel:"查询类型  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "upright_comboStore",
			        fields: [ 'selectId','selectText'],
			        data: [["jc", '机床型号']]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var upright_rank_combo = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    triggerAction: 'all',
			    editable:false,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "upright_rankComboStore",
			        fields: [
			            'rankId','rankText'],
			        data: [[1, '型号'], [2, '尺寸大小'],[3, '承受载荷']]
			    }),
			    valueField: 'rankId',
			    displayField: 'rankText',
			    emptyText:'---请选择---'});
		
		var upright_sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
                id:"upright_sousuo",
              	width:150,               
                items: [{ name: 'sousuo6', inputValue: 'wanquan', boxLabel: '完全匹配', checked: true }, { name: 'sousuo6', inputValue: 'mohu', boxLabel: '模糊匹配'}]});
		
		var column60 = { columnWidth: .33,layout: 'form',items: [upright_input]};
		var column61 = { columnWidth: .3,layout: 'form',items: [upright_select_combo]};
       	var column62 = { columnWidth: .3,layout: 'form',items: [upright_rank_combo]};
       	var column63 = { columnWidth: .3,layout: 'form',items: [upright_sousuo]};
       	
		var upright_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:170,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:[column60,upright_button]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',
					items:[column61,column62]},{layout: 'column',bodyStyle:'padding:10px 10px 10px 100px',items:column63}]
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	//var smodel = new Ext.grid.RowSelectionModel({singleSelect:true}) //只能选一个
	var upright_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true})
	var upright_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[upright_smodel,
			{header: "ID", dataIndex : "uprightId",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'upDaihao',align:'center',width:118,sortable: true},
			{header:'结构',dataIndex:'structure',width:128,align:'center'},
			{header:'材料',dataIndex:'material',width:100,align:'center'},
			{header:'壁板厚度(ST)',dataIndex:'sidingthick',width:80,align:'center'},
			{header:'上底(SD)',dataIndex:'shangdi',width:80,align:'center'},
			{header:'下底(XD)',dataIndex:'xiadi',width:80,align:'center'},
			{header:'宽度(K)',dataIndex:'kuangdu',width:80,align:'center'},
			{header:'高度(H)',dataIndex:'height',width:80,align:'center'},
			{header:'Y导轨安装间距(mm)',dataIndex:'railDistance',width:110,align:'center'},
			{header:'减重孔分布',dataIndex:'reducehole',width:105,align:'center'},
			{header:'侧部孔径(CK)',dataIndex:'cehole',width:100,align:'center'},
			{header:'顶部孔径(mm)',dataIndex:'dinghole',width:100,align:'center'},
			{header:'板筋形式',dataIndex:'rib',width:115,align:'center'},
			{header:'横筋数量',dataIndex:'hengxiang',width:80,align:'center'},
			{header:'竖筋数量',dataIndex:'shuxiang',width:80,align:'center'},
			{header:'板筋厚度(mm)',dataIndex:'ribthinck',width:80,align:'center'},
			{header:'机床型号',dataIndex:'course',width:100,align:'center'}]});
	
		var msgTip6;          // 一定要定义在使用前，且定义为全局变量
		var pageN6 = true;
    var upright_store=new Ext.data.JsonStore({  
		id:"upright_store",
		url:"queryUp.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN6){
                   msgTip6 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                  msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["uprightId","upDaihao","structure","material","yRoute","sidingthick","shangdi","xiadi","kuangdu",
					"height","railDistance","reducehole","cehole","dinghole","rib","hengxiang",
					"shuxiang","ribthinck","course"]});
	
	var upright_doc;
	if(role =="design"){
		upright_doc =['-','-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewUp}]
	}else{
		upright_doc =[{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addUp},'-',
			   {xtype:'button',text:'删除',scope:this,iconCls:"delete",handler:delUp},'-',
			   {xtype:'button',text:'修改',scope:this,iconCls:"edit",handler:editUp},'-',
			   {xtype:'button',text:'预览',scope:this,iconCls:"view",handler:viewUp},'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win06 = new Ext.Window({
							//	modal:true,
							    width:400,
							    height:438,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/4upright.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win06.close();
											}}]
							});
							
							if(!win06.isVisible()){
						  			 win06.show();//修改窗口显示
						  			 win06.center();
						  		}
								
				}}
			]	
	}
	var upright_tbar=new Ext.Toolbar({
		items:upright_doc
	});
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-170);
	var upright_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:upright_store,
		viewConfig:{
	    	scrollOffset: 0
	    	},
		colModel:upright_columns,
		sm: upright_smodel,
		tbar: upright_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:upright_store,
					pageSize:14, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})	
	});
	
//-----------------------------button_function---------------------------------------
		 var structure = {xtype:'textfield',id:"structure",fieldLabel:"结构",name:'structure', width:100,allowBlank: false};
		 var material={xtype:'textfield',id:"material",fieldLabel:"材料",name:'material',width:100,regex:/[Hh][Tt]\d\d\d/,regexText:'输入错误',allowBlank: false,emptyText:'e.g: HT200'};
		 var sidingthick={xtype:'numberfield',id:"sidingthick",fieldLabel:"壁板厚度",name:'sidingthick',unitText:"mm",labelStyle: 'width:100px',width:115,allowBlank: false};
		 var shangdi={xtype:'numberfield',id:"shangdi",fieldLabel:"上底",name:'shangdi',labelStyle: 'width:100px',unitText:"mm", width:115,allowBlank: false};
		 var xiadi={xtype:'numberfield',id:"xiadi",fieldLabel:"下底",name:'xiadi',unitText:"mm", width:115,allowBlank: false};
		 var kuangdu={xtype:'numberfield',id:"kuangdu",fieldLabel:"宽度",name:'kuangdu',unitText:"mm",width:115,allowBlank: false};
		 var height={xtype:'numberfield',id:"height",fieldLabel:"高度",name:'height',labelStyle: 'width:100px',unitText:"mm", width:115,allowBlank: false};
		 var railDistance={xtype:'numberfield',id:"railDistance",fieldLabel:"Y导轨安装间距",name:'railDistance',labelStyle: 'width:100px',unitText:"mm", width:115,allowBlank: false};
		 var yroute={xtype:'numberfield',id:"yroute",fieldLabel:"Y行程",name:'yRoute',unitText:"mm", width:115,allowBlank: false};
		 var reducehole={xtype:'textfield',id:"reducehole",fieldLabel:"减重孔分布",name:'reducehole',labelStyle: 'width:100px',width:102,allowBlank: false};
		 var cehole = {xtype:'numberfield',id:"cehole",fieldLabel:"侧孔孔径",name:'cehole',unitText:"mm", width:115,allowBlank: false};
		 var dinghole = {xtype:'numberfield',id:"dinghole",fieldLabel:"顶部孔径",name:'dinghole',unitText:"mm", width:115,allowBlank: false};
		 var rib = {xtype:'textfield',id:"rib",fieldLabel:"板筋形式",name:'rib',labelStyle: 'width:100px', width:102,allowBlank: false};
		 var hengxiang = {xtype:'numberfield',id:"hengxiang",fieldLabel:"横筋数量",name:'hengxiang', width:100,allowBlank: false};
		 var shuxiang = {xtype:'numberfield',id:"shuxiang",fieldLabel:"竖筋数量",name:'shuxiang',labelStyle: 'width:100px', width:102,allowBlank: false};
		 var ribthinck = {xtype:'numberfield',id:"ribthinck",fieldLabel:"板筋厚度",name:'ribthinck',unitText:"mm", width:115,allowBlank: false};
		 var course={xtype:'textfield',id:"course",fieldLabel:"机床型号",name:'course',labelStyle: 'width:100px', width:102,allowBlank: false,emptyText:'e.g: HE100A'};
		 
		 var column02 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"uprightId",hidden:true},structure,material,xiadi,yroute,kuangdu,cehole,dinghole,hengxiang,ribthinck]};
 		 var column03 = {layout: 'form',columnWidth:.5,items: [height,shangdi,sidingthick,reducehole,railDistance,rib,shuxiang,course]};
 		 
// query----------------------------------------------
function queryUp(){
		var upright_inputext = Ext.getCmp("upright_inputext").getValue()
		var selectextId =Ext.getCmp("upright_select_combo").getValue();
		var upright_sousuo = Ext.getCmp("upright_sousuo").getValue();
		
		if(upright_sousuo != null){	
			upright_sousuo = upright_sousuo.getRawValue();
		}else{
			upright_sousuo = "wanquan";
		}
		if(upright_inputext == undefined){
			upright_inputext = ""
		}
		
		if(upright_inputext !=""){
			if(selectextId ==null || selectextId ==""){
					Ext.MessageBox.show({
					title:"警告",msg:'<span style="font-size:16px;">请选择查询类型！</span>',buttons:Ext.MessageBox.OK,width:165});
					return; }}
		upright_store.load({params:{inputext:upright_inputext, selectextId:selectextId,sousuo:upright_sousuo,start:0,limit:14},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未搜索到结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip6.hide();  
										  	pageN6 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}
		})     
};
//-----add添加-----------------------------------
function addUp(){
		  var addUp_form = new nuaa.UpPanel({
		  		items:[column02,column03],
		 	    buttons : [{ text:"确定",handler:function(){if(addUp_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","添加的数据不符合要求"); 
 							return;}
 						addUp_form.getForm().submit({
 								url:"addUp.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
										upright_store.reload(); //刷新store
											msgTip6.hide();
										addUp_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addUp_win.close();   }}]
 																
		  });
		  var addUp_win = new nuaa.UpWin({
 					title:"信息添加", items:addUp_form });
 		  addUp_win.show()		
}
//-----delete删除----------------------------------
function delUp(){
		  var row =upright_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>')
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var did=row[0].get("uprightId");
								Ext.Ajax.request({
									url:"delUp.do",
									params:{uprightId:did},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",jsonStr.msg)
										} else {
												Ext.Msg.alert("失败",jsonStr.msg)
										}
									}
								})
								upright_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editUp(){
		  var editUp_form = new nuaa.UpPanel({
		  		items:[column02,column03],
 				buttons : [{ text:"确定",handler:function(){if(editUp_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示","修改的数据不符合要求"); 
 							return;}
 				Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
					if(btn=="yes"){
 						editUp_form.getForm().submit({
 								url:"updateUp.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:240})
										upright_store.reload(); //刷新store
										editUp_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
					}
 				})}},
 						   { text:"取消",handler:function(){
 									editUp_win.close();
 																}}]   });
 					
			var editUp_win = new nuaa.UpWin({
 					title:"信息编辑", items:editUp_form});
 			
 			var row = upright_grid.getSelectionModel().getSelections();
 		    var record =upright_grid.getSelectionModel().getSelected();
 			if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		 editUp_win.show(); 
 		  		 editUp_form.getForm().loadRecord(record);
 	 }				
}
//----view_预览------------------------------------
function viewUp(){
		  var viewUp_form = new nuaa.UpPanel({
		  		items:[column02,column03],
 				buttons : [{ text:"确定",handler:function(){
 						viewUp_win.close();
 												}}]   });
		/**	Ext.getCmp("same_name").setReadOnly(true);
			Ext.getCmp("same_pwd").setReadOnly(true);
			Ext.getCmp("same_age").setReadOnly(true);
			Ext.getCmp("same_gender").setReadOnly(true);**/
			
			var viewUp_win = new nuaa.UpWin({
 					title:"信息预览",items:viewUp_form });
		
 		    var row = upright_grid.getSelectionModel().getSelections();
 		    var record =upright_grid.getSelectionModel().getSelected();
 		  
 		  	if(row.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		  viewUp_win.show();
 		  		  viewUp_form.getForm().loadRecord(record);}	
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
if(Ext.getCmp("uprightmod") != undefined){	
		tool_tab.setActiveTab(Ext.getCmp("uprightmod"));
				return;}
		var tabPage = tool_tab.add({//动态添加tab页
		title:"立柱",
		id:"uprightmod",
		iconCls:'aboutUs',
		closable : true,//允许关闭
		items:[upright_jiansuo,upright_grid]});
		
		tool_tab.setActiveTab(tabPage);//设置当前tab页	
};