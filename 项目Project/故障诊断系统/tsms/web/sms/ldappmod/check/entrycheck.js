Ext.onReady(function(){
	//alert(sclassname);
     var viewport = new Ext.Viewport({
		layout : 'border',
		items:[ {  layout:"fit",
				   region:'center',
				   autoScroll:false,
                id:"c1",
                html:  "<iframe id='browse_frame' width=100% height=100% frameborder=1 src='/tsms/web/sms/ldappmod/check/centrycheck.jsp?sclassname="+sclassname+"'></iframe>"
            }]
				
	});
});