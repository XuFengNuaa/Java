//权值分配--------------------------------------------------------------------------------
var qzCtezheng1={xtype:'textfield',fieldLabel:'加工特征权重',name:'qzCtezheng1',width:65};
var qzCjingdu1={xtype:'textfield',fieldLabel:'加工精度权重',name:'qzCjingdu1',width:65};
var qzCzengqiangtf1={xtype:'textfield',fieldLabel:'增强相体分权重',name:'qzCzengqiangtf1',width:65};
var qzCmidu1={xtype:'textfield',fieldLabel:'材料密度权重',name:'qzCmidu1',width:65};
var qzCkanglaqd1={xtype:'textfield',fieldLabel:'抗拉强度权重',name:'qzCkanglaqd1',width:65};
var qzCqufuqd1={xtype:'textfield',fieldLabel:'屈服强度权重',name:'qzCqufuqd1',width:65};
var qzCtanxingml1={xtype:'textfield',fieldLabel:'弹性模量权重',name:'qzCtanxingml1',width:65};
var qzCdaorexs1={xtype:'textfield',fieldLabel:'导热系数权重',name:'qzCdaojuzhonglei1',width:65};

var quanzhong1=new Ext.FormPanel({
	region:'north',
	title:'属性权重',
	layout:'column',
	frame:'true',
	height:130,
	items:[
		{layout:'form',columnWidth:.33,items: qzCtezheng1},
		{layout:'form',columnWidth:.33,items: qzCjingdu1},
		 {layout:'form',columnWidth:.33,items: qzCzengqiangtf1},
		 {layout:'form',columnWidth:.33,items: qzCmidu1},
		 {layout:'form',columnWidth:.33,items: qzCkanglaqd1},
		 {layout:'form',columnWidth:.33,items: qzCqufuqd1},
		 {layout:'form',columnWidth:.33,items: qzCtanxingml1},
		 {layout:'form',columnWidth:.33,items: qzCdaorexs1}
	]
});

var sm = new Ext.grid.CheckboxSelectionModel();
var dafencolumns=new Ext.grid.ColumnModel({
	columns:[sm,
		new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
		{header:'用户ID',dataIndex:'yonghuID',width:180,align:'center',sortable: true},
		{header:'评分时间',dataIndex:'shijian',width:150,align:'center',sortable: true},
		{header:'详细',width:80,align:'center',
			renderer:function(value, metaData, record){return "<a href='#'>查看</a>";}},
		{header:'编辑',width:80,align:'center',
			renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
	]
});

var dafendata=[
	['zj-001','2018-12-3'],
	['zj-003','2019-8-3'],
	['zj-004','2019-12-3']
];

var dafenstore=new Ext.data.Store({
	proxy:new Ext.data.MemoryProxy(dafendata),
	reader:new Ext.data.ArrayReader({},[
		{name:'yonghuID'},{name:'shijian'}
	])
});

var dafentbar=new Ext.Toolbar({
	items:[
		'-',
		{xtype:'button',text:'生成属性权重',scope:this}
		]
});

dafenstore.load();
var dafenliebiao=new Ext.grid.EditorGridPanel({
	region:'center',
	title:'专家打分情况',
	loadMask:true,
	autoScroll:true,
    stripeRows:true,
    enableColumnMove:false,	
    store: dafenstore,
	viewConfig:{
    	forceFit: true,//列宽度自适应
    	scrollOffset: 0//去除最右边空白
    	},
	colModel: dafencolumns,
	tbar:dafentbar
});


var l00={xtype:'label',text:'属性',width:15};
var l111={xtype:'label',text:'加工特征',width:15};
var l11={xtype:'label',text:'加工精度',width:15};
var l12={xtype:'label',text:'增强相体分',width:15};
var l13={xtype:'label',text:'材料密度',width:15};
var l14={xtype:'label',text:'抗拉强度',width:15};
var l15={xtype:'label',text:'屈服强度',width:15};
var l16={xtype:'label',text:'弹性模量',width:15};
var l17={xtype:'label',text:'导热系数',width:15};
var juzhen={xtype:'textfield',width:30};

var dafen=new Ext.FormPanel({
	title:'PHP法',
	frame:true,
	region:'east',
	width:510,
	layout:'column',
	labelWidth:1,
	items:[{layout:'form',columnWidth:0.111,items: l00},{layout:'form',columnWidth:0.111,items: l111},{layout:'form',columnWidth:0.111,items: l11},
		{layout:'form',columnWidth:0.111,items: l12},
		 {layout:'form',columnWidth:0.111,items: l13},{layout:'form',columnWidth:0.111,items: l14},{layout:'form',columnWidth:0.111,items: l15},
		 {layout:'form',columnWidth:0.111,items: l16},{layout:'form',columnWidth:0.111,items: l17},
		 
		 {layout:'form',columnWidth:0.111,items: l111},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 
		 {layout:'form',columnWidth:0.111,items: l11},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 
		 {layout:'form',columnWidth:0.111,items: l12},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 
		 {layout:'form',columnWidth:0.111,items: l13},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 
		 {layout:'form',columnWidth:0.111,items: l14},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 
		 {layout:'form',columnWidth:0.111,items: l15},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 
		 {layout:'form',columnWidth:0.111,items: l16},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 
		 {layout:'form',columnWidth:0.111,items: l17},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},
		 {layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen},{layout:'form',columnWidth:0.111,items: juzhen}],
	buttons:[{text:'提交'},{text:'清空'}]
});


var quanzhifp = new Ext.Window({
	modal:true,
    width:800,
    height:450,
    plain: true,
    layout:'border',
    title:"权值分配",
	bodyBorder:true,
	items:[quanzhong1,dafenliebiao,dafen]
});	
	
	