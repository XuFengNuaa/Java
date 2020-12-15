
Ext.onReady(function(){
var simple = new Ext.FormPanel({
         title:"<p align='center'>个人信息查看</p>",
         layout:"form",
         id:'simpleinfo',
         hideLabels:false,
         labelAlign:"left",
         labelWidth: 120,
         buttonAlign:"center",
         frame:true,
         bodyStyle:'padding:20px 10px 0',
         style:'margin:10% 20%',
         width:455,
         height:170,
         autuHeight:true,
         defaults: {width: 250},
         defaultType: 'textfield',
         waitTitle:"请稍候",
		waitMsg:"正在查询数据，请稍候。。。",
        items: [{fieldLabel:"工&nbsp;&nbsp;&nbsp;号",labelStyle:"margin-top:8px;margin-left:20px",style:"margin-top:5px",name:"stuff_name",id:"stuff_num",readOnly: true},
                {fieldLabel:"姓&nbsp;&nbsp;&nbsp;名",labelStyle:"margin-top:8px;margin-left:20px",style:"margin-top:5px",name:"realname",id:"realname",readOnly: true}]
    });
    simple.render(document.body);
    Ext.Ajax.request({
		 	url:'logicusrinfmod.jsp',//文件路径
		 	method:'post',//提交方法post
	 		params:{type:'USRVIEW'},
	 		success: successFn,
		 	failure:function(){
				alert('提示：服务器出现错误请稍后再试！');
		 	}
	  	});
	  	simple.show();
	  	
    
    //初始化成功回调函数
	function successFn(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);
		
		Ext.getCmp("stuff_num").setValue(objtp.stuff_num);
		Ext.getCmp("realname").setValue(objtp.realname);
		//Ext.getCmp("group_num").setValue(objtp.group_num);
		//Ext.getCmp("order_num").setValue(objtp.order_num);

	}
    

});