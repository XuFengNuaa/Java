function workbed_param(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";

		var xL = Ext.util.Cookies.get("xL")*1;
		var xD = Ext.util.Cookies.get("xD")*1;
		var zL = Ext.util.Cookies.get("zL")*1;
		var zD = Ext.util.Cookies.get("zD")*1;
		var x_length = {xtype:'numberfield',id:"x_length",fieldLabel:"线轨X长度  ",value:xL, name:'x_length',unitText:"mm",width:80,readOnly:true};
		var x_distance = {xtype:'numberfield',id:"x_distance",fieldLabel:"线轨X间距  ",value:xD, name:'x_distance',unitText:"mm",width:80,readOnly:true};
		var z_length = {xtype:'numberfield',id:"z_length",fieldLabel:"线轨Z长度  ",value:zL, name:'z_length',unitText:"mm",width:80,readOnly:true};
		var z_distance = {xtype:'numberfield',id:"z_distance",fieldLabel:"线轨Z间距  ",value:zD,name:'z_distance',unitText:"mm",width:80,readOnly:true};
		var b_hole = {xtype:'numberfield',id:"b_hole",fieldLabel:"减重孔径  ",name:'b_hole',unitText:"mm",width:80,allowBlank:false};
		var b_ribthick = {xtype:'numberfield',id:"b_ribthick",fieldLabel:"板筋厚度  ",unitText:"mm",name:'b_ribthick',width:80,allowBlank:false};
		var wp_h = {xtype:'numberfield',id:"wp_h",fieldLabel:"转台安装高度  ",unitText:"mm",name:'wp_h',width:80,allowBlank:false};
		
		var bd_structure = new Ext.form.ComboBox({
			    typeAhead: true,
			    allowBlank:false,
			    triggerAction: 'all',
			    id:"bd_structure",
			    value:1,
			    fieldLabel:"结构特征  ",
			    width:110,
			    editable:false,
			    mode: 'local',
			    selectOnFocus:true,
			    store: new Ext.data.ArrayStore({
			        id: "structure_combo",
			        fields: [ 'bedId','bedText'],
			        data: [["1", 'T型大跨距结构'],
			        		["2", 'T型中央排屑结构']]
			    }),
			    valueField: 'bedId',
			    displayField: 'bedText'
			   // emptyText:'---请选择---'
			    });
			    
		var workbed_output={xtype:"button",text:"输出",handler:reasonBd};
		var workbed_next={xtype:"button",text:"下一步",handler:function(){
						var row8 =workbed_grid.getSelectionModel().getSelections();
								
							if(row8.length !=0){
							
								Ext.util.Cookies.set("bedid",row8[0].get("bedid"));  //记录选中的id
								
								Ext.util.Cookies.set("beddaihao",row8[0].get("beddaihao"));
		
									result_param();
						}else{
									Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择合适的型号！</span>')	
								}
									
		}};
		
		var workbed_column81 = {columnWidth: .33,layout: 'form',items: [x_length]};
		var workbed_column82 = {columnWidth: .33,layout: 'form',items: [x_distance]};
 		var workbed_column83 = {columnWidth: .33,layout: 'form',items: [z_length]};
 		var workbed_column84 = {columnWidth: .33,layout: 'form',items: [z_distance]};
 		var workbed_column85 = {columnWidth: .33,layout: 'form',items: [b_hole]};
 		var workbed_column86 = {columnWidth: .33,layout: 'form',items: [b_ribthick]};
 		var workbed_column87 = {columnWidth: .4,layout: 'form',items: [wp_h]};
		var workbed_column88 = {columnWidth: .4,layout: 'form',items: [bd_structure]};
		
		var workbed_param = new Ext.FormPanel({
			title:'床身参数',
			height:280,
			labelAlign: 'right',
			labelWidth: 95,
			columnWidth: .45,
			layout:'form',
			frame:true,
			items:[{layout: 'column',bodyStyle:'padding:20px 0px 15px 0px',items:[workbed_column81,workbed_column82,workbed_column83]},
					{layout: 'column',bodyStyle:'padding:15px 0px 15px 0px',items:[workbed_column84,workbed_column85,workbed_column86]},
					{layout: 'column',bodyStyle:'padding:15px 0px 15px 0px',items:[workbed_column88,workbed_column87]}],
			buttonAlign : 'center',
		 	buttons : [workbed_output,workbed_next]
	});
	var bedPanel = new Ext.Panel({
						//layout:'column',
						columnWidth: .25,
						frame:true,
						bodyStyle:'padding:0px 5px 0px 10px',
						html:'<img src="resource/picture/workbed.png"></img>'
				 })
//-------------结果表格-----------------------------------------------------
	var workbed_smodel =new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var workbed_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[workbed_smodel,
			//排序开启，正序和倒序
		//	{header: "ID", dataIndex : "bedid",align : 'center',width:30,sortable:true },//排序开启，正序和倒序
			{header:'型号',dataIndex:'beddaihao',align:'center',width:118,sortable: true},
			{header:'结构',dataIndex:'structure',width:128,align:'center'},
			{header:'材料',dataIndex:'material',width:100,align:'center'},
			{header:'计算结果',dataIndex:'showSimi',width:100,align:'center'},
			{header:'长度(L)',dataIndex:'length',width:80,align:'center'},
			{header:'宽度(K)',dataIndex:'width',width:80,align:'center'},
			{header:'高度(H)',dataIndex:'height',width:80,align:'center'},
			{header:'X行程(mm)',dataIndex:'xroute',width:80,align:'center'},
			{header:'Y行程(mm)',dataIndex:'yroute',width:80,align:'center'},
			{header:'Z行程(mm)',dataIndex:'zroute',width:110,align:'center'},
			{header:'Z安装间距(Z_D)',dataIndex:'zDistance',width:105,align:'center'},
			{header:'X安装间距(X_D)',dataIndex:'xDistance',width:105,align:'center'},
			{header:'槽宽(C_D)',dataIndex:'caokuan',width:115,align:'center'},
			{header:'槽深',dataIndex:'caoshen',width:100,align:'center'},
			
			{header:'槽长',dataIndex:'caolength',width:80,align:'center'},
			{header:'减重孔径',dataIndex:'holed',width:80,align:'center'},
			{header:'板筋分布',dataIndex:'rib',width:80,align:'center'},
			{header:'上宽侧横筋数',dataIndex:'kuanheng',width:80,align:'center'},
			{header:'上宽侧竖筋数',dataIndex:'kuanshu',width:80,align:'center'},
			{header:'下窄侧横筋数',dataIndex:'zhaiheng',width:80,align:'center'},
			{header:'下窄侧竖筋数',dataIndex:'zhaishu',width:80,align:'center'},
			{header:'板筋厚度(mm)',dataIndex:'ribthick',width:80,align:'center'},
			{header:'排屑器',dataIndex:'chip',width:80,align:'center'}]});
	
	var msgTip82;          // 一定要定义在使用前，且定义为全局变量
	var pageN82 = true;
	
    var workbed_store=new Ext.data.JsonStore({  
    	id:"workbed_store",
		url:"reasonBd.do",
		root:"extend",   //json数组请求头
   		totalProperty: "resultSize" , //后台总的记录数
   		listeners:{
             beforeload:function(){
             	if(pageN82){
                   msgTip82 = Ext.MessageBox.show({
                   title:'提示',
                   width : 220,
                   msg:'<span style="font-size:16px;">页面统计信息中,请稍后...</span>'
                });}
       }
   },
		fields: ["bedid","beddaihao","structure","material","showSimi","length","width","height","xroute",
				"yroute","zroute","zDistance","xDistance","caoshen","caokuan","caolength","holed","rib",
				"kuanheng","kuanshu","zhaiheng","zhaishu","ribthick","chip","course"]});
	
	var workbed_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'查看图片',iconCls:"view",handler:function(){
										
					var win8 = new Ext.Window({
							//	modal:true,
							    width:475,
							    height:437,
							    plain: true,
							    resizable:false,
							    title:"<p align='center'>查看图片</p>",
								bodyBorder:true,
							    items:[
							    	new Ext.Panel({
							    		//layout:'column',
							    		frame:true,
							    		html:'<img src="resource/picture/7workbed.png"></img>'
							    })],
							    buttonAlign:'center',
							    buttons: [
											{
											text: '确定',
											Align:"center",
											handler:function(){
												win8.close();
											}}]
							});
							
							if(!win8.isVisible()){
						  			 win8.show();//修改窗口显示
						  			 win8.center();
						  		}
								
				}}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23-280);
	var workbed_grid=new Ext.grid.GridPanel({
		title:'查询信息列表',	
		height:Y,
		frame:true,
		columnWidth: .8,
		autoScroll:true,
	    store:workbed_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:workbed_columns,
		sm: workbed_smodel,
		tbar: workbed_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:workbed_store,
					pageSize:7, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
//----------------------bdweight界面--------------------------------------------
		var bd_L1 = {xtype:'numberfield',id:"bd_L",fieldLabel:"床身长度  ", name:'bLength',width:80,allowBlank:false};
		var bd_K2 = {xtype:'numberfield',id:"bd_K",fieldLabel:"床身宽度  ", name:'bWidth',width:80,allowBlank:false};
		var bd_H3 = {xtype:'numberfield',id:"bd_H",fieldLabel:"床身高度  ", name:'bHeight',width:80,allowBlank:false};
		var bCd4 = {xtype:'numberfield',id:"bCd",fieldLabel:"槽宽  ",name:'bCd',width:80,allowBlank:false};
		var bXl5 = {xtype:'numberfield',id:"bXl",fieldLabel:"线轨X长度  ",name:'bXl',width:80,allowBlank:false};
		var bXd6 = {xtype:'numberfield',id:"bXd",fieldLabel:"线轨X间距  ",name:'bXd',width:80,allowBlank:false};
		var bZl7 = {xtype:'numberfield',id:"bZl",fieldLabel:"线轨Z长度",name:'bZl',width:80,allowBlank:false};
		var bZd8 = {xtype:'numberfield',id:"bZd",fieldLabel:"线轨Z间距  ",name:'bZd',width:80,allowBlank:false};
		var bRibthick9 = {xtype:'numberfield',id:"bRibthick",fieldLabel:"板筋厚度  ",name:'bRibthick',width:80,allowBlank:false};
		var bHole10 = {xtype:'numberfield',id:"bHole",fieldLabel:"减重孔  ",name:'bHole',width:80,allowBlank:false};
		var bStructure11 = {xtype:'numberfield',id:"bStructure",fieldLabel:"结构特征  ",name:'bStructure',width:80,allowBlank:false};	
		
					    
		var bdQuanzhong= new Ext.form.FormPanel({
				title:'属性权重',
				layout:'form',
				frame:'true',
				columnWidth: .3,
				labelAlign: 'right',
				height:330,
				buttonAlign : 'center',
				reader: new Ext.data.JsonReader({root:'extend'},
						[{name:'bid',mapping:'bid'},
						 {name:'bLength',mapping:'bLength'},				 
						 {name:'bWidth',mapping:'bWidth'},
						 {name:'bHeight',mapping:'bHeight'},
						 {name:'bCd',mapping:'bCd'},
						 {name:'bXl',mapping:'bXl'},
						 {name:'bXd',mapping:'bXd'},
						 {name:'bZl',mapping:'bZl'},				 
						 {name:'bZd',mapping:'bZd'},
						 {name:'bRibthick',mapping:'bRibthick'},
						 {name:'bHole',mapping:'bHole'},
						 {name:'bStructure',mapping:'bStructure'}]),
				items:[
					{layout: 'column',bodyStyle:'padding:15px 0px 0px 0px',items:[{xtype:"numberfield",name:"bid",hidden:true},{layout:'form',columnWidth:0.5,items: bd_L1},{layout:'form',columnWidth:0.5,items: bd_K2}]},
					{layout: 'column',bodyStyle:'padding:5px 0px 0px 0px',items:[{layout:'form',columnWidth:0.5,items: bd_H3},{layout:'form',columnWidth:0.5,items: bCd4}]},
					{layout: 'column',bodyStyle:'padding:5px 0px 0px 0px',items:[{layout:'form',columnWidth:0.5,items: bXl5},{layout:'form',columnWidth:0.5,items: bXd6}]},
					{layout: 'column',bodyStyle:'padding:5px 0px 0px 0px',items:[{layout:'form',columnWidth:0.5,items: bZl7},{layout:'form',columnWidth:0.5,items: bZd8}]},
					{layout: 'column',bodyStyle:'padding:5px 0px 0px 0px',items:[{layout:'form',columnWidth:0.5,items: bRibthick9},{layout:'form',columnWidth:0.5,items: bHole10},
					{layout:'form',bodyStyle:'padding:5px 0px 0px 0px',columnWidth:0.5,items: bStructure11}]}
					
				]
			});
			
			bdQuanzhong.getForm().load({
								     url: 'loadBdwgt.do',
								     waitMsg:"正在加载，请稍候...",
								   	 params: {
								        	dfid : 1
								   	 },
									 success:function(form,action){
									 	
										 	},
								     failure: function(form, action) {
								     	
								        	Ext.Msg.alert("Load failed", "数据加载失败，请稍后再试！");
								    }
					});
//---------------------------------------------------------------------
function reasonBd(){
			var wphput = Ext.getCmp("wp_h").getValue();
			var wphchoo = Ext.util.Cookies.get("wp_h")*1;
			var xiadi = Ext.util.Cookies.get("upXiadi")*1;
			var wpd = Ext.util.Cookies.get("wpMaxd")*1; 
			var bhole =Ext.getCmp("b_hole").getValue();
			var bribThick =Ext.getCmp("b_ribthick").getValue();
			var structure =Ext.getCmp("bd_structure").getValue();
			
			var qformValue = bdQuanzhong.getForm().getValues();  //获取form中的所有值
		//	alert(qformValue['height']);
			var bl = qformValue['bLength']*100;  var bw = qformValue['bWidth']*100;
			var bh = qformValue['bHeight']*100;  var bc = qformValue['bCd']*100;
			var bxl = qformValue['bXl']*100;  var bxd = qformValue['bXd']*100;
			var bzl = qformValue['bZl']*100;  var bzd = qformValue['bZd']*100;
			var bt = qformValue['bRibthick']*100;  var bho = qformValue['bHole']*100;
			var bs = qformValue['bStructure']*100;
			
			var total8 = bl + bw +bh +bc + bxl + bxd + bzl + bzd + bt + bho + bs ;
			
					bl = bl/total8; bw = bw/total8;
			        bh = bh/total8;bc = bc/total8;
					bxl = bxl/total8;bxd = bxd/total8;
					bzl = bzl/total8;bzd = bzd/total8;
					bt = bt/total8;bho = bho/total8;
					bs = bs/total8;
		//	alert(bl);
			
		if(workbed_param.getForm().isValid()!=true){
 				Ext.Msg.alert("提示","输入的数据不符合要求，请重新输入！");  return;}
 		
 	//	workbed_store.load({params:{xL:xL, wphput:wphput, zL:zL,wphchoo:wphchoo,xiadi:xiadi,wpd:wpd,
 		workbed_store.load({params:{wphput:wphput,wphchoo:wphchoo,xiadi:xiadi,wpd:wpd,
 										bhole:bhole,bribThick:bribThick,structure:structure,bid:1,
 					bl:bl , bw:bw,bh:bh,bc:bc,bxl:bxl,bxd:bxd,bzl:bzl,bzd:bzd,bt:bt,bho:bho,bs:bs		
 										},
							callback: function(r,options,success){
								  if(success){
										 if(r.length==0){
								  			Ext.MessageBox.show({
												title:"警告",msg:'<span style="font-size:16px;">未匹配合适的结果，请重新输入！</span>',
												buttons:Ext.MessageBox.OK,width:245})
								  		}else{
								  		 	msgTip82.hide();  
										  	pageN82 = false; }
							 }else{Ext.MessageBox.show({
								title:"警告",msg:'<span style="font-size:16px;">数据加载失败，请稍后重试！</span>',
								buttons:Ext.MessageBox.OK,width:260})}
				}})	
};

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

if(Ext.getCmp("workbed_param") != undefined){
		case_tab.remove(Ext.getCmp("workbed_param"));}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"床身",
		iconCls:'aboutUs',
		id:"workbed_param",
		closable : true,//允许关闭
		items:[{layout: 'column',items:[workbed_param,bdQuanzhong,bedPanel]},workbed_grid]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}