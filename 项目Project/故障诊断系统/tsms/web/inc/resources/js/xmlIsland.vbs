Option Explicit
'*********************************************************************************
'IE Version Detect
Const MS_IE_SETUP_URL	= "http://download.microsoft.com/download/ie6sp1/finrel/6_sp1/W98NT42KMeXP/CN/ie6setup.exe"
Const MS_IE				= "MSIE"
Const MS_IE_VER 		= "6"
Const MS_IE_NAME		= "Microsoft Internet Explorer"

Const C_BEHAVIOR_COOKIE	= "UseBinaryBehavior"

'Hot Keys
Const KEY_C = 67
Const KEY_H = 72
Const KEY_L = 76
Const KEY_X = 88
Const MS_POWER = 1000

'IDS Server Name Flag for Session Timeout Detecting
Const C_IDS_SERVER = "Netscape-Enterprise"

Dim m_dXmlStatus, m_hBodyReady, m_hBodyOnLoad
Dim m_Cache, m_Logger
Dim m_bDebug, m_bLoading, m_bFirst, m_bUseBinaryBehavior



Set m_dXmlStatus = CreateObject("Scripting.Dictionary")
m_bDebug = true
m_bLoading = true
Set m_hBodyReady = GetRef("doBodyReady")
m_bFirst = false
Set m_hBodyOnLoad = GetRef("doBodyOnLoad")

'Detect Something
Call IEDetect()
Call UseBehaviorDetect()

Sub onXMLIslandReadyStateChange()
	Dim srcE, aSetTemp(2), aGetTemp, sXmlGroup, sHTC, el, gBody
	Set srcE = window.event.srcElement
	'alert srcE.id & vbCrLf & srcE.readyState
	sXmlGroup = Trim(srcE.getAttribute("xmlgroup"))
	if sXmlGroup = "" then exit sub
	Set gBody = document.body
	if (not m_bFirst) then
		m_bFirst = true
		'gBody.attachEvent "onload", m_hBodyOnLoad
		'gBody.onload = m_hBodyOnLoad
		window.attachEvent "onload", m_hBodyOnLoad
	end if
	'if gBody is nothing then Set gBody = document.body
	if (srcE.readyState = "complete") and (LCase(srcE.tagName) = "xml") Then
		if m_dXmlStatus.Exists(sXmlGroup) Then
			aGetTemp = m_dXmlStatus.item(sXmlGroup)
			if aGetTemp(1) then
				'表示已经加载过
				'alert srcE.id
				Exit Sub
			Else
				if LCase(aGetTemp(0)) <> LCase(srcE.id) then
					'alert srcE.id
					'表示可以加载，搜索全文中跟该XML绑定的DIV，置相应的样式单（CSS），并且置加载过标志位
					for each el in document.all
						if UCase(el.tagName) = "DIV" Then
							if ((el.getAttribute("DataXML") = aGetTemp(0)) and (el.getAttribute("MetaXML") = srcE.id)) _
									or ((el.getAttribute("DataXML") = srcE.id) and (el.getAttribute("MetaXML") = aGetTemp(0))) then
								sHTC = el.getAttribute("HTC")

								if Not bHasClass(el, sHTC) then el.className = sHTC & " " & el.className
								if Not bHasClass(gBody, "bodyEx") Then
									gBody.className = "bodyEx " & gBody.className
									if m_bLoading then document.body.attachEvent "onreadystatechange", m_hBodyReady
								end if
								if not m_bLoading then
									document.body.RegisterHTC el
								end if
							end if
						end if
					next
					aGetTemp(1) = true
					m_dXmlStatus.item(sXmlGroup) = aGetTemp
				end if
			end if
		else
			aSetTemp(0) = srcE.id
			aSetTemp(1) = false
			m_dXmlStatus.add sXmlGroup, aSetTemp
		end if
	end if
End Sub

Sub UseBehaviorDetect()
	dim tryObj
	on error resume next
	set tryObj = CreateObject("Editfield.Efield")
	if err then
		WriteNoBinaryCss()
		err.clear
	else
		Call WriteBinaryBehaviorObjects()
	end if
	on error goto 0
End Sub

Sub WriteBinaryBehaviorObjects()
	'write object tags for binary behavior and overwrite default css for bahavior
	document.writeln "<OBJECT ID=""editFieldBH"" CLASSID=""clsid:D12BA700-594D-4ADF-9CDF-005ADDB34C42""></OBJECT>"
	document.writeln "<OBJECT ID=""efTextBH"" CLASSID=""clsid:122947C7-0CF6-48CB-BBF0-A0DCA14DF9CB""></OBJECT>"
	document.writeln "<OBJECT ID=""efBooleanBH"" CLASSID=""clsid:6E329318-E1A8-43F2-8BD4-59C7FE7E3742""></OBJECT>"
	document.writeln "<OBJECT ID=""efTimeBH"" CLASSID=""clsid:6A3B553E-CF84-430E-A3EF-A05B6C70D5FE""></OBJECT>"
	document.writeln "<OBJECT ID=""efNumericBH"" CLASSID=""clsid:4C898359-46C8-452B-8F87-3CA8116AE28C""></OBJECT>"
	document.writeln "<OBJECT ID=""efDateBH"" CLASSID=""clsid:F0C29BE8-658D-4565-85C8-33C09048C35E""></OBJECT>"
	document.writeln "<OBJECT ID=""efSelectBH"" CLASSID=""clsid:2102FEB6-4146-4F8E-AA0A-E27D07030690""></OBJECT>"
	document.writeln "<OBJECT ID=""efCalendarBH"" CLASSID=""clsid:85DF22AF-567D-423E-912D-FB5D47EAD6C7""></OBJECT>"

	'当用到Binary时调用对应的css文件
	document.writeln "<LINK REL='stylesheet' TYPE='text/css' HREF='/epstar/web/2_0_0_0/client/htc/bizdeskBH.css' ID='mainstylesheet'></LINK>"
End Sub

Sub WriteNoBinaryCss()
 '如果没有用到则调入对应的css文件
 	'alert "<LINK REL='stylesheet' TYPE='text/css' HREF='/epstar/web/2_0_0_0/client/htc/bizdesk.css' ID='mainstylesheet'></LINK>"
	document.writeln "<LINK REL='stylesheet' TYPE='text/css' HREF='/epstar/web/2_0_0_0/client/htc/bizdesk.css' ID='mainstylesheet'></LINK>"
End Sub
Sub IEDetect()
	dim sAV, iMSIE
	sAV = UCase(window.navigator.appVersion)
	iMSIE = InStr(sAV, MS_IE)
	if iMSIE > 0 then
		if CInt(Mid(sAV, iMSIE + 5, 1)) >= CInt(MS_IE_VER) then
		else
			Call RedirectIESetup("系统检测到您的浏览器版本较低！" & vbNewLine & "是否立刻下载最新版本？")
		end if
	else
		Call RedirectIESetup("系统检测到您的浏览器不是指定浏览器（" & MS_IE_NAME & "）！" & vbNewLine & "是否立刻下载安装？")
	end if
End Sub

Sub RedirectIESetup(msg)
	if window.confirm(msg) then
		window.top.location.href = MS_IE_SETUP_URL
	end if
End Sub

Function bHasClass(elItem, sClass)
	'returns true if item's classname contains class
	bHasClass = CBool(inStr(LCase(elItem.className), LCase(sClass)))
End Function

Function GetAppTitle()
	GetAppTitle = window.document.title
End Function

Function doBodyReady()
	on error resume next
	dim srcE
	set srcE = window.event.srcElement
	if srcE.readyState = "complete" then
		document.body.detachEvent "onreadystatechange", m_hBodyReady
		'alert "bodyload"
		m_bLoading = false
	end if
	on error goto 0
End Function
Function doBodyOnLoad()
	if m_bDebug then
		endLog "page loaded"
		window.detachEvent "onload", m_hBodyOnLoad
		document.body.attachEvent "onkeydown", GetRef("doBodyKeyDown")
	end if
End Function

Sub doBodyKeyDown()
	dim evt
	set evt = window.event
	if evt.ctrlKey and evt.altKey then
		'alert evt.keyCode
		select case evt.keyCode
			case KEY_C :
				if (window.confirm("是否重置UseBinaryBehavior Cookie？")) then
					DelCookie C_BEHAVIOR_COOKIE
				end if
			case KEY_H :
				'view outerHTML
				'alert "view outerHTML"
				getOuterHTML
			case KEY_L :
				'view Log
				'alert "view Log"
				viewLog
			case KEY_X :
				'view getXmlData
				'alert "get xml data"
				getXmlData
		end select
	end if
End Sub


'****************************************************************************************

'<Log Utility
Set m_Logger = new Logger
Function beginLog()
	if m_bDebug then m_Logger.begin
End Function
Function middleLog(str)
	if m_bDebug then m_Logger.middle str
End Function
Function middleLog2(str, key)
	if m_bDebug then m_Logger.middle2 str, key
End Function
Function endLog(str)
	if m_bDebug then m_Logger.endLog str
End Function
Function clearLog()
	if m_bDebug then m_Logger.clear
End Function
'>

'< Cache Utility
Set m_Cache = new Cache
Function PutCache(sNs, sItem, value)
	m_Cache.putCache sNs, sItem, value
End Function

Function GetCache(sNs, sItem)
	GetCache = m_Cache.getCache(sNs, sItem)
End Function

Function ClearCache()
	m_Cache.clearCache
End Function
'>

'< Session Utility
Function CheckSession(oResponse)
	'return 	0  	-表示Session没有失效
	'			1	-表示Session已失效，且已重新登录
	'			-1	-表示Session已失效，且没有重新登录（也有可能是其他原因），如果返回此值，通知程序不要执行
	Dim sServer, ret, statusCode
	CheckSession = 0
	statusCode = oResponse.status
	select case statusCode
		case "200"
			sServer = oResponse.getResponseHeader("Server")
			'alert sServer
			if InStr(UCase(sServer),UCase(C_IDS_SERVER)) then
				'alert document.body.UserID
				ret = window.showModalDialog("/epstar/app/login.jsp?userid=" & document.body.UserID, window, "dialogHeight:130px;dialogWidth:160px;center:yes;status:yes;help:no;resizable:no;scroll:no;status:no")
				if ret then
					CheckSession = 1
				else
					CheckSession = -1
				end if
			end if
		case "404"
			alert "服务器找不到请求的网页！"
			CheckSession = -1
		case "500"
			alert "服务器内部错误！"
			CheckSession = -1
	end Select
End Function
'>

'从URL中找出查询字符串的值
Function SearchURL(sURL, sName)
		Dim C_NullValue
		C_NullValue = Chr(0) & Chr(1) &	Chr(2)
		Dim	tmpArr,	tmpArr2, i,	j
		tmpArr = Split(sURL, "&")
		For	i =	LBound(tmpArr) to UBound(tmpArr)
				tmpArr2	= Split(tmpArr(i), "=")
				If UBound(tmpArr2) >= 1	Then
						If LCase(Trim(tmpArr2(0))) = LCase(Trim(sName))	Then
								'< edited by hmyou 2004-08-16 >
								for	j =	1 to UBound(tmpArr2)
										SearchURL =	SearchURL &	"="	& tmpArr2(j)
								next
								SearchURL =	Right(SearchURL,Len(SearchURL) - 1)
								'>
								Exit Function
						End	If
				End	If
		Next
		SearchURL =	C_NullValue
End	Function

'< Cookie Utility
	' Create a cookie with the specified name and value.
	' The cookie expires at the end of the 20th century.
	Public Sub SetCookie(sName, sValue)
	  	document.cookie = sName & "=" & escape(sValue) & "; expires=" & DateAdd("m", 1, Now)
	End Sub

	' Retrieve the value of the cookie with the specified name.
	Public Function GetCookie(sName)
	  	' cookies are separated by semicolons
	 	Dim aCookie, i, aCrumb
	  	aCookie = Split(document.cookie, "; ")
	  	for i = LBound(aCookie) to UBound(aCookie)
	  		aCrumb = Split(aCookie(i), "=")
	  		if (sName = aCrumb(0)) then
	  			GetCookie = unescape(aCrumb(1))
	  			Exit Function
	  		end if
		next

		' a cookie with the requested name does not exist
		GetCookie = ""
	End Function

	' Delete the cookie with the specified name.
	Public Sub DelCookie(sName)
		Dim sValue
		sValue = "-1"
	 	document.cookie = sName & "=" & escape(sValue) & "; expires=Fri, 31 Dec 1900 23:59:59 GMT;"
	End Sub
'>

'< Misc Utility

' shortcut : CTRL+ALT+H
Function getOuterHTML()
	Dim str, wnd
	str = document.body.outerHTML
	set wnd = window.open("about:blank","","height=400,width=800,status=no,toolbar=no,menubar=no,location=no,left=0,top=0")
	wnd.document.write("<textarea style='width:100%' rows='20'>" & str & "</textarea>")
End Function

'shortcut : CTRL+ALT+X
Function getXmlData()
	Dim xmlret, wnd
	xmlret = document.body.GetXmlData()
	set wnd = window.open("about:blank","","height=200,width=400,status=no,toolbar=no,menubar=no,location=no")
	wnd.document.write("<textarea style='width:100%' rows='10'>" & xmlret & "</textarea>")
End Function

' shortcut : CTRL+ALT+L
Function viewLog()
	Dim str, wnd
	str = m_Logger.toString(vbNewLine)
	set wnd = window.open("about:blank","","height=400,width=800,status=no,toolbar=no,menubar=no,location=no,left=0,top=0")
	wnd.document.write("<textarea style='width:100%' rows='20'>" & str & "</textarea>")
End Function

Function StringURLEncode(strInput)
	Dim cset
	cset = LCase(document.charset)
	'If cset = "UTF-8" Then
		StringURLEncode = window.encodeURI(strInput)
		Exit Function
	'End If

	Dim strEncoded, regExp, chCurr, strHexVal, objMatches, objMatch

	strEncoded = strInput

	' First, replace all % signs with the encoded value.
	strEncoded = Replace(strEncoded, "%", "%25")

	' Next, replace all non-alphanumeric characters with their encoded
	' values, EXCEPT FOR the spaces (" ").
	Set regExp = New RegExp
	regExp.Pattern = "[^%A-Za-z0-9 ]"
	regExp.Global = False
	Do
		Set objMatches = regExp.Execute(strEncoded)
		If objMatches.Count <> 0 Then
			' Get the character that matched.
			Set objMatch = objMatches.Item(0)

			' Create the URL-Encoded version.
			chCurr = objMatch.Value

			If Abs(Asc(chCurr)) < &HFF Then
				'strReturn = strReturn & ThisChr
				strHexVal = Hex(Asc(chCurr))
				strHexVal = String(2 - Len(strHexVal), "0") & strHexVal
				' Replace the character with the URL-Encoded version.
				strEncoded = Replace(strEncoded, chCurr, "%" & strHexVal)
			Else
				Dim innerCode, Hight8, Low8
				innerCode = Asc(chCurr)
				If innerCode < 0 Then
						innerCode = innerCode + &H10000
				End If
				Hight8 = (innerCode  And &HFF00)\ &HFF
				Low8 = innerCode And &HFF
				'strReturn = strReturn & "%" & Hex(Hight8) &  "%" & Hex(Low8)
				strEncoded = Replace(strEncoded, chCurr, "%" & Hex(Hight8) &  "%" & Hex(Low8))
			End If

		End If
	Loop While objMatches.Count <> 0

	' Finally, replace spaces.
	strEncoded = Replace(strEncoded, " ", "+")

	StringURLEncode = strEncoded
End Function

Function Test()
	alert ""
End Function
'>

'initialize
if m_bDebug then beginLog


'***********************************************************************************************************
' Class Definition
'< Logger Class
Class Logger
	Private m_log
	Private m_dic
	Private m_begin
	Private m_middle
	Private m_end
	Private m_logTime

	Private Sub Class_Initialize
		set m_log = new StringBuffer
		set m_dic = CreateObject("Scripting.Dictionary")
	End Sub
	Private Sub Class_Terminate
		m_log.clear
		set m_log = nothing
		m_dic.RemoveAll
		set m_dic = nothing
	End Sub

	Public Sub begin()
		clear
		m_begin = Timer
		m_middle = m_begin
		m_end = m_begin
		println("Begin  : " & Now)
	End Sub
	Public Sub middle(str)
		Dim dura, dura2begin, end_
		end_ = Timer
		'dura = (end_ - m_middle) * MS_POWER
		dura = timeDiff(end_, m_middle)
		'dura2begin = (end_ - m_begin) * MS_POWER
		dura2begin = timeDiff(end_, m_begin)
		m_middle = end_
		m_end = end_
		println("Middle : " & str & " Duration : " & dura & " (ms), Time : " & dura2begin & " (ms)")
	End Sub
	Public Sub middle2(str, key)
		Dim dura, dura2begin, end_
		end_ = Timer
		'dura = (end_ - m_middle) * MS_POWER
		dura = timeDiff(end_, m_middle)
		'dura2begin = (end_ - m_begin) * MS_POWER
		dura2begin = timeDiff(end_, m_begin)
		m_middle = end_
		m_end = end_
		println("Middle : " & str & " Duration : " & dura & " (ms), Time : " & dura2begin & " (ms)")

		Dim info
		if m_dic.Exists(key) then
			info = m_dic.Item(key)
			info(0) = info(0) + 1
			info(1) = info(1) + dura
			m_dic.Item(key) = info
		else
			info = Array(1, dura)
			'info2(0) = 1
			'info2(1) = dura
			m_dic.Add key, info
		end if
	End Sub
	Public Sub endLog(str)
		Dim end_, dura, dura2begin
		end_ = Timer
		'dura = (end_ - m_middle) * MS_POWER
		dura = timeDiff(end_, m_middle)
		'dura2begin = (end_ - m_begin) * MS_POWER
		dura2begin = timeDiff(end_, m_begin)
		m_end = end_

		println("End    : " & str & " Duration : " & dura & " (ms), Time : " & dura2begin & " (ms)")
		println("--------------------------------------------------------------------------------------")
		println("Statistic ********************")

		Dim key, keys, info
		keys = m_dic.Keys()
		for each key in keys
			info = m_dic.Item(key)
			println("      " & key & " -- " & " hit count : " & info(0) & " (times), summation of duration : " & info(1) & " (ms)")
		next
		m_dic.RemoveAll()

		println("      logTime : " & m_logTime & " (ms)")
		println("End ********************")
	End Sub
	Public Sub clear()
		m_log.clear
		m_logTime = 0
	End Sub

	Private Sub println(str)
		Dim t
		't = Timer
		m_log.append str
		'm_logTime = m_logTime + timeDiff(Timer, t)
	End Sub
	Private Sub print(str)
		Dim t
		't = Timer
		m_log.append str
		'm_logTime = m_logTime + timeDiff(Timer, t)
	End Sub
	Public Function toString(delimiter)
		toString = m_log.joinToString(delimiter)
	End Function

	Private Function timeDiff(time1, time2)
		timeDiff = (time1 * MS_POWER - time2 * MS_POWER)
	End Function
End Class
'>

'< StringBuffer Class
Class StringBuffer
	Private m_buffer
	Private m_bufidx
	Private m_size

	Private Sub Class_Initialize
		clear()
	End Sub
	Private Sub Class_Terminate
	End Sub

	Public Sub append(str)
		ensureSize()
		m_buffer(m_bufidx) = str
		m_bufidx = m_bufidx + 1
	End Sub

	Public Function joinToString(delimiter)
		trimTo
		joinToString = Join(m_buffer, delimiter)
	End Function

	Private Sub trimTo()
		if (m_bufidx) < m_size then
			m_size = m_bufidx
			redim preserve m_buffer(m_size)
		end if
	End Sub
	Private Sub ensureSize()
		if (m_bufidx) > m_size then
			m_size = CInt((m_size * 3) / 2 + 1)
			redim preserve m_buffer(m_size)
		end if
	End Sub

	Public Sub clear()
		m_bufidx = 0
		m_size = 0
		redim m_buffer(0)
	End Sub
End Class
'>



'< Cache Class
Class Cache
	Private Sub Class_Initialize
		set m_cache = CreateObject("Scripting.Dictionary")
	End Sub
	Private Sub Class_Terminate
		m_cache.RemoveAll
		set m_cache = nothing
	End Sub

	Private m_cache

	Public Sub putCache(sNs, sItem, value)
		dim oItem
		if m_cache.Exists(sNs) then
			set oItem = m_cache.Item(sNs)
			if oItem.Exists(sItem) then
				oItem.Item(sItem) = value
			else
				oItem.Add sItem, value
			end if
		else
			set oItem = CreateObject("Scripting.Dictionary")
			oItem.Add sItem, value
			m_cache.Add sNs, oItem
		end if
	End Sub

	Public Function getCache(sNs, sItem)
		dim oItem
		if m_cache.Exists(sNs) then
			set oItem = m_cache.Item(sNs)
			getCache = oItem.Item(sItem)
		end if
	End Function

	Public Sub clearCache()
		m_cache.RemoveAll
	End Sub

End Class
'>

Function sURLEncodeEx(strInput,	chset)
	Dim	cset
	cset = LCase(chset)
	'If cset	= "UTF-8" Then
			sURLEncodeEx = window.encodeURI(strInput)
			Exit Function
	'End	If

	Dim	strEncoded,	regExp,	chCurr,	strHexVal, objMatches, objMatch

	strEncoded = strInput

	' First, replace all % signs with the encoded value.
	strEncoded = Replace(strEncoded, "%", "%25")

	' Next,	replace	all	non-alphanumeric characters	with their encoded
	' values, EXCEPT FOR the spaces	(" ").
	Set	regExp = New RegExp
	regExp.Pattern = "[^%A-Za-z0-9 ]"
	regExp.Global =	False
	Do
			Set	objMatches = regExp.Execute(strEncoded)
			If objMatches.Count	<> 0 Then
					' Get the character	that matched.
					Set	objMatch = objMatches.Item(0)

					' Create the URL-Encoded version.
					chCurr = objMatch.Value

					If Abs(Asc(chCurr))	< &HFF Then
							'strReturn = strReturn & ThisChr
							strHexVal =	Hex(Asc(chCurr))
							strHexVal =	String(2 - Len(strHexVal), "0")	& strHexVal
							' Replace the character	with the URL-Encoded version.
							strEncoded = Replace(strEncoded, chCurr, "%" & strHexVal)
					Else
							Dim	innerCode, Hight8, Low8
							innerCode =	Asc(chCurr)
							If innerCode < 0 Then
											innerCode =	innerCode +	&H10000
							End	If
							Hight8 = (innerCode	 And &HFF00)\ &HFF
							Low8 = innerCode And &HFF
							'strReturn = strReturn & "%" & Hex(Hight8) &  "%" &	Hex(Low8)
							strEncoded = Replace(strEncoded, chCurr, "%" & Hex(Hight8) &  "%" &	Hex(Low8))
					End	If

			End	If
	Loop While objMatches.Count	<> 0

	' Finally, replace spaces.
	strEncoded = Replace(strEncoded, " ", "+")

	sURLEncodeEx = strEncoded
End	Function

function ErrorMessage(str)
	Dim	xmldoc
	set	xmldoc = createObject("MsXml2.DomDocument")
	xmldoc.loadXml("<Message type='unsuccessful'>" & str & "</Message>")
	set	ErrorMessage = xmldoc
end	Function

Function sURLEncode(strInput)
	sURLEncode = sURLEncodeEx(strInput,	document.charset)
End	Function