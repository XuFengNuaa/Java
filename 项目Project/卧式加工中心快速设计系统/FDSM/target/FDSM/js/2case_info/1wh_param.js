function wh_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
		var wh_gjmax_d = {xtype:'numberfield',id:"wh_gjmax_d",fieldLabel:"工件直径 ",name:'wh_gjmax_d', unitText:"mm",width:130,allowBlank: false};
		var wh_gjmax_h = {xtype:'numberfield',id:"wh_gjmax_h",fieldLabel:"工件高度  ",name:'wh_gjmax_h', width:130,allowBlank: false,unitText:"mm"};
		var wh_sd_kw = {xtype:'numberfield',id:"wh_sd_kw",fieldLabel:"主电机功率  ",name:'wh_sd_kw',allowBlank: false,unitText:"Kw", width:130};
		var wh_sd_r= {xtype:'numberfield',id:"wh_sd_r",fieldLabel:"主轴转速  ",name:'wh_sd_r',allowBlank: false,unitText:"rpm", width:130};
		var wh_tool_cap = {xtype:'numberfield',id:"wh_tool_cap",fieldLabel:"刀库容量  ",name:'wh_tool_cap',allowBlank: false,unitText:"把", width:130};
		var wh_axis_v = {xtype:'numberfield',id:"wh_axis_v",fieldLabel:"三轴快移速度  ",name:'wh_axis_v',allowBlank: false,unitText:"m/min", width:150};
		var wh_dwjd = {xtype:'textfield',id:"wh_dwjd",fieldLabel:"定位精度  ",name:'wh_dwjd',regex:/^[0]+([.]{1}[0-9]+){0,1}$/,regexText:'输入参数有误',allowBlank: false,unitText:"mm", width:125};
		var wh_agdwjd = {xtype:'textfield',id:"wh_agdwjd",fieldLabel:"重复定位精度 ",name:'wh_agdwjd',regex:/^[0]+([.]{1}[0-9]+){0,1}$/,regexText:'输入参数有误',allowBlank: false,unitText:"mm", width:130};
		
		var wh_continue={xtype:"button",text:"下一步",handler:function(){
					if(whole_param.getForm().isValid()!=true){
							Ext.Msg.alert("提示","输入的数据不符合要求，请重新输入！");  
								return;
				}else {
					Ext.util.Cookies.set("wh_gjmax_d",Ext.getCmp("wh_gjmax_d").getValue());
					Ext.util.Cookies.set("wh_gjmax_h",Ext.getCmp("wh_gjmax_h").getValue());
					Ext.util.Cookies.set("wh_sd_kw",Ext.getCmp("wh_sd_kw").getValue());
					Ext.util.Cookies.set("wh_sd_r",Ext.getCmp("wh_sd_r").getValue());
					Ext.util.Cookies.set("wh_tool_cap",Ext.getCmp("wh_tool_cap").getValue());
					Ext.util.Cookies.set("wh_axis_v",Ext.getCmp("wh_axis_v").getValue());
					Ext.util.Cookies.set("wh_dwjd",Ext.getCmp("wh_dwjd").getValue());
					Ext.util.Cookies.set("wh_agdwjd",Ext.getCmp("wh_agdwjd").getValue());
						
						wkpiece_param();
				}
					
					
		}};
		var wh_clear={xtype:"button",text:"重置",handler:function(){whole_param.form.reset();}};
		
		var wh_column11 = {columnWidth: .25,layout: 'form',items: [wh_gjmax_d]};
 		var wh_column12 = {columnWidth: .25,layout: 'form',items: [wh_gjmax_h]};
 		var wh_column13 = {columnWidth: .25,layout: 'form',items: [wh_sd_kw]};
 		var wh_column14 = {columnWidth: .25,layout: 'form',items: [wh_sd_r]};
 		var wh_column15 = {columnWidth: .25,layout: 'form',items: [wh_tool_cap]};
 		
 		var wh_column16 = {columnWidth: .25,layout: 'form',items: [wh_dwjd]};
 		var wh_column17 = {columnWidth: .25,layout: 'form',items: [wh_agdwjd]};
		var wh_column18 = {columnWidth: .25,layout: 'form',items: [wh_axis_v]};
		var whole_param = new Ext.FormPanel({
			title:'整机参数',
			height:270,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:20px 10px 10px 50px ',items:[wh_column11,wh_column12,wh_column14]},
					{layout: 'column',bodyStyle:'padding:20px 10px 10px 50px',items:[wh_column13,wh_column15,wh_column16]},
							{layout: 'column',bodyStyle:'padding:20px 10px 10px 50px',items:[wh_column17,wh_column18]}],
			buttonAlign : 'center',
		 	buttons : [wh_continue,wh_clear]
	});
	
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

if(Ext.getCmp("wh_param") != undefined){	
		case_tab.setActiveTab(Ext.getCmp("wh_param"));
				return;}
		var tabPage = case_tab.add({//动态添加tab页
		title:"整机参数",
		iconCls:'aboutUs',
		id:"wh_param",
		closable : true,//允许关闭
		items:[whole_param]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}