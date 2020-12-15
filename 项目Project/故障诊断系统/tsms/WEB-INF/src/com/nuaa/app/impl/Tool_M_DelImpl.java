package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.UUID;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.Tool_M_Del;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class Tool_M_DelImpl implements Tool_M_Del{
	public JSONObject getQuerytm_delete(HashMap deletequeryMap) {
		// TODO 自动生成方法存根
		HashMap query_tm_indiHashMap=deletequeryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  v_tmindi2v_type_com where "+(String)query_tm_indiHashMap.get("filter");
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
		int start=Integer.parseInt((String)query_tm_indiHashMap.get("start"));
		int limit=Integer.parseInt((String)query_tm_indiHashMap.get("limit"));
		
		String filter=(String)query_tm_indiHashMap.get("filter");
		String order=(String)query_tm_indiHashMap.get("order");
		sql="select * from (select * from  (select * from v_tmindi2v_type_com where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_tmindi2v_type_com where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("indi_id", rs.getString("indi_id"));
							obj.put("type_id", rs.getString("type_id"));							
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("diviv",rs.getString("diviv"));
							obj.put("company", rs.getString("company"));
							obj.put("newstatus", rs.getString("newstatus"));
							obj.put("location", rs.getString("location"));
							obj.put("keeper", rs.getString("keeper"));
							obj.put("hq", rs.getString("hq"));
							obj.put("remark", rs.getString("remark"));
							obj.put("realname", rs.getString("realname"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("status", rs.getString("status"));
							jsonArray.put(obj);
							
						}	
							jsonObj.put("indi_filedata", jsonArray);
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
		
	public String tm_delete(HashMap tm_delete_HMap) {
		// TODO 自动生成方法存根
		final HashMap tm_deleteHashMap=tm_delete_HMap;		
		final JSONObject jsonObj=new JSONObject();
		
	    final String sum=(String)tm_deleteHashMap.get("sum");	   
	    final String un_indi_id=(String)tm_deleteHashMap.get("indi_id");	
	    final String un_type_id=(String)tm_deleteHashMap.get("type_id");
	
	    final String []indi_id=un_indi_id.split(",");
	    final String []type_id=un_type_id.split(",");
	    
	    Calendar calendar = Calendar.getInstance();	    
	    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
	    final String now = form.format(calendar.getTime())  ;
	    
	    final String []result = new String[1];
	    result[0]="true";
	    			
		String sql="";
		sql="select status from T_MT_INDI where indi_id in("+un_indi_id+")";  

		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							if(rs.getString("status").equals("报废")){
								result[0]="double";										
							   }
							}	
						  if(result[0].equals("true")){
							  try {
								String []sqls = new String[1];								
								sqls[0] ="update T_MT_INDI set status='报废' where indi_id in("+un_indi_id+")";  
								Logger.debug(sqls[0]);
								DbUtil.executeBatchs(sqls,
										new IArrayPreparedStatementProcessor() {
											public void process(
											PreparedStatement[] pstmts)
											throws SQLException {				  
										pstmts[0].addBatch();						
									}										
								 });
								String []sql1 = new String[1];
								for(int i=0;i<Integer.parseInt(sum);i++){	
							      final int j = i;
								  final String lt_num = UUID.randomUUID().toString();  
								  sql1[0] ="insert into t_mt_lt_record(LT_NUM,INDI_ID,LIABLE_STUFF_NUM,OT,APPLY_TIME,OVER,TYPE_ID,leader_approve) values(?,?,?,?,?,?,?,?)";  	
								  Logger.debug(sql1[0]);
								  DbUtil.executeBatchs(sql1,
										new IArrayPreparedStatementProcessor() {
											public void process(
											PreparedStatement[] pstmts)
											throws SQLException {	
												pstmts[0].setString(1,lt_num);
													pstmts[0].setString(2,indi_id[j].replace("'",""));
													System.out.println(indi_id[j].replace("'",""));
													pstmts[0].setString(3,"store");	
													pstmts[0].setString(4,"正常报废");//OT									
													pstmts[0].setDate(5,java.sql.Date.valueOf(now));													
													pstmts[0].setString(6,"未处理");								
													pstmts[0].setString(7,type_id[j].replace("'",""));
													pstmts[0].setString(8,"未处理");	
												    pstmts[0].addBatch();
										
									}										
									});	
								}
							}catch (SQLException e) {
								e.printStackTrace();
								result[0]="false";
							
						}
						}
					} catch (SQLException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
						result[0]="false";
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
			result[0]="false";
		}
	    return 	result[0];
	}
	
	public JSONObject detail_tm_delete(HashMap detail_tm_delete_HMap) {
		// TODO 自动生成方法存根
		HashMap detail_tm_indiHashMap=detail_tm_delete_HMap;
		final JSONObject jsonObj=new JSONObject();
		final String indi_id=(String)detail_tm_indiHashMap.get("indi_id");
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
							jsonObj.put("order_num", rs.getString("order_num"));
							jsonObj.put("type_id", rs.getString("type_id"));
							jsonObj.put("realname", rs.getString("realname"));
							jsonObj.put("group_num", rs.getString("group_num"));
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