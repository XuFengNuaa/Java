// 判断admin
var flag;
if(role =="admin"){
	flag = "1";	
}else if(role =="manager"){
	flag = "2";	
}else if(role =="design"){
	flag ="3";
}

// 零件信息管理信息树---------------------------------------------------------------
var tool_info_doc = [{
						text:'已有零件管理',
					    icon:'resource/image/cartool.png',
						expanded:true,
						children:[{
							text:'外采购件', icon:'resource/image/add.gif',
								children:[{
									text:"回转工作台",
									icon:'resource/image/add.gif',
									listeners:{click:workpiece_mod},
									leaf:true
									},{
										text:"主轴",
										icon:'resource/image/add.gif',
										listeners:{click:spindle_mod},
										leaf:true
									},{
										text:"刀库",
										icon:'resource/image/add.gif',
										listeners:{click:atc_mod},
										leaf:true
									},{
										text:"滚珠丝杠",
										icon:'resource/image/add.gif',
										listeners:{click:screw_mod},
										leaf:true
									},{
										text:"线性导轨",
										icon:'resource/image/add.gif',
										listeners:{click:slideway_mod},
										leaf:true
									}]
								},{
									text:'自设计件',
									icon:'resource/image/reservin.png',
									children:[{
										text:"立柱",
										icon:'resource/image/add.gif',
										listeners:{click:upright_mod},
										leaf:true
									},{
										text:"床身",
										icon:'resource/image/add.gif',
										listeners:{click:workbed_mod},
										leaf:true
									}]
								}]
							}]

var tool_infor_root = new Ext.tree.AsyncTreeNode({       
    draggable:false,
    id:'tool_info_root',
	expanded:true,
    children:tool_info_doc
})

var tool_info_panel= new Ext.tree.TreePanel({
						title: '零件信息管理',
						iconCls:'files',
						animate: true,
						collapsible: true,
						rootVisible: false,
						autoScroll: true,
						border: false,
						root: tool_infor_root
				});

// case_maagement---------------------------------------------------------------

	var case_info = new Ext.tree.TreeNode({
		id:"case_info",text:'已有实例管理',icon:'resource/image/cartool.png' })
	case_info.appendChild(new Ext.tree.TreeNode({
							text:'整机汇总',icon:'resource/image/add.gif',leaf:true,listeners:{click:whole_mod} }))
							  
	var case_function =	new Ext.tree.TreeNode({
			id:"case_function",text:'推理功能管理',icon:'resource/image/cartool.png'});
	case_function.appendChild(new Ext.tree.TreeNode({
									text:'整机参数',
									icon:'resource/image/add.gif',
									leaf:true,listeners:{click:wh_param}}));
	case_function.appendChild(new Ext.tree.TreeNode({
									text:'回转工作台',
									icon:'resource/image/add.gif',
									leaf:true,listeners:{click:wkpiece_param}}));
	case_function.appendChild(new Ext.tree.TreeNode({
									text:'主轴',
									icon:'resource/image/add.gif',
									leaf:true,listeners:{click:spindle_param}}));
	case_function.appendChild(new Ext.tree.TreeNode({
									text:'刀库',
									icon:'resource/image/add.gif',
									leaf:true,listeners:{click:atc_param}}));
	case_function.appendChild(new Ext.tree.TreeNode({
									text:'立柱',
									icon:'resource/image/add.gif',
									leaf:true,listeners:{click:upright_param}}));
	case_function.appendChild(new Ext.tree.TreeNode({				
									text:'滚珠丝杠',
									icon:'resource/image/add.gif',
									leaf:true,
									listeners:{click:screw_param}}));
	case_function.appendChild(new Ext.tree.TreeNode({
									text:'线性导轨',
									icon:'resource/image/add.gif',
									leaf:true,
									listeners:{click:slidway_param}}));
							
	case_function.appendChild(new Ext.tree.TreeNode({
									text:'床身',
									icon:'resource/image/add.gif',
									leaf:true,
									listeners:{click:workbed_param}}));
						
	case_function.appendChild(new Ext.tree.TreeNode({
									text:'推理结果',
									icon:'resource/image/add.gif',
									leaf:true,
									listeners:{click:result_param} }));
// 权重控制
var weight_mod = new Ext.tree.TreeNode({  
								id:"weight_mod",
								text:'参数权重管理',
								icon:'resource/image/cartool.png'});
	
	weight_mod.appendChild(new Ext.tree.TreeNode({
									text:'立柱特征专家打分',
									icon:'resource/image/add.gif',
									leaf:true,
									listeners:{click:upWgt}}));
	
	weight_mod.appendChild(new Ext.tree.TreeNode({
									text:'床身特征专家打分',
									icon:'resource/image/add.gif',
									leaf:true,
									listeners:{click:bedWgt}}));
									
	weight_mod.appendChild(new Ext.tree.TreeNode({
									text:'权重计算',
									icon:'resource/image/add.gif',
									leaf:true,
									listeners:{click:wgtReason}}));
									
	weight_mod.appendChild(new Ext.tree.TreeNode({
									text:'权重维护',
									icon:'resource/image/add.gif',
									leaf:true,
									listeners:{click:upWeight}}));
							
						
var case_info_root = new Ext.tree.TreeNode({       
    draggable:false,
    id:'case_info_root',
	expanded:true});

case_info_root.appendChild(case_info);
case_info_root.appendChild(case_function);

if(flag == "1" || flag == "2"){
		case_info_root.appendChild(weight_mod);
}

var case_info_panel= new Ext.tree.TreePanel({
						title: '实例推理管理',
						iconCls:'files',
						animate: true,
						collapsible: true,
						rootVisible: false,
						autoScroll: true,
						border: false,
						root:case_info_root
})

//Personal_infor_management_panel-个人信息管理-------------------------------------------------
var Personal_info_root = new Ext.tree.TreeNode({       
	draggable:false,
	id:'personal_info_root',
	expanded:true });

//添加节点
Personal_info_root.appendChild(new Ext.tree.TreeNode({
		id:'edit_personal_infor', 
		text:'查看/编辑个人信息',
		leaf:true,
		icon:'resource/image/user.jpg',
		listeners:{click:person_edit} 
}));

var Personal_infor_panel = new Ext.tree.TreePanel({
	title: '个人信息管理',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: Personal_info_root
})

//User_info_management_panel-用户信息管理---------------------------------------------------           

var User_info_root = new Ext.tree.TreeNode({       
	draggable:false,
	id:'user_info_root',
	expanded:true
})
//添加节点
if(flag == "1"){
		User_info_root.appendChild(new Ext.tree.TreeNode({
						id:'usrmgt_add', 
						text:'添加新用户',
						leaf:true,
						icon:'resource/image/user.jpg',
						listeners:{click:user_add} 
	}));
		
		User_info_root.appendChild(new Ext.tree.TreeNode({
						id:'usrmgt_edit', 
						text:'编辑/删除用户',
						leaf:true,
						icon:'resource/image/user.jpg',
						listeners:{click:user_info} 
	}))

}

//------------------------------------------------------------------------
var User_info_panel = new Ext.tree.TreePanel({
	title: '用户信息管理',
	iconCls:'files',
	animate: true,
	collapsible: true,
	rootVisible: false,
	autoScroll: true,
	border: false,
	root: User_info_root
})

//tabpanel----------------------------------------------------------------------
var tool_tab = new Ext.TabPanel({
 	//	activeTab : 0,//默认激活第一个tab页
 		id: tool_tab,
		animScroll : true,//使用动画滚动效果
		enableTabScroll : true,//tab标签超宽时自动出现滚动按钮
		listeners:{
		'contextmenu' :function(tool_tab,myitem,e){ //鼠标右击时间
			var menu = new Ext.menu.Menu({
					items:[{text:"关闭当前选项页",handler:function(){
							
								tool_tab.remove(myitem) }},
				{text:"关闭其他所有选项页",handler:function() {
						tool_tab.items.each(function(item){
							if(item != myitem && item != tool_tab.getItem(0)) {
								tool_tab.remove(item);
							} });  } } ] });
			menu.showAt(e.getPoint());} }
 });
 
 var case_tab = new Ext.TabPanel({
 		activeTab : 0,//默认激活第一个tab页
 		id: case_tab,
		animScroll : true//使用动画滚动效果
 })
 var person_tab = new Ext.TabPanel({
 		activeTab : 0,//默认激活第一个tab页
 		id:person_tab,
		animScroll : true,//使用动画滚动效果
		enableTabScroll : true//tab标签超宽时自动出现滚动按钮
 })
 var user_tab = new Ext.TabPanel({
 		activeTab : 0,//默认激活第一个tab页
 		id:user_tab,
		animScroll : true,//使用动画滚动效果
		enableTabScroll : true//tab标签超宽时自动出现滚动按钮
 })

//首页固定的tab
var tabPanel = new Ext.TabPanel({ 
	activeTab : 0,//默认激活第一个tab页
	animScroll : true,//使用动画滚动效果
	enableTabScroll : true,//tab标签超宽时自动出现滚动按钮
	items:[{
			title: '欢迎页面',
			height:600,
			layout:"fit",
			closable : false//允许关闭
		//	items:tool_tab
		}],
	listeners:{
		'contextmenu' :function(tabPanel,myitem,e){ //鼠标右击时间
			var menu = new Ext.menu.Menu({
					items:[{text:"关闭当前选项页",handler:function(){
							if(myitem != tabPanel.getItem(0)) {
								tabPanel.remove(myitem)} }},
				{text:"关闭其他所有选项页",handler:function() {
						tabPanel.items.each(function(item){
							if(item != myitem && item != tabPanel.getItem(0)) {
								tabPanel.remove(item);
							} });  } } ] });
			menu.showAt(e.getPoint());} } });
 

 
 
 