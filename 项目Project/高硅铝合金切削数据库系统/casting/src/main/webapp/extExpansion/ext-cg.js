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
		reader = new Epx.xmlReader;
	}
	var objParam;

	if (param.nodeName && param.nodeName == "FORM")
	{
	}
	else if (param.length)
	{
		objParam = param.join("&");
	}
	else
	{
		objParam = param;
	}

	if (callback)
	{
		//var httpProxy = new Ext.data.HttpProxy(new Ext.data.Connection({url: sURL, method: 'POST', timeout: timeout}));//支持并发调用
		var httpProxy = new Ext.data.HttpProxy({url: sURL, method: 'POST'});//不支持并发调用
		httpProxy.getConnection().timeout = timeout;
		httpProxy.load(objParam, reader, callback, scope, args);//params, reader, callback, scope, args
	}
	else
	{
		xhr = YAHOO.util.Connect.getConnectionObject();
		xhr.conn.open("POST", sURL, false);
		xhr.conn.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var postData =  Ext.urlEncode(param);
		xhr.conn.send(postData);
		if (xhr.conn.responseXML)
			return xhr.conn.responseXML;
		else
			return null;
	}
}
function replaceSingleQuotesByDouble(sqlCondition){
	sqlCondition  = sqlCondition  + "";
	sqlCondition = sqlCondition.replace(/\'/g,"''");
	return sqlCondition;
}
function getFormData(form){
	var data = {};
	if (typeof form == 'string'){
		form = Ext.getDom(form);
	}
	if (form == null || form == undefined) return data;
	var el, name, val, data = {}, hasSubmit = false;
	for (var i = 0; i < form.elements.length; i++) {
		var el = form.elements[i];
		var name = form.elements[i].name;
		var val = form.elements[i].value;		
		if (name){
			switch (el.type)
			{
				case 'select-one':
				case 'select-multiple':
					for (var j = 0; j < el.options.length; j++) {
						if (el.options[j].selected) {
							data[name] = el.options[j].value;
						}
					}
					break;
				case 'radio':
					if (el.checked) {
						data[name] = value;
					}
					break;
				case 'checkbox':
					if (el.checked) {
						data[name] = 1;
					}
					else{
						data[name] = 0;
					}
					break;
				case 'file':

				case undefined:

				case 'reset':

				case 'button':

					break;
				case 'submit':
					break;
				default:
					data[name] = val;
					break;
			}
		}
	}
	return data;
}
Ext.showProgressDialog = function(title, handleHide)
{
	if (title == undefined) title = "请稍等...";
	Ext.MessageBox.show({
			autoCreate : true,
			shadow: true,
			draggable: true,
			resizable:false,
			syncHeightBeforeShow: true,
			constraintoviewport:false,
			fixedcenter:true,
			collapsible : false,
			shim:true,
			modal: true,
			width:290, height:80,
			buttonAlign:"center",
			title: title,
			msg: '<img src="/tsms/web/inc/resources/images/custom/progress.gif" width="287" height="15">',
			closable: false
	});

	if (handleHide == undefined || handleHide == null)
	{
		handleHide = function (){};
	}

	//Ext.MessageBox.on("hide", handleHide);
	//Ext.MessageBox.addKeyListener(27,  Ext.MessageBox.hide(), Ext.MessageBox);
}

/*
 * 全局进度条窗口关闭
 */
Ext.hideProgressDialog = function ()
{
	Ext.MessageBox.hide();
}