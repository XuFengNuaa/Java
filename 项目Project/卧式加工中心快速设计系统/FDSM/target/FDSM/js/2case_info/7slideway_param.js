function slidway_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
		var wp_x = Ext.util.Cookies.get("wpMaxd");  // 2wk
		var sdY = Ext.util.Cookies.get("sd_y");		//3sp
		var upWidth = Ext.util.Cookies.get("upWidth"); //5up
		var swGc =  Ext.util.Cookies.get("swGcd");
		
		
		var sway_kuan = {xtype:'numberfield',id:"sway_kuan",fieldLabel:"线轨宽度  ",value:swGc, name:'sway_kuan',unitText:"mm",width:110,readOnly:true};
	//	var sd_Y = {xtype:'numberfield',id:"sdY",fieldLabel:"主轴箱高度  ",value:sdY, name:'sdY',unitText:"mm",width:130,readOnly:true};
		var up_width = {xtype:'numberfield',id:"up_width",fieldLabel:"立柱宽度  ",value:upWidth, name:'up_width',unitText:"mm",width:110,readOnly:true};
		var way_jddj = {xtype:'combo',id:"way_jddj",fieldLabel:"精度等级 ",name:'way_jddj',width:112,triggerAction: 'all',
					    editable:false,
					    allowBlank:false,
					    mode: 'local',
					    store: new Ext.data.JsonStore({
					    	url:'resource/xml/data.json',
					    	autoLoad: true,
					    	root: 'sway',
					    	fields: ['jdId', 'value']	    
					    }), 
					    valueField: 'jdId',
					    displayField: 'value',
					    emptyText:'---请选择---'};
		var w_eddzh = {xtype:'numberfield',id:"w_eddzh",fieldLabel:"动载荷  ",name:'w_eddzh',unitText:"N",width:110,allowBlank:false};
		var Dwjd = {xtype:'textfield',id:"Dwjd",fieldLabel:"定位精度  ",value:Ext.util.Cookies.get("wh_dwjd")*1,name:'Dwjd',unitText:"mm",width:110,readOnly:true};
		var cdwjd = {xtype:'textfield',id:"cdwjd",fieldLabel:"重复定位精度  ",value:Ext.util.Cookies.get("wh_agdwjd")*1,name:'cdwjd',unitText:"mm",width:110,readOnly:true};
		

		
		var slidway_output={xtype:"button",text:"输出",handler:reasonSdWay};
		var slidway_next={xtype:"button",text:"下一步",handler:function(){
							var row7 =slidway_grid.getSelectionModel().getSelections();
								
							if(row7.length !=0){
					
								Ext.util.Cookies.set("xL",row7[0].get("xL"));
								Ext.util.Cookies.set("xD",Ext.util.Cookies.get("upXiadi")*1 -170);
								Ext.util.Cookies.set("zL",row7[0].get("zL"));
								Ext.util.Cookies.set("zD",Ext.util.Cookies.get("wpMaxd")*1 +220);
								
								Ext.util.Cookies.set("daoguiId1",row7[0].get("daoguiId"));  //记录选中的id
								
								Ext.util.Cookies.set("dwjd1",row7[0].get("dwjd"));
								Ext.util.Cookies.set("agdwjd1",row7[0].get("agdwjd"));
						
								Ext.util.Cookies.set("xinghao",row7[0].get("xinghao"));
								
									workbed_param();
						}else{
									Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择合适的型号！</span>')	
								}
									
		}};
		
		var slidway_column71 = {columnWidth: .33,layout: 'form',items: [sway_kuan]};
		var slidway_column72 = {columnWidth: .33,layout: 'form',items: [up_width]};
 		var slidway_column73 = {columnWidth: .33,layout: 'form',items: [way_jddj]};
 		var slidway_column74 = {columnWidth: .33,layout: 'form',items: [Dwjd]};
 		var slidway_column75 = {columnWidth: .33,layout: 'form',items: [cdwjd]};
 		var slidway_column76 = {columnWidth: .33,layout: 'form',items: [w_eddzh]};
 	
		
		var slidway_param = new Ext.FormPanel({
			title:'线性导轨参数',
			height:205,
			labelAlign: 'right',
			labelWidth: 100,
			columnWidth: .6,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:20px 10px 10px 50px',items:[slidway_column71,slidway_column72,slidway_column73]},
					{layout: 'column',bodyStyle:'padding:10px 10px 10px 50px',items:[slidway_column76,slidway_column74,slidway_column75]}],
			buttonAlign : 'center',
		 	buttons : [slidway_output,slidway_next]
	});
	
	var swayPanel = new Ext.Panel({
						//layout:'column',
						columnWidth: .25,
						frame:true,
						
						html:'<img src="resource/picture/slideway.png"></img>'
				 })
//----------weight-------------------------
		var Kuan = {xtype:'numberfield',id:"Kuan",fieldLabel:"线轨宽度  ", name:'Kuan',width:80,value:0.3,allowBlank:false};
		var w_jddj = {xtype:'numberfield',id:"w_jddj",fieldLabel:"精度等级  ", name:'w_jddj',width:80,value:0.25,allowBlank:false};
		var w_dwjd = {xtype:'numberfield',id:"w_dwjd",fieldLabel:"定位精度  ", name:'w_dwjd',width:80,value:0.25,allowBlank:false};
		var w_dzh = {xtype:'numberfield',id:"w_dzh",fieldLabel:"动载荷  ", name:'w_dzh',width:80,value:0.2,allowBlank:false};
		
		var way_quanzhong= new Ext.form.FormPanel({
				title:'属性权重',
				layout:'form',
				frame:'true',
				columnWidth: .15,
				labelAlign: 'right',
				height:255,
				items:[{layout:'form',bodyStyle:'padding:10px 0px 0px 0px',columnWidth:1,items: Kuan},
				{layout:'form',bodyStyle:'padding:5px 0px 0px 0px',columnWidth:1,items: w_jddj},
				{layout:'form',bodyStyle:'padding:5px 0px 0px 0px',columnWidth:1,items: w_dzh},
				{layout:'form',bodyStyle:'padding:5px 0px 0px 0px',columnWidth:1,items: w_dwjd}
					]
				});	
//-------------结果表格-----------------------------------------------------
	var slidway_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var slidway_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[slidway_smodel,
			//排序开启，正序和倒序
		//	{header : "ID", dataIndex : "daoguiId",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'xinghao',align:'center',width:115,sortable: true},
			{header:'计算结果',dataIndex:'showSimi',align:'center',width:115,sortable: true},
			{header:'精度等级(G)',dataIndex:'jdlevel',width:95,align:'center'},
			{header:'线轨宽度(D)',dataIndex:'width',width:95,align:'center'},
			{header:'线轨长度(L)',dataIndex:'length',width:110,align:'center'},
			{header:'aL(mm)',dataIndex:'aL',width:90,align:'center'},
			{header:'aR(mm)',dataIndex:'aR',width:90,align:'center'},
			{header:'滑块数量(W)',dataIndex:'hks',width:95,align:'center'},
			{header:'额定动载荷(N)',dataIndex:'eddn',width:110,align:'center'},
			{header:'额定静载荷(N)',dataIndex:'edjn',width:110,align:'center'},
			{header:'预压等级',dataIndex:'yuya',width:100,align:'center'},
			{header:'定位精度(mm)',dataIndex:'dwjd',width:100,align:'center',sortable: true},
			{header:'重复定位精度(mm)',dataIndex:'agdwjd',width:120,align:'center',sortable: true}
			]});
	
	var msgTip72;          // 一定要定义在使用前，且定义为全局变量
	var pageN72 = true;
	
    var slidway_store=new Ext.data.JsonStore({  
    	id:"slidway_store",
		url:"reasonSdWay.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN72){
                   msgTip72 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["daoguiId","xinghao","showSimi","jdlevel","width","length","aL","aR","distance","hks","eddn",
							"edjn","yuya","dwjd","agdwjd","course","xL","zL"]});
	
	var slidway_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win7 = new Ext.Window({
							//	modal:true,
							    width:535,
							    height:357,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/6slideway.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win7.close();
											}}]
							});
							
							if(!win7.isVisible()){
						  			 win7.show();//修改窗口显示
						  			 win7.center();
						  		}
								
				}}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-205);
	var slidway_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:slidway_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:slidway_columns,
		sm: slidway_smodel,
		tbar: slidway_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:slidway_store,
					pageSize:10, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});

function reasonSdWay(){
	
			var xt = Ext.util.Cookies.get("x_routes"); 
			var yt = Ext.util.Cookies.get("y_routes");
			var zt = Ext.util.Cookies.get("z_routes");
			var upWidth = Ext.util.Cookies.get("upWidth");
			var sdH = Ext.util.Cookies.get("sd_y");
			var swGcd =  Ext.util.Cookies.get("swGcd");  //轨宽
			var dwjd = Ext.getCmp("Dwjd").getValue();  //定位精度
			var wpMaxd = Ext.util.Cookies.get("wpMaxd"); 
			var w_edzh = Ext.getCmp("w_eddzh").getValue();  // 动载荷
			var jddj =  Ext.getCmp("way_jddj").getValue();
			//  权重
			var kuan2 = Ext.getCmp("Kuan").getValue()*10;
			var jd = Ext.getCmp("w_jddj").getValue()*10;
			var dw = Ext.getCmp("w_dwjd").getValue()*10;
			var way_dzh = Ext.getCmp("w_dzh").getValue()*10;
			
			var total7 = kuan2 + jd +dw +way_dzh ;
			if(total7 !=10){
					kuan2 = kuan2/total7;
					jd = jd/total7;
					dw = dw/total7;
					way_dzh = way_dzh/total7;
			} else {
					kuan2 = kuan2/10;
					jd = jd/10;
					dw = dw/10;
					way_dzh  = way_dzh/10;
			}
			
			
			
		if(slidway_param.getForm().isValid()!=true){
 				Ext.Msg.alert("提示","输入的数据不符合要求，请重新输入！");  return;}
 		
 		slidway_store.load({params:{xt:xt, yt:yt, zt:zt,upWidth:upWidth,sdH:sdH,swGcd:swGcd,dwjd:dwjd,
 								w_edzh:w_edzh, kuan:kuan2,jd:jd,dw:dw,way_dzh:way_dzh, wpMaxd:wpMaxd ,jddj:jddj},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配合适的结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip72.hide();  
										  	pageN72 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}})	
};

function chooseSw(){	
//	var x_L = row7[0].get("")
//	Ext.util.set("")

	//	alert(jggy + "-" + jgEffic);
	//	alert(row3[0].get("workid"));
	//	Ext.util.Cookies.set("record2",row3[0].get("sindleId"));
		
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

if(Ext.getCmp("slidway_param") != undefined){
		case_tab.remove(Ext.getCmp("slidway_param"));}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"线性导轨",
		iconCls:'aboutUs',
		id:"slidway_param",
		closable : true,//允许关闭 
		items:[{layout: 'column',items:[slidway_param,way_quanzhong,swayPanel]},slidway_grid]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}