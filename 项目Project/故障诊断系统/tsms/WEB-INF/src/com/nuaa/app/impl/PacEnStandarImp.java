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
import com.nuaa.app.PacEnStandar;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class PacEnStandarImp implements PacEnStandar {
	@Override
	public 	String getFile(String dir,String mnum){
		String bb="";
		System.out.println("hhhh");
		File fii=new File(dir+"\\web\\application\\"+mnum+"\\");
		System.out.println(dir+"\\web\\application\\"+mnum+"\\");
		if(!fii.exists()) {
			System.out.println("chucuo");
			   return bb; 
			}
		File[] files=fii.listFiles();
		bb+="<script>function t(f){window.open(f);};</script>";
		for(int i=0;i<files.length;i++){
			String typ = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1, files[i].getName().length()).toLowerCase();
			if(typ.equals("jpg")||typ.equals("png")||typ.equals("mp3")||typ.equals("mp4")||typ.equals("txt")||typ.equals("avi")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\application\\"+mnum+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\">" + files[i].getName() +"</a><br/>";
			}
			if(typ.equals("docx")||typ.equals("doc")||typ.equals("pdf")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\applicatin\\"+mnum+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\" style=\"color:#FF00FF;text-decoration:underline;\"> "+
						files[i].getName() + "</a><br/>"; 
			}
		}
		System.out.println("returned file xml  is "+bb);
		return bb;	
	};
	@Override
	public 	String getFile1(String dir,String mnum){
		String bb="";
		System.out.println("hhhh");
		File fii=new File(dir+"\\web\\answer\\"+mnum+"\\");
		System.out.println(dir+"\\web\\answer\\"+mnum+"\\");
		if(!fii.exists()) {
			System.out.println("chucuo");
			   return bb; 
			}
		File[] files=fii.listFiles();
		bb+="<script>function t(f){window.open(f);};</script>";
		for(int i=0;i<files.length;i++){
			String typ = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1, files[i].getName().length()).toLowerCase();
			if(typ.equals("jpg")||typ.equals("png")||typ.equals("mp3")||typ.equals("mp4")||typ.equals("txt")||typ.equals("avi")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\answer\\"+mnum+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\">" + files[i].getName() +"</a><br/>";
			}
			if(typ.equals("docx")||typ.equals("doc")||typ.equals("pdf")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\answer\\"+mnum+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\" style=\"color:#FF00FF;text-decoration:underline;\"> "+
						files[i].getName() + "</a><br/>"; 
			}
		}
		System.out.println("returned file xml  is "+bb);
		return bb;	
	} 
	public HashMap uploadFile2(HashMap fileHmap) {
		// TODO Auto-generated method stub
		final HashMap fileHashMap=fileHmap;	
    	final HashMap AniHashMap=new HashMap();	
    	String id = fileHashMap.get("id").toString();
    	System.out.println("66666666"+id);
    	String tempPath = "\\web\\answer\\"+id+"\\"; // 用于存放临时文件的目录
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
	}

	@Override
	public HashMap uploadFile1(HashMap fileHmap) {
		// TODO Auto-generated method stub
		final HashMap fileHashMap=fileHmap;	
    	final HashMap AniHashMap=new HashMap();	
    	String id1 = fileHashMap.get("special").toString();
    	String id="'"+id1+"'";
    	System.out.println("66666666"+id);
    	String tempPath = "\\web\\application\\"+id+"\\"; // 用于存放临时文件的目录
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
	}
	public String addFile(HashMap addMap) {
		// TODO 自动生成方法存根
		String result = "false";
		HashMap addHashMap = addMap;
		String mnumid=(String)addHashMap.get("pa[1]");
		System.out.println("...................."+mnumid+"....................");
		  
		  //String sql="select * from  t_ct where mnum='"+mnum+"'";
			
		final String[] pa = new String[14];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		pa[0] = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");
		pa[2] = (String) addHashMap.get("pa[2]");
		pa[3] = (String) addHashMap.get("pa[3]");
		pa[4] = (String) addHashMap.get("pa[4]");
		pa[5] = (String) addHashMap.get("pa[5]");
		pa[6] = (String) addHashMap.get("pa[6]");
		pa[7] = (String) addHashMap.get("pa[7]");
		pa[8] = (String) addHashMap.get("pa[8]");
		pa[9] = (String) addHashMap.get("pa[9]");
		pa[10] = (String) addHashMap.get("pa[10]");
		pa[11] = (String) addHashMap.get("pa[11]");
		pa[12] = (String) addHashMap.get("pa[12]");
		pa[13] = (String) addHashMap.get("pa[13]");
		String[] sqls = new String[1];
		
		sqls[0] = "insert into  t_c_entry_trecord (entry_num,type_id,in_count,in_time,newstatus,status,in_problem,user_num,company,city,pclass,bclass,bill_num,denglu) values(?,?,?,to_date(?,'yyyy/mm/dd'),?,?,?,?,?,?,?,?,?,?)";
		Logger.debug(sqls[0]);
		try {
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(PreparedStatement[] pstmts)
										throws SQLException {
									pstmts[0].setString(1, pa[0]);
									pstmts[0].setString(2, pa[1]);
									pstmts[0].setString(3, pa[2]);
									pstmts[0].setString(4, pa[3]);
									pstmts[0].setString(5, pa[4]);
									pstmts[0].setString(6, pa[5]);
									pstmts[0].setString(7, pa[6]);
									pstmts[0].setString(8, pa[7]);
									pstmts[0].setString(9, pa[8]);
									pstmts[0].setString(10, pa[9]);
									pstmts[0].setString(11, pa[10]);
									pstmts[0].setString(12, pa[11]);
									pstmts[0].setString(13, pa[12]);
									pstmts[0].setString(14, pa[13]);
									pstmts[0].addBatch();
								}
							});
					result="true";
		
		}catch (SQLException e) {
			e.printStackTrace();
			result="false";
		}
		
		return result;
	}
	public String addFile1(HashMap addMap) {
		// TODO 自动生成方法存根
		String result = "false";
		HashMap addHashMap = addMap;
		String mnumid=(String)addHashMap.get("pa[1]");
		System.out.println("...................."+mnumid+"....................");
		  
		  //String sql="select * from  t_ct where mnum='"+mnum+"'";
			
		final String[] pa = new String[3];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		pa[0] = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");
		pa[2] = (String) addHashMap.get("pa[2]");
		//String[] sqls = new String[1];
		String sqls = "update  t_c_entry_trecord set answer='"+pa[2]+"' where entry_num="+pa[1]+"";
		System.out.println("111111111111"+sqls);
		Logger.debug(sqls);
		try {
			DbUtil.execute(sqls,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
				}
			});
					result="true";
		
		}catch (SQLException e) {
			e.printStackTrace();
			result="false";
		}
		
		return result;
	}
	public String addFile2(HashMap addMap) {
		// TODO 自动生成方法存根
		String result = "false";
		HashMap addHashMap = addMap;
		String mnumid=(String)addHashMap.get("pa[1]");
		System.out.println("...................."+mnumid+"....................");
		  
		  //String sql="select * from  t_ct where mnum='"+mnum+"'";
			
		final String[] pa = new String[8];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		pa[0] = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");
		pa[2] = (String) addHashMap.get("pa[2]");
		pa[3] = (String) addHashMap.get("pa[3]");
		pa[4] = (String) addHashMap.get("pa[4]");
		pa[5] = (String) addHashMap.get("pa[5]");
		pa[6] = (String) addHashMap.get("pa[6]");
		pa[7] = (String) addHashMap.get("pa[7]");
		//String[] sqls = new String[1];
		String sqls = "update  t_c_entry_trecord set score='"+pa[2]+"',sta='"+pa[3]+"',sta1='"+pa[4]+"',sta2='"+pa[5]+"',sta3='"+pa[6]+"',remarkjs='"+pa[7]+"' where entry_num="+pa[1]+"";
		System.out.println("111111111111"+sqls);
		Logger.debug(sqls);
		try {
			DbUtil.execute(sqls,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
				}
			});
					result="true";
		
		}catch (SQLException e) {
			e.printStackTrace();
			result="false";
		}
		
		return result;
	}
	
	public JSONObject getQueryMnumAll() {
	   // TODO 自动生成方法存根
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		
		String sql="";
		sql="select distinct mnum from t_cutter ";
		
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("mnum", rs.getString("mnum"));
							//obj.put("company", rs.getString("company"));
							
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
							System.out.println(jsonObj.toString());
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


	public JSONObject getQueryFile(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		//Logger.debug(jsonObj.toString());
		String filter=(String)queryHashMap.get("id");
		sql="select * from  v_cutter2com where mnum='"+filter+"'";			
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("iso_num", rs.getString("iso_num"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));								
							obj.put("hq", rs.getString("hq"));
							obj.put("rank_angle", rs.getString("rank_angle"));
							obj.put("c_mate", rs.getString("c_mate"));
							obj.put("coat_mate", rs.getString("coat_mate"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("suit_mate1", rs.getString("suit_mate1"));
							obj.put("suit_mate2", rs.getString("suit_mate2"));
							obj.put("suit_mate3", rs.getString("suit_mate3"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("location", rs.getString("location"));
							obj.put("remark",rs.getString("remark"));
							obj.put("e_diam", rs.getString("e_diam"));
							obj.put("h_diam", rs.getString("h_diam"));
							obj.put("e_leng", rs.getString("e_leng"));
							obj.put("t_leng", rs.getString("t_leng"));
							obj.put("tee_count", rs.getString("tee_count"));
							obj.put("s_ang", rs.getString("s_ang"));
							obj.put("tip_heig", rs.getString("tip_heig"));
							obj.put("bla_wide", rs.getString("bla_wide"));
							obj.put("c_leng", rs.getString("c_leng"));
							obj.put("e_count",rs.getString("e_count"));
							obj.put("e_wide", rs.getString("e_wide"));
							obj.put("t_form", rs.getString("t_form"));
							obj.put("c_way", rs.getString("c_way"));
							obj.put("t_type", rs.getString("t_type"));
							obj.put("spec", rs.getString("spec"));	
							obj.put("type", rs.getString("type"));	
							obj.put("series_num", rs.getString("series_num"));	
							obj.put("order_num", rs.getString("order_num"));
							obj.put("effect_length", rs.getString("effect_length"));
							obj.put("nc_count_in", rs.getString("nc_count_in"));
							obj.put("uc_count_in", rs.getString("uc_count_in"));
							obj.put("gc_count_in", rs.getString("gc_count_in"));
							//obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}
	public JSONObject getQueryFileScore(HashMap queryMap2) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap2;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		//Logger.debug(jsonObj.toString());
		String filter=(String)queryHashMap.get("id");
		sql="select * from  t_c_trecord where entry_num="+filter+"";			
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("sta", rs.getString("sta"));
							obj.put("sta1", rs.getString("sta1"));
							obj.put("sta2", rs.getString("sta2"));
							obj.put("sta3", rs.getString("sta3"));
							//obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}
	
	public JSONObject getQueryFile2(HashMap queryMap2) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap2;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		//Logger.debug(jsonObj.toString());
		String filter=(String)queryHashMap.get("id");
		sql="select * from  t_c_entry_trecord where entry_num="+filter+"";			
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("in_problem", rs.getString("in_problem"));
							//obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}
	public JSONObject getQueryFile3(HashMap queryMap3) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap3;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		//Logger.debug(jsonObj.toString());
		String filter=(String)queryHashMap.get("id");
		sql="select * from  t_c_entry_trecord where entry_num="+filter+"";			
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("in_problem", rs.getString("in_problem"));
							obj.put("answer", rs.getString("answer"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}
	public JSONObject getQueryFile4(HashMap queryMap3) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap3;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		//Logger.debug(jsonObj.toString());
		String filter=(String)queryHashMap.get("id");
		sql="select * from  t_c_entry_trecord where entry_num="+filter+"";			
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("remarkjs", rs.getString("remarkjs"));
							//obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}

	public boolean chexFile(HashMap chexMap) {
		// TODO 自动生成方法存根
		boolean result = false;
		HashMap chexHashMap = chexMap;
		String mnum=(String)chexHashMap.get("mnum");
		System.out.println(mnum);
		String sql="select * from  t_cutter where mnum='"+mnum+"'";
		final int []flag=new int[1];
		flag[0]=0;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if(rs.next()){
						flag[0]=1;
					}
				}
			});
			if(flag[0]==1){
				result=true;
			}else result=false;
		}catch (SQLException e) {
			e.printStackTrace();
			result= false;
		}
		return result;
	}


	public JSONObject getQuerycomAll(HashMap comMap) {
		// TODO 自动生成方法存根
		HashMap comHashMap = comMap;
		String filter=(String)comHashMap.get("filter");
		String hq=(String)comHashMap.get("hq");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		System.out.println(".............filter..........."+filter);
		String sql="";
		sql="select distinct company from (select * from (select * from (select * from v_cutter2com)  where mnum = '"+filter+"')where hq = '"+hq+"')";
		
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("company", rs.getString("company"));
							//obj.put("company", rs.getString("company"));
							
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
							System.out.println(jsonObj.toString());
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


	public JSONObject getQuerynumAll(HashMap numMap) {
		// TODO 自动生成方法存根
		HashMap numHashMap = numMap;
		String filter=(String)numHashMap.get("filter");//1=1 and mnum like '00%'
		String filter1=(String)numHashMap.get("order");
		System.out.println(".............filter..........."+filter);
		System.out.println(".............filter1..........."+filter1);
		String filter2=(String)numHashMap.get("hq");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		
		String sql="";
		sql="select * from (select * from (select * from (select * from v_cutter2com)  where mnum = '"+filter+"') where hq = '"+filter2+"') where company = '"+filter1+"'";
		//"select * from (select * from (select * from v_cutter2com)  where mnum like '%"+filter+"')  where company like '%"+filter1+"'";
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("order_num", rs.getString("order_num"));
							//obj.put("company", rs.getString("company"));
							
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
							System.out.println(jsonObj.toString());
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


	public boolean chexFile1(HashMap chexMap1) {
		// TODO 自动生成方法存根
		boolean result = false;
		HashMap chexHashMap = chexMap1;
		String mnum=(String)chexHashMap.get("mnum");
		String hq=(String)chexHashMap.get("hq");
		System.out.println(mnum);//hq
		System.out.println(hq);
		String sql="select * from  t_cutter where mnum='"+mnum+"'";
		final int []flag=new int[1];
		flag[0]=0;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if(rs.next()){
						flag[0]=1;
					}
				}
			});
			if(flag[0]==1){
				result=true;
			}else result=false;
		}catch (SQLException e) {
			e.printStackTrace();
			result= false;
		}
		return result;
	}


	public JSONObject getQueryFile1(HashMap queryMap1) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap1;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		//Logger.debug(jsonObj.toString());
		String filter=(String)queryHashMap.get("id");
		String hq=(String)queryHashMap.get("hq");
		sql="select * from  v_cutter2com where mnum='"+filter+"'";
		
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("iso_num", rs.getString("iso_num"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));								
							obj.put("hq", rs.getString("hq"));
							obj.put("rank_angle", rs.getString("rank_angle"));
							obj.put("c_mate", rs.getString("c_mate"));
							obj.put("coat_mate", rs.getString("coat_mate"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("suit_mate1", rs.getString("suit_mate1"));
							obj.put("suit_mate2", rs.getString("suit_mate2"));
							obj.put("suit_mate3", rs.getString("suit_mate3"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("location", rs.getString("location"));
							obj.put("remark",rs.getString("remark"));
							obj.put("e_diam", rs.getString("e_diam"));
							obj.put("h_diam", rs.getString("h_diam"));
							obj.put("e_leng", rs.getString("e_leng"));
							obj.put("t_leng", rs.getString("t_leng"));
							obj.put("tee_count", rs.getString("tee_count"));
							obj.put("s_ang", rs.getString("s_ang"));
							obj.put("tip_heig", rs.getString("tip_heig"));
							obj.put("bla_wide", rs.getString("bla_wide"));
							obj.put("c_leng", rs.getString("c_leng"));
							obj.put("e_count",rs.getString("e_count"));
							obj.put("e_wide", rs.getString("e_wide"));
							obj.put("t_form", rs.getString("t_form"));
							obj.put("c_way", rs.getString("c_way"));
							obj.put("t_type", rs.getString("t_type"));
							obj.put("spec", rs.getString("spec"));	
							obj.put("type", rs.getString("type"));	
							obj.put("series_num", rs.getString("series_num"));	
							obj.put("order_num", rs.getString("order_num"));
							obj.put("effect_length", rs.getString("effect_length"));
							obj.put("nc_count_in", rs.getString("nc_count_in"));
							obj.put("uc_count_in", rs.getString("uc_count_in"));
							obj.put("gc_count_in", rs.getString("gc_count_in"));
							//obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}


	public String addFileApp(HashMap addMap) {
		// TODO 自动生成方法存根
		String result = "false";
		HashMap addHashMap = addMap;
		String mnumid=(String)addHashMap.get("pa[1]");
		System.out.println("...................."+mnumid+"....................");
		
		final String[] pa = new String[7];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		pa[0] = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");
		pa[2] = (String) addHashMap.get("pa[2]");
		pa[3] = (String) addHashMap.get("pa[3]");
		pa[4] = (String) addHashMap.get("pa[4]");
		pa[5] = (String) addHashMap.get("pa[5]");
		pa[6] = (String) addHashMap.get("pa[6]");
		
		String[] sqls = new String[1];
		
		sqls[0] = "insert into  t_c_entry_record (entry_num,type_id,in_count,in_time,newstatus,in_problem) values(?,?,?,to_date(?,'yyyy/mm/dd'),?,?)";
		Logger.debug(sqls[0]);
		try {
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(PreparedStatement[] pstmts)
										throws SQLException {
									pstmts[0].setString(1, pa[0]);
									pstmts[0].setString(2, pa[1]);
									pstmts[0].setString(3, pa[2]);
									pstmts[0].setString(4, pa[3]);
									pstmts[0].setString(5, pa[4]);
									pstmts[0].setString(6, pa[5]);
									pstmts[0].setString(7, pa[6]);
									pstmts[0].addBatch();
								}
							});
					result="true";
					if (pa[4].equals("新")){
						String sql = "update  t_cutter set nc_count_in = nc_count_in + " + pa[2] + " where id ='" + mnumid + "'";
						Logger.debug(sql);
								try {
									DbUtil.execute(sql,new IResultSetProcessor() {
										public void process(ResultSet rs) throws SQLException {	
										}
									});
									result +="true";
									
								} catch (SQLException e) {
									e.printStackTrace();
									result +="false";
								}	
					}
					else if (pa[4].equals("旧")){
						String sql = "update  t_cutter set uc_count_in = uc_count_in + " + pa[2] + " where id ='" + mnumid + "'";
						Logger.debug(sql);
								try {
									DbUtil.execute(sql,new IResultSetProcessor() {
										public void process(ResultSet rs) throws SQLException {	
										}
									});
									result +="true";
									
								} catch (SQLException e) {
									e.printStackTrace();
									result +="false";
								}	
					}
		}catch (SQLException e) {
			e.printStackTrace();
			result="false";
		}
		
		
		if (result.equals("truetrue")){
			/*String[] sql = new String[1];
			sql[0] = "update t_c_entry_trecord set status=? where entry_num = '"+pa[0]+"'"; //t_ct_entry_trecord
			Logger.debug(sql[0]);
			final String status=pa[5];
			System.out.println("status =" + status);
			System.out.println(sql[0]);
			try {
						DbUtil.executeBatchs(sql,
								new IArrayPreparedStatementProcessor() {
									public void process(PreparedStatement[] pstmts)
											throws SQLException {
										pstmts[0].setString(1, status);
										pstmts[0].addBatch();
									}
								});
			}catch (SQLException e) {
				e.printStackTrace();
				result="false";
			}*/
			String []sql=new String[1];
			sql[0]="delete from t_c_entry_trecord where entry_num in('"+pa[0]+"')";
			Logger.debug(sql[0]);
				try {
					DbUtil.executeBatchs(sql,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {						
								pstmts[0].addBatch();
							}
				});
			} catch (Exception e) {
				e.printStackTrace();
				result="false";
			}
		}
		return result; 
	}


	public String deleteRe(HashMap delMap) {
		// TODO 自动生成方法存根
		String result = "";
		String deleteid=(String)delMap.get("deleteid");
		String []sqls=new String[1];
		sqls[0]="delete from t_c_entry_trecord where entry_num in('"+deleteid+"')";
		Logger.debug(sqls[0]);
			try {
				DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(
						PreparedStatement[] pstmts)
						throws SQLException {						
							pstmts[0].addBatch();
						}
			});
			result="true";
		} catch (Exception e) {
			e.printStackTrace();
			result="false";
		}
		return result;
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
		sql="select count(*) as ct from t_c_trecord where status = '已回复' or status = '申请' or status = '拒绝'";
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
		
		sql="select * from (select * from (select  *  from  (select * from (select * from  t_c_trecord ) where status = '已回复'or status = '申请' or status = '拒绝' order by status) where rownum<="+(start+limit)+" minus select  *  from  (select  *  from  (select * from  t_c_trecord ) where status = '已回复'or status = '申请' or status = '拒绝' order by status ) where rownum<="+start+") order by status)where "+filter ;  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("type_id", rs.getString("type_id"));
							obj.put("entry_num",rs.getString("entry_num"));
							obj.put("in_time", rs.getString("in_time"));//newstatus,indi_num,location,keeper,remark,startdate,valid,cycle,entry_num,indi_id,status
							obj.put("newstatus", rs.getString("newstatus"));
							obj.put("in_count", rs.getString("in_count"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("name", rs.getString("name"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("location", rs.getString("location"));
							obj.put("company", rs.getString("company"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("status",rs.getString("status"));
							obj.put("remark",rs.getString("remark"));
							obj.put("in_problem",rs.getString("in_problem"));
							obj.put("city",rs.getString("city"));
							obj.put("user_num",rs.getString("user_num"));
							obj.put("bill_num",rs.getString("bill_num"));
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

	public JSONObject getQueryApprove1(HashMap queryMap) {
		System.out.println("111111111111111111111111111111111");
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryMap;
		final String filter = (String)queryHashMap.get("filter");
		final String pclass = (String)queryHashMap.get("pclass");
		final String bclass = (String)queryHashMap.get("bclass");
		//int start=Integer.parseInt((String)queryHashMap.get("start"));
		//int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sqlq = "select count(*) as ct from t_c_trecord where " + filter;
		System.out.println(sqlq);
		Logger.debug(sqlq);
		
		try {
			DbUtil.execute(sqlq,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			});
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		System.out.println(pclass);
		String sql="";
		//String filter=(String)queryHashMap.get("filter");
		sql="select  *  from  (select  *  from  (select  *  from  (select * from (select * from  t_c_trecord ) where status = '已回复'or status = '申请' or status = '拒绝' order by status) where "+pclass+")where "+bclass+")where "+filter ;  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("type_id", rs.getString("type_id"));
							obj.put("entry_num",rs.getString("entry_num"));
							obj.put("in_time", rs.getString("in_time"));//newstatus,indi_num,location,keeper,remark,startdate,valid,cycle,entry_num,indi_id,status
							obj.put("newstatus", rs.getString("newstatus"));
							obj.put("in_count", rs.getString("in_count"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("name", rs.getString("name"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("location", rs.getString("location"));
							obj.put("company", rs.getString("company"));
							obj.put("hq", rs.getString("hq"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("nc_count_in", rs.getString("nc_count_in"));
							obj.put("uc_count_in",rs.getString("uc_count_in"));
							obj.put("status",rs.getString("status"));
							obj.put("remark",rs.getString("remark"));
							obj.put("remarkjs",rs.getString("remarkjs"));
							obj.put("score",rs.getString("score"));
							obj.put("leader_num",rs.getString("leader_num"));
							obj.put("in_problem",rs.getString("in_problem"));
							obj.put("city",rs.getString("city"));
							obj.put("user_num",rs.getString("user_num"));
							obj.put("bill_num",rs.getString("bill_num"));
							obj.put("denglu",rs.getString("denglu"));
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

}
