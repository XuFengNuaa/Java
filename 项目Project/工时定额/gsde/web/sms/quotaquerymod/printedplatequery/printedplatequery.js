Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=100000;//每页显示记录条数
	var result = "";
	
	var record_all= Ext.data.Record.create([  
      		{name:'goodname'},
      		{name:'partname'},
      		{name:'goodnum'},
    		{name:'partnum'},
			{name:'materialname'},
			{name:'materialcard'},
			{name:'materialnum'},
			{name:'materialstandard'},
			{name:'platethick'},
			{name:'platelong'},
			{name:'platewide'},
			{name:'platenum'},
			{name:'platedate'}
						    ]); 
    
    //定义查询结果页面
	var hpObj={url:'logicprintedplatequery.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_all)
    });
	
    var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
        title: false,
        id:'filetable',
        enableColumnMove:false,
        columns: [sm,
        	{header: "产品名称",dataIndex: "goodname",width : 90,align:'center',sortable: true},
        	{header: "零件名称",dataIndex: "partname",width : 90,align:'center',sortable: true},
        	{header: "产品图号",dataIndex: "goodnum",width : 90,align:'center',sortable: true},
        	{header: "零件图号",dataIndex: "partnum",width : 90,align:'center',sortable: true},
        	{header: "材料名称",dataIndex: "materialname",width : 80,align:'center',sortable: true},
        	{header: "材料牌号",dataIndex: "materialcard",width : 80,align:'center',hidden:true,sortable: true},
        	{header: "材料编码",dataIndex: "materialnum",width : 80,align:'center',hidden:true,sortable: true},
        	{header: "材料标准号",dataIndex: "materialstandard",width : 80,hidden:true,align:'center',sortable: true},
        	{header: "毛坯厚度",width : 80,align:'center',dataIndex: "platethick",sortable: true},
        	{header: "毛坯长度",dataIndex: "platelong",width : 80,align:'center',sortable: true}, 
        	{header: "毛坯宽度",dataIndex: "platewide",width : 80,align:'center',sortable: true} ,
        	{header: "毛坯中零件数",dataIndex: "platenum",width : 80,align:'center',hidden:true,sortable: true},
        	{header: "日期",dataIndex: "platedate",width : 100,align:'center',sortable: true}],
        ds:ds,
        sm: sm, //多选框checkbox
        height: 400,
        autoScroll:true
    });
    
    //定义查询框之后的长输入表单
    var query_text = new Ext.form.TextField({
		  id:'query_text',
		  width:260,
		  fieldLabel:'',
		  labelSeparator:''
		  });
    
	//定义查询类型和排序显示combox    
  var  orderdata=[['goodnum','产品图号']]; 	
   var typestore=new Ext.data.SimpleStore({fields:['type','type_name'],data:orderdata});
  var  orderstore = new Ext.data.SimpleStore({fields:['orderid','ordername'],data:orderdata});
  
  var typecombo=new Ext.form.ComboBox({
				fieldLabel:'查询类型',
				id:"type_id",
				value:'产品图号',
				displayField:'type_name',	
				valueField:'type',			
				store:typestore,
				triggerAction: 'all',
				lazyRender:true,
				width:110,
				typeAhead: true,
				selectOnFocus:true,			
				mode:'local',
				editable:false
				});    				
	
	 var querypanel = new Ext.Panel({
			  id: 'querypanel',
			  width:800,
			  height:30,
			  hideBorders:true,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
				  width:220,
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  items:[typecombo]
			  },{
				  width:310,
				  id:'hide_query_text',
				  layout: 'form',
				  border:false,
				  labelWidth:40,
				  labelAlign:'right',				
				  items:[query_text ]
			  },
			  
			  {
				  width:48,
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  labelWidth:3,
				  items:[{
					  xtype:'button',					  
					  text:'查询',
					  handler:customQueryFn
				  }]
			  }
			  
			  ]
		  });
		  

		  //排序显示
		  var ordercombo=new Ext.form.ComboBox({				
				id:"order",
				displayField:'ordername',
				valueField:'orderid',					
				store: orderstore,
				triggerAction: 'all',
				width:110,
				value:'产品图号',
				lazyRender:true,
				fieldLabel:'排序显示',
				typeAhead: true,
				allowBlank:false,
				forceSelection:true,
				mode:'local',
				editable:false	
				});	
	  
		  var orderradio1 = new Ext.form.Radio({	                     
              labelSeparator:"",
              name:'ordertype',
              id: 'up',
              inputValue:'ASC',
              boxLabel:'升序排列',
              checked:true
		  });
		  
		  var orderradio2 = new Ext.form.Radio({	                     
              labelSeparator:"",
              name:'ordertype',
              id: 'down',
              inputValue:'DESC',
              boxLabel:'降序排列'
		  });	  
		  
		   var mode_radio1 = new Ext.form.Radio({	                     
              fieldLabel:'检索模式',
			  labelSeparator:":",
              name:'modetype',            
              boxLabel:'前方一致',
              inputValue:'front',
              checked:true
		  }); 
		  var mode_radio2 = new Ext.form.Radio({	                     
              labelSeparator:"",
              name:'modetype',  
              inputValue:'exact',          
              boxLabel:'完全匹配'
		  });
		  var mode_radio3 = new Ext.form.Radio({	                     
              labelSeparator:"",
              inputValue:'fuzzy',   
              name:'modetype',             
              boxLabel:'模糊匹配'
		  });
		  
		  var modepanel = new Ext.Panel({
			  id:'orderpanel',
			  Width:800, 
			  height:30,
			  hideBorders:true,
			  hideLabels:false,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
				  width:215,
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  labelWidth:100,
				  items:[mode_radio1]
			  },{
				  width: 115,
				  layout: 'form',
				  labelWidth:3,
				  border:false,
				  labelAlign:'right',
				  items:[mode_radio2]
			  },{
				  width: 150,
				  layout: 'form',
				  labelWidth:3,
				  border:false,
				  labelAlign:'right',
				  items:[mode_radio3]
			  }]
		  });
		  
		  var orderpanel = new Ext.Panel({
			  id:'orderpanel',
			  Width:800, 
			  height:30,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
				  width: 230,
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  items:[ordercombo]
			  },{
				  width: 100,
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  labelWidth:3,
				  items:[orderradio1]
			  },{
				  width: 100,
				  layout: 'form',
				  labelWidth:3,
				  border:false,
				  labelAlign:'right',
				  items:[orderradio2]
			  }]
		  });
		  
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
 var dt1 = new Date('1/11/2007 03:05:01 AM GMT-0800');
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

var panel = new Ext.Panel({
	    id:'panel',
	    height:125,
	    Width:800, 
	    region:"north",
	    hideBorders:true,
	    hideLabels:false,
        //renderTo:'querydiv',
        border:false,
        labelAlign:"right",
         html:"<div id='show_div' style='position:absolute;left:520px;top:65px;'>"+
				  "<span id='show_span' style='font-size:13px;color:#0080ff'>&nbsp;</span></div>",	
        bodyStyle:'padding:6px 6px 6px 6px',
        items:[{ layout:'column',
             items:[
                  
                  {   
	                width:800,
	               // labelWidth:50,
	                layout: 'form',
	                border:false,
	                labelAlign:"right",
	                items: [querypanel]	         
	              },
                 {   
	                width:800,
	                labelWidth:50,
	                layout: 'form',
	                border:false,
	                labelAlign:"right",
	                items: [modepanel]	         
			      },{   
	                width:800,
	                labelWidth:50,
	                layout: 'form',
	                border:false,
	                labelAlign:"right",
	                items: [orderpanel]	         
		          }
		          ,{   
	                width:800,
	                labelWidth:50,
	                layout: 'form',
	                border:false,
	                labelAlign:"right",
	                items: [datepanel]	         
		          }]
		}]
		        
	});	
	
    //组装页面
	new Ext.Viewport({
		layout:"border",
		autoHeight:false,
		items:[panel,
				{
				region:'center',
				layout:'fit',
				title:'查询结果',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
					items:[{ 
							id:'detail',
				            text:"明细", 
				            handler: viewFn ,
				            iconCls:"detail",
				            tooltip:"查看详细定额信息"
		        		}]
	        }),
				items:grid
		}]
	});
       
        //焦点在查询文本框中，使其能够使用特殊符号窗口
	Ext.get("query_text").focus();
	Ext.get("query_text").on("focus", function(){keyBoardInput = Ext.getDom("querydivquerytext");});					
    keyBoard();				
    keyBoard.hide();
    
    //getFilter()
    function getFilter(){
	   var i;  
   	  filter="1=1";   	
	  var string = [];   
	  var indexmode = document.getElementsByName("modetype");
	  //var resultquery=document.getElementsByName("query_result");
	  var beginDate = Ext.getCmp("beginDate").getValue();
	  var endDate = Ext.getCmp("endDate").getValue();
	  var beginDate = beginDate.format('Y-m-d');
	  //console.log(beginDate);
	  var endDate = endDate.format('Y-m-d');
	  string[1] = typecombo.getValue();
	  //console.log(string[1]);
	   if(string[1]=="产品图号"){
		   string[1]="good_num";
	   }
	  string[2] = document.getElementById("query_text").value; 
	  for(i=0; i<indexmode.length; i++){
			if(indexmode[i].checked == true)
				string[0] = indexmode[i].value;
	 	}
		
	if(string[2]==''){
	    filter = "plate_date between to_date('"+beginDate+"','yyyy-mm-dd') and to_date('"+endDate+"','yyyy-mm-dd')";
	    }
	else{		
			 
			if(string[0] == 'front'){
				    filter += " and " + string[1] + " like '" + string[2] + "%' "+" and plate_date between to_date('"+beginDate+"','yyyy-mm-dd') and to_date('"+endDate+"','yyyy-mm-dd')";
				  }else if(string[0] == 'exact'){
				    filter += " and " + string[1] + " = '" + string[2] + "' "+" and plate_date between to_date('"+beginDate+"','yyyy-mm-dd') and to_date('"+endDate+"','yyyy-mm-dd')";
				  }else if(string[0] == 'fuzzy'){
				    filter += " and " + string[1] + " like '%" + string[2] + "%' "+" and plate_date between to_date('"+beginDate+"','yyyy-mm-dd') and to_date('"+endDate+"','yyyy-mm-dd')";
				   }
			 
		 }
	 this.lastQueryFilter=filter;
	 return filter;
	}
    //good_name,part_name,good_num,part_num,plate_date
	//getOrder()
	function getOrder(){	
	    var orderValue="";
		var order = document.getElementsByName("ordertype");		
		 orderValue = ordercombo.getValue();
		   if(orderValue=="产品图号"){
			   orderValue="good_num";
		   }
		for(var i=0; i<order.length; i++){
			if(order[i].checked == true){
				orderValue +=" "+order[i].value;
				break;
			}
		}
		//console.log(orderValue);
		return orderValue;
	}
	
	//显示已查询关键字
	 function getQueryText(){	 		
		var string = [];
		string[0] = document.getElementById("query_text").value;
		if(string[0] == ""){
			string[0] = "所有";
		}		
		if(typecombo.getRawValue()!="请选择查询类型"){
		    string[1] = typecombo.getRawValue();
		}
		var spantext = "";			
	  var beginDate = Ext.getCmp("beginDate").getValue();
	  var endDate = Ext.getCmp("endDate").getValue();
	  var beginDate = beginDate.format('Y-m-d');
	  var endDate = endDate.format('Y-m-d');
		spantext += string[1] + "：" + string[0] + "&nbsp;&nbsp;&nbsp;"+"<br>"+"时间段："+beginDate+"至"+endDate;
		
		
		this.lastQueryText = spantext;
		document.getElementById("show_span").innerHTML = "已查询的关键词：<p>" + spantext;
		return spantext;
	}
	
		//查询函数 
	function customQueryFn(){   
   	 // Ext.showProgressDialog();
   	 // var query_type=Ext.getCmp("type_id").getValue(); 
	//	hpObj.extraParams.filter=getFilter();
    //	hpObj.extraParams.order=getOrder();    	
    //	hpObj.extraParams.type="QUERY";
    	//var obj={params:{start:start,limit:limit}};
   		//if(ds.load()){
   		//	Ext.hideProgressDialog();
   		//}  
   		//getQueryText(); 
   		//result = getQueryText();
   		}
	
	
	//详细定额信息窗口
    var formpanel = new Ext.FormPanel({
       id:'formpanel',
       layout:'column',
       labelAlign:"right",
       items: [{					              
		           layout: 'form',
		           labelAlign:"right",
		           columnWidth:.2,
		           labelWidth:60,
		           items:[{
		           	xtype:'textfield',
		               fieldLabel: '产品名称',
		               id: 'goodnamedetail'
		               //width: 100
		           		}]
       },{
		       		layout: 'form',
		            labelAlign:"right",
		            labelWidth:60,
		            columnWidth:.2,
		            items:[{
		              	xtype:'textfield',
		                fieldLabel: '零件名称',
		                id: 'partnamedetail'
		                //width: 100
		            		}]
       },{				              
		       		layout: 'form',
		           labelAlign:"right",
		           labelWidth:60,
		           columnWidth:.2,
		           items:[{
		           	xtype:'textfield',
		            fieldLabel: '整件图号',
		            id: 'goodnumdetail'
		            //width: 100
		           	}]
       },{					              
		       		layout: 'form',
		           labelAlign:"right",
		           buttonAlign:"center",
		           labelWidth:60,
		           columnWidth:.2,
		           items:[{
		        	xtype:'textfield',
		            fieldLabel: '零件图号',
		            id: 'partnumdetail'
		            //width: 100
		           		}]
       },{					              
	      		layout: 'form',
		           labelAlign:"right",
		           buttonAlign:"center",
		           labelWidth:60,
		           columnWidth:.2,
		           items:[{
		        	xtype:'textfield',
		            fieldLabel: '制定日期',
		            id: 'platedatedetail'
		            //width: 100
		           		}]
       	}]
		    });
    
    //定义详细定额信息列表
    var record_detail = Ext.data.Record.create([    		
                                    		{name:'processnum_detail'},
                                			{name:'process_detail'},
                                			{name:'worktype_detail'},
                                			{name:'farm_detail'},
                                			//{name:'equipment_detail'},
                                			{name:'readytime_detail'},
                                			{name:'worktime_detail'},
                                			{name:'groupnum_detail'}
                                			]); 
                            	                                    
                                
    	var hpObj_detail = {url:'logicprintedplatequery.jsp',method: 'POST',extraParams:{}};
    	var ds_detail = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj_detail)),
            reader: new Ext.data.JsonReader({
                totalProperty:'totalProperty',
                root:'filedata'
            },record_detail)
        });
                            	                                                  	
    	//var sm_dx = new Ext.grid.CheckboxSelectionModel();
    	var grid_detail = new Ext.grid.GridPanel({
    		title: "工时定额信息",
    		id:'detailtable',
    		enableColumnMove:false,
    		columns:[
    		         {header: "工序号",dataIndex: "processnum_detail",width : 100,align:'center',sortable: true},
    		         {header: "工序名称",dataIndex: "process_detail",width : 100,align:'center',sortable: true},
    		         {header: "工种",dataIndex: "worktype_detail",width : 100,align:'center',sortable: true},
    		         {header: "车间",dataIndex: "farm_detail",width : 100,align:'center',sortable: true},
    		         {header: "准备时间(h)",dataIndex: "readytime_detail",width : 100,align:'center',sortable: true},
    		         {header: "加工时间(h)",dataIndex: "worktime_detail",width : 100,align:'center',sortable: true},
    		         {header: "每组数量",dataIndex: "groupnum_detail",width : 100,align:'center',sortable: true}],
    		ds:ds_detail,
    		//sm:sm_dx,
    		width:980,
    		height:200,
    		//autoHeight :false,
            autoScroll :true
    	});                     	
                            	
	//定义弹出窗口
   var win = new Ext.Window({
   			id:'win',
   			title:"<p align='center'>零件工时定额信息</p>",
   			modal:true,                                         
               width:1000,
               height:300,
               closeAction:'hide',
               plain: true,
               buttonAlign:"center",
				bodyBorder:true,
				resizable:false,
               items:[new Ext.Panel({
               		layout:'form',
               		frame:true,
               		items:[formpanel,grid_detail]
               })],
               buttons: [{ id:'returnbtn',
						text: '返回',
						handler:cancelFn
	                }]
   });
   
 //返回函数
	function cancelFn(){
   	win.hide();
   }
	
	//详细信息查看函数
	function viewFn(){
    	var record =sm.getSelections();// 返回值为 Record 类型            	
		if(record.length==0){
            alert('请先选择要查看的定额零件!');
            return;
        }else if(record.length!=1){
        	alert('每次只能查看一个零件的详细定额信息!');
            return;
        }else{ 
        	if(!win.isVisible()){
			win.show();//明细窗口显示
			document.getElementById('goodnamedetail').value = record[0].get("goodname").toString();
			document.getElementById('partnamedetail').value = record[0].get("partname").toString();
			document.getElementById('goodnumdetail').value = record[0].get("goodnum").toString();
			document.getElementById('partnumdetail').value = record[0].get("partnum").toString();
			document.getElementById('platedatedetail').value = record[0].get("platedate").toString();
			var record =sm.getSelections();
			var filter = "1=1 and part_num = '" + record[0].get("partnum").toString() + "'";
			var order = "process_num ASC";
			hpObj_detail.extraParams.filter = filter;
			hpObj_detail.extraParams.order = order;   	
			hpObj_detail.extraParams.type = "QUERYDETAIL";
	   		ds_detail.load();
			}
		} 
    
	}

	
});