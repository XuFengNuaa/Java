Ext.onReady(function(){
	
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
    
	//主页面
    var rightborder = new Ext.FormPanel({
    layout:"column",
    bodyStyle: 'padding:10px 200px 0',
    frame: true,
    labelAlign: 'center',
	items: [{
		           width:300,
					items:[{
				     xtype:'fieldset',
				     title: '150mm*150mm以下',
				     collapsible: false,
				     autoHeight:true,
				     layout:'form',
				     items:[{xtype:'textfield',fieldLabel: '刷板',emptyText:'',id: 'ps1',width: 100}, 
				            {xtype:'textfield',fieldLabel: '贴膜',emptyText:'',id: 'dm1',width: 100},
				            //{xtype:'textfield',fieldLabel: '双面贴膜',emptyText:'',id: 'sm1',hidden:true,width: 100},
				            //{xtype:'textfield',fieldLabel: '多层贴膜',emptyText:'',id: 'dc1',hidden:true,width: 100},
				            {xtype:'textfield',fieldLabel: '曝光显影',emptyText:'',id: 'bg1',width: 100},
				            {xtype:'textfield',fieldLabel: '修版',emptyText:'',id: 'xb1',width: 100}]
					},{
					   xtype:'fieldset',
					   title: '250mm*250mm以下',
					   collapsible: false,
					   autoHeight:true,
					   layout:'form',
					   items:[{xtype:'textfield',fieldLabel: '刷板',emptyText:'',id: 'ps2',width: 100}, 
					            {xtype:'textfield',fieldLabel: '贴膜',emptyText:'',id: 'dm2',width: 100},
					           // {xtype:'textfield',fieldLabel: '双面贴膜',emptyText:'',id: 'sm2',hidden:true,width: 100},
					           // {xtype:'textfield',fieldLabel: '多层贴膜',emptyText:'',id: 'dc2',hidden:true,width: 100},
					            {xtype:'textfield',fieldLabel: '曝光显影',emptyText:'',id: 'bg2',width: 100},
					            {xtype:'textfield',fieldLabel: '修版',emptyText:'',id: 'xb2',width: 100}]
					}]
			},{
				width:300,			
				items:[
					{
						   xtype:'fieldset',
						   title: '350mm*350mm以下',
						   collapsible: false,
						   autoHeight:true,
						   layout:'form',
						   items:[{xtype:'textfield',fieldLabel: '刷板',emptyText:'',id: 'ps3',width: 100}, 
						            {xtype:'textfield',fieldLabel: '贴膜',emptyText:'',id: 'dm3',width: 100},
						           // {xtype:'textfield',fieldLabel: '双面贴膜',emptyText:'',id: 'sm3',hidden:true,width: 100},
						           // {xtype:'textfield',fieldLabel: '多层贴膜',emptyText:'',id: 'dc3',hidden:true,width: 100},
						            {xtype:'textfield',fieldLabel: '曝光显影',emptyText:'',id: 'bg3',width: 100},
						            {xtype:'textfield',fieldLabel: '修版',emptyText:'',id: 'xb3',width: 100}]
						},{
							   xtype:'fieldset',
							   title: '350mm*350mm以上',
							   collapsible: false,
							   autoHeight:true,
							   layout:'form',
							   items:[{xtype:'textfield',fieldLabel: '刷板',emptyText:'',id: 'ps4',width: 100}, 
							            {xtype:'textfield',fieldLabel: '贴膜',emptyText:'',id: 'dm4',width: 100},
							          //  {xtype:'textfield',fieldLabel: '双面贴膜',emptyText:'',id: 'sm4',hidden:true,width: 100},
							          //  {xtype:'textfield',fieldLabel: '多层贴膜',emptyText:'',id: 'dc4',hidden:true,width: 100},
							            {xtype:'textfield',fieldLabel: '曝光显影',emptyText:'',id: 'bg4',width: 100},
							            {xtype:'textfield',fieldLabel: '修版',emptyText:'',id: 'xb4',width: 100}]
							}]}],
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
	    title:"<p align='center'>图形转移规则维护(分钟)</p>",
	    border:false,	
	    frame: true,
	    layout:'fit',
	    items: rightborder
	}]
	});
	
	//修改函数
	function modifywFn(){
		var ps1 = Ext.get("ps1").dom.value.trim();
		var dm1 = Ext.get("dm1").dom.value.trim();
		var sm1 = 4;
		var dc1 = 6;
		var bg1 = Ext.get("bg1").dom.value.trim();
		var xb1 = Ext.get("xb1").dom.value.trim();
		var ps2 = Ext.get("ps2").dom.value.trim();
		var dm2 = Ext.get("dm2").dom.value.trim();
		var sm2 = 6;
		var dc2 = 8;
		var bg2 = Ext.get("bg2").dom.value.trim();
		var xb2 = Ext.get("xb2").dom.value.trim();
		var ps3 = Ext.get("ps3").dom.value.trim();
		var dm3 = Ext.get("dm3").dom.value.trim();
		var sm3 = 8;
		var dc3 = 15;
		var bg3 = Ext.get("bg3").dom.value.trim();
		var xb3 = Ext.get("xb3").dom.value.trim();
		var ps4 = Ext.get("ps4").dom.value.trim();
		var dm4 = Ext.get("dm4").dom.value.trim();
		var sm4 = 10;
		var dc4 = 20;
		var bg4 = Ext.get("bg4").dom.value.trim();
		var xb4 = Ext.get("xb4").dom.value.trim();
		
		Ext.showProgressDialog();
				Ext.Ajax.request({
					url:'logictuxingzhuanyi.jsp',
					method:'post',
					params:{
						type:'EDIT',
						ps1:ps1,
						dm1:dm1,
						sm1:sm1,
						dc1:dc1,
						bg1:bg1,
						xb1:xb1,
						ps2:ps2,
						dm2:dm2,
						sm2:sm2,
						dc2:dc2,
						bg2:bg2,
						xb2:xb2,
						ps3:ps3,
						dm3:dm3,
						sm3:sm3,
						dc3:dc3,
						bg3:bg3,
						xb3:xb3,
						ps4:ps4,
						dm4:dm4,
						sm4:sm4,
						dc4:dc4,
						bg4:bg4,
						xb4:xb4
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
		 	url:'logictuxingzhuanyi.jsp',
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
	 	Ext.getCmp("ps1").setValue(objtp.ps1);
		Ext.getCmp("dm1").setValue(objtp.dm1);
		//Ext.getCmp("sm1").setValue(objtp.sm1);
		//Ext.getCmp("dc1").setValue(objtp.dc1);
		Ext.getCmp("bg1").setValue(objtp.bg1);
		Ext.getCmp("xb1").setValue(objtp.xb1);
		Ext.getCmp("ps2").setValue(objtp.ps2);
		Ext.getCmp("dm2").setValue(objtp.dm2);
		//Ext.getCmp("sm2").setValue(objtp.sm2);
		//Ext.getCmp("dc2").setValue(objtp.dc2);
		Ext.getCmp("bg2").setValue(objtp.bg2);
		Ext.getCmp("xb2").setValue(objtp.xb2);
		Ext.getCmp("ps3").setValue(objtp.ps3);
		Ext.getCmp("dm3").setValue(objtp.dm3);
		//Ext.getCmp("sm3").setValue(objtp.sm3);
		//Ext.getCmp("dc3").setValue(objtp.dc3);
		Ext.getCmp("bg3").setValue(objtp.bg3);
		Ext.getCmp("xb3").setValue(objtp.xb3);
		Ext.getCmp("ps4").setValue(objtp.ps4);
		Ext.getCmp("dm4").setValue(objtp.dm4);
		//Ext.getCmp("sm4").setValue(objtp.sm4);
		//Ext.getCmp("dc4").setValue(objtp.dc4);
		Ext.getCmp("bg4").setValue(objtp.bg4);
		Ext.getCmp("xb4").setValue(objtp.xb4);
	}

	initializeFn();
	
});