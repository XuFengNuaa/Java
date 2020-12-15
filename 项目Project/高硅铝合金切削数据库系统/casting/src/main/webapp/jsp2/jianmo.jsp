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
	
	var shiyanID={xtype:'textfield',fieldLabel:'实验ID',name:'shiyanID',width:100,value:'X-2019-8-9'};
	var jiagongfs={xtype:'combo',fieldLabel:'加工方式',name:'jiagongfs',width:100,value:'铣削'};
	var cailiaoID={xtype:'combo',fieldLabel:'材料ID',name:'cailiaoID',width:100,value:'Al-25%Si'};
	var daojuID={xtype:'combo',fieldLabel:'刀具ID',name:'daojuID',width:100,value:'APKT1135PDFR-G2-H01'};
	var jichuangID={xtype:'combo',fieldLabel:'机床ID',name:'jichuangID',width:100,value:'wtq-001'};
	var wenjianxzan={xtype:'button',text:'选择文件',name:'wenjianxzan'};
	var wenjianxz={xtype:'textfield',name:'wenjianxz',width:120,value:'请选择Excel文件！'};
		
	var shiyanxinxi=new Ext.form.FormPanel({
		title:'实验基本信息',
		layout:'column',
		frame:true,		
		labelWidth:65,
		items:[{layout:'form',columnWidth:.25,items: shiyanID},
			{layout:'form',columnWidth:.25,items: jiagongfs},
			{layout:'form',columnWidth:.25,items: cailiaoID},
			{layout:'form',columnWidth:.25,items: daojuID},
			{layout:'form',columnWidth:.25,items: jichuangID},
			{layout:'form',columnWidth:.18,items: wenjianxz},
			{layout:'form',columnWidth:.25,items: wenjianxzan}
			
			],
		buttons:[{text:'确定'},{text:'清空'}]	
	});
	
	//----------------------------------------------
	var daojunyd={xtype:'textfield',fieldLabel:'刀具耐用度模型',name:'daojunyd',width:300,
			value:'-67.299+0.191*v-19.132*aw^2-18.791*ap^2-0.804*v*fz+1.325E-6*v*L+311.1*fz*ap'};
	var cucaodu={xtype:'textfield',fieldLabel:'粗糙度模型',name:'cucaodu',width:300,
			value:'4.025-1.596*aw-4.9*ap+0.084*aw^2+0.911*ap^2-0.006*v*fz+6.016*fz*ap'};
	var Fxmx={xtype:'textfield',fieldLabel:'切削力Fx',name:'Fxmx',width:300};
	var Fymx={xtype:'textfield',fieldLabel:'切削力Fy',name:'Fymx',width:300};
	var Fzmx={xtype:'textfield',fieldLabel:'切削力Fz',name:'Fzmx',width:300};
	var modunbz={xtype:'textfield',fieldLabel:'磨钝标准(μm)',name:'modunbz',width:60};
	var nihe={xtype:'button',text:'拟合',width:'80'};
	
	var shiyanfenxi=new Ext.form.FormPanel({
		title:'实验数据分析',
		layout:'column',
		frame:true,		
		labelWidth:90,
		items:[{layout:'form',columnWidth:.5,items: modunbz},
			{layout:'form',columnWidth:.33,items: nihe},
			{layout:'form',columnWidth:1,items: daojunyd},
			{layout:'form',columnWidth:1,items: cucaodu},
			{layout:'form',columnWidth:1,items: Fxmx},
			{layout:'form',columnWidth:1,items: Fymx},
			{layout:'form',columnWidth:1,items: Fzmx},
			{layout:'form',columnWidth:.3,items: {xtype:'button',text:'保存模型',width:'80'}},
			{layout:'form',columnWidth:.3,items: {xtype:'button',text:'优化参数',width:'80'}}
			]
	});
	
	//----------------------------------------------
	var qiesu={xtype:'textfield',fieldLabel:'切削速度(m/min)',name:'qiesu',width:'60'};
	var jinji={xtype:'textfield',fieldLabel:'每齿进给量(mm/z)',name:'jinji',width:'60'};
	var qiekuan={xtype:'textfield',fieldLabel:'径向切深(mm)',name:'qiekuan',width:'60'};
	var qieshen={xtype:'textfield',fieldLabel:'轴向切深(mm)',name:'qieshen',width:'60'};
	var yuce={xtype:'button',text:'预测',name:'yuce',width:'120'};
	var T={xtype:'textfield',fieldLabel:'刀具耐用度(min) T',name:'T',width:'60'};
	var Ra={xtype:'textfield',fieldLabel:'粗糙度(μm) Ra',name:'Ra',width:'60'};
	var Fx={xtype:'textfield',fieldLabel:'切削力(N) Fx',name:'Fx',width:'60'};
	var Fy={xtype:'textfield',fieldLabel:'切削力(N) Fy',name:'Fy',width:'60'};
	var Fz={xtype:'textfield',fieldLabel:'切削力(N) Fz',name:'Fz',width:'60'};
	
	var shiyanyuce=new Ext.form.FormPanel({
		title:'模型预测',
		layout:'column',
		frame:true,		
		
		items:[{labelWidth:110,layout:'form',columnWidth:.2,items: qiesu},
			{labelWidth:110,layout:'form',columnWidth:.2,items: jinji},
			{labelWidth:90,layout:'form',columnWidth:.2,items: qiekuan},
			{labelWidth:90,layout:'form',columnWidth:.2,items: qieshen},
			{labelWidth:100,layout:'form',columnWidth:.2,items: yuce},
			{labelWidth:100,layout:'form',columnWidth:1},
			{labelWidth:110,layout:'form',columnWidth:.2,items: T},
			{labelWidth:110,layout:'form',columnWidth:.2,items: Ra},
			{labelWidth:90,layout:'form',columnWidth:.2,items: Fx},
			{labelWidth:90,layout:'form',columnWidth:.2,items: Fy},
			{labelWidth:80,layout:'form',columnWidth:.2,items: Fz}
			]
	});
	
	var shujucolumns=new Ext.grid.ColumnModel({
		columns:[
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			{header:'v(m/min)',dataIndex:'v',width:40,align:'center',sortable: true},
			{header:'fz(mm/z)',dataIndex:'fz',width:40,align:'center'},
			{header:'aw(mm)',dataIndex:'aw',width:40,align:'center'},
			{header:'ap(mm)',dataIndex:'ap',width:40,align:'center'},
			{header:'L(mm)',dataIndex:'L',width:40,align:'center'},
			{header:'Vb(μm)',dataIndex:'vb',width:40,align:'center'},		
			{header:'Ra(μm)',dataIndex:'ra',width:40,align:'center'},
			{header:'Fx(N)',dataIndex:'fx',width:40,align:'center'},
			{header:'Fy(N)',dataIndex:'fy',width:40,align:'center'},
			{header:'Fz(N)',dataIndex:'fz',width:40,align:'center'},
			{header:'删除',dataIndex:'edit',width:40,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
		]
	});
	
	var shujudata=[
		['560','0.15','0.5','1','64000','38','0.942','67.7','198.6','278.6'],
		['560','0.2','1','1.5','80000','43','0.894','149.2','211.5','316.8'],
		['560','0.25','1.5','2','154000','68','1.559','257.3','240.3','353.8'],
		['490','0.15','0.5','1','168000','41','0.721','359.34','252.2','338.4'],
		['490','0.2','1','1.5','60000','60','1.491','172.8','236.3','331.3'],
		['560','0.25','1','1.5','120000','91','1.327','77.5','237.3','278'],
		['560','0.25','0.5','1','50000','85','0.82','368.8','265.8','376.9'],
		['490','0.25','1.5','2','72000','93','2.135','271.1','298.1','398.1'],
		['400','0.15','1.5','1.5','108000','126','2.208','302.7','337.3','469.1'],
		['400','0.25','1','1','57600','45','0.576','420.5','341.5','461.4'],
		['400','0.2','0.5','2','45290','56','1.554','68.5','246.4','288'],
		['314','0.2','1.5','1','79770','66','1.781','141.8','290.8','354.6'],
		['314','0.15','1','2','114000','33','1.373','152.6','356.3','491.7'],
		['314','0.25','0.5','1.5','102000','38','1.667','116.5','347.8','451.7'],
		['560','0.15','0.5','1','64000','38','0.942','67.7','198.6','278.6'],
		['560','0.2','1','1.5','80000','43','0.894','149.2','211.5','316.8'],
		['560','0.25','1.5','2','154000','68','1.559','257.3','240.3','353.8'],
		['490','0.15','0.5','1','168000','41','0.721','359.34','252.2','338.4'],
		['490','0.2','1','1.5','60000','60','1.491','172.8','236.3','331.3'],
		['560','0.25','1','1.5','120000','91','1.327','77.5','237.3','278']
	];
	
	var shujustore=new Ext.data.Store({
		proxy:new Ext.data.MemoryProxy(shujudata),
		reader:new Ext.data.ArrayReader({},[
			{name:'v'},{name:'fz'},{name:'aw'},
			{name:'ap'},{name:'L'},{name:'vb'},
			{name:'ra'},{name:'fx'},{name:'fy'},{name:'fz'}
		])
	});
	
	shujustore.load();
	
	var shujutbar=new Ext.Toolbar({
		items:[
			'-',
			{xtype:'combo',fieldLabel:'数据表ID',name:'shujubiao',width:'120',scope:this},
			{xtype:'button',text:'删除',name:'shujubiaodel',width:'100'}
			]
	});
	
	var shiyanshuju=new Ext.grid.EditorGridPanel({
		region:'center',
		title:'实验数据',
		loadMask:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,	
	    store:shujustore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:shujucolumns,
		tbar: shujutbar,	
	});
	
	
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[
        	{region:'north',height:120,layout:'fit',items:shiyanxinxi},
        	{region:'east',width:400,layout:'fit',items:shiyanfenxi},
        	{region:'south',height:120,layout:'fit',items:shiyanyuce},
        	shiyanshuju
        	]
	});
	
});
</script>	
</head>
<body>

</body>
</html>