Ext.onReady(function(){
	
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
    
	//主页面
    var rightborder = new Ext.FormPanel({
    layout:"form",
    bodyStyle: 'padding:10px 400px 0',
    frame: true,
    labelAlign: 'center',
	items: [{
		     xtype:'fieldset',
		     title: '实例问题描述参数权重',
		     collapsible: false,
		     autoHeight:true,
		     layout:'form',
		     items:[{xtype:'textfield',fieldLabel: '毛坯长度权重',emptyText:'',id: 'chang',width: 100}, 
		            {xtype:'textfield',fieldLabel: '毛坯宽度权重',emptyText:'',id: 'kuan',width: 100},
		            {xtype:'textfield',fieldLabel: '毛坯高度权重',emptyText:'',id: 'gao',width: 100},
		            {xtype:'textfield',fieldLabel: '去除体积权重',emptyText:'',id: 'tiji',width: 100},
		            {xtype:'textfield',fieldLabel: '去除表面积权重',emptyText:'',id: 'mianji',width: 100}]
			},{
			   xtype:'fieldset',
			   title: '工件材料属性权重',
			   collapsible: false,
			   autoHeight:true,
			   layout:'form',
			   items:[{xtype:'textfield',fieldLabel: '抗拉强度权重',emptyText:'',id: 'strw1',width: 100},
				      {xtype:'textfield',fieldLabel: '冲击韧性权重',emptyText:'',id: 'strw2',width: 100},
				      {xtype:'textfield',fieldLabel: '材料硬度权重',emptyText:'',id: 'strw3',width: 100},
				      {xtype:'textfield',fieldLabel: '材料热导率权重',emptyText:'',id: 'strw4',width: 100}]
			},{
			   xtype:'fieldset',
			   title: '相似度对最终评价结果的影响权重',
			   collapsible: false,
			   autoHeight:true,
			   layout:'form',
			   items:[{xtype:'textfield',fieldLabel: '相似度权重',emptyText:'',id: 'simw',width: 100}]
			}],
	buttons: [{text: '修改',id:"modifyw",type: 'submit',handler:modifywFn},
	          {text: '重置',id:"newReset",handler:function(){
										rightborder.getForm().reset();								     								
										//Ext.getCmp("jgjd").focus();
	          							}
	          }]
	});
	
    //页面组装
	var viewport = new Ext.Viewport({
	layout:"border",
	border:false,
	autoHeight:false,
	items: [{
		id:'cp',
	    region: 'center',
	    title:"<p align='center'>各参数相似度计算权重</p>",
	    border:false,	
	    frame: true,
	    layout:'fit',
	    items: rightborder
	}]
	});
	
	//修改函数
	function modifywFn(){
		var chang = Ext.get("chang").dom.value.trim();
		var kuan = Ext.get("kuan").dom.value.trim();
		var gao = Ext.get("gao").dom.value.trim();
		var tiji = Ext.get("tiji").dom.value.trim();
		var mianji = Ext.get("mianji").dom.value.trim();
		var clqiangdu = Ext.get("strw1").dom.value.trim();
		var clrenxing = Ext.get("strw2").dom.value.trim();
		var clyingdu = Ext.get("strw3").dom.value.trim();
		var clredaolv = Ext.get("strw4").dom.value.trim();
		var xiangsidu = Ext.get("simw").dom.value.trim();
		var regtext = /^[\+\-]?(\d{0,8}(\.\d{1,20})?)$/;
		if(chang==""){
			alert("请输入毛坯长度权重");
		}else if(kuan==""){
			alert("请输入毛坯宽度权重");
		}else if(gao==""){
			alert("请输入毛坯高度权重");
		}else if(tiji==""){
			alert("请输入去除体积权重");
		}else if(mianji==""){
			alert("请输入去除表面积权重");
		}else if(clqiangdu==""){
			alert("请输入抗拉强度权重");
		}else if(clrenxing==""){
			alert("请输入冲击韧性权重");
		}else if(clyingdu==""){
			alert("请输入硬度权重");
		}else if(clredaolv==""){
			alert("请输入热导率权重");
		}else if(xiangsidu==""){
			alert("请输入实例相似度权重");
		}else{
			if(!regtext.test(chang)){
			alert("毛坯长度权重必须为数字");
			return;
		}
		if(!regtext.test(kuan)){
			alert("毛坯宽度权重必须为数字");
			return;
		}
		if(!regtext.test(gao)){
			alert("毛坯高度权重必须为数字");
			return;
		}
		if(!regtext.test(tiji)){
			alert("去除体积权重必须为数字");
			return;
		}
		if(!regtext.test(mianji)){
			alert("去除表面积权重必须为数字");
			return;
		}
		if(!regtext.test(clqiangdu)){
			alert("抗拉强度权重必须为数字");
			return;
		}
		if(!regtext.test(clrenxing)){
			alert("冲击韧性权重必须为数字");
			return;
		}
		if(!regtext.test(clyingdu)){
			alert("硬度权重必须为数字");
			return;
		}
		if(!regtext.test(clredaolv)){
			alert("热导率权重必须为数字");
			return;
		}
		if(!regtext.test(xiangsidu)){
			alert("实例相似度权重必须为数字");
			return;
		}
			Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicweight.jsp',
						method:'post',
						params:{
							type:'EDIT',
							chang:chang,
							kuan:kuan,
							gao:gao,
							tiji:tiji,
							mianji:mianji,
							clqiangdu:clqiangdu,
							clrenxing:clrenxing,
							clyingdu:clyingdu,
							clredaolv:clredaolv,
							xiangsidu:xiangsidu
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"修改成功");
								initializeFn();
							}else{
								Ext.MessageBox.alert('提示',"修改失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
		}
	
	}		
	
	//数据载入
	function initializeFn(){
		var load=Ext.Ajax.request({
		 	url:'logicweight.jsp',
		 	method:'post',
	 		params:{type:'LOAD'},//传递userid参数
			success:successFn,
	 		failure:function(){
				alert('服务器出现错误请稍后再试！');
 			}
	  	});	
	}
			
	//初始化成功回调函数
	function successFn(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);
		//var feature = objtp.feature
	 	Ext.getCmp("chang").setValue(objtp.chang);
		Ext.getCmp("kuan").setValue(objtp.kuan);
		Ext.getCmp("gao").setValue(objtp.gao);
		Ext.getCmp("tiji").setValue(objtp.tiji);
		Ext.getCmp("mianji").setValue(objtp.mianji);
		Ext.getCmp("strw1").setValue(objtp.strw1);
		Ext.getCmp("strw2").setValue(objtp.strw2);
		Ext.getCmp("strw3").setValue(objtp.strw3);
		Ext.getCmp("strw4").setValue(objtp.strw4);
		Ext.getCmp("simw").setValue(objtp.xiangsidu);
	}

	initializeFn();
	
});