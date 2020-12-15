Ext.onReady(function(){
	var filter='1=1';
	var start=0;
	var limit=100000000;//每页显示记录条数

    var record_cut= Ext.data.Record.create([    		
    		{name:'grd_num'},
    		{name:'grd_department'},
			{name:'bclass'},
			{name:'s_time'}
    ]); 
    
    //调用数据库信息
	var hqObj={url:'logic_checkmod.jsp',method: 'POST',extraParams:{type:'QUERYCG',params:{start:start,limit:limit},filter:filter}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hqObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_cut),
        autoload:true
    });    
    ds.load();
    var hqObjAni={url:'logic_checkmod.jsp',method:'POST',extraParams:{}};
	var store_cut=new Ext.data.Store({
	    proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hqObjAni)),
	    pruneModifiedRecords:true,
		reader:new Ext.data.JsonReader({            
	        root:'root'
	    },record_cut)
	});
	
	//定义查询结果界面
	var sm = new Ext.grid.CheckboxSelectionModel();
	var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        loadMask:true,
		        enableColumnMove:false,
		        columns: [sm,
		        	{
			            header: "送磨单号",
			            dataIndex: "grd_num",
			            style:'width:10%',
			            sortable: true
		            }, {
			            header: "小类",
			            dataIndex: "bclass",
			            style:'width:20%',
			            sortable: true
			        }, {
			            header: "磨刀单位",
			            dataIndex: "grd_department",
			            style:'width:10%',
			            sortable: true
			        }, {
			            header: "送磨时间",
			            dataIndex: "s_time",
			            style:'width:10%',
			            renderer:renderFn,
			            sortable: true    
			        }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        stateId:'filetable',
		        autoScroll:true,
		        renderTo: document.body
    });
    
   //一个送磨单号的下的详细信息 
    var record_cut1= Ext.data.Record.create([    		
    		{name:'id'},
    		{name:'grd_num'},			
			{name:'s_count'},
			{name:'grd_department'},			
			{name:'s_time'},			
			{name:'rid'},
			{name:'bclass'},
			{name:'name'},
			{name:'company'},	
			{name:'price'},
			{name:'total_price'},			
			{name:'mnum'}		
    ]); 
    
    //调用数据库信息
    var hpOb={url:'logic_checkmod.jsp',method: 'POST',extraParams:{}};
	var check_ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpOb)),
        reader: new Ext.data.JsonReader({
            totalProperty:'check_totalProp',
            root:'check_data'
        },record_cut1)       
    });	
	
	//定义查询结果界面
	var sm1 = new Ext.grid.CheckboxSelectionModel();
	var grid1 = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:false,
		        columns: [sm1,
		        	{
			            header: "物资编码",
			            id:"mnum",
			            dataIndex: "mnum",
			            width:90			            
			        }, {
			            header: "小类",
			            id:"bclass",
			            dataIndex: "bclass",
			            width:70,
			            sortable: true,
			            hidden:true			           
			        },{
			            header: "名称",
			            id:"name",
			            dataIndex: "name",
			            width:90,
			            sortable: true
			        },{
			            header: "厂商",
			            id:"company",
			            dataIndex: "company",
			            width:90,
			            sortable: true
			        },{
			            header: "送磨数量",
			            id:"s_count",
			            dataIndex: "s_count",
			            width:60,
			            sortable: true
			        },{
			            header: "磨刀单位",
			            id:"grd_department",
			            dataIndex: "grd_department",
			            width:75,
			            sortable: true
		            },{
			            header: "单价（元）",
			            id:"price",
			            dataIndex: "price",
			            width:75,
			            sortable: true    
			        },{
			            header: "总价（元）",
			            id:"total_price",
			            dataIndex: "total_price",
			            width:75,			            
			            sortable: true
			        },{
			            header: "送磨日期",
			            id:"s_time",
			            dataIndex: "s_time",
			            width:70,			            
			            sortable: true  
			        }],
		        ds:check_ds,
		        sm: sm1, //多选框checkbox
		        height: 400,
		        autoScroll:true
    });
    
    
	
	//截断时间函数
     function renderFn(value){
    	return value.substring(0,10);
     }
    
    //组装界面
    new Ext.Viewport({
		layout:"border",
		autoHeight:false,
		items:[
				{
				region:'center',
				layout:'fit',
				title:"<p align='center'>刀具送磨批准</p>",
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:['-',{ 								
					            id:'refresh',
					            text:"刷新", 
					            handler:refreshFn,
					            iconCls:"refresh"
	                        },'-',{ 								
					            id:'app',
					            text:"批准", 
					            handler:appFn,
					            iconCls:"update"
				            },'-',{ 				            	
								id:'refuse',
					            text:"拒绝", 
					            handler: refFn ,
					            iconCls:"remove"
				            },'-',{ 				            	
								id:'detail-btn',
					            text:"单号明细", 
					            handler: viewFn ,
					            iconCls:"detail"
					        },'-',{ 								
					            id:'output',
					            text:"导出", 
					            handler:outFn,
					            iconCls:"wlk"
			        		},'-']
		        }),
		      /*  bbar: new Ext.PagingToolbar({
				    pageSize:limit,
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				}),*/
				items:grid
		}]
	});   

  //刷新函数   
	function refreshFn(){
		ds.reload();
	}  

    
	//批准函数
	function appFn(){
		var record =sm.getSelections();// 返回值为 Record 类型	         
		if(record.length==0){
            alert('提示：请先选择要批准的刀具!');
            return;
    	}
    	var grd_num = "";
    	var length = record.length;
		if(record.length!=0) {
			Ext.MessageBox.confirm('提示','是否确定批准？？？',function(btn) {
				if (btn=='yes'){
				Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){		//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								grd_num+=",";
							}
							grd_num+="'"+record[i].json.grd_num+"'";
					}
					
					Ext.showProgressDialog("正在提交修改数据，请稍候...");
					Ext.Ajax.request({
						 	url:'logic_checkmod.jsp',
						 	method:'post',
					 		params:{type:'APPLYCG',grd_num:grd_num,length:length},
							success:function(result){
								if (result.responseText.indexOf("success") != -1){
									Ext.hideProgressDialog();	
									alert("提示：批准成功!");
									ds.load();	
								}else{
									Ext.hideProgressDialog();
									alert("提示:某些送磨刀已被处理，请重试！");
									ds.load();	
								}
						 	},
							 //提交失败的回调函数
						 	failure:function(){
						 		Ext.hideProgressDialog();
								alert('提示:服务器出现错误请稍后再试！');
						 	}
					});
				}
			});
		}
	}
	
	//定义弹出窗口
    var win= new Ext.Window({
    			id:'win',    			
    			modal:true,                  
                width:670,
                height:450,                
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
                autoShow: true,
			//	bodyBorder:true,
				resizable:false,
				layout:'fit',
				items:[grid1],
                buttons: [
                		  {
                		  	
	                		  	id:'total',
	                		  	text:'总费用',
	                		  	handler	:function(){
	                		  		
	                		  		var record = sm1.getSelections();		// 返回值为 Record 类型  
	                		  		var Price = 0;
	                		  		
	                		  		 if(record.length==0){
							           
							            alert('提示：请先选择要计算费用的刀具!');
							            return;
							        
							        }else{
	                		  		
		                		  		for(var i=0;i<record.length;i++){
		                		  			Price += Number(record[i].json.total_price);
		                		  		}
	                		  		}
	                		  		
	                		  		alert('此次送磨刀具所需的总价为：'+Price+'元');
	                		  		
	                		  	}
                		   
                		   },{
                		  	
	                		  	id:'apps',
	                		  	text:'批准',
	                		  	handler:function(){
	                		  		var record =sm1.getSelections();// 返回值为 Record 类型  
							        var Rid = "";
							        var length = record.length;
							        if(record.length==0){
							            alert('提示：请先选择要批准的刀具!');
							            return;
							        }else{
							         	 for(var i=0;i<record.length;i++){		//若选中的不只一条，将每条的申请单号分开
											if(i!=0){
												Rid+=",";
											}
											Rid+="'"+record[i].json.rid+"'";
										}
								         Ext.showProgressDialog("正在提交修改数据，请稍候...");
									     Ext.Ajax.request({
											   url:'logic_checkmod.jsp',
											   	params : {
													type : 'APPLYCGm',	
													length:length,				
													Rid : Rid										
												},
												success : function(result) {			
													Ext.hideProgressDialog();
													if (result.responseText.indexOf("success") != -1) {		
														alert('提示：批准成功！');		    											
														check_ds.load();
														ds.load();														
													}else{
													    alert("提示:某些送磨刀已被处理，请重试！");
													    check_ds.load();
														ds.load();	
													}
												},
												failure : function(){
													Ext.hideProgressDialog();
													Ext.MessageBox.alert("提示:服务器出现错误请稍后再试！");						
												}
										  
										  });		                       
							        }
	                		  	}
                		  
                		  },{
                		  	
	                		  	id:'refs',
	                		  	text:'拒绝',
	                		  	handler:function(){
	                		  		var record =sm1.getSelections();// 返回值为 Record 类型  
							        var Rid = "";
							        var length = record.length;
							        if(record.length==0){
							            alert('提示：请先选择要拒绝的刀具!');
							            return;
							        }else{
							         	 for(var i=0;i<record.length;i++){		//若选中的不只一条，将每条的申请单号分开
											if(i!=0){
												Rid+=",";
											}
											Rid+="'"+record[i].json.rid+"'";
										}
								         Ext.showProgressDialog("正在提交修改数据，请稍候...");
									     Ext.Ajax.request({
											   url:'logic_checkmod.jsp',
											   	params : {
													type : 'RefuseM',	
													length:length,		
													Rid : Rid										
												},
												success : function(result) {			
													Ext.hideProgressDialog();
													if (result.responseText.indexOf("success") != -1) {		
														alert('提示：拒绝成功！');		    											
														check_ds.load();
														ds.load();														
													}else{
													    alert("提示:某些送磨刀已被处理，请重试！");
													    check_ds.load();
														ds.load();	
													}
												},
												failure : function(){
													Ext.hideProgressDialog();
													Ext.MessageBox.alert("提示:服务器出现错误请稍后再试！");						
												}
										  
										  });		                       
							        }
	                		  	}
                		  },
                		  { id:'ok-btn',
						    text: '返回',
						    handler:function(){							     
						    win.hide();
						   }
	                    }],
	             bbar: new Ext.PagingToolbar({
				    pageSize:limit,
				    store:check_ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				})        
    });  	
	
	
	function viewFn(){
		
		var record =sm.getSelections();//获得页面中选中信息
		
		if(record.length==0){
	        alert('请先选择送磨单!');
	            return;
        }else if(record.length!=1){
        	alert('每次只能操作一个送磨单!');
            return;
        }else if(!win.isVisible()){
        
        	var grd_num = "'"+record[0].json.grd_num+"'";
	        win.setTitle("<p align='center'>送磨刀具一览</p>");
	        win.show();
	        hpOb.extraParams.filter="grd_num = "+grd_num;
	        hpOb.extraParams.order="grd_num";
	        hpOb.extraParams.type="DETAILGRIND"; 
	   	    check_ds.load();   		      
        }		
	}
	
	
	function refFn(){	
		
		var record =sm.getSelections();// 返回值为 Record 类型	         
		if(record.length==0){
            alert('提示：请先选择要拒绝的刀具!');
            return;
    	}
    	var grd_num = "";
    	var length = record.length;
		if(record.length!=0) {
			Ext.MessageBox.confirm('提示','是否确定拒绝？？？',function(btn) {
				if (btn=='yes'){
				Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){		//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								grd_num+=",";
							}
							grd_num+="'"+record[i].json.grd_num+"'";
					}
					
					Ext.showProgressDialog("正在提交修改数据，请稍候...");
					Ext.Ajax.request({
						 	url:'logic_checkmod.jsp',
						 	method:'post',
					 		params:{type:'Refuse',grd_num:grd_num,length:length},
							success:function(result){
								if (result.responseText.indexOf("success") != -1){
									Ext.hideProgressDialog();	
									alert("提示：拒绝成功!");
									ds.load();	
								}else{
									Ext.hideProgressDialog();
									alert("提示:某些送磨刀已被处理，请重试！");
									ds.load();	
								}
						 	},
							 //提交失败的回调函数
						 	failure:function(){
						 		Ext.hideProgressDialog();
								alert('提示:服务器出现错误请稍后再试！');
						 	}
					});
				}
			});
		}
	}
	
	
	//导出Excel表
	function outFn(){
		var record =sm.getSelections();
		if(record.length==0){
			alert('提示：请先选择要导出的送磨单！');
			return;
		}else if(record.length>1){
    		alert('提示：每次只能导出一种单号!');
            return;
		}else{
			var grd_num=record[0].json.grd_num;
			var url = 'http://localhost:8080/tsms/web/sms/ldappmod/check/grdout_excel.jsp';
    		location.href='grdout_excel.jsp?url='+url+'&grd_num='+grd_num;
    	}
	}
	
	
});	




