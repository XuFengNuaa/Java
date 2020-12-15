Ext.onReady(function(){
	var special=" ";
	Ext.QuickTips.init();//支持tips提示
	Ext.form.Field.prototype.msgTarget='qtip';//提示的方式，枚举值为"qtip","title","under","side"
	//var filter = '';
	var locations = '';
 	var kunum = 0;
 	var cennum=0;
 	var cen1num=0;
 	var weinum=0;
 	var decide='';
 	var nowid = '';
 	var filter ='';
 	var filter1 = '';
	
	
	var storeMnum = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy({url:"/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp?type=QUERYALL"}), // 数据源
			     reader: new Ext.data.JsonReader({
			         id:'mnumnum',
				     root:"filedata",
				     fields:[{name: 'mnum'}]    // 解析
				       }),
				     remoteSort: true,
				     sortInfo: {field: 'mnum', direction: 'DESC'}//按零件编码降序
			     });
			  storeMnum.load();
		 
			var mnumCombo=new Ext.form.ComboBox({
					fieldLabel:'零件编码',
					name:'tmnum',
					id :'tmnum',
					listeners:{
					//focus: function(){keyBoardInput = Ext.getDom("tmnum");},	
					 select: function(){
					// Ext.getCmp('details').collapse(false);
					 Combonum.reset();			
		             Combocom.reset();
		             hpCombo.reset(); 
					//Ext.getCmp('div').hide();
					//Ext.getCmp('div1').hide();
					//Ext.getCmp('div2').hide();
					  chexExist();//在此处提取明细栏中零件的信息
					  }},		
					store:storeMnum, 
					displayField:'mnum',
					triggerAction: 'all',
					width:150,
					emptyText:'请选择零件编号',
					mode:'local',
					typeAhead: true,
					editable:true		
			});
			mnumCombo.on('blur',function(){   
              this.setValue(this.el.dom.value);   
             });
             
	var hpObj={url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',method: 'POST',extraParams:{}};
    var storenum = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)), // 数据源type=QUERYALLNUM
			     reader: new Ext.data.JsonReader({
			         id:'ordernum',
				     root:"filedata",
				     fields:[{name: 'order_num'}]    // 解析
				       }),
				     remoteSort: true
			     });
			  storenum.load();
			  
	var Combonum=new Ext.form.ComboBox({
				fieldLabel:'订货号',
				name:'innum',
				id:"innum",				
				displayField:'order_num',
				store: storenum,
				triggerAction: 'all',
				lazyRender:true,
				width:135,
				typeAhead: true,
				selectOnFocus:true,
				emptyText:'请选择订货号',
				allowBlank:true,
				mode:'local',
				editable:true		
				});
	
	
	var hpOb={url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',method: 'POST',extraParams:{}};			
	var storecom = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpOb)), // 数据源
			     reader: new Ext.data.JsonReader({
			         id:'ordercom',
				     root:"filedata",
				     fields:[{name: 'company'}]    // 解析
				       }),
				     remoteSort: true
			     });
			  storecom.load();
			  
	var Combocom=new Ext.form.ComboBox({
				fieldLabel:'厂商',
				name:'incom',
				id:"incom",				
				displayField:'company',
				store: storecom,
				triggerAction: 'all',
				lazyRender:true,
				width:135,
				typeAhead: true,
				selectOnFocus:true,
				listeners:{
					  select: function(){
					   Combonum.reset();//Ext.getCmp('innum').reset();
					   filter= mnumCombo.getValue();
					   filter1 = Combocom.getValue();
					   filter2 = hpCombo.getValue();
					   hpObj.extraParams.hq = filter2;
					   hpObj.extraParams.order = filter1;
					   hpObj.extraParams.filter = filter;
					   hpObj.extraParams.type = "QUERYALLNUM";
						  storenum.load();
					  }
					  },
				emptyText:'请选择厂商',
				allowBlank:true,
				mode:'local',
				editable:true		
				});
		
		//厂商combobox 	
	 var storeCom = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy({url:"/tsms/web/sms/bscinfmod/company/logiccompany.jsp?type=QUERYALL"}), // 数据源
	     reader: new Ext.data.JsonReader({
	         id:'id',
		     root:"filedata",
		     fields:[{name: 'id'},{name: 'company'}]    // 解析
		       }),
		     remoteSort: true
	     });
	 storeCom.load();
	
	 var ComboBoxCom=new Ext.form.ComboBox({
				fieldLabel:'厂商',
		     	name:'company',
				id:"company",
				store:storeCom, 
				displayField:'company',
				valueField:'id',
				triggerAction: 'all',
				width:125,
				emptyText:'请选择厂商',
				forceSelection:true,
				allowBlank:true,
				mode:'local',
				typeAhead: true,
				editable:true
	});  			
				
        //库存位置
		 var    ku= [['01'],['02'],['03'],['04'],['05'],['06'],['07'],['08'],['09'],['10'],
					 ['11'],['12'],['13'],['14'],['15'],['16'],['17'],['18'],['19'],['20'],
					 ['21'],['22'],['23'],['24'],['25'],['26'],['27'],['28'],['29'],['30'],
					 ['31'],['32'],['33'],['34'],['35'],['36'],['37'],['38'],['39'],['40'],
					 ['41'],['42'],['43'],['44'],['45'],['46'],['47'],['48'],['49'],['50'],
					 ['51'],['52'],['53'],['54'],['55'],['56'],['57'],['58'],['59'],['60'],
					 ['61'],['62'],['63'],['64'],['65'],['66'],['67'],['68'],['69'],['70'],
					 ['71'],['72'],['73'],['74'],['75'],['16'],['77'],['78'],['79'],['80'],
					 ['81'],['82'],['83'],['84'],['85'],['86'],['87'],['88'],['89'],['90'],
					 ['91'],['92'],['93'],['94'],['95'],['96'],['97'],['98'],['99']];
					 
		 var fuceng= [['00'],['01'],['02'],['03']];
		 
	    var storeceng=new Ext.data.SimpleStore({fields:['values'],data:fuceng});
		var storeku = new Ext.data.SimpleStore({fields:['values'],data:ku});
		var kucombo=new Ext.form.ComboBox({				
			name:'ku',
			id:"tku",
			displayField:'values',
			store: storeku,
			triggerAction: 'all',
			lazyRender:true,
			 labelSaparator:"",  
			width:50,
			readOnly:true,
			fieldLabel:'库存地点',
			typeAhead: true,
			selectOnFocus:true,
	    	emptyText:'库',
	    	blankText:'请选择库',
	    	msgTarget:'title',
			allowBlank:false,
			mode:'local',
			editable:true	
			});
			
			var cencombo=new Ext.form.ComboBox({
			name:'cen',
			id:"tcen",
			displayField:'values',
			labelWidth:0.1,	  
			store: storeku,
			labelSeparator:"",  
			triggerAction: 'all',
			lazyRender:true,
			width:50,
			readOnly:true,
			typeAhead: true,
			selectOnFocus:true,
			emptyText:'层',
			blankText:'请选择层',
			msgTarget:'title',
			allowBlank:false,
			mode:'local',
			editable:true
			});
			
			var cen1combo=new Ext.form.ComboBox({
			name:'cen1',
			id:"tcen1",	
			displayField:'values',
			store: storeceng,
			triggerAction: 'all',
			labelWidth:0.1,	  
			lazyRender:true,
			width:50,
			readOnly:true,
			labelSeparator:"",   
			typeAhead: true,
			selectOnFocus:true,
			emptyText:'(层)',
			value:'00',
			allowBlank:false,
			blankText:'请选择副层',
			msgTarget:'title',
			mode:'local',
			editable:true
			});
			
			var weicombo=new Ext.form.ComboBox({
			name:'wei',
			id:"twei",
			displayField:'values',
			store: storeku,
			 labelSeparator:"", 
			 labelWidth:0.1,	    
			triggerAction: 'all',
			lazyRender:true,
			width:50,
			readOnly:true,
			typeAhead: true,
			selectOnFocus:true,
		 	emptyText:'位',
		 	blankText:'请选择位',
		 	msgTarget:'title',
			allowBlank:false,
			mode:'local',
			editable:true
			});	
	 //高质下拉框
	 var data=[['否'],['是']]; 
	 var store = new Ext.data.SimpleStore({fields:['ishq'],data:data});
	 var ComboBoxHp=new Ext.form.ComboBox({
				fieldLabel:'高质',
		     	name:'hq',
				id:"hq",
				store: store,
				displayField:'ishq',
				 labelWidth:150,				
				triggerAction: 'all',
				forceSelection:true,
				lazyRender:true,
				width:125,
				value:'否',
				readOnly:true,
				allowBlank:true,
				mode:'local',
				editable:false
	}); 		

	//添加 大类小类级联	 	
       var  add_pclass=[[0,'热轧'],[1,'收卷'],[2,'分切'],[3,'其他']]; 
       var  add_bclass=new Array();
              add_bclass[0]=[[0,'上轧辊总成'],[1,'润滑系统'],[2,'冷却轧总成'],[3,'液压系统'],[4,'传动系统'],[5,'机架总成'],[6,'下轧辊总成'],[7,'限位调节装置'],[8,'轴交叉调节装置'],[9,'升降座']]; 
              add_bclass[1]=[[0,'类型1'],[1,'类型2'],[2,'类型3']]; 
              add_bclass[2]=[[0,'类型1']];  
              add_bclass[3]=[[0,'类型1'],[1,'类型2'],[2,'类型3'],[3,'类型4']];       
			var add_store = new Ext.data.SimpleStore({fields:['add_pclassid','add_pclass'],data:add_pclass});
			var add_pclasscombo=new Ext.form.ComboBox({				
				id:"add_pclass",
				listeners:{
					        select:function(add_pclasscombo, record,index){ 
					        add_bclasscombo.clearValue();
					        add_bclasscombo.store.loadData(add_bclass[record.data.add_pclassid]);
					        }},	
				displayField:'add_pclass',
				emptyText:'请选择整机', 
				store: add_store,
				triggerAction: 'all',
				lazyRender:true,
				width:110,
				fieldLabel:'整机',
				typeAhead: true,
				selectOnFocus:true,
		    	lowBlank:false,
				mode:'local',
				editable:false	
				});
				
				var add_bclasscombo=new Ext.form.ComboBox({
				id:"add_bclass",
				emptyText:'请选择部件', 				
				displayField:'add_bclass',				
				store: new Ext.data.SimpleStore( {
		        fields: ['add_bclassid','add_bclass'],
		        data:[]
		        }),
				triggerAction: 'all',
				fieldLabel:'部件',				
				lazyRender:true,
				width:110,
				typeAhead: true,
				selectOnFocus:true,				
				mode:'local',
				editable:false
				});		
	
    var los_mnum0=new Ext.form.TextField({ fieldLabel: '零件编码',id: 'los_mnum0',readOnly:true, width:125	});
	var los_pclass0=new Ext.form.TextField({ fieldLabel: '整机',id: 'los_pclass0',readOnly:true,   width:125});
	var los_bclass0=new Ext.form.TextField({ fieldLabel: '部件',id: 'los_bclass0',readOnly:true,   width:125});
	var los_ISO0=new Ext.form.TextField({ fieldLabel: 'ISO号',id: 'los_ISO0', readOnly:true,  width:125});
	var los_name0=new Ext.form.TextField({ fieldLabel: '名称',id: 'los_name0', readOnly:true, width:125});	
	var los_mini_qs0=new Ext.form.TextField({ fieldLabel: '最低库存量',  id: 'los_mini_qs0',readOnly:true, width:125});
	var los_use_freq0=new Ext.form.TextField({ fieldLabel: '借用频率',  id: 'los_use_freq0',readOnly:true, width:125});
	var los_suit_mate10=new Ext.form.TextField({ fieldLabel: '适合加工材料1',  id: 'los_suit_mate10',readOnly:true, width:125});
	var los_suit_mate20=new Ext.form.TextField({ fieldLabel: '适合加工材料2',  id: 'los_suit_mate20',readOnly:true, width:125});	
	var los_suit_mate30=new Ext.form.TextField({ fieldLabel: '适合加工材料3',  id: 'los_suit_mate30',readOnly:true, width:125});
	var los_rank_angle0=new Ext.form.TextField({ fieldLabel: '前角',  id: 'los_rank_angle0',readOnly:true, width:125});
	var los_c_mate0=new Ext.form.TextField({ fieldLabel: '刀体材料',  id: 'los_c_mate0',readOnly:true, width:125});
	var los_coat_mate0=new Ext.form.TextField({ fieldLabel: '涂层材料',  id: 'los_coat_mate0',readOnly:true, width:125});
	var los_e_diam0=new Ext.form.TextField({ fieldLabel: '刃径',  id: 'los_e_diam0',readOnly:true, width:125});
	var los_h_diam0=new Ext.form.TextField({ fieldLabel: '柄径',  id: 'los_h_diam0', readOnly:true,width:125});
	var los_e_leng0=new Ext.form.TextField({ fieldLabel: '刃长',  id: 'los_e_leng0',readOnly:true, width:125});
	var los_t_leng0=new Ext.form.TextField({ fieldLabel: '总长',  id: 'los_t_leng0',readOnly:true, width:125});
	var los_tee_count0=new Ext.form.TextField({ fieldLabel: '齿数',  id: 'los_tee_count0',readOnly:true, width:125});
	var los_s_ang0=new Ext.form.TextField({ fieldLabel: '螺旋角',  id: 'los_s_ang0', readOnly:true,width:125});
	var los_tip_heig0=new Ext.form.TextField({ fieldLabel: '刀尖高度',  id: 'los_tip_heig0',readOnly:true, width:125});
	var los_bla_wide0=new Ext.form.TextField({ fieldLabel: '刀体宽度',  id: 'los_bla_wide0',readOnly:true, width:125});
	var los_c_leng0=new Ext.form.TextField({ fieldLabel: '刀具长度',  id: 'los_c_leng0',readOnly:true, width:125});
	var los_e_count0=new Ext.form.TextField({ fieldLabel: '刃数',  id: 'los_e_count0', readOnly:true,width:125});
	var los_e_wide0=new Ext.form.TextField({ fieldLabel: '切削刃宽',  id: 'los_e_wide0',readOnly:true, width:125});
	var los_t_form0=new Ext.form.TextField({ fieldLabel: '螺纹形式',  id: 'los_t_form0',readOnly:true, width:125});
	var los_c_way0=new Ext.form.TextField({ fieldLabel: '冷却方式',  id: 'los_c_way0', readOnly:true,width:125});
	var los_t_type0=new Ext.form.TextField({ fieldLabel: '牙型',  id: 'los_t_type0', readOnly:true,width:125});
	var los_spec0=new Ext.form.TextField({ fieldLabel: '规格',  id: 'los_spec0', readOnly:true,width:125});
	var los_remark0=new Ext.form.TextArea({ fieldLabel: '备注',id: 'los_remark0', readOnly:true,width:364,height:80});
	var los_type0=new Ext.form.TextField({ fieldLabel: '型号',id: 'los_type0', readOnly:true,width:125});
	var los_series_num0=new Ext.form.TextField({ fieldLabel: '系列号',id: 'los_series_num0',readOnly:true, width:125});
	var los_order_num0=new Ext.form.TextField({ fieldLabel: '订货号',id: 'los_order_num0',readOnly:true, width:125});
	var los_nc_count_in0=new Ext.form.TextField({ fieldLabel: '新刀在库量',id: 'los_nc_count_in0',readOnly:true, width:125});
	var los_gc_count_in0=new Ext.form.TextField({ fieldLabel: '刃磨刀在库量',id: 'los_gc_count_in0',readOnly:true, width:125}); 
	var los_uc_count_in0=new Ext.form.TextField({ fieldLabel: '旧刀当前在库量',id: 'los_uc_count_in0', readOnly:true,width:125});	
	var los_effect_length0=new Ext.form.TextField({ fieldLabel: '有效长度',id: 'los_effect_length0', readOnly:true,width:125});			
	var los_hq0=new Ext.form.TextField({ fieldLabel: '是否高质',id: 'los_hq0',readOnly:true, width:125}); 
	var los_company0=new Ext.form.TextField({ fieldLabel: '厂商',id: 'los_company0', readOnly:true,width:125});	
	var los_location0=new Ext.form.TextField({ fieldLabel: '刀具位置',id: 'los_location0', readOnly:true,width:125});			
    var los_iso_num0=new Ext.form.TextField({ fieldLabel: '所属类型',id: 'los_iso_num0',  width:125});
	    

	//左面板
	 var leftpanel0 = new Ext.Panel({
			  id:'leftpanel',
			  width:320, 
			  height:178,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
			        columnWidth:1,					              
	                layout: 'form',		                
	                id:'left_panel',
	                labelAlign:"right",		               
	                items: [{   
		            	columnWidth:1,					              
		                layout: 'column',
		                id:'import_mnum',
		                items: [{   
			            	columnWidth:.81,					              
			                layout: 'form',
			                labelAlign:"right",
			                items: [los_mnum0]	            
			          },{   columnWidth: .1,
			                bodyStyle:'padding:5px 0px 0',
			                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
			                border:false
		           }]	            
		           },los_pclass0,los_type0,los_series_num0 ]
		            }]
		  });
		 
	//右面板
	 var rightpanel0 = new Ext.Panel({
			  id:'rightpanel',
			  width:350, 
			  height:178,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
			        columnWidth:1,					              
	                layout: 'form',		                
	                id:'right_panel',
	                labelWidth:100,
	                labelAlign:"right",       
	                items: [ {   
		            	columnWidth:1,					              
		                layout: 'column',
		                items: [{   
			            	columnWidth:.81,					              
			                layout: 'form',
			                labelAlign:"right",
			                items: [los_name0]	            
			          },{   columnWidth: .1,
			                bodyStyle:'padding:5px 0px 0',
			                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
			                border:false
		           }]	            
		           },los_bclass0,los_spec0,los_iso_num0]  }]
		  });
	//remark面板
		  var remarkpanel0 = new Ext.Panel({
			  id:'remarkpanel0',
			  width:520, 
			  height:85,					   
			  hideLabels:false,			
			  labelAlign:"right",
			  bodyStyle:'padding:4px 0px 0px 0px',
			  layout:'column',
			  items:[{
			        columnWidth:1,					              
	                layout: 'form',		                
	                id:'remark_panel0',	               
	                labelAlign:"right",		               
	                items: [los_remark0]
	                }]
		  });
		  
	//是否为高质
			var data = [['否','否'],['是','是']]; 
	        var storehq = new Ext.data.SimpleStore({fields:['key','value'],data:data});
			var hpCombo=new Ext.form.ComboBox({
				fieldLabel:'是否高质',
				name:'thp',
				id:"thp",				
				displayField:'value',
				valueField:'key',
				store: storehq,
				triggerAction: 'all',
				lazyRender:true,
				width:125,
				listeners:{
					  select:chexExist1
					        },
				typeAhead: true,
				selectOnFocus:true,
				emptyText:'请选择是否高质',
				allowBlank:true,
				mode:'local',
				editable:true		
				});
				
	/*var details = new Ext.form.FieldSet({
	                title: '明细',     //<font color="#FF0000">*&nbsp;*</font>
		            id: 'details',
		            width:490,
		            style:'padding:10px 10px 10px 10px',
		            autoHeight:true,
		            labelAlign:"right",
		            collapsible : true,
		            collapsed :true,
			         items:[{
			            layout:'column',
			            items:[{
			                columnWidth:.15,
			                labelWidth:70,					              
			                layout: 'form',
			                labelAlign:"right",
			                items: [leftpanel0
					        ]},
					        {columnWidth:.18,
					        //labelWidth:70,
			                layout: 'form',
			                labelAlign:"right",
			                items: [rightpanel0
			                ]},{
			                columnWidth:1,
			                labelWidth:70,					              
			                layout: 'form',
			                labelAlign:"right",
			                items: [remarkpanel0]
					}]
	     		}]	
	})*/
var problem=new Ext.form.TextArea({fieldLabel: '问题描述',id: 'problem', readOnly:false,width:364,height:80});
	var leftpanel = new Ext.Panel({
			  id:'leftpanel',
			  width:260, 
			  height:110,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{ columnWidth:1,					              
	                layout: 'form',		                
	                id:'left_panel',
	                labelWidth:100,
	                labelAlign:"right",  
		               items:[{columnWidth:1,					              
		                layout: 'column',
		                 items:[{columnWidth:1,					              
			                layout: 'form',
			                labelAlign:"right",
			                items:[{fieldLabel:"整机",xtype: 'textfield',width:125,name:"apply_pclass",id:"apply_pclass",allowBlank:false,blankText:"整机名称不能为空，请填写!"},
			                	{fieldLabel:"联系人",xtype: 'textfield',width:125,name:"apply_user",id:"apply_user",allowBlank:false,blankText:"联系人不能为空，请填写!"},  //零件编码
				{fieldLabel:"联系电话",xtype: 'textfield',width:125,name:"toolnum",id:"toolnum",allowBlank:false,blankText:"联系方式不能为空，请填写!",regex:/^\d+$/,regexText:'联系方式应该由阿拉伯数字组成！'}            
        		,{fieldLabel:"申请时间",xtype: 'textfield',width:125,name:"tcreatetime",id:"tcreatetime",readOnly: true}
        		]
		        }]
		           }]
			  }]
		  });
		 
	//右面板
	 var rightpanel = new Ext.Panel({
			  id:'rightpanel',
			  width:280, 
			  height:140,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{ columnWidth:1,					              
	                layout: 'form',		                
	                id:'right_panel',
	                labelWidth:100,
	                labelAlign:"right",  
		               items:[{columnWidth:1,					              
		                layout: 'column',
		                 items:[{columnWidth:1,					              
			                layout: 'form',
			                labelAlign:"right",
			                items:[{fieldLabel:"部件",xtype: 'textfield',width:125,name:"apply_bclass",id:"apply_bclass",allowBlank:false,blankText:"部件名称不能为空，请填写!"},
			                	{fieldLabel:"所在城市",xtype: 'textfield',width:125,name:"city",id:"city",allowBlank:false,blankText:"所在城市不能为空，请填写!",regex:/^\d+$/,regexText:'确保联系人真实存在！'},  //零件编码
				{fieldLabel:"公司名称",xtype: 'textfield',width:125,name:"company",id:"company",allowBlank:false,blankText:"公司名称不能为空，请填写!"}            
        		,{fieldLabel:"订单号",xtype: 'textfield',width:125,name:"bill_num",id:"bill_num",blankText:"订单号不能为空，请填写!",regex:/^\d+$/,regexText:'订单号应该由阿拉伯数字组成！'}
        		]
		        }]
		           }]
			  }]
		  });			
	var simple = new Ext.FormPanel({
         id:'simple',
        labelWidth: 80,        
        width:540,
        height:240,	
        layout:"form",
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',         
		layout:'column',
        items: [ {		           		                
	                columnWidth:.47,					              
	                layout: 'form',	            
	                labelAlign:"right",		               
	                items: [leftpanel]
	           },{		           		                
	                columnWidth:.53,					              
	                layout: 'form',	               
	                labelAlign:"right",		               
	                items:[rightpanel]
	           },{columnWidth:1,labelWidth:70,	layout: 'form',labelAlign:"right", items:[problem] }/*,
					{layout: 'column', columnWidth:1,items:[details]}*/]
    });
     //获取时间
     var data=getNowtime();
     Ext.getCmp("tcreatetime").setValue(data);
     function getNowtime(){
		var sUrl = 'pa_c_logic.jsp';
		var parms = {type:'getNowtime'};
		var objtp = Ext.util.JSON.decode(callService(sUrl,parms));
		var nowtimeValue = objtp.nowtime;
		return nowtimeValue;
	}
	function onUploadSuccess1(dialog,filename,resp_data){
				var parts = filename.split(/\/|\\/);
				if (parts.length == 1) {
				  filename = parts[0];
				  filenames = filename;
				}else{
				  filename = parts.pop(); // filename实际文件名称(加后缀名)
				  filenames = filename;
				}
				var name=filename.split(".");     
				fileid=resp_data.fileid; 
				Ext.getCmp("save1").show();
		        Ext.getCmp("upload1").hide();  
		        //Ext.getCmp("tpmc").setValue(filename);//案例名称
			}
	function uuid(len, radix) {  
    var chars = "0123456789abcdefghijklmnopqrstuvwxyz".split("");  
    var uuid = [], i;  
    radix = radix || chars.length; 
      if (len) { 
      	for (i = 0; i < len; i++) uuid[i] = chars[0 |Math.random()*radix]; 
      	 uuid[8] = uuid[13] = uuid[18] = uuid[23] = "-";  
      uuid[14] = "4";
      	} else {   // rfc4122, version 4 form  
      var r; 
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = "-";  
      uuid[14] = "4";  
      for (i = 0; i < 36; i++) {  
        if (!uuid[i]) {  
        	  r = 0 | Math.random()*16;  
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];  
           }  
      }  
    }   
    return uuid.join("");  
}  
    
	 var dialog1 = null;  
		 var button1 = null;
			function getDialog1(){
				special=uuid(36,16);
				// ljth = Ext.get("ljthtp").dom.value//零件编码
				//if(ljth==null){alert('请选择零件编码')}
				var dialog1 = null;
			if (!dialog1) {
				dialog1 = new Ext.ux.UploadDialog.Dialog({
				  url: 'pa_c_logic.jsp?type=uploadimg&special='+special, 
				  reset_on_hide: false,
				  modal:true,
				  closable : false,   
		          collapsible : false,   
		          draggable : true, 
		          proxyDrag : true,
		          permitted_extensions:['JPG','jpg','mp4','mp3','GIF','gif','pdf','txt','jar','docx','doc'],   
		          constraintoviewport : true, 
				  allow_close_on_upload: true,
				  post_var_name:'upload',
				  autoCreate : true 
				});		
				dialog1.on('uploadsuccess', onUploadSuccess1);
				//dialog1.on('beforehide',update, this);
			}
				return dialog1;
			}
	function addsaveFn1(){
		//var ljth = Ext.get("ljthtp").dom.value.trim();
		//var scr = Ext.get("scrtp").dom.value.trim();
		//var tpmc = Ext.get("tpmc").dom.value.trim();
		//alert(cpmc+""+tpmc);
		Ext.showProgressDialog();
		Ext.Ajax.request({
					url : 'pa_c_logic.jsp',
					params : {
						type : 'ADDIMG'
						
					},
					success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)) {
							Ext.getCmp("inform1").form.reset();
							 Ext.getCmp("upload1").show();
							Ext.getCmp("window1").hide();
							alert( "提示:保存成功！");
						}else{
							alert( "提示:案例已经存在，不需要添加！");
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						alert( "提示:保存失败！");
					}
					
				}); 
		}
	
			var inform1=new Ext.FormPanel({
			id:'inform1',
		    labelWidth:75,
		    width:340,
		    height:180,			      				       
		    frame:true,
		    bodyStyle:'padding:10px 10px 0',	
		    items: [{
		    	layout:'column',
		        items:[{
		                columnWidth:1,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [
		               /* {
			    	    columnWidth:1,					              
		                layout: 'column',
		                items:[{
	                	columnWidth:.7,					              
		                layout: 'form',
		                labelAlign:"right",
		                 items:[{
	                 	xtype:'textfield',
			            fieldLabel: '零件编码',
			            name: 'ljthtp',
			            width:100,
			            id: 'ljthtp'
	                 }]
                },{
                columnWidth:.3,					              
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
                	columnWidth:.7,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[{
	                 	xtype:'textfield',
			            fieldLabel: '公司地址',
			            name: 'tpmc',
			            width:100,
			            id: 'tpmc'
	                 }]
                },{
                columnWidth:.3,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*所在城市&nbsp;*</font>',
                border:false
                }]
            },*/{
                xtype:'textfield',
                fieldLabel: '上传人',
                width:100,
                name: 'scrtp',
                id: 'scrtp',
                readOnly: true
	              }]
		       }],
		        buttonAlign:"center",
		     buttons: [{
		                text:'上传',
		                id:'upload1',
		                handler: function(){showDialog1(this);}
		            },{
		                text:'保存',
		                id:'save1',
		                handler:function(){
		                	
		                		addsaveFn1();
		                	
		                }
					  },{
						text: '返回',
						handler: function(){
						 Ext.getCmp("window1").hide();
						
						}
		            }
		       	]
		    }]
		   	});	     
			var window1 = new Ext.Window({
    			id:'window1',
    			modal:true,
                width:340,
                height:180,
                closable:false,
             //   closeAction:'hide',
                plain: true,
                title:"<p align='center'>上传案件详情</p>",    
				bodyBorder:true,
                items:inform1      
		    });		   	
	function showDialog1(button){
				getDialog1().show(button.getEl());
			}
	function imgFn(){
		
				//window1.findById("ljthtp").setValue(Ext.get("tmnum").dom.value);
				window1.findById("scrtp").setValue(userid);
				Ext.getCmp("window1").show();
				Ext.getCmp("save1").hide();
			}
					
	//页面
	var Panel=new Ext.Panel({
		//renderTo:"hello",
		width:540,
		//height:480,
		frame:true,
		layout:"column",
		style:'margin:10% 20%',
		buttonAlign:"center",
		title:"<p align='center'>申请</p>",
		items:[simple],
                buttons: [
                {
					text: '确定',
					//type: 'submit',
					Align:"center",
					//定义表单提交事件
					handler:function(){
						//var mnumjs=Ext.get("tmnum").dom.value;
						var applyjs=Ext.get("apply_user").dom.value;
						var mnumReg=/^([A-Z]|[a-z]|[\d])*$/;
						//var com = Ext.get("incom").dom.value.trim();//厂商
		               // var num = Ext.get("innum").dom.value.trim();//订货号
						var problemjs=Ext.get("problem").dom.value;
						var pclassjs=Ext.get("apply_pclass").dom.value;
						var bclassjs=Ext.get("apply_bclass").dom.value;
						var toolnumjs=Ext.get("toolnum").dom.value;//联系电话
						var toolnumReg=/^\d+$/;
						var city = Ext.get("city").dom.value;//公司所在城市
						var company = Ext.get("company").dom.value;//公司名称
						var bill_num = Ext.get("bill_num").dom.value;//订货号
							if(bill_num==""){
							Ext.MessageBox.alert('错误','订货号号不能为空！');
						}else if(pclassjs==""){
							Ext.MessageBox.alert('错误','请填写整机名称！');
							}else if(toolnumjs==""){
							Ext.MessageBox.alert('错误','请填写联系电话！');
						}else if(city==""){
							Ext.MessageBox.alert('公司所在城市不能为空！');
							}else if(company==""){
							Ext.MessageBox.alert('公司名称不能为空！');
							//chexExist();
						}else if(applyjs==""){
							Ext.MessageBox.alert('错误','申请者姓名不能为空！');
						}/*else if(mnumjs!="" && !mnumReg.test(mnumjs)){
							Ext.MessageBox.alert('错误','物资编号只能由字母、数字组成！');
						}*/else{ Ext.showProgressDialog("正在提交修改数据，请稍候...");
								toolAdd();
							
							}
						}	
				},{text:"上传附件",handler: imgFn
				},
				{
					text: '重置',
					handler:function(){
					//Ext.getCmp('div1').hide();
		           // Ext.getCmp('div2').hide();
		            hpCombo.reset();
					//Ext.getCmp('div').hide();
		            Combonum.reset();
		            Combocom.reset();
		            keyBoard.hide();
					simple.form.reset();
					}//重置表单
				}/*,{
					text: '键盘',
					id:'keyA',
					handler:function(){
						if(keyBoardWin.isVisible()){									    
							keyBoard.hide();
						}else{
							keyBoard.show();
						}
			          }//键盘
		                }*/]
	});
    Panel.render(document.body);
    keyBoard();				
    keyBoard.hide();
        //Ext.getCmp("keyA").setText("显示键盘");	  
	     //Ext.getCmp("tmnum").focus(); 
		Ext.getCmp("tmnum").on("focus", function(){keyBoardInput = Ext.getDom("tmnum");});           
    //明细表的内容
    function detailsFn(obj){
         var objtp = obj;
		nowid = objtp.id;

		//Ext.DomQuery.selectNode("label[for='los_rank_angle0']").innerHTML="前角:";
		var pclass = objtp.pclass;
		var bclass = objtp.bclass;
		detailsharedFn(pclass,bclass);
		//var tmnumss= ob.mnum;							
		//alert('okk');
		//alert(tmnumss);
		//Ext.getCmp("toolnum1").setValue(ob.order_num);
	 	//Ext.getCmp("toolnum2").setValue(ob.company);
	 	Ext.getCmp("los_mnum0").setValue(objtp.mnum);
		Ext.getCmp("los_pclass0").setValue(objtp.pclass);
		Ext.getCmp("los_bclass0").setValue(objtp.bclass);
		Ext.getCmp("los_iso_num0").setValue(objtp.iso_num);
		Ext.getCmp("los_name0").setValue(objtp.name);
		Ext.getCmp("los_nc_count_in0").setValue(objtp.nc_count_in);
		Ext.getCmp("los_uc_count_in0").setValue(objtp.uc_count_in);
		Ext.getCmp("los_gc_count_in0").setValue(objtp.gc_count_in);
		Ext.getCmp("los_mini_qs0").setValue(objtp.mini_qs);		
		Ext.getCmp("los_suit_mate10").setValue(objtp.suit_mate1);
		Ext.getCmp("los_suit_mate20").setValue(objtp.suit_mate2);
		Ext.getCmp("los_suit_mate30").setValue(objtp.suit_mate3);
		Ext.getCmp("los_use_freq0").setValue(objtp.use_freq);
		Ext.getCmp("los_rank_angle0").setValue(objtp.rank_angle);
		Ext.getCmp("los_c_mate0").setValue(objtp.c_mate);
		Ext.getCmp("los_coat_mate0").setValue(objtp.coat_mate);
		Ext.getCmp("los_e_diam0").setValue(objtp.e_diam);
		Ext.getCmp("los_h_diam0").setValue(objtp.h_diam);
		Ext.getCmp("los_e_leng0").setValue(objtp.e_leng);		
		Ext.getCmp("los_t_leng0").setValue(objtp.t_leng);
		Ext.getCmp("los_tee_count0").setValue(objtp.tee_count);
		Ext.getCmp("los_s_ang0").setValue(objtp.s_ang);
		Ext.getCmp("los_tip_heig0").setValue(objtp.tip_heig);
		Ext.getCmp("los_bla_wide0").setValue(objtp.bla_wide);
		Ext.getCmp("los_c_leng0").setValue(objtp.c_leng);
		Ext.getCmp("los_e_count0").setValue(objtp.e_count);
		Ext.getCmp("los_e_wide0").setValue(objtp.e_wide);
		Ext.getCmp("los_t_form0").setValue(objtp.t_form);
		Ext.getCmp("los_c_way0").setValue(objtp.c_way);
		Ext.getCmp("los_t_type0").setValue(objtp.t_type);
		Ext.getCmp("los_spec0").setValue(objtp.spec);
		Ext.getCmp("los_remark0").setValue(objtp.remark);
		Ext.getCmp("los_type0").setValue(objtp.type);
		Ext.getCmp("los_series_num0").setValue(objtp.series_num);
		Ext.getCmp("los_order_num0").setValue(objtp.order_num);
		Ext.getCmp("los_location0").setValue(objtp.location);
		Ext.getCmp("los_company0").setValue(objtp.company);
	    Ext.getCmp("los_hq0").setValue(objtp.hq);
    }
	//个体添加
	function toolAdd(){
		if(special==" "){
		special=uuid(36,16);
		};
		//alert(special);
		var mnum = nowid;
		var pclass=Ext.get("apply_pclass").dom.value;
		var bclass=Ext.get("apply_bclass").dom.value;
		var bill_num=Ext.get("bill_num").dom.value;
		var company=Ext.get("company").dom.value;
		var city=Ext.get("city").dom.value;
		var user_num=Ext.get("apply_user").dom.value;
		var in_count = Ext.get("toolnum").dom.value.trim();//联系电话
		var in_time = Ext.get("tcreatetime").dom.value.trim();//申请时间
		in_time = in_time.replace(/-/g,'/')
		var in_problem=Ext.get("problem").dom.value;
		var denglu=userid;
		var new1 = "";
		var num = 0;        //parseInt
		var status = "申请";
		Ext.showProgressDialog();
		Ext.Ajax.request({
					url:'pa_c_logic.jsp',
					params : {
						type : 'TOOLALL',
						special:special,
						pclass:pclass,
						bclass:bclass,
						bill_num:bill_num,
						company:company,
						city:city,
						//mnum : mnum,
						user_num:user_num,
						in_count : in_count,
						in_time : in_time,
						new1 : new1,
						status : status,
						in_problem:in_problem,
						denglu:denglu
						
						},
					success : function(resp) {
						
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)==1) {
						Ext.MessageBox.alert("提示","申请添加成功！");
						simple.form.reset();
						Combonum.reset();
						Combocom.reset();
						hpCombo.reset();
						//Ext.getCmp('div').hide();
						//Ext.getCmp('div1').hide();
						//Ext.getCmp('div2').hide();
						}else{
						Ext.MessageBox.alert("提示","申请添加失败，请检查数据再次尝试，如多次失败请联系管理员！");
						simple.form.reset();
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert("提示","零件添加失败，数据传送中出现问题！");
						simple.form.reset();
					}
					
				}); 
	}
	 
   //检查物资编码是否存在，如果存在，提取当前物资编码下所有记录
     

    function chexExist(){
	    var mnum = mnumCombo.getValue(); 
	    var filter = mnumCombo.getValue(); 
	    Ext.Ajax.request({
					url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
					params : {
						type : 'CHEX',
						mnum : mnum
						},
					success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)) {
						var load=Ext.Ajax.request({
							 	url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
							 	method:'post',
						 		params:{type:'QUERY',id:filter},//传递物资编号以便获取详细信息
								success:successFn,
						 		failure:function(){
									Ext.MessageBox.alert('警告','服务器出现错误请稍后再试！');
					 			}
						  	});		
						}else{
						var confirm=Ext.MessageBox.show({
							title:'提醒!',
							width:400,
							msg:"零件编号不存在，请仔细确认！",
							buttons:{"ok":"确定","no":"取消"},
							icon:Ext.MessageBox.INFO,
							animEl:"test1",
							fn:function(btn){
							              
											if (btn=='ok'){
											        tas.cancel();
													
													}
											else if (btn=='no'){
											        tas.cancel();
													simple.form.reset();
													var data=getNowtime();
					     							Ext.getCmp("tcreatetime").setValue(data);
													}
											
											}
						});
						 var tas=new Ext.util.DelayedTask();
							               tas.delay(3025,function(){
							                    confirm.hide();
							                    
							               });
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert( "提示:添加失败！");
					}
					
				}); 
    }
   	
   	//在物资编码存在时提取数据     
    function successFn(result,request){
		var objtp = Ext.util.JSON.decode(result.responseText);
		//var objtp = result.responseText;
		//alert(objtp.filedata);
		var objt =	Ext.util.JSON.encode(objtp.filedata);
		//alert(objt);
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
		   hpCombo.reset();
		  // Ext.getCmp('div').show();
		   Ext.MessageBox.alert('提醒!物资编号不唯一',"你选择一种物资编号不唯一的工具，请您点击确定选择工具是否为高质！");
		   //Ext.getCmp('details').reset();
		}else{//物资编码不重复						
		var obj = Ext.util.JSON.decode(obj);
		Combonum.reset();
		Combocom.reset();
		hpCombo.reset();
		//Ext.getCmp('div').hide();
		//Ext.getCmp('div1').hide();
		//Ext.getCmp('div2').hide();
        detailsFn(obj);
	}}
	
	 function chexExist1(){
	    var mnum = mnumCombo.getValue(); 
	    Ext.Ajax.request({
					url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
					params : {
						type : 'CHEX1',
						mnum : mnum
						},
					success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)) {
						Ext.MessageBox.alert( '提示！',"零件编号存在，可以申请，点击确认继续！");
						hpOb.extraParams.filter = mnum;
						//hpOb.extraParams.hq = hq
						hpOb.extraParams.type = "QUERYALLCOM";
						storecom.load();
						 
						var load=Ext.Ajax.request({
							 	url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
							 	method:'post',
						 		params:{type:'QUERY1', id:mnum},//传递物资编号以便获取详细信息
								success:successFn1,
						 		failure:function(){
									Ext.MessageBox.alert('警告','服务器出现错误请稍后再试！');
					 			}
						  	});		
						}else{
						var confirm=Ext.MessageBox.show({
							title:'提醒!',
							width:400,
							msg:"该零件编号下不存在，请仔细确认！"				
						});
						 var tas=new Ext.util.DelayedTask();
							               tas.delay(3025,function(){
							                    confirm.hide();
							                    addFn();
							               });
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert( "提示:添加失败！");
					}
					
				}); 
    }
     function successFn1(result,request){
		var objtp = Ext.util.JSON.decode(result.responseText);
		//var objtp = result.responseText;
		//alert(objtp.filedata);
		var objt =	Ext.util.JSON.encode(objtp.filedata);
		//alert(objt);
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
		//alert(hq);
		   //取出所有物资编号相同时其中一条记录的高质与否的值，判断其是否为高质，如果是用厂商与进货号判断，如果不是用物资编码与厂商判断 
		   if(hq=='否'){//非高质
		   		Combocom.reset();
		   		Ext.getCmp('div1').show();
		   		Combonum.reset();
		   		Ext.getCmp('div2').hide();
		   		Ext.MessageBox.alert('提醒!零件编号不唯一',"你选择一种零件编号不唯一的非高质工具，请您点击确定对零件的厂商进行选择！");
		   		//simple.form.reset();
		   		Combocom.on("select",function(){
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
					   var com = Ext.get("incom").dom.value.trim();
					   //var num = Ext.get("innum").dom.value.trim();
					   if(com!='请选择厂商'){
					   		for(var j = 0;j<array.length;j++){
						   		if(com==array[j].company){
						   		detailsFn(array[j]);
						   		}
					   		}
			 		   }
		      });
		   }else{//高质
			   Combonum.reset();
			   Combocom.reset();
			   Ext.getCmp('div1').show();
			   Ext.getCmp('div2').show();
			   Ext.MessageBox.alert('提醒!零件编号不唯一',"你选择一种物资编号不唯一的高质工具，请您点击确定对工具的订货号和厂商进行选择！");
			   Combonum.on("select",function(){
						  var n = obj.indexOf('{');
						    var m = obj.indexOf('}'); 
						    var array = new Array();
						    var i=0;
						    while (n!=-1&&m!=-1){
						    	array[i]= obj.slice(n,m+1);
						    	n = obj.indexOf('{',n+1);
						    	m = obj.indexOf('}',m+1);
						    	array[i] = Ext.util.JSON.decode(array[i]);
						    	i++;
						    }
						   var com = Ext.get("incom").dom.value.trim();
						   var num = Ext.get("innum").dom.value.trim();
						   if(com!='请选择厂商' && num!='请选择订货号'){
						   		for(var j = 0;j<array.length;j++){
							   		if(com==array[j].company && num==array[j].order_num){
							   		detailsFn(array[j]);
							   		}
						   		}
						   }
			  });
		  }
		}else{//物资编码不重复
		var obj = Ext.util.JSON.decode(obj);
		Combonum.reset();
		Combocom.reset();
		//Ext.getCmp('div1').hide();
		//Ext.getCmp('div2').hide();
        detailsFn(obj);
	}}
    
    var los_mnum=new Ext.form.TextField({ fieldLabel: '零件编码',id: 'los_mnum', allowBlank:false,blankText:"物资编码不能为空，请填写!", width:125	});
	var los_pclass=new Ext.form.TextField({ fieldLabel: '整机',id: 'los_pclass',readOnly:true,   width:125 ,style : "background: #C1C1C1;"});
	var los_bclass=new Ext.form.TextField({ fieldLabel: '部件',id: 'los_bclass',readOnly:true,   width:125 ,style : "background: #C1C1C1;"});
	var los_ISO=new Ext.form.TextField({ fieldLabel: 'ISO号',id: 'los_ISO',   width:125});
	var los_name=new Ext.form.TextField({ fieldLabel: '名称',id: 'los_name',  width:125});	
	var los_mini_qs=new Ext.form.TextField({ fieldLabel: '最低库存量',  id: 'los_mini_qs', width:125});
	var los_use_freq=new Ext.form.TextField({ fieldLabel: '借用频率',  id: 'los_use_freq',readOnly:true, width:125 ,style : "background: #C1C1C1;"});
	var los_suit_mate1=new Ext.form.TextField({ fieldLabel: '适合加工材料1',  id: 'los_suit_mate1', width:125});
	var los_suit_mate2=new Ext.form.TextField({ fieldLabel: '适合加工材料2',  id: 'los_suit_mate2', width:125});	
	var los_suit_mate3=new Ext.form.TextField({ fieldLabel: '适合加工材料3',  id: 'los_suit_mate3', width:125});
	var los_rank_angle=new Ext.form.TextField({ fieldLabel: '前角',  id: 'los_rank_angle'});
	var los_c_mate=new Ext.form.TextField({ fieldLabel: '刀体材料',  id: 'los_c_mate', width:125});
	var los_coat_mate=new Ext.form.TextField({ fieldLabel: '涂层材料',  id: 'los_coat_mate', width:125});
	var los_e_diam=new Ext.form.TextField({ fieldLabel: '刃径',  id: 'los_e_diam'});
	var los_h_diam=new Ext.form.TextField({ fieldLabel: '柄径',  id: 'los_h_diam'});
	var los_e_leng=new Ext.form.TextField({ fieldLabel: '刃长',  id: 'los_e_leng', width:125,regex:/^\d+(\.\d+)?$/,regexText:'刃长必须由数字组成！！'});
	var los_t_leng=new Ext.form.TextField({ fieldLabel: '总长',  id: 'los_t_leng', width:125,regex:/^\d+(\.\d+)?$/,regexText:'总长必须由数字组成！'});
	var los_tee_count=new Ext.form.TextField({ fieldLabel: '齿数',  id: 'los_tee_count', width:125,regex:/^(0|[1-9][0-9]*)$/,regexText:'齿数必须为整数！'});
	var los_s_ang=new Ext.form.TextField({ fieldLabel: '螺旋角',  id: 'los_s_ang'});
	var los_tip_heig=new Ext.form.TextField({ fieldLabel: '刀尖高度',  id: 'los_tip_heig', width:125 ,regex:/^\d+(\.\d+)?$/,regexText:'刀尖高度必须由数字组成！'});
	var los_bla_wide=new Ext.form.TextField({ fieldLabel: '刀体宽度',  id: 'los_bla_wide', width:125,regex:/^\d+(\.\d+)?$/,regexText:'刀体宽度必须由数字组成！'});
	var los_c_leng=new Ext.form.TextField({ fieldLabel: '刀具长度',  id: 'los_c_leng', width:125,regex:/^\d+(\.\d+)?$/,regexText:'刀具长度必须由数字组成！'});
	var los_e_count=new Ext.form.TextField({ fieldLabel: '刃数',  id: 'los_e_count', width:125,regex:/^(0|[1-9][0-9]*)$/,regexText:'刃数必须由整数组成！'});
	var los_e_wide=new Ext.form.TextField({ fieldLabel: '切削刃宽',  id: 'los_e_wide', width:125,regex:/^\d+(\.\d+)?$/,regexText:'切削刃宽必须由数字组成！'});
	var los_t_form=new Ext.form.TextField({ fieldLabel: '螺纹形式',  id: 'los_t_form', width:125});
	var los_c_way=new Ext.form.TextField({ fieldLabel: '冷却方式',  id: 'los_c_way', width:125});
	var los_t_type=new Ext.form.TextField({ fieldLabel: '牙型',  id: 'los_t_type', width:125});
	var los_spec=new Ext.form.TextField({ fieldLabel: '规格',  id: 'los_spec', width:125});
	var los_remark=new Ext.form.TextArea({ fieldLabel: '备注',id: 'los_remark', width:374,height:80});
	var los_type=new Ext.form.TextField({ fieldLabel: '型号',id: 'los_type', width:125});
	var los_series_num=new Ext.form.TextField({ fieldLabel: '系列号',id: 'los_series_num', width:125});
	var los_order_num=new Ext.form.TextField({ fieldLabel: '订货号',id: 'los_order_num', width:125});
	var los_nc_count_in=new Ext.form.TextField({ fieldLabel: '新刀在库量',id: 'los_nc_count_in',readOnly:true, width:125,style : "background: #C1C1C1;"});
	var los_gc_count_in=new Ext.form.TextField({ fieldLabel: '刃磨刀在库量',id: 'los_gc_count_in',readOnly:true, width:125 ,style : "background: #C1C1C1;"}); 
	var los_uc_count_in=new Ext.form.TextField({ fieldLabel: '旧刀当前在库量',id: 'los_uc_count_in', readOnly:true,width:125 ,style : "background: #C1C1C1;"});	
	var los_effect_length=new Ext.form.TextField({ fieldLabel: '有效长度',id: 'los_effect_length', readOnly:true,width:125,regex:/^\d+(\.\d+)?$/,regexText:'有效长度必须由数字组成！'});	
    var los_company=new Ext.form.TextField({ fieldLabel: '厂商',id: 'los_company', readOnly:true,width:125});
    var los_iso_num=new Ext.form.TextField({ fieldLabel: '所属类型',id: 'los_iso_num',  width:125});
	//左面板
	 var leftpanel = new Ext.Panel({
			  id:'leftpanel',
			  width:320, 
			  height:178,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
			        columnWidth:1,					              
	                layout: 'form',		                
	                id:'left_panel',
	                labelAlign:"right",		               
	                items: [{   
		            	columnWidth:1,					              
		                layout: 'column',
		                id:'import_mnum',
		                items: [{   
			            	columnWidth:.81,					              
			                layout: 'form',
			                labelAlign:"right",
			                items: [los_mnum]	            
			          },{   columnWidth: .1,
			                bodyStyle:'padding:5px 0px 0',
			                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
			                border:false
		           }]	            
		           },los_pclass,los_type,los_series_num ]
		            }]
		  });
		 
	//右面板
	 var rightpanel = new Ext.Panel({
			  id:'rightpanel',
			  width:350, 
			  height:178,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
			        columnWidth:1,					              
	                layout: 'form',		                
	                id:'right_panel',
	                labelWidth:100,
	                labelAlign:"right",       
	                items: [ {   
		            	columnWidth:1,					              
		                layout: 'column',
		                items: [{   
			            	columnWidth:.81,					              
			                layout: 'form',
			                labelAlign:"right",
			                items: [los_name]	            
			          },{   columnWidth: .1,
			                bodyStyle:'padding:5px 0px 0',
			                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
			                border:false
		           }]	            
		           },los_bclass,los_spec,los_iso_num]  }]
		  });
		                    
     //库存位置面板
		 /* var locationpanel = new Ext.Panel({
			  id:'locationpanel',
			  width:520, 
			  height:26,
			  hideBorders:true,			   
			  hideLabels:false,
			  border:false,
			  
			  labelAlign:"right",
			  bodyStyle:'padding:4px 0px 0px 0px',
			  layout:'column',
			  items:[{   
	            	columnWidth:.26,					              
	                layout: 'form',		                         
	                id:'ku_panel',	               
	                labelAlign:"right",
	                items: [kucombo]	            
	          },{   
	            	columnWidth:.11,					              
	                layout: 'form',	
	                labelWidth:1,	                           
	                id:'cen_panel',
	                labelAlign:"right",
	                items: [cencombo]	            
	          },{   
	            	columnWidth:.11,					              
	                layout: 'form',	  
	                hideLabel:true,
	                labelWidth:1,	                               
	                id:'cen1_panel',
	                labelAlign:"right",
	                items: [cen1combo]	            
	          },{   
	            	columnWidth:.11,					              
	                layout: 'form',	  
	                hideLabel:true,              
	                labelWidth:1, 	                           
	                id:'wei_panel',
	                labelAlign:"right",
	                items: [weicombo]	            
	          },{  columnWidth: .4,
	                bodyStyle:'padding:5px 0px 0',
	                html:'（库-层-副层-位）<font color="#FF0000">&nbsp;*&nbsp;*</font>',
	                border:false
	            }]
		  });		 
		  
		   
		  */
	//remark面板
		  var remarkpanel = new Ext.Panel({
			  id:'remarkpanel',
			  width:520, 
			  height:85,					   
			  hideLabels:false,			
			  labelAlign:"right",
			  bodyStyle:'padding:4px 0px 0px 0px',
			  layout:'column',
			  items:[{
			        columnWidth:1,					              
	                layout: 'form',		                
	                id:'remark_panel',	               
	                labelAlign:"right",		               
	                items: [los_remark]
	                }]
		  });
		  
   //修改、添加弹出面板   
    var modi_panel = new Ext.FormPanel({
        id:'modi_panel',
        labelWidth: 80,        
        width:520 ,
        height:325,	
        layout:"form",
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:10px 10px 0',         
		layout:'column',
		items:[
	            {		           		                
	                columnWidth:.47,					              
	                layout: 'form',	            
	                labelAlign:"right",		               
	                items: [leftpanel]
	           },{		           		                
	                columnWidth:.53,					              
	                layout: 'form',	               
	                labelAlign:"right",		               
	                items:[rightpanel]
	           },{   
	            	columnWidth:1,					              
	                layout: 'form',	           
	                labelAlign:"right",
	                items: [remarkpanel]	            
	          }]
		     });   
	
	 
    var win_in_panel=new Ext.Panel({
				        id:'win_panel',
                		layout:'column',
                		frame:true,
                		height:350,
                		items:[modi_panel,
			                {
								columnWidth: .10,
								bodyStyle:'padding:1px 0px 0',
								border:false
							} ]
                })
    
	//定义弹出窗口
    var win = new Ext.Window({
    			id:'win',
    			//modal:true,                                         
                width:540,
                height:400,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
                autoShow: true,
				bodyBorder:true,
				resizable:false,
				listeners:{
				  "hide":function(){
				    if(keyBoardWin.isVisible()){									    
						keyBoard.hide();
					}
				    if($.keypad._keypadShowing){	
					     $('#los_rank_angle').keypad('destroy');;
			             $('#los_e_diam').keypad('destroy');
			             $('#los_h_diam').keypad('destroy');				           
			             $('#los_s_ang').keypad('destroy');
			             $('#los_t_form').keypad('destroy');
			             $('#los_t_type').keypad('destroy');
			             $('#los_spec').keypad('destroy');
			             $('#los_type').keypad('destroy');	
			             }
				  }
				},	
				items:[win_in_panel],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                    var xiaoleijs = Ext.getCmp("los_bclass").getValue();	
		                    if(xiaoleijs==''){		                    
		                     Ext.MessageBox.alert("提醒","部件不能为空");											
							if(mnumjs==""){
								alert('零件编码不能为空！');
							   }
							else if(namejs==""){
									alert('名称不能为空！');
							   }
							else if(remarkjs.length>60){
									alert('备注不能超过60个字，请修改！');
							}else{
							       addsaveFn();		
						   }
						   }
						   }
	                },{ id:'cancelbtn',
						text: '取消',
						handler:cancelFn
	                },{ id:'resetbtn',
						text: '重置',
						handler:resetFn
	                },{
					text: '键盘',
					id:'keyB',
					handler:function(){
						if(keyBoardWin.isVisible()){
							keyBoard.hide();						
							//Ext.getCmp("keyB").setText("显示键盘");
						}else{						 
							keyBoard.show();
							//Ext.getCmp("keyB").setText("隐藏键盘");
						}
			          }//键盘
		            }]
    });    
    
    
    var add_win = new Ext.Window({
    			id:'add_win',
    			modal:true,                                         
                width:520,
                height:200,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
                autoShow: true,
				bodyBorder:true,
				resizable:false,			
				items:[new Ext.Panel({
                		layout:'column',
                		frame:true,
                		height:127,                		
						   items:[{		                
				                columnWidth:.45,	
				                labelWidth:45,
				                id:'add_left',				              
				                layout: 'form',
				                bodyStyle:'padding:41px 4px 4px',				            
				                labelAlign:"right",		               
				                items: [add_pclasscombo]
				           },{		                
				                columnWidth:.55,	
				                labelWidth:45,	
				                bodyStyle:'padding:41px 4px 4px',			              
				                layout: 'form',
				                id:'add_right',				                
				                labelAlign:"right",		               
				                items: [add_bclasscombo]
		          
		           }]
                })],
                buttons: [{
                        id:'nextbtn',
		                text:'下一步',
		            //   type: 'submit',
		                handler:winShowFn
		                },{ id:'cancelbtn1',
						text: '取消',
						handler:add_cancelFn
	                }]	                
    });
     
     //add_win窗口显示函数   
     function  winShowFn(){
		                var daleijs=add_pclasscombo.getValue();
		                var xiaoleijs=add_bclasscombo.getValue();
		                  if(daleijs==''){
		                     alert("请选择部件");
		                    }else if(xiaoleijs==''){
		                     alert("请选择整机");
		                    }else if(!win.isVisible()){	
		                    Ext.getCmp("resetbtn").show();			                    
		                   	 win.setTitle("<p align='center'>添加新类型零件</p>");
		                     add_win.hide();
		                     win.show();
		                     $('#los_rank_angle').keypad();
				            $('#los_e_diam').keypad();
				            $('#los_h_diam').keypad();				           
				            $('#los_s_ang').keypad();
				            $('#los_t_form').keypad();
				            $('#los_t_type').keypad();
				            $('#los_spec').keypad();
				            $('#los_type').keypad();
		                     //focus
						     Ext.onReady(function(){			    
							 Ext.getCmp("los_mnum").focus();				
							 Ext.get("los_mnum").on("focus", function(){keyBoardInput = Ext.getDom("los_mnum");});
							// Ext.get("los_ISO").on("focus", function(){keyBoardInput = Ext.getDom("los_ISO");});
							 Ext.get("los_name").on("focus", function(){keyBoardInput = Ext.getDom("los_name");});
							// Ext.get("los_mini_qs").on("focus", function(){keyBoardInput = Ext.getDom("los_mini_qs");});
							// Ext.get("los_suit_mate1").on("focus", function(){keyBoardInput = Ext.getDom("los_suit_mate1");});
							// Ext.get("los_suit_mate2").on("focus", function(){keyBoardInput = Ext.getDom("los_suit_mate2");});				
							// Ext.get("los_suit_mate3").on("focus", function(){keyBoardInput = Ext.getDom("los_suit_mate3");});
							// Ext.get("los_rank_angle").on("focus", function(){keyBoardInput = Ext.getDom("los_rank_angle");});
							// Ext.get("los_c_mate").on("focus", function(){keyBoardInput = Ext.getDom("los_c_mate");});
							// Ext.get("los_coat_mate").on("focus", function(){keyBoardInput = Ext.getDom("los_coat_mate");});
							// Ext.get("los_e_diam").on("focus", function(){keyBoardInput = Ext.getDom("los_e_diam");});
							// Ext.get("los_h_diam").on("focus", function(){keyBoardInput = Ext.getDom("los_h_diam");});
							// Ext.get("los_e_leng").on("focus", function(){keyBoardInput = Ext.getDom("los_e_leng");});
							// Ext.get("los_t_leng").on("focus", function(){keyBoardInput = Ext.getDom("los_t_leng");});				
							// Ext.get("los_tee_count").on("focus", function(){keyBoardInput = Ext.getDom("los_tee_count");});
							// Ext.get("los_s_ang").on("focus", function(){keyBoardInput = Ext.getDom("los_s_ang");});							 
							// Ext.get("los_tip_heig").on("focus", function(){keyBoardInput = Ext.getDom("los_tip_heig");});
							// Ext.get("los_bla_wide").on("focus", function(){keyBoardInput = Ext.getDom("los_bla_wide");});
							// Ext.get("los_c_leng").on("focus", function(){keyBoardInput = Ext.getDom("los_c_leng");});
							// Ext.get("los_e_count").on("focus", function(){keyBoardInput = Ext.getDom("los_e_count");});
							 //Ext.get("los_e_wide").on("focus", function(){keyBoardInput = Ext.getDom("los_e_wide");});
							// Ext.get("los_t_form").on("focus", function(){keyBoardInput = Ext.getDom("los_t_form");});				
							// Ext.get("los_c_way").on("focus", function(){keyBoardInput = Ext.getDom("los_c_way");});
							// Ext.get("los_t_type").on("focus", function(){keyBoardInput = Ext.getDom("los_t_type");});
							// Ext.get("los_spec").on("focus", function(){keyBoardInput = Ext.getDom("los_spec");});
							 Ext.get("los_remark").on("focus", function(){keyBoardInput = Ext.getDom("los_remark");});
							 Ext.get("los_type").on("focus", function(){keyBoardInput = Ext.getDom("los_type");});
							 Ext.get("los_series_num").on("focus", function(){keyBoardInput = Ext.getDom("los_series_num");});
							 Ext.get("los_order_num").on("focus", function(){keyBoardInput = Ext.getDom("los_order_num");});
							// Ext.get("los_effect_length").on("focus", function(){keyBoardInput = Ext.getDom("los_effect_length");});
							 Ext.get("company").on("focus", function(){keyBoardInput = Ext.getDom("company");});
										
							 keyBoard.hide();		   
					       });
					       
					         Ext.getCmp("keyB").hide();	
					         Ext.getCmp("keyB").setText("键盘");	
		                     var pclass = add_pclasscombo.getValue();
		                     var bclass = add_bclasscombo.getValue();
		                     Ext.getCmp("resetbtn").show();		
			                 Ext.getCmp("savebtn").show();	
							 Ext.getCmp("cancelbtn").show();		
		                     modi_panel.form.reset();        
		                     sharedFn(pclass,bclass);
						 }
	                }
	 	  //取消函数
   	  function cancelFn(){   	 
	     	   simple.form.reset();
   	   Combonum.reset();
		Combocom.reset();
		hpCombo.reset();
		//Ext.getCmp('div').hide();
		//Ext.getCmp('div1').hide();
		//Ext.getCmp('div2').hide();
		keyBoard.hide();
        //Ext.getCmp("keyA").setText("显示键盘");
        //Ext.getCmp("keyB").setText("显示键盘");
    	win.hide();   
       }                
	//添加取消函数  
   	 function add_cancelFn(){
   	   add_win.hide();
   	   simple.form.reset();
   	   Combonum.reset();
		Combocom.reset();
		hpCombo.reset();
		//Ext.getCmp('div').hide();
		//Ext.getCmp('div1').hide();
		//Ext.getCmp('div2').hide();
		keyBoard.hide();
        //Ext.getCmp("keyA").setText("显示键盘");
       // Ext.getCmp("keyB").setText("显示键盘");
   	   }                
    
    //重置函数
   	  function resetFn(){
   	    var former_pclass=Ext.getCmp("los_pclass").getValue();
   	    var former_bclass=Ext.getCmp("los_bclass").getValue(); 
   	    modi_panel.form.reset();   	    
   	    Ext.getCmp("los_pclass").setValue(former_pclass);   	    
   	    Ext.getCmp("los_bclass").setValue(former_bclass);
	    Ext.getCmp("los_nc_count_in").setValue(0);
        Ext.getCmp("los_gc_count_in").setValue(0);
        Ext.getCmp("los_uc_count_in").setValue(0);
        Ext.getCmp("los_use_freq").setValue(0); 
   	   }
   	   
	//添加弹出窗口函数
     function addFn(){
		if(!add_win.isVisible()){                
				add_win.setTitle("<p align='center'>添加新类型零件</p>");
				add_win.show();//添加窗口显示	
				keyBoard.hide();
				 //Ext.getCmp("keyA").setText("显示键盘");
				 //Ext.getCmp("keyB").setText("显示键盘");			
				}
	 }
	 
	 function sharedFn(pclass,bclass){
	 keyBoard.hide();
	 //Ext.getCmp("keyA").setText("显示键盘");
	 //Ext.getCmp("keyB").setText("显示键盘");
      var daleijs=pclass;
      var xiaoleijs=bclass;      
     // Ext.DomQuery.selectNode("label[for='los_rank_angle']").innerHTML="前角:";
     if(xiaoleijs=="上轧辊总成"){	  
		                     Panel_Refesh()  ;		                       
			                 win.setHeight(460);
	                         win_in_panel.setHeight(425);
	                         rightpanel.setHeight(258);	                        
	                         leftpanel.setHeight(258);
	                         modi_panel.setHeight(425); 
	                         Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					        
					         Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);                       
		                     Ext.getCmp('import_mnum').setVisible(true);
				             Ext.getCmp('los_ISO').getEl().up('.x-form-item').setDisplayed(true); 
		                     Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                     Ext.getCmp('import_company').setVisible(true); 
		                     Ext.getCmp('import_hq').setVisible(true);
		                     Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('import_mini_qs').setVisible(true); 
                             Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                     Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);	
		              //     Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);		                                     
		                     Ext.getCmp("los_pclass").setValue(daleijs);
		                     Ext.getCmp("los_bclass").setValue(xiaoleijs);
		                     Ext.getCmp("los_nc_count_in").setValue(0);		                  
		                     Ext.getCmp("los_uc_count_in").setValue(0);
		                     Ext.getCmp("los_use_freq").setValue(0);
		                    }else if(xiaoleijs=="润滑系统"){	
		                       Panel_Refesh()  ;
		                       win.setHeight(525);
		                       win_in_panel.setHeight(497);
		                       rightpanel.setHeight(312);
		                       leftpanel.setHeight(312);
		                       modi_panel.setHeight(487);
		                       Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					          
					           Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('import_mnum').setVisible(true);                              
		                       Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('import_company').setVisible(true);
                               Ext.getCmp('import_hq').setVisible(true);
                               Ext.getCmp('los_e_diam').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_h_diam').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_e_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_e_count').getEl().up('.x-form-item').setDisplayed(true);		                       
		                       Ext.getCmp('los_t_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('import_mini_qs').setVisible(true); 
                               Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);
		                  //   Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.DomQuery.selectNode('label[for="los_rank_angle"]').innerHTML='R角:';
                               Ext.getCmp("los_pclass").setValue(daleijs);
		                       Ext.getCmp("los_bclass").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in").setValue(0);		                     
		                       Ext.getCmp("los_uc_count_in").setValue(0);	
		                       Ext.getCmp("los_use_freq").setValue(0);                      
		                    }else if(xiaoleijs=="冷却辊总成"){	
		                       Panel_Refesh()  ;
		                       win.setHeight(510);
		                       win_in_panel.setHeight(497);
		                       rightpanel.setHeight(310);
		                       leftpanel.setHeight(310);
		                       modi_panel.setHeight(487);
		                       Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('import_mnum').setVisible(true);                              
		                       Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('import_company').setVisible(true);
                               Ext.getCmp('import_hq').setVisible(true);
                               Ext.getCmp('los_e_diam').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_h_diam').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_e_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_s_ang').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_e_count').getEl().up('.x-form-item').setDisplayed(true);		                       
		                       Ext.getCmp('los_t_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('import_mini_qs').setVisible(true); 
                               Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);
		                    // Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);
		                       //改变前角为R角
		                      // Ext.DomQuery.selectNode('label[for="los_rank_angle"]').innerHTML='R角:';
		                       
                               Ext.getCmp("los_pclass").setValue(daleijs);
		                       Ext.getCmp("los_bclass").setValue(xiaoleijs); 	
		                       Ext.getCmp("los_nc_count_in").setValue(0);		                      
		                       Ext.getCmp("los_uc_count_in").setValue(0);
		                       Ext.getCmp("los_use_freq").setValue(0);                      
		                    }else if(xiaoleijs=="液压系统"){	
		                       Panel_Refesh()  ;
		                       win.setHeight(490);
		                       win_in_panel.setHeight(477);
		                       rightpanel.setHeight(285);
		                       leftpanel.setHeight(285);
		                       modi_panel.setHeight(467);
		                       Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('import_mnum').setVisible(true);                              
		                       Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('import_company').setVisible(true);
                               Ext.getCmp('import_hq').setVisible(true);                               
                               Ext.getCmp('los_tip_heig').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_bla_wide').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_c_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);		                      
		                       Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('import_mini_qs').setVisible(true); 
                               Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);
		                 //    Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);		                 
                               Ext.getCmp("los_pclass").setValue(daleijs);
		                       Ext.getCmp("los_bclass").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in").setValue(0);		                     
		                       Ext.getCmp("los_uc_count_in").setValue(0);
		                       Ext.getCmp("los_use_freq").setValue(0);	                      
		                    }else if(xiaoleijs=="传动系统"){	
		                       Panel_Refesh()  ;
		                       win.setHeight(530);
		                       win_in_panel.setHeight(527);
		                       rightpanel.setHeight(312);
		                       leftpanel.setHeight(312);
		                       modi_panel.setHeight(487);
		                       Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('import_mnum').setVisible(true);                              
		                       Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('import_company').setVisible(true);
                               Ext.getCmp('import_hq').setVisible(true); 
                               Ext.getCmp('los_tip_heig').getEl().up('.x-form-item').setDisplayed(true);                              
                               Ext.getCmp('los_e_wide').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_bla_wide').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_c_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);		                      
		                       Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('import_mini_qs').setVisible(true); 
                               Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);	
		                       Ext.getCmp('los_e_count').getEl().up('.x-form-item').setDisplayed(true);
		                 //    Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);	                 
                               Ext.getCmp("los_pclass").setValue(daleijs);
		                       Ext.getCmp("los_bclass").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in").setValue(0);		                      
		                       Ext.getCmp("los_uc_count_in").setValue(0);
		                       Ext.getCmp("los_use_freq").setValue(0);	                      
		                    }else if(xiaoleijs=="机架总成"){	
		                       Panel_Refesh()  ;
		                       win.setHeight(500);
		                       win_in_panel.setHeight(477);
		                       rightpanel.setHeight(287);
		                       leftpanel.setHeight(287);
		                       modi_panel.setHeight(467);
		                       Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('import_mnum').setVisible(true);                              
		                       Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('import_company').setVisible(true);
                               Ext.getCmp('import_hq').setVisible(true); 
                               Ext.getCmp('los_t_form').getEl().up('.x-form-item').setDisplayed(true);     
                               Ext.getCmp('los_tip_heig').getEl().up('.x-form-item').setDisplayed(true);                
                               Ext.getCmp('los_bla_wide').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_c_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);		                      
		                       Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('import_mini_qs').setVisible(true); 
                               Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);
		               //      Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);			                               
                               Ext.getCmp("los_pclass").setValue(daleijs);
		                       Ext.getCmp("los_bclass").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in").setValue(0);		                     
		                       Ext.getCmp("los_uc_count_in").setValue(0);
		                       Ext.getCmp("los_use_freq").setValue(0);		                       	          					         			                                         
		                    }else if(xiaoleijs=="下轧辊总成"){	
		                       Panel_Refesh()  ;
		                       win.setHeight(550);
		                       win_in_panel.setHeight(527);
		                       rightpanel.setHeight(339);
		                       leftpanel.setHeight(339);
		                       modi_panel.setHeight(514);
		                       Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('import_mnum').setVisible(true);                              
		                       Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('import_company').setVisible(true);
                               Ext.getCmp('import_hq').setVisible(true); 
                               Ext.getCmp('los_e_diam').getEl().up('.x-form-item').setDisplayed(true);     
                               Ext.getCmp('los_h_diam').getEl().up('.x-form-item').setDisplayed(true);                
                               Ext.getCmp('los_e_leng').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_t_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_s_ang').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_tee_count').getEl().up('.x-form-item').setDisplayed(true);  
		                       Ext.getCmp('los_c_way').getEl().up('.x-form-item').setDisplayed(true);  		                      
		                       Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('import_mini_qs').setVisible(true); 
                               Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);
		                //     Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);			                               
                               Ext.getCmp("los_pclass").setValue(daleijs);
		                       Ext.getCmp("los_bclass").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in").setValue(0);		                    
		                       Ext.getCmp("los_uc_count_in").setValue(0);
		                       Ext.getCmp("los_use_freq").setValue(0);		                       	          					         			                                         
		                    }else if(xiaoleijs=="限位调节装置"){	
		                       Panel_Refesh()  ;
		                       win.setHeight(530);
		                       win_in_panel.setHeight(507);
		                       rightpanel.setHeight(312);
		                       leftpanel.setHeight(312);
		                       modi_panel.setHeight(497);
		                       Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('import_mnum').setVisible(true);                              
		                       Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('import_company').setVisible(true);
                               Ext.getCmp('import_hq').setVisible(true); 
                               Ext.getCmp('los_e_diam').getEl().up('.x-form-item').setDisplayed(true);     
                               Ext.getCmp('los_h_diam').getEl().up('.x-form-item').setDisplayed(true);                
                               Ext.getCmp('los_e_leng').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_t_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_tee_count').getEl().up('.x-form-item').setDisplayed(true);  		                                         
		                       Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('import_mini_qs').setVisible(true); 
                               Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);
		              //       Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);			                               
                               Ext.getCmp("los_pclass").setValue(daleijs);
		                       Ext.getCmp("los_bclass").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in").setValue(0);		                     
		                       Ext.getCmp("los_uc_count_in").setValue(0);
		                       Ext.getCmp("los_use_freq").setValue(0);		                       	          					         			                                         
		                    }else if(xiaoleijs=="轴交叉调节装置"){	
		                       Panel_Refesh()  ;
		                       win.setHeight(540);
		                       win_in_panel.setHeight(527);
		                       rightpanel.setHeight(339);
		                       leftpanel.setHeight(339);
		                       modi_panel.setHeight(517);
		                       Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					        
					           Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('import_mnum').setVisible(true);                              
		                       Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('import_company').setVisible(true);
                               Ext.getCmp('import_hq').setVisible(true); 
                               Ext.getCmp('los_e_diam').getEl().up('.x-form-item').setDisplayed(true);     
                               Ext.getCmp('los_h_diam').getEl().up('.x-form-item').setDisplayed(true);                
                               Ext.getCmp('los_e_leng').getEl().up('.x-form-item').setDisplayed(true);                              
                               Ext.getCmp('los_t_leng').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_t_type').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_tee_count').getEl().up('.x-form-item').setDisplayed(true); 		                       
		                       Ext.getCmp('los_c_way').getEl().up('.x-form-item').setDisplayed(true);  		                      
		                       Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('import_mini_qs').setVisible(true); 
                               Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);
		                //     Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);			                               
                               Ext.getCmp("los_pclass").setValue(daleijs);
		                       Ext.getCmp("los_bclass").setValue(xiaoleijs); 		                             					         			                                         
		                       Ext.getCmp("los_nc_count_in").setValue(0);		                      
		                       Ext.getCmp("los_uc_count_in").setValue(0);
		                       Ext.getCmp("los_use_freq").setValue(0);
		                    }else if(xiaoleijs=="升降座"){	  
		                     Panel_Refesh()  ;	
	                         win.setHeight(440);
	                         win_in_panel.setHeight(417);
	                         rightpanel.setHeight(235);	                        
	                         leftpanel.setHeight(235);
	                         modi_panel.setHeight(417); 
	                         Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(true);					        
					         Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(true);      
		                     Ext.getCmp('import_mnum').setVisible(true);				           
		                     Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(true);		                     
		                     Ext.getCmp('import_company').setVisible(true);
                             Ext.getCmp('import_hq').setVisible(true);
		                     Ext.getCmp('los_spec').getEl().up('.x-form-item').setDisplayed(true);	
		                     Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(true);		                    
		                     Ext.getCmp('import_mini_qs').setVisible(true); 
                             Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(true); 
		                     Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(true);
		             //      Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(true);		                     
		                     Ext.getCmp("los_pclass").setValue(daleijs);
		                     Ext.getCmp("los_bclass").setValue(xiaoleijs);
		                     Ext.getCmp("los_nc_count_in").setValue(0);		                    
		                     Ext.getCmp("los_uc_count_in").setValue(0);
		                     Ext.getCmp("los_use_freq").setValue(0);
		                    }
   }
   
   	 function detailsharedFn(pclass,bclass){
      var daleijs=pclass;
      var xiaoleijs=bclass;
    //  Ext.DomQuery.selectNode("label[for='los_rank_angle0']").innerHTML="前角:";
     if(xiaoleijs=="上轧辊总成"){	  
		                     //Panel_Refesh0();		
	                         rightpanel0.setHeight(200);	                        
	                         leftpanel0.setHeight(200); 
	                         /*Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					        
					         Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);                       
		                     Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true); 
				             Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		                     Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                     Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true); 
		                     Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                     Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);	
		              //     Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);	   */                                  
		                     Ext.getCmp("los_pclass0").setValue(daleijs); 
		                     Ext.getCmp("los_bclass0").setValue(xiaoleijs);
		                     Ext.getCmp("los_nc_count_in0").setValue(0);		                  
		                     Ext.getCmp("los_uc_count_in0").setValue(0);
		                     Ext.getCmp("los_use_freq0").setValue(0);
		                    }else if(xiaoleijs=="润滑系统"){	
		                      // Panel_Refesh0();
		                       rightpanel0.setHeight(200);
		                       leftpanel0.setHeight(200);
		                      /* Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					          
					           Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true);                               
		                       Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                              Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_e_diam0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_h_diam0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_e_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_e_count0').getEl().up('.x-form-item').setDisplayed(true);		                       
		                       Ext.getCmp('los_t_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);
		                  //   Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.DomQuery.selectNode("label[for='los_rank_angle0']").innerHTML="R角:";*/
                               Ext.getCmp("los_pclass0").setValue(daleijs);
		                       Ext.getCmp("los_bclass0").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in0").setValue(0);		                     
		                       Ext.getCmp("los_uc_count_in0").setValue(0);	
		                       Ext.getCmp("los_use_freq0").setValue(0);                      
		                    }else if(xiaoleijs=="冷却辊总成"){	
		                       //Panel_Refesh0();
		                       rightpanel0.setHeight(200);
		                       leftpanel0.setHeight(200);
		                     /*  Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true);                               
		                       Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_e_diam0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_h_diam0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_e_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_s_ang0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_e_count0').getEl().up('.x-form-item').setDisplayed(true);		                       
		                       Ext.getCmp('los_t_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		                    // Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.DomQuery.selectNode("label[for='los_rank_angle0']").innerHTML="R角:";*/
                               Ext.getCmp("los_pclass0").setValue(daleijs);
		                       Ext.getCmp("los_bclass0").setValue(xiaoleijs); 	
		                       Ext.getCmp("los_nc_count_in0").setValue(0);		                      
		                       Ext.getCmp("los_uc_count_in0").setValue(0);
		                       Ext.getCmp("los_use_freq0").setValue(0);                      
		                    }else if(xiaoleijs=="液压系统"){	
		                       //Panel_Refesh0();
		                       rightpanel0.setHeight(200);
		                       leftpanel0.setHeight(200);
		                      /* Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true);                               
		                       Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true);                                 
                               Ext.getCmp('los_tip_heig0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_bla_wide0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_c_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);		                      
		                       Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		                 //    Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);		*/                 
                               Ext.getCmp("los_pclass0").setValue(daleijs);
		                       Ext.getCmp("los_bclass0").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in0").setValue(0);		                     
		                       Ext.getCmp("los_uc_count_in0").setValue(0);
		                       Ext.getCmp("los_use_freq0").setValue(0);	                      
		                    }else if(xiaoleijs=="传动系统"){	
		                      //Panel_Refesh0()  ;
		                       rightpanel0.setHeight(200);
		                       leftpanel0.setHeight(200);
		                     /*  Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true);                               
		                       Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true);   
                               Ext.getCmp('los_tip_heig0').getEl().up('.x-form-item').setDisplayed(true);                              
                               Ext.getCmp('los_e_wide0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_bla_wide0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_c_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);		                      
		                       Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);	
		                       Ext.getCmp('los_e_count0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		                 //    Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);	*/                 
                               Ext.getCmp("los_pclass0").setValue(daleijs);
		                       Ext.getCmp("los_bclass0").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in0").setValue(0);		                      
		                       Ext.getCmp("los_uc_count_in0").setValue(0);
		                       Ext.getCmp("los_use_freq0").setValue(0);	                      
		                    }else if(xiaoleijs=="机架总成"){	
		                       //Panel_Refesh0()  ;
		                       rightpanel0.setHeight(200);
		                       leftpanel0.setHeight(200);
		                     /*  Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true);                               
		                       Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true);   
                               Ext.getCmp('los_t_form0').getEl().up('.x-form-item').setDisplayed(true);     
                               Ext.getCmp('los_tip_heig0').getEl().up('.x-form-item').setDisplayed(true);                
                               Ext.getCmp('los_bla_wide0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_c_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);		                      
		                       Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		               //      Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);	*/		                               
                               Ext.getCmp("los_pclass0").setValue(daleijs);
		                       Ext.getCmp("los_bclass0").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in0").setValue(0);		                     
		                       Ext.getCmp("los_uc_count_in0").setValue(0);
		                       Ext.getCmp("los_use_freq0").setValue(0);		                       	          					         			                                         
		                    }else if(xiaoleijs=="下轧辊总成"){	
		                      // Panel_Refesh0()  ;
		                       rightpanel0.setHeight(200);
		                       leftpanel0.setHeight(200);
		                      /* Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true);                               
		                       Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                      Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true);   
                               Ext.getCmp('los_e_diam0').getEl().up('.x-form-item').setDisplayed(true);     
                               Ext.getCmp('los_h_diam0').getEl().up('.x-form-item').setDisplayed(true);                
                               Ext.getCmp('los_e_leng0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_t_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_s_ang0').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_tee_count0').getEl().up('.x-form-item').setDisplayed(true);  
		                       Ext.getCmp('los_c_way0').getEl().up('.x-form-item').setDisplayed(true);  		                      
		                       Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		                //     Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);*/			                               
                               Ext.getCmp("los_pclass0").setValue(daleijs);
		                       Ext.getCmp("los_bclass0").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in0").setValue(0);		                    
		                       Ext.getCmp("los_uc_count_in0").setValue(0);
		                       Ext.getCmp("los_use_freq0").setValue(0);		                       	          					         			                                         
		                    }else if(xiaoleijs=="限位调节装置"){	
		                       //Panel_Refesh0();
		                       rightpanel0.setHeight(200);
		                       leftpanel0.setHeight(200);
		                      /* Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					         
					           Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true);                               
		                       Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true);  
                               Ext.getCmp('los_e_diam0').getEl().up('.x-form-item').setDisplayed(true);     
                               Ext.getCmp('los_h_diam0').getEl().up('.x-form-item').setDisplayed(true);                
                               Ext.getCmp('los_e_leng0').getEl().up('.x-form-item').setDisplayed(true);
                               Ext.getCmp('los_t_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_tee_count0').getEl().up('.x-form-item').setDisplayed(true);  		                                         
		                       Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		              //       Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);	*/		                               
                               Ext.getCmp("los_pclass0").setValue(daleijs);
		                       Ext.getCmp("los_bclass0").setValue(xiaoleijs); 
		                       Ext.getCmp("los_nc_count_in0").setValue(0);		                     
		                       Ext.getCmp("los_uc_count_in0").setValue(0);
		                       Ext.getCmp("los_use_freq0").setValue(0);		                       	          					         			                                         
		                    }else if(xiaoleijs=="轴交叉调节装置"){	
		                      // Panel_Refesh0()  ;
		                       //win.setHeight(540);
		                      // win_in_panel.setHeight(527);
		                       rightpanel0.setHeight(200);
		                       leftpanel0.setHeight(200);
		                       //modi_panel.setHeight(517);
		                      /* Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					           Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					        
					           Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                       Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true);                               
		                       Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                       Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_e_diam0').getEl().up('.x-form-item').setDisplayed(true);     
                               Ext.getCmp('los_h_diam0').getEl().up('.x-form-item').setDisplayed(true);                
                               Ext.getCmp('los_e_leng0').getEl().up('.x-form-item').setDisplayed(true);                              
                               Ext.getCmp('los_t_leng0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_t_type0').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_tee_count0').getEl().up('.x-form-item').setDisplayed(true); 		                       
		                       Ext.getCmp('los_c_way0').getEl().up('.x-form-item').setDisplayed(true);  		                      
		                       Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                               Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                       Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		                //     Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                       Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);	*/		                               
                               Ext.getCmp("los_pclass0").setValue(daleijs);
		                       Ext.getCmp("los_bclass0").setValue(xiaoleijs); 		                             					         			                                         
		                       Ext.getCmp("los_nc_count_in0").setValue(0);		                      
		                       Ext.getCmp("los_uc_count_in0").setValue(0);
		                       Ext.getCmp("los_use_freq0").setValue(0);
		                    }else if(xiaoleijs=="升降座"){	  
		                     //Panel_Refesh0()  ;	
	                         //win.setHeight(440);
	                        // win_in_panel.setHeight(417);
	                         rightpanel0.setHeight(200);	                        
	                         leftpanel0.setHeight(200);
	                         //modi_panel.setHeight(417); 
	                       /*  Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(true);
					         Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(true);					        
					         Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(true);      
		                     Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(true); 				           
		                     Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(true);		                     
		                     Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true);  
		                     Ext.getCmp('los_spec0').getEl().up('.x-form-item').setDisplayed(true);	
		                     Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(true);		                    
		                     Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(true); 
                             Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(true); 
		                     Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(true); 
		             //      Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(true);
		                     Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(true);		*/                     
		                     Ext.getCmp("los_pclass0").setValue(daleijs);
		                     Ext.getCmp("los_bclass0").setValue(xiaoleijs);
		                     Ext.getCmp("los_nc_count_in0").setValue(0);		                    
		                     Ext.getCmp("los_uc_count_in0").setValue(0);
		                     Ext.getCmp("los_use_freq0").setValue(0);
		                    }
		                //readOnlyFn();    
		                  
   }
   
   //readOnly true 函数
	function Panel_Refesh()    	 
		{     
		      //Ext.getCmp('dis_pclass').getEl().up('.x-form-item').setDisplayed(false);
		      //Ext.getCmp('dis_bclass').getEl().up('.x-form-item').setDisplayed(false);
		      //Ext.getCmp('dis_use_freq').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('locationpanel').setVisible(true);
		      Ext.getCmp('dislocation').setVisible(false);
		      Ext.getCmp('import_mnum').setVisible(false); 		      
		      Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(false);		    
		      Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_ISO').getEl().up('.x-form-item').setDisplayed(false);
		     Ext.getCmp('import_company').setVisible(false);
		      Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('import_hq').setVisible(false); 
		      Ext.getCmp('los_e_diam').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_h_diam').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_leng').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_wide').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_tip_heig').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_bla_wide').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_way').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(false);		      
		      Ext.getCmp('import_mini_qs').setVisible(false); 
		      Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_tee_count').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_leng').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_s_ang').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_count').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_leng').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_form').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_spec').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_type').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_gc_count_in').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(false);		      
		      Ext.getCmp('los_effect_length').getEl().up('.x-form-item').setDisplayed(false);
		}
   	 
    
		      //面板控件隐藏函数
   	 function Panel_Refesh0()    	 
		{   
		      Ext.getCmp('los_mnum0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_pclass0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_bclass0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_iso_num0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_company0').getEl().up('.x-form-item').setDisplayed(true); 
               Ext.getCmp('los_hq0').getEl().up('.x-form-item').setDisplayed(true); 
		      Ext.getCmp('los_name0').getEl().up('.x-form-item').setDisplayed(false);
		      //Ext.getCmp('hq').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_diam0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_h_diam0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_leng0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_wide0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_tip_heig0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_bla_wide0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_way0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_mate0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_coat_mate0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_mini_qs0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_suit_mate10').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_suit_mate20').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_suit_mate30').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_use_freq0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_rank_angle0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_tee_count0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_leng0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_s_ang0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_count0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_leng0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_form0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_spec0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_type0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_type0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_series_num0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_order_num0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_nc_count_in0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_gc_count_in0').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_uc_count_in0').getEl().up('.x-form-item').setDisplayed(false);		      
		      Ext.getCmp('los_effect_length0').getEl().up('.x-form-item').setDisplayed(false);
		}
		
	      //面板控件隐藏函数
   	 function Panel_Refesh()    	 
		{   
		      Ext.getCmp('los_mnum').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_pclass').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_bclass').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_iso_num').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('company').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_name').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('hq').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_diam').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_h_diam').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_leng').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_wide').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_tip_heig').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_bla_wide').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_way').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_mate').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_coat_mate').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_mini_qs').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_suit_mate1').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_suit_mate2').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_suit_mate3').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_use_freq').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_rank_angle').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_tee_count').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_leng').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_s_ang').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_e_count').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_c_leng').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_form').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_spec').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_t_type').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_type').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_series_num').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_order_num').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_nc_count_in').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_gc_count_in').getEl().up('.x-form-item').setDisplayed(false);
		      Ext.getCmp('los_uc_count_in').getEl().up('.x-form-item').setDisplayed(false);		      
		      Ext.getCmp('los_effect_length').getEl().up('.x-form-item').setDisplayed(false);
		}
		
		
   	  function addsaveFn(){
   	                          var mnum1 = Ext.get("los_mnum").dom.value.trim();//输入文本框的内容
				             var name1 = Ext.get("los_name").dom.value.trim();
				             var pclass1=add_pclasscombo.getValue();
		                     var bclass1=add_bclasscombo.getValue();
				             var type1 = Ext.get("los_type").dom.value.trim();
				             var spec1 = Ext.get("los_spec").dom.value.trim();	
				             var remark1 = Ext.get("los_remark").dom.value.trim();
				             var iso_num1 = Ext.get("los_iso_num").dom.value.trim();   
				             var series_num1 = Ext.get("los_series_num").dom.value.trim();                      	
                          	Ext.showProgressDialog("正在提交数据，请稍候...");
                      		modi_panel.form.doAction('submit',
                      		{
								url : '/tsms/web/sms/bscinfmod/cutter/logic_cutter.jsp',
								method:'post',
								params : { type : 'ADD',mnum1:mnum1,
								name1:name1,
								pclass1:pclass1,
								bclass1:bclass1,
								type1:type1,
								spec1:spec1,
								remark1:remark1},
								success : function(form,action){
									if (action.result.msg=='ok'){
											Ext.hideProgressDialog();
											alert( "提示:添加完成");
											 storeMnum.reload();
											resetFn();
										    Ext.getCmp("los_pclass").setValue(daleijs);
		                                    Ext.getCmp("los_bclass").setValue(xiaoleijs);
									}
									else if(action.result.msg=='repeat'){
											Ext.hideProgressDialog();
											alert("提示:该类型零件已存在,不可重复添加!");
									}
									else{
											Ext.hideProgressDialog();
											alert("提示:添加失败");
									}
							 	},
								failure : function(form,action){
									Ext.hideProgressDialog();
									alert( "提示:'服务器出现错误请稍后再试！");
								 }
						
					         });
					   }   	 	           
});