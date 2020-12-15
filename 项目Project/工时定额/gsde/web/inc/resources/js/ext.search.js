/*
*Copyright(c) 2008, 
*作者:马浩
*edit:mahao
*/
//var RenderDiv = "";
Ext.Search = Ext.extend(Ext.Container, {
	
    /**
    * @cfg {String} baseCls
    * The base CSS class to apply to this panel's element (defaults to 'x-panel').
    */
    baseCls : 'x-panel',
    /**
    * @cfg {String} collapsedCls
    * A CSS class to add to the panel's element after it has been collapsed (defaults to 'x-panel-collapsed').
    */
    collapsedCls : 'x-panel-collapsed',
    /**
    * @cfg {Boolean} maskDisabled
    * True to mask the panel when it is disabled, false to not mask it (defaults to true).  Either way, the panel
    * will always tell its contained elements to disable themselves when it is disabled, but masking the panel
    * can provide an additional visual cue that the panel is disabled.
    */
    maskDisabled: true,
    /**
    * @cfg {Boolean} animCollapse
    * True to animate the transition when the panel is collapsed, false to skip the animation (defaults to true
    * if the {@link Ext.Fx} class is available, otherwise false).
    */
    animCollapse: Ext.enableFx,
    /**
    * @cfg {Boolean} headerAsText
    * True to display the panel title in the header, false to hide it (defaults to true).
    */
    headerAsText: true,
    /**
    * @cfg {String} buttonAlign
    * The alignment of any buttons added to this panel.  Valid values are 'right,' 'left' and 'center' (defaults to 'right').
    */
    buttonAlign: 'right',
    /**
     * @cfg {Boolean} collapsed
     * True to render the panel collapsed, false to render it expanded (defaults to false).
     */
    collapsed : false,
    /**
    * @cfg {Boolean} collapseFirst
    * True to make sure the collapse/expand toggle button always renders first (to the left of) any other tools
    * in the panel's title bar, false to render it last (defaults to true).
    */
    collapseFirst: true,
    /**
     * @cfg {Number} minButtonWidth
     * Minimum width in pixels of all buttons in this panel (defaults to 75)
     */
    minButtonWidth:75,
    /**
     * @cfg {String} elements
     * A comma-delimited list of panel elements to initialize when the panel is rendered.  Normally, this list will be
     * generated automatically based on the items added to the panel at config time, but sometimes it might be useful to
     * make sure a structural element is rendered even if not specified at config time (for example, you may want
     * to add a button or toolbar dynamically after the panel has been rendered).  Adding those elements to this
     * list will allocate the required placeholders in the panel when it is rendered.  Valid values are<ul>
     * <li><b>header</b></li>
     * <li><b>tbar</b> (top bar)</li>
     * <li><b>body</b></li>
     * <li><b>bbar</b> (bottom bar)</li>
     * <li><b>footer</b><li>
     * </ul>
     * Defaults to 'body'.
     */
    elements : 'body',

    // protected - these could be used to customize the behavior of the window,
    // but changing them would not be useful without further mofifications and
    // could lead to unexpected or undesirable results.
    toolTarget : 'header',
    collapseEl : 'bwrap',
    slideAnchor : 't',

    // private, notify box this class will handle heights
    deferHeight: true,
    // private
    expandDefaults: {
        duration:.25
    },
    // private
    collapseDefaults: {
        duration:.25
    },

    // private
    initComponent : function(){
        Ext.Search.superclass.initComponent.call(this);

        this.addEvents(
            /**
             * @event bodyresize
             * Fires after the Search has been resized.
             * @param {Ext.Search} p the Search which has been resized.
             * @param {Number} width The Search's new width.
             * @param {Number} height The Search's new height.
             */
            'bodyresize',
            /**
             * @event titlechange
             * Fires after the Search title has been set or changed.
             * @param {Ext.Search} p the Search which has had its title changed.
             * @param {String} The new title.
             */
            'titlechange',
            /**
             * @event collapse
             * Fires after the Search has been collapsed.
             * @param {Ext.Search} p the Search that has been collapsed.
             */
            'collapse',
            /**
             * @event expand
             * Fires after the Search has been expanded.
             * @param {Ext.Search} p The Search that has been expanded.
             */
            'expand',
            /**
             * @event beforecollapse
             * Fires before the Search is collapsed.  A handler can return false to cancel the collapse.
             * @param {Ext.Search} p the Search being collapsed.
             * @param {Boolean} animate True if the collapse is animated, else false.
             */
            'beforecollapse',
            /**
             * @event beforeexpand
             * Fires before the Search is expanded.  A handler can return false to cancel the expand.
             * @param {Ext.Search} p The Search being expanded.
             * @param {Boolean} animate True if the expand is animated, else false.
             */
            'beforeexpand',
            /**
             * @event beforeclose
             * Fires before the Search is closed.  Note that Searchs do not directly support being closed, but some
             * Search subclasses do (like {@link Ext.Window}).  This event only applies to such subclasses.
             * A handler can return false to cancel the close.
             * @param {Ext.Search} p The Search being closed.
             */
            'beforeclose',
            /**
             * @event close
             * Fires after the Search is closed.  Note that Searchs do not directly support being closed, but some
             * Search subclasses do (like {@link Ext.Window}).
             * @param {Ext.Search} p The Search that has been closed.
             */
            'close',
            /**
             * @event activate
             * Fires after the Search has been visually activated.
             * Note that Searchs do not directly support being activated, but some Search subclasses
             * do (like {@link Ext.Window}). Searchs which are child Components of a TabSearch fire the
             * activate and deactivate events under the control of the TabSearch.
             * @param {Ext.Search} p The Search that has been activated.
             */
            'activate',
            /**
             * @event deactivate
             * Fires after the Search has been visually deactivated.
             * Note that Searchs do not directly support being deactivated, but some Search subclasses
             * do (like {@link Ext.Window}). Searchs which are child Components of a TabSearch fire the
             * activate and deactivate events under the control of the TabSearch.
             * @param {Ext.Search} p The Search that has been deactivated.
             */
            'deactivate'
        );

        // shortcuts
        if(this.tbar){
            this.elements += ',tbar';
            if(typeof this.tbar == 'object'){
                this.topToolbar = this.tbar;
            }
            delete this.tbar;
        }
        if(this.bbar){
            this.elements += ',bbar';
            if(typeof this.bbar == 'object'){
                this.bottomToolbar = this.bbar;
            }
            delete this.bbar;
        }

        if(this.header === true){
            this.elements += ',header';
            delete this.header;
        }else if(this.title && this.header !== false){
            this.elements += ',header';
        }

        if(this.footer === true){
            this.elements += ',footer';
            delete this.footer;
        }

        if(this.buttons){
            var btns = this.buttons;
            /**
             * This Search's Array of buttons as created from the <tt>buttons</tt>
             * config property. Read only.
             * @type Array
             * @property buttons
             */
            this.buttons = [];
            for(var i = 0, len = btns.length; i < len; i++) {
                if(btns[i].render){ // button instance
                    this.buttons.push(btns[i]);
                }else{
                    this.addButton(btns[i]);
                }
            }
        }
        if(this.autoLoad){
            this.on('render', this.doAutoLoad, this, {delay:10});
        }
    },

    // private
    createElement : function(name, pnode){
        if(this[name]){
            pnode.appendChild(this[name].dom);
            return;
        }

        if(name === 'bwrap' || this.elements.indexOf(name) != -1){
            if(this[name+'Cfg']){
                this[name] = Ext.fly(pnode).createChild(this[name+'Cfg']);
            }else{
                var el = document.createElement('div');
                el.className = this[name+'Cls'];
                this[name] = Ext.get(pnode.appendChild(el));
            }
        }
    },

    // private
    onRender : function(ct, position){
        Ext.Search.superclass.onRender.call(this, ct, position);
                
        this.createClasses();

        if(this.el){ // existing markup
            this.el.addClass(this.baseCls);
            this.header = this.el.down('.'+this.headerCls);
            this.bwrap = this.el.down('.'+this.bwrapCls);
            var cp = this.bwrap ? this.bwrap : this.el;
            this.tbar = cp.down('.'+this.tbarCls);
            this.body = cp.down('.'+this.bodyCls);
            this.bbar = cp.down('.'+this.bbarCls);
            this.footer = cp.down('.'+this.footerCls);
            this.fromMarkup = true;
        }else{
            this.el = ct.createChild({
                id: this.id,
                cls: this.baseCls
            }, position);
        }
        var el = this.el, d = el.dom;

        if(this.cls){
            this.el.addClass(this.cls);
        }

        if(this.buttons){
            this.elements += ',footer';
        }

        // This block allows for maximum flexibility and performance when using existing markup

        // framing requires special markup
        if(this.frame){
            el.insertHtml('afterBegin', String.format(Ext.Element.boxMarkup, this.baseCls));

            this.createElement('header', d.firstChild.firstChild.firstChild);
            this.createElement('bwrap', d);

            // append the mid and bottom frame to the bwrap
            var bw = this.bwrap.dom;
            var ml = d.childNodes[1], bl = d.childNodes[2];
            bw.appendChild(ml);
            bw.appendChild(bl);

            var mc = bw.firstChild.firstChild.firstChild;
            this.createElement('tbar', mc);
            this.createElement('body', mc);
            this.createElement('bbar', mc);
            this.createElement('footer', bw.lastChild.firstChild.firstChild);

            if(!this.footer){
                this.bwrap.dom.lastChild.className += ' x-panel-nofooter';
            }
        }else{
            this.createElement('header', d);
            this.createElement('bwrap', d);

            // append the mid and bottom frame to the bwrap
            var bw = this.bwrap.dom;
            this.createElement('tbar', bw);
            this.createElement('body', bw);
            this.createElement('bbar', bw);
            this.createElement('footer', bw);

            if(!this.header){
                this.body.addClass(this.bodyCls + '-noheader');
                if(this.tbar){
                    this.tbar.addClass(this.tbarCls + '-noheader');
                }
            }
        }

        if(this.border === false){
            this.el.addClass(this.baseCls + '-noborder');
            this.body.addClass(this.bodyCls + '-noborder');
            if(this.header){
                this.header.addClass(this.headerCls + '-noborder');
            }
            if(this.footer){
                this.footer.addClass(this.footerCls + '-noborder');
            }
            if(this.tbar){
                this.tbar.addClass(this.tbarCls + '-noborder');
            }
            if(this.bbar){
                this.bbar.addClass(this.bbarCls + '-noborder');
            }
        }

        if(this.bodyBorder === false){
           this.body.addClass(this.bodyCls + '-noborder');
        }

        if(this.bodyStyle){
           this.body.applyStyles(this.bodyStyle);
        }

        this.bwrap.enableDisplayMode('block');

        if(this.header){
            this.header.unselectable();

            // for tools, we need to wrap any existing header markup
            if(this.headerAsText){
                this.header.dom.innerHTML =
                    '<span class="' + this.headerTextCls + '">'+this.header.dom.innerHTML+'</span>';

                if(this.iconCls){
                    this.setIconClass(this.iconCls);
                }
            }
        }

        if(this.floating){
            this.makeFloating(this.floating);
        }

        if(this.collapsible){
            this.tools = this.tools ? this.tools.slice(0) : [];
            if(!this.hideCollapseTool){
                this.tools[this.collapseFirst?'unshift':'push']({
                    id: 'toggle',
                    handler : this.toggleCollapse,
                    scope: this
                });
            }
            if(this.titleCollapse && this.header){
                this.header.on('click', this.toggleCollapse, this);
                this.header.setStyle('cursor', 'pointer');
            }
        }
        if(this.tools){
            var ts = this.tools;
            this.tools = {};
            this.addTool.apply(this, ts);
        }else{
            this.tools = {};
        }

        if(this.buttons && this.buttons.length > 0){
            // tables are required to maintain order and for correct IE layout
            var tb = this.footer.createChild({cls:'x-panel-btns-ct', cn: {
                cls:"x-panel-btns x-panel-btns-"+this.buttonAlign,
                html:'<table cellspacing="0"><tbody><tr></tr></tbody></table><div class="x-clear"></div>'
            }}, null, true);
            var tr = tb.getElementsByTagName('tr')[0];
            for(var i = 0, len = this.buttons.length; i < len; i++) {
                var b = this.buttons[i];
                var td = document.createElement('td');
                td.className = 'x-panel-btn-td';
                b.render(tr.appendChild(td));
            }
        }

        if(this.tbar && this.topToolbar){
            if(this.topToolbar instanceof Array){
                this.topToolbar = new Ext.Toolbar(this.topToolbar);
            }
            this.topToolbar.render(this.tbar);
        }
        if(this.bbar && this.bottomToolbar){
            if(this.bottomToolbar instanceof Array){
                this.bottomToolbar = new Ext.Toolbar(this.bottomToolbar);
            }
            this.bottomToolbar.render(this.bbar);
        }
    },

    /**
     * Sets the CSS class that provides the icon image for this panel.  This method will replace any existing
     * icon class if one has already been set.
     * @param {String} cls The new CSS class name
     */
    setIconClass : function(cls){
        var old = this.iconCls;
        this.iconCls = cls;
        if(this.rendered && this.header){
            if(this.frame){
                this.header.addClass('x-panel-icon');
                this.header.replaceClass(old, this.iconCls);
            }else{
                var hd = this.header.dom;
                var img = hd.firstChild && String(hd.firstChild.tagName).toLowerCase() == 'img' ? hd.firstChild : null;
                if(img){
                    Ext.fly(img).replaceClass(old, this.iconCls);
                }else{
                    Ext.DomHelper.insertBefore(hd.firstChild, {
                        tag:'img', src: Ext.BLANK_IMAGE_URL, cls:'x-panel-inline-icon '+this.iconCls
                    });
                 }
            }
        }
    },

    // private
    makeFloating : function(cfg){
        this.floating = true;
        this.el = new Ext.Layer(
            typeof cfg == 'object' ? cfg : {
                shadow: this.shadow !== undefined ? this.shadow : 'sides',
                shadowOffset: this.shadowOffset,
                constrain:false,
                shim: this.shim === false ? false : undefined
            }, this.el
        );
    },

    /**
     * Returns the toolbar from the top (tbar) section of the panel.
     * @return {Ext.Toolbar} The toolbar
     */
    getTopToolbar : function(){
        return this.topToolbar;
    },

    /**
     * Returns the toolbar from the bottom (bbar) section of the panel.
     * @return {Ext.Toolbar} The toolbar
     */
    getBottomToolbar : function(){
        return this.bottomToolbar;
    },

    /**
     * Adds a button to this panel.  Note that this method must be called prior to rendering.  The preferred
     * approach is to add buttons via the {@link #buttons} config.
     * @param {String/Object} config A valid {@link Ext.Button} config.  A string will become the text for a default
     * button config, an object will be treated as a button config object.
     * @param {Function} handler The function to be called on button {@link Ext.Button#click}
     * @param {Object} scope The scope to use for the button handler function
     * @return {Ext.Button} The button that was added
     */
    addButton : function(config, handler, scope){
        var bc = {
            handler: handler,
            scope: scope,
            minWidth: this.minButtonWidth,
            hideParent:true
        };
        if(typeof config == "string"){
            bc.text = config;
        }else{
            Ext.apply(bc, config);
        }
        var btn = new Ext.Button(bc);
        if(!this.buttons){
            this.buttons = [];
        }
        this.buttons.push(btn);
        return btn;
    },

    // private
    addTool : function(){
        if(!this[this.toolTarget]) { // no where to render tools!
            return;
        }
        if(!this.toolTemplate){
            // initialize the global tool template on first use
            var tt = new Ext.Template(
                 '<div class="x-tool x-tool-{id}">&#160;</div>'
            );
            tt.disableFormats = true;
            tt.compile();
            Ext.Search.prototype.toolTemplate = tt;
        }
        for(var i = 0, a = arguments, len = a.length; i < len; i++) {
            var tc = a[i], overCls = 'x-tool-'+tc.id+'-over';
            var t = this.toolTemplate.insertFirst(this[this.toolTarget], tc, true);
            this.tools[tc.id] = t;
            t.enableDisplayMode('block');
            t.on('click', this.createToolHandler(t, tc, overCls, this));
            if(tc.on){
                t.on(tc.on);
            }
            if(tc.hidden){
                t.hide();
            }
            if(tc.qtip){
                if(typeof tc.qtip == 'object'){
                    Ext.QuickTips.register(Ext.apply({
                          target: t.id
                    }, tc.qtip));
                } else {
                    t.dom.qtip = tc.qtip;
                }
            }
            t.addClassOnOver(overCls);
        }
    },

    // private
    onShow : function(){
        if(this.floating){
            return this.el.show();
        }
        Ext.Search.superclass.onShow.call(this);
    },

    // private
    onHide : function(){
        if(this.floating){
            return this.el.hide();
        }
        Ext.Search.superclass.onHide.call(this);
    },

    // private
    createToolHandler : function(t, tc, overCls, panel){
        return function(e){
            t.removeClass(overCls);
            e.stopEvent();
            if(tc.handler){
                tc.handler.call(tc.scope || t, e, t, panel);
            }
        };
    },
    
    //  to get the renderTo
    renderDiv : '',
    
    //  to save the last query filter
    lastQueryFilter : '1=1 ',
    
    //  to save the last query text
    lastQueryText : '',
    
    // private
    showSelect : function(StringArray, selectid){
		var selecttag = document.getElementById(selectid);
		//var e=1;
		//var selecttag = eval(e+"=5");
		//alert(e)
		var i, iArrayCount = 0;
		while(StringArray[0][iArrayCount]!=null){
			iArrayCount++; //the count of StringArray
		}
		selecttag.options.length = iArrayCount;	//to change the size of the select tag
		for (i=0;i<=iArrayCount-1;i++){	//to change the content of the select tag according to the StringArray
			selecttag.options[i]=new Option(StringArray[1][i],StringArray[0][i]);
			if(i == 0)
				selecttag.options[i].selected = true;
		}
	},
    // private
    afterRender : function(){
    	this.renderDiv = this.renderTo;
    
        if(this.fromMarkup && this.height === undefined && !this.autoHeight){
            this.height = this.el.getHeight();
        }
        if(this.floating && !this.hidden && !this.initHidden){
            this.el.show();
        }
        if(this.title){
            this.setTitle(this.title);
        }
		this.setAutoScroll();
        if(this.html==undefined){
            this.html="";
        }else{
        	this.body.update(typeof this.html == 'object' ?
                             Ext.DomHelper.markup(this.html) :
                             this.html);
        }
        this.html+="<p>&nbsp;</p><table border='0' style='width:500'>"+
		 "<tr><td>&nbsp;</td><td colspan='3'><input type='text' name='"+ this.renderTo +"querytext' id='"+ this.renderTo +"querytext' style='width:270'></td><td><input type='button' id='"+ this.renderTo +"querybn' name='"+ this.renderTo +"querybn' style='width:60' class='button' value='查 询'>"+
		 "&nbsp;&nbsp;&nbsp;<input type='checkbox' name='"+ this.renderTo +"resultquery' value='resultquery'>在结果中查询</td></tr>"+
		 "<tr><td width='45px'>&nbsp;</td><td width='65px'>查询类型：</td><td width='115px' align='center'>"+
		 "<select name='"+ this.renderTo +"querytype' id='"+ this.renderTo +"querytype' size='1'><option value='tempquery' selected>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option></select></td>"+
		 "<td id='"+ this.renderTo +"stagetext' width='90px' align='center'>";
		if(this.state == true){
			this.html += "记录类型：</td><td id='"+ this.renderTo +"stageselect' width='200px'><select name='"+ this.renderTo +"filestage' id='"+ this.renderTo +"filestage' size='1'><option value='tempstage' selected>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option></select>";
		}else{
			this.html += "&nbsp;</td><td id='"+ this.renderTo +"stageselect' width='200px'>&nbsp;";
		}
		this.html+= "</td></tr><tr><td>&nbsp;</td><td>检索模式：</td><td align='center'>"+
				  "<input name='"+ this.renderTo +"indexmode' type='radio' value='front' CHECKED>前方一致</td>"+
				  "<td><input name='"+ this.renderTo +"indexmode' type='radio' value='exact'>完全匹配</td>"+
				  "<td><input name='"+ this.renderTo +"indexmode' type='radio' value='fuzzy'>模糊匹配</td></tr>"+
				  "<tr><td>&nbsp;</td><td>排序显示：</td><td align='center'><select name='"+ this.renderTo +"ordertype' id='"+ this.renderTo +"ordertype' size='1'>"+
				  "<option value='temporder' selected>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>"+
				  "</select></td><td><input name='"+ this.renderTo +"order' type='radio' value='ASC' CHECKED>升序排列</td>"+
				  "<td><input name='"+ this.renderTo +"order' type='radio' value='DESC'>降序排列</td></tr></table>"+
				  "<div id='"+ this.renderTo +"tipdiv' style='position:absolute;left:520px;top:10px;'>"+
				  "<span id='"+ this.renderTo +"tipspan' style='font-size:13px;color:#0080ff'>&nbsp;</span></div>";			 
        this.body.update(this.html);
        delete this.html;
        var qstring = this.renderTo+"querytype";
		var sstring = this.renderTo+"filestage";
		var ostring = this.renderTo+"ordertype";
        if(this.columnArray){
        	this.showSelect(this.columnArray, qstring);
        	//alert(this.columnArray[0]);
  			//if(stage_mark == 1)
        }
		if(this.stageArray && this.state == 1){
        	//alert(this.stageArray);
        	this.showSelect(this.stageArray, sstring);
        }
        if(this.orderArray){
        	//alert(this.orderArray);
        	this.showSelect(this.orderArray, ostring);
        }
        if(this.contentEl){
            var ce = Ext.getDom(this.contentEl);
            Ext.fly(ce).removeClass(['x-hidden', 'x-hide-display']);
            this.body.dom.appendChild(ce);
        }
        if(this.collapsed){
            this.collapsed = false;
            this.collapse(false);
        } 
        if(this.handler){
        	Ext.get(this.renderTo+"querybn").on('click',this.handler);
        }
        Ext.Search.superclass.afterRender.call(this); // do sizing calcs last
        this.initEvents();
    },

    // private
    setAutoScroll : function(){
        if(this.rendered && this.autoScroll){
			this.body.setOverflow('auto');
        }
    },

    // private
    getKeyMap : function(){
        if(!this.keyMap){
            this.keyMap = new Ext.KeyMap(this.el, this.keys);
        }
        return this.keyMap;
    },

    // private
    initEvents : function(){
        if(this.keys){
            this.getKeyMap();
        }
        if(this.draggable){
            this.initDraggable();
        }
    },

    // private
    initDraggable : function(){
        this.dd = new Ext.Search.DD(this, typeof this.draggable == 'boolean' ? null : this.draggable);
    },

    // private
    beforeEffect : function(){
        if(this.floating){
            this.el.beforeAction();
        }
        this.el.addClass('x-panel-animated');
    },

    // private
    afterEffect : function(){
        this.syncShadow();
        this.el.removeClass('x-panel-animated');
    },

    // private - wraps up an animation param with internal callbacks
    createEffect : function(a, cb, scope){
        var o = {
            scope:scope,
            block:true
        };
        if(a === true){
            o.callback = cb;
            return o;
        }else if(!a.callback){
            o.callback = cb;
        }else { // wrap it up
            o.callback = function(){
                cb.call(scope);
                Ext.callback(a.callback, a.scope);
            };
        }
        return Ext.applyIf(o, a);
    },

    /**
     * Collapses the panel body so that it becomes hidden.  Fires the {@link #beforecollapse} event which will
     * cancel the collapse action if it returns false.
     * @param {Boolean} animate True to animate the transition, else false (defaults to the value of the
     * {@link #animCollapse} panel config)
     * @return {Ext.Search} this
     */
    collapse : function(animate){
        if(this.collapsed || this.el.hasFxBlock() || this.fireEvent('beforecollapse', this, animate) === false){
            return;
        }
        var doAnim = animate === true || (animate !== false && this.animCollapse);
        this.beforeEffect();
        this.onCollapse(doAnim, animate);
        return this;
    },

    // private
    onCollapse : function(doAnim, animArg){
        if(doAnim){
            this[this.collapseEl].slideOut(this.slideAnchor,
                    Ext.apply(this.createEffect(animArg||true, this.afterCollapse, this),
                        this.collapseDefaults));
        }else{
            this[this.collapseEl].hide();
            this.afterCollapse();
        }
    },

    // private
    afterCollapse : function(){
        this.collapsed = true;
        this.el.addClass(this.collapsedCls);
        this.afterEffect();
        this.fireEvent('collapse', this);
    },

    /**
     * Expands the panel body so that it becomes visible.  Fires the {@link #beforeexpand} event which will
     * cancel the expand action if it returns false.
     * @param {Boolean} animate True to animate the transition, else false (defaults to the value of the
     * {@link #animCollapse} panel config)
     * @return {Ext.Search} this
     */
    expand : function(animate){
        if(!this.collapsed || this.el.hasFxBlock() || this.fireEvent('beforeexpand', this, animate) === false){
            return;
        }
        var doAnim = animate === true || (animate !== false && this.animCollapse);
        this.el.removeClass(this.collapsedCls);
        this.beforeEffect();
        this.onExpand(doAnim, animate);
        return this;
    },

    // private
    onExpand : function(doAnim, animArg){
        if(doAnim){
            this[this.collapseEl].slideIn(this.slideAnchor,
                    Ext.apply(this.createEffect(animArg||true, this.afterExpand, this),
                        this.expandDefaults));
        }else{
            this[this.collapseEl].show();
            this.afterExpand();
        }
    },

    // private
    afterExpand : function(){
        this.collapsed = false;
        this.afterEffect();
        this.fireEvent('expand', this);
    },

    /**
     * Shortcut for performing an {@link #expand} or {@link #collapse} based on the current state of the panel.
     * @param {Boolean} animate True to animate the transition, else false (defaults to the value of the
     * {@link #animCollapse} panel config)
     * @return {Ext.Search} this
     */
    toggleCollapse : function(animate){
        this[this.collapsed ? 'expand' : 'collapse'](animate);
        return this;
    },

    // private
    onDisable : function(){
        if(this.rendered && this.maskDisabled){
            this.el.mask();
        }
        Ext.Search.superclass.onDisable.call(this);
    },

    // private
    onEnable : function(){
        if(this.rendered && this.maskDisabled){
            this.el.unmask();
        }
        Ext.Search.superclass.onEnable.call(this);
    },

    // private
    onResize : function(w, h){
        if(w !== undefined || h !== undefined){
            if(!this.collapsed){
                if(typeof w == 'number'){
                    this.body.setWidth(
                            this.adjustBodyWidth(w - this.getFrameWidth()));
                }else if(w == 'auto'){
                    this.body.setWidth(w);
                }

                if(typeof h == 'number'){
                    this.body.setHeight(
                            this.adjustBodyHeight(h - this.getFrameHeight()));
                }else if(h == 'auto'){
                    this.body.setHeight(h);
                }
            }else{
                this.queuedBodySize = {width: w, height: h};
                if(!this.queuedExpand && this.allowQueuedExpand !== false){
                    this.queuedExpand = true;
                    this.on('expand', function(){
                        delete this.queuedExpand;
                        this.onResize(this.queuedBodySize.width, this.queuedBodySize.height);
                        this.doLayout();
                    }, this, {single:true});
                }
            }
            this.fireEvent('bodyresize', this, w, h);
        }
        this.syncShadow();
    },

    // private
    adjustBodyHeight : function(h){
        return h;
    },

    // private
    adjustBodyWidth : function(w){
        return w;
    },

    // private
    onPosition : function(){
        this.syncShadow();
    },

    // private
    onDestroy : function(){
        if(this.tools){
            for(var k in this.tools){
                Ext.destroy(this.tools[k]);
            }
        }
        if(this.buttons){
            for(var b in this.buttons){
                Ext.destroy(this.buttons[b]);
            }
        }
        Ext.destroy(
            this.topToolbar,
            this.bottomToolbar
        );
        Ext.Search.superclass.onDestroy.call(this);
    },

    /**
     * Returns the width in pixels of the framing elements of this panel (not including the body width).  To
     * retrieve the body width see {@link #getInnerWidth}.
     * @return {Number} The frame width
     */
    getFrameWidth : function(){
        var w = this.el.getFrameWidth('lr');

        if(this.frame){
            var l = this.bwrap.dom.firstChild;
            w += (Ext.fly(l).getFrameWidth('l') + Ext.fly(l.firstChild).getFrameWidth('r'));
            var mc = this.bwrap.dom.firstChild.firstChild.firstChild;
            w += Ext.fly(mc).getFrameWidth('lr');
        }
        return w;
    },

    /**
     * Returns the height in pixels of the framing elements of this panel (including any top and bottom bars and
     * header and footer elements, but not including the body height).  To retrieve the body height see {@link #getInnerHeight}.
     * @return {Number} The frame height
     */
    getFrameHeight : function(){
        var h  = this.el.getFrameWidth('tb');
        h += (this.tbar ? this.tbar.getHeight() : 0) +
             (this.bbar ? this.bbar.getHeight() : 0);

        if(this.frame){
            var hd = this.el.dom.firstChild;
            var ft = this.bwrap.dom.lastChild;
            h += (hd.offsetHeight + ft.offsetHeight);
            var mc = this.bwrap.dom.firstChild.firstChild.firstChild;
            h += Ext.fly(mc).getFrameWidth('tb');
        }else{
            h += (this.header ? this.header.getHeight() : 0) +
                (this.footer ? this.footer.getHeight() : 0);
        }
        return h;
    },

    /**
     * Returns the width in pixels of the body element (not including the width of any framing elements).
     * For the frame width see {@link #getFrameWidth}.
     * @return {Number} The body width
     */
    getInnerWidth : function(){
        return this.getSize().width - this.getFrameWidth();
    },

    /**
     * Returns the height in pixels of the body element (not including the height of any framing elements).
     * For the frame height see {@link #getFrameHeight}.
     * @return {Number} The body height
     */
    getInnerHeight : function(){
        return this.getSize().height - this.getFrameHeight();
    },

    // private
    syncShadow : function(){
        if(this.floating){
            this.el.sync(true);
        }
    },

    // private
    getLayoutTarget : function(){
        return this.body;
    },

    /**
     * Sets the title text for the panel and optionally the icon class.
     * @param {String} title The title text to set
     * @param {String} (optional) iconCls A custon, user-defined CSS class that provides the icon image for this panel
     */
    setTitle : function(title, iconCls){
        this.title = title;
        if(this.header && this.headerAsText){
            this.header.child('span').update(title);
        }
        if(iconCls){
            this.setIconClass(iconCls);
        }
        this.fireEvent('titlechange', this, title);
        return this;
    },

    /**
     * Get the {@link Ext.Updater} for this panel. Enables you to perform Ajax updates of this panel's body.
     * @return {Ext.Updater} The Updater
     */
    getUpdater : function(){
        return this.body.getUpdater();
    },

     /**
     * Loads this content panel immediately with content returned from an XHR call.
     * @param {Object/String/Function} config A config object containing any of the following options:
<pre><code>
panel.load({
    url: "your-url.php",
    params: {param1: "foo", param2: "bar"}, // or a URL encoded string
    callback: yourFunction,
    scope: yourObject, // optional scope for the callback
    discardUrl: false,
    nocache: false,
    text: "Loading...",
    timeout: 30,
    scripts: false
});
</code></pre>
     * The only required property is url. The optional properties nocache, text and scripts
     * are shorthand for disableCaching, indicatorText and loadScripts and are used to set their
     * associated property on this panel Updater instance.
     * @return {Ext.Search} this
     */
    load : function(){
        var um = this.body.getUpdater();
        um.update.apply(um, arguments);
        return this;
    },

    // private
    beforeDestroy : function(){
        Ext.Element.uncache(
            this.header,
            this.tbar,
            this.bbar,
            this.footer,
            this.body
        );
    },

    // private
    createClasses : function(){
        this.headerCls = this.baseCls + '-header';
        this.headerTextCls = this.baseCls + '-header-text';
        this.bwrapCls = this.baseCls + '-bwrap';
        this.tbarCls = this.baseCls + '-tbar';
        this.bodyCls = this.baseCls + '-body';
        this.bbarCls = this.baseCls + '-bbar';
        this.footerCls = this.baseCls + '-footer';
    },

    // private
    createGhost : function(cls, useShim, appendTo){
        var el = document.createElement('div');
        el.className = 'x-panel-ghost ' + (cls ? cls : '');
        if(this.header){
            el.appendChild(this.el.dom.firstChild.cloneNode(true));
        }
        Ext.fly(el.appendChild(document.createElement('ul'))).setHeight(this.bwrap.getHeight());
        el.style.width = this.el.dom.offsetWidth + 'px';;
        if(!appendTo){
            this.container.dom.appendChild(el);
        }else{
            Ext.getDom(appendTo).appendChild(el);
        }
        if(useShim !== false && this.el.useShim !== false){
            var layer = new Ext.Layer({shadow:false, useDisplay:true, constrain:false}, el);
            layer.show();
            return layer;
        }else{
            return new Ext.Element(el);
        }
    },

    // private
    doAutoLoad : function(){
        this.body.load(
            typeof this.autoLoad == 'object' ?
                this.autoLoad : {url: this.autoLoad});
    },
    
    queryrequest : function(){
		//parent.bottomframe.ds.load({params:{type:"QUERY",filter:queryMod.getFilter()}});
	},
	
	//显示已查询关键字
	getQueryText : function(){
		
		var string = [];
		string[0] = document.getElementById(this.renderDiv+"querytext").value;
		if(string[0] == ""){
			string[0] = "所有";
		}
		
		var selectobj = document.getElementById(this.renderDiv+"querytype");
		var index = selectobj.selectedIndex;
		string[1] = selectobj.options[index].text;
		
		if(this.state == true){
			selectobj = document.getElementById(this.renderDiv+"filestage");
			index = selectobj.selectedIndex;
			string[2] = selectobj.options[index].text;
		}else
			string[2] = "";
		
		var spantext = "";
		var resultquery = document.getElementsByName(this.renderDiv+'resultquery');
		
		if(resultquery[0].checked == true)
			spantext = this.lastQueryText + "<br>";
			
		spantext += string[1] + "：" + string[0] + "&nbsp;&nbsp;&nbsp;";
		if(this.state == true)
			spantext += "文件状态：" + string[2];
		
		this.lastQueryText = spantext;
		document.getElementById(this.renderDiv+"tipspan").innerHTML = "已查询的关键词：<p>" + spantext;
		return spantext;

	},
	
    getFilter : function(){
    	    		
		var filter;
		var string = [];
		var mark = "state ";
		var i;
		var resultquery = document.getElementsByName(this.renderDiv+'resultquery');
		var indexmode = document.getElementsByName(this.renderDiv+'indexmode');
	
		string[0] = document.getElementById(this.renderDiv+"querytext").value;
		string[1] = document.getElementById(this.renderDiv+"querytype").value;
		
		for(i=0; i<indexmode.length; i++){
			if(indexmode[i].checked == true)
				string[2] = indexmode[i].value;
		}
		if(this.state == true){
			string[5] = document.getElementById(this.renderDiv+"filestage").value;
			var iCount = 0;
			for(i=0; this.stageArray[0][i]!=null ; i++){
				if(string[5] == this.stageArray[0][i])
					iCount = i;
			}
			if(this.stageArray[2][iCount].toUpperCase() == "NULL")
				mark += "is NULL";
			else
				mark += "= '" + this.stageArray[2][iCount] + "' ";
		}else
			string[5] = "all";
		
		//是否二次查询
		if(resultquery[0].checked == true)
			filter = this.lastQueryFilter;
		else
			filter = "1=1 ";
			
				
		if(string[0] == ""){   //to query all of file without any content.
			if(string[5] != "all"){
				filter += "and " + mark;
			}
		}else{
			if(string[5] == "all"){
				if(string[2] == 'front')
					filter += "and " + string[1] + " like '" + string[0] + "%' ";
				if(string[2] == 'exact')
					filter += "and " + string[1] + " = '" + string[0] + "' ";
				if(string[2] == 'fuzzy')
					filter += "and " + string[1] + " like '%" + string[0] + "%' ";
			}
			else{
				if(string[2] == 'front')
					filter += "and " + string[1] + " like '" + string[0] + "%' and " + mark ;
				if(string[2] == 'exact')
					filter += "and " + string[1] + " = '" + string[0] + "' and " + mark ;
				if(string[2] == 'fuzzy')
					filter += "and " + string[1] + " like '%" + string[0] + "%' and " + mark ;
			}
		}
		
		this.lastQueryFilter = filter;
		//alert(filter);
		return filter;
	},
	
    getOrder : function(){
    	    
		var orderValue="";
		var order = document.getElementsByName(this.renderDiv+'order');
		orderValue = document.getElementById(this.renderDiv+"ordertype").value;
		for(var i=0; i<order.length; i++){
			if(order[i].checked == true){
				orderValue +=" "+order[i].value;
				break;
			}
		}
		return orderValue;
	},
	
	//仅留下一个值为value的下拉选项
	reserveOption : function(value){
		var d=document.getElementById(this.renderDiv+"filestage");
		for(var i=0;i<d.options.length;i++){	
			if(d.options[i].value==value){
				var reserveValue=d[i].value;
				var reserveText=d[i].text;
				d.options.length=0;	
				d.options.add(new Option(reserveText,reserveValue));
			}
		}
	},
	
	getFullFilter : function(){
		var filter = this.getFilter();
		var orderstring = this.getOrder();
		filter += " ORDER BY " + orderstring;
		return filter;
	}
});
Ext.reg('search', Ext.Search);



/*
Ext.search=function(el,settings) {
	this.searchEl;
	this.columnArray;
	this.stageArray;
	this.orderArray;
	this.state;
	Ext.apply(this,settings);
	if(el){
		this.searchEl=Ext.get(el);
		this.init();
	}
};
Ext.extend(Ext.vessel,Ext.util.Observable,{
	init:function(){
		this.searchEl.dom.innerHTML="<p>&nbsp;</p><table border='0' style='width:500'>"+
				 "<tr><td>&nbsp;</td><td colspan='3'><input type='text' name='querytext' id='querytext' style='width:250'></td><td><input type='button' name='querybn' value='查 询' onclick=queryMod.queryrequest()></td></tr>"+
				 "<tr><td width='10%'>&nbsp;</td><td width='20%'>查询类型：</td><td width='25%'>"+
				 "<select name='querytype' id='querytype' size='1'><option value='tempquery' selected>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option></select></td>"+
				 "<td id='stagetext' width='20%' align='center'>";
		if(this.state == 1){
			this.searchEl.dom.innerHTML += "文件状态：</td><td id='stageselect' width='25%'><select name='filestage' id='filestage' size='1'><option value='tempstage' selected>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option></select>";
		}
		this.searchEl.dom.innerHTML += "</td><td id='stageselect' width='25%'></td></tr><tr><td>&nbsp;</td><td>检索模式：</td><td align='center'>"+
				  "<input name='indexmode' type='radio' value='front' CHECKED>前方一致</td>"+
				  "<td><input name='indexmode' type='radio' value='exact'>完全匹配</td>"+
				  "<td><input name='indexmode' type='radio' value='fuzzy'>模糊匹配</td></tr>"+
				  "<tr><td>&nbsp;</td><td>排序显示：</td><td><select name='ordertype' id='ordertype' size='1'>"+
				  "<option value='temporder' selected>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>"+
				  "</select></td><td><input name='order' type='radio' value='ASC' CHECKED>升序排列</td>"+
				  "<td><input name='order' type='radio' value='DESC'>降序排列</td></tr></table>";
	}
});
*/
//以下为对系统的修复
Ext.tree.TreeNodeUI.prototype.onCheckChange=function(){
	
}

/**
*   函数: 调用jsp并返回xml
*  参数:
*		对象格式参数: 建议用这种格式，对参数顺序没有要求，返回的是json格式
*			callService({url: 'xxx/xxx.jsp', param: {param1: 'v1', param2: 'v2'}, scope: this, args: {myargs1: value1}, callback: function(message){alert(message.type)}, timeout:1000 });
*			同时要求服务器端返回json格式, 如：out.println("{Message: {type: "true", info: "成功提交"}}");
*			
*		
*		普通格式:
*		sURL: jsp的url
*		param: form对象或js object或js array, 如document.getElementById("myForm"),
*					或{param1: "value1", param2: "value2"}, 或param["param1"] = "value1";param["param2"] = "value2"
*		callback: 回调函数, 如function (xmlDocument, args) {alert(XmlDocument.xml)}
*		[scope]: 可选, 函数范围, 如function (xmlDocument, args) {alert(this)} //this等于scope
*		[args]: 可选, 附加参数, 如function (xmlDocument, args) {alert(args)}
*/ 

function callService(){

	var sURL, param, callback, scope, args, reader, timeout = 30000;
	//如果参数是一个参数对象的话
	if (arguments.length == 1 && typeof(arguments[0]) == 'object')
	{
		var config = arguments[0];
		sURL = config.url;
		param = config.param;
		callback = config.callback;
		scope = config.scope;
		args = config.args;
		reader = config.reader;
		if (config.timeout != undefined) timeout = config.timeout;
		if (reader == null || reader == undefined) reader = new Epx.jsonReader;
	}
	else//否则参数为通常的列表参数
	{
		sURL = arguments[0];
		param = arguments[1];
		callback = arguments[2];
		scope = arguments[3];
		args = arguments[4];
		if (arguments[5] != undefined) timeout = arguments[5];
	}
	var objParam;
	if (param.nodeName && param.nodeName == "FORM"){
	}
	else if (param.length){
		objParam = param.join("&");
	}
	else{
		objParam = param;
	}
	xhr = Ext.lib.Ajax.getConnectionObject();
	xhr.conn.open("POST", sURL, false);
	xhr.conn.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var postData =  Ext.urlEncode(param);
	xhr.conn.send(postData);
	if (xhr.conn.responseText){
		return xhr.conn.responseText;
	}else{
		return null;
	}		
}