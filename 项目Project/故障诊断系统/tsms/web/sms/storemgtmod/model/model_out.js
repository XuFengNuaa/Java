var sm = new Ext.grid.CheckboxSelectionModel();
Ext.onReady(function(){
	var insertormodify=0;
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
    var filter="";
	var order="";
	var start=0;
	var limit=100000;//每页显示记录条数
	//以下数据用来定义工具库存位置
	var locations = '';
	var finallocal= '';
	var ku='';
	var cen='';
	var cen1='';
	var wei='';
 	var kunum = 0;
 	var cennum=0;
 	var cen1num=0;
 	var weinum=0;
 	var idnum = '';


	//定义查询页面
	var search=new Ext.Search({
		region:'north',
        renderTo:'querydiv',
        state:false,
        columnArray:Stringquery,
		orderArray:Stringorder,
        height:123,
        handler:customQueryFn
    });
    
	//定义结果页面
	var hpObj={url:'logic_gen.jsp',method: 'POST',extraParams:{}};
	
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            		totalProperty:'totalProperty',
            		root:'filedata'
        		},[
        		{name:'id'},
				{name:'mnum'},
				{name:'pclass'},
				{name:'bclass'},
				{name:'name'},
				{name:'spec'},
				{name:'company'},
				{name:'location'},
				{name:'nct_count_in'},
				{name:'uct_count_in'},
				{name:'mini_qs'},
				{name:'hq'},
				{name:'use_freq'},
				{name:'remark'},
				{name:'order_num'},
				{name:'companyid'}
				
        ])
    });
    

    var grid = new Ext.grid.GridPanel({
		        title: false,
		        id:'filetable',
		        loadMask:true,
		        enableColumnMove:false,
		        columns: [sm,
		        	{
			            header: "零件编号",
			            dataIndex: "mnum",
			            width : 80,
			            align:'center',
			            sortable: true
			        },{
			            header: "名称",
			            dataIndex: "name",
			            width : 100,
			            align:'center',
			            sortable: true
			        },{
			        	header: "故障原因",
			            width : 80,
			            align:'center',
			            dataIndex: "spec",
			            sortable: true
			        },{
			        	header: "解决方法",
			            width : 80,
			            align:'center',
			            dataIndex: "pclass",
			            hidden:true,
			            sortable: true
			        },{
			        	header: "类别",
			            width : 80,
			            align:'center',
			            dataIndex: "bclass",
			            hidden:true,
			            sortable: true
			        },{
			        	header: "已有库存",
			            width : 90,
			            align:'center',
			            dataIndex: "nct_count_in",
			            sortable: true
			        },{
			        	header: "最新需求",
			            width : 90,
			            align:'center',
			            dataIndex: "uct_count_in",
			            sortable: true
			        },{
			            header: "推荐专家",
			            width : 80,
			            align:'center',
			            dataIndex: "hq",
			            sortable: true
			            //hidden: true
			        },{
			            header: "库存位置",
			            width : 80,
			            align:'center',
			            dataIndex: "location",
			            sortable: true,
			            hidden: true
			        },{
			            header: "厂商",
			            width : 120,
			            align:'center',
			            dataIndex: "company",
			            sortable: true
			            //hidden: true
			        },{
			            header: "订货号",
			            width : 80,
			            align:'center',
			            dataIndex: "order_num",
			            sortable: true,
			            hidden: true
			        }],
		        ds:ds,
		        sm:sm, //多选框checkbox
		        height:300,
		        stateId:'filetable',
		        autoScroll:true
    });
    
    var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
	var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});
	var toolthr=new Ext.Toolbar.Separator({id:'toolthr'});
	var toolfour=new Ext.Toolbar.Separator({id:'toolfour'});
	var toolfive=new Ext.Toolbar.Separator({id:'toolfive'});
	var toolsix=new Ext.Toolbar.Separator({id:'toolsix'});

	 var tabs = new Ext.TabPanel({   
                 region: 'center', //border 布局，将页面分成东，南，西，北，中五部分，这里表示TabPanel放在中间   
                 margins: '3 3 3 0',   
                 activeTab: 0,   
                 defaults: {   
                    autoScroll: true   
                 },  
                 items: [{   
                     title: '中文',   
                     html: ""   
                 }, {   
                     title: '英文',   
                     html: ""   
                 }]   
            });   

	var win=new Ext.Viewport({
		layout:"border",
		id:"win",
		autoHeight:false,
		items:[search,
		        tabs ]
	});
	   if(isUser()||isTeamLeader()||isBoss()||isPro()){	   
	     toolone.hide();
	     tooltwo.hide();
	     toolsix.hide();	     
	    Ext.getCmp("add-btn").hide();
	    Ext.getCmp("edit-btn").hide();
	    Ext.getCmp("input").hide();
	   }  
 	   if(isStoreManager()||isBoss()||isPro()){	   
	     toolthr.hide();
	    Ext.getCmp("need").hide();
	   } 
 	  

    var mainclass = [[1,'常规工具'],[2,'非常规工具'],[3,'用电工具'],[4,'组合工具'],[5,'耗料工具']];
    var subclass = new Array();
		       subclass[1] = [[11,'扳手'],[12,'锉刀'],[13,'锯'],[14,'锤子'],[15,'起子'],[16,'钳子'],[17,'其他']];
		       subclass[2] = [[21,'放大器'],[22,'剪刀类'],[23,'锁'],[24,'注射器'],[25,'镊子'],[26,'其他']];
		       subclass[3] = [[31,'焊枪'],[32,'电炉'],[33,'电磨'],[34,'电器'],[35,'风动工具'],[36,'其他']];
		       subclass[4] = [[41,'组合工具'],[42,'其他']];
		       subclass[5] = [[51,'油石'],[52,'焊膏'],[53,'清洗类'],[54,'润滑类'],[55,'粘接类'],[56,'其他']];
		       var combomainclass = new Ext.form.ComboBox({
			        store: new Ext.data.SimpleStore( {
			        fields: ["mainclassId", "mainclassName"],
			        		data: mainclass
			        }),
			        listeners:{
				        select:function(combo, record,index){ 
				        combosubclass.clearValue();
				        combosubclass.store.loadData(subclass[record.data.mainclassId]);
				        }
			        },
			        valueField :"mainclassId",
			        displayField: "mainclassName",
			        mode: 'local',
			        forceSelection: true,
			        blankText:'请选择大类',
			        emptyText:'请选择大类',
			        hiddenName:'mainclassId',
			        editable: false,
			        triggerAction: 'all',
			        allowBlank:true,
			        fieldLabel: '大类',
			        name: 'tpclass',
			        id: 'tpclass',
			        width:100
		    });
		    var combosubclass = new Ext.form.ComboBox({
			        store: new Ext.data.SimpleStore( {
			        fields: ["subclassId",'subclassName'],
			        		data:[]
			        }),
			        valueField :"subclassId",
			        displayField: "subclassName",
			        mode: 'local',
			        forceSelection: true,
			        blankText:'选择小类',
			        emptyText:'选择小类',
			        hiddenName:'subclassId', 
			        editable: false,
			        triggerAction: 'all',
			        allowBlank:true,
			        fieldLabel: '小类',
			        name: 'tbclass',
			        id: 'tbclass',
			        width: 100
		    });
	
	  //数据库查询厂商
			  var storeCom = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy({url:"/tsms/web/sms/bscinfmod/gen_tool/logic_gen.jsp?type=QUERYALL"}), // 数据源
			     reader: new Ext.data.JsonReader({
			         id:'id',
				     root:"filedata",
				     fields:[{name: 'id'},{name: 'company'}]    // 解析
				       }),
				     remoteSort: true
			     });
			  storeCom.load();
		 
			var comnameCombo=new Ext.form.ComboBox({
					fieldLabel:'厂商',
					name:'com',
					id:'tcom',
					/*listeners:{
					        select:function(kucombo, record,index){ 
					        cencombo.clearValue();
					        cencombo.store.loadData(array[record.data.values]);
					        }},*/		
					store:storeCom, 
					displayField:'company',
					valueField:'id',
					triggerAction: 'all',
					width:100,
					emptyText:'请选择厂商',
					forceSelection:true,
					allowBlank:true,
					mode:'local',
					typeAhead: true,
					editable:true		
			});	
	
	//工具是否为高质
			var data = [['否','否'],['是','是']]; 
	        var storehq = new Ext.data.SimpleStore({fields:['key','value'],data:data});
			var hpCombo=new Ext.form.ComboBox({
				fieldLabel:'是否高质',
				name:'thp',
				id:"thp",				
				displayField:'value',
				valueField:'key',
				value:'否',
				store: storehq,
				triggerAction: 'all',
				lazyRender:true,
				width:100,
				typeAhead: true,
				selectOnFocus:true,
	//			emptyText:'选择专业...',
				allowBlank:true,
				mode:'local',
				editable:true		
				});
				
			
	//工具的库存位置    	
       var  ku= [[1,'01'],[2,'02'],[3,'03'],[4,'04'],[5,'05'],[6,'06'],[7,'07'],[8,'08'],[9,'09'],[10,'10'],
					 [11,'11'],[12,'12'],[13,'13'],[14,'14'],[15,'15'],[16,'16'],[17,'17'],[18,'18'],[19,'19'],[20,'20'],
					 [21,'21'],[22,'22'],[23,'23'],[24,'24'],[25,'25'],[26,'26'],[27,'27'],[18,'28'],[29,'29'],[30,'30'],
					 [31,'31'],[32,'32'],[33,'33'],[34,'34'],[35,'35'],[36,'36'],[37,'37'],[38,'38'],[39,'39'],[40,'40'],
					 [41,'41'],[42,'42'],[43,'43'],[44,'44'],[45,'45'],[46,'46'],[47,'47'],[48,'48'],[49,'49'],[50,'50'],
					 [51,'51'],[52,'52'],[53,'53'],[54,'54'],[55,'55'],[56,'56'],[57,'57'],[58,'58'],[59,'59'],[60,'60'],
					 [61,'61'],[62,'62'],[63,'63'],[64,'64'],[65,'65'],[66,'66'],[67,'67'],[68,'68'],[69,'69'],[70,'70'],
					 [71,'71'],[72,'72'],[73,'73'],[74,'74'],[75,'75'],[76,'76'],[77,'77'],[78,'78'],[79,'79'],[80,'80'],
					 [81,'81'],[82,'82'],[83,'83'],[84,'84'],[85,'85'],[86,'86'],[87,'87'],[88,'88'],[89,'89'],[90,'90'],
					 [91,'91'],[92,'92'],[93,'93'],[94,'94'],[95,'95'],[96,'96'],[97,'97'],[98,'98'],[99,'99']];
		var cen1= [[0,'00'],[1,'01'],[2,'02'],[3,'03']];
		
			var storeku = new Ext.data.SimpleStore({fields:['values','key'],data:ku});
			var storecen1 = new Ext.data.SimpleStore({fields:['values','key'],data:cen1});
			
			var kucombo=new Ext.form.ComboBox({				
				name:'ku',
				id:"tku",
				displayField:'key',
				valueField:'values',
				store: storeku,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				fieldLabel:'库存位置',
				typeAhead: true,
				selectOnFocus:true,
		    	emptyText:'库',
				allowBlank:true,
				mode:'local',
				editable:false,
				listeners:{
					select : function(){
					cencombo.setDisabled(false);
					kunum=parseInt(kucombo.getValue());
					if(kunum<10){
					kunum='0'+kunum}
					cennum=parseInt(cencombo.getValue());
					if(cennum<10){
					cennum='0'+cennum}
					cen1num=parseInt(cen1combo.getValue());
					if(cen1num<10){
					cen1num='0'+cen1num}
					weinum = parseInt(weicombo.getValue());
					if(weinum<10){
					weinum='0'+weinum}
					locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
					//Ext.MessageBox.confirm('工具原来的存放位置是:'+finallocal,'您修改了工具存放的库位置，现在工具的存放位置是：'+locations);
					}
				}			
				});
				
				
				var cencombo=new Ext.form.ComboBox({
				name:'cen',
				id:"tcen",
				displayField:'key',
				valueField:'values',
		        store: storeku,
		        disabled:true,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				typeAhead: true,
				selectOnFocus:true,
				emptyText:'层',
				allowBlank:true,
				mode:'local',
				editable:false,
				listeners:{
					select : function(){
					cen1combo.setDisabled(false);
					weicombo.setDisabled(false);
					kunum=parseInt(kucombo.getValue());
					if(kunum<10){
					kunum='0'+kunum}
					cennum=parseInt(cencombo.getValue());
					if(cennum<10){
					cennum='0'+cennum}
					cen1num=parseInt(cen1combo.getValue());
					if(cen1num<10){
					cen1num='0'+cen1num}
					weinum = parseInt(weicombo.getValue());
					if(weinum<10){
					weinum='0'+weinum}
					locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
					
					//Ext.MessageBox.confirm('工具原来的存放位置是:'+finallocal,'您修改了工具存放的库位置，现在工具的存放位置是：'+locations);
					}
				}			
				});
				
				var cen1combo=new Ext.form.ComboBox({
				name:'cen1',
				id:"tcen1",	
				displayField:'key',
				valueField:'values',
		        store: storecen1,
				disabled:true,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				typeAhead: true,
				selectOnFocus:true,
				emptyText:'(层)',
				allowBlank:true,
				mode:'local',
				editable:false,
				listeners:{
					select : function(){
						if (weicombo.getValue()!=''&&cen1combo.getValue()!=''){
						        kunum=kucombo.getValue();
								if(kunum<10){
								kunum='0'+kunum}
								cennum=cencombo.getValue();
								if(cennum<10){
								cennum='0'+cennum}
								cen1num=cen1combo.getValue();
								if(cen1num<10){
								cen1num='0'+cen1num}
								weinum = weicombo.getValue();
								if(weinum<10){
								weinum='0'+weinum};
								locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
								Ext.MessageBox.confirm('消息确认','工具的存放位置是：'+locations,function(btn){
							        if (btn=="no"){
								        kucombo.clearValue();
										cencombo.clearValue();
										cen1combo.clearValue();
										weicombo.clearValue();
							             kucombo.reset();
										 cencombo.reset();
										 cen1combo.reset();
										 weicombo.reset();
										 cencombo.setDisabled(true);
										 cen1combo.setDisabled(true);
										 weicombo.setDisabled(true);
										Ext.MessageBox.alert('提醒','请重新选择工具存放位置！');
										kunum='';
										cennum='';
										cen1num='';
										weinum = '';
										locations='';
							        }
						       });	
						}
					}
				}
				});
				
				
				var weicombo=new Ext.form.ComboBox({
				name:'wei',
				id:"twei",
				displayField:'key',
				valueField:'values',
				disabled:true,
		        store: storeku,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				typeAhead: true,
				selectOnFocus:true,
			 	emptyText:'位',
				allowBlank:true,
				mode:'local',
				editable:false,
				listeners:{
					select : function(){
					if (cen1combo.getValue()==""){
							Ext.MessageBox.confirm('消息确认','工具的存放位置夹层位置为空，如您不选择夹层位置将设置为00！',function(btn){
							if (btn=='yes'){
								kunum=kucombo.getValue();
								if(kunum<10){
								kunum='0'+kunum}
								cennum=cencombo.getValue();
								if(cennum<10){
								cennum='0'+cennum}
								cen1combo.setValue("0");
								cen1num='00';
								weinum = weicombo.getValue();
								if(weinum<10){
							    weinum='0'+weinum}
								locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
								Ext.MessageBox.confirm('消息确认','工具的存放位置是：'+locations,function(btn){
							        if (btn=="no"){
								        kucombo.clearValue();
										cencombo.clearValue();
										cen1combo.clearValue();
										weicombo.clearValue();
							            kucombo.reset();
										 cencombo.reset();
										 cen1combo.reset();
										 weicombo.reset();
										 cencombo.setDisabled(true);
										 cen1combo.setDisabled(true);
										 weicombo.setDisabled(true);
										Ext.MessageBox.alert('提醒','请重新选择工具存放位置！');
										kunum='';
										cennum='';
										cen1num='';
										weinum = '';
										locations='';
							        }
						       });
							}else {
								kucombo.clearValue();
								cencombo.clearValue();
								cen1combo.clearValue();
								weicombo.clearValue();
					            kucombo.reset();
								 cencombo.reset();
								 cen1combo.reset();
								 weicombo.reset();
								 cencombo.setDisabled(true);
								 cen1combo.setDisabled(true);
								 weicombo.setDisabled(true);
								Ext.MessageBox.alert('提醒','请重新选择工具存放位置！');
							}});
					}else {
							kunum=kucombo.getValue();
							if(kunum<10){
							kunum='0'+kunum}
							cennum=cencombo.getValue();
							if(cennum<10){
							cennum='0'+cennum}
							cen1num=cen1combo.getValue();
							if(cen1num<10){
							cen1num='0'+cen1num}
							weinum = weicombo.getValue();
							if(weinum<10){
							weinum='0'+weinum}
							locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
							Ext.MessageBox.confirm('消息确认','工具的存放位置是：'+locations,function(btn){
								if (btn=="yes"){
								locations = locations;      
						        }else if(btn=="no"){
						        kucombo.clearValue();
										cencombo.clearValue();
										cen1combo.clearValue();
										weicombo.clearValue();
							            kucombo.reset();
										 cencombo.reset();
										 cen1combo.reset();
										 weicombo.reset();
										 cencombo.setDisabled(true);
										 cen1combo.setDisabled(true);
										 weicombo.setDisabled(true);
										Ext.MessageBox.alert('提醒','请重新选择工具存放位置！');
										kunum='';
										cennum='';
										cen1num='';
										weinum = '';
										locations='';
						        }
							});
						}
					}
				}			
				});			
			
		    
	var inform=new Ext.FormPanel({
    	id:'inform',
       // width : 400,
        //labelWidth:100, 	
        height:360,			      				       
        frame:true,
        bodyStyle:'padding:10px 10px 0',	
        items: [{
	            layout:'column',
	            items:[{
	                columnWidth:.4,					              
	                layout: 'form',
	                labelWidth:75,
	                labelAlign:"right",
	                items: [{
		                    xtype:'textfield',
		                    fieldLabel: '物资编号',
		                    name: 'tmnum',
		                    width:100,
		                    id: 'tmnum'
			                },
			                combomainclass,combosubclass,{
			                    xtype:'textfield',
			                    fieldLabel: '名称',
			                    name: 'name',
			                    width:100,
			                    id: 'tname'
			                },{
			                   xtype:'textfield',
			                    fieldLabel: '规格',
			                    name: 'guige',
			                    width:100,
			                    id: 'tguige'
			                },{
							xtype : 'textfield',
							id : 'torder',
							fieldLabel : '订货号',
							width:100
					}
			        ]},{
					columnWidth:.07,
					labelAlign:"left",
					bodyStyle:'padding:10px 10px 0',
					html:'<font color="#FF0000">**<br><br>**<br><br>**<br><br>**<br><br></font>',
					border:false
					},
			        {columnWidth:.46,
	                layout: 'form',
	                labelWidth:100,
	                labelAlign:"right",
	                items: [comnameCombo,hpCombo,{
		                    xtype:'textfield',
		                    fieldLabel: '最低库存量',
		                    width:100,
		                    name: 'mini',
		                    id: 'tmini'
	                		},{
		                    xtype:'textfield',
		                    fieldLabel: '新工具在库量',
		                    readOnly: true,
		                    width:100,
		                    style:"background:#C1C1C1;",
		                    name: 'nct',
		                    id: 'tnct'
	                		},{
		                    xtype:'textfield',
		                    fieldLabel: '旧工具在库量',
		                    readOnly: true,
		                    width:100,
		                    style:"background:#C1C1C1;",
		                    name: 'uct',
		                    id: 'tuct'
	                		},{
							xtype : 'textfield',
							id : 'tfreq',
							fieldLabel : '使用频率',
							readOnly: true,
		                    width:100,
		                    style:"background:#C1C1C1;"
					}
	                ]},{
						columnWidth:.07,
						labelAlign:"left",
						bodyStyle:'padding:10px 10px 0',
						html:'<font color="#FF0000">**<br><br>**<br><br>**<br><br></font>',
						border:false
							},
	               /* {
	                columnWidth:1,					              
	                layout: 'form',
	                labelWidth:75,
	                labelAlign:"right",
	                items: [{
							xtype : 'textfield',
							id : 'torder',
							fieldLabel : '订货号',
							width:100
					}]},*/{columnWidth:.31,					              
	                layout: 'form',
	                labelWidth:75,
	                labelAlign:"right",
	                items:[kucombo]
	                },{
	                columnWidth:.125,					              
	                layout: 'form',
	                labelAlign:"left",
	                hideLabels:true,
	                items:[cencombo]
	                },{
	                columnWidth:.125,					              
	                layout: 'form',
	                labelAlign:"left",
	                hideLabels:true,
	                items:[cen1combo]
	                },{
	                columnWidth:.125,					              
	                layout: 'form',
	                labelAlign:"left",
	                hideLabels:true,
	                items:[weicombo]
	                },{
						columnWidth:.07,
						labelAlign:"left",
						bodyStyle:'padding:10px 10px 0',
						html:'<font color="#FF0000">**<br><br></font>',
						border:false
							},{
	                columnWidth:1,					              
	                layout: 'form',
	                labelWidth:75,
	                labelAlign:"right",
	                items: [{
							xtype : 'textarea',
							id : 'tremark',
							fieldLabel : '备注',
							width:325,
							height: 100
					}]
			}]
            
        }],
      
         buttonAlign:"center",
         buttons: [{
	                text:'保存',
	                id:'save',
	                //type:'submit',
	                handler:function(){
	                       var id=Ext.get("tmnum").dom.value;
				           var idReg=/^[0-9a-zA-Z]+$/;
	
				           var tnamejs=Ext.get("tname").dom.value;
				           var tnamejsReg=/^[0-9a-zA-Z\u4e00-\u9fa5\.]+$/;
				           
				           var tminijs=Ext.get("tmini").dom.value;
				           var tminijsReg=/^(0|[1-9][0-9]*)$/;
				           
	                       var remark=Ext.get("tremark").dom.value;
					       var remarkReg=/^\d+(\.\d+)?$/;
					       
					       var order=Ext.get("torder").dom.value;
					       var hq=hpCombo.getValue();
		                        if(Ext.get("tmnum").dom.value.trim()==""){
						     	        Ext.MessageBox.alert("警告！","物资编号必填项！");
						     	}else if(Ext.get("tname").dom.value=="" ){
								        Ext.MessageBox.alert("警告！",'请填写工具名称!');
						     	}else if(Ext.get("tpclass").dom.value=="请选择大类" ){
								        Ext.MessageBox.alert("警告！",'请选择一种大类!');
						     	}else if(Ext.get("tbclass").dom.value=="选择小类" ){
								        Ext.MessageBox.alert("警告！",'请选择一种小类!');
						     	}else if(Ext.get("tcom").dom.value=="请选择厂商" ){
								        Ext.MessageBox.alert("警告！",'请选择工具的生产厂商!');
						     	}else if(hq == '是'&& order == '' ){
								        Ext.MessageBox.alert("警告！",'高质工具必须输入订货号！');
						     	}else if(locations == ''||kucombo.getValue()==''|| cencombo.getValue()==''||cen1combo.getValue()==''||weicombo.getValue()==''){
								        Ext.MessageBox.alert("警告！",'请选择工具存放位置!');
						     	}/*else if(id!="" && !idReg.test(id)){
								        Ext.MessageBox.alert("警告！",'物资编号由英文字符、数字组成，请修改！');
								}else if(tnamejs!="" && !tnamejsReg.test(tnamejs)){
								        Ext.MessageBox.alert("警告！",'工具名称由中英文字符、数字和点组成，请修改！');
								}*/else if(tminijs =="" || !tminijsReg.test(tminijs)){
								        Ext.MessageBox.alert("警告！",'最低库存量必须是一个非负整数，请修改！');
								}/*else if(stanlifejs!="" && !stanlifeReg.test(stanlifejs)){
								        alert('标准年限请填入阿拉伯数字');
								}*/else if(Ext.get("tname").dom.value.trim().length>30){
									    Ext.MessageBox.alert('数量不允许大于30字！');
								}else if(Ext.get("tremark").dom.value.trim().length>300){
									   Ext.MessageBox.alert('备注不允许大于60个字！');
								}
								else{
									//alert("提醒您！",'确认保存？');
									   keyBoard.hide();
						                 insertSaveFn();
									
								}
						}
                },{
					text: '取消',
					handler: function(){
					 if(insertormodify==1){insertormodify=0;}
					 Ext.getCmp("window").hide();
					 Ext.getCmp("inform").form.reset();
					 cencombo.setDisabled(true),
					 cen1combo.setDisabled(true),
					 weicombo.setDisabled(true),
					 locations = '';
					 keyBoard.hide();
		             $('#tguige').keypad('destroy');
					}
                },{
					text: '重置',
					handler: function(){
					 Ext.getCmp("inform").form.reset();
					 cencombo.setDisabled(true),
					 cen1combo.setDisabled(true),
					 weicombo.setDisabled(true),
					 locations = '';
					 keyBoard.hide();
					}
                },{
					text: '键盘',
					id:'keyB',
					handler:function(){
						if(keyBoardWin.isVisible()){
							keyBoard.hide();
						}else{						 
							keyBoard.show();
						}
			          }//键盘
		            }
           	]
	});
	//焦点在查询文本框中，使其能够使用特殊符号窗口
	Ext.get("querydivquerytext").focus();
	Ext.get("querydivquerytext").on("focus", function(){keyBoardInput = Ext.getDom("querydivquerytext");});					
	
	
    keyBoard();				
    keyBoard.hide();
	
	
	//明细表
	var details=new Ext.FormPanel({
    	id:'details',
       // width : 400,
        labelWidth:100, 	
        height:360,			      				       
        frame:true,
        bodyStyle:'padding:10px 10px 0',	
        items: [{
	            layout:'column',
	            items:[{
	                columnWidth:.5,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
		                    xtype:'textfield',
		                    fieldLabel: '物资编号',
		                    readOnly: true,
		                    name: 'iso',
		                    width:100,
		                    id: 'tmnums'
			                },{
		                    xtype:'textfield',
		                    fieldLabel: '大类',
		                    readOnly: true,
		                    name: 'minaclass',
		                    width:100,
		                    id: 'tminaclass'
			                },{
		                    xtype:'textfield',
		                    fieldLabel: '小类',
		                    readOnly: true,
		                    name: 'subclass',
		                    width:100,
		                    id: 'tsubclass'
			                },{
			                    xtype:'textfield',
			                    fieldLabel: '名称',
			                    readOnly: true,
			                    name: 'name',
			                    width:100,
			                    id: 'tnames'
			                },{
			                   xtype:'textfield',
			                   fieldLabel: '规格',
			                    readOnly: true,
			                    name: 'guige',
			                    width:100,
			                    id: 'tguiges'
			                },{
		                	xtype: 'textfield',
		                	fieldLabel: '是否高质',
		                	readOnly: true,
		                	name: 'hqs',
		                	id: 'thqs',
		                	width:100
		                }
			        ]},
			        {columnWidth:.5,
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
		                    xtype:'textfield',
		                    fieldLabel: '厂商',
		                    readOnly: true,
		                    width:100,
		                    name: 'coms',
		                    id: 'tcoms'
	                		},{
		                    xtype:'textfield',
		                    fieldLabel: '最低库存量',
		                    readOnly: true,
		                    width:100,
		                    name: 'mini',
		                    id: 'tminis'
	                		},{
		                    xtype:'textfield',
		                    fieldLabel: '新工具在库量',
		                    readOnly: true,
		                    width:100,
		                    name: 'nct',
		                    id: 'tncts'
	                		},{
		                    xtype:'textfield',
		                    fieldLabel: '旧工具在库量',
		                    readOnly: true,
		                    width:100,
		                    name: 'uct',
		                    id: 'tucts'
	                		},{
		                    xtype:'textfield',
		                    fieldLabel: '使用频率',
		                    readOnly: true,
		                    width:100,
		                    name: 'freq',
		                    id: 'tfreqs'
	                		},{
		                	xtype: 'textfield',
		                	fieldLabel: '存放位置',
		                	readOnly: true,
		                	name: 'fkeyword',
		                	id: 'tkeywords',
		                	width: 100
		                }
	                ]},{
	                columnWidth:1,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
							xtype : 'textfield',
							id : 'torders',
							fieldLabel : '订货号',
							readOnly: true,
							width:100
					}]
			      },{
	                columnWidth:1,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
							xtype : 'textarea',
							id : 'tremarks',
							fieldLabel : '备注',
							readOnly: true,
							width:325,
							height: 100
					}]
			}]
            
        }],
      
         buttonAlign:"center",
         buttons: [{
	                text:'确定',
	                id:'okok',
	                //type:'submit',
	                handler:function(){
	                   Ext.getCmp("win").hide();
					   Ext.getCmp("details").form.reset();    
						}
                }]
	});
	
		//定义....................增加......................工具信息弹出窗口
	   var window = new Ext.Window({
	    			id:'window',
	    			modal:true,
	                width:500,
	                height:400,
	                //closable:false,
	                listeners:{
							  "hide":function(){
							    if(keyBoardWin.isVisible()){									    
									keyBoard.hide();
								}
							  if($.keypad._keypadShowing){				  
								$('#tguige').keypad('destroy');
							  }
							  }
							 },	
	                closeAction:'hide',
	                plain: true,
	                title:"<p align='center'>工具信息</p>",    
					bodyBorder:true,
	                items:inform, 
				    resizable:false             
	    }); 
	
	var FormPanel_need = new Ext.FormPanel({
        labelWidth: 120, // label settings here cascade unless overridden
        columnWidth:.96,
        layout:"form",
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:5px 5px 0',
        items: [  {   
			            	columnWidth:1,					              
			                layout: 'column',
			                items: [{   
				            	columnWidth:.9,					              
				                layout: 'form',
				                labelAlign:"right",
				                items: [
				                {    fieldLabel:"图号或产品令号",
                                      xtype: 'textfield',
                                      align:'center',
                                      width:125,
                                      name:"drawing_num_need",
                                      id:"drawing_num_need",
                                      allowBlank:false,
                                      blankText:"图号或产品令号不能为空，请填写!",
                                      regex:/^([A-Z]|[a-z]|[\d])*$/,
                                      regexText:'图号或产品令号只能由共100位内字母、数字组成！'
                                   }
			                     ]	            
					            },{ columnWidth: .1,
					                bodyStyle:'padding:5px 0px 0',
					                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
					                border:false
			           }]            
		              },
		              {
			            	columnWidth:1,					              
			                layout: 'column',
			                items: [{   
				            	columnWidth:.9,					              
				                layout: 'form',
				                labelAlign:"right",
				                items: [
			                     { fieldLabel:"需&nbsp;&nbsp;&nbsp;求&nbsp;&nbsp;&nbsp;数&nbsp;&nbsp;&nbsp;量",
			                       xtype: 'textfield',
			                       width:125,
			                       name:"need_count",
			                       id:"need_count",
			                       allowBlank:false,
			                       blankText:"数量不能为空，请填写!",
			                       regex:/^\d+$/,regexText:'数量应该是一个非负整数！'
                                   }]	            
					            },{ columnWidth: .1,
					                bodyStyle:'padding:5px 0px 0',
					                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
					                border:false
			           }]    
		              
		              },
		              
		             { columnWidth:1,					              
			                layout: 'form',
			                items: [{
                             fieldLabel:"需&nbsp;&nbsp;&nbsp;求&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;期",
                             xtype: 'textfield',
                             width:125,
                             align:'center',
                             name:"need_time",
                             id:"need_time",
                             style:"background:#C1C1C1;text-align:center",
                             readOnly:true
                }]
                }
                ]
                
        
    });
		//获取时间
     var data_need=getNowtime();
     Ext.getCmp("need_time").setValue(data_need);
     function getNowtime(){
		var sUrl = 'logic_gen.jsp';
		var parms = {type:'getNowtime'};
		var objtp = Ext.util.JSON.decode(callService(sUrl,parms));
		var nowtimeValue = objtp.nowtime;
		return nowtimeValue;
	}
		
	    
	var win_need = new Ext.Window({
 			 id:'win_need',
 			 modal:true,
             width:350,
             height:200,
             closeAction:'hide',
             plain: true,
             title:"<p align='center'>需&nbsp;求&nbsp;信&nbsp;息</p>",
             buttonAlign:"center",
			 bodyBorder:true,
			 items:[  new Ext.Panel({
                		layout:'column',
                		frame:true,
                		height:170,
						items:[FormPanel_need,
						 		{
									columnWidth:.10,
									bodyStyle:'padding:15px 0px 0',
									border:false
								}]
					})],
             buttons: [{
                text:'确定',
                handler:editneedFn
              },{
				text: '取消',
				handler: cancelneedFn1
              }]
 		}); 	
		//需求数量输入窗口
	
function needFn(){
		var record =sm.getSelections();// 返回值为 Record 类型
		 
		if(record.length==0){
	            Ext.MessageBox.alert("提示",'请先选择一种工具!');
	            return;
	        }else if(record.length!=1){
	        	Ext.MessageBox.alert("提示",'每次只能选择一种工具!');
	            return;
	        }else{ 
        if(record.length!=0){
        
        win_need.show();
        }
        }
	}
	
	//处理提交函数
	function editneedFn(){
		var record =sm.getSelections();
		var id="";
		var drawing_num_need=Ext.get("drawing_num_need").dom.value;
		var drawing_num_needReg=/^([A-Z]|[a-z]|[\d])*$/;
		var need_count=Ext.get("need_count").dom.value;
		var need_countReg=/^(0|[1-9][0-9]*)$/;
		
		if(drawing_num_need==""){
			Ext.MessageBox.alert("警告",'图号或产品令号不能为空！');
		}else if(need_count==""){
			Ext.MessageBox.alert("警告",'数量不能为空！');
		}else if(need_count=="0"){
			Ext.MessageBox.alert("警告",'需求数量不能为零！');
		}else if(need_count!=""&&!need_countReg.test(need_count)){
			Ext.MessageBox.alert("警告",'需求数量应该是一个非负整数！');
		}else if(drawing_num_need!=""&&!drawing_num_needReg.test(drawing_num_need)){
			Ext.MessageBox.alert("警告",'图号或产品令号只能由字母和数字组成！');
		}else{
		Ext.MessageBox.confirm('提示','是否确定提交？？？',function(btn) {
			if (btn=='yes'){
			Ext.showProgressDialog();
				id=record[0].json.id;
				Ext.showProgressDialog("正在提交修改数据，请稍候...");
				Ext.Ajax.request({
					 	url:'logic_gen.jsp',
					 	method:'post',
				 		params:{
				 				type:'NEEDPOST',
				 				id:id,
				 				need_count:need_count,
				 				drawing_num_need:drawing_num_need
				 				},
						success:function(result){
							if (result){
								Ext.hideProgressDialog();	
								alert('提示:所选工具的需求已提交成功！');
								//customQueryFn();
								win_need.hide();	
							}
					 	},
						 //提交失败的回调函数
					 	failure:function(){
					 		Ext.hideProgressDialog();
							alert('提示:服务器出现错误请稍后再试！');
					 	}
				});
			}
		});}
	}
	
	function cancelneedFn1(){
		Ext.MessageBox.confirm('提示','放弃此次选择，确认吗？',function(btn) {
			if (btn=='yes'){
				win_need.hide();
			}
		});  		
	}
		
	
	function addFn(){
		  if(isSys()){
		    alert("对不起，您无权进行此项操作")
		  }else{   
		    if(!Ext.getCmp("window").isVisible()){
		    			keyBoard.hide();
				        
						Ext.getCmp("tmnum").on("focus", function(){keyBoardInput = Ext.getDom("tmnum");});
						Ext.getCmp("tmini").on("focus", function(){keyBoardInput = Ext.getDom("tmini");});
						Ext.getCmp("tname").on("focus", function(){keyBoardInput = Ext.getDom("tname");});
						Ext.getCmp("tguige").on("focus", function(){keyBoardInput = Ext.getDom("tguige");});
						Ext.getCmp("torder").on("focus", function(){keyBoardInput = Ext.getDom("torder");});
						Ext.getCmp("tremark").on("focus", function(){keyBoardInput = Ext.getDom("tremark");});
								    
		                Ext.getCmp("tmnum").setValue("");
						//Ext.getCmp("tcom").setValue("");
						//Ext.getCmp("company").getEl().dom.readOnly  = false;
						//Ext.getCmp("tloc").setValue("");
						Ext.getCmp("tmini").setValue("");
						Ext.getCmp("tname").setValue("");
						Ext.getCmp("tnct").setValue("0");
						Ext.getCmp("tguige").setValue("");
						Ext.getCmp("tuct").setValue("0");
						Ext.getCmp("tfreq").setValue("0");
						Ext.getCmp("tremark").setValue("");
						insertormodify=0;
			            Ext.getCmp("save").show();
			            Ext.getCmp("keyB").hide();
						Ext.getCmp("window").show();
		    			$('#tguige').keypad();//修改窗口显示
				}
		   }
	}
	//定义.................显示.................工具信息弹出窗口
	 var win = new Ext.Window({
	    			id:'win',
	    			modal:true,
	                width:500,
	                height:400,
	                //closable:false,
	                closeAction:'hide',
	                plain: true,
	                title:"<p align='center'>工具详细信息</p>",    
					bodyBorder:true,
	                items:details               
	    }); 
	    
	    
    function detFn(){
		//alert("detfn");
		var record =sm.getSelections();// 返回值为 Record 类型            
	    var havaPasser = "";
		if(record.length != 0){
			havaPasser = record[0].get("passer") + "";
		}
		if(record.length==0){
            Ext.MessageBox.alert("警告！",'请选择一条记录!');
            return;
        }else if(record.length!=1){
        	Ext.MessageBox.alert("警告！",'您最多选择一条记录进行操作！');
            return;
        }/*else if(record[0].get("creator")!=userid && !isProCharger()){
            alert('提示:您不具有修改该文件的权限!');
            return;
        }else if(havaPasser != "" && !isProCharger()){
        	alert('提示:文件已经批准通过，只有项目负责人有权修改！');
        	return;
        }*/else{
	    	   if(!Ext.getCmp("win").isVisible()){
	    	           keyBoard.hide();
		                //Ext.getCmp("keyA").setText("显示键盘");
				        //Ext.getCmp("keyB").setText("显示键盘");
				        //Ext.getCmp("keyC").setText("显示键盘")
						Ext.getCmp("tmnums").setValue(record[0].get("mnum"));
						Ext.getCmp("tminaclass").setValue(record[0].get("pclass"));
						Ext.getCmp("tsubclass").setValue(record[0].get("bclass"));
						Ext.getCmp("tcoms").setValue(record[0].get("company"));
						Ext.getCmp("tkeywords").setValue(record[0].get("location"));
						Ext.getCmp("tminis").setValue(record[0].get("mini_qs"));
						
						Ext.getCmp("tnames").setValue(record[0].get("name"));
						Ext.getCmp("tncts").setValue(record[0].get("nct_count_in"));
						Ext.getCmp("tguiges").setValue(record[0].get("spec"));
						Ext.getCmp("tucts").setValue(record[0].get("uct_count_in"));
						//Ext.getCmp("tuct").setValue(record[0].get("uct_count_in"));
						Ext.getCmp("thqs").setValue(record[0].get("hq"));
						Ext.getCmp("tfreqs").setValue(record[0].get("use_freq"));
						Ext.getCmp("tremarks").setValue(record[0].get("remark"));
						Ext.getCmp("torders").setValue(record[0].get("order_num"));
			            Ext.getCmp("okok").show();
						Ext.getCmp("win").show();//修改窗口显
						}
    	     }
	}
	

	//......................修改.......................选中数据，修改选中数据
	
	  var editdata = [['否','否'],['是','是']]; 
			        	var editstore = new Ext.data.SimpleStore({fields:['key','value'],data:editdata});
						edithqCombo=new Ext.form.ComboBox({
						fieldLabel:'是否高质',
						name:'thq',
						id:"thqs1",	
						displayField:'value',
						valueField:'key',
						//value: hqvalue,
						store: editstore,
						triggerAction: 'all',
						lazyRender:true,
						width:100,
						typeAhead: true,
						selectOnFocus:true,
						//emptyText:hqvalue,
						allowBlank:true,
						mode:'local',
						editable:true		
						}); 
						
	 			 var storeCom1 = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy({url:"/tsms/web/sms/bscinfmod/gen_tool/logic_gen.jsp?type=QUERYALL"}), // 数据源
			     reader: new Ext.data.JsonReader({
			         id:'id',
				     root:"filedata",
				     fields:[{name: 'id'},{name: 'company'}]    // 解析
				       }),
				     remoteSort: true
			     });
			     storeCom1.load();
		 
			        var comnameCombo1=new Ext.form.ComboBox({
					fieldLabel:'厂商',
					name:'com',
					id:'tcoms1',
					store:storeCom1, 
					displayField:'company',
					readOnly: true,
					valueField:'id',
					triggerAction: 'all',
					width:100,
					//emptyText:'请选择厂商',
					forceSelection:true,
					allowBlank:true,
					mode:'local',
					typeAhead: true,
					editable:true		
					});
					
		       var combomainclass1 = new Ext.form.ComboBox({
			        store: new Ext.data.SimpleStore( {
			        fields: ["mainclassId", "mainclassName"],
			        		data: mainclass
			        }),
			        listeners:{
				        select:function(combo, record,index){ 
				        combosubclass1.clearValue();
				        combosubclass1.store.loadData(subclass[record.data.mainclassId]);
				        }
			        },
			        valueField :"mainclassId",
			        displayField: "mainclassName",
			        mode: 'local',
			        forceSelection: true,
			        blankText:'请选择大类',
			        emptyText:'请选择大类',
			        hiddenName:'mainclassId',
			        editable: false,
			        triggerAction: 'all',
			        allowBlank:true,
			        fieldLabel: '大类',
			        name: 'tpclass',
			        id: 'tpclass1',
			        width:100
		    });
		    var combosubclass1 = new Ext.form.ComboBox({
			        store: new Ext.data.SimpleStore( {
			        fields: ["subclassId",'subclassName'],
			        		data:[]
			        }),
			        valueField :"subclassId",
			        displayField: "subclassName",
			        mode: 'local',
			        forceSelection: true,
			        blankText:'选择小类',
			        emptyText:'选择小类',
			        hiddenName:'subclassId', 
			        editable: false,
			        triggerAction: 'all',
			        allowBlank:true,
			        fieldLabel: '小类',
			        name: 'tbclass',
			        id: 'tbclass1',
			        width: 100
		    });					
		
		//工具的库存位置    	
       var  ku1= [[0,'00'],[1,'01'],[2,'02'],[3,'03'],[4,'04'],[5,'05'],[6,'06'],[7,'07'],[8,'08'],[9,'09'],[10,'10'],
					 [11,'11'],[12,'12'],[13,'13'],[14,'14'],[15,'15'],[16,'16'],[17,'17'],[18,'18'],[19,'19'],[20,'20'],
					 [21,'21'],[22,'22'],[23,'23'],[24,'24'],[25,'25'],[26,'26'],[27,'27'],[18,'28'],[29,'29'],[30,'30'],
					 [31,'31'],[32,'32'],[33,'33'],[34,'34'],[35,'35'],[36,'36'],[37,'37'],[38,'38'],[39,'39'],[40,'40'],
					 [41,'41'],[42,'42'],[43,'43'],[44,'44'],[45,'45'],[46,'46'],[47,'47'],[48,'48'],[49,'49'],[50,'50'],
					 [51,'51'],[52,'52'],[53,'53'],[54,'54'],[55,'55'],[56,'56'],[57,'57'],[58,'58'],[59,'59'],[60,'60'],
					 [61,'61'],[62,'62'],[63,'63'],[64,'64'],[65,'65'],[66,'66'],[67,'67'],[68,'68'],[69,'69'],[70,'70'],
					 [71,'71'],[72,'72'],[73,'73'],[74,'74'],[75,'75'],[76,'76'],[77,'77'],[78,'78'],[79,'79'],[80,'80'],
					 [81,'81'],[82,'82'],[83,'83'],[84,'84'],[85,'85'],[86,'86'],[87,'87'],[88,'88'],[89,'89'],[90,'90'],
					 [91,'91'],[92,'92'],[93,'93'],[94,'94'],[95,'95'],[96,'96'],[97,'97'],[98,'98'],[99,'99']];
		var cen11= [[0,'00'],[1,'01'],[2,'02'],[3,'03']];
		
			var storeku1 = new Ext.data.SimpleStore({fields:['values','key'],data:ku1});
			var storecen11 = new Ext.data.SimpleStore({fields:['values','key'],data:cen11});
			
			var kucombo1=new Ext.form.ComboBox({				
				name:'ku1',
				id:"tku1",
				/*listeners:{
					        select:function(kucombo, record,index){ 
					        cencombo1.clearValue();
					        cencombo1.store.loadData(array[record.data.values]);
					        }},	*/
				displayField:'key',
				valueField:'values',
				//value:0,
				store: storeku1,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				fieldLabel:'库存位置',
				typeAhead: true,
				selectOnFocus:true,
		    	emptyText:'库',
				allowBlank:true,
				mode:'local',
				editable:false,
				listeners:{
					select : function(){
					kunum=parseInt(kucombo1.getValue());
					if(kunum<10){
					kunum='0'+kunum}
					cennum=parseInt(cencombo1.getValue());
					if(cennum<10){
					cennum='0'+cennum}
					cen1num=parseInt(cen1combo1.getValue());
					if(cen1num<10){
					cen1num='0'+cen1num}
					weinum = parseInt(weicombo1.getValue());
					if(weinum<10){
					weinum='0'+weinum}
					locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
					
					//Ext.MessageBox.confirm('工具原来的存放位置是:'+finallocal,'您修改了工具存放的库位置，现在工具的存放位置是：'+locations);
					}
				}			
				});
				
				
				var cencombo1=new Ext.form.ComboBox({
				name:'cen1s',
				id:"tcen1s",
				/*listeners:{
				        select:function(cencombo, record,index){ 
				        cen1combo1.clearValue();
				        cen1combo1.store.loadData(array[record.data.values]);
				        }},	*/
				displayField:'key',
				valueField:'values',
				//value:0,
				/*store: new Ext.data.SimpleStore( {
		        fields: ["values",'key'],
		        data:[]
		        }),*/
		        store: storeku1,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				typeAhead: true,
				selectOnFocus:true,
				emptyText:'层',
				allowBlank:true,
				mode:'local',
				editable:false,
				listeners:{
					select : function(){
					kunum=parseInt(kucombo1.getValue());
					if(kunum<10){
					kunum='0'+kunum}
					cennum=parseInt(cencombo1.getValue());
					if(cennum<10){
					cennum='0'+cennum}
					cen1num=parseInt(cen1combo1.getValue());
					if(cen1num<10){
					cen1num='0'+cen1num}
					weinum = parseInt(weicombo1.getValue());
					if(weinum<10){
					weinum='0'+weinum}
					locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
					
					//Ext.MessageBox.confirm('工具原来的存放位置是:'+finallocal,'您修改了工具存放的层位置，现在工具的存放位置是：'+locations);
					}
				}		
				});
				
				var cen1combo1=new Ext.form.ComboBox({
				name:'cen11',
				id:"tcen11",	
				/*listeners:{
		        select:function(cen1combo, record,index){ 
				        weicombo1.clearValue();
				        weicombo1.store.loadData(array[record.data.values]);
		        }},	*/
				displayField:'key',
				valueField:'values',
				//value:0,
				/*store: new Ext.data.SimpleStore( {
		        fields: ["values",'key'],
		        data:[]
		        }),*/
		        store: storecen11,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				typeAhead: true,
				selectOnFocus:true,
				emptyText:'(层)',
				allowBlank:true,
				mode:'local',
				editable:false,
				listeners:{
					select : function(){
					kunum=parseInt(kucombo1.getValue());
					if(kunum<10){
					kunum='0'+kunum}
					cennum=parseInt(cencombo1.getValue());
					if(cennum<10){
					cennum='0'+cennum}
					cen1num=parseInt(cen1combo1.getValue());
					if(cen1num<10){
					cen1num='0'+cen1num}
					weinum = parseInt(weicombo1.getValue());
					if(weinum<10){
					weinum='0'+weinum}
					locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
					
					//Ext.MessageBox.confirm('工具原来的存放位置是:'+finallocal,'您修改了工具存放的夹层位置，现在工具的存放位置是：'+locations);
					}
				}		
				});
				
				
				var weicombo1=new Ext.form.ComboBox({
				name:'wei1',
				id:"twei1",
				displayField:'key',
				valueField:'values',
				//value:0,
				/*store: new Ext.data.SimpleStore( {
		        fields: ["values",'key'],
		        data:[]
		        }),*/
		        store: storeku1,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				typeAhead: true,
				selectOnFocus:true,
			 	emptyText:'位',
				allowBlank:true,
				mode:'local',
				editable:false,
				listeners:{
					select : function(){
					kunum=parseInt(kucombo1.getValue());
					if(kunum<10){
					kunum='0'+kunum}
					cennum=parseInt(cencombo1.getValue());
					if(cennum<10){
					cennum='0'+cennum}
					cen1num=parseInt(cen1combo1.getValue());
					if(cen1num<10){
					cen1num='0'+cen1num}
					weinum = parseInt(weicombo1.getValue());
					if(weinum<10){
					weinum='0'+weinum}
					locations=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
					
					//Ext.MessageBox.confirm('工具原来的存放位置是:'+finallocal,'您修改了工具存放位，工具的存放位置是：'+locations);
					}
				}		
				});
										
	//定义修改的formpanel
	
					var editform=new Ext.FormPanel({
				    	id:'editform',
				       // width : 400,
				         	
				        height:360,			      				       
				        frame:true,
				        bodyStyle:'padding:10px 10px 0',	
				        items: [{
					            layout:'column',
					            items:[{
					                columnWidth:.43,
					                labelWidth:75,					              
					                layout: 'form',
					                labelAlign:"right",
					                items: [{
						                    xtype:'textfield',
						                    fieldLabel: '物资编号',
						                    //readOnly: true,
						                    name: 'iso',
						                    width:100,
						                    id: 'tmnums1'
							                },combomainclass1,combosubclass1,{
							                    xtype:'textfield',
							                    fieldLabel: '名称',
							                    //readOnly: true,
							                    name: 'name',
							                    width:100,
							                    id: 'tnames1'
							                }
							                
							        ]},{
								columnWidth:.07,
								labelAlign:"left",
								bodyStyle:'padding:10px 10px 0',
								html:'<font color="#FF0000">**<br><br>**<br><br>**<br><br>**<br><br></font>',
								border:false
							},
							        {columnWidth:.43,
					                layout: 'form',
					                labelWidth:80,
					                labelAlign:"right",
					                items: [comnameCombo1,{
						                    xtype:'textfield',
						                    fieldLabel: '最低库存量',
						                    //readOnly: true,
						                    width:100,
						                    name: 'mini',
						                    id: 'tminis1'
					                		},{
							                   xtype:'textfield',
							                    fieldLabel: '规格',
							                    //readOnly: true,
							                    name: 'guige',
							                    width:100,
							                    id: 'tguiges1'
							                },edithqCombo
					                ]},{
								columnWidth:.07,
								labelAlign:"left",
								bodyStyle:'padding:10px 10px 0',
								html:'<font color="#FF0000">**<br><br>**<br><br><br><br>**<br><br></font>',
								border:false
							},{
						                columnWidth:1,					              
						                layout: 'form',
						                labelWidth:75,
						                labelAlign:"right",
						                items: [{
												xtype : 'textfield',
												id : 'torders1',
												fieldLabel : '订货号',
												//readOnly: true,
												width:100
										}]
								      },{columnWidth:.31,					              
						                layout: 'form',
						                labelWidth:75,
						                labelAlign:"right",
						                items:[kucombo1]
						                },{
						                columnWidth:.125,					              
						                layout: 'form',
						                labelAlign:"right",
						                hideLabels:true,
						                items:[cencombo1]
						                },{
						                columnWidth:.125,					              
						                layout: 'form',
						                labelAlign:"right",
						                hideLabels:true,
						                items:[cen1combo1]
						                },{
						                columnWidth:.125,					              
						                layout: 'form',
						                labelAlign:"right",	
						                hideLabels:true,
						                items:[weicombo1]
						                },{
										columnWidth:.07,
										labelAlign:"left",
										bodyStyle:'padding:10px 10px 0',
										html:'<font color="#FF0000">**<br><br></font>',
										border:false
										},{
					                columnWidth:1,					              
					                layout: 'form',
					                labelWidth:75,
					                labelAlign:"right",
					                items: [{
											xtype : 'textarea',
											id : 'tremarks1',
											fieldLabel : '备注',
											//readOnly: true,
											width:325,
											height: 100
									}]
							}]
				            
				        }],
				      
				         buttonAlign:"center",
         buttons: [{
	                text:'修改',
	                id:'yes',
	                //type:'submit',
	                handler:function(){
	                       var idmnum=Ext.get("tmnums1").dom.value;
				           var idmnumReg=/^[0-9a-zA-Z]+$/;
	
				           var tnamejs=Ext.get("tnames1").dom.value;
				           var tnamejsReg=/^[0-9a-zA-Z\u4e00-\u9fa5\.]+$/;
				           
				           var tminijs=Ext.get("tminis1").dom.value;
				           var tminijsReg=/^(0|[1-9][0-9]*)$/;
				           
	                       var remark=Ext.get("tremarks1").dom.value;
					       var remarkReg=/^\d+(\.\d+)?$/;
					       
					       var order=Ext.get("torders1").dom.value;
					       var hq=edithqCombo.getValue();
					       //alert(hq);
		                   if(Ext.get("tmnums1").dom.value.trim()==""){
						     	        Ext.MessageBox.alert("警告！","物资编号必填项！");
						     	}else if(Ext.get("tnames1").dom.value=="" ){
								        Ext.MessageBox.alert("警告！",'请填写工具名称!');
						     	}else if(Ext.get("tpclass1").dom.value=="请选择大类" ){
								        Ext.MessageBox.alert("警告！",'请选择一种大类!');
						     	}else if(Ext.get("tbclass1").dom.value=="选择小类" ){
								        Ext.MessageBox.alert("警告！",'请选择一种小类!');
						     	}else if(Ext.get("tcoms1").dom.value=="请选择厂商"||Ext.get("tcoms1").dom.value=="" ){
								        Ext.MessageBox.alert("警告！",'请选择工具的生产厂商!');
						     	}else if(hq == '是'&& order == '' ){
								        Ext.MessageBox.alert("警告！",'高质工具必须输入订货号！');
						     	}else if(locations == ''){
								        Ext.MessageBox.alert("警告！",'请选择工具存放位置!');
						     	}else if(idmnum!="" && !idmnumReg.test(idmnum)){
								        Ext.MessageBox.alert("警告！",'物资编号由英文字符、数字组成，请修改！');
								}else if(tnamejs!="" && !tnamejsReg.test(tnamejs)){
								        Ext.MessageBox.alert("警告！",'工具名称由中英文字符、数字和点组成，请修改！');
								}else if(tminijs =="" || !tminijsReg.test(tminijs)){
								        Ext.MessageBox.alert("警告！",'最低库存量必须是一个非负整数，请修改！');
								}/*else if(stanlifejs!="" && !stanlifeReg.test(stanlifejs)){
								        alert('标准年限请填入阿拉伯数字');
								}*/else if(Ext.get("tnames1").dom.value.trim().length>30){
									    Ext.MessageBox.alert('数量不允许大于30字！');
								}else if(Ext.get("tremarks1").dom.value.trim().length>60){
									   Ext.MessageBox.alert('备注不允许大于60个字！');
								}
								else{   keyBoard.hide();
									 	 modifySaveFn();
								}
						}
                },{
					text: '取消',
					handler: function(){
					 if(insertormodify==0){insertormodify=1;}
					 Ext.getCmp("wind").hide();
					 Ext.getCmp("editform").form.reset();
					keyBoard.hide();
					$('#tguiges').keypad('destroy');
					}
                },{
					text: '键盘',
					id:'keyC',
					handler:function(){
						if(keyBoardWin.isVisible()){
							keyBoard.hide();
						}else{						 
							keyBoard.show();
						}
			          }//键盘
		            }
           	]
					});
					
					
					
					
					//定义.................显示.................工具信息弹出窗口
				var wind = new Ext.Window({
					    			id:'wind',
					    			modal:true,
					                width:500,
					                height:400,
					                //closable:false,
					                listeners:{
											  "hide":function(){
											    if(keyBoardWin.isVisible()){									    
													keyBoard.hide();
												}
												  if($.keypad._keypadShowing){				  
													$('#tguiges1').keypad('destroy');
												  }
											 }
											 },	
					                closeAction:'hide',
					                plain: true,
					                title:"<p align='center'>修改工具信息</p>",    
									bodyBorder:true,
					                items:editform               
					    }); 	
	
			    	  
						
						
        
					    
	function editFn(){
		//alert("c");
		var record =sm.getSelections();// 返回值为 Record 类型            
	    var havaPasser = "";
		if(record.length != 0){
			havaPasser = record[0].get("passer") + "";
		}
		if(record.length==0){
            Ext.MessageBox.alert("警告！",'请选择一条记录!');
            return;
        }else if(record.length!=1){
        	Ext.MessageBox.alert("警告！",'您最多选择一条记录进行操作！');
            return;
        }/*else if(record[0].get("creator")!=userid && !isProCharger()){
            alert('提示:您不具有修改该文件的权限!');
            return;
        }else if(havaPasser != "" && !isProCharger()){
        	alert('提示:文件已经批准通过，只有项目负责人有权修改！');
        	return;
        }*/else{   
			    	  	if(!Ext.getCmp("wind").isVisible()){
			    	  	keyBoard.hide();
			    	  	
						Ext.getCmp("tmnums").on("focus", function(){keyBoardInput = Ext.getDom("tmnums");});
						Ext.getCmp("tminis").on("focus", function(){keyBoardInput = Ext.getDom("tminis");});
						Ext.getCmp("tnames").on("focus", function(){keyBoardInput = Ext.getDom("tnames");});
						Ext.getCmp("tguiges").on("focus", function(){keyBoardInput = Ext.getDom("tguiges");});
						Ext.getCmp("torders").on("focus", function(){keyBoardInput = Ext.getDom("torders");});
						Ext.getCmp("tremarks").on("focus", function(){keyBoardInput = Ext.getDom("tremarks");});
								    
						Ext.getCmp("tmnums1").setValue(record[0].get("mnum"));
						combomainclass1.setValue(record[0].get("pclass"));
						combosubclass1.setValue(record[0].get("bclass"));
						comnameCombo1.setValue(record[0].get("companyid"));
						finallocal='';
						var editarray = new Array();
						editarray = (record[0].get("location")).split("-");
						if(editarray.length==0){
						finallocal='您所选的工具没有存放位置！'
						Ext.getCmp("tku1").setValue(0);
						Ext.getCmp("tcen1s").setValue(0);
						Ext.getCmp("tcen11").setValue(0);
						Ext.getCmp("twei1").setValue(0);
						}
						else{
						for(var i=0;i<editarray.length;i++){
						finallocal +=editarray[i]+'-';
						}
						var lastlocal = finallocal.lastIndexOf('-');
						finallocal = finallocal.slice(0,lastlocal);
						locations = finallocal;
						Ext.getCmp("tku1").setValue(editarray[0]);
						Ext.getCmp("tcen1s").setValue(editarray[1]);
						Ext.getCmp("tcen11").setValue(editarray[2]);
						Ext.getCmp("twei1").setValue(editarray[3]);
						}
						
						Ext.getCmp("tminis1").setValue(record[0].get("mini_qs"));
						Ext.getCmp("tnames1").setValue(record[0].get("name"));
						Ext.getCmp("tguiges1").setValue(record[0].get("spec"));
						edithqCombo.setValue(record[0].get("hq"));
						Ext.getCmp("torders1").setValue(record[0].get("order_num"));
						Ext.getCmp("tremarks1").setValue(record[0].get("remark"));
						insertormodify=insertormodify+1;
			            Ext.getCmp("yes").show();
			            Ext.getCmp("keyC").hide();
						Ext.getCmp("wind").show();//修改窗口显
						$('#tguiges1').keypad();
						}	
    	     }
		
	}
		
	function insertSaveFn(){
	            var mnum = Ext.get("tmnum").dom.value.trim();
		    	var pclass = Ext.get("tpclass").dom.value.trim();
				var bclass = Ext.get("tbclass").dom.value.trim();
				var name = Ext.get("tname").dom.value.trim();
				var location = locations;
				//alert(locations);
				var spec = Ext.get("tguige").dom.value.trim();
				//var company = Ext.get("tcom").dom.value.trim();
				var companyid = comnameCombo.getValue();
				var nct_count_in = Ext.get("tnct").dom.value.trim();
				var uct_count_in = Ext.get("tuct").dom.value.trim();
				var hq = hpCombo.value;
				//alert(hq);
				var order_num = Ext.get("torder").dom.value.trim();
				var mini_qs = Ext.get("tmini").dom.value.trim();
				var use_freq = Ext.get("tfreq").dom.value.trim();
				var remark = Ext.get("tremark").dom.value.trim();
				Ext.showProgressDialog();
				Ext.Ajax.request({
					url:'logic_gen.jsp',
					params : {
						type : 'addba',
						mnum : mnum,
						pclass : pclass,
						bclass : bclass,
						name : name,
						location: location,
						spec : spec,
						companyid : companyid,
						nct_count_in : nct_count_in,
						uct_count_in : uct_count_in,
						hq : hq,
						mini_qs : mini_qs,
						order_num : order_num,
						use_freq : use_freq,
						remark: remark
					},
					success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)==1) {
							customQueryFn();
							Ext.getCmp("inform").form.reset();
							Ext.getCmp("window").hide();
							cencombo.setDisabled(true),
							cen1combo.setDisabled(true),
							weicombo.setDisabled(true),
							Ext.MessageBox.alert( "提示","新增工具保存成功！");
							locations = '';
							//Ext.getCmp("inform").form.reset();
						}else if(parseInt(resp.responseText)==0){
							Ext.MessageBox.alert( "提示","保存失败,保存时出现异常！");
							//locations = '';
						}else if(parseInt(resp.responseText)==2){
							Ext.MessageBox.alert( "高质工具添加提示","保存失败,高质工具厂商、订货号不能完全相同！");
							//locations = '';
						}else if(parseInt(resp.responseText)==3){
							Ext.MessageBox.alert( "非高质工具添加提示","保存失败,非高质工具物资编号和厂商不能完全相同！");
							//locations = '';
						}else if(parseInt(resp.responseText)==4){
							Ext.MessageBox.alert( "提示","保存失败,物资编号相同时,必须同为高质工具或非高质工具！");
							//locations = '';
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert("提示","保存失败，数据传送中出现问题！");
						//locations = '';
					}
	
				});
					//	
	}
	
	//对数据进行修改
	function modifySaveFn(){
	 Ext.MessageBox.alert("确认",'确认修改吗？');
	            var mnum = Ext.get("tmnums1").dom.value.trim();
	            var record =sm.getSelections();
	            var finalmnum = record[0].get("mnum") + record[0].get("companyid") + record[0].get("order_num");
	            //alert(finalmnum);
		    	var pclass = Ext.get("tpclass1").dom.value.trim();
				var bclass = Ext.get("tbclass1").dom.value.trim();
				var name = Ext.get("tnames1").dom.value.trim();
				var location = locations;
				//alert(locations);
				var spec = Ext.get("tguiges1").dom.value.trim();
				//var company = Ext.get("tcoms1").dom.value.trim();
				var companyid = comnameCombo1.getValue();
				//var nct_count_in = Ext.get("tncts1").dom.value.trim();
				//var uct_count_in = Ext.get("tucts1").dom.value.trim();
				var hq = Ext.get("thqs1").dom.value.trim();
				var finalhq = record[0].get("hq");
				//alert(hq);
				var order_num = Ext.get("torders1").dom.value.trim();
				var mini_qs = Ext.get("tminis1").dom.value.trim();
				var remark = Ext.get("tremarks1").dom.value.trim();
				Ext.showProgressDialog();
				Ext.Ajax.request({
					url:'logic_gen.jsp',
					params : {
						type : 'modifyba',
						mnum : mnum,
						pclass : pclass,
						bclass : bclass,
						name : name,
						location: location,
						spec : spec,
						companyid : companyid,
						//nct_count_in : nct_count_in,
						//uct_count_in : uct_count_in,
						hq : hq,
						mini_qs : mini_qs,
						remark: remark,
						order_num: order_num,
						finalmnum: finalmnum,
						id: record[0].get("id"),
						finalhq : finalhq
					},
					success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)==1) {
						    customQueryFn();
							Ext.getCmp("editform").form.reset();
							Ext.getCmp("wind").hide();
							Ext.MessageBox.alert("提示", "工具信息修改成功！");
							
							locations = '';
							insertormodify = 0;
							finallocal='';
						}else if (parseInt(resp.responseText)==0){
							Ext.MessageBox.alert( "提示","工具信息修改时发生异常，请检查数据重新尝试！");
							locations = '';
							insertormodify = 0;
							finallocal='';
						}
						else if (parseInt(resp.responseText)==2){
							Ext.MessageBox.alert( "高质工具修改提示","修改失败,高质工具厂商、订货号不能完全相同！");
							//locations = '';
							insertormodify = 0;
							finallocal='';
						}
						else if(parseInt(resp.responseText)==3){
							Ext.MessageBox.alert( "非高质工具修改提示","修改失败,非高质工具物资编号和厂商不能完全相同！");
							//locations = '';
						}else if(parseInt(resp.responseText)==4){
							Ext.MessageBox.alert( "提示","保存失败,物资编号相同时,必须同为高质工具或非高质工具！");
							//locations = '';
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert( "提示","工具信息修改失败，请检查数据重新尝试！");
						locations = '';
						insertormodify=0;
						finallocal='';
					}
				});		
	}
	//点击查询
	function customQueryFn(){
		Ext.showProgressDialog();
		hpObj.extraParams.filter = search.getFilter();
		hpObj.extraParams.order = search.getOrder();
		hpObj.extraParams.type = "QUERY";
		//var pagebar = Ext.getCmp("pagebar");
		
    	var obj={params:{start:start,limit:limit}};	
		if(ds.load(obj)){
			Ext.hideProgressDialog();
		}
		search.getQueryText();
	}
	
	//导入窗口
	 var input_win = new Ext.Window({ 
		         id:'input_win',
				 title:'<p align="center">文件导入</p>',
				 layout:'form',
				 modal:true,  
				 width:310,
				 height:160,
				 plain:true,				
				 buttonAlign:"center",
			 	 bodyBorder:true,
				 resizable:false,
				 listeners:{
				  "hide":function(){
				    if(keyBoardWin.isVisible()){									    
						keyBoard.hide();
					}
				  }
				},	
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
				 buttons : [{
						  text : '上传文件',
						  id:'upload_file',						
						  handler : function() {
								 var value = document.getElementById("input_id").value;
								 if(value == ''){
								   alert("请选择要上传的文件!");
								 }else if(value.indexOf(".xls")== -1) {
								   alert("上传的文件只能为excel表格,请修改!");
								 }else{		
								    var parts = value.split(/\/|\\/);
									if (parts.length == 1) {
									  filename = parts[0];
									}else{
									  filename = parts.pop();
									}	
									 Ext.getCmp("input_panel").getForm().submit({
								       waitMsg:'上传中，请等待...', 
								       waitTitle:' ',
								       method: 'post',      
								       url:'logic_gen.jsp?type=UPLOAD',         
								       success: function(form, action){
								           	    alert( "提示:上传成功!");												
												Ext.getCmp("input_file").setVisible(true);
												Ext.getCmp("upload_file").setVisible(false);
								          },
								       failure: function(form, action){             
								                Ext.hideProgressDialog();
											    alert("提示:上传失败！请重试！");
								          }
								      }); 
								}		
							}
					},{	  text:'导入文件',						
						    id:'input_file',
					       hidden:true,
						  handler : function() {							      
						      Ext.showProgressDialog();
										Ext.Ajax.request({
											url : 'logic_gen.jsp',
											success : function(resp) {				
												Ext.hideProgressDialog();												
												if (parseInt(resp.responseText)==1) {
													alert( "提示:导入成功!");
													Ext.getDom('input_id').outerHTML  = Ext.getDom('input_id').outerHTML;																								
												}else if(parseInt(resp.responseText)==0){
													alert("导入失败！请重试！");
												}else if(parseInt(resp.responseText)==2){
													alert("excel文件中有最低库存量格式不正确项,请修改使之为正整数后再导入！");														
												}else if(parseInt(resp.responseText)==3){
													alert("excel文件中有最低库存量未填写项,请填写使之为正整数后再导入！");													
												}else if(parseInt(resp.responseText)==4){
													alert("excel文件中有新工具库存量格式不正确项,请修改使之为正整数后再导入！");														
												}else if(parseInt(resp.responseText)==5){
													alert("excel文件中有旧工具库存量格式不正确项,请修改使之为正整数后再导入！");													
												}else if(parseInt(resp.responseText)==6){
													alert("excel文件中有库存位置格式不正确项,请修改后再导入！");													
												}else if(parseInt(resp.responseText)==7){
													alert("excel文件中有库存位置未填写项,请填写后再导入！");
												}else if(parseInt(resp.responseText)==8){
													alert("excel文件中有大类未填写项,请填写后再导入！");													
												}else if(parseInt(resp.responseText)==9){
													alert("excel文件中有小类未填写项,请填写后再导入！");	
												}else if(parseInt(resp.responseText)==10){
													alert("excel文件中有大类与软件中所分大类不同项,请修改后再导入！");	
												}else if(parseInt(resp.responseText)==11){
													alert("excel文件中有小类与软件中所分小类不同项,请修改后再导入！");	
												}else if(parseInt(resp.responseText)==12){
													alert("excel文件中有大类、小类对应关系与软件中不同项,请修改后再导入！");	
												}else if(parseInt(resp.responseText)==13){
													alert("excel文件中有物资编码为空项,请填写后再导入！");	
												}else if(parseInt(resp.responseText)==14){
													alert("excel文件中有厂商为空项,请填写后再导入！");	
												}else if(parseInt(resp.responseText)==15){
													alert("excel文件中有名称为空项,请修改后再导入！");	
												}else if(parseInt(resp.responseText)==16){
													alert("excel文件中有高质属性填写不符合条件项,请修改后再导入！");	
												}else if(parseInt(resp.responseText)==17){
													alert("excel文件中有高质工具订货号未填写项,请填写后再导入！");	
												}
												Ext.getCmp("input_file").setVisible(false);
												Ext.getCmp("upload_file").setVisible(true);	
											},
											failure : function() {
												Ext.hideProgressDialog();
												alert("导入失败！请重试！");
											},
											params : {
												type : 'INPUT'											
											}
										});					         
								 }
				   }, {
						 text : '取消',						  
						 handler : function() {
						     input_win.hide();
						 }
					 }]						
		  });
});

