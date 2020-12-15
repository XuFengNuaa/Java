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
			{name:'xinghao'},
			{name:'xingcheng'},
			{name:'chicun'},
			{name:'zhoushu'},
			{name:'niuju'},
			{name:'jingeili'},
			{name:'zhuansumax'},
			{name:'zhuansumin'},
			{name:'vmax'},
			{name:'vmin'},
			{name:'jingdu'},
			{name:'power'},
			{name:'company'},
			{name:'beizhu'}
		]); 
    
    //定义查询结果页面
	var hpObj={url:'logicmachine.jsp',method: 'POST',extraParams:{}};
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
			            header: "机床编号",
			            dataIndex: "bianhao",
			            width:100,
			            align:'center',
			            hidden:true,
			            sortable: true
			        }, {
			            header: "机床型号",
			             align:'center',
			            dataIndex: "xinghao",
			            width:100,
			            sortable: true
			        }, {
			            header: "厂商",
			             align:'center',
			            dataIndex: "company",
			             width:100,
			            sortable: true
			        },{
			            header: "工作台行程",
			            hidden:true,
			            align:'center',
			            width:100,
			            dataIndex: "xingcheng",
			            sortable: true
		        	}, {
			            header: "工作台尺寸",
			            align:'center',
			             hidden:true,
			            dataIndex: "chicun",
			            width:100,
			            sortable: true
			        }, {
			            header: "控制轴数",
			            dataIndex: "zhoushu",
			             hidden:true,
			             align:'center',
			             width:100,
			            sortable: true
			        }, {
			            header: "最大主轴扭矩(N*M)",
			            dataIndex: "niuju",
			             align:'center',
			             width:110,
			            sortable: true
			        }, {
			            header: "主轴进给力(N)",
			             align:'center',
			              hidden:true,
			            dataIndex: "jingeili",
			             width:110,
			            sortable: true
			        }, {
			            header: "最高转速(r/min)",
			            dataIndex: "zhuansumax",
			             align:'center',
			             width:125,
			            sortable: true
			        }, {
			            header: "最低转速(r/min)",
			            dataIndex: "zhuansumin",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "最大进给速度(mm/min)",
			            dataIndex: "vmax",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "最小进给速度(mm/min)",
			            dataIndex: "vmin",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "可达到精度(mm)",
			            dataIndex: "jingdu",
			             align:'center',
			             width:100,
			            sortable: true
			        },{
			            header: "机床功率",
			             align:'center',
			            dataIndex: "power",
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
        labelWidth: 90, // label settings here cascade unless overridden
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
			            fieldLabel: '机床型号',
			            name: 'xinghao',
			            width:100,
			            id: 'xinghao'
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
                fieldLabel: '机床编号',
                name: 'bianhao',
                width:100,
                id: 'bianhao'
            },{
                xtype:'textfield',
                fieldLabel: '工作台行程',
                name: 'xingcheng',
                width:100,
                id: 'xingcheng'
            },{
               xtype:'textfield',
               fieldLabel: '工作台尺寸',
                name: 'chicun',
                width:100,
                id: 'chicun'
            },{
        	xtype: 'textfield',
        	fieldLabel: '控制轴数',
        	name: 'zhoushu',
        	id: 'zhoushu',
        	width:100
        },{
			xtype : 'textfield',
			id : 'power',
			name:'power',
			fieldLabel : '机床功率',
			width:100
		},{
			xtype : 'textfield',
			id : 'company',
			fieldLabel : '制造商',
			width:100
	       },{
			xtype : 'textfield',
			id : 'jingdu',
			fieldLabel : '可达到精度(mm)',
			width:100
	      }
          ]},
		    {columnWidth:.5,
		    layout: 'form',
		    labelAlign:"right",
		    items: [{
            xtype:'textfield',
            fieldLabel: '最大主轴扭矩(N*m)',
            width:100,
            name: 'niuju',
            id: 'niuju'
    		},{
            xtype:'textfield',
            fieldLabel: '主轴进给力(N)',
            width:100,
            name: 'jingeili',
            id: 'jingeili'
    		},{
            xtype:'textfield',
            fieldLabel: '最高转速(r/min)',
            width:100,
            name: 'zhuansumax',
            id: 'zhuansumax'
    		},{
            xtype:'textfield',
            fieldLabel: '最低转速(r/min)',
            width:100,
            name: 'zhuansumin',
            id: 'zhuansumin'
    		},{
            xtype:'textfield',
            fieldLabel: '最大进给速度(mm/min)',
            width:100,
            name: 'vmax',
            id: 'vmax'
    		},{
        	xtype: 'textfield',
        	fieldLabel: '最小进给速度(mm/min)',
        	name: 'vmin',
        	id: 'vmin',
        	width: 100
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
                width:550,
                height:400,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
				bodyBorder:true,
				resizable:false,
                items:[new Ext.Panel({
                		layout:'column',
                		columnWidth:1,
                		frame:true,
                		height:390,
                		items:[formpanel]
                })
                ],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                var bianhao = Ext.get("xinghao").dom.value;	
		                /*var xinghao = Ext.get("xinghao").dom.value;	*/
		                if(bianhao == ""){
		                	Ext.MessageBox.alert("提醒","机床型号不能为空");	
		                }/*else if(xinghao == ""){
		                	Ext.MessageBox.alert("提醒","机床型号不能为空");
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
					            tooltip:"添加一条机床信息"
					        },tooltwo,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update",
					            tooltip:"修改一条机床信息"
					        },toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete",
					            tooltip:"删除一条机床信息"
			        		},toolfour,{ 
								id:'detail',
					            text:"明细", 
					            handler: viewFn ,
					            iconCls:"detail",
					            tooltip:"查看机床信息明细"
			        		},toolfive]
		        }),
		        bbar: new Ext.PagingToolbar({
		        	id:'pagebar',
		        	afterPageText: '页共{0}页',
		        	beforePageText: '第',
				    pageSize:limit,
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
          url:'logicmachine.jsp',
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
          		Ext.MessageBox.alert("提示","机床信息已经存在，不能重复添加");
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
           Ext.MessageBox.alert("提示",'请先选择要修改的机床!');
            return;
        }else if(record.length!=1){
        	Ext.MessageBox.alert("提示",'每次只能修改一条机床信息!');
            return;
        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>机床信息修改</p>");
				win.show();//修改窗口显示
				Ext.getCmp("resetbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").hide();
				insertormodify = insertormodify+1;
				formpanel.form.reset();//所有修改面板的panel
				initializeFn();
		     
				Ext.getCmp("bianhao").getEl().dom.readOnly  = false;
				Ext.getCmp("xinghao").getEl().dom.readOnly  = false;
				Ext.getCmp("xingcheng").getEl().dom.readOnly  = false;
				Ext.getCmp("chicun").getEl().dom.readOnly  = false;
				Ext.getCmp("zhoushu").getEl().dom.readOnly  = false;
				Ext.getCmp("niuju").getEl().dom.readOnly  = false;
				Ext.getCmp("jingeili").getEl().dom.readOnly  = false;
				Ext.getCmp("zhuansumax").getEl().dom.readOnly  = false;
				Ext.getCmp("zhuansumin").getEl().dom.readOnly  = false;
				Ext.getCmp("vmax").getEl().dom.readOnly  = false;
				Ext.getCmp("vmin").getEl().dom.readOnly  = false;
				Ext.getCmp("jingdu").getEl().dom.readOnly  = false;
				Ext.getCmp("power").getEl().dom.readOnly  = false;
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
		 	url:'logicmachine.jsp',
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
		win.findById("xinghao").setValue(objtp.xinghao);
		win.findById("xingcheng").setValue(objtp.xingcheng);
		win.findById("chicun").setValue(objtp.chicun);
		win.findById("zhoushu").setValue(objtp.zhoushu);
		win.findById("niuju").setValue(objtp.niuju);
		win.findById("jingeili").setValue(objtp.jingeili);
		win.findById("zhuansumax").setValue(objtp.zhuansumax);
		win.findById("zhuansumin").setValue(objtp.zhuansumin);
		win.findById("vmax").setValue(objtp.vmax);
		win.findById("vmin").setValue(objtp.vmin);
		win.findById("jingdu").setValue(objtp.jingdu);
		win.findById("power").setValue(objtp.power);
		win.findById("company").setValue(objtp.company);
		win.findById("beizhu").setValue(objtp.beizhu);
	}

	//用户修改函数.保存
	function editsaveFn(){
	           	Ext.showProgressDialog("正在提交修改数据，请稍候...");
	           	var record =sm.getSelections();
	           	var editid=record[0].json.id;
				formpanel.form.doAction('submit',{
					 	url:'logicmachine.jsp',
					 	method:'post',
				 		params:{type:'EDIT',id:editid},
				 		success : function(form,action){
							 if (action.result.msg=='ok'){
									Ext.hideProgressDialog();
									win.hide();
									customQueryFn();
									Ext.MessageBox.alert('提示','已完成对机床信息的修改！');
									insertormodify=0;
							}else if(action.result.msg=='repeat'){
									Ext.hideProgressDialog();
									Ext.MessageBox.alert("提示","该机床已存在,不可重复!");
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
    	formpanel.form.reset();
    	Ext.getCmp("bianhao").getEl().dom.readOnly  = false;
		Ext.getCmp("xinghao").getEl().dom.readOnly  = false;
		Ext.getCmp("xingcheng").getEl().dom.readOnly  = false;
		Ext.getCmp("chicun").getEl().dom.readOnly  = false;
		Ext.getCmp("zhoushu").getEl().dom.readOnly  = false;
		Ext.getCmp("niuju").getEl().dom.readOnly  = false;
		Ext.getCmp("jingeili").getEl().dom.readOnly  = false;
		Ext.getCmp("zhuansumax").getEl().dom.readOnly  = false;
		Ext.getCmp("zhuansumin").getEl().dom.readOnly  = false;
		Ext.getCmp("vmax").getEl().dom.readOnly  = false;
		Ext.getCmp("vmin").getEl().dom.readOnly  = false;
		Ext.getCmp("jingdu").getEl().dom.readOnly  = false;
		Ext.getCmp("power").getEl().dom.readOnly  = false;
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
					msg:"将删除所选机床，确认吗？",
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
						   	url: 'logicmachine.jsp',
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
				win.setTitle("<p align='center'>添加机床信息</p>")
				Ext.getCmp("resetbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").hide();
				win.show();//添加窗口显示
		 }
		} 
       	
   
	  function resetFn(){
	     formpanel.form.reset();
	      }
	      
	 //明细窗口显示
	  function viewFn(){
	    	var record =sm.getSelections();// 返回值为 Record 类型            	
			if(record.length==0){
	            alert('请先选择要查看的机床!');
	            return;
	        }else if(record.length!=1){
	        	alert('每次只能查看一条机床信息!');
	            return;
	        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>机床信息明细</p>")
				win.show();//明细窗口显示
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").hide();
				Ext.getCmp("cancelbtn").hide();
				Ext.getCmp("returnbtn").show();
				
				formpanel.form.reset();
				initializeFn();
		     
				Ext.getCmp("bianhao").getEl().dom.readOnly  = true;
				Ext.getCmp("xinghao").getEl().dom.readOnly  = true;
				Ext.getCmp("xingcheng").getEl().dom.readOnly  = true;
				Ext.getCmp("chicun").getEl().dom.readOnly  = true;
				Ext.getCmp("zhoushu").getEl().dom.readOnly  = true;
				Ext.getCmp("niuju").getEl().dom.readOnly  = true;
				Ext.getCmp("jingeili").getEl().dom.readOnly  = true;
				Ext.getCmp("zhuansumax").getEl().dom.readOnly  = true;
				Ext.getCmp("zhuansumin").getEl().dom.readOnly  = true;
				Ext.getCmp("vmax").getEl().dom.readOnly  = true;
				Ext.getCmp("vmin").getEl().dom.readOnly  = true;
				Ext.getCmp("jingdu").getEl().dom.readOnly  = true;
				Ext.getCmp("power").getEl().dom.readOnly  = true;
				Ext.getCmp("company").getEl().dom.readOnly  = true;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = true;
				}
			} 
	    } 
	});
	

	

