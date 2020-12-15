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
<script type="text/javascript" src="/casting/js/castingcontentread.js"></script>
<script type="text/javascript">
Ext.onReady(function(){	
	var userrole = '<%=(String)session.getAttribute("Userrole")%>';
		
	var sm = new Ext.grid.CheckboxSelectionModel();
	var castinginforgridcolumns=new Ext.grid.ColumnModel({
		columns:[sm,
			new Ext.grid.RowNumberer({header:"序号",width:40,align:'center'}),
			{header:'编号',dataIndex:'castingnumber',width:180},
			{header:'零件号',dataIndex:'partsnumber',width:180},
			{header:'结构类型',dataIndex:'structuretype',width:180},
			{header:'零件名称',dataIndex:'castingname',width:180},
			{header:'明细',dataIndex:'load',width:80,
				renderer:function(value, metaData, record){return "<a href='#'>查看</a>";}}
			]
	});
		
	var castinginforgridstore=new Ext.data.JsonStore({
		fields:['castingnumber','partsnumber','structuretype','castingname'],//变量名，数据名，数据库字段名要对应，顺序可以不同
		url:'/casting/readallcastingcontent.do',
		totalProperty : 'totalcount',
	    root: 'data'
	});	
	

	var castinginforgridpaginTB=new Ext.PagingToolbar({	
		store:castinginforgridstore,
		pageSize:22,
		displayInfo:true,
		displayMsg:'记录：第{0}条-第{1}条，共{2}条',
		emptyMsg:'没有数据'
	})
	
	var castinginforgridtbarformpanel=new Ext.form.FormPanel({
		layout:'column',
		region:'north',
		height:35,
		frame:true,	
		items:[
			{columnWidth:.3,layout:'column',items:[
				{layout:'form',columnWidth:.8,xtype:'textfield',emptyText:'请输入关键字...',name:'findstring'},
				{layout:'form',columnWidth:.2,xtype:'button',text:'查询',scope:this,handler:findfn}
			]},{
				layout:'form',columnWidth:.4,
				items:{layout:'form',xtype:'radiogroup',
					items:[
						{boxLabel:'编号',name:'rb',inputValue:'castingnumber'},
						{boxLabel:'零件号',name:'rb',inputValue:'partsnumber'},
						{boxLabel:'结构类型',name:'rb',inputValue:'structuretype'},
						{boxLabel:'零件名称',name:'rb',inputValue:'castingname'}
					]}
			}
		]			
	});
	
	
	var castinginforgridtbar=new Ext.Toolbar({ 
		items:['-',
			{ 								
	            text:"修改", 
	            handler:modifyfn,
	            iconCls:"update"
	        },'-',{ 								
	            text:"删除", 
	            handler: deletefn,
	            iconCls:"delete"
            },'-']
	}) 
	
	
	var castinginforgridpanel=new Ext.grid.EditorGridPanel({
		region:'center',
		loadMask:true,
		frame:true,
		autoScroll:true,
	    stripeRows:true,
	    enableColumnMove:false,
	    sm:sm,
		store:castinginforgridstore,
		viewConfig:{
	    	forceFit: true,//列宽度自适应
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:castinginforgridcolumns,
		tbar: castinginforgridtbar,
		bbar: castinginforgridpaginTB,
		listeners:{
			cellclick: function(grid,rowIndex,columnIndex){
				var name = grid.getColumnModel().getDataIndex(columnIndex);
				if(name=='load'){
					var gridcastingnumber = grid.getStore().getAt(rowIndex).get('castingnumber');
					if(!castingContentReadwin.isVisible()){
						castingContentReadwin.show();//修改窗口显示
						castingContentReadwin.center();
						castingContentReadfn(gridcastingnumber);
		    		}
						
					}else{return;}
			}
		}
	});
	
	castinginforgridstore.load({params:{start:0,limit:22}});
	
	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[castinginforgridtbarformpanel,castinginforgridpanel]
	});	
	
	
	function findfn(){		
		var findtype=castinginforgridtbarformpanel.form.findField('rb').getGroupValue();
		var findstring=castinginforgridtbarformpanel.form.findField('findstring').getValue();
		if(findtype==null||findstring=='') { alert('请正确设置查找要求！'); return;}
		
		var partcastingcontentStore=new Ext.data.JsonStore({
			baseParams:{findtype : findtype , findstring : findstring},
			fields:['castingnumber','partsnumber','structuretype','castingname'],
			url:'/casting/readpartcastingcontent.do',
			totalProperty : 'totalcount',
		    root: 'data'
			});	
		
		partcastingcontentStore.load({params:{start:0,limit:22}});					
		castinginforgridpanel.reconfigure(partcastingcontentStore,castinginforgridcolumns);		
		castinginforgridpaginTB.bind(partcastingcontentStore);
      }
	
	function deletefn(){	
		if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权进行删除操作！');return;}
		var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
	        alert('提示:请先选择要删除项!');
	        return;
		}
		var castinginforgriddel="";
		if(record.length!=0) {
	       	Ext.MessageBox.confirm('提示','确认删除所选项？',function(btn) {
				if(btn == 'yes') {
					for(var i=0;i<record.length;i++){
						if(i!=0){
							castinginforgriddel+=",";
						}
						castinginforgriddel+="'"+record[i].json.castingnumber+"'";
					}
		   			Ext.Ajax.request({
					   	url: '/casting/castinginforgriddel.do',
					   	params: {castinginforgriddel:castinginforgriddel},
					   	success:function(result){
							if (result.responseText.indexOf("success") != -1){								
								alert('提示:所选项已删除成功！');
								castinginforgridstore.load({params:{start:0,limit:22}});					
								castinginforgridpanel.reconfigure(castinginforgridstore,castinginforgridcolumns);		
								castinginforgridpaginTB.bind(castinginforgridstore);								
							}
					 	},
					   	failure: function(){
							alert('提示:服务器出现错误请稍后再试！');				   
					   	}
					});	
	            }
			});
	   	}		
	}
	
	//castingcontentmod.js-----------------------------
	
	//定义字段--------------------
	var textfieldWidth2='250';  var textareaWidth2='250';		
	var castingNumber2={xtype:'textfield',fieldLabel:'编号',name:'castingnumber2',width:textfieldWidth2,readOnly: true};		
	var partsDrawing2={xtype: 'textfield',fieldLabel:'零件图',name:'partsdrawing2',
			inputType:'file',blankText:'选择上传零件图',width:240,height:22};					
	var partsNumber2={xtype:'textfield',fieldLabel:'零件号',name:'partsnumber2',width:textfieldWidth2};		
	var structureType2={xtype:'textfield',fieldLabel:'结构类型',name:'structuretype2',width:textfieldWidth2};
	var pouringWeight2={xtype:'textfield',fieldLabel:'浇注重量(kg)',name:'pouringweight2',width:textfieldWidth2};
	var castingMethod2={xtype:'textarea',fieldLabel:'铸造方法',name:'castingmethod2',width:textareaWidth2};		
	var castingProcessDesign2={xtype:'textarea',fieldLabel:'铸造工艺设计',name:'castingprocessdesign2',width:textareaWidth2};
	var riserDesign2={xtype:'textarea',fieldLabel:'冒口设计',name:'riserdesign2',width:textareaWidth2};
	var pouringTemperature2={xtype:'textfield',fieldLabel:'浇注温度(℃)',name:'pouringtemperature2',width:textfieldWidth2};					
	var boxTime2={xtype:'textfield',fieldLabel:'打箱时间(s)',name:'boxtime2',width:textfieldWidth2};
	var heatProcess2={xtype:'textarea',fieldLabel:'热处理工艺',name:'heatprocess2',width:textareaWidth2};
	var smeltingProcess2={xtype:'textarea',fieldLabel:'合金熔炼工艺',name:'smeltingprocess2',width:textareaWidth2};		
	var inoculationProcess2={xtype:'textarea',fieldLabel:'孕育处理工艺',name:'inoculationprocess2',width:textareaWidth2};
	//var processFeasibility2={xtype:'textfield',fieldLabel:'工艺可行性分析报告',name:'processfeasibility2',width:textfieldWidth2};
	var qualityTest2={xtype:'textfield',fieldLabel:'铸件质量检测结果',name:'qualitytest2',width:textfieldWidth2};
		
	var castingName2={xtype:'textfield',fieldLabel:'零件名称',name:'castingname2',width:textfieldWidth2};
	var materialNumber2={xtype:'textfield',fieldLabel:'材料牌号',name:'materialnumber2',width:textfieldWidth2};
	var mainThickness2={xtype:'textfield',fieldLabel:'主要壁厚(mm)',name:'mainthickness2',width:textfieldWidth2};		
	var castingWeight2={xtype:'textfield',fieldLabel:'铸件重量(kg)',name:'castingweight2',width:textfieldWidth2};
	var acceptCondition2={xtype:'textarea',fieldLabel:'铸件验收条件',name:'acceptcondition2',width:textareaWidth2};		
	var gatingSystemDesign2={xtype:'textarea',fieldLabel:'浇注系统设计',name:'gatingsystemdesign2',width:textareaWidth2};
	var chillDesign2={xtype:'textarea',fieldLabel:'冷铁设计',name:'chilldesign2',width:textareaWidth2};
	var pouringTime2={xtype:'textfield',fieldLabel:'浇注时间(s)',name:'pouringtime2',width:textfieldWidth2};		
	var chemicalComponent2={xtype:'textarea',fieldLabel:'化学成分',name:'chemicalcomponent2',width:textareaWidth2};
	var physicalTreatment2={xtype:'textarea',fieldLabel:'球化或蠕化处理工艺',name:'physicaltreatment2',width:textareaWidth2};
	var physicalParameter2={xtype:'textarea',fieldLabel:'材料热物性参数',name:'physicalparameter2',width:textareaWidth2};		
	//var processOptimization2={xtype:'textfield',fieldLabel:'铸造工艺优化报告',name:'processoptimization2',width:textfieldWidth2};
	var defectType2={xtype:'textfield',fieldLabel:'缺陷类型',name:'defecttype2',width:textfieldWidth2};
	var editor2={xtype:'textfield',fieldLabel:'编辑人',name:'editor2',width:textfieldWidth2};

	//定义布局、工具栏------------------------------

	var partsDrawingForm2 = new Ext.form.FormPanel({
	     fileUpload: true,
	     layout:'form',
	     region:'center',
	     frame:true,
	     items:partsDrawing2
	});

	var tbar2={
			xtype:'toolbar',
			items:['-',
				{ 								
		            text:"上传零件图", 
		            handler:partsDrawingFormfn2,
		            iconCls:"update"
		        },'-'
				]
		}

	var bbar2={
			xtype:'toolbar',
			buttonAlign:'right',
			items:['-',{ 										            
			            text:"提交", 
			            handler: castingSubmitfn2,
			            iconCls:"delete"
		            },'-'
				]
		}

	var contentForm2=new Ext.form.FormPanel({
		//title:'铸造信息',	
		region:'center',
		frame:true,
		layout:'column',
		autoScroll:true,
		frame:true,		
		labelWidth:120,
		items:[{layout:'form',columnWidth:.5,items: castingNumber2},
					{layout:'form',columnWidth:.5,items: castingName2},							
				{layout:'form',columnWidth:.5,items: partsNumber2},
					{layout:'form',columnWidth:.5,items: materialNumber2},
				{layout:'form',columnWidth:.5,items: defectType2},	
					{layout:'form',columnWidth:.5,items: mainThickness2},					
				{layout:'form',columnWidth:.5,items: structureType2},					
					{layout:'form',columnWidth:.5,items: castingWeight2},					
				{layout:'form',columnWidth:.5,items: pouringWeight2},					
					{layout:'form',columnWidth:.5,items: pouringTemperature2},						
				{layout:'form',columnWidth:.5,items: castingMethod2},					
					{layout:'form',columnWidth:.5,items: acceptCondition2},					
				{layout:'form',columnWidth:.5,items: castingProcessDesign2},					
					{layout:'form',columnWidth:.5,items: gatingSystemDesign2},					
				{layout:'form',columnWidth:.5,items: riserDesign2},				
					{layout:'form',columnWidth:.5,items: chillDesign2},					
				{layout:'form',columnWidth:.5,items: boxTime2},
					{layout:'form',columnWidth:.5,items: pouringTime2},
				{layout:'form',columnWidth:.5,items: smeltingProcess2},
					{layout:'form',columnWidth:.5,items: chemicalComponent2},
				{layout:'form',columnWidth:.5,items: inoculationProcess2},
					{layout:'form',columnWidth:.5,items: physicalTreatment2},
				{layout:'form',columnWidth:.5,items: heatProcess2},
					{layout:'form',columnWidth:.5,items: physicalParameter2},
				//{layout:'form',columnWidth:.5,items: processFeasibility2},	
					//{layout:'form',columnWidth:.5,items: processOptimization2},
				{layout:'form',columnWidth:.5,items: qualityTest2},				
					{layout:'form',columnWidth:.5,items: editor2},
			]
	});

	//定义试图-------------------------------------
		
	var castingContentModwin = new Ext.Window({
		modal:true,
	    width:860,
	    height:600,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"铸件信息",
		bodyBorder:true,
		tbar:tbar2,
		bbar:bbar2,
		items:contentForm2
	});	

	var partsDrawingwin2 = new Ext.Window({
			modal:true,
		    width:375,
		    height:110,
		    closeAction:'hide',
			plain: true,
			layout:'fit',
			title:"上传零件图",
			buttonAlign:"center",
			bodyBorder:true,
			items:partsDrawingForm2,
			buttons:[{
			            text:'确认',
			            id:'xiugai',
			            handler:function(){
			            	
						   	partsDrawingForm2.form.doAction('submit',{
						         url:'/casting/moddocument.do',
						         method : 'post',
						         success : function(form,action) {
						        	 Ext.Msg.alert('Success','零件图上传成功！');
						         },
						         
						         failure : function(form,action) {
						        	 Ext.Msg.alert('Failure','零件图上传失败！');
						         }
					        });
				            	partsDrawingwin2.hide();
				            }
		            }]		
	});

	function partsDrawingFormfn2(){
			if(!partsDrawingwin2.isVisible()){
				partsDrawingwin2.show();//修改窗口显示
				partsDrawingwin2.center();
			}
				
	}	
		
	function castingSubmitfn2(){   			 
			contentForm2.form.doAction('submit',{
			url:'/casting/castingcontentmod.do',
			method : 'post',
			success : function(form,action) {
			Ext.Msg.alert('Success','表单上传成功！');
			castingContentModwin.hide();
			},
			failure : function() {
			Ext.Msg.alert('Failure','表单上传失败！');
			}
			});			   	
	}			
		
	function castingContentReadfn2(gridcastingnumber){
		Ext.Ajax.request({
		         url:'/casting/castingcontentread.do',
		         method : 'post',
		         params:{castingnumber:gridcastingnumber},//向后端发送json格式‘castingnumber=？’
		         success : function(result,request) {
		         var objtp=Ext.util.JSON.decode(result.responseText);		          
		         contentForm2.form.findField('castingnumber2').setValue(objtp.castingnumber); 
		         contentForm2.form.findField('partsnumber2').setValue(objtp.partsnumber);
		         contentForm2.form.findField('structuretype2').setValue(objtp.structuretype);
		         contentForm2.form.findField('castingname2').setValue(objtp.castingname);
		         contentForm2.form.findField('editor2').setValue(objtp.editor);
		         contentForm2.form.findField('defecttype2').setValue(objtp.defecttype);
		         contentForm2.form.findField('pouringweight2').setValue(objtp.pouringweight);
		         contentForm2.form.findField('castingmethod2').setValue(objtp.castingmethod);
		         contentForm2.form.findField('castingprocessdesign2').setValue(objtp.castingprocessdesign);
		         contentForm2.form.findField('riserdesign2').setValue(objtp.riserdesign);
		         contentForm2.form.findField('pouringtemperature2').setValue(objtp.pouringtemperature);
		         contentForm2.form.findField('boxtime2').setValue(objtp.boxtime);
		         contentForm2.form.findField('heatprocess2').setValue(objtp.heatprocess);
		         contentForm2.form.findField('smeltingprocess2').setValue(objtp.smeltingprocess);
		         contentForm2.form.findField('inoculationprocess2').setValue(objtp.inoculationprocess);
		         contentForm2.form.findField('qualitytest2').setValue(objtp.qualitytest);
		         contentForm2.form.findField('materialnumber2').setValue(objtp.materialnumber);
		         contentForm2.form.findField('mainthickness2').setValue(objtp.mainthickness);			         
		         contentForm2.form.findField('castingweight2').setValue(objtp.castingweight);
		         contentForm2.form.findField('acceptcondition2').setValue(objtp.acceptcondition);
		         contentForm2.form.findField('gatingsystemdesign2').setValue(objtp.gatingsystemdesign);
		         contentForm2.form.findField('chilldesign2').setValue(objtp.chilldesign);
		         contentForm2.form.findField('pouringtime2').setValue(objtp.pouringtime);
		         contentForm2.form.findField('chemicalcomponent2').setValue(objtp.chemicalcomponent);
		         contentForm2.form.findField('physicaltreatment2').setValue(objtp.physicaltreatment);
		         contentForm2.form.findField('physicalparameter2').setValue(objtp.physicalparameter);	          
		         },
		         failure : function() {
		          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
		         }
		        });
	}
	
	
	
	//---------------------------------------------------
	function modifyfn(){
		if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权进行修改操作！');return;}
		var record =sm.getSelections();// 返回值为 Record 类型          	
    	if(record.length==0){
            alert('提示:请先选择要修改项!');
            return;
        } 
       	if(record.length!=1){
       		alert('提示:每次只能对一个用户进行修改!');
            return;
        }
  		if(!castingContentModwin.isVisible()){
  			castingContentModwin.show();//修改窗口显示
  			castingContentModwin.center();
			var castinginforgridmod=record[0].json.castingnumber;
			castingContentReadfn2(castinginforgridmod);
  		}
  		castinginforgridpanel.getStore().load({params:{start:0,limit:22}});
	}
	
});


</script>
</head>
<body>

</body>
</html>