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
   
	//定义添加窗口用的机床型号combox	
	var machine_com= Ext.data.Record.create([    		
            		{name:'id'},
        			{name:'xinghao'}			
        			 ]);  
	//
	 var storeCom = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/bscinfmod/bscinfmilling/logicmachine.jsp?type=QUERYALL"}), // 数据源
	     reader: new Ext.data.JsonReader({
	         id:'id',
		     root:"filedata"
		 //    fields:[{name: 'id'},{name: 'company'}]    // 解析
		       },machine_com),
		     remoteSort: true
	     });
	 storeCom.load();
             
    	 var ComboBoxMachine=new Ext.form.ComboBox({
				fieldLabel:'机床型号',
		     	name:'xinghao',
				id:"xinghao",
				store:storeCom, 
				displayField:'xinghao',
				valueField:'id',
				triggerAction: 'all',
				width:90,
				emptyText:'请选择机床型号',
			//	forceSelection:true,
				allowBlank:true,
				mode:'local',
				typeAhead: true,
				editable:true
    	});  
  
	//定义添加窗口材料牌号combox
    	 var machine_com= Ext.data.Record.create([    		
		              		{name:'id'},
		          			{name:'paihao'}			
		          		   ]); 
    	 var materialCom = new Ext.data.Store ({
    	     proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/bscinfmod/bscinfmilling/logicmaterial.jsp?type=QUERYALL"}), // 数据源
    	     reader: new Ext.data.JsonReader({
    	         id:'id',
    		     root:"filedata"
    		       },machine_com),
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
				width:90,
				emptyText:'请选择材料牌号',
			//	forceSelection:true,
				allowBlank:true,
				mode:'local',
				typeAhead: true,
				editable:true
	}); 	
	
	//定义刀具厂商combox
    var storecompany = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/bscinfmod/bscinfmilling/logiccutter.jsp?type=QUERYCOM"}),//刀具厂商数据源
	     reader: new Ext.data.JsonReader({
	         id:'company',
		     root:"filedata",
		     fields:[{name: 'company'}]    // 解析
		       }),
		     remoteSort: true
	     });   
    storecompany.load();//厂商信息加载
    
    var Combocompany=new Ext.form.ComboBox({
		fieldLabel:'刀具厂商',
		name:'company',
		displayField:'company',
		store: storecompany,
		triggerAction: 'all',
		lazyRender:true,
		width:90,
		listeners:{
			 select: function(){
			 var company = Combocompany.getValue().toString();
			 storexiliehao.load({params:{company:Combocompany.getValue()}});
			 }
			},
		typeAhead: true,
		selectOnFocus:true,
		emptyText:'选择刀具厂商',
		forceSelection:true,
		allowBlank:true,
		mode:'local',
		typeAhead: true,
		//transform:'AList',
		editable:true		
		});
		
		Combocompany.on('blur',function(){   
              this.setValue(this.el.dom.value);   
           }); 	          
           
	//定义刀具系列号combox
	var storexiliehao = new Ext.data.Store ({
         proxy: new Ext.data.HttpProxy({url:"/gsde/web/sms/bscinfmod/bscinfmilling/logiccutter.jsp?type=XLH"
                                       }),//刀具厂商数据源
	     reader: new Ext.data.JsonReader({
	         id:'xlh',
		     root:"filedata",
		     fields:[{name: 'xlh'}]    // 解析
		       }),
		     remoteSort: true
	     });
    var Comboxlh=new Ext.form.ComboBox({
		fieldLabel:'刀具系列号',
		name:'xlh',
		id:"xlh",
		displayField:'xlh',
		store: storexiliehao,
		triggerAction: 'all',
		lazyRender:true,
		width:90,
		listeners:{
			 select: function(){
			 var xlh = Comboxlh.getValue().toString();
			 //alert(xlh);
			 storezhijing.load({params:{xlh:Comboxlh.getValue(),company:Combocompany.getValue()}});
			 }
			},
		readOnly: true,
		typeAhead: true,
		selectOnFocus:true,
		emptyText:'选择系列号',
		allowBlank:true,
		mode:'local',
		editable:true		
		});
		
	//定义刀具直径combox
    var zhijingobj={url:'/gsde/web/sms/bscinfmod/bscinfmilling/logiccutter.jsp',method: 'POST',extraParams:{type:"ZHIJING"}};
    var storezhijing = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy(new Ext.data.Connection(zhijingobj)), // 数据源type=QUERYALLNUM
	     reader: new Ext.data.JsonReader({
	         id:'zhijing',
		     root:"filedata",
		     fields:[{name: 'zhijing'}]    // 解析
		       }),
		     remoteSort: true
	     });	
    var Combozhijing=new Ext.form.ComboBox({
		fieldLabel:'刀具直径',
		name:'zhijing',
		id:"zhijing",		
		displayField:'zhijing',
		store: storezhijing,
		triggerAction: 'all',
		lazyRender:true,
		listeners:{
			 select: function(){
			 var zhijing = Combozhijing.getValue().toString();
			 //alert(xlh);
			 rjbjstore.load({params:{xlh:Comboxlh.getValue(),company:Combocompany.getValue(),zhijing:zhijing}});
			 }
			},
		width:90,
		readOnly: true,
		typeAhead: true,
		selectOnFocus:true,
		emptyText:'请选择直径',
		allowBlank:true,
		mode:'local',
		editable:true		
		});  

   //定义R角半径；刀具厂商+刀具系列号+刀具直径+R角半径 对应一种类型的刀，用户选择厂商后，检索该厂商下的所有刀具系列号，选择系列号
    //后，检索在该系列号下的所有铣刀直径
    var rjbjobj={url:'/gsde/web/sms/bscinfmod/bscinfmilling/logiccutter.jsp',method: 'POST',extraParams:{type:'RJBJ'}};
    var rjbjstore = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy(new Ext.data.Connection(rjbjobj)), // 数据源type=QUERYALLNUM
	     reader: new Ext.data.JsonReader({
	         id:'rjbj',
		     root:"filedata",
		     fields:[{name: 'rjbj'}]    // 解析
		       }),
		     remoteSort: true
	     });	
    var Comborjbj=new Ext.form.ComboBox({
		fieldLabel:'R角半径',
		name:'rjbj',
		id:"rjbj",				
		displayField:'rjbj',
		store: rjbjstore,
		triggerAction: 'all',
		lazyRender:true,
		width:90,
		readOnly: true,
		typeAhead: true,
		selectOnFocus:true,
		emptyText:'请选择R角半径',
		allowBlank:true,
		mode:'local',
		editable:true		
		});
		//定义加工方式combox
		var jjfs = [['cjg','粗加工'],['bjjg','半精加工'],['jjg','精加工']];
    
    var storetype = new Ext.data.SimpleStore({fields:['typeid','type'],data:jjfs});
    
    var jjfsCombox = new Ext.form.ComboBox({
    	id:"jgfs",
    	name:"jgfs",
    	fieldLabel:'加工方式',
    	displayField:'type',
    	lazyRender:true,
    	emptyText:'请选择加工方式',
    	width:90,
		store: storetype,
		typeAhead: true,
		triggerAction: 'all',
		selectOnFocus:true,
    //	allowBlank:false,
		mode:'local',
		editable:false	
    });
    //****************************************************************************//
    var record_cutpara= Ext.data.Record.create([    		
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
    
    //定义查询结果页面数据源
	var hpObj={url:'logiccutpara.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_cutpara)
    });

	var sm = new Ext.grid.CheckboxSelectionModel();
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:false,
				loadMask:true,
		        columns: [sm,
		        	{header: "铣削参数编号",dataIndex: "bianhao",width:90,hidden:true,align:'center',sortable: true},
		        	{header: "机床型号",align:'center',dataIndex: "jcxh",width:90,sortable: true}, 
		        	{header: "材料牌号",hidden:true,align:'center',width:90,dataIndex: "clmc",sortable: true},
		        	{header: "刀具型号",align:'center',dataIndex: "djxh",width:100,sortable: true}, 
		        	{header: "刀具直径(mm)",align:'center',dataIndex: "zhijing",width:90,sortable: true},
		        	{header: "刀具厂商",align:'center',width:90,dataIndex: "company",sortable: true},
		        	{header: "刀具系列号",align:'center',hidden:true,dataIndex: "xlh",width:100,sortable: true},
		        	{header: "刀具名称",dataIndex: "djmc",hidden:true,align:'center',width:100,sortable: true},
		        	{header: "R角半径(mm)",dataIndex: "rjbj",align:'center',width:100,sortable: true},
		        	{header: "加工方式",align:'center',width:90,dataIndex: "jgfs",sortable: true},
		        	{header: "加工余量",align:'center',width:90,dataIndex: "jgyl",sortable: true},
		        	{header: "粗糙度等级 ",align:'center',dataIndex: "ccddj",width:110,sortable: true},
		        	{header: "切深(mm)",dataIndex: "ap",align:'center',width:110,sortable: true},
		        	{header: "切宽(mm)",align:'center',dataIndex: "ae",width:110,sortable: true},
		        	{header: "进给速度(mm/min)",dataIndex: "jgsd",align:'center',width:125,sortable: true},
		        	{header: "切削速度(m/min)",dataIndex: "qxsd",align:'center',width:125,sortable: true }, 
		        	{header: "主轴转速(r/min)",dataIndex: "zzzs",align:'center',width:125,sortable: true},
		        	{header: "每齿进给量(mm/齿)",dataIndex: "mcjg",align:'center',width:125,sortable: true},
		        	{header: "悬伸量(mm)",dataIndex: "xsl",align:'center',width:125,sortable: true},
		        	{header: "刀套类型",dataIndex: "dtlx",align:'center',width:100,sortable: true},
		        	{header: "冷却液",align:'center',dataIndex: "lqy",width:100,sortable: true},
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
		        	{header: "应用状态 ",align:'center',dataIndex: "yyzt",width:110,sortable: true},
		        	{header: "刀具寿命 ",align:'center',hidden:true,dataIndex: "djsm",width:110,sortable: true},
		        	{header: "备注 ",align:'center',hidden:true,dataIndex: "beizhu",width:110,sortable: true}],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        stateId:'filetable',
		        autoScroll:true
    });  

   //修改、添加弹出panel
     var formpanel = new Ext.FormPanel({
        id:'formpanel',
        labelWidth:95, // label settings here cascade unless overridden
        layout:"column",
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',
        items: [
         {
	    columnWidth:.34,					              
	    layout: 'form',
	    labelAlign:"right",
	    items: [{
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[ComboBoxMachine]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[ComboBoxMaterial]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[Combocompany]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[Comboxlh]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[Combozhijing]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[Comborjbj]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{//刀具结束
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[jjfsCombox]
                },{
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
                border:false
                }]
            },{//刀具结束
	    	columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[{
			        	xtype: 'textfield',
			        	fieldLabel: '加工余量',
			        	name: 'jgyl',
			        	id: 'jgyl',
			        	width: 90
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
	            fieldLabel: '切削深度(mm)',
	            name: 'ap',
	            width:90,
	            id: 'ap'
	                 },{
                xtype:'textfield',
                fieldLabel: '切削宽度(mm)',
                name: 'ae',
                width:90,
                id: 'ae'
            },{
            xtype:'textfield',
            fieldLabel: '主轴转速(r/min)',
            width:90,
            name: 'zzzs',
            id: 'zzzs'
    		},{
        	xtype: 'textfield',
        	fieldLabel: '变形(mm)',
        	name: 'bx',
        	id: 'bx',
        	width: 90
        },{
	                 	xtype:'textfield',
			            fieldLabel: '铣削编号',
			            name: 'bianhao',
			            width:85,
			            id: 'bianhao'
	                 }
          ]},
		    {columnWidth:.33,
		    layout: 'form',
		    labelAlign:"right",
		    items: [{
            xtype:'textfield',
            fieldLabel: '悬伸量(mm)',
            width:90,
            name: 'xsl',
            id: 'xsl'
    		},{
            xtype:'textfield',
            fieldLabel: '每齿进给量(mm/齿)',
            width:90,
            name: 'mcjg',
            id: 'mcjg'
    		},{
            xtype:'textfield',
            fieldLabel: '刀套类型',
            width:90,
            name: 'dtlx',
            id: 'dtlx'
    		},{
            xtype:'textfield',
            fieldLabel: '冷却液',
            width:90,
            name: 'lqy',
            id: 'lqy'
    		},{
        	xtype: 'textfield',
        	fieldLabel: '冷却方式',
        	name: 'lqfs',
        	id: 'lqfs',
        	width: 90
        },{
        	xtype: 'textfield',
        	fieldLabel: '材料去除率(cm3/min)',
        	name: 'clqcl',
        	id: 'clqcl',
        	width: 90
        },{
        	xtype: 'textfield',
        	fieldLabel: 'Fx(N)',
        	name: 'fx',
        	id: 'fx',
        	width: 90
        },{
        	xtype: 'textfield',
        	fieldLabel: 'Fy(N)',
        	name: 'fy',
        	id: 'fy',
        	width: 90
        },{
        	xtype: 'textfield',
        	fieldLabel: 'Fz(N)',
        	name: 'fz',
        	id: 'fz',
        	width: 90
        },{
        	xtype: 'textfield',
        	fieldLabel: '功率(W)',
        	name: 'gl',
        	id: 'gl',
        	width: 90
        },{
        	xtype: 'textfield',
        	fieldLabel: '扭矩(N·m)',
        	name: 'nj',
        	id: 'nj',
        	width: 90
        }
                ]},
                {
            columnWidth:.33,
		    layout: 'form',
		    labelAlign:"right",
		    items:[{
		    	xtype:'textfield',
                fieldLabel: '夹持部弯曲应力(MPa)',
                name: 'jcbw',
                id: 'jcbw',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '韧部弯曲应力(MPa)',
                name: 'rbwq',
                id: 'rbwq',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '刀头应力(MPa)',
                name: 'dtyl',
                id: 'dtyl',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '粗糙度等级',
                name: 'ccddj',
                id: 'ccddj',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '切削温度(°C)',
                name: 'qxwd',
                id: 'qxwd',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '验证人',
                name: 'yzr',
                id: 'yzr',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '数据来源',
                name: 'sjly',
                id: 'sjly',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '铣削方式',
                name: 'xxfs',
                id: 'xxfs',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '刚性条件',
                name: 'gxtj',
                id: 'gxtj',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '应用状态',
                name: 'yyzt',
                id: 'yyzt',
                width:90
		    	},{
		    	xtype:'textfield',
                fieldLabel: '刀具寿命',
                name: 'djsm',
                id: 'djsm',
                width:90
		    	}]
                },{
            columnWidth:1,
		    layout: 'form',
		    labelAlign:"right",
		    items:[
		    	{
		    	xtype:'textarea',
                fieldLabel: '备注',
                name: 'beizhu',
                id: 'beizhu',
                width:450,
                height:75
		    }]}
                ]
     });
   
	//定义弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			modal:true,                                         
                width:720,
                height:550,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
				bodyBorder:true,
				resizable:false,
                items:[new Ext.Panel({
                		layout:'column',
                		columnWidth:1,
                		frame:true,
                		height:500,
                		items:[formpanel]
                })
                ],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                	if(insertormodify == 0){
		                		addsaveFn();
		                	}else{
		                		editsaveFn();
		                	}
		                }
	                },{ id:'cancelbtn',
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
					            tooltip:"添加一条切削参数信息"
					        },tooltwo,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update",
					            tooltip:"修改一条切削参数信息"
					        },toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete",
					            tooltip:"删除一条切削参数信息"
			        		},toolfour,{ 
								id:'detail',
					            text:"明细", 
					            handler: viewFn ,
					            iconCls:"detail",
					            tooltip:"查看切削参数信息明细"
			        		},toolfive]
		        }),
		        bbar: new Ext.PagingToolbar({
		        	id:'pagebar',
				    pageSize:limit,
				    store:ds,
				    afterPageText: '页共{0}页',
		        	beforePageText: '第',
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				}),
				items:grid
		}]
	});
	
    //添加保存
     function addsaveFn(){
          Ext.showProgressDialog("正在提交数据，请稍候...");
          var jcxh = ComboBoxMachine.getRawValue();
          var jgyl = Ext.get("jgyl").dom.value.trim();
          //alert(jcxh);
          var mingcheng = ComboBoxMaterial.getRawValue();
          //alert(mingcheng);
          var djcs = Combocompany.getRawValue();
           //alert(djcs);
          var djxlh = Comboxlh.getRawValue();
          //alert(djxlh);
          var zhijing = Combozhijing.getRawValue();
          //alert(zhijing);
          var rjbj = Comborjbj.getRawValue();
          //alert(rjbj);
          var jjfs = jjfsCombox.getRawValue();
          //alert(jjfs);
          var mcjg = Ext.get("mcjg").dom.value.trim();
          var zzzs = Ext.get("zzzs").dom.value.trim();
         if(jcxh==""){
          	Ext.MessageBox.alert('提示','机床型号不能为空');
          	return;
          }else if(mingcheng==""){
          	Ext.MessageBox.alert('提示','材料名称不能为空');
          	return;
          }else if(djcs==""){
          	Ext.MessageBox.alert('提示','刀具厂商不能为空');
          	return;
          }else if(djxlh==""){
          	Ext.MessageBox.alert('提示','刀具系列号不能为空');
          	return;
          }else if(zhijing==""){
          	Ext.MessageBox.alert('提示','刀具直径不能为空');
          	return;
          }else if(rjbj==""){
          	Ext.MessageBox.alert('提示','刀具R角半径不能为空');
          	return;
          }else if(jgyl==""){
          	Ext.MessageBox.alert('提示','加工余量不能为空');
          	return;
          }else if(mcjg==""){
            	Ext.MessageBox.alert('提示','每齿进给量不能为空');
              	return;
          }else if(zzzs==""){
          	Ext.MessageBox.alert('提示','主轴转速不能为空');
          	return;
          }
          formpanel.form.doAction('submit',{
          url:'logiccutpara.jsp',
          method:'post',
          params : { type : 'ADD',jcxh:jcxh,mingcheng:mingcheng,djcs:djcs,djxlh:djxlh,zhijing:zhijing,rjbj:rjbj,mcjg:mcjg,zzzs:zzzs},
          success:function(form,action){
          	if(action.result.msg=='ok'){
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","添加成功");
          		formpanel.form.reset();
          		win.hide();
          		customQueryFn();
          	}else if(action.result.msg=='repeat'){
          		Ext.hideProgressDialog();
          		Ext.MessageBox.alert("提示","切削参数已经存在，不能重复添加");
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
           Ext.MessageBox.alert("提示",'请先选择要修改的切削参数!');
            return;
        }else if(record.length!=1){
        	Ext.MessageBox.alert("提示",'每次只能修改一条切削参数信息!');
            return;
        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>切削参数信息修改</p>");
				win.show();//修改窗口显示
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").hide();
				insertormodify = insertormodify+1;
				formpanel.form.reset();
				Ext.getCmp("bianhao").getEl().dom.readOnly  = false;
				Ext.getCmp("ap").getEl().dom.readOnly  = false;
				Ext.getCmp("jgyl").getEl().dom.readOnly  = false;
				Ext.getCmp("ae").getEl().dom.readOnly  = false;				
				Ext.getCmp("zzzs").getEl().dom.readOnly  = false;
				Ext.getCmp("mcjg").getEl().dom.readOnly  = false;
				Ext.getCmp("xsl").getEl().dom.readOnly  = false;
				Ext.getCmp("dtlx").getEl().dom.readOnly  = false;
				Ext.getCmp("lqy").getEl().dom.readOnly  = false;
				Ext.getCmp("lqfs").getEl().dom.readOnly  = false;
				Ext.getCmp("clqcl").getEl().dom.readOnly  = false;
				Ext.getCmp("fx").getEl().dom.readOnly  = false;
				Ext.getCmp("fy").getEl().dom.readOnly  = false;
				Ext.getCmp("fz").getEl().dom.readOnly  = false;
				Ext.getCmp("gl").getEl().dom.readOnly  = false;
				Ext.getCmp("nj").getEl().dom.readOnly  = false;
				Ext.getCmp("bx").getEl().dom.readOnly  = false;
				Ext.getCmp("jcbw").getEl().dom.readOnly  = false;
				Ext.getCmp("rbwq").getEl().dom.readOnly  = false;
				Ext.getCmp("dtyl").getEl().dom.readOnly  = false;
				Ext.getCmp("ccddj").getEl().dom.readOnly  = false;
				Ext.getCmp("qxwd").getEl().dom.readOnly  = false;
				Ext.getCmp("yzr").getEl().dom.readOnly  = false;
				Ext.getCmp("sjly").getEl().dom.readOnly  = false;
				Ext.getCmp("xxfs").getEl().dom.readOnly  = false;
				Ext.getCmp("gxtj").getEl().dom.readOnly  = false;
				Ext.getCmp("yyzt").getEl().dom.readOnly  = false;
				Ext.getCmp("djsm").getEl().dom.readOnly  = false;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = false;
				initializeFn();
				}
			}       
    }
    
    	
 	//初始化赋值
	function initializeFn(){
		var record =sm.getSelections();//获得页面中选中信息
		var editid=record[0].json.id;
		//初始化设置
		win.findById("bianhao").setValue(record[0].json.bianhao);
		ComboBoxMachine.setValue(record[0].json.jcxh);
		ComboBoxMaterial.setValue(record[0].json.clmc);
		Combocompany.setValue(record[0].json.company);
		Comboxlh.setValue(record[0].json.xlh);
		Combozhijing.setValue(record[0].json.zhijing);
		Comborjbj.setValue(record[0].json.rjbj);
		jjfsCombox.setValue(record[0].json.jgfs);
		win.findById("ap").setValue(record[0].json.ap);
		win.findById("jgyl").setValue(record[0].json.jgtz);
		win.findById("ae").setValue(record[0].json.ae);
		win.findById("zzzs").setValue(record[0].json.zzzs);
		win.findById("mcjg").setValue(record[0].json.mcjg);
		win.findById("xsl").setValue(record[0].json.xsl);
		win.findById("dtlx").setValue(record[0].json.dtlx);
		win.findById("lqy").setValue(record[0].json.lqy);
		win.findById("lqfs").setValue(record[0].json.lqfs);
		win.findById("clqcl").setValue(record[0].json.clqcl);
		win.findById("fx").setValue(record[0].json.fx);
		win.findById("fy").setValue(record[0].json.fy);
		win.findById("fz").setValue(record[0].json.fz);
		win.findById("gl").setValue(record[0].json.gl);	
		win.findById("nj").setValue(record[0].json.nj);
		win.findById("bx").setValue(record[0].json.bx);
		win.findById("jcbw").setValue(record[0].json.djjc);
		win.findById("rbwq").setValue(record[0].json.djrb);
		win.findById("dtyl").setValue(record[0].json.dtyl);
		win.findById("ccddj").setValue(record[0].json.ccddj);
		win.findById("qxwd").setValue(record[0].json.qxwd);
		win.findById("jgyl").setValue(record[0].json.jgyl);
		win.findById("yzr").setValue(record[0].json.yzr);
		win.findById("sjly").setValue(record[0].json.sjly);
		win.findById("xxfs").setValue(record[0].json.xxfs);
		win.findById("gxtj").setValue(record[0].json.gxtj);
		win.findById("yyzt").setValue(record[0].json.yyzt);
		win.findById("djsm").setValue(record[0].json.djsm);
		win.findById("beizhu").setValue(record[0].json.beizhu);
	}

	//用户修改函数.保存
	function editsaveFn(){
	      Ext.showProgressDialog("正在提交修改数据，请稍候...");
          var jcxh = ComboBoxMachine.getRawValue();
          //alert(jcxh);
          var mingcheng = ComboBoxMaterial.getRawValue();
          //alert(mingcheng);
          var djcs = Combocompany.getRawValue();
           //alert(djcs);
          var djxlh = Comboxlh.getRawValue();
          //alert(djxlh);
          var zhijing = Combozhijing.getRawValue();
          //alert(zhijing);
          var rjbj = Comborjbj.getRawValue();
          //alert(rjbj);
          var jjfs = jjfsCombox.getRawValue();
          var jgyl = Ext.get("jgyl").dom.value.trim();
          //alert(jjfs);
          if(jcxh==""){
          	Ext.MessageBox.alert('提示','机床型号不能为空');
          	return;
          }else if(mingcheng==""){
          	Ext.MessageBox.alert('提示','材料名称不能为空');
          	return;
          }else if(djcs==""){
          	Ext.MessageBox.alert('提示','刀具厂商不能为空');
          	return;
          }else if(djxlh==""){
          	Ext.MessageBox.alert('提示','刀具系列号不能为空');
          	return;
          }else if(zhijing==""){
          	Ext.MessageBox.alert('提示','刀具直径不能为空');
          	return;
          }else if(rjbj==""){
          	Ext.MessageBox.alert('提示','刀具R角半径不能为空');
          	return;
          }else if(jgyl==""){
          	Ext.MessageBox.alert('提示','加工余量不能为空');
          	return;
          }
            var record =sm.getSelections();
	       	var editid=record[0].json.id;
			formpanel.form.doAction('submit',{
				 	url:'logiccutpara.jsp',
				 	method:'post',
			 		params:{type:'EDIT',id:editid,jcxh:jcxh,mingcheng:mingcheng,djcs:djcs,djxlh:djxlh,zhijing:zhijing,rjbj:rjbj},
			 		success : function(form,action){
						 if (action.result.msg=='ok'){
								Ext.hideProgressDialog();
								win.hide();
								customQueryFn();
								Ext.MessageBox.alert('提示','已完成对切削参数信息的修改！');
								insertormodify=0;
						}else if(action.result.msg=='repeat'){
								Ext.hideProgressDialog();
								Ext.MessageBox.alert("提示","该切削参数已存在,不可重复!");
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
    	formpanel.form.reset();
    	Ext.getCmp("ap").getEl().dom.readOnly  = false;
				Ext.getCmp("ae").getEl().dom.readOnly  = false;
				Ext.getCmp("zzzs").getEl().dom.readOnly  = false;
				Ext.getCmp("mcjg").getEl().dom.readOnly  = false;
				Ext.getCmp("xsl").getEl().dom.readOnly  = false;
				Ext.getCmp("dtlx").getEl().dom.readOnly  = false;
				Ext.getCmp("lqy").getEl().dom.readOnly  = false;
				Ext.getCmp("lqfs").getEl().dom.readOnly  = false;
				Ext.getCmp("jgyl").getEl().dom.readOnly  = false;
				Ext.getCmp("clqcl").getEl().dom.readOnly  = false;
				Ext.getCmp("fx").getEl().dom.readOnly  = false;
				Ext.getCmp("fy").getEl().dom.readOnly  = false;
				Ext.getCmp("fz").getEl().dom.readOnly  = false;
				Ext.getCmp("gl").getEl().dom.readOnly  = false;
				Ext.getCmp("nj").getEl().dom.readOnly  = false;
				Ext.getCmp("bx").getEl().dom.readOnly  = false;
				Ext.getCmp("jcbw").getEl().dom.readOnly  = false;
				Ext.getCmp("rbwq").getEl().dom.readOnly  = false;
				Ext.getCmp("dtyl").getEl().dom.readOnly  = false;
				Ext.getCmp("ccddj").getEl().dom.readOnly  = false;
				Ext.getCmp("qxwd").getEl().dom.readOnly  = false;
				Ext.getCmp("yzr").getEl().dom.readOnly  = false;
				Ext.getCmp("sjly").getEl().dom.readOnly  = false;
				Ext.getCmp("xxfs").getEl().dom.readOnly  = false;
				Ext.getCmp("gxtj").getEl().dom.readOnly  = false;
				Ext.getCmp("yyzt").getEl().dom.readOnly  = false;
				Ext.getCmp("djsm").getEl().dom.readOnly  = false;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = false;
    }
   	
	//查询函数（操作完毕二次刷新）
    function customQueryFn(){   
   	    hpObj.extraParams.filter=search.getFilter();
    	hpObj.extraParams.order=search.getOrder();   	
    	hpObj.extraParams.type="QUERY";
    	var obj={params:{start:start,limit:limit}};
   		ds.load(obj);
   		search.getQueryText();    		
	}
	
	//删除函数
	function delFn(){	
		var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
            Ext.MessageBox.alert('提示','请先选择要删除的项!');
            return;
    	}
   		var id="";
		if(record.length!=0) {
	        var confirm=Ext.MessageBox.show({
					title:'提醒!',
					width:300,
					msg:"将删除所选切削参数，确认吗？",
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
						   	url: 'logiccutpara.jsp',
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
				win.setTitle("<p align='center'>添加切削参数信息</p>")
				Ext.getCmp("resetbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").hide();
				win.show();//添加窗口显示
		 }
		}      	
   
	  function resetFn(){
	     formpanel.form.reset();
	      }
	      
	 //明细窗口显示
	  function viewFn(){
	    	var record =sm.getSelections();// 返回值为 Record 类型            	
			if(record.length==0){
	            alert('请先选择要查看的切削参数!');
	            return;
	        }else if(record.length!=1){
	        	alert('每次只能查看一条切削参数信息!');
	            return;
	        }else{ if(!win.isVisible()){
                win.setTitle("<p align='center'>切削参数信息明细</p>")
				win.show();//明细窗口显示
				Ext.getCmp("resetbtn").hide();
				Ext.getCmp("savebtn").hide();
				Ext.getCmp("cancelbtn").hide();
				Ext.getCmp("returnbtn").show();
				
				formpanel.form.reset();
				initializeFn();
		     
				Ext.getCmp("ap").getEl().dom.readOnly  = true;
				Ext.getCmp("ae").getEl().dom.readOnly  = true;
				Ext.getCmp("zzzs").getEl().dom.readOnly  = true;
				Ext.getCmp("jgyl").getEl().dom.readOnly  = true;
				Ext.getCmp("mcjg").getEl().dom.readOnly  = true;
				Ext.getCmp("xsl").getEl().dom.readOnly  = true;
				Ext.getCmp("dtlx").getEl().dom.readOnly  = true;
				Ext.getCmp("lqy").getEl().dom.readOnly  = true;
				Ext.getCmp("lqfs").getEl().dom.readOnly  = true;
				Ext.getCmp("clqcl").getEl().dom.readOnly  = true;
				Ext.getCmp("fx").getEl().dom.readOnly  = true;
				Ext.getCmp("fy").getEl().dom.readOnly  = true;
				Ext.getCmp("fz").getEl().dom.readOnly  = true;
				Ext.getCmp("gl").getEl().dom.readOnly  = true;
				Ext.getCmp("nj").getEl().dom.readOnly  = true;
				Ext.getCmp("bx").getEl().dom.readOnly  = true;
				Ext.getCmp("jcbw").getEl().dom.readOnly  = true;
				Ext.getCmp("rbwq").getEl().dom.readOnly  = true;
				Ext.getCmp("dtyl").getEl().dom.readOnly  = true;
				Ext.getCmp("ccddj").getEl().dom.readOnly  = true;
				Ext.getCmp("qxwd").getEl().dom.readOnly  = true;
				Ext.getCmp("yzr").getEl().dom.readOnly  = true;
				Ext.getCmp("sjly").getEl().dom.readOnly  = true;
				Ext.getCmp("xxfs").getEl().dom.readOnly  = true;
				Ext.getCmp("gxtj").getEl().dom.readOnly  = true;
				Ext.getCmp("yyzt").getEl().dom.readOnly  = true;
				Ext.getCmp("djsm").getEl().dom.readOnly  = true;
				Ext.getCmp("beizhu").getEl().dom.readOnly  = true;
				}
			} 
	    } 
	});
	

	

