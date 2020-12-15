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
import com.nuaa.app.UsrPunish;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class UsrPunishImpl implements UsrPunish{
	public JSONObject getQueryUsrPunishC(HashMap queryUsrpunish_cMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrpunish_cMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  v_cut2ltapply where worker_num='"+worker_num+"' and lt<>'正常报废' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from v_cut2ltapply where "+filter+" and worker_num='"+worker_num+"' and lt<>'正常报废' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_cut2ltapply where "+filter+" and worker_num='"+worker_num+"' order by "+order+" ) where rownum<="+start+") order by "+order;
		//System.out.println('"'+sql+'"');
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
							obj.put("order_num", rs.getString("order_num"));
							obj.put("count", rs.getString("count"));
							obj.put("lt", rs.getString("lt"));
							obj.put("leader_num", rs.getString("leader_num"));
							obj.put("leader_name", rs.getString("leader_name"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("leader_time", rs.getDate("leader_time"));
							obj.put("approve_result", rs.getString("approve_result"));
							if(rs.getString("over")==null) {
								obj.put("over", "未处理");}
							else {obj.put("over", rs.getString("over"));}
							

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

	
	
	public JSONObject getQueryUsrPunishC_hide(HashMap queryUsrpunish_cMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrpunish_cMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  v_cut2ltapply where worker_num='"+worker_num+"' and over<>'已处理' and lt<>'正常报废'  and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from v_cut2ltapply where "+filter+" and worker_num='"+worker_num+"' and over<>'已处理'  and lt<>'正常报废' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_cut2ltapply where "+filter+" and worker_num='"+worker_num+"' and over<>'已处理' and lt<>'正常报废' order by "+order+" ) where rownum<="+start+") order by "+order;
		//System.out.println('"'+sql+'"');
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
							obj.put("order_num", rs.getString("order_num"));
							obj.put("count", rs.getString("count"));
							obj.put("lt", rs.getString("lt"));
							obj.put("leader_num", rs.getString("leader_num"));
							obj.put("leader_name", rs.getString("leader_name"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("leader_time", rs.getDate("leader_time"));
							obj.put("approve_result", rs.getString("approve_result"));
							if(rs.getString("over")==null) {
								obj.put("over", "未处理");}
							else {obj.put("over", rs.getString("over"));}
							

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
	
	
	
	public JSONObject getQueryUsrPunishM(HashMap queryUsrpunish_mMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrpunish_mMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  v_m2ltapply where worker_num='"+worker_num+"' and ot<>'正常报废' and  "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from v_m2ltapply where "+filter+" and worker_num='"+worker_num+"' and ot<>'正常报废' order by "+order+" ) where rownum<="+(start+limit)+" minus select  *  from  (select * from v_m2ltapply where "+filter+" and worker_num='"+worker_num+"' and ot<>'正常报废' order by "+order+"  ) where rownum<="+start+") order by "+order; 
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
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("ot", rs.getString("ot"));
							obj.put("leader_num", rs.getString("leader_num"));
							obj.put("leader_name", rs.getString("leader_name"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("approve_time", rs.getDate("approve_time"));
							obj.put("approve_result", rs.getString("approve_result"));
							if(rs.getString("over")==null) {
								obj.put("over", "未处理");}
							else {obj.put("over", rs.getString("over"));}

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
	
	
	public JSONObject getQueryUsrPunishM_hide(HashMap queryUsrpunish_mMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrpunish_mMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  v_m2ltapply where worker_num='"+worker_num+"' and  over<>'已处理'  and ot<>'正常报废' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from v_m2ltapply where "+filter+" and worker_num='"+worker_num+"' and over<>'已处理'  and ot<>'正常报废' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_m2ltapply where "+filter+" and worker_num='"+worker_num+"' and over<>'已处理' and ot<>'正常报废'  order by "+order+" ) where rownum<="+start+") order by "+order;
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
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("ot", rs.getString("ot"));
							obj.put("leader_num", rs.getString("leader_num"));
							obj.put("leader_name", rs.getString("leader_name"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("approve_time", rs.getDate("approve_time"));
							obj.put("approve_result", rs.getString("approve_result"));
							if(rs.getString("over")==null) {
								obj.put("over", "未处理");}
							else {obj.put("over", rs.getString("over"));}

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
	
	public JSONObject getQueryUsrPunishCT(HashMap queryUsrpunish_ctMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrpunish_ctMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  v_ct2ltapply where worker_num='"+worker_num+"'  and lt<>'正常报废' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from v_ct2ltapply where "+filter+" and worker_num='"+worker_num+"' and lt<>'正常报废' order by "+order+" ) where rownum<="+(start+limit)+" minus select  *  from  (select * from v_ct2ltapply where "+filter+" and worker_num='"+worker_num+"' and lt<>'正常报废'  order by "+order+" ) where rownum<="+start+") order by "+order; 
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
							obj.put("lt", rs.getString("lt"));
							obj.put("leader_num", rs.getString("leader_num"));
							obj.put("leader_name", rs.getString("leader_name"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("leader_time", rs.getDate("leader_time"));
							obj.put("approve_result", rs.getString("approve_result"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("spec", rs.getString("spec"));
							if(rs.getString("over")==null) {
								obj.put("over", "未处理");}
							else {obj.put("over", rs.getString("over"));}

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
	
	public JSONObject getQueryUsrPunishCT_hide(HashMap queryUsrpunish_ctMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrpunish_ctMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from  v_ct2ltapply where worker_num='"+worker_num+"' and  over<>'已处理' and lt<>'正常报废' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from v_ct2ltapply where "+filter+" and worker_num='"+worker_num+"' and over<>'已处理' and lt<>'正常报废'  order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_ct2ltapply where "+filter+" and worker_num='"+worker_num+"' and over<>'已处理' and lt<>'正常报废'  order by "+order+" ) where rownum<="+start+") order by "+order;
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
							obj.put("lt", rs.getString("lt"));
							obj.put("leader_num", rs.getString("leader_num"));
							obj.put("leader_name", rs.getString("leader_name"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("leader_time", rs.getDate("leader_time"));
							obj.put("approve_result", rs.getString("approve_result"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("spec", rs.getString("spec"));
							if(rs.getString("over")==null) {
								obj.put("over", "未处理");}
							else {obj.put("over", rs.getString("over"));}

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
	
	
	public JSONObject getQueryUsrPunishMT(HashMap queryUsrpunish_mtMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrpunish_mtMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from v_mt2ltapply where worker_num='"+worker_num+"' and ot<>'正常报废'  and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from v_mt2ltapply where "+filter+" and worker_num='"+worker_num+"' and ot<>'正常报废' order by "+order+" ) where rownum<="+(start+limit)+" minus select  *  from  (select * from v_mt2ltapply where "+filter+" and worker_num='"+worker_num+"' and ot<>'正常报废'  order by "+order+" ) where rownum<="+start+") order by "+order; 
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
							//obj.put("order_num", rs.getString("order_num"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));
							//obj.put("count", rs.getString("count"));
							obj.put("ot", rs.getString("ot"));
							obj.put("leader_num", rs.getString("leader_num"));
							obj.put("leader_name", rs.getString("leader_name"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("approve_time", rs.getDate("approve_time"));
							obj.put("approve_result", rs.getString("approve_result"));
							if(rs.getString("over")==null) {
								obj.put("over", "未处理");}
							else {obj.put("over", rs.getString("over"));}

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
		
	
	public JSONObject getQueryUsrPunishMT_hide(HashMap queryUsrpunish_mtMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrpunish_mtMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String worker_num=(String)queryHashMap.get("worker_num");
		String sql="";
		sql="select count(*) as ct from v_mt2ltapply where worker_num='"+worker_num+"' and ot<>'正常报废'  and  over<>'已处理' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from v_mt2ltapply where "+filter+" and worker_num='"+worker_num+"' and ot<>'正常报废'  and over<>'已处理' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_mt2ltapply where "+filter+" and worker_num='"+worker_num+"' and over<>'已处理' and ot<>'正常报废'  order by "+order+" ) where rownum<="+start+") order by "+order;
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
							//obj.put("order_num", rs.getString("order_num"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));
							//obj.put("count", rs.getString("count"));
							obj.put("ot", rs.getString("ot"));
							obj.put("leader_num", rs.getString("leader_num"));
							obj.put("leader_name", rs.getString("leader_name"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("approve_time", rs.getDate("approve_time"));
							obj.put("approve_result", rs.getString("approve_result"));
							if(rs.getString("over")==null) {
								obj.put("over", "未处理");}
							else {obj.put("over", rs.getString("over"));}

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
	public JSONObject viewUsrPunishC(HashMap viewCompanyMap) {
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