Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=1000000;//每页显示记录条数
	var result = 0;
	
	//定义查询页面
	var search=new Ext.Search({
		region:'north',
        renderTo:'querydiv',
        state:0,        
        columnArray:Stringquery,
		orderArray:Stringorder,
        height:123,          
        handler:customQueryFn
    });
    
    var record_user= Ext.data.Record.create([    		
    		{name:'id'},
			{name:'stuff_num'},
			{name:'rolename'},
			{name:'realname'},
			{name:'group_num'},
			{name:'remark'}
    ]); 
    
    //定义结果页面
	var hpObj={url:'logicusrmod.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_user)
    });
    ds.load();
    var hpObjAni={url:'logicusrmod.jsp',method:'POST',extraParams:{}};
	var store_user=new Ext.data.Store({
	    proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObjAni)),
	    pruneModifiedRecords:true,
		reader:new Ext.data.JsonReader({            
	        root:'root'
	    },record_user)
	});
	var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        loadMask:true,
		        enableColumnMove:false,
		        columns: [sm,
		        	{
			            header: "用户工号",
			            dataIndex: "stuff_num",
			            style:'width:10%',
			            sortable: true
			        }, {
			            header: "角色",
			            dataIndex: "rolename",
			            style:'width:10%',
			            sortable: true
			        },{
			            header: "用户姓名",
			            dataIndex: "realname",
			            style:'width:10%',
			            sortable: true
			        }, {
			            header: "联系电话",
			            dataIndex: "group_num",
			            style:'width:10%',
			            sortable: true
			        }, {
			            header: "备注",
			            dataIndex: "remark",
			            style:'width:20%',
			            sortable: true
			        }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        stateId:'filetable',
		        autoScroll:true
    });
    
 	//修改用户信息窗口
     var FormPanel = new Ext.FormPanel({
        labelWidth: 75, // label settings here cascade unless overridden
        columnWidth:.90,
        layout:"form",
        hideLabels:false,
        border:false,
        labelAlign:"right",
        buttonAlign:"center",
        bodyStyle:'padding:15px 10px 10',
        items: [{fieldLabel:"工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号",xtype: 'textfield',width:180,name:"stuff_num",id:"stuff_num",style : "background: #C1C1C1;", allowBlank:false,readOnly: true},
                {fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",xtype: 'textfield',width:180,name:"realname",id:"realname",allowBlank:false,blankText:"姓名不能为空，请填写!",regex:/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/,regexText:'姓名只能由汉字、字母、数字组成！'},
                {fieldLabel:"联&nbsp;系&nbsp;电&nbsp;话",xtype: 'textfield',width:180,name:"group_num",id:"group_num",allowBlank:false,blankText:"组别不能为空，请填写!",regex:/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/,regexText:'组别只能由汉字、字母、数字组成！'},
                {fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',width:180,name:"password",id:"password",style : "background: #C1C1C1;", inputType:'password',readOnly: true},
                {
		            xtype:'fieldset',
		            title: '用户角色<font color="#FF0000">*&nbsp;*</font>',
		            labelWidth:5,
		            width:315,
		            autoHeight:true,
		            items:[{
			               layout:'table',
			               columnWidth:.92,
			               defaults: {bodyStyle:'padding:20px'},
						   layoutConfig: {columns:3},
						   anchor:'100%-43',
						   items:[{
					                 xtype:"checkbox",
					                 boxLabel:"一般用户",//显示在复选框右边的文字
					                 inputValue:'1',
					                 name:"User",
					                 id:'1',
					                 style:"margin-left:15px;margin-top:5px"
					             },{
					                 xtype:"checkbox",
					                 boxLabel:"专家",//显示在复选框右边的文字
					                 inputValue:'3',
					                 name:"TeamLeader",
					                 id:'3',
					                 style:"margin-left:15px;margin-top:5px"
					             },{
					                 xtype:"checkbox",
					                 boxLabel:"领导",//显示在复选框右边的文字
					                 inputValue:'4',
					                 name:"Boss",
					                 id:'4',
					                 style:"margin-left:15px;margin-top:5px"
					             },{
					                 xtype:"checkbox",
					                 boxLabel:"系统管理员",//显示在复选框右边的文字
					                 inputValue:'5',
					                 name:"Sys",
					                 id:'5',
					                 style:"margin-left:15px;margin-top:5px"
					             },{
					                 xtype:"checkbox",
					                 boxLabel:"程序员",//显示在复选框右边的文字
					                 inputValue:'6',					            
					                 name:"Programmer",
					                 id:'6',
					                 style:"margin-left:15px;margin-top:5px"
					             },{
					                 xtype:"checkbox",
					                 boxLabel:"技术人员",//显示在复选框右边的文字
					                 inputValue:'10',
					                 name:"wc",
					                 id:'10',
					                 style:"margin-left:15px;margin-top:5px"
					             }/*{
					                 xtype:"checkbox",
					                 boxLabel:"模具管理员",//显示在复选框右边的文字
					                 inputValue:'11',
					                 name:"wc",
					                 id:'11',
					                 style:"margin-left:15px;margin-top:5px"
					             }/*{
					                 xtype:"checkbox",
					                 boxLabel:"仪表管理员",//显示在复选框右边的文字
					                 inputValue:'12',
					                 name:"wc",
					                 id:'12',
					                 style:"margin-left:15px;margin-top:5px"
					             }*/]
					     }]
		       		},
		       		{},
		       		{fieldLabel:"备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注",xtype: 'textarea',width:180,height:60,name:"remark",id:"remark",allowBlank:true}
        ]
    });
    
	//定义修改弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			modal:true,
                width:375,
                height:410,
                closeAction:'hide',
                plain: true,
                title:"<p align='center'>用户信息修改</p>",
                buttonAlign:"center",
				bodyBorder:true,
                items:[new Ext.Panel({
                		layout:'column',
                		frame:true,
                		height:340,
                		items:[FormPanel,
			                {
								columnWidth:.10,
								bodyStyle:'padding:20px 10px 10',
								html:'<font color="#FF0000">*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*</font>',
								border:false
							}
                		]
                })],
                buttons: [{
		                text:'保存',
		                handler:editFn
	                },{
						text: '重置',
						handler:resetFn
	                },{
	                    text: '密码复位',
	                    handler: function(){
			                    win.findById("password").setValue("111111");	
			                    alert('提示:用户密码复位：111111，保存后生效！',function(buttion){
			                    	if(buttion=="ok"){
			                    		win.hide();
			                    		win.show();	
			                    	}
			                    });
                		}
                	}
               	]
    });
    
    //组装页面
	new Ext.Viewport({
		layout:"border",
		autoHeight:false,
		items:[search,
				{
				region:'center',
				layout:'fit',
				title:'用户查询结果',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:['-',
							{ 								
					            id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn,
					            iconCls:"update"
					        },'-',{ 								
					            id:'del',
					            text:"删除", 
					            handler: delFn,
					            iconCls:"remove"
				            },'-',{ 								
					            id:'output',
					            text:"导出", 
					            handler:outFn,
					            iconCls:"wlk"
			        		},'-']
		        }),
		       /* bbar: new Ext.PagingToolbar({
				    pageSize:limit,
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				}),*/
				items:grid
		}]
	});
       
	//修改页面显示
	function editwinFn(){
		var record =sm.getSelections();// 返回值为 Record 类型            	
		if(record.length==0){
            alert('提示:请先选择要修改的用户!');
            return;
        }else if(record.length!=1){
        	alert('提示:每次只能对一个用户进行修改!');
            return;
        }else{
        	if(record[0].json.userlevel==5){
        		alert('提示:此工号为系统管理员，不能进行修改!');
        		return false;
        	}
	    	if(!win.isVisible()){
				win.show();//修改窗口显示
				win.center();
				initializeFn();
			} 
    	}
	}
	
	//初始化赋值
	function initializeFn(){
		var record =sm.getSelections();
		var stuff_num="'"+record[0].json.stuff_num+"'";
		var load=Ext.Ajax.request({
		 	url:'logicusrmod.jsp',
		 	method:'post',
	 		params:{type:'VIEW',stuff_num:stuff_num},//传递stuff_num参数
			success:successFn,
	 		failure:function(){
				alert('提示:服务器出现错误请稍后再试！');
 			}
	  	});	
	}
	
	//初始化成功回调函数
	function successFn(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);	
		win.findById("stuff_num").setValue(objtp.stuff_num);
		win.findById("realname").setValue(objtp.realname);
		win.findById("group_num").setValue(objtp.group_num);
		win.findById("remark").setValue(objtp.remark);
		win.findById("password").setValue(objtp.password);
		
		var rolenumber = parseInt(objtp.rolenumber);
	 	
	 	Ext.getCmp('1').setValue('false');
		Ext.getCmp('3').setValue('false');
		Ext.getCmp('4').setValue('false');
		//Ext.getCmp('StoreManager').setValue('false');
		Ext.getCmp('5').setValue('false');
		Ext.getCmp('6').setValue('false');
		//Ext.getCmp('zongku').setValue('false');
		//Ext.getCmp('fenku').setValue('false');
		Ext.getCmp('10').setValue('false');
		//Ext.getCmp('mj').setValue('false');
		//Ext.getCmp('yb').setValue('false');
		var usrlevels = new String(objtp.usrlevels);
		
		if(rolenumber>0){
			if(objtp.role1!=undefined){
				if(objtp.role1=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role1=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role1=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role1=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role1=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role1=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}
			
			if(objtp.role3!=undefined){
				if(objtp.role3=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role3=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role3=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role3=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role3=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role3=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}
			if(objtp.role4!=undefined){
				if(objtp.role4=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role4=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role4=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role4=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role4=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role4=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}
			if(objtp.role5!=undefined){
				if(objtp.role5=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role5=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role5=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role5=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role5=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role5=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}
			if(objtp.role6!=undefined){
				if(objtp.role6=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role6=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role6=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role6=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role6=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role6=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}
			if(objtp.role7!=undefined){
				if(objtp.role7=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role7=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role7=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role7=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role7=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role7=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}if(objtp.role8!=undefined){
				if(objtp.role8=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role8=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role8=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role8=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role8=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role8=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}if(objtp.role9!=undefined){
				if(objtp.role7=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role9=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role9=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role9=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role9=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role9=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}if(objtp.role10!=undefined){
				if(objtp.role10=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role10=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role10=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role10=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role10=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role10=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}if(objtp.role11!=undefined){
				if(objtp.role11=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role11=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role11=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role11=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role11=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role11=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}if(objtp.role12!=undefined){
				if(objtp.role12=="1"){
					Ext.getCmp('1').setValue('true');
				}else if(objtp.role12=="3"){
					Ext.getCmp('3').setValue('true');
				}else if(objtp.role12=="4"){
					Ext.getCmp('4').setValue('true');
				}else if(objtp.role12=="5"){
					Ext.getCmp('5').setValue('true');
				}else if(objtp.role12=="6"){
					Ext.getCmp('6').setValue('true');
				}else if(objtp.role12=="10"){
					Ext.getCmp('10').setValue('true');
				}
			}
			
		}
	}
	
	//用户修改函数
	function editFn(){
		var stuff_numjs=Ext.get("stuff_num").dom.value;
		var stuff_numReg=/^([A-Z]|[a-z]|[\d])*$/;
		
		var realnamejs=Ext.get("realname").dom.value;
		var realnameReg=/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/;
		
		var groupjs=Ext.get("group_num").dom.value;
		var groupReg=/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/;
		
		var remarkjs=Ext.get("remark").dom.value;
		
		var record =sm.getSelections();
		var editid="'"+record[0].json.id+"'";
		
		var order_num = Ext.getCmp("order_num").value;
		
		if(Ext.get("stuff_num").dom.value==""){
			alert('提示:用户工号不能为空！');
		}else if(Ext.get("realname").dom.value==""){
			alert('提示:用户姓名不能为空！');
		}else if(Ext.get("group_num").dom.value==""){
			alert('提示:联系电话不能为空！');
		}else if(Ext.get("stuff_num").dom.value.length>66){
			alert('提示:用户工号长度不能超过66个字！');
		}else if(Ext.get("realname").dom.value.length>66){
			alert('提示:用户姓名长度不能超过66个字！');
		}else if(Ext.get("remark").dom.value.length>66){
			alert('提示:备注长度不能超过66个字！');
		}else if(!Ext.getCmp('1').getValue()&&!Ext.getCmp('3').getValue()&&!Ext.getCmp('4').getValue()&&!Ext.getCmp('2').getValue()&&!Ext.getCmp('5').getValue()&&!Ext.getCmp('6').getValue()&&!Ext.getCmp('7').getValue()){
			alert('提示:请至少选择一个角色名称！');
		}else{
			if(stuff_numjs!="" && !stuff_numReg.test(stuff_numjs)){
				alert('提示:工号只能由字母、数字、下划线组成！');
			}else if(realnamejs!="" && !realnameReg.test(realnamejs)){
				alert('提示:姓名只能由汉字、字母、数字组成！');
			}else{	
				Ext.showProgressDialog("正在提交修改数据，请稍候...");
				FormPanel.form.doAction('submit',{
					 	url:'logicusrmod.jsp',
					 	method:'post',
				 		params:{type:'EDIT',uid:editid},
						success:function(form,action){
							Ext.hideProgressDialog();								
							win.hide();
							customQueryFn();
							alert('提示:已完成对用户信息的修改！');
					 	},
						 //提交失败的回调函数
					 	failure:function(){
					 		Ext.hideProgressDialog();
							alert('错误:服务器出现错误请稍后再试！');
					 	}
				});
			}
		}
	}
	
	//重置函数
	function resetFn(){
		Ext.MessageBox.confirm('提示','放弃对该用户信息的所有修改，确认吗？',function(btn) {
			if (btn=='yes'){
				initializeFn(); 
			}
			win.hide();
			win.show();
		});  		
	}
	
	//查询函数
    function customQueryFn(){
    	Ext.showProgressDialog();
    	hpObj.extraParams.filter=search.getFilter();
    	hpObj.extraParams.order=search.getOrder();
    	hpObj.extraParams.type="QUERY";
    	var obj={params:{start:start,limit:limit}};
   		if(ds.load(obj)){
   			Ext.hideProgressDialog();
   		}            
   		result = search.getQueryText();  
	}
	
	
	//删除函数
	function delFn(){
		var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
            alert('提示:请先选择要删除的用户!');
            return;
    	}
   		var stuff_num="";
		if(record.length!=0) {
           	Ext.MessageBox.confirm('提示','你将永久删除所选用户，确认吗？',function(btn) {
				if(btn == 'yes') {
                   	Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){
						if(i!=0){
							stuff_num+=",";
						}
						stuff_num+="'"+record[i].json.stuff_num+"'";
					}
		   			Ext.Ajax.request({
					   	url: 'logicusrmod.jsp',
					   	params: {type:'DEL',stuff_num:stuff_num},
					   	success:function(result){
							if (result.responseText.indexOf("success") != -1){
								Ext.hideProgressDialog();	
								alert('提示:所选用户已删除成功！');
								customQueryFn();
							}else{
								alert('错误：不能删除自己的工号！');
								Ext.hideProgressDialog();
							}
					 	},
					   	failure: function(){
					   		Ext.hideProgressDialog();
							alert('提示:服务器出现错误请稍后再试！');				   
					   	}
					});	           
                }
			});
       	}		
	}
	
	//用户信息导出Excel
	function outFn(){
		for(var i=1;i<5;i++){
			result = result.replace("&nbsp;&nbsp;&nbsp;","/→  ");
			result = result.replace("<br>",+i+"次结果中查询：");
		}
		if(ds.getCount()==0){
			alert('数据为空，无需导出Excel!');
			return;
		}else{
			hpObj.extraParams.filter=search.getFilter();
		var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
            alert('请先选择要导出的内容!');
            return;
    	}
   		var stuff_num1="";
		if(record.length!=0) {	for(var i=0;i<record.length;i++){
								if(i!=0){
									stuff_num1+=",";
								}
								stuff_num1+="'"+record[i].json.stuff_num+"'";
								//alert(record[i].json.id);
							}}
		//	alert(stuff_num1);
			window.open("userxml.jsp?filter="+encodeURI(hpObj.extraParams.filter)+"&result="+encodeURI(result)+"&stuff_num1="+encodeURI(stuff_num1)+"");
		}
	}
	
});
