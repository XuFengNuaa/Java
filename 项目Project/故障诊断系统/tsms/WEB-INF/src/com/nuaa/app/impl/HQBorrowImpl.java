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

import com.nuaa.app.HQBorrow;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class HQBorrowImpl implements HQBorrow {
	  public JSONObject getQuerygene(HashMap query_cutterMap) {
			// TODO 自动生成方法存根
			HashMap query_cutterHashMap=query_cutterMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select  count(*) as ct  from  V_CUTTER2COM where hq='是' and "+(String)query_cutterMap.get("filter");
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
			int start=Integer.parseInt((String)query_cutterHashMap.get("start"));
			int limit=Integer.parseInt((String)query_cutterHashMap.get("limit"));
			
			String filter=(String)query_cutterHashMap.get("filter");
			String order=(String)query_cutterHashMap.get("order");        
			sql="select * from (select * from  (select * from V_CUTTER2COM where  "+filter+" and hq='是' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from V_CUTTER2COM where "+filter+" and hq='是' order by "+order+" ) where rownum<="+start+") order by "+order; 
			
			Logger.debug(sql);
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
								obj.put("order_num", rs.getString("order_num"));
								obj.put("hq", rs.getString("hq"));
								obj.put("rank_angle", rs.getString("rank_angle"));
								obj.put("c_mate", rs.getString("c_mate"));
								obj.put("coat_mate", rs.getString("coat_mate"));
								obj.put("mini_qs", rs.getString("mini_qs"));
								obj.put("suit_mate1", rs.getString("suit_mate1"));
								obj.put("suit_mate2", rs.getString("suit_mate2"));
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
								obj.put("use_freq", rs.getString("use_freq"));	
								obj.put("series_num", rs.getString("series_num"));	
								obj.put("order_num", rs.getString("order_num"));									
								obj.put("nc_count_in", rs.getString("nc_count_in"));	
								obj.put("uc_count_in", rs.getString("uc_count_in"));	
								obj.put("gc_count_in", rs.getString("gc_count_in"));
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
		};
		 	
	
	public boolean appOut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String id=(String)appcutHMap.get("id");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String drawing_num=(String)appcutHMap.get("drawing_num");
		final String count=(String)appcutHMap.get("count");
		final String team_num=(String)appcutHMap.get("team_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="insert into t_hq_c_apply_record (apply_num,worker_num,count,type_id,drawing_num,apply_time,leader_approve,gleader_approve,status,gleader_num) values(?,?,?,?,?,?,?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							String uuid = UUID.randomUUID().toString();
							pstmts[0].setString(1,uuid);
							pstmts[0].setString(2,worker_num);
							pstmts[0].setString(3,count);
							pstmts[0].setString(4,id);
							pstmts[0].setString(5,drawing_num);
							pstmts[0].setDate(6,curDate);
							pstmts[0].setString(7,"未处理");
							pstmts[0].setString(8,"未处理");
							pstmts[0].setString(9,"未处理");
							pstmts[0].setString(10,team_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
	public boolean needC(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String id=(String)appcutHMap.get("id");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String drawing_num=(String)appcutHMap.get("drawing_num_need");
		final String need_count=(String)appcutHMap.get("need_count");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="insert into t_c_need (need_num,worker_num,count,type_id,drawing_num,time,leader_approve,gleader_approve,status) values(?,?,?,?,?,?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							String uuid = UUID.randomUUID().toString();
							pstmts[0].setString(1,uuid);
							pstmts[0].setString(2,worker_num);
							pstmts[0].setString(3,need_count);
							pstmts[0].setString(4,id);
							pstmts[0].setString(5,drawing_num);
							pstmts[0].setDate(6,curDate);
							pstmts[0].setString(7,"未处理");
							pstmts[0].setString(8,"未处理");
							pstmts[0].setString(9,"未处理");
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
	
	public JSONObject getQuerygeneCT(HashMap queryUsrinfmod_ct2Map) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrinfmod_ct2Map;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from  ct_base where hq='是' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from ct_base where "+filter+" and hq='是' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from ct_base where "+filter+" and hq='是' order by "+order+" ) where rownum<="+start+") order by "+order; 
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
							obj.put("spec", rs.getString("spec"));
							obj.put("hq", rs.getString("hq"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("location", rs.getString("location"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
							obj.put("order_num", rs.getString("order_num"));

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

	public boolean appOutCT(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String id=(String)appcutHMap.get("id");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String drawing_num=(String)appcutHMap.get("drawing_num");
		final String count=(String)appcutHMap.get("count");
		final String team_num=(String)appcutHMap.get("team_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="insert into t_hq_ct_apply_record (apply_num,worker_num,count,type_id,drawing_num,apply_time,leader_approve,gleader_approve,status,gleader_num) values(?,?,?,?,?,?,?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							String uuid = UUID.randomUUID().toString();
							pstmts[0].setString(1,uuid);
							pstmts[0].setString(2,worker_num);
							pstmts[0].setString(3,count);
							pstmts[0].setString(4,id);
							pstmts[0].setString(5,drawing_num);
							pstmts[0].setDate(6,curDate);
							pstmts[0].setString(7,"未处理");
							pstmts[0].setString(8,"未处理");
							pstmts[0].setString(9,"未处理");
							pstmts[0].setString(10,team_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
	
	public boolean needCT(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String id=(String)appcutHMap.get("id");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String drawing_num=(String)appcutHMap.get("drawing_num_need");
		final String need_count=(String)appcutHMap.get("need_count");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="insert into t_ct_need (need_num,worker_num,count,type_id,drawing_num,time,leader_approve,gleader_approve,status) values(?,?,?,?,?,?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							String uuid = UUID.randomUUID().toString();
							pstmts[0].setString(1,uuid);
							pstmts[0].setString(2,worker_num);
							pstmts[0].setString(3,need_count);
							pstmts[0].setString(4,id);
							pstmts[0].setString(5,drawing_num);
							pstmts[0].setDate(6,curDate);
							pstmts[0].setString(7,"未处理");
							pstmts[0].setString(8,"未处理");
							pstmts[0].setString(9,"未处理");
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public JSONObject getQuerygeneM(HashMap queryUsrinfmod_ct2Map) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrinfmod_ct2Map;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from  m_indi_base where status='在库' and newstatus='新' and hq='是'and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from m_indi_base where  "+filter+" and newstatus='新' and status='在库' and hq='是' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from m_indi_base where "+filter+" and newstatus='新' and status='在库' and hq='是'  order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("indi_id", rs.getString("indi_id"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("classname", rs.getString("classname"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("spec", rs.getString("spec"));
							obj.put("diviv", rs.getString("diviv"));
							obj.put("hq", rs.getString("hq"));
							obj.put("remark", rs.getString("remark"));
							if (rs.getString("apply_state")==null) 
							{obj.put("apply_state","未申请");}else {
							obj.put("apply_state", rs.getString("apply_state"));}
							
							obj.put("apply_time", rs.getDate("apply_time"));

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

	public boolean appOutM(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String id=(String)appcutHMap.get("id");
		final String indi_id=(String)appcutHMap.get("indi_id");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String drawing_num=(String)appcutHMap.get("drawing_num");
		final String team_num=(String)appcutHMap.get("team_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[2];
		try {
			sqls[0]="insert into t_hqm_bapply (apply_num,indi_id,worker_num,type_id,drawing_num,apply_time,gleader_approve,state,gleader_num) values(?,?,?,?,?,?,?,?,?)";
			sqls[1] = "update T_M_INDI set apply_time=?,apply_state=?  where indi_id='"+indi_id+"'";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							String uuid = UUID.randomUUID().toString();
							pstmts[0].setString(1,uuid);
							pstmts[0].setString(2,indi_id);
							pstmts[0].setString(3,worker_num);
							pstmts[0].setString(4,id);
							pstmts[0].setString(5,drawing_num);
							pstmts[0].setDate(6,curDate);
							pstmts[0].setString(7,"未处理");
							pstmts[0].setString(8,"未处理");
							pstmts[0].setString(9,team_num);
							pstmts[0].addBatch();
							pstmts[1].setDate(1,curDate);
							pstmts[1].setString(2,"已申请");
							pstmts[1].addBatch();
						}
			});			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}	
	
	
	public boolean needM(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String id=(String)appcutHMap.get("id");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String drawing_num=(String)appcutHMap.get("drawing_num_need");
		final String need_count=(String)appcutHMap.get("need_count");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="insert into t_m_need (need_num,worker_num,count,type_id,drawing_num,time,leader_approve,gleader_approve,status) values(?,?,?,?,?,?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							String uuid = UUID.randomUUID().toString();
							pstmts[0].setString(1,uuid);
							pstmts[0].setString(2,worker_num);
							pstmts[0].setString(3,need_count);
							pstmts[0].setString(4,id);
							pstmts[0].setString(5,drawing_num);
							pstmts[0].setDate(6,curDate);
							pstmts[0].setString(7,"未处理");
							pstmts[0].setString(8,"未处理");
							pstmts[0].setString(9,"未处理");
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
	
	
	//判断借出的同一段时间与空间内是否已被操作
	public boolean checkStateM(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		final String id=(String)checkHMap.get("id");
		final String indi_id="'"+(String)checkHMap.get("indi_id")+"'";
		String apply_num=(String)checkHMap.get("apply_num");
		sql="select * from m_indi_base where apply_state='已申请' and indi_id="+indi_id;  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=0;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					while(rs.next()){					
						count[0]=1;
					}
				}
			});	
			if (count[0]==1){	
				return false;
			}else{
				return true;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public JSONObject getQuerygeneMT(HashMap queryUsrinfmod_ct2Map) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryUsrinfmod_ct2Map;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from  mt_indi_base where status='在库' and newstatus='新' and hq='是'and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from mt_indi_base where  "+filter+" and newstatus='新' and status='在库' and hq='是' order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from mt_indi_base where "+filter+" and newstatus='新' and status='在库' and hq='是'  order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("indi_id", rs.getString("indi_id"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("classname", rs.getString("classname"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("spec", rs.getString("spec"));
							obj.put("diviv", rs.getString("diviv"));
							obj.put("hq", rs.getString("hq"));
							obj.put("remark", rs.getString("remark"));
							if (rs.getString("apply_state")==null) 
							{obj.put("apply_state","未申请");}else {
							obj.put("apply_state", rs.getString("apply_state"));}
							
							obj.put("apply_time", rs.getDate("apply_time"));

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

	public boolean appOutMT(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String id=(String)appcutHMap.get("id");
		final String indi_id=(String)appcutHMap.get("indi_id");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String drawing_num=(String)appcutHMap.get("drawing_num");
		final String team_num=(String)appcutHMap.get("team_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[2];
		try {
			sqls[0]="insert into t_hqmt_bapply (apply_num,indi_id,worker_num,type_id,drawing_num,apply_time,gleader_approve,state,gleader_num) values(?,?,?,?,?,?,?,?,?)";
			sqls[1]= "update T_MT_INDI set apply_time=?,apply_state=?  where indi_id='"+indi_id+"'";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							String uuid = UUID.randomUUID().toString();
							pstmts[0].setString(1,uuid);
							pstmts[0].setString(2,indi_id);
							pstmts[0].setString(3,worker_num);
							pstmts[0].setString(4,id);
							pstmts[0].setString(5,drawing_num);
							pstmts[0].setDate(6,curDate);
							pstmts[0].setString(7,"未处理");
							pstmts[0].setString(8,"未处理");
							pstmts[0].setString(9,team_num);
							pstmts[0].addBatch();
							pstmts[1].setDate(1,curDate);
							pstmts[1].setString(2,"已申请");
							pstmts[1].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}	
	
	
	public boolean needMT(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String id=(String)appcutHMap.get("id");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String drawing_num=(String)appcutHMap.get("drawing_num_need");
		final String need_count=(String)appcutHMap.get("need_count");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="insert into t_mt_need (need_num,worker_num,count,type_id,drawing_num,time,leader_approve,gleader_approve,status) values(?,?,?,?,?,?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							String uuid = UUID.randomUUID().toString();
							pstmts[0].setString(1,uuid);
							pstmts[0].setString(2,worker_num);
							pstmts[0].setString(3,need_count);
							pstmts[0].setString(4,id);
							pstmts[0].setString(5,drawing_num);
							pstmts[0].setDate(6,curDate);
							pstmts[0].setString(7,"未处理");
							pstmts[0].setString(8,"未处理");
							pstmts[0].setString(9,"未处理");
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
	
	public boolean checkStateMT(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		final String id=(String)checkHMap.get("id");
		final String indi_id="'"+(String)checkHMap.get("indi_id")+"'";
		String apply_num=(String)checkHMap.get("apply_num");
		sql="select * from mt_indi_base where apply_state='已申请' and indi_id="+indi_id;  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=0;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					while(rs.next()){					
						count[0]=1;
					}
				}
			});	
			if (count[0]==1){	
				return false;
			}else{
				return true;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
