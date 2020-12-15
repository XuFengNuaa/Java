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
	var userrole = '<%=(String)session.getAttribute("Userrole")%>';
	
	var jzproparacolumns=new Ext.grid.ColumnModel({
		columns:[
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			{header:'铸件质量/kg',dataIndex:'pouringweight',width:100,align:'center',sortable: true},
			{header:'內浇道数目',dataIndex:'njdnum',width:80,align:'center'},
			{header:'內浇道a(mm)',dataIndex:'njda',width:80,align:'center'},
			{header:'內浇道b(mm)',dataIndex:'njdb',width:80,align:'center'},
			{header:'內浇道c(mm)',dataIndex:'njdc',width:80,align:'center'},
			{header:'横浇道a(mm)',dataIndex:'hjda',width:80,align:'center'},
			{header:'横浇道b(mm)',dataIndex:'hjdb',width:80,align:'center'},
			{header:'横浇道c(mm)',dataIndex:'hjdc',width:80,align:'center'},
			{header:'直浇道D(mm)',dataIndex:'zjdd',width:80,align:'center'},		
			{header:'备注',dataIndex:'pouringremarks',width:180,align:'center'},
			{header:'编辑',dataIndex:'edit',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
		]
	});

	var jzproparastore=new Ext.data.JsonStore({
		fields:['pouringweight','njdnum','njda','njdb','njdc','hjda','hjdb','hjdc','zjdd','pouringremarks'],
		url:'/casting/readallpouringinfor.do',
	});

	var pouringweight={xtype:'textfield',fieldLabel:'铸件质量/kg',name:'pouringweight'};
	var njdnum={xtype:'textfield',fieldLabel:'內浇道数目',name:'njdnum'};
	var njda={xtype:'textfield',fieldLabel:'內浇道a',name:'njda'};
	var njdb={xtype:'textfield',fieldLabel:'內浇道b',name:'njdb'};
	var njdc={xtype:'textfield',fieldLabel:'內浇道c',name:'njdc'};
	var hjda={xtype:'textfield',fieldLabel:'横浇道a',name:'hjda'};
	var hjdb={xtype:'textfield',fieldLabel:'横浇道b',name:'hjdb'};
	var hjdc={xtype:'textfield',fieldLabel:'横浇道c',name:'hjdc'};
	var zjdd={xtype:'textfield',fieldLabel:'直浇道D',name:'zjdd'};
	var pouringremarks={xtype:'textarea',fieldLabel:'备注',name:'pouringremarks',width:135};


	var addjzformPanel=new Ext.form.FormPanel({	
		layout:'column',
		autoScroll:true,
		frame:true,		
		labelWidth:80,
		items:[
			{layout:'form',columnWidth:1,items:pouringweight},
			{layout:'form',columnWidth:.5,items:njdnum},{layout:'form',columnWidth:.5,items:zjdd},
			{layout:'form',columnWidth:.5,items:njda},{layout:'form',columnWidth:.5,items:hjda},
			{layout:'form',columnWidth:.5,items:njdb},{layout:'form',columnWidth:.5,items:hjdb},	
			{layout:'form',columnWidth:.5,items:njdc},{layout:'form',columnWidth:.5,items:hjdc},
			{layout:'form',columnWidth:1,items:pouringremarks}
			]
	});
	
	var jzproparatbar=new Ext.Toolbar({
		items:[
			'-',
			{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addjzwinfn}
			]
	});
	
	function addjzwinfn(){
		if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权添加条目！');return;}
		jzaddwin.show();//修改窗口显示
		jzaddwin.center();
	}
	
	var jzformpanel=new Ext.grid.EditorGridPanel({
		title:'浇注系统参数',
		region:'center',
		loadMask:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,	
	    store:jzproparastore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:jzproparacolumns,
		tbar: jzproparatbar,	
		listeners:{
			cellclick: function(grid,rowIndex,columnIndex){
				var name = grid.getColumnModel().getDataIndex(columnIndex);
				if(name=='edit'){
					if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权进行删除操作！');return;}
					var pouringweight = grid.getStore().getAt(rowIndex).get('pouringweight');
					var njdnum = grid.getStore().getAt(rowIndex).get('njdnum');					
					Ext.Ajax.request({
	    		         url:'/casting/jzproparadel.do',
	    		         method : 'post',
	    		         params:{pouringweight:pouringweight,
	    		        	 		njdnum:njdnum},
	    		         success : function() {
	    		        	 Ext.Msg.alert('success','删除成功！');
	    		        	 jzformpanel.getStore().load();
	    		        	 jzformpanel.reconfigure(jzformpanel.getStore(),jzproparacolumns);		
	    		         },
	    		         failure : function() {
	    			          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
	    		         }
	    		         });	
					}else{return;}
			}
		}
	});
	
	var jzbox=new Ext.BoxComponent({
		region:'north',
		height:350,
		id:'jzbox',
		autoEl:{tag:'img',src:'/casting/images/jz.png'}	
	});

	var jzaddwin = new Ext.Window({
		modal:true,
	    width:500,
	    height:300,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"添加浇注系统参数",
	    buttonAlign:"center",
		bodyBorder:true,
		items:addjzformPanel,
		buttons:[{
	        text:'保存',
	        handler:jzaddFn
	    	}]
	});	

	function jzaddFn(){			
		addjzformPanel.form.doAction('submit',{
		 	url:'/casting/addjzpropara.do',
		 	method:'post',
			success:function(form,action){
				jzaddwin.hide();
				alert('保存成功！');
				jzproparastore.load();
	        	jzformpanel.reconfigure(jzproparastore,jzproparacolumns);
		 	},
			 //提交失败的回调函数
		 	failure:function(){
				alert('错误:服务器出现错误请稍后再试！');
		 	}
		});
	}
	
	jzproparastore.load();
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[jzbox,jzformpanel]
	});	
		
})

</script>
</head>
<body>

</body>
</html>