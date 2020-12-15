function upWeight(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";

		var up_H = {xtype:'numberfield',id:"up_H",fieldLabel:"立柱高度  ", name:'height',width:100,allowBlank:false};
		var up_K = {xtype:'numberfield',id:"up_K",fieldLabel:"立柱宽度  ", name:'kuang',width:100,allowBlank:false};
		var up_yt = {xtype:'numberfield',id:"up_yt",fieldLabel:"Y轴行程  ", name:'yroute',width:100,allowBlank:false};
		var up_sdx = {xtype:'numberfield',id:"up_sdx",fieldLabel:"主轴宽度  ",name:'sdwidth',width:100,allowBlank:false};
		var up_sdy = {xtype:'numberfield',id:"up_sdy",fieldLabel:"主轴高度  ",name:'sdheight',width:100,allowBlank:false};
		var up_sideThick = {xtype:'numberfield',id:"up_sideThick",fieldLabel:"立柱壁厚  ",name:'sidethick',width:100,allowBlank:false};
		var up_hole = {xtype:'numberfield',id:"up_hole",fieldLabel:"减重孔  ",name:'hole',width:100,allowBlank:false};
		var up_ribThick = {xtype:'numberfield',id:"up_ribThick",fieldLabel:"板筋厚度  ",name:'ribthick',width:100,allowBlank:false};
		var up_hN = {xtype:'numberfield',id:"up_hN",fieldLabel:"横板筋数量  ",name:'hnumber',width:100,allowBlank:false};
		var up_sN = {xtype:'numberfield',id:"up_sN",fieldLabel:"竖板筋数量  ",name:'snumber',width:100,allowBlank:false};
	
		var column101 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"wid",hidden:true},up_H,up_K,up_yt,up_sdx,up_sdy]}; //有 id
 	//	var column10_1 = {layout: 'form',columnWidth:.5,items: [up_H,up_K,up_yt,up_sdx,up_sdy]}; // id
 		var column102 = {layout: 'form',columnWidth:.5,items: [up_sideThick,up_hole,up_ribThick,up_hN,up_sN]};
	
//-------------结果表格-----------------------------------------------------
	var upWeight_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var upWeight_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[upWeight_smodel,
			new Ext.grid.RowNumberer({header:'序号',width:35,align:'center'}),
			//排序开启，正序和倒序
		//	{header: "ID", dataIndex : "wid",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'高度',dataIndex:'height',align:'center',width:118,sortable: true},
			{header:'宽度',dataIndex:'kuang',width:128,align:'center'},
			{header:'Y轴行程',dataIndex:'yroute',width:100,align:'center'},
			{header:'主轴宽度',dataIndex:'sdwidth',width:100,align:'center'},
			{header:'主轴高度',dataIndex:'sdheight',width:80,align:'center'},
			{header:'壁厚',dataIndex:'sidethick',width:80,align:'center'},
			{header:'减重孔',dataIndex:'hole',width:80,align:'center'},
			{header:'板筋厚度',dataIndex:'ribthick',width:80,align:'center'},
			{header:'横筋数',dataIndex:'hnumber',width:80,align:'center'},
			{header:'竖筋数',dataIndex:'snumber',width:110,align:'center'}]});
	
	var msgTip102;          // 一定要定义在使用前，且定义为全局变量
	var pageN102 = true;
	
    var upWeight_store=new Ext.data.JsonStore({  
    	id:"upWeight_store",
		url:"queryUpWeight.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN102){
                   msgTip102 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["wid","height","kuang","yroute","sdwidth","sdheight","sidethick","hole","ribthick",
						"hnumber","snumber"]});
	
	var upWeight_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'增加',iconCls:"add",handler:addUpWgt},
				'-',{xtype:'button',text:'删除',iconCls:"delete",handler:delUpWgt},
				'-',{xtype:'button',text:'编辑',iconCls:"edit",handler:editUpWgt}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23)*0.5;
		
	var upWeight_grid=new Ext.grid.GridPanel({
		title:'立柱信息列表',	
		height:Y,
		frame:true,
		autoScroll:true,
	    store:upWeight_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:upWeight_columns,
		sm: upWeight_smodel,
		tbar: upWeight_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:upWeight_store,
					pageSize:8, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
	
	
//------Bed列表-----------------------------------------------
		var bd_L = {xtype:'numberfield',id:"bd_L",fieldLabel:"床身长度  ", name:'bLength',width:100,allowBlank:false};
		var bd_K = {xtype:'numberfield',id:"bd_K",fieldLabel:"床身宽度  ", name:'bWidth',width:100,allowBlank:false};
		var bd_H = {xtype:'numberfield',id:"bd_H",fieldLabel:"床身高度  ", name:'bHeight',width:100,allowBlank:false};
		var bCd = {xtype:'numberfield',id:"bCd",fieldLabel:"槽宽  ",name:'bCd',width:100,allowBlank:false};
		var bXl = {xtype:'numberfield',id:"bXl",fieldLabel:"线轨X长度  ",name:'bXl',width:100,allowBlank:false};
		var bXd = {xtype:'numberfield',id:"bXd",fieldLabel:"线轨X间距  ",name:'bXd',width:100,allowBlank:false};
		var bZl = {xtype:'numberfield',id:"bZl",fieldLabel:"线轨Z长度",name:'bZl',width:100,allowBlank:false};
		var bZd = {xtype:'numberfield',id:"bZd",fieldLabel:"线轨Z间距  ",name:'bZd',width:100,allowBlank:false};
		var bRibthick = {xtype:'numberfield',id:"bRibthick",fieldLabel:"板筋厚度  ",name:'bRibthick',width:100,allowBlank:false};
		var bHole = {xtype:'numberfield',id:"bHole",fieldLabel:"减重孔  ",name:'bHole',width:100,allowBlank:false};
		var bStructure = {xtype:'numberfield',id:"bStructure",fieldLabel:"结构特征  ",name:'bStructure',width:100,allowBlank:false};
	
		var column103 = {layout: 'form',columnWidth:.5,items: [{xtype:"numberfield",name:"bid",hidden:true},bd_L,bd_K,bd_H,bCd,bXl,bXd]}; //有 id
 	//	var column10_1 = {layout: 'form',columnWidth:.5,items: [up_H,up_K,up_yt,up_sdx,up_sdy]}; // id
 		var column104 = {layout: 'form',columnWidth:.5,items: [bZl,bZd,bRibthick,bHole,bStructure]};
	
	
	var bdWeight_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var bdWeight_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[bdWeight_smodel,
			new Ext.grid.RowNumberer({header:'序号',width:35,align:'center'}),
			//排序开启，正序和倒序
			//{header: "ID", dataIndex : "bid",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'长度',dataIndex:'bLength',align:'center',width:118,sortable: true},
			{header:'宽度',dataIndex:'bWidth',width:128,align:'center'},
			{header:'高度',dataIndex:'bHeight',width:100,align:'center'},
			{header:'槽宽',dataIndex:'bCd',width:100,align:'center'},
			{header:'线轨X长度',dataIndex:'bXl',width:80,align:'center'},
			{header:'线轨X间距',dataIndex:'bXd',width:80,align:'center'},
			{header:'线轨Z长度',dataIndex:'bZl',width:80,align:'center'},
			{header:'线轨Z间距',dataIndex:'bZd',width:80,align:'center'},
			{header:'板筋厚度',dataIndex:'bRibthick',width:110,align:'center'},
			{header:'减重孔',dataIndex:'bHole',width:80,align:'center'},
			{header:'结构特征',dataIndex:'bStructure',width:110,align:'center'}]});
	
	var msgTip103;          // 一定要定义在使用前，且定义为全局变量
	var pageN103 = true;
	
    var bdWeight_store=new Ext.data.JsonStore({  
    	id:"bdWeight_store",
		url:"queryBdWeight.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN103){
                   msgTip103 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["bid","bLength","bWidth","bHeight","bCd","bXl","bXd","bZl","bZd",
					"bRibthick","bHole","bStructure"]});
	
	var bdWeight_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'增加',iconCls:"add",handler:addBdWgt},
				'-',{xtype:'button',text:'删除',iconCls:"delete",handler:delBdWgt},
				'-',{xtype:'button',text:'编辑',iconCls:"edit",handler:editBdWgt}]
	});
	
	//	var viewPort =new Ext.Viewport();
		var Y2 = (viewPort.getSize().height-47-26.6-30.2-23)*0.5;
		
	var bdWeight_grid=new Ext.grid.GridPanel({
		title:'床身信息列表',	
		height:Y2,
		frame:true,
		autoScroll:true,
	    store:bdWeight_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:bdWeight_columns,
		sm: bdWeight_smodel,
		tbar: bdWeight_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:bdWeight_store,
					pageSize:8, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//-------------------------Upweight.js-------------------------------------------
 		upWeight_store.load({params:{start:0,limit:8},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配到合适结果!</span>',
												buttons:Ext.MessageBox.OK,width:200})
								  		}else{
								  		 	msgTip102.hide();  
										  	pageN102 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}});
//=============load store
				bdWeight_store.load({params:{start:0,limit:8},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配到合适结果!</span>',
												buttons:Ext.MessageBox.OK,width:200})
								  		}else{
								  		 	msgTip103.hide();  
										  	pageN103 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}})	

function addUpWgt(){
				
				
		  var addUpWgt_form = new Ext.form.FormPanel({
			  	border : false,
	 			layout:'column',
	 			width:430,
	 			height:200,
	 			frame:true,
	 			labelWidth:80,
	 			labelAlign:"right",
		  		items:[column101,column102]  															
		  });
		  
		  var addUpWgt_win = new Ext.Window({
				  	modal:true,
					height:245,
		 			width:430,
		 			resizable:false,
		 			draggable:false,
 					title:"<p align='center'>信息添加</p>", 
 					items:	new Ext.Panel({
				    		layout:'column',
				    		frame:true,
				    		height:290,
				    		items:[addUpWgt_form] }),
				    		
				    buttonAlign:'center',
		 	  		  buttons : [{ text:"确定",handler:function(){
		 	  		  		var up1 = Ext.getCmp("up_H").getValue()*10;
							var up2 = Ext.getCmp("up_K").getValue()*10;
							var up3 = Ext.getCmp("up_yt").getValue()*10;
							var up4 = Ext.getCmp("up_sdx").getValue()*10;
							var up5 = Ext.getCmp("up_sdy").getValue()*10;
							var up6 = Ext.getCmp("up_sideThick").getValue()*10;
							var up7 = Ext.getCmp("up_hole").getValue()*10;
							var up8 = Ext.getCmp("up_ribThick").getValue()*10;
							var up9 = Ext.getCmp("up_hN").getValue()*10;
							var up10 = Ext.getCmp("up_sN").getValue()*10;
							var total = up1 +up2 +up3 +up4 +up5 +up6 +up7 +up8 +up9 + up10 ;
		 	  	 
		 	  		  	if(addUpWgt_form.getForm().isValid()!=true ||  total != 10){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">添加的数据不符合要求</span>'); 
 							return;}
 						addUpWgt_form.getForm().submit({
 								url:"addUpwgt.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
										
										upWeight_store.reload(); //刷新store
										msgTip102.hide(); 
									addUpWgt_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addUpWgt_win.close();   	}}]	
	});
	
 		 	 addUpWgt_win.show()	
}
//-----delete删除----------------------------------
function delUpWgt(){
		  var row =upWeight_grid.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>')
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var wid=row[0].get("wid");
								Ext.Ajax.request({
									url:"delUpWgt.do",
									params:{wid:wid},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										} else {
												Ext.Msg.alert("失败",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										}
									}
								})
								upWeight_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
}
//-----edit编辑-----------------------------------
function editUpWgt(){
	
	var editUpWgs_form = new Ext.form.FormPanel({
			  	border : false,
	 			layout:'column',
	 			width:430,
	 			height:200,
	 			frame:true,
	 			labelWidth:80,
	 			labelAlign:"right",
		  		items:[column101,column102]  															
		  });
		  
		  var editUpWgs_win = new Ext.Window({
				  	modal:true,
					height:245,
		 			width:430,
		 			resizable:false,
		 			draggable:false,
 					title:"<p align='center'>信息编辑</p>", 
 					items:	new Ext.Panel({
				    		layout:'column',
				    		frame:true,
				    		height:290,
				    		items:[editUpWgs_form] }),
				    		
				    buttonAlign:'center',
		 	  		  buttons : [{ text:"确定",handler:function(){
		 	  		  		var up1 = Ext.getCmp("up_H").getValue()*10;
							var up2 = Ext.getCmp("up_K").getValue()*10;
							var up3 = Ext.getCmp("up_yt").getValue()*10;
							var up4 = Ext.getCmp("up_sdx").getValue()*10;
							var up5 = Ext.getCmp("up_sdy").getValue()*10;
							var up6 = Ext.getCmp("up_sideThick").getValue()*10;
							var up7 = Ext.getCmp("up_hole").getValue()*10;
							var up8 = Ext.getCmp("up_ribThick").getValue()*10;
							var up9 = Ext.getCmp("up_hN").getValue()*10;
							var up10 = Ext.getCmp("up_sN").getValue()*10;
							var total = up1 +up2 +up3 +up4 +up5 +up6 +up7 +up8 +up9 + up10 ;
							
							up1 = up1/total; up2= up2/total;
							up3 = up3/total;up4 = up4/total;
							up5 = up5/total; up6 =up6/total;
							up7 =up7/total; up8 =up8/total;
							up9 = up9/total;up10 = up10/total;
		  
		  if(editUpWgs_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">修改的数据不符合要求</span>'); 
 							return;}
 					Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
 						if(btn=="yes"){
 						editUpWgs_form.getForm().submit({
 								url:"updateUpWgt.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:240})
										upWeight_store.reload(); //刷新store
										editUpWgs_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 						}})
 				}},
 						   { text:"取消",handler:function(){
 									editUpWgs_win.close();
 																}}]   });
 			
 			var row3 = upWeight_grid.getSelectionModel().getSelections();
 		    var record =upWeight_grid.getSelectionModel().getSelected();
 			if(row3.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		 editUpWgs_win.show(); 
 		  		 editUpWgs_form.getForm().loadRecord(record);
 	 }			
}
//-----------------------------bedWeight-----------------------------------------------------------

	
 		


function delBdWgt(){
		  var row1 =bdWeight_grid.getSelectionModel().getSelections();
					if(row1.length==0){
						Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>')
					}else{
						Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否删除该数据</span>',function(btn){
							if(btn=="yes"){
								var bid=row1[0].get("bid");
								Ext.Ajax.request({
									url:"delBdWgt.do",
									params:{bid:bid},
									callback:function(options,success,response){
										var jsonStr = Ext.util.JSON.decode(response.responseText);
										if(jsonStr.success){
												Ext.Msg.alert("成功",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										} else {
												Ext.Msg.alert("失败",'<span style="font-size:16px;">'+jsonStr.msg+'</span>')
										}
									}
								})
								upWeight_store.remove(row[0]);  //在store中删除数据
						}		
					})	
				}
};

function addBdWgt(){
		
		  var addBdWgt_form = new Ext.form.FormPanel({
			  	border : false,
	 			layout:'column',
	 			width:430,
	 			height:210,
	 			frame:true,
	 			labelWidth:80,
	 			labelAlign:"right",
		  		items:[column103,column104]  															
		  });
		  
		  var addBdWgt_win = new Ext.Window({
				  	modal:true,
					height:265,
		 			width:430,
		 			resizable:false,
		 			draggable:false,
 					title:"<p align='center'>信息添加</p>", 
 					items:	new Ext.Panel({
				    		layout:'column',
				    		frame:true,
				    		height:290,
				    		items:[addBdWgt_form] }),
		    		
				    buttonAlign:'center',
		 	  		  buttons : [{ text:"确定",handler:function(){
		 	  		  		var bd1 = Ext.getCmp("bd_L").getValue()*10;
							var bd2 = Ext.getCmp("bd_K").getValue()*10;
							var bd3 = Ext.getCmp("bd_H").getValue()*10;
							var bd4 = Ext.getCmp("bCd").getValue()*10;
							var bd5 = Ext.getCmp("bXl").getValue()*10;
							var bd6 = Ext.getCmp("bXd").getValue()*10;
							var bd7 = Ext.getCmp("bZl").getValue()*10;
							var bd8 = Ext.getCmp("bZd").getValue()*10;
							var bd9 = Ext.getCmp("bRibthick").getValue()*10;
							var bd10 = Ext.getCmp("bHole").getValue()*10;
							var bd11 = Ext.getCmp("bStructure").getValue()*10;
							var total2 = bd1 +bd2 +bd3 +bd4 +bd5 +bd6 +bd7 +bd8 +bd9 + bd10 +bd11 ;
		 	  	 
							bd1 = bd1/total2; bd3 = bd3/total2;
							bd2 = bd2/total2; bd4 = bd4/total2;
							bd5 = bd5/total2; bd6 = bd6/total2;
							bd7 = bd7/total2; bd8 = bd8/total2;
							bd9 = bd9/total2; bd10 = bd10/total2;
							bd11 = bd11/total2;
		 	  		  	if(addBdWgt_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">添加的数据不符合要求</span>'); 
 							return;}
 						addBdWgt_form.getForm().submit({
 								url:"addBdwgt.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
										
										bdWeight_store.reload(); //刷新store
										msgTip103.hide(); 
									addBdWgt_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 				}},
 						   { text:"取消",handler:function(){
 									addBdWgt_win.close();   	}}]	
	});
	
 		 	 addBdWgt_win.show()	
};

function editBdWgt(){
	
	var editBdWgs_form = new Ext.form.FormPanel({
			  	border : false,
	 			layout:'column',
	 			width:430,
	 			height:210,
	 			frame:true,
	 			labelWidth:80,
	 			labelAlign:"right",
		  		items:[column103,column104]  															
		  });
		  
		  var editBdWgs_win = new Ext.Window({
				  	modal:true,
					height:265,
		 			width:430,
		 			resizable:false,
		 			draggable:false,
 					title:"<p align='center'>信息编辑</p>", 
 					items:	new Ext.Panel({
				    		layout:'column',
				    		frame:true,
				    		height:290,
				    		items:[editBdWgs_form] }),
				    		
				    buttonAlign:'center',
		 	  		  buttons : [{ text:"确定",handler:function(){
		 	  		  		var bd1 = Ext.getCmp("bd_L").getValue()*10;
							var bd2 = Ext.getCmp("bd_K").getValue()*10;
							var bd3 = Ext.getCmp("bd_H").getValue()*10;
							var bd4 = Ext.getCmp("bCd").getValue()*10;
							var bd5 = Ext.getCmp("bXl").getValue()*10;
							var bd6 = Ext.getCmp("bXd").getValue()*10;
							var bd7 = Ext.getCmp("bZl").getValue()*10;
							var bd8 = Ext.getCmp("bZd").getValue()*10;
							var bd9 = Ext.getCmp("bRibthick").getValue()*10;
							var bd10 = Ext.getCmp("bHole").getValue()*10;
							var bd11 = Ext.getCmp("bStructure").getValue()*10;
							var total2 = bd1 +bd2 +bd3 +bd4 +bd5 +bd6 +bd7 +bd8 +bd9 + bd10 +bd11 ;
		  
		  if(editBdWgs_form.getForm().isValid()!=true ||  total != 10){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">修改的数据不符合要求</span>'); 
 							return;}
 				Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否修改该数据</span>',function(btn){
 						if(btn=="yes"){
 						editBdWgs_form.getForm().submit({
 								url:"updateBdWgt.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:240})
										bdWeight_store.reload(); //刷新store
										editBdWgs_win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Success', action.result.msg); }
 						})
 						}})
 				}},
 						   { text:"取消",handler:function(){
 									editBdWgs_win.close();
 																}}]   });
 			
 			var row4 = bdWeight_grid.getSelectionModel().getSelections();
 		    var record2 =bdWeight_grid.getSelectionModel().getSelected();
 			if(row4.length==0){
 		  		Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择一条信息进行操作</span>');}else{
 		  		 editBdWgs_win.show(); 
 		  		 editBdWgs_form.getForm().loadRecord(record2);
 	 }			
}

//----add tabpanel-  判断页面是否已存在----------------------------------------------------------
if( Ext.getCmp("caseMod") != undefined){	
				tabPanel.setActiveTab(Ext.getCmp("caseMod"));
		}else{			
				var casePage = tabPanel.add({
					title:"实例推理管理",
					id:"caseMod",
					layout:"fit",
					closable : false,//允许关闭
					items: case_tab
				}).show();		
				//tabPanel.setActiveTab(toolPage);
		}

if(Ext.getCmp("Weight") != undefined){
		case_tab.setActiveTab(Ext.getCmp("Weight"));
				return;}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"权重管理",
		iconCls:'aboutUs',
		id:"upWeight",
		closable : true,//允许关闭
		items:[upWeight_grid,bdWeight_grid]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}