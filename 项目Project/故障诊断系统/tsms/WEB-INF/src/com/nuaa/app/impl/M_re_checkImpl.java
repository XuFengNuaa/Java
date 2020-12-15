package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.M_re_check;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class M_re_checkImpl implements M_re_check{
	public JSONObject getQuerym_re_check(HashMap requeryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=requeryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  v_mtest2v_indi_typecom where "+(String)queryHashMap.get("filter");
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
		
		sql="select * from (select * from  (select * from v_mtest2v_indi_typecom where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_mtest2v_indi_typecom where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("indi_id", rs.getString("indi_id"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("type_id", rs.getString("type_id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("diviv",rs.getString("diviv"));
							obj.put("company", rs.getString("company"));							
							obj.put("location", rs.getString("location"));							
							obj.put("startdate", rs.getString("startdate"));
							obj.put("cycle", rs.getString("cycle"));
							obj.put("valid", rs.getString("valid"));
							obj.put("hq", rs.getString("hq"));							
							obj.put("status", rs.getString("status"));							
							obj.put("test_num", rs.getString("test_num"));							
							obj.put("stuff_num", rs.getString("stuff_num"));
							obj.put("s_time", rs.getString("s_time"));
							obj.put("test_department", rs.getString("test_department"));
							obj.put("r_time", rs.getString("r_time"));							
							obj.put("test_id", rs.getString("test_id"));	
							obj.put("group_num", rs.getString("group_num"));
							obj.put("realname", rs.getString("realname"));
							obj.put("keeper", rs.getString("keeper"));
							obj.put("apply_num", rs.getString("apply_num"));							
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
	
	//量具送检正常返库
	public boolean re_check(HashMap re_check_HMap) {
		// TODO 自动生成方法存根
		final HashMap re_checkHashMap=re_check_HMap;		
		String sql ="";	
	    final String sum=(String)re_checkHashMap.get("sum");
	    final String un_test_id=(String)re_checkHashMap.get("test_id");
	    final String un_indi_id=(String)re_checkHashMap.get("indi_id");
	    final String un_validdate=(String)re_checkHashMap.get("validdate");
	    final String r_time=(String)re_checkHashMap.get("r_time");	
	    final String keeper=(String)re_checkHashMap.get("keeper");	
	    final String []indi_id=un_indi_id.split(",");
	    final String []validdate=un_validdate.split(",");
	    final String []keeper_indi=keeper.split(",");	
		Logger.debug(sql);
		//final JSONArray idArray=new JSONArray();			   
			try {
				String []sqls = new String[2];
				for(int i=0;i<Integer.parseInt(sum);i++){
				String status = keeper_indi[i].replace("'","");
				String status_now = status.equals("store") ?"在库":"借出";				
					
				sqls[0] = "update T_M_INDI set valid="+validdate[i]+",status='"+status_now+"' where indi_id="+indi_id[i]+"";
				sqls[1]= "update T_TEST_RECORD set r_time='"+r_time+"',r_status='正常返库',r_time_date=to_date('"+r_time+"','yyyy-mm-dd')  where test_id in("+un_test_id+")";
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {				  
							pstmts[0].addBatch();
							
							pstmts[1].addBatch();
					}										
				  });
				}				
					return true;				
			}catch (SQLException e) {
				e.printStackTrace();
				return false;			
		}
	}
	
	//量具返库正常报废
	public boolean delete_re_check(HashMap delete_re_check_HMap) {
		// TODO 自动生成方法存根
		final HashMap delete_re_checkHashMap=delete_re_check_HMap;		
		String sql ="";	
	    final String sum=(String)delete_re_checkHashMap.get("sum");
	    final String un_test_id=(String)delete_re_checkHashMap.get("test_id");
	    final String un_indi_id=(String)delete_re_checkHashMap.get("indi_id");	  
	    final String un_type_id=(String)delete_re_checkHashMap.get("type_id");	
	    final String r_time=(String)delete_re_checkHashMap.get("r_time");
	    final String un_keeper=(String)delete_re_checkHashMap.get("keeper");
	    final String un_apply_num=(String)delete_re_checkHashMap.get("apply_num");	  
	    final String un_lt_id=(String)delete_re_checkHashMap.get("lt_id");	
	    final String un_worker_num=(String)delete_re_checkHashMap.get("lt_worker");
	    final String un_hq=(String)delete_re_checkHashMap.get("lt_hq");	
	    final String num=(String)delete_re_checkHashMap.get("j");	
	    
	    final String []indi_id=un_indi_id.split(",");
	    final String []type_id=un_type_id.split(",");
	    final String []keeper=un_keeper.split(",");
	   
	    final String []apply_num=un_apply_num.split(",");
	    final String []lt_id=un_lt_id.split(",");
	    final String []worker_num=un_worker_num.split(",");
	    final String []hq=un_hq.split(",");
	    
	    
	    Calendar calendar = Calendar.getInstance();	    
	    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
	    final String now = form.format(calendar.getTime())  ;
	    //final String []indi_id=un_indi_id.split(",");	  
		Logger.debug(sql);
		//final JSONArray idArray=new JSONArray();			   
			try {
				String []sqls = new String[3];
				String []sql1 = new String[1];
								
				sqls[0] ="update T_M_INDI set status='报废',keeper='store',apply_num='' where indi_id in("+un_indi_id+")";   
				sqls[1]= "update T_TEST_RECORD set r_time='"+r_time+"',r_time_date=to_date('"+r_time+"','yyyy-mm-dd'),r_status='报废'  where test_id in("+un_test_id+")";				
				sqls[2] ="insert into t_m_lt_record(LT_NUM,INDI_ID,LIABLE_STUFF_NUM,OT,APPLY_TIME,OVER,TYPE_ID,leader_approve) values(?,?,?,?,?,?,?,?)"; 
				sql1[0]="insert into t_m_br_record(apply_num,worker_num,indi_id,ot,time,br_num,hq) values(?,?,?,?,?,?,?)";
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {				  
						   pstmts[0].addBatch();
						
						   pstmts[1].addBatch();
						
						for(int i=0;i<Integer.parseInt(sum);i++){							
							final int j = i;	
							String lt_num = UUID.randomUUID().toString();  
							pstmts[2].setString(1,lt_num);
							pstmts[2].setString(2,indi_id[j].replace("'",""));							
							pstmts[2].setString(3,keeper[j].replace("'",""));	
							pstmts[2].setString(4,"正常报废");//OT									
							pstmts[2].setDate(5,java.sql.Date.valueOf(now));													
							pstmts[2].setString(6,"未处理");								
							pstmts[2].setString(7,type_id[j].replace("'",""));//telephone
							pstmts[2].setString(8,"未处理");	
						    pstmts[2].addBatch();
						}
					}
										
				});	
				
				DbUtil.executeBatchs(sql1,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmt)
							throws SQLException {		  
						 
						if(Integer.parseInt(num)!=0){
						for(int k=0;k<Integer.parseInt(num);k++){							
							final int j = k;	
							String br_num = UUID.randomUUID().toString();  
							pstmt[0].setString(1,apply_num[k].replace("'",""));
							pstmt[0].setString(2,worker_num[j].replace("'",""));							
							pstmt[0].setString(3,lt_id[j].replace("'",""));	
							pstmt[0].setString(4,"1");//OT									
							pstmt[0].setDate(5,java.sql.Date.valueOf(now));													
							pstmt[0].setString(6,br_num);								
							pstmt[0].setString(7,hq[j].replace("'",""));//telephone							
							pstmt[0].addBatch();
						}
					   }	
					}
										
				});	
					return true;				
			}catch (SQLException e) {
				e.printStackTrace();
				return false;
			
		}
		
	}
	
	//量具返库非正常报废
	public boolean undelete_re_check(HashMap undelete_re_check_HMap) {
		// TODO 自动生成方法存根
		final HashMap delete_re_checkHashMap=undelete_re_check_HMap;		
		String sql ="";	
	    final String sum=(String)delete_re_checkHashMap.get("sum");
	    final String un_test_id=(String)delete_re_checkHashMap.get("test_id");
	    final String un_indi_id=(String)delete_re_checkHashMap.get("indi_id");	  
	    final String un_type_id=(String)delete_re_checkHashMap.get("type_id");	
	    final String r_time=(String)delete_re_checkHashMap.get("r_time");
	    final String un_keeper=(String)delete_re_checkHashMap.get("keeper");
	    final String un_apply_num=(String)delete_re_checkHashMap.get("apply_num");	
	    final String un_reason=(String)delete_re_checkHashMap.get("reason");
	    final String un_hq=(String)delete_re_checkHashMap.get("lt_hq");	
	    
	    
	    final String []indi_id=un_indi_id.split(",");
	    final String []type_id=un_type_id.split(",");
	    final String []keeper=un_keeper.split(",");
	   
	    final String []apply_num=un_apply_num.split(",");
	    final String []hq=un_hq.split(",");
	    
	    
	    Calendar calendar = Calendar.getInstance();	    
	    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
	    final String now = form.format(calendar.getTime())  ;
	    //final String []indi_id=un_indi_id.split(",");	  
		Logger.debug(sql);
		//final JSONArray idArray=new JSONArray();			   
			try {
				String []sqls = new String[4];				
								
				sqls[0] ="update T_M_INDI set status='报废',keeper='store',apply_num='' where indi_id in("+un_indi_id+")";   
				sqls[1]= "update T_TEST_RECORD set r_time='"+r_time+"',r_time_date=to_date('"+r_time+"','yyyy-mm-dd'),r_status='报废'  where test_id in("+un_test_id+")";				
				sqls[2] ="insert into t_m_lt_record(LT_NUM,INDI_ID,LIABLE_STUFF_NUM,OT,APPLY_TIME,OVER,TYPE_ID,leader_approve,reason) values(?,?,?,?,?,?,?,?,?)"; 
				sqls[3]="insert into t_m_br_record(apply_num,worker_num,indi_id,ot,time,br_num,hq) values(?,?,?,?,?,?,?)";
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {				  
						   pstmts[0].addBatch();
						
						   pstmts[1].addBatch();
						
						for(int i=0;i<Integer.parseInt(sum);i++){							
							final int j = i;	
							String lt_num = UUID.randomUUID().toString();  
							pstmts[2].setString(1,lt_num);
							pstmts[2].setString(2,indi_id[j].replace("'",""));							
							pstmts[2].setString(3,keeper[j].replace("'",""));	
							pstmts[2].setString(4,"非正常报废");//OT									
							pstmts[2].setDate(5,java.sql.Date.valueOf(now));													
							pstmts[2].setString(6,"未处理");								
							pstmts[2].setString(7,type_id[j].replace("'",""));//telephone
							pstmts[2].setString(8,"未处理");	
							pstmts[2].setString(9,un_reason);	
						    pstmts[2].addBatch();
						}
						
						for(int k=0;k<Integer.parseInt(sum);k++){							
							final int j = k;	
							String br_num = UUID.randomUUID().toString();  
							pstmts[3].setString(1,apply_num[k].replace("'",""));
							pstmts[3].setString(2,keeper[j].replace("'",""));							
							pstmts[3].setString(3,indi_id[j].replace("'",""));	
							pstmts[3].setString(4,"2");//OT									
							pstmts[3].setDate(5,java.sql.Date.valueOf(now));													
							pstmts[3].setString(6,br_num);								
							pstmts[3].setString(7,hq[j].replace("'",""));//telephone							
							pstmts[3].addBatch();
						}
					}
										
				});	
				
					return true;				
			}catch (SQLException e) {
				e.printStackTrace();
				return false;
			
		}
		
	}
	
}