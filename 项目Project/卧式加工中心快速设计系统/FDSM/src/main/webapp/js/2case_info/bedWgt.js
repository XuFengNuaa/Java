function bedWgt(){
		
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";

var b00={xtype:'label',text:'属性',width:15};
var b10={xtype:'label',text:'床身长度',width:15};
var b11={xtype:'label',text:'床身宽度',width:15};
var b12={xtype:'label',text:'床身高度',width:15};
var b13={xtype:'label',text:'排屑槽宽',width:15};
var b14={xtype:'label',text:'X轨长度',width:15};
var b15={xtype:'label',text:'X轨间距',width:15};
var b16={xtype:'label',text:'Z轨长度',width:15};
var b17={xtype:'label',text:'Z轨间距',width:15};
var b18={xtype:'label',text:'板筋厚度',width:15};
var b19={xtype:'label',text:'减重孔径',width:15};
var b20={xtype:'label',text:'结构特征',width:15};
var bed_juzhen={xtype:'numberfield',id:"11",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false};
var bed_distx = {xtype:'numberfield',width:45,disabled: true};
var bed_value1 = {xtype:'numberfield',width:45,value:1, readOnly:true}

var viewPort =new Ext.Viewport();
var Y = (viewPort.getSize().height-47-27-27-15);

var bed_dafen=new Ext.form.FormPanel({
	title:'AHP法',
	frame:true,
	height:Y/1.42,
	bodyStyle:"padding-left:150px;padding-top:10px",
	//width:700,
	layout:'column',
	buttonAlign : 'center',
	labelWidth:1,
	items:[{layout:'form',columnWidth:0.08,items: b00},{layout:'form',columnWidth:0.08,items: b10},{layout:'form',columnWidth:0.08,items: b11},
		{layout:'form',columnWidth:0.08,items: b12},
		 {layout:'form',columnWidth:0.08,items: b13},{layout:'form',columnWidth:0.08,items: b14},{layout:'form',columnWidth:0.08,items: b15},
		 {layout:'form',columnWidth:0.08,items: b16},{layout:'form',columnWidth:0.08,items: b17},{layout:'form',columnWidth:0.08,items: b18},
		 {layout:'form',columnWidth:0.08,items: b19},{layout:'form',columnWidth:0.08,items: b20},
		 
		 {layout:'form',columnWidth:0.08,items: b10},{layout:'form',columnWidth:0.08,items: bed_value1},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b11",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b12",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false,allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b13",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b14",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b15",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b16",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b17",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b18",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b19",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b20",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b11},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_value1},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b22",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b23",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b24",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b25",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b26",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b27",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b28",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b29",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b30",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b12},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_value1},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b32",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b33",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b34",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b35",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b36",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b37",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b38",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b39",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b13},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_value1},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b42",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b43",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b44",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b45",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b46",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b47",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b48",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b14},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_value1},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b52",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b53",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b54",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b55",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b56",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b57",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b15},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_value1},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b62",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b63",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b64",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b65",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b66",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b16},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_value1},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b72",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b73",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b74",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b75",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b17},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_value1},
		{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b82",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b83",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b84",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]}, 
		
		 {layout:'form',columnWidth:0.08,items: b18},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_value1},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',id:"b92",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b93",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b19},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_value1},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',id:"b102",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: b20},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},
		 {layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_distx},{layout:'form',columnWidth:0.08,items: bed_value1}],
	buttons:[{text:'提交',handler:bed_jisuan},{text:'清空',handler:function(){
												bed_dafen.form.reset()
	}}]
});

function bed_jisuan(){
	if(bed_dafen.getForm().isValid()!=true){
 					Ext.Msg.alert("提示",'<span style="font-size:15px;">添加的数据不符合要求</span>'); 
 							return;}			
	bed_dafen.getForm().submit({
 								url:"addBedDf.do",
 								params: {
								        	loginName : loginName
								   	 },
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">提交成功</span>',buttons:Ext.MessageBox.OK,width:260})
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

if(Ext.getCmp("bedWgt") != undefined){
		case_tab.setActiveTab(Ext.getCmp("bedWgt"));
				return;}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"床身特征专家打分",
		iconCls:'aboutUs',
		id:"bedWgt",
		closable : true,//允许关闭
		items:[bed_dafen]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}