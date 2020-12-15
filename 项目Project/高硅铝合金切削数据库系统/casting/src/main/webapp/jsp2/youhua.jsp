<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/casting/extjs/resources/css/ext-all.css"/></link>
<link rel="stylesheet" type="text/css" href="/casting/extjs/add.css"/>
<script type="text/javascript" src="/casting/extjs/adapter/ext/ext-base.js"> </script>
<script type="text/javascript" src="/casting/extjs/ext-all.js"></script>
<script type="text/javascript" src="/casting/extjs/src/locale/ext-lang-zh_CN.js"></script>

<script type="text/javascript">
Ext.onReady(function(){	

	    var jiagongmianji={xtype:'textfield',fieldLabel:'加工面积(mm²)',name:'jiagongmianji',width:'40',value:'10800'};
		var qieshen={xtype:'textfield',fieldLabel:'轴向切深(mm)',name:'qieshen',width:'40',value:'1'};
		var daojuzhijing={xtype:'textfield',fieldLabel:'刀具直径(mm)',name:'daojuzhijing',width:'40',value:'13'};
		var daojuchishu={xtype:'textfield',fieldLabel:'刀具齿数(齿)',name:'daojuchishu',width:'40',value:'1'};
		var huandaoshijian={xtype:'textfield',fieldLabel:'换刀时间(min)',name:'huandaoshijian',width:'40',value:'5'};
		var fuzhugongshi={xtype:'textfield',fieldLabel:'辅助工时(min)',name:'fuzhugongshi',width:'40',value:'10'};
		var gongshifei={xtype:'textfield',fieldLabel:'工时费用(元/min)',name:'gongshifei',width:'40',value:'1'};
		var daojufei={xtype:'textfield',fieldLabel:'刀具费用(元)',name:'daojufei',width:'40',value:'30'};
		
		var mxcanshu={xtype:'fieldset',title:'模型参数',height:90,layout:'column',labelWidth:100,
				items:[
					{layout:'form',columnWidth:.25,items: jiagongmianji},
					{layout:'form',columnWidth:.25,items: qieshen},
					{layout:'form',columnWidth:.25,items: daojuzhijing},
					{layout:'form',columnWidth:.25,items: daojuchishu},
					{layout:'form',columnWidth:1},
					{layout:'form',columnWidth:.25,items: huandaoshijian},
					{layout:'form',columnWidth:.25,items: fuzhugongshi},
					{layout:'form',columnWidth:.25,items: gongshifei},
					{layout:'form',columnWidth:.25,items: daojufei}
				]};
		
		
		var vmax={xtype:'textfield',fieldLabel:'高速(m/min)',name:'vmax',width:'40',value:'500'};
		var vmin={xtype:'textfield',fieldLabel:'低速(m/min)',name:'vmin',width:'40',value:'300'};
		var fzmax={xtype:'textfield',fieldLabel:'每齿进给量高值(mm/z)',name:'fzmax',width:'40',value:'0.25'};
		var fzmin={xtype:'textfield',fieldLabel:'每齿进给量低值(mm/z)',name:'fzmin',width:'40',value:'0.15'};
		var awmax={xtype:'textfield',fieldLabel:'径向切宽高值(mm)',name:'awmax',width:'40',value:'1.5'};
		var awmin={xtype:'textfield',fieldLabel:'径向切宽低值(mm)',name:'awmin',width:'40',value:'0.5'};
		var Ramax={xtype:'textfield',fieldLabel:'粗糙度要求(μm)',name:'Ramax',width:'40',value:'0.8'};
		
		var mxyueshu={xtype:'fieldset',title:'模型约束',height:115,layout:'column',
				items:[
					{layout:'form',columnWidth:.3,items: vmax,labelWidth:100},
					{layout:'form',columnWidth:.33,items: fzmax,labelWidth:140},
					{layout:'form',columnWidth:.36,items: awmax,labelWidth:110},
					{layout:'form',columnWidth:.3,items: vmin,labelWidth:100},
					{layout:'form',columnWidth:.33,items: fzmin,labelWidth:140},
					{layout:'form',columnWidth:.36,items: awmin,labelWidth:110},
					{layout:'form',columnWidth:.33,items: Ramax,labelWidth:100}
				]};
		
		
		var zuidascl={xtype:'radio',name:'youhua',boxLabel:'最大生产率',value:1};
		var zuidicb={xtype:'radio',name:'youhua',boxLabel:'最低生产成本'};
		
		var mxmubiao={xtype:'fieldset',title:'优化目标',height:60,layout:'column',labelWidth:100,
				items:[
					{layout:'form',columnWidth:.3,items: zuidascl},
					{layout:'form',columnWidth:.3,items: zuidicb}
				]};
		
		
		var youhuav={xtype:'textfield',fieldLabel:'切削速度(m/min)',name:'youhuav',width:'100',value:'500'};
		var youhuafz={xtype:'textfield',fieldLabel:'每齿进给量(mm/z)',name:'youhuafz',width:'100',value:'0.21'};
		var youhuaaw={xtype:'textfield',fieldLabel:'径向切深(mm)',name:'youhuaaw',width:'100',value:'1.48'};
		
		var mxyhcanshu={xtype:'fieldset',title:'优化结果',height:60,layout:'column',labelWidth:110,
				items:[
					{layout:'form',columnWidth:.25,items: youhuav},
					{layout:'form',columnWidth:.25,items: youhuafz},
					{layout:'form',columnWidth:.25,items: youhuaaw},
					{layout:'form',columnWidth:.25,items: {xtype:'button',text:'优化',width:'100'}},
				]};
		
		var lizishu={xtype:'textfield',fieldLabel:'粒子数(N)',name:'lizishu',width:'100',value:'16'};
		var daishu={xtype:'textfield',fieldLabel:'代数(kmax)',name:'daishu',width:'100',value:'300'};
		var xxx={xtype:'textfield',fieldLabel:'学习因子小值(cmin)',name:'xxx',width:'100',value:'0.5'};
		var xxd={xtype:'textfield',fieldLabel:'学习因子大值(cmax)',name:'xxd',width:'100',value:'2'};
		var w0={xtype:'textfield',fieldLabel:'惯性权值初值(w0)',name:'w0',width:'100',value:'0.4'};
		var wk={xtype:'textfield',fieldLabel:'惯性权值增量系数(wk)',name:'wk',width:'100',value:'0.4'};
		var fangcha={xtype:'textfield',fieldLabel:'适应度方差阈值(σT²)',name:'fangcha',width:'100',value:'0.001'};
		var sfyz={xtype:'textfield',fieldLabel:'缩放因子(λ)',name:'sfyz',width:'100',value:'0.8'};
		var sfyzF={xtype:'textfield',fieldLabel:'缩放因子(F)',name:'sfyzF',width:'100',value:'0.8'};
		var jcyz={xtype:'textfield',fieldLabel:'交叉因子(CR)',name:'jcyz',width:'100',value:'0.3'};
		
		var yhsfcasnhu={xtype:'fieldset',title:'优化算法参数',height:110,layout:'column',labelWidth:140,
				items:[
					{layout:'form',columnWidth:.25,items: lizishu},
					{layout:'form',columnWidth:.25,items: daishu},
					{layout:'form',columnWidth:.25,items: xxx},
					{layout:'form',columnWidth:.25,items: xxd},
					{layout:'form',columnWidth:.25,items: w0},
					{layout:'form',columnWidth:.25,items: wk},
					{layout:'form',columnWidth:.25,items: fangcha},
					{layout:'form',columnWidth:.25,items: sfyz},
					{layout:'form',columnWidth:.25,items: sfyzF},
					{layout:'form',columnWidth:.25,items: jcyz},
					{layout:'form',columnWidth:.25,items: {xtype:'button',text:'默认值',width:'100'}}
				]};
		
		
		var canshuyouhua1=new Ext.form.FormPanel({
			frame:true,
			region:'south',
			height:225,
			frame:true,		
			items:[yhsfcasnhu,mxyhcanshu],
			buttons:[{text:'上传数据'},{text:'清空'}]	
		});
		
		var canshuyouhua2=new Ext.form.FormPanel({
			frame:true,
			region:'east',
			width:700,
			frame:true,		
			items:[mxcanshu,mxyueshu,mxmubiao],
			buttons:[{text:'上传数据'},{text:'清空'}]	
		});
		
		var bmbox=new Ext.BoxComponent({
			region:'center',
			frame:true,	
			id:'bmbox',
			autoEl:{tag:'img',src:'/casting/images/shouliantu.png'}
		});
		
		new Ext.Viewport({
			layout:'border',
			autoScroll:true,
	        items:[canshuyouhua1,canshuyouhua2,bmbox]
		});
		
});

</script>
</head>
<body>

</body>
</html>