var insertormodify=0;
Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=100000;//每页显示记录条数
	
	
	//定义领用人查询页面
	var search=new Ext.Search({
		region:'north',
        renderTo:'querydiv',
        state:false,
        columnArray:Stringquery,
		orderArray:Stringorder,
        height:123,
        handler:customQueryFn
    });   
      
	
    var record_materialgetperson= Ext.data.Record.create([    		
            {name:'id'},
    		{name:'materialgetperson'},
			{name:'materialgetname'}
			    ]); 
    
    //定义查询结果页面
	var hpObj={url:'logicmaterialgetperson.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_materialgetperson)
    });
   
	var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:false,
				loadMask:true,
		        columns: [sm,
		        	{
			            header: "号码",
			            dataIndex: "materialgetperson",
			            width:120,
			            sortable: true
			        }, {
			            header: "姓名",
			            dataIndex: "materialgetname",
			            width:100,
			            sortable: true
			        }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        stateId:'filetable',
		        autoScroll:true
    });
    
   
   //修改、添加弹出panel
     var formpanel = new Ext.FormPanel({
        id:'formpanel',
        labelWidth: 75, // label settings here cascade unless overridden
        columnWidth:.80,
        layout:"form",
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',
        items: [
                {xtype: 'textfield',fieldLabel:"号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",width:210,name:"materialgetperson",id:"materialgetperson",allowBlank:false,blankText:"号码不能为空，请填写!"},
                {xtype: 'textfield',fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",width:210,name:"materialgetname",id:"materialgetname",allowBlank:false,blankText:"姓名不能为空，请填写!"}	]
		    });
	//定义弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			modal:true,                                         
                width:430,
                height:200,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
				bodyBorder:true,
				resizable:false,
                items:[new Ext.Panel({
                		layout:'column',
                		frame:true,
                		height:230,
                		items:[formpanel,
			                {
								columnWidth: .10,
								bodyStyle:'padding:15px 0px 0',
								html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
								//&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;*<br></font>',
								border:false
							} ]
                })],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                	
							if(Ext.get("materialgetperson").dom.value==""){
								alert('号码不能为空！');
							}else if(Ext.get("materialgetname").dom.value==""){
									alert('姓名不能为空！');
								}else{
								if(insertormodify==0){
							       addsaveFn();
							   }else{
								   editsaveFn();}}
							}
	                },{ id:'cancelbtn',
						text: '取消',
						handler:cancelFn
	                },{ id:'returnbtn',
						text: '返回',
						handler:cancelFn
	                },{ id:'resetbtn',
						text: '重置',
						handler:resetFn
	                }]
    });
    
    
    var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
    var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});//按钮之间的竖线 
    var toolthree=new Ext.Toolbar.Separator({id:'toolthree'});//按钮之间的竖线    
    var toolfour=new Ext.Toolbar.Separator({id:'toolfour'});//按钮之间的竖线
    var toolfive=new Ext.Toolbar.Separator({id:'toolfive'});//按钮之间的竖线 
    var toolsix=new Ext.Toolbar.Separator({id:'toolsix'});//按钮之间的竖线    
          
  
     
    //组装页面
	new Ext.Viewport({
		layout:"border",
		id:"viewport",
		autoHeight:false,
		items:[search,
				{
				region:'center',
				layout:'fit',
				title:'查询结果',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:[toolone,{   
								id:'add',
					            text:"添加", 
					            handler:addFn ,
					            iconCls:"add",
					            tooltip:"添加一条领用人信息"
					        },tooltwo/*,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update",
					            tooltip:"修改一条领用人信息"
					        }*/,toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete",
					            tooltip:"删除一条领用人信息"
			        		},toolfour/*,{ 
								id:'detail',
					            text:"明细", 
					            handler: viewFn ,
					            iconCls:"detail",
					            tooltip:"查看厂商信息明细"
			        		},toolfive*/]
		        }),
				items:grid
		}]
	});
	
       
	//修改页面显示
	function editwinFn(){	
		var record =sm.getSelections();// 返回值为 Record 类型            	
		if(record.length==0){
            alert('请先选择要修改的领用人!');
            return;
        }else if(record.length!=1){
        	alert('每次只能修改一条领用人信息!');
            return;
        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>领用人信息修改</p>");
				win.show();//修改窗口显示
			    
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("returnbtn").hide();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				initializeFn();
				Ext.getCmp("materialgetperson").getEl().dom.readOnly  = false;
				Ext.getCmp("materialgetname").getEl().dom.readOnly  = false;
				insertormodify=insertormodify+1;
				}
			}       
    }
    
    	
 	//初始化赋值
	function initializeFn(){
		var record =sm.getSelections();//获得页面中选中信息
		var editid=record[0].json.id;
		//初始化设置
		var load=Ext.Ajax.request({
		 	url:'logicmaterialgetperson.jsp',
		 	method:'post',
	 		params:{type:'VIEW',id:editid},//传递userid参数
			success:successFn,
	 		failure:function(){
				alert('服务器出现错误请稍后再试！');
 			}
	  	});	
	}
	
	//初始化成功回调函数
	function successFn(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);								
		win.findById("materialgetperson").setValue(objtp.materialgetperson);
		win.findById("materialgetname").setValue(objtp.materialgetname);
	 		
	}

	//用户修改函数.保存
	function editsaveFn(){
	           	Ext.showProgressDialog("正在提交修改数据，请稍候...");
	           	var record =sm.getSelections();
	           	var editid=record[0].json.id;
				formpanel.form.doAction('submit',{
					 	url:'logicmaterialgetperson.jsp',
					 	method:'post',
				 		params:{type:'EDIT',id:editid},
				 		success : function(form,action){
							 if (action.result.msg=='ok'){
									Ext.hideProgressDialog();
									win.hide();
									customQueryFn();
									Ext.getCmp("pagebar")
									
									alert('已完成对领用人信息的修改！');
									insertormodify=0;
							}else if(action.result.msg=='repeat'){
									Ext.hideProgressDialog();
									alert("提示:该领用人已存在,不可重复!");
							}else{
									Ext.hideProgressDialog();
									alert("提示:修改失败");
									}
						 	},
							failure : function(form,action){
								Ext.hideProgressDialog();
								alert( "提示:'服务器出现错误请稍后再试！");
							 }});
		}
	
	//取消函数
	function cancelFn(){
	if(insertormodify==1){insertormodify=0;}
    	win.hide();
    }
   	
	//查询函数（操作完毕二次刷新）
    function customQueryFn(){   
   	    hpObj.extraParams.filter=search.getFilter();
    	hpObj.extraParams.order=search.getOrder();   	
    	hpObj.extraParams.type="QUERY";
    	//var pagebar = Ext.getCmp("pagebar");
    	var obj={params:{start:start,limit:limit}};
    	Ext.showProgressDialog("正在提交修改数据，请稍候...");
   		if(ds.load(obj)){
   			Ext.hideProgressDialog();
   		}
   		search.getQueryText();    		
	}
	
	
	
	//删除函数
	function delFn(){	
		var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
            alert('请先选择要删除的项!');
            return;
    	}
   		var id="";
		if(record.length!=0) {
	        var confirm=Ext.MessageBox.show({
					title:'提醒!',
					width:300,
					msg:"将删除所选领用人，确认吗？",
					buttons:{"ok":"确定","no":"取消"},
					icon:Ext.MessageBox.INFO,
					animEl:"test5",
					fn:function(btn){					              
						if (btn=='ok'){	
						   Ext.showProgressDialog();
						   	for(var i=0;i<record.length;i++){
								if(i!=0){
									id+=",";
								}
								id+="'"+record[i].json.id+"'";
							}					
							Ext.Ajax.request({
						   	url: 'logicmaterialgetperson.jsp',
						   	params: {type:'DEL',id:id},
						   	success: function(resp){
						   		Ext.hideProgressDialog();
						   		if(parseInt(resp.responseText)){
							   		for(var i=0;i<record.length;i++){
										ds.remove(record[i]);
									}
									alert('删除成功！');
									customQueryFn();
						   		}else{
						   			alert("删除失败！");				   
						   		}						   
						   	},
						   	failure: function(){
						   		Ext.hideProgressDialog();
								alert('服务器出现错误请稍后再试！');				   
						   	}
						});	    
				    	}
						else if (btn=='no'){						      
							}									
					}
			     });		  
		       };         
       	}
       	
       	//添加窗口显示
       	function addFn(){
       	  if(!win.isVisible()){
                formpanel.form.reset();
				win.setTitle("<p align='center'>添加领用人信息</p>")
				win.show();//添加窗口显示
				Ext.getCmp("resetbtn").show();
				Ext.getCmp('returnbtn').hide();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("materialgetperson").getEl().dom.readOnly  = false;
				Ext.getCmp("materialgetname").getEl().dom.readOnly  = false;
		 }
		} 
       	
     //添加保存
     function addsaveFn(){
                         	Ext.showProgressDialog("正在提交数据，请稍候...");
                      		formpanel.form.doAction('submit',{
							url : 'logicmaterialgetperson.jsp',
							method:'post',
							params : { type : 'ADD'},
							success : function(form,action){
							 if (action.result.msg=='ok'){
									Ext.hideProgressDialog();
									alert( "提示:添加完成");
									formpanel.form.reset();
									win.hide();
									customQueryFn();
									}else if(action.result.msg=='repeat'){
									Ext.hideProgressDialog();
									alert("提示:该领用人已存在,不可重复添加!");
									}else{
									Ext.hideProgressDialog();
									alert("提示:添加失败");
									}
						 	},
							failure : function(form,action){
								Ext.hideProgressDialog();
								alert( "提示:'服务器出现错误请稍后再试！");
							 }
						
					         }) 
					         }
	  function resetFn(){
	     formpanel.form.reset();
	      }
	      
	 //明细窗口显示function viewFn
		 
	               
 
	});
	

	

