<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/casting/extjs/resources/css/ext-all.css"/></link>
<link rel="stylesheet" type="text/css" href="/casting/extjs/add.css" />
<script type="text/javascript" src="/casting/extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="/casting/extjs/ext-all.js"></script>
<script type="text/javascript" src="/casting/extjs/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">

Ext.onReady(function(){
	
	var sm = new Ext.grid.CheckboxSelectionModel();
	var yonghucolumns=new Ext.grid.ColumnModel({
		columns:[sm,
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			{header: "角色",dataIndex: "userrole",width:100,sortable: true},
			{header: "工号", dataIndex: "stuff_num",width:100,sortable: true},
			{header: "用户姓名",dataIndex: "username",width:100,sortable: true},
			{header: "联系电话",dataIndex: "phonenumber",width:150,sortable: true}, 
			{header: "备注",dataIndex: "remark",width:350,sortable: true}
			]
	});
	
	var yonghudata=[
		['技术管理员','WTQ20010503','张天祥','',''],
		['技术管理员','WTQ20030506','王伟才','',''],
		['技术管理员','WTQ20160302','李栋','',''],
		['系统管理员','WTQ19980503','方成','','']
	];
	
	var yonghustore=new Ext.data.Store({
		proxy:new Ext.data.MemoryProxy(yonghudata),
		reader:new Ext.data.ArrayReader({},[
			{name:'userrole'},{name:'stuff_num'},{name:'username'},{name:'phonenumber'},
			{name:'remark'}
		])
	});
	
	yonghustore.load();
    var grid = new Ext.grid.EditorGridPanel({	      			
    			id:'filetable',
    		    loadMask:true,
    		    enableColumnMove:false,
    		    sm:sm, //多选框checkbox
			    stateId:'filetable',
			    viewConfig:{
			    	forceFit: true,//列宽度自适应
			    	scrollOffset: 0//去除最右边空白
			    	},
			    	colModel: yonghucolumns,
    		        store:yonghustore  	       
    });
    
    new Ext.Viewport({
		layout:'border',
		autoHeight:false,
		items:[{
				region:'center',
				layout:'fit',				
				title:'用户查询结果',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:['-',
							{ 								
					            id:'edit-btn',
					            text:"修改", 
					            iconCls:"update"
					        },'-',{ 								
					            id:'del',
					            text:"删除", 
					            iconCls:"delete"
				            },'-']
		        }),
		        items:grid
		}] 
	});	

   
    
  
});

</script>
</head>
<body>

</body>
</html>