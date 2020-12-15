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
import com.nuaa.app.UsrInfModMT;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class UsrInfModMTImpl implements UsrInfModMT{
		public JSONObject getQueryUsrInfModMT1(HashMap queryUsrinfmod_mt1Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryUsrinfmod_mt1Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from  c_apply_record where "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from c_apply_record where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_apply_record where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
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
								obj.put("count", rs.getString("count"));
								obj.put("drawing_num", rs.getString("drawing_num"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("apply_time", rs.getDate("apply_time"));
								obj.put("gleader_num", rs.getString("gleader_num"));
								obj.put("gleader_name", rs.getString("gleader_name"));
								obj.put("gleader_approve", rs.getString("gleader_approve"));
								obj.put("gleader_time", rs.getDate("gleader_time"));
								obj.put("leader_num", rs.getString("leader_num"));
								obj.put("leader_name", rs.getString("leader_name"));
								obj.put("leader_approve", rs.getString("leader_approve"));
								obj.put("leader_time", rs.getDate("leader_time"));
								obj.put("status", rs.getString("status"));

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
		
		
		public JSONObject getQueryUsrInfModMT2(HashMap queryUsrinfmod_mt2Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryUsrinfmod_mt2Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			final String worker_num=(String)queryHashMap.get("worker_num");
			String sql="";
			sql="select count(*) as ct from  mt_apply_record where worker_num='"+worker_num+"' and status = '未处理' and "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from mt_apply_record where "+filter+"  and status = '未处理' and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from mt_apply_record where "+filter+"  and status = '未处理' and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("indi_id", rs.getString("indi_id"));
								obj.put("indi_num", rs.getString("indi_num"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("classname", rs.getString("classname"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));								
								obj.put("drawing_num", rs.getString("drawing_num"));
								obj.put("apply_time", rs.getDate("apply_time"));
								obj.put("gleader_num", rs.getString("gleader_num"));
								obj.put("gleader_name", rs.getString("gleader_name"));
								obj.put("gleader_approve", rs.getString("gleader_approve"));
								obj.put("gleader_time", rs.getDate("gleader_time"));
								obj.put("status", rs.getString("status"));

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

		public JSONObject getQueryUsrInfModMT3(HashMap queryUsrinfmod_mt3Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryUsrinfmod_mt3Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			final String worker_num=(String)queryHashMap.get("worker_num");
			String sql="";
			sql="select count(*) as ct from  mt_br_now where worker_num='"+worker_num+"' and "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from mt_br_now where "+filter+" and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from mt_br_now where "+filter+" and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("indi_id", rs.getString("indi_id"));
								obj.put("indi_num", rs.getString("indi_num"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("classname", rs.getString("classname"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));

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

		
		public JSONObject getQueryUsrInfModMT4(HashMap queryUsrinfmod_mt4Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryUsrinfmod_mt4Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			final String worker_num=(String)queryHashMap.get("worker_num");
			String sql="";
			sql="select count(*) as ct from  mt_br_record where worker_num='"+worker_num+"' and "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from mt_br_record where "+filter+" and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from mt_br_record where "+filter+" and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("indi_id", rs.getString("indi_id"));
								obj.put("apply_num", rs.getString("apply_num"));
								obj.put("indi_num", rs.getString("indi_num"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("classname", rs.getString("classname"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								if(rs.getString("state").equals("5")){
								    obj.put("status", "工具归还");}
								else if (rs.getString("state").equals("4")) {
									obj.put("status", "工具借用");}
								else if (rs.getString("state").equals("1")) {
									obj.put("status", "正常报损");}
								else if (rs.getString("state").equals("2")) {
									obj.put("status", "非正常报损");}
								else if (rs.getString("state").equals("3")) {
									obj.put("status", "工具报丢");}
								obj.put("time", rs.getDate("time"));

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