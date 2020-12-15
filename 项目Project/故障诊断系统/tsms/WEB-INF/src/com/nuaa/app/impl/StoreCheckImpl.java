package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.StoreCheck;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class StoreCheckImpl implements StoreCheck {
	
	//工人报废刀具处罚信息查询
	public JSONObject getQuerycut(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_cut2ltapply where trim(over)='未处理' and trim(leader_approve)='批准' and trim(lt)!='正常报废' and "+(String)queryHashMap.get("filterc");
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
		
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		String order=(String)queryHashMap.get("orderc");
		String filterc=(String)queryHashMap.get("filterc");
		sql="select * from (select * from (select * from v_cut2ltapply where trim(over)='未处理' and trim(leader_approve)='批准' and trim(lt)!='正常报废' and "+filterc+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_cut2ltapply where over='未处理' and leader_approve='批准' and "+filterc+" order by "+order+") where rownum<="+start+") order by "+order;  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("company", rs.getString("company"));
							obj.put("approve_result", rs.getString("approve_result"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name", rs.getString("name"));
							obj.put("count", rs.getString("count"));
							obj.put("pl_num", rs.getString("worker_num"));
							obj.put("reason",rs.getString("reason"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("lt", rs.getString("lt"));
							obj.put("lt_num", rs.getString("lt_num"));
							obj.put("workname", rs.getString("workname"));
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
	
//	工人报废非测量工具处罚信息查询
	public JSONObject getQueryct(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_ct2ltapply where trim(over)='未处理' and trim(leader_approve)='批准' and trim(lt)!='正常报废' and "+(String)queryHashMap.get("filterct");
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
		
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		String order=(String)queryHashMap.get("orderc");
		String filterct=(String)queryHashMap.get("filterct");
		sql="select * from (select * from (select * from v_ct2ltapply where over='未处理' and leader_approve='批准' and trim(lt)!='正常报废' and "+filterct+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_ct2ltapply where over='未处理' and leader_approve='批准' and "+filterct+" order by "+order+") where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("company", rs.getString("company"));
							obj.put("approve_result", rs.getString("approve_result"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name", rs.getString("name"));
							obj.put("count", rs.getString("count"));
							obj.put("pl_num", rs.getString("worker_num"));
							obj.put("reason",rs.getString("reason"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("lt", rs.getString("lt"));
							obj.put("lt_num", rs.getString("lt_num"));
							obj.put("workname", rs.getString("workname"));
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
	
//	工人报废量具处罚信息查询
	public JSONObject getQuerym(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_m2ltapply where trim(over)='未处理' and trim(leader_approve)='批准' and trim(ot)!='正常报废' and "+(String)queryHashMap.get("filterm");
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
		
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		String order=(String)queryHashMap.get("orderc");
		String filterm=(String)queryHashMap.get("filterm");
		sql="select * from (select * from (select * from v_m2ltapply where trim(over)='未处理' and trim(leader_approve)='批准' and trim(ot)!='正常报废' and "+filterm+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_m2ltapply where over='未处理' and leader_approve='批准' and "+filterm+" order by "+order+") where rownum<="+start+") order by "+order;
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("ot", rs.getString("ot"));
							obj.put("approve_result", rs.getString("approve_result"));
							obj.put("reason", rs.getString("reason"));
							obj.put("liable_stuff_num", rs.getString("worker_num"));
							obj.put("apply_time", rs.getDate("apply_time"));
							obj.put("company", rs.getString("company"));
							obj.put("lt_num", rs.getString("lt_num"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("workname", rs.getString("workname"));
							obj.put("group_num", rs.getString("group_num"));
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
	
//	工人报废测量工具处罚信息查询
	public JSONObject getQuerymt(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_mt2ltapply where trim(over)='未处理' and trim(leader_approve)='批准' and trim(ot)!='正常报废' and "+(String)queryHashMap.get("filtermt");
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
		
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		String order=(String)queryHashMap.get("orderc");
		String filtermt=(String)queryHashMap.get("filtermt");
		sql="select * from (select * from (select * from v_mt2ltapply where trim(over)='未处理' and trim(leader_approve)='批准' and trim(ot)!='正常报废' and "+filtermt+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_mt2ltapply where over='未处理' and leader_approve='批准' and "+filtermt+" order by "+order+") where rownum<="+start+") order by "+order;
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("ot", rs.getString("ot"));
							obj.put("approve_result", rs.getString("approve_result"));
							obj.put("reason", rs.getString("reason"));
							obj.put("liable_stuff_num", rs.getString("worker_num"));
							obj.put("apply_time", rs.getDate("apply_time"));
							obj.put("company", rs.getString("company"));
							obj.put("lt_num", rs.getString("lt_num"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("workname", rs.getString("workname"));
							obj.put("group_num", rs.getString("group_num"));
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
	
	//报废刀具处罚信息查看完毕
	public boolean cutCheck(HashMap cutcheckHMap) {
		// TODO 自动生成方法存根
		final String lt_nums=(String)cutcheckHMap.get("lt_nums");
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_lt_apply_record set over=? where lt_num in("+lt_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"已处理");
								pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	报废非测量工具处罚信息查看完毕
	public boolean ctCheck(HashMap cutcheckHMap) {
		// TODO 自动生成方法存根
		final String lt_nums=(String)cutcheckHMap.get("lt_nums");
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_ct_lt_apply_record set over=? where lt_num in("+lt_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"已处理");
								pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	报废测量工具处罚信息查看完毕
	public boolean mtCheck(HashMap cutcheckHMap) {
		// TODO 自动生成方法存根
		final String lt_nums=(String)cutcheckHMap.get("lt_nums");
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_mt_lt_record set over=? where lt_num in("+lt_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"已处理");
								pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	报废量具处罚信息查看完毕
	public boolean mCheck(HashMap cutcheckHMap) {
		// TODO 自动生成方法存根
		final String lt_nums=(String)cutcheckHMap.get("lt_nums");
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_m_lt_record set over=? where lt_num in("+lt_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"已处理");
								pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
}
