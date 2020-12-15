<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>铸造工艺信息</title>
<link rel="stylesheet" type="text/css" href="/casting/extjs/resources/css/ext-all.css"/></link>
<link rel="stylesheet" type="text/css" href="/casting/extjs/add.css" />
<script type="text/javascript" src="/casting/extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="/casting/extjs/ext-all.js"></script>
<script type="text/javascript" src="/casting/extjs/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	Ext.onReady(function(){	
		var userrole = '<%=(String)session.getAttribute("Userrole")%>';
		if(userrole !='管理员'&&userrole !='系统管理员'){alert('对不起！您无权访问此页。');return;}	
		//定义字段--------------------
		var textfieldWidth='250';  var textareaWidth='250';		
		var castingNumber={xtype:'textfield',fieldLabel:'编号',name:'castingnumber',width:textfieldWidth};		
		var partsDrawing={xtype: 'textfield',fieldLabel:'零件图',name:'partsdrawing',id:'partsdrawing',
			inputType:'file',blankText:'选择上传零件图',width:240,height:22};					
		var partsNumber={xtype:'textfield',fieldLabel:'零件号',name:'partsnumber',width:textfieldWidth};		
		var structureType={xtype:'textfield',fieldLabel:'结构类型',name:'structuretype',width:textfieldWidth};
		var pouringWeight={xtype:'textfield',fieldLabel:'浇注重量(kg)',name:'pouringweight',width:textfieldWidth};
		var castingMethod={xtype:'textarea',fieldLabel:'铸造方法',name:'castingmethod',width:textareaWidth};		
		var castingProcessDesign={xtype:'textarea',fieldLabel:'铸造工艺设计',name:'castingprocessdesign',width:textareaWidth};
		var riserDesign={xtype:'textarea',fieldLabel:'冒口设计',name:'riserdesign',width:textareaWidth};
		var pouringTemperature={xtype:'textfield',fieldLabel:'浇注温度(℃)',name:'pouringtemperature',width:textfieldWidth};					
		var boxTime={xtype:'textfield',fieldLabel:'打箱时间(s)',name:'boxtime',width:textfieldWidth};
		var heatProcess={xtype:'textarea',fieldLabel:'热处理工艺',name:'heatprocess',width:textareaWidth};
		var smeltingProcess={xtype:'textarea',fieldLabel:'合金熔炼工艺',name:'smeltingprocess',width:textareaWidth};		
		var inoculationProcess={xtype:'textarea',fieldLabel:'孕育处理工艺',name:'inoculationprocess',width:textareaWidth};
		//var processFeasibility={xtype:'textfield',fieldLabel:'工艺可行性分析报告',name:'processfeasibility',width:textfieldWidth};
		var qualityTest={xtype:'textfield',fieldLabel:'铸件质量检测结果',name:'qualitytest',width:textfieldWidth};
			
		var castingName={xtype:'textfield',fieldLabel:'零件名称',name:'castingname',id:'castingname',width:textfieldWidth};
		var materialNumber={xtype:'textfield',fieldLabel:'材料牌号',name:'materialnumber',width:textfieldWidth};
		var mainThickness={xtype:'textfield',fieldLabel:'主要壁厚(mm)',name:'mainthickness',width:textfieldWidth};		
		var castingWeight={xtype:'textfield',fieldLabel:'铸件重量(kg)',name:'castingweight',width:textfieldWidth};
		var acceptCondition={xtype:'textarea',fieldLabel:'铸件验收条件',name:'acceptcondition',width:textareaWidth};		
		var gatingSystemDesign={xtype:'textarea',fieldLabel:'浇注系统设计',name:'gatingsystemdesign',width:textareaWidth};
		var chillDesign={xtype:'textarea',fieldLabel:'冷铁设计',name:'chilldesign',width:textareaWidth};
		var pouringTime={xtype:'textfield',fieldLabel:'浇注时间(s)',name:'pouringtime',width:textfieldWidth};		
		var chemicalComponent={xtype:'textarea',fieldLabel:'化学成分',name:'chemicalcomponent',width:textareaWidth};
		var physicalTreatment={xtype:'textarea',fieldLabel:'球化或蠕化处理工艺',name:'physicaltreatment',width:textareaWidth};
		var physicalParameter={xtype:'textarea',fieldLabel:'材料热物性参数',name:'physicalparameter',width:textareaWidth};		
		//var processOptimization={xtype:'textfield',fieldLabel:'铸造工艺优化报告',name:'processoptimization',width:textfieldWidth};
		var defectType={xtype:'textfield',fieldLabel:'缺陷类型',name:'defecttype',width:textfieldWidth};
		var editor={xtype:'textfield',fieldLabel:'编辑人',name:'editor',width:textfieldWidth};
		
		//定义布局、工具栏------------------------------
		
		var partsDrawingForm = new Ext.form.FormPanel({
		     fileUpload: true,
		     layout:'form',
		     region:'center',
		     frame:true,
		     items:partsDrawing
	    });
		
		var tbar={
				xtype:'toolbar',
				style:'margin-bottom:10px',
				items:['-',
						{ 								
				            text:"上传零件图", 
				            handler:partsDrawingFormfn,
				            iconCls:"addFile"
				        },'-'
					]
        	}
		
		var bbar={
				xtype:'toolbar',
				buttonAlign:'right',
				items:['-',{ 										            
				            text:"提交", 
				            handler: castingSubmitfn,
				            iconCls:"wlk_temp"
			            },'-'
					]
        	}
		
		
		var contentForm=new Ext.form.FormPanel({
			//title:'铸造信息',	
			region:'center',
			frame:true,
			layout:'column',
			autoScroll:true,
			tbar:tbar,
			bbar:bbar,
			frame:true,		
			labelWidth:120,
			items:[{layout:'form',columnWidth:.5,items: castingNumber},
						{layout:'form',columnWidth:.5,items: castingName},							
					{layout:'form',columnWidth:.5,items: partsNumber},
						{layout:'form',columnWidth:.5,items: materialNumber},
					{layout:'form',columnWidth:.5,items: defectType},	
						{layout:'form',columnWidth:.5,items: mainThickness},					
					{layout:'form',columnWidth:.5,items: structureType},					
						{layout:'form',columnWidth:.5,items: castingWeight},					
					{layout:'form',columnWidth:.5,items: pouringWeight},					
						{layout:'form',columnWidth:.5,items: pouringTemperature},						
					{layout:'form',columnWidth:.5,items: castingMethod},					
						{layout:'form',columnWidth:.5,items: acceptCondition},					
					{layout:'form',columnWidth:.5,items: castingProcessDesign},					
						{layout:'form',columnWidth:.5,items: gatingSystemDesign},					
					{layout:'form',columnWidth:.5,items: riserDesign},				
						{layout:'form',columnWidth:.5,items: chillDesign},					
					{layout:'form',columnWidth:.5,items: boxTime},
						{layout:'form',columnWidth:.5,items: pouringTime},
					{layout:'form',columnWidth:.5,items: smeltingProcess},
						{layout:'form',columnWidth:.5,items: chemicalComponent},
					{layout:'form',columnWidth:.5,items: inoculationProcess},
						{layout:'form',columnWidth:.5,items: physicalTreatment},
					{layout:'form',columnWidth:.5,items: heatProcess},
						{layout:'form',columnWidth:.5,items: physicalParameter},
					//{layout:'form',columnWidth:.5,items: processFeasibility},	
						//{layout:'form',columnWidth:.5,items: processOptimization},
					{layout:'form',columnWidth:.5,items: qualityTest},				
						{layout:'form',columnWidth:.5,items: editor},
			]
		});
		
		//定义试图-------------------------------------
		new Ext.Viewport({
			layout:'border',
			items:contentForm
		});	
			
		
		var partsDrawingwin = new Ext.Window({
			modal:true,
		    width:375,
		    height:110,
		    closeAction:'hide',
		    plain: true,
		    layout:'fit',
		    title:"上传零件图",
		    buttonAlign:"center",
			bodyBorder:true,
		    items:partsDrawingForm,
		    buttons:[{
			            text:'确认',
			            handler:function(){
			            	Ext.MessageBox.show({
						           title : '请等待',
						           msg : '文件正在上传...',
						           progressText : '',
						           width : 300,
						           progress : true,
						           closable : false,
						           animEl : 'loding'
						          });
						   	partsDrawingForm.form.doAction('submit',{
						         url:'/casting/uploaddocument.do',
						         method : 'post',
						         success : function(form,action) {
						          Ext.Msg.alert('Success','零件图上传成功！');
						         },
						         failure : function(form,action) {
						        	
						          Ext.Msg.alert('Failure','零件图上传失败！');
						         }
						        });
						   	
			            	partsDrawingwin.hide();
			            }
		            }]		
		});
		
		function partsDrawingFormfn(){
			if(!partsDrawingwin.isVisible()){
				partsDrawingwin.show();//修改窗口显示
				partsDrawingwin.center();
    		}
					
		}	
			
		function castingSubmitfn(){
			    //var partsdrawingpath=Ext.getCmp('partsdrawing').getValue();		   			 
			   	contentForm.form.doAction('submit',{
			         url:'/casting/castingsubmit.do',
			         method : 'post',
			         //params : {partsdrawing:partsdrawingpath},
			         success : function(form,action) {
			          Ext.Msg.alert('Success','表单上传成功！');
			          contentForm.form.reset();
			          partsDrawingForm.form.reset();
			         },
			         failure : function() {
			          Ext.Msg.alert('Failure','表单上传失败！');
			         }
			        });			   	
		}			
	})
		
</script>
</head>
<body>

</body>
</html>