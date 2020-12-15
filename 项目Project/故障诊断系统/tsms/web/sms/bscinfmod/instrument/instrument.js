var insertormodify=0;
Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=100000;//每页显示记录条数
	
	//定义仪表查询页面
	var search=new Ext.Search({
		region:'north',
        renderTo:'querydiv',
        state:false,
        columnArray:Stringquery,
		orderArray:Stringorder,
        height:123,
        handler:customQueryFn
    });
    //获取当前时间函数
    function getNowtime(){
		var sUrl = 'logicinstrument.jsp';
		var parms = {type:'getNowtime'};
		var objtp = Ext.util.JSON.decode(callService(sUrl,parms));
		var nowtimeValue = objtp.nowtime;
		return nowtimeValue;
		alert(nowtimeValue);
	}
	
    //定义仪表查询映射
    var record_material = Ext.data.Record.create([
    {
        name:'id'//id
    },{
    	name:'serialnum'//序号
    },{
    	name:'enterprisenum'//企业编号唯一标识
    },{
    	name:'outnum'//出厂编号
    },{
    	name:'classname'//类别
    },{
    	name:'name'//名称
    },{
    	name:'modelnum'//型号
    },{
    	name:'nowclass'//现场类别
    },{
    	name:'detectdate'//待检日期
    },{
    	name:'nextdate'//下次待检日期
    },{
    	name:'detectmode'//检测方式
    },{
    	name:'state'//状态
    },{
    	name:'execution'//完成情况
    },{
    	name:'blongteam'//所在班组
    },{
    	name:'person'//责任人
    }
    ]);
    
    //建立数据源
    var hpObj={url:'logicinstrument.jsp',method:'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_material)
    });
    
      function customQueryFn(){
    	hpObj.extraParams.filter=search.getFilter();
    	hpObj.extraParams.order=search.getOrder();   	
    	hpObj.extraParams.type="QUERY";
    	var pagebar = Ext.getCmp("pagebar");
    	var obj={params:{start:start,limit:limit}};
    	Ext.showProgressDialog("正在提交修改数据，请稍候...");
   		if(ds.load(obj)){
   			Ext.hideProgressDialog();
   		}
   		search.getQueryText();  
    }
    
   
    //定义列选择checkBox
    var sm = new Ext.grid.CheckboxSelectionModel();
    //定义表格grid
    var grid = new Ext.grid.EditorGridPanel({
    	title: false,
		        id:'filetable',
		        enableColumnMove:false,
		        loadMask:true,
		        columns:[sm,{
		        	header:'序号',
		        	dataIndex:'serialnum',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'企业编号',
		        	dataIndex:'enterprisenum',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'出厂编号',
		        	dataIndex:'outnum',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'型号',
		        	dataIndex:'modelnum',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'名称',
		        	dataIndex:'name',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'类别',
		        	dataIndex:'classname',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'现场类别',
		        	dataIndex:'nowclass',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'待检日期',
		        	dataIndex:'detectdate',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'下次待检日期',
		        	dataIndex:'nextdate',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'检测方式',
		        	dataIndex:'detectmode',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'状态',
		        	dataIndex:'state',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'完成情况',
		        	dataIndex:'execution',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'所在班组',
		        	dataIndex:'blongteam',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        },{
		        	header:'责任人',
		        	dataIndex:'person',
		        	width:100,
		        	align:'center',
		        	sortable:true
		        }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        autoScroll:true,
		        stateId:'filetable',
		        renderTo:document.body
    });
    
     grid.getStore().addListener('load',handleGridLoadEvent);
     function handleGridLoadEvent(store,records){
     	/*var d = new Date();
     	var d1 = new Date();
     	var d2 = new Date();
     	d.setTime(d.getTime()-30*24*3600*1000);
     	alert(d);
     	d1.setTime(d1.getTime()+10*24*3600*1000);
     	alert(d1);*/
     	var month = 0;
     	 var girdcount=0;
     	 var nowtime = getNowtime();
     	 //alert(nowtime);
     	 var nextnowtime = nowtime.toString();
     	 var dateArray = nextnowtime.split("-");
     	// alert(parseInt(dateArray[1],10));
     	 if(parseInt(dateArray[1],10)+1<10){
     	 	month = '0'+(parseInt(dateArray[1],10)+1).toString();
     	 	//alert(month);
     	 }else{
     	 	month = (parseInt(dateArray[1])+1).toString();
     	 }
     	 var now = dateArray[0]+"-"+ month +"-"+dateArray[2];//得到当前日期加上一个月的日期用做预警
     	 
     	
     	//黄色提前30天预警；红色已经过期的now
    store.each(function(r){
    	if(r.get('detectdate')<=nowtime){
    grid.getView().getRow(girdcount).style.backgroundColor='#FF0000';//red
    }else if(r.get('detectdate')<=now){
    	grid.getView().getRow(girdcount).style.backgroundColor='#FFFF00';//yellow
    }/*else if(r.get('detectdate')<=now1){
    	grid.getView().getRow(girdcount).style.backgroundColor='#0000FF';
    }*/
    girdcount=girdcount+1;
    });
   }
    
    var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
    var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});//按钮之间的竖线 
    var toolthree=new Ext.Toolbar.Separator({id:'toolthree'});//按钮之间的竖线    
    var toolfour=new Ext.Toolbar.Separator({id:'toolfour'});//按钮之间的竖线
    var toolfive=new Ext.Toolbar.Separator({id:'toolfive'});//按钮之间的竖线 
    var toolsix = new Ext.Toolbar.Separator({id:'toolfive'});//按钮之间的竖线 
    //组装界面
    var viewport = new Ext.Viewport({
    	layout:'border',
    	id:'viewport',
    	autoHight:false,
    	items:[search,{
    		region:'center',
    		id:'tabpanel',
    		layout:'fit',
    		title:'查询结果',
    		autoScroll:true,
    		tbar:new Ext.Toolbar({
    			items:[toolone,{
    				id:'add',
		            text:"添加", 
		            handler:addFn ,
		            iconCls:"add",
		            tooltip:"添加一条仪表信息"
    			},tooltwo,{
    			id:'edit',
	            text:"修改", 
	            handler:editFn ,
	            iconCls:"update",
	            tooltip:"修改一条仪表信息"
    			},toolthree,{
    			id:'del',
	            text:"删除", 
	            handler:delFn ,
	            iconCls:"delete",
	            tooltip:"删除仪表信息"
    			},toolfour,{
    			id:'imoprt',
    			text:'导入',
    			handler: function(){
    						if(!input_win.isVisible())
    						input_win.show();
    						Ext.getDom('input_id').outerHTML  = Ext.getDom('input_id').outerHTML;
    						Ext.getCmp("input_file").setVisible(false);
    						Ext.getCmp("upload_file").setVisible(true);
    					},
    			iconCls:"wlk",
    			tooltip:"将仪表信息导入"
    			},toolfive,{								
					            id:'output',
					            text:"导出", 
					            handler:outFn,
					            iconCls:"wlk"
			        		},toolsix]
    		}),
    		items:grid
    	}]
    });
    
    var formpanel = new Ext.form.FormPanel({
    	id:'formpanel',
        labelWidth:85, // label settings here cascade unless overridden
        columnWidth:1,
        width:453,
        height:270,	
        layout:'form',
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:0px 6px 0',
        items:[{
        	layout:'column',
        	items:[{
        		columnWidth:.5,
                layout:'form',
                labelAlign:"right",
                items:[{
                	columnWidth:1,					              
                	layout: 'column',
                	items:[
                		{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '序号',
                    name: 'serialnum',
                    id: 'serialnum'
                }]
                }]
                },{
                	columnWidth:1,					              
                	layout: 'column',
                	items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '企业编号',
                    name: 'enterprisenum',
                    id: 'enterprisenum'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			       }
                ]},{
                    columnWidth:1,					              
                	layout: 'column',
                	items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '出厂编号',
                    name: 'outnum',
                    id: 'outnum'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			       }
                ]
                },{
                    columnWidth:1,					              
                	layout: 'column',
                	items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '型号',
                    name: 'modelnum',
                    id: 'modelnum'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			       }
                ]
                },{
                    columnWidth:1,					              
                	layout: 'column',
                	items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '名称',
                    name: 'name',
                    id: 'name'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			       }
                ]
                },{
                    columnWidth:1,					              
                	layout: 'column',
                	items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '类别',
                    name: 'classname',
                    id: 'classname'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			       }
                ]},{
                    columnWidth:1,					              
                	layout: 'column',
                	items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '现场类别',
                    name: 'nowclass',
                    id: 'nowclass'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			       }
                ]
                }
                ]
        	},{
        		columnWidth:.5,
                layout:'form',
                labelAlign:"right",
                items:[{
                		columnWidth:1,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [{
	                    xtype:'datefield',
	                    fieldLabel: '待检日期',
	                    name: 'detectdate',
	                    format:'Y-n-j',
	                    id: 'detectdate'
                	   }]
                },{
                	columnWidth:1,					              
                	layout: 'column',
                	items:[{
                		columnWidth:1,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [{
	                    xtype:'datefield',
	                    fieldLabel: '下次待检日期',
	                    format:'Y-n-j',
	                    name: 'nextdate',
	                    id: 'nextdate'
                               }]
                	   }]
                },{
                	columnWidth:1,					              
                	layout: 'column',
                	items:[{
                		columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [{
	                    xtype:'textfield',
	                    fieldLabel: '检测方式',
	                    name: 'detectmode',
	                    id: 'detectmode'
                               }]
                	   }]
                },{
                	columnWidth:1,					              
                	layout: 'column',
                	items:[{
                		columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [{
	                    xtype:'textfield',
	                    fieldLabel: '状态',
	                    name: 'state',
	                    id: 'state'
                               }]
                	   }]
                },{
                	columnWidth:1,					              
                	layout: 'column',
                	items:[{
                		columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [{
	                    xtype:'textfield',
	                    fieldLabel: '完成情况',
	                    name: 'execution',
	                    id: 'execution'
                               }]
                	   }]
                },{
                	columnWidth:1,					              
                	layout: 'column',
                	items:[{
                		columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [{
	                    xtype:'textfield',
	                    fieldLabel: '所在班组',
	                    name: 'belongteam',
	                    id: 'belongteam'
                               }]
                	   }]
                },{
                	columnWidth:1,					              
                	layout: 'column',
                	items:[{
                		columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [{
	                    xtype:'textfield',
	                    fieldLabel: '责任人',
	                    name: 'person',
	                    id: 'person'
                               }]
                	   }]
                }]
        	}]
        }]
    });
    
    //将formpanel渲染到窗口，建立添加窗口
    var window = new Ext.Window({
		id:'win',
		modal:true,                                         
        width:540,
        height:280,
        closeAction:'hide',
        plain: true,
        buttonAlign:"center",
		bodyBorder:true,
		resizable:false,
		items:[
	        		new Ext.Panel({
                		layout:'column',
                		frame:true,
                		height:320,
                		items:[formpanel]
	        		})
                		
		],
		buttons:[{
			id:'savebtn',
            text:'保存',
            handler:saveFn
		},{
			id:'reset',
            text:'重置',
            type: 'reset',
            handler:resetFn
		},{
			id:'cancle',
            text:'取消',
            handler:cancleFn
		}]
	});
	
    //添加函数
    function addFn(){
    	window.show();
    	window.center();
    }
    
    //删除函数
    function editFn(){
    	insertormodify=insertormodify+1;
    	var record =sm.getSelections();// 返回值为 Record 类型            	
		if(record.length==0){
            alert('请先选择要修改的仪表信息!');
            return;
        }else if(record.length!=1){
        	alert('每次只能修改一条仪表信息!');
            return;
        }else{ if(!window.isVisible()){
                window.setTitle("<p align='center'>仪表信息修改</p>");
                Ext.getCmp('reset').hide();
                formpanel.form.reset();
				window.show();//修改窗口显示
        }
        initializeFn();
    }
    }
    
    //初始化函数
    function initializeFn(){
    var record =sm.getSelections();//获得页面中选中信息
		var editid=record[0].json.id;
		//初始化设置
		var load=Ext.Ajax.request({
		 	url:'logicinstrument.jsp',
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
    	var obj = Ext.util.JSON.decode(result.responseText);
    	window.findById("serialnum").setValue(obj.serialnum);
    	window.findById("enterprisenum").setValue(obj.enterprisenum);
    	window.findById("outnum").setValue(obj.outnum);
    	window.findById("name").setValue(obj.name);
    	window.findById("modelnum").setValue(obj.modelnum);
    	window.findById("classname").setValue(obj.classname);
    	window.findById("nowclass").setValue(obj.nowclass);
    	window.findById("detectdate").setValue(obj.detectdate);
    	window.findById("nextdate").setValue(obj.nextdate);
    	window.findById("detectmode").setValue(obj.detectmode);
    	window.findById("state").setValue(obj.state);
    	window.findById("execution").setValue(obj.execution);
    	window.findById("belongteam").setValue(obj.blongteam);
    	window.findById("person").setValue(obj.person);
    }
    //修改函数
    function delFn(){
    	var record = sm.getSelections();
    	if(record.length==0){
    		alert("请选择一条要删除的仪表记录");
    		return;
    	}
    	var id="";
    	if(record.length!=0){
    		var confirm = Ext.MessageBox.show({
    			title:'提醒',
    			width:300,
    			msg:"将所选的仪表记录确定要删除吗？",
    			buttons:{"ok":"确定","no":"取消"},
    			icon:Ext.MessageBox.INFO,
    			animEl:"test5",
    			fn:function(btn){
    				if(btn=='ok'){
    					Ext.showProgressDialog();
    					for(var i=0;i<record.length;i++){
    						if(i!=0){
    							id+=",";
    						}
    						id += "'"+record[i].json.id+"'";
    					}
    					Ext.Ajax.request({
    						url:"logicinstrument.jsp",
    						params:{type:"DEL",id:id},
    						success:function(resp){
    							Ext.hideProgressDialog();
    							if(parseInt(resp.responseText)){
    							for(var i=0;i<record.length;i++){
    								ds.remove(record[i]);
    							}
    							alert("删除成功");
    							customQueryFn();
    						}else{
    							alert("删除成功");
    						}
    						},
    						failure:function(){
    							Ext.hideProgressDialog();
								alert('服务器出现错误请稍后再试！');
    						}
    					})
    				}else if(btn=='no'){
    					
    				}
    			}
    		});
    	}
    }
    
    //保存函数
    function saveFn(){
    	var serialnum = Ext.get("serialnum").dom.value.trim();//取出序号
    	var enterprisenum = Ext.get("enterprisenum").dom.value.trim();//取出企业编号
    	var outnum = Ext.get("outnum").dom.value.trim();//取出出厂编号
    	var modelnum = Ext.get("modelnum").dom.value.trim();//取出型号
    	var name = Ext.get("name").dom.value.trim();//取出名称
    	var classname = Ext.get("classname").dom.value.trim();//取出类别
    	var nowclass = Ext.get("nowclass").dom.value.trim();//取出现场类别
    	var detectdate = Ext.get("detectdate").dom.value.trim();//取出待检日期
    	var nextdate = Ext.get("nextdate").dom.value.trim();//下次待检日期
    	var detectmodel = Ext.get("detectmode").dom.value.trim();//检测方式
    	var state = Ext.get("state").dom.value.trim();//状态
    	var execution = Ext.get("execution").dom.value.trim();//完成情况
    	var belongteam = Ext.get("belongteam").dom.value.trim();//所在班组
    	var person = Ext.get("person").dom.value.trim();//责任人
		/*alert(serialnum + " " + enterprisenum +" " +outnum+" " +　modelnum　+ " " + name + " "　+ classname+" "+nowclass);
		alert(detectdate+" "+nextdate+" "+detectmodel+" "+state+" "+execution+" "+belongteam+" "+person);*/
    	if(enterprisenum==""){
    		alert("企业编号不能为空");
    	}else if(outnum==""){
    		alert("出厂编号不能为空");
    	}else if(modelnum==""){
    		alert("型号不能为空");
    	}else if(name==""){
    		alert("名称不能为空");
    	}else if(classname==""){
    		alert("类别不能为空");
    	}else if(nowclass==""){
    		alert("现场类别不能为空");
    	}else if(insertormodify==0){
			addsaveFn();
		}else{
			editsaveFn();
		}
    }
    //重置函数
    function resetFn(){
    	formpanel.form.reset();
    }
    //取消函数
    function cancleFn(){
    	if(insertormodify==1){insertormodify=0;}
    	formpanel.form.reset();
    	window.hide();
    }
    //添加保存函数
    function addsaveFn(){
    	Ext.showProgressDialog("正在提交数据，请稍候...");
		formpanel.form.doAction('submit',{
			url : 'logicinstrument.jsp',
			method:'post',
			params : { type : 'ADD'},
			success:function(form,action){
				if(action.result.msg=='ok'){
					Ext.hideProgressDialog();
					formpanel.form.reset();
					window.hide();
					/*customQueryFn();*/
					alert("仪表信息添加成功");
					customQueryFn();
				}else if(action.result.msg=='repeat'){
					Ext.hideProgressDialog();
					alert("同类仪表记录已经存在不需要添加");
				}else{
					Ext.hideProgressDialog();
					alert("仪表信息添加失败");
				}
			},
			failure : function(form,action){
							Ext.hideProgressDialog();
							alert( "提示:'服务器出现错误请稍后再试！");
								 }
		});
    }
    function editsaveFn(){
    	var record =sm.getSelections();//获得页面中选中信息
		var editid=record[0].json.id;
		Ext.showProgressDialog("正在提交数据，请稍候...");
		var date = Ext.get("detectdate").dom.value.trim();
		//alert(date);
		formpanel.form.doAction('submit',{
			url : 'logicinstrument.jsp',
			method:'post',
			params : {type:'EDIT',id:editid},
			success:function(form,action){
				if(action.result.msg=='ok'){
					Ext.hideProgressDialog();
					formpanel.form.reset();
					window.hide();
					insertormodify = insertormodify-1;
					customQueryFn();
					alert("仪表信息修改成功");
				}else if(action.result.msg=='repeat'){
					Ext.hideProgressDialog();
					alert("提示:'仪表信息已经存在");
				}else{
					Ext.hideProgressDialog();
					alert("仪表信息修改失败");
				}
			},
			failure : function(form,action){
				Ext.hideProgressDialog();
				alert( "提示:'服务器出现错误请稍后再试！");
								 }
		});	
		
    }
    //导入
    var input_win = new Ext.Window({
   		 id:'input_win',
   		 text:'<p align="center">文件导入</p>',
   		 layout:'form',
   		 model:true,//遮挡window后面信息
   		 width:310,
   		 height:160,
   		 plain:true,
   		 buttonAlign:"center",
	 	 bodyBorder:true,
		 resizable:false,
   		 /*listeners:{
				  "hide":function(){
				    if(keyBoardWin.isVisible()){									    
						keyBoard.hide();
					}
				  }
				},*/
	    closeAction:'hide',
	    items:[new Ext.form.FormPanel({
	    id:"input_panel",
    	bodyStyle : 'padding:30px 10px 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:140,
		width:500,
		labelWidth:55,
		layout:'form',
		items : [{  xtype : 'textfield',
					fieldLabel : '文件名称',									
					id : 'input_id',
					inputType : 'file',
					name : 'input_name',
					width : 200								
				}]
		 })],
		  buttons : [{
						  text : '上传文件',
						  id:'upload_file',						
						  handler : function() {
								 var value = document.getElementById("input_id").value;
								 if(value == ''){
								   alert("请选择要上传的文件!");
								 }else if(value.indexOf(".xls")== -1) {
								   alert("上传的文件只能为excel表格,请修改!");
								 }else{		
								    var parts = value.split(/\/|\\/);
									if (parts.length == 1) {
									  filename = parts[0];
									}else{
									  filename = parts.pop();
									}	
									 Ext.getCmp("input_panel").getForm().submit({
								       waitMsg:'上传中，请等待...', 
								       waitTitle:' ',
								       method: 'post',      
								       url:'logicinstrument.jsp?type=UPLOAD',         
								       success: function(form, action){
								           	    alert( "提示:上传成功!");												
												Ext.getCmp("input_file").setVisible(true);
												Ext.getCmp("upload_file").setVisible(false);
								          },
								       failure: function(form, action){             
								                Ext.hideProgressDialog();
											    alert("提示:上传失败！请重试！");
								          }
								      }); 
								}		
							}
					},{	  text:'导入文件',						
						  id:'input_file',
					      hidden:true,
						  handler : function() {							      
						      Ext.showProgressDialog();
										Ext.Ajax.request({
											url : 'logicinstrument.jsp',
											success : function(resp) {				
												Ext.hideProgressDialog();												
												if(parseInt(resp.responseText)==1){
													alert("导入成功");
													customQueryFn();
												}else if(parseInt(resp.responseText)==3){
													alert("日期格式错误");
												}else if(parseInt(resp.responseText)==2){
													alert("存在内容为空");
												}else if(parseInt(resp.responseText)==5){
													alert("仪表信息已经存在，无需导入");
												}else{
													alert("导入失败！请重试！");
												}
												Ext.getCmp("input_file").setVisible(false);
												Ext.getCmp("upload_file").setVisible(true);	
											},
											failure : function() {
												Ext.hideProgressDialog();
												alert("导入失败！请重试！");
											},
											params : {
												type : 'INPUT'											
											}
										});					         
								 }
					},{
						 text : '取消',						  
						 handler : function() {
						 input_win.hide();
						 }
					}]
		 
   });
     	function outFn(){
		var url = 'http://localhost:8080/tsms/web/sms/bscinfmod/instrument/instrument_excel_out.jsp';
    	location.href='instrument_excel_out.jsp?url='+url;
	}
	
});