package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.Ministore;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class MinistoreImpl implements Ministore{
		public JSONObject getQueryMinistoreC(HashMap queryMinistore_cMap) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryMinistore_cMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String totlecount=(String)queryHashMap.get("totlecount");;
			String sql="";
			sql="select count(*) as ct from  c_base where mini_qs>uc_count_in+nc_count_in and "+(String)queryHashMap.get("filter");
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
			int start=Integer.parseInt((String)queryHashMap.get("start"));
			int limit=Integer.parseInt((String)queryHashMap.get("limit"));
			
			String filter=(String)queryHashMap.get("filter");
			String order=(String)queryHashMap.get("order");
			sql="select * from (select * from  (select * from c_base where "+filter+" and mini_qs>uc_count_in+nc_count_in order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_base where "+filter+" and mini_qs>uc_count_in+nc_count_in order by "+order+" ) where rownum<="+start+") order by "+order; 
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+""+sql+"");
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								obj.put("nc_count_in", rs.getString("nc_count_in"));
								obj.put("uc_count_in", rs.getString("uc_count_in"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("mini_qs", rs.getString("mini_qs"));
								obj.put("hq", rs.getString("hq"));
								obj.put("location", rs.getString("location"));
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
			Logger.debug("This is mini_qs********************************");
			Logger.debug(jsonObj.toString());
			return jsonObj;
		}
		
		
		public JSONObject getQueryMinistoreM(HashMap queryMinistore_mMap) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryMinistore_mMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String totlecount=(String)queryHashMap.get("totlecount");;
			String sql="";
			sql="select count(*) as ct from  m_type_base where mini_qs>um_count_in+nm_count_in and "+(String)queryHashMap.get("filter");
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
			int start=Integer.parseInt((String)queryHashMap.get("start"));
			int limit=Integer.parseInt((String)queryHashMap.get("limit"));
			
			String filter=(String)queryHashMap.get("filter");
			String order=(String)queryHashMap.get("order");
			sql="select * from (select * from  (select * from m_type_base where "+filter+" and mini_qs>um_count_in+nm_count_in order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from m_type_base where "+filter+" and mini_qs>um_count_in+nm_count_in order by "+order+" ) where rownum<="+start+") order by "+order; 
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+""+sql+"");
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("classname", rs.getString("classname"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								obj.put("nm_count_in", rs.getString("nm_count_in"));
								obj.put("um_count_in", rs.getString("um_count_in"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("mini_qs", rs.getString("mini_qs"));
								obj.put("hq", rs.getString("hq"));
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
		
		
		public JSONObject getQueryMinistoreMT(HashMap queryMinistore_mtMap) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryMinistore_mtMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String totlecount=(String)queryHashMap.get("totlecount");;
			String sql="";
			sql="select count(*) as ct from  mt_type_base where mini_qs>umt_count_in+nmt_count_in and "+(String)queryHashMap.get("filter");
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
			int start=Integer.parseInt((String)queryHashMap.get("start"));
			int limit=Integer.parseInt((String)queryHashMap.get("limit"));
			
			String filter=(String)queryHashMap.get("filter");
			String order=(String)queryHashMap.get("order");
			sql="select * from (select * from  (select * from mt_type_base where "+filter+" and mini_qs>umt_count_in+nmt_count_in order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from mt_type_base where "+filter+" and mini_qs>umt_count_in+nmt_count_in order by "+order+" ) where rownum<="+start+") order by "+order; 
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+""+sql+"");
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("classname", rs.getString("classname"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								obj.put("nmt_count_in", rs.getString("nmt_count_in"));
								obj.put("umt_count_in", rs.getString("umt_count_in"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("mini_qs", rs.getString("mini_qs"));
								obj.put("hq", rs.getString("hq"));
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
		
		public JSONObject getQueryMinistoreCT(HashMap queryMinistore_ctMap) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryMinistore_ctMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String totlecount=(String)queryHashMap.get("totlecount");;
			String sql="";
			sql="select count(*) as ct from  ct_base where mini_qs>uct_count_in+nct_count_in and "+(String)queryHashMap.get("filter");
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
			int start=Integer.parseInt((String)queryHashMap.get("start"));
			int limit=Integer.parseInt((String)queryHashMap.get("limit"));
			
			String filter=(String)queryHashMap.get("filter");
			String order=(String)queryHashMap.get("order");
			sql="select * from (select * from  (select * from ct_base where "+filter+" and mini_qs>uct_count_in+nct_count_in order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from ct_base where "+filter+" and mini_qs>uct_count_in+nct_count_in order by "+order+" ) where rownum<="+start+") order by "+order; 
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+""+sql+"");
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								obj.put("nct_count_in", rs.getString("nct_count_in"));
								obj.put("uct_count_in", rs.getString("uct_count_in"));
								obj.put("mini_qs", rs.getString("mini_qs"));
								obj.put("hq", rs.getString("hq"));
								obj.put("location", rs.getString("location"));
								obj.put("use_freq", rs.getString("use_freq"));
								obj.put("remark", rs.getString("remark"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("spec", rs.getString("spec"));
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
		
}