Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=10000;
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
            layout: 'form',
            labelAlign:"right",
            columnWidth:.25,
            items:[{
            	xtype:'textfield',
                fieldLabel: '产品名称',
                id: 'goodname',
                width: 150
            },{
            	xtype:'textfield',
                fieldLabel: '零件名称',
                id: 'partname',
                width: 150
            },{
            	xtype:'textfield',
                fieldLabel: '整件图号',
                id: 'goodnum',
                width: 150
            },{
            	xtype:'textfield',
                fieldLabel: '零件图号',
                id: 'partnum',
                width: 150
            }]
        },{
        	layout: 'form',
            labelAlign:"right",
            //labelWidth:70,
            columnWidth:.25,
            items:[{
            	xtype:'textfield',
                fieldLabel: '材料名称',
                id: 'materialname',
                width: 150
            },{
            	xtype:'textfield',
                fieldLabel: '材料牌号',
                id: 'materialcard',
                width: 150
            },{
            	xtype:'textfield',
                fieldLabel: '材料编码',
                id: 'materialnum',
                width: 150
            },{
            	xtype:'textfield',
                fieldLabel: '材料标准号',
                id: 'materialstandard',
                width: 150
            }]
        },{				              
        	layout: 'form',
            labelAlign:"right",
           // labelWidth:80,
            columnWidth:.25,
            items:[{
            	xtype:'textfield',
                fieldLabel: '毛坯厚度',
                //emptyText:'0',
                id: 'platethick',
                width: 150,
               	regex:/^[0-9]+([.]{0,1}[0-9]+){0,1}$/,
                regexText:'只能由数字组成!'
            },{
            	xtype:'textfield',
                fieldLabel: '毛坯长度',
                //emptyText:'0',
                id: 'platelong',
                width: 150,
               	regex:/^[0-9]+([.]{0,1}[0-9]+){0,1}$/,
                regexText:'只能由数字组成!'
            },{
            	xtype:'textfield',
                fieldLabel: '毛坯宽度',
                //emptyText:'0',
                id: 'platewide',
                width: 150,
               	regex:/^[0-9]+([.]{0,1}[0-9]+){0,1}$/,
                regexText:'只能由数字组成!'
            },{
            	xtype:'textfield',
                fieldLabel: '毛坯中零件数',
                //emptyText:'0',
                id: 'platenum',
                width: 150,
               	regex:/^[0-9]*$/,
                regexText:'只能由数字组成!'
            }]
        },{					              
        	layout: 'form',
            labelAlign:"right",
            buttonAlign:"center",
            //labelWidth:60,
            columnWidth:.25,
            items:[{
            	fieldLabel:"日期",
	         	xtype: 'datefield',
	         	width:150,
	         	id:'platedate',
	         	format:'Y-m-d'
            }],
            buttons:[{
    			id:'saveallbtn',
                text:'任务载入',
                handler:function(){
					if(!input_win.isVisible())
						input_win.show();
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
        	document.getElementById('platelong').value = oSheet.Cells(1,6).value.toString();
        	}else{document.getElementById('platelong').value = "";}
        if(!oSheet.Cells(2, 6).value == ""){
        	document.getElementById('platewide').value = oSheet.Cells(2,6).value.toString();
        	}else{document.getElementById('platewide').value = "";}
        if(!oSheet.Cells(3, 6).value == ""){
        	document.getElementById('platethick').value = oSheet.Cells(3,6).value.toString();
        	}else{document.getElementById('platethick').value = "";}
        if(!oSheet.Cells(4, 6).value == ""){
        	document.getElementById('platenum').value = oSheet.Cells(4,6).value.toString();
        	}else{document.getElementById('platenum').value = "";}
        
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
	                        	                                    
	                            
	var hpObj = {url:'logicprintedplate.jsp',method: 'POST',extraParams:{}};
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
		         {
		        	 header: "工序号",
			         dataIndex: "processnum",
			         width : 75,
			         align:'center',
			         sortable: true			         
		         },{
		        	 header: "工序名称",
			         dataIndex: "process",
			         width : 100,
			         //editor: gongxuCombox,
			         align:'center',			        
			         sortable: true
		         },{
		        	 header: "工种",
			         dataIndex: "worktype",
			         width : 100,
			         align:'center',
			         //editor: new Ext.form.TextField(),
			         sortable: true
		         },{
		        	 header: "车间",
			         dataIndex: "farm",
			         width : 100,
			         align:'center',
			        // editor: new Ext.form.TextField(),
			         sortable: true
		         },{
		        	 header: "设备",
			         dataIndex: "equipment",
			         width : 100,
			         align:'center',
			         hidden:true,
			         //editor: new Ext.form.TextField(),
			         sortable: true
		         },{
		        	 header: "准备时间",
			         dataIndex: "readytime",
			         width : 100,
			         align:'center',
			         editor: new Ext.form.NumberField(),
			         sortable: true
		         },{
		        	 header: "加工时间",
			         dataIndex: "worktime",
			         width : 100,
			         align:'center',
			         editor: new Ext.form.NumberField(),
			         sortable: true
		         },{
		        	 header: "每组数量",
			         dataIndex: "groupnum",
			         width : 100,
			         align:'center',
			         editor: new Ext.form.NumberField(),
			         sortable: true
		         },{  
		        	    header : '工时计算',  
		        	    dataIndex : 'jisuan',  
		        	    width : 100,  
		        	    align:'center',		        	    
		        	    renderer: createButton
		         }],
		ds:ds,
		sm:sm,
		height:height-165,
		autoHeight :false,
        autoScroll :true
	});
	
	window.jisuanFn = function(){ 
		var records = sm.getSelections();
		if(records[0].get("process")=="制模板"){
			if(!zhimubanjs.isVisible())
				zhimubanjs.show();			
		}else if(records[0].get("process")=="备料"){			
			beiliaojsFn();
		}else if(records[0].get("process")=="制定位孔"){
			if(!zhidingweikongjs.isVisible())
				zhidingweikongjs.show();
		}else if(records[0].get("process")=="数控钻孔"){
			if(!shukongzuankongjs.isVisible())
				shukongzuankongjs.show();
		}else if(records[0].get("process")=="图形转移"){
			if(!tuxingzhuanyijs.isVisible())
				tuxingzhuanyijs.show();
		}else if(records[0].get("process")=="蚀刻"){
			shikejsFn();
		}else if(records[0].get("process")=="涂覆"){
			if(!tufujs.isVisible())
				tufujs.show();
		}else if(records[0].get("process")=="机械加工"){
			jixiejiagongjsFn();
		}else if(records[0].get("process")=="层压"){
			if(!cengyajs.isVisible())
				cengyajs.show();
		}else if(records[0].get("process")=="孔金属化"){
			if(!kongjinshuhuajs.isVisible())
				kongjinshuhuajs.show();
		}else if(records[0].get("process")=="制样板"){
			if(!zhiyangbanjs.isVisible())
				zhiyangbanjs.show();
		}else if(records[0].get("process")=="制字符"){
			if(!zhizifujs.isVisible())
				zhizifujs.show();
		}else if(records[0].get("process")=="热风平整"){
			refengjsFn();
		}else if(records[0].get("process")=="包封"){
			baofengjsFn();
		}  
   	}
	
	function createButton(){   
		return "<input type='button' value='工时计算' onclick='jisuanFn()' >" ;   
	    } 

	//工时定额窗口组装
	var centerborder = new Ext.FormPanel({
		renderTo: 'selectdiv',
		height:height-135,
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
		        items:[{
		        	id:'tiaoshibtn',
			        text:"调试信息载入", 
			        handler:tiaoshiFn,
			        iconCls:"add"
		        },{
		        	id:'addbtn',
			        text:"定额结果存储", 
			        handler:addFn,
			        iconCls:"save"
		        },{
		        	id:'delbtn',
			        text:"删除", 
			        handler:delFn,
			        iconCls:"delete"
		        }]
			})
		}]
	});

	//工时定额调试信息输入函数
	function tiaoshiFn(){
    	var row1 = new Ext.data.Record({'processnum':'1','process':'制模板','worktype':'照相工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row2 = new Ext.data.Record({'processnum':'2','process':'备料','worktype':'机加工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row3 = new Ext.data.Record({'processnum':'3','process':'制定位孔','worktype':'机加工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row4 = new Ext.data.Record({'processnum':'4','process':'数控钻孔','worktype':'机加工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row5 = new Ext.data.Record({'processnum':'5','process':'图形转移','worktype':'图形制作工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row6 = new Ext.data.Record({'processnum':'6','process':'蚀刻','worktype':'镀覆工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row7 = new Ext.data.Record({'processnum':'7','process':'涂覆','worktype':'镀覆工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row8 = new Ext.data.Record({'processnum':'8','process':'机械加工','worktype':'机加工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row9 = new Ext.data.Record({'processnum':'9','process':'层压','worktype':'机加工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row10 = new Ext.data.Record({'processnum':'10','process':'孔金属化','worktype':'镀覆工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row11 = new Ext.data.Record({'processnum':'11','process':'制样板','worktype':'图形制作工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row12 = new Ext.data.Record({'processnum':'12','process':'制字符','worktype':'图形制作工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row13 = new Ext.data.Record({'processnum':'13','process':'热风平整','worktype':'镀覆工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	var row14 = new Ext.data.Record({'processnum':'14','process':'包封','worktype':'镀覆工','farm':'四','equipment':'','readytime':'','worktime':'','groupnum':''});
    	ds.add(row1); 
        ds.add(row2); 
        ds.add(row3); 
        ds.add(row4); 
        ds.add(row5); 
        ds.add(row6); 
        ds.add(row7); 
        ds.add(row8); 
        ds.add(row9); 
        ds.add(row10); 
        ds.add(row11); 
        ds.add(row12); 
        ds.add(row13); 
        ds.add(row14);
        Ext.getCmp("goodname").setValue("印制板");
        Ext.getCmp("partname").setValue("双面印制板");
        Ext.getCmp("goodnum").setValue("2.726.875");
        Ext.getCmp("partnum").setValue("6.726.875");
        Ext.getCmp("materialname").setValue("覆铜板");
        Ext.getCmp("materialcard").setValue("5A05H");
        Ext.getCmp("materialnum").setValue("2258632159");
        Ext.getCmp("materialstandard").setValue("GB1765");
        Ext.getCmp("platethick").setValue("1");
        Ext.getCmp("platelong").setValue("200");
        Ext.getCmp("platewide").setValue("200");
        Ext.getCmp("platenum").setValue("65");     
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
	
	//制模板
	var zhimubanjs = new Ext.Window({
  		 id:'zhimubanjs',
  		 title:'<p align="center">制模板工时计算</p>',
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
	    id:"zhimubanjs_panel",
	    bodyStyle : 'padding:30px 10px 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:140,
		width:500,
		labelWidth:100,
		layout:'form',
		items : [{  xtype : 'textfield',
					fieldLabel : '请输入层数',					
					id : 'cengshu',
					width : 150,
					regex:/^(0|[1-9][0-9]*)$/,
	                regexText:'只能由数字组成!'
				}]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'zhimuban',
						  handler : function(){
							  var cengshu = Ext.get("cengshu").dom.value.trim();
							  if(cengshu <= 0){
								  alert("请输入正确的层数");
							  }else{
								  var readytime = cengshu*2+4;
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
						          zhimubanjs.hide();
							  }
							  
						  }
					},{
						 text : '取消',						  
						 handler : function() {
						 zhimubanjs.hide();
						 }
					}]
		 
	});
	
	//备料
	function beiliaojsFn(){
		var platelong = Ext.get("platelong").dom.value.trim();
		var platewide = Ext.get("platewide").dom.value.trim();
		var platenum = Ext.get("platenum").dom.value.trim();
		var x = Ext.get("platethick").dom.value.trim();
		var y = platelong*1 + platewide*1;
		var p00 = 0.2551;
	    var p10 = 0.06343;
	    var p01 = 0.001806;
	    var p20 = -0.003283;
	    var p11 = 4.189e-05;
	    var p02 = 2.067e-06;
	    var t = p00*1 + p10*x + p01*y + p20*x*x + p11*x*y + p02*y*y;
	    //数量系数
	    if(platenum>20 && platenum<=50){
	    	var k1 = 0.95;
	    }else if(platenum>50 && platenum<=100){
	    	var k1 = 0.9;
	    }else if(platenum>100 && platenum<=200){
	    	var k1 = 0.85;
	    }else if(platenum>200){
	    	var k1 = 0.8;
	    }else{
	    	var k1 = 1;
	    }
	    //面积系数
	    var s = platelong*platewide/10000;
	    if(s>20 && s<=50){
	    	var k2 = 1.1;
	    }else if(s>50 && s<=100){
	    	var k2 = 1.3;
	    }else if(s>100 && s<=200){
	    	var k2 = 1.6;
	    }else if(s>200){
	    	var k2 = 2;
	    }else{
	    	var k2 = 1;
	    }
	      var readytime = 0.3;
		  var worktime = t*k1*k2/6;
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
	
	//制定位孔
	var zhidingweikongjs = new Ext.Window({
  		 id:'zhidingweikongjs',
  		 title:'<p align="center">制定位孔工时计算</p>',
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
	    id:"zhidingweikongjs_panel",
	    bodyStyle : 'padding:30px 0 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:140,
		width:500,
		labelWidth:150,
		layout:'form',
		items : [{  xtype : 'textfield',
					fieldLabel : '请输入定位孔数量',					
					id : 'kongshu',
					width : 100,
					regex:/^(0|[1-9][0-9]*)$/,
	                regexText:'只能由数字组成!'
				}]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'zhidingweikong',
						  handler : function(){
							  var kongshu = Ext.get("kongshu").dom.value.trim();
							  if(kongshu <= 0){
								  alert("请输入正确的孔数");
							  }else{
								  var readytime = 2;
								  var worktime = 0.025*kongshu;
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
						          zhidingweikongjs.hide();
							  }
							  
						  }
					},{
						 text : '取消',						  
						 handler : function() {
						 zhidingweikongjs.hide();
						 }
					}]
		 
	});
	
	
	//数控钻孔
	var shukongzuankongjs = new Ext.Window({
  		 id:'shukongzuankongjs',
  		 title:'<p align="center">数控钻孔工时计算</p>',
  		 layout:'form',
  		 model:true,//遮挡window后面信息
  		 width:350,
  		 height:250,
  		 plain:true,
  		 buttonAlign:"center",
	 	 bodyBorder:true,
		 resizable:false,
	    closeAction:'hide',
	    items:[new Ext.form.FormPanel({
	    id:"shukongzuankongjs_panel",
	    bodyStyle : 'padding:30px 30px 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:230,
		width:350,
		labelWidth:100,
		layout:'form',
		items : [{  xtype : 'textfield',
					fieldLabel : '请输入孔数量',					
					id : 'shukongkongshu',
					width : 100,
					regex:/^(0|[1-9][0-9]*)$/,
	                regexText:'只能由数字组成!'
				},{
					xtype:"panel", 
                    fieldLabel:"钻孔规格",
                    layout:'table',
                    layoutConfig: {columns:3}, 
                    isFormField:true, 
	                  items: [{
						         xtype:"radio",
						         boxLabel:"6块组",//显示在复选框右边的文字
						         id:'sixpaper',
						         name:"paper",
						         checked:true
			            	 },{
						         xtype:"radio",
						         boxLabel:"4块组",
						         id:'fourpaper',
						         name:"paper"
			                 },{
						         xtype:"radio",
						         boxLabel:"2快组",
						         id:'twopaper',
						         name:"paper"
				             }]
				},{
					xtype:"panel", 
                    fieldLabel:"去毛刺方式",
                    layout:'table',
                    layoutConfig: {columns:2}, 
                    isFormField:true, 
	                  items: [{
					         xtype:"radio",
					         boxLabel:"手动",//显示在复选框右边的文字
					         id:'hand',
					         name:"way",
					         checked:true     
			            	 },{
					         xtype:"radio",
					         boxLabel:"机动",
					         id:'machine',
					         name:"way"
			                 }]
				}]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'shukongzuankong',
						  handler : function(){
							  var shukongkongshu = Ext.get("shukongkongshu").dom.value.trim();
							  if(shukongkongshu <= 0){
								  alert("请输入正确的孔数");
							  }else{
									if(Ext.getCmp("sixpaper").getValue()==true){
										var k1 = 1;
									}
									if(Ext.getCmp("fourpaper").getValue()==true){
										var k1 = 1.5;
									}
									if(Ext.getCmp("twopaper").getValue()==true){
										var k1 = 3;
									}
									if(Ext.getCmp("hand").getValue()==true){
										var t1 = 30;
									}
									if(Ext.getCmp("machine").getValue()==true){
										var t1 = 5;
									}
									//数量系数
									var platenum = Ext.get("platenum").dom.value.trim();
								    if(platenum>30 && platenum<=50){
								    	var k2 = 0.9;
								    }else if(platenum>50 && platenum<=100){
								    	var k2 = 0.8;
								    }else if(platenum>100){
								    	var k2 = 0.75;
								    }else{
								    	var k2 = 1;
								    }
								  var readytime = 2;
								  var worktime = (shukongkongshu/100*k1*k2 + t1)/60;
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
						          shukongzuankongjs.hide();
							  }
							  
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 shukongzuankongjs.hide();
						 }
					}]
		 
	});
	
	//图形转移
	var tuxingzhuanyijs = new Ext.Window({
  		 id:'tuxingzhuanyijs',
  		 title:'<p align="center">图形转移工时计算</p>',
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
	    id:"tuxingzhuanyijs_panel",
	    bodyStyle : 'padding:30px 0 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:140,
		width:500,
		labelWidth:100,
		layout:'form',
		items : [{
			xtype:"panel", 
            fieldLabel:"贴膜方式",
            layout:'table',
            layoutConfig: {columns:3}, 
            isFormField:true, 
              items: [{
				         xtype:"radio",
				         boxLabel:"单面",//显示在复选框右边的文字
				         id:'danmian',
				         name:"tiemo",
				         checked:true
	            	 },{
				         xtype:"radio",
				         boxLabel:"双面",
				         id:'shuangmian',
				         name:"tiemo"
	                 },{
				         xtype:"radio",
				         boxLabel:"多层",
				         id:'duoceng',
				         name:"tiemo"
		             }]
				 }]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'tuxingzhuanyi',
						  handler : function(){
							  var platelong = Ext.get("platelong").dom.value.trim();
							  var platewide = Ext.get("platewide").dom.value.trim();
							  var s = platelong*platewide;
							  if(s <= 150*150){
								  if(Ext.getCmp("danmian").getValue()==true){
									  var t = 20;
								  }
								  if(Ext.getCmp("shuangmian").getValue()==true){
									  var t = 22;
								  }
								  if(Ext.getCmp("duoceng").getValue()==true){
									  var t = 24;
								  }
							  }else if(s>150*150 && s<=250*250){
								  if(Ext.getCmp("danmian").getValue()==true){
									  var t = 28.5;
								  }
								  if(Ext.getCmp("shuangmian").getValue()==true){
									  var t = 31.5;
								  }
								  if(Ext.getCmp("duoceng").getValue()==true){
									  var t = 33.5;
								  }		  
							  }else if(s>250*250 && s<=350*350){
								  if(Ext.getCmp("danmian").getValue()==true){									
									  var t = 41;
								  }
								  if(Ext.getCmp("shuangmian").getValue()==true){
									  var t = 44;
								  }
								  if(Ext.getCmp("duoceng").getValue()==true){
									  var t = 51;
								  }
							  }else{
								  if(Ext.getCmp("danmian").getValue()==true){
									  var t = 54;
								  }
								  if(Ext.getCmp("shuangmian").getValue()==true){
									  var t = 56;
								  }
								  if(Ext.getCmp("duoceng").getValue()==true){
									  var t = 66;
								  }								  
							  }
							  var readytime = 2;
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
					          tuxingzhuanyijs.hide();
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 tuxingzhuanyijs.hide();
						 }
					}]
		 
	});
	
	//蚀刻
	function shikejsFn(){
		  var platelong = Ext.get("platelong").dom.value.trim();
		  var platewide = Ext.get("platewide").dom.value.trim();
		  var s = platelong*platewide;
		  if(s <= 100*150){
			  var t = 13;
		  }else if(s>100*150 && s<=200*200){
			  var t = 19;
		  }else if(s>200*200 && s<=250*300){
			  var t = 25;
		  }else if(s>250*300 && s<=350*350){
			  var t = 38;
		  }else{
			  var t = 58;
		  }
		  var readytime = 0.5;
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
	
	//涂覆
	var tufujs = new Ext.Window({
  		 id:'tufujs',
  		 title:'<p align="center">涂覆工时计算</p>',
  		 layout:'form',
  		 model:true,//遮挡window后面信息
  		 width:450,
  		 height:160,
  		 plain:true,
  		 buttonAlign:"center",
	 	 bodyBorder:true,
		 resizable:false,
	    closeAction:'hide',
	    items:[new Ext.form.FormPanel({
	    id:"tufujs_panel",
	    bodyStyle : 'padding:30px 0 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:140,
		width:500,
		labelWidth:100,
		layout:'form',
		items : [{
			xtype:"panel", 
            fieldLabel:"涂覆方式",
            layout:'table',
            layoutConfig: {columns:4}, 
            isFormField:true, 
              items: [{
				         xtype:"radio",
				         boxLabel:"普通镀铜",//显示在复选框右边的文字
				         id:'dutong',
				         name:"diandu",
				         checked:true
	            	 },{
				         xtype:"radio",
				         boxLabel:"镀锡铅",
				         id:'duxiqian',
				         name:"diandu"
	                 },{
				         xtype:"radio",
				         boxLabel:"镀金",
				         id:'dujin',
				         name:"diandu"
		             },{
				         xtype:"radio",
				         boxLabel:"加厚镀铜",
				         id:'duhoutong',
				         name:"diandu"
	            	 }]
				 }]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'tufu',
						  handler : function(){
							  var platelong = Ext.get("platelong").dom.value.trim();
							  var platewide = Ext.get("platewide").dom.value.trim();
							  var s = platelong*platewide;							
							  if(s <= 50*100){
								  if(Ext.getCmp("dutong").getValue()==true){									  
									  var t1 = 6;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("duxiqian").getValue()==true){									  
									  var t1 = 6;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("dujin").getValue()==true){									  
									  var t1 = 10;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("duhoutong").getValue()==true){									  
									  var t1 = 8;
									  var t2 = 30;
								  }
							  }else if(s>50*100 && s<=100*150){
								  if(Ext.getCmp("dutong").getValue()==true){									  
									  var t1 = 8;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("duxiqian").getValue()==true){									  
									  var t1 = 6;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("dujin").getValue()==true){									  
									  var t1 = 12;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("duhoutong").getValue()==true){									  
									  var t1 = 8;
									  var t2 = 30;
								  }
							  }else if(s>100*150 && s<=250*300){
								  if(Ext.getCmp("dutong").getValue()==true){									  
									  var t1 = 10;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("duxiqian").getValue()==true){									  
									  var t1 = 10;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("dujin").getValue()==true){									  
									  var t1 = 15;
									  var t2 = 30;
								  }
								  if(Ext.getCmp("duhoutong").getValue()==true){									  
									  var t1 = 10;
									  var t2 = 30;
								  }
							  }else if(s>250*300 && s<=300*350){
								  if(Ext.getCmp("dutong").getValue()==true){									  
									  var t1 = 15;
									  var t2 = 60;
								  }
								  if(Ext.getCmp("duxiqian").getValue()==true){									  
									  var t1 = 15;
									  var t2 = 60;
								  }
								  if(Ext.getCmp("dujin").getValue()==true){									  
									  var t1 = 20;
									  var t2 = 60;
								  }
								  if(Ext.getCmp("duhoutong").getValue()==true){									  
									  var t1 = 15;
									  var t2 = 60;
								  }
							  }else{
								  if(Ext.getCmp("dutong").getValue()==true){									  
									  var t1 = 20;
									  var t2 = 60;
								  }
								  if(Ext.getCmp("duxiqian").getValue()==true){									  
									  var t1 = 20;
									  var t2 = 60;
								  }
								  if(Ext.getCmp("dujin").getValue()==true){									  
									  var t1 = 25;
									  var t2 = 60;
								  }
								  if(Ext.getCmp("duhoutong").getValue()==true){									  
									  var t1 = 20;
									  var t2 = 60;
								  }
							  }
							  var readytime = t2/60;
							  var worktime = t1/60;
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
					          tufujs.hide();
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 tufujs.hide();
						 }
					}]
		 
	});
	
	
	//机械加工
	function jixiejiagongjsFn(){
		hpObj_detail.extraParams.filter="";
		hpObj_detail.extraParams.order="";   	
		hpObj_detail.extraParams.type="QUERY";
    	var obj={params:{start:start,limit:limit}};
    	//console.log(obj);
    	ds_detail.load(obj);
		if(!jixiejiagongjs.isVisible()){
			jixiejiagongjs.show();
		}
			//jixiejiagongjs.show();
	}

	var record_detail = Ext.data.Record.create([    		
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
	                            	                                               
	    	var hpObj_detail = {url:'logicprintedplate.jsp',method: 'POST',extraParams:{}};
	    	var ds_detail = new Ext.data.Store({
	            proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj_detail)),
	            reader: new Ext.data.JsonReader({
	                totalProperty:'totalProperty',
	                root:'filedata'
	            },record_detail)
	        });
	          
	    	var sm_dx = new Ext.grid.CheckboxSelectionModel();
	    	var grid_detail = new Ext.grid.GridPanel({
	    		title: "请选择一条切削参数",
	    		id:'detailtable',
	    		enableColumnMove:false,
	    		columns:[sm_dx,
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
	    		ds:ds_detail,
	    		sm:sm_dx,
	    		width:980,
	    		height:200,
	    		//autoHeight :false,
	            autoScroll :true
	    	});
	    	
	var jixiejiagongjs = new Ext.Window({
			id:'jixiejiagongjs',
			title:"<p align='center'>机械加工工时计算</p>",
			modal:true,                                         
           width:980,
           height:300,
           closeAction:'hide',
           plain: true,
           buttonAlign:"center",
			bodyBorder:true,
			resizable:false,
           items:[new Ext.Panel({
           		layout:'form',
           		frame:true,
           		items:[grid_detail]
           })],
           buttons: [{	  text:'计算',						
				  id:'jixiejiagong',
				  handler : function(){
					  var platelong = Ext.get("platelong").dom.value.trim();
					  var platewide = Ext.get("platewide").dom.value.trim();
					  var platenum = Ext.get("platenum").dom.value.trim();
			
						var record_dx = sm_dx.getSelections();
						if(record_dx.length==0){
							Ext.MessageBox.alert('提示',"请选择一条切削参数!");
							return;
						}else if(record_dx.length!=1){
							Ext.MessageBox.alert('提示',"只能选择一条切削参数!");
							return;												
						}else{
							  var readytime = 2;
							  var vf = record_dx[0].get("vf");
							  var t = 2*platenum*(platelong*1 + platewide*1)/vf;
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
					          jixiejiagongjs.hide();												
						}
				 		  
				  }
			},{
				 text : '取消',						  
				 handler : function() {
					 jixiejiagongjs.hide();
				 }
			}]
		});
	
	
	//层压
	var cengyajs = new Ext.Window({
  		 id:'cengyajs',
  		 title:'<p align="center">层压工时计算</p>',
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
	    id:"cengyajs_panel",
	    bodyStyle : 'padding:30px 10px 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:140,
		width:500,
		labelWidth:100,
		layout:'form',
		items : [{  xtype : 'textfield',
					fieldLabel : '请输入片数',					
					id : 'pianshu',
					width : 150,
					regex:/^(0|[1-9][0-9]*)$/,
	                regexText:'只能由数字组成!'
				}]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'cengya',
						  handler : function(){
							  var pianshu = Ext.get("pianshu").dom.value.trim();
							  if(pianshu <= 0){
								  alert("请输入正确的片数");
							  }else{
								  var readytime = 0.5;
								  var worktime = (5.5*pianshu + 30)/60;
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
						          cengyajs.hide();
							  }
							  
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 cengyajs.hide();
						 }
					}]
		 
	});
	
	
	//孔金属化
	var kongjinshuhuajs = new Ext.Window({
  		 id:'kongjinshuhuajs',
  		 title:'<p align="center">孔金属化工时计算</p>',
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
	    id:"kongjinshuhuajs_panel",
	    bodyStyle : 'padding:5px 0 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:140,
		width:310,
		labelWidth:100,
		layout:'form',
		defaultType : 'radio', 
		items : [{ 
            checked : true, 
            fieldLabel : '选择印制板种类', 
            boxLabel : '双面印制板', 
            id:'shuangmian-yzb',
            name : 'yinzhiban-zl'
        }, { 
            fieldLabel : '', 
            labelSeparator : '', 
            boxLabel : '含环氧材料的多层印制板', 
            id:'yes-yzb',
            name : 'yinzhiban-zl'
        }, { 
            fieldLabel : '', 
            labelSeparator : '', 
            boxLabel : '不含环氧材料的多层印制板', 
            id:'no-yzb',
            name : 'yinzhiban-zl'
        }]
		 })],
		  buttons : [{	  text:'确定',						
						  id:'kongjinshuhua',
						  handler : function(){
							  if(Ext.getCmp("shuangmian-yzb").getValue()==true){
								  kongjinshuhuajs.hide();
								  kongjinshuhua14js.show();
							  }else if(Ext.getCmp("yes-yzb").getValue()==true){
								  kongjinshuhuajs.hide();
								  kongjinshuhua21js.show();
							  }else if(Ext.getCmp("no-yzb").getValue()==true){
								  kongjinshuhuajs.hide();
								  kongjinshuhua14js.show();
							  }
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 kongjinshuhuajs.hide();
						 }
					}]
		 
	});
	
	//孔金属化14
	var kongjinshuhua14js = new Ext.Window({
  		 id:'kongjinshuhua14js',
  		 title:'<p align="center">孔金属化工时计算</p>',
  		 layout:'form',
  		 model:true,//遮挡window后面信息
  		 width:400,
  		 height:270,
  		 plain:true,
  		 buttonAlign:"center",
	 	 bodyBorder:true,
		 resizable:false,
	    closeAction:'hide',
	    items:[new Ext.form.FormPanel({
	    id:"kongjinshuhua14js_panel",
	    bodyStyle : 'padding:5px 0 0',						
		frame:true,
		fileUpload: true,
		height:250,
		width:400,
		labelWidth:10,
		layout:'column',
		items : [{
			layout: 'form',
			columnWidth:.5,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '清洁整孔', 
	            id:'qingjie-14',
	            labelSeparator : '',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '热水洗', 
	            id:'reshuixi-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji1-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '微蚀', 
	            id:'weishi-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji2-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '酸浸', 
	            id:'suanjin-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji3-14',
	            name : 'kongjinshuhua14-gb' 
	        }]
		},{
			layout: 'form',
			columnWidth:.5,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '预浸', 
	            id:'yujin-14',
	            labelSeparator : '',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '活化', 
	            id:'huohua-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji4-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '速化', 
	            id:'suhua-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji5-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '化学沉铜', 
	            id:'chentong-14',
	            name : 'kongjinshuhua14-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '双水洗', 
	            id:'shuangshuixi-14',
	            name : 'kongjinshuhua14-gb' 
	        }]
		}]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'kongjinshuhua14',
						  handler : function(){
							  if(Ext.getCmp("qingjie-14").getValue()==true){
								  var t1 = 5;
							  }else{
								  var t1 = 0;
							  }
							  if(Ext.getCmp("reshuixi-14").getValue()==true){
								  var t2 = 2;
							  }else{
								  var t2 = 0;
							  }
							  if(Ext.getCmp("erji1-14").getValue()==true){
								  var t3 = 2;
							  }else{
								  var t3 = 0;
							  }
							  if(Ext.getCmp("weishi-14").getValue()==true){
								  var t4 = 1;
							  }else{
								  var t4 = 0;
							  }
							  if(Ext.getCmp("erji2-14").getValue()==true){
								  var t5 = 2;
							  }else{
								  var t5 = 0;
							  }
							  if(Ext.getCmp("suanjin-14").getValue()==true){
								  var t6 = 2;
							  }else{
								  var t6 = 0;
							  }
							  if(Ext.getCmp("erji3-14").getValue()==true){
								  var t7 = 2;
							  }else{
								  var t7 = 0;
							  }
							  if(Ext.getCmp("yujin-14").getValue()==true){
								  var t8 = 1;
							  }else{
								  var t8 = 0;
							  }
							  if(Ext.getCmp("huohua-14").getValue()==true){
								  var t9 = 5;
							  }else{
								  var t9 = 0;
							  }
							  if(Ext.getCmp("erji4-14").getValue()==true){
								  var t10 = 2;
							  }else{
								  var t10 = 0;
							  }
							  if(Ext.getCmp("suhua-14").getValue()==true){
								  var t11 = 4;
							  }else{
								  var t11 = 0;
							  }
							  if(Ext.getCmp("erji5-14").getValue()==true){
								  var t12 = 2;
							  }else{
								  var t12 = 0;
							  }
							  if(Ext.getCmp("chentong-14").getValue()==true){
								  var t13 = 15;
							  }else{
								  var t13 = 0;
							  }
							  if(Ext.getCmp("shuangshuixi-14").getValue()==true){
								  var t14 = 2;
							  }else{
								  var t14 = 0;
							  }							  
							  var readytime = 0.5;
							  var t = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11 + t12 + t13 + t14;
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
					          kongjinshuhua14js.hide();
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 kongjinshuhua14js.hide();
						 }
					}]
		 
	});
	
	//孔金属化21
	var kongjinshuhua21js = new Ext.Window({
  		 id:'kongjinshuhua21js',
  		 title:'<p align="center">孔金属化工时计算</p>',
  		 layout:'form',
  		 model:true,//遮挡window后面信息
  		 width:600,
  		 height:270,
  		 plain:true,
  		 buttonAlign:"center",
	 	 bodyBorder:true,
		 resizable:false,
	    closeAction:'hide',
	    items:[new Ext.form.FormPanel({
	    id:"kongjinshuhua21js_panel",
	    bodyStyle : 'padding:5px 0 0',						
		frame:true,
		fileUpload: true,
		height:250,
		width:600,
		labelWidth:10,
		layout:'column',
		items : [{
			layout: 'form',
			columnWidth:.33,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '膨松', 
	            id:'pengsong-21',
	            labelSeparator : '',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji1-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '凹蚀', 
	            id:'aoshi-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '预中和', 
	            id:'yuzhonghe-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji2-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '中和', 
	            id:'zhonghe-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji3-21',
	            name : 'kongjinshuhua21-gb' 
	        }]
		},{
			layout: 'form',
			columnWidth:.33,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '清洁整孔', 
	            id:'qingjie-21',
	            labelSeparator : '',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '热水洗', 
	            id:'reshuixi-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji4-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '微蚀', 
	            id:'weishi-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji5-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '酸浸', 
	            id:'suanjin-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji6-21',
	            name : 'kongjinshuhua21-gb' 
	        }]
		},{
			layout: 'form',
			columnWidth:.33,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '预浸', 
	            id:'yujin-21',
	            labelSeparator : '',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '活化', 
	            id:'huohua-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji7-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '速化', 
	            id:'suhua-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '二级逆流水洗', 
	            id:'erji8-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '化学沉铜', 
	            id:'chentong-21',
	            name : 'kongjinshuhua21-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '双水洗', 
	            id:'shuangshuixi-21',
	            name : 'kongjinshuhua21-gb' 
	        }]
		}]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'kongjinshuhua21',
						  handler : function(){
							  if(Ext.getCmp("pengsong-21").getValue()==true){
								  var t1 = 5;
							  }else{
								  var t1 = 0;
							  }
							  if(Ext.getCmp("erji1-21").getValue()==true){
								  var t2 = 2;
							  }else{
								  var t2 = 0;
							  }
							  if(Ext.getCmp("aoshi-21").getValue()==true){
								  var t3 = 10;
							  }else{
								  var t3 = 0;
							  }
							  if(Ext.getCmp("yuzhonghe-21").getValue()==true){
								  var t4 = 1;
							  }else{
								  var t4 = 0;
							  }
							  if(Ext.getCmp("erji2-21").getValue()==true){
								  var t5 = 2;
							  }else{
								  var t5 = 0;
							  }
							  if(Ext.getCmp("zhonghe-21").getValue()==true){
								  var t6 = 3;
							  }else{
								  var t6 = 0;
							  }
							  if(Ext.getCmp("erji3-21").getValue()==true){
								  var t7 = 2;
							  }else{
								  var t7 = 0;
							  }
							  if(Ext.getCmp("qingjie-21").getValue()==true){
								  var t8 = 6;
							  }else{
								  var t8 = 0;
							  }
							  if(Ext.getCmp("reshuixi-21").getValue()==true){
								  var t9 = 2;
							  }else{
								  var t9 = 0;
							  }
							  if(Ext.getCmp("erji4-21").getValue()==true){
								  var t10 = 2;
							  }else{
								  var t10 = 0;
							  }
							  if(Ext.getCmp("weishi-21").getValue()==true){
								  var t11 = 1;
							  }else{
								  var t11 = 0;
							  }
							  if(Ext.getCmp("erji5-21").getValue()==true){
								  var t12 = 2;
							  }else{
								  var t12 = 0;
							  }
							  if(Ext.getCmp("suanjin-21").getValue()==true){
								  var t13 = 2;
							  }else{
								  var t13 = 0;
							  }
							  if(Ext.getCmp("erji6-21").getValue()==true){
								  var t14 = 2;
							  }else{
								  var t14 = 0;
							  }
							  if(Ext.getCmp("yujin-21").getValue()==true){
								  var t15 = 1;
							  }else{
								  var t15 = 0;
							  }
							  if(Ext.getCmp("huohua-21").getValue()==true){
								  var t16 = 5;
							  }else{
								  var t16 = 0;
							  }
							  if(Ext.getCmp("erji7-21").getValue()==true){
								  var t17 = 2;
							  }else{
								  var t17 = 0;
							  }
							  if(Ext.getCmp("suhua-21").getValue()==true){
								  var t18 = 4;
							  }else{
								  var t18 = 0;
							  }
							  if(Ext.getCmp("erji8-21").getValue()==true){
								  var t19 = 2;
							  }else{
								  var t19 = 0;
							  }
							  if(Ext.getCmp("chentong-21").getValue()==true){
								  var t20 = 15;
							  }else{
								  var t20 = 0;
							  }
							  if(Ext.getCmp("shuangshuixi-21").getValue()==true){
								  var t21 = 2;
							  }else{
								  var t21 = 0;
							  }
							  var readytime = 0.5;
							  var t = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11 + t12 + t13 + t14 + t15 + t16 + t17 + t18 + t19 + t20 + t21;
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
					          kongjinshuhua21js.hide();
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 kongjinshuhua21js.hide();
						 }
					}]
		 
	});
	
	
	//制样板
	var zhiyangbanjs = new Ext.Window({
  		 id:'zhiyangbanjs',
  		 title:'<p align="center">制样板工时计算</p>',
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
	    id:"zhiyangbanjs_panel",
	    bodyStyle : 'padding:30px 0 0',						
		labelAlign:'right',
		frame:true,
		fileUpload: true,
		height:140,
		width:500,
		labelWidth:100,
		layout:'form',
		items : [{
			xtype:"panel", 
            fieldLabel:"贴膜方式",
            layout:'table',
            layoutConfig: {columns:3}, 
            isFormField:true, 
              items: [{
				         xtype:"radio",
				         boxLabel:"单面",//显示在复选框右边的文字
				         id:'zybdanmian',
				         name:"zybtiemo",
				         checked:true
	            	 },{
				         xtype:"radio",
				         boxLabel:"双面",
				         id:'zybshuangmian',
				         name:"zybtiemo"
	                 },{
				         xtype:"radio",
				         boxLabel:"多层",
				         id:'zybduoceng',
				         name:"zybtiemo"
		             }]
				 }]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'zhiyangban',
						  handler : function(){
							  var platelong = Ext.get("platelong").dom.value.trim();
							  var platewide = Ext.get("platewide").dom.value.trim();
							  var s = platelong*platewide;
							  if(s <= 150*150){
								  if(Ext.getCmp("zybdanmian").getValue()==true){
									  var t = 20;
								  }
								  if(Ext.getCmp("zybshuangmian").getValue()==true){
									  var t = 22;
								  }
								  if(Ext.getCmp("zybduoceng").getValue()==true){
									  var t = 24;
								  }
							  }else if(s>150*150 && s<=250*250){
								  if(Ext.getCmp("zybdanmian").getValue()==true){
									  var t = 28.5;
								  }
								  if(Ext.getCmp("zybshuangmian").getValue()==true){
									  var t = 31.5;
								  }
								  if(Ext.getCmp("zybduoceng").getValue()==true){
									  var t = 33.5;
								  }		  
							  }else if(s>250*250 && s<=350*350){
								  if(Ext.getCmp("zybdanmian").getValue()==true){									
									  var t = 41;
								  }
								  if(Ext.getCmp("zybshuangmian").getValue()==true){
									  var t = 44;
								  }
								  if(Ext.getCmp("zybduoceng").getValue()==true){
									  var t = 51;
								  }
							  }else{
								  if(Ext.getCmp("zybdanmian").getValue()==true){
									  var t = 54;
								  }
								  if(Ext.getCmp("zybshuangmian").getValue()==true){
									  var t = 56;
								  }
								  if(Ext.getCmp("zybduoceng").getValue()==true){
									  var t = 66;
								  }								  
							  }
							  var readytime = 0.5;
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
					          zhiyangbanjs.hide();
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 zhiyangbanjs.hide();
						 }
					}]
		 
	});
	
	
	//制字符
	var zhizifujs = new Ext.Window({
  		 id:'zhizifujs',
  		 title:'<p align="center">制字符工时计算</p>',
  		 layout:'form',
  		 model:true,//遮挡window后面信息
  		 width:600,
  		 height:190,
  		 plain:true,
  		 buttonAlign:"center",
	 	 bodyBorder:true,
		 resizable:false,
	    closeAction:'hide',
	    items:[new Ext.form.FormPanel({
	    id:"zhizifujs_panel",
	    bodyStyle : 'padding:5px 0 0',						
		frame:true,
		fileUpload: true,
		height:170,
		width:600,
		labelWidth:10,
		layout:'column',
		items : [{
			layout: 'form',
			columnWidth:.25,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '绷网',
	            id:'bengwang',
	            labelSeparator : '',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '刷胶', 
	            id:'shuajiao',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '贴胶膜', 
	            id:'tiejiaomo',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '烘干', 
	            id:'honggan',
	            name : 'zhizifu-gb' 
	        }]
		},{
			layout: 'form',
			columnWidth:.25,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '曝光', 
	            id:'baoguang',
	            labelSeparator : '',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '固定清洗', 
	            id:'qingxi',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '烘干', 
	            id:'honggan2',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '图形转移', 
	            id:'tuxingzhuanyi',
	            name : 'zhizifu-gb' 
	        }]
		},{
			layout: 'form',
			columnWidth:.25,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '定位图号', 
	            id:'dingweituhao',
	            labelSeparator : '',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '修网', 
	            id:'xiuwang',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '翻网', 
	            id:'fanwang',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '单面板漏印', 
	            id:'danlouyin',
	            name : 'zhizifu-gb' 
	        }]
		},{
			layout: 'form',
			columnWidth:.25,
			defaultType:'checkbox',
			items:[{ 
	            fieldLabel : '', 
	            boxLabel : '双面板漏印', 
	            id:'shuanglouyin',
	            labelSeparator : '',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '面板移印', 
	            id:'yiyin',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '贴保护膜', 
	            id:'baohumo',
	            name : 'zhizifu-gb' 
	        }, { 
	            fieldLabel : '', 
	            labelSeparator : '', 
	            boxLabel : '清洗', 
	            id:'qingxi2',
	            name : 'zhizifu-gb' 
	        }]
		}]
		 })],
		  buttons : [{	  text:'计算',						
						  id:'zhizifu',
						  handler : function(){
							  if(Ext.getCmp("bengwang").getValue()==true){
								  var t1 = 20;
							  }else{
								  var t1 = 0;
							  }
							  if(Ext.getCmp("shuajiao").getValue()==true){
								  var t2 = 6;
							  }else{
								  var t2 = 0;
							  }
							  if(Ext.getCmp("tiejiaomo").getValue()==true){
								  var t3 = 30;
							  }else{
								  var t3 = 0;
							  }
							  if(Ext.getCmp("honggan").getValue()==true){
								  var t4 = 10;
							  }else{
								  var t4 = 0;
							  }
							  if(Ext.getCmp("baoguang").getValue()==true){
								  var t5 = 5;
							  }else{
								  var t5 = 0;
							  }
							  if(Ext.getCmp("qingxi").getValue()==true){
								  var t6 = 5;
							  }else{
								  var t6 = 0;
							  }
							  if(Ext.getCmp("honggan2").getValue()==true){
								  var t7 = 20;
							  }else{
								  var t7 = 0;
							  }
							  if(Ext.getCmp("tuxingzhuanyi").getValue()==true){
								  var t8 = 30;
							  }else{
								  var t8 = 0;
							  }
							  if(Ext.getCmp("dingweituhao").getValue()==true){
								  var t9 = 10;
							  }else{
								  var t9 = 0;
							  }
							  if(Ext.getCmp("xiuwang").getValue()==true){
								  var t10 = 20;
							  }else{
								  var t10 = 0;
							  }
							  if(Ext.getCmp("fanwang").getValue()==true){
								  var t11 = 40;
							  }else{
								  var t11 = 0;
							  }
							  if(Ext.getCmp("danlouyin").getValue()==true){
								  var t12 = 4;
							  }else{
								  var t12 = 0;
							  }
							  if(Ext.getCmp("shuanglouyin").getValue()==true){
								  var t13 = 6;
							  }else{
								  var t13 = 0;
							  }
							  if(Ext.getCmp("yiyin").getValue()==true){
								  var t14 = 3;
							  }else{
								  var t14 = 0;
							  }
							  if(Ext.getCmp("baohumo").getValue()==true){
								  var t15 = 5;
							  }else{
								  var t15 = 0;
							  }
							  if(Ext.getCmp("qingxi2").getValue()==true){
								  var t16 = 2;
							  }else{
								  var t16 = 0;
							  }
							  var readytime = 2;
							  var t = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11 + t12 + t13 + t14 + t15 + t16;
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
					          zhizifujs.hide();
						  }
					},{
						 text : '取消',						  
						 handler : function() {
							 zhizifujs.hide();
						 }
					}]
		 
	});
	
	
	//热风平整
	function refengjsFn(){
		var records = sm.getSelections();
		var row = new Ext.data.Record({ 
  		  processnum:records[0].get("processnum"), 
  		  process:records[0].get("process"), 
  		  worktype:records[0].get("worktype"), 
  		  farm:records[0].get("farm"), 
  		  equipment:records[0].get("equipment"), 
  		  readytime:0.5,
  		  worktime:0.83,
  		  groupnum:''
        });
		var num = ds.indexOf(records[0]);							  
		ds.remove(records[0]);
        ds.insert(num,row);
	}

	//包封
	function baofengjsFn(){
		  var records = sm.getSelections();
		  var row = new Ext.data.Record({ 
    		  processnum:records[0].get("processnum"), 
    		  process:records[0].get("process"), 
    		  worktype:records[0].get("worktype"), 
    		  farm:records[0].get("farm"), 
    		  equipment:records[0].get("equipment"), 
    		  readytime:0.5,
    		  worktime:0.05,
    		  groupnum:''
          });
		  var num = ds.indexOf(records[0]);							  
		  ds.remove(records[0]);
          ds.insert(num,row);
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
		var platethick = Ext.get("platethick").dom.value.trim();
		var platelong = Ext.get("platelong").dom.value.trim();
		var platewide = Ext.get("platewide").dom.value.trim();
		var platenum = Ext.get("platenum").dom.value.trim();
		var platedate = Ext.get("platedate").dom.value.trim();
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
		}else if(platethick==""){
			alert("毛坯厚度不能为空");
		}else if(platelong==""){
			alert("毛坯长度不能为空");
		}else if(platewide==""){
			alert("毛坯宽度不能为空");
		}else if(platenum==""){
			alert("毛坯中零件数不能为空");
		}else if(platedate==""){
			alert("日期不能为空");
		}else if(!numReg.test(platethick)){
			alert("毛坯厚度必须为数字");
		}else if(!numReg.test(platelong)){
			alert("毛坯长度必须为数字");
		}else if(!numReg.test(platewide)){
			alert("毛坯宽度必须为数字");
		}else if(!numReg1.test(platenum)){
			alert("毛坯中零件数必须为数字");
		}else{
			Ext.showProgressDialog("正在提交数据，请稍候...");
			Ext.Ajax.request({
							url:'logicprintedplate.jsp',
							params:{ type : 'ADDALL',
								goodname:goodname,
								partname:partname,
								goodnum:goodnum,
								partnum:partnum,
								materialname:materialname,
								materialcard:materialcard,
								materialnum:materialnum,
								materialstandard:materialstandard,
								platethick:platethick,
								platelong:platelong,
								platewide:platewide,
								platenum:platenum,
								platedate:platedate},
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
					url:'logicprintedplate.jsp',
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
				//ds.remove(record[i]);
				//ds.load();
				}
			}
	}
	
});