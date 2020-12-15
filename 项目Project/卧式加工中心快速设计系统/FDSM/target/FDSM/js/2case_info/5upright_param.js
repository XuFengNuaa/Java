function upright_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
		
		var yt = Ext.util.Cookies.get("y_routes");
		var s_w = Ext.util.Cookies.get("sd_x");
		var s_h = Ext.util.Cookies.get("sd_y");
		var Y_route = {xtype:'numberfield',id:"Y_route",fieldLabel:"Y轴行程  ",value:yt, name:'Y_route',unitText:"mm",width:100,readOnly:true};
		var sd_width = {xtype:'numberfield',id:"sd_width",fieldLabel:"主轴箱宽度  ",value:s_w, name:'sd_width',unitText:"mm",width:100,readOnly:true};
		var sd_height = {xtype:'numberfield',id:"sd_height",fieldLabel:"主轴箱高度  ",value:s_h, name:'sd_height',unitText:"mm",width:100,readOnly:true};
		var sidingthick = {xtype:'numberfield',id:"sidingthick",fieldLabel:"立柱壁厚  ",name:'sidingthick',unitText:"mm",width:100,allowBlank:false};
		var hole = {xtype:'numberfield',id:"hole",fieldLabel:"减重孔径  ",name:'hole',unitText:"mm",width:100,allowBlank:false};
		var ribthick = {xtype:'numberfield',id:"ribthick",fieldLabel:"板筋厚度  ",unitText:"mm",name:'ribthinck',width:100,allowBlank:false};
			
		var structure = new Ext.form.ComboBox({
			    typeAhead: true,
			    allowBlank:false,
			    triggerAction: 'all',
			    id:"structure",
			    value:1,
			    fieldLabel:"结构形式  ",
			    width:130,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "structure_combo",
			        fields: [ 'upId','upText'],
			        data: [["1", '双柱对称框架式']]
			    }),
			    valueField: 'upId',
			    displayField: 'upText'
			   // emptyText:'---请选择---'
			    });
			    
		var upright_output={xtype:"button",text:"输出",handler:reasonUp};
		var upright_next={xtype:"button",text:"下一步",handler:function(){
							var row5 =upright_grid.getSelectionModel().getSelections();
								
							if(row5.length !=0){
									
								Ext.util.Cookies.set("upWidth",row5[0].get("kuangdu"))
								Ext.util.Cookies.set("upXiadi",row5[0].get("xiadi"))
								Ext.util.Cookies.set("uprightId",row5[0].get("uprightId"));  //记录选中的id
							//	alert(Ext.util.Cookies.get("zhuiKong"));
								Ext.util.Cookies.set("upDaihao",row5[0].get("upDaihao"));
							//	alert(row3[0].get("workid"));
							//	Ext.util.Cookies.set("record2",row3[0].get("sindleId"));
									screw_param();
						}else{
									Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择合适的型号！</span>')	
								}
									
		}};
		
		var upright_column51 = {columnWidth: .33,layout: 'form',items: [Y_route]};
		var upright_column52 = {columnWidth: .33,layout: 'form',items: [sd_width]};
 		var upright_column53 = {columnWidth: .33,layout: 'form',items: [sd_height]};
 		var upright_column54 = {columnWidth: .33,layout: 'form',items: [sidingthick]};
 		var upright_column55 = {columnWidth: .33,layout: 'form',items: [hole]};
 		var upright_column56 = {columnWidth: .33,layout: 'form',items: [ribthick]};
 		var upright_column57 = {columnWidth: .33,layout: 'form',items: [structure]};
		
		var upright_param = new Ext.FormPanel({
			title:'输入参数',
			height:250,
			columnWidth: .5,
			labelAlign: 'right',
			labelWidth: 80,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:20px 0px 5px 0px',items:[upright_column51,upright_column52,upright_column53]},
					{layout: 'column',bodyStyle:'padding:20px 0px 5px 0px',items:[upright_column54,upright_column55,upright_column56]},
					{layout: 'column',bodyStyle:'padding:20px 0px 5px 0px',items:[upright_column57]}],
			buttonAlign : 'center',
		 	buttons : [upright_output,upright_next]
	});
	var upPanel = new Ext.Panel({
						//layout:'column',
						columnWidth: .2,
						frame:true,
						bodyStyle:'padding:0px 5px 0px 10px',
						html:'<img src="resource/picture/upright.png"></img>'
				 })
//-------------结果表格-----------------------------------------------------
	var upright_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var upright_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[upright_smodel,
			//排序开启，正序和倒序
			{header:'型号',dataIndex:'upDaihao',align:'center',width:118,sortable: true},
			{header:'结构',dataIndex:'structure',width:128,align:'center'},
			{header:'材料',dataIndex:'material',width:100,align:'center'},
			{header:'计算结果',dataIndex:'showSimi',width:100,align:'center'},
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
			{header:'板筋厚度(mm)',dataIndex:'ribthinck',width:80,align:'center'}]});
	
	var msgTip52;          // 一定要定义在使用前，且定义为全局变量
	var pageN52 = true;
	
    var upright_store=new Ext.data.JsonStore({  
    	id:"upright_store",
		url:"reasonUp.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN52){
                   msgTip52 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["uprightId","upDaihao","structure","material","showSimi","sidingthick","shangdi","xiadi","kuangdu",
					"height","railDistance","reducehole","cehole","dinghole","rib","hengxiang",
					"shuxiang","ribthinck","course"]});
	
	var upright_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win5 = new Ext.Window({
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
												win5.close();
											}}]
							});
							
							if(!win5.isVisible()){
						  			 win5.show();//修改窗口显示
						  			 win5.center();
						  		}
								
				}}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-250);
	var upright_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:upright_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:upright_columns,
		sm: upright_smodel,
		tbar: upright_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:upright_store,
					pageSize:10, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//-----------------------------------权重页面----------------------------
		var up_H1 = {xtype:'numberfield',id:"up_H1",fieldLabel:"立柱高度  ", name:'height',width:80,allowBlank:false};
		var up_K2 = {xtype:'numberfield',id:"up_K2",fieldLabel:"立柱宽度  ", name:'kuang',width:80,allowBlank:false};
		var up_yt3= {xtype:'numberfield',id:"up_yt3",fieldLabel:"Y轴行程  ", name:'yroute',width:80,allowBlank:false};
		var up_sdx4 = {xtype:'numberfield',id:"up_sdx4",fieldLabel:"主轴宽度  ",name:'sdwidth',width:80,allowBlank:false};
		var up_sdy5 = {xtype:'numberfield',id:"up_sdy5",fieldLabel:"主轴高度  ",name:'sdheight',width:80,allowBlank:false};
		var up_sideThick6 = {xtype:'numberfield',id:"up_sideThick6",fieldLabel:"立柱壁厚  ",name:'sidethick',width:80,allowBlank:false};
		var up_hole7 = {xtype:'numberfield',id:"up_hole7",fieldLabel:"减重孔  ",name:'hole',width:80,allowBlank:false};
		var up_ribThick8 = {xtype:'numberfield',id:"up_ribThick8",fieldLabel:"板筋厚度  ",name:'ribthick',width:80,allowBlank:false};
		var up_hN9 = {xtype:'numberfield',id:"up_hN9",fieldLabel:"横板筋数量  ",name:'hnumber',width:80,allowBlank:false};
		var up_sN10 = {xtype:'numberfield',id:"up_sN10",fieldLabel:"竖板筋数量  ",name:'snumber',width:80,allowBlank:false};
		
		var quanzhong= new Ext.form.FormPanel({
				title:'属性权重',
				layout:'form',
				frame:'true',
				columnWidth: .3,
				labelAlign: 'right',
				height:300,
				buttonAlign : 'center',
				reader: new Ext.data.JsonReader({root:'extend'},
						[{name:'wid',mapping:'wid'},
						 {name:'height',mapping:'height'},				 
						 {name:'kuang',mapping:'kuang'},
						 {name:'yroute',mapping:'yroute'},
						 {name:'sdwidth',mapping:'sdwidth'},
						 {name:'sdheight',mapping:'sdheight'},
						 {name:'sidethick',mapping:'sidethick'},
						 {name:'hole',mapping:'hole'},				 
						 {name:'ribthick',mapping:'ribthick'},
						 {name:'hnumber',mapping:'hnumber'},
						 {name:'snumber',mapping:'snumber'}]),
				items:[
					{layout: 'column',bodyStyle:'padding:10px 5px 0px 5px',items:[{xtype:"numberfield",name:"wid",hidden:true},{layout:'form',columnWidth:0.5,items: up_H1},{layout:'form',columnWidth:0.5,items: up_K2}]},
					{layout: 'column',bodyStyle:'padding:10px 5px 0px 5px',items:[{layout:'form',columnWidth:0.5,items: up_yt3},{layout:'form',columnWidth:0.5,items: up_sdx4}]},
					{layout: 'column',bodyStyle:'padding:10px 5px 0px 5px',items:[{layout:'form',columnWidth:0.5,items: up_sdy5},{layout:'form',columnWidth:0.5,items: up_sideThick6}]},
					{layout: 'column',bodyStyle:'padding:10px 5px 0px 5px',items:[{layout:'form',columnWidth:0.5,items: up_hole7},{layout:'form',columnWidth:0.5,items: up_ribThick8}]},
					{layout: 'column',bodyStyle:'padding:10px 5px 0px 5px',items:[{layout:'form',columnWidth:0.5,items: up_hN9},{layout:'form',columnWidth:0.5,items: up_sN10}]}
				]
			});
			
			quanzhong.getForm().load({
								     url: 'loadUpwgt.do',
								     waitMsg:"正在加载，请稍候...",
								   	 params: {
								        	wid : 1
								   	 },
									 success:function(form,action){
									 	
										 	},
								     failure: function(form, action) {
								     	
								        	Ext.Msg.alert("Load failed", "数据加载失败，请稍后再试！");
								    }
					});
			
//----------------------------------------------------------------
function reasonUp(){
			var sideThick = Ext.getCmp("sidingthick").getValue();
			var hole =Ext.getCmp("hole").getValue();
			var ribThick =Ext.getCmp("ribthick").getValue();
	//		alert(sideThick + "-" + hole + "-" + ribThick);
	//		var wid = Ext.getCmp("weightId").getValue();  //获得权重id  
			
			var formValue = quanzhong.getForm().getValues();  //获取form中的所有值
		//	alert(formValue['height']);
			var h = formValue['height']*100;  var k = formValue['kuang']*100;
			var yr = formValue['yroute']*100;  var w = formValue['sdwidth']*100;
			var dh = formValue['sdheight']*100;  var t = formValue['sidethick']*100;
			var ho = formValue['hole']*100;  var rb = formValue['ribthick']*100;
			var hn = formValue['hnumber']*100;  var sn = formValue['snumber']*100;
			
			var total5 = h + k +yr +w + dh + t + ho + rb + hn + sn ;
			
					h = h/total5; k = k/total5;
			        yr = yr/total5;w = w/total5;
					dh = dh/total5;t = t/total5;
					ho = ho/total5;rb = rb/total5;
					hn = hn/total5;sn = sn/total5;
			
				//	alert(h);
/*		if(upright_param.getForm().isValid()!=true){
 				Ext.Msg.alert("提示","输入的数据不符合要求，请重新输入！");  return;} */
 		
 		upright_store.load({params:{sideThick:sideThick, hole:hole, ribThick:ribThick,
 										yt:yt, s_w:s_w, s_h:s_h, wid:1,
 							h:h,k:k,yr:yr,w:w,dh:dh,t:t,ho:ho,rb:rb,hn:hn,sn:sn},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配合适的结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip52.hide();  
										  	pageN52 = false; }
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

if(Ext.getCmp("upright_param") != undefined){
		case_tab.remove(Ext.getCmp("upright_param"));}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"立柱",
		iconCls:'aboutUs',
		id:"upright_param",
		closable : true,//允许关闭
		items:[{layout: 'column',items:[upright_param,quanzhong,upPanel]},upright_grid]});
		
		case_tab.setActiveTab(tabPage);//设置当前tab页
}