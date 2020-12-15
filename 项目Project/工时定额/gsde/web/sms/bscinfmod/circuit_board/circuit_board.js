var insertormodify=0;
Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=100000;//每页显示记录条数
	
	
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
      
	
    var record_circuit_board= Ext.data.Record.create([    		
    		{name:'id'},
			{name:'name'},
			{name:'time_one'},
			{name:'time_two'},
			{name:'remark'}
			    ]); 
    
    //定义查询结果页面
	var hpObj={url:'logic_circuit_board.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_circuit_board)
    });
   
	var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:false,
				loadMask:true,
		        columns: [sm,
		            {
			            header: "名称",
			            dataIndex: "name",
			            width:120,
			            sortable: true
			        }, {
			            header: "二厂",
			            dataIndex: "time_one",
			            width:100,
			            sortable: true
			        }, {
			            header: "微电子",
			            width:120,
			            dataIndex: "time_two",
			            sortable: true
		        	} ,{
			            header: "备注",
			            dataIndex: "remark",
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
               {xtype: 'textfield',fieldLabel:"名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称",width:210,name:"name",id:"name",allowBlank:false,blankText:"名称不能为空，请填写!"},
               {xtype: 'textfield',fieldLabel:"二&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;厂",width:210,name:"time_one",id:"time_one"},
               {xtype: 'textfield',fieldLabel:"微&nbsp;电&nbsp;&nbsp;子",width:210,name:"time_two",id:"time_two"},
               {xtype: 'textfield',fieldLabel:"备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注",width:210,name:"remark",id:"remark"}	]
		    });
	//定义弹出窗口
   var win = new Ext.Window({
   			id:'win',
   			modal:true,                                         
               width:430,
               height:300,
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
               		height:230,
               		items:[formpanel,
			                {
								columnWidth: .10,
								bodyStyle:'padding:15px 0px 0',
								html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
								
								border:false
							} ]
               })],
               buttons: [{
                       id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                	
							
								if(insertormodify==0){
							       addsaveFn();
							   }else{
								   editsaveFn();
								   }
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
					            tooltip:"添加一条工时信息"
					        },tooltwo,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update",
					            tooltip:"修改一条工时信息"
					        },toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete",
					            tooltip:"删除一条工时信息"
			        		},toolfour]
		        }),
		       /* bbar: new Ext.PagingToolbar({
		        	id:'pagebar',
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
           alert('请先选择要修改的工时信息!');
           return;
       }else if(record.length!=1){
       	alert('每次只能修改一条工时信息!');
           return;
       }else{ if(!win.isVisible()){
               win.setTitle("<p align='center'>厂商信息修改</p>");
				win.show();//修改窗口显示
			    		     
				$('#email').keypad();
			    
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("returnbtn").hide();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				initializeFn();
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
		 	url:'logic_circuit_board.jsp',
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
		win.findById("name").setValue(objtp.name);
		win.findById("time_one").setValue(objtp.time_one);
		win.findById("time_two").setValue(objtp.time_two);
		win.findById("remark").setValue(objtp.remark);
	 		
	}

	//用户修改函数.保存
	function editsaveFn(){
	           	Ext.showProgressDialog("正在提交修改数据，请稍候...");
	           	var record =sm.getSelections();
	           	var editid=record[0].json.id;
				formpanel.form.doAction('submit',{
					 	url:'logic_circuit_board.jsp',
					 	method:'post',
				 		params:{type:'EDIT',id:editid},
				 		success : function(form,action){
							 if (action.result.msg=='ok'){
									Ext.hideProgressDialog();
									win.hide();
									customQueryFn();
									Ext.getCmp("pagebar")
									
									alert('已完成对工时信息的修改！');
									insertormodify=0;
							}else if(action.result.msg=='repeat'){
									Ext.hideProgressDialog();
									alert("提示:该工时信息已存在,不可重复!");
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
		$('#email').keypad('destroy');		
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
					msg:"将删除所选工时信息，确认吗？",
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
						   	url: 'logic_circuit_board.jsp',
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
							
			$('#email').keypad();				
			
			Ext.getCmp("resetbtn").show();
			Ext.getCmp('returnbtn').hide();
			Ext.getCmp("cancelbtn").show();
			Ext.getCmp("savebtn").show();
		 }
		} 
      	
    //添加保存
    function addsaveFn(){
                        	Ext.showProgressDialog("正在提交数据，请稍候...");
                     		formpanel.form.doAction('submit',{
							url : 'logic_circuit_board.jsp',
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
									alert("提示:该工时信息已存在,不可重复添加!");
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
	            alert('请先选择要查看的工时信息!');
	            return;
	        }else if(record.length!=1){
	        	alert('每次只能查看一条工时信息!');
	            return;
	        }else{ if(!win.isVisible()){
               win.setTitle("<p align='center'>工时信息明细</p>")
				win.show();//明细窗口显示
				
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").hide();
				Ext.getCmp("cancelbtn").hide();
				Ext.getCmp("returnbtn").show();
				initializeFn();
				Ext.getCmp("name").getEl().dom.readOnly  = true;
				Ext.getCmp("time_one").getEl().dom.readOnly  = true;
				Ext.getCmp("remark").getEl().dom.readOnly  = true;
				}
			} 
	    } 
		 
	               
 
	});
	