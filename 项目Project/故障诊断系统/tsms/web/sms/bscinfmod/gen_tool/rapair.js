
Ext.onReady(function(){
	 var tabs = new Ext.TabPanel({   
                 region: 'center', //border 布局，将页面分成东，南，西，北，中五部分，这里表示TabPanel放在中间   
                 margins: '3 3 3 0',   
                 activeTab: 0,   
                 defaults: {   
                    autoScroll: true   
                 },  
                 items: [{   
                     title: '中文', 
                     id:"chinese1",
                     html :"<iframe id='browse_frame' width=100% height=100% frameborder=1 src='/tsms/web/sms/bscinfmod/gen_tool/chinese1.jsp'></iframe>"   
                 }, {   
                     title: '英文',
                     id:'english1',
                     html: "<iframe id='browse_frame' width=100% height=100% frameborder=1 src='/tsms/web/sms/bscinfmod/gen_tool/english1.jsp'></iframe>"    
                 }]   
            });   

	var win=new Ext.Viewport({
		layout:"border",
		id:"win",
		autoHeight:false,
		items: tabs 
	});
	  
});

