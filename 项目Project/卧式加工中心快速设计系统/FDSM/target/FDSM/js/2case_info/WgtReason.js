function wgtReason(){
		
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";

//-------------结果表格-----------------------------------------------------
	var upWgt_smodel =new Ext.grid.CheckboxSelectionModel();
	var upWgt_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[upWgt_smodel,
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			//排序开启，正序和倒序
			{header: "专家姓名", dataIndex : "name",align : 'center',width:80,sortable:true },//排序开启，正序和倒序
			{header:'评分时间',dataIndex:'dfDate',align:'center',width:118,sortable: true}]});
	
	var msgTip102;          // 一定要定义在使用前，且定义为全局变量
	var pageN102 = true;
	
    var upWgt_store=new Ext.data.JsonStore({  
    	id:"upWgt_store",
		url:"queryupDfResult.do",
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
		fields: ["zjId","name","dfDate"]});
	
	var upWgt_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'权重值计算',iconCls:"reason",handler:reasonUpWgt},
				'-',{xtype:'button',text:'查看详情',iconCls:"view",handler:viewUpWgt}]
	});
	
		var viewPort =new Ext.Viewport();
		var Y = (viewPort.getSize().height-47-26.6-30.2-23)*0.5;
		
	var upWgt_grid=new Ext.grid.GridPanel({
		title:'可选择的立柱专家评分',	
		height:Y,
		frame:true,
		columnWidth: .5,
		autoScroll:true,
	    store:upWgt_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:upWgt_columns,
		sm: upWgt_smodel,
		tbar: upWgt_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:upWgt_store,
					pageSize: 8, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
	
	// upWgt_store.load-------------------------------------------
	upWgt_store.load({params:{start:0,limit:8},
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
   
//------计算方法---------------------------------------	
function reasonUpWgt(){
			var up_row = upWgt_grid.getSelectionModel().getSelections();
			if(up_row.length <2){
					Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择两个结果！</span>')	
			}else{
						var uid = up_row[0].get("zjId");
						for(var i=1;i<up_row.length;i++){   // 传所选中的id
								uid= uid+"-"+ up_row[i].get("zjId");
								}
								
					up_quanzhong.getForm().load({
								     url: 'jsUpDf.do',
								     waitMsg:"正在加载，请稍候...",
								   	 params: {
								        	uid : uid
								   	 },
									 success:function(form,action){
										 	},
								     failure: function(form, action) {
								        	Ext.Msg.alert("Load failed", "数据加载失败，请稍后再试！");
								    }
					});
						
			}
			
			
}
function viewUpWgt(){
	
	var rowBed =upWgt_grid.getSelectionModel().getSelections();
					
			if(rowBed.length >1 || rowBed.length==0){
							Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择一条信息查看！</span>');		
							return;	}
							
		var w00={xtype:'label',text:'属性',width:15};
var w10={xtype:'label',text:'立柱高度',width:15};
var w11={xtype:'label',text:'立柱宽度',width:15};
var w12={xtype:'label',text:'Y轴行程',width:15};
var w13={xtype:'label',text:'主轴宽度',width:15};
var w14={xtype:'label',text:'主轴高度',width:15};
var w15={xtype:'label',text:'立柱壁厚',width:15};
var w16={xtype:'label',text:'减重孔',width:15};
var w17={xtype:'label',text:'板筋厚度',width:15};
var w18={xtype:'label',text:'横板筋数量',width:15};
var w19={xtype:'label',text:'竖板筋数量',width:15};
var juzhen={xtype:'numberfield',id:"11",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false};
var distxw = {xtype:'numberfield',width:45,disabled: true};
var value2 = {xtype:'numberfield',width:45,value:1, readOnly:true}


var dafen2=new Ext.form.FormPanel({
	frame:true,
//	height:Y/1.54,
	width:770,
	height:360,
	bodyStyle:"padding-left:10px;padding-top:10px",
	//width:700,
	layout:'column',
	buttonAlign : 'center',
	labelWidth:1,
	reader: new Ext.data.JsonReader({root:'extend'},
			[{name:'p11',mapping:'p11'},{name:'p12',mapping:'p12'},{name:'p13',mapping:'p13'},
			{name:'p14',mapping:'p14'},{name:'p15',mapping:'p15'},{name:'p16',mapping:'p16'},
			{name:'p17',mapping:'p17'},{name:'p18',mapping:'p18'},{name:'p19',mapping:'p19'},
			{name:'p22',mapping:'p22'},{name:'p23',mapping:'p23'},{name:'p24',mapping:'p24'},
			{name:'p25',mapping:'p25'},{name:'p26',mapping:'p26'},{name:'p27',mapping:'p27'},
			{name:'p28',mapping:'p28'},{name:'p29',mapping:'p29'},{name:'p32',mapping:'p32'},
			{name:'p33',mapping:'p33'},{name:'p34',mapping:'p34'},{name:'p35',mapping:'p35'},
			{name:'p36',mapping:'p36'},{name:'p37',mapping:'p37'},{name:'p38',mapping:'p38'},
			{name:'p42',mapping:'p42'},{name:'p43',mapping:'p43'},{name:'p44',mapping:'p44'},
			{name:'p45',mapping:'p45'},{name:'p46',mapping:'p46'},{name:'p47',mapping:'p47'},
			{name:'p52',mapping:'p52'},{name:'p53',mapping:'p53'},{name:'p54',mapping:'p54'},
			{name:'p55',mapping:'p55'},{name:'p56',mapping:'p56'},{name:'p62',mapping:'p62'},
			{name:'p63',mapping:'p63'},{name:'p64',mapping:'p64'},
			{name:'p65',mapping:'p65'},{name:'p72',mapping:'p72'},{name:'p73',mapping:'p73'},
			{name:'p74',mapping:'p74'},{name:'p82',mapping:'p82'},{name:'p83',mapping:'p83'},
			{name:'p92',mapping:'p92'}]),
	items:[{layout:'form',columnWidth:0.09,items: w00},{layout:'form',columnWidth:0.09,items: w10},{layout:'form',columnWidth:0.09,items: w11},
		{layout:'form',columnWidth:0.09,items: w12},
		 {layout:'form',columnWidth:0.09,items: w13},{layout:'form',columnWidth:0.09,items: w14},{layout:'form',columnWidth:0.09,items: w15},
		 {layout:'form',columnWidth:0.09,items: w16},{layout:'form',columnWidth:0.09,items: w17},{layout:'form',columnWidth:0.09,items: w18},{layout:'form',columnWidth:0.09,items: w19},
		 
		 {layout:'form',columnWidth:0.09,items: w10},{layout:'form',columnWidth:0.09,items: value2},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p11',id:"p11",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false,allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p12',id:"p12",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false,allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p13',id:"p13",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p14',id:"p14",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p15',id:"p15",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p16',id:"p16",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p17',id:"p17",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p18',id:"p18",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p19',id:"p19",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: w11},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: value2},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p22',id:"p22",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p23',id:"p23",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p24',id:"p24",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p25',id:"p25",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p26',id:"p26",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p27',id:"p27",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p28',id:"p28",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p29',id:"p29",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: w12},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: value2},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p32',id:"p32",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p33',id:"p33",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p34',id:"p34",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p35',id:"p35",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p36',id:"p36",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p37',id:"p37",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p38',id:"p38",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: w13},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: value2},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p42',id:"p42",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p43',id:"p43",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p44',id:"p44",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p45',id:"p45",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p46',id:"p46",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items: [{xtype:'numberfield',name:'p47',id:"p47",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: w14},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: value2},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p52',id:"p52",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p53',id:"p53",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p54',id:"p54",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p55',id:"p55",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p56',id:"p56",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: w15},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: value2},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p62',id:"p62",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p63',id:"p63",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p64',id:"p64",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p65',id:"p65",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: w16},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: value2},
		 {layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p72',id:"p72",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p73',id:"p73",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p74',id:"p74",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 
		 {layout:'form',columnWidth:0.09,items: w17},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: value2},
		{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p82',id:"p82",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p83',id:"p83",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.09,items: w18},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: value2},{layout:'form',columnWidth:0.09,items:[{xtype:'numberfield',name:'p92',id:"p92",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 
		 {layout:'form',columnWidth:0.09,items: w19},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: distxw},
		 {layout:'form',columnWidth:0.09,items: distxw},{layout:'form',columnWidth:0.09,items: value2}]
});

		var winBWgt = new Ext.Window({
				modal:true,
				width:780,
				height:360,
				plain: true,
				resizable:false,
				title:"<p align='center'>查看详情</p>",
				bodyBorder:true,
				items:[
					new Ext.Panel({
			    		//layout:'column',
			    		frame:true,
			    	//	height:350,
			    		items:[dafen2]})]
		})
		
		if(!winBWgt.isVisible()){
  			 winBWgt.show();//修改窗口显示
  			 winBWgt.center();
  		}

  		
  		dafen2.getForm().load({
								     url: 'selectUpWgt.do',
								     waitMsg:"正在加载，请稍候...",
								   	 params: {
								        	dfid : rowBed[0].get("zjId")
								   	 },
									 success:function(form,action){
									 	
										 	},
								     failure: function(form, action) {
								     	
								        	Ext.Msg.alert("Load failed", "数据加载失败，请稍后再试！");
								    }
					});
}
//----------upright右边 计算界面-----------------------
		var H1 = {xtype:'numberfield',id:"H1",fieldLabel:"立柱高度  ", name:'height',width:100,allowBlank:false};
		var K2 = {xtype:'numberfield',id:"K2",fieldLabel:"立柱宽度  ", name:'kuang',width:100,allowBlank:false};
		var yt3= {xtype:'numberfield',id:"yt3",fieldLabel:"Y轴行程  ", name:'yroute',width:100,allowBlank:false};
		var sdx4 = {xtype:'numberfield',id:"sdx4",fieldLabel:"主轴宽度  ",name:'sdwidth',width:100,allowBlank:false};
		var sdy5 = {xtype:'numberfield',id:"sdy5",fieldLabel:"主轴高度  ",name:'sdheight',width:100,allowBlank:false};
		var sideThick6 = {xtype:'numberfield',id:"sideThick6",fieldLabel:"立柱壁厚  ",name:'sidethick',width:100,allowBlank:false};
		var hole7 = {xtype:'numberfield',id:"hole7",fieldLabel:"减重孔  ",name:'hole',width:100,allowBlank:false};
		var ribThick8 = {xtype:'numberfield',id:"ribThick8",fieldLabel:"板筋厚度  ",name:'ribthick',width:100,allowBlank:false};
		var hN9 = {xtype:'numberfield',id:"hN9",fieldLabel:"横板筋数量  ",name:'hnumber',width:100,allowBlank:false};
		var sN10 = {xtype:'numberfield',id:"sN10",fieldLabel:"竖板筋数量  ",name:'snumber',width:100,allowBlank:false};

		var up_quanzhong= new Ext.form.FormPanel({
				title:'立柱属性权重计算结果',
				layout:'form',
				frame:'true',
				columnWidth: .5,
				labelAlign: 'right',
				height:Y+150,
				buttonAlign : 'center',
				reader: new Ext.data.JsonReader({root:'extend'},
						[{name:'wid',mapping:'wid'},
						 {name:'height',mapping:'height'},				 
						 {name:'kuang',mapping:'kuang'},
						 {name:'yroute',mapping:'yroute'},
						 {name:'sdwidth',mapping:'sdwidth'},
						 {name:'sdheight',mapping:'sdheight'},
						 {name:'sidethick',mapping:'sidethick'},
						 {name:'hole',mapping:'hole'},				 
						 {name:'ribthick',mapping:'ribthick'},
						 {name:'hnumber',mapping:'hnumber'},
						 {name:'snumber',mapping:'snumber'}]),
				items:[
					{layout: 'column',bodyStyle:'padding:20px 10px 0px 50px',items:[{layout:'form',columnWidth:0.4,items: H1},{layout:'form',columnWidth:0.4,items: K2}]},
					{layout: 'column',bodyStyle:'padding:5px 10px 0px 50px',items:[{layout:'form',columnWidth:0.4,items: yt3},{layout:'form',columnWidth:0.4,items: sdx4}]},
					{layout: 'column',bodyStyle:'padding:5px 10px 0px 50px',items:[{layout:'form',columnWidth:0.4,items: sdy5},{layout:'form',columnWidth:0.4,items: sideThick6}]},
					{layout: 'column',bodyStyle:'padding:5px 10px 0px 50px',items:[{layout:'form',columnWidth:0.4,items: hole7},{layout:'form',columnWidth:0.4,items: ribThick8}]},
					{layout: 'column',bodyStyle:'padding:5px 10px 0px 50px',items:[{layout:'form',columnWidth:0.4,items: hN9},{layout:'form',columnWidth:0.4,items: sN10}]}
				],
				buttons:[{text:'保存',handler:function(){  // 更新进权重表中
					if(up_quanzhong.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">添加的数据不符合要求</span>'); 
 							return;}
					Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否保存该数据</span>',function(btn){
						if(btn=="yes"){
							up_quanzhong.getForm().submit({
 								url:"updateUpWgt.do",
 								params: {
								        	wid : 1
								   	 },
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Failure', action.result.msg); }
 						})
				}})
				}},{text:'重置',handler:function(){up_quanzhong.form.reset()}}]
			});
			
//-------------bed结果表格-----------------------------------------------------
	var bedWgt_smodel =new Ext.grid.CheckboxSelectionModel();
	var bedWgt_columns=new Ext.grid.ColumnModel({
		 defaults: {
            width: 140 },
		 columns:[bedWgt_smodel,
			new Ext.grid.RowNumberer({header:'序号',width:40,align:'center'}),
			//排序开启，正序和倒序
			{header: "专家姓名", dataIndex : "name",align : 'center',width:80,sortable:true },//排序开启，正序和倒序
			{header:'评分时间',dataIndex:'dfDate',align:'center',width:118,sortable: true}]});
	
	var msgTip103;          // 一定要定义在使用前，且定义为全局变量
	var pageN103 = true;
	
    var bedWgt_store=new Ext.data.JsonStore({  
    	id:"bedWgt_store",
		url:"querybedDfResult.do",
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
		fields: ["zjId","name","dfDate"]});
	
	var bedWgt_tbar=new Ext.Toolbar({
		items:[
				'-',{xtype:'button',text:'权重值计算',iconCls:"reason",handler:reasonBedWgt},
				'-',{xtype:'button',text:'查看详情',iconCls:"view",handler:viewBedWgt}]
	});
	
		
	var bedWgt_grid=new Ext.grid.GridPanel({
		title:'可选择的床身专家评分',	
		height:Y,
		frame:true,
		columnWidth: .5,
		autoScroll:true,
	    store:bedWgt_store,
		viewConfig:{
	    	scrollOffset: 0//去除最右边空白
	    	},
		colModel:bedWgt_columns,
		sm: bedWgt_smodel,
		tbar: bedWgt_tbar,
		bbar: new Ext.PagingToolbar({ 
					store:bedWgt_store,
					pageSize:8, //每页显示多少记录,和下面的limit参数保持一致
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条记录',
					emptyMsg: "没有记录",
					displayInfo: true
		})
	});
	
	// bedWgt_store.load-------------------------------------------
	bedWgt_store.load({params:{start:0,limit:8},
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
				}});

function reasonBedWgt(){
	
		var bed_row = bedWgt_grid.getSelectionModel().getSelections();
			if(bed_row.length <2){
					Ext.Msg.alert("警告",'<span style="font-size:15px;">至少选择两个结果！</span>')	
			}else{
						var uid = bed_row[0].get("zjId");
						for(var i=1;i<bed_row.length;i++){   // 传所选中的id
								uid= uid+"-"+ bed_row[i].get("zjId");
								}
								
					bd_Quanzhong.getForm().load({
								     url: 'jsBedDf.do',
								     waitMsg:"正在加载，请稍候...",
								   	 params: {
								        	did : uid
								   	 },
									 success:function(form,action){
										 	},
								     failure: function(form, action) {
								        	Ext.Msg.alert("Load failed", "数据加载失败，请稍后再试！");
								    }
					});
						
			}
			
			
}
function viewBedWgt(){
	
	var rowBed =bedWgt_grid.getSelectionModel().getSelections();
					
			if(rowBed.length >1 || rowBed.length==0){
							Ext.Msg.alert("警告",'<span style="font-size:15px;">请选择一条信息查看！</span>');		
							return;	}

var bw00={xtype:'label',text:'属性',width:15};
var bw10={xtype:'label',text:'床身长度',width:15};
var bw11={xtype:'label',text:'床身宽度',width:15};
var bw12={xtype:'label',text:'床身高度',width:15};
var bw13={xtype:'label',text:'排屑槽宽',width:15};
var bw14={xtype:'label',text:'X轨长度',width:15};
var bw15={xtype:'label',text:'X轨间距',width:15};
var bw16={xtype:'label',text:'Z轨长度',width:15};
var bw17={xtype:'label',text:'Z轨间距',width:15};
var bw18={xtype:'label',text:'板筋厚度',width:15};
var bw19={xtype:'label',text:'减重孔径',width:15};
var bw20={xtype:'label',text:'结构特征',width:15};
//var bed_juzhen={xtype:'numberfield',id:"11",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false};
var bed_distxw = {xtype:'numberfield',width:45,disabled: true};
var bed_value2 = {xtype:'numberfield',width:45,value:1, readOnly:true}

var bed_dafen2=new Ext.form.FormPanel({
	frame:true,
//	height:420,
	bodyStyle:"padding-left:10px;padding-top:10px",
//	width:770,
	layout:'column',
	buttonAlign : 'center',
	labelWidth:1,
	reader: new Ext.data.JsonReader({root:'extend'},
		[{name:'d11',mapping:'d11'},{name:'d12',mapping:'d12'},{name:'d13',mapping:'d13'},
		{name:'d14',mapping:'d14'},{name:'d15',mapping:'d15'},{name:'d16',mapping:'d16'},
 		{name:'d17',mapping:'d17'}, {name:'d18',mapping:'d18'}, {name:'d19',mapping:'d19'},
        {name:'d20',mapping:'d20'}, {name:'d22',mapping:'d22'},{name:'d23',mapping:'d23'},
        {name:'d24',mapping:'d24'},
		{name:'d25',mapping:'d25'},{name:'d26',mapping:'d26'}, {name:'d27',mapping:'d27'},
		{name:'d28',mapping:'d28'},{name:'d29',mapping:'d29'},{name:'d30',mapping:'d30'},
		{name:'d32',mapping:'d32'},{name:'d33',mapping:'d33'},{name:'d34',mapping:'d34'},
		{name:'d35',mapping:'d35'},{name:'d36',mapping:'d36'},{name:'d37',mapping:'d37'},
		{name:'d38',mapping:'d38'},{name:'d39',mapping:'d39'},{name:'d42',mapping:'d42'},{name:'d43',mapping:'d43'},
		{name:'d44',mapping:'d44'},{name:'d45',mapping:'d45'},{name:'d46',mapping:'d46'},
		 {name:'d47',mapping:'d47'},{name:'d48',mapping:'d48'},{name:'d52',mapping:'d52'},
		{name:'d53',mapping:'d53'}, {name:'d54',mapping:'d54'},{name:'d55',mapping:'d55'},
		{name:'d56',mapping:'d56'}, {name:'d57',mapping:'d57'},{name:'d62',mapping:'d62'},
		 {name:'d63',mapping:'d63'},{name:'d64',mapping:'d64'},{name:'d65',mapping:'d65'},
		{name:'d66',mapping:'d66'}, {name:'d72',mapping:'d72'},{name:'d73',mapping:'d73'},
		{name:'d74',mapping:'d74'},{name:'d75',mapping:'d75'},{name:'d82',mapping:'d82'},
		 {name:'d83',mapping:'d83'},{name:'d84',mapping:'d84'},{name:'d92',mapping:'d92'},
		{name:'d93',mapping:'d93'},{name:'d102',mapping:'d102'}]),
	items:[{layout:'form',columnWidth:0.08,items: bw00},{layout:'form',columnWidth:0.08,items: bw10},{layout:'form',columnWidth:0.08,items: bw11},
		{layout:'form',columnWidth:0.08,items: bw12},
		 {layout:'form',columnWidth:0.08,items: bw13},{layout:'form',columnWidth:0.08,items: bw14},{layout:'form',columnWidth:0.08,items: bw15},
		 {layout:'form',columnWidth:0.08,items: bw16},{layout:'form',columnWidth:0.08,items: bw17},{layout:'form',columnWidth:0.08,items: bw18},
		 {layout:'form',columnWidth:0.08,items: bw19},{layout:'form',columnWidth:0.08,items: bw20},
		 
		 {layout:'form',columnWidth:0.08,items: bw10},{layout:'form',columnWidth:0.08,items: bed_value2},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d11",id:"d11",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d12",id:"d12",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false,allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d13",id:"d13",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d14",id:"d14",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d15",id:"d15",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d16",id:"d16",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d17",id:"d17",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d18",id:"d18",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d19",id:"d19",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d20",id:"d20",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw11},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_value2},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d22",id:"d22",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d23",id:"d23",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d24",id:"d24",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d25",id:"d25",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d26",id:"d26",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d27",id:"d27",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d28",id:"d28",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d29",id:"d29",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d30",id:"d30",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw12},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_value2},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d32",id:"d32",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d33",id:"d33",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d34",id:"d34",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d35",id:"d35",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d36",id:"d36",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d37",id:"d37",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d38",id:"d38",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d39",id:"d39",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw13},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_value2},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d42",id:"d42",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d43",id:"d43",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d44",id:"d44",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d45",id:"d45",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d46",id:"d46",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d47",id:"d47",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d48",id:"d48",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw14},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_value2},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d52",id:"d52",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d53",id:"d53",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d54",id:"d54",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d55",id:"d55",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d56",id:"d56",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d57",id:"d57",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw15},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_value2},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d62",id:"d62",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d63",id:"d63",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d64",id:"d64",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d65",id:"d65",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d66",id:"d66",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw16},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_value2},
		 {layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d72",id:"d72",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d73",id:"d73",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d74",id:"d74",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d75",id:"d75",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw17},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_value2},
		{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d82",id:"d82",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d83",id:"d83",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d84",id:"d84",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]}, 
		
		 {layout:'form',columnWidth:0.08,items: bw18},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_value2},{layout:'form',columnWidth:0.08,items:[{xtype:'numberfield',name:"d92",id:"d92",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 {layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d93",id:"d93",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw19},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_value2},{layout:'form',columnWidth:0.08,items: [{xtype:'numberfield',name:"d102",id:"d102",width:45,regex:/^[1-9]{1}$/,regexText:'输入参数有误',allowBlank:false}]},
		 
		 {layout:'form',columnWidth:0.08,items: bw20},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},
		 {layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_distxw},{layout:'form',columnWidth:0.08,items: bed_value2}]

});

		var winWgt = new Ext.Window({
				modal:true,
				width:760,
				height:385,
				plain: true,
				resizable:false,
				title:"<p align='center'>查看详情</p>",
				bodyBorder:true,
				items:[
					new Ext.Panel({
			    		frame:true,
			    		items:[bed_dafen2]})]
		})
		
		if(!winWgt.isVisible()){
  			 winWgt.show();//修改窗口显示
  			 winWgt.center();
  		} 
  		
  		bed_dafen2.getForm().load({
								     url: 'selectBedWgt.do',
								     waitMsg:"正在加载，请稍候...",
								   	 params: {
								        	bid : rowBed[0].get("zjId")
								   	 },
									 success:function(form,action){
									 	
										 	},
								     failure: function(form, action) {
								     	
								        	Ext.Msg.alert("Load failed", "数据加载失败，请稍后再试！");
								    }
					});
}	
//--bed右边计算列表------------------------------------
		var L1 = {xtype:'numberfield',id:"L",fieldLabel:"床身长度  ", name:'bLength',width:100,allowBlank:false};
		var K2 = {xtype:'numberfield',id:"K",fieldLabel:"床身宽度  ", name:'bWidth',width:100,allowBlank:false};
		var H3 = {xtype:'numberfield',id:"H",fieldLabel:"床身高度  ", name:'bHeight',width:100,allowBlank:false};
		var Cd4 = {xtype:'numberfield',id:"Cd",fieldLabel:"槽宽  ",name:'bCd',width:100,allowBlank:false};
		var Xl5 = {xtype:'numberfield',id:"Xl",fieldLabel:"线轨X长度  ",name:'bXl',width:100,allowBlank:false};
		var Xd6 = {xtype:'numberfield',id:"Xd",fieldLabel:"线轨X间距  ",name:'bXd',width:100,allowBlank:false};
		var Zl7 = {xtype:'numberfield',id:"Zl",fieldLabel:"线轨Z长度",name:'bZl',width:100,allowBlank:false};
		var Zd8 = {xtype:'numberfield',id:"Zd",fieldLabel:"线轨Z间距  ",name:'bZd',width:100,allowBlank:false};
		var Ribthick9 = {xtype:'numberfield',id:"Ribthick",fieldLabel:"板筋厚度  ",name:'bRibthick',width:100,allowBlank:false};
		var Hole10 = {xtype:'numberfield',id:"Hole",fieldLabel:"减重孔  ",name:'bHole',width:100,allowBlank:false};
		var Structure11 = {xtype:'numberfield',id:"Structure",fieldLabel:"结构特征  ",name:'bStructure',width:100,allowBlank:false};	

		var bd_Quanzhong= new Ext.form.FormPanel({
				title:'床身属性权重计算结果',
				layout:'form',
				frame:'true',
				columnWidth: .5,
				labelAlign: 'right',
				height:Y+155,
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
					{layout: 'column',bodyStyle:'padding:20px 10px 0px 50px',items:[{layout:'form',columnWidth:0.5,items: L1},{layout:'form',columnWidth:0.5,items: K2}]},
					{layout: 'column',bodyStyle:'padding:5px 10px 0px 50px',items:[{layout:'form',columnWidth:0.5,items: H3},{layout:'form',columnWidth:0.5,items: Cd4}]},
					{layout: 'column',bodyStyle:'padding:5px 10px 0px 50px',items:[{layout:'form',columnWidth:0.5,items: Xl5},{layout:'form',columnWidth:0.5,items: Xd6}]},
					{layout: 'column',bodyStyle:'padding:5px 10px 0px 50px',items:[{layout:'form',columnWidth:0.5,items: Zl7},{layout:'form',columnWidth:0.5,items: Zd8}]},
					{layout: 'column',bodyStyle:'padding:5px 10px 0px 50px',items:[{layout:'form',columnWidth:0.5,items: Ribthick9},{layout:'form',columnWidth:0.5,items: Hole10},
					{layout:'form',bodyStyle:'padding:5px 10px 0px 0px',columnWidth:0.5,items: Structure11}]}
					
				],
				buttons:[{text:'保存',handler:function(){
					if(bd_Quanzhong.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">添加的数据不符合要求</span>'); 
 							return;}
					Ext.Msg.confirm("提示",'<span style="font-size:16px;">是否保存该数据</span>',function(btn){
						if(btn=="yes"){
							bd_Quanzhong.getForm().submit({
 								url:"updateBdWgt.do",
 								params: {
								        	bid : 1
								   	 },
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,width:150})
	},
    							failure: function(form, action) {
    									Ext.Msg.alert('Failure', action.result.msg); }
 						})
				}})
			}}
					,{text:'重置',handler:function(){bd_Quanzhong.form.reset()}}]
			});		
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

if(Ext.getCmp("wgtReason") != undefined){
		case_tab.setActiveTab(Ext.getCmp("wgtReason"));
				return;}
		
		var tabPage = case_tab.add({//动态添加tab页
		title:"权重计算",
		iconCls:'aboutUs',
		id:"wgtReason",
		closable : true,//允许关闭
		items:[{layout: 'column',items:[upWgt_grid,up_quanzhong]},
				{layout: 'column',items:[bedWgt_grid,bd_Quanzhong]}	]})
		case_tab.setActiveTab(tabPage);//设置当前tab页
}