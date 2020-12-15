function tool_Mod(){		
		if( Ext.getCmp("toolMod") != undefined){	
				tabPanel.setActiveTab(Ext.getCmp("toolMod"));
		}else{	
			
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
				var toolPage = tabPanel.add({
					title:"零件管理",
					id:"toolMod",
					layout:"fit",
					closable : true,//允许关闭
					items: tool_tab
				}).show();		
				//tabPanel.setActiveTab(toolPage);
		}
}