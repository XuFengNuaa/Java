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
<script type="text/javascript" src="/casting/js/sxbmconfig.js"></script>

<script type="text/javascript">
Ext.onReady(function(){	
	var userrole = '<%=(String)session.getAttribute("Userrole")%>';
	
	//拔模斜度模块----------------------------------------------------------------------------
	var bmproparatbar=new Ext.Toolbar({
		items:[
			'-',
			{xtype:'textfield',emptyText:'请输入检索类型...',name:'bmfindstring',width:200,id:'bmfindstring'},
			{xtype:'spacer',width:10},
			{xtype:'button',text:'查询',scope:this,handler:bmfindfn},
			{xtype:'spacer',width:20},
			'-',
			{xtype:'button',text:'增加',scope:this,iconCls:'add',handler:addbmwinfn}
			]
	});
	
	function bmfindfn(){
		var bmfindstring=Ext.getCmp('bmfindstring').getValue();
		if(bmfindstring=='') { alert('请正确设置查找要求！'); return;}	
		var bmpartproparastore=new Ext.data.JsonStore({
			baseParams:{bmfindstring : bmfindstring},
			fields:['castingmodeling','sizecondition','draftangle','draftlength','draftremarks'],
			url:'/casting/readpartdraftinfor.do'		
			});	
		
		bmpartproparastore.load();					
		bmformpanel.reconfigure(bmpartproparastore,bmproparacolumns);		
	}
	
	function addbmwinfn(){
		if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权添加条目！');return;}
		bmaddwin.show();//修改窗口显示
		bmaddwin.center();
	}
	
	
	
	var bmformpanel=new Ext.grid.EditorGridPanel({
		region:'center',
		title:'拔模斜度',
		loadMask:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,	
	    store:bmproparastore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:bmproparacolumns,
		tbar: bmproparatbar,	
		listeners:{
			cellclick: function(grid,rowIndex,columnIndex){
				var name = grid.getColumnModel().getDataIndex(columnIndex);
				if(name=='edit'){
					if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权进行删除操作！');return;}
					var sizecondition=grid.getStore().getAt(rowIndex).get('sizecondition');
					var castingmodeling=grid.getStore().getAt(rowIndex).get('castingmodeling');
					var draftremarks=grid.getStore().getAt(rowIndex).get('draftremarks');
					Ext.Ajax.request({
	    		         url:'/casting/bmproparadel.do',
	    		         method : 'post',
	    		         params:{sizecondition:sizecondition,
	    		        	 	castingmodeling:castingmodeling,
	    		        	 	draftremarks:draftremarks},
	    		         success : function() {
	    		        	 Ext.Msg.alert('success','删除成功！');
	    		        	 bmformpanel.getStore().load();
	    		        	 bmformpanel.reconfigure(bmformpanel.getStore(),bmproparacolumns);		
	    		         },
	    		         failure : function() {
	    			          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
	    		         }
	    		         });	
					}else{return;}
			}
		}
		
	});
	
	var bmbox=new Ext.BoxComponent({
		region:'north',
		height:350,
		id:'bmbox',
		autoEl:{tag:'img',src:'/casting/images/bm.png'}
	});
	
	var bmaddwin = new Ext.Window({
		modal:true,
	    width:280,
	    height:270,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"添加拔模斜度参数",
	    buttonAlign:"center",
		bodyBorder:true,
		items:addbmformPanel,
		buttons:[{
	        text:'保存',
	        handler:bmaddFn
	    	}]
	});	

	function bmaddFn(){			
		addbmformPanel.form.doAction('submit',{
		 	url:'/casting/addbmpropara.do',
		 	method:'post',
			success:function(form,action){
				bmaddwin.hide();
				alert('保存成功！');
				bmproparastore.load();
	        	bmformpanel.reconfigure(bmproparastore,bmproparacolumns);
		 	},
			 //提交失败的回调函数
		 	failure:function(){
				alert('错误:服务器出现错误请稍后再试！');
		 	}
		});
	}
	
	
	bmproparastore.load();
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[bmbox,bmformpanel]
	});	
	
});
</script>
</head>
<body>


</body>
</html>