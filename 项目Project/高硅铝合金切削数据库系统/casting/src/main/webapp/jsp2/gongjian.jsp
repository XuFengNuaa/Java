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
	
	var cailiaoleibie={xtype:'textfield',fieldLabel:'材料类别',name:'cailiaoleibie',width:'70',value:'硅铝合金'};
	var zengqiangxiangyuansu={xtype:'textfield',fieldLabel:'增强相元素',name:'cailiaotifen',width:'70',value:'Si'};
	var jiticailiao={xtype:'textfield',fieldLabel:'基体材料',name:'jiticailiao',width:'70',value:'Al'};
	var zhibeigongyi={xtype:'textfield',fieldLabel:'制备工艺',name:'zhibeigongyi',width:'70',value:'粉末冶金'};
	
	
	var cailiaosousuo=new Ext.form.FormPanel({
		frame:true,
		layout:'column',
		frame:true,		
		labelWidth:65,
		items:[{layout:'form',columnWidth:.25,items: cailiaoleibie},
			{layout:'form',columnWidth:.25,items: zengqiangxiangyuansu},
			{layout:'form',columnWidth:.25,items: jiticailiao},
			{layout:'form',columnWidth:.25,items: zhibeigongyi}
			],
		buttons:[{text:'搜索'},{text:'清空'}]	
	});
	
	var cailiaocolumns=new Ext.grid.ColumnModel({
		columns:[
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			{header:'材料牌号',dataIndex:'cailiaopaihao',width:160,align:'center',sortable: true},
			{header:'材料类别',dataIndex:'cailiaoleibie',width:160,align:'center'},
			{header:'基体材料',dataIndex:'jiticailiao',width:180,align:'center'},
			{header:'增强相元素',dataIndex:'zengqiangxiangyuansu',width:175,align:'center'},
			{header:'体积分数(%)',dataIndex:'tijifenshu',width:180,align:'center'},
			{header:'制备工艺',dataIndex:'zhibeigongyi',width:180,align:'center'},
			{header:'抗拉强度(MPa)',dataIndex:'kanglaqiangdu',width:180,align:'center'},
			{header:'弹性模量(GPa)',dataIndex:'tanxingmoliang',width:180,align:'center'},
			{header:'导热(W/(m*K))',dataIndex:'daore',width:180,align:'center'},
			{header:'详细',dataIndex:'edit',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>查看</a>";}},
			{header:'编辑',dataIndex:'edit',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
		]
	});
	
	var cailiaocumdata=[
		['Al-25%Si','硅铝合金','Al','Si','25','粉末冶金，急速冷却','480','96','133.1'],
		['Al-35%Si','硅铝合金','Al','Si','35','粉末冶金，急速冷却','480','96','133.1'],
		['Al-22%Si','硅铝合金','Al','Si','22','粉末冶金，急速冷却','480','96','133.1'],
		['Al-20%Si','硅铝合金','Al','Si','20','粉末冶金，急速冷却','480','96','133.1'],
		['Al-18%Si','硅铝合金','Al','Si','18','粉末冶金，急速冷却','480','96','133.1'],
	];
	
	var cailiaostore=new Ext.data.Store({
		proxy:new Ext.data.MemoryProxy(cailiaocumdata),
		reader:new Ext.data.ArrayReader({},[
			{name:'cailiaopaihao'},{name:'cailiaoleibie'},{name:'jiticailiao'},
			{name:'zengqiangxiangyuansu'},{name:'tijifenshu'},{name:'zhibeigongyi'},
			{name:'kanglaqiangdu'},{name:'tanxingmoliang'},
			{name:'daore'}
		])
	});
	
	cailiaostore.load();
	
	var cailiaotbar=new Ext.Toolbar({
		items:[
			'-',
			{xtype:'button',text:'增加',scope:this,iconCls:"add"}
			]
	});
	
	var cailiaoxinxi=new Ext.grid.EditorGridPanel({
		region:'center',
		title:'材料信息列表',
		loadMask:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,	
	    store:cailiaostore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:cailiaocolumns,
		tbar: cailiaotbar,	
	});
	
	
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[
        	{title:'搜索条件',frame:true,region:'north',height:120,layout:'fit',items:cailiaosousuo},
        	cailiaoxinxi
        	]
	});
	
});
</script>	
</head>
<body>

</body>
</html>