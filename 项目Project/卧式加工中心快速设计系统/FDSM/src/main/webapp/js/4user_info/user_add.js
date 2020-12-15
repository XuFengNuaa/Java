function user_add(){
	Ext.QuickTips.init();//支持tips提示
	Ext.form.Field.prototype.msgTarget='qtip';
	
	var add_form = new Ext.form.FormPanel({
	    labelWidth:75, // 
	    columnWidth:.9,
	    layout:"form",
	    hideLabels:false,
	    border:false,
	    labelAlign:"right",
	    bodyStyle:'padding:10px 40px 10px 10',
	    items: [
	    		{	fieldLabel:"工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号",xtype: 'textfield',
	    			width:210,name:"usernumber",id:"usernumber",allowBlank:false,blankText:"工号不能为空且10位以内，请填写!"},
	            {	fieldLabel:"姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名",xtype: 'textfield',
		            width:210,name:"username",id:"username",allowBlank:false,blankText:"姓名不能为空且10位以内，请填写!"},
		        {	fieldLabel:"密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码",xtype: 'textfield',inputType:'password',
		            width:210,name:"pwd",id:"pwd",value:"111111",allowBlank:false,blankText:"密码不能为空且10位以内，请填写!"},
		            {
						//columnWidth:.10,
						bodyStyle:'padding:0px 0px 0px 100px',
						html:'初始密码：111111',
						border:false
					},
		        {	fieldLabel:"年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄",xtype: 'numberfield',
	            	width:210,name:"age",id:"age",allowBlank:false,blankText:"年龄不能为空，请填写!"},
	            {	fieldLabel:"联&nbsp;系&nbsp;电&nbsp;话",xtype: 'numberfield',minLength:11,minLengthText:"长度不能小于{0}",maxLength: 11,
					maxLengthText:"长度不能大于{0}",
	            	width:210,name:"tel",id:"tel",allowBlank:false,blankText:"姓名不能为空，请填写!"},
		            
				   new Ext.form.RadioGroup({  
					    fieldLabel: '用&nbsp;户&nbsp;角&nbsp;色',  
					    width: 245,
					    readOnly: true,
					    items:[{     
					            id:"design",  
					            name: 'role',  
					            inputValue: 'design',  
					            boxLabel: '设计员'  
					        }, {  
					            id:"manager",  
					            name: 'role',  
					            inputValue: 'manager',  
					            boxLabel: '管理员'  
					        }, {  
					            id:"admin",  
					            name: 'role',  
					            inputValue: 'admin',  
					            boxLabel: '系统管理员'  
					        }]   
					}),
					 {xtype:'combo',id:"job",fieldLabel:"所&nbsp;在&nbsp;部&nbsp;门",name:'job',width:180,triggerAction: 'all',
					   	 editable:false,
					   	 mode: 'local',
					  	 store:new Ext.data.ArrayStore({
			       				id:"user_job",
			      			 	fields: [ 'jobId','jobText'],
								data: [["1", '技术部'],["2", '研发部'], ["3", '市场部'],["4", '管理部']]}), 
					  	 valueField: 'jobId',
					   	 displayField:'jobText',
					   	 emptyText:'---请选择---'},
	       		{
	       			fieldLabel:"备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注",xtype:'textarea',
	       			width:210,height:90,name:"remark",id:"remark",emptyText:'默认为空'
	       		}]
	});
	
	var win = new Ext.Window({
		modal:true,
	    width:420,
	    height:390,
	    plain: true,
	    title:"<p align='center'>用户信息添加</p>",
		bodyBorder:true,
	    items:[
	    	new Ext.Panel({
	    		layout:'column',
	    		frame:true,
	    		height:350,
	    		items:[add_form,
	                {
						columnWidth:.10,
						bodyStyle:'padding:20px 10px 10',
						html:'<font color="#FF0000">*&nbsp;*<br><br>*&nbsp;*<br><br>*&nbsp;*<br><br><br><br>*&nbsp;*<br><br>*&nbsp;*</font>',
						border:false
					}
	    		  ]
	    })],
	    buttonAlign:'center',
	    buttons: [
                {
					text: '保存',
					Align:"center",
					handler:addUser}]
	});
	
	if(!win.isVisible()){
  			 win.show();//修改窗口显示
  			 win.center();
  		}
//添加方法
function addUser(){
		if(add_form.getForm().isValid()!=true){
 							Ext.Msg.alert("提示",'<span style="font-size:15px;">添加的数据不符合要求</span>');
 							return;}
 				Ext.Msg.confirm("提示",'<span style="font-size:14px;">是否删除该数据</span>',function(btn){
					if(btn=="yes"){
 						add_form.getForm().submit({
 								url:"addPerson.do",
 								success: function(form, action) {
       									Ext.MessageBox.show({
										title:"成功",msg:'<span style="font-size:16px;">'+action.result.msg+'</span>',buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.INFO,width:260})
										win.close();	},
    							failure: function(form, action) {
    									Ext.Msg.alert('失败', action.result.msg); }
 						})
					}})
 				}
	
}