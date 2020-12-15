var textfieldwidth='150';
var addpartType={xtype:'textfield',name:'addparttype',fieldLabel:'类型',width:textfieldwidth};
var addmaterial={xtype:'textfield',name:'addmaterial',fieldLabel:'材料',width:textfieldwidth};
var addpartNumber={xtype:'textfield',name:'addpartnumber',fieldLabel:'编号',width:textfieldwidth};
var addpartOuterDiam={xtype:'textfield',name:'addpartouterdiam',fieldLabel:'零件外径(mm)',width:textfieldwidth};
var addouterSurfMachAllow={xtype:'textfield',name:'addoutersurfmachallow',fieldLabel:'外表面加工余量(mm)',width:textfieldwidth};
var addinnerSurfMachAllow={xtype:'textfield',name:'addinnersurfmachallow',fieldLabel:'内表面加工余量(mm)',width:textfieldwidth};
var addendMachAllow={xtype:'textfield',name:'addendmachallow',fieldLabel:'端面加工余量(mm)',width:textfieldwidth};
var addrotatSpeed={xtype:'textfield',name:'addrotatspeed',fieldLabel:'转速(r/min)',width:textfieldwidth};
var addcoating={xtype:'textfield',name:'addcoating',fieldLabel:'涂料',width:textfieldwidth};
var addoperatTtemper={xtype:'textfield',name:'addoperattemper',fieldLabel:'铸型工作温度(℃)',width:textfieldwidth};
var addpourTemper={xtype:'textfield',name:'addpourtemper',fieldLabel:'浇注温度(℃)',width:textfieldwidth};
var addreleaseTemper={xtype:'textfield',name:'addreleasetemper',fieldLabel:'脱模温度(℃)',width:textfieldwidth};
var addmoldDryingPro={xtype:'textarea',name:'addmolddryingpro',fieldLabel:'铸型烘干工艺',width:textfieldwidth};

var addlxformpanel=new Ext.form.FormPanel({
	layout:'column',
	autoScroll:true,
	frame:true,		
	labelWidth:120,
	items:[
		{layout:'form',columnWidth:.5,items:addpartType},{layout:'form',columnWidth:.5,items:addmaterial},
		{layout:'form',columnWidth:.5,items:addpartNumber},{layout:'form',columnWidth:.5,items:addpartOuterDiam},
		{layout:'form',columnWidth:.5,items:addrotatSpeed},{layout:'form',columnWidth:.5,items:addcoating},
		{layout:'form',columnWidth:.5,items:addinnerSurfMachAllow},{layout:'form',columnWidth:.5,items:addoperatTtemper},
		{layout:'form',columnWidth:.5,items:addouterSurfMachAllow},{layout:'form',columnWidth:.5,items:addpourTemper},
		{layout:'form',columnWidth:.5,items:addendMachAllow},{layout:'form',columnWidth:.5,items:addreleaseTemper},	
		{layout:'form',columnWidth:1,items:addmoldDryingPro}
	]
});


var modpartType={xtype:'textfield',name:'modparttype',fieldLabel:'类型',width:textfieldwidth};
var modmaterial={xtype:'textfield',name:'modmaterial',fieldLabel:'材料',width:textfieldwidth};
var modpartNumber={xtype:'textfield',name:'modpartnumber',fieldLabel:'编号',width:textfieldwidth,style:"background:#C1C1C1;",readOnly:true};
var modpartOuterDiam={xtype:'textfield',name:'modpartouterdiam',fieldLabel:'零件外径(mm)',width:textfieldwidth};
var modouterSurfMachAllow={xtype:'textfield',name:'modoutersurfmachallow',fieldLabel:'外表面加工余量(mm)',width:textfieldwidth};
var modinnerSurfMachAllow={xtype:'textfield',name:'modinnersurfmachallow',fieldLabel:'内表面加工余量(mm)',width:textfieldwidth};
var modendMachAllow={xtype:'textfield',name:'modendmachallow',fieldLabel:'端面加工余量(mm)',width:textfieldwidth};
var modrotatSpeed={xtype:'textfield',name:'modrotatspeed',fieldLabel:'转速(r/min)',width:textfieldwidth};
var modcoating={xtype:'textfield',name:'modcoating',fieldLabel:'涂料',width:textfieldwidth};
var modoperatTtemper={xtype:'textfield',name:'modoperattemper',fieldLabel:'铸型工作温度(℃)',width:textfieldwidth};
var modpourTemper={xtype:'textfield',name:'modpourtemper',fieldLabel:'浇注温度(℃)',width:textfieldwidth};
var modreleaseTemper={xtype:'textfield',name:'modreleasetemper',fieldLabel:'脱模温度(℃)',width:textfieldwidth};
var modmoldDryingPro={xtype:'textarea',name:'modmolddryingpro',fieldLabel:'铸型烘干工艺',width:textfieldwidth};

var modlxformpanel=new Ext.form.FormPanel({
	layout:'column',
	autoScroll:true,
	frame:true,		
	labelWidth:120,
	items:[
		{layout:'form',columnWidth:.5,items:modpartType},{layout:'form',columnWidth:.5,items:modmaterial},
		{layout:'form',columnWidth:.5,items:modpartNumber},{layout:'form',columnWidth:.5,items:modpartOuterDiam},
		{layout:'form',columnWidth:.5,items:modrotatSpeed},{layout:'form',columnWidth:.5,items:modcoating},
		{layout:'form',columnWidth:.5,items:modinnerSurfMachAllow},{layout:'form',columnWidth:.5,items:modoperatTtemper},
		{layout:'form',columnWidth:.5,items:modouterSurfMachAllow},{layout:'form',columnWidth:.5,items:modpourTemper},
		{layout:'form',columnWidth:.5,items:modendMachAllow},{layout:'form',columnWidth:.5,items:modreleaseTemper},	
		{layout:'form',columnWidth:1,items:modmoldDryingPro}
	]
});


function lxReadfn2(gridpartnumber){
	Ext.Ajax.request({
	         url:'/casting/lxreadbypartnumber.do',
	         method : 'post',
	         params:{partnumber:gridpartnumber},//向后端发送json格式‘partnumber=？,后端匹配字段为partnumber’
	         success : function(result,request) {
		         var objtp=Ext.util.JSON.decode(result.responseText);
		         modlxformpanel.form.findField('modparttype').setValue(objtp.parttype); 
		         modlxformpanel.form.findField('modmaterial').setValue(objtp.material);
		         modlxformpanel.form.findField('modpartnumber').setValue(objtp.partnumber);
		         modlxformpanel.form.findField('modpartouterdiam').setValue(objtp.partouterdiam);
		         modlxformpanel.form.findField('modoutersurfmachallow').setValue(objtp.outersurfmachallow);
		         modlxformpanel.form.findField('modinnersurfmachallow').setValue(objtp.innersurfmachallow);
		         modlxformpanel.form.findField('modendmachallow').setValue(objtp.endmachallow); 
		         modlxformpanel.form.findField('modrotatspeed').setValue(objtp.rotatspeed); 
		         modlxformpanel.form.findField('modcoating').setValue(objtp.coating);
		         modlxformpanel.form.findField('modoperattemper').setValue(objtp.operattemper);
		         modlxformpanel.form.findField('modpourtemper').setValue(objtp.pourtemper);
		         modlxformpanel.form.findField('modreleasetemper').setValue(objtp.releasetemper);
		         modlxformpanel.form.findField('modmolddryingpro').setValue(objtp.molddryingpro);
	         },
	         failure : function() {
	          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
	         }
	        });
}





