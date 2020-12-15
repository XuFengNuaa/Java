//猜测因为panel已经new了，编辑框已存在，所以不能被共用
	var quetextfieldWidth='200';
	var quematerialCategory={xtype:'textfield',fieldLabel:'类别',name:'quematerialcategory',width:quetextfieldWidth};									
	var quematerialBrand={xtype:'textfield',fieldLabel:'牌号',name:'quematerialbrand',width:quetextfieldWidth};	
	var quetensileStrength={xtype:'textfield',fieldLabel:'抗拉强度Rm/MPa',name:'quetensilestrength',width:quetextfieldWidth};									
	var queextensionStrength={xtype:'textfield',fieldLabel:'塑性延伸强度Rp0.2/MPa(≥)',name:'queextensionstrength',width:quetextfieldWidth};
	var queelongation={xtype:'textfield',fieldLabel:'伸长率A(%)(≥)',name:'queelongation',width:quetextfieldWidth};									
	var quehardness={xtype:'textfield',fieldLabel:'硬度(HBW)',name:'quehardness',width:quetextfieldWidth};
	var quemainMatrixOrganization={xtype:'textfield',fieldLabel:'主要基体组织',name:'quemainmatrixorganization',width:quetextfieldWidth};
	
	var quedistributionRatio={xtype:'textarea',fieldLabel:'成分配比',name:'quedistributionratio',width:quetextfieldWidth};
	var queMelting={xtype:'textarea',fieldLabel:'熔炼',name:'quemelting',width:quetextfieldWidth};
	var queInoculation={xtype:'textarea',fieldLabel:'孕育处理',name:'queinoculation',width:quetextfieldWidth};
	var queSpheroidizing={xtype:'textarea',fieldLabel:'球化处理',name:'quespheroidizing',width:quetextfieldWidth};
	var queAnnealing={xtype:'textarea',fieldLabel:'退火',name:'queannealing',width:quetextfieldWidth};
	
		
		
	var mpContentReadformPanel2=new Ext.form.FormPanel({
	layout:'column',
	autoScroll:true,
	frame:true,		
	labelWidth:120,
	items:[
		{layout:'form',columnWidth:.5,items:quematerialBrand},{layout:'form',columnWidth:.5,items:quematerialCategory},
		{layout:'form',columnWidth:.5,items:quemainMatrixOrganization},{layout:'form',columnWidth:.5,items:quehardness},	
		{layout:'form',columnWidth:.5,items:quetensileStrength},{layout:'form',columnWidth:.5,items:queelongation},
		{layout:'form',columnWidth:.5,items:queextensionStrength},{layout:'form',columnWidth:.5,items:quedistributionRatio},
		{layout:'form',columnWidth:.5,items:queMelting},{layout:'form',columnWidth:.5,items:queInoculation},
		{layout:'form',columnWidth:.5,items:queSpheroidizing},{layout:'form',columnWidth:.5,items:queAnnealing}	
		]
	});
	
	var mpContentReadwin = new Ext.Window({
		modal:true,
	    width:750,
	    height:370,
	    closeAction:'hide',
	    plain: true,
	    layout:'fit',
	    title:"查询材料属性",
	    buttonAlign:"center",
		bodyBorder:true,
		items: mpContentReadformPanel2  
	});	
	
	function mpContentReadfn2(gridmaterialbrand){
		Ext.Ajax.request({
		         url:'/casting/mpcontentread.do',
		         method : 'post',
		         params:{materialbrand:gridmaterialbrand},//向后端发送json格式‘castingnumber=？’
		         success : function(result,request) {
		         var objtp=Ext.util.JSON.decode(result.responseText);		          
		         mpContentReadformPanel2.form.findField('quematerialcategory').setValue(objtp.materialcategory); 
		         mpContentReadformPanel2.form.findField('quematerialbrand').setValue(objtp.materialbrand);
		         mpContentReadformPanel2.form.findField('quetensilestrength').setValue(objtp.tensilestrength);
		         mpContentReadformPanel2.form.findField('queextensionstrength').setValue(objtp.extensionstrength);
		         mpContentReadformPanel2.form.findField('queelongation').setValue(objtp.elongation);
		         mpContentReadformPanel2.form.findField('quehardness').setValue(objtp.hardness);
		         mpContentReadformPanel2.form.findField('quemainmatrixorganization').setValue(objtp.mainmatrixorganization); 
		         
		         mpContentReadformPanel2.form.findField('quedistributionratio').setValue(objtp.distributionratio); 
		         mpContentReadformPanel2.form.findField('quemelting').setValue(objtp.melting);
		         mpContentReadformPanel2.form.findField('queinoculation').setValue(objtp.inoculation);
		         mpContentReadformPanel2.form.findField('quespheroidizing').setValue(objtp.spheroidizing);
		         mpContentReadformPanel2.form.findField('queannealing').setValue(objtp.annealing);
		         },
		         failure : function() {
		          Ext.Msg.alert('Error','服务繁忙请稍后重试！');			       
		         }
		        });
	}




var addtextfieldWidth='200';
var addmaterialCategory={xtype:'textfield',fieldLabel:'类别',name:'addmaterialcategory',width:addtextfieldWidth};									
var addmaterialBrand={xtype:'textfield',fieldLabel:'牌号',name:'addmaterialbrand',width:addtextfieldWidth};	
var addtensileStrength={xtype:'textfield',fieldLabel:'抗拉强度Rm/MPa',name:'addtensilestrength',width:addtextfieldWidth};									
var addextensionStrength={xtype:'textfield',fieldLabel:'塑性延伸强度Rp0.2/MPa(≥)',name:'addextensionstrength',width:addtextfieldWidth};
var addelongation={xtype:'textfield',fieldLabel:'伸长率A(%)(≥)',name:'addelongation',width:addtextfieldWidth};									
var addhardness={xtype:'textfield',fieldLabel:'硬度(HBW)',name:'addhardness',width:addtextfieldWidth};
var addmainMatrixOrganization={xtype:'textfield',fieldLabel:'主要基体组织',name:'addmainmatrixorganization',width:addtextfieldWidth};

var adddistributionRatio={xtype:'textarea',fieldLabel:'成分配比',name:'adddistributionratio',width:addtextfieldWidth};
var addMelting={xtype:'textarea',fieldLabel:'熔炼',name:'addmelting',width:addtextfieldWidth};
var addInoculation={xtype:'textarea',fieldLabel:'孕育处理',name:'addinoculation',width:addtextfieldWidth};
var addSpheroidizing={xtype:'textarea',fieldLabel:'球化处理',name:'addspheroidizing',width:addtextfieldWidth};
var addAnnealing={xtype:'textarea',fieldLabel:'退火',name:'addannealing',width:addtextfieldWidth};


var addmpContentReadformPanel=new Ext.form.FormPanel({	
	layout:'column',
	autoScroll:true,
	frame:true,		
	labelWidth:120,
	items:[
		{layout:'form',columnWidth:.5,items:addmaterialBrand},{layout:'form',columnWidth:.5,items:addmaterialCategory},
		{layout:'form',columnWidth:.5,items:addmainMatrixOrganization},{layout:'form',columnWidth:.5,items:addhardness},	
		{layout:'form',columnWidth:.5,items:addtensileStrength},{layout:'form',columnWidth:.5,items:addelongation},
		{layout:'form',columnWidth:.5,items:addextensionStrength},{layout:'form',columnWidth:.5,items:adddistributionRatio},
		{layout:'form',columnWidth:.5,items:addMelting},{layout:'form',columnWidth:.5,items:addInoculation},
		{layout:'form',columnWidth:.5,items:addSpheroidizing},{layout:'form',columnWidth:.5,items:addAnnealing}	
		]
});
	






