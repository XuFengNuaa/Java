Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=14;//每页显示记录条数
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
			{name:'realname'}
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
			            header: "用户姓名",
			            dataIndex: "realname",
			            style:'width:10%',
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
                {fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',width:180,name:"password",id:"password",style : "background: #C1C1C1;", inputType:'password',readOnly: true}
        ]
    });
    
	//定义修改弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			modal:true,
                width:350,
                height:200,
                closeAction:'hide',
                plain: true,
                title:"<p align='center'>用户信息修改</p>",
                buttonAlign:"center",
				bodyBorder:true,
                items:[new Ext.Panel({
                		layout:'column',
                		frame:true,
                		height:200,
                		items:[FormPanel,
			                {
								columnWidth:.10,
								bodyStyle:'padding:20px 10px 10',
								//html:'<font color="#FF0000">*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*</font>',
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
				            },'-'/*,{ 								
					            id:'output',
					            text:"导出", 
					            handler:outFn,
					            iconCls:"wlk"
			        		},'-'*/]
		        }),
		        bbar: new Ext.PagingToolbar({
				    pageSize:limit,
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				}),
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
		var stuff_num=record[0].get("stuff_num");
		//alert(stuff_num);
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
		win.findById("password").setValue(objtp.password);
	}
	
	//用户修改函数
	function editFn(){
		var stuff_numjs=Ext.get("stuff_num").dom.value;
		var stuff_numReg=/^([A-Z]|[a-z]|[\d])*$/;
		
		var realnamejs=Ext.get("realname").dom.value;
		var realnameReg=/^[0-9 a-z　A-Z\u4e00-\u9fa5]+$/;
		
		var record =sm.getSelections();
		var editid="'"+record[0].json.id+"'";
				
		if(Ext.get("stuff_num").dom.value==""){
			alert('提示:用户工号不能为空！');
		}else if(Ext.get("realname").dom.value==""){
			alert('提示:用户姓名不能为空！');
		}else if(Ext.get("stuff_num").dom.value.length>66){
			alert('提示:用户工号长度不能超过66个字！');
		}else if(Ext.get("realname").dom.value.length>66){
			alert('提示:用户姓名长度不能超过66个字！');
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
			window.open("userxml.jsp?filter="+encodeURI(hpObj.extraParams.filter)+"&result="+encodeURI(result));
		}
	}
	
});
