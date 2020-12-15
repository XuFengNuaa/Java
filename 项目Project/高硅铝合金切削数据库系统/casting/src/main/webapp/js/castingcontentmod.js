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
		            iconCls:"remove"
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