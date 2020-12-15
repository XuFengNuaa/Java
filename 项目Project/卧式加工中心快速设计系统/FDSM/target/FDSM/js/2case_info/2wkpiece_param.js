function wkpiece_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";

		
		var gjmax_d = {xtype:'numberfield',fieldLabel:"零件最大直径 ",id:"gjmax_d",value:Ext.util.Cookies.get("wh_gjmax_d")*1,name:'gjmax_d', unitText:"mm",width:130,allowBlank: false};
		var gjmax_h = {xtype:'numberfield',fieldLabel:"零件最大高度  ",id:"gjmax_h",name:'gjmax_h',value:Ext.util.Cookies.get("wh_gjmax_h")*1, width:130,allowBlank: false,unitText:"mm"};
		var gj_kg = {xtype:'numberfield',fieldLabel:"零件重量",id:"gj_kg",name:'gj_kg',allowBlank: false,unitText:"Kg", width:130};
		var gj_hole_d = new Ext.form.ComboBox({
			    typeAhead: true,
			    allowBlank:false,
			    triggerAction: 'all',
			    id:"gj_hole_d",
			    value:"zs",
			    fieldLabel:"零件孔间距  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "gj_hole_d",
			        fields: [ 'gjId','gjText'],
			        data: [["zs", '整数倍间距'], ["bzs", '半整数倍间距']]
			    }),
			    valueField: 'gjId',
			    displayField: 'gjText',
			    emptyText:'---请选择---'});
		var wp_output = {xtype:"button",text:"输出",id:"wp_output",handler:reasonWp};
		var wp_next = {xtype:"button",text:"下一步",id:"wp_next",
				handler:function(){
							var row2 =wk_p_grid.getSelectionModel().getSelections();
							//	alert(row2[0].get("workid"));
							//	alert(row2[0].get("zhuantaiMaxd"));
							if(row2.length !=0){
									var gjH = row2[0].get("gongjianH");
									var ztMax = row2[0].get("zhuantaiMaxd");
									var gongjMaxd = row2[0].get("gongjianMaxd");  //工件最大直径
									var wp_h = row2[0].get("overHeight");
									var y_routes;
									var x_routes;
									var z_routes;
								
						//-----------   // 计算Y行程，并加入cookie	---------	
							if(gjH>=700){   
									if(ztMax >=500 && ztMax<=630){
												y_routes =gjH -(50+ ztMax*1 -500);
									}else if(ztMax >630 && ztMax <=800){
												y_routes = gjH -150;
									}else if(ztMax >800 && ztMax <=1000){
												y_routes = gjH -150-(ztMax *1 - 800)/4 }else{
												y_routes = gjH - 1.2*ztMax +1000;
											}
										}else{
												y_routes = 850;
												};
						// 计算X.Z行程
								if(gongjMaxd <1000){
										x_routes = gongjMaxd +120;
										z_routes = x_routes-100;
								}else if(gongjMaxd == 1000){
										x_routes = 1000;
										z_routes = 950;
								}else {
										//x_routes = 1.2*gongjMaxd -200;
										x_routes = gongjMaxd +200;
										z_routes = 0.5*gongjMaxd +450;
								};			
							
							//	alert(gongjMaxd+"---"+gjH+"---"+ztMax);
							//	alert( x_routes +"---"+ y_routes +"---"+ z_routes);
								
								Ext.util.Cookies.set("y_routes",y_routes); //Y行程
								Ext.util.Cookies.set("x_routes",x_routes); //X行程
								Ext.util.Cookies.set("z_routes",z_routes); //Z行程
								
								Ext.util.Cookies.set("wpMaxd",ztMax);  
								Ext.util.Cookies.set("wp_h",wp_h);
						//--- 结果展示---
								Ext.util.Cookies.set("workid",row2[0].get("workid"));  // 选中的记录id
								Ext.util.Cookies.set("type",row2[0].get("type"));  // 选中的记录id
								Ext.util.Cookies.set("gongjMaxd",gongjMaxd);  // gjD
								Ext.util.Cookies.set("gjH",gjH);//gjH
								
								spindle_param();
							}else{
								Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择合适的型号！</span>')	
										}
																	
														
		}};
		
		var wh_column21 = {columnWidth: .4,layout: 'form',items: [gjmax_d]};
 		var wh_column22 = {columnWidth: .4,layout: 'form',items: [gjmax_h]};
 		var wh_column23 = {columnWidth: .4,layout: 'form',items: [gj_kg]};
 		var wh_column24 = {columnWidth: .4,layout: 'form',items: [gj_hole_d]};
		
		var wkpiece_param = new Ext.form.FormPanel({
			title:'输入参数',
			height:185,
			labelAlign: 'right',
			labelWidth: 100,
			columnWidth: .6,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 150px',items:[wh_column21,wh_column23]},
					{layout: 'column',bodyStyle:'padding:10px 10px 10px 150px',items:[wh_column22,wh_column24]}],
			buttonAlign : 'center',
		 	buttons : [wp_output,wp_next]
	});
//---------------------weight-----------------
	
		var tool_d = {xtype:'numberfield',id:"tool_d",fieldLabel:"零件直径  ", name:'tool_d',width:100,value:0.35,allowBlank:false};
		var tool_kg = {xtype:'numberfield',id:"tool_kg",fieldLabel:"零件重量  ", name:'tool_kg',width:100,value:0.3,allowBlank:false};
		var tool_h = {xtype:'numberfield',id:"tool_h",fieldLabel:"零件高度  ", name:'tool_h',width:100,value:0.35,allowBlank:false};
	
		var wp_quanzhong= new Ext.form.FormPanel({
				title:'属性权重',
				layout:'form',
				frame:'true',
				columnWidth: .25,
				labelAlign: 'right',
				height:235,
				items:[{layout:'form',bodyStyle:'padding:10px 10px 0px 15px',columnWidth:1,items: tool_d},
				{layout:'form',bodyStyle:'padding:15px 10px 0px 15px',columnWidth:1,items: tool_kg},
				{layout:'form',bodyStyle:'padding:15px 10px 0px 15px',columnWidth:1,items: tool_h}
					]
				});
//-------------结果表格-----------------------------------------------------
	var wk_p_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var wk_p_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[wk_p_smodel,
			//排序开启，正序和倒序
			{header:'型号',dataIndex:'type',align:'center',width:115,sortable: true},
			{header:'转台最大直径(D)',dataIndex:'zhuantaiMaxd',width:120,align:'center',sortable: true},
			{header:'计算结果',dataIndex:'showSimi',align:'center',width:115,sortable: true},
	//		{header:'转台高度(mm)',dataIndex:'zhuantaiH',width:115,align:'center',sortable: true},
			{header:'工件直径(mm)',dataIndex:'gongjianMaxd',width:115,align:'center',sortable: true},
			{header:'工件高度(mm)',dataIndex:'gongjianH',width:115,align:'center',sortable: true},
			{header:'转台分度',dataIndex:'fendu',width:100,align:'center'},
			{header:'承受载荷(Kg)',dataIndex:'zaihe',width:115,align:'center',sortable: true},
			{header:'切削力矩(kg/cm^2)',dataIndex:'cuttingF',width:117,align:'center',sortable: true},
			{header:'减速比',dataIndex:'reRatio',width:90,align:'center',sortable: true},
			{header:'最高转速(r/min)',dataIndex:'maxSpeed',width:115,align:'center',sortable: true},
			{header:'机床型号',dataIndex:'course',width:115,align:'center'},
			{header:'平置高度(OH)',dataIndex:'overHeight',width:115,align:'center',sortable: true}]});
	
	var msgTip22;          // 一定要定义在使用前，且定义为全局变量
	var pageN22 = true;
	
    var wk_p_store=new Ext.data.JsonStore({  
    	id:"wk_p_store",
		url:"reasonWp.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN22){
                   msgTip22 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["workid","type","showSimi","zhuantaiMaxd","zhuantaiH","gongjianMaxd","gongjianH","fendu","zaihe","cuttingF","reRatio",
		"maxSpeed","overHeight","course"]});
	
	var wk_p_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win = new Ext.Window({
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
												win.close();
											}}]
							});
							
							if(!win.isVisible()){
						  			 win.show();//修改窗口显示
						  			 win.center();
						  		}
								
				}}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-185);
	var wk_p_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:wk_p_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:wk_p_columns,
		sm: wk_p_smodel,
		tbar: wk_p_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:wk_p_store,
					pageSize:10, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
	
	var wpPanel = new Ext.Panel({
						//layout:'column',
						columnWidth: .15,
						frame:true,
						html:'<img src="resource/picture/workpiece.png"></img>'
				 })
	
function reasonWp(){
		var gjMaxd = Ext.getCmp("gjmax_d").getValue();
		var gjHoled =Ext.getCmp("gj_hole_d").getValue();
		var gjMaxh = Ext.getCmp("gjmax_h").getValue();
		var gjKg = Ext.getCmp("gj_kg").getValue();
//	alert(gjMaxd + "-" + gjHoled+"-"+gjMaxh+"-"+gjKg);
		//weight---------------------
		var qz_d = Ext.getCmp("tool_d").getValue()*10;
		var qz_k = Ext.getCmp("tool_kg").getValue()*10;
		var qz_h = Ext.getCmp("tool_h").getValue()*10;
		var total = qz_d + qz_k +qz_h ;
		if(total !=10){
				qz_d = qz_d/total;
				qz_k = qz_k/total;
				qz_h = qz_h/total;
		} else {
				qz_d = qz_d/10;
				qz_k = qz_k/10;
				qz_h = qz_h/10;
		}
		if(wkpiece_param.getForm().isValid()!=true){
 				Ext.Msg.alert("提示","输入的数据不符合要求，请重新输入！");  return;}
 		
 		wk_p_store.load({params:{gjMaxd:gjMaxd, gjHoled:gjHoled,gjMaxh:gjMaxh,gjKg:gjKg,qz_d:qz_d,qz_k:qz_k,qz_h:qz_h,start:0,limit:10},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配合适的结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip22.hide();  
										  	pageN22 = false; }
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

if(Ext.getCmp("wkpiece_param") != undefined){	
		case_tab.remove(Ext.getCmp("wkpiece_param"));}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"回转工作台",
		iconCls:'aboutUs',
		id:"wkpiece_param",
		closable : true,//允许关闭
		items:[{layout: 'column',items:[wkpiece_param,wp_quanzhong,wpPanel]},wk_p_grid]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}