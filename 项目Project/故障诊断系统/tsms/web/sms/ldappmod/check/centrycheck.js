Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter='1=1';
	var start=0;
	var limit=100000000;//每页显示记录条数

    var record_cut= Ext.data.Record.create([    		
    		{name:'entry_num'},
    		{name:'mnum'},
			{name:'id'},
			{name:'in_count'},
			{name:'in_time'},
			{name:'pclass'},
			{name:'bclass'},
			{name:'company'},
			{name:'newstatus'},
			{name:'name'},
			{name:'city'},
			{name:'user_num'},
			{name:'bill_num'}
    ]); 
    
    //调用数据库信息
	var hqObj={url:'logic_checkmod.jsp',method: 'POST',extraParams:{type:'QUERYC',params:{start:start,limit:limit},filter:filter}};
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
		        enableColumnMove:false,
		        columns: [sm,
		        	{ 
			            header: "整机",
			            dataIndex: "pclass",
			            width:80,
			            sortable: true
			        }, {
			            header: "部件",
			            dataIndex: "bclass",
			            width:95,
			            sortable: true
			        }, {
			            header: "联系人",
			            dataIndex: "user_num",
			            width:100,
			            sortable: true
		             },{
			            header: "所在城市",
			            dataIndex: "city",
			            width:100,
			            sortable: true
		             },{
			            header: "公司名称",
			            dataIndex: "company",
			            width:110,
			            sortable: true
			        }, {
			            header: "联系电话",
			            dataIndex: "in_count",
			            width:70,
			            sortable: true
			        }, {
			            header: "订单号",
			            dataIndex: "bill_num",
			            width:100,
			            sortable: true
		             }, {
			            header: "申请时间",
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
    
     function addFn(){};
     function detailsFn(obj){
         var objtp = obj;
		nowid = objtp.id;
		 Ext.getCmp("in_problem").setValue(objtp.in_problem);			

    }
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
		//Combonum.reset();
		//Combocom.reset();
		//hpCombo.reset();
		//Ext.getCmp('div').hide();
		//Ext.getCmp('div1').hide();
		//Ext.getCmp('div2').hide();
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
		    var mnum = record[0].get("mnum");
	    var filter = entry_nums;
	    Ext.Ajax.request({
					url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
							 	method:'post',
						 		params:{type:'QUERY2',id:filter},
					success:successFn,
					failure:function(){
									Ext.MessageBox.alert('警告','服务器出现错误请稍后再试！');
					}
					
				}); 
			}
    } 
    //组装界面
    new Ext.Viewport({
		layout:"border",
		autoHeight:false,
		items:[
				{
				region:'center',
				layout:'fit',
				title:"<p align='center'>专家答疑</p>",
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
					            text:"提交", 
					            handler:appFn,
					            iconCls:"update"
				            },'-',{								
					            id:'refuse',
					            text:"拒绝", 
					            handler:refFn,
					            iconCls:"remove"
					        },'-',{ 																
					            id:'reply',
					            text:"回复", 
					            handler:replyFn,
					            iconCls:"update"
			                },'-']
		        }),
				items:grid
		}]
	}); 
	function addanswer(){
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
				};
			entry_nums+="'"+record[i].json.entry_num+"'";
		}
			}
		    var mnum = entry_nums;
	var answer = Ext.get("answer").dom.value.trim();//回答栏的内容
	Ext.showProgressDialog();
		Ext.Ajax.request({
					url:'/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
					params : {
						type : 'TOOLALL1',
						mnum : mnum,
						answer:answer
						},
						success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)==1) {
						Ext.MessageBox.alert("提示","回复成功！");
						imgviewpanelck.form.reset();
						}else{
						Ext.MessageBox.alert("提示","回复失败，请检查数据再次尝试，如多次失败请联系管理员！");
						imgviewpanelck.form.reset();
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						Ext.MessageBox.alert("提示","回复添加失败，数据传送中出现问题！");
						imgviewpanelck.form.reset();
					}
						});
						
	};
	var in_problem=new Ext.form.TextArea({fieldLabel: '问题描述',id: 'in_problem', readOnly:false,width:364,height:180});
	var answer=new Ext.form.TextArea({fieldLabel: '解决方案',id: 'answer', readOnly:false,width:364,height:180});
	 function viewImgFn(){
            
			 //显示图片panel
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
				};
			entry_nums+="'"+record[i].json.entry_num+"'";
		}	
		   var mnum = entry_nums;
		  // alert(mnum);
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
						mnum:mnum
					},
					success : function(result) {
							Ext.getCmp("imgview").body.update(result.responseText);
							
					},
					failure : function(result){
						alert( "提示:读取失败！");

					}
				}); 
			   	
			}
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
		        Ext.getCmp("tpmc").setValue(filename);//案例名称
			}
					
	 var dialog1 = null;  
		 var button1 = null;
			function getDialog1(){
				var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
				var id1="";
				var entry_nums="";
    	var length = record.length;
		for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								entry_nums+=",";
							}
							entry_nums+="'"+record[i].json.entry_num+"'";
					}}
					id1=entry_nums;
		//alert(id1);
				 ljth = Ext.get("ljthtp").dom.value//零件编码
				if(ljth==null){alert('请选择零件编码')}
				var dialog1 = null;
			if (!dialog1) {
				
				dialog1 = new Ext.ux.UploadDialog.Dialog({
				  url: '/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp?type=uploadimg1&id1='+id1, 
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
		var ljth = Ext.get("ljthtp").dom.value.trim();
		//var scr = Ext.get("scrtp").dom.value.trim();
		var tpmc = Ext.get("tpmc").dom.value.trim();
		//alert(cpmc+""+tpmc);
		Ext.showProgressDialog();
		Ext.Ajax.request({
					url : '/tsms/web/sms/storemgtmod/cut_tool/pa_c_logic.jsp',
					params : {
						type : 'ADDIMG',
						ljth : ljth,
						//scr:scr,
						//scsj:scsj,
						tpmc:tpmc
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
		    width:380,
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
		                {
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
			            fieldLabel: '文件名称',
			            name: 'tpmc',
			            width:100,
			            id: 'tpmc'
	                 }]
                },{
                columnWidth:.3,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*请以部件或零件名称命名&nbsp;*</font>',
                border:false
                }]
            },{
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
		                	if(Ext.get("ljthtp").dom.value.trim()==""){
		                	Ext.MessageBox.alert('提示','零件编码不能为空');
		                	}else if(Ext.get("tpmc").dom.value.trim()==""){
		                	Ext.MessageBox.alert('提示','案例名称不能为空');
		                	}else {
		                		addsaveFn1();
		                		}
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
                width:385,
                height:240,
                closable:false,
             //   closeAction:'hide',
                plain: true,
                title:"<p align='center'>上传解决方案</p>",    
				bodyBorder:true,
                items:inform1      
		    });		   	
	function showDialog1(button){
				getDialog1().show(button.getEl());
			};
	function imgFn(){
		var record = sm.getSelections();
		if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
		//var mnum = record[0].get("mnum");
			};
				//window1.findById("ljthtp").setValue(mnum);
				window1.findById("scrtp").setValue(userid);
				Ext.getCmp("window1").show();
				Ext.getCmp("save1").hide();
				}
function replyFn(){
var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的案例');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个案例');
			}else{
		   // var mnum = record[0].get("mnum");
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
	                closable:false,
	             //   closeAction:'hide',
	                plain: true,
	                title:"<p align='center'>查看案例详情</p>",    
					bodyBorder:true,
	                items:imgviewpanelck,
	                buttonAlign:"center",
	                 buttons: [{
		                text:'加载附件',
		                id:'download',
		                handler: viewImgFn
		            },{
		                text:'上传附件',
		                handler:imgFn
					  },{
						text: '保存',
						handler: addanswer
						
						
						},{
						text: '返回',
						handler: function(){
						 Ext.getCmp("imgwindowck").hide();
						
						}
		            }
		       	]
			    });
			   	imgwindowck.show();}
          }
          
  //刷新函数   
	function refreshFn(){
		ds.reload();
	}  
    
	//批准函数
	function appFn(){
		var record =sm.getSelections();// 返回值为 Record 类型	         
		if(record.length==0){
            alert('提示：请先选择要提交的申请!');
            return;
    	}
    	var entry_nums="";
    	var length = record.length;
		if(record.length!=0) {
			Ext.MessageBox.confirm('提示','是否确定提交？？？',function(btn) {
				if (btn=='yes'){
				Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								entry_nums+=",";
							}
							entry_nums+="'"+record[i].json.entry_num+"'";
					}
					
					Ext.showProgressDialog("正在提交修改数据，请稍候...");
					Ext.Ajax.request({
						 	url:'logic_checkmod.jsp',
						 	method:'post',
					 		params:{type:'APPLY',entry_nums:entry_nums,length:length},
							success:function(result){
								if (result.responseText.indexOf("success") != -1){
									Ext.hideProgressDialog();	
									alert('提示：提交成功！');
									ds.load();	
								}else{
									Ext.hideProgressDialog();
									alert('错误：某些问题已被解决，请重试！');
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
	
	
	//拒绝
	function refFn(){
		var record =sm.getSelections();// 返回值为 Record 类型	         
		if(record.length==0){
            alert('提示：请先选择要拒绝的申请!');
            return;
    	}
    	var entry_nums="";
    	var length = record.length;
		if(record.length!=0) {
			Ext.MessageBox.confirm('提示','是否确定拒绝？？？',function(btn) {
				if (btn=='yes'){
				Ext.showProgressDialog();
					for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
							if(i!=0){
								entry_nums+=",";
							}
							entry_nums+="'"+record[i].json.entry_num+"'";
					}
					
					Ext.showProgressDialog("正在提交修改数据，请稍候...");
					Ext.Ajax.request({
						 	url:'logic_checkmod.jsp',
						 	method:'post',
					 		params:{type:'CRefuse',entry_nums:entry_nums,length:length},
							success:function(result){
								if (result.responseText.indexOf("success") != -1){
									Ext.hideProgressDialog();
									alert("提示：拒绝成功!");
									ds.load();	
								}else{
									Ext.hideProgressDialog();
									alert('错误：某些问题已被解决，请重试！');
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
			var url = 'http://localhost:8080/tsms/web/sms/ldappmod/check/centry_excel.jsp';
    		location.href='centry_excel.jsp?url='+url;
    	}
	}
	
	
});	




