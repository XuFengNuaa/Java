Ext.onReady(function(){
	var filter='1=1';
	var start=0;
	var limit=100000000;//每页显示记录条数

    var record_cut= Ext.data.Record.create([    		
    		{name:'entry_num'},
    		{name:'mnum'},
			{name:'id'},
			{name:'indi_num'},
			{name:'in_time'},
			{name:'classname'},
			{name:'spec'},
			{name:'company'},
			{name:'newstatus'},
			{name:'name'},
    ]); 
    
    //调用数据库信息
	var hqObj={url:'logic_checkmod.jsp',method: 'POST',extraParams:{type:'QUERYMT',params:{start:start,limit:limit},filter:filter}};
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
			            header: "个体编号",
			            dataIndex: "indi_num",
			            width:110,
			            sortable: true
			        }, {
			            header: "物资编码",
			            dataIndex: "mnum",
			            width:110,
			            sortable: true
			        }, {
			            header: "类别",
			            dataIndex: "classname",
			            width:90,
			            sortable: true
			        }, {
			            header: "名称",
			            dataIndex: "name",
			            width:100,
			            sortable: true
		            }, {
			            header: "厂商",
			            dataIndex: "company",
			            width:110,
			            sortable: true
		             }, {
			            header: "规格",
			            dataIndex: "spec",
			            width:100,
			            sortable: true
			        }, {
			            header: "新旧状态",
			            dataIndex: "newstatus",
			            width:70,
			            sortable: true
			        }, {
			            header: "入库时间",
			            dataIndex: "in_time",
			            width:85,
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
				title:"<p align='center'>测量工具入库批准</p>",
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
					            handler:refFn,
					            iconCls:"remove"
					        },'-',{ 								
					            id:'output',
					            text:"导出", 
					            handler:outFn,
					            iconCls:"wlk"
			        		},'-']
		        }),
		        /*bbar: new Ext.PagingToolbar({
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
            alert('提示：请先选择要批准的测量工具!');
            return;
    	}
    	var entry_nums="";
    	var length = record.length;
		if(record.length!=0) {
			Ext.MessageBox.confirm('提示','是否确定批准？？？',function(btn) {
				if (btn=='yes'){
				Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){		//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								entry_nums+=",";
							}
							entry_nums+="'"+record[i].json.entry_num+"'";
					}
					
					Ext.showProgressDialog("正在提交修改数据，请稍候...");
					Ext.Ajax.request({
						 	url:'logic_checkmod.jsp',
						 	method:'post',
					 		params:{type:'APPLYMT',entry_nums:entry_nums,length:length},
							success:function(result){
								if (result.responseText.indexOf("success") != -1){
									Ext.hideProgressDialog();	
									alert("提示：批准成功!");
									ds.load();	
								}else{
									Ext.hideProgressDialog();
									alert('错误：某些量具已被操作，请重试！');
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
	
	
	//拒绝函数
	function refFn(){
		var record =sm.getSelections();// 返回值为 Record 类型	         
		if(record.length==0){
            alert('提示：请先选择要拒绝的测量工具!');
            return;
    	}
    	var entry_nums="";
    	var length = record.length;
		if(record.length!=0) {
			Ext.MessageBox.confirm('提示','是否确定拒绝？？？',function(btn) {
				if (btn=='yes'){
				Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){		//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								entry_nums+=",";
							}
							entry_nums+="'"+record[i].json.entry_num+"'";
					}
					
					Ext.showProgressDialog("正在提交修改数据，请稍候...");
					Ext.Ajax.request({
						 	url:'logic_checkmod.jsp',
						 	method:'post',
					 		params:{type:'MTRefuse',entry_nums:entry_nums,length:length},
							success:function(result){
								if (result.responseText.indexOf("success") != -1){
									Ext.hideProgressDialog();	
									alert("提示：拒绝成功!");
									ds.load();	
								}else{
									Ext.hideProgressDialog();
									alert('错误：某些量具已被操作，请重试！');
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
		if(ds.getCount()==0){
			alert('数据为空，无需导出Excel!');
			return;
		}else{
			var url = 'http://localhost:8080/tsms/web/sms/ldappmod/check/mtentry_excel.jsp';
    		location.href='mtentry_excel.jsp?url='+url;
    	}
	}
	
	
});	




