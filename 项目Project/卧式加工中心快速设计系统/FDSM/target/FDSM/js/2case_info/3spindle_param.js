function spindle_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var material = new Ext.form.ComboBox({
			    typeAhead: true,
			    allowBlank:false,
			    triggerAction: 'all',
			    value:"HT200",
			    id:"material",
			    fieldLabel:"加工零件材料  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.JsonStore({
			        id: "material_combo",
			        url:'resource/xml/data.json',
					autoLoad: true,
					root: 'spindle',
			        fields: [ 'maId','maText']}),		        
			    valueField: 'maId',
			    displayField: 'maText'   
			    });
		
		var jgType = new Ext.form.ComboBox({
			    typeAhead: true,
			    allowBlank:false,
			    triggerAction: 'all',
			    id:"jgType",
			    value:1,
			    fieldLabel:"加工节拍  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "jgType_combo",
			        fields: [ 'jgId','jgText'],
			        data: [["1", '普通型'], ["2", '重切削型'],["HE", '高效率型']]
			    }),
			    valueField: 'jgId',
			    displayField: 'jgText'
			   // emptyText:'---请选择---'
			    });
			    
		var sd_output={xtype:"button",text:"输出",handler:reasonSd};
		var sd_next={xtype:"button",text:"下一步",handler:function(){
							var row3 =spindle_grid.getSelectionModel().getSelections();
							
							if(row3.length !=0){		
								//alert(Ext.util.Cookies.get("record"));
								var s =row3[0].get("zhuikong");
								var sd_x = row3[0].get("xLength");
								var sd_y = row3[0].get("yLength");
								Ext.util.Cookies.set("zhuiKong",s);
								Ext.util.Cookies.set("sd_x",sd_x);
								Ext.util.Cookies.set("sd_y",sd_y);
								
							//	alert(s + sd_x + sd_y );
							//-----------结果展示
								Ext.util.Cookies.set("sindleId",row3[0].get("sindleId"));  //记录选中的id
								Ext.util.Cookies.set("sdType",row3[0].get("sdType"));  //记录选中的id
								Ext.util.Cookies.set("kw",row3[0].get("power"));
								Ext.util.Cookies.set("rpm",row3[0].get("maxSpeed"));
															
									atc_param();
						}else{
									Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择合适的型号！</span>')	
								}} };
		
		var sd_column31 = {columnWidth: .4,layout: 'form',items: [material]};
 		var sd_column32 = {columnWidth: .4,layout: 'form',items: [jgType]};
		
		var spindle_param = new Ext.FormPanel({
			title:'输入参数',
			height:185,
			labelAlign: 'right',
			labelWidth: 100,
			columnWidth: .82,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 400px',items:[sd_column31]},
					{layout: 'column',bodyStyle:'padding:10px 10px 10px 400px',items:[sd_column32]}],
			buttonAlign : 'center',
		 	buttons : [sd_output,sd_next]
	});
	
//-------------结果表格-----------------------------------------------------
	var spindle_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var spindle_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[spindle_smodel,
			//排序开启，正序和倒序
			{header:'型号',dataIndex:'sdType',align:'center',width:115,sortable: true},
			{header:'主轴类型',dataIndex:'kinds',width:120,align:'center'},
			{header:'电机功率(kw)',dataIndex:'power',width:115,align:'center',sortable: true},
			{header:'最高转速(rpm)',dataIndex:'maxSpeed',width:115,align:'center',sortable: true},
			{header:'主轴扭矩（N.m）',dataIndex:'torsion',width:100,align:'center',sortable: true},
			{header:'锥孔形式',dataIndex:'zhuikong',width:110,align:'center'},
			{header:'主轴箱X尺寸(Sd_X)',dataIndex:'xLength',width:117,align:'center'},
			{header:'主轴箱Y尺寸(Sd_Y)',dataIndex:'yLength',width:117,align:'center'},
			{header:'机床型号',dataIndex:'course',width:108,align:'center'},
			{header:'端面至工作台距离(mm)',dataIndex:'duanmianZhuantai',width:128,align:'center'},
			{header:'中心至工作台距离(mm)',dataIndex:'zhongxin',width:128,align:'center'}]});
	
	var msgTip32;          // 一定要定义在使用前，且定义为全局变量
	var pageN32 = true;
	
    var spindle_store=new Ext.data.JsonStore({  
    	id:"spindle_store",
		url:"reasonSd.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN32){
                   msgTip32 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["sindleId","sdType","kinds","course","power","maxSpeed","torsion","zhuikong","xLength",
			"yLength","duanmianZhuantai","zhongxin"]});
	
	var spindle_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win3 = new Ext.Window({
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
												win3.close();
											}}]
							});
							
							if(!win3.isVisible()){
						  			 win3.show();//修改窗口显示
						  			 win3.center();
						  		}
								
				}}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-185);
	var spindle_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:spindle_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:spindle_columns,
		sm: spindle_smodel,
		tbar: spindle_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:spindle_store,
					pageSize:10, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
	
	var sdPanel = new Ext.Panel({
						//layout:'column',
						columnWidth: .18,
						frame:true,
						html:'<img src="resource/picture/spindle.png"></img>'
				 })
	
function reasonSd(){
			var material = Ext.getCmp("material").getValue();
			var jgType =Ext.getCmp("jgType").getValue();
			var wpMaxd = Ext.util.Cookies.get("wpMaxd");
			//alert(gjMaxd + "-" + gjHoled);
			if(material=="HT200"){
					material = "1"
			};
		if(spindle_param.getForm().isValid()!=true){
 				Ext.Msg.alert("提示","输入的数据不符合要求，请重新输入！");  return;}
 		
 		spindle_store.load({params:{wpMaxd:wpMaxd, material:material,jgType:jgType,start:0,limit:10},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配合适的结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip32.hide();  
										  	pageN32 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}})	
					Ext.util.Cookies.set("jgType",jgType);
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

if(Ext.getCmp("spindle_param") != undefined){	
		case_tab.remove(Ext.getCmp("spindle_param"));}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"主轴",
		iconCls:'aboutUs',
		id:"spindle_param",
		closable : true,//允许关闭
		items:[{layout: 'column',items:[spindle_param,sdPanel]},spindle_grid]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}