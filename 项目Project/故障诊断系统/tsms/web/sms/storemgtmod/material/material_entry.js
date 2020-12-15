var sm = new Ext.grid.CheckboxSelectionModel();
Ext.onReady(function(){
	Ext.QuickTips.init();//支持tips提示
	Ext.form.Field.prototype.msgTarget='qtip';//提示的方式，枚举值为"qtip","title","under","side"
	//定义表单映射
	var nowid = '';
	var inrecord = new Ext.data.Record.create(
		{
		name:'ordernum'
		},{
		name:'mnum'
		},{
		name:'classname'
		},{
		name:'name'
		},{
		name:'spec'
		},{
		name:'kuname'
		},{
		name:'shelf'
		},{
		name:'validate'
		},{
		name:'guaranteedate'
		},{
		name:'company'
		},{
		name:'id'
		},{
		name:'incount'
		},{
		name:'producetime'
		},{
		name:'inperson'
		},{
		name:'neworold'
		},{
		name:'creattime'
		}
	);
	var data = new Array();
	
	var ds= new Ext.data.Store({
        proxy: new Ext.data.MemoryProxy(data),
        reader: new Ext.data.JsonReader({
            		totalProperty:'totalProperty',
            		root:'rows'
        		},inrecord)
    });
    
	//定义库名
	var ku = [['海波库','海波库']];
	var storeku = new Ext.data.SimpleStore({fields:['value','values'],data:ku});
	var kucombo=new Ext.form.ComboBox({				
				name:'tku',
				id:"tku",
				displayField:'values',
				valueField:'value',
				store: storeku,
				triggerAction: 'all',
				lazyRender:true,
				width:100,
				value:'海波库',
				readOnly: true,
				fieldLabel:'库名',
				typeAhead: true,
				selectOnFocus:true,
		    	emptyText:'库',
				allowBlank:true,
				mode:'local',
				editable:false
				
	});
	
	//定义厂商映射
      var record_com = new Ext.data.Record.create([
      {name:'id'},
      {name:'company'}]
      );
	  var storeCom = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy({url:"/tsms/web/sms/bscinfmod/company/logiccompany.jsp?type=QUERYALL"}), // 数据源
	     reader: new Ext.data.JsonReader({
	         id:'id',
		     root:"filedata"
		       },record_com),
		     remoteSort: true
	     });
	  storeCom.load();
	  
     //添加定义厂商combox
	 var ComboBoxCom=new Ext.form.ComboBox({
			fieldLabel:'生产厂商',
	     	name:'company',
			id:"company",
			store:storeCom, 
			displayField:'company',
			valueField:'id',              
			triggerAction: 'all',
			width:120,
			emptyText:'请选择厂商',
		//	forceSelection:true,
			allowBlank:true,
			mode:'local',
			typeAhead: true,
			editable:true
	});
		//厂商定义完毕
	
	//定义采购周期
	var cycledate = [['1'],['2'],['3'],['6']];
	var storecycle = new Ext.data.SimpleStore({
		fields:['cycleshow'],
		data:cycledate
	});
	
	
	var buycyclecom = new Ext.form.ComboBox({
		fieldLabel:'采购周期(月)',
		name:'cycledate',
		id:"cycledate",
		store: storecycle,
		displayField:'cycleshow',
		triggerAction: 'all',
		forceSelection:true,
		lazyRender:true,
		width:124,
		readOnly:true,
		allowBlank:true,
		mode:'local',
		editable:false
	});
	//定义保质期
	var guaranteedate = [['6'],['12'],['18'],['24'],['60']];
	var guaranteestore = new Ext.data.SimpleStore({
		fields:['guaranteedate'],
		data:guaranteedate
	});
	var guaranteeCombox = new Ext.form.ComboBox({
		fieldLabel:'保质期',
		name:'ttguaranteedate',
		id:"ttguaranteedate",
		store: guaranteestore,
		displayField:'guaranteedate',
		triggerAction: 'all',
		forceSelection:true,
		lazyRender:true,
		width:124,
		readOnly:true,
		allowBlank:true,
		mode:'local',
		editable:false
	});
	//保质期定义完毕
	//定义复检月份
	var fujiandata =  [['1'],['2'],['3'],['4'],['5'],['6'],['7'],['8'],['9'],['10'],['11'],['12']];
	var fujianstore = new Ext.data.SimpleStore({
		fields:['fujiandate'],
		data:fujiandata
	});
	var fujianCombox = new Ext.form.ComboBox({
		fieldLabel:'复检后可用月份',
		name:'fujian',
		id:"fujian",
		store: fujianstore,
		displayField:'fujiandate',
		triggerAction: 'all',
		forceSelection:true,
		lazyRender:true,
		width:130,
		readOnly:true,
		allowBlank:true,
		mode:'local',
		editable:false
	});
	//复检combox定义完毕
	
	
	
	//定义货架号
	var shelf = [['A','A'],['B','B'],['C','C'],['D','D'],['E','E'],['F','F'],['G','G'],['H','H'],['I','I'],['J','J']
	,['K','K'],['L','L'],['M','M'],['N','N'],['O','O'],['P',''],['Q','Q'],['R','R'],['S','S'],['T','T'],['U','U']
	,['V','V'],['W','W'],['X','X'],['Y','Y'],['Z','Z']];
	var storeshelf = new Ext.data.SimpleStore({fields:['value','values'],data:shelf});
	var shelfcombo=new Ext.form.ComboBox({				
				name:'tshelf',
				id:"tshelf",
				displayField:'values',
				valueField:'value',
				store: storeshelf,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				fieldLabel:'货架号',
				typeAhead: true,
				selectOnFocus:true,
		    	emptyText:'货架号',
				allowBlank:true,
				mode:'local',
				editable:false
				
	});
	
	//定义层
	var ceng = [['1','1'],['2','2'],['3','3'],['4','4'],['5','5'],['6','6'],['7','7'],['8','8'],['9','9'],['10','10']];
	var storeceng = new Ext.data.SimpleStore({fields:['value','values'],data:ceng});
	var cengcombo=new Ext.form.ComboBox({				
				name:'tceng',
				id:"tceng",
				displayField:'values',
				valueField:'value',
				store: storeceng,
				triggerAction: 'all',
				lazyRender:true,
				width:50,
				readOnly: true,
				fieldLabel:'层',
				typeAhead: true,
				selectOnFocus:true,
		    	emptyText:'层',
				allowBlank:true,
				mode:'local',
				editable:false
				
	});
	//定义材料类别
	var materialclass = [['油漆'],['油墨'],['电镀'],['盐浴焊'],['热处理'],['其它']];
	var storeclass = new Ext.data.SimpleStore({
		fields:['classshow'],
		data:materialclass
	});
	
	var ComboBoxClass = new Ext.form.ComboBox({
		fieldLabel:'材料类别',
		name:'classname',
		id:"classname",
		store: storeclass,
		displayField:'classshow',
		triggerAction: 'all',
		forceSelection:true,
		lazyRender:true,
		width:100,
		emptyText:'请选择材料类别',
		readOnly:true,
		allowBlank:true,
		mode:'local',
		editable:false
	});
	       
	  //定义规格
			  var hpObj={url:'logicmaterial_entry.jsp',method: 'POST',extraParams:{}};
              var storespec = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)), // 数据源type=QUERYALLNUM
			     reader: new Ext.data.JsonReader({
			         id:'spec',
				     root:"filedata",
				     fields:[{name: 'spec'}]    // 解析
				       }),
				     remoteSort: true
			     });
			  storespec.load();
			  
	      var Combospec=new Ext.form.ComboBox({
				fieldLabel:'规格',
				name:'spec',
				id:"spec",				
				displayField:'spec',
				store: storespec,
				triggerAction: 'all',
				lazyRender:true,
				width:85,
				readOnly: true,
				typeAhead: true,
				selectOnFocus:true,
				emptyText:'请选择规格',
				allowBlank:true,
				mode:'local',
				editable:true		
				});
				//定义厂商
				 var hpOb={url:'logicmaterial_entry.jsp',method: 'POST',extraParams:{}};			
	             var storecom = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpOb)), // 数据源
			     reader: new Ext.data.JsonReader({
			         id:'ordercom',
				     root:"filedata",
				     fields:[{name: 'company'}]    // 解析
				       }),
				     remoteSort: true
			     });
			     
			  
	    var Combocom=new Ext.form.ComboBox({
				fieldLabel:'生产厂商',
				name:'incom',
				id:"incom",				
				displayField:'company',
				store: storecom,
				triggerAction: 'all',
				lazyRender:true,
				readOnly: true,
				width:85,
				listeners:{
					select:function(){
						Combospec.reset();
						Ext.getCmp('div1').hide();
						checkcompany();
					}
				},
				typeAhead: true,
				selectOnFocus:true,
				emptyText:'请选择厂商',
				allowBlank:true,
				mode:'local',
				editable:true		
				});	
				
	        //定义物资编码combox
	          var storeMnum = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy({url:"/tsms/web/sms/storemgtmod/material/logicmaterial_entry.jsp?type=QUERYALL"}), // 数据源
			     reader: new Ext.data.JsonReader({
			         id:'mnumnum',
				     root:"filedata",
				     fields:[{name: 'mnum'}]    // 解析
				       }),
				     remoteSort: true,
				     sortInfo: {field: 'mnum', direction: 'DESC'}
			     });
			   storeMnum.load();
			  
			 
			var mnumCombo=new Ext.form.ComboBox({
					fieldLabel:'材料名称',
					name:'tmnum',
					id :'tmnum',		
					store:storeMnum, 
					displayField:'mnum',
					//valueField:'id',
					 triggerAction: 'all',
					 width:100,
					 listeners:{
					 select: function(){
					 Ext.getCmp('details').collapse(false);
					 Combocom.reset();
					 Combospec.reset();
					 Ext.getCmp('div').hide();//规格
					 Ext.getCmp('div1').hide();//厂商
					 chexExist();
					 }
					},
					emptyText:'请选择材料名称',
					forceSelection:true,
					allowBlank:true,
					mode:'local',
					typeAhead: true,
					//transform:'AList',
					editable:true
			});
			
			mnumCombo.on('blur',function(){   
              this.setValue(this.el.dom.value);   
             });  
      
			 
	      
    var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
    var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});
    var toolthr=new Ext.Toolbar.Separator({id:'toolthr'});//按钮之间的竖线 
    var toolfou=new Ext.Toolbar.Separator({id:'toolfou'});
    
    //定义toolbar
    var toolbar = new Ext.Toolbar({ 
   						renderTo: 'toolbar',
   						name : 'toolbar',
   						//region:'center',
				        // layout:'fit',
						items:[{text:"保存",
				                id:'save',
				                handler:saveFn1,
				                iconCls:"save",
				                tooltip:"将保存材料保存至表单"
				                },toolone,{
				       			text:"新类型",
				       			id :'add',
				       			handler:addtoolFn,
				       			tooltip:"添加新类型材料信息",//..........................................................................................
				       			iconCls:"add"
				       			},//tooltwo,
				       			toolthr,{
				       			text:"重置",
				       			id :'cancel',
				       			tooltip:"重置表单",
				       			handler:function(){
				       			simple.form.reset();
				       			var data=getNowtime();
     							Ext.getCmp("tcreattime").setValue(data);
     							Ext.getCmp("admin").setValue(realname);
     							Ext.getCmp('div').hide();
		                        Ext.getCmp('div1').hide();
							    },//重置表单
				       			iconCls:"refresh"
				       			},toolfou]
	});
	//定义添加材料信息窗口
	var formpanel = new Ext.FormPanel({
		id:'formpanel',
        labelWidth:85, // label settings here cascade unless overridden
        columnWidth:1,
        width:453,
        height:200,	
        layout:'form',
        hideLabels:false,
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:0px 6px 0',
        items:[{
        	layout:'column',
        	items:[{
        		columnWidth:.5,
                layout:'form',
                labelAlign:"right",		               
                items:[{
                columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '物资编码',
                    name: 'mnum',
                    id: 'mnum'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			                }]
        	},{
                columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [ComboBoxClass]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			                }]
        	},{
                columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料名称',
                    name: 'name',
                    id: 'name'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			                }]
        	},{
                columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '计量单位',
                    name: 'measure',
                    id: 'measure'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			                }]
        	},{
                columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '规格',
                    name: 'ttspec',
                    id: 'ttspec'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			                }]
        	}]
        	},{
        		columnWidth:.5,
                layout:'form',
                labelAlign:"right",
                items:[{
	                columnWidth:1,					              
	                layout: 'column',
	                items:[{
	                	columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [buycyclecom]
	                },{ columnWidth: .1,
		                bodyStyle:'padding:5px 0px 0',
		                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
		                border:false
			                }]
                },{
	                columnWidth:1,					              
	                layout: 'column',
	                items:[{
	                	columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [ComboBoxCom]
	                },{ columnWidth: .1,
		                bodyStyle:'padding:5px 0px 0',
		                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
		                border:false
			                }]
                },{
	                columnWidth:1,					              
	                layout: 'column',
	                items:[{
	                	columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [guaranteeCombox]
	                },{ columnWidth: .1,
		                bodyStyle:'padding:5px 0px 0',
		                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
		                border:false
			                }]
                },{
                columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.9,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
                    xtype:'textfield',
                    fieldLabel: '月用量',
                    name: 'monthuse',
                    id: 'monthuse'
                }]
                },{ columnWidth: .1,
	                bodyStyle:'padding:5px 0px 0',
	                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
			                }]
        	},{
	                columnWidth:1,					              
	                layout: 'column',
	                items:[{
	                	columnWidth:.9,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [{
	                    xtype:'textfield',
	                    fieldLabel: '材料型号',
	                    name: 'modelnum',
	                    id: 'modelnum'
                			}]
	                },{ columnWidth: .1,
		                bodyStyle:'padding:5px 0px 0',
		                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
		                border:false
			                }]
                }]
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
		                    height:90			                   
							    }]
		            }]
        	}]
	});
	//将formpanel渲染到窗体上
	var window = new Ext.Window({
		id:'win',
		modal:true,                                         
        width:540,
        height:270,
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
        		items:[formpanel]
    		})
                		
		],
		buttons:[{
			id:'savebtn',
            text:'保存',
            handler:saveFn
		},{
			id:'reset',
            text:'重置',
            type: 'reset',
            handler:resetFn
		},{
			id:'cancle',
            text:'取消',
            handler:cancleFn
		}]
	});
	//定义入库窗体和明细窗体
             var simple = new Ext.FormPanel({
			        labelWidth: 85,
			        //columnWidth:.90,
			        region:'north',
			        renderTo:'querydiv',
			        frame:true,
			        height:200,
			        layout:"form",
			        hideLabels:false,
			        //width:600,
			        autoWidth :true,
			        autoHeight :false,
			        border:false,
			        labelAlign:"right",
			        //bodyStyle:'padding:10px 10px 0',
			        autoScroll:true,
			        items:[{
			        	xtype:"panel",
		                layout:"column",
				        items:[{
				        	 width:285,
			                 layout: 'form',
			                 labelWidth: 75,
			                 items:[mnumCombo,
			                 	{layout:"form",
							    id : 'div',
							    hidden: true,
			                	items:[Combocom]//厂商
					            },{layout:"form",
							    id : 'div1',
							    hidden: true,
			                	items:[Combospec]//规格
					            },
			                 	
				                kucombo,
				                shelfcombo,
				                cengcombo,
				                {
				                fieldLabel:"入库数量",
				                xtype: 'textfield',
				                width:100,
				                name:"tincount",
				                id:"tincount"
				                }]
				        },{
				        	 width:285,
			                 layout: 'form',
			                 labelWidth: 75,
			                 items:[
			                     {fieldLabel:"生产日期",
					         	  xtype: 'datefield',
					         	  width:100,
					         	  name:"producetime",
					         	  id:"producetime",
					         	  format:'Y-m-d'
			                     },
								  {xtype:"panel", 
                                  fieldLabel:"复检入库",
                                  layout:'table',
                                  layoutConfig: {columns:2}, 
                                  isFormField:true, 
				                  items: [{
								         xtype:"radio",
								         boxLabel:"否",//显示在复选框右边的文字
								         id:'newmaterial',
								         name:"material",
								         checked:true
								         
						            	 },{
								         xtype:"radio",
								         boxLabel:"是",
								         id:'oldmaterial',
								         name:"material"
								       
						                 }
						         ]},{fieldLabel:"入库人",
	                		 		  xtype: 'textfield',
	                		 		  readOnly: true,
	                		 		  width:100,
	                		 		  style:"background:#C1C1C1;",
	                		 		  name:"admin",
	                		 		  id:"admin"
	                		 		},{fieldLabel:"入库日期",
	                		 		  xtype: 'textfield',
	                		 		  readOnly: false,
	                		 		  width:100,
	                		 		  style:"background:#C1C1C1;",
	                		 		  name:"tcreattime",
	                		 		  id:"tcreattime"
	                		 		}
			               ]
				        
						},{
				        	 width:250,
			                 layout: 'form',
			                 labelWidth: 95,
			                 items:[fujianCombox]
				        }]
			        },{
			                layout: 'column',
				            width:1500,
				   			items:[{ xtype:'fieldset',
				            title: '明细',     //<font color="#FF0000">*&nbsp;*</font>
				            id: 'details',
				            style:'padding:10px 10px 10px 10px',
				            autoWidth :false,
				          
				            labelAlign:"left",
				            collapsible : true,
				            collapsed :true,
				            //labelWidth: 75,
							items:[{
							 layout:'column',
					         labelWidth:85,
					         items:[{
					         	width:190,					              
				                layout: 'form',
				                labelAlign:"right",
				                items:[{
				                	xtype:'textfield',
				                    fieldLabel: '物资编号',
				                    readOnly: true,
				                    name: 'tmnums',
				                    width:100,
				                    id: 'tmnums'
					                },{
				                    xtype:'textfield',
				                    fieldLabel: '材料类别',
				                    readOnly: true,
				                    name: 'tclassname',
				                    width:100,
				                    id: 'tclassname'
					                },{
					                	xtype: 'textfield',
					                	fieldLabel: '采购周期',
					                	readOnly: true,
					                	name: 'tbuycycle',
					                	id: 'tbuycycle',
					                	width:100
					                   }
				                ]
					         },{
					                width:190,
					                layout: 'form',
					                labelAlign:"right",
					                items: [{
						                    xtype:'textfield',
						                    fieldLabel: '厂&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商',
						                    readOnly: true,
						                    width:100,
						                    name: 'tcompany',
						                    id: 'tcompany'
					                		},{
						                    xtype:'textfield',
						                    fieldLabel: '计量单位',
						                    readOnly: true,
						                    width:100,
						                    name: 'tmeasure',
						                    id: 'tmeasure'
					                		},{
					                	xtype: 'textfield',
					                	fieldLabel: '月用量',
					                	readOnly: true,
					                	name: 'tmonthuse',
					                	id: 'tmonthuse',
					                	width:100
					                   }
					                ]
					         },{
					                width:190,
					                layout: 'form',
					                labelAlign:"right",
					                items: [{
							                    xtype:'textfield',
							                    fieldLabel: '名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称',
							                    readOnly: true,
							                    name: 'tname',
							                    width:100,
							                    id: 'tname'
							                },{
							                   xtype:'textfield',
							                    fieldLabel: '规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格',
							                    readOnly: true,
							                    name: 'tspec',
							                    width:100,
							                    id: 'tspec'
							                },
					                        {
						                	xtype: 'textfield',
						                	fieldLabel: '单次采购量',
						                	readOnly: true,
						                	name: 'tsinglepur',
						                	id: 'tsinglepur',
						                	width:100
						                   }]
					         },      
					        {
					                width:220,					              
					                layout: 'form',
					                labelAlign:"right",
					                items: [{
					                	xtype: 'textfield',
					                	fieldLabel: '最小安全库存',
					                	readOnly: true,
					                	name: 'tminstore',
					                	id: 'tminstore',
					                	width:120
					                   },{
					                	xtype: 'textfield',
					                	fieldLabel: '最大安全库存',
					                	readOnly: true,
					                	name: 'tmaxstore',
					                	id: 'tmaxstore',
					                	width:120
					                   },{
						                	xtype: 'textfield',
						                	fieldLabel: '保质期',
						                	readOnly: true,
						                	name: 'guaranteedate',
						                	id: 'guaranteedate',
						                	width:120
						                   }
							        ]
					         }]
							}]
			        }]}]
             });
	
         
             //定义grid
             var sm = new Ext.grid.CheckboxSelectionModel();
              var grid = new Ext.grid.GridPanel({
		        title: false,
		        id:'filetable',
		        enableColumnMove:true,
		        columns: [sm,
		        	{
			            header: "订单号",
			            dataIndex: "ordernum",
			            width : 120,
			            align:'center',
			            sortable: true
			        },{
			            header: "物资编号",
			            dataIndex: "mnum",
			            width : 70,
			            align:'center',
			            sortable: true
			        },{
			        	header: "类别",
			            width : 70,
			            align:'center',
			            //hidden: true,
			            dataIndex: "classname",
			            sortable: true
			        },{
			            header: "名称",
			            dataIndex: "name",
			            width : 70,
			            align:'center',
			            sortable: true
			        },{
			        	header: "规格",
			            width : 70,
			            align:'center',
			            dataIndex: "spec",
			            sortable: true
			        },{
			        	header: "库名",
			            width : 70,
			            align:'center',
			            dataIndex: "kuname",
			            sortable: true
			        },{
			        	header: "货架号",
			            width : 70,
			            align:'center',
			            dataIndex: "shelf",
			            sortable: true
			        },{
			        	header: "入库人",
			            width : 70,
			            align:'center',
			           // hidden: true,
			            dataIndex: "inperson",
			            sortable: true
			        },{
			        	header: "生产日期",
			            width : 70,
			            align:'center',
			           // hidden: true,
			            dataIndex: "producetime",
			            sortable: true
			        },{
			        	header: "入库数量",
			            width : 70,
			            align:'center',
			           // hidden: true,
			            dataIndex: "incount",
			            sortable: true
			        },{
			        	header: "入库日期",
			            width : 70,
			            align:'center',
			           // hidden: true,
			            dataIndex: "creattime",
			            sortable: true
			        },{
			        	header: "有效期",
			            width : 70,
			            align:'center',
			            dataIndex: "validate",
			            sortable: true
			        },{
			        	header: "保质期",
			            width : 70,
			            align:'center',
			            //hidden: true,
			            dataIndex: "guaranteedate",
			            sortable: true
			        },{
			            header: "厂商",
			            width :70,
			            align:'center',
			            //hidden: true,
			            dataIndex: "company",
			            sortable: true
			        },{
			            header: "复检入库",
			            width :70,
			            align:'center',
			            //hidden: true,
			            dataIndex: "neworold",
			            sortable: true
			        },{
			        	header: "ID",
			            width : 70,
			            align:'center',
			            dataIndex: "id",
			            hidden: true,
			            sortable: true
			        }],
		        ds:ds,
		        sm:sm, //多选框checkbox
		        height: 300,
		        autoHeight :false,
		        autoScroll:true
    });
             //定义viewport
             var Panel = new Ext.Viewport({
             	layout:"border",
				border:false,
				id :'win',
		        state:false,
				//layout:"fit",
				//autoScroll:true,
				items:[simple,
					{items:grid,
					 region:'center',
					 layout:'fit',
					 id:'tablepanel',
					 autoScroll:true,
					 tbar:new Ext.Toolbar({ 
				         id: 'tbar',
				         name: 'tbar',
						items:[
							{ 
					            id:'add-btn',
					            text:"入库", 
					            handler:addFn,
					            iconCls:"add"
			        		},'-',{ 
					            id:'del-btn',
					            text:"删除", 
					            handler:delFn,
					            iconCls:"delete"
			        		}]
		        })
		        /*bbar: new Ext.PagingToolbar({
				    pageSize:limit,
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				})*/		
		          }
				]
             });
             
             			//总库管理员
	 Ext.getCmp("admin").setValue(realname);
              //时间赋值函数        
     var data=getNowtime();
     Ext.getCmp("tcreattime").setValue(data);
     
     function getNowtime(){
		var sUrl = '/tsms/web/sms/storemgtmod/material/logicmaterial_entry.jsp';
		var parms = {type:'getNowtime'};
		var objtp = Ext.util.JSON.decode(callService(sUrl,parms));
		var nowtimeValue = objtp.nowtime;
		return nowtimeValue;
	}
	
	function chexExist(){
		var mnum = mnumCombo.getValue(); 
	    var filter = mnumCombo.getValue();
	   
	    Ext.Ajax.request({
	    	        url:'/tsms/web/sms/storemgtmod/material/logicmaterial_entry.jsp',
					params : {
					type : 'CHEX',
					mnum : mnum
						},
					success : function(resp){
						Ext.hideProgressDialog();
						if(parseInt(resp.responseText)){
							var load = Ext.Ajax.request({
								url:'/tsms/web/sms/storemgtmod/material/logicmaterial_entry.jsp',
								method:'post',
								params:{type:'QUERY',id:filter},//传递物资编号以便获取详细信息
								success:successFn,
								failure:function(){
									Ext.MessageBox.alert('警告','服务器出现错误请稍后再试！');
								}
							});
						}else{
							Combocom.reset();
					        Combospec.reset();
					        Ext.getCmp('div').hide();//规格
					        Ext.getCmp('div1').hide();//厂商
					        
					        
						}
					},
					failure:function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert( "提示:添加失败！");
					}
	    });
	}
	
	//判断company下的spec是否唯一
	function checkcompany(){
		var company = Combocom.getValue();
		var mnum = mnumCombo.getValue();
		//alert(company);
		Ext.Ajax.request({
			url:'/tsms/web/sms/storemgtmod/material/logicmaterial_entry.jsp',
			params : {
				type : 'QUERYSPEC',
				company : company,
				mnum:mnum
					},
			success:successFn1,
			failure:function(){
					  Ext.hideProgressDialog();
					  Ext.MessageBox.alert( "提示:添加失败！");
				}
		});
		
	}
	//物资编码不唯一时，提示选取生产厂商
	function successFn(result,request){
		var objtp = Ext.util.JSON.decode(result.responseText);
		//alert(objtp);
		//alert(objtp.filedata);
		var objt = Ext.util.JSON.encode(objtp.filedata);
		//alert(objt);
		var obj = objt.slice(1,objt.length-1);
		//alert(obj);
		var pos = obj.indexOf("{");
		var i=0;
		while(pos!=-1){
			pos = obj.indexOf("{",pos+1);
			i++;
		}
		if(i>1){
			Combocom.reset();
			Ext.getCmp('div').show();
			Ext.MessageBox.alert('提醒，物资编码不唯一',"您所选的材料物资编码不唯一，请选择不同的材料生产厂商");
			hpOb.extraParams.mnum = mnumCombo.getValue();
			hpOb.extraParams.type = "QUERYALLCOM";
			storecom.load();
		}
		else{
			
			//alert("*****");
			var obj = Ext.util.JSON.decode(obj);
			Combospec.reset();
			Combocom.reset();
			
			Ext.getCmp('div').hide();
			Ext.getCmp('div1').hide();
			
			datailFn(obj);
		}
	}
	//生产厂商不唯一时提示选取材料规格
	function successFn1(result,request){
		var objtp = Ext.util.JSON.decode(result.responseText);
		//alert(objtp);
		//alert(objtp.filedata);
		var objt = Ext.util.JSON.encode(objtp.filedata);
		//alert(objt);
		var obj = objt.slice(1,objt.length-1);
		//alert(obj);
		var pos = obj.indexOf("{");
		var i=0;
		while(pos!=-1){
			pos = obj.indexOf("{",pos+1);
			i++;
		}
		if(i>1){
			Combospec.reset();
			Ext.getCmp('div1').show();
			Ext.MessageBox.alert('提醒，物资编码不唯一',"您所选的材料的生产厂商不唯一，请选择不同的材料规格");
			hpObj.extraParams.mnum = mnumCombo.getValue();
			hpObj.extraParams.company = Combocom.getValue();
			hpObj.extraParams.type = "QUERYALLCOMADNSPEC";
			storespec.load();
			
			Combospec.on("select",function(){
					  var n = obj.indexOf('{');
					    //alert(n);
					    var m = obj.indexOf('}'); 
					    var array = new Array();
					    var i=0;
					    while (n!=-1&&m!=-1){
					    	array[i]= obj.slice(n,m+1);
					    	n = obj.indexOf('{',n+1);
					    	m = obj.indexOf('}',m+1);
					    	array[i] = Ext.util.JSON.decode(array[i]);
					    	//alert(array[i].company+'+'+array[i].order_num);
					    	i++;
					    }
					   var com = Ext.get("spec").dom.value.trim();
					   //var num = Ext.get("innum").dom.value.trim();
					   if(com!='请选择规格'){
					   		for(var j = 0;j<array.length;j++){
						   		if(com==array[j].spec){
						   		datailFn(array[j]);
						   		}
					   		}
			 		   }
			 		   
		});
		}
		else{
			//alert("*****");
			var obj = Ext.util.JSON.decode(obj);
			Combospec.reset();
			//Combocom.reset();
			//Ext.getCmp('div').hide();
			Ext.getCmp('div1').hide();
			datailFn(obj);
		}
	}
	//将明细面板中的field都赋值
	function datailFn(obj){
		var ob = obj;
		nowid = ob.id;
		//alert(nowid);
		Ext.getCmp("tmnums").setValue(ob.mnum);
		Ext.getCmp("tclassname").setValue(ob.classname);
		Ext.getCmp("tbuycycle").setValue(ob.buycycle);
		Ext.getCmp("tcompany").setValue(ob.company);
		Ext.getCmp("tmeasure").setValue(ob.measure);
		Ext.getCmp("tmonthuse").setValue(ob.monthuse);
		Ext.getCmp("tname").setValue(ob.name);
		Ext.getCmp("tspec").setValue(ob.spec);
		Ext.getCmp("tsinglepur").setValue(ob.singlepur);
		Ext.getCmp("tminstore").setValue(ob.minstore);
		Ext.getCmp("tmaxstore").setValue(ob.maxstore);
		Ext.getCmp("guaranteedate").setValue(ob.guaranteedate);
		//var guaranteedate = ob.guaranteedate;
		//ValidTime(guaranteedate);
	}
	//入库时自动生成有效期
	function ValidTime(){
		var data = Ext.getCmp("guaranteedate").getValue();
		if(Ext.get("producetime").dom.value.trim()!=""){
		var datetime = Ext.get("producetime").dom.value.trim();
		var monthtime = data;
		var aDate=datetime.split("-");
		var year=parseInt(aDate[0]);
		//alert(year);
		var monthEnd=aDate[1];
		var month=monthEnd.split("");
		var month1=parseInt(month[0]);
		var month2=parseInt(month[1]);
		var month3=parseInt(month1)*10+month2;
		//alert(month3);
		var date=aDate[2];
		var num=month3+parseInt(monthtime);
		if(data==60){
		year = year+5;
		}
		//alert(num);
		if(monthtime!=12&&monthtime!=24&&monthtime!=36){
			  if(num<10){
			   monthEnd="0"+num;
			   }else if(num<=12&&num>=10){
			    monthEnd=""+num;
			   }else if(num>12&&num<=18){
			   monthtime=num-12;
			   monthEnd="0"+monthtime;
			   year=year+1;
			   }
		  }else if(monthtime==12){
		    year=year+1;
		  }else if(monthtime==24){
		    year=year+2;
		  }else if(monthtime==36){
		    year=year+3;
		  }
		if(date=="31"&&(monthEnd=="04"||monthEnd=="06"||monthEnd=="09"||monthEnd=="11")){
		   date="01";
		   var change_month=monthEnd.split("");
		   var change_month1=parseInt(change_month[0]);		  
		   var change_month2=parseInt(change_month[1]);		   
		   var change_month3=parseInt(change_month1)*10+change_month2;		     
		   var sum=change_month3+1;
		   //alert(sum);
		    if(sum<10){
			   monthEnd="0"+sum;			   
			   }else{
			    monthEnd=""+sum;			   
			   }
		   }
		   if(year%4==0){
		    if(monthEnd=="02"&&(date=="30"||date=="31")){
		      monthEnd="03"
		      date="01";
		    }
		   }else{
		    if(monthEnd=="02"&&(date=="29"||date=="30"||date=="31")){
		      monthEnd="03"
		      date="01";
		    }
		   }
						
	    var  ValidDate=year+'-'+monthEnd+'-'+date;  
		//Ext.getCmp("validtime").setValue(ValidDate);
		return ValidDate;
		}else{
			alert("请填写生产日期");
			return;
		}
		
}

function ValidTime1(){
		var data = Ext.getCmp("fujian").getValue();
		//alert(data);
		if(Ext.get("producetime").dom.value.trim()!=" " && data!=" "){
		var datetime = Ext.get("producetime").dom.value.trim();
		var monthtime = data;
		var aDate=datetime.split("-");
		var year=parseInt(aDate[0]);
		//alert(year);
		var monthEnd=aDate[1];
		var month=monthEnd.split("");
		var month1=parseInt(month[0]);
		var month2=parseInt(month[1]);
		var month3=parseInt(month1)*10+month2;
		//alert(month3);
		var date=aDate[2];
		var num=month3+parseInt(monthtime);
		//alert(num);
		if(monthtime!=12&&monthtime!=24&&monthtime!=36){
			  if(num<10){
			   monthEnd="0"+num;
			   }else if(num<=12&&num>=10){
			    monthEnd=""+num;
			   }else if(num>12&&num<=18){
			   monthtime=num-12;
			   monthEnd="0"+monthtime;
			   year=year+1;
			   }
		  }else if(monthtime==12){
		    year=year+1;
		  }else if(monthtime==24){
		    year=year+2;
		  }else if(monthtime==36){
		    year=year+3;
		  }
		if(date=="31"&&(monthEnd=="04"||monthEnd=="06"||monthEnd=="09"||monthEnd=="11")){
		   date="01";
		   var change_month=monthEnd.split("");
		   var change_month1=parseInt(change_month[0]);		  
		   var change_month2=parseInt(change_month[1]);		   
		   var change_month3=parseInt(change_month1)*10+change_month2;		     
		   var sum=change_month3+1;
		   alert(sum);
		    if(sum<10){
			   monthEnd="0"+sum;			   
			   }else{
			    monthEnd=""+sum;			   
			   }
		   }
		   if(year%4==0){
		    if(monthEnd=="02"&&(date=="30"||date=="31")){
		      monthEnd="03"
		      date="01";
		    }
		   }else{
		    if(monthEnd=="02"&&(date=="29"||date=="30"||date=="31")){
		      monthEnd="03"
		      date="01";
		    }
		   }
						
	    var  ValidDate=year+'-'+monthEnd+'-'+date;  
		//Ext.getCmp("validtime").setValue(ValidDate);
		return ValidDate;
		}else{
			alert("请填写生产日期或者复检后允许使用日期");
		}
		
}


	//新类型按钮执行的函数
	function addtoolFn(){
		formpanel.form.reset();
    	window.show();
    	window.center();
	}
	//添加页面的保存按钮执行函数
	function saveFn(){
		var mnum = Ext.get("mnum").dom.value.trim();
		var materialclass = ComboBoxClass.getValue();
		var name = Ext.get("name").dom.value.trim();
		var measure = Ext.get("measure").dom.value.trim();
		var spec = Ext.get("ttspec").dom.value.trim();
		var monthuse = Ext.get("monthuse").dom.value.trim();
		var modelnum = Ext.get("modelnum").dom.value.trim();
		var buycycle = buycyclecom.getValue();
		var companyid = ComboBoxCom.getValue();
		var guaranteedate = guaranteeCombox.getValue();
		/*var maxcycle = Ext.get("maxcycle").dom.value.trim();
		var mincycle = Ext.get("mincycle").dom.value.trim();*/
		//var prewarndate = Ext.get("prewarndate").dom.value.trim();
		//alert(guaranteedate+""+maxcycle+""+mincycle+""+prewarndate);
		var numReg = /^(0|[1-9][0-9]*)$/;
		if(mnum==""){
			alert("物资编码不能为空");
		}else if(materialclass==""){
			alert("材料类别不能为空");
		}else if(name==""){
			alert("材料名称不能为空");
		}else if(measure==""){
			alert("计量单位不能为空不能为空");
		}else if(spec==""){
			alert("规格不能为空");
		}else if(monthuse==""){
			alert("月用量不能为空");
		}else if(modelnum==""){
			alert("材料型号不能为空");
		}else if(buycycle==""){
			alert("采购周期不能为空");
		}else if(companyid==""){
			alert("生产厂商不能为空");
		}else if(guaranteedate==""){
			alert("保质期不能为空");
			}
		/*else if(maxcycle==""){
			alert("最大安全周期不能为空");
		}else if(mincycle==""){
			alert("最小安全周期不能为空");
		}else if(prewarndate==""){
			alert("提前预警天数不能为空");
		}*/else if(!numReg.test(spec)){
			alert("规格必须为数字");
		}else if(!numReg.test(monthuse)){
			alert("月用量必须为数字");
		}else if(!numReg.test(buycycle)){
			alert("采购周期必须为数字")
		}/*else if(!numReg.test(maxcycle)){
			alert("最大安全周期必须为数字")
		}else if(!numReg.test(mincycle)){
			alert("最小安全周期必须为数字")
		}else if(!numReg.test(prewarndate)){
			alert("提前预警天数必须为数字")
		*/else{
			addsaveFn();
		}
	}
	
	//材料类型添加保存函数
	function addsaveFn(){
		var buycycle = buycyclecom.getValue();
		var companyid = ComboBoxCom.getValue();
		var guaranteedate = guaranteeCombox.getValue();
		Ext.showProgressDialog("正在提交数据，请稍候...");
		formpanel.form.doAction('submit',{
			url : 'logicmaterial_entry.jsp',
			method:'post',
			params : { type : 'ADD',buycycle:buycycle,companyid:companyid,guaranteedate:guaranteedate},
			success:function(form,action){
				if(action.result.msg=='ok'){
					Ext.hideProgressDialog();
					formpanel.form.reset();
					window.hide();
					alert("材料添加成功");
				}else if(action.result.msg=='repeat'){
					Ext.hideProgressDialog();
					alert("同类材料已经存在不需要添加,请修改生成厂商或者规格");
				}else{
					Ext.hideProgressDialog();
					alert("材料添加失败");
				}
			},
			failure : function(form,action){
					Ext.hideProgressDialog();
					alert( "提示:'服务器出现错误请稍后再试！");
					}
		});	
	}
	//重置函数
	function resetFn(){
		formpanel.form.reset();
		
	}
	
	//取消函数
	function cancleFn(){
		window.hide();
		formpanel.form.reset();
	}
	//获取当前年月日时分秒
	function getnowtime(){
		var now = new Date();
		var year=now.getYear();
		var month=now.getMonth()+1;
		var day=now.getDate();
		var hour=now.getHours();
		var minute=now.getMinutes();
		var second=now.getSeconds();
		return year+""+month+""+day+""+hour+""+minute+""+second;
	}
	
	//保存入库材料函数
	function saveFn1(){
		var id = nowid;
		//alert(nowid);
		var mnumraw = mnumCombo.getRawValue();
		//alert(mnumraw);
		var mnum = Ext.get("tmnum").dom.value.trim();
		//alert(mnum);
		var ordernum = mnum + "-" + getnowtime();
		var mnums = Ext.get("tmnums").dom.value.trim();
		var classname = Ext.get("tclassname").dom.value.trim();
		var name = Ext.get("tname").dom.value.trim();
		var spec = Ext.get("tspec").dom.value.trim();
		
		var incount = Ext.get("tincount").dom.value.trim();
		var kuname = "海波库";
		var shelf = shelfcombo.getValue();
		var ceng = cengcombo.getValue();
		//alert(shelf+ceng);
		var creattime = Ext.get("tcreattime").dom.value.trim();
		//alert(producetime);
		var neworold = "";
		if(Ext.getCmp("newmaterial").getValue()==true){
			neworold = "否"
		}
		if(Ext.getCmp("oldmaterial").getValue()==true){
			neworold = "是"
		}
		//alert(neworold);
		
		
		//alert(validate+""+guaranteedate);
		var inperson = Ext.get("admin").dom.value.trim();
		var company = Ext.get("tcompany").dom.value.trim();
		if(mnumraw==""){
			alert("请选择物资编码");
			return;
		}else if(kuname==""){
			alert("请选择库名");
			return;
		}else if(shelf==""){
			alert("请选择货架号");
			return;
		}else if(incount==""){
			alert("请填写入库数量");
			return;
		}else if(Ext.get("producetime").dom.value.trim()==""){
		alert("请填写生产日期");
		return;
		}else if(mnum!=""&&mnums!=""&&incount!=""&&kuname!=""&&shelf!=""&&creattime!=""&&ceng!=""){
			if(neworold=="是"){
				if(Ext.getCmp("fujian").getValue()==""){
				alert("请填写复检后可用日期");
				return;
				}
				else{var validate = ValidTime1();
				var guaranteedate = Ext.getCmp("fujian").getValue(); 
				}
				
			}else{
				var validate = ValidTime();
				var guaranteedate = Ext.get("guaranteedate").dom.value.trim();
			}
	}    else if(mnum==""){
			alert("请填写物资编码");
			return;
		}
		var producetime = Ext.get("producetime").dom.value.trim();
			//var validate = ValidTime();
			 //var validate = Ext.get("validtime").dom.value.trim();
			//alert(ordernum+""+classname+""+name+""+spec+""+intime+""+incount+""+kuname+""+shelf+""+validate+""+guaranteedate+""+inperson+""+company);

		ds.add(new inrecord({
		ordernum:ordernum,
		mnum:mnum,
		classname:classname,
		name:name,
		spec:spec,
		kuname:kuname,
		shelf:shelf+ceng,
		validate:validate,
		guaranteedate:guaranteedate,
		company:company,
		id:id,
		creattime:creattime,
		incount:incount,
		producetime:producetime,
		inperson:inperson,
		neworold:neworold
			}));
		simple.form.reset();
		 Ext.getCmp("admin").setValue(realname);
              //时间赋值函数        
     var data=getNowtime();
     Ext.getCmp("tcreattime").setValue(data);
}
		
	//将表格中的记录删除函数
	function delFn(){
		var record = sm.getSelections();
		if(record.length==0){
			Ext.MessageBox.alert('提示',"请选择要删除的行!");
			return;
		}else{
			Ext.MessageBox.confirm('提示',"您即将删除所选择的的材料入库信息,这些信息还为被添加到数据库中,确认吗？",function(btn){
				if(btn == 'yes'){
					Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){
						ds.remove(record[i]);
					}
					Ext.hideProgressDialog();
					Ext.MessageBox.alert('提示',"您所选择的材料入库信息已经删除");
					ds.load();
				}else{
					Ext.MessageBox.alert('提示',"您已经取消当前入库操作");
				}
			});
		}
	}
	//入库函数
	function addFn(){
		var record = sm.getSelections();
		if(record.length==0){
			Ext.MessageBox.alert('提示',"请选择要入库的材料");
		}else{
			Ext.MessageBox.confirm('提示',"确定要将选中的订单入库吗？",function(btn){
				if(btn=="yes"){
					for(var i=0;i<record.length;i++){
					var id = record[i].get("id");
					var ordernum = record[i].get("ordernum");
					var kuname = record[i].get("kuname");
					var shelfnum = record[i].get("shelf");
					var producedate = record[i].get("producetime");
					var guaranteedate = record[i].get("guaranteedate");
					var valiadate = record[i].get("validate");
					var incount = record[i].get("incount");
					var indate = record[i].get("creattime");
					var inperson = record[i].get("inperson");
					var state = record[i].get("neworold");
					//alert(valiadate);
					Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicmaterial_entry.jsp',
						//method:'post',
						params:{
							type:'INRECORD',
							id:id,
							ordernum:ordernum,
							kuname:kuname,
							shelfnum:shelfnum,
							producedate:producedate,
							guaranteedate:guaranteedate,
							valiadate:valiadate,
							incount:incount,
							indate:indate,
							inperson:inperson,
							state:state
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"材料入库成功");
							}else{
								Ext.MessageBox.alert('提示',"材料入库失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
					ds.remove(record[i]);
					ds.load();
					}
				}else{
					Ext.MessageBox.alert('提示',"您取消了入库操作");
				}
			}
			);
		}
	}
});