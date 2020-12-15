
var assesstf; //记录打分的textfield的ID

Ext.onReady(function(){
			
	Ext.QuickTips.init();//开启表单提示
	Ext.form.Field.prototype.msgTarget = 'side';//设置提示信息位置为边上
	
	var featuredoc = [['1','上轧辊总成'],['2','润滑系统'],['3','冷却辊总成'],['4','液压系统'],['5','传动系统'],['6','机架总成'],['7','下轧辊总成'],['8','限位调节装置']];
    var featuretype = new Ext.data.SimpleStore({fields:['value','text'],data:featuredoc});
    var featurecombox = new Ext.form.ComboBox({
    	id:"feature",
    	name:"feature",
    	fieldLabel:'部件名称',
    	displayField:'text',
    	lazyRender:true,
    	emptyText:'请选择要打分的部件',
    	width:140,
		store: featuretype,
		typeAhead: true,
		triggerAction: 'all',
		selectOnFocus:true,
    // allowBlank:false,
		mode:'local',
		editable:false	
    });
    
    var add_win = new Ext.Window({
		id:'add_win',
		modal:true,                                         
        width:300,
        height:180,
        closeAction:'hide',
        plain: true,
        buttonAlign:"center",
        autoShow: true,
		bodyBorder:true,
		resizable:false,		
		items:[new Ext.Panel({
        		layout:'column',
        		frame:true,
        		height:100,                		
				items:[{		                
		                columnWidth:1,	
		                labelWidth:85,
		                id:'add_left',				              
		                layout: 'form',
		                bodyStyle:'padding:41px 0px 4px',				            
		                labelAlign:"right",		               
		                items: [featurecombox]
		           }]
        })],
        buttons: [{
                id:'nextbtn',
                text:'下一步',		           
                handler:winShowFn
                },{ id:'cancelbtn1',
				text: '取消',
				handler:add_cancelFn
            }]	                
});
   
    add_win.setTitle("<p align='center'>请选择打分特征</p>");
    add_win.show();
    
   
    //取消函数
    function add_cancelFn(){
    	add_win.hide();
    }
    
  //定义打分选择面板弹出窗口
    var assesswin = new Ext.Window({
    			id:'assesswin',
    			modal: false,
                width:200,
                height:160,
                layout:'fit',
                closeAction:'hide',
                plain: true,
                title:"<p align='center'>分数选择面板</p>",
                buttonAlign:"center",
				bodyBorder:true,
                items:[new Ext.Panel({
                		layout:'column',
                		frame:true,
                		items:[{
								columnWidth:.25,
								bodyStyle:'padding:2px 2px 0', 
								layout: 'form',
                				border:false,
                				items: [
                					new Ext.Button({
								    text:'&nbsp;1&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'&nbsp;5&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'&nbsp;9&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								})/*,new Ext.Button({
								    text:'1/2',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'1/6',
								    handler: function(){
								    	assessfn(this.text);
								    }
								})*/]
							},{
								columnWidth:.25,
								bodyStyle:'padding:2px 2px 0', 
								layout: 'form',
                				border:false,
                				items: [
                					new Ext.Button({
								    text:'&nbsp;2&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'&nbsp;6&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
								})/*,new Ext.Button({
								    text:'1/3',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'1/7',
								    handler: function(){
								    	assessfn(this.text);
								    }
								})*/]
							},{
								columnWidth:.25,
								bodyStyle:'padding:2px 2px 0', 
								layout: 'form',
                				border:false,
                				items: [
                					new Ext.Button({
								    text:'&nbsp;3&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'&nbsp;7&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
								})/*,new Ext.Button({
								    text:'1/4',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'1/8',
								    handler: function(){
								    	assessfn(this.text);
								    }
								})*/]
							},{
								columnWidth:.25,
								bodyStyle:'padding:2px 2px 0', 
								layout: 'form',
                				border:false,
                				items: [
                					new Ext.Button({
								    text:'&nbsp;4&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'&nbsp;8&nbsp;&nbsp;',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
								})/*,new Ext.Button({
								    text:'1/5',
								    handler: function(){
								    	assessfn(this.text);
								    }
								}),new Ext.Button({
								    text:'1/9',
								    handler: function(){
								    	assessfn(this.text);
								    }
								})*/]
							}
                		]
                })]
    });
		  //focus事件
		    function showassesswin(tfid){
		    	//alert(tfid);
		    	assesstf = tfid;
		    	assesswin.show();
		    }
		    
		    function assessfn(text){
		    	var st = text;
		    	var st1, st2;
		    	if(st.indexOf("&nbsp;") != -1){
		    		st1 = st.substring(6, 7);
		    		if(st1 != "1")
		    			st2 = "1/" + st1;
		    		else
		    			st2 = st1;
		    	}else{
		    		st1 = st;
		    		st2 = st.substring(st.indexOf("1/")+2, st.length);
		    	}
		    	//alert(st1+"  "+st2);
		    	
		    	var stf = assesstf;
		    	var index = stf.indexOf("2");
		    	var s1 = stf.substring(0, index);
		    	var s2 = stf.substring(index+1, stf.length);
		    	var stf2 = s2 + "2" + s1;
		    	//alert(stf2);
		    	assesswin.hide();
		    	Ext.get(stf).dom.value = st1;
		    	Ext.get(stf2).dom.value = st2;
		    }
    
    //下一步函数
   		 function winShowFn(){
	    	var feature = featurecombox.getValue();//获取下拉框中的值
	    	if(feature == '上轧辊总成'){
		    var form_title = new Ext.Panel({     
		    	  bodyStyle:'padding:0px 50px 10px', 
		    	  layout:'table',
		    	  layoutConfig: {columns: 2},
		    	  items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),       
		    	  new Ext.Panel({html:"<p align='center'></p>",width:'50',style: "margin:10,0,0,0"})
		    	    	]
		    	    });
    	    var form_row1 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	    	width:'400',
    	        layoutConfig: {columns:3},
    	        
    	    	items:[new Ext.Panel({html:"<p align='right'>答疑速度&nbsp;&nbsp;</p> ",width:'100',style: "margin:0,0,0,0" }),
    	    	       new Ext.Panel({html:"<div id='star' ></div>",width:'150' }),
    	    	      new Ext.Panel({html:"<div id='coment'>请打分</div>",width:'100'})
    	
    	    	]});	
    		var form_row2 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	    	width:'400',
    	        layoutConfig: {columns: 3},
    	    	items:[new Ext.Panel({html:"<p align='right'>答疑效果&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	              new Ext.Panel({html:"<div id='star1' ></div>",width:'150' }),
    	    	      new Ext.Panel({html:"<div id='coment1'>请打分</div>",width:'100'})
    	    	]
    	    });	
    	  var form_row3  = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	    	width:'400',
    	        layoutConfig: {columns: 3},
    	    	items:[new Ext.Panel({html:"<p align='right'>服务态度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               new Ext.Panel({html:"<div id='star2' ></div>",width:'150' }),
    	    	      new Ext.Panel({html:"<div id='coment2'>请打分</div>",width:'100'})
    	    	]
    	    });  	    
    		  var form_row4 = new Ext.Panel({     
    		    	bodyStyle:'padding:5px 50px 0', 
    		    	//labelSeparator: ' ',
    		    	layout:'table',
    		    	width:'400',
    		        layoutConfig: {columns: 3},
    		    	items:[new Ext.Panel({html:"<p align='right'>产品质量&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    		               new Ext.Panel({html:"<div id='star3' ></div>",width:'150' }),
    	    	      new Ext.Panel({html:"<div id='coment3'>请打分</div>",width:'100'})
    		    	]
    		    });
    		var remark=new Ext.form.TextArea({preventScrollbars:true,fieldLabel: '留言',labelSeparator:':',id: 'remark',value:'请您留下宝贵的意见和建议。',width:374,height:200});
    		    add_win.hide();
    		 
    		 var assessform = new Ext.FormPanel({
    				bodyStyle: 'padding:10px 10px 0px',
    				region:'center',
    				//width:600,
    				//height:10,
    				frame:true,
    				layout:'form',
    				layoutConfig: {column:1 },
    				//style:'margin:0% 20%',
    				buttonAlign:"center",
    				title:"<p align='center'>专家满意度评分</p>",
    				items:[form_title,form_row1,form_row2,form_row3,form_row4,remark],
    				buttons:[{
    			       	text: '保存',
    				  	id:'save',					
    				  	handler:function(){
    				  		
    				  		//alert(a41+" "+a42+" "+a43+" "+a44);
    				  		//保存打分信息
    				  		Ext.showProgressDialog();
							Ext.Ajax.request({
								url:'logicexpertscores.jsp',
								//method:'post',
								params:{                                                                                                                                                                     
									type:'ADD',
									feature:"上轧辊总成"
								},
								success:function(resp){
									Ext.hideProgressDialog();
									if(parseInt(resp.responseText)==1){
										Ext.MessageBox.alert('提示',"保存成功");
									}else{
										Ext.MessageBox.alert('提示',"保存失败");
									}
								},
								failure:function(){
									Ext.hideProgressDialog();
									Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
								}
							});
		    				  	}
		    				  },{
		    				   	text: '重置',
		    				   	id:'reset',
		    				   	handler:function(){
		    						assessform.getForm().reset();								     								
		    						//Ext.getCmp("diameter").focus();
		    				   }		   
		    				}]
		    			});
    			
		 //材料属性权重
	/*var otherform = new Ext.FormPanel({
		region:'south',
		layout:'form',
		id:'tablepanel',
		title:"<p align='center'>其它权重打分</p>",
		height:230,
		autoScroll:true,
		frame: true,
		buttonAlign:"center",
		items:[{
			bodyStyle: 'padding:0px 180px 0',
            //width:750,
            xtype:'fieldset',
            title: '工件材料属性权重(0～1)',
            collapsible: false,
            autoHeight:true,
            layout:'column',
            //border:false,
            items:[{
            	//width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '抗拉强度权重',
                    emptyText:'0',
                    id: 'klqd',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '冲击韧性权重',
                    emptyText:'0',
                    id: 'cjrx',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料硬度权重',
                    emptyText:'0',
                    id: 'yd',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料热导率权重',
                    emptyText:'0',
                    id: 'rdl',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            }]
        },{
        	bodyStyle: 'padding:5px 400px 0',
            //width:750,
            xtype:'fieldset',
            title: '相似度对最终评价结果的影响权重(0～1)',
            collapsible: false,
            autoHeight:true,
            layout:'column',
            items:[{
            	   columnWidth:1,
            	   layout: 'form',
            	   items: [{
            		   xtype:'textfield',
                       fieldLabel: '相似度权重',
                       emptyText:'0',
                       id: 'simw',
                       width: 100,
                       regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                       regexText:'只能由数字组成!'
                   }]
               }]
        }],
		buttons:[{
	       	text: '保存',
		  	id:'save1',					
		  	handler:function(){
		  		var klqd = Ext.get("klqd").dom.value.trim();
		  		var cjrx = Ext.get("cjrx").dom.value.trim();
		  		var yd = Ext.get("yd").dom.value.trim();
		  		var rdl = Ext.get("rdl").dom.value.trim();
		  		var simw = Ext.get("simw").dom.value.trim();
		  		if(klqd==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料抗拉强度权重信息');
		  			return;
		  		}else if(cjrx==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料冲击韧性权重信息');
		  			return;
		  		}else if(yd==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料硬度权重信息');
		  			return;
		  		}else if(rdl==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料热导率信息');
		  			return;
		  		}else if(rdl==""){
		  			Ext.MessageBox.alert('提示','请填写相似度权重信息');
		  			return;
		  		}
		  		Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicexpertscores.jsp',
						//method:'post',
						params:{
							type:'ADDCLSIM',
							feature:"上轧辊总成",
							klqd:klqd,
							cjrx:cjrx,
							yd:yd,
							rdl:rdl,
							simw:simw
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"保存成功");
							}else{
								Ext.MessageBox.alert('提示',"保存失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
		  	}
		  },{
		   	text: '重置',
		   	id:'reset2',
		   	handler:function(){
				otherform.getForm().reset();								     								
				
		   }		   
		}]
	});*/
	
	 //组装页面
	var pingfen = new Ext.Window({
		           modal:true,
	                width:600,
	                height:520,
	                closable:true,
			layout:"border",
			title:"<p align='center'>评价</p>",    
					bodyBorder:true,
			items:assessform
	}
		);
		pingfen.show();
		 $('#star').raty({path:'lib/img',  size:24,
			 mouseover: function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				 $('#coment').text(score+'分');	
		  } 	 
		 });
		  $('#star1').raty({path:'lib/img',  size:24,
			 mouseover: function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				 $('#coment1').text(score+'分');    		
		  }	 
		 });
		  $('#star2').raty({path:'lib/img',  size:24,
			 mouseover: function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);	
				 $('#coment2').text(score+'分'); 		 
		  } 	 
		 });
		  $('#star3').raty({path:'lib/img',  size:24,
			 mouseover: function(score, evt) {
			   // alert('ID: ' + this.id + "\nscore: " + score + "\nevent: " + evt);
				 $('#coment3').text(score+'分');	
		  } 	 
		 });
		;//如果是槽
    	}else if(feature == '润滑系统'){
    		var form_title = new Ext.Panel({     
    	    	bodyStyle:'padding:0px 50px 10px', 
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>答疑速度</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>答疑效果</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>产品质量</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>服务态度</p>",width:'50',style: "margin:10,0,0,0"})
    	    	      
    	    	]
    	    });
    	    var form_row1 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:"<p align='right'>答疑效率&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	//emptyText:'1',
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'dia2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2pit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2fit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}
    	    	]
    	    });
    		
    		var form_row2 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:"<p align='right'>服务态度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'pit2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'pit2pit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'pit2fit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'pit2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}
    	    	]
    	    });
	
    	  var form_row3  = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:"<p align='right'>槽宽&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'fit2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'fit2pit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'fit2fit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'fit2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}
    	    	]
    	    });
    	    
    		  var form_row4 = new Ext.Panel({     
    		    	bodyStyle:'padding:5px 50px 0', 
    		    	//labelSeparator: ' ',
    		    	layout:'table',
    		        layoutConfig: {columns: 5},
    		    	items:[new Ext.Panel({html:"<p align='right'>槽深&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    		               {
    		        		style : 'margin:0,10,0,4',
    			        	xtype:'textfield',
    			        	//hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2dia',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,5,0,7',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2pit',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,5,0,7',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2fit',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,10,0,5',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '1',
    			        	disabled: true,
    			        	id: 'hol2hol',
    			        	width: 40
    		        	}
    		    	]
    		    });
    		
    		    add_win.hide();
    		 
    		 var assessform = new Ext.FormPanel({
    				bodyStyle: 'padding:0px 300px 0px',
    				region:'center',
    				//width:600,
    				//height:10,
    				frame:true,
    				layout:"column",
    				//style:'margin:0% 20%',
    				buttonAlign:"center",
    				title:"<p align='center'>AHP法权重评分矩阵</p>",
    				items:[form_title,form_row1,form_row2,form_row3,form_row4],
    				buttons:[{
    			       	text: '保存',
    				  	id:'save',					
    				  	handler:function(){
    				  		var a11 = Ext.get("dia2dia").dom.value.trim();
    				  		var a12 = Ext.get("dia2pit").dom.value.trim();
    				  		var a13 = Ext.get("dia2fit").dom.value.trim();
    				  		var a14 = Ext.get("dia2hol").dom.value.trim();
    				  		var a21 = Ext.get("pit2dia").dom.value.trim();
    				  		var a22 = Ext.get("pit2pit").dom.value.trim();
    				  		var a23 = Ext.get("pit2fit").dom.value.trim();
    				  		var a24 = Ext.get("pit2hol").dom.value.trim();
    				  		var a31 = Ext.get("fit2dia").dom.value.trim();
    				  		var a32 = Ext.get("fit2pit").dom.value.trim();
    				  		var a33 = Ext.get("fit2fit").dom.value.trim();
    				  		var a34 = Ext.get("fit2hol").dom.value.trim();
    				  		var a41 = Ext.get("hol2dia").dom.value.trim();
    				  		var a42 = Ext.get("hol2pit").dom.value.trim();
    				  		var a43 = Ext.get("hol2fit").dom.value.trim();
    				  		var a44 = Ext.get("hol2hol").dom.value.trim();
    				  		//alert(a41+" "+a42+" "+a43+" "+a44);
    				  		//保存打分信息
    				  		Ext.showProgressDialog();
							Ext.Ajax.request({
								url:'logicexpertscores.jsp',
								//method:'post',
								params:{
									type:'ADD',
									feature:"润滑系统",
									a11:a11,a12:a12,a13:a13,a14:a14,a21:a21,a22:a22,a23:a23,a24:a24,a31:a31,a32:a32,a33:a33,a34:a34,a41:a41,a42:a42,a43:a43,a44:a44
								},
								success:function(resp){
									Ext.hideProgressDialog();
									if(parseInt(resp.responseText)==1){
										Ext.MessageBox.alert('提示',"保存成功");
									}else{
										Ext.MessageBox.alert('提示',"保存失败");
									}
								},
								failure:function(){
									Ext.hideProgressDialog();
									Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
								}
							});
    				  	}
    				  },{
    				   	text: '重置',
    				   	id:'reset',
    				   	handler:function(){
    						assessform.getForm().reset();
    				   }		   
    				}]
    			});
    			
		 //材料属性权重
	var otherform = new Ext.FormPanel({
		region:'south',
		layout:'form',
		id:'tablepanel',
		title:"<p align='center'>其它权重打分</p>",
		height:230,
		autoScroll:true,
		frame: true,
		buttonAlign:"center",
		items:[{
			bodyStyle: 'padding:0px 180px 0',
            //width:750,
            xtype:'fieldset',
            title: '工件材料属性权重(0～1)',
            collapsible: false,
            autoHeight:true,
            layout:'column',
            //border:false,
            items:[{
            	//width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '抗拉强度权重',
                    emptyText:'0',
                    id: 'klqd',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '冲击韧性权重',
                    emptyText:'0',
                    id: 'cjrx',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料硬度权重',
                    emptyText:'0',
                    id: 'yd',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料热导率权重',
                    emptyText:'0',
                    id: 'rdl',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            }]
        },{
        	bodyStyle: 'padding:5px 400px 0',
            //width:750,
            xtype:'fieldset',
            title: '相似度对最终评价结果的影响权重(0～1)',
            collapsible: false,
            autoHeight:true,
            layout:'column',
            items:[{
            	   columnWidth:1,
            	   layout: 'form',
            	   items: [{
            		   xtype:'textfield',
                       fieldLabel: '相似度权重',
                       emptyText:'0',
                       id: 'simw',
                       width: 100,
                       regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                       regexText:'只能由数字组成!'
                   }]
               }]
        }],
		buttons:[{
	       	text: '保存',
		  	id:'save1',					
		  	handler:function(){
		  		var klqd = Ext.get("klqd").dom.value.trim();
		  		var cjrx = Ext.get("cjrx").dom.value.trim();
		  		var yd = Ext.get("yd").dom.value.trim();
		  		var rdl = Ext.get("rdl").dom.value.trim();
		  		var simw = Ext.get("simw").dom.value.trim();
		  		if(klqd==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料抗拉强度权重信息');
		  			return;
		  		}else if(cjrx==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料冲击韧性权重信息');
		  			return;
		  		}else if(yd==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料硬度权重信息');
		  			return;
		  		}else if(rdl==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料热导率信息');
		  			return;
		  		}else if(rdl==""){
		  			Ext.MessageBox.alert('提示','请填写相似度权重信息');
		  			return;
		  		}
		  		Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicexpertscores.jsp',
						//method:'post',
						params:{
							type:'ADDCLSIM',
							feature:"润滑系统",
							klqd:klqd,
							cjrx:cjrx,
							yd:yd,
							rdl:rdl,
							simw:simw
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"保存成功");
							}else{
								Ext.MessageBox.alert('提示',"保存失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
		  	}
		  },{
		   	text: '重置',
		   	id:'reset2',
		   	handler:function(){
				otherform.getForm().reset();								     								
				
		   }		   
		}]
	});
	 //组装页面
		new Ext.Viewport({
			layout:"border",
			autoHeight:false,
			items:[assessform, otherform]
	});
    	}else if(feature == '液压系统'){
    		var form_title = new Ext.Panel({     
    	    	bodyStyle:'padding:0px 50px 10px', 
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>答疑效率</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>服务态度</p>",width:'50',style: "margin:10,0,0,0"}),
    	    	       new Ext.Panel({html:"<p align='center'>台阶宽度</p>",width:'50',style: "margin:10,0,0,0"}),
    	    	       new Ext.Panel({html:"<p align='center'>台阶深度</p>",width:'50',style: "margin:10,0,0,0"})
    	    	]
    	    });
    	    var form_row1 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:"<p align='right'>答疑效率&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	//emptyText:'1',
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'dia2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2pit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2fit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}
    	    	]
    	    });
    		
    		var form_row2 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:"<p align='right'>服务态度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'pit2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'pit2pit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'pit2fit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'pit2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}
    	    	]
    	    });
	
    	  var form_row3  = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:"<p align='right'>台阶宽度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'fit2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'fit2pit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'fit2fit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'fit2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}
    	    	]
    	    });
    	    
    		  var form_row4 = new Ext.Panel({     
    		    	bodyStyle:'padding:5px 50px 0', 
    		    	//labelSeparator: ' ',
    		    	layout:'table',
    		        layoutConfig: {columns: 5},
    		    	items:[new Ext.Panel({html:"<p align='right'>台阶宽度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    		               {
    		        		style : 'margin:0,10,0,4',
    			        	xtype:'textfield',
    			        	//hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2dia',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,5,0,7',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2pit',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,5,0,7',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2fit',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,10,0,5',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '1',
    			        	disabled: true,
    			        	id: 'hol2hol',
    			        	width: 40
    		        	}
    		    	]
    		    });
    		
    		    add_win.hide();
    		 
    		 var assessform = new Ext.FormPanel({
    				bodyStyle: 'padding:0px 300px 0px',
    				region:'center',
    				//width:600,
    				//height:10,
    				frame:true,
    				layout:"column",
    				//style:'margin:0% 20%',
    				buttonAlign:"center",
    				title:"<p align='center'>AHP法权重评分矩阵</p>",
    				items:[form_title,form_row1,form_row2,form_row3,form_row4],
    				buttons:[{
    			       	text: '保存',
    				  	id:'save',					
    				  	handler:function(){
    				  		var a11 = Ext.get("dia2dia").dom.value.trim();
    				  		var a12 = Ext.get("dia2pit").dom.value.trim();
    				  		var a13 = Ext.get("dia2fit").dom.value.trim();
    				  		var a14 = Ext.get("dia2hol").dom.value.trim();
    				  		var a21 = Ext.get("pit2dia").dom.value.trim();
    				  		var a22 = Ext.get("pit2pit").dom.value.trim();
    				  		var a23 = Ext.get("pit2fit").dom.value.trim();
    				  		var a24 = Ext.get("pit2hol").dom.value.trim();
    				  		var a31 = Ext.get("fit2dia").dom.value.trim();
    				  		var a32 = Ext.get("fit2pit").dom.value.trim();
    				  		var a33 = Ext.get("fit2fit").dom.value.trim();
    				  		var a34 = Ext.get("fit2hol").dom.value.trim();
    				  		var a41 = Ext.get("hol2dia").dom.value.trim();
    				  		var a42 = Ext.get("hol2pit").dom.value.trim();
    				  		var a43 = Ext.get("hol2fit").dom.value.trim();
    				  		var a44 = Ext.get("hol2hol").dom.value.trim();
    				  		//alert(a41+" "+a42+" "+a43+" "+a44);
    				  		//保存打分信息
    				  		Ext.showProgressDialog();
							Ext.Ajax.request({
								url:'logicexpertscores.jsp',
								//method:'post',
								params:{
									type:'ADD',
									feature:"液压系统",
									a11:a11,a12:a12,a13:a13,a14:a14,a21:a21,a22:a22,a23:a23,a24:a24,a31:a31,a32:a32,a33:a33,a34:a34,a41:a41,a42:a42,a43:a43,a44:a44
								},
								success:function(resp){
									Ext.hideProgressDialog();
									if(parseInt(resp.responseText)==1){
										Ext.MessageBox.alert('提示',"保存成功");
									}else{
										Ext.MessageBox.alert('提示',"保存失败");
									}
								},
								failure:function(){
									Ext.hideProgressDialog();
									Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
								}
							});
    				  	}
    				  },{
    				   	text: '重置',
    				   	id:'reset',
    				   	handler:function(){
    						assessform.getForm().reset();								     								
    						//Ext.getCmp("diameter").focus();
    				   }		   
    				}]
    			});
    			
		 //材料属性权重
	var otherform = new Ext.FormPanel({
		region:'south',
		layout:'form',
		id:'tablepanel',
		title:"<p align='center'>其它权重打分</p>",
		height:230,
		autoScroll:true,
		frame: true,
		buttonAlign:"center",
		items:[{
			bodyStyle: 'padding:0px 180px 0',
            //width:750,
            xtype:'fieldset',
            title: '工件材料属性权重(0～1)',
            collapsible: false,
            autoHeight:true,
            layout:'column',
            //border:false,
            items:[{
            	//width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '抗拉强度权重',
                    emptyText:'0',
                    id: 'klqd',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '冲击韧性权重',
                    emptyText:'0',
                    id: 'cjrx',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料硬度权重',
                    emptyText:'0',
                    id: 'yd',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.25,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料热导率权重',
                    emptyText:'0',
                    id: 'rdl',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            }]
        },{
        	bodyStyle: 'padding:5px 400px 0',
            //width:750,
            xtype:'fieldset',
            title: '相似度对最终评价结果的影响权重(0～1)',
            collapsible: false,
            autoHeight:true,
            layout:'column',
            items:[{
            	   columnWidth:1,
            	   layout: 'form',
            	   items: [{
            		   xtype:'textfield',
                       fieldLabel: '相似度权重',
                       emptyText:'0',
                       id: 'simw',
                       width: 100,
                       regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                       regexText:'只能由数字组成!'
                   }]
               }]
        }],
		buttons:[{
	       	text: '保存',
		  	id:'save1',					
		  	handler:function(){
		  		var klqd = Ext.get("klqd").dom.value.trim();
		  		var cjrx = Ext.get("cjrx").dom.value.trim();
		  		var yd = Ext.get("yd").dom.value.trim();
		  		var rdl = Ext.get("rdl").dom.value.trim();
		  		var simw = Ext.get("simw").dom.value.trim();
		  		if(klqd==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料抗拉强度权重信息');
		  			return;
		  		}else if(cjrx==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料冲击韧性权重信息');
		  			return;
		  		}else if(yd==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料硬度权重信息');
		  			return;
		  		}else if(rdl==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料热导率信息');
		  			return;
		  		}else if(rdl==""){
		  			Ext.MessageBox.alert('提示','请填写相似度权重信息');
		  			return;
		  		}
		  		Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicexpertscores.jsp',
						//method:'post',
						params:{
							type:'ADDCLSIM',
							feature:"液压系统",
							klqd:klqd,
							cjrx:cjrx,
							yd:yd,
							rdl:rdl,
							simw:simw
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"保存成功");
							}else{
								Ext.MessageBox.alert('提示',"保存失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
		  	}
		  },{
		   	text: '重置',
		   	id:'reset2',
		   	handler:function(){
				otherform.getForm().reset();								     								
				
		   }		   
		}]
	});
	 //组装页面
		new Ext.Viewport({
			layout:"border",
			autoHeight:false,
			items:[assessform, otherform]
		});
    	}else if(feature == '冷却辊总成'){
    		var form_title = new Ext.Panel({     
    	    	bodyStyle:'padding:0px 50px 10px', 
    	    	layout:'table',
    	        layoutConfig: {columns: 7},
    	    	items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>工件材料</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>毛坯长度</p>",width:'50',style: "margin:10,0,0,0"}),
    	    	 	   new Ext.Panel({html:"<p align='center'>毛坯高度</p>",width:'50',style: "margin:10,0,0,0"}),
    	    	 	   new Ext.Panel({html:"<p align='center'>毛坯宽度</p>",width:'50',style: "margin:10,0,0,0"}),
    	    	 	   new Ext.Panel({html:"<p align='center'>结构特征</p>",width:'50',style: "margin:10,0,0,0"})
    	    	]
    	    });
    	    var form_row1 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 7},
    	    	items:[new Ext.Panel({html:"<p align='right'>工件材料&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	//emptyText:'1',
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'dia2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2pit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2fit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2fits',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}/*,{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'dia2hols',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}*/
    	    	]
    	    });
    		
    		var form_row2 = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 7},
    	    	items:[new Ext.Panel({html:"<p align='right'>毛坯长度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'pit2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'pit2pit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'pit2fit',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'pit2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'pit2fits',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}/*,{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'pit2hols',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}*/
    	    	]
    	    });
	
    	  var form_row3  = new Ext.Panel({     
    	    	bodyStyle:'padding:5px 50px 0', 
    	    	//labelSeparator: ' ',
    	    	layout:'table',
    	        layoutConfig: {columns: 7},
    	    	items:[new Ext.Panel({html:"<p align='right'>毛坯宽度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    	               {
    	        		style : 'margin:0,10,0,4',
    		        	xtype:'textfield',
    		        	//hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'fit2dia',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '',
    		        	readOnly: true,
    		        	disabled: true,
    		        	id: 'fit2pit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,5,0,7',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	value: '1',
    		        	disabled: true,
    		        	id: 'fit2fit',
    		        	width: 40
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'fit2hol',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'fit2hols',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}/*,{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'fit2holss',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}*/
    	    	]
    	    });
    	    
    		  var form_row4 = new Ext.Panel({     
    		    	bodyStyle:'padding:5px 50px 0', 
    		    	//labelSeparator: ' ',
    		    	layout:'table',
    		        layoutConfig: {columns: 7},
    		    	items:[new Ext.Panel({html:"<p align='right'>毛坯高度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
    		               {
    		        		style : 'margin:0,10,0,4',
    			        	xtype:'textfield',
    			        	//hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2dia',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,5,0,7',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2pit',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,5,0,7',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '',
    			        	readOnly: true,
    			        	disabled: true,
    			        	id: 'hol2fit',
    			        	width: 40
    		        	},{
    		        		style : 'margin:0,10,0,5',
    			        	xtype:'textfield',
    			        	hideLabel: true,
    			        	value: '1',
    			        	disabled: true,
    			        	id: 'hol2hol',
    			        	width: 40
    		        	},{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'qt2kd',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}/*,{
    	        		style : 'margin:0,10,0,5',
    		        	xtype:'textfield',
    		        	hideLabel: true,
    		        	emptyText:'打分',
    		        	id: 'qt2kds',
    		        	width: 40,
    		        	listeners: {'focus': function(){
    		        							showassesswin(this.id);
    		        							}}
    	        	}*/
    		    	]
    		    });
    var form_row5 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 7},
    	items:[new Ext.Panel({html:"<p align='right'>结构特征&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fits2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fits2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'hols2fit',
	        	width: 40
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'kd2qt',
	        	width: 40
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'len2len',
	        	width: 40
        	}/*,{
        		style : 'margin:0,5,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'len2mat',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}*/
    	]
    });
    
    var form_row6 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 7},
    	items:[new Ext.Panel({html:"<p align='right'>圆角半径&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'hols2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'hols2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'holss2fit',
	        	width: 40
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'kds2qt',
	        	width: 40
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'mat2len',
	        	width: 40
        	}/*,{
        		style : 'margin:0,5,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'mat2mat',
	        	width: 40
        	}*/
    	]
    });
    		    add_win.hide();
    		 
    		 var assessform = new Ext.FormPanel({
    				bodyStyle: 'padding:0px 300px 0px',
    				region:'center',
    				//width:600,
    				//height:10,
    				frame:true,
    				layout:"column",
    				//style:'margin:0% 20%',
    				buttonAlign:"center",
    				title:"<p align='center'>AHP法权重评分矩阵</p>",
    				items:[form_title,form_row1,form_row2,form_row3,form_row4,form_row5/*,form_row6*/],
    				buttons:[{
    			       	text: '保存',
    				  	id:'save',					
    				  	handler:function(){
    				  		//第一行
    				  		var a11 = Ext.get("dia2dia").dom.value.trim();
    				  		var a12 = Ext.get("dia2pit").dom.value.trim();
    				  		var a13 = Ext.get("dia2fit").dom.value.trim();
    				  		var a14 = Ext.get("dia2hol").dom.value.trim();
    				  		var a15 = Ext.get("dia2fits").dom.value.trim();
    				  		var a16 = Ext.get("dia2hols").dom.value.trim();
    				  		//第二行
    				  		var a21 = Ext.get("pit2dia").dom.value.trim();
    				  		var a22 = Ext.get("pit2pit").dom.value.trim();
    				  		var a23 = Ext.get("pit2fit").dom.value.trim();
    				  		var a24 = Ext.get("pit2hol").dom.value.trim();
    				  		var a25 = Ext.get("pit2fits").dom.value.trim();
    				  		var a26 = Ext.get("pit2hols").dom.value.trim();
    				  		//第三行
    				  		var a31 = Ext.get("fit2dia").dom.value.trim();
    				  		var a32 = Ext.get("fit2pit").dom.value.trim();
    				  		var a33 = Ext.get("fit2fit").dom.value.trim();
    				  		var a34 = Ext.get("fit2hol").dom.value.trim();
    				  		var a35 = Ext.get("fit2hols").dom.value.trim();
    				  		var a36 = Ext.get("fit2holss").dom.value.trim();
    				  		//第四行
    				  		var a41 = Ext.get("hol2dia").dom.value.trim();
    				  		var a42 = Ext.get("hol2pit").dom.value.trim();
    				  		var a43 = Ext.get("hol2fit").dom.value.trim();
    				  		var a44 = Ext.get("hol2hol").dom.value.trim();
    				  		var a45 = Ext.get("qt2kd").dom.value.trim();
    				  		var a46 = Ext.get("qt2kds").dom.value.trim();
    				  		//第五行
    				  		var a51 = Ext.get("fits2dia").dom.value.trim();
    				  		var a52 = Ext.get("fits2pit").dom.value.trim();
    				  		var a53 = Ext.get("hols2fit").dom.value.trim();
    				  		var a54 = Ext.get("kd2qt").dom.value.trim();
    				  		var a55 = Ext.get("len2len").dom.value.trim();
    				  		var a56 = Ext.get("len2mat").dom.value.trim();
    				  		//第六行
    				  		var a61 = Ext.get("hols2dia").dom.value.trim();
    				  		var a62 = Ext.get("hols2pit").dom.value.trim();
    				  		var a63 = Ext.get("holss2fit").dom.value.trim();
    				  		var a64 = Ext.get("kds2qt").dom.value.trim();
    				  		var a65 = Ext.get("mat2len").dom.value.trim();
    				  		var a66 = Ext.get("mat2mat").dom.value.trim();
    				  		/*alert(a11+" "+a12+" "+a13+" "+a14+" "+a15+" "+a16);
    				  		alert(a21+" "+a22+" "+a23+" "+a24+" "+a25+" "+a26);
    				  		alert(a31+" "+a32+" "+a33+" "+a34+" "+a35+" "+a36);
    				  		alert(a41+" "+a42+" "+a43+" "+a44+" "+a45+" "+a46);
    				  		alert(a51+" "+a52+" "+a53+" "+a54+" "+a55+" "+a56);
    				  		alert(a61+" "+a62+" "+a63+" "+a64+" "+a65+" "+a66);*/
    				  		
    				  		//保存打分信息
    				  		Ext.showProgressDialog();
							Ext.Ajax.request({
								url:'logicexpertscores.jsp',
								//method:'post',
								params:{
									type:'ADD',
									feature:"冷却辊总成",
									a11:a11,a12:a12,a13:a13,a14:a14,a15:a15,a16:a16,
									a21:a21,a22:a22,a23:a23,a24:a24,a25:a25,a26:a26,
									a31:a31,a32:a32,a33:a33,a34:a34,a35:a35,a36:a36,
									a41:a41,a42:a42,a43:a43,a44:a44,a45:a45,a46:a46,
									a51:a51,a52:a52,a53:a53,a54:a54,a55:a55,a56:a56,
									a61:a61,a62:a62,a63:a63,a64:a64,a65:a65,a66:a66
								},
								success:function(resp){
									Ext.hideProgressDialog();
									if(parseInt(resp.responseText)==1){
										Ext.MessageBox.alert('提示',"保存成功");
									}else{
										Ext.MessageBox.alert('提示',"保存失败");
									}
								},
								failure:function(){
									Ext.hideProgressDialog();
									Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
								}
							});
    				  	}
    				  },{
    				   	text: '重置',
    				   	id:'reset',
    				   	handler:function(){
    						assessform.getForm().reset();
    				   }		   
    				}]
    			});
    			
		 //材料属性权重
	var otherform = new Ext.FormPanel({
		region:'south',
		layout:'form',
		id:'tablepanel',
		title:"<p align='center'>其它权重打分</p>",
		height:330,
		autoScroll:true,
		//labelWidth:150,
		frame: true,
		buttonAlign:"center",
		items:[{
			bodyStyle: 'padding:0px 0px 0',
            //width:750,
            xtype:'fieldset',
            title: '工件材料属性权重(0～1)',
            collapsible: false,
            autoHeight:true,
            //labelWidth:120,
            layout:'column',
            //border:false,
            items:[{
            	//width: 250,
                columnWidth:.2,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料硬度权重',
                    emptyText:'0',
                    id: 'klqd',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.2,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '抗拉强度权重',
                    emptyText:'0',
                    id: 'cjrx',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.2,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '屈服强度权重',
                    emptyText:'0',
                    id: 'yd',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.2,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '材料热导率权重',
                    emptyText:'0',
                    id: 'rdl',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            },{
                //width: 250,
                columnWidth:.2,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '弹性模量值权重',
                    emptyText:'0',
                    id: 'txml',
                    width: 80,
                   	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                    regexText:'只能由数字组成!'
                }]
            }]
        },{
        	bodyStyle: 'padding:5px 200px 0',
            //width:750,
        	labelWidth:150,
            xtype:'fieldset',
            title: '结构特征权重(0～1)',
            collapsible: false,
            autoHeight:true,
            layout:'column',
            items:[{
            	   columnWidth:.5,
            	   layout: 'form',
            	   items: [{
            		   xtype:'textfield',
                       fieldLabel: '相同特征类别数量权重',
                       emptyText:'0',
                       id: 'simw11',
                       width: 60,
                       regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                       regexText:'只能由数字组成!'
                   }]
               },{
            	   columnWidth:.5,
            	   layout: 'form',
            	   items: [{
            		   xtype:'textfield',
                       fieldLabel: '相同类别下特征数量权重',
                       emptyText:'0',
                       id: 'simw22',
                       width: 60,
                       regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                       regexText:'只能由数字组成!'
                   }]
               }]
        },{
        	bodyStyle: 'padding:5px 400px 0',
            //width:750,
            xtype:'fieldset',
            title: '相似度对最终评价结果的影响权重(0～1)',
            collapsible: false,
            autoHeight:true,
            layout:'column',
            items:[{
            	   columnWidth:1,
            	   layout: 'form',
            	   items: [{
            		   xtype:'textfield',
                       fieldLabel: '相似度权重',
                       emptyText:'0',
                       id: 'simw',
                       width: 100,
                       regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
                       regexText:'只能由数字组成!'
                   }]
               }]
        }],
		buttons:[{
	       	text: '保存',
		  	id:'save1',					
		  	handler:function(){
		  		var klqd = Ext.get("klqd").dom.value.trim();
		  		var cjrx = Ext.get("cjrx").dom.value.trim();
		  		var yd = Ext.get("yd").dom.value.trim();
		  		var rdl = Ext.get("rdl").dom.value.trim();
		  		var simw = Ext.get("simw").dom.value.trim();
		  		if(klqd==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料抗拉强度权重信息');
		  			return;
		  		}else if(cjrx==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料冲击韧性权重信息');
		  			return;
		  		}else if(yd==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料硬度权重信息');
		  			return;
		  		}else if(rdl==""){
		  			Ext.MessageBox.alert('提示','请填写详细材料热导率信息');
		  			return;
		  		}else if(rdl==""){
		  			Ext.MessageBox.alert('提示','请填写相似度权重信息');
		  			return;
		  		}
		  		Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicexpertscores.jsp',
						//method:'post',
						params:{
							type:'ADDCLSIM',
							feature:"冷却辊总成",
							klqd:klqd,
							cjrx:cjrx,
							yd:yd,
							rdl:rdl,
							simw:simw
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"保存成功");
							}else{
								Ext.MessageBox.alert('提示',"保存失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
		  	}
		  },{
		   	text: '重置',
		   	id:'reset2',
		   	handler:function(){
				otherform.getForm().reset();								     								
				
		   }		   
		}]
	});
	 //组装页面
		new Ext.Viewport({
			layout:"border",
			autoHeight:false,
			items:[assessform, otherform]
		});
    	}else if(feature == '传动系统'){
    		var form_title = new Ext.Panel({     
    	    	bodyStyle:'padding:0px 50px 10px', 
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>答疑效率</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>服务态度</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>长度</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>宽度</p>",width:'50',style: "margin:10,0,0,0"})
    	    	]
    	    });
    var form_row1 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>答疑效率&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	//emptyText:'1',
	        	value: '1',
	        	disabled: true,
	        	id: 'dia2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2pit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2fit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });
	
	var form_row2 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>服务态度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'pit2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'pit2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'pit2fit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'pit2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });

  var form_row3  = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>长度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fit2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fit2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'fit2fit',
	        	width: 40
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'fit2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });
    
	  var form_row4 = new Ext.Panel({     
	    	bodyStyle:'padding:5px 50px 0', 
	    	//labelSeparator: ' ',
	    	layout:'table',
	        layoutConfig: {columns: 5},
	    	items:[new Ext.Panel({html:"<p align='right'>宽度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
	               {
	        		style : 'margin:0,10,0,4',
		        	xtype:'textfield',
		        	//hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2dia',
		        	width: 40
	        	},{
	        		style : 'margin:0,5,0,7',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2pit',
		        	width: 40
	        	},{
	        		style : 'margin:0,5,0,7',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2fit',
		        	width: 40
	        	},{
	        		style : 'margin:0,10,0,5',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '1',
		        	disabled: true,
		        	id: 'hol2hol',
		        	width: 40
	        	}
	    	]
	    });
	
	    add_win.hide();
	 
	 var assessform = new Ext.FormPanel({
			bodyStyle: 'padding:0px 300px 0px',
			region:'center',
			//width:600,
			//height:10,
			frame:true,
			layout:"column",
			//style:'margin:0% 20%',
			buttonAlign:"center",
			title:"<p align='center'>AHP法权重评分矩阵</p>",
			items:[form_title,form_row1,form_row2,form_row3,form_row4],
			buttons:[{
		       	text: '保存',
			  	id:'save',					
			  	handler:function(){
			  		var a11 = Ext.get("dia2dia").dom.value.trim();
			  		var a12 = Ext.get("dia2pit").dom.value.trim();
			  		var a13 = Ext.get("dia2fit").dom.value.trim();
			  		var a14 = Ext.get("dia2hol").dom.value.trim();
			  		var a21 = Ext.get("pit2dia").dom.value.trim();
			  		var a22 = Ext.get("pit2pit").dom.value.trim();
			  		var a23 = Ext.get("pit2fit").dom.value.trim();
			  		var a24 = Ext.get("pit2hol").dom.value.trim();
			  		var a31 = Ext.get("fit2dia").dom.value.trim();
			  		var a32 = Ext.get("fit2pit").dom.value.trim();
			  		var a33 = Ext.get("fit2fit").dom.value.trim();
			  		var a34 = Ext.get("fit2hol").dom.value.trim();
			  		var a41 = Ext.get("hol2dia").dom.value.trim();
			  		var a42 = Ext.get("hol2pit").dom.value.trim();
			  		var a43 = Ext.get("hol2fit").dom.value.trim();
			  		var a44 = Ext.get("hol2hol").dom.value.trim();
			  		//alert(a41+" "+a42+" "+a43+" "+a44);
			  		//保存打分信息
			  		Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicexpertscores.jsp',
						//method:'post',
						params:{
							type:'ADD',
							feature:"传动系统",
							a11:a11,a12:a12,a13:a13,a14:a14,a21:a21,a22:a22,a23:a23,a24:a24,a31:a31,a32:a32,a33:a33,a34:a34,a41:a41,a42:a42,a43:a43,a44:a44
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"保存成功");
							}else{
								Ext.MessageBox.alert('提示',"保存失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
    				  	}
    				  },{
    				   	text: '重置',
    				   	id:'reset',
    				   	handler:function(){
    						assessform.getForm().reset();								     								
    						//Ext.getCmp("diameter").focus();
    				   }		   
    				}]
    			});
		
 //材料属性权重
var otherform = new Ext.FormPanel({
region:'south',
layout:'form',
id:'tablepanel',
title:"<p align='center'>其它权重打分</p>",
height:230,
autoScroll:true,
frame: true,
buttonAlign:"center",
items:[{
	bodyStyle: 'padding:0px 180px 0',
    //width:750,
    xtype:'fieldset',
    title: '工件材料属性权重(0～1)',
    collapsible: false,
    autoHeight:true,
    layout:'column',
    //border:false,
    items:[{
    	//width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '抗拉强度权重',
            emptyText:'0',
            id: 'klqd',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '冲击韧性权重',
            emptyText:'0',
            id: 'cjrx',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '材料硬度权重',
            emptyText:'0',
            id: 'yd',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '材料热导率权重',
            emptyText:'0',
            id: 'rdl',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    }]
},{
	bodyStyle: 'padding:5px 400px 0',
    //width:750,
    xtype:'fieldset',
    title: '相似度对最终评价结果的影响权重(0～1)',
    collapsible: false,
    autoHeight:true,
    layout:'column',
    items:[{
    	   columnWidth:1,
    	   layout: 'form',
    	   items: [{
    		   xtype:'textfield',
               fieldLabel: '相似度权重',
               emptyText:'0',
               id: 'simw',
               width: 100,
               regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
               regexText:'只能由数字组成!'
           }]
       }]
}],
buttons:[{
   	text: '保存',
  	id:'save1',					
  	handler:function(){
  		var klqd = Ext.get("klqd").dom.value.trim();
  		var cjrx = Ext.get("cjrx").dom.value.trim();
  		var yd = Ext.get("yd").dom.value.trim();
  		var rdl = Ext.get("rdl").dom.value.trim();
  		var simw = Ext.get("simw").dom.value.trim();
  		if(klqd==""){
  			Ext.MessageBox.alert('提示','请填写详细材料抗拉强度权重信息');
  			return;
  		}else if(cjrx==""){
  			Ext.MessageBox.alert('提示','请填写详细材料冲击韧性权重信息');
  			return;
  		}else if(yd==""){
  			Ext.MessageBox.alert('提示','请填写详细材料硬度权重信息');
  			return;
  		}else if(rdl==""){
  			Ext.MessageBox.alert('提示','请填写详细材料热导率信息');
  			return;
  		}else if(rdl==""){
  			Ext.MessageBox.alert('提示','请填写相似度权重信息');
  			return;
  		}
  		Ext.showProgressDialog();
			Ext.Ajax.request({
				url:'logicexpertscores.jsp',
				//method:'post',
				params:{
					type:'ADDCLSIM',
					feature:"传动系统",
					klqd:klqd,
					cjrx:cjrx,
					yd:yd,
					rdl:rdl,
					simw:simw
				},
				success:function(resp){
					Ext.hideProgressDialog();
					if(parseInt(resp.responseText)==1){
						Ext.MessageBox.alert('提示',"保存成功");
					}else{
						Ext.MessageBox.alert('提示',"保存失败");
					}
				},
				failure:function(){
					Ext.hideProgressDialog();
					Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
				}
			});
  	}
  },{
   	text: '重置',
   	id:'reset2',
   	handler:function(){
		otherform.getForm().reset();								     								
		
   }		   
}]
});

//组装页面
new Ext.Viewport({
	layout:"border",
	autoHeight:false,
	items:[assessform, otherform]
});
    	}else if(feature=='下轧辊总成'){
    		var form_title = new Ext.Panel({     
    	    	bodyStyle:'padding:0px 50px 10px', 
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>答疑效率</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>服务态度</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>缺口宽度</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>缺口深度</p>",width:'50',style: "margin:10,0,0,0"})
    	    	]
    	    });
    var form_row1 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>答疑效率&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	//emptyText:'1',
	        	value: '1',
	        	disabled: true,
	        	id: 'dia2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2pit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2fit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });
	
	var form_row2 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>服务态度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'pit2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'pit2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'pit2fit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'pit2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });

  var form_row3  = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>缺口宽度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fit2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fit2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'fit2fit',
	        	width: 40
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'fit2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });
    
	  var form_row4 = new Ext.Panel({     
	    	bodyStyle:'padding:5px 50px 0', 
	    	//labelSeparator: ' ',
	    	layout:'table',
	        layoutConfig: {columns: 5},
	    	items:[new Ext.Panel({html:"<p align='right'>缺口深度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
	               {
	        		style : 'margin:0,10,0,4',
		        	xtype:'textfield',
		        	//hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2dia',
		        	width: 40
	        	},{
	        		style : 'margin:0,5,0,7',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2pit',
		        	width: 40
	        	},{
	        		style : 'margin:0,5,0,7',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2fit',
		        	width: 40
	        	},{
	        		style : 'margin:0,10,0,5',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '1',
		        	disabled: true,
		        	id: 'hol2hol',
		        	width: 40
	        	}
	    	]
	    });
	
	    add_win.hide();
	 
	 var assessform = new Ext.FormPanel({
			bodyStyle: 'padding:0px 300px 0px',
			region:'center',
			//width:600,
			//height:10,
			frame:true,
			layout:"column",
			//style:'margin:0% 20%',
			buttonAlign:"center",
			title:"<p align='center'>AHP法权重评分矩阵</p>",
			items:[form_title,form_row1,form_row2,form_row3,form_row4],
			buttons:[{
		       	text: '保存',
			  	id:'save',					
			  	handler:function(){
			  		var a11 = Ext.get("dia2dia").dom.value.trim();
			  		var a12 = Ext.get("dia2pit").dom.value.trim();
			  		var a13 = Ext.get("dia2fit").dom.value.trim();
			  		var a14 = Ext.get("dia2hol").dom.value.trim();
			  		var a21 = Ext.get("pit2dia").dom.value.trim();
			  		var a22 = Ext.get("pit2pit").dom.value.trim();
			  		var a23 = Ext.get("pit2fit").dom.value.trim();
			  		var a24 = Ext.get("pit2hol").dom.value.trim();
			  		var a31 = Ext.get("fit2dia").dom.value.trim();
			  		var a32 = Ext.get("fit2pit").dom.value.trim();
			  		var a33 = Ext.get("fit2fit").dom.value.trim();
			  		var a34 = Ext.get("fit2hol").dom.value.trim();
			  		var a41 = Ext.get("hol2dia").dom.value.trim();
			  		var a42 = Ext.get("hol2pit").dom.value.trim();
			  		var a43 = Ext.get("hol2fit").dom.value.trim();
			  		var a44 = Ext.get("hol2hol").dom.value.trim();
			  		//alert(a41+" "+a42+" "+a43+" "+a44);
			  		//保存打分信息
			  		Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicexpertscores.jsp',
						//method:'post',
						params:{
							type:'ADD',
							feature:"下轧辊总成",
							a11:a11,a12:a12,a13:a13,a14:a14,a21:a21,a22:a22,a23:a23,a24:a24,a31:a31,a32:a32,a33:a33,a34:a34,a41:a41,a42:a42,a43:a43,a44:a44
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"保存成功");
							}else{
								Ext.MessageBox.alert('提示',"保存失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
    				  	}
    				  },{
    				   	text: '重置',
    				   	id:'reset',
    				   	handler:function(){
    						assessform.getForm().reset();								     								
    						//Ext.getCmp("diameter").focus();
    				   }		   
    				}]
    			});
		
 //材料属性权重
var otherform = new Ext.FormPanel({
region:'south',
layout:'form',
id:'tablepanel',
title:"<p align='center'>其它权重打分</p>",
height:230,
autoScroll:true,
frame: true,
buttonAlign:"center",
items:[{
	bodyStyle: 'padding:0px 180px 0',
    //width:750,
    xtype:'fieldset',
    title: '工件材料属性权重(0～1)',
    collapsible: false,
    autoHeight:true,
    layout:'column',
    //border:false,
    items:[{
    	//width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '抗拉强度权重',
            emptyText:'0',
            id: 'klqd',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '冲击韧性权重',
            emptyText:'0',
            id: 'cjrx',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '材料硬度权重',
            emptyText:'0',
            id: 'yd',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '材料热导率权重',
            emptyText:'0',
            id: 'rdl',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    }]
},{
	bodyStyle: 'padding:5px 400px 0',
    //width:750,
    xtype:'fieldset',
    title: '相似度对最终评价结果的影响权重(0～1)',
    collapsible: false,
    autoHeight:true,
    layout:'column',
    items:[{
    	   columnWidth:1,
    	   layout: 'form',
    	   items: [{
    		   xtype:'textfield',
               fieldLabel: '相似度权重',
               emptyText:'0',
               id: 'simw',
               width: 100,
               regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
               regexText:'只能由数字组成!'
           }]
       }]
}],
buttons:[{
   	text: '保存',
  	id:'save1',					
  	handler:function(){
  		var klqd = Ext.get("klqd").dom.value.trim();
  		var cjrx = Ext.get("cjrx").dom.value.trim();
  		var yd = Ext.get("yd").dom.value.trim();
  		var rdl = Ext.get("rdl").dom.value.trim();
  		var simw = Ext.get("simw").dom.value.trim();
  		if(klqd==""){
  			Ext.MessageBox.alert('提示','请填写详细材料抗拉强度权重信息');
  			return;
  		}else if(cjrx==""){
  			Ext.MessageBox.alert('提示','请填写详细材料冲击韧性权重信息');
  			return;
  		}else if(yd==""){
  			Ext.MessageBox.alert('提示','请填写详细材料硬度权重信息');
  			return;
  		}else if(rdl==""){
  			Ext.MessageBox.alert('提示','请填写详细材料热导率信息');
  			return;
  		}else if(rdl==""){
  			Ext.MessageBox.alert('提示','请填写相似度权重信息');
  			return;
  		}
  		Ext.showProgressDialog();
			Ext.Ajax.request({
				url:'logicexpertscores.jsp',
				//method:'post',
				params:{
					type:'ADDCLSIM',
					feature:"下轧辊总成",
					klqd:klqd,
					cjrx:cjrx,
					yd:yd,
					rdl:rdl,
					simw:simw
				},
				success:function(resp){
					Ext.hideProgressDialog();
					if(parseInt(resp.responseText)==1){
						Ext.MessageBox.alert('提示',"保存成功");
					}else{
						Ext.MessageBox.alert('提示',"保存失败");
					}
				},
				failure:function(){
					Ext.hideProgressDialog();
					Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
				}
			});
  	}
  },{
   	text: '重置',
   	id:'reset2',
   	handler:function(){
		otherform.getForm().reset();								     								
		
   }		   
}]
});

//组装页面
new Ext.Viewport({
	layout:"border",
	autoHeight:false,
	items:[assessform, otherform]
});
    	}else if(feature=='机架总成'){
    		var form_title = new Ext.Panel({     
    	    	bodyStyle:'padding:0px 50px 10px', 
    	    	layout:'table',
    	        layoutConfig: {columns: 5},
    	    	items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>答疑效率</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>服务态度</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>侧壁高度</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>侧壁间距</p>",width:'50',style: "margin:10,0,0,0"})
    	    	]
    	    });
    var form_row1 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>答疑效率&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	//emptyText:'1',
	        	value: '1',
	        	disabled: true,
	        	id: 'dia2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2pit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2fit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });
	
	var form_row2 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>服务态度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'pit2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'pit2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'pit2fit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'pit2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });

  var form_row3  = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>侧壁高度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fit2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fit2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'fit2fit',
	        	width: 40
        	},{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'fit2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}
    	]
    });
    
	  var form_row4 = new Ext.Panel({     
	    	bodyStyle:'padding:5px 50px 0', 
	    	//labelSeparator: ' ',
	    	layout:'table',
	        layoutConfig: {columns: 5},
	    	items:[new Ext.Panel({html:"<p align='right'>侧壁间距&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
	               {
	        		style : 'margin:0,10,0,4',
		        	xtype:'textfield',
		        	//hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2dia',
		        	width: 40
	        	},{
	        		style : 'margin:0,5,0,7',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2pit',
		        	width: 40
	        	},{
	        		style : 'margin:0,5,0,7',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '',
		        	readOnly: true,
		        	disabled: true,
		        	id: 'hol2fit',
		        	width: 40
	        	},{
	        		style : 'margin:0,10,0,5',
		        	xtype:'textfield',
		        	hideLabel: true,
		        	value: '1',
		        	disabled: true,
		        	id: 'hol2hol',
		        	width: 40
	        	}
	    	]
	    });
	
	    add_win.hide();
	 
	 var assessform = new Ext.FormPanel({
			bodyStyle: 'padding:0px 300px 0px',
			region:'center',
			//width:600,
			//height:10,
			frame:true,
			layout:"column",
			//style:'margin:0% 20%',
			buttonAlign:"center",
			title:"<p align='center'>AHP法权重评分矩阵</p>",
			items:[form_title,form_row1,form_row2,form_row3,form_row4],
			buttons:[{
		       	text: '保存',
			  	id:'save',					
			  	handler:function(){
			  		var a11 = Ext.get("dia2dia").dom.value.trim();
			  		var a12 = Ext.get("dia2pit").dom.value.trim();
			  		var a13 = Ext.get("dia2fit").dom.value.trim();
			  		var a14 = Ext.get("dia2hol").dom.value.trim();
			  		var a21 = Ext.get("pit2dia").dom.value.trim();
			  		var a22 = Ext.get("pit2pit").dom.value.trim();
			  		var a23 = Ext.get("pit2fit").dom.value.trim();
			  		var a24 = Ext.get("pit2hol").dom.value.trim();
			  		var a31 = Ext.get("fit2dia").dom.value.trim();
			  		var a32 = Ext.get("fit2pit").dom.value.trim();
			  		var a33 = Ext.get("fit2fit").dom.value.trim();
			  		var a34 = Ext.get("fit2hol").dom.value.trim();
			  		var a41 = Ext.get("hol2dia").dom.value.trim();
			  		var a42 = Ext.get("hol2pit").dom.value.trim();
			  		var a43 = Ext.get("hol2fit").dom.value.trim();
			  		var a44 = Ext.get("hol2hol").dom.value.trim();
			  		//alert(a41+" "+a42+" "+a43+" "+a44);
			  		//保存打分信息
			  		Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicexpertscores.jsp',
						//method:'post',
						params:{
							type:'ADD',
							feature:"机架总成",
							a11:a11,a12:a12,a13:a13,a14:a14,a21:a21,a22:a22,a23:a23,a24:a24,a31:a31,a32:a32,a33:a33,a34:a34,a41:a41,a42:a42,a43:a43,a44:a44
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"保存成功");
							}else{
								Ext.MessageBox.alert('提示',"保存失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
    				  	}
    				  },{
    				   	text: '重置',
    				   	id:'reset',
    				   	handler:function(){
    						assessform.getForm().reset();								     								
    						//Ext.getCmp("diameter").focus();
    				   }		   
    				}]
    			});
		
 //材料属性权重
var otherform = new Ext.FormPanel({
region:'south',
layout:'form',
id:'tablepanel',
title:"<p align='center'>其它权重打分</p>",
height:230,
autoScroll:true,
frame: true,
buttonAlign:"center",
items:[{
	bodyStyle: 'padding:0px 180px 0',
    //width:750,
    xtype:'fieldset',
    title: '工件材料属性权重(0～1)',
    collapsible: false,
    autoHeight:true,
    layout:'column',
    //border:false,
    items:[{
    	//width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '抗拉强度权重',
            emptyText:'0',
            id: 'klqd',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '冲击韧性权重',
            emptyText:'0',
            id: 'cjrx',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '材料硬度权重',
            emptyText:'0',
            id: 'yd',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '材料热导率权重',
            emptyText:'0',
            id: 'rdl',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    }]
},{
	bodyStyle: 'padding:5px 400px 0',
    //width:750,
    xtype:'fieldset',
    title: '相似度对最终评价结果的影响权重(0～1)',
    collapsible: false,
    autoHeight:true,
    layout:'column',
    items:[{
    	   columnWidth:1,
    	   layout: 'form',
    	   items: [{
    		   xtype:'textfield',
               fieldLabel: '相似度权重',
               emptyText:'0',
               id: 'simw',
               width: 100,
               regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
               regexText:'只能由数字组成!'
           }]
       }]
}],
buttons:[{
   	text: '保存',
  	id:'save1',					
  	handler:function(){
  		var klqd = Ext.get("klqd").dom.value.trim();
  		var cjrx = Ext.get("cjrx").dom.value.trim();
  		var yd = Ext.get("yd").dom.value.trim();
  		var rdl = Ext.get("rdl").dom.value.trim();
  		var simw = Ext.get("simw").dom.value.trim();
  		if(klqd==""){
  			Ext.MessageBox.alert('提示','请填写详细材料抗拉强度权重信息');
  			return;
  		}else if(cjrx==""){
  			Ext.MessageBox.alert('提示','请填写详细材料冲击韧性权重信息');
  			return;
  		}else if(yd==""){
  			Ext.MessageBox.alert('提示','请填写详细材料硬度权重信息');
  			return;
  		}else if(rdl==""){
  			Ext.MessageBox.alert('提示','请填写详细材料热导率信息');
  			return;
  		}else if(rdl==""){
  			Ext.MessageBox.alert('提示','请填写相似度权重信息');
  			return;
  		}
  		Ext.showProgressDialog();
			Ext.Ajax.request({
				url:'logicexpertscores.jsp',
				//method:'post',
				params:{
					type:'ADDCLSIM',
					feature:"机架总成",
					klqd:klqd,
					cjrx:cjrx,
					yd:yd,
					rdl:rdl,
					simw:simw
				},
				success:function(resp){
					Ext.hideProgressDialog();
					if(parseInt(resp.responseText)==1){
						Ext.MessageBox.alert('提示',"保存成功");
					}else{
						Ext.MessageBox.alert('提示',"保存失败");
					}
				},
				failure:function(){
					Ext.hideProgressDialog();
					Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
				}
			});
  	}
  },{
   	text: '重置',
   	id:'reset2',
   	handler:function(){
		otherform.getForm().reset();								     								
		
   }		   
}]
});

//组装页面
new Ext.Viewport({
	layout:"border",
	autoHeight:false,
	items:[assessform, otherform]
});
    	}else if(feature=='限位调节装置'){
    		var form_title = new Ext.Panel({     
    	    	bodyStyle:'padding:0px 50px 10px', 
    	    	layout:'table',
    	        layoutConfig: {columns: 4},
    	    	items:[new Ext.Panel({html:'&nbsp;',width:'100',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>答疑效率</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>服务态度</p>",width:'50',style: "margin:10,0,0,0"}),
    	               new Ext.Panel({html:"<p align='center'>清角半径</p>",width:'50',style: "margin:10,0,0,0"})
    	    	]
    	    });
    var form_row1 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 4},
    	items:[new Ext.Panel({html:"<p align='right'>答疑效率&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	//emptyText:'1',
	        	value: '1',
	        	disabled: true,
	        	id: 'dia2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2pit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2fit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}/*,{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'dia2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}*/
    	]
    });
	
	var form_row2 = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>服务态度&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'pit2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'pit2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'pit2fit',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}/*,{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'pit2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}*/
    	]
    });

  var form_row3  = new Ext.Panel({     
    	bodyStyle:'padding:5px 50px 0', 
    	//labelSeparator: ' ',
    	layout:'table',
        layoutConfig: {columns: 5},
    	items:[new Ext.Panel({html:"<p align='right'>清角半径&nbsp;&nbsp;</p>",width:'100',style: "margin:0,0,0,0"}),
               {
        		style : 'margin:0,10,0,4',
	        	xtype:'textfield',
	        	//hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fit2dia',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '',
	        	readOnly: true,
	        	disabled: true,
	        	id: 'fit2pit',
	        	width: 40
        	},{
        		style : 'margin:0,5,0,7',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	value: '1',
	        	disabled: true,
	        	id: 'fit2fit',
	        	width: 40
        	}/*,{
        		style : 'margin:0,10,0,5',
	        	xtype:'textfield',
	        	hideLabel: true,
	        	emptyText:'打分',
	        	id: 'fit2hol',
	        	width: 40,
	        	listeners: {'focus': function(){
	        							showassesswin(this.id);
	        							}}
        	}*/
    	]
    });	
	    add_win.hide();
	 
	 var assessform = new Ext.FormPanel({
			bodyStyle: 'padding:0px 300px 0px',
			region:'center',
			//width:600,
			//height:10,
			frame:true,
			layout:"column",
			//style:'margin:0% 20%',
			buttonAlign:"center",
			title:"<p align='center'>AHP法权重评分矩阵</p>",
			items:[form_title,form_row1,form_row2,form_row3],
			buttons:[{
		       	text: '保存',
			  	id:'save',					
			  	handler:function(){
			  		var a11 = Ext.get("dia2dia").dom.value.trim();
			  		var a12 = Ext.get("dia2pit").dom.value.trim();
			  		var a13 = Ext.get("dia2fit").dom.value.trim();
			  		/*var a14 = Ext.get("dia2hol").dom.value.trim();*/
			  		var a21 = Ext.get("pit2dia").dom.value.trim();
			  		var a22 = Ext.get("pit2pit").dom.value.trim();
			  		var a23 = Ext.get("pit2fit").dom.value.trim();
			  		/*var a24 = Ext.get("pit2hol").dom.value.trim();*/
			  		var a31 = Ext.get("fit2dia").dom.value.trim();
			  		var a32 = Ext.get("fit2pit").dom.value.trim();
			  		var a33 = Ext.get("fit2fit").dom.value.trim();
			  		
			  		//保存打分信息
			  		Ext.showProgressDialog();
					Ext.Ajax.request({
						url:'logicexpertscores.jsp',
						//method:'post',
						params:{
							type:'ADD',
							feature:"限位调节装置",
							a11:a11,a12:a12,a13:a13,a21:a21,a22:a22,a23:a23,a31:a31,a32:a32,a33:a33
						},
						success:function(resp){
							Ext.hideProgressDialog();
							if(parseInt(resp.responseText)==1){
								Ext.MessageBox.alert('提示',"保存成功");
							}else{
								Ext.MessageBox.alert('提示',"保存失败");
							}
						},
						failure:function(){
							Ext.hideProgressDialog();
							Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
						}
					});
    				  	}
    				  },{
    				   	text: '重置',
    				   	id:'reset',
    				   	handler:function(){
    						assessform.getForm().reset();								     								
    						//Ext.getCmp("diameter").focus();
    				   }		   
    				}]
    			});
		
 //材料属性权重
var otherform = new Ext.FormPanel({
region:'south',
layout:'form',
id:'tablepanel',
title:"<p align='center'>其它权重打分</p>",
height:230,
autoScroll:true,
frame: true,
buttonAlign:"center",
items:[{
	bodyStyle: 'padding:0px 180px 0',
    //width:750,
    xtype:'fieldset',
    title: '工件材料属性权重(0～1)',
    collapsible: false,
    autoHeight:true,
    layout:'column',
    //border:false,
    items:[{
    	//width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '抗拉强度权重',
            emptyText:'0',
            id: 'klqd',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '冲击韧性权重',
            emptyText:'0',
            id: 'cjrx',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '材料硬度权重',
            emptyText:'0',
            id: 'yd',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    },{
        //width: 250,
        columnWidth:.25,
        layout: 'form',
        border:false,
        items: [{
            xtype:'textfield',
            fieldLabel: '材料热导率权重',
            emptyText:'0',
            id: 'rdl',
            width: 80,
           	regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
            regexText:'只能由数字组成!'
        }]
    }]
},{
	bodyStyle: 'padding:5px 400px 0',
    //width:750,
    xtype:'fieldset',
    title: '相似度对最终评价结果的影响权重(0～1)',
    collapsible: false,
    autoHeight:true,
    layout:'column',
    items:[{
    	   columnWidth:1,
    	   layout: 'form',
    	   items: [{
    		   xtype:'textfield',
               fieldLabel: '相似度权重',
               emptyText:'0',
               id: 'simw',
               width: 100,
               regex:/^[\+\-]?(\d{1,8}(\.\d{1,3})?)$/,
               regexText:'只能由数字组成!'
           }]
       }]
}],
buttons:[{
   	text: '保存',
  	id:'save1',					
  	handler:function(){
  		var klqd = Ext.get("klqd").dom.value.trim();
  		var cjrx = Ext.get("cjrx").dom.value.trim();
  		var yd = Ext.get("yd").dom.value.trim();
  		var rdl = Ext.get("rdl").dom.value.trim();
  		var simw = Ext.get("simw").dom.value.trim();
  		if(klqd==""){
  			Ext.MessageBox.alert('提示','请填写详细材料抗拉强度权重信息');
  			return;
  		}else if(cjrx==""){
  			Ext.MessageBox.alert('提示','请填写详细材料冲击韧性权重信息');
  			return;
  		}else if(yd==""){
  			Ext.MessageBox.alert('提示','请填写详细材料硬度权重信息');
  			return;
  		}else if(rdl==""){
  			Ext.MessageBox.alert('提示','请填写详细材料热导率信息');
  			return;
  		}else if(rdl==""){
  			Ext.MessageBox.alert('提示','请填写相似度权重信息');
  			return;
  		}
  		Ext.showProgressDialog();
			Ext.Ajax.request({
				url:'logicexpertscores.jsp',
				//method:'post',
				params:{
					type:'ADDCLSIM',
					feature:"限位调节装置",
					klqd:klqd,
					cjrx:cjrx,
					yd:yd,
					rdl:rdl,
					simw:simw
				},
				success:function(resp){
					Ext.hideProgressDialog();
					if(parseInt(resp.responseText)==1){
						Ext.MessageBox.alert('提示',"保存成功");
					}else{
						Ext.MessageBox.alert('提示',"保存失败");
					}
				},
				failure:function(){
					Ext.hideProgressDialog();
					Ext.MessageBox.alert('提示',"数据传送中出现问题,请稍后再试");
				}
			});
  	}
  },{
   	text: '重置',
   	id:'reset2',
   	handler:function(){
		otherform.getForm().reset();								     								
		
   }		   
}]
});

//组装页面
new Ext.Viewport({
	layout:"border",
	autoHeight:false,
	items:[assessform, otherform]
});
    	}
    }

   	 
});