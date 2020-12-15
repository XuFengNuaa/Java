//拔模斜度---------------------------------------------------------------------
var bmproparacolumns=new Ext.grid.ColumnModel({
	columns:[
		new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
		{header:'类型',dataIndex:'castingmodeling',width:200,align:'center'},
		{header:'尺寸条件',dataIndex:'sizecondition',width:180,align:'center',sortable: true},
		{header:'α(≤)',dataIndex:'draftangle',width:120,align:'center'},
		{header:'a(≤)',dataIndex:'draftlength',width:120,align:'center'},
		{header:'备注',dataIndex:'draftremarks',width:240,align:'center'},
		{header:'编辑',dataIndex:'edit',width:80,align:'center',
			renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
	]
});

var bmproparastore=new Ext.data.JsonStore({
	fields:['castingmodeling','sizecondition','draftangle','draftlength','draftremarks'],
	url:'/casting/readalldraftinfor.do',
});

var textfieldwidth='150';
var castingmodeling={xtype:'textfield',fieldLabel:'类型',name:'castingmodeling',width:textfieldwidth};
var sizecondition={xtype:'textfield',fieldLabel:'尺寸条件',name:'sizecondition',width:textfieldwidth};
var draftangle={xtype:'textfield',fieldLabel:'α(≤)',name:'draftangle',width:textfieldwidth};
var draftlength={xtype:'textfield',fieldLabel:'a(≤)',name:'draftlength',width:textfieldwidth};
var draftremarks={xtype:'textarea',fieldLabel:'备注',name:'draftremarks',width:textfieldwidth};

var addbmformPanel=new Ext.form.FormPanel({	
	layout:'form',
	autoScroll:true,
	frame:true,		
	labelWidth:60,
	items:[castingmodeling,sizecondition,draftangle,draftlength,draftremarks]
});

