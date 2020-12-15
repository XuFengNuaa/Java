Ext.onReady(function(){
	Ext.QuickTips.init();//开启表单提示
    Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	var filter="";
	var order="";
	var start=0;
	var limit=100;//每页显示记录条数

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
	     var record_sim = Ext.data.Record.create([    		
	                                      		{name:'dalei_sim'},
	                                  			{name:'xiaolei_sim'},
	                                  			{name:'daihao_sim'},
	                                  			{name:'mingcheng_sim'},
	                                  			{name:'tuhao_sim'},
	                                  			{name:'material_sim'},
	                                  			{name:'machine_sim'},
	                                  			{name:'maopilong_sim'},
	                                  			{name:'maopiwidth_sim'},
	                                  			{name:'maopihigh_sim'},
	                                  			{name:'qxtj_sim'},
	                                  			{name:'qxmj_sim'},
	                                  			{name:'zxd_sim'},
	                                  			{name:'id'}
	                                  			]); 
	     
	var hpObj={url:'logic_matain.jsp',method: 'POST',extraParams:{}};
	var ds= new Ext.data.Store({
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection(hpObj)),
        reader: new Ext.data.JsonReader({
            		totalProperty:'totalProperty',
            		root:'filedata'
        		},record_sim)
    });
	
	var sm = new Ext.grid.CheckboxSelectionModel();
	var grid = new Ext.grid.GridPanel({
        title: false,
        id:'filetable',
        enableColumnMove:false,
        columns: [sm,
                   {header: "整机",width: 80,align:'center',dataIndex: "dalei_sim",sortable: true},
                   {header: "部件",width:100,align:'center',dataIndex: "xiaolei_sim",sortable: true},
                   {header: "产品代号",width:80,align:'center',dataIndex: "daihao_sim",sortable: true},
                   {header: "零件名称",width:80,align:'center',dataIndex: "mingcheng_sim",sortable: true},
                   {header: "零件图号",width:80,align:'center',dataIndex: "tuhao_sim",sortable: true},
                   {header: "材料",width:80,align:'center',dataIndex: "material_sim",sortable: true},
                   {header: "加工设备",width:80,align:'center',dataIndex: "machine_sim",sortable: true},
                   {header: "零件编码",width:80,align:'center',dataIndex: "maopilong_sim",sortable: true}],
                   //{header: "毛坯宽度",width:80,align:'center',dataIndex: "maopiwidth_sim",sortable: true},
                  //{header: "毛坯高度",width: 80,align:'center',dataIndex: "maopihigh_sim",sortable: true},
                  // {header: "切削体积",width: 80,align:'center',dataIndex: "qxtj_sim",sortable: true},
                  // {header: "切削表面积",width: 80,align:'center',dataIndex: "qxmj_sim",sortable: true},
                  // {header: "置信度",width:80,align:'center',dataIndex: "zxd_sim",sortable: true},
                   //{header: "id",width:80,align:'center',dataIndex: "id",hidden:true,sortable: true}],
        ds:ds,
        sm: sm, //多选框checkbox
        height: 400,
        autoScroll:true
	});
	
	//查询函数
	function customQueryFn(){
		hpObj.extraParams.filter = search.getFilter();
		hpObj.extraParams.order = search.getOrder();
		hpObj.extraParams.type = "QUERY";
		var obj={params:{start:start,limit:limit}};
   		ds.load(obj);
	}

		var toolone=new Ext.Toolbar.Separator({id:'toolone'});//按钮之间的竖线 
		var tooltwo=new Ext.Toolbar.Separator({id:'tooltwo'});//按钮之间的竖线 
		var toolthree=new Ext.Toolbar.Separator({id:'toolthree'});//按钮之间的竖线    
		var toolfour=new Ext.Toolbar.Separator({id:'toolfour'});//按钮之间的竖线
		var toolfive=new Ext.Toolbar.Separator({id:'toolfive'});//按钮之间的竖线    
		var toolsix=new Ext.Toolbar.Separator({id:'toolsix'});//按钮之间的竖线
		//组装页面
		var win=new Ext.Viewport({
		layout:"border",
		id:"win",
		autoHeight:false,
		items:[search,
				{items:grid,
				region:'center',
				layout:'fit',
				id:'tablepanel',
				autoScroll:true,
				tbar:new Ext.Toolbar({ 
						items:[toolone,{id:'add-btn',text:"增加实例",handler:addFn,iconCls:"add"},
							tooltwo,{id:'del-btn',text:"删除实例", handler: delFn,iconCls:"remove"},
							//toolthree,{id:'edit-btn',text:"修改置信度", handler : editFn,iconCls:"update"},
							toolfour,{id:'pass-btn',text:"上传物料清单",handler: imgFn,iconCls:"add"},
							//toolfive,{id:'img-btn',text:"修改零件图片",handler: imgEditFn,iconCls:"update"},
							toolsix,{id:'img-btn',text:"查看物料清单",handler: viewImgFn,iconCls:"detail"}]
		        }),
		        bbar: new Ext.PagingToolbar({
				    pageSize:limit,
				    afterPageText: '页共{0}页',
		        	beforePageText: '第',
				    store:ds,
				    displayInfo:true,
				    displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
				    emptyMsg:"没有记录"
				})			
		}]
		});
		

		function viewImgFn(){
			 //显示图片panel
			var record = sm.getSelections();
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择要浏览的图片');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一个零件');
			}else{
		    var tuhao_sim = record[0].get("tuhao_sim");
	    	var imgviewpanel=new Ext.FormPanel({
				id:'imgview',
			    labelWidth:75,
			    width:1000,
			    height:700,			      				       
			    frame:true,
			    bodyStyle:'padding:10px 10px 0',	
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
	                title:"<p align='center'>查看零件图片</p>",    
					bodyBorder:true,
	                items:imgviewpanel      
			    });
			   	imgwindow.show();
			   		Ext.Ajax.request({
					url : 'logic_matain.jsp',
					params : {
						type : 'ADD2',
						tuhao:tuhao_sim
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
				
		//零件类型添加大类
	    var partdalei= Ext.data.Record.create([
	        			{name:'dalei'}			
	        			 ]);  
		 var storedalei = new Ext.data.Store ({
		     proxy: new Ext.data.HttpProxy({url:"logic_matain_delete.jsp?type=QUERYDALEI"}), // 数据源
		     reader: new Ext.data.JsonReader({
		         id:'id',
			     root:"filedata"
			       },partdalei),
			     remoteSort: true
		     });
		 storedalei.load();
		var daleicombox=new Ext.form.ComboBox({
			fieldLabel:'整机',
	     	name:'dalei',
			id:"dalei",
			store:storedalei, 
			displayField:'dalei',
			triggerAction: 'all',
			width:150,
			listeners:{
				select:function(){
					storexiaolei.load({params:{dalei:daleicombox.getValue()}});
				}
				},
			emptyText:'请选择整机',
			allowBlank:true,
			mode:'local',
			typeAhead: true,
			editable:true
	     });
		//部件
		var partxiaolei= Ext.data.Record.create([
	    			{name:'xiaolei'}			
	    			 ]);  
		var storexiaolei = new Ext.data.Store ({
	     proxy: new Ext.data.HttpProxy({url:"logic_matain_delete.jsp?type=QUERYXIAOLEI"}), // 数据源
	     reader: new Ext.data.JsonReader({
	         id:'id',
		     root:"filedata"
		       },partxiaolei),
		     remoteSort: true
	     });
	     var xiaoleicombox=new Ext.form.ComboBox({
				fieldLabel:'部件',
		     	name:'xiaolei',
				id:"xiaolei",
				store:storexiaolei, 
				displayField:'xiaolei',
				triggerAction: 'all',
				width:150,
				emptyText:'请选择零件小类',
				allowBlank:true,
				mode:'local',
				typeAhead: true,
				editable:true
		});
	  // 定义添加窗口材料
		 var material_com= Ext.data.Record.create([    		
		              		{name:'id'},
		          			{name:'paihao'}			
		          		   ]); 
		 var materialCom = new Ext.data.Store ({
		     proxy: new Ext.data.HttpProxy({url:"logic_matain_delete.jsp?type=QUERYMATERIAL"}), // 数据源
		     reader: new Ext.data.JsonReader({
		         id:'id',
			     root:"filedata"
			       },material_com),
			     remoteSort: true
		     });
			materialCom.load();
		 	var ComboBoxMaterial=new Ext.form.ComboBox({
				fieldLabel:'材料牌号',
		     	name:'clph',
				id:"clph",
				store:materialCom, 
				displayField:'paihao',
				valueField:'paihao',
				triggerAction: 'all',
				width:150,
				emptyText:'请选择材料牌号',
				allowBlank:true,
				mode:'local',
				typeAhead: true,
				editable:true
	   }); 
		 // 定义机床型号
			 var machine_com= Ext.data.Record.create([    		
			              		{name:'id'},
			          			{name:'xinghao'}			
			          		   ]); 
			 var machineCom = new Ext.data.Store ({
			     proxy: new Ext.data.HttpProxy({url:"logic_matain_delete.jsp?type=QUERYMACHINE"}), // 数据源
			     reader: new Ext.data.JsonReader({
			         id:'id',
				     root:"filedata"
				       },machine_com),
				     remoteSort: true
			     });
			 machineCom.load();
			 	var ComboBoxmachine=new Ext.form.ComboBox({
					fieldLabel:'机床型号',
			     	name:'jcxh',
					id:"jcxh",
					store:machineCom, 
					displayField:'xinghao',
					valueField:'xinghao',
					triggerAction: 'all',
					width:150,
					emptyText:'请选择机床型号',
					allowBlank:true,
					mode:'local',
					typeAhead: true,
					editable:true
		   }); 
		//添加panel
		var inform=new Ext.FormPanel({
			id:'inform',
		    labelWidth:75, 	
		    height:400,			      				       
		    frame:true,
		    bodyStyle:'padding:10px 10px 0',
		    items: [{
		            layout:'column',
		            items:[{
		                columnWidth:.5,					              
		                layout: 'form',
		                labelAlign:"right",
		                items: [
			                daleicombox,
			                xiaoleicombox,
			                ComboBoxMaterial,
			                ComboBoxmachine
			                
			                ]
			         },{
		                columnWidth:.5,
		                layout: 'form',
		                labelAlign:"right",
		                items: [
		                {xtype:'textfield',fieldLabel: '产品代号',name: 'cpdh',width:150,id: 'cpdh'},
		                {xtype:'textfield',fieldLabel: '零件名称',name: 'ljmc',width:150,id: 'ljmc'},
			            {xtype:'textfield',fieldLabel: '零件图号',name: 'ljth',width:150, id: 'ljth'},
		                {xtype:'textfield',fieldLabel: '零件编码',name: 'maopilong',width:150,id: 'maopilong'}]
		             }],
		     buttonAlign:"center",
		     buttons: [{text:'保存',id:'save',handler:savebtn},
		               {text: '取消',handler: cancelbtn}]
		   	}]
		   	});
		   	
		function savebtn(){
			
        	if(Ext.get("cpdh").dom.value.trim()==""){
     	        Ext.MessageBox.alert('提示',"产品代号为必填项");
     	        return;
        }else if(Ext.get("ljmc").dom.value.trim()==""){
     	        Ext.MessageBox.alert('提示',"零件名称为必填项");
     	        return;
        }else if(Ext.get("ljth").dom.value.trim()==""){
     	        Ext.MessageBox.alert('提示',"零件图号为必填项");
     	        return;
        }else if(daleicombox.getValue()==""){
     	        Ext.MessageBox.alert('提示',"整机类型为必填项");
     	        return;
        }else if(xiaoleicombox.getValue()==""){
     	        Ext.MessageBox.alert('提示',"部件类型为必填项");
     	        return;
        }else if(Ext.getDom("clph").value==""){
 	        Ext.MessageBox.alert('提示',"材料牌号为必填项");
 	        return;
        }else if(Ext.getDom("jcxh").value==""){
 	        Ext.MessageBox.alert('提示',"机床型号为必填项");
 	        return;
        }else if(Ext.get("maopilong").dom.value.trim()==""){
     	        Ext.MessageBox.alert('提示',"零件编码为必填项");
     	        return;
        }
		 else{
			 insertSaveFn();}	
		};
		//
		function cancelbtn(){
			 Ext.getCmp("window").hide();			
		}
		
		//定义增加弹出窗口
		   var window = new Ext.Window({
 			id:'window',
 			modal:true,
             width:600,
             height:300,
             closable:false,
          //   closeAction:'hide',
             plain: true,
             title:"<p align='center'>零件实例添加</p>",    
				bodyBorder:true,
             items:inform	               
		    });
			//添加函数
			function addFn(){
				if(!Ext.getCmp("window").isVisible()){
	 	            Ext.getCmp("save").show();
					Ext.getCmp("window").show();//添加窗口显示
					  $('#ljmc').keypad();
			             $('#ljth').keypad();
		                    $('#maopilong').keypad();
				            $('#cpdh').keypad();
				}
			}
			
		 //上传图片panel
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
	                	columnWidth:.85,					              
		                layout: 'form',
		                labelAlign:"right",
		                 items:[{
	                 	xtype:'textfield',
			            fieldLabel: '零件图号',
			            name: 'ljthtp',
			            width:100,
			            id: 'ljthtp'
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
	    	    columnWidth:1,					              
                layout: 'column',
                items:[{
                	columnWidth:.85,					              
	                layout: 'form',
	                labelAlign:"right",
	                 items:[{
	                 	xtype:'textfield',
			            fieldLabel: '图片名称',
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
		                	if(Ext.get("ljthtp").dom.value.trim()==""){
		                	Ext.MessageBox.alert('提示','零件图号不能为空');
		                	}else if(Ext.get("tpmc").dom.value.trim()==""){
		                	Ext.MessageBox.alert('提示','图片名称不能为空');
		                	}else {
		                		addsaveFn();
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
                title:"<p align='center'>上传零件图片</p>",    
				bodyBorder:true,
                items:inform1      
		    });		    


		//上传零件图片
		function imgFn(){
			var record =sm.getSelections();// 返回值为 Record 类型
			if(record.length==0){
				Ext.MessageBox.alert('提示','请选择一条零件信息');
			}else if(record.length!=1){
				Ext.MessageBox.alert('提示','每次只能选择一条零件信息');
			}else{
				window1.findById("ljthtp").setValue(record[0].get("tuhao_sim"));
				window1.findById("scrtp").setValue(userid);
				Ext.getCmp("window1").show();
				Ext.getCmp("save1").hide();
			}
			
		}

			//上传零件图片getDialog
		 var dialog1 = null;  
		 var button1 = null;
			function getDialog1(){
				var record = sm.getSelections();
				var ljth = record[0].get("tuhao_sim");
			if (!dialog1) {
				dialog1 = new Ext.ux.UploadDialog.Dialog({
				  url: 'logic_matain.jsp?type=uploadimg&ljth='+ljth, 
				  reset_on_hide: false,
				  modal:true,
				  closable : false,   
		          collapsible : false,   
		          draggable : true, 
		          proxyDrag : true,   
		          constraintoviewport : true, 
				  allow_close_on_upload: true,
				  post_var_name:'upload',
				  autoCreate : true 
				});		
				dialog1.on('uploadsuccess', onUploadSuccess1);
				dialog1.on('beforehide',update, this);
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
		        Ext.getCmp("upload1").show();  
		        Ext.getCmp("tpmc").setValue(filename);
			}
					
						
	function addsaveFn(){
		var ljth = Ext.get("ljthtp").dom.value.trim();
		//var scr = Ext.get("scrtp").dom.value.trim();
		var tpmc = Ext.get("tpmc").dom.value.trim();
		//alert(cpmc+""+tpmc);
		Ext.showProgressDialog();
		Ext.Ajax.request({
					url : 'logic_matain.jsp',
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
							Ext.getCmp("window1").hide();
							alert( "提示:保存成功！");
						}else{
							alert( "提示:图片已经存在，不需要添加！");
						}
					},
					failure : function(){
						Ext.hideProgressDialog();
						alert( "提示:保存失败！");
					}
					
				}); 
		}
	
	    //添加实例保存函数
		function insertSaveFn(){
				var dalei_sim = daleicombox.getValue();
				var xiaolei_sim = xiaoleicombox.getValue();
				var machine_sim = Ext.getDom("jcxh").value;
				var material_sim = Ext.getDom("clph").value;
		    	var daihao_sim = Ext.get("cpdh").dom.value.trim();
				var mingcheng_sim = Ext.get("ljmc").dom.value.trim();
				var tuhao_sim = Ext.get("ljth").dom.value.trim();				
				var maopilong_sim = Ext.get("maopilong").dom.value.trim();
				//var maopiwidth_sim = Ext.get("maopikuan").dom.value.trim();
				//var maopihigh_sim = Ext.get("maopigao").dom.value.trim();	
				//var qxtj_sim = Ext.get("qxtj").dom.value.trim();
				//var qxmj_sim = Ext.get("qxmj").dom.value.trim();
				//var zxd_sim = Ext.get("zxd").dom.value.trim();
				Ext.showProgressDialog();
				Ext.Ajax.request({
					url : 'logic_matain.jsp',
					params : {
						type : 'ADD',
						dalei_sim:dalei_sim,
						xiaolei_sim : xiaolei_sim,
						machine_sim : machine_sim,
						material_sim : material_sim,
						daihao_sim : daihao_sim,
						mingcheng_sim : mingcheng_sim,
						tuhao_sim : tuhao_sim,
						maopilong_sim:maopilong_sim	
					},
					success : function(resp) {
						Ext.hideProgressDialog();
						if (parseInt(resp.responseText)) {
							Ext.getCmp("inform").form.reset();
							Ext.getCmp("window").hide();
							customQueryFn();
							alert( "提示:保存成功！");
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
		
		//删除零件信息函数
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
								//alert(record[i].json.id);
							}
						   	
							Ext.Ajax.request({
						   	url: 'logic_matain.jsp',
						   	params: {type:'DEL',id:id},
						   	success: function(resp){
						   		Ext.hideProgressDialog();
						   		if(parseInt(resp.responseText)){
							   		for(var i=0;i<record.length;i++){
										ds.remove(record[i]);
									}
									alert('删除成功！');
									customQueryFn();
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
		}
		
	
});