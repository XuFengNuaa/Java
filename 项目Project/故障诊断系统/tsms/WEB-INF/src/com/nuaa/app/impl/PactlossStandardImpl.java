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

import com.nuaa.app.PactlossStandard;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.Logger;

import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class PactlossStandardImpl implements PactlossStandard  {
	
	public JSONObject getQueryFile(HashMap queryMap){
	
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		sql="select  count(*) as ct  from  (select * from v_ct2com ) where "+(String)queryHashMap.get("filter");
		Logger.debug(sql);
		final String[] count=new String[1];
		count[0]="";
		try {			
			DbUtil.execute(sql, new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO
							e.printStackTrace();
						}
					}
				}
			});
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		//Logger.debug(jsonObj.toString());
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		String filter=(String)queryHashMap.get("filter");///////
		String order=(String)queryHashMap.get("order");
		System.out.println("filter............"+filter);
		System.out.println("order...................."+order);
		sql="select * from (select  *  from  (select  *  from  (select * from  v_ct2com ) where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select  *  from  (select * from  v_ct2com ) where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order;			
		System.out.println("sql="+sql);
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
							obj.put("spec", rs.getString("spec"));
							obj.put("company", rs.getString("company"));
							obj.put("location", rs.getString("location"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("hq", rs.getString("hq"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("remark", rs.getString("remark"));
							obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}
	
	

	public boolean modifyFile(HashMap addMap) {
		// TODO 自动生成方法存根
		boolean result = false ;
		HashMap addHashMap = addMap;
		final String lt_num = UUID.randomUUID().toString();  
		final String[] pa = new String[3];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		pa[0] = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");
		pa[2] = (String) addHashMap.get("pa[2]");
		//pa[3] = (String) addHashMap.get("pa[3]");
		
		Calendar calendar = Calendar.getInstance();	    
	    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
	    final String now = form.format(calendar.getTime())  ;
	    
		String[] sql2 = new String[1];	
		sql2[0] = "insert into t_ct_lt_apply_record(LT_NUM,TYPE_ID,COUNT,PL_NUM,LT,APPLY_TIME,LEADER_APPROVE)  values(?,?,?,?,?,?,?)";
		try {
			DbUtil.executeBatchs(sql2,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmt)	throws SQLException {			
					pstmt[0].setString(1,lt_num);	
					pstmt[0].setString(2,pa[0]);
					pstmt[0].setString(3,pa[1]);		
					pstmt[0].setString(4,"store");	
					pstmt[0].setString(5,"正常报废");	
					pstmt[0].setDate(6,java.sql.Date.valueOf(now));	
					pstmt[0].setString(7,"未处理");																						
					pstmt[0].addBatch();
				}										
			});
			result=true;
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
			result=false;
		}
		if(result){
		String sqls = "update  t_ct set uct_count_in =uct_count_in-"+pa[1]+" where id='" + pa[0] + "'";
		Logger.debug(sqls);
			try {
				DbUtil.execute(sqls,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {	
					}
				});
					result=true;
				} catch (SQLException e) {
					e.printStackTrace();
					result=false;
				} 
		}
				
	return result;  
	}

	
  public String modify(HashMap modyMap){
	    String result;
		HashMap addHashMap = modyMap;
		final String[] pa = new String[12];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		pa[0] = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");
		pa[2] = (String) addHashMap.get("pa[2]");
		pa[3] = (String) addHashMap.get("pa[3]");
		pa[4] = (String) addHashMap.get("pa[4]");
		pa[5] = (String) addHashMap.get("pa[5]");
		pa[6] = (String) addHashMap.get("pa[6]");
		pa[7] = (String) addHashMap.get("pa[7]");
		pa[8] = (String) addHashMap.get("pa[8]");
		pa[9] = (String) addHashMap.get("pa[9]");
		pa[10] = (String) addHashMap.get("pa[10]");
		pa[11] = (String) addHashMap.get("pa[12]");
		String[] sqls = new String[1];
		sqls[0] = "update  t_ct set " +
				"mnum=?,pclass=?,bclass=?,name=?,spec=?,companyid=?,location=?," +
				"mini_qs=?,hq=?," +
				"remark=?,order_num=?  where id='" + pa[10] + "'";
				try {
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(PreparedStatement[] pstmts)
										throws SQLException {
									pstmts[0].setString(1, pa[0]);
									pstmts[0].setString(2, pa[1]);
									pstmts[0].setString(3, pa[2]);
									pstmts[0].setString(4, pa[3]);
									pstmts[0].setString(5, pa[4]);
									pstmts[0].setString(6, pa[5]);
									pstmts[0].setString(7, pa[6]);
									pstmts[0].setString(8, pa[7]);
									pstmts[0].setString(9, pa[8]);
									pstmts[0].setString(10, pa[9]);
									pstmts[0].setString(11, pa[11]);
									//pstmts[0].setString(13, pa[10]);
									/*pstmts[0].setString(10, pa[10]);
									pstmts[0].setString(11, process[1]);*/
									pstmts[0].addBatch();
								}
							});
					result="true";
					
				} catch (SQLException e) {
					e.printStackTrace();
					result="false";
				}  
	return result;  
  }

}