Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=1000000;//每页显示记录条数
	
	//定义查询页面
	var search=new Ext.Search({
		region:'north',
        renderTo:'querydiv',
        state:0,        
        columnArray:Stringquery,
		orderArray:Stringorder,
        height:123,          
        handler:customQueryFn
    });

    //定义材料record
	var materialrecord = new Ext.data.Record.create([
		{
			name:'ordernum'//订单号
		},{
			name:'mnum'//物资编码
		},{
			name:'classname'//类别
		},{
			name:'name'//名称
		},{
			name:'modelnum'//型号
		},{
			name:'spec'//规格
		},{
			name:'prewarndate'//提前预警天数
		},{
			name:'company'//厂商
		},{
			name:'measure'//计量单位
		},{
			name:'guaranteedate'//保质期
		},/*{
			name:'mincycle'//最小安全周期
		},{
			name:'maxcycle'//最大安全周期
		},*/{
			name:'minstore'//最小安全库存
		},{
			name:'maxstore'//最大安全库存
		},{
			name:'monthuse'//月用量
		},{
			name:'singlepur'//单次采购量
		},{
			name:'kuname'//库名
		},{
			name:'shelfnum'//货架号
		},{
			name:'valiadate',
			type:'date',
			dateFormat:'Y-n-j'//有效期
		},{
			name:'indate',
			type:'date',
			dateFormat:'Y-n-j'//有效期
		},{
			name:'nowcount',
			type:'int'//在库量
		},{
			name:'remark'//备注
		},{
			name:'deletedate'
			/*type:'date',
			dateFormat:'Y-n-j'*/
		}/*,{
			name:'getperson'
		}*/,/*{
			name:'getcount'
		},*/{
			name:'state'
		},{
			name:'id'
		},{
			name:'incount'
		},{
			name:'outcount'
		},{
			name:'changecount'
		}
	]);
	
	var materialobj = {url:'logicmaterial_delete.jsp',method:'POST',extraParams:{}};
	//定义数据源
	var ds = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy(new Ext.data.Connection(materialobj)),
		reader:new Ext.data.JsonReader({
			totalProperty:'totalProperty',
			root:'filedata'
		},materialrecord)
	});
	
		function customQueryFn(){
			Ext.showProgressDialog();
			materialobj.extraParams.filter=search.getFilter();
			materialobj.extraParams.order = search.getOrder();
			materialobj.extraParams.type="QUERY";
    	    //var pagebar = Ext.getCmp("pagebar");
    	    
    	   // var obj = {params:{start:start,limit:limit}};
    		if(ds.load()){
    			Ext.hideProgressDialog();
    			}
   		setTimeout(function(){search.getQueryText()},1500);
	}
	//定义grid
	  var sm = new Ext.grid.CheckboxSelectionModel();
      var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        clicksToEdit:1,
		        loadMask:true,
		        enableColumnMove:false,
		        columns: [sm,
		            {
			            header: "订单号",
			            dataIndex: "ordernum",
			            width:130,
			            align:'center',
			            sortable: true
			            //hidden:true
		            },
		            {
			            header: "物资编码",
			            dataIndex: "mnum",
			            width:95,
			            align:'center',
			            sortable: true,
			            hidden:true,
			             align:'center'
			            
		            }, {
			             header: "类别",
			            dataIndex: "classname",
			            width:65,
			            hidden:true,
			            sortable: true,
			             align:'center'
			        }, {
			            header: "名称",
			            dataIndex: "name",
			            width:95,
			            hidden:true,
			            sortable: true,
			             align:'center'
			        }, {
			            header: "型号",
			            dataIndex: "modelnum",
			            width:100,
			            hidden:true,
			            sortable: true,
			            align:'center'
			        },{
			            header: "规格",
			            dataIndex: "spec",
			            width:80,
			            hidden:true,
			            sortable: true,
			             align:'center'
		            }, {
			            header: "预警天数",
			            dataIndex: "prewarndate",
			            width:80,
			            sortable: true,
			            hidden:true,
			            align:'center'
		            }, {
			            header: "厂商",
			            dataIndex: "company",
			            width:75,
			            sortable: true,
			            hidden:true,
			             align:'center'
		            }, {
			            header: "计量单位",
			            dataIndex: "measure",
			            width:85,
			            sortable: true,
			            hidden:true,
			             align:'center'
			        }, {
			            header: "保质期",
			            dataIndex: "guaranteedate",
			            width:75,
			            sortable: true,
			            hidden:true,
			             align:'center'
			        }/*,{
			            header: "最大安全周期",
			            dataIndex: "maxcycle",
			            width:75,
			            sortable: true,
			            hidden:true,
			             align:'center'
			        }, {
			            header: "最小安全周期",
			            dataIndex: "mincycle",
			            width:95,
			            sortable: true,
			            hidden:true,
			             align:'center'
		            }*//*, {
			            header: "最小安全周期",
			            dataIndex: "mincycle",
			            width:95,
			            sortable: true,
			            hidden:true,
			             align:'center'
		            }*/, {
			            header: "最小安全库存",
			            dataIndex: "minstore",
			            width:95,
			            sortable: true,
			            hidden:true,
			             align:'center'
		            }, {
			            header: "最大安全库存",
			            dataIndex: "maxstore",
			            width:95,
			            sortable: true,
			            hidden:true,
			             align:'center'
		            }, {
			            header: "月用量",
			            dataIndex: "monthuse",
			            width:95,
			            sortable: true,
			            hidden:true,
			            hidden:true,
			             align:'center'
		            }, {
			            header: "单次采购量",
			            dataIndex: "singlepur",
			            width:95,
			            sortable: true,
			            hidden:true,
			            hidden:true,
			             align:'center'
		            },{
			            header: "ID",
			            dataIndex: "id",
			            width:95,
			            sortable: true,
			            hidden:true,
			            hidden:true,
			             align:'center'
		            }, {
			            header: "库名",
			            dataIndex: "kuname",
			            width:95,
			            sortable: true,
			             align:'center'
		            }, {
			            header: "货架号",
			            dataIndex: "shelfnum",
			            width:95,
			            sortable: true,
			             align:'center'
		            }, {
			            header: "入库日期",
			            dataIndex: "indate",
			            width:95,
			            sortable: true,
			            renderer : Ext.util.Format.dateRenderer('Y-m-d'),
			            align:'center'
		            }, {
			            header: "入库数量",
			            dataIndex: "incount",
			            width:95,
			            sortable: true,
			             align:'center'
		            }, {
			            header: "出库数量",
			            dataIndex: "outcount",
			            width:95,
			            sortable: true,
			             align:'center'
		            }, {
			            header: "移库数量",
			            dataIndex: "changecount",
			            width:95,
			            sortable: true,
			             align:'center'
		            },{
			            header: "复检入库",
			            dataIndex: "state",
			            width:95,
			            sortable: true,
			            align:'center'
		            },{
			            header: "有效期",
			            dataIndex: "valiadate",
			            width:95,
			            sortable: true,
			            renderer : Ext.util.Format.dateRenderer('Y-m-d'),
			            align:'center'
		            }, {
			            header: "报废数量",
			            dataIndex: "nowcount",
			            width:95,
			            sortable: true,
			             align:'center'
		            }/*,{
			            header: "领用数量",
			            dataIndex: "getcount",
			            width:95,
			            sortable: true,
			            editor:new Ext.form.TextField(),
			            align:'center'
		            }*/,{
			            header: "备注",
			            dataIndex: "remark",
			            width:95,
			            sortable: true,
			            hidden:true,
			             align:'center'
		            },{
			            header: "报废日期",
			            dataIndex: "deletedate",
			            width:95,
			            sortable: true,
			            editor:new Ext.form.DateField({format: 'Y-n-j'}),
			            renderer : Ext.util.Format.dateRenderer('Y-m-d'),
			             align:'center'
		            }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        autoScroll:true,
		        stateId:'filetable',
		        renderTo: document.body
    });
    

      //组装界面
    new Ext.Viewport({
		layout:"border",
		autoHeight:false,
		items:[search,
				{
				region:'center',
				layout:'fit',
				title:"查询结果",
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:['-',
							{ 								
					            id:'app',
					            text:"报废", 
					            handler:deleteFn,
					            iconCls:"update"
					        },'-',{								
					            id:'detail',
					            text:"明细", 
					            handler:detailFn,
					            iconCls:"detail"
				            },'-']
		        }),
		        /*bbar: new Ext.PagingToolbar({
		        	id:"pagebar",
				    pageSize:limit,
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				}),*/
				items:grid
		}]
	});  
	function deleteFn(){
		var record = sm.getSelections();
		if(record.length==0){
			Ext.MessageBox.alert('提示',"请选择要报废的材料");
		}else{
		for(var i=0;i<record.length;i++){
			if(record[i].get("deletedate")==""){
				Ext.MessageBox.alert('提示',"请填写报废日期");
			}/*else if(record[i].get("nowcount")<record[i].get("deletecount")){
				Ext.MessageBox.alert('提示',"出库数量:"+record[i].get("deletecount")+"不能大于在库量:"+record[i].get("nowcount"))
			}*/else{
			Ext.showProgressDialog();
			var id = record[i].get("id");
			var ordernum = record[i].get("ordernum");
			var kuname = record[i].get("kuname");
			var shelfnum = record[i].get("shelfnum");
			var deletecount = record[i].get("nowcount");
			var deletedate = record[i].get("deletedate");
			
			var year = deletedate.getFullYear();//获得年
			var month = deletedate.getMonth();//获得月
			var day = deletedate.getDate();//获得日
			var month1 = parseInt(month)+1;//月份加1
			var deletedate1 = year+"-"+month1+"-"+day;//组合年-月-日格式一遍传入后台供数据库使用
			//alert(outdate1);
			//outdate.format("Y-m-d");
			/*alert(outdate.getFullYear());
			alert(outdate.toString());SSSSs
			alert(outdate.getMonth());
			alert(outdate.getDate());
			alert(outdate.toLocaleTimeString());*/
			Ext.Ajax.request({
				url:'logicmaterial_delete.jsp',
				params:{
					type:'DELETE',
					ordernum:ordernum,
					kuname:kuname,
					shelfnum:shelfnum,
					deletecount:deletecount,
					deletedate:deletedate1,
					id:id
				},
				success:function(resp){
					Ext.hideProgressDialog();
					if(parseInt(resp.responseText)){
						Ext.MessageBox.alert('提示',"报废成功");
						ds.load();
					}else{
						Ext.MessageBox.alert('提示',"报废失败");
				}
				
			},
			failure:function(){
				Ext.hideProgressDialog();
				Ext.MessageBox.alert('提示',"数据传送过程中出现问题,请稍后再试");
			}
		});
	}
		}
	}
	}
	
	//定义明细formpanel
	var formpanel = new Ext.form.FormPanel({
		id:'formpanel',
        labelWidth:85, // label settings here cascade unless overridden
        width:453,
        height:270,
        columnWidth:1,
        labelWidth: 70,
        layout:'form',
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',
        items:[{
        	layout:'column',
        	items:[{
        		columnWidth:.5,
                layout:'form',
                labelAlign:"right",
                   items:[
                   	{xtype:'textfield',
                    fieldLabel: '物资编码',
                    //name: 'mnum',
                    id: 'mnum',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '类别',
                    //name: 'classname',
                    id: 'classname',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '名称',
                   // name: 'name',
                    id: 'name',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '型号',
                    //name: 'modelnum',
                    id: 'modelnum',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '规格',
                    //name: 'spec',
                    id: 'spec',
                    readOnly:true,
		            width:125}/*,{
                    xtype:'textfield',
                    fieldLabel: '预警天数',
                   // name: 'prewarndate',
                    id: 'prewarndate',
                    readOnly:true,
		            width:125}*/,{
                    xtype:'textfield',
                    fieldLabel: '厂商',
                    name: 'company',
                    id: 'company',
                    readOnly:true,
		            width:125}]
        	},{
        	    columnWidth:.5,
                layout:'form',
                labelAlign:"right",	
                labelWidth:105,
                items:[
                	{xtype:'textfield',
                    fieldLabel: '保质期',
                    //name: 'guaranteedate',
                    id: 'guaranteedate',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '月用量',
                   // name: 'monthuse',
                    id: 'monthuse',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '单次采购量',
                    //name: 'singlepur',
                    id: 'singlepur',
                    readOnly:true,
		            width:125}/*,{
                    xtype:'textfield',
                    fieldLabel: '最小安全周期',
                    //name: 'mincycle',
                    id: 'mincycle',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '最大安全周期',
                    //name: 'maxcycle',
                    id: 'maxcycle',
                    readOnly:true,
		            width:125}*/,{
                    xtype:'textfield',
                    fieldLabel: '最小安全库存',
                    //name: 'minstore',
                    id: 'minstore',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '最小安全库存',
                    //name: 'maxstore',
                    id: 'maxstore',
                    readOnly:true,
		            width:125},{
                    xtype:'textfield',
                    fieldLabel: '计量单位',
                   // name: 'measure',
                    id: 'measure',
                    readOnly:true,
		            width:125}/*,{
                    xtype:'textfield',
                    fieldLabel: '领用数量',
                    //name: 'maxstore',
                    id: 'outcount',
                    readOnly:true,
		            width:125}*/]
        	},{
        	columnWidth:1,					              
            layout: 'form',
            labelAlign:"right",
            bodyStyle:'padding:5px 0px 0',
            items: [{
                    xtype:'textarea',
                    fieldLabel: '备注',
                    name: 'remark',
                    id: 'remark',
                    width:396,
                    height:80			                   
				    }]
        	}]}]
	});
	
	//将formpanel渲染到windows上
	var window = new Ext.Window({
		id:'win',
		modal:true,                                         
        width:540,
        height:360,
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
                		items:[formpanel,
                			{
								columnWidth: .10,
								bodyStyle:'padding:15px 0px 0',
								border:false
							}]
	        		})
                		
		],
		buttons:[{
			id:'cancle',
            text:'返回',
            handler:cancleFn
		}
            ]
	});
	//返回函数
	function cancleFn(){
		window.hide();
	}
	//明细函数
 	function detailFn(){
		var record = sm.getSelections();
		if(record.length ==0){
			alert("请选择要查看的材料");
			return;
		}else if(record.length!=1){
			alert("每次只能查看一条材料信息");
			return;
		}else{
			window.setTitle("<p align='center'>材料详细信息查看</P>");
			window.findById("mnum").value = record[0].get("mnum");
			window.findById("classname").value = record[0].get("classname");
			window.findById("name").value = record[0].get("name");
			window.findById("modelnum").value = record[0].get("modelnum");
			window.findById("spec").value = record[0].get("spec");
			/*window.findById("prewarndate").value = record[0].get("prewarndate");*/
			window.findById("company").value = record[0].get("company");
			window.findById("measure").value = record[0].get("measure");
			window.findById("guaranteedate").value = record[0].get("guaranteedate");
			window.findById("monthuse").value = record[0].get("monthuse");
			window.findById("singlepur").value = record[0].get("singlepur");
			/*window.findById("mincycle").value = record[0].get("mincycle");
			window.findById("maxcycle").value = record[0].get("maxcycle");*/
			window.findById("minstore").value = record[0].get("minstore");
			window.findById("maxstore").value = record[0].get("maxstore");
			window.findById("remark").value = record[0].get("remark");
			/*window.findById("outcount").value = record[0].get("deletecount");*/
			window.show();
		}
	}
});