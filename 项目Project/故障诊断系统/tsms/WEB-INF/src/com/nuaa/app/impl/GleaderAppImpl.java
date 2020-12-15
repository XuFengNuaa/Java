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

import com.nuaa.app.GleaderApp;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class GleaderAppImpl implements GleaderApp {

	
	//查询已申请但未批准的高质新刀具
	public JSONObject getQuerycut(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final String gleader_num="'"+(String)queryMap.get("gleader_num")+"'";
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
	//		sql="select count(*) as ct from v_cut2hqapply where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") and "+(String)queryHashMap.get("filter");	
		sql="select count(*) as ct from v_cut2hqapply where trim(gleader_approve)='未处理' and gleader_num="+gleader_num+" and "+(String)queryHashMap.get("filter");
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
//		int start=Integer.parseInt((String)queryHashMap.get("start"));
//		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		
//		String filter=(String)queryHashMap.get("filter");
//		String order=(String)queryHashMap.get("order");
		sql="select * from v_cut2hqapply where trim(gleader_approve)='未处理' and gleader_num="+gleader_num+" order by apply_time DESC";  
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
							obj.put("name", rs.getString("name"));
//							obj.put("company", rs.getString("company"));
							obj.put("count", rs.getString("count"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("gleader_num", rs.getString("gleader_num"));
							obj.put("status",rs.getString("status"));
							obj.put("apply_num",rs.getString("apply_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("realname",rs.getString("realname"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("drawing_num", rs.getString("drawing_num"));
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
	
	
//	查询已申请但未批准的高质新非测量工具
	public JSONObject getQueryct(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final String gleader_num="'"+(String)queryMap.get("gleader_num")+"'";
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_ct2hqapply where trim(gleader_approve)='未处理' and gleader_num="+gleader_num+"  and "+(String)queryHashMap.get("filter");
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
		sql="select * from v_ct2hqapply where trim(gleader_approve)='未处理' and gleader_num="+gleader_num+"  order by apply_time DESC";  
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
							obj.put("name", rs.getString("name"));
							obj.put("company", rs.getString("company"));
							obj.put("location", rs.getString("location"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("spec", rs.getString("spec"));
							obj.put("hq", rs.getString("hq"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("gleader_num", rs.getString("gleader_num"));
							obj.put("count", rs.getString("count"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("status",rs.getString("status"));
							obj.put("apply_num",rs.getString("apply_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("realname",rs.getString("realname"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("drawing_num", rs.getString("drawing_num"));
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
	
//	查询已申请但未批准的高质新量具
	public JSONObject getQuerym(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final String gleader_num="'"+(String)queryMap.get("gleader_num")+"'";
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_m2hqmmt where trim(gleader_approve)='未处理' and gleader_num="+gleader_num+" and "+(String)queryHashMap.get("filter");
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
		sql="select * from v_m2hqmmt where trim(gleader_approve)='未处理' and gleader_num="+gleader_num+"  order by apply_time DESC";  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("indi_id", rs.getString("indi_id"));
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("gleader_num", rs.getString("gleader_num"));
							obj.put("status",rs.getString("status"));
							obj.put("apply_num",rs.getString("apply_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("realname",rs.getString("realname"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("drawing_num", rs.getString("drawing_num"));
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
	
	//量具明细查询
	public JSONObject detailM(HashMap detail_m_indiHMap) {
		// TODO 自动生成方法存根
		HashMap detail_m_indiHashMap=detail_m_indiHMap;
		final JSONObject jsonObj=new JSONObject();
		final String indi_id=(String)detail_m_indiHashMap.get("indi_id");
		String sql="";
		sql="select * from v_mindi2v_mcom where indi_id='"+indi_id+"'"; 

		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							jsonObj.put("indi_id", rs.getString("indi_id"));
							jsonObj.put("indi_num", rs.getString("indi_num"));
							jsonObj.put("mnum", rs.getString("mnum"));
							jsonObj.put("classname", rs.getString("classname"));
							jsonObj.put("name", rs.getString("name"));
							jsonObj.put("spec", rs.getString("spec"));
							jsonObj.put("diviv",rs.getString("diviv"));
							jsonObj.put("company", rs.getString("company"));
							jsonObj.put("newstatus", rs.getString("newstatus"));
							jsonObj.put("location", rs.getString("location"));
							jsonObj.put("keeper", rs.getString("keeper"));
							jsonObj.put("startdate", rs.getString("startdate"));
							jsonObj.put("cycle", rs.getString("cycle"));
							jsonObj.put("valid", rs.getString("valid"));
							jsonObj.put("hq", rs.getString("hq"));
							jsonObj.put("remark", rs.getString("remark"));
							jsonObj.put("status", rs.getString("status"));	
							jsonObj.put("order_num", rs.getString("order_num"));	
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
	
	
	//非测量工具明细查询
	public JSONObject detailMt(HashMap detail_m_indiHMap) {
		// TODO 自动生成方法存根
		HashMap detail_m_indiHashMap=detail_m_indiHMap;
		final JSONObject jsonObj=new JSONObject();
		final String indi_id=(String)detail_m_indiHashMap.get("indi_id");
		String sql="";
		sql="select * from v_tmindi2v_type_com where indi_id='"+indi_id+"'"; 

		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							jsonObj.put("indi_id", rs.getString("indi_id"));
							jsonObj.put("indi_num", rs.getString("indi_num"));
							jsonObj.put("mnum", rs.getString("mnum"));
							jsonObj.put("classname", rs.getString("classname"));
							jsonObj.put("name", rs.getString("name"));
							jsonObj.put("spec", rs.getString("spec"));
							jsonObj.put("diviv",rs.getString("diviv"));
							jsonObj.put("company", rs.getString("company"));
							jsonObj.put("newstatus", rs.getString("newstatus"));
							jsonObj.put("location", rs.getString("location"));
							jsonObj.put("keeper", rs.getString("keeper"));
							jsonObj.put("hq", rs.getString("hq"));
							jsonObj.put("remark", rs.getString("remark"));
							jsonObj.put("status", rs.getString("status"));	
							jsonObj.put("order_num", rs.getString("order_num"));	
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
	
	
	//刀具明细查询
	public JSONObject detailC(HashMap viewusrMap) {
		// TODO 自动生成方法存根
		HashMap viewusrHashMap=viewusrMap;
		final JSONObject jsonObj=new JSONObject();
		final String id=(String)viewusrHashMap.get("id");
		String sql="";
		sql="select * from V_CUTTER2COM where id='"+id+"'"; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
							decimalformat.setMaximumFractionDigits(2);		
							jsonObj.put("id", rs.getString("id"));
							jsonObj.put("mnum", rs.getString("mnum"));
							jsonObj.put("pclass", rs.getString("pclass"));
							jsonObj.put("bclass", rs.getString("bclass"));
							jsonObj.put("iso_num", rs.getString("iso_num"));
							jsonObj.put("name",rs.getString("name"));
							jsonObj.put("nc_count_in", rs.getString("nc_count_in"));
							jsonObj.put("uc_count_in", rs.getString("uc_count_in"));
							jsonObj.put("gc_count_in", rs.getString("gc_count_in"));
							jsonObj.put("mini_qs", rs.getString("mini_qs"));							
							jsonObj.put("suit_mate1", rs.getString("suit_mate1"));
							jsonObj.put("suit_mate2", rs.getString("suit_mate2"));
							jsonObj.put("suit_mate3", rs.getString("suit_mate3"));
							jsonObj.put("use_freq", rs.getString("use_freq"));
							jsonObj.put("rank_angle", rs.getString("rank_angle"));
							jsonObj.put("c_mate", rs.getString("c_mate"));
							jsonObj.put("coat_mate",rs.getString("coat_mate"));
							jsonObj.put("e_diam", rs.getString("e_diam"));
							jsonObj.put("h_diam", rs.getString("h_diam"));
							jsonObj.put("e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
							jsonObj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
							jsonObj.put("tee_count", rs.getString("tee_count"));
							jsonObj.put("s_ang", rs.getString("s_ang"));
							jsonObj.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
							jsonObj.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
							jsonObj.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
							jsonObj.put("e_count",rs.getString("e_count"));
							jsonObj.put("t_form", rs.getString("t_form"));
							jsonObj.put("c_way", rs.getString("c_way"));
							jsonObj.put("t_type", rs.getString("t_type"));
							jsonObj.put("spec", rs.getString("spec"));
							jsonObj.put("remark", rs.getString("remark"));
							jsonObj.put("type", rs.getString("type"));
							jsonObj.put("series_num", rs.getString("series_num"));
							jsonObj.put("order_num", rs.getString("order_num"));						
							jsonObj.put("hq", rs.getString("hq"));
							jsonObj.put("companyid",rs.getString("companyid"));
							jsonObj.put("company",rs.getString("company"));
							jsonObj.put("location", rs.getString("location"));	
							jsonObj.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));	
							jsonObj.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));		
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
	
//	查询已申请但未批准的高质新测量工具
	public JSONObject getQuerymt(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final String gleader_num="'"+(String)queryMap.get("gleader_num")+"'";
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_mt2hqmmt where trim(gleader_approve)='未处理' and gleader_num="+gleader_num+" and "+(String)queryHashMap.get("filter");
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
		sql="select * from v_mt2hqmmt where trim(gleader_approve)='未处理' and gleader_num="+gleader_num+" order by apply_time DESC";  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("indi_id", rs.getString("indi_id"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("gleader_num", rs.getString("gleader_num"));
							obj.put("status",rs.getString("status"));
							obj.put("apply_num",rs.getString("apply_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("realname",rs.getString("realname"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("drawing_num", rs.getString("drawing_num"));
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
	
//	判断特殊借出时同一时间段内这些待审批刀具是否被处理
	public boolean checkCut(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String apply_nums=(String)checkHMap.get("apply_nums");
		String counts=(String)checkHMap.get("count");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_cut2hqapply where trim(gleader_approve)='未处理' and apply_num in("+apply_nums+")";  
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
	
//	判断特殊借出时同一时间段内这些待审批测量工具是否被处理
	public boolean checkCt(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String apply_nums=(String)checkHMap.get("apply_nums");
		String counts=(String)checkHMap.get("count");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_ct2hqapply where trim(gleader_approve)='未处理' and apply_num in("+apply_nums+")";  
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
	
//	判断特殊借出时同一时间段内这些待审批量具是否被处理
	public boolean checkM(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String apply_nums=(String)checkHMap.get("apply_nums");
		String counts=(String)checkHMap.get("count");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_m2hqmmt where trim(gleader_approve)='未处理' and apply_num in("+apply_nums+")";  
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
	
//	判断特殊借出时同一时间段内这些待审批测量工具是否被处理
	public boolean checkMt(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String apply_nums=(String)checkHMap.get("apply_nums");
		String counts=(String)checkHMap.get("count");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_mt2hqmmt where trim(gleader_approve)='未处理' and apply_num in("+apply_nums+")";  
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
	
	
	//高质刀具批准
	public boolean appCut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)appcutHMap.get("apply_nums");
		final String gleader_num=(String)appcutHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hq_c_apply_record set gleader_approve=?,gleader_time=?,gleader_num=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"批准");
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,gleader_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	高质非测量工具批准
	public boolean appCt(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)appcutHMap.get("apply_nums");
		final String gleader_num=(String)appcutHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hq_ct_apply_record set gleader_approve=?,gleader_time=?,gleader_num=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"批准");
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,gleader_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
//	高质量具批准
	public boolean appMmt(HashMap appmmtHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)appmmtHMap.get("apply_nums");
		final String gleader_num=(String)appmmtHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hqm_bapply set gleader_approve=?,approve_time=?,gleader_num=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"批准");
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,gleader_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	高质测量工具批准
	public boolean appMt(HashMap appmmtHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)appmmtHMap.get("apply_nums");
		final String gleader_num=(String)appmmtHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hqmt_bapply set gleader_approve=?,approve_time=?,gleader_num=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"批准");
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,gleader_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	高质刀具拒绝批准
	public boolean refCut(HashMap refcutHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)refcutHMap.get("apply_nums");
		final String gleader_num=(String)refcutHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hq_c_apply_record set gleader_approve=?,gleader_time=?,gleader_num=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"拒绝");
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,gleader_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	高质非测量工具拒绝批准
	public boolean refCt(HashMap refcutHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)refcutHMap.get("apply_nums");
		final String gleader_num=(String)refcutHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hq_ct_apply_record set gleader_approve=?,gleader_time=?,gleader_num=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"拒绝");
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,gleader_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	高质量具拒绝批准
	public boolean refMmt(HashMap refmmtHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)refmmtHMap.get("apply_nums");
		final String gleader_num=(String)refmmtHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hqm_bapply set gleader_approve=?,approve_time=?,gleader_num=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"拒绝");
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,gleader_num);
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	高质测量工具拒绝批准
	public boolean refMt(HashMap refmmtHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)refmmtHMap.get("apply_nums");
		final String gleader_num=(String)refmmtHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hqmt_bapply set gleader_approve=?,approve_time=?,gleader_num=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"拒绝");
							pstmts[0].setDate(2,curDate);
							pstmts[0].setString(3,gleader_num);
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
