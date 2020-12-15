var insertormodify=0;
Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=28;//每页显示记录条数
	
	
	//定义厂商查询页面
	var search=new Ext.Search({
		region:'north',
        renderTo:'querydiv',
        state:false,
        columnArray:Stringquery,
		orderArray:Stringorder,
        height:123,
        handler:customQueryFn
    });   
      
    var record_material= Ext.data.Record.create([    		
    		{name:'id'},
			{name:'leixing'},
			{name:'mingcheng'},
			{name:'bianhao'},
			{name:'company'},
			{name:'xinghao'},
			{name:'daoti'},
			{name:'tuceng'},
			{name:'zhijing'},
			{name:'zhijinggc'},
			{name:'daobing'},
			{name:'dbgg'},
			{name:'quanchang'},
			{name:'renchang'},
			{name:'renshu'},
			{name:'lx'},
			{name:'djjd'},
			{name:'maxv'},
			{name:'shjg1'},
			{name:'shjg2'},
			{name:'shjg3'},
			{name:'xlh'},
			{name:'daojiao'},
			{name:'qj'},
			{name:'hj'},
			{name:'zpj'},
			{name:'fpj'},
			{name:'djc'},
			{name:'rjbj'},
			{name:'chishu'},
			{name:'dtlx'}
		]); 
    
    //定义查询结果页面
	var hpObj={url:'logiccutter.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_material)
    });
   
	var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:false,
				loadMask:true,
		        columns: [sm,
		        	 {
			            header: "刀具型号",
			            dataIndex: "xinghao",
			             align:'center',
			             width:100,
			            sortable: true
			        },{
			            header: "刀具编号",
			            align:'center',
			            width:100,
			             hidden:true,
			            dataIndex: "bianhao",
			            sortable: true
		        	},{
			            header: "刀具类型",
			            dataIndex: "leixing",
			            width:100,
			            hidden:true,
			            align:'center',
			            sortable: true
			        },  {
			            header: "刀具系列号",
			            dataIndex: "xlh",
			             align:'center',
			             width:100,
			            sortable: true
			        },{
			            header: "刀具名称",
			             align:'center',
			             hidden:true,
			            dataIndex: "mingcheng",
			            width:100,
			            sortable: true
			        }, {
			            header: "生产厂商",
			            align:'center',
			             hidden:true,
			            dataIndex: "company",
			            hidden:true,
			            width:100,
			            sortable: true
			        },  {
			            header: "刀体材料",
			            dataIndex: "daoti",
			             align:'center',
			             width:110,
			            sortable: true
			        }, {
			            header: "涂层材料",
			             align:'center',
			              hidden:true,
			            dataIndex: "tuceng",
			             width:110,
			            sortable: true
			        }, {
			            header: "刀具直径",
			            dataIndex: "zhijing",
			             align:'center',
			             width:125,
			            sortable: true
			        }, {
			            header: "刀具直径公差",
			            dataIndex: "zhijinggc",
			            hidden:true,
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "刀柄直径",
			            dataIndex: "daobing",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "刀柄规格",
			            dataIndex: "dbgg",
			             align:'center',
			             width:125,
			            sortable: true
			        },{
			            header: "全长",
			            dataIndex: "quanchang",
			             align:'center',
			             width:100,
			            sortable: true
			        },{
			            header: "刃长",
			             align:'center',
			            dataIndex: "renchang",
			             width:100,
			            sortable: true
			        }/*,{
			            header: "刃数",
			             align:'center',
			              hidden:true,
			            dataIndex: "renshu",
			             width:110,
			            sortable: true
			        }*/,{
			            header: "齿数",
			             align:'center',
			              hidden:true,
			            dataIndex: "chishu",
			             width:110,
			            sortable: true
			        },{
			            header: "螺旋角",
			             align:'center',
			              hidden:true,
			            dataIndex: "lx",
			             width:110,
			            sortable: true
			        },{
			            header: "刀尖角度",
			             align:'center',
			              hidden:true,
			            dataIndex: "djjd",
			             width:110,
			            sortable: true
			        },{
			            header: "最大允许速度",
			             align:'center',
			              hidden:true,
			            dataIndex: "maxv",
			             width:110,
			            sortable: true
			        },{
			            header: "R角半径",
			             align:'center',
			              hidden:true,
			            dataIndex: "rjbj",
			             width:110,
			            sortable: true
			        },{
			            header: "适合加工材料1",
			             align:'center',
			            dataIndex: "shjg1",
			             width:110,
			            sortable: true
			        },{
			            header: "适合加工材料2",
			             align:'center',
			            dataIndex: "shjg2",
			             width:110,
			             hidden:true,
			            sortable:true
			        },{
			            header: "适合加工材料3",
			             align:'center',
			            dataIndex: "shjg3",
			             width:110,
			             hidden:true,
			            sortable: true
			        },{
			            header: "倒角",
			             align:'center',
			            dataIndex: "daojiao",
			             width:110,
			             hidden:true,
			            sortable: true
			        },{
			            header: "前角",
			             align:'center',
			            dataIndex: "qj",
			             width:110,
			             hidden:true,
			            sortable: true
			        },{
			            header: "后角",
			             align:'center',
			            dataIndex: "hj",
			             width:110,
			             hidden:true,
			            sortable: true
			        },{
			            header: "主偏角",
			             align:'center',
			            dataIndex: "zpj",
			             width:110,
			             hidden:true,
			            sortable: true
			        },{
			            header: "副偏角",
			             align:'center',
			            dataIndex:"fpj",
			             width:110,
			             hidden:true,
			            sortable: true
			        },{
			            header: "刀颈长",
			             align:'center',
			            dataIndex: "djc",
			             width:110,
			             hidden:true,
			            sortable: true
			        }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        stateId:'filetable',
		        autoScroll:true
    });
    
    //定义刀具类型Combox
    var typedata = [['xidao','铣刀'],['zuantou','钻头'],['chedao','车刀'],['qita','其他类']];
    
    var storetype = new Ext.data.SimpleStore({fields:['typeid','type'],data:typedata});
    
    var typeCombox = new Ext.form.ComboBox({
    	id:"type",
    	name:"type",
    	fieldLabel:'刀具类型',
    	displayField:'type',
    	lazyRender:true,
    	emptyText:'请选择刀具类型',
    	width:100,
		store: storetype,
		typeAhead: true,
		triggerAction: 'all',
		selectOnFocus:true,
    //	allowBlank:false,
		mode:'local',
		editable:false	
    });
   
    //alert(typeCombox.getValue());
    
     keyBoard();				
    keyBoard.hide();  
    
   //定义修改、添加弹出panel
     var formpanel = new Ext.FormPanel({
        id:'formpanel',
        labelWidth: 85, // label settings here cascade unless overridden
        layout:"column",
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',
        items: [
         {
	    columnWidth:.5,					              
	    layout: 'form',
	    labelAlign:"right",
	    items: [{
	    	    columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[{
	                 	xtype:'textfield',
			            fieldLabel: '刀具型号',
			            name: 'xinghao',
			            width:100,
			            id: 'xinghao'
	                 }]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{
             	xtype:'textfield',
	            fieldLabel: '刀具编号',
	            name: 'bianhao',
	            width:100,
	            id: 'bianhao'
	            },
	             typeCombox
                ,{
	                 	xtype:'textfield',
			            fieldLabel: '刀具名称',
			            name: 'mingcheng',
			            width:100,
			            id: 'mingcheng'
	                 },{
                xtype:'textfield',
                fieldLabel: '刀具系列号',
                name: 'xlh',
                width:100,
                id: 'xlh'
            },{
               xtype:'textfield',
               fieldLabel: '涂层材料',
                name: 'tuceng',
                id:'tuceng',
                width:100
            },{
        	xtype: 'textfield',
        	fieldLabel: '刀具直径',
        	name: 'zhijing',
        	id: 'zhijing',
        	width:100
        },{
			xtype : 'textfield',
			id : 'zhijinggc',
			name:'zhijinggc',
			fieldLabel : '刀具直径公差',
			width:100
		},{
			xtype : 'textfield',
			id : 'daobing',
			fieldLabel : '刀柄直径',
			width:100
	       },{
			xtype : 'textfield',
			id : 'quanchang',
			fieldLabel : '全长',
			width:100
	       },{
			xtype : 'textfield',
			id : 'renchang',
			fieldLabel : '刃长',
			width:100
	       },{
			xtype : 'textfield',
			id : 'renshu',
			fieldLabel : '刃数',
			width:100
	       },{
			xtype : 'textfield',
			id : 'daojiao',
			fieldLabel : '倒角',
			width:100
	       },{
			xtype : 'textfield',
			id : 'qj',
			fieldLabel : '前角',
			width:100
	       },{
			xtype : 'textfield',
			id : 'hj',
			fieldLabel : '后角',
			width:100
	       }]},
		    {
		    columnWidth:.5,
		    layout: 'form',
		    labelAlign:"right",
		    items: [{
            xtype:'textfield',
            fieldLabel: '生产厂商',
            width:100,
            name: 'company',
            id: 'company'
    		},{
            xtype:'textfield',
            fieldLabel: '刀柄规格',
            width:100,
            name: 'dbgg',
            id: 'dbgg'
    		},{
            xtype:'textfield',
            fieldLabel: '螺旋角',
            width:100,
            name: 'lx',
            id: 'lx'
    		},{
            xtype:'textfield',
            fieldLabel: '齿数',
            width:100,
            name: 'chishu',
            id: 'chishu'
    		},{
        	xtype: 'textfield',
        	fieldLabel: '刀尖角度',
        	name: 'djjd',
        	id: 'djjd',
        	width: 100
        },{
			xtype : 'textfield',
			id : 'maxv',
			name:'maxv',
			fieldLabel : '最大允许速度',
			width:100
	      },{
			xtype : 'textfield',
			id : 'shjg1',
			name:'shjg1',
			fieldLabel : '适合加工材料1',
			width:100
	      },{
			xtype : 'textfield',
			id : 'shjg2',
			name:'shjg2',
			fieldLabel : '适合加工材料2',
			width:100
	      },{
			xtype : 'textfield',
			id : 'shjg3',
			name:'shjg3',
			fieldLabel : '适合加工材料3',
			width:100
	      },{
			xtype : 'textfield',
			id : 'daoti',
			name:'daoti',
			fieldLabel : '刀体材料',
			width:100
	      },{
			xtype : 'textfield',
			id : 'zp',
			name:'zp',
			fieldLabel : '主偏角',
			width:100
	      },{
			xtype : 'textfield',
			id : 'fp',
			name:'fp',
			fieldLabel : '副偏角',
			width:100
	      },{
			xtype : 'textfield',
			id : 'djc',
			name:'djc',
			fieldLabel : '刀颈长',
			width:100
	      },{
			xtype : 'textfield',
			id : 'rjbj',
			name:'rjbj',
			fieldLabel : 'R角半径',
			width:100
	      }
	      ]}, {
            columnWidth:1,
		    layout: 'form',
		    labelAlign:"right",
		    items:[{
		    	xtype:'textarea',
                fieldLabel: '备注',
                name: 'beizhu',
                id: 'beizhu',
                width:300,
                height:72
		    	}]
                }
         ]
     });
   
	//定义弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			modal:true,                                         
                width:550,
                height:600,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
				bodyBorder:true,
				resizable:false,
                items:[new Ext.Panel({
                		layout:'column',
                		columnWidth:1,
                		frame:true,
                		height:530,
                		items:[formpanel]
                })
                ],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                /*var type = typeCombox.getValue();
		                var mingcheng = Ext.get("mingcheng").dom.value;	*/
		                var bianhao = Ext.get("bianhao").dom.value;	
		                var xinghao = Ext.get("xinghao").dom.value;
		                /*if(type == ""){
		                	Ext.MessageBox.alert("提醒","刀具类型不能为空");
		                	return;
		                }
		                if(mingcheng == ""){
		                	Ext.MessageBox.alert("提醒","刀具名称不能为空");	
		                	return;
		                }*/if(xinghao == ""){
		                	Ext.MessageBox.alert("提醒","刀具型号不能为空");
		                	return;
		                }/*else if(xinghao == ""){
		                	Ext.MessageBox.alert("提醒","刀具型号不能为空");
		                	return;
		                }*/else{
		                	if(insertormodify == 0){
		                		addsaveFn();
		                	}else{
		                		editsaveFn();
		                	}
		                }
	                }},{ id:'cancelbtn',
						text: '取消',
						handler:cancelFn
	                },{ id:'returnbtn',
						text: '返回',
						handler:cancelFn
	                },{ id:'resetbtn',
						text: '重置',
						handler:resetFn
	                }]
    });
    
    
    var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
    var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});//按钮之间的竖线 
    var toolthree=new Ext.Toolbar.Separator({id:'toolthree'});//按钮之间的竖线    
    var toolfour=new Ext.Toolbar.Separator({id:'toolfour'});//按钮之间的竖线
    var toolfive=new Ext.Toolbar.Separator({id:'toolfive'});//按钮之间的竖线 
    var toolsix=new Ext.Toolbar.Separator({id:'toolsix'});//按钮之间的竖线    
          
  
     
    //组装页面
	new Ext.Viewport({
		layout:"border",
		id:"viewport",
		autoHeight:false,
		items:[search,
				{
				region:'center',
				layout:'fit',
				title:'查询结果',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:[toolone,{   
								id:'add',
					            text:"添加", 
					            handler:addFn ,
					            iconCls:"add",
					            tooltip:"添加一条刀具信息"
					        },tooltwo,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update",
					            tooltip:"修改一条刀具信息"
					        },toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete",
					            tooltip:"删除一条刀具信息"
			        		},toolfour,{ 
								id:'detail',
					            text:"明细", 
					            handler: viewFn ,
					            iconCls:"detail",
					            tooltip:"查看刀具信息明细"
			        		},toolfive]
		        }),
		        bbar: new Ext.PagingToolbar({
		        	id:'pagebar',
		        	afterPageText: '页共{0}页',
		        	beforePageText: '第',
				    pageSize:limit,
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				}),
				items:grid
		}]
	});
	
	//隐藏按钮、添加、修改、删除
	if(isStoreManager()||isTeamLeader()||isBoss()){
		toolone.hide();
	     tooltwo.hide();
	     toolthree.hide();
	    Ext.getCmp("add").hide();
	    Ext.getCmp("edit-btn").hide();
	    Ext.getCmp("del").hide();
	}
	//判断用户   
    //添加保存
     function addsaveFn(){
     	var cuttertype = typeCombox.getValue();
          Ext.showProgressDialog("正在提交数据，请稍候...");
          formpanel.form.doAction('submit',{
          url:'logiccutter.jsp',
          method:'post',
          params : { type : 'ADD',cuttertype:cuttertype},
          success:function(form,action){
          	if(action.result.msg=='ok'){
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","添加成功");
          		formpanel.form.reset();
          		win.hide();
          		customQueryFn();
          	}else if(action.result.msg=='repeat'){
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","刀具信息已经存在，不能重复添加");
          	}else{
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","添加失败");
          	}
          },
          failure:function(form,action){
          	Ext.hideProgressDialog();
          	Ext.MessageBox.alert("提示","服务器出现错误，请稍后再试");
          }
          });  
       }
       
	//修改页面显示
	function editwinFn(){
             
		var record =sm.getSelections();// 返回值为 Record 类型            	
		if(record.length==0){
           Ext.MessageBox.alert("提示",'请先选择要修改的刀具信息!');
            return;
        }else if(record.length!=1){
        	Ext.MessageBox.alert("提示",'每次只能修改一条刀具信息!');
            return;
        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>刀具信息修改</p>");
				win.show();//修改窗口显示
				Ext.getCmp("resetbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").hide();
				insertormodify = insertormodify+1;
				formpanel.form.reset();
				initializeFn();
			     if(keyBoardWin.isVisible()){
					  keyBoardWin.hide();  
					}
	       	  	 /*$('#zhijing').keypad();*/
	             $('#xlh').keypad();
	             $('#daobing').keypad();				           
	             $('#daojiao').keypad();
	             $('#qj').keypad();
	             $('#lxj').keypad();
	             $('#hj').keypad();
	             $('#zp').keypad();
	             $('#fp').keypad();
				Ext.getCmp("mingcheng").getEl().dom.readOnly  = false;
				Ext.getCmp("bianhao").getEl().dom.readOnly  = false;
				Ext.getCmp("company").getEl().dom.readOnly  = false;
				Ext.getCmp("xinghao").getEl().dom.readOnly  = false;
				Ext.getCmp("daoti").getEl().dom.readOnly  = false;
				Ext.getCmp("tuceng").getEl().dom.readOnly  = false;
				Ext.getCmp("zhijing").getEl().dom.readOnly  = false;
				Ext.getCmp("zhijinggc").getEl().dom.readOnly  = false;
				Ext.getCmp("daobing").getEl().dom.readOnly  = false;
				Ext.getCmp("dbgg").getEl().dom.readOnly  = false;
				Ext.getCmp("quanchang").getEl().dom.readOnly  = false;
				Ext.getCmp("renchang").getEl().dom.readOnly  = false;
				Ext.getCmp("renshu").getEl().dom.readOnly  = false;
				Ext.getCmp("lx").getEl().dom.readOnly  = false;
				Ext.getCmp("chishu").getEl().dom.readOnly  = false;
				Ext.getCmp("djjd").getEl().dom.readOnly  = false;
				Ext.getCmp("maxv").getEl().dom.readOnly  = false;
				Ext.getCmp("shjg1").getEl().dom.readOnly  = false;
				Ext.getCmp("shjg2").getEl().dom.readOnly  = false;
				Ext.getCmp("shjg3").getEl().dom.readOnly  = false;
				Ext.getCmp("xlh").getEl().dom.readOnly  = false;
				Ext.getCmp("daojiao").getEl().dom.readOnly  = false;
				Ext.getCmp("qj").getEl().dom.readOnly  = false;
				Ext.getCmp("hj").getEl().dom.readOnly  = false;
				Ext.getCmp("zp").getEl().dom.readOnly  = false;
				Ext.getCmp("fp").getEl().dom.readOnly  = false;
				Ext.getCmp("djc").getEl().dom.readOnly  = false;
				Ext.getCmp("rjbj").getEl().dom.readOnly  = false;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = false;
				
				
				}
			}       
    }
    
    	
 	//初始化赋值
	function initializeFn(){
		var record =sm.getSelections();//获得页面中选中信息
		var editid=record[0].json.id;
		//初始化设置
		var load=Ext.Ajax.request({
		 	url:'logiccutter.jsp',
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
		var objtp=Ext.util.JSON.decode(result.responseText);	
	 	typeCombox.setValue(objtp.leixing);
		win.findById("mingcheng").setValue(objtp.mingcheng);
		win.findById("djc").setValue(objtp.djc);
		win.findById("bianhao").setValue(objtp.bianhao);
		win.findById("company").setValue(objtp.company);
		win.findById("xinghao").setValue(objtp.xinghao);
		win.findById("daoti").setValue(objtp.daoti);
		win.findById("tuceng").setValue(objtp.tuceng);
		win.findById("zhijing").setValue(objtp.zhijing);
		win.findById("zhijinggc").setValue(objtp.zhijinggc);
		win.findById("daobing").setValue(objtp.daobing);
		win.findById("dbgg").setValue(objtp.dbgg);
		win.findById("quanchang").setValue(objtp.quanchang);
		win.findById("renchang").setValue(objtp.renchang);
		win.findById("renshu").setValue(objtp.renshu);
		win.findById("lx").setValue(objtp.lx);
		win.findById("chishu").setValue(objtp.chishu);
		win.findById("djjd").setValue(objtp.djjd);
		win.findById("maxv").setValue(objtp.maxv);
		win.findById("shjg1").setValue(objtp.shjg1);
		win.findById("shjg2").setValue(objtp.shjg2);
		win.findById("shjg3").setValue(objtp.shjg3);
		win.findById("xlh").setValue(objtp.xlh);
		win.findById("daojiao").setValue(objtp.daojiao);
		win.findById("qj").setValue(objtp.qj);
		win.findById("hj").setValue(objtp.hj);
		win.findById("zp").setValue(objtp.zp);
		win.findById("fp").setValue(objtp.fp);
		win.findById("rjbj").setValue(objtp.rjbj);
		win.findById("beizhu").setValue(objtp.beizhu);
	}

	//用户修改函数.保存
	function editsaveFn(){
	           	Ext.showProgressDialog("正在提交修改数据，请稍候...");
	           	var record =sm.getSelections();
	           	var cuttertype = typeCombox.getValue();
	           	var editid=record[0].json.id;
				formpanel.form.doAction('submit',{
					 	url:'logiccutter.jsp',
					 	method:'post',
				 		params:{type:'EDIT',id:editid,cuttertype:cuttertype},
				 		success : function(form,action){
							 if (action.result.msg=='ok'){
									Ext.hideProgressDialog();
									win.hide();
									customQueryFn();
									Ext.MessageBox.alert('提示','已完成对刀具信息的修改！');
									insertormodify=0;
							}else if(action.result.msg=='repeat'){
									Ext.hideProgressDialog();
									Ext.MessageBox.alert("提示","该刀具信息已存在,不可重复!");
							}else{
									Ext.hideProgressDialog();
									Ext.MessageBox.alert("提示","修改失败!");
									}
						 	},
							failure : function(form,action){
								Ext.hideProgressDialog();
								Ext.MessageBox.alert("提示","服务器出现错误请稍后再试！");
							 }});
		}
	
	//取消函数
	function cancelFn(){
	if(insertormodify==1){insertormodify=0;}
    	win.hide();	
    	Ext.getCmp("mingcheng").getEl().dom.readOnly  = false;
				Ext.getCmp("bianhao").getEl().dom.readOnly  = false;
				Ext.getCmp("company").getEl().dom.readOnly  = false;
				Ext.getCmp("xinghao").getEl().dom.readOnly  = false;
				Ext.getCmp("daoti").getEl().dom.readOnly  = false;
				Ext.getCmp("tuceng").getEl().dom.readOnly  = false;
				Ext.getCmp("zhijing").getEl().dom.readOnly  = false;
				Ext.getCmp("zhijinggc").getEl().dom.readOnly  = false;
				Ext.getCmp("daobing").getEl().dom.readOnly  = false;
				Ext.getCmp("dbgg").getEl().dom.readOnly  = false;
				Ext.getCmp("quanchang").getEl().dom.readOnly  = false;
				Ext.getCmp("renchang").getEl().dom.readOnly  = false;
				Ext.getCmp("renshu").getEl().dom.readOnly  = false;
				Ext.getCmp("lx").getEl().dom.readOnly  = false;
				Ext.getCmp("chishu").getEl().dom.readOnly  = false;
				Ext.getCmp("djjd").getEl().dom.readOnly  = false;
				Ext.getCmp("maxv").getEl().dom.readOnly  = false;
				Ext.getCmp("shjg1").getEl().dom.readOnly  = false;
				Ext.getCmp("shjg2").getEl().dom.readOnly  = false;
				Ext.getCmp("shjg3").getEl().dom.readOnly  = false;
				Ext.getCmp("xlh").getEl().dom.readOnly  = false;
				Ext.getCmp("daojiao").getEl().dom.readOnly  = false;
				Ext.getCmp("qj").getEl().dom.readOnly  = false;
				Ext.getCmp("hj").getEl().dom.readOnly  = false;
				Ext.getCmp("zp").getEl().dom.readOnly  = false;
				Ext.getCmp("fp").getEl().dom.readOnly  = false;
				Ext.getCmp("djc").getEl().dom.readOnly  = false;
				Ext.getCmp("rjbj").getEl().dom.readOnly  = false;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = false;
    }
   	
	//查询函数（操作完毕二次刷新）
    function customQueryFn(){   
   	    hpObj.extraParams.filter=search.getFilter();
    	hpObj.extraParams.order=search.getOrder();   	
    	hpObj.extraParams.type="QUERY";
    	//var pagebar = Ext.getCmp("pagebar");
    	var obj={params:{start:start,limit:limit}};
   		ds.load(obj);
   		search.getQueryText();    		
	}
	
	
	//删除函数
	function delFn(){	
		var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
            alert('请先选择要删除的项!');
            return;
    	}
   		var id="";
		if(record.length!=0) {
	        var confirm=Ext.MessageBox.show({
					title:'提醒!',
					width:300,
					msg:"将删除所选刀具信息，确认吗？",
					buttons:{"ok":"确定","no":"取消"},
					icon:Ext.MessageBox.INFO,
					animEl:"test5",
					fn:function(btn){					              
						if (btn=='ok'){	
						   Ext.showProgressDialog();
						   	for(var i=0;i<record.length;i++){
								if(i!=0){
									id+=",";
								}
								id+="'"+record[i].json.id+"'";
							}					
							Ext.Ajax.request({
						   	url: 'logiccutter.jsp',
						   	params: {type:'DEL',id:id},
						   	success: function(resp){
						   		Ext.hideProgressDialog();
						   		if(parseInt(resp.responseText)){
							   		for(var i=0;i<record.length;i++){
										ds.remove(record[i]);
									}
									Ext.MessageBox.alert('提示','删除成功！');
									customQueryFn();
						   		}else{
						   			Ext.MessageBox.alert('提示','删除失败！');			   
						   		}						   
						   	},
						   	failure: function(){
						   		Ext.hideProgressDialog();
						   		Ext.MessageBox.alert('提示','服务器出现错误请稍后再试！');			   
						   	}
						});	    
				    	}
						else if (btn=='no'){
							
							}									
					}
			     });		  
		       };         
       	}
       	
       	//添加窗口显示
       	function addFn(){
       	  if(!win.isVisible()){
            formpanel.form.reset();
			win.setTitle("<p align='center'>刀具信息添加</p>")
			win.show();//添加窗口显示
			Ext.getCmp("resetbtn").show();
			Ext.getCmp("savebtn").show();
			Ext.getCmp("cancelbtn").show();
			Ext.getCmp("returnbtn").hide();
		 }
		 
		 if(keyBoardWin.isVisible()){
				  keyBoardWin.hide();  
				}
       	  	 /*$('#zhijing').keypad();*/
             $('#xlh').keypad();
             $('#daobing').keypad();				           
             $('#daojiao').keypad();
             $('#qj').keypad();
             $('#lxj').keypad();
             $('#hj').keypad();
             $('#zp').keypad();
             $('#fp').keypad();
		} 
       	
   
	  function resetFn(){
	     formpanel.form.reset();
	      }
	      
	 //明细窗口显示
	  function viewFn(){
	    	var record =sm.getSelections();// 返回值为 Record 类型            	
			if(record.length==0){
	            alert('请先选择要查看的刀具信息!');
	            return;
	        }else if(record.length!=1){
	        	alert('每次只能查看一条刀具信息!');
	            return;
	        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>刀具信息明细</p>")
				win.show();//明细窗口显示
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").hide();
				Ext.getCmp("cancelbtn").hide();
				Ext.getCmp("returnbtn").show();
				
				formpanel.form.reset();
				initializeFn();
		     
				Ext.getCmp("mingcheng").getEl().dom.readOnly  = true;
				Ext.getCmp("bianhao").getEl().dom.readOnly  = true;
				Ext.getCmp("company").getEl().dom.readOnly  = true;
				Ext.getCmp("xinghao").getEl().dom.readOnly  = true;
				Ext.getCmp("daoti").getEl().dom.readOnly  = true;
				Ext.getCmp("tuceng").getEl().dom.readOnly  = true;
				Ext.getCmp("zhijing").getEl().dom.readOnly  = true;
				Ext.getCmp("zhijinggc").getEl().dom.readOnly  = true;
				Ext.getCmp("daobing").getEl().dom.readOnly  = true;
				Ext.getCmp("dbgg").getEl().dom.readOnly  = true;
				Ext.getCmp("quanchang").getEl().dom.readOnly  = true;
				Ext.getCmp("renchang").getEl().dom.readOnly  = true;
				Ext.getCmp("renshu").getEl().dom.readOnly  = true;
				Ext.getCmp("lx").getEl().dom.readOnly  = true;
				Ext.getCmp("chishu").getEl().dom.readOnly  = true;
				Ext.getCmp("djjd").getEl().dom.readOnly  = true;
				Ext.getCmp("maxv").getEl().dom.readOnly  = true;
				Ext.getCmp("shjg1").getEl().dom.readOnly  = true;
				Ext.getCmp("shjg2").getEl().dom.readOnly  = true;
				Ext.getCmp("shjg3").getEl().dom.readOnly  = true;
				Ext.getCmp("xlh").getEl().dom.readOnly  = true;
				Ext.getCmp("daojiao").getEl().dom.readOnly  = true;
				Ext.getCmp("qj").getEl().dom.readOnly  = true;
				Ext.getCmp("hj").getEl().dom.readOnly  = true;
				Ext.getCmp("zp").getEl().dom.readOnly  = true;
				Ext.getCmp("fp").getEl().dom.readOnly  = true;
				Ext.getCmp("djc").getEl().dom.readOnly  = true;
				Ext.getCmp("rjbj").getEl().dom.readOnly  = true;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = false;
				}
			} 
	    } 
	});
	

	

