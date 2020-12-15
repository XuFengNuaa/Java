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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.app.FenQieMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;



public class FenQieModImpl implements FenQieMod{


	@Override
	public boolean addFile(HashMap addMap) {
		// TODO Auto-generated method stub
		boolean result = false;
		HashMap addHashMap = addMap;
		final String[] pa = new String[14];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		final String id = UUID.randomUUID().toString();
		pa[1] = (String) addHashMap.get("pa[1]");//
		pa[2] = (String) addHashMap.get("pa[2]");//
		pa[3] = (String) addHashMap.get("pa[3]");//
		pa[4] = (String) addHashMap.get("pa[4]");//
		pa[5] = (String) addHashMap.get("pa[5]");//
		pa[6] = (String) addHashMap.get("pa[6]");//
		pa[7] = (String) addHashMap.get("pa[7]");//
		pa[8] = (String) addHashMap.get("pa[8]");//
		pa[9] = (String) addHashMap.get("pa[9]");//
		pa[10] = (String) addHashMap.get("pa[10]");//
		pa[11] = (String) addHashMap.get("pa[11]");//	
		pa[12] = (String) addHashMap.get("pa[12]");//
		pa[13] = (String) addHashMap.get("pa[13]");//
		String[] sql = new String[1];
		System.out.println(pa[1]);
		 sql[0] = "insert into t_fenqie (ID,dalei_sim,xiaolei_sim,machine_sim,material_sim,daihao_sim,mingcheng_sim,tuhao_sim,maopilong_sim,maopiwidth_sim,maopihigh_sim,qxtj_sim,qxmj_sim,zxd_sim)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,id);
					pstmts[0].setString(2,pa[1]);
					pstmts[0].setString(3,pa[2]);
					pstmts[0].setString(4,pa[3]);
					pstmts[0].setString(5,pa[4]);
					pstmts[0].setString(6,pa[5]);
					pstmts[0].setString(7,pa[6]);
					pstmts[0].setString(8,pa[7]);
					pstmts[0].setString(9,pa[8]);
					pstmts[0].setString(10,pa[9]);
					pstmts[0].setString(11,pa[10]);
					pstmts[0].setString(12,pa[11]);
					pstmts[0].setString(13,pa[12]);
					pstmts[0].setString(14,pa[13]);				
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

	@Override
	public JSONObject getQueryFile(HashMap hashmap) {
		// TODO Auto-generated method stub
		HashMap queryHashMap=new HashMap();
		queryHashMap=hashmap;	
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from t_fenqie ";
		System.out.println("sql="+sql);
		//Logger.debug(sql);
		final String[] count=new String[1];
		count[0]="";
		try {
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO
							e.printStackTrace();
						}
					}
				}
			});	
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		//Logger.debug(jsonObj.toString());
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		String filter=(String)queryHashMap.get("filter");///////
		String order=(String)queryHashMap.get("order");

		String sql1 = "select id,dalei_sim,xiaolei_sim,daihao_sim,mingcheng_sim,tuhao_sim,material_sim,machine_sim,maopilong_sim,maopiwidth_sim,maopihigh_sim,qxtj_sim,qxmj_sim,zxd_sim from " +
				"(select id,dalei_sim,xiaolei_sim,daihao_sim,mingcheng_sim,tuhao_sim,material_sim,machine_sim,maopilong_sim,maopiwidth_sim,maopihigh_sim,qxtj_sim,qxmj_sim,zxd_sim  from  " +
				"(select id,dalei_sim,xiaolei_sim,daihao_sim,mingcheng_sim,tuhao_sim,material_sim,machine_sim,maopilong_sim,maopiwidth_sim,maopihigh_sim,qxtj_sim,qxmj_sim,zxd_sim from " +
				"t_fenqie where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  " +
				"id,dalei_sim,xiaolei_sim,daihao_sim,mingcheng_sim,tuhao_sim,material_sim,machine_sim,maopilong_sim,maopiwidth_sim,maopihigh_sim,qxtj_sim,qxmj_sim,zxd_sim from  " +
				"(select id,dalei_sim,xiaolei_sim,daihao_sim,mingcheng_sim,tuhao_sim,material_sim,machine_sim,maopilong_sim,maopiwidth_sim,maopihigh_sim,qxtj_sim,qxmj_sim,zxd_sim from " +
				"t_fenqie where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		try {
			DbUtil.execute(sql1,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("dalei_sim", rs.getString("dalei_sim"));
							obj.put("xiaolei_sim", rs.getString("xiaolei_sim"));
							obj.put("daihao_sim", rs.getString("daihao_sim"));
							obj.put("mingcheng_sim", rs.getString("mingcheng_sim"));
							obj.put("tuhao_sim", rs.getString("tuhao_sim"));
							obj.put("material_sim", rs.getString("material_sim"));
							obj.put("machine_sim", rs.getString("machine_sim"));
							obj.put("maopilong_sim", rs.getString("maopilong_sim"));
							obj.put("maopiwidth_sim", rs.getString("maopiwidth_sim"));
							obj.put("maopihigh_sim", rs.getString("maopihigh_sim"));
							obj.put("qxtj_sim", rs.getString("qxtj_sim"));
							obj.put("qxmj_sim", rs.getString("qxmj_sim"));
							obj.put("zxd_sim", rs.getString("zxd_sim"));
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
		//System.out.println("asdas"+jsonObj);
		return jsonObj;
	}
	
	@Override
	public 	String getFile(String dir,String tuhao){
		String bb="";
		System.out.println("hhhh");
		File fii=new File(dir+"\\web\\tempfile2\\"+tuhao+"\\");
		System.out.println(dir+"\\web\\tempfile2\\"+tuhao+"\\");
		if(!fii.exists()) {
			System.out.println("chucuo");
			   return bb; 
			}
		File[] files=fii.listFiles();
		bb+="<script>function t(f){window.open(f);};</script>";
		for(int i=0;i<files.length;i++){
			String typ = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1, files[i].getName().length()).toLowerCase();
			if(typ.equals("jpg")||typ.equals("png")||typ.equals("mp3")||typ.equals("mp4")||typ.equals("txt")||typ.equals("avi")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\tempfile2\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\">" + files[i].getName() +"</a><br/>";
			}
			if(typ.equals("docx")||typ.equals("doc")||typ.equals("pdf")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\tempfile2\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\" style=\"color:#FF00FF;text-decoration:underline;\"> "+
						files[i].getName() + "</a><br/>"; 
			}
		}
		System.out.println("returned file xml  is "+bb);
		return bb;	
	} 
	@Override
	public 	String getFile2(String dir,String tuhao){
		String bb="";
		System.out.println("hhhh");
		File fii=new File(dir+"\\web\\bom\\"+tuhao+"\\");
		System.out.println(dir+"\\web\\bom\\"+tuhao+"\\");
		if(!fii.exists()) {
			System.out.println("chucuo");
			   return bb; 
			}
		File[] files=fii.listFiles();
		bb+="<script>function t(f){window.open(f);};</script>";
		for(int i=0;i<files.length;i++){
			String typ = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1, files[i].getName().length()).toLowerCase();
			if(typ.equals("jpg")||typ.equals("png")||typ.equals("txt")||typ.equals("mp4")||typ.equals("mp3")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\bom\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\">" + files[i].getName() +"</a><br/>";
			}
			if(typ.equals("docx")||typ.equals("doc")||typ.equals("pdf")){
				bb += "<a href=\"..\\..\\..\\..\\..\\tsms\\web\\bom\\"+tuhao+"\\" +
					files[i].getName() + "\"" +" target=\"_blank()\" style=\"color:#FF00FF;text-decoration:underline;\"> "+
						files[i].getName() + "</a><br/>"; 
			}
		}
		System.out.println("returned file xml  is "+bb);
		return bb;	
	} 
	

	@Override
	public HashMap uploadFile1(HashMap fileHmap) {
		// TODO Auto-generated method stub
		final HashMap fileHashMap=fileHmap;	
    	final HashMap AniHashMap=new HashMap();	
    	String id = fileHashMap.get("id").toString();
    	System.out.println("333333"+id);
    	String tempPath = "\\web\\tempfile2\\"+id+"\\"; // 用于存放临时文件的目录
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
	public HashMap uploadFile2(HashMap fileHmap) {
		// TODO Auto-generated method stub
		final HashMap fileHashMap=fileHmap;	
    	final HashMap AniHashMap=new HashMap();	
    	String id = fileHashMap.get("ljth").toString();
    	System.out.println("333333"+id);
    	String tempPath = "\\web\\bom\\"+id+"\\"; // 用于存放临时文件的目录
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
	public boolean addImg(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String ljth = hashmap.get("ljth").toString();
		final String tpmc = hashmap.get("tpmc").toString();
		final String scsj = hashmap.get("scsj").toString();
		final String scr = hashmap.get("scr").toString();
		System.out.println("****************"+tpmc);
		String [] sql = new String[1];
		final String [] count = new String[1];
		/*String sql1 = "select  count(*) as ct  from t_blobtableimg where tpmc = '"+tpmc+"'";
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						count[0] = rs.getString("ct");
					}
				}
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if("0".equals(count[0])){*/
		sql[0] = "update t_blobtableimg set id = ?,tpmc = ? ,scr=?,scsj=? where ljth = '"+ljth+"'";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,UUID.randomUUID().toString());
					pstmts[0].setString(2,tpmc);
					pstmts[0].setString(3,scr);
					pstmts[0].setString(4,scsj);
					pstmts[0].addBatch();
				}
			 });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		/*}else{
			return false;
		}*/
		return true;
	}

	@Override
	public JSONObject QueryAllDalei() {
		// TODO Auto-generated method 
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarry = new JSONArray();
		String sql = "select distinct dalei from t_parttype";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
						 JSONObject json = new JSONObject();
							json.put("dalei",rs.getString("dalei"));
							jsonarry.put(json);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					try {
						obj.put("filedata", jsonarry);
						System.out.println(obj);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public JSONObject QueryAllXiaoleiByDalei(HashMap hashmap) {
		// TODO Auto-generated method stub
		
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarry = new JSONArray();
		final String dalel = hashmap.get("dalei").toString();
		String sql = "select distinct xiaolei from t_parttype where dalei = '"+dalel+"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							JSONObject json = new JSONObject();
							json.put("xiaolei",rs.getString("xiaolei"));
							jsonarry.put(json);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					try {
						obj.put("filedata", jsonarry);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String delPart(HashMap hashMap) {
		// TODO Auto-generated method stub
		final String id=(String)hashMap.get("id");
		final String []result=new String[1];
		result[0]="true";
		String []sqls=new String[1];
		final String[] sql = new String[1];
		sql[0] = "delete from t_fenqie where id in ('',"+id+")";
		String sql2 = "select * from t_fenqie where id in ('',"+id+")";
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
	}

	@Override
	public String editFile(HashMap hashMap) {
		// TODO Auto-generated method stub
		boolean result = false;
		HashMap addHashMap = hashMap;
		final String[] pa = new String[20];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		pa[0] = (String) addHashMap.get("pa[0]");//产品名称 
		pa[1] = (String) addHashMap.get("pa[1]");//产品名称 
		pa[2] = (String) addHashMap.get("pa[2]");//零件名称 4
		pa[3] = (String) addHashMap.get("pa[3]");//零件图号 6
		pa[4] = (String) addHashMap.get("pa[4]");//整件图号 7
		pa[5] = (String) addHashMap.get("pa[5]");//工作令号 3
		pa[6] = (String) addHashMap.get("pa[6]");//零件零点  14 
		pa[7] = (String) addHashMap.get("pa[7]");//零件大类 11
		pa[8] = (String) addHashMap.get("pa[8]");//主制车间 8 
		pa[9] = (String) addHashMap.get("pa[9]");//派工日期 9
		pa[10] = (String) addHashMap.get("pa[10]");//模型设计 10  
		pa[13] = (String) addHashMap.get("pa[13]");//备注  15
		pa[14] = (String) addHashMap.get("pa[14]");//工序号2
		pa[15] = (String) addHashMap.get("pa[15]");//全批数量 5
		pa[16] = (String) addHashMap.get("pa[16]");//零件小类16
		pa[17] = (String) addHashMap.get("pa[17]");//材料牌号
		pa[18] = (String) addHashMap.get("pa[18]");//特征类别
		pa[19] = (String) addHashMap.get("pa[19]");//类别数量
		
		String[] sql = new String[1];
		System.out.println(pa[1]);
		String sql1 = "select count(*) as ct from t_blobtable where id !='"+pa[0]+"'and ljth = '"+pa[3]+"'";
		final String[] count = new String[1];
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						count[0] = rs.getString("ct");
					}	
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("0".equals(count[0])){
			sql[0] = "update t_blobtable set GONGXUHAO=?,GZLH=?,LJMC=?,QPL=?,LJTH=?,ZJTH=?,ZZCJ=?,PGRQ=?,MXSJ=?,DALEI=?,LJLD=?,beizhu=? ,XIAOLEI=?,featype=?,typenum=?,clph=? where id = '"+pa[0]+"'";
			try {
				DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts) throws SQLException {
						// TODO Auto-generated method stub
						pstmts[0].setString(1,pa[14]);
						pstmts[0].setString(2,pa[5]);
						pstmts[0].setString(3,pa[2]);
						pstmts[0].setString(4,pa[15]);
						pstmts[0].setString(5,pa[3]);
						pstmts[0].setString(6,pa[4]);
						pstmts[0].setString(7,pa[8]);
						pstmts[0].setString(8,pa[9]);
						pstmts[0].setString(9,pa[10]);
						pstmts[0].setString(10,pa[7]);
						pstmts[0].setString(11,pa[6]);//零件零点
						pstmts[0].setString(12,pa[13]);//备注
						pstmts[0].setString(13,pa[16]);//小类
						pstmts[0].setString(14,pa[18]);//特征类别
						pstmts[0].setString(15,pa[19]);//特征数量
						pstmts[0].setString(16,pa[17]);//材料牌号
						pstmts[0].addBatch();
					}
				 });
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "false";
			}
		}
		return "true";
	}


}
