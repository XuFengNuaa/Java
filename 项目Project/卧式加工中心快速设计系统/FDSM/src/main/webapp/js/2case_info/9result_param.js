function result_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";												
		
		var pre_gjmax_d = {xtype:'numberfield',id:"pre_gjmax_d",fieldLabel:"工件直径 ",name:'pre_gjmax_d',value:Ext.util.Cookies.get("wh_gjmax_d")*1, unitText:"mm",width:130,allowBlank: false};
		var pre_gjmax_h = {xtype:'numberfield',id:"pre_gjmax_h",fieldLabel:"工件高度  ",name:'pre_gjmax_h',value:Ext.util.Cookies.get("wh_gjmax_h")*1, width:130,allowBlank: false,unitText:"mm"};
		var pre_sd_kw = {xtype:'numberfield',id:"pre_sd_kw",fieldLabel:"主电机功率  ",name:'pre_sd_kw',value:Ext.util.Cookies.get("wh_sd_kw"),unitText:"Kw", width:130};
		var pre_sd_r= {xtype:'numberfield',id:"pre_sd_r",fieldLabel:"主轴转速  ",name:'pre_sd_r',value:Ext.util.Cookies.get("wh_sd_r"),KunitText:"rpm", width:130};
		var pre_tool_cap = {xtype:'numberfield',id:"pre_tool_cap",fieldLabel:"刀库容量  ",name:'pre_tool_cap',value:Ext.util.Cookies.get("wh_tool_cap")*1,allowBlank: false,unitText:"把", width:130};
		var pre_axis_v = {xtype:'numberfield',id:"pre_axis_v",fieldLabel:"三轴快移速度  ",name:'pre_axis_v',value:Ext.util.Cookies.get("wh_axis_v")*1,allowBlank: false,unitText:"m/min", width:150};
		var pre_dwjd = {xtype:'textfield',id:"pre_dwjd",fieldLabel:"定位精度  ",name:'pre_dwjd',value:Ext.util.Cookies.get("wh_dwjd")*1,allowBlank: false,readOnly:true,unitText:"mm", width:125};
		var pre_agdwjd = {xtype:'textfield',id:"pre_agdwjd",fieldLabel:"重复定位精度 ",name:'pre_agdwjd',value:Ext.util.Cookies.get("wh_agdwjd")*1,readOnly:true,unitText:"mm", width:130};
	
		var pre_column11 = {columnWidth: .25,layout: 'form',items: [pre_gjmax_d]};
 		var pre_column12 = {columnWidth: .25,layout: 'form',items: [pre_gjmax_h]};
 		var pre_column13 = {columnWidth: .25,layout: 'form',items: [pre_sd_kw]};
 		var pre_column14 = {columnWidth: .25,layout: 'form',items: [pre_sd_r]};
 		var pre_column15 = {columnWidth: .25,layout: 'form',items: [pre_tool_cap]};
 		var pre_column16 = {columnWidth: .25,layout: 'form',items: [pre_axis_v]};
 		var pre_column17 = {columnWidth: .25,layout: 'form',items: [pre_dwjd]};
 		var pre_column18 = {columnWidth: .25,layout: 'form',items: [pre_agdwjd]};
		
		var pre_yq= {xtype:'fieldset',title:'预期要求',labelWidth:100,height:160,
				items:[{layout: 'column',bodyStyle:'padding:0px 10px 10px 50px',items:[pre_column11,pre_column12,pre_column14]},
					   {layout: 'column',bodyStyle:'padding:0px 10px 10px 50px',items:[pre_column13,pre_column15,pre_column17]},
					   {layout: 'column',bodyStyle:'padding:0px 10px 10px 50px',items:[pre_column18,pre_column16]}

					   ]};
//--------------------------result-------------------------	
	
		var rt_gjmax_d = {xtype:'numberfield',id:"rt_gjmax_d",fieldLabel:"最大工件直径 ",name:'rt_gjmax_d',value:Ext.util.Cookies.get("gongjMaxd")*1, unitText:"mm",width:130,allowBlank: false};
		var rt_gjmax_h = {xtype:'numberfield',id:"rt_gjmax_h",fieldLabel:"最大工件高度  ",name:'rt_gjmax_h',value:Ext.util.Cookies.get("gjH")*1, width:130,allowBlank: false,unitText:"mm"};
		var rt_sd_kw = {xtype:'textfield',id:"rt_sd_kw",fieldLabel:"主电机功率  ",name:'rt_sd_kw',value:Ext.util.Cookies.get("kw"),allowBlank: false,unitText:"Kw", width:130};
		var rt_sd_r= {xtype:'numberfield',id:"rt_sd_r",fieldLabel:"主轴转速  ",name:'rt_sd_r',value:Ext.util.Cookies.get("rpm")*1,allowBlank: false,unitText:"rpm", width:130};
		var rt_tool_cap = {xtype:'numberfield',id:"rt_tool_cap",fieldLabel:"刀库容量  ",name:'rt_tool_cap',value:Ext.util.Cookies.get("capacity")*1,allowBlank: false,unitText:"把", width:130};
		var rt_axis_v = {xtype:'numberfield',id:"rt_axis_v",fieldLabel:"三轴快移速度  ",name:'rt_axis_v',value:Ext.util.Cookies.get("k")*1,allowBlank: false,unitText:"m/min", width:150};
		var rt_dwjd = {xtype:'textfield',id:"rt_dwjd",fieldLabel:"定位精度  ",name:'rt_dwjd',value:Ext.util.Cookies.get("dwjd1")*1,allowBlank: false,readOnly:true,unitText:"mm", width:125};
		var rt_agdwjd = {xtype:'textfield',id:"rt_agdwjd",fieldLabel:"重复定位精度 ",name:'rt_agdwjd',value:Ext.util.Cookies.get("agdwjd1")*1,allowBlank: false,readOnly:true,unitText:"mm", width:130};
	
		var rt_column11 = {columnWidth: .25,layout: 'form',items: [rt_gjmax_d]};
 		var rt_column12 = {columnWidth: .25,layout: 'form',items: [rt_gjmax_h]};
 		var rt_column13 = {columnWidth: .25,layout: 'form',items: [rt_sd_kw]};
 		var rt_column14 = {columnWidth: .25,layout: 'form',items: [rt_sd_r]};
 		var rt_column15 = {columnWidth: .25,layout: 'form',items: [rt_tool_cap]};
 		var rt_column16 = {columnWidth: .25,layout: 'form',items: [rt_axis_v]};
 		var rt_column17 = {columnWidth: .25,layout: 'form',items: [rt_dwjd]};
 		var rt_column18 = {columnWidth: .25,layout: 'form',items: [rt_agdwjd]};
		
		var rt_yq ={xtype:'fieldset',title:'推理结果',labelWidth:100,height:150,
				items:[{layout: 'column',bodyStyle:'padding:10px 10px 10px 50px',items:[rt_column11,rt_column12,rt_column14]},
					   {layout: 'column',bodyStyle:'padding:0px 10px 10px 50px',items:[rt_column13,rt_column15,rt_column18]},
					   {layout: 'column',bodyStyle:'padding:0px 10px 10px 50px',items:[rt_column17,rt_column16]}
					 ]};
											
		var result_compare = new Ext.form.FormPanel({
				title:'结果展示',
				frame:'true',
			//	height:360,
				items:[pre_yq, rt_yq]
			});
//---------------详细结果----------------------------------------------------
		 
		 var rt_mtType = {xtype:'textfield',id:"rt_mtType",fieldLabel:"机床型号",name:'mtType',emptyText:'e.g: HE63A', width:130,allowBlank: false};
		 var rt_mtWp={xtype:'textfield',id:"rt_mtWp",fieldLabel:"回转工作台",name:'mtWp',value:Ext.util.Cookies.get("type"),width:130,readOnly:true};
		 var rt_mtSpindle={xtype:'textfield',id:"rt_mtSpindle",fieldLabel:"主轴",value:Ext.util.Cookies.get("sdType"),name:'mtSpindle',width:130,readOnly:true};
		 var rt_mtAtc={xtype:'textfield',id:"rt_mtAtc",fieldLabel:"刀库",value:Ext.util.Cookies.get("tooldaihao"),name:'mtAtc', width:130,readOnly:true};
		 var rt_mtScrew={xtype:'textfield',id:"rt_mtScrew",fieldLabel:"滚珠丝杠",value:Ext.util.Cookies.get("Swxinghao"),name:'mtScrew', width:130,readOnly:true};
		 var rt_mtSlideway={xtype:'textfield',id:"rt_mtSlideway",fieldLabel:"线性导轨",value:Ext.util.Cookies.get("xinghao"),name:'mtSlideway',width:130,readOnly:true};
		 var rt_mtUpright={xtype:'textfield',id:"rt_mtUpright",fieldLabel:"立柱",value:Ext.util.Cookies.get("upDaihao"),name:'mtUpright', width:130,readOnly:true};
		 var rt_mtWorkbed={xtype:'textfield',id:"rt_mtWorkbed",fieldLabel:"床身",value:Ext.util.Cookies.get("beddaihao"),name:'mtWorkbed', width:130,readOnly:true};
			
	/*	 var rt_Wpbutton={xtype:"button",text:"预览",handler:view1Wp};
		 var rt_Sdbutton={xtype:"button",text:"预览"};
		 var rt_Atcbutton={xtype:"button",text:"预览"};
		 var rt_Swbutton={xtype:"button",text:"预览"};
		 var rt_SwWaybutton={xtype:"button",text:"预览"};
		 var rt_Upbutton={xtype:"button",text:"预览"};
		 var rt_Bdbutton={xtype:"button",text:"预览"}; */
		
		 var column11 = { columnWidth: 0.25,layout: 'form',items: [rt_mtWp]};        //有按钮，改成columnWidth:  1
		 var column12 = { columnWidth: 0.25,layout: 'form',items: [rt_mtSpindle]};
		 var column13 = { columnWidth: .25,layout: 'form',items: [rt_mtAtc]};
		 var column14 = { columnWidth: .25,layout: 'form',items: [rt_mtScrew]};
		 var column15 = { columnWidth: .25,layout: 'form',items: [rt_mtSlideway]};
		 var column16 = { columnWidth: .25,layout: 'form',items: [rt_mtUpright]};
		 var column17 = { columnWidth: .25,layout: 'form',items: [rt_mtWorkbed]};
		 
		 
	/*	 var column01 = { columnWidth: .25,layout: 'column',items: [column11,rt_Wpbutton]};    
		 var column02 = { columnWidth: .25,layout: 'column',items: [column12,rt_Sdbutton]};
		 var column03 = { columnWidth: .25,layout: 'column',items: [column13,rt_Atcbutton]};
		 var column04 = { columnWidth: .25,layout: 'column',items: [column14,rt_Swbutton]};
		 var column05 = { columnWidth: .25,layout: 'column',items: [column15,rt_SwWaybutton]};
		 var column06 = { columnWidth: .25,layout: 'column',items: [column16,rt_Upbutton]};
		 var column07 = { columnWidth: .25,layout: 'column',items: [column17,rt_Bdbutton]};*/
		 var column18 = { columnWidth: 0.25,layout: 'form',items: [rt_mtType]}; 
		 
		 
		var column09 = {layout: 'column',bodyStyle:'padding:10px 0px 10px 100px',items: [{xtype:"numberfield",name:"mtId",hidden:true},column11,column12,column13]};
 		var column10 = {layout: 'column',bodyStyle:'padding:10px 0px 20px 100px',items: [column14,column15,column16]};
 		var column11 = {layout: 'column',bodyStyle:'padding:0px 0px 0px 100px',items: [column17,column18]};
		
		
 		var viewPort1 =new Ext.Viewport();
		var Y = (viewPort1.getSize().height-390-27-30-43);
		
if(role =="design"){
			result_doc = [];
		}else{
				
			result_doc =  [{ text:"添加实例",handler:addCase},{ text:"取消"}]
						//		,{ text:"结果展示",handler:seeResult}]
		}
		var xingxiInfo = new Ext.form.FormPanel({
			title:'详细信息',
			height:Y,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[column09,column10,column11],
			buttonAlign:'center',
			buttons : result_doc
	});
	//-----------function--------------------	 
/*function view1Wp(){
		
		 var type = {xtype:'textfield',id:"type",fieldLabel:"型号",name:'type', width:102,
		 					allowBlank: false,emptyText:'e.g: HMC-630ZH9'};
		 var zhuantaiMaxd = {xtype:'numberfield',id:"zhuantaiMaxd",fieldLabel:"转台直径",name:'zhuantaiMaxd',unitText:"mm",width:115,allowBlank: false};
		 var zhuantaiH = {xtype:'numberfield',id:"zhuantaiH",fieldLabel:"转台高度",name:'zhuantaiH',unitText:"mm", width:115,allowBlank: false};
		 var gongjianMaxd={xtype:'numberfield',id:"gongjianMaxd",fieldLabel:"工件直径",name:'gongjianMaxd',unitText:"mm", width:115,allowBlank: false};
		 var gongjianH={xtype:'numberfield',id:"gongjianH",fieldLabel:"工件高度",name:'gongjianH', unitText:"mm",width:115,allowBlank: false};
		 var overHeight={xtype:'numberfield',id:"overHeight",fieldLabel:"平置高度",name:'overHeight',unitText:"mm", width:115,allowBlank: false};
		 var fendu={xtype:'combo',id:"fendu",fieldLabel:"转台分度",name:'fendu2', width:102,triggerAction: 'all',
						    editable:false,
						    mode: 'local',
							store:new Ext.data.ArrayStore({
									        id: "fendu_combo",
									        fields: ['fdId','fdText'],         
									        data: [[1, '1°*360'], [0.001, '0.001°*360000'],[0.001/1, '0.001/1']]
									    }), 
						    valueField: 'fdId',
						    displayField: 'fdText'};
		 var reRatio={xtype:'textfield',id:"reRatio",fieldLabel:"减速比",name:'reRatio', width:102,allowBlank: false,emptyText:'e.g: 1:90'};
		 var zaihe={xtype:'numberfield',id:"zaihe",fieldLabel:"承受载荷",name:'zaihe',unitText:"Mpa", width:115,allowBlank: false};
		 var maxSpeed={xtype:'numberfield',id:"maxSpeed",fieldLabel:"最高转速",name:'maxSpeed',unitText:"r/min", width:115,allowBlank: false};
		 var cuttingF={xtype:'numberfield',id:"cuttingF",fieldLabel:"切削力矩",name:'cuttingF',unitText:"kg/cm^2",width:115,allowBlank: false};
		 var course={xtype:'textfield',id:"course",fieldLabel:"机床型号",name:'course', width:106,allowBlank: false,emptyText:'e.g: HE100A'};
		 
		 var column22 = {layout: 'form',items: [{xtype:"numberfield",name:"workid",hidden:true},type,zhuantaiMaxd,zhuantaiH,gongjianMaxd]};
 		 var column23 = {layout: 'form',items: [gongjianH,overHeight,fendu,reRatio]};
 		 var column24 = {layout: 'form',items: [zaihe,maxSpeed,cuttingF,course]};
	
 		 var viewWp1_form = new nuaa.workpiecePanel({
		  		items:[column22,column23,column24],
 				buttons : [{ text:"确定",handler:function(){
 						view1_win.close();
 												}}]   });
 		var view1_win = new nuaa.workpieceWin({
 					title:"信息预览",items:viewWp1_form });
		
 //		    var row = wp_grid.getSelectionModel().getSelections();
 //		    var record =wp_grid.getSelectionModel().getSelected();
 		    
 		    view1_win.show();
 		  	viewWp1_form.getForm().loadRecord((Ext.decode(Ext.util.Cookies.get("Wprecord"))));
}		*/ 
function addCase(){
		if(xingxiInfo.getForm().isValid()!=true){
 							Ext.Msg.alert("提示！",'<span style="font-size:15px;">请输入新实例的型号！</span>'); 
 							return;}else{
		Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否添加该实例</span>',function(btn){
				if(btn=="yes"){
						xingxiInfo.getForm().submit({
 								url:"addCase.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:14px;">添加实例成功</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:180})
									},
    							failure: function(form, action) {
    									Ext.Msg.alert('failure',action.result.msg); }
 						})	
								
							}
					})
			}
}

function seeResult(){
		// 查看结果是否存在
		var para = Ext.getCmp("rt_mtWorkbed").getValue();
		
//		if(para !=null || para !=" "){
			
			window.open("./threejs/result.html");
//		}
		
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

if(Ext.getCmp("result_param") != undefined){
		case_tab.remove(Ext.getCmp("result_param"));}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"结果展示",
		iconCls:'aboutUs',
		layout:'form',	
		id:"result_param",
		closable : true,//允许关闭
		items:[result_compare,xingxiInfo]})
		case_tab.setActiveTab(tabPage);//设置当前tab页	
}