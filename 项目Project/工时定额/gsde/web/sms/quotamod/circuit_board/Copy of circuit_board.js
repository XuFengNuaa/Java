Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=100000;//每页显示记录条数
	var width=this.frameElement.offsetWidth;//获得当前窗口宽度
	
	
	//清洗剂
	var pclass =[[0,'醇类'],[1,'有机硅'],[2,'N-甲基-2-2吡咯烷'],[3,'氯化溶剂'],[4,'乙二醇醚'],[5,'其他']]; 
	var pclassstore = new Ext.data.SimpleStore({
		fields:['pclassid','pclass'],
		data:pclass
	});
	var pclasscombo = new Ext.form.ComboBox({
		/*listeners:{
	        select:function(pclasscombo,record,index){ 
	        bclasscombo.clearValue();
	        bclasscombo.store.loadData(bclass[record.data.pclassid]);
	        }},	*/
		id:"pclass",
		fieldLabel:'清洗剂',
		emptyText:'请选择清洗剂',
		name:'pclass',
		id:"pclass",
		store: pclassstore,
		valueField:'pclassid', //传送的值
		displayField:'pclass',
		triggerAction: 'all',
		forceSelection:true,
		lazyRender:true,
		width:150,
		readOnly:true,
		allowBlank:true,
		frame:true,
		mode:'local',
		hiddenName:'pclassID',
		editable:false
	});
	
	
	//清洗工艺
	var bclass =[[0,'半水清洗技术'],[1,'水清洗技术'],[2,'免清洗技术']]; 
	var bclassstore = new Ext.data.SimpleStore({
		fields:['bclassid','bclass'],
		data:bclass
	});
	var bclasscombo = new Ext.form.ComboBox({
		/*listeners:{
	        select:function(pclasscombo,record,index){ 
	        bclasscombo.clearValue();
	        bclasscombo.store.loadData(bclass[record.data.pclassid]);
	        }},	*/
		id:"bclass",
		fieldLabel:'清洗工艺',
		emptyText:'请选择清洗工艺',
		name:'bclass',
		id:"bclass",
		store: bclassstore,
		valueField:'bclassid', //传送的值
		displayField:'bclass',
		triggerAction: 'all',
		forceSelection:true,
		lazyRender:true,
		width:150,
		readOnly:true,
		allowBlank:true,
		frame:true,
		mode:'local',
		hiddenName:'bclassID',
		editable:false
	});
	
	//清洗工艺方法
	var xclass =[[0,'喷洗'],[1,'浸洗'],[2,'汽相清洗'],[3,'手工刷洗'],[4,'超声波清洗']]; 
	var xclassstore = new Ext.data.SimpleStore({
		fields:['xclassid','xclass'],
		data:xclass
	});
	var xclasscombo = new Ext.form.ComboBox({
		/*listeners:{
	        select:function(pclasscombo,record,index){ 
	        bclasscombo.clearValue();
	        bclasscombo.store.loadData(bclass[record.data.pclassid]);
	        }},	*/
		id:"xclass",
		fieldLabel:'清洗工艺方法',
		emptyText:'请选择清洗工艺方法',
		name:'xclass',
		id:"xclass",
		store: xclassstore,
		valueField:'xclassid', //传送的值
		displayField:'xclass',
		triggerAction: 'all',
		forceSelection:true,
		lazyRender:true,
		width:150,
		readOnly:true,
		allowBlank:true,
		frame:true,
		mode:'local',
		hiddenName:'xclassID',
		editable:false
	});
	
	//预烘
	var yclass =[[0,'热固化'],[1,'光固化']]; 
	var yclassstore = new Ext.data.SimpleStore({
		fields:['yclassid','yclass'],
		data:yclass
	});
	var yclasscombo = new Ext.form.ComboBox({
		listeners:{
	        select:function(){ 
	        	var yclassid=$("#yclassID")[0].value; 
	        	var load=Ext.Ajax.request({
	        		url:'logic_circuit_board.jsp',
	        		method:'post',
	        		params:{type:'VIEWONE',numn:yclassid},//传递name参数
	        		success:successFm,
	        		failure:function(){
	        			alert('服务器出现错误请稍后再试！');
	        			}
	        		});
	        }},	
		fieldLabel:'预烘',
		emptyText:'请选择预烘方法',
		name:'yclass',
		id:"yclass",
		store: yclassstore,
		//valueField:'yclassid', //传送的值
		displayField:'yclass',  //UI列表显示的文本
		triggerAction: 'all',
		forceSelection:true,
		lazyRender:true,
		width:150,
		readOnly:true,
		allowBlank:true,
		frame:true,
		mode:'local',
		hiddenName:'yclassID',
		editable:false
	});
	
	function successFm(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);
		var bb=parseInt(objtp.work_time);
		document.getElementById('gongshi').value =bb;
	}
	
	
	
	
	var upborder = new Ext.FormPanel({
		renderTo : 'querydiv',
		border:false,
		layout:'form',
		height:100,
		width:width,
        frame: true,
        items:[{
        	layout:'column',
        	items:[{
            	layout: 'form',
                labelAlign:"right",
                columnWidth:.25,
                items:[{
                	xtype:'textfield',
                    fieldLabel: '产品图号',
                    id: 'materialname',
                    width: 150
                }]
            },{				              
            	layout: 'form',
                labelAlign:"right",
                columnWidth:.25,
                items:[{
                	xtype:'textfield',
                    fieldLabel: '产品名称',
                    id: 'platelong',
                    width: 150
                }]
            }]
        },{
            	layout: 'column',				  
				border:false,
				labelAlign:'right',
				items:[{
					  columnWidth: .25,
					  layout: 'form',
					  border:false,
					  labelAlign:'right',				 
					  items:[pclasscombo]
				  },{
					  columnWidth: .25,
					  layout: 'form',
					  border:false,
					  labelAlign:'right',
					  items:[bclasscombo]
				  },{
					  columnWidth: .25,
					  layout: 'form',
					  border:false,
					  labelAlign:'right',
					  items:[xclasscombo]
				  }]
            },{
            	layout: 'column',				  
				border:false,
				labelAlign:'right',
				items:[{
					  columnWidth: .25,
					  layout: 'form',
					  border:false,
					  labelAlign:'right',				 
					  items:[yclasscombo]
				  },{
		            	layout: 'form',
		                labelAlign:"right",
		                columnWidth:.25,
		                items:[{
		                	xtype:'textfield',
		                    fieldLabel: '工时',
		                    id: 'gongshi',
		                    width: 150
		                }]
		            },{
					  layout: 'form',
		                labelAlign:"right",
		                columnWidth:.1,
		                items:[{
		                	xtype:'button',
		                    text: '计算',
		                    id: 'jisuan',
		                    width: 150,
		                    handler:jisuan
		                }]
				  }]
            }]
        
	});
	
	function jisuan(){
		var pclassid=$("#pclassID")[0].value; 
		var bclassid=$("#bclassID")[0].value; 
		var xclassid=$("#xclassID")[0].value; 
		
		var editnumn=pclassid+bclassid+xclassid;
		//alert(numn);
		//初始化设置
		var load=Ext.Ajax.request({
		url:'logic_circuit_board.jsp',
		method:'post',
		params:{type:'VIEWONE',numn:editnumn},//传递name参数
		success:successFnn,
		failure:function(){
			alert('服务器出现错误请稍后再试！');
			}
		});
		
	}
	
	function successFnn(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);
		var aa=parseInt(document.getElementById('gongshi').value);
		document.getElementById('gongshi').value =parseInt(objtp.work_time)+aa;
		
	}
	
	
	
	
	
	
	var circuir_board= Ext.data.Record.create([
	                     {name:'id'}, 
	                     {name:'name'},
	                     {name:'company_type'},
	                     {name:'work_time'}
	                      ]); 
//定义查询结果页面
	 var hpObj={url:'logic_circuit_board.jsp',method: 'POST',extraParams:{}};
	 var ds= new Ext.data.Store({
	      proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
	      	reader: new Ext.data.JsonReader({
	      		totalProperty:'totalProperty',
	      		root:'filedata'
	      },circuir_board)
	 });
	
	 var sm = new Ext.grid.CheckboxSelectionModel();
	 var grid = new Ext.grid.EditorGridPanel({
				        title: false,
				        id:'filetable',
				        loadMask:true,
				        clicksToEdit: 1,
				        enableColumnMove:false,
				        columns: [sm,
						            {
							            header: "名称",
							            id:"co_name",
							            dataIndex: "name",
							            width:100,
							            sortable: true
							        }, {
							            header: "名称、型号、规格",
							            id: "co_company_type",
							            dataIndex: "company_type",
							            width:140,
							            sortable: true
							        }, {
							        	header: "数量",
							        	id: "co_number",
							            dataIndex: "number",
							            width:60,
							            sortable: true
							        },{
							        	header: "单位时间（分）",
							        	id: "co_work_time",
							            dataIndex: "work_time",
							            width:100,
							            editor: new Ext.form.NumberField(),//可以修改
							            sortable: true
							        },{
							        	header: "备注",
							        	id: "co_remark",
							            dataIndex: "remark",
							            width:100,
							            sortable: true
							        },{
							        	header: "工时（分）",
							        	id: "co_man_hour",
							            dataIndex: "man_hour",
							            width:80,
							            editor: new Ext.form.NumberField(),//可以修改
							            sortable: true
							        }],
						        ds:ds,
						        sm: sm, //多选框checkbox
						        height: 500,
						        stateId:'filetable',
						        autoScroll:true
				    });
	 
	 	var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
	    var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});//按钮之间的竖线 
	    var toolthree=new Ext.Toolbar.Separator({id:'toolthree'});//按钮之间的竖线    
	    var toolfour=new Ext.Toolbar.Separator({id:'toolfour'});//按钮之间的竖线
	    var toolfive=new Ext.Toolbar.Separator({id:'toolfive'});//按钮之间的竖线 
	
	 var downpanel = new Ext.FormPanel({
	    	renderTo: 'selectdiv',
	    	layout:"form",
	    	border:false,
	    	width:width,
			items: [{
				region:'center',
				layout:'fit',
				tbar:new Ext.Toolbar({ 
					items:[toolone,{   
							id:'load',
				            text:"载入", 
				            handler:function(){
								if(!input_win.isVisible())
									input_win.show();
								},
				            iconCls:"add",
				            tooltip:"载入齐套表"
				        },tooltwo,{
				        	id:'del',
				            text:"删除", 
				            handler: deleteRow ,
				            iconCls:"delete",
				            tooltip:"删除一条错误信息"
				        },toolthree,{
				        	id:'del',
				            text:"更新", 
				            handler: deleteRow ,
				            iconCls:"delete",
				            tooltip:"删除一条错误信息"
				        }]
				}),
				items:grid
			}]
	    }); 
	
	//载入齐套表弹出框
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
						id :'input_id',
						inputType : 'file',
						name : 'input_name',
						width : 180								
						}]
			 })],
			  buttons : [{	  text:'导入文件',						
							  id:'inputfile',
							 handler : readtest
						},{
							 text : '取消',						  
							 handler : function() {
							 input_win.hide();
							 }
						}]
			 
	  });
		
		
function readtest(){
	
	 var xianxing=new Array();
	    xianxing[0]="实验数据";
	    for(var i=1;i<30;i++){
	    	xianxing[i]="";
	    }
	    var rem=new Array();
	    rem[0]="备注";
	    var num=new Array();
	    num[0]=0;
	    var pd=true;
	    
	//得到文件路径的值
	var filePath = document.getElementById("input_id").value;
	//创建操作EXCEL应用程序的实例  
    var oXL = new ActiveXObject("Excel.application");
     //打开指定路径的excel文件  
    var oWB = oXL.Workbooks.open(filePath);  
    //操作第一个sheet(从一开始，而非零)  
   
    oWB.worksheets(1).select();
    var sheet = oWB.ActiveSheet;
    for(var j=3;j<36;j++){
    	if(sheet.Cells(j,6).value==null){
    		continue;
    	}
    	for(var n=0;n<30;n++){
    		if(xianxing[n]==""){
    			break;
    		}else if(xianxing[n]==sheet.Cells(j,6).value){
    				//var number=sheet.Cells(j,10).value;
        			
	    			var a=String(sheet.Cells(j,10).value);
					var b=a.replace(/[^A-Za-z0-9\\-]/g,"");
					num[n]=num[n]+parseInt(b);	
            		
        			if(sheet.Cells(j,11).value==null){
        				
        			}else{
        				rem[n]=rem[n]+"*"+sheet.Cells(j,11).value;
        			}
        			
        			pd=false;
        			break;
        		}
    		}
    	
    	while(pd==true){
			for(var nn=0;nn<30;nn++){
				if(xianxing[nn]==""){
					xianxing[nn]=sheet.Cells(j,6).value;
            		
					var aa=String(sheet.Cells(j,10).value);
					var bb=aa.replace(/[^A-Za-z0-9\\-]/g,"");
					num[nn]=parseInt(bb);
		    		
					
					if(sheet.Cells(j,11).value==null){
		    			rem[nn]="";
		    		}else{
		    			rem[nn]=sheet.Cells(j,11).value;
		    		}
					pd=false;
					break;
				}
			}
		}
    	pd=true;
    }
    //去除空的字符串
    for(var xx=0;xx<xianxing.length;xx++){
    	if(xianxing[xx]==""){
    		var leng=xx;
    		break;
    	}
    }
    
    //去除空的字符串
    for(var xx=0;xx<xianxing.length;xx++){
    	if(xianxing[xx]==""){
    		var leng=xx;
    		break;
    	}
    }
    
  
	   for(var x=1;x<leng+1;x++){
		   
		var a=xianxing[x];
		var b=num[x];
		var c=rem[x];
		var d=a.replace(/[^\u4e00-\u9fa5]/gi,"");  
		
		
		var row = new Ext.data.Record({
			name:d,
			company_type:a,
			number:b,
			remark:c
	    });
		
		ds.add(row);
	   }
	   
     
   	//退出操作excel的实例对象  
   	oXL.Application.Quit();  
   	//手动调用垃圾收集器  
   	CollectGarbage();
   	//导入完成后隐藏导入窗口
   	input_win.hide();
   	initializeFn();
	}
    

	function initializeFn(){
		grid.getSelectionModel().selectFirstRow();
		initializeFnn();
	}
	function initializeFnn(){
		var record =sm.getSelections();// 返回值为 Record 类型            	
		if(record.length==0){
	        alert('请先选择要查找的信息!');
	        return;
	    }else if(record.length!=1){
	    	alert('每次只能查看一条信息!');
	        return;
	    }else{
				var editname=record[0].get("name");
				if(editname==""){
					ds.remove(record[0]);
					jisuangongshi();
					return;
				}
				//初始化设置
				var load=Ext.Ajax.request({
				url:'logic_circuit_board.jsp',
				method:'post',
				params:{type:'VIEW',name:editname},//传递name参数
				success:successFn,
				failure:function(){
					alert('服务器出现错误请稍后再试！');
					}
				});
				
	    }
	}
	
	//初始化成功回调函数
	function successFn(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);
		var currentRowNum = grid.store.indexOf(grid.getSelectionModel().getSelected());
		grid.store.getAt(currentRowNum).set("work_time",objtp.work_time);
		grid.getSelectionModel().selectNext();
		initializeFnn();
	}
	
	function jisuangongshi(){
		grid.getSelectionModel().selectAll();
		//var currentRowNum = grid.store.indexOf(grid.getSelectionModel().getSelected());
		var record = sm.getSelections();//获得页面中选中信息
		for(var i=0;i<record.length;i++){
			
			var a1=record[i].get("work_time");
			var b1=record[i].get("number");
			if(a1==null){
				continue;
			}else{
				grid.store.getAt(i).set("man_hour",a1*b1);
			}
		}
		grid.getSelectionModel().clearSelections();
	}
	
	
	//删除函数
	function deleteRow(){
    	var record =sm.getSelections();// 返回值为 Record 类型		            		            	
		// 弹出对话框警告
		if(record.length==0){
			alert("提示:请选择要删除的记录!");
		    return;
		}  
		if(record.length!=0) {
	        var confirm=Ext.MessageBox.show({
					title:'提醒!',
					width:300,
					msg:"将删除所选此信息，确认吗？",
					buttons:{"ok":"确定","no":"取消"},
					icon:Ext.MessageBox.INFO,
					animEl:"test5",
					fn:function(btn){					              
						if (btn=='ok'){
							for(var i=0;i<record.length;i++){
								ds.remove(record[i]);
							}
						}
						else if (btn=='no'){						      
							}									
					}
			     });		  
		       }  
     }
	
	
	
	
});