package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.UsrInfModC;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class UsrInfModCImpl implements UsrInfModC{
		public JSONObject getQueryUsrInfModC1(HashMap queryUsrinfmod_c1Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryUsrinfmod_c1Map;
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
								obj.put("apply_time", rs.getDate("apply_time"));
								obj.put("gleader_num", rs.getString("gleader_num"));
								obj.put("gleader_name", rs.getString("gleader_name"));
								obj.put("gleader_approve", rs.getString("gleader_approve"));
								obj.put("order_num", rs.getString("order_num"));
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
		public JSONObject getQueryUsrInfModC2(HashMap queryUsrinfmod_c2Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryUsrinfmod_c2Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			final String worker_num=(String)queryHashMap.get("worker_num");
			String sql="";
			sql="select count(*) as ct from  c_apply_record where worker_num='"+worker_num+"'  and status = '未处理' and "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from c_apply_record where "+filter+"  and status = '未处理' and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_apply_record where "+filter+"  and status = '未处理' and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();	
							//	decimalformat.applyPattern("###.##");
								decimalformat.setMaximumFractionDigits(2);
							
								JSONObject obj=new JSONObject();								
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));								
								obj.put("order_num", rs.getString("order_num"));
								obj.put("iso_num", rs.getString("iso_num"));															
								obj.put("hq", rs.getString("hq"));
								obj.put("rank_angle",rs.getString("rank_angle"));
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
								obj.put("h_diam",rs.getString("h_diam"));
								obj.put("e_leng",rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));
								obj.put("tee_count",rs.getString("tee_count"));
								obj.put("s_ang", rs.getString("s_ang"));
								obj.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
								obj.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
								obj.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
								obj.put("e_count",rs.getString("e_count"));
								obj.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));
								obj.put("t_form", rs.getString("t_form"));
								obj.put("c_way", rs.getString("c_way"));
								obj.put("t_type", rs.getString("t_type"));
								obj.put("spec", rs.getString("spec"));	
								obj.put("type", rs.getString("type"));	
								obj.put("series_num", rs.getString("series_num"));	
								obj.put("order_num", rs.getString("order_num"));
								obj.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));
								obj.put("uc_count_in", rs.getString("uc_count_in"));
								obj.put("nc_count_in", rs.getString("nc_count_in"));
								obj.put("gc_count_in", rs.getString("gc_count_in"));
								obj.put("count", rs.getString("count"));
								obj.put("drawing_num", rs.getString("drawing_num"));
								obj.put("apply_time", rs.getDate("apply_time"));
								obj.put("gleader_num", rs.getString("gleader_num"));
								obj.put("gleader_name", rs.getString("gleader_name"));
								obj.put("gleader_approve", rs.getString("gleader_approve"));
								obj.put("order_num", rs.getString("order_num"));
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

		public JSONObject getQueryUsrInfModC3(HashMap queryUsrinfmod_c3Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryUsrinfmod_c3Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			final String worker_num=(String)queryHashMap.get("worker_num");
			String sql="";
			sql="select count(*) as ct from  c_br_now where worker_num='"+worker_num+"' and "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from c_br_now where "+filter+" and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_br_now where "+filter+" and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();	
							//	decimalformat.applyPattern("###.##");
								decimalformat.setMaximumFractionDigits(2);
								JSONObject obj=new JSONObject();
					
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));								
								obj.put("order_num", rs.getString("order_num"));
								obj.put("iso_num", rs.getString("iso_num"));															
								obj.put("hq", rs.getString("hq"));
								obj.put("rank_angle",rs.getString("rank_angle"));
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
								obj.put("h_diam",rs.getString("h_diam"));
								obj.put("e_leng",rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));
								obj.put("tee_count",rs.getString("tee_count"));
								obj.put("s_ang", rs.getString("s_ang"));
								obj.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
								obj.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
								obj.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
								obj.put("e_count",rs.getString("e_count"));
								obj.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));
								obj.put("t_form", rs.getString("t_form"));
								obj.put("c_way", rs.getString("c_way"));
								obj.put("t_type", rs.getString("t_type"));
								obj.put("spec", rs.getString("spec"));	
								obj.put("type", rs.getString("type"));	
								obj.put("series_num", rs.getString("series_num"));	
								obj.put("order_num", rs.getString("order_num"));
								obj.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));
								obj.put("uc_count_in", rs.getString("uc_count_in"));
								obj.put("nc_count_in", rs.getString("nc_count_in"));
								obj.put("gc_count_in", rs.getString("gc_count_in"));
								obj.put("br_num", rs.getString("br_num"));
								obj.put("count", rs.getString("br_count"));
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

		
		public JSONObject getQueryUsrInfModC4(HashMap queryUsrinfmod_c4Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryUsrinfmod_c4Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			final String worker_num=(String)queryHashMap.get("worker_num");
			String sql="";
			sql="select count(*) as ct from  c_br_record where worker_num='"+worker_num+"' and "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from c_br_record where "+filter+" and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_br_record where "+filter+" and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();	
							//	decimalformat.applyPattern("###.##");
								decimalformat.setMaximumFractionDigits(2);
								JSONObject obj=new JSONObject();
								
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));								
								obj.put("order_num", rs.getString("order_num"));
								obj.put("iso_num", rs.getString("iso_num"));															
								obj.put("hq", rs.getString("hq"));
								obj.put("rank_angle",rs.getString("rank_angle"));
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
								obj.put("h_diam",rs.getString("h_diam"));
								obj.put("e_leng",rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));
								obj.put("tee_count",rs.getString("tee_count"));
								obj.put("s_ang", rs.getString("s_ang"));
								obj.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
								obj.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
								obj.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
								obj.put("e_count",rs.getString("e_count"));
								obj.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));
								obj.put("t_form", rs.getString("t_form"));
								obj.put("c_way", rs.getString("c_way"));
								obj.put("t_type", rs.getString("t_type"));
								obj.put("spec", rs.getString("spec"));	
								obj.put("type", rs.getString("type"));	
								obj.put("series_num", rs.getString("series_num"));	
								obj.put("order_num", rs.getString("order_num"));
								obj.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));
								obj.put("uc_count_in", rs.getString("uc_count_in"));
								obj.put("nc_count_in", rs.getString("nc_count_in"));
								obj.put("gc_count_in", rs.getString("gc_count_in"));
								obj.put("apply_num", rs.getString("apply_num"));
								obj.put("count", rs.getString("count"));
								if(rs.getString("state").equals("5")){
								    obj.put("status", "刀具归还");}
								else if (rs.getString("state").equals("4")) {
									obj.put("status", "刀具借用");}
								else if (rs.getString("state").equals("1")) {
									obj.put("status", "正常报损");}
								else if (rs.getString("state").equals("2")) {
									obj.put("status", "非正常报损");}
								else if (rs.getString("state").equals("3")) {
									obj.put("status", "刀具报丢");}
							//	obj.put("status", rs.getString("status"));
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