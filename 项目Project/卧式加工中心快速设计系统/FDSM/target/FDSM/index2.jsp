<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人理财信息</title>
<% 
	pageContext.setAttribute("APP_PATH",request.getContextPath());
	if(request.getSession().getAttribute("loginUsers")==null){
	response.sendRedirect("login.jsp");
	return;}
%>
 <link rel="stylesheet" type="text/css" href="${APP_PATH}/Extjs/resources/css/ext-all.css"/></link>
 <script src="${APP_PATH}/Extjs/adapter/ext/ext-base.js"> </script>
 <script src="${APP_PATH}/Extjs/adapter/ext/ext-all.js"></script>
 <script src="${APP_PATH}/Extjs/adapter/ext/ext-lang-zh_CN.js"></script>
 
 <script> 
 		var select ={xtype:'textfield',fieldLabel:"输入参数",name:'select_text', width:130};
		var button_select={xtype:"button",text:"查 询"}
		var select_combo = new Ext.form.ComboBox({
			    typeAhead: true,
			    triggerAction: 'all',
			    fieldLabel:"查询类型",
			    width:130,
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "select_combo",
			        fields: [
			            'selectId',
			            'selectText'
			        ],
			        data: [[1, '机床型号'], [2, '零件型号']]
			    }),
			    valueField: 'selectId',
			    displayField: 'selectText',
			    emptyText:'---请选择---'});
			    
		var rank_combo = new Ext.form.ComboBox({
			    fieldLabel:"排序方式  ",
			    labelWidth:80,
			    width:125,
			    typeAhead: true,
			    triggerAction: 'all',
			    mode: 'local',
			    store: new Ext.data.ArrayStore({
			        id: "rank_combo",
			        fields: [
			            'rankId',
			            'rankText'
			        ],
			        data: [[1, '型号'], [2, '尺寸大小'],[3, '承受载荷']]
			    }),
			    valueField: 'rankId',
			    displayField: 'rankText',
			    emptyText:'---请选择---'});
		
		var sousuo = new Ext.form.RadioGroup({
                fieldLabel:'检索模式  ',
              	width:150,               
              	//style: 'padding-top:3px;height:17px;',
                items: [{ name: 'sousuo', inputValue: '0', boxLabel: '完全匹配', checked: true }, { name: 'sousuo', inputValue: '1', boxLabel: '模糊匹配'}]});
		
		var column0 = { columnWidth: .25,layout: 'form',items: [select]};
		var column1 = { columnWidth: .3,layout: 'form',items: [select_combo]};
       	var column2 = { columnWidth: .3,layout: 'form',items: [rank_combo]};
       	var column3 = { columnWidth: .3,layout: 'form',items: [sousuo]};
       	
		var tool_jiansuo = new Ext.FormPanel({
			title:'搜索条件',
			height:150,
			labelAlign: 'right',
			labelWidth: 100,
			layout:'form',
			frame:true,
			items:[{layout: 'column',items:[column0,button_select]},{layout: 'column',items:[column1,column2,column3]}]
			//buttonAlign : 'center',
			//buttons:[{text:'搜索',listeners:{click:Tool_pay}},{text:'清空'}]	
	});
	
//------定义查询结果表格-----------------------------------------------------------------------
	var vstore=new Ext.data.JsonStore({  
		url:"getUser.action",
		root:"allUser",
		idProperty:"id",
		fields: ["id","username","password"],
		totalProperty: "recordSize"  //后台总的记录数
		
		//reader: new Ext.data.JsonReader({
			//root:"allUser", //getUser.action 的对象属性
		//	idProperty:"id"
		//	},user) //上面定义的user
	});
	
	var gridpanel=new Ext.grid.EditorGridPanel({
			width:700,
			height:500,
			frame:true,
			//store:vstore,  //上面定义的store
			columns:[{
			header : "ID", //表格上的重要字段
			dataIndex : "id",
			align : 'center',
			sortable:true //排序开启，正序和倒序
		}, {
			header : "用户名",
			dataIndex : "username",
			sortable:true,
			align : 'center',
			editor:new Ext.form.TextField({
					fieldLabel : "用户名",
					minLength:4,
					minLengthText:"用户名长度不能小于{0}",
					maxLength: 16,
					maxLengthText : "用户名长度不能小于{0}",
					allowBlank:false,
					blankText:"用户名不能为空"
			}) //即可编辑该字段！
		},{
			header : "密码",
			dataIndex : "password",
			sortable:true,
			align : 'center',
			editor:new Ext.form.TextField({
					fieldLabel : "密码",
					id:"password",
					name : "password",
				 	allowBlank:false,
					blankText:"密码不能为空"
			})
		}],
			autoExpandColumn:2,
			bbar:new Ext.PagingToolbar({  //分页显示
					//store:vstore,
					pageSize:10, //每页显示多少记录
			 		displayMsg: '当前显示第 {0} 条到 {1} 条记录，一共 {2} 条',
					emptyMsg: "没有记录",
					displayInfo: true		 
		}),
		selModel:new Ext.grid.RowSelectionModel({
						singleSelect:false  })})
	
	Ext.onReady(function(){	new Ext.Viewport({
		layout:'border',
		autoScroll:true,
        items:[
        	gridpanel
        	]
	})});
 		</script>
 

 
</head>
<body>
<div id="loginfrom"></div>
</body>
</html>