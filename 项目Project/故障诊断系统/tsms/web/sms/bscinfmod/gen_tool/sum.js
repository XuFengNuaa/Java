Ext.onReady(function(){
	 var tabs = new Ext.TabPanel({   
                 region: 'center', //border 布局，将页面分成东，南，西，北，中五部分，这里表示TabPanel放在中间   
                 margins: '3 3 3 0',   
                 activeTab: 0,   
                 defaults: {   
                    autoScroll: true   
                 },  
                 items: [{   
                     title: '设备使用说明', 
                     id:"use",
                     html :"<iframe id='browse_frame' width=100% height=100% frameborder=1 src='/tsms/web/sms/bscinfmod/gen_tool/pa_ct_que.jsp'></iframe>"   
                 }, {   
                     title: '设备维修说明',
                     id:'rapair',
                     html: "<iframe id='browse_frame' width=100% height=100% frameborder=1 src='/tsms/web/sms/bscinfmod/gen_tool/rapair.jsp'></iframe>"    
                 },{   
                     title: '软件系统说明',
                     id:'system',
                     html: "<iframe id='browse_frame' width=100% height=100% frameborder=1 src='/tsms/web/sms/bscinfmod/gen_tool/system.jsp'></iframe>"    
                 }]   
            });   
	
	var win=new Ext.Viewport({
		layout:"border",
		id:"win",
		autoHeight:false,
		items: tabs 
	});
	  
});

