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
	
	var mkproparacolumns=new Ext.grid.ColumnModel({
		columns:[
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			{header:'铸件模数Mj/cm',dataIndex:'castingmodulus',width:160,align:'center',sortable: true},
			{header:'慢浇冒口直径(mm)',dataIndex:'slowpourd',width:175,align:'center'},
			{header:'快浇冒口直径(mm)',dataIndex:'quickpourd',width:180,align:'center'},
			{header:'正方形(圆形)冒口颈截面尺寸(mm)',dataIndex:'squaremk',width:180,align:'center'},
			{header:'2axa矩形冒口颈截面尺寸(mm)',dataIndex:'rectanglemk',width:180,align:'center'},
			{header:'备注',dataIndex:'mkremarks',width:180,align:'center'},
			{header:'编辑',dataIndex:'edit',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
		]
	});

	var mkproparastore=new Ext.data.JsonStore({
		fields:['castingmodulus','slowpourd','quickpourd','squaremk','rectanglemk','mkremarks'],
		url:'/casting/readallriserinfor.do',
	});

	var textfieldwidth='150';
	var castingmodulus={xtype:'textfield',fieldLabel:'铸件模数',name:'castingmodulus',width:textfieldwidth};
	var slowpourd={xtype:'textfield',fieldLabel:'慢浇冒口直径(mm)',name:'slowpourd',width:textfieldwidth};
	var quickpourd={xtype:'textfield',fieldLabel:'快浇冒口直径(mm)',name:'quickpourd',width:textfieldwidth};
	var squaremk={xtype:'textfield',fieldLabel:'正方形(圆形)冒口颈截面尺寸(mm)',name:'squaremk',width:textfieldwidth};
	var rectanglemk={xtype:'textfield',fieldLabel:'2axa矩形冒口颈截面尺寸(mm)',name:'rectanglemk',width:textfieldwidth};
	var mkremarks={xtype:'textarea',fieldLabel:'备注',name:'mkremarks',width:textfieldwidth};

	var addmkformPanel=new Ext.form.FormPanel({	
		layout:'form',
		autoScroll:true,
		frame:true,		
		labelWidth:120,
		items:[castingmodulus,slowpourd,quickpourd,squaremk,rectanglemk,mkremarks]
	});
	
	
	var mkproparatbar=new Ext.Toolbar({
		items:[
			'-',
			{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addmkwinfn}
			]
	});
	
	function addmkwinfn(){
		if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权添加条目！');return;}
		mkaddwin.show();//修改窗口显示
		mkaddwin.center();
	}
	
	var mkformpanel=new Ext.grid.EditorGridPanel({
		region:'center',
		title:'冒口参数(模数法)',
		loadMask:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,	
	    store:mkproparastore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:mkproparacolumns,
		tbar: mkproparatbar,	
		listeners:{
			cellclick: function(grid,rowIndex,columnIndex){
				var name = grid.getColumnModel().getDataIndex(columnIndex);
				if(name=='edit'){
					if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权进行删除操作！');return;}
					var castingmodulus = grid.getStore().getAt(rowIndex).get('castingmodulus');
					Ext.Ajax.request({
	    		         url:'/casting/mkproparadel.do',
	    		         method : 'post',
	    		         params:{castingmodulus:castingmodulus},
	    		         success : function() {
	    		        	 Ext.Msg.alert('success','删除成功！');
	    		        	 mkformpanel.getStore().load();
	    		        	 mkformpanel.reconfigure(mkformpanel.getStore(),mkproparacolumns);		
	    		         },
	    		         failure : function() {
	    			          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
	    		         }
	    		         });	
					}else{return;}
			}
		}
	});
		
	var mkbox=new Ext.BoxComponent({
		id:'mkbox',
		autoEl:{tag:'img',src:'/casting/images/mk.png'}
	});
		
	var mkaddwin = new Ext.Window({
		modal:true,
	    width:330,
	    height:320,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"添加冒口参数",
	    buttonAlign:"center",
		bodyBorder:true,
		items:addmkformPanel,
		buttons:[{
	        text:'保存',
	        handler:mkaddFn
	    	}]
	});	

	function mkaddFn(){			
		addmkformPanel.form.doAction('submit',{
		 	url:'/casting/addmkpropara.do',
		 	method:'post',
			success:function(form,action){
				mkaddwin.hide();
				alert('保存成功！');
				mkformpanel.getStore().load();
	        	mkformpanel.reconfigure(mkformpanel.getStore(),mkproparacolumns);
		 	},
			 //提交失败的回调函数
		 	failure:function(){
				alert('错误:服务器出现错误请稍后再试！');
		 	}
		});
	}
	
	
	mkproparastore.load();
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[
        	{xtype:'panel',title:'冒口参数(热节圆法)',frame:true,region:'north',height:350,layout:'fit',items:mkbox}
        	,mkformpanel]
	});	
	
	
});
</script>	
</head>
<body>

</body>
</html>