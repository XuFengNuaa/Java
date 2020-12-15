var keyBoardInput;
function add(s){
	if(keyBoardInput!=undefined){
	  //var o = Ext.getDom('edit_erea'); //The text box to be inserted
      keyBoardInput.focus();
      var t = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
        
      var value = s;
      if (document.selection) {//用于IE
          t.text+= value;
      }
      else {//用于FF
          keyBoardInput.value = keyBoardInput.value.substr(0, okeyBoardInput.selectionStart) + value + keyBoardInput.value.substr(keyBoardInput.selectionEnd);
      }  		
	}			
 }
 
function clearvalue(){
	if(keyBoardInput!=null && keyBoardInput!=undefined){
		keyBoardInput.value = "";
	}			
}
function del(){
	if(keyBoardInput!=null && keyBoardInput!=undefined){
		//keyBoardInput.value = keyBoardInput.value.slice(0,-1);	
		//keyBoardInput.value = keyBoardInput.value.substr(0, okeyBoardInput.selectionStart) + keyBoardInput.value.substr(keyBoardInput.selectionEnd);	
	  keyBoardInput.focus();
      var t = document.selection.createRange();
	  t.move("character",-1);
      t.expand("character");
      t.text="";
	}			
}
//var keyBoardWin=new Ext.Window();
keyBoard.hide=function(){keyBoardWin.hide();}
keyBoard.show=function(){keyBoardWin.show();}

function keyBoard(){		
	function addtd(s, onclickFn){
			var tdtag = "<td valign='center' align='center' width='33%'><input type='button' value="+s+" onmouseup='"+onclickFn+"'></td>";
			return tdtag;
	}
	var htmlString2 = "<table >";	
	
	htmlString2 += "<tr>";
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;&nbsp;Φ&nbsp;&nbsp;&nbsp;", "add(\"Φ\")");	
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;&nbsp;Ø&nbsp;&nbsp;&nbsp;", "add(\"Ø\")");	
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;@&nbsp;&nbsp;&nbsp;", "add(\"@\")");
	htmlString2 += "</tr>";
	
	htmlString2 += "<tr>";
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;&nbsp;°&nbsp;&nbsp;", "add(\"°\")");	
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;′&nbsp;&nbsp;&nbsp;", "add(\"′\")");	
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;″&nbsp;&nbsp;&nbsp;", "add(\"″\")");
	htmlString2 += "</tr>";
	
	htmlString2 += "<tr>";
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;±&nbsp;&nbsp;&nbsp;", "add(\"±\")");	
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;%&nbsp;&nbsp;&nbsp;", "add(\"%\")");	
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;℃&nbsp;&nbsp;&nbsp;", "add(\"℃\")");
	htmlString2 += "</tr>";
	
	htmlString2 += "<tr>";
	htmlString2 += addtd("&nbsp;&nbsp;&nbsp;×&nbsp;&nbsp;&nbsp;", "add(\"×\")");	
	htmlString2 += addtd("&nbsp;删除&nbsp;", "del()");
	htmlString2 += addtd("&nbsp;清空&nbsp;", "clearvalue()");
	htmlString2 += "</tr></table>";
	
	function addTable(divid, htmlString){
		var divtag = document.getElementById(divid);
		if(divtag == null || divtag == undefined){
			newDiv = document.createElement("div");
			newDiv.id = divid;
			document.body.appendChild(newDiv);
		}
		document.getElementById(divid).innerHTML = htmlString;
	}
	var centerdiv = "panel_center";
	addTable(centerdiv, htmlString2);
	
	keyBoardWin = new Ext.Window({
		id:'win_keyboard',
		title:'软键盘',
		layout:'fit',	//之前提到的布局方式fit，自适应布局					
		width:300,
		height:280,
		plain:true,
	    bodyStyle:'padding:3px;',
		maximizable:false,//禁止最大化
		closable:true,//禁止关闭
		closeAction:'hide',
		collapsible:false,//可折叠
		resizable:false,
		plain: true,
		buttonAlign:'center',
		items:
			new Ext.Panel({
				id: "win_keyboard_panel",
				border:false,
				title:false,
				autoScroll: false,
				layout: 'border',
				items: [{
						region:'center',
						title: false,
						border: '1px;',
						autoScroll: true,
						//html:'<div id="panel_center"></div>' 
						contentEl: centerdiv
					}]
				})
	});
	var width = document.body.clientWidth-200;
	var height = parseInt(document.body.clientHeight*0.125);
	keyBoardWin.setPosition(width, height);
	keyBoardWin.show();//显示窗体		
	document.body.onresize = function(){
		width = document.body.clientWidth-240;
		height = parseInt(document.body.clientHeight*0.12);
		keyBoardWin.setPosition(width, height);
    }
    
}; 