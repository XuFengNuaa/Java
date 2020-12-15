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
    	
    var record_cutpara= Ext.data.Record.create([    		
    		{name:'id'},//铣削参数id
			{name:'zhijing'},//刀具直径
			{name:'ap'},//切深
			{name:'ae'},//切宽
			{name:'vf'},//进给速度
			{name:'vc'},//切削速度
			{name:'zzzs'},//主轴转速
			{name:'mcjg'},//每齿进给量
			{name:'chishu'}//刀齿数
		]); 	
    
    //定义查询结果页面数据源
	var hpObj={url:'logicpp_cut.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_cutpara)
    });

	var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:false,
				loadMask:true,
		        columns: [sm,
		        	{
			            header: "刀具直径(mm)",
			            align:'center',
			            dataIndex: "zhijing",
			            width:90,
			            sortable: true
			        },{
			            header: "切深(mm)",
			            dataIndex: "ap",
			             align:'center',
			             width:110,
			            sortable: true
			        }, {
			            header: "切宽(mm)",
			             align:'center',
			            dataIndex: "ae",
			             width:110,
			            sortable: true
			        },{
			            header: "进给速度(mm/min)",
			            dataIndex: "vf",
			             align:'center',
			             width:125,
			            sortable: true
			        }, {
			            header: "切削速度(m/min)",
			            dataIndex: "vc",
			             align:'center',
			             width:125,
			            sortable: true
			        }, {
			            header: "主轴转速(r/min)",
			            dataIndex: "zzzs",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "每齿进给量(mm/齿)",
			            dataIndex: "mcjg",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "刀齿数",
			            dataIndex: "chishu",
			             align:'center',
			             width:125,
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
        labelWidth:115, // label settings here cascade unless overridden
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
		        	xtype: 'textfield',
		        	fieldLabel: '刀具直径(mm)',
		        	name: 'zhijing',
		        	id: 'zhijing',
		        	width: 90
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
	    	columnWidth:1,					              
            layout: 'column',
            items:[{
            	columnWidth:.85,					              
                layout: 'form',
                labelAlign:"right",
                 items:[{
		        	xtype: 'textfield',
		        	fieldLabel: '切削深度(mm)',
		        	name: 'ap',
		        	id: 'ap',
		        	width: 90
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
	    	columnWidth:1,					              
            layout: 'column',
            items:[{
            	columnWidth:.85,					              
                layout: 'form',
                labelAlign:"right",
                 items:[{
		        	xtype: 'textfield',
		        	fieldLabel: '切削宽度(mm)',
		        	name: 'qiekuan',
		        	id: 'qiekuan',
		        	width: 90
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
	    	columnWidth:1,					              
            layout: 'column',
            items:[{
            	columnWidth:.85,					              
                layout: 'form',
                labelAlign:"right",
                 items:[{
		        	xtype: 'textfield',
		        	fieldLabel: '刀齿数',
		        	name: 'chishu',
		        	id: 'chishu',
		        	width: 90
		        }]
            },{
            columnWidth:.15,					              
            layout: 'form',
            labelAlign:"right",
            bodyStyle:'padding:5px 0px 0',
            html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
            border:false
            }]
        }
          ]},
		    {columnWidth:.5,
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
			        	xtype: 'textfield',
			        	fieldLabel: '主轴转速(r/min)',
			        	name: 'zzzs',
			        	id: 'zzzs',
			        	width: 90
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
		    	columnWidth:1,					              
	                layout: 'column',
	                items:[{
	                	columnWidth:.85,					              
		                layout: 'form',
		                labelAlign:"right",
		                 items:[{
				        	xtype: 'textfield',
				        	fieldLabel: '每齿进给量(mm/齿)',
				        	name: 'mcjg',
				        	id: 'mcjg',				        	
				        	width: 90
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
            fieldLabel: '进给速度(mm/min)',
            width:90,
            name: 'vf',
            id: 'vf'
    		},{
                xtype:'textfield',
                fieldLabel: '切削速度(m/min)',
                width:90,
                name: 'vc',
                id: 'vc'     		
    		}
                ]},
           {
            columnWidth:1,
		    layout: 'form',
		    labelAlign:"right",
		    items:[
		    	{
		    	xtype:'textarea',
                fieldLabel: '备注',
                name: 'beizhu',
                id: 'beizhu',
                width:340,
                height:75
		    }]}
                ]
     });
   
	//定义弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			modal:true,                                         
                width:550,
                height:300,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
				bodyBorder:true,
				resizable:false,
                items:[new Ext.Panel({
                		layout:'column',
                		columnWidth:1,
                		frame:true,
                		height:450,
                		items:[formpanel]
                })
                ],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                	if(insertormodify == 0){
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
					            tooltip:"添加一条切削参数信息"
					        },tooltwo,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update",
					            tooltip:"修改一条切削参数信息"
					        },toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete",
					            tooltip:"删除一条切削参数信息"
			        		},toolfour,{ 
								id:'detail',
					            text:"明细", 
					            handler: viewFn ,
					            iconCls:"detail",
					            tooltip:"查看切削参数信息明细"
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
	    
    //添加保存
     function addsaveFn(){
          Ext.showProgressDialog("正在提交数据，请稍候...");
          var zhijing = Ext.get("zhijing").dom.value.trim();
          var ap = Ext.get("ap").dom.value.trim();
          var ae = Ext.get("qiekuan").dom.value.trim();
          var zzzs = Ext.get("zzzs").dom.value.trim();
          var mcjg = Ext.get("mcjg").dom.value.trim();
          var chishu = Ext.get("chishu").dom.value.trim();
          
          var vf = Ext.get("vf").dom.value.trim();
          var vc = Ext.get("vc").dom.value.trim();
          var beizhu = Ext.get("beizhu").dom.value.trim();
          if(zhijing==""){
          	Ext.MessageBox.alert('提示','刀具直径不能为空');
          	return;
          }else if(ap==""){
          	Ext.MessageBox.alert('提示','切削深度不能为空');
          	return;
          }else if(ae==""){
          	Ext.MessageBox.alert('提示','切削宽度不能为空');
          	return;
          }else if(zzzs==""){
          	Ext.MessageBox.alert('提示','主轴转速不能为空');
          	return;
          }else if(mcjg==""){
          	Ext.MessageBox.alert('提示','每齿进给量不能为空');
          	return;
          }else if(chishu==""){
        	Ext.MessageBox.alert('提示','刀齿数不能为空');
          	return;
          }
          formpanel.form.doAction('submit',{
          url:'logicpp_cut.jsp',
          method:'post',
          params : { type : 'ADD',zhijing:zhijing,ap:ap,ae:ae,chishu:chishu,zzzs:zzzs,mcjg:mcjg,vf:vf,vc:vc,beizhu:beizhu},
          success:function(form,action){
          	if(action.result.msg=='ok'){
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","添加成功");
          		formpanel.form.reset();
          		win.hide();
          		customQueryFn();
          	}else if(action.result.msg=='repeat'){
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","切削参数已经存在，不能重复添加");
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
           Ext.MessageBox.alert("提示",'请先选择要修改的切削参数!');
            return;
        }else if(record.length!=1){
        	Ext.MessageBox.alert("提示",'每次只能修改一条切削参数信息!');
            return;
        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>切削参数信息修改</p>");
				win.show();//修改窗口显示
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").hide();
				insertormodify = insertormodify+1;
				formpanel.form.reset();
				Ext.getCmp("zhijing").getEl().dom.readOnly  = false;
				Ext.getCmp("ap").getEl().dom.readOnly  = false;
				Ext.getCmp("qiekuan").getEl().dom.readOnly  = false;
				Ext.getCmp("zzzs").getEl().dom.readOnly  = false;
				Ext.getCmp("chishu").getEl().dom.readOnly  = false;
				Ext.getCmp("mcjg").getEl().dom.readOnly  = false;
				
				Ext.getCmp("vf").getEl().dom.readOnly  = false;
				Ext.getCmp("vc").getEl().dom.readOnly  = false;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = false;
				initializeFn();
				}
			}       
    }
    
    	
 	//初始化赋值
	function initializeFn(){
		var record =sm.getSelections();//获得页面中选中信息
		var editid=record[0].json.id;
		//初始化设置
		win.findById("zhijing").setValue(record[0].json.zhijing);
		win.findById("ap").setValue(record[0].json.ap);
		win.findById("qiekuan").setValue(record[0].json.ae);
		win.findById("zzzs").setValue(record[0].json.zzzs);
		win.findById("chishu").setValue(record[0].json.chishu);
		win.findById("mcjg").setValue(record[0].json.mcjg);
		
		win.findById("vf").setValue(record[0].json.vf);
		win.findById("vc").setValue(record[0].json.vc);
		win.findById("beizhu").setValue(record[0].json.beizhu);
	}
	
	

	//用户修改函数.保存
	function editsaveFn(){
	      Ext.showProgressDialog("正在提交修改数据，请稍候...");
	      var zhijing = Ext.get("zhijing").dom.value.trim();
          var ap = Ext.get("ap").dom.value.trim();
          var ae = Ext.get("qiekuan").dom.value.trim();
          var zzzs = Ext.get("zzzs").dom.value.trim();
          var chishu = Ext.get("chishu").dom.value.trim();
          var mcjg = Ext.get("mcjg").dom.value.trim();
          
          var vf = Ext.get("vf").dom.value.trim();
          var vc = Ext.get("vc").dom.value.trim();
          var beizhu = Ext.get("beizhu").dom.value.trim();
          if(zhijing==""){
          	Ext.MessageBox.alert('提示','刀具直径不能为空');
          	return;
          }else if(ap==""){
          	Ext.MessageBox.alert('提示','切削深度不能为空');
          	return;
          }else if(ae==""){
          	Ext.MessageBox.alert('提示','切削宽度不能为空');
          	return;
          }else if(zzzs==""){
          	Ext.MessageBox.alert('提示','主轴转速不能为空');
          	return;
          }else if(mcjg==""){
          	Ext.MessageBox.alert('提示','每齿进给量不能为空');
          	return;
          }else if(chishu==""){
        	Ext.MessageBox.alert('提示','刀齿数不能为空');
          	return;
          }
            var record =sm.getSelections();
	       	var editid=record[0].json.id;
			formpanel.form.doAction('submit',{
				 	url:'logicpp_cut.jsp',
				 	method:'post',
			 		params:{type:'EDIT',id:editid,zhijing:zhijing,ap:ap,ae:ae,chishu:chishu,zzzs:zzzs,mcjg:mcjg,vf:vf,vc:vc,beizhu:beizhu},
			 		success : function(form,action){
						 if (action.result.msg=='ok'){
								Ext.hideProgressDialog();
								win.hide();
								customQueryFn();
								Ext.MessageBox.alert('提示','已完成对切削参数信息的修改！');
								insertormodify=0;
						}else if(action.result.msg=='repeat'){
								Ext.hideProgressDialog();
								Ext.MessageBox.alert("提示","该切削参数已存在,不可重复!");
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
    	Ext.getCmp("zhijing").getEl().dom.readOnly  = false;
		Ext.getCmp("ap").getEl().dom.readOnly  = false;
		Ext.getCmp("qiekuan").getEl().dom.readOnly  = false;
		Ext.getCmp("zzzs").getEl().dom.readOnly  = false;
		Ext.getCmp("chishu").getEl().dom.readOnly  = false;
		Ext.getCmp("mcjg").getEl().dom.readOnly  = false;
		
		Ext.getCmp("vf").getEl().dom.readOnly  = false;
		Ext.getCmp("vc").getEl().dom.readOnly  = false;
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
            Ext.MessageBox.alert('提示','请先选择要删除的项!');
            return;
    	}
   		var id="";
		if(record.length!=0) {
	        var confirm=Ext.MessageBox.show({
					title:'提醒!',
					width:300,
					msg:"将删除所选切削参数，确认吗？",
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
						   	url: 'logicpp_cut.jsp',
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
				win.setTitle("<p align='center'>添加切削参数信息</p>")
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
	            alert('请先选择要查看的切削参数!');
	            return;
	        }else if(record.length!=1){
	        	alert('每次只能查看一条切削参数信息!');
	            return;
	        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>切削参数信息明细</p>")
				win.show();//明细窗口显示
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").hide();
				Ext.getCmp("cancelbtn").hide();
				Ext.getCmp("returnbtn").show();
				
				formpanel.form.reset();
				initializeFn();
		     
				Ext.getCmp("zhijing").getEl().dom.readOnly  = true;
				Ext.getCmp("ap").getEl().dom.readOnly  = true;
				Ext.getCmp("qiekuan").getEl().dom.readOnly  = true;
				Ext.getCmp("zzzs").getEl().dom.readOnly  = true;
				Ext.getCmp("chishu").getEl().dom.readOnly  = true;
				Ext.getCmp("mcjg").getEl().dom.readOnly  = true;
				
				Ext.getCmp("vf").getEl().dom.readOnly  = true;
				Ext.getCmp("vc").getEl().dom.readOnly  = true;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = true;
				}
			} 
	    } 
	});
	

	

