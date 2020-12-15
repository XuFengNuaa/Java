Ext.onReady(function(){
	//alert(sclassname);
      var tabs = new Ext.TabPanel({
            region: 'center',
            id:"tabPanel",
            margins:'3 3 3 0', 
            activeTab: 0,
            defaults:{autoScroll:false},

            items:[{
                title: '专家审批',
                id:"c1",
                html:  "<iframe id='browse_frame' width=100% height=100% frameborder=1 src='/tsms/web/sms/storemgtmod/cut_tool/pa_c_approve.jsp'></iframe>"
            },{
                title: '申请答疑',
                id:"c2",
                html:  "<iframe id='browse_frame' width=100% height=100% frameborder=1 src='/tsms/web/sms/storemgtmod/cut_tool/pa_c_entry.jsp'></iframe>"
            }]
        });
        
        
     var viewport = new Ext.Viewport({
		layout : 'border',
		items:[ {  layout:"fit",
				   region:'center',
				   autoScroll:false,
				   items:tabs
				}]
	});
});