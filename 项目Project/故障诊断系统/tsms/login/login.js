Ext.onReady(function(){	
	//使用表单提示
	Ext.QuickTips.init();
	//设置提示信息位置为边上
	Ext.form.Field.prototype.msgTarget='side';		 
	//定义表单
	var simple = new Ext.FormPanel({
		labelWidth: 75, 			
		baseCls: 'x-plain',
		defaults: {width: 150},
		defaultType: 'textfield',//默认字段类型	              
		//定义表单元素
		items: [{
				fieldLabel: '工号',
				name: 'username',//元素名称
				//anchor:'95%',//也可用此定义自适应宽度
				value: userid,
				allowBlank:false,//不允许为空
				blankText:'工号不能为空',//错误提示内容
				regex:/^[0-9a-zA-Z]+$/,
				regexText:'工号只能含有字母或数字！'
			},{
			    inputType:'password',
				fieldLabel: '密码',
				//anchor:'95%',
				name: 'password',
				allowBlank:false,
				blankText:'密码不能为空',
				regex:/^[0-9a-zA-Z]+$/,
				regexText:'密码只能含有字母或数字！'
			}
		],	
		buttons: [{
			text: '登录',
			type: 'submit',
			id:'loginBtn',
			//定义表单提交事件
			handler:loginFn
		},{
			text: '重置',
			handler:function(){simple.form.reset();}//重置表单
		}
		/*,{
			text: '显示键盘',
			id:'keyB',
			handler:function(){
				if(keyBoardWin.isVisible()){
					keyBoard.hide();
					Ext.getCmp("keyB").setText("显示键盘");
				}else{
					keyBoard.show();
					Ext.getCmp("keyB").setText("隐藏键盘");
				}
			}//键盘
		}*/
		]
	});
	//定义登录窗体
	win = new Ext.Window({
		id:'win',
		title:'用户登录',
		layout:'fit',	//之前提到的布局方式fit，自适应布局					
		width:300,
		height:150,
		plain:true,
	    bodyStyle:'padding:5px;',
		maximizable:false,//禁止最大化
		closeAction:'close',
		closable:true,//禁止关闭
		collapsible:false,//可折叠
		plain: true,
		buttonAlign:'center',
		items:simple//将表单作为窗体元素嵌套布局
	});
	win.on('close',closeFn);
	function closeFn(){
		top.window.opener = null;
		top.window.close();
	}
	win.show();//显示窗体
	function showrolechoosewin(yh,kf,bz,ld,xt,cx,zb,zk,fk,wc,mj,yb,userid){
				var rolechoosepanel=new Ext.FormPanel({
				      layout:'form',
				      anchor:'100%',
				      xtype:'panel',
				      baseCls: 'x-plain',			
				      buttons:[{
		                  id:'enter',
					      text: '进入',	
					      handler:chooserole
					  },{
		                  id:'out',
					      text: '退出',	
					      handler:function(){
					       rolechoosewin.close();
					       win.show();
					      }
					  }]
				});
	        var rolechoosewin=new Ext.Window({
							id:'rolechoosewin',
							title:'选择角色',
							layout:'fit',	//之前提到的布局方式fit，自适应布局					
							width:300,
							height:230,
							plain:true,
						    bodyStyle:'padding:5px;',
							maximizable:false,//禁止最大化							
							closable:false,//禁止关闭
							collapsible:false,//可折叠
							plain: true,
							buttonAlign:'center',
							items:rolechoosepanel//将表单作为窗体元素嵌套布局
				});	
				if(yh==undefined){	
				}else{
					 var radiogy = {id:'normalUser',xtype:'radio',hideLabel:true,name:'display',boxLabel:'用户',inputValue:'y',checked:true};
				     rolechoosepanel.add(radiogy);
				}	
				if(kf==undefined){	
				}else{
				     var radiosh={id:'storeManager',xtype:'radio',hideLabel:true,name:'display',boxLabel:'库房管理员',inputValue:'y'};
				     rolechoosepanel.add(radiosh);
				}
				if(bz==undefined){
				}else{
				     var radiofs={id:'teamLeader',xtype:'radio',hideLabel:true,name:'display',boxLabel:'班组长',inputValue:'y'};
				     rolechoosepanel.add(radiofs);				   
				}
				if(ld==undefined){	
				}else{
				     var radiobj={id:'boss',xtype:'radio',hideLabel:true,name:'display', boxLabel:'领导',inputValue:'n'};
				     rolechoosepanel.add(radiobj);				
				}
				if(xt==undefined){	
				}else{
				     var radiobj={id:'sysManager',xtype:'radio',hideLabel:true,name:'display', boxLabel:'系统管理员',inputValue:'n'};
				     rolechoosepanel.add(radiobj);		
				}
				if(cx==undefined){	
				}else{
				     var radiobj={id:'Programmer',xtype:'radio',hideLabel:true,name:'display', boxLabel:'程序员',inputValue:'n'};
				     rolechoosepanel.add(radiobj);		
				}
				if(zb==undefined){	
				}else{
				     var radiobj={id:'Prepare',xtype:'radio',hideLabel:true,name:'display', boxLabel:'生产准备组',inputValue:'n'};
				     rolechoosepanel.add(radiobj);		
				}if(zk==undefined){	
				}else{
				     var radiobj={id:'zongku',xtype:'radio',hideLabel:true,name:'display', boxLabel:'总库管理员',inputValue:'n'};
				     rolechoosepanel.add(radiobj);		
				}if(fk==undefined){	
				}else{
				     var radiobj={id:'fenku',xtype:'radio',hideLabel:true,name:'display', boxLabel:'分库管理员',inputValue:'n'};
				     rolechoosepanel.add(radiobj);		
				}if(wc==undefined){	
				}else{
				     var radiobj={id:'waichang',xtype:'radio',hideLabel:true,name:'display', boxLabel:'外厂计划员',inputValue:'n'};
				     rolechoosepanel.add(radiobj);		
				}if(mj==undefined){	
				}else{
				     var radiobj={id:'moju',xtype:'radio',hideLabel:true,name:'display', boxLabel:'模具管理员',inputValue:'n'};
				     rolechoosepanel.add(radiobj);		
				}
				if(yb==undefined){	
				}else{
				     var radiobj={id:'yibiao',xtype:'radio',hideLabel:true,name:'display', boxLabel:'仪表管理员',inputValue:'n'};
				     rolechoosepanel.add(radiobj);		
				}
				rolechoosewin.show();				
				function  chooserole(){				                       
				    					var roleindex=0;				        
								        if((yh!=undefined) && (Ext.getCmp("normalUser").getValue()==true)){								            
								             roleindex=1;
								        }
								        if((kf!= undefined) && (Ext.getCmp("storeManager").getValue()==true)){								       
								             roleindex=2;
								        } 
								        if(bz!=undefined && Ext.getCmp("teamLeader").getValue()==true){								           
								             roleindex=3;
								        }
								        if(ld!=undefined && Ext.getCmp("boss").getValue()==true){								         
								             roleindex=4;
								        }
								        if(xt!=undefined && Ext.getCmp("sysManager").getValue()==true){								         
								             roleindex=5;
								        }
								        if(cx!=undefined && Ext.getCmp("Programmer").getValue()==true){								         
								             roleindex=6;
								        }
								        if(zb!=undefined && Ext.getCmp("Prepare").getValue()==true){								         
								             roleindex=7;
								        }if(zk!=undefined && Ext.getCmp("zongku").getValue()==true){								         
								             roleindex=8;
								        }
								        if(fk!=undefined && Ext.getCmp("fenku").getValue()==true){								         
								             roleindex=9;
								        } 
								        if(wc!=undefined && Ext.getCmp("waichang").getValue()==true){								         
								             roleindex=10;
								        }
								        if(mj!=undefined && Ext.getCmp("moju").getValue()==true){								         
								             roleindex=11;
								        }
								        if(yb!=undefined && Ext.getCmp("yibiao").getValue()==true){								         
								             roleindex=12;
								        }
								        if(roleindex!=0 && roleindex!=undefined && userid!=undefined){
								        	var url="/tsms/login/logiclogin.jsp";
											var parms={type:'queryRoleInf',userlevel:roleindex,userid:userid};
											var response=Ext.util.JSON.decode(callService(url,parms));
											if(response.msg=='ok'){		
													var sUrlRole='/tsms/index.jsp';
													top.window.opener = null;
				     						        top.window.close();
											        var left =(screen.width-1024)/2;
				     						        var top_px =(screen.height-768)/2;     										
											        top.window.open(sUrlRole,"","width=1024,height=550,top="+top_px+",left="+left+",resizable=yes,titlebar=yes,toolbar=yes,menubar=yes,status=yes,location=yes");
								            }
								        }else{
								             alert("请选择一种角色登陆!");
								        }
				}
	};
	function loginFn(){
       if(simple.form.isValid()){//验证合法后使用加载进度条
	       //显示进度条
		   Ext.showProgressDialog();	                               
		  //提交到服务器操作
		  simple.form.doAction('submit',{
			 url:'/tsms/login/logiclogin.jsp',//文件路径
			 method:'post',//提交方法post或get
			 params:{type:'LOGIN'},
			 //提交成功的回调函数
			 success:function(form,action){					 		
					if (action.result.msg=='ok'){				
					    if(action.result.multrole==false){						      
								var sUrl='/tsms/index.jsp';
									top.window.opener = null;
		     						top.window.close();
									var left =(screen.width-1024)/2;
		     						var top_px =(screen.height-768)/2;     										
									top.window.open(sUrl,"","width=1024,height=550,top="+top_px+",left="+left+",resizable=yes,titlebar=yes,toolbar=yes,menubar=yes,status=yes,location=yes");									
						}else{	
						         if(Ext.getCmp("rolechoosewin")==undefined){						    
							         win.hide();
									 Ext.hideProgressDialog();	
									 showrolechoosewin(action.result.yh,action.result.kf,action.result.bz,action.result.ld,action.result.xt,action.result.cx,action.result.zb,action.result.zk,action.result.fk,action.result.wc,action.result.mj,action.result.yb,action.result.userid);
						         }else{						         							  
								     Ext.hideProgressDialog();	
								 }
						}
					}else{
						Ext.hideProgressDialog();								
						alert('登录错误',"工号或密码错误");						
					}							
			 },
			 //提交失败的回调函数
			 failure:function(){
			 		Ext.hideProgressDialog();
					alert('错误','服务器出现错误请稍后再试！');
			 }
		  });
	   }else{
	   		if(Ext.get("username").dom.value==""){
	   			Ext.getDom("username").focus();
				alert('工号不能为空！');
				return false;
			}else if(Ext.get("password").dom.value==""){
				Ext.getDom("password").focus();
				alert('密码不能为空！');
				return false;
			}else if(!(/^[0-9a-zA-Z]+$/.test(Ext.get("username").getValue().trim()))){				
				Ext.getDom("username").focus();
				alert('工号只能含有字母或数字！');
				return false;
			}else if(!(/^[0-9a-zA-Z]+$/.test(Ext.get("password").getValue().trim()))){
				Ext.getDom("password").focus();
				alert('密码只能含有字母或数字！');
				return false;
			}
	   }                               						   
	};
	
	 document.onkeypress=function(){   
           switch(event.keyCode){   
                       case 5://CRTL+E   
                                      break;   
                       case 9://CRTL+I                                                 
                                     break;   
                       case 17://CRTL+Q   
                                     window.close();   
                                     break;   
                       case 18://CRTL+R   
                                     break;  
                       case 20://CRTL+T   
                                     break;
                      case 13://ENTER  
                                     //MsgBox("提交表单");
                                     if(!Ext.Msg.isVisible()){
                                     	loginFn();
                                     }                                                                   
                                     break;  
                       case 23://CRTL+W 
                                  break;   
                       default:   
                                   break;    
           }
	}	
	}							
	);

Ext.onReady(function(){
	Ext.getDom("username").focus();
	Ext.get("username").on("focus", function(){keyBoardInput = Ext.getDom("username");});
	Ext.get("password").on("focus", function(){keyBoardInput = Ext.getDom("password");});
	keyBoard();
	keyBoard.hide();	
});

var setGradient = (function(){	 
	//private variables;
	var p_dCanvas = document.createElement('canvas');
	var p_useCanvas =  !!( typeof(p_dCanvas.getContext) == 'function');
	var p_dCtx = p_useCanvas?p_dCanvas.getContext('2d'):null;
	var p_isIE = /*@cc_on!@*/false;
	 
	
	 //test if toDataURL() is supported by Canvas since Safari may not support it
	
	  try{   p_dCtx.canvas.toDataURL() }catch(err){
	         p_useCanvas = false ;
	  };
	        
	if(p_useCanvas){
	   
	   return function (dEl , sColor1 , sColor2 , bRepeatY ){
			
			if(typeof(dEl) == 'string') dEl =  document.getElementById(dEl);
			if(!dEl) return false;
			var nW = dEl.offsetWidth;
			var nH = dEl.offsetHeight;
			p_dCanvas.width = nW;
			p_dCanvas.height = nH;
			
		
			var dGradient;
			var sRepeat;
			// Create gradients
			if(bRepeatY){
				dGradient = p_dCtx.createLinearGradient(0,0,nW,0);
				sRepeat = 'repeat-y';
			}else{
				dGradient = p_dCtx.createLinearGradient(0,0,0,nH);
				sRepeat = 'repeat-x';
			}		
			
			dGradient.addColorStop(0,sColor1);
			dGradient.addColorStop(1,sColor2);				
			
			p_dCtx.fillStyle = dGradient ; 
			p_dCtx.fillRect(0,0,nW,nH);
			var sDataUrl = p_dCtx.canvas.toDataURL('image/png');
			
			with(dEl.style){
				backgroundRepeat = sRepeat;
				backgroundImage = 'url(' + sDataUrl + ')';
				backgroundColor = sColor2;    
			};
	   }
	}else if(p_isIE){		
		p_dCanvas = p_useCanvas = p_dCtx =  null;		
		return function (dEl , sColor1 , sColor2 , bRepeatY){
			if(typeof(dEl) == 'string') dEl =  document.getElementById(dEl);
			if(!dEl) return false;
			dEl.style.zoom = 1;
			var sF = dEl.currentStyle.filter;
			dEl.style.filter += ' ' + ['progid:DXImageTransform.Microsoft.gradient(	GradientType=',  +(!!bRepeatY ),',enabled=true,startColorstr=',sColor1,', endColorstr=',sColor2,')'].join('');
		    
		};	
	}else{		
		p_dCanvas = p_useCanvas = p_dCtx =  null;
		return function(dEl , sColor1 , sColor2){			
			if(typeof(dEl) == 'string') dEl =  document.getElementById(dEl);
			if(!dEl) return false;
			with(dEl.style){
				 backgroundColor = sColor2; 
			};
			//alert('your browser does not support gradient effet');
		}
	}
})();
Ext.onReady(function(){
	setGradient('bgdiv','#0099ff','#666699',1);
});
