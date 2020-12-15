var insertormodify=0;
Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=28;//每页显示记录条数
	
	//定义厂商查询页面
	var search=new Ext.Search({
		region:'north',
        renderTo:'querydiv',
        state:false,
        columnArray:Stringquery,
		orderArray:Stringorder,
        height:123,
        handler:customQueryFn
    });   
	
    var record_company= Ext.data.Record.create([    		
    		{name:'id'},
			{name:'leibie'},
			{name:'mj1'},
			{name:'mj2'},
			{name:'mj3'},
			{name:'mj4'},
			{name:'mj5'}
			    ]); 
    
    //定义查询结果页面
	var hpObj={url:'logictufu.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_company)
    });
   
	var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:false,
				loadMask:true,
		        columns: [sm,
		        	{header: "类别(时间min)",dataIndex: "leibie",width:100,sortable: true}, 
		        	{header: "50mm*100mm以下",dataIndex: "mj1",width:150,sortable: true},
		        	{header: "50mm*100mm至100mm*150mm",dataIndex: "mj2",width:200,sortable: true},
		        	{header: "100mm*150mm至250mm*300mm",dataIndex: "mj3",width:200,sortable: true},
		        	{header: "250mm*300mm至300mm*350mm",dataIndex: "mj4",width:200,sortable: true},
		        	{header: "300mm*350mm以上",dataIndex: "mj5",width:150,sortable: true}],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        stateId:'filetable',
		        autoScroll:true
    });
   
   //修改、添加弹出panel
     var formpanel = new Ext.FormPanel({
        id:'formpanel',
        labelWidth: 200, 
        columnWidth:.90,
        layout:"form",
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',
        items: [
                {xtype: 'textfield',fieldLabel:"类别(时间min)",width:210,name:"leibie",id:"leibie",allowBlank:false},
                {xtype: 'textfield',fieldLabel:"50mm*100mm以下",width:210,name:"mj1",id:"mj1",allowBlank:false},
                {xtype: 'textfield',fieldLabel:"50mm*100mm至100mm*150mm",width:210,name:"mj2",id:"mj2",allowBlank:false},
                {xtype: 'textfield',fieldLabel:"100mm*150mm至250mm*300mm",width:210,name:"mj3",id:"mj3",allowBlank:false},
                {xtype: 'textfield',fieldLabel:"250mm*300mm至300mm*350mm",width:210,name:"mj4",id:"mj4",allowBlank:false},
                {xtype: 'textfield',fieldLabel:"300mm*350mm以上",width:210,name:"mj5",id:"mj5",allowBlank:false}
                ]
		    });
	//定义弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			modal:true,                                         
                width:550,
                height:275,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
				bodyBorder:true,
				resizable:false,
				listeners:{
				 'hide':function(){
				  if($.keypad._keypadShowing)
				   $('#email').keypad('destroy');				 
				 }
				},
                items:[new Ext.Panel({
                		layout:'column',
                		frame:true,
                		height:200,
                		items:[formpanel,
			                {
								columnWidth: .10,
								bodyStyle:'padding:15px 0px 0',
								//html:'<font color="#FF0000">&nbsp;*&nbsp;*<br/><br/>&nbsp;*&nbsp;*</font>',
								border:false
							} ]
                })],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                	
							var leibie=Ext.get("leibie").dom.value;
							var mj1=Ext.get("mj1").dom.value;
							var mj2=Ext.get("mj2").dom.value;
							var mj3=Ext.get("mj3").dom.value;
							var mj4=Ext.get("mj4").dom.value;
							var mj5=Ext.get("mj5").dom.value;
																						
							if(leibie==""){
								alert('类别名称不能为空！');
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
					            tooltip:"添加一条信息"
					        },tooltwo,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update",
					            tooltip:"修改一条信息"
					        },toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete",
					            tooltip:"删除一条信息"
			        		},toolfour,{ 
								id:'detail',
					            text:"明细", 
					            handler: viewFn ,
					            iconCls:"detail",
					            tooltip:"查看明细"
			        		},toolfive]
		        }),
		        bbar: new Ext.PagingToolbar({
		        	id:'pagebar',
				    pageSize:limit,
				    store:ds,
				     afterPageText: '页共{0}页',
		        	beforePageText: '第',
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
            alert('请先选择要修改的工时信息!');
            return;
        }else if(record.length!=1){
        	alert('每次只能修改一条信息!');
            return;
        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>工时信息修改</p>");
				win.show();//修改窗口显示
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("returnbtn").hide();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				initializeFn();
				Ext.getCmp("leibie").getEl().dom.readOnly  = false;
				Ext.getCmp("mj1").getEl().dom.readOnly  = false;
				Ext.getCmp("mj2").getEl().dom.readOnly  = false;
				Ext.getCmp("mj3").getEl().dom.readOnly  = false;
				Ext.getCmp("mj4").getEl().dom.readOnly  = false;
				Ext.getCmp("mj5").getEl().dom.readOnly  = false;
				
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
		 	url:'logictufu.jsp',
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
		win.findById("leibie").setValue(objtp.leibie);
		win.findById("mj1").setValue(objtp.mj1);
		win.findById("mj2").setValue(objtp.mj2);
		win.findById("mj3").setValue(objtp.mj3);
		win.findById("mj4").setValue(objtp.mj4);
		win.findById("mj5").setValue(objtp.mj5);
	 		
	}

	//用户修改函数.保存
	function editsaveFn(){
	           	Ext.showProgressDialog("正在提交修改数据，请稍候...");
	           	var record =sm.getSelections();
	           	var editid=record[0].json.id;
				formpanel.form.doAction('submit',{
					 	url:'logictufu.jsp',
					 	method:'post',
				 		params:{type:'EDIT',id:editid},
				 		success : function(form,action){
							 if (action.result.msg=='ok'){
									Ext.hideProgressDialog();
									win.hide();
									customQueryFn();
									
									alert('已完成修改！');
									insertormodify=0;
							}else if(action.result.msg=='repeat'){
									Ext.hideProgressDialog();
									alert("提示:该零件类型信息已存在,不可重复!");
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
					msg:"将删除所选零件信息，确认吗？",
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
						   	url: 'logictufu.jsp',
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
				win.setTitle("<p align='center'>添加信息</p>")
				win.show();//添加窗口显示
				Ext.getCmp("resetbtn").show();
				Ext.getCmp('returnbtn').hide();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("leibie").getEl().dom.readOnly  = false;
				Ext.getCmp("mj1").getEl().dom.readOnly  = false;
				Ext.getCmp("mj2").getEl().dom.readOnly  = false;
				Ext.getCmp("mj3").getEl().dom.readOnly  = false;
				Ext.getCmp("mj4").getEl().dom.readOnly  = false;
				Ext.getCmp("mj5").getEl().dom.readOnly  = false;
		 }
		} 
       	
     //添加保存
     function addsaveFn(){
             	Ext.showProgressDialog("正在提交数据，请稍候...");
          		formpanel.form.doAction('submit',{
				url : 'logictufu.jsp',
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
						alert("提示:该零件类型信息已存在,不可重复添加!");
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
	      
	 //明细窗口显示
	  function viewFn(){
	    	var record =sm.getSelections();// 返回值为 Record 类型            	
			if(record.length==0){
	            alert('请先选择要查看的零件类型信息!');
	            return;
	        }else if(record.length!=1){
	        	alert('每次只能查看一条零件类型信息!');
	            return;
	        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>信息明细</p>")
				win.show();//明细窗口显示
				
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").hide();
				Ext.getCmp("cancelbtn").hide();
				Ext.getCmp("returnbtn").show();
				initializeFn();
				Ext.getCmp("leibie").getEl().dom.readOnly  = true;
				Ext.getCmp("mj1").getEl().dom.readOnly  = true;
				Ext.getCmp("mj2").getEl().dom.readOnly  = true;
				Ext.getCmp("mj3").getEl().dom.readOnly  = true;
				Ext.getCmp("mj4").getEl().dom.readOnly  = true;
				Ext.getCmp("mj5").getEl().dom.readOnly  = true;
				}
			} 
	    } 
		 
	               
 
	});
	