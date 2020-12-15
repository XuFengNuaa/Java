	
	Ext.namespace('Ext.ux.InlineToolbarTabPanel');
	
	Ext.ux.InlineToolbarTabPanel = function(config) {
		this.toolbar = { width:150,id:this.id + 'Toolbar'};
		Ext.apply(this, config);
		this.config = config;
		Ext.ux.InlineToolbarTabPanel.superclass.constructor.call(this, config);//这个意思就是调用父类的构造函数 作用域是当前子类 传入config参数 将来config中有什么属性 会为子类构造出什么属性
		this.addEvents('beforerender','onrender')
		};
	 
	// plugin code
	Ext.extend(Ext.ux.InlineToolbarTabPanel, Ext.TabPanel, {
		
		getScrollArea : function(){
			var newScrollArea = this.stripWrap.dom.clientWidth-this.config.toolbar.width;
			return parseInt(newScrollArea, 10) || 0;
			},
			
		renderToolbar : function() {
			this.fireEvent('beforeRender');
			var toolbarDiv = this.toolbar.id || this.id + 'Toolbar';
			Ext.DomHelper.insertFirst(document.getElementById(this.id), '<div id="'+toolbarDiv+'" class="x-tab-toolbar-wrap" style="float:right;height:26px;overflow:hidden;border-left:0px;border-top:1px solid #8DB2E3;margin-left:0px;"></div>');
			var toolbar = new Ext.Panel({
				renderTo: toolbarDiv,
				border: false,
				tbar: this.config.toolbar.tbar
				});
			
			toolbarExt = Ext.get(toolbarDiv);
			toolbarExt.setWidth(this.config.toolbar.width || 150);
			
			headerEl = Ext.query('#myTabs div[class^=x-tab-panel-header]');
			headerExt = Ext.get(headerEl);
			//Ext.getCmp(this.id).getTopToolbar().disabled=true;
			
			
			if ( Ext.isIE ) {
				// adjust for scrollbars being present if scrolling
				extraLength = 0;
				if ( this.scrolling ) { extraLength = 35; };
				
				// adjust width for IE
				headerExt.setWidth(this.getScrollArea()+extraLength);
				Ext.DomHelper.insertFirst(document.getElementById(this.id), '<div id="'+toolbarDiv+'IEHack" class="x-toolbar" style="position:absolute;z-index:-2;height:21px;width:100px;border:1px solid #8DB2E3;border-bottom:1px solid #99BBE8;;"> </div>');
				newDiv = Ext.get(toolbarDiv+'IEHack');
				newDiv.setWidth(this.getScrollArea()+(this.config.toolbar.width*.5));
				}
				
			else {
				headerExt.setWidth('auto');	
				}
			this.fireEvent('afterrender');
			},
				
		getToolbar : function() {
			return Ext.get(toolbarDiv);
			}
		
		});