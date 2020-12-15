Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter='1=1';
	var order="";
	var start=0;
	var limit=100000;//每页显示记录条数
	var width=this.frameElement.offsetWidth;//获得当前窗口宽度
	var height=this.frameElement.offsetHeight;//获得当前窗口宽度
	var pclassjs='1=1';
	var bclassjs='1=1';
	//整机
	var pclass =[[0,'热轧'],[1,'收卷'],[2,'分切'],[3,'其他']]; 
	var pclassstore = new Ext.data.SimpleStore({fields:['pclassid','pclass'],data:pclass
	});
	var pclasscombo = new Ext.form.ComboBox({
	listeners:{
					        select:function(pclasscombo,record,index){ 
					        bclasscombo.clearValue();
					        bclasscombo.store.loadData(bclass[record.data.pclassid]);
					        }},	
		fieldLabel:'整机',
		emptyText:'请选择整机',
		name:'pclass',
		id:"pclass",
		store: pclassstore,
		//valueField:'pclassid', //传送的值
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
	
	
	//部件
	 var  bclass=new Array();
              bclass[0]=[[0,'上轧辊总成'],[1,'润滑系统'],[2,'冷却轧总成'],[3,'液压系统'],[4,'传动系统'],[5,'机架总成'],[6,'下轧辊总成'],[7,'限位调节装置'],[8,'轴交叉调节装置'],[9,'升降座'],[10,'电、气控制系统']]; 
             bclass[1]=[[11,'机架总成'],[12,'挡块机构'],[13,'下支撑管总成'],[14,'主传动系统'],[15,'张紧轮总成'],[16,'卷取总成'],[17,'气涨轴总成'],[18,'张力机构'],[19,'气缸连接下芯轴'],[20,'纵向分切'],[21,'横向分切'],[22,'刀架'],[23,'压紧总成'],[24,'吸风装置'],[25,'电、气控制系统']];  
              bclass[2]=[[26,'左护罩'],[27,'卷取装置'],[28,'张紧轮总成'],[29,'24Z、48Z齿形轮结合件'],[30,'机架'],[31,'右护罩'],[32,'压辊机构'],[33,'制动装置'],[34,'张力装置'],[35,'放料架'],[36,'轴承座总成'],[37,'分切装置'],[38,'退料车'],[38,'电、气控制系统']]; 
              bclass[3]=[[37,'5'],[38,'6'],[39,'7'],[40,'8']];
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
		fieldLabel:'部件',
		emptyText:'请选择部件名称',
		name:'bclass',
		id:"bclass",
		store: bclassstore,
		//valueField:'bclassid', //传送的值
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
	function successFm(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);
		var bb=parseInt(objtp.work_time);//写入输出框
		
	}
	 Ext.apply(Ext.form.VTypes, {  
        dateRange: function(val, field){  
            if(field.dateRange){  
                var beginId = field.dateRange.begin;  
                this.beginField = Ext.getCmp(beginId);  
                var endId = field.dateRange.end;  
                this.endField = Ext.getCmp(endId);  
                var beginDate = this.beginField.getValue();  
                var endDate = this.endField.getValue();  
            }  
            if(beginDate <= endDate){  
                return true;  
            }else{ 
                return false;  
            }  
        },  
        //验证失败信息
        dateRangeText: '开始日期不能大于结束日期'
          
         
    });
    
var dt = new Date();
 var tempdt = dt.format('Y-m-d');
 var dt1 = new Date('4/1/2018 03:05:01 AM GMT-0800');
 var tempdt1 = dt1.format('Y-m-d');
    
    var datepanel = new Ext.Panel({
              id:'daterange',
			  Width:800, 
			  height:60,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  labelAlign:"central",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
				  width: 240,
				  layout: 'form',
				  border:false,
				  labelWidth:100,
				  labelAlign:'right',
				  items:[{
            xtype: 'datefield',  
            id: 'beginDate',  
            format: 'Y-m-d',  
            width: 110,  
            allowBlank: false,  
            readOnly: true,  
            value: tempdt1,  
            fieldLabel: '开始日期',  
            //用于Vtype类型dateRange  
            dateRange: {begin: 'beginDate', end: 'endDate' },
            vtype: 'dateRange'  
                          }]
				
			  },{
				  width: 220,
				  layout: 'form',
				  border:false,
				  labelWidth:60,
				  labelAlign:'right',
				  items:[{
            xtype: 'datefield',  
            id: 'endDate',  
            format: 'Y-m-d',  
            width: 110,  
            allowBlank: false,  
           readOnly: true,  
            value: tempdt,  
            fieldLabel: '结束日期',  
            //用于Vtype类型dateRange  
            dateRange: {begin: 'beginDate', end: 'endDate' },
            vtype: 'dateRange'  
                        }]
			      }]
     
}) ; 
	
	
	
	var upborder = new Ext.FormPanel({
		renderTo : 'querydiv',//渲染到querydiv容器中
		border:false,
		layout:'form',
		height:200,
		width:width,
        frame: true,
        items:[{
        	xtype:'fieldset',
        	title:'产品特征选择',
        	collapsible:false,
        	autoHeight:true,
        	layout:'form',
        	items:[{
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
		            	layout: 'form',
		                labelAlign:"right",
		                columnWidth:.4,
		                items:[datepanel
		                ]
		            },{
					  layout: 'form',
		                labelAlign:"right",
		                columnWidth:.1,
		                items:[{
		                	xtype:'button',
		                    text: '筛选',
		                    id: 'jisuan',
		                    width: 50,
		                    handler:jisuan
		                }]
				  }]
            }]
        },{
            	xtype:'fieldset',
                title: '资讯模块',
                collapsible: false,
                autoHeight:true,
            	layout: 'column',				  
				//border:false,
				labelAlign:'right',
				items:[{
					layout: 'form',
	                labelAlign:"right",
	                columnWidth:.2,
	                items:[{
	                	xtype:'textfield',
	                    fieldLabel: '答疑速度',
	                    id: 'sd',
	                    width: 100
	                }]
				},{
					layout: 'form',
	                labelAlign:"right",
	                columnWidth:.2,
	                items:[{
	                	xtype:'textfield',
	                    fieldLabel: '答疑效果',
	                    id: 'xg',
	                    width: 100
	                }]
				},{
					layout: 'form',
	                labelAlign:"right",
	                columnWidth:.2,
	                items:[{
	                	xtype:'textfield',
	                    fieldLabel: '服务态度',
	                    id: 'td',
	                    width: 100
	                }]
				},{
					layout: 'form',
	                labelAlign:"right",
	                columnWidth:.2,
	                items:[{
	                	xtype:'textfield',
	                    fieldLabel: '产品质量',
	                    id: 'zl',
	                    width: 100
	                }]
				},{
					layout: 'form',
	                labelAlign:"right",
	                columnWidth:.1,
	                items:[{
	                	xtype:'button',
	                    text: '分数',
	                    id: 'tongji',
	                    width: 150,
	                    handler:tongji
	                }]
				},{
					layout: 'form',
	                labelAlign:"right",
	                columnWidth:.1,
	                items:[{
	                	xtype:'button',
	                    text: '评价',
	                    id: 'remark',
	                    width: 150,
	                    handler:remark
	                }]
				}]
            }]
        
	});
	function detailsFn2(obj){
         var objtp = obj;
		nowid = objtp.id;
         Ext.getCmp("remark").setValue(objtp.remarkjs);
    };
	function successFn2(result,request){
     var objtp = Ext.util.JSON.decode(result.responseText);
		var objt =	Ext.util.JSON.encode(objtp.filedata);
		var obj = objt.slice(1,objt.length-1);
		var pos = obj.indexOf('{');
		var i = 0;
		while (pos!=-1){
		    pos = obj.indexOf('{',pos+1);
		    i++;
		}
		if (i>1) {//物资编码重复
		   var hq = (Ext.util.JSON.decode(obj)).hq;
		   hpCombo.reset();
		   Ext.MessageBox.alert('提醒!物资编号不唯一',"你选择一种物资编号不唯一的工具，请您点击确定选择工具是否为高质！");
		}else{//物资编码不重复

		var obj = Ext.util.JSON.decode(obj);
        detailsFn2(obj);}};
	function chexExistnew(){
    var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
			var entry_nums="";
    	var length = record.length;
		//Ext.showProgressDialog();
		for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
			if(i!=0){
			entry_nums+=",";
				};
			entry_nums+="'"+record[i].json.entry_num+"'";
		}
		    //var mnum = record[0].get("mnum");
	    var filter = entry_nums;
	    Ext.Ajax.request({
							 	url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
							 	method:'post',
						 		params:{type:'QUERY4',id:filter},//传递物资编号以便获取详细信息
								success:successFn2,
						 		failure:function(){
									Ext.MessageBox.alert('警告','服务器出现错误请稍后再试！');
					 			}
						  	});		
						
					
			}
    } 
	var remark=new Ext.form.TextArea({fieldLabel: '用户评价',id: 'remark', readOnly:false,width:364,height:180});
	function remark(){
	var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
		    //var mnum = record[0].get("mnum");
		   
		    chexExistnew();
	    	var imgviewpanelck1=new Ext.FormPanel({
				id:'imgviewck',
			    labelWidth:75,
			    width:500,
			    height:250,			      				       
			    frame:true,
			    bodyStyle:'padding:10px 10px 0',
			    items: [ {xtype:"panel",
        		    layout:"column",
					items:[{columnWidth:1,
					        //rowspan:2,
						    layout:"form",
						    //hidden: true,
		                	items:[remark]//零件编码
				           }]
				}]
			   	});
			   	var imgwindowck1 = new Ext.Window({
	    			id:'imgwindowck1',
	    			modal:true,
	                width:500,
	                height:280,
	                closable:true,
	             //   closeAction:'hide',
	                plain: true,
	                title:"<p align='center'>用户意见及建议</p>",    
					bodyBorder:true,
	                items:imgviewpanelck1,
	                buttonAlign:"center",
	                 buttons: [{
						text: '关闭',
						handler: function(){
						 Ext.getCmp("imgwindowck1").hide();
						
						}
		            }
		       	]
			    });
			     imgwindowck1.show();
			   	}
          
	};
	function tongji(){
		var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
		var entry_nums="";
    	var length = record.length;
		for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
			if(i!=0){
			entry_nums+=",";
				};
			entry_nums+="'"+record[i].json.entry_num+"'";
		}}
	 Ext.Ajax.request({
							 	url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
							 	method:'post',
						 		params:{type:'QUERYSCORE',id:entry_nums},//传递物资编号以便获取详细信息
								success:successFn1,
						 		failure:function(){
									Ext.MessageBox.alert('警告','服务器出现错误请稍后再试！');
					 			}
						  	});						
    }
	function successFn1(result,request){
		var objtp = Ext.util.JSON.decode(result.responseText);
		var objt =	Ext.util.JSON.encode(objtp.filedata);
		var obj = objt.slice(1,objt.length-1);
		var pos = obj.indexOf('{');
		var i = 0;
		//判断物资编码是否重复
		while (pos!=-1){
		    pos = obj.indexOf('{',pos+1);
		    i++;
		}
		if (i>1) {//物资编码重复
		   var hq = (Ext.util.JSON.decode(obj)).hq;
		  // hpCombo.reset();
		  // Ext.getCmp('div').show();
		   Ext.MessageBox.alert('提醒!物资编号不唯一',"你选择一种物资编号不唯一的工具，请您点击确定选择工具是否为高质！");
		   //Ext.getCmp('details').reset();
		}else{//物资编码不重复						
		var obj = Ext.util.JSON.decode(obj);
        detailsFn(obj);
	}}
	function detailsFn(obj){
         var objtp = obj;
	 	Ext.getCmp("sd").setValue(objtp.sta);
		Ext.getCmp("xg").setValue(objtp.sta1);
		Ext.getCmp("td").setValue(objtp.sta2);
		Ext.getCmp("zl").setValue(objtp.sta3);
    }
	function jisuan(){
		Ext.showProgressDialog();
	  var pclassjs=Ext.getCmp("pclass").getValue();
	  if(pclassjs==""){pclassjs='1=1'}else{pclassjs="pclass='"+pclassjs+"'"};
	  var bclassjs=Ext.getCmp("bclass").getValue();
	  if(bclassjs==""){bclassjs='1=1'}else{bclassjs="bclass='"+bclassjs+"'"};
   	  //var query_type=Ext.getCmp("type_id").getValue(); 
		hpObj.extraParams.filter=getFilter();   	
    	hpObj.extraParams.type="QUERYCT1";
    	hpObj.extraParams.pclassjs=pclassjs;
    	hpObj.extraParams.bclassjs=bclassjs;
   		if(ds.load()){
   			Ext.hideProgressDialog();
   		}  
	}
	  function getFilter(){
	   var i;  
   	  filter="1=1";   	
	  var string = [];   
	  var beginDate = Ext.getCmp("beginDate").getValue();
	  var endDate = Ext.getCmp("endDate").getValue();
	  var beginDate = beginDate.format('Y-m-d');
	  //console.log(beginDate);
	  var endDate = endDate.format('Y-m-d');
	    filter = "in_time between to_date('"+beginDate+"','yyyy-mm-dd') and to_date('"+endDate+"','yyyy-mm-dd')";
	 
	 this.lastQueryFilter=filter;
	 return filter;
	}
	function successFnn(result,request){
		var objtp=Ext.util.JSON.decode(result.responseText);	
	}
	var circuir_board= Ext.data.Record.create([
	        {name:'type_id'},
    		{name:'in_time'},//newstatus,indi_num,location,keeper,remark,startdate,valid,cycle,entry_num,indi_id,status
			{name:'newstatus'},
			{name:'in_count'},
			{name:'entry_num'},
			{name:'mnum'},
			{name:'order_num'},
			{name:'name'},
			{name:'company'},
			{name:'pclass'},
			{name:'bclass'},
			{name:'hq'},
			{name:'location'},
			{name:'nc_count_in'},
			{name:'uc_count_in'},
			{name:'status'},
			{name:'remark'},
			{name:'remarkjs'},
			{name:'score'},
			{name:'leader_num'},
			{name:'city'},
			{name:'user_num'},
			{name:'bill_num'}
	                      ]); 
//定义查询结果页面
	
	 var hpObj={url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',method: 'POST',extraParams:{type:'QUERYCT1',params:{start:start,limit:limit},filter:filter,pclassjs:pclassjs,bclassjs:bclassjs}};
	 var ds= new Ext.data.Store({
	      proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
	      	reader: new Ext.data.JsonReader({
	      		totalProperty:'totalProperty',
	      		root:'filedata'
	      },circuir_board),
	      autoload:true
	 });
	var obj={params:{start:start,limit:limit}};   
    ds.load(obj);
    var hqObjAni={url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',method:'POST',extraParams:{}};
	var store_cut=new Ext.data.Store({
	    proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hqObjAni)),
	    pruneModifiedRecords:true,
		reader:new Ext.data.JsonReader({            
	        root:'root'
	    },circuir_board)
	});
	 var sm = new Ext.grid.CheckboxSelectionModel();
	 var grid = new Ext.grid.EditorGridPanel({
				        title: false,
				        id:'filetable',
				        loadMask:true,
				        enableColumnMove:false,
				        columns: [sm,
						            {
			            header: "专家答疑",
			            dataIndex: "status",
			            width:80,
			            sortable: true
			        },/*,{
			            header: "零件编号",
			            dataIndex: "mnum",
			            width:80,
			            //hidden:true,
			            sortable: true
			        },{
			            header: "零件名称",
			            dataIndex: "name",
			            width:80,
			            sortable: true
			        }, */{
			            header: "部件",
			            dataIndex: "pclass",
			            width:80,
			            sortable: true
			        }, {
			            header: "整机",
			            dataIndex: "bclass",
			            width:80,
			            sortable: true
			        },{
			            header: "专家",
			            dataIndex: "leader_num",
			            width:80,
			            sortable: true
			        },{
			            header: "分数",
			            dataIndex: "score",
			            width:80,
			            sortable: true
			        },{
			            header: "所在城市",
			            dataIndex: "city",
			            width:100,
			            sortable: true
		             },{
			            header: "公司名称",
			            dataIndex: "company",
			            width:110,
			            sortable: true
			        },  {
			            header: "联系人",
			            dataIndex: "user_num",
			            width:70,
			            sortable: true
			        }, {
			            header: "联系电话",
			            dataIndex: "in_count",
			            width:70,
			            sortable: true
			        }, {
			            header: "订单号",
			            dataIndex: "bill_num",
			            width:100,
			            sortable: true
		             }, {
			            header: "申请时间",
			            dataIndex: "in_time",
			            width:80,
			            renderer:renderFn,
			            sortable: true
			        }],
						        ds:ds,
						        sm: sm, //多选框checkbox
						        height: height-160,
						        stateId:'filetable',
						        autoScroll:true,
						        renderTo: document.body
				    });
	  function renderFn(value){
    	return value.substring(0,10);
     }
	 	var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
	    var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});//按钮之间的竖线 
	    var toolthree=new Ext.Toolbar.Separator({id:'toolthree'});//按钮之间的竖线    
	    var toolfour=new Ext.Toolbar.Separator({id:'toolfour'});//按钮之间的竖线
	    var toolfive=new Ext.Toolbar.Separator({id:'toolfive'});//按钮之间的竖线 
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
							// handler : 			
							  handler : function() {
							 	readtest();	
							 }
						},{
							 text : '取消',						  
							 handler : function() {
                             input_win.hide();
							 }
						}]			 
	  });
	 var downpanel = new Ext.FormPanel({
	    	renderTo: 'selectdiv',
	    	layout:"form",
	    	border:false,
	    	width:width,
			items: [{
				region:'center',
				layout:'fit',
				tbar:new Ext.Toolbar({ 
					items:[tooltwo,{
				        	id:'del',
				            text:"删除", 
				            handler: deleteRow ,
				            iconCls:"delete",
				            tooltip:"删除一条信息"
				        }]
				}),
				items:grid
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
    var numbersheet=oWB.Worksheets.Count;//计算表单有多少行
    //操作第一个sheet(从一开始，而非零)  
   for(var w=1;w<numbersheet+1;w++){
    oWB.worksheets(w).select();
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
		//grid.store.getAt(currentRowNum).set("time_one",objtp.time_one);
	//	grid.store.getAt(currentRowNum).set("time_two",objtp.time_two);
		grid.getSelectionModel().selectNext();
		initializeFnn();
	}
	
	function jisuangongshi(){
		grid.getSelectionModel().selectAll();
		//var currentRowNum = grid.store.indexOf(grid.getSelectionModel().getSelected());
		var record = sm.getSelections();//获得页面中选中信息
		
		var confirm=Ext.MessageBox.show({
			title:'提醒!',
			width:300,
			msg:"请选择所需定额，二厂？微电子",
			buttons:{"ok":"二厂","no":"微电子"},
			icon:Ext.MessageBox.INFO,
			animEl:"test5",
			fn:function(btn){					              
				if (btn=='ok'){
					for(var i=0;i<record.length;i++){
						var a1=record[i].get("time_one");
						var c1=record[i].get("number");
						if(a1==null&&b1==null){
							continue;
						}else{
							grid.store.getAt(i).set("man_hour",a1*c1);
						}
					}
				}
				else if (btn=='no'){
					for(var i=0;i<record.length;i++){
						var b1=record[i].get("time_two");
						var c1=record[i].get("number");
						if(a1==null&&b1==null){
							continue;
						}else{
							grid.store.getAt(i).set("man_hour",b1*c1);
						}
					}
				
				}									
			}
	     });
		grid.getSelectionModel().clearSelections();
	}
	
	
	
	//删除函数
	function deleteRow(){
    	var record =sm.getSelections();// 返回值为 Record 类型		    
    	alert(record);
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