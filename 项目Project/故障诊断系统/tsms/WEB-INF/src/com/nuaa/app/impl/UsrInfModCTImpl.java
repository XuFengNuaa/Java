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
import com.nuaa.app.UsrInfModCT;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class UsrInfModCTImpl implements UsrInfModCT{
	public JSONObject getQueryUsrInfModCT1(HashMap queryUsrinfmod_ct1Map) {
		HashMap queryHashMap=queryUsrinfmod_ct1Map;
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
							while(rs.next()){
								JSONObject obj=new JSONObject();
								try {
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
									obj.put("nct_count_in", rs.getString("nct_count_in"));
									obj.put("uct_count_in", rs.getString("uct_count_in"));
									obj.put("hq", rs.getString("hq"));
									obj.put("location", rs.getString("location"));
									obj.put("mini_qs", rs.getString("mini_qs"));
									obj.put("use_freq", rs.getString("use_freq"));
									obj.put("remark", rs.getString("remark"));
									obj.put("spec", rs.getString("spec"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								jsonArray.put(obj);
								
							}
							try {
								jsonObj.put("filedata", jsonArray);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(jsonObj.toString());
						}
					});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				Logger.debug(jsonObj.toString());
				return jsonObj;
		
	}
	
	public JSONObject getQueryUsrInfModCT2(HashMap queryUsrinfmod_ct2Map) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrinfmod_ct2Map;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  ct_apply_record where worker_num='"+worker_num+"'  and status = '未处理' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from ct_apply_record where "+filter+"  and status = '未处理' and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from ct_apply_record where "+filter+"  and status = '未处理' and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
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
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
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
	
	public JSONObject getQueryUsrInfModCT3(HashMap queryUsrinfmod_ct3Map) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrinfmod_ct3Map;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  ct_br_now where worker_num='"+worker_num+"' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from ct_br_now where "+filter+" and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from ct_br_now where "+filter+" and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
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
							obj.put("br_count", rs.getString("br_count"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
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
	
	public JSONObject getQueryUsrInfModCT4(HashMap queryUsrinfmod_ct4Map) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrinfmod_ct4Map;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  ct_br_record where worker_num='"+worker_num+"' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from ct_br_record where "+filter+" and worker_num='"+worker_num+"' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from ct_br_record where "+filter+" and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("apply_num", rs.getString("apply_num"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("count", rs.getString("count"));
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
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
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
	
	public JSONObject getQueryUsrInfModCT5(HashMap queryUsrinfmod_ct5Map) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrinfmod_ct5Map;
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
		sql="select * from (select * from  (select * from c_br_record where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from c_br_record where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("apply_num", rs.getString("apply_num"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("realname", rs.getString("realname"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("count", rs.getString("count"));
							if(rs.getString("state").equals("4")){
							    obj.put("status", "工具归还");}
							else if (rs.getString("state").equals("5")) {
								obj.put("status", "工具借出");}
							else if (rs.getString("state").equals("1")) {
								obj.put("status", "正常报损");}
							else if (rs.getString("state").equals("2")) {
								obj.put("status", "非正常报损");}
							else if (rs.getString("state").equals("3")) {
								obj.put("status", "工具报丢");}
							obj.put("time", rs.getDate("time"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("mini_qs", rs.getString("mini_qs"));
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
	
	public JSONObject getQueryUsrInfModCT6(HashMap queryUsrinfmod_ct6Map) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrinfmod_ct6Map;
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
							obj.put("company", rs.getString("company"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("mini_qs", rs.getString("mini_qs"));
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
	
	public JSONObject viewUsrInfModCT1(HashMap viewCompanyMap) {
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