Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var limit=10000;
	var start=0;
	var filter="";
	var order="";
	var width=this.frameElement.offsetWidth;//获得当前窗口宽度
	var height=this.frameElement.offsetHeight;//获得当前窗口高度

	//零件总体信息输入窗口
	var upborder = new Ext.FormPanel({
		renderTo : 'querydiv',
		layout:'column',
		height:135,
		width:width,
        frame: true,
        items:[{					              
            layout: 'form',labelAlign:"right",columnWidth:.25,
            items:[
                   {xtype:'textfield',fieldLabel: '产品名称',id: 'goodname',width: 150},
                   {xtype:'textfield',fieldLabel: '零件名称',id: 'partname',width: 150},
                   {xtype:'textfield',fieldLabel: '整件图号',id: 'goodnum',width: 150},
                   {xtype:'textfield',fieldLabel: '零件图号',id: 'partnum',width: 150}]
        },{layout: 'form',labelAlign:"right",columnWidth:.25,
            items:[{xtype:'textfield',fieldLabel: '材料名称',id: 'materialname',width: 150},
                   {xtype:'textfield',fieldLabel: '材料牌号',id: 'materialcard',width: 150},
                   {xtype:'textfield',fieldLabel: '材料编码',id: 'materialnum',width: 150},
                   {xtype:'textfield',fieldLabel: '材料标准号',id: 'materialstandard',width: 150}]
        },{layout: 'form',labelAlign:"right",columnWidth:.25,
            items:[{xtype:'textfield',fieldLabel: '毛坯长度',id: 'milllong',width: 150,regex:/^[0-9]+([.]{0,1}[0-9]+){0,1}$/,regexText:'只能由数字组成!'},
                   {xtype:'textfield',fieldLabel: '毛坯宽度',id: 'millwide',width: 150,regex:/^[0-9]+([.]{0,1}[0-9]+){0,1}$/,regexText:'只能由数字组成!'},
                   {xtype:'textfield',fieldLabel: '毛坯高度',id: 'millthick',width: 150,regex:/^[0-9]+([.]{0,1}[0-9]+){0,1}$/,regexText:'只能由数字组成!'},
                   {xtype:'textfield',fieldLabel: '毛坯中零件数',id: 'millnum',width: 150,regex:/^[0-9]*$/,regexText:'只能由数字组成!'}]
        },{layout: 'form',labelAlign:"right",buttonAlign:"center",columnWidth:.25,
            items:[{fieldLabel:"日期",xtype: 'datefield',width:150,id:'milldate',format:'Y-m-d'}],
            buttons:[{id:'saveallbtn',text:'任务载入',handler:function(){
																		if(!input_win.isVisible())
																			input_win.show();
																		}
    		},{
    			id:'simbtn',
                text:'相似检索',
                handler:function(){
					if(!sim_win.isVisible())
						sim_win.show();
					}
    		}]
        }]
	});
	
	//任务载入函数
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
		  buttons : [{	  text:'导入文件',						
						  id:'inputfile',
					      //hidden:true,
						  handler : readtest
					},{
						 text : '取消',						  
						 handler : function() {
						 input_win.hide();
						 }
					}]
		 
  });
		
	function readtest(){
        //得到文件路径的值  
        var filePath = document.getElementById("input_id").value;  
        //创建操作EXCEL应用程序的实例  
        var oXL = new ActiveXObject("Excel.application");  
         //打开指定路径的excel文件  
        var oWB = oXL.Workbooks.open(filePath);  
        //操作第一个sheet(从一开始，而非零)  
        oWB.worksheets(1).select();  
        var oSheet = oWB.ActiveSheet;  
        //使用的行数  
        var rows =  oSheet.usedrange.rows.count;
        //总体信息的读取
        if(!oSheet.Cells(1, 2).value == ""){
        	document.getElementById('goodname').value = oSheet.Cells(1,2).value.toString();
        	}else{document.getElementById('goodname').value = "";}
        if(!oSheet.Cells(2, 2).value == ""){
        	document.getElementById('partname').value = oSheet.Cells(2,2).value.toString();
        	}else{document.getElementById('partname').value = "";}
        if(!oSheet.Cells(3, 2).value == ""){
        	document.getElementById('goodnum').value = oSheet.Cells(3,2).value.toString();
        	}else{document.getElementById('goodnum').value = "";}
        if(!oSheet.Cells(4, 2).value == ""){
        	document.getElementById('partnum').value = oSheet.Cells(4,2).value.toString();
        	}else{document.getElementById('partnum').value = "";}
        
        if(!oSheet.Cells(1, 4).value == ""){
        	document.getElementById('materialname').value = oSheet.Cells(1,4).value.toString();
        	}else{document.getElementById('materialname').value = "";}
        if(!oSheet.Cells(2, 4).value == ""){
        	document.getElementById('materialcard').value = oSheet.Cells(2,4).value.toString();
        	}else{document.getElementById('materialcard').value = "";}
        if(!oSheet.Cells(3, 4).value == ""){
        	document.getElementById('materialnum').value = oSheet.Cells(3,4).value.toString();
        	}else{document.getElementById('materialnum').value = "";}
        if(!oSheet.Cells(4, 4).value == ""){
        	document.getElementById('materialstandard').value = oSheet.Cells(4,4).value.toString();
        	}else{document.getElementById('materialstandard').value = "";}
        
        if(!oSheet.Cells(1, 6).value == ""){
        	document.getElementById('milllong').value = oSheet.Cells(1,6).value.toString();
        	}else{document.getElementById('milllong').value = "";}
        if(!oSheet.Cells(2, 6).value == ""){
        	document.getElementById('millwide').value = oSheet.Cells(2,6).value.toString();
        	}else{document.getElementById('millwide').value = "";}
        if(!oSheet.Cells(3, 6).value == ""){
        	document.getElementById('millthick').value = oSheet.Cells(3,6).value.toString();
        	}else{document.getElementById('millthick').value = "";}
        if(!oSheet.Cells(4, 6).value == ""){
        	document.getElementById('millnum').value = oSheet.Cells(4,6).value.toString();
        	}else{document.getElementById('millnum').value = "";}
        
        //定额信息的读取
      for(var i=6;i<=rows;i++){
    	  if(!oSheet.Cells(i, 1).value == ""){
    		  var a = oSheet.Cells(i,1).value.toString();
          	}else{var a = "";}
    	  if(!oSheet.Cells(i, 2).value == ""){
    		  var b = oSheet.Cells(i,2).value.toString();
          	}else{var b = "";}
    	  if(!oSheet.Cells(i, 3).value == ""){
    		  var c = oSheet.Cells(i,3).value.toString();
          	}else{var c = "";}
    	  if(!oSheet.Cells(i, 4).value == ""){
    		  var d = oSheet.Cells(i,4).value.toString();
          	}else{var d = "";}
    	  if(!oSheet.Cells(i, 5).value == ""){
    		  var e = oSheet.Cells(i,5).value.toString();
          	}else{var e = "";}
    	  var row = new Ext.data.Record({ 
    		  processnum:a, //这里是和ColumnModel里面的dataIndex相互对应的 
    		  process:b, 
    		  worktype:c, 
    		  farm:d, 
    		  equipment:e, 
    		  readytime:'',
    		  worktime:'',
    		  groupnum:''
          });  
          ds.add(row);   	  
      }
        //退出操作excel的实例对象  
        oXL.Application.Quit();  
         //手动调用垃圾收集器  
        CollectGarbage();  		
	}

	//工时定额输入数据
	var record = Ext.data.Record.create([    		
	                                		{name:'processnum'},
	                            			{name:'process'},
	                            			{name:'worktype'},
	                            			{name:'farm'},
	                            			{name:'equipment'},
	                            			{name:'readytime'},
	                            			{name:'worktime'},
	                            			{name:'groupnum'}
	                            			]); 
	                        	                                    
	                            
	var hpObj = {url:'logicmilling.jsp',method: 'POST',extraParams:{}};
	var ds = new Ext.data.Store({
	    proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
	    reader: new Ext.data.JsonReader({
	        totalProperty:'totalProperty',
	        root:'filedata'
	    },record)
	});
	
	//工时定额表格窗口
	var sm = new Ext.grid.CheckboxSelectionModel();
	var grid = new Ext.grid.EditorGridPanel({
		title: false,
		id:'filetable',
		loadMask:true,
		enableColumnMove:false,
		clicksToEdit: 1,
		columns:[sm,
		         {header: "工序号",dataIndex: "processnum",width : 75,align:'center',sortable: true},
		         {header: "工序名称",dataIndex: "process",width : 100,align:'center',sortable: true},
		         {header: "工种",dataIndex: "worktype",width : 100,align:'center',sortable: true},
		         {header: "车间",dataIndex: "farm",width : 100,align:'center', sortable: true},
		         {header: "设备",dataIndex: "equipment",width : 100,align:'center',hidden:true,sortable: true},
		         {header: "准结时间(h)",dataIndex: "readytime",width : 100,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "加工时间(h)",dataIndex: "worktime",width : 100,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "每组数量",dataIndex: "groupnum",width : 100,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header : '工时计算',dataIndex : 'jisuan',width : 100,align:'center',renderer: createButton}],
		ds:ds,
		sm:sm,
		height:300,
		autoHeight :false,
        autoScroll :true
	});

	window.jisuanFn = function(){ 
		var records = sm.getSelections();
		if(records[0].get("process")=="备料"){
			beiliaojsFn();			
		}else if(records[0].get("process")=="铣"){
			if(!xijs.isVisible())
				xijs.show();
		}else if(records[0].get("process")=="粗铣"){
			if(!xijs.isVisible())
				xijs.show();
		}else if(records[0].get("process")=="精铣"){
			if(!xijs.isVisible())
				xijs.show();
		}else if(records[0].get("process")=="钳"){
			if(!qianjs.isVisible())
				qianjs.show();
		}else if(records[0].get("process")=="零钳"){
			if(!qianjs.isVisible())
				qianjs.show();
		}else if(records[0].get("process")=="热处理"){
			rechulijsFn();
		}else if(records[0].get("process")=="电镀"){
			if(!diandujs.isVisible())
				diandujs.show();
		}
   	}
	
	function createButton(){   
		return "<input type='button' value='工时计算' onclick='jisuanFn()' >" ;   
	    } 
	
	//工时定额窗口组装
	var centerborder = new Ext.FormPanel({
		renderTo: 'selectdiv',
		height:325,
		width:width,
		layout:"form",
		items:[{
			items:grid,
			region:'center',
			layout:'fit',
			id:'leftdownpanel',
			autoScroll:true,
			tbar:new Ext.Toolbar({
				id:'tbar',
		        name: 'tbar',
		        items:[
		               {id:'tiaoshibtn',text:"调试信息载入", handler:tiaoshiFn,iconCls:"add"},
		               {id:'addbtn',text:"定额结果存储", handler:addFn,iconCls:"save"},
		               {id:'delbtn',text:"删除", handler:delFn,iconCls:"delete"},
		               {id:'jsbtn',text:"工时计算", handler:gsjsFn,iconCls:"detail"},
		               {id:'thjsbtn',text:"图号检索", handler:thjsFn,iconCls:"detail"}]
			})
		}]
	});
	
	//工时整体计算函数
	function gsjsFn(){
		var millwide = Ext.get("millwide").dom.value.trim();
		var millthick = Ext.get("millthick").dom.value.trim();
		var s = (millwide*millwide + millthick*millthick);
		var x = Math.sqrt(s);
		var p1 = -(9.539e-06); 
        var p2 = 0.005648;
        var p3 = -0.3948;
        var p4 = 13.02;
	    var t = p1*x*x*x + p2*x*x + p3*x + p4;
		gx11=0.25; gx12=(t/60).toFixed(3); gx13=1;//备料
		gx21=2; gx23=1;//铣
		gx31=0.5; gx33=1;//钳
		gx41=2; gx42=0; gx43=1;//热处理
		gx51=0; gx52=1; gx53=1;//电镀
		//循环插入
		sm.selectAll();//全选
		var records = sm.getSelections();
		for(var i=0;i<records.length;i++){
			if(records[i].get("process")=="备料"){
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:gx11,
			  		  worktime:gx12,
			  		  groupnum:gx13
			        });		
			}else if(records[i].get("process")=="铣"){
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:gx21,
			  		  worktime:records[i].get("worktime"),
			  		  groupnum:gx23
			        });		
			}else if(records[i].get("process")=="粗铣"){
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:gx21,
			  		  worktime:records[i].get("worktime"),
			  		  groupnum:gx23
			        });		
			}else if(records[i].get("process")=="精铣"){
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:gx21,
			  		  worktime:records[i].get("worktime"),
			  		  groupnum:gx23
			        });		
			}else if(records[i].get("process")=="钳"){
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:gx31,
			  		  worktime:records[i].get("worktime"),
			  		  groupnum:gx33
			        });		
			}else if(records[i].get("process")=="零钳"){
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:gx31,
			  		  worktime:records[i].get("worktime"),
			  		  groupnum:gx33
			        });		
			}else if(records[i].get("process")=="热处理"){
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:gx41,
			  		  worktime:gx42,
			  		  groupnum:gx43
			        });		
			}else if(records[i].get("process")=="电镀"){
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:gx51,
			  		  worktime:gx52,
			  		  groupnum:gx53
			        });		
			}else{
				var row = new Ext.data.Record({ 
			  		  processnum:records[i].get("processnum"), 
			  		  process:records[i].get("process"), 
			  		  worktype:records[i].get("worktype"), 
			  		  farm:records[i].get("farm"), 
			  		  equipment:records[i].get("equipment"), 
			  		  readytime:records[i].get("readytime"),
			  		  worktime:records[i].get("worktime"),
			  		  groupnum:'1'
			        });		
			}
			var num = ds.indexOf(records[i]);							  
			ds.remove(records[i]);
	        ds.insert(num,row);
			}
	}
	
	//图号检索
	function thjsFn(){
		if(!thjs.isVisible())
			thjs.show();
	}
	var record_th = Ext.data.Record.create([    		
	                                		{name:'processnum_dx'},
	                            			{name:'process_dx'},
	                            			{name:'worktype_dx'},
	                            			{name:'farm_dx'},
	                            			{name:'readytime_dx'},
	                            			{name:'worktime_dx'},
	                            			{name:'groupnum_dx'}
	                            			]); 	                                    
	                            
	                        	var hpObj_th = {url:'logicmillsim.jsp',method: 'POST',extraParams:{}};
	                        	var ds_th = new Ext.data.Store({
	                                proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj_th)),
	                                reader: new Ext.data.JsonReader({
	                                    totalProperty:'totalProperty',
	                                    root:'filedata'
	                                },record_th)
	                            });	                                	
	                        	var sm_th = new Ext.grid.CheckboxSelectionModel();
	                        	var grid_th = new Ext.grid.GridPanel({
	                        		title: false,
	                        		id:'thtable',
	                        		enableColumnMove:true,
	                        		columns:[sm_th,
	                        				 {header: "工序号",dataIndex: "processnum_dx",width : 75,align:'center',sortable: true},
	                        				 {header: "工序名称",dataIndex: "process_dx",width : 75,align:'center',sortable: true},
	                        				 {header: "工种",dataIndex: "worktype_dx",width : 75,align:'center',sortable: true},
	                        				 {header: "车间",dataIndex: "farm_dx",width : 75, align:'center', sortable: true},
	                        				 {header: "准备时间(h)",dataIndex: "readytime_dx",width : 75,align:'center',sortable: true},
	                        				 {header: "加工时间(h)",dataIndex: "worktime_dx",width : 75,align:'center',sortable: true},
	                        				 {header: "每组数量",dataIndex: "groupnum_dx",width : 75,align:'center',sortable: true}],
	                        				ds:ds_th,
	                        				sm:sm_th,
	                        				height:300,
	                        				autoHeight :false,
	                        		        autoScroll :true
	                        	});
	var xiangsishuru1 = new Ext.FormPanel({
        id:'xiangsishuru1',
        labelWidth: 80, 
        hideLabels:false,
        frame: true,
        border:false,
        labelAlign:"right",
		layout:'column',
        items: [{
        	layout: 'form',
            labelAlign:"right",
            columnWidth:.5,
            items:[{},{xtype:'textfield',fieldLabel: '输入图号',id: 'thsr',width: 200}]
        },{					              
        	layout: 'form',
            buttonAlign:"left",
            columnWidth:.5,
            buttons:[{
    			id:'jiansuo1btn',
                text:'检索',
                handler:jiansuo1Fn}]
        }]
		    });  
	var thjs = new Ext.Window({
		id:'window',
		modal:true,
        width:600,
        height:440,
        closable:false,
        buttonAlign:"center",
        plain: true,
        title:"<p align='center'>图号检索</p>",    
		bodyBorder:true,
		items:[xiangsishuru1,{
				        	items:grid_th,
							region:'center',
							layout:'fit',
							id:'tablepanel',
							autoScroll:true		
				        }],
        buttons: [{ id:'xuanyong1btn',
			text: '选用实例',
			handler:xuanyong1Fn
        },{ id:'returnbtn',
			text: '返回',
			handler:function cancelFn(){
				thjs.hide();
			   }
        }]
    });
	function jiansuo1Fn(){
		var thsr = Ext.get("thsr").dom.value.trim();
		if(thsr==""){
			Ext.MessageBox.alert('提示','请输入零件图号');
		}else{
			//Ext.MessageBox.alert('提示',thsr);
			var filter = "1=1 and part_num = '" + thsr + "'";
			//var filter = "1=1 and part_num = '6.726.888'";
			var order = "process_num ASC";
			hpObj_th.extraParams.order = order;
			hpObj_th.extraParams.filter = filter; 	
			hpObj_th.extraParams.type = "QUERYSIMALL";
			ds_th.load();
		}	
	}
	function xuanyong1Fn(){
		sm.selectAll();//全选
		sm_th.selectAll();//全选
		var record = sm.getSelections();
		var records = sm_th.getSelections();
		for(var i=0;i<record.length;i++){
			var readytime_dx = records[i].get("readytime_dx").toString();
			var worktime_dx = records[i].get("worktime_dx").toString();
			var groupnum_dx = records[i].get("groupnum_dx").toString();
			var row = new Ext.data.Record({ 
		  		  processnum:record[i].get("processnum"), 
		  		  process:record[i].get("process"), 
		  		  worktype:record[i].get("worktype"), 
		  		  farm:record[i].get("farm"), 
		  		  equipment:record[i].get("equipment"), 
		  		  readytime:readytime_dx,
		  		  worktime:worktime_dx,
		  		  groupnum:groupnum_dx
		        });	
			var num = ds.indexOf(record[i]);							  
			ds.remove(record[i]);
	        ds.insert(num,row);
		}
		thjs.hide();
	}
	
	//工时定额调试信息输入函数
	function tiaoshiFn(){
    	var row1 = new Ext.data.Record({'processnum':'1','process':'备料','worktype':'备料工','farm':'料','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row2 = new Ext.data.Record({'processnum':'2','process':'粗铣','worktype':'铣工','farm':'三','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row3 = new Ext.data.Record({'processnum':'3','process':'钳','worktype':'钳工','farm':'三','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row4 = new Ext.data.Record({'processnum':'4','process':'热处理','worktype':'热处理工','farm':'三','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row5 = new Ext.data.Record({'processnum':'5','process':'22精铣','worktype':'铣工','farm':'三','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row6 = new Ext.data.Record({'processnum':'6','process':'钳','worktype':'钳工','farm':'三','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row7 = new Ext.data.Record({'processnum':'7','process':'电镀','worktype':'电镀工','farm':'三','equipment':'','readytime':'','worktime':'','groupnum':''});
    	ds.add(row1); 
        ds.add(row2); 
        ds.add(row3); 
        ds.add(row4); 
        ds.add(row5); 
        ds.add(row6); 
        ds.add(row7); 
        Ext.getCmp("goodname").setValue("铣加工");
        Ext.getCmp("partname").setValue("壳体");
        Ext.getCmp("goodnum").setValue("2.726.875");
        Ext.getCmp("partnum").setValue("6.726.875");
        Ext.getCmp("materialname").setValue("铝合金");
        Ext.getCmp("materialcard").setValue("5A05H");
        Ext.getCmp("materialnum").setValue("2258632159");
        Ext.getCmp("materialstandard").setValue("GB1765");     
        Ext.getCmp("milllong").setValue("186");
        Ext.getCmp("millwide").setValue("55");
        Ext.getCmp("millthick").setValue("25");
        Ext.getCmp("millnum").setValue("1");     
	}
	
	//定额结果保存函数	
	function addFn(){
		saveallFn();
		saveFn();
	}
	//零件总体信息保存函数
	function saveallFn(){
		var goodname = Ext.get("goodname").dom.value.trim();
		var partname = Ext.get("partname").dom.value.trim();
		var goodnum = Ext.get("goodnum").dom.value.trim();
		var partnum = Ext.get("partnum").dom.value.trim();
		var materialname = Ext.get("materialname").dom.value.trim();
		var materialcard = Ext.get("materialcard").dom.value.trim();
		var materialnum = Ext.get("materialnum").dom.value.trim();
		var materialstandard = Ext.get("materialstandard").dom.value.trim();
		var milllong = Ext.get("milllong").dom.value.trim();//长
		var millwide = Ext.get("millwide").dom.value.trim();//宽
		var millthick = Ext.get("millthick").dom.value.trim();//高
		var millnum = Ext.get("millnum").dom.value.trim();
		var milldate = Ext.get("milldate").dom.value.trim();
		var numReg = /^[0-9]+([.]{0,1}[0-9]+){0,1}$/;
		var numReg1 = /^(0|[1-9][0-9]*)$/;
		
		if(goodname==""){
			alert("产品名称不能为空");
		}else if(partname==""){
			alert("零件名称不能为空");
		}else if(goodnum==""){
			alert("产品图号不能为空");
		}else if(partnum==""){
			alert("零件图号不能为空");
		}else if(materialname==""){
			alert("材料名称不能为空");
		}else if(materialcard==""){
			alert("材料牌号不能为空");
		}else if(materialnum==""){
			alert("材料编码不能为空");
		}else if(materialstandard==""){
			alert("材料标准号不能为空");
		}else if(millthick==""){
			alert("毛坯高度不能为空");
		}else if(milllong==""){
			alert("毛坯长度不能为空");
		}else if(millwide==""){
			alert("毛坯宽度不能为空");
		}else if(millnum==""){
			alert("毛坯中零件数不能为空");
		}else if(milldate==""){
			alert("日期不能为空");
		}else if(!numReg.test(millthick)){
			alert("毛坯高度必须为数字");
		}else if(!numReg.test(milllong)){
			alert("毛坯长度必须为数字");
		}else if(!numReg.test(millwide)){
			alert("毛坯宽度必须为数字");
		}else if(!numReg1.test(millnum)){
			alert("毛坯中零件数必须为数字");
		}else{
			Ext.showProgressDialog("正在提交数据，请稍候...");
			Ext.Ajax.request({
							url:'logicmilling.jsp',
							params:{ type : 'ADDALL',
								goodname:goodname,
								partname:partname,
								goodnum:goodnum,
								partnum:partnum,
								materialname:materialname,
								materialcard:materialcard,
								materialnum:materialnum,
								materialstandard:materialstandard,								
								milllong:milllong,//长
								millwide:millwide,//宽
								millthick:millthick,//高
								millnum:millnum,
								milldate:milldate},
							success:function(resp){
								Ext.hideProgressDialog();
								if(parseInt(resp.responseText)==1){
									Ext.MessageBox.alert('提示',"零件总体信息存储成功");											
								}else{
									Ext.MessageBox.alert('提示',"零件总体信息存储失败");
								}
							},
							failure:function(){
								Ext.hideProgressDialog();
								Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
							}
						});
		}
	}
	//定额信息存储
	function saveFn(){
		sm.selectAll();//全选
		var record = sm.getSelections();
		if(record.length==0){
			Ext.MessageBox.alert('提示',"请选择定额信息");
		}else{
			for(var i=0;i<record.length;i++){
				var goodnum = Ext.get("goodnum").dom.value.trim();
				var partnum = Ext.get("partnum").dom.value.trim();
				var processnum = record[i].get("processnum");
				var process = record[i].get("process");
				var worktype = record[i].get("worktype");
				var farm = record[i].get("farm");
				var readytime = record[i].get("readytime");
				var worktime = record[i].get("worktime");
				var groupnum = record[i].get("groupnum");
				//alert(valiadate);
				Ext.showProgressDialog();
				Ext.Ajax.request({
					url:'logicmilling.jsp',
					//method:'post',
					params:{
						type:'ADD',
						goodnum:goodnum,
						partnum:partnum,
						processnum:processnum,
						process:process,
						worktype:worktype,
						farm:farm,
						readytime:readytime,
						worktime:worktime,
						groupnum:groupnum
					},
					success:function(resp){
						Ext.hideProgressDialog();
						if(parseInt(resp.responseText)==1){
							Ext.MessageBox.alert('提示',"零件定额信息存储成功");
						}else{
							Ext.MessageBox.alert('提示',"零件定额信息存储失败");
						}
					},
					failure:function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
					}
				});				
				}
			}	
	}
	
	//工时定额删除函数
	function delFn(){
		var records = sm.getSelections();
		if(records.length==0){
			Ext.MessageBox.alert('提示',"请选择要删除的行!");
			return;
		}else{
			Ext.MessageBox.confirm('提示',"您即将删除所选择的工时定额信息,确认吗？",function(btn){
				if(btn == 'yes'){
					Ext.showProgressDialog();
					for(var i=0;i<records.length;i++){
						ds.remove(records[i]);
					}
					Ext.hideProgressDialog();
					Ext.MessageBox.alert('提示',"您所选择的工时定额信息已经删除");
					//ds.load();
				}else{
					Ext.MessageBox.alert('提示',"您已经取消当前操作");
				}
			});
		}
	
	}
	
	//铣加工相似实例显示界面		
	var simin = new Ext.Panel({
		height:height-465,
		width:300,
		bodyStyle: 'padding:10px 0 0',
		layout:'form',
		labelAlign:"right",
		buttonAlign:"center",
		frame: true,
		title: "<p align='left'>相似零件总体信息</p>",
		items:[
               {xtype: 'textfield',fieldLabel:"零件名称",readOnly:true,width:150,name:"ljmcdx",id:"ljmcdx",allowBlank:false},
               {xtype: 'textfield',fieldLabel:"零件图号",readOnly:true,width:150,name:"ljthdx",id:"ljthdx",allowBlank:false},
               {xtype: 'textfield',fieldLabel:"材料名称",readOnly:true,width:150,name:"clmcdx",id:"clmcdx",allowBlank:false},
               {xtype: 'textfield',fieldLabel:"毛坯长度",readOnly:true,width:150,name:"mpcddx",id:"mpcddx",allowBlank:false},
               {xtype: 'textfield',fieldLabel:"毛坯宽度",readOnly:true,width:150,name:"mpkddx",id:"mpkddx",allowBlank:false},
               {xtype: 'textfield',fieldLabel:"毛坯高度",readOnly:true,width:150,name:"mpgddx",id:"mpgddx",allowBlank:false},
               {xtype: 'textfield',fieldLabel:"相似度",readOnly:true,width:150,name:"xsddx",id:"xsddx",allowBlank:false},
               {xtype: 'textfield',fieldLabel:"评价值",readOnly:true,width:150,name:"pjzdx",id:"pjzdx",allowBlank:false}
              ],
		buttons:[{
			id:'tpyldx',
            text:'图片预览',
            handler:tpyldxFn
		}]
	});
	
	function tpyldxFn(){
		var tuhao_sim1 = Ext.get("ljthdx").dom.value.trim();
    	var imgviewpanel1=new Ext.FormPanel({
			id:'imgview1',
		    labelWidth:75,
		    width:1000,
		    height:700,			      				       
		    frame:true,
		    bodyStyle:'padding:10px 10px 0',	
		    html:'<img width=510 hight=1024 alt="零件图片" src="logicimgview.jsp?type=viewimg&tuhao_sim='+tuhao_sim1+'"/>',
		    items: [{
		    buttons: [{
		     			id:'back1btn',
						text: '返回',
						handler: function(){
						 Ext.getCmp("imgview1").hide();
						}
		            }
		       	]
		    }]
		   	});
		  var imgwindow1 = new Ext.Window({
    			id:'imgwindow1',
    			modal:true,
                width:1000,
                height:700,
                closable:true,
                plain: true,
                title:"<p align='center'>查看零件图片</p>",    
				bodyBorder:true,
                items:imgviewpanel1      
		    });
		   	imgwindow1.show();
		}
	
	//典型库检索结果
	var record_dx = Ext.data.Record.create([    		
        		{name:'processnum_dx'},
    			{name:'process_dx'},
    			{name:'worktype_dx'},
    			{name:'farm_dx'},
    			{name:'readytime_dx'},
    			{name:'worktime_dx'},
    			{name:'groupnum_dx'}
    			]); 	                                    
    
	var hpObj_dx = {url:'logicmillsim.jsp',method: 'POST',extraParams:{}};
	var ds_dx = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj_dx)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_dx)
    });	                                	
	
	var grid_dx = new Ext.grid.GridPanel({
		title: false,
		id:'dxtable',
		enableColumnMove:true,
		columns:[
				 {header: "工序号",dataIndex: "processnum_dx",width : 75,align:'center',sortable: true},
				 {header: "工序名称",dataIndex: "process_dx",width : 100,align:'center',sortable: true},
				 {header: "工种",dataIndex: "worktype_dx",width : 100,align:'center',sortable: true},
				 {header: "车间",dataIndex: "farm_dx",width : 100, align:'center', sortable: true},
				 {header: "准备时间(h)",dataIndex: "readytime_dx",width : 100,align:'center',sortable: true},
				 {header: "加工时间(h)",dataIndex: "worktime_dx",width : 100,align:'center',sortable: true},
				 {header: "每组数量",dataIndex: "groupnum_dx",width : 100,align:'center',sortable: true}],
				ds:ds_dx,
				//sm:sm_dx,
				height:height-500,
				autoHeight :false,
		        autoScroll :true
	});
	
	var simout = new Ext.Panel({
		height:height-465,
		width:width-320,
		layout:"form",
		title: "<p align='left'>相似实例</p>",
		items: [grid_dx]
	});
	
	var sim = new Ext.FormPanel({
		renderTo: 'simdiv',
		layout:"column",
		items: [simin,simout]
	});
		
	//相似检索弹出界面
	//零件类型添加大类
    var partdalei= Ext.data.Record.create([
        			{name:'dalei'}			
        			 ]);  
	 var storedalei = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/quotamod/milling/logicmillsim.jsp?type=QUERYDALEI"}), // 数据源
	     reader: new Ext.data.JsonReader({
	         id:'id',
		     root:"filedata"
		       },partdalei),
		     remoteSort: true
	     });
	 storedalei.load();
	var daleicombox=new Ext.form.ComboBox({
		fieldLabel:'零件大类',
     	name:'dalei',
		id:"dalei",
		store:storedalei, 
		displayField:'dalei',
		triggerAction: 'all',
		width:200,
		listeners:{
			select:function(){
				storexiaolei.load({params:{dalei:daleicombox.getValue()}});
			}
			},
		emptyText:'请选择零件大类',
		allowBlank:true,
		mode:'local',
		typeAhead: true,
		editable:true
     });
	//零件小类
	var partxiaolei= Ext.data.Record.create([
    			{name:'xiaolei'}			
    			 ]);  
	var storexiaolei = new Ext.data.Store ({
     proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/quotamod/milling/logicmillsim.jsp?type=QUERYXIAOLEI"}), // 数据源
     reader: new Ext.data.JsonReader({
         id:'id',
	     root:"filedata"
	       },partxiaolei),
	     remoteSort: true
     });
     var xiaoleicombox=new Ext.form.ComboBox({
			fieldLabel:'零件小类',
	     	name:'xiaolei',
			id:"xiaolei",
			store:storexiaolei, 
			displayField:'xiaolei',
			triggerAction: 'all',
			width:200,
			emptyText:'请选择零件小类',
			allowBlank:true,
			mode:'local',
			typeAhead: true,
			editable:true
	});
     // 定义图号阈值
	 var tuhao_com= Ext.data.Record.create([    		
	              		{name:'id'},
	          			{name:'tuhao'}			
	          		   ]); 
	 var tuhaoCom = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/quotamod/milling/logicmillsim.jsp?type=QUERYTUHAO"}), // 数据源
	     reader: new Ext.data.JsonReader({
	         id:'id',
		     root:"filedata"
		       },tuhao_com),
		     remoteSort: true
	     });
	 tuhaoCom.load();
	 	var ComboBoxtuhao=new Ext.form.ComboBox({
			fieldLabel:'图号阈值',
	     	name:'thyz',
			id:"thyz",
			store:tuhaoCom, 
			displayField:'tuhao',
			valueField:'id',
			triggerAction: 'all',
			width:200,
			emptyText:'请选择图号阈值',
			allowBlank:true,
			mode:'local',
			typeAhead: true,
			editable:true
   }); 
  // 定义添加窗口材料
	 var material_com= Ext.data.Record.create([    		
	              		{name:'id'},
	          			{name:'paihao'}			
	          		   ]); 
	 var materialCom = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/quotamod/milling/logicmillsim.jsp?type=QUERYMATERIAL"}), // 数据源
	     reader: new Ext.data.JsonReader({
	         id:'id',
		     root:"filedata"
		       },material_com),
		     remoteSort: true
	     });
		materialCom.load();
	 	var ComboBoxMaterial=new Ext.form.ComboBox({
			fieldLabel:'材料牌号',
	     	name:'clph',
			id:"clph",
			store:materialCom, 
			displayField:'paihao',
			valueField:'id',
			triggerAction: 'all',
			width:200,
			emptyText:'请选择材料牌号',
			allowBlank:true,
			mode:'local',
			typeAhead: true,
			editable:true
   }); 
	 // 定义机床型号
		 var machine_com= Ext.data.Record.create([    		
		              		{name:'id'},
		          			{name:'xinghao'}			
		          		   ]); 
		 var machineCom = new Ext.data.Store ({
		     proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/quotamod/milling/logicmillsim.jsp?type=QUERYMACHINE"}), // 数据源
		     reader: new Ext.data.JsonReader({
		         id:'id',
			     root:"filedata"
			       },machine_com),
			     remoteSort: true
		     });
		 machineCom.load();
		 	var ComboBoxmachine=new Ext.form.ComboBox({
				fieldLabel:'机床型号',
		     	name:'jcxh',
				id:"jcxh",
				store:machineCom, 
				displayField:'xinghao',
				valueField:'id',
				triggerAction: 'all',
				width:200,
				emptyText:'请选择机床型号',
				allowBlank:true,
				mode:'local',
				typeAhead: true,
				editable:true
	   }); 
    //检索信息输入
	var xiangsishuru = new Ext.FormPanel({
        id:'xiangsishuru',
        labelWidth: 80, 
        hideLabels:false,
        frame: true,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',
		layout:'column',
        items: [{					              
            layout: 'form',
            labelAlign:"right",
            columnWidth:.33,
            items:[daleicombox,xiaoleicombox,ComboBoxtuhao,ComboBoxMaterial]
        },{
        	layout: 'form',
            labelAlign:"right",
            columnWidth:.33,
            items:[ComboBoxmachine,
                   {xtype:'textfield',fieldLabel: '毛坯长度',id: 'maopilong',width: 200},
                   {xtype:'textfield',fieldLabel: '毛坯宽度',id: 'maopiwidth',width: 200},
                   {xtype:'textfield',fieldLabel: '毛坯高度',id: 'maopihigh',width: 200}]
        },{					              
        	layout: 'form',
            labelAlign:"right",
            buttonAlign:"center",
            columnWidth:.34,
            items:[
                   {xtype:'textfield',fieldLabel: '切削体积',id: 'qxtj',width: 200},
                   {xtype:'textfield',fieldLabel: '切削表面积',id: 'qxmj',width: 200}],
            buttons:[{
    			id:'jiansuobtn',
                text:'相似检索',
                handler:jiansuoFn
                },{
    			id:'ceshibtn',
                text:'测试',
                handler:ceshiFn}]
        }]
		    })
	//相似度计算函数
	function jiansuoFn(){
		var millwide = Ext.get("millwide").dom.value.trim();
		var millthick = Ext.get("millthick").dom.value.trim();
		var x = millwide*millwide + millthick*millthick;
		var s = Math.sqrt(x);
		alert(s);
	}
	//相似度计算测试函数
	function ceshiFn(){
		//var filter = "1=1 and good_num = '" + record[0].get("goodnum").toString() + "'";
		//var order = "process_num ASC";
		//hpObj_sim.extraParams.filter = filter;
		//hpObj_sim.extraParams.order = order;   	
		hpObj_sim.extraParams.type = "QUERYSIM";
		ds_sim.load();
	}
    //相似检索结果显示
    var record_sim = Ext.data.Record.create([    		
                                    		{name:'dalei_sim'},
                                			{name:'xiaolei_sim'},
                                			{name:'daihao_sim'},
                                			{name:'mingcheng_sim'},
                                			{name:'tuhao_sim'},
                                			{name:'material_sim'},
                                			{name:'machine_sim'},
                                			{name:'maopilong_sim'},
                                			{name:'maopiwidth_sim'},
                                			{name:'maopihigh_sim'},
                                			{name:'qxtj_sim'},
                                			{name:'qxmj_sim'},
                                			{name:'xiangsidu_sim'},
                                			{name:'pingjia_sim'}
                                			]); 
                            	                                                                
    	var hpObj_sim = {url:'logicmillsim.jsp',method: 'POST',extraParams:{}};
    	var ds_sim = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj_sim)),
            reader: new Ext.data.JsonReader({
                totalProperty:'totalProperty',
                root:'filedata'
            },record_sim)
        });
                            	                                                  	
    	var sm_sim = new Ext.grid.CheckboxSelectionModel();
    	var grid_sim = new Ext.grid.GridPanel({
    		title: false,//"工时定额信息",
    		id:'simtable',
    		enableColumnMove:false,
    		columns:[sm_sim,
    		         {header: "零件大类",dataIndex: "dalei_sim",hidden:true,width : 80,align:'center',sortable: true},
    		         {header: "零件小类",dataIndex: "xiaolei_sim",hidden:true,width : 80,align:'center',sortable: true},
    		         {header: "产品代号",dataIndex: "daihao_sim",hidden:true,width : 80,align:'center',sortable: true},
    		         {header: "零件名称",dataIndex: "mingcheng_sim",width : 80,align:'center',sortable: true},
    		         {header: "零件图号",dataIndex: "tuhao_sim",width : 80,align:'center',sortable: true},
    		         {header: "材料",dataIndex: "material_sim",width : 80,align:'center',sortable: true},
    		         {header: "加工设备",dataIndex: "machine_sim",width : 80,align:'center',sortable: true},
    		         {header: "毛坯长度",dataIndex: "maopilong_sim",width : 80,align:'center',sortable: true},
    		         {header: "毛坯宽度",dataIndex: "maopiwidth_sim",width : 80,align:'center',sortable: true},
    		         {header: "毛坯高度",dataIndex: "maopihigh_sim",width : 80,align:'center',sortable: true},
    		         {header: "切削体积",dataIndex: "qxtj_sim",width : 80,align:'center',sortable: true},
    		         {header: "切削表面积",dataIndex: "qxmj_sim",width : 80,align:'center',sortable: true},
    		         {header: "相似度",dataIndex: "xiangsidu_sim",width : 80,align:'center',sortable: true},
    		         {header: "评价",dataIndex: "pingjia_sim",width : 70,align:'center',sortable: true}],
    		ds:ds_sim,
    		sm:sm_sim,
    		width:980,
    		height:300,
            autoScroll :true
    	}); 
    	var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
    //相似检索总体弹出界面	
	var sim_win = new Ext.Window({
		id:'window',
		modal:true,
        width:1000,
        height:550,
        closable:false,
        buttonAlign:"center",
        plain: true,
        title:"<p align='center'>相似检索</p>",    
		bodyBorder:true,
		items:[xiangsishuru,{
				        	items:grid_sim,
							region:'center',
							layout:'fit',
							id:'tablepanel',
							autoScroll:true,
							tbar:new Ext.Toolbar({ 
									items:[toolone,{
					        			id:'img-btn',
					        			text:"查看零件图片",
					        			handler: viewImgFn,
					        			iconCls:"detail"
					        		}]
					        })		
				        }],
        buttons: [{ id:'xuanyongbtn',
			text: '选用实例',
			handler:xuanyongFn
        },{ id:'returnbtn',
			text: '返回',
			handler:function cancelFn(){
			   	sim_win.hide();
			   }
        }]
    });
	
	//查看零件图片函数
	function viewImgFn(){
			 //显示图片panel
		var record = sm_sim.getSelections();
		if(record.length==0){
			Ext.MessageBox.alert('提示','请选择要浏览的图片');
		}else if(record.length!=1){
			Ext.MessageBox.alert('提示','每次只能选择一个零件');
		}else{
	    var tuhao_sim = record[0].get("tuhao_sim");
    	var imgviewpanel=new Ext.FormPanel({
			id:'imgview',
		    labelWidth:75,
		    width:1000,
		    height:700,			      				       
		    frame:true,
		    bodyStyle:'padding:10px 10px 0',	
		    html:'<img width=510 hight=1024 alt="零件图片" src="logicimgview.jsp?type=viewimg&tuhao_sim='+tuhao_sim+'"/>',
		    items: [{
		    buttons: [{
		     			id:'backbtn',
						text: '返回',
						handler: function(){
						 Ext.getCmp("imgview").hide();
						}
		            }
		       	]
		    }]
		   	});
		  var imgwindow = new Ext.Window({
    			id:'imgwindow',
    			modal:true,
                width:1000,
                height:700,
                closable:true,
             //   closeAction:'hide',
                plain: true,
                title:"<p align='center'>查看零件图片</p>",    
				bodyBorder:true,
                items:imgviewpanel      
		    });
		   	imgwindow.show();
		}
		}
	
	//实例选用函数
	function xuanyongFn(){
		var record = sm_sim.getSelections();
		if(record.length==0){
			Ext.MessageBox.alert('提示','请选择一条实例');
		}else if(record.length!=1){
			Ext.MessageBox.alert('提示','每次只能选择一个实例');
		}else{
			var filter = "1=1 and part_num = '" + record[0].get("tuhao_sim").toString() + "'";
			var order = "process_num ASC";
			hpObj_dx.extraParams.order = order;
			hpObj_dx.extraParams.filter = filter; 	
			hpObj_dx.extraParams.type = "QUERYSIMALL";
			ds_dx.load();
			//总体信息填充
			simin.findById("ljmcdx").setValue(record[0].get("mingcheng_sim").toString());
			simin.findById("ljthdx").setValue(record[0].get("tuhao_sim").toString());
			simin.findById("clmcdx").setValue(record[0].get("material_sim").toString());
			simin.findById("mpcddx").setValue(record[0].get("maopilong_sim").toString());
			simin.findById("mpkddx").setValue(record[0].get("maopiwidth_sim").toString());
			simin.findById("mpgddx").setValue(record[0].get("maopihigh_sim").toString());
			simin.findById("xsddx").setValue(record[0].get("xiangsidu_sim").toString());
			simin.findById("pjzdx").setValue(record[0].get("pingjia_sim").toString());			
			sim_win.hide();
		}	
	}
	
	function addsimFn(){
		
	}
	
	//基于典型工序的工时计算模块
	//备料
	function beiliaojsFn(){
		var millwide = Ext.get("millwide").dom.value.trim();
		var millthick = Ext.get("millthick").dom.value.trim();
		var s = (millwide*millwide + millthick*millthick);
		var x = Math.sqrt(s);
		var p1 = -(9.539e-06); 
        var p2 = 0.005648;
        var p3 = -0.3948;
        var p4 = 13.02;
	    var t = p1*x*x*x + p2*x*x + p3*x + p4;
	      var readytime = 0.25;
		  var worktime = t/60;
		  var records = sm.getSelections();
		  var row = new Ext.data.Record({ 
  		  processnum:records[0].get("processnum"), 
  		  process:records[0].get("process"), 
  		  worktype:records[0].get("worktype"), 
  		  farm:records[0].get("farm"), 
  		  equipment:records[0].get("equipment"), 
  		  readytime:readytime,
  		  worktime:worktime,
  		  groupnum:''
            });
		  var num = ds.indexOf(records[0]);							  
		  ds.remove(records[0]);
          ds.insert(num,row); 
		
	}
	//铣
	//定义加工特征combox
	var jgtz = [['mian','面'],['qiang','腔'],['kong','孔'],['cao','槽']];
	var storejgtz = new Ext.data.SimpleStore({fields:['typeid','type'],data:jgtz});	
	var jgtzCombox = new Ext.form.ComboBox({
		id:"jgtz",
		name:"jgtz",
		fieldLabel:'加工特征',
		displayField:'type',
		lazyRender:true,
		emptyText:'请选择加工特征',
		width:90,
		store: storejgtz,
		typeAhead: true,
		triggerAction: 'all',
		selectOnFocus:true,
		mode:'local',
		editable:false	
	});
	//详细定额列表
	var record_xi = Ext.data.Record.create([    {name:'tezheng_xi'},
	                                			{name:'chang_xi'},
	                                			{name:'kuan_xi'},
	                                			{name:'hou_xi'},
	                                			{name:'d_xi'},
	                                			{name:'shen_xi'},
	                                			{name:'shu_xi'},//特征参数
	                                			{name:'time_xi'},
	                                			{name:'time1_xi'}
	                                			]); 
	                            	                                                                
	var hpObj_xi = {url:'logicmillsim.jsp',method: 'POST',extraParams:{}};
	var ds_xi = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj_xi)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_xi)
    });
	                            	                                                  	
	var sm_xi = new Ext.grid.CheckboxSelectionModel();
	var grid_xi = new Ext.grid.EditorGridPanel({
		title: false,
		id:'xitable',
		enableColumnMove:false,
		loadMask:true,
		clicksToEdit: 1,
		columns:[sm_xi,
		         {header: "加工特征",dataIndex: "tezheng_xi",width : 50,align:'center',editor:jgtzCombox,sortable: true},
		         {header: "刀具直径(mm)",dataIndex: "chang_xi",width : 70,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "主轴转速(r/min)",dataIndex: "kuan_xi",width : 70,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "进给速度(mm/min)",dataIndex: "hou_xi",width : 70,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "切削深度(mm)",dataIndex: "d_xi",width : 70,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "切削宽度(mm)",dataIndex: "shen_xi",width : 70,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "尺寸长度(mm)",dataIndex: "shu_xi",width : 70,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "尺寸宽度(mm)",dataIndex: "time_xi",width : 70,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header: "尺寸深度(mm)",dataIndex: "time1_xi",width : 70,align:'center',editor: new Ext.form.NumberField(),sortable: true},
		         {header : '计算',dataIndex : 'jisuan_xi',width : 70,align:'center',renderer: createButton_xi}],
		ds:ds_xi,
		sm:sm_xi,
		width:700,
		height:300,
        autoScroll :true
	}); 
	
	window.jisuanxiFn = function(){ 
		var records = sm_xi.getSelections();
		if(records[0].get("tezheng_xi")=="面"){
			 mianjsFn();		
		}else if(records[0].get("tezheng_xi")=="腔"){
			 mianjsFn();		
		}else if(records[0].get("tezheng_xi")=="孔"){
			kongjsFn();
		}else if(records[0].get("tezheng_xi")=="槽"){
			 mianjsFn();		
		}
   	}
	
	function createButton_xi(){   
		return "<input type='button' value='计算' onclick='jisuanxiFn()' >" ;   
	    }
	//加工参数选择列表
			var record_x = Ext.data.Record.create([    		
			                               		{name:'id'},//铣削参数id
			                        			{name:'bianhao'},//铣削参数编号
			                        			{name:'jcxh'},//机床型号
			                        			{name:'clmc'},//工件材料名称
			                        			/*{name:'jgte'},*/
			                        			{name:'djxh'},//刀具型号
			                        			{name:'zhijing'},//刀具直径
			                        			{name:'company'},//刀具生产厂商
			                        			{name:'xlh'},//刀具系列号
			                        			{name:'chishu'},//刀具齿数
			                        			{name:'djmc'},//刀具名称
			                        			{name:'rjbj'},//R角半径
			                        			{name:'jgfs'},//加工方式
			                        			{name:'ap'},//切深
			                        			{name:'ae'},//切宽
			                        			{name:'jgsd'},//进给速度
			                        			{name:'qxsd'},//切削速度
			                        			{name:'zzzs'},//主轴转速
			                        			{name:'mcjg'},//每齿进给量
			                        			{name:'xsl'},//悬伸量
			                        			{name:'dtlx'},//刀套类型
			                        			{name:'lqy'},//冷却液
			                        			{name:'lqfs'},//冷却方式
			                        			{name:'clqcl'},//材料去除率
			                        			{name:'fx'},//FX
			                        			{name:'fy'},//Fy
			                        			{name:'fz'},//Fz
			                        			{name:'gl'},//功率
			                        			{name:'nj'},//扭矩
			                        			{name:'bx'},//变形
			                        			{name:'jgyl'},//加工余量
			                        			{name:'djjc'},//刀具夹持部位弯曲应力
			                        			{name:'djrb'},//刀具韧部弯曲应力
			                        			{name:'dtyl'},//刀头应力
			                        			{name:'ccddj'},//粗糙度等级
			                        			{name:'qxwd'},//切削温度
			                        			{name:'yzr'},//验证人
			                        			{name:'sjly'},//数据来源
			                        			{name:'xxfs'},//铣削方式
			                        			{name:'gxtj'},//刚性条件
			                        			{name:'yyzt'},//应用状态
			                        			{name:'djsm'},//刀具寿命
			                        			{name:'beizhu'}//备注
			                        		]); 
		                            	                                                                
		var hpObj_x = {url:'/gsde/web/sms/cutparamod/logiccutpara.jsp',method: 'POST',extraParams:{}};
		var ds_x = new Ext.data.Store({
		    proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj_x)),
		    reader: new Ext.data.JsonReader({
		        totalProperty:'totalProperty',
		        root:'filedata'
		    },record_x)
		});
		                            	                                                  	
		var sm_x = new Ext.grid.CheckboxSelectionModel();
		var grid_x = new Ext.grid.GridPanel({
			title: false,
			id:'table',
			enableColumnMove:false,
			columns:[sm_x,
			        	{header: "铣削参数编号",dataIndex: "bianhao",width:80,hidden:true,align:'center',sortable: true},
			        	{header: "机床型号",align:'center',dataIndex: "jcxh",width:80,sortable: true}, 
			        	{header: "材料牌号",hidden:true,align:'center',width:80,dataIndex: "clmc",sortable: true},
			        	{header: "刀具型号",align:'center',dataIndex: "djxh",width:80,sortable: true}, 
			        	{header: "刀具直径(mm)",align:'center',dataIndex: "zhijing",width:80,sortable: true},
			        	{header: "刀具厂商",align:'center',width:90,hidden:true,dataIndex: "company",sortable: true},
			        	{header: "刀具系列号",align:'center',hidden:true,dataIndex: "xlh",width:100,sortable: true},
			        	{header: "刀具名称",dataIndex: "djmc",hidden:true,align:'center',width:100,sortable: true},
			        	{header: "R角半径(mm)",dataIndex: "rjbj",align:'center',hidden:true,width:100,sortable: true},
			        	{header: "加工方式",align:'center',width:90,dataIndex: "jgfs",sortable: true},
			        	{header: "加工余量",align:'center',hidden:true,width:90,dataIndex: "jgyl",sortable: true},
			        	{header: "粗糙度等级 ",align:'center',hidden:true,dataIndex: "ccddj",width:110,sortable: true},
			        	{header: "切深(mm)",dataIndex: "ap",align:'center',width:100,sortable: true},
			        	{header: "切宽(mm)",align:'center',dataIndex: "ae",width:100,sortable: true},
			        	{header: "进给速度(mm/min)",dataIndex: "jgsd",align:'center',width:100,sortable: true},
			        	{header: "切削速度(m/min)",dataIndex: "qxsd",align:'center',width:100,sortable: true }, 
			        	{header: "主轴转速(r/min)",dataIndex: "zzzs",align:'center',width:100,sortable: true},
			        	{header: "每齿进给量(mm/齿)",dataIndex: "mcjg",align:'center',width:120,sortable: true},
			        	{header: "悬伸量(mm)",dataIndex: "xsl",hidden:true,align:'center',width:125,sortable: true},
			        	{header: "刀套类型",dataIndex: "dtlx",align:'center',width:100,hidden:true,sortable: true},
			        	{header: "冷却液",align:'center',dataIndex: "lqy",width:100,hidden:true,sortable: true},
			        	{header: "冷却方式",align:'center',dataIndex: "lqfs",width:100,hidden:true,sortable: true},
			        	{header: "材料去除率(cm3/min)",align:'center',hidden:true,dataIndex: "clqcl",width:110,sortable: true},
			        	{header: "Fx(N)",align:'center',dataIndex: "fx",width:100,hidden:true,sortable: true},
			        	{header: "Fy(N)",align:'center',hidden:true,dataIndex: "fy",width:110,sortable: true},
			        	{header: "Fz(N)",align:'center',hidden:true,dataIndex: "fz",width:110,sortable: true},
			        	{header: "功率(W)",align:'center',dataIndex: "gl",width:100,hidden:true,sortable: true},
			        	{header: "扭矩(NM)",align:'center',hidden:true,dataIndex: "nj",width:110,sortable: true   },
			        	{header: "变形(mm)",align:'center',hidden:true,dataIndex: "bx",width:110,sortable: true},
			        	{header: "刀具夹持部位弯曲应力(MPa) ",align:'center',hidden:true,dataIndex: "djjc",width:110,sortable: true},
			        	{header: "刀具韧部位弯曲应力 (MPa)",align:'center',hidden:true,dataIndex: "djrb",width:110,sortable: true},
			        	{header: "刀头应力 (MPa)",align:'center',hidden:true,dataIndex: "dtyl",width:110,sortable: true},
			        	{header: "切削温度(°C) ",align:'center',hidden:true,dataIndex: "qxwd",width:110,sortable: true},
			        	{header: "验证人 ",align:'center',hidden:true,dataIndex: "yzr",width:110,sortable: true},
			        	{header: "数据来源 ",align:'center',hidden:true,dataIndex: "sjly",width:110,sortable: true},
			        	{header: "铣削方式 ",align:'center',hidden:true,dataIndex: "xxfs",width:110,sortable: true},
			        	{header: "刚性条件 ",align:'center',hidden:true,dataIndex: "gxtj",width:110,sortable: true},
			        	{header: "应用状态 ",align:'center',hidden:true,dataIndex: "yyzt",width:110,sortable: true},
			        	{header: "刀具寿命 ",align:'center',hidden:true,dataIndex: "djsm",width:110,sortable: true},
			        	{header: "备注 ",align:'center',hidden:true,dataIndex: "beizhu",width:110,sortable: true}],
			ds:ds_x,
			sm:sm_x,
			width:980,
			height:200,
		    autoScroll :true
		});

		var xijs = new Ext.Window({
			id:'xijs',
			modal:true,
	        width:700,
	        height:400,
	        closable:false,
	        buttonAlign:"center",
	        plain: true,
	        title:"<p align='center'>铣工序工时计算</p>",    
			bodyBorder:true,
			items:[{
					        	items:grid_xi,
								region:'center',
								layout:'fit',
								id:'tablepanel',
								autoScroll:true,
								tbar:new Ext.Toolbar({ 
										items:[{
						        			id:'gb-btn',
						        			text:"添加工步",
						        			handler: function addgbFn(){
						        		    	var row1 = new Ext.data.Record({});
						        				ds_xi.add(row1);				        		
						        			},
						        			iconCls:"add"
						        		},{id:'delbtn',text:"删除工步", handler:delgbFn,iconCls:"delete"}]
						        })		
					        }/*,{
					        	items:grid_x,
								region:'center',
								layout:'fit',
								id:'tablepanelx',
								autoScroll:true,
								tbar:new Ext.Toolbar({ 
										items:[{
						        			id:'xycs-btn',
						        			text:"选用参数",//附带定额时间计算
						        			handler: xycsFn,
						        			iconCls:"detail"
						        		}]
						        })	
					        }*/],
	        buttons: [{ id:'hzjs',
				text: '汇总计算',
				handler:hzjsFn
	        },{ id:'returnbtn',
				text: '返回',
				handler:function cancelFn(){
					xijs.hide();
				   }
	        }]
	    });
		//汇总计算功能
		function hzjsFn(){
			var s = 0;
			var t = 0;
			sm_xi.selectAll();//全选
			var record = sm_xi.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示',"无工步工时");
			}else{
				Ext.MessageBox.confirm('提示',"所有工步已齐全,确认汇总计算吗？",function(btn){
					if(btn == 'yes'){
						for(var i=0;i<record.length;i++){
								var t = record[i].get("time_xi");	
								s = s*1 + t*1;
								}
						var records = sm.getSelections();
						  var row = new Ext.data.Record({ 
				    		  processnum:records[0].get("processnum"), 
				    		  process:records[0].get("process"), 
				    		  worktype:records[0].get("worktype"), 
				    		  farm:records[0].get("farm"), 
				    		  equipment:records[0].get("equipment"), 
				    		  readytime:records[0].get("readytime"), 
				    		  worktime:s,
				    		  groupnum:''
				          });
						  var num = ds.indexOf(records[0]);							  
						  ds.remove(records[0]);
				          ds.insert(num,row); 
				          xijs.hide();
						}else{
						Ext.MessageBox.alert('提示',"您已经取消当前操作");
					}
				});
				}
			
		}
		//选用加工参数函数
		function xycsFn(){
			var record =sm_x.getSelections();// 返回值为 Record 类型            	
			if(record.length==0){
	           Ext.MessageBox.alert("提示",'请先选择要选用的加工参数!');
	            return;
	        }else if(record.length!=1){
	        	Ext.MessageBox.alert("提示",'每次只能选择一条加工参数!');
	            return;
	        }else{    			
	        	var jgsd_xi = record[0].get("jgsd").toString();
	        	var qxsd_xi = record[0].get("qxsd").toString();
	        	var qs_xi = record[0].get("ap").toString();
	        	var qk_xi = record[0].get("ae").toString();
	        	var zzzs_xi = record[0].get("zzzs").toString();	        	
	        	var records = sm_xi.getSelections();
	        	//var company_xi = records[0].get("company_xi").toString();
				  var row = new Ext.data.Record({ 
					  tezheng_xi:records[0].get("tezheng_xi"), 
					  machine_xi:records[0].get("machine_xi"), 
					  material_xi:records[0].get("material_xi"), 
					  company_xi:records[0].get("company_xi"), 
					  cut_xi:records[0].get("cut_xi"), 
					  zhijing_xi:records[0].get("zhijing_xi"), 
					  jingdu_xi:records[0].get("jingdu_xi"), 
					  jgsd_xi:jgsd_xi,
					  qxsd_xi:qxsd_xi,
					  qs_xi:qs_xi,
					  qk_xi:qk_xi,
					  zzzs_xi:zzzs_xi
		          });
				  var num = ds_xi.indexOf(records[0]);		
				  ds_xi.remove(records[0]);
				  ds_xi.insert(num,row);
	        }       		
		}
		//删除工步函数
		function delgbFn(){
			var records = sm_xi.getSelections();
			if(records.length==0){
				Ext.MessageBox.alert('提示',"请选择要删除的行!");
				return;
			}else{
				Ext.MessageBox.confirm('提示',"您即将删除所选择的工步,确认吗？",function(btn){
					if(btn == 'yes'){
						Ext.showProgressDialog();
						for(var i=0;i<records.length;i++){
							ds_xi.remove(records[i]);
						}
						Ext.hideProgressDialog();
						Ext.MessageBox.alert('提示',"您所选择的工步已经删除");
						//ds.load();
					}else{
						Ext.MessageBox.alert('提示',"您已经取消当前操作");
					}
				});
			}	
		}
	//工步计算函数
	   //面,槽，腔	
	function mianjsFn(){
		var record = sm_xi.getSelections();
		var tezheng_xi = record[0].get("tezheng_xi").toString();
		var chang_xi = record[0].get("chang_xi").toString();
		var kuan_xi = record[0].get("kuan_xi").toString();
		var hou_xi = record[0].get("hou_xi").toString();
		var v = chang_xi * kuan_xi * hou_xi;//体积
		var quchulv = 4 * 8 * 600;//切深4mm，切宽：8mm，进给600mm/min
		var t = ((v/quchulv)/60).toFixed(3);
		var row = new Ext.data.Record({ 
			  tezheng_xi:tezheng_xi,
			  chang_xi:chang_xi,
			  kuan_xi:kuan_xi,
			  hou_xi:hou_xi,
			  time_xi:t
        });
		var num = ds_xi.indexOf(record[0]);		
		ds_xi.remove(record[0]);
		ds_xi.insert(num,row);
	}
		//孔
	function kongjsFn(){
		var record = sm_xi.getSelections();
		var tezheng_xi = record[0].get("tezheng_xi").toString();
		var d_xi = record[0].get("d_xi").toString();
		var shen_xi = record[0].get("shen_xi").toString();
		var shu_xi = record[0].get("shu_xi").toString();
		var t = ((shu_xi * shen_xi/100)/60).toFixed(3);//进给100mm/min
		var row = new Ext.data.Record({ 
			  tezheng_xi:tezheng_xi,
			  d_xi:d_xi,
			  shen_xi:shen_xi,
			  shu_xi:shu_xi,
			  time_xi:t
        });
		var num = ds_xi.indexOf(record[0]);		
		ds_xi.remove(record[0]);
		ds_xi.insert(num,row);			
	}
	//钳工
	
	//热处理
	function rechulijsFn(){
	      var readytime = 2;
		  var worktime = 0;
		  var records = sm.getSelections();
		  var row = new Ext.data.Record({ 
  		  processnum:records[0].get("processnum"), 
  		  process:records[0].get("process"), 
  		  worktype:records[0].get("worktype"), 
  		  farm:records[0].get("farm"), 
  		  equipment:records[0].get("equipment"), 
  		  readytime:readytime,
  		  worktime:worktime,
  		  groupnum:''
            });
		  var num = ds.indexOf(records[0]);							  
		  ds.remove(records[0]);
          ds.insert(num,row); 
		
	}
	//电镀
	
	
});