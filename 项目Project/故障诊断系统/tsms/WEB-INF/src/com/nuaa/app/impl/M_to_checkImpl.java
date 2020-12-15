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
import com.nuaa.app.M_to_check;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class M_to_checkImpl implements M_to_check{
	//查询所有量具
	public JSONObject getQuerym_to_check(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String indi_id=(String)queryHashMap.get("indi_id");
				
		String sql="";
		String sql1="";
		if(indi_id.equals("")){
		sql="select  count(*) as ct  from  v_mindi2v_mcom where "+(String)queryHashMap.get("filter")+" ";
		}else{
		sql="select  count(*) as ct  from  v_mindi2v_mcom where "+(String)queryHashMap.get("filter")+" and indi_id not in("+indi_id+") ";	
		}
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
		
		if(indi_id.equals("")){
			sql1="select * from (select * from  (select * from v_mindi2v_mcom where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_mindi2v_mcom where "+filter+"  order by "+order+" ) where rownum<="+start+") order by "+order; 
		}else{
			sql1="select * from (select * from  (select * from v_mindi2v_mcom where "+filter+" and indi_id not in("+indi_id+") order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_mindi2v_mcom where "+filter+" and indi_id not in("+indi_id+") order by "+order+" ) where rownum<="+start+") order by "+order; 	
		}
		
		Logger.debug(sql);
		Logger.debug(sql1);
		
		try {			
			DbUtil.execute(sql1,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("indi_id", rs.getString("indi_id"));
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
							obj.put("startdate", rs.getString("startdate"));
							obj.put("cycle", rs.getString("cycle"));
							obj.put("valid", rs.getString("valid"));
							obj.put("hq", rs.getString("hq"));
							obj.put("remark", rs.getString("remark"));
							obj.put("status", rs.getString("status"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("realname", rs.getString("realname"));
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
	
	//明细
	public JSONObject detail_to_check(HashMap detail_to_check_HMap) {
		// TODO 自动生成方法存根
		HashMap detail_to_checkHashMap=detail_to_check_HMap;
		final JSONObject jsonObj=new JSONObject();
		final String indi_id=(String)detail_to_checkHashMap.get("indi_id");
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
							jsonObj.put("group_num", rs.getString("group_num"));
							jsonObj.put("realname", rs.getString("realname"));
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
	
	//将某量具添加到送检单中
	public String to_check(HashMap to_check_HMap) {
		// TODO 自动生成方法存根
		final HashMap add_to_checkHashMap=to_check_HMap;		
		String sql ="";	
	//    final String sum=(String)add_to_checkHashMap.get("sum");
	    final String test_department=(String)add_to_checkHashMap.get("test_department");
	    final String test_num=(String)add_to_checkHashMap.get("test_num");
	    final String s_time=(String)add_to_checkHashMap.get("s_time");
	    final String stuff_num=(String)add_to_checkHashMap.get("stuff_num");	
	    final String test_id=(String)add_to_checkHashMap.get("test_id");
	//	final String []test_id=new String[Integer.parseInt(sum)];
		String indi_id=(String)add_to_checkHashMap.get("indi_id");
	//	final String []indi=indi_id.split(",");		
		
		Logger.debug(sql);
		//final JSONArray idArray=new JSONArray();	
		final String[] count=new String[1];		
		sql="select count(*) as ct from T_TEST_RECORD where test_num='"+test_num+"' "; 		
		try {			
				DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException{
						while(rs.next()){					
							count[0]=rs.getString("ct");
						}
					}
				});	
			}catch (SQLException e){
				e.printStackTrace();
			  }
			try {				
				if("0".equals(count[0])){
					String []sqls = new String[2];
					sqls[0]= "update T_TEST_RECORD set stuff_num='"+stuff_num+"',s_time='"+s_time+"',test_department='"+test_department+"',test_num='"+test_num+"'  where test_id in("+test_id+")";
					sqls[1]= "update T_M_INDI set status='送检' where indi_id in("+indi_id+")";
					DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() 
					         {public void process(	PreparedStatement[] pstmts)
								throws SQLException {				
				 	            pstmts[0].addBatch();
				 	            
				 	            pstmts[1].addBatch();	
					  }
					});					
					return "true";				
			}else{
				  return "double";	
			}}catch (SQLException e) {
				e.printStackTrace();
				return "Exception";			
		    }	
	}
	
	
	public JSONObject get_check(HashMap get_checkMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=get_checkMap;
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
							jsonObj.put("checkProperty",rs.getString("ct"));
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
	//	int start=Integer.parseInt((String)queryHashMap.get("start"));
	//	int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		
		String filter=(String)queryHashMap.get("filter");
		String order=(String)queryHashMap.get("order");
		
		sql="select * from v_mtest2v_indi_typecom where "+filter+" order by "+order+""; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("test_id", rs.getString("test_id"));
							obj.put("indi_id", rs.getString("indi_id"));
							obj.put("indi_num", rs.getString("indi_num"));
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
							obj.put("keeper", rs.getString("keeper"));
							obj.put("group_num", rs.getString("group_num"));
							obj.put("realname", rs.getString("realname"));
							jsonArray.put(obj);
														
						}	
							jsonObj.put("check_filedata", jsonArray);
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
	
	
	//检查当前查询结果界面中的某量具是否已被另一个库房管理员送检(若没有，则添加到送检单)
	public String examine_to_check(HashMap examine_HMap) {
		// TODO 自动生成方法存根
		HashMap examine_to_checkHashMap=examine_HMap;
		final JSONObject jsonObj=new JSONObject();		
		final String sum=(String)examine_to_checkHashMap.get("sum");
		final String indi_id=(String)examine_to_checkHashMap.get("indi_id");		
		final String []indi=indi_id.split(",");
		final String []test_id=new String[Integer.parseInt(sum)];
		
		String sql="";
		sql="select count(*) as ct  from T_TEST_RECORD where indi_id in ("+indi_id+") and test_num is null"; 
        final String []result=new String[1];
        result[0] = "true";
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						
						while(rs.next()){							
							if(Integer.parseInt(rs.getString("ct"))!=0){
								result[0]="false";								
							}						
						}
						if(result[0].equals("true")){
							try {	
								String []sqls = new String[1];
								sqls[0]= "insert into T_TEST_RECORD(test_id,indi_id) values(?,?)";
								DbUtil.executeBatchs(sqls,
										new IArrayPreparedStatementProcessor() {
											public void process(
											PreparedStatement[] pstmts)
											throws SQLException {
								for(int i=0;i<Integer.parseInt(sum);i++){
								      String end_id= indi[i].replace("'", "");
									  test_id[i]=UUID.randomUUID().toString(); //自动生成id		
											pstmts[0].setString(1,test_id[i]);
										if(indi[i]!=null){
											pstmts[0].setString(2,end_id);//userid
										}else{
											pstmts[0].setString(2," ");//userid
										}										
										pstmts[0].addBatch();
								    	}
									}
								});								
							result[0]="true";					
						}catch (SQLException e) {
							e.printStackTrace();
							result[0]="exception";							
					    }							
					  }
					} catch (SQLException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
						result[0] = "exception";
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
			result[0] = "exception";
		}
		Logger.debug(jsonObj.toString());
		return result[0];
	}
	
	
//	检查当前送检单中的某量具是否已被另一个库房管理员删除(若没有，则删除)
	public String delete(HashMap delete_HMap) {
		// TODO 自动生成方法存根
		HashMap deleteHashMap=delete_HMap;
		final JSONObject jsonObj=new JSONObject();	
	    final String test_id=(String)deleteHashMap.get("test_id");	
	    final String keeper=(String)deleteHashMap.get("keeper");	
	    final String indi_id=(String)deleteHashMap.get("indi_id");	
	    final String sum=(String)deleteHashMap.get("sum");
	  //  final String []indi = indi_id.split(",") ;
	  //  final String []indi_keeper = keeper.split(",") ;
	
		String sql="";
		sql="select count(*) as ct  from T_TEST_RECORD where indi_id in ("+indi_id+") and test_num is null"; 
        final String []result=new String[1];
        result[0] = "true";
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){							
							if(Integer.parseInt(rs.getString("ct"))<Integer.parseInt(sum)){
								result[0]="false";								
							}						
						}
						if(result[0].equals("true")){
							try {	
								String []sqls = new String[1];
								sqls[0]= "delete from T_TEST_RECORD  where test_id in("+test_id+")";
								DbUtil.executeBatchs(sqls,
										new IArrayPreparedStatementProcessor() {
											public void process(
											PreparedStatement[] pstmts)
											throws SQLException {													
										pstmts[0].addBatch();				
									  }
									});	
							result[0] = "true";			
						}catch (SQLException e) {
							e.printStackTrace();
							result[0] = "exception";		
					   }							
							
					  }
					} catch (SQLException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
						result[0] = "exception";
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
			result[0] = "exception";
		}
		Logger.debug(jsonObj.toString());
		return result[0];
	}


}