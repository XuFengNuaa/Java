//案例改写--------------------------------------------------------------------------------
	var XScailiaomc={xtype:'textfield',fieldLabel:'材料名称',name:'XScailiaomc',width:65};
	var XSjiticl={xtype:'textfield',fieldLabel:'基体材料',name:'XSjiticl',width:65};
	var XSzengqiangys={xtype:'textfield',fieldLabel:'增强相元素',name:'XSzengqiangys',width:65};
	var XStijifs={xtype:'textfield',fieldLabel:'体积分数',name:'Cjiagongtz',width:65};
	var XScailiaocs={xtype:'fieldset',title:'材料基本信息',layout:'column',labelWidth:70,height:60,
			items:[
				{layout:'form',columnWidth:.25,items: XScailiaomc},
				{layout:'form',columnWidth:.25,items: XSjiticl},
				{layout:'form',columnWidth:.25,items: XSzengqiangys},
				{layout:'form',columnWidth:.25,items: XStijifs}
			]};
	
	
	var xs1={xtype:'label',text:'加工方式:铣削'};
	var xs2={xtype:'label',text:'加工精度:精加工'};
	var xs3={xtype:'label',text:'加工特征:平面铣削 '};
	var xs3_1={xtype:'label',text:'切削液:否'};
	var XS1={xtype:'fieldset',title:'加工信息',height:50,layout:'column',
			items:[
				{layout:'form',columnWidth:.15,items: xs1},
				{layout:'form',columnWidth:.15,items: xs2},
				{layout:'form',columnWidth:.15,items: xs3},
				{layout:'form',columnWidth:.15,items: xs3_1}
			]};
	
	
	var xs4={xtype:'label',text:'材料密度:2.64g/cm³    抗拉强度:480MPa    弹性模量:96GPa    导热系数:133.1W/(m*K)  '};
	var XS2={xtype:'fieldset',title:'材料性能',height:50,layout:'column',
			items:[
				{layout:'form',columnWidth:1,items: xs4}
			]};
	
	
	var xs5={xtype:'label',text:'刀具型号:APKT1135PDFR-G2-H01  刀具圆角:0.8mm  后角:11°  切削直径:13mm'};
	var XS3={xtype:'fieldset',title:'刀具信息',height:50,layout:'column',
			items:[
				{layout:'form',columnWidth:1,items: xs5}
			]};
	
	
	var xs6={xtype:'label',text:'切削速度:500m/min  每齿进给量: 0.21mm  径向切深:1.48mm  轴向切深:1mm  '};
	var xs7={xtype:'label',text:'粗糙度:0.8μm '};
	var XS4={xtype:'fieldset',title:'切削参数',height:50,layout:'column',
			items:[
				{layout:'form',columnWidth:.66,items: xs6},
				{layout:'form',columnWidth:.33,items: xs7}
			]};
	
	var XSanli=new Ext.FormPanel({
		region:'north',
		title:'最相似案例',
		layout:'column',
		frame:'true',
		height:340,
		items:[
			{layout:'form',columnWidth:1,items: XScailiaocs},
			{layout:'form',columnWidth:1,items: XS1},
			{layout:'form',columnWidth:1,items: XS2},
			{layout:'form',columnWidth:1,items: XS3},
			 {layout:'form',columnWidth:1,items: XS4}]
	});

	
	var XSGXdaoju={xtype:'combo',fieldLabel:'刀具型号',name:'XSGXdaoju',width:100};
	var XSGXjichuang={xtype:'combo',fieldLabel:'机床ID',name:'XSGXdaoju',width:100};
	var XSGXqiesu={xtype:'textfield',fieldLabel:'切削速度(m/min)',name:'XSGXdaoju',width:65};
	var XSGXjinji={xtype:'textfield',fieldLabel:'每齿进给量(mm)',name:'XSGXdaoju',width:65};
	var XSGXqiekuan={xtype:'textfield',fieldLabel:'径向切深(mm)',name:'XSGXdaoju',width:65};
	var XSGXqieshen={xtype:'textfield',fieldLabel:'轴向切深(mm)',name:'XSGXdaoju',width:65};
	

	var XS6=new Ext.form.RadioGroup({  
					    fieldLabel: '切削液',
					    width: 120,  
					    items:[{ name:'qiexiaoye',boxLabel:'是',checked:true},
					    	{ name:'qiexiaoye',boxLabel:'否'}]   
					})
	
	var XSanliGX=new Ext.FormPanel({
		region:'west',
		title:'改写案例',
		layout:'column',
		frame:'true',
		width:220,
		labelAlign:'right',
		items:[
			{layout:'form',columnWidth:1,items: XSGXjichuang},
			{layout:'form',columnWidth:1,items: XSGXdaoju},
			{layout:'form',columnWidth:1,items: XSGXqiesu},
			{layout:'form',columnWidth:1,items: XSGXjinji},
			{layout:'form',columnWidth:1,items: XSGXqiekuan},
			{layout:'form',columnWidth:1,items: XSGXqieshen},
			{layout:'form',columnWidth:1,items: XS6}
		]
	});
	
	
	
	var pingjia3=new Ext.form.RadioGroup({  
	    fieldLabel: '水平',
	    width: 180,  
	    items:[{name:'pingjia',boxLabel:'很好',checked:true},
	    	{name:'pingjia',boxLabel:'一般'},
	    	{name:'pingjia',boxLabel:'较差'}]   
	});
	var pingjia4={xtype:'textarea',fieldLabel:'评价',name:'pingjia4',width:200,height:90}
	var pingjia5={xtype:'textfield',fieldLabel:'提交用户ID',name:'pingjia5',width:90}
	
	var XSpingjia=new Ext.FormPanel({
		region:'center',
		title:'评价',
		layout:'column',
		frame:'true',
		labelWidth:50,
		items:[
			{layout:'form',columnWidth:.6,items: pingjia3},
			{layout:'form',columnWidth:.6,items: pingjia4},
			{layout:'form',columnWidth:.4,items: pingjia5,labelWidth:70}
		],
		buttons:[{text:'提交'}]
	 });
	
	var anligaixie = new Ext.Window({
		modal:true,
	    width:730,
	    height:590,
	    plain: true,
	    layout:'border',
	    title:"案例改写",
		bodyBorder:true,
		items:[XSanli,XSanliGX,XSpingjia]
	});	
	
	