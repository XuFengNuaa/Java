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
      
    var record_material= Ext.data.Record.create([    		
    		{name:'id'},
			{name:'bianhao'},
			{name:'paihao'},
			{name:'mingcheng'},
			{name:'rechuli'},
			{name:'yinyong'},
			{name:'leixing'},
			{name:'yingdu'},
			{name:'kangla'},
			{name:'yanshen'},
			{name:'chongji'},
			{name:'midu'},
			{name:'repengzhang'},
			{name:'daore'},
			{name:'qufu'},
			{name:'beizhu'}
		]); 
    
    //定义查询结果页面
	var hpObj={url:'logicmaterial.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_material)
    });
   
	var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:false,
				loadMask:true,
		        columns: [sm,
		        	{
			            header: "材料编号",
			            dataIndex: "bianhao",
			            width:100,
			            align:'center',
			            hidden:true,
			            sortable: true
			        }, {
			            header: "材料牌号",
			            dataIndex: "paihao",
			            width:100,
			            align:'center',
			            sortable: true
			        },{
			            header: "材料名称",
			             align:'center',
			            dataIndex: "mingcheng",
			            width:100,
			            sortable: true
			        }, {
			            header: "热处理状态",
			            align:'center',
			            width:100,
			            dataIndex: "rechuli",
			            sortable: true
		        	}, {
			            header: "材料类型",
			            dataIndex: "leixing",
			             hidden:true,
			             align:'center',
			             width:100,
			            sortable: true
			        }, {
			            header: "引用标准",
			            align:'center',
			             hidden:true,
			            dataIndex: "yinyong",
			            width:100,
			            sortable: true
			        }, {
			            header: "硬度",
			            dataIndex: "yingdu",
			             align:'center',
			             width:110,
			            sortable: true
			        }, {
			            header: "抗拉强度(MPa)",
			             align:'center',
			              hidden:true,
			            dataIndex: "kangla",
			             width:110,
			            sortable: true
			        }, {
			            header: "延伸率",
			            dataIndex: "yanshen",
			             align:'center',
			             width:125,
			            sortable: true
			        }, {
			            header: "冲击韧性(J)",
			            dataIndex: "chongji",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "密度",
			            dataIndex: "midu",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "热膨胀系数",
			            dataIndex: "repengzhang",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "导热率",
			            dataIndex: "daore",
			             align:'center',
			             width:100,
			            sortable: true
			        },{
			            header: "屈服强度(MPa)",
			             align:'center',
			            dataIndex: "qufu",
			             width:100,
			            sortable: true
			        },{
			            header: "备注",
			             align:'center',
			              hidden:true,
			            dataIndex: "beizhu",
			             width:110,
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
        labelWidth: 85, // label settings here cascade unless overridden
        layout:"column",
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',
        items: [
         {
	    columnWidth:.5,					              
	    layout: 'form',
	    labelAlign:"right",
	    items: [{
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[{
	                 	xtype:'textfield',
			            fieldLabel: '材料牌号',
			            name: 'paihao',
			            width:100,
			            id: 'paihao'
	                 }]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{
             	xtype:'textfield',
	            fieldLabel: '材料编号',
	            name: 'bianhao',
	            width:100,
	            id: 'bianhao'
             },{
             	xtype:'textfield',
	            fieldLabel: '材料名称',
	            name: 'mingcheng',
	            width:100,
	            id: 'mingcheng'
             },{
                xtype:'textfield',
                fieldLabel: '热处理状态',
                name: 'rechuli',
                width:100,
                id: 'rechuli'
            },{
               xtype:'textfield',
               fieldLabel: '引用标准',
                name: 'yinyong',
                width:100,
                id: 'yinyong'
            },{
        	xtype: 'textfield',
        	fieldLabel: '材料类型',
        	name: 'leixing',
        	id: 'leixing',
        	width:100
        },{
			xtype : 'textfield',
			id : 'yingdu',
			name:'yingdu',
			fieldLabel : '硬度',
			width:100
		},{
			xtype : 'textfield',
			id : 'company',
			fieldLabel : '制造商',
			width:100
	       }
          ]},
		    {columnWidth:.5,
		    layout: 'form',
		    labelAlign:"right",
		    items: [{
            xtype:'textfield',
            fieldLabel: '抗拉强度(MPa)',
            width:100,
            name: 'kangla',
            id: 'kangla'
    		},{
            xtype:'textfield',
            fieldLabel: '延伸率',
            width:100,
            name: 'yanshen',
            id: 'yanshen'
    		},{
            xtype:'textfield',
            fieldLabel: '冲击韧性(J)',
            width:100,
            name: 'chongji',
            id: 'chongji'
    		},{
            xtype:'textfield',
            fieldLabel: '密度',
            width:100,
            name: 'midu',
            id: 'midu'
    		},{
            xtype:'textfield',
            fieldLabel: '热膨胀系数',
            width:100,
            name: 'repengzhang',
            id: 'repengzhang'
    		},{
        	xtype: 'textfield',
        	fieldLabel: '导热率',
        	name: 'daore',
        	id: 'daore',
        	width: 100
        },{
			xtype : 'textfield',
			id : 'qufu',
			name:'qufu',
			fieldLabel : '屈服强度(MPa)',
			width:100
	      }
                ]},
                {
            columnWidth:1,
		    layout: 'form',
		    labelAlign:"right",
		    items:[{
		    	xtype:'textarea',
                fieldLabel: '备注',
                name: 'beizhu',
                id: 'beizhu',
                width:300,
                height:72
		    	}]
                }
                ]
     });
   
	//定义弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			modal:true,                                         
                width:500,
                height:380,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
				bodyBorder:true,
				resizable:false,
                items:[new Ext.Panel({
                		layout:'column',
                		columnWidth:1,
                		frame:true,
                		height:350,
                		items:[formpanel]
                })
                ],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                var paihao = Ext.get("paihao").dom.value;
		                /*var paihao = Ext.get("paihao").dom.value;	
		                var mingcheng = Ext.get("mingcheng").dom.value;	*/
		                if(paihao == ""){
		                	Ext.MessageBox.alert("提醒","材料牌号不能为空");	
		                }/*else if(paihao == ""){
		                	Ext.MessageBox.alert("提醒","材料牌号不能为空");
		                }else if(mingcheng == ""){
		                	Ext.MessageBox.alert("提醒","材料名称不能为空");
		                }*/else{
		                	if(insertormodify == 0){
		                		addsaveFn();
		                	}else{
		                		editsaveFn();
		                	}
		                }
	                }},{ id:'cancelbtn',
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
					            tooltip:"添加一条材料信息"
					        },tooltwo,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update",
					            tooltip:"修改一条材料信息"
					        },toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete",
					            tooltip:"删除一条材料信息"
			        		},toolfour,{ 
								id:'detail',
					            text:"明细", 
					            handler: viewFn ,
					            iconCls:"detail",
					            tooltip:"查看材料信息明细"
			        		},toolfive]
		        }),
		        bbar: new Ext.PagingToolbar({
		        	id:'pagebar',
				    pageSize:limit,
				    afterPageText: '页共{0}页',
	        		beforePageText: '第',
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				}),
				items:grid
		}]
	});
	    
	//判断用户   
	if(isStoreManager()||isTeamLeader()||isBoss()){
		toolone.hide();
	     tooltwo.hide();
	     toolthree.hide();
	    Ext.getCmp("add").hide();
	    Ext.getCmp("edit-btn").hide();
	    Ext.getCmp("del").hide();
	}
    //添加保存
     function addsaveFn(){
          Ext.showProgressDialog("正在提交数据，请稍候...");
          formpanel.form.doAction('submit',{
          url:'logicmaterial.jsp',
          method:'post',
          params : { type : 'ADD'},
          success:function(form,action){
          	if(action.result.msg=='ok'){
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","添加成功");
          		formpanel.form.reset();
          		win.hide();
          		customQueryFn();
          	}else if(action.result.msg=='repeat'){
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","材料信息已经存在，不能重复添加");
          	}else{
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","添加失败");
          	}
          },
          failure:function(form,action){
          	Ext.hideProgressDialog();
          	Ext.MessageBox.alert("提示","服务器出现错误，请稍后再试");
          }
          });  
       }
       
	//修改页面显示
	function editwinFn(){	
		var record =sm.getSelections();// 返回值为 Record 类型            	
		if(record.length==0){
           Ext.MessageBox.alert("提示",'请先选择要修改的材料信息!');
            return;
        }else if(record.length!=1){
        	Ext.MessageBox.alert("提示",'每次只能修改一条材料信息!');
            return;
        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>材料信息修改</p>");
				win.show();//修改窗口显示
				Ext.getCmp("resetbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").hide();
				insertormodify = insertormodify+1;
				formpanel.form.reset();
				initializeFn();
		     
				Ext.getCmp("bianhao").getEl().dom.readOnly  = false;
				Ext.getCmp("paihao").getEl().dom.readOnly  = false;
				Ext.getCmp("mingcheng").getEl().dom.readOnly  = false;
				Ext.getCmp("rechuli").getEl().dom.readOnly  = false;
				Ext.getCmp("yinyong").getEl().dom.readOnly  = false;
				Ext.getCmp("leixing").getEl().dom.readOnly  = false;
				Ext.getCmp("yingdu").getEl().dom.readOnly  = false;
				Ext.getCmp("kangla").getEl().dom.readOnly  = false;
				Ext.getCmp("yanshen").getEl().dom.readOnly  = false;
				Ext.getCmp("chongji").getEl().dom.readOnly  = false;
				Ext.getCmp("midu").getEl().dom.readOnly  = false;
				Ext.getCmp("repengzhang").getEl().dom.readOnly  = false;
				Ext.getCmp("daore").getEl().dom.readOnly  = false;
				Ext.getCmp("qufu").getEl().dom.readOnly  = false;
				Ext.getCmp("company").getEl().dom.readOnly  = false;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = false;
				}
			}       
    }
    
    	
 	//初始化赋值
	function initializeFn(){
		var record =sm.getSelections();//获得页面中选中信息
		var editid=record[0].json.id;
		//初始化设置
		var load=Ext.Ajax.request({
		 	url:'logicmaterial.jsp',
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
	 	win.findById("bianhao").setValue(objtp.bianhao);
	 	win.findById("paihao").setValue(objtp.paihao);
		win.findById("mingcheng").setValue(objtp.mingcheng);
		win.findById("rechuli").setValue(objtp.rechuli);
		win.findById("yinyong").setValue(objtp.yinyong);
		win.findById("leixing").setValue(objtp.leixing);
		win.findById("yingdu").setValue(objtp.yingdu);
		win.findById("kangla").setValue(objtp.kangla);
		win.findById("yanshen").setValue(objtp.yanshen);
		win.findById("chongji").setValue(objtp.chongji);
		win.findById("midu").setValue(objtp.midu);
		win.findById("repengzhang").setValue(objtp.repengzhang);
		win.findById("daore").setValue(objtp.daore);
		win.findById("qufu").setValue(objtp.qufu);
		win.findById("company").setValue(objtp.company);
		win.findById("beizhu").setValue(objtp.beizhu);
	}

	//用户修改函数.保存
	function editsaveFn(){
	           	Ext.showProgressDialog("正在提交修改数据，请稍候...");
	           	var record =sm.getSelections();
	           	var editid=record[0].json.id;
				formpanel.form.doAction('submit',{
					 	url:'logicmaterial.jsp',
					 	method:'post',
				 		params:{type:'EDIT',id:editid},
				 		success : function(form,action){
							 if (action.result.msg=='ok'){
									Ext.hideProgressDialog();
									win.hide();
									customQueryFn();
									Ext.MessageBox.alert('提示','已完成对材料信息的修改！');
									insertormodify=0;
							}else if(action.result.msg=='repeat'){
									Ext.hideProgressDialog();
									Ext.MessageBox.alert("提示","该材料已存在,不可重复!");
							}else{
									Ext.hideProgressDialog();
									Ext.MessageBox.alert("提示","修改失败!");
									}
						 	},
							failure : function(form,action){
								Ext.hideProgressDialog();
								Ext.MessageBox.alert("提示","服务器出现错误请稍后再试！");
							 }});
		}
	
	//取消函数
	function cancelFn(){
	if(insertormodify==1){insertormodify=0;}
    	win.hide();	
    	Ext.getCmp("bianhao").getEl().dom.readOnly  = false;
    	Ext.getCmp("paihao").getEl().dom.readOnly  = false;
		Ext.getCmp("mingcheng").getEl().dom.readOnly  = false;
		Ext.getCmp("rechuli").getEl().dom.readOnly  = false;
		Ext.getCmp("yinyong").getEl().dom.readOnly  = false;
		Ext.getCmp("leixing").getEl().dom.readOnly  = false;
		Ext.getCmp("yingdu").getEl().dom.readOnly  = false;
		Ext.getCmp("kangla").getEl().dom.readOnly  = false;
		Ext.getCmp("yanshen").getEl().dom.readOnly  = false;
		Ext.getCmp("chongji").getEl().dom.readOnly  = false;
		Ext.getCmp("midu").getEl().dom.readOnly  = false;
		Ext.getCmp("repengzhang").getEl().dom.readOnly  = false;
		Ext.getCmp("daore").getEl().dom.readOnly  = false;
		Ext.getCmp("qufu").getEl().dom.readOnly  = false;
		Ext.getCmp("company").getEl().dom.readOnly  = false;
		Ext.getCmp("beizhu").getEl().dom.readOnly  = false;
    }
   	
	//查询函数（操作完毕二次刷新）
    function customQueryFn(){   
   	    hpObj.extraParams.filter=search.getFilter();
    	hpObj.extraParams.order=search.getOrder();   	
    	hpObj.extraParams.type="QUERY";
    	//var pagebar = Ext.getCmp("pagebar");
    	var obj={params:{start:start,limit:limit}};
   		ds.load(obj);
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
					msg:"将删除所选材料信息，确认吗？",
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
						   	url: 'logicmaterial.jsp',
						   	params: {type:'DEL',id:id},
						   	success: function(resp){
						   		Ext.hideProgressDialog();
						   		if(parseInt(resp.responseText)){
							   		for(var i=0;i<record.length;i++){
										ds.remove(record[i]);
									}
									Ext.MessageBox.alert('提示','删除成功！');
									customQueryFn();
						   		}else{
						   			Ext.MessageBox.alert('提示','删除失败！');			   
						   		}						   
						   	},
						   	failure: function(){
						   		Ext.hideProgressDialog();
						   		Ext.MessageBox.alert('提示','服务器出现错误请稍后再试！');			   
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
				win.setTitle("<p align='center'>添加工件材料信息</p>")
				win.show();//添加窗口显示
				Ext.getCmp("resetbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").hide();
		 }
		} 
       	
   
	  function resetFn(){
	     formpanel.form.reset();
	      }
	      
	 //明细窗口显示
	  function viewFn(){
	    	var record =sm.getSelections();// 返回值为 Record 类型            	
			if(record.length==0){
	            alert('请先选择要查看的工件材料!');
	            return;
	        }else if(record.length!=1){
	        	alert('每次只能查看一条工件材料信息!');
	            return;
	        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>工件材料信息明细</p>")
				win.show();//明细窗口显示
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").hide();
				Ext.getCmp("cancelbtn").hide();
				Ext.getCmp("returnbtn").show();
				
				formpanel.form.reset();
				initializeFn();
		     
				Ext.getCmp("bianhao").getEl().dom.readOnly  = true;
				Ext.getCmp("paihao").getEl().dom.readOnly  = true;
				Ext.getCmp("mingcheng").getEl().dom.readOnly  = true;
				Ext.getCmp("rechuli").getEl().dom.readOnly  = true;
				Ext.getCmp("yinyong").getEl().dom.readOnly  = true;
				Ext.getCmp("leixing").getEl().dom.readOnly  = true;
				Ext.getCmp("yingdu").getEl().dom.readOnly  = true;
				Ext.getCmp("kangla").getEl().dom.readOnly  = true;
				Ext.getCmp("yanshen").getEl().dom.readOnly  = true;
				Ext.getCmp("chongji").getEl().dom.readOnly  = true;
				Ext.getCmp("midu").getEl().dom.readOnly  = true;
				Ext.getCmp("repengzhang").getEl().dom.readOnly  = true;
				Ext.getCmp("daore").getEl().dom.readOnly  = true;
				Ext.getCmp("qufu").getEl().dom.readOnly  = true;
				Ext.getCmp("company").getEl().dom.readOnly  = true;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = true;
				}
			} 
	    } 
	});
	

	

