<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,org.json.*,java.io.*,com.nuaa.sys.util.DbUtil,com.nuaa.sys.util.PublicUtil,java.util.UUID,java.sql.*,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.MillSim_Mod"%>
<%@ page import="org.apache.commons.fileupload.*,javax.sql.DataSource" %>
<%@ page import="com.nuaa.sys.util.Logger,com.nuaa.app.RoleLevel,com.nuaa.app.FileState"%>

<%  
	//不允许浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	String type = request.getParameter("type");
   try{
    	if("viewimg".equals(type)){
    		DbUtil DbUtil = new DbUtil();
    		HashMap hashMap=new HashMap();
    		System.out.println(request.getParameter("tuhao_sim"));
    		String sqlout = "select * from T_MILLSIM where tuhao_sim = '"+request.getParameter("tuhao_sim")+"'";
    		PreparedStatement ps = null;
    		ResultSet rs = null;
    		InputStream is = null;
    		OutputStream os = null;
    		DataSource ds = DbUtil.getDs();
    		Connection conn = ds.getConnection();
    		ps = conn.prepareStatement(sqlout);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			is = rs.getBinaryStream("imgvalue");
    			response.setContentType("image/jpeg");
    			os = response.getOutputStream();
    			 String len=is.toString();
    			 long length=len.length();		
    		 byte[] buffer = new byte[(int)length/7];
    			while ((length = is.read(buffer)) != -1) {
    			os.write(buffer);
    			}
    		}
    		 
    		os.flush(); 
			os.close();
    		os=null;
    		response.flushBuffer();  
    		out.clear();  
    		out = pageContext.pushBody();
    		 
    	}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
	
%>