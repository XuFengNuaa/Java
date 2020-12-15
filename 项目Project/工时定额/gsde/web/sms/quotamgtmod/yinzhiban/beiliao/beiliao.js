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
		     title: '件数系数',
		     collapsible: false,
		     autoHeight:true,
		     layout:'form',
		     items:[{xtype:'textfield',fieldLabel: '21~50',emptyText:'',id: 'js1',width: 100}, 
		            {xtype:'textfield',fieldLabel: '51~100',emptyText:'',id: 'js2',width: 100},
		            {xtype:'textfield',fieldLabel: '101~200',emptyText:'',id: 'js3',width: 100},
		            {xtype:'textfield',fieldLabel: '>200',emptyText:'',id: 'js4',width: 100}]
			},{
			   xtype:'fieldset',
			   title: '单件面积(万mm2)系数',
			   collapsible: false,
			   autoHeight:true,
			   layout:'form',
			   items:[{xtype:'textfield',fieldLabel: '21~50',emptyText:'',id: 'mj1',width: 100},
				      {xtype:'textfield',fieldLabel: '51~100',emptyText:'',id: 'mj2',width: 100},
				      {xtype:'textfield',fieldLabel: '101~200',emptyText:'',id: 'mj3',width: 100},
				      {xtype:'textfield',fieldLabel: '>200',emptyText:'',id: 'mj4',width: 100}]
			},{
			   xtype:'fieldset',
			   title: '材料系数',
			   collapsible: false,
			   autoHeight:true,
			   layout:'form',
			   items:[{xtype:'textfield',fieldLabel: '橡胶',emptyText:'',id: 'xj',width: 100},
			          {xtype:'textfield',fieldLabel: '毛毡',emptyText:'',id: 'mz',width: 100}]
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
	    title:"<p align='center'>备料规则维护</p>",
	    border:false,	
	    frame: true,
	    layout:'fit',
	    items: rightborder
	}]
	});
	
	//修改函数
	function modifywFn(){
		var js1 = Ext.get("js1").dom.value.trim();
		var js2 = Ext.get("js2").dom.value.trim();
		var js3 = Ext.get("js3").dom.value.trim();
		var js4 = Ext.get("js4").dom.value.trim();
		var mj1 = Ext.get("mj1").dom.value.trim();
		var mj2 = Ext.get("mj2").dom.value.trim();
		var mj3 = Ext.get("mj3").dom.value.trim();
		var mj4 = Ext.get("mj4").dom.value.trim();
		var xj = Ext.get("xj").dom.value.trim();
		var mz = Ext.get("mz").dom.value.trim();
		var regtext = /^[\+\-]?(\d{0,8}(\.\d{1,20})?)$/;
		if(js1==""){
			alert("请输入件数系数");
		}else if(js2==""){
			alert("请输入件数系数");
		}else if(js3==""){
			alert("请输入件数系数");
		}else if(js4==""){
			alert("请输入件数系数");
		}else if(mj1==""){
			alert("请输入面积系数");
		}else if(mj2==""){
			alert("请输入面积系数");
		}else if(mj3==""){
			alert("请输入面积系数");
		}else if(mj4==""){
			alert("请输入面积系数");
		}else if(xj==""){
			alert("请输入材料系数");
		}else if(mz==""){
			alert("请输入材料系数");
		}else{
			if(!regtext.test(js1)){
			alert("件数系数必须为数字");
			return;
		}
		if(!regtext.test(js2)){
			alert("件数系数必须为数字");
			return;
		}
		if(!regtext.test(js3)){
			alert("件数系数必须为数字");
			return;
		}
		if(!regtext.test(js4)){
			alert("件数系数必须为数字");
			return;
		}
		if(!regtext.test(mj1)){
			alert("面积系数必须为数字");
			return;
		}
		if(!regtext.test(mj2)){
			alert("面积系数必须为数字");
			return;
		}
		if(!regtext.test(mj3)){
			alert("面积系数必须为数字");
			return;
		}
		if(!regtext.test(mj4)){
			alert("面积系数必须为数字");
			return;
		}
		if(!regtext.test(xj)){
			alert("材料系数必须为数字");
			return;
		}
		if(!regtext.test(mz)){
			alert("材料系数必须为数字");
			return;
		}
			Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicbeiliao.jsp',
						method:'post',
						params:{
							type:'EDIT',
							js1:js1,
							js2:js2,
							js3:js3,
							js4:js4,
							mj1:mj1,
							mj2:mj2,
							mj3:mj3,
							mj4:mj4,
							xj:xj,
							mz:mz
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
		 	url:'logicbeiliao.jsp',
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
	 	Ext.getCmp("js1").setValue(objtp.js1);
		Ext.getCmp("js2").setValue(objtp.js2);
		Ext.getCmp("js3").setValue(objtp.js3);
		Ext.getCmp("js4").setValue(objtp.js4);
		Ext.getCmp("mj1").setValue(objtp.mj1);
		Ext.getCmp("mj2").setValue(objtp.mj2);
		Ext.getCmp("mj3").setValue(objtp.mj3);
		Ext.getCmp("mj4").setValue(objtp.mj4);
		Ext.getCmp("xj").setValue(objtp.xj);
		Ext.getCmp("mz").setValue(objtp.mz);
	}

	initializeFn();
	
});