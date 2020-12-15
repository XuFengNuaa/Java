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
    		     title: '制字符',
    		     collapsible: false,
    		     autoHeight:true,
    		     layout:'form',
    		     items:[{xtype:'textfield',fieldLabel: '绷网',emptyText:'',id: 'kk1',width: 100}, 
			            {xtype:'textfield',fieldLabel: '刷胶',emptyText:'',id: 'kk2',width: 100},
			            {xtype:'textfield',fieldLabel: '贴胶膜',emptyText:'',id: 'kk3',width: 100},
			            {xtype:'textfield',fieldLabel: '烘干',emptyText:'',id: 'kk4',width: 100},
			            {xtype:'textfield',fieldLabel: '曝光',emptyText:'',id: 'kk5',width: 100},
			            {xtype:'textfield',fieldLabel: '固定清洗',emptyText:'',id: 'kk6',width: 100},
			            {xtype:'textfield',fieldLabel: '烘干',emptyText:'',id: 'kk7',width: 100}, 
			            {xtype:'textfield',fieldLabel: '图形转移',emptyText:'',id: 'kk8',width: 100},
			            {xtype:'textfield',fieldLabel: '定位图号',emptyText:'',id: 'kk9',width: 100},
			            {xtype:'textfield',fieldLabel: '修网',emptyText:'',id: 'kk10',width: 100},
			            {xtype:'textfield',fieldLabel: '翻网',emptyText:'',id: 'kk11',width: 100},
			            {xtype:'textfield',fieldLabel: '单面板漏印',emptyText:'',id: 'kk12',width: 100},
			            {xtype:'textfield',fieldLabel: '双面板漏印',emptyText:'',id: 'kk13',width: 100}, 
			            {xtype:'textfield',fieldLabel: '面板移印',emptyText:'',id: 'kk14',width: 100},
			            {xtype:'textfield',fieldLabel: '贴保护膜',emptyText:'',id: 'kk15',width: 100},
			            {xtype:'textfield',fieldLabel: '清洗',emptyText:'',id: 'kk16',width: 100}]
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
	    title:"<p align='center'>制字符规则维护(分钟)</p>",
	    border:false,	
	    frame: true,
	    layout:'fit',
	    items: rightborder
	}]
	});
	
	//修改函数
	function modifywFn(){		
		Ext.showProgressDialog();
				Ext.Ajax.request({
					url:'logiczhizifu.jsp',
					method:'post',
					params:{
						type:'EDIT'
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
		Ext.getCmp("kk1").setValue(20);
		Ext.getCmp("kk2").setValue(6);
		Ext.getCmp("kk3").setValue(30);
		Ext.getCmp("kk4").setValue(10);
		Ext.getCmp("kk5").setValue(5);
		Ext.getCmp("kk6").setValue(5);
		Ext.getCmp("kk7").setValue(20);
		Ext.getCmp("kk8").setValue(30);
		Ext.getCmp("kk9").setValue(10);
		Ext.getCmp("kk10").setValue(20);
		Ext.getCmp("kk11").setValue(40);
		Ext.getCmp("kk12").setValue(4);
		Ext.getCmp("kk13").setValue(6);
		Ext.getCmp("kk14").setValue(3);
		Ext.getCmp("kk15").setValue(5);
		Ext.getCmp("kk16").setValue(2);
	}

	initializeFn();
	
});