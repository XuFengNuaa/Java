Ext.onReady(function(){
	if(isUser()){filter='denglu = '+"'"+userid+"'"}
else{var filter='1=1';}
	var start=0;
	var limit=10000;//每页显示记录条数

    var record_cut= Ext.data.Record.create([    		
    		{name:'type_id'},
    		{name:'in_time'},//newstatus,indi_num,location,keeper,remark,startdate,valid,cycle,entry_num,indi_id,status
			{name:'newstatus'},
			{name:'in_count'},
			{name:'entry_num'},
			{name:'mnum'},
			{name:'order_num'},
			{name:'name'},
			{name:'company'},
			{name:'pclass'},
			{name:'bclass'},
			{name:'status'},
			{name:'remark'},
			{name:'user_num'},
			{name:'bill_num'},
			{name:'city'}
    ]); 
    
    //调用数据库信息
	var hqObj={url:'pa_c_logic.jsp',method: 'POST',extraParams:{type:'QUERYCT',params:{start:start,limit:limit},filter:filter}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hqObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_cut),
        autoload:true
    }); 
    var obj={params:{start:start,limit:limit}};   
    ds.load(obj);
    var hqObjAni={url:'pa_c_logic.jsp',method:'POST',extraParams:{}};
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
			            header: "状态",
			            dataIndex: "status",
			            width:80,
			            align:'center',
			            sortable: true
			        }, 
		        		{ 
			            header: "整机",
			            dataIndex: "pclass",
			            width:80,
			            align:'center',
			            sortable: true
			        }, {
			            header: "部件",
			            dataIndex: "bclass",
			            width:95,
			            align:'center',
			            sortable: true
			        }, {
			            header: "联系人",
			            dataIndex: "user_num",
			            width:100,
			            align:'center',
			            sortable: true
		             },{
			            header: "所在城市",
			            dataIndex: "city",
			            width:100,
			            align:'center',
			            sortable: true
		             },{
			            header: "公司名称",
			            dataIndex: "company",
			            width:110,
			            align:'center',
			            sortable: true
			        }, {
			            header: "联系电话",
			            dataIndex: "in_count",
			            width:70,
			            align:'center',
			            sortable: true
			        }, {
			            header: "订单号",
			            dataIndex: "bill_num",
			            width:100,
			            align:'center',
			            sortable: true
		             }, {
			            header: "申请时间",
			            dataIndex: "in_time",
			            width:85,
			            align:'center',
			            renderer:renderFn,
			            sortable: true    
			        }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        autoScroll:true,
		        stateId:'filetable',
		        renderTo: document.body
    });
    new Ext.Viewport({
		layout:"border",
		autoHeight:false,
		items:[
				{
				region:'center',
				layout:'fit',
				title:"<p align='center'>在线答疑审批</p>",
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:[/*'-',
							{ 								
					            id:'app',
					            text:"写入数据库", 
					            handler:appFn,
					            iconCls:"update"
					        },*/'-',{								
					            id:'refuse',
					            text:"删除", 
					            handler:refFn,
					            iconCls:"remove"
					        },'-',{								
					            id:'output',
					            text:"查看", 
					            handler:look,//outFn,
					            iconCls:"wlk"
			        		},'-',{								
					            id:'evaluate',
					            text:"用户评价", 
					            handler:winShowFn,//outFn,
					            iconCls:"detail"
			        		},'-']
		        }),
		        bbar: new Ext.PagingToolbar({
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
	    Ext.getCmp("refuse").hide();
	};
	function winShowFn(){
	 var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');}
			 var form_title = new Ext.Panel({     
		    	  bodyStyle:'padding:0px 50px 10px', 
		    	  layout:'table',
		    	  layoutConfig: {columns: 2},
		    	  items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),       
		    	  new Ext.Panel({html:"<p align='center'></p>",width:'50',style: "margin:10,0,0,0"})
		    	    	]
		    	    });
    	    var form_row1 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	    	width:'400',
    	        layoutConfig: {columns:3},
    	        
    	    	items:[new Ext.Panel({html:"<p align='right'>答疑速度&nbsp;&nbsp;</p> ",width:'100',style: "margin:0,0,0,0" }),
    	    	       new Ext.Panel({html:"<div id='star' ></div>",width:'150' }),
    	    	      new Ext.Panel({html:"<div id='coment'>请打分</div>",width:'100'})
    	
    	    	]});	
    		var form_row2 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	    	width:'400',
    	        layoutConfig: {columns: 3},
    	    	items:[new Ext.Panel({html:"<p align='right'>答疑效果&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	              new Ext.Panel({html:"<div id='star1' ></div>",width:'150' }),
    	    	      new Ext.Panel({html:"<div id='coment1'>请打分</div>",width:'100'})
    	    	]
    	    });	
    	  var form_row3  = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	    	width:'400',
    	        layoutConfig: {columns: 3},
    	    	items:[new Ext.Panel({html:"<p align='right'>服务态度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               new Ext.Panel({html:"<div id='star2' ></div>",width:'150' }),
    	    	      new Ext.Panel({html:"<div id='coment2'>请打分</div>",width:'100'})
    	    	]
    	    });  	    
    		  var form_row4 = new Ext.Panel({     
    		    	bodyStyle:'padding:5px 50px 0', 
    		    	//labelSeparator: ' ',
    		    	layout:'table',
    		    	width:'400',
    		        layoutConfig: {columns: 3},
    		    	items:[new Ext.Panel({html:"<p align='right'>产品质量&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    		               new Ext.Panel({html:"<div id='star3' ></div>",width:'150' }),
    	    	      new Ext.Panel({html:"<div id='coment3'>请打分</div>",width:'100'})
    		    	]
    		    });
    		var remarkjs=new Ext.form.TextArea({preventScrollbars:true,fieldLabel: '留言',labelSeparator:':',id: 'remarkjs',value:'请您留下宝贵的意见和建议。',width:374,height:200});
    		 
    		 var assessform = new Ext.FormPanel({
    				bodyStyle: 'padding:10px 10px 0px',
    				region:'center',
    				//width:600,
    				//height:10,
    				frame:true,
    				layout:'form',
    				layoutConfig: {column:1 },
    				//style:'margin:0% 20%',
    				buttonAlign:"center",
    				title:"<p align='center'>专家满意度评分</p>",
    				items:[form_title,form_row1,form_row2,form_row3,form_row4,remarkjs],
    				buttons:[{
    			       	text: '保存',
    				  	id:'save',					
    				  	handler:function(){
    				  		var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
			var entry_nums="";
    	var length = record.length;
		Ext.showProgressDialog();
		for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
			if(i!=0){
			entry_nums+=",";
				};
			entry_nums+="'"+record[i].json.entry_num+"'";
		}
			}
    				  		var scor=(sta+sta1+sta2+sta3)/4;
    				  		//alert(a41+" "+a42+" "+a43+" "+a44);
    				  		//保存打分信息
    				  		var remarkjs=Ext.get("remarkjs").dom.value;
    				  		Ext.showProgressDialog();
							Ext.Ajax.request({
								url:'pa_c_logic.jsp',
								//method:'post',
								params:{                                                                                                                                                                     
									type:'ADDMARK',
									entry_nums:entry_nums,
									score:scor,
									sta:sta,
									sta1:sta1,
									sta2:sta2,
									sta3:sta3,
									remarkjs:remarkjs
								},
								success:function(resp){
									Ext.hideProgressDialog();
									if(parseInt(resp.responseText)==1){
										Ext.MessageBox.alert('提示',"保存成功");
									}else{
										Ext.MessageBox.alert('提示',"保存失败");
									}
								},
								failure:function(){
									Ext.hideProgressDialog();
									Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
								}
							});
		    				  	}
		    				  },{
		    				   	text: '重置',
		    				   	id:'reset',
		    				   	handler:function(){
		    						assessform.getForm().reset();								     								
		    						//Ext.getCmp("diameter").focus();
		    				   }		   
		    				}]
		    			});
    			var pingfen = new Ext.Window({
		           modal:true,
	                width:600,
	                height:520,
	                closable:true,
			layout:"border",
			title:"<p align='center'>评价</p>",    
					bodyBorder:true,
			items:assessform
	}
		);
		pingfen.show();
		var sta=0;
		var sta1=0;
		var sta2=0;
		var sta3=0;
		 $('#star').raty({path:'lib/img',  size:24,
			 mouseover: function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				 $('#coment').text(score+'分');	
		  } ,
		  click:function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				sta=score;	
		  } 
		 });
		 
		  $('#star1').raty({path:'lib/img',  size:24,
			 mouseover: function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				 $('#coment1').text(score+'分');	 
		  },	 
		   click:function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				sta1=score;	
		  } 
		 });
		  $('#star2').raty({path:'lib/img',  size:24,
			 mouseover: function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);	
				 $('#coment2').text(score+'分'); 		 
		  }, 
		   click:function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				sta2=score;	
		  } 
		 });
		  $('#star3').raty({path:'lib/img',  size:24,
			 mouseover: function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				 $('#coment3').text(score+'分');	
		  }, 
		   click:function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				sta3=score;	
		  } 
		 });	
				
	};
	 function detailsFn(obj){
         var objtp = obj;
		nowid = objtp.id;
		 Ext.getCmp("in_problem").setValue(objtp.in_problem);
         Ext.getCmp("answer").setValue(objtp.answer);
    };
	function successFn(result,request){
     var objtp = Ext.util.JSON.decode(result.responseText);
		var objt =	Ext.util.JSON.encode(objtp.filedata);
		var obj = objt.slice(1,objt.length-1);
		var pos = obj.indexOf('{');
		var i = 0;
		while (pos!=-1){
		    pos = obj.indexOf('{',pos+1);
		    i++;
		}
		if (i>1) {//物资编码重复
		   var hq = (Ext.util.JSON.decode(obj)).hq;
		   hpCombo.reset();
		   Ext.MessageBox.alert('提醒!物资编号不唯一',"你选择一种物资编号不唯一的工具，请您点击确定选择工具是否为高质！");
		}else{//物资编码不重复

		var obj = Ext.util.JSON.decode(obj);
        detailsFn(obj);}};
function chexExistnew(){
    var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
			var entry_nums="";
    	var length = record.length;
		//Ext.showProgressDialog();
		for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
			if(i!=0){
			entry_nums+=",";
				};
			entry_nums+="'"+record[i].json.entry_num+"'";
		}
		    //var mnum = record[0].get("mnum");
	    var filter = entry_nums;
	    Ext.Ajax.request({
							 	url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
							 	method:'post',
						 		params:{type:'QUERY3',id:filter},
								success:successFn,
						 		failure:function(){
									Ext.MessageBox.alert('警告','服务器出现错误请稍后再试！');
					 			}
						  	});		
						
					
			}
    } 
function viewImgFn(){   
			 //显示图片panel
			var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
		    var mnum = record[0].get("mnum");
		    var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
				var entry_nums="";
    	var length = record.length;
		for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								entry_nums+=",";
							}
							entry_nums+="'"+record[i].json.entry_num+"'";
					}}
					
	    	var imgviewpanel=new Ext.FormPanel({
				id:'imgview',
			    labelWidth:75,
			    width:1000,
			    height:700,			      				       
			    frame:true,
			    bodyStyle:'padding:10px 10px 0',	//logicm_re_check.jsp?type=viewimg&tuhao_sim='+tuhao_sim+'"
			    html:'',
			    items: [{
			    buttons: [{
			     			id:'backbtn',
							text: '返回',
							handler: function(){
							 Ext.getCmp("imgview").hide();
							}
			            }
			       	]
			    }]
			   	});
			   	var imgwindow = new Ext.Window({
	    			id:'imgwindow',
	    			modal:true,
	                width:1000,
	                height:700,
	                closable:true,
	             //   closeAction:'hide',
	                plain: true,
	                title:"<p align='center'>查看案例详情</p>",    
					bodyBorder:true,
	                items:imgviewpanel      
			    });
			   	imgwindow.show();
			   	Ext.Ajax.request({
					url : '/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
					params : {
						type : 'ADD',
						mnum:entry_nums
					},
					success : function(result) {
							Ext.getCmp("imgview").body.update(result.responseText);
							
					},
					failure : function(result){
						alert( "提示:读取失败！");

					}
				}); 
			   	
			}
			};
			
var in_problem=new Ext.form.TextArea({fieldLabel: '问题描述',id: 'in_problem', readOnly:false,width:364,height:180});
	var answer=new Ext.form.TextArea({fieldLabel: '解决方案',id: 'answer', readOnly:false,width:364,height:180});	
function imgFn(){
var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
				var entry_nums="";
    	var length = record.length;
		for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								entry_nums+=",";
							}
							entry_nums+="'"+record[i].json.entry_num+"'";
					}
		    var mnum = record[0].get("mnum");
	    	var imgviewpanel2=new Ext.FormPanel({
				id:'imgview2',
			    labelWidth:75,
			    width:1000,
			    height:700,			      				       
			    frame:true,
			    bodyStyle:'padding:10px 10px 0',	//logicm_re_check.jsp?type=viewimg&tuhao_sim='+tuhao_sim+'"
			    html:'',
			    items: [{
			    buttons: [{
			     			id:'backbtn',
							text: '返回',
							handler: function(){
							 Ext.getCmp("imgview2").hide();
							}
			            }
			       	]
			    }]
			   	});
			   	var imgwindow2 = new Ext.Window({
	    			id:'imgwindow',
	    			modal:true,
	                width:1000,
	                height:700,
	                closable:true,
	             //   closeAction:'hide',
	                plain: true,
	                title:"<p align='center'>查看回复详情</p>",    
					bodyBorder:true,
	                items:imgviewpanel2      
			    });
			   	imgwindow2.show();
			   	Ext.Ajax.request({
					url : '/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
					params : {
						type : 'ADD1',
						mnum:entry_nums
					},
					success : function(result) {
							Ext.getCmp("imgview2").body.update(result.responseText);
							
					},
					failure : function(result){
						alert( "提示:读取失败！");

					}
				}); 
			   	
			}
};
function look(){
var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
		    //var mnum = record[0].get("mnum");
		   
		    chexExistnew();
	    	var imgviewpanelck=new Ext.FormPanel({
				id:'imgviewck',
			    labelWidth:75,
			    width:580,
			    height:480,			      				       
			    frame:true,
			    bodyStyle:'padding:10px 10px 0',
			    items: [ {xtype:"panel",
        		    layout:"column",
					items:[{columnWidth:1,
					        //rowspan:2,
						    layout:"form",
						    //hidden: true,
		                	items:[in_problem]//零件编码
				           }]
				},{xtype:"panel",
        		    layout:"column",
					items:[{columnWidth:1,
					        //rowspan:2,
						    layout:"form",
						    //hidden: true,
		                	items:[answer]//零件编码
				           }]
				}]
			   	});
			   	var imgwindowck = new Ext.Window({
	    			id:'imgwindowck',
	    			modal:true,
	                width:600,
	                height:500,
	                closable:true,
	             //   closeAction:'hide',
	                plain: true,
	                title:"<p align='center'>查看案例详情</p>",    
					bodyBorder:true,
	                items:imgviewpanelck,
	                buttonAlign:"center",
	                 buttons: [{
		                text:'故障附件',
		                id:'download',
		                handler: viewImgFn
		            },{
		                text:'回复附件',
		                handler:imgFn
					  },{
						text: '返回',
						handler: function(){
						 Ext.getCmp("imgwindowck").hide();
						
						}
		            }
		       	]
			    });
			     imgwindowck.show();
			   	}
          
          
};
	//入库函数
	function appFn(){
		// alert('空.....空......空');
		var result = 0;
		var approve = 0;
		var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
            Ext.MessageBox.alert('提示','请先选择要写入数据库的零件信息!');
            return;
    	}
    	if (record.length!=0) {
           	Ext.MessageBox.confirm('提示','选中信息将被填写进数据库，确认吗？',function(btn) {   //'提示','选中信息将被填写进数据库，确认吗？'
				if(btn == 'yes') {
                   	Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){
					if (record[i].get("status") == '已批'){
					    var entry_num = record[i].get("entry_num");
						var type_id = record[i].get("type_id");
						var in_time = renderFn(record[i].get("in_time"));
						var newstatus = record[i].get("newstatus");
						var in_count = record[i].get("in_count");
						var status = '已写入数据库';   //在库
						Ext.showProgressDialog();
						Ext.Ajax.request({
									url:'pa_c_logic.jsp',
									params : {
										type : 'APPTOOLALL',
										entry_num : entry_num,
										type_id : type_id,
										in_time : in_time,
										newstatus : newstatus,
										in_count : in_count,
										status : status
										},
									success : function(resp) {
										Ext.hideProgressDialog();
										if (parseInt(resp.responseText)==1){
										ds.reload();
										}else{
										alert("案例添加失败，请检查数据再次尝试，如多次失败请联系管理员！");
										//break;
										}
									},
									failure : function(){
										Ext.hideProgressDialog();
										alert("案例添加失败，数据传送中出现问题！");
										//break;
									}
								});
								}
							else if (record[i].get("status") == '申请')	{
							alert(record[i].get("name")+"  信息未批准，不能写入数据库！");
						    Ext.hideProgressDialog();
							}
							else if (record[i].get("status") == '拒绝') {
							alert(record[i].get("name")+"  信息已被拒绝，不能写入数据库！");
						    Ext.hideProgressDialog();
							}
					}
				}
				else 
					Ext.MessageBox.alert('提示','您取消当前的写入数据库操作！');
			});
		}
	}

	//删除函数
	function refFn(){
		var record =sm.getSelections();// 返回值为 Record 类型	         
		if(record.length==0){
            alert('提示：请先选择至少一种被领导拒绝写入数据库的零件!');
            return;
    	}
		if(record.length!=0) {
			Ext.MessageBox.confirm('提示','选中信息将被删除，确认吗？',function(btn) {   //'提示','选中信息将被填写进数据库，确认吗？'
				if(btn == 'yes') {
                   	Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){
					    if(record[i].get("status")=="拒绝"||record[i].get("status")=="已回复"){
						var deleteid = '';
						deleteid = record[i].get("entry_num") ;   //在库
						Ext.showProgressDialog();
						Ext.Ajax.request({
									url:'pa_c_logic.jsp',
									params : {
										type : 'DELETE',
										deleteid : deleteid
										},
									success : function(resp) {
										Ext.hideProgressDialog();
										if (parseInt(resp.responseText)==1){
										ds.reload();
										}else{
										alert("信息删除失败，请检查数据再次尝试，如多次失败请联系管理员！");
										//break;
										}
									},
									failure : function(){
										Ext.hideProgressDialog();
										alert("信息删除失败，数据传送中出现问题！");
										//break;
									}
								});	
						}
						else {
						alert(record[i].get("name")+"信息未被拒绝，不能删除！");
						Ext.hideProgressDialog()
						}
					}
				}
				else 
					Ext.MessageBox.alert('提示','您取消当前的入库操作！');
			});
		}
	}
	//明细表，明细表，明细表
	var details=new Ext.FormPanel({
    	id:'details',
        labelWidth:80, 	
        height:360,			      				       
        frame:true,
        bodyStyle:'padding:10px 0px 0 0',	
        items: [{
	            layout:'column',
	            items:[{
	                columnWidth:.5,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
		                    xtype:'textfield',
		                    fieldLabel: '零件编码',
		                    readOnly: true,
		                    name: 'tmnum',
		                    width:100,
		                    id: 'tmnum'
			                },{
		                    xtype:'textfield',
		                    fieldLabel: '名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称',
		                    readOnly: true,
		                    name: 'tname',
		                    width:100,
		                    id: 'tname'
			                },{
		                    xtype:'textfield',
		                    fieldLabel: '大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类',
		                    readOnly: true,
		                    name: 'tpclass',
		                    width:100,
		                    id: 'tpclass'
			                },{
		                    xtype:'textfield',
		                    fieldLabel: '小&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类',
		                    readOnly: true,
		                    name: 'tbclass',
		                    width:100,
		                    id: 'tbclass'
			                }
			        ]},
			        {columnWidth:.5,
	                layout: 'form',
	                labelWidth:100,
	                labelAlign:"right",
	                items: [{
		                    xtype:'textfield',
		                    fieldLabel: '订货号',
		                    readOnly: true,
		                    name: 'torder_num',
		                    width:100,
		                    id: 'torder_num'
			                },{
		                    xtype:'textfield',
		                    fieldLabel: '申请日期',
		                    readOnly: true,
		                    width:100,
		                    //renderer:renderFn,
		                    name: 'tin_time',
		                    id: 'tin_time'
	                		},{
		                    xtype:'textfield',
		                    fieldLabel: '申请状态',
		                    readOnly: true,
		                    width:100,
		                    name: 'tstatus',
		                    id: 'tstatus'
	                		},{
		                    xtype:'textfield',
		                    fieldLabel: '厂&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商',
		                    readOnly: true,
		                    width:100,
		                    name: 'tcom',
		                    id: 'tcom'
	                		}
	                ]},{
	                columnWidth:1,					              
	                layout: 'form',
	                labelAlign:"right",
	                items: [{
							xtype : 'textarea',
							id : 'tremark',
							fieldLabel : '备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
							readOnly: true,
							width:325,
							height: 180
					}]
			}]
            
        }],
      
         buttonAlign:"center",
         buttons: [{
	                text:'确定',
	                id:'okok',
	                handler:function(){
	                   Ext.getCmp("win").hide();
					   Ext.getCmp("details").form.reset();    
						}
                }]
	});
	
	//截断时间函数
     function renderFn(value){
    	return value.substring(0,10);
     }
    
	//刀具信息弹出窗口
	 var win = new Ext.Window({
	    			id:'win',
	    			modal:true,
	                width:480,
	                height:400,
	                closable:false,
	                plain: true,
	                title:"<p align='center'>案例详细信息</p>",    
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
        }else{
	    	   if(!Ext.getCmp("win").isVisible()){
						Ext.getCmp("tmnum").setValue(record[0].get("mnum"));
						Ext.getCmp("torder_num").setValue(record[0].get("order_num"));
						Ext.getCmp("tname").setValue(record[0].get("name"));
						Ext.getCmp("tpclass").setValue(record[0].get("pclass"));
						Ext.getCmp("tbclass").setValue(record[0].get("bclass"));
						Ext.getCmp("tnewstatus").setValue(record[0].get("newstatus"));
						//Ext.getCmp("thq").setValue(record[0].get("hq"));
						//Ext.getCmp("tcom").setValue(record[0].get("company"));
						Ext.getCmp("tin_time").setValue(renderFn(record[0].get("in_time")));
						Ext.getCmp("tstatus").setValue(record[0].get("status"));
						//Ext.getCmp("tin_count").setValue(record[0].get("in_count"));
						//Ext.getCmp("tnct").setValue(record[0].get("nc_count_in"));
						//Ext.getCmp("tuct").setValue(record[0].get("uc_count_in"));
						//Ext.getCmp("tlocation").setValue(record[0].get("location"));
						Ext.getCmp("tremark").setValue(record[0].get("remark"));
			            Ext.getCmp("okok").show();
						Ext.getCmp("win").show();//修改窗口显示
						win.center();
						}
    	     }
	}
	
	//导出Excel表
	function outFn(){
		var url = 'http://localhost:8080/tsms/web/sms/storemgtmod/nmg_tool/c_trecord_out.jsp';
    	location.href='c_trecord_out.jsp?url='+url;
	}
	
	
});	




