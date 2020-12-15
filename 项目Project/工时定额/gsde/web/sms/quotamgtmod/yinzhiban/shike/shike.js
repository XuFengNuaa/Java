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
		     title: '100mm*150mm以下',
		     collapsible: false,
		     autoHeight:true,
		     layout:'form',
		     items:[{xtype:'textfield',fieldLabel: '腐蚀',emptyText:'',id: 'fs1',width: 100}, 
		            {xtype:'textfield',fieldLabel: '脱膜',emptyText:'',id: 'tm1',width: 100},
		            {xtype:'textfield',fieldLabel: '修板',emptyText:'',id: 'xb1',width: 100}]
			},{
			   xtype:'fieldset',
			   title: '200mm*200mm以下',
			   collapsible: false,
			   autoHeight:true,
			   layout:'form',
			   items:[{xtype:'textfield',fieldLabel: '腐蚀',emptyText:'',id: 'fs2',width: 100}, 
			            {xtype:'textfield',fieldLabel: '脱膜',emptyText:'',id: 'tm2',width: 100},
			            {xtype:'textfield',fieldLabel: '修板',emptyText:'',id: 'xb2',width: 100}]
			},{
			   xtype:'fieldset',
			   title: '250mm*300mm以下',
			   collapsible: false,
			   autoHeight:true,
			   layout:'form',
			   items:[{xtype:'textfield',fieldLabel: '腐蚀',emptyText:'',id: 'fs3',width: 100}, 
			            {xtype:'textfield',fieldLabel: '脱膜',emptyText:'',id: 'tm3',width: 100},
			            {xtype:'textfield',fieldLabel: '修板',emptyText:'',id: 'xb3',width: 100}]
			},{
				   xtype:'fieldset',
				   title: '350mm*350mm以下',
				   collapsible: false,
				   autoHeight:true,
				   layout:'form',
				   items:[{xtype:'textfield',fieldLabel: '腐蚀',emptyText:'',id: 'fs4',width: 100}, 
				            {xtype:'textfield',fieldLabel: '脱膜',emptyText:'',id: 'tm4',width: 100},
				            {xtype:'textfield',fieldLabel: '修板',emptyText:'',id: 'xb4',width: 100}]
				},{
				   xtype:'fieldset',
				   title: '350mm*350mm以上',
				   collapsible: false,
				   autoHeight:true,
				   layout:'form',
				   items:[{xtype:'textfield',fieldLabel: '腐蚀',emptyText:'',id: 'fs5',width: 100}, 
				            {xtype:'textfield',fieldLabel: '脱膜',emptyText:'',id: 'tm5',width: 100},
				            {xtype:'textfield',fieldLabel: '修板',emptyText:'',id: 'xb5',width: 100}]
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
	    title:"<p align='center'>蚀刻规则维护(分钟)</p>",
	    border:false,	
	    frame: true,
	    layout:'fit',
	    items: rightborder
	}]
	});
	
	//修改函数
	function modifywFn(){
		var fs1 = Ext.get("fs1").dom.value.trim();
		var tm1 = Ext.get("tm1").dom.value.trim();
		var xb1 = Ext.get("xb1").dom.value.trim();
		var fs2 = Ext.get("fs2").dom.value.trim();
		var tm2 = Ext.get("tm2").dom.value.trim();
		var xb2 = Ext.get("xb2").dom.value.trim();
		var fs3 = Ext.get("fs3").dom.value.trim();
		var tm3 = Ext.get("tm3").dom.value.trim();
		var xb3 = Ext.get("xb3").dom.value.trim();
		var fs4 = Ext.get("fs4").dom.value.trim();
		var tm4 = Ext.get("tm4").dom.value.trim();
		var xb4 = Ext.get("xb4").dom.value.trim();
		var fs5 = Ext.get("fs5").dom.value.trim();
		var tm5 = Ext.get("tm5").dom.value.trim();
		var xb5 = Ext.get("xb5").dom.value.trim();
		var zj1 = fs1*1+tm1*1+xb1*1;
		var zj2 = fs2*1+tm2*1+xb2*1;
		var zj3 = fs3*1+tm3*1+xb3*1;
		var zj4 = fs4*1+tm4*1+xb4*1;
		var zj5 = fs5*1+tm5*1+xb5*1;

		Ext.showProgressDialog();
				Ext.Ajax.request({
					url:'logicshike.jsp',
					method:'post',
					params:{
						type:'EDIT',
						fs1:fs1,
						tm1:tm1,
						xb1:xb1,
						zj1:zj1,
						fs2:fs2,
						tm2:tm2,
						xb2:xb2,
						zj2:zj2,
						fs3:fs3,
						tm3:tm3,
						xb3:xb3,
						zj3:zj3,
						fs4:fs4,
						tm4:tm4,
						xb4:xb4,
						zj4:zj4,
						fs5:fs5,
						tm5:tm5,
						xb5:xb5,
						zj5:zj5
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
	
	//数据载入
	function initializeFn(){
		var load=Ext.Ajax.request({
		 	url:'logicshike.jsp',
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
	 	Ext.getCmp("fs1").setValue(objtp.fs1);
		Ext.getCmp("tm1").setValue(objtp.tm1);
		Ext.getCmp("xb1").setValue(objtp.xb1);
		Ext.getCmp("fs2").setValue(objtp.fs2);
		Ext.getCmp("tm2").setValue(objtp.tm2);
		Ext.getCmp("xb2").setValue(objtp.xb2);
		Ext.getCmp("fs3").setValue(objtp.fs3);
		Ext.getCmp("tm3").setValue(objtp.tm3);
		Ext.getCmp("xb3").setValue(objtp.xb3);
		Ext.getCmp("fs4").setValue(objtp.fs4);
		Ext.getCmp("tm4").setValue(objtp.tm4);
		Ext.getCmp("xb4").setValue(objtp.xb4);
		Ext.getCmp("fs5").setValue(objtp.fs5);
		Ext.getCmp("tm5").setValue(objtp.tm5);
		Ext.getCmp("xb5").setValue(objtp.xb5);
	}

	initializeFn();
	
});