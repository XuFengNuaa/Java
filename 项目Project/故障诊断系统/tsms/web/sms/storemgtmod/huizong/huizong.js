var insertormodify=0;
Ext.onReady(function(){
	  Ext.QuickTips.init();//开启表单提示
	    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
		var filter="";
		var order="";
		var start=0;
		var limit=100000;//每页显示记录条数
   var record_cutter= Ext.data.Record.create([ 
    		{name:'id'},	
			{name:'pclass'},
			{name:'bclass'},			
			{name:'remark'},
			{name:'problem'},
			{name:'reason'},
			{name:'solution'},
			{name:'date'},
			{name:'people'}
			  ]); 
    //定义查询结果页面
	var hpObj={url:'logic_huizong.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_cutter)
    });
    var sm = new Ext.grid.CheckboxSelectionModel();//选择框
    var grid = new Ext.grid.EditorGridPanel({
		        title: false,
		        id:'filetable',
		        loadMask:true,
		        enableColumnMove:false,
		        columns: [sm,
		        	{
			            header: "整机",
			            id:"co_pclass",
			            dataIndex: "pclass",
			            width:60,
			             align:'center',
			             sortable: true
			        }, {
			            header: "部件",
			            id:"co_bclass",
			            dataIndex: "bclass",
			            width:100,
			             align:'center',
			            sortable: true
			            
			        }, {
			            header: "问题",
			            id:"problem",
			            dataIndex: "problem",
			            width:200,	
			             align:'center',
			            sortable: true
			        },{
			            header: "产生原因",
			            id:"reason",
			            dataIndex: "reason",
			            width:200,
			             align:'center',
			            sortable: true
			        }, {
			            header: "解决办法",
			            id:"solution",
			            dataIndex: "solution",
			            width:200,
			             align:'center',
			            align:'center',
			            sortable: true
			        },{
			            header: "责任人",
			            id:"people",
			            dataIndex: "people",
			            width:70,
			             align:'center',
			            sortable: true
			        }, {
			            header: "整改期",
			            id:"date",
			            dataIndex: "date",
			            width:70,
			            renderer:renderFn,
			            align:'center',
			            sortable: true
			        },{
			            header: "备注",
			            id:"co_remark",
			            dataIndex: "remark",  
			            width:200,
			             align:'center',
			            stateId:'filetable',
			            sortable: true
			        }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        autoScroll:true
    });
     function renderFn(value){
    	return value.substring(0,10);
     }
  		//大类、小类、查询条件级联    	
       var  pclass=[[0,'热轧'],[1,'收卷'],[2,'分切'],[3,'其他']];
       var  bclass=new Array();
              bclass[0]=[[0,'上轧辊总成'],[1,'润滑系统'],[2,'冷却轧总成'],[3,'液压系统'],[4,'传动系统'],[5,'机架总成'],[6,'下轧辊总成'],[7,'限位调节装置'],[8,'轴交叉调节装置'],[9,'升降座'],[10,'电、气控制系统']]; 
             bclass[1]=[[11,'机架总成'],[12,'挡块机构'],[13,'下支撑管总成'],[14,'主传动系统'],[15,'张紧轮总成'],[16,'卷取总成'],[17,'气涨轴总成'],[18,'张力机构'],[19,'气缸连接下芯轴'],[20,'纵向分切'],[21,'横向分切'],[22,'刀架'],[23,'压紧总成'],[24,'吸风装置'],[25,'电、气控制系统']];  
              bclass[2]=[[26,'左护罩'],[27,'卷取装置'],[28,'张紧轮总成'],[29,'24Z、48Z齿形轮结合件'],[30,'机架'],[31,'右护罩'],[32,'压辊机构'],[33,'制动装置'],[34,'张力装置'],[35,'放料架'],[36,'轴承座总成'],[37,'分切装置'],[38,'退料车'],[38,'电、气控制系统']]; 
              bclass[3]=[[37,'5'],[38,'6'],[39,'7'],[40,'8']];
       var  querytype=new Array();
              querytype[0]=[['bclass','部件']]; 
              querytype[1]=[['bclass','部件']];
              querytype[5]=[['bclass','部件']];
	          querytype[3]=[['bclass','部件']];
	          querytype[4]=[['bclass','部件']];
	          querytype[5]=[['bclass','部件']];
	          querytype[6]=[['bclass','部件']];
	          querytype[7]=[['bclass','部件']];
	          querytype[8]=[['bclass','部件']];
              querytype[10]=[['bclass','部件']];
              querytype[11]=[['bclass','部件']];
              querytype[12]=[['bclass','部件']];
              querytype[13]=[['bclass','部件']];  
              querytype[14]=[['bclass','部件']];  
              querytype[15]=[['bclass','部件']];  
              querytype[16]=[['bclass','部件']]; 
              querytype[17]=[['bclass','部件']];  
			var store = new Ext.data.SimpleStore({fields:['pclassid','pclass'],data:pclass});
			var pclasscombo=new Ext.form.ComboBox({				
				id:"pclass",
				listeners:{
					        select:function(pclasscombo,record,index){ 
					        bclasscombo.clearValue();
					        bclasscombo.store.loadData(bclass[record.data.pclassid]);
					        }},	
				displayField:'pclass',
				emptyText:'请选择整机',
				store: store,
				triggerAction: 'all',
				lazyRender:true,
				width:110,
				fieldLabel:'整机',
				typeAhead: true,
				selectOnFocus:true,
		    //	allowBlank:false,
				mode:'local',
				editable:false	
				});
				
				
				var bclasscombo=new Ext.form.ComboBox({
				id:"bclass",
				listeners:{
				        select:function(bclasscombo,record,index){ 
				        typecombo.clearValue();
				        typecombo.store.loadData(querytype[record.data.bclassid]);				     
				        ds.removeAll();
				        Ext.getCmp("query_result").reset();
				        Ext.getCmp("query_text").reset();
				       // changeGrid();				        
				        }
				        },	
				displayField:'bclass',
				emptyText:'请选择部件',
				store: new Ext.data.SimpleStore( {
		        fields: ["bclassid",'bclass'],
		        data:[]
		        }),
				triggerAction: 'all',
				fieldLabel:'部件',				
				lazyRender:true,
				width:110,
				typeAhead: true,
				selectOnFocus:true,
			//	allowBlank:false,
				mode:'local',
				editable:false
				});
				
				var typecombo=new Ext.form.ComboBox({
				fieldLabel:'查询类型',
				emptyText:'请选择查询类型',
				id:"type_name",	
				listeners:{
				        select:function(bclasscombo,record,index){ 
				         if(record.data.type=="name"||record.data.type=="mnum"){
				           // Ext.getCmp('hide_length1').setVisible(true);
				          // Ext.getCmp('hide_length2').setVisible(true);
				            Ext.getCmp('query_text').reset();//查询的文本框
				            Ext.getCmp('hide_query_text').setVisible(false);	 				           
				          }else{
				          //  Ext.getCmp('hide_length1').setVisible(false);
				            //Ext.getCmp('hide_length2').setVisible(false);
				            Ext.getCmp('query_length1').reset();
				            Ext.getCmp('query_length2').reset();
				            Ext.getCmp('hide_query_text').setVisible(true);	 	
		
				          }
				       
				        }},	
				displayField:'type_name',	
				valueField:'type',			
				store: new Ext.data.SimpleStore({
		        fields: ["type",'type_name'],
		        data:[]
		        }),
				triggerAction: 'all',
				lazyRender:true,
				width:110,
				typeAhead: true,
				selectOnFocus:true,			
				mode:'local',
				editable:false
				});    
				
		//排序显示
		  var  orderdata=[ ['bclass','部件']];//,['hq','高质']]; 		
		  var  orderstore = new Ext.data.SimpleStore({fields:['orderid','ordername'],data:orderdata});
		  var ordercombo=new Ext.form.ComboBox({				
				id:"order",
				displayField:'ordername',
				valueField:'orderid',
				value:'部件',						
				store: orderstore,
				triggerAction: 'all',
				lazyRender:true,
				width:110,
				fieldLabel:'排序显示',
				typeAhead: true,
			//	allowBlank:false,
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
		  
		  var classpanel = new Ext.Panel({
			  id: 'classpanel',
			  width:800,
			  height:30,
			  hideBorders:true,
			  border:false,
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'column',
			  items:[{
				  columnWidth: 0.28,
				  layout: 'form',
				  border:false,
				  labelAlign:'right',				 
				  items:[pclasscombo]
			  },{
				  columnWidth: 0.28,
				  layout: 'form',
				  border:false,
				  labelWidth:60,
				  labelAlign:'right',
				  items:[bclasscombo]
			  }]
		  });
		  
		  var query_text = new Ext.form.TextField({
			  id:'query_text',
			  width:200,
			  fieldLabel:'',
			  labelSeparator:''
		  });
		  
		  var query_length1 = new Ext.form.TextField({
			  id:'query_length1',
			  width:70,				
			  fieldLabel:'',		
			  labelSeparator:''
		  }); 
		  
		  var query_length2 = new Ext.form.TextField({
			  id:'query_length2',
			  width:70,
			  fieldLabel:'至',			 
			  labelSeparator:''
		  }); 
		 
		  var query_result = new Ext.form.Checkbox({
			  id:'query_result',
			  width:100,
			  name:'query_result',
			  fieldLabel:'',
			  labelWidth:60,	
			  boxLabel:'在结果中查询',		 
			  labelSeparator:''
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
				  columnWidth: .3,//240
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  items:[typecombo]//查询，整机和部件
			  },{
				  columnWidth: .3,
				  id:'hide_query_text',
				  layout: 'fit',
				  border:false,
				  //fieldDefaults:{labelWidth:50},
				  labelWidth:30,
				  labelAlign:'right',				
				  items:[query_text]//类型查询
			  },{
				  columnWidth: .1,//48
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  labelWidth:30,
				  items:[{
					  xtype:'button',					  
					  text:'查询',
					  handler:customQueryFn
				  }]
			  },{
				  columnWidth: .25,
				  layout: 'fit',
				  border:false,
				  labelAlign:'right',
				  labelWidth:30,
				  items:[query_result]
			  }
			  ]
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
			  layout:"form",
			  labelAlign:"right",
			  bodyStyle:'padding:0px 0px 0px 0px',
			  layout:'absolute',
			  labelSeparate:':',
			  labeWidth:50,
			  items:[{
			      x:0,
			      y:0,
				  width:230,
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  labelWidth:3,
				  items:[mode_radio1]
			  },{ x:230,
			      y:0,
				  width: 115,
				  layout: 'fit',
				  labelWidth:3,
				  border:false,
				  labelAlign:'right',
				  items:[mode_radio2]
			  },{ x:345,
			      y:0,
				  width: 150,
				  layout: 'fit',
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
			  layout:'absolute',
			  items:[{
			      x:0,
			      y:0,
				  width: 230,
				  layout: 'form',
				  border:false,
				  labelAlign:'right',
				  items:[ordercombo]
			  },{ x:240,
			      y:0,
				  width: 100,
				  layout: 'fit',
				  border:false,
				  labelAlign:'right',
				  labelWidth:3,
				  items:[orderradio1]
			  },{ x:340,
			      y:0,
				  width: 100,
				  layout: 'fit',
				  labelWidth:3,
				  border:false,
				  labelAlign:'right',
				  items:[orderradio2]
			  }]
		  });
		  
		  
     var panel = new Ext.Panel({
	    id:'panel',
	    height:125,
	    Width:800, 
	    region:"north",
	    hideBorders:true,
	    hideLabels:false,
        renderTo:'querydiv',
        border:false,
        labelAlign:"right",
        bodyStyle:'padding:6px 6px 6px 6px',
        html:"<div id='show_div' style='position:absolute;left:520px;top:65px;'>"+
				  "<span id='show_span' style='font-size:13px;color:#0080ff'>&nbsp;</span></div>",	
        layout:'column',
             items:[{                                  
		           
					width: 1600,
					layout: 'form',				  
					border:false,
					labelAlign:'right',
					items:[classpanel]			 
	              },{ 
	                  
	                width: 1600,
	                labelWidth:50,
	                layout: 'form',
	                border:false,
	                labelAlign:"right",
	                items: [querypanel]	         
	              },{ 
	                 
	                width: 1600,
	                labelWidth:50,
	                layout: 'form',
	                border:false,
	                labelAlign:"right",
	                items: [modepanel]	         
			      },
			      {
				    width: 1600,	             
	                labelWidth:50,
	                layout: 'form',
	                border:false,
	                labelAlign:"right",
	                items: [orderpanel]	         
		          }
		          ]
		
		        
	});	
	 
   var record_com= Ext.data.Record.create([    		
    		{name:'id'},
			{name:'company'}			
			    ]);  
   
	
			
	
	//添加 大类小类级联	 	
	   var  add_pclass=[[0,'热轧'],[1,'收卷'],[2,'分切'],[3,'其他']];                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
       var  add_bclass=new Array();
              add_bclass[0]=[['上轧辊总成'],['润滑系统'],['冷却轧总成'],['液压系统'],['传动系统'],['机架总成'],['下轧辊总成'],['限位调节装置'],['轴交叉调节装置'],['升降座'],['电、气控制系统']];  
              add_bclass[2]=[['左护罩'],['卷取装置'],['张紧轮总成'],['24Z、48Z齿形轮结合件'],['机架'],['右护罩'],['压辊机构'],['制动装置'],['张力装置'],['放料架'],['轴承座总成'],['分切装置'],['退料车'],['电、气控制系统']]; 
              add_bclass[1]=[['机架总成'],['挡块机构'],['下支撑管总成'],['主传动系统'],['张紧轮总成'],['卷取总成'],['气涨轴总成'],['张力机构'],['气缸连接下芯轴'],['纵向分切'],['横向分切'],['刀架'],['压紧总成'],['吸风装置'],['电、气控制系统']];  
              add_bclass[3]=[['5'],['6'],['7'],['8']];
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
		        fields: ['add_bclass'],
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
	
	
	
	var los_pclass=new Ext.form.TextField({ fieldLabel: '整机',id: 'los_pclass',readOnly:true,   width:125 ,style : "background: #C1C1C1;"});
	var los_bclass=new Ext.form.TextField({ fieldLabel: '部件',id: 'los_bclass',readOnly:true,   width:125 ,style : "background: #C1C1C1;"});
	var los_people=new Ext.form.TextField({ fieldLabel: '责任人',id: 'los_people',   width:125});
	var los_date=new Ext.form.DateField( {fieldLabel:"日期",xtype: 'datefield',width:125,id:'los_date',format:'Y-m-d'});
	var los_problem=new Ext.form.TextArea({ fieldLabel: '问题',id: 'los_problem', width:374,height:70});
	var los_reason=new Ext.form.TextArea({ fieldLabel: '产生原因',id: 'los_reason', width:374,height:70});
	var los_solution=new Ext.form.TextArea({ fieldLabel: '解决办法',id: 'los_solution', width:374,height:70});
	var los_remark=new Ext.form.TextArea({ fieldLabel: '备注',id: 'los_remark', width:374,height:70});

	
	
	//左面板
	 var leftpanel = new Ext.Panel({
			  id:'leftpanel',
			  width:320, 
			  height:70,
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
			                items: [los_people]	            
			          },{   columnWidth: .1,
			                bodyStyle:'padding:5px 0px 0',
			                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
			                border:false
		           }]	            
		           },los_pclass]
		            }]
		  });
		 
	//右面板
	 var rightpanel = new Ext.Panel({
			  id:'rightpanel',
			  width:350, 
			  height:70,
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
			                items: [los_date]	            
			          },{   columnWidth: .1,
			                bodyStyle:'padding:5px 0px 0',
			                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
			                border:false
		           }]	            
		           },los_bclass]  }]
		  });
	//remark面板
		  var remarkpanel = new Ext.Panel({
			  id:'remarkpanel',
			  width:670, 
			  height:300,					   
			  hideLabels:false,			
			  labelAlign:"right",
			  bodyStyle:'padding:6px 0px 0px 0px',
			  layout:'column',
			  items:[{
			        columnWidth:1,					              
	                layout: 'form',		                
	                id:'remark_panel',	               
	                labelAlign:"right",		               
	                items: [los_problem, los_reason, los_solution, los_remark]
	                }]
		  });
		  
   //修改、添加弹出面板   
    var modi_panel = new Ext.FormPanel({
        id:'modi_panel',
        labelWidth: 80,        
        width:670 ,
        height:460,	
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
	
    var edit_win = new Ext.Window({
    	id:'edit_win',
    	modal:true,                                         
        width:250,
        height:150,
        closeAction:'hide',
        plain: true,
        buttonAlign:"center",  
    	bodyBorder:true,
    	resizable:false,		
    	items:[new Ext.Panel({
    		   columnWidth:.90,
    		   height:130,
        	   layout:'form',
        	   labelWidth:65,
               border:false,
               frame:true,
               labelAlign:"right",
               bodyStyle:'padding:10px 10px 0',
    		   items:[{	                
                        xtype:'textfield',
                        fieldLabel: '当前数量',				                                    
                        id: 'before_edit',
                        readOnly:true,
                        style : "background: #C1C1C1;"                        
                   },{
                        xtype:'textfield',
                        fieldLabel: '改后数量',				                                    
                        id: 'edit_amount',
                        regex:/^(0|[1-9][0-9]*)$/,
                        regexText:'改后数量必须由整数组成！'	                        				                    
                   }]
        })],
        buttons: [{
                id:'nextbtn',
                text:'确定',		           
                handler:edit_ok_Fn
                },{ id:'cancelbtn1',
    			text: '取消',
    			handler:function(){
               	   edit_win.hide();
                }                    
              }]	                
    });   

    
	var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
    var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});//按钮之间的竖线 
    var toolthree=new Ext.Toolbar.Separator({id:'toolthree'});//按钮之间的竖线    
    var toolfour=new Ext.Toolbar.Separator({id:'toolfour'});//按钮之间的竖线   
    var toolfive=new Ext.Toolbar.Separator({id:'toolfive'});//按钮之间的竖线   
	var toolsix=new Ext.Toolbar.Separator({id:'toolsix'});//按钮之间的竖线 
	var toolseven=new Ext.Toolbar.Separator({id:'toolseven'});//按钮之间的竖线 
	var tooleight=new Ext.Toolbar.Separator({id:'tooleight'});//按钮之间的竖线 
	var toolnine=new Ext.Toolbar.Separator({id:'toolnine'});//按钮之间的竖线 
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
					msg:"将删除所选零件，确认吗？",
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
						   	url: 'logic_huizong.jsp',
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
       	
		//组装       
		var Viewport=new Ext.Viewport({
		   layout:"border",
		   autoHeight:false,
		   items:[panel,
				{
				region:'center',
				layout:'fit',
				title:'查询结果2',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:[toolone,{   
								id:'add',
					            text:"添加", 
					            handler:addFn ,
					            iconCls:"add"
					        },tooltwo,{   
								id:'edit-btn',
					            text:"修改", 
					            handler:editwinFn ,
					            iconCls:"update"
					        },toolthree,{ 
								id:'del',
					            text:"删除", 
					            handler: delFn ,
					            iconCls:"delete"
			        		},toolfour,{ 								
					            id:'output',
					            text:"导出", 
					            handler:outFn,
					            iconCls:"wlk"
			        		},toolnine]
		        }),
		        bbar: new Ext.PagingToolbar({
		        	id:"pagebar",
				    pageSize:limit,
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				}),
				items:grid
		}]
	}); 
	if(isUser()){
	    Ext.getCmp("add").hide();
	    Ext.getCmp("edit-btn").hide();
	    Ext.getCmp("del").hide();
	    Ext.getCmp("output").hide();
	};
		function outFn(){	
			alert("888888888888888888");
		for(var i=1;i<5;i++){
			result = result.replace("&nbsp;&nbsp;&nbsp;","/→  ");
			result = result.replace("<br>",+i+"次结果中查询：");
		}
		if(ds.getCount()==0){
			alert('数据为空，无需导出Excel!');
			return;
		}else{
			hpObj.extraParams.filter=getFilter();
			alert(filter="+encodeURI(hpObj.extraParams.filter)+");
			window.open("huizongxml.jsp?filter="+encodeURI(hpObj.extraParams.filter)+"result="+encodeURI(result));
		}
	}
    //焦点在查询文本框中，使其能够使用特殊符号窗口
	Ext.get("query_text").focus();
	Ext.get("query_text").on("focus", function(){keyBoardInput = Ext.getDom("query_text");});	
	//Ext.get("query_length1").on("focus", function(){keyBoardInput = Ext.getDom("query_length1");});	
	//Ext.get("query_length2").on("focus", function(){keyBoardInput = Ext.getDom("query_length2");});
		
    keyBoard();				
    keyBoard.hide();  
	
	
	 if(isUser()){	   
	     toolone.hide();
	     tooltwo.hide();
	     tooleight.hide();
	     toolseven.hide();
	     
	    Ext.getCmp("add").hide();
	    Ext.getCmp("delFn").hide(); 
	    Ext.getCmp("edit-btn").hide();	  
	    //Ext.getCmp("input").hide();
	  //  Ext.getCmp("combine").hide();
	   }  
	var win_in_panel=new Ext.Panel({//修改，添加弹出窗口
				        id:'win_panel',
                		layout:'column',
                		frame:true,
                		height:480,
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
    			modal:true,                                         
                width:670,
                height:500,
                closeAction:'hide',
                plain: true,
                buttonAlign:"center",
                autoShow: true,
				bodyBorder:true,
				resizable:false,			
				items:[win_in_panel],
                buttons: [{
                        id:'savebtn',
		                text:'保存',
		                type: 'submit',
		                handler:function(){
		                 var xiaoleijs = Ext.getCmp("los_bclass").getValue();
		                 var daleijs = Ext.getCmp("los_pclass").getValue();
		                    if(xiaoleijs == ""){
		                	Ext.MessageBox.alert("提醒","部件不能为空"); }
							if(daleijs == ""){
		                	Ext.MessageBox.alert("提醒","整机不能为空"); }	
								if(insertormodify==0){
									
							       addsaveFn();							 
							      }
							   else{
							   	
								   editsaveFn();
								  }
							  
							  
						   }
	                },{ id:'cancelbtn',
						text: '取消',
						handler:cancelFn
	                },{ id:'resetbtn',
						text: '重置',
						handler:resetFn
	                },{ id:'returnbtn',
						text: '返回',
						handler:returnFn
	                }]
    });  
    
      var add_win = new Ext.Window({
    			id:'add_win',
    			modal:true,                                         
                width:500,
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
				                columnWidth:.5,	
				                labelWidth:45,
				                id:'add_left',				              
				                layout: 'form',
				                bodyStyle:'padding:41px 4px 4px',				            
				                labelAlign:"right",		               
				                items: [add_pclasscombo]
				           },{		                
				                columnWidth:.5,	
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
		                handler:winShowFn
		                },{ id:'cancelbtn1',
						text: '取消',
						handler:add_cancelFn
	                }]	                
    });   
    
    //add_win窗口显示函数   ，添加新零件
     function  winShowFn(){
		                var daleijs=add_pclasscombo.getValue();
		                var xiaoleijs=add_bclasscombo.getValue();
		                  if(daleijs==''){
		                     alert("请选择整机");
		                    }else if(xiaoleijs==''){
		                     alert("请选择部件");
		                    }else if(!win.isVisible()){	
		                    Ext.getCmp("resetbtn").show();			                    
		                   	win.setTitle("<p align='center'>添加新案例</p>");
		                    add_win.hide();
		                    win.show(); 
		                    if(keyBoardWin.isVisible())									    
				              keyBoard.hide();  
		                     Ext.getCmp("resetbtn").show();		
			                 Ext.getCmp("savebtn").show();	
							 Ext.getCmp("cancelbtn").show();	
							 Ext.getCmp("returnbtn").hide();		
		                     modi_panel.form.reset(); 
		                      Ext.getCmp("los_pclass").setValue(daleijs);
		                     Ext.getCmp("los_bclass").setValue(xiaoleijs);
		                     //sharedFn();
						 }
	                }
	    
     function editFn(){
    	 var record =sm.getSelections();//获得页面中选中信息
    	 var edit_class = record[0].json.bclass;
    	 if(edit_class=="上轧辊总成"){
    		 var edit_amount=record[0].json.gc_count_in; 
    		 Ext.getCmp("before_edit").setValue(edit_amount); 
    	 }else{
    		 var edit_amount=record[0].json.uc_count_in; 
    		 Ext.getCmp("before_edit").setValue(edit_amount); 
    	 } 		 		
    	 edit_win.show();  
    	 Ext.getCmp("edit_amount").setValue("");
     }
	      
     function edit_ok_Fn(){
    	 var record =sm.getSelections();//获得页面中选中信息    	 
    	 var edit_id = record[0].json.id;
    	 var edit_class = record[0].json.bclass;
    	 var k;
    	 var before_amount;
    	 if(edit_class=="上轧辊总成"){
    		 k=0;
    		 before_amount=record[0].json.gc_count_in; 
    	 }else{
    		 k=1;
    		 before_amount=record[0].json.uc_count_in; 
    	 }    
    	 var amount = Ext.getCmp("edit_amount").getValue();
    	 var regex = /^(0|[1-9][0-9]*)$/;
    	 if(!regex.test(amount)){
    		 alert("改后数量必须由整数组成！");
    	 }else{
    		 Ext.showProgressDialog("正在提交数据，请稍候...");
				Ext.Ajax.request({
					 	url:'logic_huizong.jsp',
					 	method:'post',
				 		params:{type:'APPLYEDIT',edit_id:edit_id,amount:amount,k:k,before_amount:before_amount},
						success:function(result){
							if (parseInt(result.responseText)==1) {
								Ext.hideProgressDialog();	
								alert('提示:修改成功！');
								edit_win.hide();
								ds.reload();							
							}else{
								Ext.hideProgressDialog();
								alert('服务器出现错误请稍后再试！');															
							}
					 	},
						 //提交失败的回调函数
					 	failure:function(){
					 		Ext.hideProgressDialog();
							alert('提示:服务器出现错误请稍后再试！');
					 	}
				});	
    	 }    	         
     }
    
    //添加弹出窗口函数
     function addFn(){
	   if(!add_win.isVisible()){
	   	
				add_win.setTitle("<p align='center'>添加新案例</p>");
				add_win.show();//添加窗口显示	
				add_win.center(); 	
				/*if(keyBoardWin.isVisible())
				  keyBoardWin.hide();  
				};*/
	   }
		} ;  	 
   	 //添加保存
   	  function addsaveFn(){
		                  var people1 = Ext.get("los_people").dom.value.trim();//输入文本框的内容)
				             var date1 = Ext.get("los_date").dom.value.trim();
				             var pclass1=add_pclasscombo.getValue();
		                     var bclass1=add_bclasscombo.getValue();
				             var problem1 = Ext.get("los_problem").dom.value.trim();
				             var reason1 = Ext.get("los_reason").dom.value.trim();	
				             var remark1 = Ext.get("los_remark").dom.value.trim();
				             var solution1 = Ext.get("los_solution").dom.value.trim();   
                          	Ext.showProgressDialog("正在提交数据，请稍候...");
                      		modi_panel.form.doAction('submit',
                      		{
								url : 'logic_huizong.jsp',
								method:'post',
								params : { type : 'ADD',
								people1:people1,
								date1:date1,
								pclass1:pclass1,
								bclass1:bclass1,
								problem1:problem1,
								reason1:reason1,
								remark1:remark1,
								solution1:solution1
								},
								success : function(form,action){
									if (action.result.msg=='ok'){
											Ext.hideProgressDialog();
											alert( "提示:添加完成");
											modi_panel.form.reset();
										    //Ext.getCmp("los_pclass").setValue(daleijs);
		                                   // Ext.getCmp("los_bclass").setValue(xiaoleijs);
		                                    //Ext.getCmp("los_nc_count_in").setValue(0);		                    
						                    //Ext.getCmp("los_uc_count_in").setValue(0);
						                   // Ext.getCmp("los_use_freq").setValue(0);
		                                    customQueryFn();
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
   	   
   	  //取消函数
   	  function cancelFn(){   	 
	  if(insertormodify==1){insertormodify=0;}
    	win.hide();
    	
        $('#los_rank_angle').keypad('destroy');;
        $('#los_e_diam').keypad('destroy');
        $('#los_h_diam').keypad('destroy');				           
        $('#los_s_ang').keypad('destroy');
        $('#los_t_form').keypad('destroy');
        $('#los_t_type').keypad('destroy');
        $('#los_spec').keypad('destroy');
        $('#los_type').keypad('destroy');	
			 		
       } 
   	   
   	   function returnFn(){
   	      win.hide();
   	   } 
   	        
   	//添加取消函数  
   	 function add_cancelFn(){
   	   add_win.hide();
   	   }
   	     
   	 //查询函数
   	  function customQueryFn(){   
   	  var query_pclass=Ext.getCmp("pclass").getValue();
   	  var query_bclass=Ext.getCmp("bclass").getValue();   	 
   	  var query_type=Ext.getCmp("type_name").getValue();    //查询类型 	  	 
   	  if(query_pclass==""||query_pclass=="请选择整机"){
   	         alert('请选择零件所属整机！');
   	  }else if(query_bclass==""||query_bclass=="请选择部件"){
   	         alert('请选择零件所属部件！');
   	  }else if(query_type==""||query_type=="请选择查询类型"){
   	         alert('请选择查询类型！');
   	  }else{ 
	        	query_store();
	        	
	        }
	   	  
   	       	
	} 
	 
   	  function query_store() {
   	  	
   	  	 var query_type=Ext.getCmp("type_name").getValue();
  		hpObj.extraParams.filter=getFilter();
      	hpObj.extraParams.order=getOrder();    	
      	hpObj.extraParams.type="QUERY";
      	//var pagbar = Ext.getCmp("pagebar");
    	var obj={params:{start:start,limit:limit}};
    	Ext.showProgressDialog();
     	if(ds.load(obj)){
     		//alert(obj);
     	Ext.hideProgressDialog();	
     	}    
     	getQueryText(); 
     	result = getQueryText();
     	//alert(result);
   	  }
   	  
	 
	//getFilter()
	function getFilter(){
	   var i;  
   	  filter="1=1";   	
	  var string = [];   
	  var indexmode = document.getElementsByName("modetype");//检索模式
	  var query_bclass=bclasscombo.getValue();	
	   var query_pclass=pclasscombo.getValue();
	 // var resultquery=document.getElementsByName("query_result");
	  
	  string[1] = typecombo.getValue();
	  string[2] = document.getElementById("query_text").value; 	 
	 // var former_text1=Ext.getCmp("query_length1").getValue(); 
	 // var former_text2=Ext.getCmp("query_length2").getValue();  
	  for(i=0; i<indexmode.length; i++){
			if(indexmode[i].checked == true)
				string[0] = indexmode[i].value;
	 	}
 	// if(resultquery[0].checked == true)
		//filter = this.lastQueryFilter;
	// else{
		//filter = "1=1 ";}
	 string[1] = typecombo.getValue();
	  //console.log(string[1]);
	   var orderValue="";
		var order = document.getElementsByName("ordertype");		
		 orderValue = ordercombo.getValue();
		// alert("orderValue"); 
	   if(orderValue=="部件"){orderValue="bclass";}
	if(string[2]==''){
	    filter += " and bclass = '"+query_bclass+"'and pclass='"+query_pclass+"'";
	}/*else{		
		 if(former_text1!=''&&former_text2!=''){
		    filter += " and " + string[1] + " between " + former_text1 +  " and "+former_text2 +" and bclass='"+query_bclass+"'";	 
		 }else if((former_text1==''&&former_text2!='')||(former_text1!=''&&former_text2=='')){
		    filter += " and " + string[1] + " between 0 and 0 and bclass='"+query_bclass+"'";	 
		 }else if(former_text1==''&&former_text2==''){
		    filter += " and bclass = '"+query_bclass+"'"; 
		 }else{	
			  if(string[1]=='mnum'){
				    if(string[0] == 'front'){
				    filter += " and bclass='"+query_bclass+"'"+" and ( " + string[1] + " like '" + string[2] + "%' "+"or suit_mate2 like '"+string[2]+"%' "+"or suit_mate3 like '"+string[2]+"%'"+")";
				  }else if(string[0] == 'exact'){	    
				    filter +=" and bclass='"+query_bclass+"'"+ " and ( " + string[1] + " = '" + string[2] + "' "+"or suit_mate2 = '"+string[2]+"'"+"or suit_mate3 = '"+string[2]+"'"+")";
				  }else if(string[0] == 'fuzzy'){
				    filter +=" and bclass='"+query_bclass+"'"+ " and ( " + string[1] + " like '%" + string[2] + "%' "+"or suit_mate2 like '%"+string[2]+"%'"+"or suit_mate3 like '%"+string[2]+"%'"+")";
				   }
			  }*/else{ 
			 	  if(string[0] == 'front'){
				    filter += " and " + string[1] + " like '" + string[2] + "%' "+" and bclass='"+query_bclass+"'";
				  }else if(string[0] == 'exact'){
				    filter += " and " + string[1] + " = '" + string[2] + "' "+" and bclass='"+query_bclass+"'";
				  }else if(string[0] == 'fuzzy'){
				    filter += " and " + string[1] + " like '%" + string[2] + "%' "+" and bclass='"+query_bclass+"'";
				   }
			 }
		 
	 
	 this.lastQueryFilter=filter;
	 return filter;
	}
	
	//getOrder()
	function getOrder(){
	   var orderValue="";
		var order = document.getElementsByName("ordertype");		
		 orderValue = ordercombo.getValue();
		  
		   if(orderValue=="部件")
		   	{
		   	orderValue="bclass";
		   	}
		for(var i=0; i<order.length; i++){
			if(order[i].checked == true){
				orderValue +=" "+order[i].value;
				break;
			}
		}
		return orderValue;
	
	}
	
	
	//显示已查询关键字
	 function getQueryText(){	 		
		var string = [];	
		    string[0] = document.getElementById("query_text").value;
		  if(string[0] == ""){
			string[0] = "查询类型";		  		
		}//else{		 
		  //var former_text1=Ext.getCmp("query_length1").getValue(); 
	     // var former_text2=Ext.getCmp("query_length2").getValue();  
		 // if(former_text1==""&&former_text2==""){
		   // string[0] = "所有";
		  //}else{
		   // string[0] = ""+former_text1+""+"mm"+"     至     "+""+former_text2+""+"mm";
		 // }		
		//}		
		if(typecombo.getRawValue()!="请选择查询类型"){
		    string[1] = typecombo.getRawValue();
		}
		var spantext = "";
		 var bclass = Ext.getCmp("bclass").getValue();
	  var pclass = Ext.getCmp("pclass").getValue();
		var resultquery = document.getElementsByName("query_result"); //在结果中查询
		
		if(resultquery[0].checked == true)
			spantext = this.lastQueryText + "<br>";
			
		spantext += string[0] + "：" + string[1] +"<br>"+ "&nbsp;&nbsp;&nbsp;"+"整机 ："+pclass+ "&nbsp;&nbsp;&nbsp;"+"部件 ："+bclass;
		
		
		this.lastQueryText = spantext;
		document.getElementById("show_span").innerHTML = "已查询的关键词：<p>" + spantext;
		return spantext;

	}
		
	
	 //修改页面显示
	function editwinFn(){	 
		var record =sm.getSelections();// 返回值为 Record 类型            	
		if(record.length==0){
            alert('请先选择要修改的案例！');
           }
        else if(record.length!=1){
        	alert('每次只能修改一条案例信息!');
           }
        else{ 
           if(!win.isVisible()){
                win.setTitle("<p align='center'>案例信息修改</p>");
				win.show();//修改窗口显示
				Ext.getCmp("resetbtn").show();
				Ext.getCmp("savebtn").show();
				Ext.getCmp("cancelbtn").show();
				Ext.getCmp("returnbtn").show();
				insertormodify = insertormodify+1;
				if(keyBoardWin.isVisible())
				  keyBoardWin.hide();  
                 Ext.getCmp("los_people").getEl().dom.readOnly  = false;
				Ext.getCmp("los_pclass").getEl().dom.readOnly  = false;
				Ext.getCmp("los_bclass").getEl().dom.readOnly  = false;
				Ext.getCmp("los_date").getEl().dom.readOnly  = false;
				Ext.getCmp("los_remark").getEl().dom.readOnly  = false;
				Ext.getCmp("los_problem").getEl().dom.readOnly  = false;
				Ext.getCmp("los_reason").getEl().dom.readOnly  = false;
				Ext.getCmp("los_solution").getEl().dom.readOnly  = false			    
				initializeFn();	
				win.center();									
			 	  }	  
			}      
    }
    
   //初始化赋值
	function initializeFn(){
		var record =sm.getSelections();//获得页面中选中信息
		var editid=record[0].json.id;
		//初始化设置
		var load=Ext.Ajax.request({
		 	url:'logic_huizong.jsp',
		 	method:'post',
	 		params:{type:'VIEW',id:editid},//传递id参数
			success:successFn ,
	 		failure:function(){
				alert('服务器出现错误请稍后再试！');
 			}
	  	});	
	}   
   //初始化成功回调函数
	 function renderFn1(value){
    	return value.substring(0,10);
     }
	function successFn(result,request){
		
		var objtp=Ext.util.JSON.decode(result.responseText);	
		var new_date=renderFn1(objtp.date);
		win.findById("los_people").setValue(objtp.people);
		win.findById("los_pclass").setValue(objtp.pclass);
		win.findById("los_bclass").setValue(objtp.bclass);
		win.findById("los_date").setValue(new_date);
		win.findById("los_problem").setValue(objtp.problem);
		win.findById("los_remark").setValue(objtp.remark);
		win.findById("los_reason").setValue(objtp.reason);
		win.findById("los_solution").setValue(objtp.solution);
		//var local=objtp.location.split("-");
			}  
			   
	//用户修改函数.保存
	function editsaveFn(){	
	           	Ext.showProgressDialog("正在提交修改数据，请稍候...");
	           	var record =sm.getSelections();
	           	var editid=record[0].json.id;
				modi_panel.form.doAction('submit',
				{
					 	url:'logic_huizong.jsp',
					 	method:'post',
				 		params:{type:'EDIT',id:editid
				 		       },
				 		success : function(form,action){
								if (action.result.msg=='ok'){
										Ext.hideProgressDialog();
										win.hide();
										ds.reload();
										//customQueryFn();
										alert('已完成对零件信息的修改！');
										insertormodify=0;
										
								}
								else if(action.result.msg=='repeat'){
										Ext.hideProgressDialog();
										alert("该类型零件已存在,重复!");
								}
								else{
										Ext.hideProgressDialog();
										alert("修改失败！");
								}
						 	},
						failure : function(form,action){
								Ext.hideProgressDialog();
								alert( "提示:'服务器出现错误请稍后再试！");
					}});
		}  
		
	function anothersaveFn(){	  
		
	            var com_id=ComboBoxCom.getValue();	                     
	            var kunum=kucombo.getValue();
	            var cennum=cencombo.getValue();
	            var cen1num=cen1combo.getValue();
	            var weinum=weicombo.getValue();
	            var location=kunum+'-'+cennum+'-'+cen1num+'-'+weinum;
	           	Ext.showProgressDialog("正在提交修改数据，请稍候...");
	           	var record =sm.getSelections();
	           	var editid=record[0].json.id;	           	
	           	var los_pclass=Ext.getCmp("los_pclass").getValue();
	           	var los_bclass=Ext.getCmp("los_bclass").getValue();
	           	var los_name=Ext.getCmp("los_name").getValue();
	           	var los_gc_count_in=Ext.getCmp("los_gc_count_in").getValue();
	           	var los_suit_mate1=Ext.getCmp("los_suit_mate1").getValue();
	           	var los_suit_mate2=Ext.getCmp("los_suit_mate2").getValue();
	           	var los_suit_mate3=Ext.getCmp("los_suit_mate3").getValue();
	           	var los_rank_angle=Ext.getCmp("los_rank_angle").getValue();
	           	var los_c_mate=Ext.getCmp("los_c_mate").getValue();
	           	var los_coat_mate=Ext.getCmp("los_coat_mate").getValue();
	           	var los_e_diam=Ext.getCmp("los_e_diam").getValue();
	           	var los_h_diam=Ext.getCmp("los_h_diam").getValue();	           	
	           	var los_e_leng=Ext.getCmp("los_e_leng").getValue();
	           	var los_t_leng=Ext.getCmp("los_t_leng").getValue();
	           	var los_e_count=Ext.getCmp("los_e_count").getValue();
	           	var los_remark=Ext.getCmp("los_remark").getValue();
	           	var hq=Ext.getCmp("hq").getValue();           	
	           	var los_effect_length=Ext.getCmp("los_effect_length").getValue();
	           	
	        	Ext.Ajax.request({
					url:'logic_huizong.jsp',
					params : {
						type : 'GRINDEDIT',
						los_pclass : los_pclass,
						los_bclass : los_bclass,
						los_name : los_name,
						los_gc_count_in : los_gc_count_in,
						los_suit_mate1: los_suit_mate1,
						los_suit_mate2 : los_suit_mate2,
						los_suit_mate3 : los_suit_mate3,
						los_rank_angle : los_rank_angle,
						los_c_mate : los_c_mate,
						los_coat_mate : los_coat_mate,
						los_e_diam : los_e_diam,
						los_h_diam : los_h_diam,
						los_e_leng: los_e_leng,						
						los_t_leng : los_t_leng,
						los_e_count: los_e_count,
						los_remark : los_remark,
						hq : hq,
						location :location,
						los_effect_length : los_effect_length,
						com_id:com_id,
						id:editid						
					},
					success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)==1) {
						    win.hide();										
							customQueryFn();							
							alert( "已完成对零件信息的修改！");	
							insertormodify=0;						
						}else{
						   alert("修改失败，请稍后再试！");	 
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert("提示:'服务器出现错误请稍后再试！");						
					}
	
				});	
	 }	

	 
  });