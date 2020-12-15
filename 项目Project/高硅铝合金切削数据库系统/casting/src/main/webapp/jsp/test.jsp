<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>实验平台</title>
<link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-all.css"/></link>
<script type="text/javascript" src="../extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="../extjs/ext-all.js"></script>
<script type="text/javascript" src="../extjs/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
<script type="text/javascript">
	Ext.onReady(function(){ 
		
		var lxproparacolumns=new Ext.grid.ColumnModel({
			columns:[
				new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
				{header:'类型',dataIndex:'parttype',width:100,align:'center',sortable: true},
				{header:'材料',dataIndex:'material',width:80,align:'center'},
				{header:'编号',dataIndex:'partnumber',width:80,align:'center'},
				{header:'零件外径(mm)',dataIndex:'partouterdiam',width:80,align:'center'},
				{header:'转速(r/min)',dataIndex:'rotatspeed',width:80,align:'center'},
				{header:'涂料',dataIndex:'coating',width:80,align:'center'},
				{header:'浇注温度',dataIndex:'pourtemper',width:80,align:'center'}
			]
		});
		
	
		
		var lxproparastore=new Ext.data.JsonStore({
			fields:['parttype','material','partnumber','partouterdiam','outersurfmachallow','innersurfmachallow',
				'endmachallow','rotatspeed','coating','operattemper','pourtemper','releasetemper','molddryingpro'],
			url:'/casting/readalllxinfor.do'
		});
		
		
		
		var lxgridpanel=new Ext.grid.EditorGridPanel({
			loadMask:true,
			autoScroll:true,
		    stripeRows:true,
		    enableColumnMove:false,	
		    store:lxproparastore,
			viewConfig:{
		    	forceFit: true,//列宽度自适应
		    	scrollOffset: 0//去除最右边空白
		    	},
			colModel:lxproparacolumns
		});
		
		
		
		lxproparastore.load();
		 new Ext.Viewport({
				layout:'fit',
				items:lxgridpanel
		 });
		
		
});  
        	
</script>
</head>
<body>

</body>
</html>