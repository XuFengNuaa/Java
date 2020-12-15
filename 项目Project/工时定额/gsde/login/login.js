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
	function loginFn(){
       if(simple.form.isValid()){//验证合法后使用加载进度条
	       //显示进度条
		   Ext.showProgressDialog();
		  //提交到服务器操作
		  simple.form.doAction('submit',{
			 url:'/gsde/login/logiclogin.jsp',//文件路径
			 method:'post',//提交方法post或get
			 params:{type:'LOGIN'},
			 //提交成功的回调函数
			 success:function(form,action){	
					if (action.result.msg=='ok'){
								    var sUrl='/gsde/index.jsp';
									top.window.opener = null;
		     						top.window.close();
									var left =(screen.width-1024)/2;
		     						var top_px =(screen.height-768)/2;     										
									top.window.open(sUrl,"","width=1024,height=550,top="+top_px+",left="+left+",resizable=yes,titlebar=yes,toolbar=yes,menubar=yes,status=yes,location=yes");									
									Ext.hideProgressDialog();
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
	}
	
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
								
});

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
