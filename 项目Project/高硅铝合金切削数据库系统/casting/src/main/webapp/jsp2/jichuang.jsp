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
		var jcID={xtype:'combo',fieldLabel:'机床ID',name:'jcID',width:80};
		var jcxinghao={xtype:'textfield',fieldLabel:'机床型号',name:'jcxinghao',width:80};
		var jczhonglei={xtype:'combo',fieldLabel:'机床种类',name:'jczhonglei',width:80};
		var jcchangjia={xtype:'textfield',fieldLabel:'制造商',name:'jcchangjia',width:80,value:'美国哈斯'};
		
		var jcsousuo=new Ext.form.FormPanel({
			frame:true,
			layout:'column',
			frame:true,		
			labelWidth:60,
			items:[{layout:'form',columnWidth:.25,items: jcID},
				{layout:'form',columnWidth:.25,items: jcxinghao},
				{layout:'form',columnWidth:.25,items: jczhonglei},
				{layout:'form',columnWidth:.25,items: jcchangjia}
				],
			buttons:[{text:'检索'},{text:'清空'}]	
		});
		
		var jccolumns=new Ext.grid.ColumnModel({
			columns:[
				new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
				{header:'机床ID',dataIndex:'jcID',width:160,align:'center',sortable: true},
				{header:'机床型号',dataIndex:'jcxinghao',width:175,align:'center'},
				{header:'机床种类',dataIndex:'jczhonglei',width:180,align:'center'},
				{header:'制造商',dataIndex:'jcchangjia',width:180,align:'center'},
				{header:'最高转速(r/min)',dataIndex:'r',width:180,align:'center'},
				{header:'主电机功率(kw)',dataIndex:'p',width:180,align:'center'},
				{header:'最大进给速度(m/min)',dataIndex:'f',width:180,align:'center'},
				{header:'最大扭矩(Nm)',dataIndex:'niu',width:180,align:'center'},
				{header:'X轴行程(mm)',dataIndex:'x',width:180,align:'center'},
				
				{header:'详细',dataIndex:'edit',width:80,align:'center',
					renderer:function(value, metaData, record){return "<a href='#'>查看</a>";}},
				{header:'编辑',dataIndex:'edit',width:80,align:'center',
					renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
			]
		});
		
		var jcdata=[
			['wtq-001','SMINIMILL','镗铣加工中心','美国哈斯','15000','11.2','21.2','23','406'],
			['wtq-002','SMINIMILL','立式铣床','美国哈斯','10000','11.2','21.2','23','406'],
			['wtq-003','SMINIMILL','立式铣床','美国哈斯','12000','11.2','21.2','23','406'],
			['wtq-004','SMINIMILL','立式铣床','美国哈斯','8000','9.8','21.2','23','406'],
			['wtq-005','SMINIMILL','镗铣加工中心','美国哈斯','15000','11.2','21.2','23','406'],
			['wtq-006','SMINIMILL','镗铣加工中心','美国哈斯','15000','11.2','21.2','23','406'],
			['wtq-007','SMINIMILL','镗铣加工中心','美国哈斯','15000','11.2','21.2','23','406']
		];
		
		var jcstore=new Ext.data.Store({
			proxy:new Ext.data.MemoryProxy(jcdata),
			reader:new Ext.data.ArrayReader({},[
				{name:'jcID'},{name:'jcxinghao'},{name:'jczhonglei'},{name:'jcchangjia'},
				{name:'r'},{name:'p'},{name:'f'},{name:'niu'},{name:'x'}
			])
		});
		
		jcstore.load();
		
		var jctbar=new Ext.Toolbar({
			items:[
				'-',
				{xtype:'button',text:'增加',scope:this,iconCls:"add"}
				]
		});
		
		var jcxinxi=new Ext.grid.EditorGridPanel({
			region:'center',
			title:'机床信息列表',
			loadMask:true,
			autoScroll:true,
		    stripeRows:true,
		    enableColumnMove:false,	
		    store:jcstore,
			viewConfig:{
		    	forceFit: true,//列宽度自适应
		    	scrollOffset: 0//去除最右边空白
		    	},
			colModel:jccolumns,
			tbar: jctbar,	
		});
		
		new Ext.Viewport({
			layout:'border',
			autoScroll:true,
	        items:[
	        	{title:'搜索条件',frame:true,region:'north',height:110,layout:'fit',items:jcsousuo},
	        	jcxinxi
	        	]
		});
		
});

</script>
</head>
<body>

</body>
</html>