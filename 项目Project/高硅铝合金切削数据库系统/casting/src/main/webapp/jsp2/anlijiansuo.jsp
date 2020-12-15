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
<script type="text/javascript" src="/casting/jsp2/anligaixie.js"></script>
<script type="text/javascript" src="/casting/jsp2/quanzhifenpei.js"></script>
<script type="text/javascript">
Ext.onReady(function(){	
	
	var Cmingcheng={xtype:'textfield',fieldLabel:'材料名称',name:'Cmingcheng',width:80,value:'硅铝合金'};
	var Cjiti={xtype:'textfield',fieldLabel:'基体材料',name:'Cjiti',width:80,value:'Al'};
	var Czengqiangys={xtype:'textfield',fieldLabel:'增强相元素',name:'Czengqiangys',width:80,value:'Si'};
	var Cjiagongtz={xtype:'combo',fieldLabel:'加工特征',name:'Cjiagongtz',width:80,value:'平面铣削'};
	var Cjiagongfs={xtype:'combo',fieldLabel:'加工方式',name:'Cjiagongtz',width:80,value:'铣削'};
	var Cjiagongyl={xtype:'textfield',fieldLabel:'加工余量(mm)',name:'Cjiagongtz',width:80,value:'1'};
	
	var Cjingdu={xtype:'combo',fieldLabel:'加工精度',name:'Cjingdu',width:80,value:'精加工'};
	var Czengqiangtf={xtype:'textfield',fieldLabel:'增强相体分(%)',name:'Czengqiangtf',width:80,value:'25'};
	var Cmidu={xtype:'textfield',fieldLabel:'材料密度(g/cm³)',name:'Cmidu',width:80,value:'2.64'};
	var Ckanglaqd={xtype:'textfield',fieldLabel:'抗拉强度(MPa)',name:'Ckanglaqd',width:80,value:'480'};
	var Cqufuqd={xtype:'textfield',fieldLabel:'屈服强度(MPa)',name:'Cqufuqd',width:80,value:'441'};
	var Ctanxingml={xtype:'textfield',fieldLabel:'弹性模量(GPa)',name:'Ctanxingml',width:80,value:'96'};
	var Cdaorexs={xtype:'textfield',fieldLabel:'导热系数(W/(m*K))',name:'Cdaojuzhonglei',width:80,value:'133'};
	
	var qzCtezheng={xtype:'textfield',fieldLabel:'加工特征权重',name:'qzCtezheng',width:80,value:'0.35'};
	var qzCjingdu={xtype:'textfield',fieldLabel:'加工精度权重',name:'qzCjingdu',width:80,value:'0.21'};
	var qzCzengqiangtf={xtype:'textfield',fieldLabel:'增强相体分权重',name:'qzCzengqiangtf',width:80,value:'0.03'};
	var qzCmidu={xtype:'textfield',fieldLabel:'材料密度权重',name:'qzCmidu',width:80,value:'0.02'};
	var qzCkanglaqd={xtype:'textfield',fieldLabel:'抗拉强度权重',name:'qzCkanglaqd',width:80,value:'0.13'};
	var qzCqufuqd={xtype:'textfield',fieldLabel:'屈服强度权重',name:'qzCqufuqd',width:80,value:'0.13'};
	var qzCtanxingml={xtype:'textfield',fieldLabel:'弹性模量权重',name:'qzCtanxingml',width:80,value:'0.05'};
	var qzCdaorexs={xtype:'textfield',fieldLabel:'导热系数权重',name:'qzCdaojuzhonglei',width:80,value:'0.08'};
	
	var quanzhong=new Ext.FormPanel({
		region:'east',
		title:'属性权重',
		layout:'column',
		frame:'true',
		width:220,
		items:[
			{layout:'form',columnWidth:1,items: qzCtezheng},
			{layout:'form',columnWidth:1,items: qzCjingdu},
			 {layout:'form',columnWidth:1,items: qzCzengqiangtf},
			 {layout:'form',columnWidth:1,items: qzCmidu},
			 {layout:'form',columnWidth:1,items: qzCkanglaqd},
			 {layout:'form',columnWidth:1,items: qzCqufuqd},
			 {layout:'form',columnWidth:1,items: qzCtanxingml},
			 {layout:'form',columnWidth:1,items: qzCdaorexs}
		],
		buttons:[{text:'权值分配',handler:quanzhifpfn},{text:'检索',handler:jiansuofn}]
	});
	
	
	var jiansuotiaojian=new Ext.FormPanel({
		title:'案例检索条件',
		frame:true,
		region:'north',
		height:110,
		layout:'column',
		labelAlign:'right',
		labelWidth:100,
		items:[{layout:'form',columnWidth:.2,items: Cmingcheng},
			{layout:'form',columnWidth:.2,items: Cjiti},
			{layout:'form',columnWidth:.2,items: Czengqiangys},
			{layout:'form',columnWidth:.2,items: Cjiagongtz},
			{layout:'form',columnWidth:.2,items: Cjiagongfs},
			{layout:'form',columnWidth:.2,items: Cjiagongyl},
			{layout:'form',columnWidth:.2,items: Cjingdu},
			{layout:'form',columnWidth:.2,items: Czengqiangtf},
			{layout:'form',columnWidth:.2,items: Cmidu},
			{layout:'form',columnWidth:.2,items: Ckanglaqd},
			{layout:'form',columnWidth:.2,items: Cqufuqd},
			{layout:'form',columnWidth:.2,items: Ctanxingml},
			{layout:'form',columnWidth:.3,items: Cdaorexs,labelWidth:120}
			]
	});
	
	
	var anlicolumns=new Ext.grid.ColumnModel({
		columns:[sm,
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			{header:'案例ID',dataIndex:'anliID',width:80,align:'center',sortable: true},
			{header:'相似度(%)',dataIndex:'xiangsidu',width:50,align:'center',sortable: true},
			{header:'材料ID',dataIndex:'cailiaoID',width:80,align:'center'},
			{header:'机床ID',dataIndex:'jichuangID',width:80,align:'center'},
			{header:'刀具型号',dataIndex:'daojuxinghao',width:100,align:'center'},
			{header:'转速',dataIndex:'n',width:80,align:'center'},
			{header:'每齿进给量',dataIndex:'fz',width:100,align:'center'},
			{header:'径向切深',dataIndex:'aw',width:100,align:'center'},
			{header:'轴向切深',dataIndex:'ap',width:100,align:'center'},
			{header:'切削液',dataIndex:'qiexiaoye',width:80,align:'center'},
			{header:'加工质量',dataIndex:'zhiliang',width:80,align:'center'},
			{header:'评价',dataIndex:'value',width:60,align:'center'},
			{header:'详细',dataIndex:'edit',width:60,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>详细</a>";}}
		]
	});
	
	
	var anlidata=[
		['p-001','95','Al-25%Si','wtq-001','APKT1135PDFR-G2-H01','12000','0.21','1.48','1','否','0.8','良好'],
		['p-003','93','Al-22%Si','wtq-003','APKT1135PDFR-G2-H01','13820','0.15','1.5','1','否','0.754','良好'],
		['p-004','90','Al-18%Si','wtq-004','APKT1135PDFR-G2-H01','12000','0.21','1.48','1','否','0.8','良好'],
		['p-002','87','Al-35%Si','wtq-002','APKT1135PDFR-G2-H01','13800','0.2','1.48','1','否','0.765','良好'],
		['p-005','85','Al-42%Si','wtq-005','APKT1135PDFR-G2-H01','11600','0.18','1.48','1','否','0.73','良好'],
		['p-004','90','Al-18%Si','wtq-004','APKT1135PDFR-G2-H01','12000','0.21','1.48','1','否','0.8','良好'],
		['p-002','87','Al-35%Si','wtq-002','APKT1135PDFR-G2-H01','13800','0.2','1.48','1','否','0.765','良好'],
		['p-005','85','Al-42%Si','wtq-005','APKT1135PDFR-G2-H01','11600','0.18','1.48','1','否','0.73','良好'],
		['p-004','90','Al-18%Si','wtq-004','APKT1135PDFR-G2-H01','12000','0.21','1.48','1','否','0.8','良好'],
		['p-002','87','Al-35%Si','wtq-002','APKT1135PDFR-G2-H01','13800','0.2','1.48','1','否','0.765','良好'],
		['p-005','85','Al-42%Si','wtq-005','APKT1135PDFR-G2-H01','11600','0.18','1.48','1','否','0.73','良好']
	];
	
	var anlistore=new Ext.data.Store({
		proxy:new Ext.data.MemoryProxy(anlidata),
		reader:new Ext.data.ArrayReader({},[
			{name:'anliID'},{name:'xiangsidu'},{name:'cailiaoID'},{name:'jichuangID'},
			{name:'daojuxinghao'},{name:'n'},{name:'fz'},
			{name:'aw'},{name:'ap'},{name:'qiexiaoye'},
			{name:'zhiliang'},{name:'value'}
		])
	});
	
	anlistore.load();
	var anliliebiao=new Ext.grid.EditorGridPanel({
		region:'center',
		height:200,
		title:'相似案例列表',
		loadMask:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,	
	    store: anlistore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel: anlicolumns,
		tbar:['-',{xtype:'button',text:'改写案例',scope:this}]
	});
	

	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[jiansuotiaojian,anliliebiao,quanzhong]
	});
	
	function jiansuofn(){
		if(!anligaixie.isVisible()){
			anligaixie.show();//修改窗口显示
			anligaixie.center();
		}
	}
	
	function quanzhifpfn(){
		if(!quanzhifp.isVisible()){
			quanzhifp.show();//修改窗口显示
			quanzhifp.center();
		}
	}
	
});
</script>	
</head>
<body>

</body>
</html>