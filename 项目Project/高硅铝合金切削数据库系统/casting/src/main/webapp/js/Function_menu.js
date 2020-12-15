var urlMap=[];

// Material_infor_panel-材料信息树--------------------------------------------------------
var Material_infor_doc = [
							{id:'processparamque', text:'铸造工艺参数查询',
									children:[
										{id:'sx',text:'砂型铸造',
											children:[
												{id:'sxbm',text:'拔模斜度',leaf:true},
												{id:'sxjz',text:'浇注系统参数',leaf:true},
												{id:'sxmk',text:'冒口参数',leaf:true}
											]
										},
										{id:'lx',text:'离心铸造',leaf:true}
										]},
							{id:'materialinforque', text:'材料信息查询', leaf:true}
						]

urlMap["materialinforque"]="/casting/jsp/materialproperties.jsp";
urlMap["sxbm"]="/casting/jsp/sxbm.jsp";
urlMap["sxjz"]="/casting/jsp/sxjz.jsp";
urlMap["sxmk"]="/casting/jsp/sxmk.jsp";
urlMap["lx"]="/casting/jsp/lxprocessparameters.jsp";

var Material_infor_root = new Ext.tree.AsyncTreeNode({       
    draggable:false,
    id:'material_infor_root',
	expanded:true,
    children:Material_infor_doc
})

var Material_infor_panel = new Ext.tree.TreePanel({
	title: '铸造工艺',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root:Material_infor_root
})

//Casting_infor_panel-铸件信息树------------------------------------------------------------
var Casting_infor_doc = [{id:'casting_req', text: '铸造件信息查询', leaf:true},				
					  		{id:'casting_wri', text: '铸造件信息编辑', leaf:true}]

urlMap["casting_req"]="/casting/jsp/castinginforgrid.jsp";
urlMap["casting_wri"]="/casting/jsp/castingsubmit.jsp";

var Casting_infor_root = new Ext.tree.AsyncTreeNode({       
	draggable:false,
	id:'casting_infor_root',
	expanded:true,
	children:Casting_infor_doc
})

var Casting_infor_panel =new Ext.tree.TreePanel({
	title: '铸件信息',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: Casting_infor_root
	//loader: new Ext.tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
})



//Personal_infor_management_panel-个人信息管理-------------------------------------------------
var Personal_infor_doc = [
	{
		id:'view_personal_infor', 
		text:'个人信息查看',
		leaf:true 
	},{
		id:'personal_password_modif', 
		text:'个人信息修改',
		leaf:true 
	}
]

urlMap["view_personal_infor"]="/casting/jsp/perinforread.jsp";
urlMap["personal_password_modif"]="/casting/jsp/perinformod.jsp";

var Personal_infor_root = new Ext.tree.AsyncTreeNode({       
	draggable:false,
	id:'personal_infor_root',
	expanded:true,
	children:Personal_infor_doc
})

var Personal_infor_management_panel = new Ext.tree.TreePanel({
	title: '个人信息管理',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: Personal_infor_management_root
	//loader: new Ext.tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
})

//User_infor_management_panel-用户信息管理---------------------------------------------------           
var User_infor_doc = [
	{
		id:'usrmgt_add', 
		text:'添加新用户',
		leaf:true
	},{
		id:'usrmgt_mod', 
		text:'修改/删除用户', 
		leaf:true
	}
]

urlMap["usrmgt_add"]="/casting/jsp/usradd.jsp";
urlMap["usrmgt_mod"]="/casting/jsp/usrmod.jsp";

var User_infor_management_root = new Ext.tree.AsyncTreeNode({       
	draggable:false,
	id:'user_infor_management_root',
	expanded:true,
	children:User_infor_doc
})

var User_infor_panel = new Ext.tree.TreePanel({
	title: '用户信息管理',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: User_infor_management_root
	//loader: new Ext.tree.TreeLoader({preloadChildren: true,clearOnLoad: false})
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

/*
function setEvent(){
	try{
	  	Ext.getDom("mainFrame").onfocus=function(){
	  		hideMenu();		  		
	  	}
	}catch(e){		
	}		
}
*/
function changeTitle(title){
	Ext.getCmp('centertab').setTitle(title);
}

Casting_infor_panel.on('click',jumpFn);
//Casting_infor_panel.getRootNode().expand(true);

User_infor_management_panel.on('click',jumpFn);
//User_infor_management_panel.getRootNode().expand(true);

Personal_infor_management_panel.on('click',jumpFn);

Material_infor_panel.on('click',jumpFn);

