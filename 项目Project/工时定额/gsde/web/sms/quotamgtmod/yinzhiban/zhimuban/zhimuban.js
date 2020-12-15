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
		     title: '制模板',
		     collapsible: false,
		     autoHeight:true,
		     layout:'form',
		     items:[{xtype:'textfield',fieldLabel: '设计时间(每层)',emptyText:'',id: 'sheji',width: 100}, 
		            {xtype:'textfield',fieldLabel: '光绘时间',emptyText:'',id: 'gh',width: 100},
		            {xtype:'textfield',fieldLabel: '修版时间',emptyText:'',id: 'xiuban',width: 100}]
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
	    title:"<p align='center'>制模板规则维护</p>",
	    border:false,	
	    frame: true,
	    layout:'fit',
	    items: rightborder
	}]
	});
	
	//修改函数
	function modifywFn(){
		var sheji = Ext.get("sheji").dom.value.trim();
		var gh = Ext.get("gh").dom.value.trim();
		var xiuban = Ext.get("xiuban").dom.value.trim();
		var regtext = /^[\+\-]?(\d{0,8}(\.\d{1,20})?)$/;
		if(sheji==""){
			alert("请输入设计时间");
		}else if(gh==""){
			alert("请输入光绘时间");
		}else if(xiuban==""){
			alert("请输入修版时间");
		}else{
			if(!regtext.test(sheji)){
			alert("设计时间必须为数字");
			return;
		}
		if(!regtext.test(gh)){
			alert("光绘时间必须为数字");
			return;
		}
		if(!regtext.test(xiuban)){
			alert("修版时间必须为数字");
			return;
		}
			Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logiczhimuban.jsp',
						method:'post',
						params:{
							type:'EDIT',
							sheji:sheji,
							gh:gh,
							xiuban:xiuban
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
		 	url:'logiczhimuban.jsp',
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
	 	Ext.getCmp("sheji").setValue(objtp.sheji);
		Ext.getCmp("gh").setValue(objtp.gh);
		Ext.getCmp("xiuban").setValue(objtp.xiuban);
	}

	initializeFn();
	
});