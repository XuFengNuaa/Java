<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/casting/extjs/resources/css/ext-all.css"/></link>
<link rel="stylesheet" type="text/css" href="/casting/extjs/add.css" />
<script type="text/javascript" src="/casting/extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="/casting/extjs/ext-all.js"></script>
<script type="text/javascript" src="/casting/extjs/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="/casting/js/addmaterialproperies.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	var userrole = '<%=(String)session.getAttribute("Userrole")%>';
	
	var textfieldWidth='200';
	var materialCategory={xtype:'textfield',fieldLabel:'类别',name:'materialcategory',width:textfieldWidth};									
	var materialBrand={xtype:'textfield',fieldLabel:'牌号',name:'materialbrand',width:textfieldWidth,style:"background:#C1C1C1;",readOnly:true};	
	var tensileStrength={xtype:'textfield',fieldLabel:'抗拉强度Rm/MPa',name:'tensilestrength',width:textfieldWidth};									
	var extensionStrength={xtype:'textfield',fieldLabel:'塑性延伸强度Rp0.2/MPa(≥)',name:'extensionstrength',width:textfieldWidth};
	var elongation={xtype:'textfield',fieldLabel:'伸长率A(%)(≥)',name:'elongation',width:textfieldWidth};									
	var hardness={xtype:'textfield',fieldLabel:'硬度(HBW)',name:'hardness',width:textfieldWidth};
	var mainMatrixOrganization={xtype:'textfield',fieldLabel:'主要基体组织',name:'mainmatrixorganization',width:textfieldWidth};

	var distributionRatio={xtype:'textarea',fieldLabel:'成分配比',name:'distributionratio',width:textfieldWidth};
	var melting={xtype:'textarea',fieldLabel:'熔炼',name:'melting',width:textfieldWidth};
	var inoculation={xtype:'textarea',fieldLabel:'孕育处理',name:'inoculation',width:textfieldWidth};
	var spheroidizing={xtype:'textarea',fieldLabel:'球化处理',name:'spheroidizing',width:textfieldWidth};
	var annealing={xtype:'textarea',fieldLabel:'退火',name:'annealing',width:textfieldWidth};
	
	
	var mpContentReadformPanel=new Ext.form.FormPanel({	
		layout:'column',
		autoScroll:true,
		frame:true,		
		labelWidth:120,
		items:[
			{layout:'form',columnWidth:.5,items:materialBrand},{layout:'form',columnWidth:.5,items:materialCategory},
			{layout:'form',columnWidth:.5,items:mainMatrixOrganization},{layout:'form',columnWidth:.5,items:hardness},	
			{layout:'form',columnWidth:.5,items:tensileStrength},{layout:'form',columnWidth:.5,items:elongation},
			{layout:'form',columnWidth:.5,items:extensionStrength},{layout:'form',columnWidth:.5,items:distributionRatio},
			{layout:'form',columnWidth:.5,items:melting},{layout:'form',columnWidth:.5,items:inoculation},
			{layout:'form',columnWidth:.5,items:spheroidizing},{layout:'form',columnWidth:.5,items:annealing}	
			]
	});

	function mpContentReadfn(gridmaterialbrand){
		Ext.Ajax.request({
		         url:'/casting/mpcontentread.do',
		         method : 'post',
		         params:{materialbrand:gridmaterialbrand},//向后端发送json格式‘castingnumber=？’
		         success : function(result,request) {
		         var objtp=Ext.util.JSON.decode(result.responseText);		          
		         mpContentReadformPanel.form.findField('materialcategory').setValue(objtp.materialcategory); 
		         mpContentReadformPanel.form.findField('materialbrand').setValue(objtp.materialbrand);
		         mpContentReadformPanel.form.findField('tensilestrength').setValue(objtp.tensilestrength);
		         mpContentReadformPanel.form.findField('extensionstrength').setValue(objtp.extensionstrength);
		         mpContentReadformPanel.form.findField('elongation').setValue(objtp.elongation);
		         mpContentReadformPanel.form.findField('hardness').setValue(objtp.hardness);
		         mpContentReadformPanel.form.findField('mainmatrixorganization').setValue(objtp.mainmatrixorganization); 
		         
		         mpContentReadformPanel.form.findField('distributionratio').setValue(objtp.distributionratio); 
		         mpContentReadformPanel.form.findField('melting').setValue(objtp.melting);
		         mpContentReadformPanel.form.findField('inoculation').setValue(objtp.inoculation);
		         mpContentReadformPanel.form.findField('spheroidizing').setValue(objtp.spheroidizing);
		         mpContentReadformPanel.form.findField('annealing').setValue(objtp.annealing);
		         },
		         failure : function() {
		          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
		         }
		        });
	}


	//------------------------------------------------------------------------------
	var mpContentWritewin = new Ext.Window({
		modal:true,
	    width:750,
	    height:370,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"添加材料属性",
	    buttonAlign:"center",
		bodyBorder:true,
		items:addmpContentReadformPanel,
		buttons:[{
	        text:'保存',
	        handler:mpwritesaveFn
	    	}]
	});	
	

	function mpwritesaveFn(){			
		addmpContentReadformPanel.form.doAction('submit',{
		 	url:'/casting/addmaterialproperties.do',
		 	method:'post',
			success:function(form,action){
				mpContentWritewin.hide();
				Ext.Msg.alert('提示','保存成功！');
				mpinforgridstore.load({params:{start:0,limit:22}});
				mpinforgridpanel.reconfigure(mpinforgridstore,mpinforgridcolumns);		
				mpinforgridpaginTB.bind(mpinforgridstore);
		 	},
			 //提交失败的回调函数
		 	failure:function(){
		 		Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
		 	}
		});
	}
	
	var mpContentModwin = new Ext.Window({
		modal:true,
	    width:750,
	    height:370,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"修改材料属性",
	    buttonAlign:"center",
		bodyBorder:true,
		items:mpContentReadformPanel,
		buttons:[{
	        text:'保存',
	        handler:mpsaveFn
	    	}]
	});	
	
	function mpsaveFn(){			
		mpContentReadformPanel.form.doAction('submit',{
		 	url:'/casting/modmaterialproperties.do',
		 	method:'post',
			success:function(form,action){
				mpContentModwin.hide();
				Ext.Msg.alert('提示','保存成功！');
				mpinforgridpanel.getStore().load({params:{start:0,limit:22}});
				mpinforgridpanel.reconfigure(mpinforgridpanel.getStore(),mpinforgridcolumns);		
				mpinforgridpaginTB.bind(mpinforgridpanel.getStore());
				
		 	},
			 //提交失败的回调函数
		 	failure:function(){
		 		Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
		 	}
		});
	}
	
	var mpinforgridcolumns=new Ext.grid.ColumnModel({
		columns:[
			new Ext.grid.RowNumberer({header:"序号",width:35,align:'center'}),
			{header:'类别',dataIndex:'materialcategory',width:100,align:'center'},
			{header:'牌号',dataIndex:'materialbrand',width:120,align:'center'},
			{header:'抗拉强度Rm/MPa',dataIndex:'tensilestrength',width:150,align:'center'},
			{header:'塑性延伸强度Rp0.2/MPa(≥)',dataIndex:'extensionstrength',width:220,align:'center'},			
			{header:'伸长率A(%)(≥)',dataIndex:'elongation',width:150,align:'center'},
			{header:'硬度(HBW)',dataIndex:'hardness',width:150,align:'center'},
			{header:'主要基体组织',dataIndex:'mainmatrixorganization',width:150,align:'center'},
			{dataIndex:'mpque',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>查询</a>";}},
			{dataIndex:'mpmod',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>修改</a>";}},
			{dataIndex:'mpdel',width:80,align:'center',
				renderer:function(value, metaData, record){return "<a href='#'>删除</a>";}}
			]
	});
	
	var mpinforgridstore=new Ext.data.JsonStore({
		fields:['materialcategory','materialbrand','tensilestrength','extensionstrength',
			'elongation','hardness','mainmatrixorganization'],//变量名，数据名，数据库字段名要对应，顺序可以不同
		url:'/casting/readallmaterialproperties.do',
		totalProperty : 'totalcount',
	    root: 'data'
	});
	
	var mpinforgridpaginTB=new Ext.PagingToolbar({	
		store:mpinforgridstore,
		pageSize:22,
		displayInfo:true,
		displayMsg:'记录：第{0}条-第{1}条，共{2}条',
		emptyMsg:'没有数据'
	})
	
	var mpinforgridtbarformpanel=new Ext.form.FormPanel({
		layout:'column',
		region:'north',
		height:35,
		frame:true,	
		items:[
			{columnWidth:.3,layout:'column',items:[
				{layout:'form',columnWidth:.8,xtype:'textfield',emptyText:'请输入关键字...',name:'findstring'},
				{layout:'form',columnWidth:.2,xtype:'button',text:'查询',scope:this,handler:mpfindfn}
			]},{
				layout:'form',columnWidth:.4,
				items:{layout:'form',xtype:'radiogroup',
					items:[
						{boxLabel:'类别',name:'rb',inputValue:'materialcategory'},
						{boxLabel:'牌号',name:'rb',inputValue:'materialbrand'}
						
					]}
			}
		]			
	});
	
	var mpinforgridtbar=new Ext.Toolbar({ 
		items:['-',
			{ 								
	            text:"增加", 
	            handler:addmaterialpropertiesfn,
	            iconCls:"add"
	        }]
	}) 
	
	var mpinforgridpanel=new Ext.grid.EditorGridPanel({
		region:'center',
		loadMask:true,
		frame:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,
		store:mpinforgridstore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:mpinforgridcolumns,
		tbar: mpinforgridtbar,
		bbar: mpinforgridpaginTB,
		listeners:{
			cellclick: function(grid,rowIndex,columnIndex){
				var name = grid.getColumnModel().getDataIndex(columnIndex);
				var gridmaterialbrand = grid.getStore().getAt(rowIndex).get('materialbrand');
				if(name=='mpmod'){
					if(userrole !='管理员'&&userrole !='系统管理员'){Ext.Msg.alert('提示','对不起！您无权进行修改操作！');return;}
						mpContentModwin.show();//修改窗口显示
						mpContentModwin.center();
						mpContentReadfn(gridmaterialbrand);	
	    		}else if(name=='mpdel'){
	    			if(userrole !='管理员'&&userrole !='系统管理员'){Ext.Msg.alert('提示','对不起！您无权进行删除操作！');return;}
		    			Ext.Ajax.request({
		    		         url:'/casting/mpdel.do',
		    		         method : 'post',
		    		         params:{materialbrand:gridmaterialbrand},
		    		         success : function() {
		    		        	 Ext.Msg.alert('success','删除成功！');
		    		        	 mpinforgridpanel.getStore().load({params:{start:0,limit:22}});
		    					 mpinforgridpanel.reconfigure(mpinforgridpanel.getStore(),mpinforgridcolumns);		
		    					 mpinforgridpaginTB.bind(mpinforgridpanel.getStore());
		    		         },
		    		         failure : function() {
		    			          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
		    		         }
		    		         });
    			}else if(name=='mpque'){
    				mpContentReadwin.show();//修改窗口显示
    				mpContentReadwin.center();
					mpContentReadfn2(gridmaterialbrand);
    			}else{return;}
				
			}
		}
	});
	
	mpinforgridstore.load({params:{start:0,limit:22}});
	
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[mpinforgridtbarformpanel,mpinforgridpanel]
	});	
	
	function addmaterialpropertiesfn(){
		if(userrole !='管理员'&&userrole !='系统管理员'){Ext.Msg.alert('提示','对不起！您无权进行删除操作！');return;}		
		if(!mpContentWritewin.isVisible()){
			mpContentReadformPanel.form.reset();
			mpContentWritewin.show();
			mpContentWritewin.center();
		}
	}
	
	function mpfindfn(){
		var findtype=mpinforgridtbarformpanel.form.findField('rb').getGroupValue();
		var findstring=mpinforgridtbarformpanel.form.findField('findstring').getValue();
		if(findtype==null||findstring=='') { Ext.Msg.alert('提示','请正确设置查找要求！'); return;}
		
		var partmpcontentStore=new Ext.data.JsonStore({
			baseParams:{findtype : findtype , findstring : findstring},
			fields:['materialcategory','materialbrand','tensilestrength','extensionstrength',
					'elongation','hardness','mainmatrixorganization'],
			url:'/casting/readpartmpcontent.do',
			totalProperty : 'totalcount',
		    root: 'data'
			});	
		
		partmpcontentStore.load({params:{start:0,limit:22}});					
		mpinforgridpanel.reconfigure(partmpcontentStore,mpinforgridcolumns);		
		mpinforgridpaginTB.bind(partmpcontentStore);	
	}
		
});
</script>
</head>
<body>

</body>
</html>