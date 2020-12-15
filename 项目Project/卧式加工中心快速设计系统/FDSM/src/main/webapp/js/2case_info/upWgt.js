function upWgt(){
		
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";

var l00={xtype:'label',text:'属性',width:15};
var l10={xtype:'label',text:'立柱高度',width:15};
var l11={xtype:'label',text:'立柱宽度',width:15};
var l12={xtype:'label',text:'Y轴行程',width:15};
var l13={xtype:'label',text:'主轴宽度',width:15};
var l14={xtype:'label',text:'主轴高度',width:15};
var l15={xtype:'label',text:'立柱壁厚',width:15};
var l16={xtype:'label',text:'减重孔',width:15};
var l17={xtype:'label',text:'板筋厚度',width:15};
var l18={xtype:'label',text:'横板筋数量',width:15};
var l19={xtype:'label',text:'竖板筋数量',width:15};
var juzhen={xtype:'numberfield',id:"11",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false};
var distx = {xtype:'numberfield',width:45,disabled: true};
var value1 = {xtype:'numberfield',width:45,value:1, readOnly:true}

var viewPort =new Ext.Viewport();
var Y = (viewPort.getSize().height-47-27-27-15);

var dafen=new Ext.form.FormPanel({
	title:'AHP法',
	frame:true,
	height:Y/1.54,
	bodyStyle:"padding-left:150px;padding-top:10px",
	//width:700,
	layout:'column',
	buttonAlign : 'center',
	labelWidth:1,
	items:[{layout:'form',columnWidth:0.09,items: l00},{layout:'form',columnWidth:0.09,items: l10},{layout:'form',columnWidth:0.09,items: l11},
		{layout:'form',columnWidth:0.09,items: l12},
		 {layout:'form',columnWidth:0.09,items: l13},{layout:'form',columnWidth:0.09,items: l14},{layout:'form',columnWidth:0.09,items: l15},
		 {layout:'form',columnWidth:0.09,items: l16},{layout:'form',columnWidth:0.09,items: l17},{layout:'form',columnWidth:0.09,items: l18},{layout:'form',columnWidth:0.09,items: l19},
		 
		 {layout:'form',columnWidth:0.09,items: l10},{layout:'form',columnWidth:0.09,items: value1},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u11",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false,allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u12",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false,allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u13",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u14",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u15",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u16",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u17",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u18",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u19",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: l11},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: value1},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u22",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u23",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u24",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u25",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u26",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u27",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u28",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u29",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: l12},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: value1},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u32",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u33",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u34",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u35",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u36",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u37",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u38",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: l13},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: value1},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u42",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u43",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u44",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u45",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u46",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',id:"u47",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: l14},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: value1},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u52",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u53",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u54",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u55",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u56",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: l15},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: value1},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u62",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u63",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u64",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u65",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: l16},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: value1},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u72",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u73",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u74",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 
		 {layout:'form',columnWidth:0.09,items: l17},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: value1},
		{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u82",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u83",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: l18},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: value1},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',id:"u92",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 
		 {layout:'form',columnWidth:0.09,items: l19},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: distx},
		 {layout:'form',columnWidth:0.09,items: distx},{layout:'form',columnWidth:0.09,items: value1}],
	buttons:[{text:'提交',handler:jisuan},{text:'清空',handler:function(){
												dafen.form.reset()
	}}]
});


function jisuan(){
	if(dafen.getForm().isValid()!=true){
 					Ext.Msg.alert("提示",'<span style="font-size:15px;">添加的数据不符合要求</span>'); 
 							return;}	
	dafen.getForm().submit({
 								url:"addUpDf.do",
 								params: {
								        	loginName : loginName
								   	 },
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">提交成功！</span>',buttons:Ext.MessageBox.OK,width:260})
									},
    							failure: function(form, action) {
    									Ext.Msg.alert('Failure', "服务器出现问题，请稍后再试"); }
 						})
				

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

if(Ext.getCmp("upWgt") != undefined){
		case_tab.setActiveTab(Ext.getCmp("upWgt"));
				return;}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"立柱特征专家打分",
		iconCls:'aboutUs',
		id:"upWgt",
		closable : true,//允许关闭
		items:[dafen]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}