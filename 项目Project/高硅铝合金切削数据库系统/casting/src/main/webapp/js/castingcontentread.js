//弹出的window界面--------------------------------------------------------------------------
//定义字段---------------------------------------------------------------------------------
var textfieldWidth='250';  var textareaWidth='250';		
var castingNumber={xtype:'textfield',fieldLabel:'编号',name:'castingnumber',width:textfieldWidth};									
var partsNumber={xtype:'textfield',fieldLabel:'零件号',name:'partsnumber',width:textfieldWidth};		
var structureType={xtype:'textfield',fieldLabel:'结构类型',name:'structuretype',width:textfieldWidth};
var pouringWeight={xtype:'textfield',fieldLabel:'浇注重量(kg)',name:'pouringweight',width:textfieldWidth};
var castingMethod={xtype:'textarea',fieldLabel:'铸造方法',name:'castingmethod',width:textareaWidth};		
var castingProcessDesign={xtype:'textarea',fieldLabel:'铸造工艺设计',name:'castingprocessdesign',width:textareaWidth};
var riserDesign={xtype:'textarea',fieldLabel:'冒口设计',name:'riserdesign',width:textareaWidth};
var pouringTemperature={xtype:'textfield',fieldLabel:'浇注温度(℃)',name:'pouringtemperature',width:textfieldWidth};					
var boxTime={xtype:'textfield',fieldLabel:'打箱时间(s)',name:'boxtime',width:textfieldWidth};
var heatProcessDocuments={xtype:'textarea',fieldLabel:'热处理工艺文件',name:'heatprocess',width:textareaWidth};
var smeltingProcess={xtype:'textarea',fieldLabel:'合金熔炼工艺',name:'smeltingprocess',width:textareaWidth};		
var inoculationProcess={xtype:'textarea',fieldLabel:'孕育处理工艺',name:'inoculationprocess',width:textareaWidth};
//var processFeasibility={xtype:'textfield',fieldLabel:'工艺可行性分析报告',name:'processfeasibility',width:textfieldWidth};
var qualityTest={xtype:'textfield',fieldLabel:'铸件质量检测结果',name:'qualitytest',width:textfieldWidth};
	
var castingName={xtype:'textfield',fieldLabel:'零件名称',name:'castingname',width:textfieldWidth};
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


var castingContentReadtbar={
		xtype:'toolbar',
		frame:true,
		border:false,
		items:['-',
			{ 								
	            text:"下载零件图", 
	            handler:partsDrawingDownLoadfn,
	            iconCls:"update"
	        },'-']
	}


//定义布局、工具栏------------------------------							
var castingContentReadformPanel=new Ext.form.FormPanel({
	//title:'铸造信息',	
	layout:'column',
	autoScroll:true,
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
			{layout:'form',columnWidth:.5,items: heatProcessDocuments},
				{layout:'form',columnWidth:.5,items: physicalParameter},
			//{layout:'form',columnWidth:.5,items: processFeasibility},	
				//{layout:'form',columnWidth:.5,items: processOptimization},
			{layout:'form',columnWidth:.5,items: qualityTest},				
				{layout:'form',columnWidth:.5,items: editor},
	]
});

//定义视图-------------------------------------
var castingContentReadwin = new Ext.Window({
	modal:true,
    width:860,
    height:600,
    closeAction:'hide',
    plain: true,
    layout:'fit',
    title:"铸件信息",
	bodyBorder:true,
	tbar:castingContentReadtbar,
	items:castingContentReadformPanel
});	
	
var partsDrawingDownLoadPath;
	
function castingContentReadfn(gridcastingnumber){
	Ext.Ajax.request({
	         url:'/casting/castingcontentread.do',
	         method : 'post',
	         params:{castingnumber:gridcastingnumber},//向后端发送json格式‘castingnumber=？’
	         success : function(result,request) {
	         var objtp=Ext.util.JSON.decode(result.responseText);		          
	         castingContentReadformPanel.form.findField('castingnumber').setValue(objtp.castingnumber); 
	         castingContentReadformPanel.form.findField('partsnumber').setValue(objtp.partsnumber);
	         castingContentReadformPanel.form.findField('structuretype').setValue(objtp.structuretype);
	         castingContentReadformPanel.form.findField('castingname').setValue(objtp.castingname);
	         castingContentReadformPanel.form.findField('editor').setValue(objtp.editor);
	         castingContentReadformPanel.form.findField('defecttype').setValue(objtp.defecttype);
	         castingContentReadformPanel.form.findField('pouringweight').setValue(objtp.pouringweight);
	         castingContentReadformPanel.form.findField('castingmethod').setValue(objtp.castingmethod);
	         castingContentReadformPanel.form.findField('castingprocessdesign').setValue(objtp.castingprocessdesign);
	         castingContentReadformPanel.form.findField('riserdesign').setValue(objtp.riserdesign);
	         castingContentReadformPanel.form.findField('pouringtemperature').setValue(objtp.pouringtemperature);
	         castingContentReadformPanel.form.findField('boxtime').setValue(objtp.boxtime);
	         castingContentReadformPanel.form.findField('heatprocess').setValue(objtp.heatprocess);
	         castingContentReadformPanel.form.findField('smeltingprocess').setValue(objtp.smeltingprocess);
	         castingContentReadformPanel.form.findField('inoculationprocess').setValue(objtp.inoculationprocess);
	         castingContentReadformPanel.form.findField('qualitytest').setValue(objtp.qualitytest);
	         castingContentReadformPanel.form.findField('materialnumber').setValue(objtp.materialnumber);
	         castingContentReadformPanel.form.findField('mainthickness').setValue(objtp.mainthickness);			         
	         castingContentReadformPanel.form.findField('castingweight').setValue(objtp.castingweight);
	         castingContentReadformPanel.form.findField('acceptcondition').setValue(objtp.acceptcondition);
	         castingContentReadformPanel.form.findField('gatingsystemdesign').setValue(objtp.gatingsystemdesign);
	         castingContentReadformPanel.form.findField('chilldesign').setValue(objtp.chilldesign);
	         castingContentReadformPanel.form.findField('pouringtime').setValue(objtp.pouringtime);
	         castingContentReadformPanel.form.findField('chemicalcomponent').setValue(objtp.chemicalcomponent);
	         castingContentReadformPanel.form.findField('physicaltreatment').setValue(objtp.physicaltreatment);
	         castingContentReadformPanel.form.findField('physicalparameter').setValue(objtp.physicalparameter);
	         partsDrawingDownLoadPath=objtp.partsdrawing;
	         },
	         failure : function() {
	          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
	         }
	        });
}

function partsDrawingDownLoadfn(){
	
	    	  if(partsDrawingDownLoadPath==""||partsDrawingDownLoadPath==null)
	    	  {alert('未上传零件图！');return;}
	    	  alert(partsDrawingDownLoadPath);
	          window.location.href=partsDrawingDownLoadPath;  
}	









