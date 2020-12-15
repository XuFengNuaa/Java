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
                    bscinf_panel,//基础信息管理
                    storemgt_panel,//故障案例库
                    gldapp_panel,//专家在线诊断
                    leader_panel,//领导资讯
                    usrinf_panel_leader,//个人信息管理
                    usrmgt_panel //用户信息管理  
                    //other_panel
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
	if(multrole=="false"){
		Ext.get("rolechoose").setVisible(false);
		Ext.get("roleimages").setVisible(false);
	}else{
	   	Ext.get("rolechoose").setVisible(true);
	    Ext.get("roleimages").setVisible(true);
	}
	Ext.get("loginInf").dom.innerHTML="您好:"+username+"&nbsp;"+rolename;
	
	bscinf_panel.on('click',jumpFn);
	bscinf_panel.getRootNode().expand(true);
	
	storemgt_panel.on('click',jumpFn);
	storemgt_panel.getRootNode().expand(true);
	
	gldapp_panel.on('click',jumpFn);
	gldapp_panel.getRootNode().expand(true);
	
	leader_panel.on('click',jumpFn);
	leader_panel.getRootNode().expand(true);
	/*
	usrinf_panel.on('click',jumpFn);
	usrinf_panel.getRootNode().expand(true);
	*/
	usrinf_panel_leader.on('click',jumpFn);
	usrinf_panel_leader.getRootNode().expand(true);
	
	usrmgt_panel.on('click',jumpFn);
	usrmgt_panel.getRootNode().expand(true);
});
if(isUser()){
  gldapp_panel.hide();
  leader_panel.hide();
  usrmgt_panel.hide(); 
}else if(isTeamLeader()){
  usrmgt_panel.hide();
   leader_panel.hide(); 
 }
if(iswc()){
	usrmgt_panel.hide();
   gldapp_panel.hide();
   leader_panel.hide();

}
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

function resizeWindow(){
	Ext.get("north").setHeight(32);
	viewport.doLayout();
	var activeid=Ext.getCmp('west-panel').layout.activeItem.id;
	var heitemp=viewport.findById(activeid).getSize().height-23;
}

//新增的多角色的选择
function showrole(){     
			var url="/tsms/login/logiclogin.jsp";
			var parms={type:'LOGIN'};
			var response=Ext.util.JSON.decode(callService(url,parms));
			showrolechoosewin(response.yh,response.kf,response.bz,response.ld,response.xt,response.cx,response.zb,response.zk,response.fk,response.wc,response.mj,response.yb,response.userid);   
}
function showrolechoosewin(yh,kf,bz,ld,xt,cx,zb,zk,fk,wc,mj,yb,userid){
				var rolechoosepanel=new Ext.FormPanel({
				      layout:'form',
				      anchor:'100%',
				      xtype:'panel',
				      baseCls: 'x-plain',
				      buttons:[{
		                  id:'enter',
					      text: '切换',	
					      handler:chooserole
					  },{
		                  id:'out',
					      text: '取消',	
					      handler:function(){
					       rolechoosewin.close();					  
					      }
					  }]
				});
				var	rolechoosewin=new Ext.Window({
							id:'rolechoosewin',
							title:'选择角色',
							layout:'fit',	//之前提到的布局方式fit，自适应布局					
							width:300,
							height:250,
							modal:true,
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
}
