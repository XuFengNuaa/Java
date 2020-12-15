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
import com.nuaa.app.StoreStatModC;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class StoreStatModCImpl implements StoreStatModC{
		public JSONObject getQueryStoreStatModC1(HashMap queryStorestatmod_c1Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryStorestatmod_c1Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from  c_base where "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from c_base where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_base where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								obj.put("nc_count_in", rs.getString("nc_count_in"));
								obj.put("uc_count_in", rs.getString("uc_count_in"));
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
			Logger.debug(jsonObj.toString());
			return jsonObj;
		}
		public JSONObject getQueryStoreStatModC2(HashMap queryStorestatmod_c2Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryStorestatmod_c2Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from  c_entry where "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from c_entry where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_entry where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("entry_num", rs.getString("entry_num"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								obj.put("in_count", rs.getString("in_count"));
								obj.put("in_time", rs.getDate("in_time"));
								obj.put("newstatus", rs.getString("newstatus"));
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

		public JSONObject getQueryStoreStatModC3(HashMap queryStorestatmod_c1Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryStorestatmod_c1Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from  c_br_now where "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from c_br_now where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_br_now where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
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
								obj.put("br_count", rs.getString("br_count"));
								obj.put("time", rs.getDate("time"));
								obj.put("worker_num", rs.getString("worker_num"));
								obj.put("realname", rs.getString("realname"));
								obj.put("group_num", rs.getString("group_num"));
							
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
		
		public JSONObject getQueryStoreStatModC4(HashMap queryStorestatmod_c4Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryStorestatmod_c4Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from  c_gr where "+(String)queryHashMap.get("filter");
			final String [] count=new String[1];
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
			sql="select * from (select * from  (select * from c_gr where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_gr where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("grd_num", rs.getString("grd_num"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("order_num", rs.getString("order_num"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								obj.put("s_count", rs.getString("s_count"));
								obj.put("s_time", rs.getString("s_time"));
								obj.put("r_count", rs.getString("r_count"));
								obj.put("r_time_date", rs.getDate("r_time_date"));
								obj.put("l_count", rs.getInt("s_count")-rs.getInt("r_count"));
								obj.put("grd_department", rs.getString("grd_department"));
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
		
		
		public JSONObject getQueryStoreStatModC5(HashMap queryStorestatmod_c5Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryStorestatmod_c5Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from  c_br_record where "+(String)queryHashMap.get("filter");
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
			//sql="select * from (select * from  (select apply_num,mnum,pclass,bclass,name,company,worker_num,count,status,time from c_br_record where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select apply_num,mnum,pclass,bclass,name,company,worker_num,count,status,time from c_br_record where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			sql="select * from (select * from  (select * from c_br_record where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_br_record where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
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
								obj.put("status", rs.getString("status"));
								obj.put("time", rs.getDate("time"));
								obj.put("apply_num", rs.getString("apply_num"));
								obj.put("worker_num", rs.getString("worker_num"));
								obj.put("realname", rs.getString("realname"));
								obj.put("group_num", rs.getString("group_num"));
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
		public JSONObject getQueryStoreStatModC6(HashMap queryStorestatmod_c6Map) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryStorestatmod_c6Map;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from  c_base where "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from c_base where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_base where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
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
								obj.put("order_num", rs.getString("order_num"));
								obj.put("company", rs.getString("company"));
								obj.put("use_freq", rs.getString("use_freq"));
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
			Logger.debug(jsonObj.toString());
			return jsonObj;
		}
	
		public JSONObject viewStoreStatModC1(HashMap viewCompanyMap) {
			// TODO 自动生成方法存根
			HashMap viewCompanyHashMap=viewCompanyMap;
			final JSONObject jsonObj=new JSONObject();
			final String id=(String)viewCompanyHashMap.get("id");
			String sql="";
			sql="select * from T_blade where id='"+id+"'"; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("mnum", rs.getString("mnum"));
								jsonObj.put("pclass", rs.getString("pclass"));
								jsonObj.put("bclass", rs.getString("bclass"));
								jsonObj.put("name", rs.getString("name"));
								jsonObj.put("company", rs.getString("company"));
								jsonObj.put("blad_mate", rs.getString("blade_mate"));
								jsonObj.put("coat_mate", rs.getString("coat_mate"));
								jsonObj.put("nc_count_in", rs.getString("nc_count_in"));
								jsonObj.put("uc_count_in", rs.getString("uc_count_in"));
								jsonObj.put("gc_count_in", rs.getString("gc_count_in"));
								jsonObj.put("mini_qs", rs.getString("mini_qs"));
								jsonObj.put("hq", rs.getString("hq"));
								jsonObj.put("used_fred", rs.getString("used_fred"));
								jsonObj.put("suit_mate1", rs.getString("suit_mate1"));
								jsonObj.put("suit_mate2", rs.getString("suit_mate2"));
								jsonObj.put("suit_mate3", rs.getString("suit_mate3"));
								jsonObj.put("location", rs.getString("location"));
								
								}	
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