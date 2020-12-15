Ext.onReady(function(){
	var filter='1=1';
	var start=0;
	var limit=10000;//每页显示记录条数
	var special="";

    var record_chinese= Ext.data.Record.create([    		
    		{name:'tpmc'},
    		{name:'time'},
    		{name:'id'}	
    ]); 
    
    //调用数据库信息
	var hqObj={url:'logic_english1.jsp',method: 'POST',extraParams:{type:'QUERYCH1',params:{start:start,limit:limit},filter:filter}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hqObj)),
        reader: new Ext.data.JsonReader({
            totalProperty:'totalProperty',
            root:'filedata'
        },record_chinese),
        autoload:true
    }); 
    var obj={params:{start:start,limit:limit}};   
    ds.load(obj);
    var hqObjAni={url:'logic_english1.jsp',method:'POST',extraParams:{}};
	var record_chinese=new Ext.data.Store({
	    proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hqObjAni)),
	    pruneModifiedRecords:true,
		reader:new Ext.data.JsonReader({            
	        root:'root'
	    },record_chinese)
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
			            header: "名称",
			            dataIndex: "tpmc",
			            width:180,
			            align:'center',
			            sortable: true
			        } , 
		        		{ 
			            header: "上传时间",
			            dataIndex: "time",
			            width:80,
			            renderer:renderFn,
			            align:'center',
			            sortable: true
			        }],
		        ds:ds,
		        sm: sm, //多选框checkbox
		        height: 400,
		        autoScroll:true,
		        stateId:'filetable',
		        renderTo: document.body
    });
	
 function renderFn(value){
    	return value.substring(0,10);
     }
    //组装界面
    new Ext.Viewport({
		layout:"border",
		autoHeight:false,
		items:[
				{
				region:'center',
				layout:'fit',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:['-',{								
					            id:'refuse',
					            text:"删除", 
					            handler:refFn,
					            iconCls:"remove"
					        },'-','-',{								
					            id:'output',
					            text:"添加", 
					            handler:addFn,//outFn,
					            iconCls:"add"
			        		},'-',{								
					            id:'detail',
					            text:"明细", 
					            handler:detFn,
					            iconCls:"detail"
					        }]
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
	    Ext.getCmp("output").hide();
	};
	function refFn(){
	var record =sm.getSelections();// 返回值为 Record 类型	            	
		if(record.length==0){
            alert('请先选择要删除的项!');
            return;
    	}
   		var id1="";
		if(record.length!=0) {
	        var confirm=Ext.MessageBox.show({
					title:'提醒!',
					width:300,
					msg:"将删除所选文件，确认吗？",
					buttons:{"ok":"确定","no":"取消"},
					icon:Ext.MessageBox.INFO,
					animEl:"test5",
					fn:function(btn){					              
						if (btn=='ok'){	
						   Ext.showProgressDialog();							
						   	for(var i=0;i<record.length;i++){
								if(i!=0){
									id1+=",";
								}
								id1+="'"+record[i].json.id+"'";
								//alert(record[i].json.id);
							}
						   	alert(id1);
							Ext.Ajax.request({
						   	url: 'logic_english1.jsp',
						   	params: {type:'DEL1',id:id1},
						   	success: function(resp){
						   		Ext.hideProgressDialog();
						   		if(parseInt(resp.responseText)){
							   		for(var i=0;i<record.length;i++){
										ds.remove(record[i]);
										ds.load(obj);
									}
									alert('删除成功！');
									//customQueryFn();
						   		}else{
						   			alert("删除失败！");				   
						   		}						   
						   	},
						   	failure: function(){
						   		Ext.hideProgressDialog();
								alert('服务器出现错误请稍后再试！');				   
						   	}
						});	    
				    	}
						else if (btn=='no'){						      
							}									
					}
			     });		  
		       }
		};
	
	
	function detFn(){var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的文件');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个文件');
			}else{
				var id2="";
    	var length = record.length;
		for(var i=0;i<record.length;i++){			//若选中的不只一条，将每条的申请单号分开
			if(i!=0){
			id2+=",";
				};
			id2+="'"+record[i].json.id+"'";}
			//alert(id2);
		   // var tuhao_sim = record[0].get("tuhao_sim");
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
	                title:"<p align='center'>查看文件</p>",    
					bodyBorder:true,
	                items:imgviewpanel      
			    });
			   	imgwindow.show();
			   	Ext.Ajax.request({
					url : 'logic_english1.jsp',
					params : {
						type : 'VIEW1',
						tuhao:id2
					},
					success : function(result) {
							Ext.getCmp("imgview").body.update(result.responseText);
							
					},
					failure : function(result){
						alert( "提示:读取失败！");

					}
				}); 
			   	
			}};
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
    
		//上传零件图片getDialog
		 var dialog1 = null;  
		 var button1 = null;
	function getDialog1(){	
		special=uuid(36,16);
				// ljth = Ext.get("ljthtp").dom.value//零件编码
				//if(ljth==null){alert('请选择零件编码')}
		var dialog1 = null;
		//alert(special);
		if (!dialog1) {
				dialog1 = new Ext.ux.UploadDialog.Dialog({
				  url: 'logic_english1.jsp?type=uploadimg1&special='+special,
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
			
			//上传图片函数
			function showDialog1(button){
				getDialog1().show(button.getEl());
			}
			
			function update(dialog){}
			
			function onUploadSuccess1(dialog,filename,resp_data){
				var parts = filename.split(/\/|\\/);
				//alert(filename);
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
		        Ext.getCmp("tpmc").setValue(filename);
			}		
	 var data=getNowtime();
     function getNowtime(){
		var sUrl = 'logic_english1.jsp';
		var parms = {type:'getNowtime'};
		var objtp = Ext.util.JSON.decode(callService(sUrl,parms));
		var nowtimeValue = objtp.nowtime;
		return nowtimeValue;
	}		
	function addsaveFn(){
		//var ljth = Ext.get("ljthtp").dom.value.trim();
		var scr = Ext.get("scrtp").dom.value.trim();
		var tpmc = Ext.get("tpmc").dom.value.trim();
		var time=data;
		//alert(cpmc+""+tpmc);
		Ext.showProgressDialog();
		Ext.Ajax.request({
					url : 'logic_english1.jsp',
					params : {
						type : 'ADD1',
						scr:scr,
						time:time,
						tpmc:tpmc,
						special:special
					},
					success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)) {
							Ext.getCmp("inform1").form.reset();
						
							 Ext.getCmp("upload1").show();
							Ext.getCmp("window1").hide();
							alert( "提示:保存成功！");
							ds.load(obj);
						}else{
							alert( "提示:保存失败！");
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
		               {
	    	    columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
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
                columnWidth:.15,					              
                layout: 'form',
                labelAlign:"right",
                bodyStyle:'padding:5px 0px 0',
                html:'<font color="#FF0000">&nbsp;*&nbsp;*</font>',
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
		                	
		                		addsaveFn();
		                	
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
                title:"<p align='center'>添加使用说明</p>",    
				bodyBorder:true,
                items:inform1      
		    });		   	
	function addFn(){if(!Ext.getCmp("window1").isVisible()){
			    window1.findById("scrtp").setValue(userid);
	 	            Ext.getCmp("save1").show();
					Ext.getCmp("window1").show();//添加窗口显示
				}};
	})