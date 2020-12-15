Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var limit=16;
	var width=this.frameElement.offsetWidth;
	var height=this.frameElement.offsetHeight;
	var wpwidth = 700;
	
	var search=new Ext.Search({
	 	region:"north",
		renderTo : 'querydiv',
		id:"search",
		style:'margin:0 -30',
        state:0,        
        columnArray:Stringquery,
		//stageArray:Stringstage,
		orderArray:Stringorder,
        height:125,          
        handler:customQueryFn
    });
    
    function customQueryFn(){
    	ds.load(); 
		search.getQueryText();
	}
	var jsondata = { 'results': 1, 'rows': [
					]};
	
	var record_case = Ext.data.Record.create([    		
			{name:'creator'},
			{name:'createtime'}
	]);

	var ds = new Ext.data.Store({
		autoLoad: false,
	    proxy: new Ext.data.MemoryProxy(jsondata),
	    reader: new Ext.data.JsonReader({
	        totalProperty: 'results',
	        root: 'rows'
	    },record_case)
	}); 
	
	var frameheight = this.frameElement.offsetHeight;
	var leftHeight = frameheight - 125;
	var sm = new Ext.grid.CheckboxSelectionModel();
	var grid = new Ext.grid.GridPanel({
					        id:'experttable',
					        title:"专家评分查询结果",
					        border : true,
					        enableColumnMove:false,
					        enableColumnHide:false,
					        columns: [sm,
					       {
					            header: "专家姓名", 
					            align:"center",
					            dataIndex: "creator",
					            width: 100,
					            sortable: true
					        }, {
					            header: "评分时间", 
					            align:"center",
					            dataIndex: "createtime",
					            width: 120,
					            //renderer : Ext.util.Format.dateRenderer('Y-m-d'),
					            sortable: true
					        }],
					        ds:ds,
					        sm:sm,
					        //height: leftHeight,//393
					        //autoHeight:true,
					        autoScroll:true,
							bbar: new Ext.PagingToolbar({
								    	pageSize:limit,
								    	store:ds,
								   		displayInfo:true,
								    	displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
								    	emptyMsg:"没有记录"
								})
					       });
	

    var ds_In = new Ext.data.Store({
         proxy: new Ext.data.MemoryProxy(jsondata),
         reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
         },[{name:'creator'},
			{name:'createtime'}
    	])
    });
    
    var sm_In = new Ext.grid.CheckboxSelectionModel();
    var grid_In = new Ext.grid.GridPanel({
					        id:'grid_In',
					        title:"已选择的专家评分",
					        border :true,
					        enableColumnMove:false,
					        enableColumnHide:false,
					        columns: [sm_In,
					        {
					            header: "专家姓名",  
					            align:"center", 
					            dataIndex: "creator",
					            width: 100,
					            sortable: true
					        }, {
					            header: "评分时间", 
					            align:"center",
					            dataIndex: "createtime",
					            width: 120,
					            //renderer : Ext.util.Format.dateRenderer('Y-m-d'),
					            sortable: true
					        }],
					        ds:ds_In,
					        sm:sm_In,
					        //height: 600,
					        autoScroll:true,
							tbar:new Ext.Toolbar({ 
									height:26,
									items:
									[{ id:'assess',iconCls:"update",text:"权重值计算", handler: weightcalFn}]
								})
	});				       
					       		       
					


	var button1 = new Ext.Button({ 
			id: 'btnright',  
			iconCls: "right", 
			pressed :true,
			style:'margin-top:'+(height-250)/2,
			handler:getRow
	}); 

	var button2 = new Ext.Button({   
	        iconCls: "left",  
	        pressed :false,
	        style:'margin-top:30',
	        handler:deleteRow 
	}); 

	var simple = new Ext.FormPanel({
	        layout:"form",
	        frame:true,
	        //height:600,
	        border:false,
	        items: [button1,button2]
	});
    
    var selectpanel = new Ext.Panel({
    	border: false,
    	renderTo : 'selectdiv',
        layout: "table",
        layoutConfig: {columns: 3},
        title: "<p align='center'>专家打分选择</p>",
        items: [{	
				id:'c1',
				height: height-150,	
				layout:'fit',
				width: (wpwidth-40)/2,
		        border:false,			      
		        items: grid
		    },{
		    	id:'c2',
		        height: height-150,				        
		        border:false,
		        width: 	40,
		        layout:'fit',
		        items: simple
		    },{
		    	id:'c3',
		        height: height-150,	
		        width: (wpwidth-40)/2,
		        border:false,	
		        layout:'fit',
		        autoScroll:true,
		        items: grid_In
		    }]
    });    
			
	var leftborder = new Ext.FormPanel({
        layout:"form",
        border:false,
		items: [
		    	{
		    		//columnWidth:1,
			        border:false,
			        layout:'fit',
			        contentEl:"querydiv"
			    },{
			    	//columnWidth:1,
			        border:false,
			        layout:'fit',
			        contentEl:"selectdiv"
			    }]
	});
	
	var rightborder = new Ext.FormPanel({
        layout:"form",
        bodyStyle: 'padding:50px 50px 0',
        frame: true,
        labelAlign: 'right',
        border:false,
		items: [{
			xtype:'fieldset',
            title: '实例问题描述参数权重',
            collapsible: false,
            autoHeight:true,
            layout:'form',
            //border:false,
            items:[{
                    xtype:'textfield',
                    fieldLabel: '工件材料',
                    emptyText:'0',
                    id: 'diameterw',
                    width: 100,
                    //value: '0.3',
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }, {
                    xtype:'textfield',
                    fieldLabel: '毛坯长度',
                    emptyText:'0',
                    id: 'pitchw',
                    width: 100,
                    //value: '0.3',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                }, {
                    xtype:'textfield',
                    fieldLabel: '毛坯宽度',
                    emptyText:'0',
                    id: 'fitgradew',
                    width: 100,
                    //value: '0.1',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                }, {
                    xtype:'textfield',
                    fieldLabel: '毛坯高度',
                    emptyText:'0',
                    id: 'holetypew',
                    width: 100,
                    //value: '0.1',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                }, {
                    xtype:'textfield',
                    fieldLabel: '结构特征',
                    emptyText:'0',
                    id: 'lenthw',
                    width: 100,
                    //value: '0.1',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                }/*, {
                    xtype:'textfield',
                    fieldLabel: '工件材料',
                    emptyText:'0',
                    id: 'materialw',
                    width: 100,
                    //value: '0.2',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                }*/]
         },{
			xtype:'fieldset',
            title: '工件材料属性权重',
            collapsible: false,
            autoHeight:true,
            layout:'form',
            //border:false,
            items:[{
                    xtype:'textfield',
                    fieldLabel: '材料硬度权重',
                    emptyText:'0',
                    id: 'strw1',
                    width: 100,
                    //value: '0.4',
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }, {
                    xtype:'textfield',
                    fieldLabel: '抗拉强度权重',
                    emptyText:'0',
                    id: 'strw2',
                    width: 100,
                    //value: '0.4',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                }, {
                    xtype:'textfield',
                    fieldLabel: '屈服强度权重',
                    emptyText:'0',
                    id: 'strw3',
                    width: 100,
                    //value: '0.2',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                },{
                    xtype:'textfield',
                    fieldLabel: '材料热导率权重',
                    emptyText:'0',
                    id: 'strw4',
                    width: 100,
                    //value: '0.2',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                },{
                    xtype:'textfield',
                    fieldLabel: '弹性模量权重',
                    emptyText:'0',
                    id: 'strw5',
                    width: 100,
                    //value: '0.2',
                    regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成,精确到小数点后2位!'
                }]
         },{
			xtype:'fieldset',
            title: '结构特征权重',
            collapsible: false,
            autoHeight:true,
            layout:'form',
            //border:false,
            items:[{
                    xtype:'textfield',
                    fieldLabel: '相同特征类别数量权重',
                    emptyText:'0',
                    id: 'simw11',
                    width: 100,
                    //value: '0.8',
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                },{
                    xtype:'textfield',
                    fieldLabel: '相同类别下特征数量权重',
                    emptyText:'0',
                    id: 'simw22',
                    width: 100,
                    //value: '0.8',
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
         },{
			xtype:'fieldset',
            title: '相似度对最终评价结果的影响权重',
            collapsible: false,
            autoHeight:true,
            layout:'form',
            //border:false,
            items:[{
                    xtype:'textfield',
                    fieldLabel: '相似度权重',
                    emptyText:'0',
                    id: 'simw',
                    width: 100,
                    //value: '0.8',
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
         }],
         buttons: [{
            text: '修改',
            id:"modifyw",
            type: 'submit',
            disabled: true,
            handler:function(){
				
		   }
        },{
            text: '重置',
            id:"newReset",
            handler:function(){
				//rightborder.getForm().reset();								     								
				//Ext.getCmp("diameterw").focus();
            	var row1 = new Ext.data.Record({'creator':'张三','createtime':'2015-10-9'});
            	var row2 = new Ext.data.Record({'creator':'赵四','createtime':'2015-10-9'});
            	var row3 = new Ext.data.Record({'creator':'和二','createtime':'2015-10-9'});
            	var row4 = new Ext.data.Record({'creator':'纪大','createtime':'2015-10-9'});
            	var row5 = new Ext.data.Record({'creator':'王五','createtime':'2015-10-9'});
            	ds.add(row1); 
                ds.add(row2); 
                ds.add(row3); 
                ds.add(row4); 
                ds.add(row5); 
		   }
        }]
	});
	
	var viewport = new Ext.Viewport({
		layout:"border",
		border:false,
		autoHeight:false,
		items: [{
			id:'wp',
			region: 'west',
			width: wpwidth,
			border:false,
			layout:'fit',
	        items: leftborder
		},{
	    	id:'cp',
	        region: 'center',
	        title:"<p align='center'>各参数相似度计算权重</p>",
	        border:false,	
	        frame: true,
	        layout:'fit',
	        items: rightborder
	    }]
	});
	
	//Ext.get("querydiv").setVisible(true);
	var weightjson = {'diameterw': 0.3, 'pitchw': 0.3, 'fitgradew': 0.1, 'holetypew': 0.1, 'lenthw': 0.1, 'materialw': 0.2, 'strw1': 0.35, 'strw2': 0.35, 'strw3': 0.3, 'simw': 0.8};
	function weightcalFn(){
		var n = Ext.getCmp("grid_In").getStore().getCount();
		if(n==0){
			alert( "提示:请至少选择一条记录!");
			return;
		}
		Ext.getDom("diameterw").value = weightjson.diameterw;
		Ext.getDom("pitchw").value = weightjson.pitchw;
		Ext.getDom("fitgradew").value = weightjson.fitgradew;
		Ext.getDom("holetypew").value = weightjson.holetypew;
		Ext.getDom("lenthw").value = weightjson.lenthw;
		Ext.getDom("materialw").value = weightjson.materialw;
		
		Ext.getDom("strw1").value = weightjson.strw1;
		Ext.getDom("strw2").value = weightjson.strw2;
		Ext.getDom("strw3").value = weightjson.strw3;
		
		Ext.getDom("simw").value = weightjson.simw;
		
		Ext.getCmp("modifyw").enable();
	}
    
    function getRow(){
    	var record = sm.getSelections();// 返回值为 Record 类型
		if (record.length == 0) {
			alert( "提示:请至少选择一条记录!");
			return;
		}
		var store = ds_In.getRange();	
		for(var i=0;i<record.length;i++){
			for(var j=0;j<store.length;j++){
				if(record[i].get("wid")==store[j].get("wid")){
					alert("提示：有相同的记录，不能重复导入！");
					return ;
				}
			}
		}
		var gdata="";
		for (var i = 0; i < record.length; i++) {
			if (i != 0) {
				gdata += ",";
			}
			gdata += "{'creator':'" + record[i].get("creator") + "'";
			gdata +=",'createtime':'" + record[i].get("createtime") + "'}";
		}
		openPcpdfixass(gdata);
    }
    //导入
	function openPcpdfixass(gdata){
		var obj = "";
        var recordfix = "";
	    recordfix = gdata.split("},");
        for( var i = 0;i<recordfix.length;i++){
            if(i<recordfix.length-1){
  					 recordfix[i] = recordfix[i]+"}";
			         obj = Ext.util.JSON.decode(recordfix[i]);
		             guidefix(obj.creator,obj.createtime);
	         }else {
			         obj = Ext.util.JSON.decode(recordfix[i]);
					 guidefix(obj.creator,obj.createtime);
	         }
		}	
	}
	function guidefix(creator,createtime){
		var n = Ext.getCmp("grid_In").getStore().getCount();
        var record_fix = Ext.data.Record.create([    		
		         {name: 'creator', type: 'string'},
		         {name: 'createtime', type: 'string'}      
  		]);   
		var p = new record_fix({                	
	              creator:creator,
	              createtime:createtime
	    });         
        Ext.getCmp("grid_In").stopEditing();				
        Ext.getCmp("grid_In").getStore().insert(n, p);
	}
    
    function deleteRow(){
    	var record =sm_In.getSelections();// 返回值为 Record 类型		            		            	
		// 弹出对话框警告
		if(record.length==0){
			alert("提示:请选择要删除的记录!");
		    return;
		}  
		if(record) {
			for(var i=0;i<record.length;i++){
				ds_In.remove(record[i]);
			}
		 }
    }
    
    //改变窗口大小时候改变表格的宽度
	window.onresize = function(){	  
		 var frameheight = this.frameElement.offsetHeight;	
		 var leftHeight = frameheight - 150;//filetable
		 Ext.getCmp('c1').setHeight(leftHeight);
		 Ext.getCmp('c2').setHeight(leftHeight);
		 Ext.getCmp('c3').setHeight(leftHeight);
	}
});

