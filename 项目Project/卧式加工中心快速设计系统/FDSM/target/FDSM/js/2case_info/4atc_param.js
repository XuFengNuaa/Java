function atc_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var daoBing = {xtype:'textfield',id:"daoBing",fieldLabel:"锥孔形式  ",name:'daoBing',value:Ext.util.Cookies.get("zhuiKong"),width:130};
		
			
		var jggy = new Ext.form.ComboBox({
			    typeAhead: true,
			    allowBlank:false,
			    triggerAction: 'all',
			    id:"jggy",
			    value:1,
			    fieldLabel:"加工工艺要求  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "jggy_combo",
			        fields: [ 'gyId','gyText'],
			        data: [["1", '普通工艺'], ["2", '较复杂工艺'],["3", '复杂多种工艺']]
			    }),
			    valueField: 'gyId',
			    displayField: 'gyText'
			   // emptyText:'---请选择---'
			    });
		
		var jgEffic = new Ext.form.ComboBox({
			    typeAhead: true,
			    allowBlank:false,
			    triggerAction: 'all',
			    id:"jgEffic",
			    value:1,
			    fieldLabel:"加工节拍  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "jgEffic_combo",
			        fields: [ 'EffId','EffText'],
			        data: [["1", '普通型'], ["2", '高速高效']]
			    }),
			    valueField: 'EffId',
			    displayField: 'EffText'
			   // emptyText:'---请选择---'
			    });
			    
		var atc_output={xtype:"button",text:"输出",handler:reasonAtc};
		var atc_next={xtype:"button",text:"下一步",handler:function(){
								
								var row4 =atc_grid.getSelectionModel().getSelections();
								
								if(row4.length !=0){
										
								//	alert(Ext.util.Cookies.get("zhuiKong"));
									var jggy = Ext.getCmp("jggy").getValue();
									var jgEffic =Ext.getCmp("jgEffic").getValue();
								//	alert(jggy + "-" + jgEffic);
							
									Ext.util.Cookies.set("toolid",row4[0].get("toolid"));  //记录选中的id
									Ext.util.Cookies.set("tooldaihao",row4[0].get("tooldaihao"));  //记录选中的id
									Ext.util.Cookies.set("capacity",row4[0].get("capacity"))
									
									upright_param();
						}else{
									Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择合适的型号！</span>')	
								}
									
		}};
		
		var atc_column41 = {columnWidth: .4,layout: 'form',items: [jggy]};
		var atc_column42 = {columnWidth: .4,layout: 'form',items: [daoBing]};
 		var atc_column43 = {columnWidth: .4,layout: 'form',items: [jgEffic]};

		var atc_param = new Ext.FormPanel({
			title:'输入参数',
			height:185,
			labelAlign: 'right',
			labelWidth: 100,
			columnWidth: .9,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 400px',items:[atc_column41,atc_column42]},
					{layout: 'column',bodyStyle:'padding:10px 10px 10px 400px',items:[atc_column43]}],
			buttonAlign : 'center',
		 	buttons : [atc_output,atc_next]
	});
	
	var atcPanel = new Ext.Panel({
						//layout:'column',
						columnWidth: .1,
						frame:true,
						bodyStyle:'padding:0px 5px 0px 10px',
						html:'<img src="resource/picture/atc.jpg"></img>'
				 })
	
//-------------结果表格-----------------------------------------------------
	var atc_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var atc_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[atc_smodel,
			//排序开启，正序和倒序
			{header:'型号',dataIndex:'tooldaihao',align:'center',width:115,sortable: true},
			{header:'刀库类型',dataIndex:'xingshi',width:100,align:'center'},
			{header:'驱动方式',dataIndex:'qudong',width:100,align:'center'},
			{header:'刀柄型式',dataIndex:'daobing',width:100,align:'center'},
			{header:'刀库容量(把）',dataIndex:'capacity',width:100,align:'center',sortable: true},
			{header:'最大刀具直径（mm)',dataIndex:'maxd',width:120,align:'center',sortable: true},
			{header:'最大刀具直径（相邻无刀具）',dataIndex:'maxdNull',width:160,align:'center'},
			{header:'刀具长度(mm)',dataIndex:'length',width:105,align:'center',sortable: true},
			{header:'刀具重量(kg)',dataIndex:'tWeight',width:100,align:'center',sortable: true},
			{header:'换刀时间(s)',dataIndex:'tooltime',width:100,align:'center',sortable: true},
			{header:'钻孔直径',dataIndex:'zuanhole',width:100,align:'center',sortable: true},
			{header:'攻丝螺纹孔',dataIndex:'gongsi',width:100,align:'center',sortable: true},
			{header:'铣削面积(cm^3/min)',dataIndex:'xixue',width:115,align:'center',sortable: true}]});
	
	var msgTip42;          // 一定要定义在使用前，且定义为全局变量
	var pageN42 = true;
	
    var atc_store=new Ext.data.JsonStore({  
    	id:"atc_store",
		url:"reasonAtc.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN42){
                   msgTip42 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["toolid","tooldaihao","xingshi","qudong","daobing","capacity","maxd","maxdNull",
			"length","tWeight","tooltime","zuanhole","gongsi","xixue","course"]});
	
	var atc_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win4 = new Ext.Window({
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
												win4.close();
											}}]
							});
							
							if(!win4.isVisible()){
						  			 win4.show();//修改窗口显示
						  			 win4.center();
						  		}
								
				}}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-185);
	var atc_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:atc_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:atc_columns,
		sm: atc_smodel,
		tbar: atc_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:atc_store,
					pageSize:10, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
	
function reasonAtc(){
			var jggy = Ext.getCmp("jggy").getValue();
			var jgEffic =Ext.getCmp("jgEffic").getValue();
			var toolType = Ext.util.Cookies.get("zhuiKong");
			var wpMaxd = Ext.util.Cookies.get("wpMaxd");
			//alert(gjMaxd + "-" + gjHoled);
		if(atc_param.getForm().isValid()!=true){
 				Ext.Msg.alert("提示","输入的数据不符合要求，请重新输入！");  return;}
 		
 		atc_store.load({params:{wpMaxd:wpMaxd,jggy:jggy, jgEffic:jgEffic,toolType:toolType,start:0,limit:10},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配合适的结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip42.hide();  
										  	pageN42 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}})	
};

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

if(Ext.getCmp("atc_param") != undefined){
		case_tab.remove(Ext.getCmp("atc_param"));}
		var tabPage = case_tab.add({//动态添加tab页
		title:"刀库",
		iconCls:'aboutUs',
		id:"atc_param",
		closable : true,//允许关闭
		items:[{layout: 'column',items:[atc_param,atcPanel]},atc_grid]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}