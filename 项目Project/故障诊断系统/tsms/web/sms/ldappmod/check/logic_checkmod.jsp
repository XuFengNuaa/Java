<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.LeaderCheck,
java.io.PrintWriter,
java.net.URLEncoder,
java.sql.PreparedStatement,
java.sql.ResultSet,
java.sql.Statement,
java.sql.SQLException,
java.util.HashMap,
java.util.UUID,
com.nuaa.sys.util.DbUtil,
com.nuaa.sys.util.PublicUtil,
com.nuaa.sys.util.IResultSetProcessor,
com.nuaa.sys.util.IArrayPreparedStatementProcessor,
com.nuaa.sys.util.IStatementProcessor;"
%>

<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");
	String stuff_num=(String)session.getAttribute("userid");
	LeaderCheck um=(LeaderCheck)AppInsFactory.getBean("LeaderCheck");
	PrintWriter writer = response.getWriter();
	String result="0";
	
	try{
		if("QUERYC".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			result=um.getQuerycut(hashMap).toString();
		
		}else if("QUERYCT".equals(type)){
				
			HashMap hashMap=new HashMap();
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			result=um.getQueryct(hashMap).toString();
		
		}else if("QUERYM".equals(type)){
			
			HashMap hashMap=new HashMap();
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			result=um.getQuerym(hashMap).toString();
		
		}else if("QUERYMT".equals(type)){
			
			HashMap hashMap=new HashMap();
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			result=um.getQuerymt(hashMap).toString();
		
		}else if("QUERYCG".equals(type)){
		
			HashMap hashMap=new HashMap();
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			result=um.getQueryCgout(hashMap).toString();
			
		}else if("DETAILGRIND".equals(type)){
		
			HashMap hashMap=new HashMap();			
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashMap.put("order",request.getParameter("order"));
			}
			result=um.detail_do_togrind(hashMap).toString();
			
		}else if("QUERYCGIN".equals(type)){
		
			HashMap hashMap=new HashMap();
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			result=um.getQueryCgin(hashMap).toString();
		
		}else if("grd_numDetail".equals(type)){
			
			HashMap hashMap=new HashMap();			
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashMap.put("order",request.getParameter("order"));
			}
			result=um.detail_do_ingrind(hashMap).toString();	
			
		}else if("APPLY".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.cutCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCut(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
		
		}else if("CRefuse".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.cutCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refCut(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYCT".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.ctCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCt(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
		
		}else if("CTRefuse".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.ctCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refCt(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYM".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.mCheck(hashMap);
				
			if(returncheck){
				String returnValue = um.appM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("MRefuse".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.mCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYMT".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.mtCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appMt(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
	
		}else if("MTRefuse".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.mtCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refMt(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
				
			
		}else if("APPLYCG".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("grd_num")!=null){
				hashMap.put("grd_num",request.getParameter("grd_num"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCgout(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYCGm".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("Rid")!=null){
				hashMap.put("Rid",request.getParameter("Rid"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdMCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCgoutM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("RefuseM".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("Rid")!=null){
				hashMap.put("Rid",request.getParameter("Rid"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdMCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refCgoutM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("Refuse".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("grd_num")!=null){
				hashMap.put("grd_num",request.getParameter("grd_num"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refCgout(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYCGIN".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("grd_num")!=null){
				hashMap.put("grd_num",request.getParameter("grd_num"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdinCheck(hashMap);
			System.out.println("returnchcek=="+returncheck);
			if(returncheck){
				String returnValue = um.appCgin(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
		
		}else if("APPLYCGINm".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("Rid")!=null){
				hashMap.put("Rid",request.getParameter("Rid"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdinMCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCginM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
		}
		
	}catch(Exception e){
		e.printStackTrace();
		result="{success:false}";
	}finally{
		response.setContentType("text/html;charset=UTF-8");
		writer.write(result);		
		writer.close();
	}



%>