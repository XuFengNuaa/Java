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
		     title: '钻孔规格系数',
		     collapsible: false,
		     autoHeight:true,
		     layout:'form',
		     items:[{xtype:'textfield',fieldLabel: '2块组',emptyText:'',id: 'zk2',width: 100}, 
		            {xtype:'textfield',fieldLabel: '4块组',emptyText:'',id: 'zk4',width: 100},
		            {xtype:'textfield',fieldLabel: '6块组',emptyText:'',id: 'zk6',width: 100}]
			},{
			   xtype:'fieldset',
			   title: '批量系数',
			   collapsible: false,
			   autoHeight:true,
			   layout:'form',
			   items:[{xtype:'textfield',fieldLabel: '30~50',emptyText:'',id: 'js1',width: 100},
				      {xtype:'textfield',fieldLabel: '51~100',emptyText:'',id: 'js2',width: 100},
				      {xtype:'textfield',fieldLabel: '>100',emptyText:'',id: 'js3',width: 100}]
			},{
			   xtype:'fieldset',
			   title: '去毛刺时间(分钟)',
			   collapsible: false,
			   autoHeight:true,
			   layout:'form',
			   items:[{xtype:'textfield',fieldLabel: '手动',emptyText:'',id: 'sd',width: 100},
			          {xtype:'textfield',fieldLabel: '机动',emptyText:'',id: 'jd',width: 100}]
			},{
				   xtype:'fieldset',
				   title: '其他时间',
				   collapsible: false,
				   autoHeight:true,
				   layout:'form',
				   items:[{xtype:'textfield',fieldLabel: '单孔时间(分钟)',emptyText:'',id: 'dk',width: 100},
				          {xtype:'textfield',fieldLabel: '准结时间(小时)',emptyText:'',id: 'zj',width: 100}]
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
	    title:"<p align='center'>数控钻孔规则维护</p>",
	    border:false,	
	    frame: true,
	    layout:'fit',
	    items: rightborder
	}]
	});
	
	//修改函数
	function modifywFn(){
		var zk2 = Ext.get("zk2").dom.value.trim();
		var zk4 = Ext.get("zk4").dom.value.trim();
		var zk6 = Ext.get("zk6").dom.value.trim();
		var js1 = Ext.get("js1").dom.value.trim();
		var js2 = Ext.get("js2").dom.value.trim();
		var js3 = Ext.get("js3").dom.value.trim();
		var sd = Ext.get("sd").dom.value.trim();
		var jd = Ext.get("jd").dom.value.trim();
		var dk = Ext.get("dk").dom.value.trim();
		var zj = Ext.get("zj").dom.value.trim();
		var regtext = /^[\+\-]?(\d{0,8}(\.\d{1,20})?)$/;
		if(zk2==""){
			alert("请输入钻孔规格系数");
		}else if(zk4==""){
			alert("请输入钻孔规格系数");
		}else if(zk6==""){
			alert("请输入钻孔规格系数");
		}else if(js1==""){
			alert("请输入批量系数");
		}else if(js2==""){
			alert("请输入批量系数");
		}else if(js3==""){
			alert("请输入批量系数");
		}else if(sd==""){
			alert("请输入去毛刺时间");
		}else if(jd==""){
			alert("请输入去毛刺时间");
		}else if(dk==""){
			alert("请输入单孔时间");
		}else if(zj==""){
			alert("请输入准结时间");
		}else{
			if(!regtext.test(zk2)){
			alert("钻孔规格系数必须为数字");
			return;
		}
		if(!regtext.test(zk4)){
			alert("钻孔规格系数必须为数字");
			return;
		}
		if(!regtext.test(zk6)){
			alert("钻孔规格系数必须为数字");
			return;
		}
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
		if(!regtext.test(sd)){
			alert("去毛刺时间必须为数字");
			return;
		}
		if(!regtext.test(jd)){
			alert("去毛刺时间必须为数字");
			return;
		}
		if(!regtext.test(dk)){
			alert("单孔时间必须为数字");
			return;
		}
		if(!regtext.test(zj)){
			alert("准结时间必须为数字");
			return;
		}
			Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicshukongzuankong.jsp',
						method:'post',
						params:{
							type:'EDIT',
							zk2:zk2,
							zk4:zk4,
							zk6:zk6,
							js1:js1,
							js2:js2,
							js3:js3,
							sd:sd,
							jd:jd,
							dk:dk,
							zj:zj
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
		 	url:'logicshukongzuankong.jsp',
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
	 	Ext.getCmp("zk2").setValue(objtp.zk2);
		Ext.getCmp("zk4").setValue(objtp.zk4);
		Ext.getCmp("zk6").setValue(objtp.zk6);
		Ext.getCmp("js1").setValue(objtp.js1);
		Ext.getCmp("js2").setValue(objtp.js2);
		Ext.getCmp("js3").setValue(objtp.js3);
		Ext.getCmp("sd").setValue(objtp.sd);
		Ext.getCmp("jd").setValue(objtp.jd);
		Ext.getCmp("dk").setValue(objtp.dk);
		Ext.getCmp("zj").setValue(objtp.zj);
	}

	initializeFn();
	
});