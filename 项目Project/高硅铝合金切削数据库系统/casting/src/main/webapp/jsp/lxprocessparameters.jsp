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
<script type="text/javascript" src="/casting/js/lxconfig.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	var userrole = '<%=(String)session.getAttribute("Userrole")%>';
	
	var lxproparacolumns=new Ext.grid.ColumnModel({
		columns:[
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			{header:'类型',dataIndex:'parttype',width:100,align:'center',sortable: true},
			{header:'材料',dataIndex:'material',width:80,align:'center'},
			{header:'编号',dataIndex:'partnumber',width:80,align:'center'},
			{header:'零件外径(mm)',dataIndex:'partouterdiam',width:80,align:'center'},
			{header:'转速(r/min)',dataIndex:'rotatspeed',width:80,align:'center'},
			{header:'涂料',dataIndex:'coating',width:80,align:'center'},
			{header:'浇注温度',dataIndex:'pourtemper',width:80,align:'center'},
			{dataIndex:'query',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>查询</a>";}},
			{dataIndex:'edit',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>修改</a>";}},
			{dataIndex:'delete',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
		]
	});
	
	var lxproparastore=new Ext.data.JsonStore({
		fields:['parttype','material','partnumber','partouterdiam',
							'rotatspeed','coating','pourtemper'],//变量名，数据名，数据库字段名要对应，顺序可以不同，部分字段也可
		url:'/casting/readalllxinfor.do'
	});
	
	var lxproparatbar=new Ext.Toolbar({
		items:[
			'-',
			{xtype:'button',text:'增加',scope:this,iconCls:"add",handler:addlxwinfn}
			]
	});
	
	var lxnorthformpanel=new Ext.form.FormPanel({
			layout:'column',
			region:'north',
			height:35,
			frame:true,	
			items:[
				{columnWidth:.3,layout:'column',items:[
					{layout:'form',columnWidth:.8,xtype:'textfield',emptyText:'请输入关键字...',name:'findstring'},
					{layout:'form',columnWidth:.2,xtype:'button',text:'查询',scope:this,handler:lxfindfn}
				]},{
					layout:'form',columnWidth:.4,
					items:{layout:'form',xtype:'radiogroup',
						items:[
							{boxLabel:'类型',name:'rb',inputValue:'parttype'},
							{boxLabel:'材料',name:'rb',inputValue:'material'}
						]}
				}
			]			
	});
	
	var lxgridpanel=new Ext.grid.EditorGridPanel({
		region:'center',
		loadMask:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,	
	    store:lxproparastore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:lxproparacolumns,
		tbar: lxproparatbar,	
		listeners:{
			cellclick: function(grid,rowIndex,columnIndex){
				var name = grid.getColumnModel().getDataIndex(columnIndex);
				var partnumber = grid.getStore().getAt(rowIndex).get('partnumber');
				if(name=='delete'){
					if(userrole !='管理员'&&userrole !='系统管理员'){Ext.Msg.alert('提示','对不起！您无权进行删除操作！');return;}
					Ext.Ajax.request({
	    		         url:'/casting/lxproparadel.do',
	    		         method : 'post',
	    		         params:{bypartnumber:partnumber},
	    		         success : function() {
	    		        	 Ext.Msg.alert('success','删除成功！');
	    		        	 lxgridpanel.getStore().load();
	    		        	 lxgridpanel.reconfigure(lxgridpanel.getStore(),lxproparacolumns);		
	    		         },
	    		         failure : function() {
	    			         Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
	    		         }
	    		         });
				}else if(name=='query'){
  		        	lxquewin.show();
  		        	lxquewin.center();
					lxReadfn(partnumber); 
				}else if(name=='edit'){
					if(userrole !='管理员'&&userrole !='系统管理员'){Ext.Msg.alert('提示','对不起！您无权进行该操作！');return;}		
					lxmodwin.show();//修改窗口显示
					lxmodwin.center();
					lxReadfn2(partnumber);
  		        }else{return;}	
			}
		}
	});
	
	lxproparastore.load();
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[lxnorthformpanel,lxgridpanel]
	});	
		
	
	function lxfindfn(){
		var findtype=lxnorthformpanel.form.findField('rb').getGroupValue();
		var findstring=lxnorthformpanel.form.findField('findstring').getValue();
		if(findtype==null||findstring=='') { 
			Ext.Msg.alert('提示','请正确设置查找要求！'); 
			return;
		}
		
		var partlxStore=new Ext.data.JsonStore({
			baseParams:{findtype : findtype , findstring : findstring},
			fields:['parttype','material','partnumber','partouterdiam',
								'rotatspeed','coating','pourtemper'],
			url:'/casting/readpartlxinfor.do'
			});	
		
		partlxStore.load();					
		lxgridpanel.reconfigure(partlxStore,lxproparacolumns);		
	}

//弹出窗口界面----------------------------------------------------------------------------	
	//var textfieldwidth='150';js中已有
	var partType={xtype:'textfield',name:'parttype',fieldLabel:'类型',width:textfieldwidth};
	var material={xtype:'textfield',name:'material',fieldLabel:'材料',width:textfieldwidth};
	var partNumber={xtype:'textfield',name:'partnumber',fieldLabel:'编号',width:textfieldwidth};
	var partOuterDiam={xtype:'textfield',name:'partouterdiam',fieldLabel:'零件外径(mm)',width:textfieldwidth};
	var outerSurfMachAllow={xtype:'textfield',name:'outersurfmachallow',fieldLabel:'外表面加工余量(mm)',width:textfieldwidth};
	var innerSurfMachAllow={xtype:'textfield',name:'innersurfmachallow',fieldLabel:'内表面加工余量(mm)',width:textfieldwidth};
	var endMachAllow={xtype:'textfield',name:'endmachallow',fieldLabel:'端面加工余量(mm)',width:textfieldwidth};
	var rotatSpeed={xtype:'textfield',name:'rotatspeed',fieldLabel:'转速(r/min)',width:textfieldwidth};
	var coating={xtype:'textfield',name:'coating',fieldLabel:'涂料',width:textfieldwidth};
	var operatTtemper={xtype:'textfield',name:'operattemper',fieldLabel:'铸型工作温度(℃)',width:textfieldwidth};
	var pourTemper={xtype:'textfield',name:'pourtemper',fieldLabel:'浇注温度(℃)',width:textfieldwidth};
	var releaseTemper={xtype:'textfield',name:'releasetemper',fieldLabel:'脱模温度(℃)',width:textfieldwidth};
	var moldDryingPro={xtype:'textarea',name:'molddryingpro',fieldLabel:'铸型烘干工艺',width:textfieldwidth};


	var lxformpanel=new Ext.form.FormPanel({
		layout:'column',
		autoScroll:true,
		frame:true,		
		labelWidth:120,
		items:[
			{layout:'form',columnWidth:.5,items:partType},{layout:'form',columnWidth:.5,items:material},
			{layout:'form',columnWidth:.5,items:partNumber},{layout:'form',columnWidth:.5,items:partOuterDiam},
			{layout:'form',columnWidth:.5,items:rotatSpeed},{layout:'form',columnWidth:.5,items:coating},
			{layout:'form',columnWidth:.5,items:innerSurfMachAllow},{layout:'form',columnWidth:.5,items:operatTtemper},
			{layout:'form',columnWidth:.5,items:outerSurfMachAllow},{layout:'form',columnWidth:.5,items:pourTemper},
			{layout:'form',columnWidth:.5,items:endMachAllow},{layout:'form',columnWidth:.5,items:releaseTemper},	
			{layout:'form',columnWidth:1,items:moldDryingPro}
		]
	});
		
	var lxaddwin=new Ext.Window({
		modal:true,
	    width:650,
	    height:310,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"添加离心铸造工艺参数",
	    buttonAlign:"center",
		bodyBorder:true,
		items:addlxformpanel,
		buttons:[{
	        text:'保存',
	        handler:lxaddFn
	    	}]
	});

	var lxmodwin=new Ext.Window({
		modal:true,
	    width:650,
	    height:310,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"修改离心铸造工艺参数",
	    buttonAlign:"center",
		bodyBorder:true,
		items:modlxformpanel,
		buttons:[{
	        text:'保存',
	        handler:lxmodFn
	    	}]
	});
		
	function lxaddFn(){
	addlxformpanel.form.doAction('submit',{
		url:'/casting/addlxpropara.do',
		method:'post',
		success:function(form,action){
			lxaddwin.hide();
			lxproparastore.load();
	    	lxgridpanel.reconfigure(lxgridpanel.getStore(),lxproparacolumns);
	    	Ext.Msg.alert('提示','保存成功！');
	 	},
		 //提交失败的回调函数
	 	failure:function(){
	 		Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
	 	}
	});
	}

	function lxmodFn(){
	modlxformpanel.form.doAction('submit',{
		url:'/casting/modlxpropara.do',
		method:'post',
		success:function(form,action){
			lxmodwin.hide();
			lxproparastore.load();
	    	lxgridpanel.reconfigure(lxgridpanel.getStore(),lxproparacolumns);
	    	Ext.Msg.alert('提示','保存成功！');
	 	},
		 //提交失败的回调函数
	 	failure:function(){
	 		Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
	 	}
	});
	}
	
	var lxquewin=new Ext.Window({
		modal:true,
	    width:650,
	    height:310,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"离心铸造工艺参数查询",
	    buttonAlign:"center",
		bodyBorder:true,
		items:lxformpanel
	});
	
//--------------------------------------------------------------------------------	
	
	function addlxwinfn(){
		if(userrole !='管理员'&&userrole !='系统管理员'){Ext.Msg.alert('提示','对不起！您无权进行该操作！');return;}		
		lxaddwin.show();
		lxformpanel.form.reset();
		lxaddwin.center();
	}

	function lxReadfn(gridpartnumber){
		Ext.Ajax.request({
		         url:'/casting/lxreadbypartnumber.do',
		         method : 'post',
		         params:{partnumber:gridpartnumber},//向后端发送json格式‘partnumber=？,后端匹配字段为partnumber’
		         success : function(result,request) {
			         var objtp=Ext.util.JSON.decode(result.responseText);
			         lxformpanel.form.findField('parttype').setValue(objtp.parttype); 
			         lxformpanel.form.findField('material').setValue(objtp.material);
			         lxformpanel.form.findField('partnumber').setValue(objtp.partnumber);
			         lxformpanel.form.findField('partouterdiam').setValue(objtp.partouterdiam);
			         lxformpanel.form.findField('outersurfmachallow').setValue(objtp.outersurfmachallow);
			         lxformpanel.form.findField('innersurfmachallow').setValue(objtp.innersurfmachallow);
			         lxformpanel.form.findField('endmachallow').setValue(objtp.endmachallow); 
			         lxformpanel.form.findField('rotatspeed').setValue(objtp.rotatspeed); 
			         lxformpanel.form.findField('coating').setValue(objtp.coating);
			         lxformpanel.form.findField('operattemper').setValue(objtp.operattemper);
			         lxformpanel.form.findField('pourtemper').setValue(objtp.pourtemper);
			         lxformpanel.form.findField('releasetemper').setValue(objtp.releasetemper);
			         lxformpanel.form.findField('molddryingpro').setValue(objtp.molddryingpro);
		         },
		         failure : function() {
		          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
		         }
		        });
	}
	
});

</script>

</head>
<body>

</body>
</html>