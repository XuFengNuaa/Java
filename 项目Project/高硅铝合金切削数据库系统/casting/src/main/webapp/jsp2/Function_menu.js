var urlMap=[];

// 生产信息--------------------------------------------------------
var doc1 = [
	{id:'1', text:'基本信息查询',
		children:[
			{id:'11',text:'工件材料信息',leaf:true},
			{id:'12',text:'机床信息',leaf:true},
			{id:'13',text:'刀具信息',leaf:true}
			]}
			]

urlMap["11"]="/casting/jsp2/gongjian.jsp";
urlMap["12"]="/casting/jsp2/jichuang.jsp";
urlMap["13"]="/casting/jsp2/daoju.jsp";

var root1 = new Ext.tree.AsyncTreeNode({       
    draggable:false,
    id:'root1',
	expanded:true,
    children:doc1
})

var panel1 = new Ext.tree.TreePanel({
	title: '生产信息',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root:root1
})

//参数推荐------------------------------------------------------------
var doc2 = [{id:'2', text: '铣削案例推理', leaf:true}]

urlMap["2"]="/casting/jsp2/anlijiansuo.jsp";

var root2 = new Ext.tree.AsyncTreeNode({       
	draggable:false,
	id:'root2',
	expanded:true,
	children:doc2
})

var panel2 =new Ext.tree.TreePanel({
	title: '铣削参数推荐',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: root2
})

//建模优化-------------------------------------------------

var doc3= [
	{
		id:'31', 
		text:'铣削模型构建与预测',
		leaf:true 
	},{
		id:'32', 
		text:'铣削参数优化',
		leaf:true 
	}
]

urlMap["31"]="/casting/jsp2/jianmo.jsp";
urlMap["32"]="/casting/jsp2/youhua.jsp";

var root3= new Ext.tree.AsyncTreeNode({       
	draggable:false,
	id:'root3',
	expanded:true,
	children:doc3
})

var panel3= new Ext.tree.TreePanel({
	title: '建模预测与优化',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: root3
})


//个人信息-------------------------------------------------
var doc4 = [
	{
		id:'41', 
		text:'个人信息查看',
		leaf:true 
	},{
		id:'42', 
		text:'个人信息修改',
		leaf:true 
	}
]

urlMap["view_personal_infor"]="/casting/jsp/perinforread.jsp";
urlMap["personal_password_modif"]="/casting/jsp/perinformod.jsp";

var root4 = new Ext.tree.AsyncTreeNode({       
	draggable:false,
	id:'root4',
	expanded:true,
	children:doc4
})

var panel4 = new Ext.tree.TreePanel({
	title: '个人信息管理',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: root4
})

//User_infor_management_panel-用户信息管理---------------------------------------------------           
var doc5 = [
	{
		id:'51', 
		text:'添加新用户',
		leaf:true
	},{
		id:'52', 
		text:'修改/删除用户', 
		leaf:true
	}
]

urlMap["51"]="/casting/jsp2/usradd.jsp";
urlMap["52"]="/casting/jsp2/usrmod.jsp";

var root5 = new Ext.tree.AsyncTreeNode({       
	draggable:false,
	id:'root5',
	expanded:true,
	children:doc5
})

var panel5 = new Ext.tree.TreePanel({
	title: '用户信息管理',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: root5
})

//页面跳转功能-----------------------------------------------------------------------
function jumpFn(node){	
	if(node.isLeaf()){
		//页面转向
		mainFrame.location.href=urlMap[node.id];
		//改变标题
		changeTitle(node.text);
	}
}

function changeTitle(title){
	Ext.getCmp('centertab').setTitle(title);
}

panel1.on('click',jumpFn);
panel2.on('click',jumpFn);
panel3.on('click',jumpFn);
panel4.on('click',jumpFn);
panel5.on('click',jumpFn);

