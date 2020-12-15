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

import com.nuaa.app.Gleader_need;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class Gleader_needImpl implements Gleader_need {

//	查询刀具需求申请
	public JSONObject getQuerycut(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final String gleader_num="'"+(String)queryMap.get("gleader_num")+"'";
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_cut2need where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") and "+(String)queryHashMap.get("filter");
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
		
		
		sql="select * from v_cut2need where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") order by apply_time DESC";  
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
							obj.put("gleader_num", rs.getString("gleader_num"));
							obj.put("count", rs.getString("count"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("drawing_num",rs.getString("drawing_num"));
							obj.put("need_num",rs.getString("need_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("realname",rs.getString("workername"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
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
	
	
//	查询非测量工具需求申请
	public JSONObject getQueryct(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final String gleader_num="'"+(String)queryMap.get("gleader_num")+"'";
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_ct2need where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") and "+(String)queryHashMap.get("filter");
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
		sql="select * from v_ct2need where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") order by apply_time DESC";  
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
							obj.put("drawing_num",rs.getString("drawing_num"));
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
							obj.put("need_num",rs.getString("need_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("realname",rs.getString("workername"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
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
	
//	查询量具需求申请
	public JSONObject getQuerym(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final String gleader_num="'"+(String)queryMap.get("gleader_num")+"'";
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_m2need where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") and "+(String)queryHashMap.get("filter");
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
		sql="select * from v_m2need where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") order by apply_time DESC";  
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
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("gleader_num", rs.getString("gleader_num"));
							obj.put("status",rs.getString("status"));
							obj.put("count",rs.getString("count"));
							obj.put("need_num",rs.getString("need_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("realname",rs.getString("workername"));
							obj.put("drawing_num",rs.getString("drawing_num"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
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
	
	//量具明细查询
	public JSONObject detailM(HashMap detail_m_indiHMap) {
		// TODO 自动生成方法存根
		HashMap detail_m_indiHashMap=detail_m_indiHMap;
		final JSONObject jsonObj=new JSONObject();
		final String type_id=(String)detail_m_indiHashMap.get("type_id");
		String sql="";
		sql="select * from t_m_type where id='"+type_id+"'"; 

		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							jsonObj.put("mnum", rs.getString("mnum"));
							jsonObj.put("classname", rs.getString("classname"));
							jsonObj.put("name", rs.getString("name"));
							jsonObj.put("spec", rs.getString("spec"));
							jsonObj.put("diviv",rs.getString("diviv"));
							jsonObj.put("remark", rs.getString("remark"));
							jsonObj.put("use_freq",rs.getString("use_freq"));
							jsonObj.put("mini_qs", rs.getString("mini_qs"));
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
		final String type_id=(String)detail_m_indiHashMap.get("type_id");
		String sql="";
		sql="select * from t_mt_type where id='"+type_id+"'"; 

		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							jsonObj.put("mnum", rs.getString("mnum"));
							jsonObj.put("classname", rs.getString("classname"));
							jsonObj.put("name", rs.getString("name"));
							jsonObj.put("spec", rs.getString("spec"));
							jsonObj.put("diviv",rs.getString("diviv"));
							jsonObj.put("remark", rs.getString("remark"));
							jsonObj.put("use_freq",rs.getString("use_freq"));
							jsonObj.put("mini_qs", rs.getString("mini_qs"));	
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
	
//	查询测量工具需求申请
	public JSONObject getQuerymt(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final String gleader_num="'"+(String)queryMap.get("gleader_num")+"'";
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_mt2need where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") and "+(String)queryHashMap.get("filter");
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
		sql="select * from v_mt2need where trim(gleader_approve)='未处理' and group_num in (select group_num from t_user where stuff_num="+gleader_num+") order by apply_time DESC";  
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
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("gleader_num", rs.getString("gleader_num"));
							obj.put("status",rs.getString("status"));
							obj.put("count",rs.getString("count"));
							obj.put("need_num",rs.getString("need_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("realname",rs.getString("workername"));
							obj.put("drawing_num",rs.getString("drawing_num"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
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
	
//	判断特殊借出时同一时间段内这些待审批刀具是否被处理
	public boolean checkCut(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String need_nums=(String)checkHMap.get("need_nums");
		String counts=(String)checkHMap.get("count");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_cut2need where trim(gleader_approve)='未处理' and need_num in("+need_nums+")";  
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
		String need_nums=(String)checkHMap.get("need_nums");
		String counts=(String)checkHMap.get("count");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_ct2need where trim(gleader_approve)='未处理' and need_num in("+need_nums+")";  
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
		String need_nums=(String)checkHMap.get("need_nums");
		String counts=(String)checkHMap.get("count");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_m2need where trim(gleader_approve)='未处理' and need_num in("+need_nums+")";  
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
		String need_nums=(String)checkHMap.get("need_nums");
		String counts=(String)checkHMap.get("count");
		final int Count = Integer.parseInt(counts);
		sql="select * from v_mt2need where trim(gleader_approve)='未处理' and need_num in("+need_nums+")";  
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
	
	
	//刀具需求批准
	public boolean appCut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String need_nums=(String)appcutHMap.get("need_nums");
		final String gleader_num=(String)appcutHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_need set gleader_approve=?,gleader_time=?,gleader_num=? where need_num in("+need_nums+")";
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
	
//	非测量工具需求批准
	public boolean appCt(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String need_nums=(String)appcutHMap.get("need_nums");
		final String gleader_num=(String)appcutHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_ct_need set gleader_approve=?,gleader_time=?,gleader_num=? where need_num in("+need_nums+")";
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
	
	
//	量具需求批准
	public boolean appMmt(HashMap appmmtHMap) {
		// TODO 自动生成方法存根
		final String need_nums=(String)appmmtHMap.get("need_nums");
		final String gleader_num=(String)appmmtHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_m_need set gleader_approve=?,gleader_time=?,gleader_num=? where need_num in("+need_nums+")";
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
	
//	测量工具需求批准
	public boolean appMt(HashMap appmmtHMap) {
		// TODO 自动生成方法存根
		final String need_nums=(String)appmmtHMap.get("need_nums");
		final String gleader_num=(String)appmmtHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_mt_need set gleader_approve=?,gleader_time=?,gleader_num=? where need_num in("+need_nums+")";
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
	
//	拒绝刀具需求
	public boolean refCut(HashMap refcutHMap) {
		// TODO 自动生成方法存根
		final String need_nums=(String)refcutHMap.get("need_nums");
		final String gleader_num=(String)refcutHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_c_need set gleader_approve=?,gleader_time=?,gleader_num=? where need_num in("+need_nums+")";
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
	
//	拒绝非测量工具需求
	public boolean refCt(HashMap refcutHMap) {
		// TODO 自动生成方法存根
		final String need_nums=(String)refcutHMap.get("need_nums");
		final String gleader_num=(String)refcutHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_ct_need set gleader_approve=?,gleader_time=?,gleader_num=? where need_num in("+need_nums+")";
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
	
//	拒绝量具需求
	public boolean refMmt(HashMap refmmtHMap) {
		// TODO 自动生成方法存根
		final String need_nums=(String)refmmtHMap.get("need_nums");
		final String gleader_num=(String)refmmtHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_m_need set gleader_approve=?,gleader_time=?,gleader_num=? where need_num in("+need_nums+")";
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
	
//	拒绝测量工具需求
	public boolean refMt(HashMap refmmtHMap) {
		// TODO 自动生成方法存根
		final String need_nums=(String)refmmtHMap.get("need_nums");
		final String gleader_num=(String)refmmtHMap.get("gleader_num");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_mt_need set gleader_approve=?,gleader_time=?,gleader_num=? where need_num in("+need_nums+")";
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
