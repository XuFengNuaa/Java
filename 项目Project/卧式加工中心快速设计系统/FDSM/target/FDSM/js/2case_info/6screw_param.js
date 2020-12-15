function screw_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";

		var yt2 = Ext.util.Cookies.get("y_routes");
		var x = Ext.util.Cookies.get("x_routes");
		var z = Ext.util.Cookies.get("z_routes");
		
		var X_route = {xtype:'numberfield',id:"X_route",fieldLabel:"X轴行程  ",value:x , name:'X_route',unitText:"mm",width:100,readOnly:true};
		var Y_route1 = {xtype:'numberfield',id:"Y_route1",fieldLabel:"Y轴行程  ",value:z , name:'Y_route1',unitText:"mm",width:100,readOnly:true};	
		var Z_route = {xtype:'numberfield',id:"Z_route",fieldLabel:"Z轴行程  ",value:yt2, name:'Z_route',unitText:"mm",width:100,readOnly:true};
		var jddj = {xtype:'combo',id:"jddj",fieldLabel:"精度等级 ",name:'jddj',width:100,triggerAction: 'all',
					    editable:false,
					    allowBlank:false,
					    mode: 'local',
					    store: new Ext.data.JsonStore({
					    	url:'resource/xml/data.json',
					    	autoLoad: true,
					    	root: 'root',
					    	fields: ['jdId', 'value']	    
					    }), 
					    valueField: 'jdId',
					    displayField: 'value',
					    emptyText:'---请选择---'};
		var eddzh = {xtype:'numberfield',id:"eddzh",fieldLabel:"动载荷  ",name:'eddzh',unitText:"N",width:100,allowBlank:false};
		var k_speed = {xtype:'numberfield',id:"k_speed",fieldLabel:"快移速度  ",value:Ext.util.Cookies.get("wh_axis_v")*1,unitText:"m/min",name:'ribthinck',width:120,allowBlank:false};

		
		var screw_output={xtype:"button",text:"输出",handler:reasonSw};
		var screw_next={xtype:"button",text:"下一步",handler:function(){
							var row6 =screw_grid.getSelectionModel().getSelections();
								
								if(row6.length !=0){
									
								//	alert(row6[0].get("gcd"));
									Ext.util.Cookies.set("swGcd",row6[0].get("gcd"));
									Ext.util.Cookies.set("swid",row6[0].get("swid"));  //记录选中的id
									
									Ext.util.Cookies.set("Swxinghao",row6[0].get("xinghao"));
								//	alert(jggy + "-" + jgEffic);
								//	alert(row3[0].get("workid"));
								//	Ext.util.Cookies.set("record2",row3[0].get("sindleId"));
									var kSpeedf =Ext.getCmp("k_speed").getValue();
									var dc = row6[0].get("daochen");
									var k
									if(kSpeedf <50){
											 k = 2 * dc*1 ;
									}else{
											 k=  3 * dc*1;
									}
									Ext.util.Cookies.set("k",k);  // 实际speed
									
									slidway_param();
						}else{
									Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择合适的型号！</span>')	
								}
									
		}};
		
		var screw_column61 = {columnWidth: .33,layout: 'form',items: [X_route]};
		var screw_column62 = {columnWidth: .33,layout: 'form',items: [Y_route1]};
 		var screw_column63 = {columnWidth: .33,layout: 'form',items: [Z_route]};
 		var screw_column64 = {columnWidth: .33,layout: 'form',items: [jddj]};
 		var screw_column65 = {columnWidth: .33,layout: 'form',items: [eddzh]};
 		var screw_column66 = {columnWidth: .33,layout: 'form',items: [k_speed]};
		
		var screw_param = new Ext.FormPanel({
			title:'滚珠丝杠参数',
			height:185,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			columnWidth: .6,
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:20px 10px 10px 50px',items:[screw_column61,screw_column62,screw_column63]},
					{layout: 'column',bodyStyle:'padding:10px 10px 10px 50px',items:[screw_column64,screw_column65,screw_column66]}],
			buttonAlign : 'center',
		 	buttons : [screw_output,screw_next]
	});
	
	var swPanel = new Ext.Panel({
						//layout:'column',
						columnWidth: .25,
						frame:true,
						
						html:'<img src="resource/picture/screw.png"></img>'
				 })
	
//----------weight-------------------------
		var sw_speed = {xtype:'numberfield',id:"sw_speed",fieldLabel:"快移速度  ", name:'sw_speed',width:80,value:0.3,allowBlank:false};
		var sw_dzh = {xtype:'numberfield',id:"sw_dzh",fieldLabel:"动载荷  ", name:'sw_dzh',width:80,value:0.25,allowBlank:false};
		var sw_jd = {xtype:'numberfield',id:"sw_jd",fieldLabel:"精度等级  ", name:'sw_jd',width:80,value:0.2,allowBlank:false};
		var sw_dc = {xtype:'numberfield',id:"sw_dc",fieldLabel:"导程  ", name:'sw_dc',width:80,value:0.25,allowBlank:false};
		
		var sw_quanzhong= new Ext.form.FormPanel({
				title:'属性权重',
				layout:'form',
				frame:'true',
				columnWidth: .15,
				labelAlign: 'right',
				height:235,
				items:[{layout:'form',bodyStyle:'padding:10px 0px 0px 0px',columnWidth:1,items: sw_speed},
				{layout:'form',bodyStyle:'padding:5px 0px 0px 0px',columnWidth:1,items: sw_dzh},
				{layout:'form',bodyStyle:'padding:5px 0px 0px 0px',columnWidth:1,items: sw_jd},
				{layout:'form',bodyStyle:'padding:5px 0px 0px 0px',columnWidth:1,items: sw_dc}
					]
				});
//-------------结果表格-----------------------------------------------------
	var screw_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var screw_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[screw_smodel,
			//排序开启，正序和倒序
		//	{header : "ID", dataIndex : "daoguiId",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'xinghao',align:'center',width:115,sortable: true},
		//	{header:'快移速度(m/min)',dataIndex:'speed',width:110,align:'center'},
			{header:'计算结果',dataIndex:'showSimi',align:'center',width:115,sortable: true},
			{header:'精度等级',dataIndex:'jdlevel',width:80,align:'center'},
			{header:'导程(mm)',dataIndex:'daochen',width:70,align:'center'},
			{header:'公称直径(mm)',dataIndex:'gcd',width:90,align:'center'},
			{header:'滚珠直径(mm)',dataIndex:'gunzhud',width:100,align:'center'},
			{header:'轴向刚度(N/m)',dataIndex:'zhougd',width:100,align:'center',sortable: true},
			{header:'额定动载荷(N)',dataIndex:'eddn',width:100,align:'center',sortable: true},
			{header:'额定静载荷(N)',dataIndex:'edjn',width:100,align:'center',sortable: true},
			{header:'电机功率(kw)',dataIndex:'power',width:100,align:'center',sortable: true},
			{header:'电机种类',dataIndex:'djtype',width:150,align:'center',sortable: true}]});
	
	var msgTip62;          // 一定要定义在使用前，且定义为全局变量
	var pageN62 = true;
	
    var screw_store=new Ext.data.JsonStore({  
    	id:"screw_store",
		url:"reasonSw.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN62){
                   msgTip62 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["swid","xinghao","showSimi","axis","speed","jdlevel","daochen","gcd","gunzhud",
				"zhougd","eddn","edjn","power","djtype","course"]});
	
	var screw_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win6 = new Ext.Window({
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
												win6.close();
											}}]
							});
							
							if(!win6.isVisible()){
						  			 win6.show();//修改窗口显示
						  			 win6.center();
						  		}
								
				}}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-187-23);
	var screw_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:screw_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:screw_columns,
		sm: screw_smodel,
		tbar: screw_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:screw_store,
					pageSize:10, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});

function reasonSw(){
			var jddj = Ext.getCmp("jddj").getValue();
			var eddzh =Ext.getCmp("eddzh").getValue();
			var kSpeed =Ext.getCmp("k_speed").getValue();
			var wpMaxd = Ext.util.Cookies.get("wpMaxd");
			var jgTyp2 = Ext.util.Cookies.get("jgType");
		//	alert(jddj + "-" + eddzh + "-" + kSpeed);
			//weight -----------------------
		
		var speed = Ext.getCmp("sw_speed").getValue()*10;
		var dzh = Ext.getCmp("sw_dzh").getValue()*10;
		var jd = Ext.getCmp("sw_jd").getValue()*10;
		var dc = Ext.getCmp("sw_dc").getValue()*10;
		
		var total6 = speed + dzh +jd +dc ;
		if(total6 !=10){
				speed = speed/total6;
				dzh = dzh/total6;
				jd = jd/total6;
				dc = dc/total6;
		} else {
				speed = speed/10;
				dzh = dzh/10;
				jd = jd/10;
				dc  = dc/10;
		}
		if(screw_param.getForm().isValid()!=true){
 				Ext.Msg.alert("提示","输入的数据不符合要求，请重新输入！");  return;}
 		
 		screw_store.load({params:{kSpeed:kSpeed, eddzh:eddzh, jddj:jddj,wpMaxd:wpMaxd,
 										 jgTyp2:jgTyp2,speed:speed,dzh:dzh,jd:jd,dc:dc},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配合适的结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip62.hide();  
										  	pageN62 = false; }
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

if(Ext.getCmp("screw_param") != undefined){
		case_tab.remove(Ext.getCmp("screw_param"));}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"滚珠丝杠",
		iconCls:'aboutUs',
		id:"screw_param",
		closable : true,//允许关闭
		items:[{layout: 'column',items:[screw_param,sw_quanzhong,swPanel]},screw_grid]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}