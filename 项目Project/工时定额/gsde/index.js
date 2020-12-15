Ext.onReady(function(){
	//this is newest 
	window.resizeTo(screen.availWidth,screen.availHeight);   
  	window.moveTo(0,0);	
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	
	viewport=new Ext.Viewport({
		layout:'border',
		hideMode:'display',
		items:[
			new Ext.BoxComponent({ // raw
				region:'north',
				el: 'north',
				height:32
			}),			
		   {region:'west',
            id:'west-panel',
            title:'管理菜单',
            split:true,
            width: 200,
            minSize: 200,
            maxSize: 400,
            collapsible: true,
            animate:false,
            margins:'0 0 0 5',
            layout:'accordion',
            hideMode:'display',
            items: [ 
                    bscinf_panel,
                    cutpara_panel,
                    quota_panel,
                    quotaquery_panel,
                    quotamgtmod_panel,
                    userinf_panel,
                    usermanage_panel         
            ]
                 }, 
				new Ext.TabPanel({
				region:'center',
				deferredRender:false,
				activeTab:0,
				items:[
					{	contentEl:'center',
						title:'管理平台首页',
						id:'centertab',
						autoScroll:true						
					}
				]
			})				
		]
	});		 
	 
	Ext.get("header").setVisible(true);
	
	bscinf_panel.on('click',jumpFn);
	bscinf_panel.getRootNode().expand(true);
	
	cutpara_panel.on('click',jumpFn);
	cutpara_panel.getRootNode().expand(true);
	
	quota_panel.on('click',jumpFn);
	quota_panel.getRootNode().expand(true);
	
	quotaquery_panel.on('click',jumpFn);
	quotaquery_panel.getRootNode().expand(true);
	
	quotamgtmod_panel.on('click',jumpFn);
	quotamgtmod_panel.getRootNode().expand(true);
	
	userinf_panel.on('click',jumpFn);
	userinf_panel.getRootNode().expand(true);
	
	usermanage_panel.on('click',jumpFn);
	usermanage_panel.getRootNode().expand(true);
	
		
});


function setEvent(){
	try{
	  	Ext.getDom("mainFrame").onfocus=function(){
	  		hideMenu();		  		
	  	}
	}catch(e){		
	}		
}
//resize事件后的恢复
function jumpFn(node){	
	if(node.isLeaf()){
		//页面转向
		mainFrame.location.href=urlMap[node.id];
		//改变标题
		changeTitle(node.text);
	}
}
//改变主栏标题
function changeTitle(title){
	Ext.getCmp('centertab').setTitle(title);
}
//主窗口
function resizeWindow(){
	Ext.get("north").setHeight(32);
	viewport.doLayout();
	var activeid=Ext.getCmp('west-panel').layout.activeItem.id;
	var heitemp=viewport.findById(activeid).getSize().height-23;
}

