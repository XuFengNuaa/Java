package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.LeaderCheck;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class LeaderCheckImpl implements LeaderCheck {
	
	//查询入库刀具
	public JSONObject getQuerycut(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String leader_num=(String)queryHashMap.get("leader_num");
		String sql="";
		sql="select count(*) as ct from v_cut2entrycheck where trim(status)='申请'";
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
		sql="select entry_num,id,mnum,pclass,bclass,name,company,newstatus,in_count,in_time,city,bill_num,user_num from v_cut2entrycheck where trim(status)='申请'";
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
							obj.put("newstatus", rs.getString("newstatus"));
							obj.put("name", rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("in_count", rs.getString("in_count"));
							obj.put("entry_num",rs.getString("entry_num"));
							obj.put("in_time",rs.getString("in_time"));
							obj.put("city",rs.getString("city"));
							obj.put("bill_num",rs.getString("bill_num"));
							obj.put("user_num",rs.getString("user_num"));
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
	
//	查询入库非测量工具
	public JSONObject getQueryct(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_ct2entrycheck where trim(status)='申请'";
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
		sql="select distinct entry_num,id,mnum,pclass,bclass,name,company,newstatus,in_count,in_time from v_ct2entrycheck where trim(status)='申请'";
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
							obj.put("newstatus", rs.getString("newstatus"));
							obj.put("name", rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("in_count", rs.getString("in_count"));
							obj.put("entry_num",rs.getString("entry_num"));
							obj.put("in_time",rs.getString("in_time"));
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
	
//	查询入库量具
	public JSONObject getQuerym(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_m2entrycheck where trim(status)='申请'"; 
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
		sql="select entry_num,id,mnum,classname,spec,name,company,newstatus,indi_num,in_time from v_m2entrycheck where trim(status)='申请'"; 
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
							obj.put("spec", rs.getString("spec"));
							obj.put("newstatus", rs.getString("newstatus"));
							obj.put("name", rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("entry_num",rs.getString("entry_num"));
							obj.put("in_time",rs.getString("in_time"));
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
	
//	查询入库测量工具
	public JSONObject getQuerymt(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_mt2entrycheck where trim(status)='申请'"; 
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
		sql="select entry_num,id,mnum,classname,spec,name,company,newstatus,indi_num,in_time from v_mt2entrycheck where trim(status)='申请'"; 
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
							obj.put("spec", rs.getString("spec"));
							obj.put("newstatus", rs.getString("newstatus"));
							obj.put("name", rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("entry_num",rs.getString("entry_num"));
							obj.put("in_time",rs.getString("in_time"));
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
	
//	查询送磨刀具
	public JSONObject getQueryCgout(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql = "select count(distinct grd_num) as ct from v_cgrd2check where trim(status)='0' and (trim(app_status)='未处理' or app_status is null)";
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

		sql = "select distinct grd_num,bclass,grd_department,s_time from v_cgrd2check where trim(status)='0' and (trim(app_status)='未处理' or app_status is null)";
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("bclass", rs.getString("bclass"));
							obj.put("grd_department", rs.getString("grd_department"));
							obj.put("grd_num",rs.getString("grd_num"));
							obj.put("s_time",rs.getString("s_time"));
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
	
//	具送磨审批状态查询
	 public JSONObject detail_do_togrind(HashMap queryMap) {
			// TODO 自动生成方法存根
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			String sql1="";
			System.out.println("filter0=="+(String)queryMap.get("filter"));
			
			sql="select  *  from  v_cgrd2check  where leader_num is null and "+(String)queryMap.get("filter")+" order by "+(String)queryMap.get("order")+"" ;
			sql1="select  count(*) as ct  from  v_cgrd2check  where leader_num is null and "+(String)queryMap.get("filter");
			final String [] count=new String[1];
			count[0] = "";
			
			try {
				DbUtil.execute(sql1,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {
						if (rs.next()) {
							try {
								jsonObj.put("check_totalProp",rs.getString("ct"));
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
			
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
								decimalformat.setMaximumFractionDigits(2);
								obj.put("id", rs.getString("id"));															
								obj.put("rid", rs.getString("rid"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));	
								obj.put("mnum", rs.getString("mnum"));
								obj.put("grd_department", rs.getString("grd_department"));	
								obj.put("s_time", rs.getString("s_time"));	
								obj.put("grd_num", rs.getString("grd_num"));	
								obj.put("s_count", rs.getString("s_count"));	
								obj.put("price", decimalformat.format(rs.getFloat("price")));	
								obj.put("total_price", decimalformat.format(rs.getFloat("price")*rs.getInt("s_count")));	
							      jsonArray.put(obj);
							     
							}	
								jsonObj.put("check_data", jsonArray);
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
	
	
//	查询返库刀具
	public JSONObject getQueryCgin(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		final String leader_num=(String)queryHashMap.get("leader_num");
		String sql="";
		sql="select count(distinct grd_num) as ct from v_cgrd2returncheck where trim(status)='2' and (trim(app_status_in)='未处理' or app_status_in is null)";
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
		sql = "select distinct grd_num,bclass,grd_department,s_time,r_time from v_cgrd2returncheck where trim(status)='2' and (trim(app_status_in)='未处理' or app_status_in is null)";
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("bclass", rs.getString("bclass"));
							obj.put("grd_department", rs.getString("grd_department"));
							obj.put("grd_num",rs.getString("grd_num"));
							obj.put("s_time",rs.getString("s_time"));
							obj.put("r_time",rs.getString("r_time"));
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
	
	
//	具送磨审批状态查询
	 public JSONObject detail_do_ingrind(HashMap queryMap) {
			// TODO 自动生成方法存根
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			String sql1="";
			sql="select  *  from  v_cgrd2returncheck  where leader_num is null and "+(String)queryMap.get("filter")+" order by "+(String)queryMap.get("order")+"" ;
			sql1="select  count(*) as ct  from  v_cgrd2returncheck  where leader_num is null and "+(String)queryMap.get("filter");
			final String [] count=new String[1];
			count[0] = "";
			
			try {
				DbUtil.execute(sql1,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {
						if (rs.next()) {
							try {
								jsonObj.put("check_totalProp",rs.getString("ct"));
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
			
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
								decimalformat.setMaximumFractionDigits(2);
								obj.put("id", rs.getString("id"));															
								obj.put("rid", rs.getString("rid"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("pclass", rs.getString("pclass"));	
								obj.put("mnum", rs.getString("mnum"));
								obj.put("grd_department", rs.getString("grd_department"));	
								obj.put("s_time", rs.getString("s_time"));	
								obj.put("grd_num", rs.getString("grd_num"));	
								obj.put("s_count", rs.getString("s_count"));	
								obj.put("r_count", rs.getString("r_count"));
								obj.put("r_time",rs.getString("r_time"));
								obj.put("l_count",rs.getInt("s_count")-rs.getInt("r_count"));
							      jsonArray.put(obj);
							     
							}	
								jsonObj.put("check_data", jsonArray);
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
	
	
	
//	刀具入库批准
	public String appCut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String entry_nums=(String)appcutHMap.get("entry_nums");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_entry_trecord set leader_num=?,check_time=?,status=? where trim(status)='申请' and entry_num in ("+entry_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"批准");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	拒绝刀具入库
	public String refCut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String entry_nums=(String)appcutHMap.get("entry_nums");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_entry_trecord set leader_num=?,check_time=?,status=? where trim(status)='申请' and entry_num in ("+entry_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"拒绝");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	判断显示的数据是否有被操作过
	public boolean cutCheck(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String entry_nums=(String)checkHMap.get("entry_nums");
		String counts=(String)checkHMap.get("length");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_cut2entrycheck where trim(status)='申请' and entry_num in("+entry_nums+")";  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int wholecount = 0;
					while(rs.next()){		
						wholecount = wholecount+1;
					}
				System.out.println("wholecount=="+wholecount);
				if(Count>wholecount){
					count[0] = 0;
				}else{
					count[0] = 1;
				}
				}
			});	
			if (count[0]==1){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}	
	
	
//	入库非测量工具批准
	public String appCt(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String entry_nums=(String)appcutHMap.get("entry_nums");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_ct_entry_trecord set leader_num=?,check_time=?,status=? where trim(status)='申请' and entry_num in ("+entry_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"批准");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	拒绝非测量工具入库
	public String refCt(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String entry_nums=(String)appcutHMap.get("entry_nums");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_ct_entry_trecord set leader_num=?,check_time=?,status=? where trim(status)='申请' and entry_num in ("+entry_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"拒绝");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	判断显示的数据是否有被操作过
	public boolean ctCheck(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String entry_nums=(String)checkHMap.get("entry_nums");
		String counts=(String)checkHMap.get("length");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_ct2entrycheck where trim(status)='申请' and entry_num in("+entry_nums+")";  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int wholecount = 0;
					while(rs.next()){		
						wholecount = wholecount+1;
					}
				System.out.println("wholecount=="+wholecount);
				if(Count>wholecount){
					count[0] = 0;
				}else{
					count[0] = 1;
				}
				}
			});	
			if (count[0]==1){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}	
	
	
//	批准量具批准
	public String appM(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String entry_nums=(String)appcutHMap.get("entry_nums");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_m_entry_trecord set leader_num=?,check_time=?,status=? where entry_num in ("+entry_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"批准");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
//	拒绝量具入库
	public String refM(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String entry_nums=(String)appcutHMap.get("entry_nums");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_m_entry_trecord set leader_num=?,check_time=?,status=? where entry_num in ("+entry_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"拒绝");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	判断显示的数据是否有被操作过
	public boolean mCheck(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String entry_nums=(String)checkHMap.get("entry_nums");
		String counts=(String)checkHMap.get("length");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_m2entrycheck where trim(status)='申请' and entry_num in("+entry_nums+")";  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int wholecount = 0;
					while(rs.next()){		
						wholecount = wholecount+1;
					}
				System.out.println("wholecount=="+wholecount);
				if(Count>wholecount){
					count[0] = 0;
				}else{
					count[0] = 1;
				}
				}
			});	
			if (count[0]==1){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}	
	
	
//	入库测量工具批准
	public String appMt(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String entry_nums=(String)appcutHMap.get("entry_nums");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_mt_entry_trecord set leader_num=?,check_time=?,status=? where entry_num in ("+entry_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"批准");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	拒绝测量工具入库
	public String refMt(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String entry_nums=(String)appcutHMap.get("entry_nums");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_mt_entry_trecord set leader_num=?,check_time=?,status=? where entry_num in ("+entry_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"拒绝");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	判断显示的数据是否有被操作过
	public boolean mtCheck(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String entry_nums=(String)checkHMap.get("entry_nums");
		String counts=(String)checkHMap.get("length");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_mt2entrycheck where trim(status)='申请' and entry_num in("+entry_nums+")";  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int wholecount = 0;
					while(rs.next()){		
						wholecount = wholecount+1;
					}
				System.out.println("wholecount=="+wholecount);
				if(Count>wholecount){
					count[0] = 0;
				}else{
					count[0] = 1;
				}
				}
			});	
			if (count[0]==1){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}	
	
	
//	送磨刀具批准完毕
	public String appCgout(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String grd_num=(String)appcutHMap.get("grd_num");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
//			sqls[0]="insert into t_c_grd_check (leader_num,check_time,rid) values(?,?,?)";
			sqls[0]="update t_c_grd_record set app_status=?,leader_num_grd=?,check_time_grd=? where leader_num_grd is null and grd_num in ("+grd_num+")";
			Logger.debug(sqls[0]);
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
/*							for(int i=0;i<rid.length;i++){
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,rid[i]);
								pstmts[0].addBatch();
							}          */
							pstmts[0].setString(1, "批准");
							pstmts[0].setString(2,leader_num);
							pstmts[0].setDate(3,curDate);
							pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	送磨刀具批准完毕
	public String appCgoutM(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String Rid=(String)appcutHMap.get("Rid");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_grd_record set app_status=?,leader_num_grd=?,check_time_grd=? where rid in ("+Rid+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1, "批准");
							pstmts[0].setString(2,leader_num);
							pstmts[0].setDate(3,curDate);
							pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	

//	送磨刀具批准完毕
	public String refCgoutM(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String Rid=(String)appcutHMap.get("Rid");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_grd_record set app_status=?,leader_num_grd=?,check_time_grd=? where rid in ("+Rid+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1, "拒绝");
							pstmts[0].setString(2,leader_num);
							pstmts[0].setDate(3,curDate);
							pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	送磨刀具拒绝
	public String refCgout(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String grd_num=(String)appcutHMap.get("grd_num");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_grd_record set app_status=?,leader_num_grd=?,check_time_grd=? where leader_num_grd is null and grd_num in ("+grd_num+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1, "拒绝");
							pstmts[0].setString(2,leader_num);
							pstmts[0].setDate(3,curDate);
							pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	判断显示的数据是否有被操作过
	public boolean grdMCheck(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String Rid=(String)checkHMap.get("Rid");
		String counts=(String)checkHMap.get("length");
		final int Count = Integer.parseInt(counts);
		sql="select * from t_c_grd_record where leader_num_grd is null and rid in ("+Rid+")";  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int wholecount = 0;
					while(rs.next()){		
						wholecount = wholecount+1;
					}
				System.out.println("wholecount=="+wholecount);
				if(Count>wholecount){
					count[0] = 0;
				}else{
					count[0] = 1;
				}
				}
			});	
			if (count[0]==1){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}	
	
	
//	判断显示的数据是否有被操作过
	public boolean grdCheck(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String grd_num=(String)checkHMap.get("grd_num");
		String counts=(String)checkHMap.get("length");
		final int Count = Integer.parseInt(counts);
		sql="select distinct grd_num from t_c_grd_record where leader_num_grd is null and grd_num in ("+grd_num+")";  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int wholecount = 0;
					while(rs.next()){		
						wholecount = wholecount+1;
					}
				System.out.println("wholecount=="+wholecount);
				if(Count>wholecount){
					count[0] = 0;
				}else{
					count[0] = 1;
				}
				}
			});	
			if (count[0]==1){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
//	返库刀具查看完毕
	public String appCgin(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String grd_num=(String)appcutHMap.get("grd_num");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_grd_record set leader_num_in=?,check_time_in=?,app_status_in=? where grd_num in ("+grd_num+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,leader_num);
								pstmts[0].setDate(2,curDate);
								pstmts[0].setString(3,"批准");
								pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
//	返库刀具查看完毕
	public String appCginM(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String Rid=(String)appcutHMap.get("Rid");
		final String leader_num=(String)appcutHMap.get("leader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_grd_record set leader_num_in=?,check_time_in=?,app_status_in=? where Rid in ("+Rid+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,leader_num);
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,"批准");
							pstmts[0].addBatch();
						}
			});						
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	

//	判断显示的数据是否有被操作过
	public boolean grdinMCheck(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String Rid=(String)checkHMap.get("Rid");
		String counts=(String)checkHMap.get("length");
		final int Count = Integer.parseInt(counts);
		sql="select * from t_c_grd_record where leader_num_in is null and rid in ("+Rid+")";  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int wholecount = 0;
					while(rs.next()){		
						wholecount = wholecount+1;
					}
					System.out.println("wholecount=="+wholecount);
					if(Count>wholecount){
						count[0] = 0;
					}else{
						count[0] = 1;
					}
				}
			});	
			if (count[0]==1){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}	
	
	
//	判断显示的数据是否有被操作过
	public boolean grdinCheck(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String grd_num=(String)checkHMap.get("grd_num");
		String counts=(String)checkHMap.get("length");
		final int Count = Integer.parseInt(counts);
		sql="select distinct grd_num from t_c_grd_record where leader_num_in is null and grd_num in ("+grd_num+")";  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int wholecount = 0;
					while(rs.next()){		
						wholecount = wholecount+1;
					}
					System.out.println("wholecount=="+wholecount);
					if(Count>wholecount){
						count[0] = 0;
					}else{
						count[0] = 1;
					}
				}
			});	
			if (count[0]==1){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	
}
