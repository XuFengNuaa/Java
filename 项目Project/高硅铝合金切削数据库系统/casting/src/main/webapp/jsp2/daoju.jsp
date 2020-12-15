<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/casting/extjs/resources/css/ext-all.css"/></link>
<link rel="stylesheet" type="text/css" href="/casting/extjs/add.css"/>
<script type="text/javascript" src="/casting/extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="/casting/extjs/ext-all.js"></script>
<script type="text/javascript" src="/casting/extjs/src/locale/ext-lang-zh_CN.js"></script>

<script type="text/javascript">
Ext.onReady(function(){	
	var jiagongleibie={xtype:'textfield',fieldLabel:'加工类别',name:'jiagongleibie',width:'90'};
	var daojucailiao={xtype:'textfield',fieldLabel:'刀具材料',name:'daojucailiao',width:'90',value:'硬质合金'};
	var daojuxinghao={xtype:'textfield',fieldLabel:'刀具型号',name:'daojuxinghao',width:'90'};
	var shengchanchangjia={xtype:'textfield',fieldLabel:'生产厂家',name:'shengchanchangjia',width:'90'};
	var yingdu={xtype:'textfield',fieldLabel:'硬度',name:'shengchanchangjia',width:'90'};
	var souanniu={xtype:'button',text:'搜索',name:'shengchanchangjia',width:'80'};
	var souqingkong={xtype:'button',text:'取消',name:'shengchanchangjia',width:'80'};
	
	
	var daojusousuo=new Ext.form.FormPanel({
		frame:true,
		layout:'column',
		frame:true,		
		labelWidth:60,
		items:[{layout:'form',columnWidth:.3,items: jiagongleibie},
			{layout:'form',columnWidth:.3,items: daojucailiao},
			{layout:'form',columnWidth:.3,items: daojuxinghao},
			{layout:'form',columnWidth:.3,items: shengchanchangjia},
			{layout:'form',columnWidth:.3,items: yingdu},
			{layout:'form',columnWidth:.3,item:'-'}
			],
		buttons:[{text:'搜索'},{text:'清空'}]	
	});
	
	var daojucolumns=new Ext.grid.ColumnModel({
		columns:[
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			{header:'加工类别',dataIndex:'jiagongleibie',width:160,align:'center',sortable: true},
			{header:'刀具材料',dataIndex:'daojucailiao',width:175,align:'center'},
			{header:'刀具型号',dataIndex:'daojuxinghao',width:180,align:'center'},
			{header:'生产厂家',dataIndex:'shengchanchangjia',width:180,align:'center'},
			{header:'备注',dataIndex:'mkremarks',width:180,align:'center'},
			{header:'详细',dataIndex:'edit',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>查看</a>";}},
			{header:'编辑',dataIndex:'edit',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
		]
	});
	
	var daojucumdata=[
		['铣加工','硬质合金','APKT1135PDFR-G2-H01','韩国克洛伊'],
		['铣加工','硬质合金','KSA345A','德国KHC'],
		['铣加工','硬质合金','KSA245A','德国KHC'],
		['铣加工','硬质合金','MiTSUBiSHi','日本三菱'],
		['铣加工','硬质合金','APPLITEC','瑞士阿彼特'],
		['铣加工','硬质合金','AL04060503T','德国来宝']
	];
	
	var daojustore=new Ext.data.Store({
		proxy:new Ext.data.MemoryProxy(daojucumdata),
		reader:new Ext.data.ArrayReader({},[
			{name:'jiagongleibie'},{name:'daojucailiao'},{name:'daojuxinghao'},
			{name:'shengchanchangjia'},{name:'mkremarks'}
		])
	});
	
	daojustore.load();
	
	var daojutbar=new Ext.Toolbar({
		items:[
			'-',
			{xtype:'button',text:'增加',scope:this,iconCls:"add"}
			]
	});
	
	var daojuxinxi=new Ext.grid.EditorGridPanel({
		region:'center',
		title:'刀具信息列表',
		loadMask:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,	
	    store:daojustore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:daojucolumns,
		tbar: daojutbar,	
	});
	
	
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[
        	{title:'搜索条件',frame:true,region:'north',height:130,layout:'fit',items:daojusousuo},
        	daojuxinxi
        	]
	});
	
	
	
	
	
	
	
});
</script>	
</head>
<body>

</body>
</html>