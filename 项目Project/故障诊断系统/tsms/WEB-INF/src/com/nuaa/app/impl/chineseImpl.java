package com.nuaa.app.impl;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.app.chinese;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class chineseImpl implements chinese {
	public HashMap uploadFile2(HashMap fileHmap) {
		// TODO Auto-generated method stub
		final HashMap fileHashMap=fileHmap;	
    	final HashMap AniHashMap=new HashMap();	
    	String id1 = fileHashMap.get("special").toString();
    	String id="'"+id1+"'";
    	System.out.println("66666666"+id);
    	String tempPath = "\\web\\chinese\\"+id+"\\"; // 用于存放临时文件的目录
    	//String imgdir = "\\image\\";
		String trace="";
		String filename="";
		String fileid="";
		String types="";
		String dir=(String)fileHashMap.get("dir");//文件路径
		//建临时文件夹
		File fii=new File(dir+tempPath);
		if(!fii.exists()) {
		   fii.mkdirs(); 
		}
		try{		
			List fileItems=(List)fileHashMap.get("fileItems");
			Iterator i = fileItems.iterator();
			while(i.hasNext()){		
    			  FileItem fi = (FileItem)i.next();
    			  String fileName = fi.getName();
  			 	  if(fileName!=null){
    					String[] s=fileName.split("\\.");
    					String[] name = s[s.length-2].split("\\\\");
    					filename=name[name.length-1]+"."+s[s.length-1];
    					String[] n=filename.split("\\.");
    					fileid=n[0];
    					types=s[s.length-1];
    					System.out.println(filename);
    					trace=tempPath+filename;
    					fi.write(new File(dir+trace));
    					AniHashMap.put("fileid",fileid);
    					AniHashMap.put("types",types);
    					HashMap hashmap = new HashMap();
    					hashmap.put("dir",dir+tempPath);
    					hashmap.put("tpmc",filename);
    					hashmap.put("id",id);
    					FileIn file = new FileIn();
    					file.In1(hashmap);
    					
    			  }
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return AniHashMap;
	};
	@Override
	public 	String getFile2(String dir,String tuhao){
		String bb="";
		//String aa="";
		System.out.println("hhhh");
		File fii=new File(dir+"\\web\\chinese\\"+tuhao+"\\");
		System.out.println(dir+"\\web\\chinese\\"+tuhao+"\\");
		if(!fii.exists()) {
			System.out.println("chucuo");
			   return bb; 
			}
		File[] files=fii.listFiles();
		bb+="<script>function t(f){window.open(f);};</script>";
		for(int i=0;i<files.length;i++){
			String typ = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1, files[i].getName().length()).toLowerCase();
			if(typ.equals("jpg")||typ.equals("png")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\chinese\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\">" + files[i].getName() +"</a><br/>";
			}
			if(typ.equals("docx")||typ.equals("doc")||typ.equals("pdf")||typ.equals("txt")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\chinese\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\" style=\"color:#FF00FF;text-decoration:underline;\"> "+
						files[i].getName() + "</a><br/>"; 
				//aa+=dir+"\\web\\chinese\\"+tuhao+"\\"+ files[i].getName() ;
			}
		}
		System.out.println("returned file xml  is "+bb);
		//System.out.println(aa);
		return bb;	
	} 
	public JSONObject getQueryApprove2(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryMap;
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		final String filter = (String)queryHashMap.get("filter");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from t_chinese2 where "+filter;
		Logger.debug(sql);
		final String [] count=new String[1];
		count[0] = "";
		try {
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
					}
				}	
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		//String filter=(String)queryHashMap.get("filter");
		
		sql="select * from  t_chinese2  where "+filter;  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("tpmc", rs.getString("tpmc"));
							obj.put("time",rs.getString("time"));
							obj.put("id",rs.getString("id"));
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Logger.debug(jsonObj.toString());
		return jsonObj;
	}
	@Override
	public String delPart2(HashMap hashMap) {
		// TODO Auto-generated method stub
		final String id=(String)hashMap.get("id");
		final String []result=new String[1];
		result[0]="true";
		String []sqls=new String[1];
		final String[] sql = new String[1];
		sql[0] = "delete from t_chinese2 where id in ('',"+id+")";
		String sql2 = "select * from t_chinese2 where id in ('',"+id+")";
		try {
			DbUtil.execute(sql2,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor() {
										public void process(PreparedStatement[] pstmts){
											try {
												pstmts[0].addBatch();
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}	
								}
							});
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "false";
		}
		return "true";
	};
	@Override
	public boolean addFile2(HashMap addMap) {
		// TODO Auto-generated method stub
		boolean result = false;
		HashMap addHashMap = addMap;
		final String[] pa = new String[4];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		final String id = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");//
		pa[2] = (String) addHashMap.get("pa[2]");//
		pa[3] = (String) addHashMap.get("pa[3]");//
		String[] sql = new String[1];
		System.out.println(pa[1]);
		 sql[0] = "insert into t_chinese2 (ID,scr,tpmc,time)values(?,?,?,to_date(?,'yyyy/mm/dd'))";
		 try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,id);
					pstmts[0].setString(2,pa[1]);
					pstmts[0].setString(3,pa[2]);
					pstmts[0].setString(4,pa[3]);
					pstmts[0].addBatch();
				}
			 });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	};
	public HashMap uploadFile1(HashMap fileHmap) {
		// TODO Auto-generated method stub
		final HashMap fileHashMap=fileHmap;	
    	final HashMap AniHashMap=new HashMap();	
    	String id1 = fileHashMap.get("special").toString();
    	String id="'"+id1+"'";
    	System.out.println("66666666"+id);
    	String tempPath = "\\web\\chinese\\"+id+"\\"; // 用于存放临时文件的目录
    	//String imgdir = "\\image\\";
		String trace="";
		String filename="";
		String fileid="";
		String types="";
		String dir=(String)fileHashMap.get("dir");//文件路径
		//建临时文件夹
		File fii=new File(dir+tempPath);
		if(!fii.exists()) {
		   fii.mkdirs(); 
		}
		try{		
			List fileItems=(List)fileHashMap.get("fileItems");
			Iterator i = fileItems.iterator();
			while(i.hasNext()){		
    			  FileItem fi = (FileItem)i.next();
    			  String fileName = fi.getName();
  			 	  if(fileName!=null){
    					String[] s=fileName.split("\\.");
    					String[] name = s[s.length-2].split("\\\\");
    					filename=name[name.length-1]+"."+s[s.length-1];
    					String[] n=filename.split("\\.");
    					fileid=n[0];
    					types=s[s.length-1];
    					System.out.println(filename);
    					trace=tempPath+filename;
    					fi.write(new File(dir+trace));
    					AniHashMap.put("fileid",fileid);
    					AniHashMap.put("types",types);
    					HashMap hashmap = new HashMap();
    					hashmap.put("dir",dir+tempPath);
    					hashmap.put("tpmc",filename);
    					hashmap.put("id",id);
    					FileIn file = new FileIn();
    					file.In1(hashmap);
    					
    			  }
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return AniHashMap;
	};
	@Override
	public 	String getFile1(String dir,String tuhao){
		String bb="";
		//String aa="";
		System.out.println("hhhh");
		File fii=new File(dir+"\\web\\chinese\\"+tuhao+"\\");
		System.out.println(dir+"\\web\\chinese\\"+tuhao+"\\");
		if(!fii.exists()) {
			System.out.println("chucuo");
			   return bb; 
			}
		File[] files=fii.listFiles();
		bb+="<script>function t(f){window.open(f);};</script>";
		for(int i=0;i<files.length;i++){
			String typ = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1, files[i].getName().length()).toLowerCase();
			if(typ.equals("jpg")||typ.equals("png")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\chinese\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\">" + files[i].getName() +"</a><br/>";
			}
			if(typ.equals("docx")||typ.equals("doc")||typ.equals("pdf")||typ.equals("txt")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\chinese\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\" style=\"color:#FF00FF;text-decoration:underline;\"> "+
						files[i].getName() + "</a><br/>"; 
				//aa+=dir+"\\web\\chinese\\"+tuhao+"\\"+ files[i].getName() ;
			}
		}
		System.out.println("returned file xml  is "+bb);
		//System.out.println(aa);
		return bb;	
	} 
	public JSONObject getQueryApprove1(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryMap;
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		final String filter = (String)queryHashMap.get("filter");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from t_chinese1 where "+filter;
		Logger.debug(sql);
		final String [] count=new String[1];
		count[0] = "";
		try {
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
					}
				}	
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		//String filter=(String)queryHashMap.get("filter");
		
		sql="select * from  t_chinese1  where "+filter;  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("tpmc", rs.getString("tpmc"));
							obj.put("time",rs.getString("time"));
							obj.put("id",rs.getString("id"));
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Logger.debug(jsonObj.toString());
		return jsonObj;
	}
	@Override
	public String delPart1(HashMap hashMap) {
		// TODO Auto-generated method stub
		final String id=(String)hashMap.get("id");
		final String []result=new String[1];
		result[0]="true";
		String []sqls=new String[1];
		final String[] sql = new String[1];
		sql[0] = "delete from t_chinese1 where id in ('',"+id+")";
		String sql2 = "select * from t_chinese1 where id in ('',"+id+")";
		try {
			DbUtil.execute(sql2,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor() {
										public void process(PreparedStatement[] pstmts){
											try {
												pstmts[0].addBatch();
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}	
								}
							});
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "false";
		}
		return "true";
	};
	@Override
	public boolean addFile1(HashMap addMap) {
		// TODO Auto-generated method stub
		boolean result = false;
		HashMap addHashMap = addMap;
		final String[] pa = new String[4];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		final String id = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");//
		pa[2] = (String) addHashMap.get("pa[2]");//
		pa[3] = (String) addHashMap.get("pa[3]");//
		String[] sql = new String[1];
		System.out.println(pa[1]);
		 sql[0] = "insert into t_chinese1 (ID,scr,tpmc,time)values(?,?,?,to_date(?,'yyyy/mm/dd'))";
		 try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,id);
					pstmts[0].setString(2,pa[1]);
					pstmts[0].setString(3,pa[2]);
					pstmts[0].setString(4,pa[3]);
					pstmts[0].addBatch();
				}
			 });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	};
	public HashMap uploadFile(HashMap fileHmap) {
		// TODO Auto-generated method stub
		final HashMap fileHashMap=fileHmap;	
    	final HashMap AniHashMap=new HashMap();	
    	String id1 = fileHashMap.get("special").toString();
    	String id="'"+id1+"'";
    	System.out.println("66666666"+id);
    	String tempPath = "\\web\\chinese\\"+id+"\\"; // 用于存放临时文件的目录
    	//String imgdir = "\\image\\";
		String trace="";
		String filename="";
		String fileid="";
		String types="";
		String dir=(String)fileHashMap.get("dir");//文件路径
		//建临时文件夹
		File fii=new File(dir+tempPath);
		if(!fii.exists()) {
		   fii.mkdirs(); 
		}
		try{		
			List fileItems=(List)fileHashMap.get("fileItems");
			Iterator i = fileItems.iterator();
			while(i.hasNext()){		
    			  FileItem fi = (FileItem)i.next();
    			  String fileName = fi.getName();
  			 	  if(fileName!=null){
    					String[] s=fileName.split("\\.");
    					String[] name = s[s.length-2].split("\\\\");
    					filename=name[name.length-1]+"."+s[s.length-1];
    					String[] n=filename.split("\\.");
    					fileid=n[0];
    					types=s[s.length-1];
    					System.out.println(filename);
    					trace=tempPath+filename;
    					fi.write(new File(dir+trace));
    					AniHashMap.put("fileid",fileid);
    					AniHashMap.put("types",types);
    					HashMap hashmap = new HashMap();
    					hashmap.put("dir",dir+tempPath);
    					hashmap.put("tpmc",filename);
    					hashmap.put("id",id);
    					FileIn file = new FileIn();
    					file.In1(hashmap);
    					
    			  }
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return AniHashMap;
	};
	@Override
	public 	String getFile(String dir,String tuhao){
		String bb="";
		//String aa="";
		System.out.println("hhhh");
		File fii=new File(dir+"\\web\\chinese\\"+tuhao+"\\");
		System.out.println(dir+"\\web\\chinese\\"+tuhao+"\\");
		if(!fii.exists()) {
			System.out.println("chucuo");
			   return bb; 
			}
		File[] files=fii.listFiles();
		bb+="<script>function t(f){window.open(f);};</script>";
		for(int i=0;i<files.length;i++){
			String typ = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1, files[i].getName().length()).toLowerCase();
			if(typ.equals("jpg")||typ.equals("png")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\chinese\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\">" + files[i].getName() +"</a><br/>";
			}
			if(typ.equals("docx")||typ.equals("doc")||typ.equals("pdf")||typ.equals("txt")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\chinese\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\" style=\"color:#FF00FF;text-decoration:underline;\"> "+
						files[i].getName() + "</a><br/>"; 
				//aa+=dir+"\\web\\chinese\\"+tuhao+"\\"+ files[i].getName() ;
			}
		}
		System.out.println("returned file xml  is "+bb);
		//System.out.println(aa);
		return bb;	
	} 
	public JSONObject getQueryApprove(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryMap;
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		final String filter = (String)queryHashMap.get("filter");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from t_chinese where "+filter;
		Logger.debug(sql);
		final String [] count=new String[1];
		count[0] = "";
		try {
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
					}
				}	
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		//String filter=(String)queryHashMap.get("filter");
		
		sql="select * from  t_chinese  where "+filter;  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("tpmc", rs.getString("tpmc"));
							obj.put("time",rs.getString("time"));
							obj.put("id",rs.getString("id"));
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Logger.debug(jsonObj.toString());
		return jsonObj;
	}
	@Override
	public String delPart(HashMap hashMap) {
		// TODO Auto-generated method stub
		final String id=(String)hashMap.get("id");
		final String []result=new String[1];
		result[0]="true";
		String []sqls=new String[1];
		final String[] sql = new String[1];
		sql[0] = "delete from t_chinese where id in ('',"+id+")";
		String sql2 = "select * from t_chinese where id in ('',"+id+")";
		try {
			DbUtil.execute(sql2,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor() {
										public void process(PreparedStatement[] pstmts){
											try {
												pstmts[0].addBatch();
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}	
								}
							});
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "false";
		}
		return "true";
	};
	@Override
	public boolean addFile(HashMap addMap) {
		// TODO Auto-generated method stub
		boolean result = false;
		HashMap addHashMap = addMap;
		final String[] pa = new String[4];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		final String id = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");//
		pa[2] = (String) addHashMap.get("pa[2]");//
		pa[3] = (String) addHashMap.get("pa[3]");//
		String[] sql = new String[1];
		System.out.println(pa[1]);
		 sql[0] = "insert into t_chinese (ID,scr,tpmc,time)values(?,?,?,to_date(?,'yyyy/mm/dd'))";
		 try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,id);
					pstmts[0].setString(2,pa[1]);
					pstmts[0].setString(3,pa[2]);
					pstmts[0].setString(4,pa[3]);
					pstmts[0].addBatch();
				}
			 });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}

}