Ext.onReady(function(){
	
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
    
	//主页面
    var rightborder = new Ext.FormPanel({
    layout:"form",
    bodyStyle: 'padding:10px 400px 0',
    frame: true,
    labelAlign: 'right',
    labelWidth:150,
	items: [{
		     xtype:'fieldset',
		     title: '层压',
		     collapsible: false,
		     autoHeight:true,
		     layout:'form',
		     width:280,
		     items:[
		            {xtype:'textfield',fieldLabel: '截半园化片时间(每片)',emptyText:'',id: 'jp',width: 100},
		            {xtype:'textfield',fieldLabel: '冲定位孔时间(每片)',emptyText:'',id: 'ck',width: 100},
		            {xtype:'textfield',fieldLabel: '叠片时间(每片)',emptyText:'',id: 'dp',width: 100},
		            {xtype:'textfield',fieldLabel: '层压时间',emptyText:'',id: 'cy',width: 100}]
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
	    title:"<p align='center'>层压规则维护</p>",
	    border:false,	
	    frame: true,
	    layout:'fit',
	    items: rightborder
	}]
	});

	//修改函数
	function modifywFn(){
		var jp = Ext.get("jp").dom.value.trim();
		var ck = Ext.get("ck").dom.value.trim();
		var dp = Ext.get("dp").dom.value.trim();
		var cy = Ext.get("cy").dom.value.trim();
		var regtext = /^[\+\-]?(\d{0,8}(\.\d{1,20})?)$/;
		if(jp==""){
			alert("请输入截半园化片时间");
		}else if(ck==""){
			alert("请输入冲定位孔时间");
		}else if(dp==""){
			alert("请输入叠片时间");
		}else if(cy==""){
			alert("请输入层压时间");
		}else{
			if(!regtext.test(jp)){
			alert("截半园化片时间必须为数字");
			return;
		}
		if(!regtext.test(ck)){
			alert("冲定位孔时间必须为数字");
			return;
		}
		if(!regtext.test(dp)){
			alert("叠片时间必须为数字");
			return;
		}
		if(!regtext.test(cy)){
			alert("层压时间必须为数字");
			return;
		}
			Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logiccengya.jsp',
						method:'post',
						params:{
							type:'EDIT',
							jp:jp,
							ck:ck,
							dp:dp,
							cy:cy
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
		 	url:'logiccengya.jsp',
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
	 	Ext.getCmp("jp").setValue(objtp.jp);
		Ext.getCmp("ck").setValue(objtp.ck);
		Ext.getCmp("dp").setValue(objtp.dp);
		Ext.getCmp("cy").setValue(objtp.cy);
	}

	initializeFn();
	
});