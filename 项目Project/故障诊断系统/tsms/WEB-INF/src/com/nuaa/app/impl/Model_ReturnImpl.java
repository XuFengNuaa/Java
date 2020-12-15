package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Model_Return;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class Model_ReturnImpl implements Model_Return{
	public JSONObject queryAllModel_Br(HashMap hashmap) {
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		
		String sql = "select count(*) as ct from t_model_br_record where " + filter;
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
					try {
						obj.put("totalProperty",rs.getString("ct"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("****************************");
		String sql1 = "select * from  t_model_br_record  where "+filter+" order by "+ order;
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
						final JSONObject json = new JSONObject();
						try {
							json.put("id",rs.getString("id"));
							json.put("inid",rs.getString("inid"));
							json.put("modelnum",rs.getString("modelnum"));
							json.put("outcount",rs.getString("outcount"));
							json.put("outdate",rs.getString("outdate"));
							json.put("getperson",rs.getString("getperson"));
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String modelnum = rs.getString("modelnum");
						String sql = "select name from t_model where modelnum = '"+modelnum+"'";
						DbUtil.execute(sql,new IResultSetProcessor(){
							public void process(ResultSet rs) throws SQLException {
								while(rs.next()){
									try {
										json.put("name",rs.getString("name"));
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
						jsonarray.put(json);
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj.put("filedata",jsonarray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public String modelReturn_Record(final HashMap hashmap) {
		final String outid = (String) hashmap.get("outid");
		final String state = (String) hashmap.get("state");
		final String modelnum = (String) hashmap.get("modelnum");
		final String returncount = (String) hashmap.get("returncount");
		final String returndate = (String) hashmap.get("returndate");
		final String returnperson = (String) hashmap.get("returnperson");
		final String id = UUID.randomUUID().toString();
		String []sqls = new String[1];
		sqls[0]= "insert into T_MODEL_RE_RECORD (id,outid,modelnum,returncount,returndate,returnperson) values(?,?,?,?,to_date(?,'yyyy/mm/dd'),?)";
		try {
			DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(
						PreparedStatement[] pstmts)
						throws SQLException {
						pstmts[0].setString(1,id);
					if(hashmap.get("outid")!=null){
						pstmts[0].setString(2,outid);//userid
					}else{
						pstmts[0].setString(2," ");//userid
					}
					if(hashmap.get("modelnum")!=null){
						pstmts[0].setString(3,modelnum);//username
					}else{
						pstmts[0].setString(3," ");//username
					}
					if(hashmap.get("returncount")!=null){
						pstmts[0].setString(4,returncount);//pwd
					}else{
						pstmts[0].setString(4," ");//pwd
					}
					if(hashmap.get("returndate")!=null){
						pstmts[0].setString(5,returndate);//email
					}else{
						pstmts[0].setString(5," ");//email
					}
					if(hashmap.get("returnperson")!=null){
						pstmts[0].setString(6,returnperson);//mobilephone
					}else{
						pstmts[0].setString(6," ");//mobilephone
					}
					pstmts[0].addBatch();
					
				}
			});
			
			if("over".equals(state)){
				String []sql = new String[1];
				sql[0] = "delete from t_model_br_record where modelnum='"+modelnum+"' and id = '"+outid+"'";
				DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)
							throws SQLException {
						pstmts[0].addBatch();	
					}
				});
			}else if("noover".equals(state)){
				String []sql1=new String[1];
				sql1[0]="update t_model_br_record set outcount=outcount-"+Integer.parseInt(returncount)+"where modelnum='"+modelnum+"'and id = '"+outid+"'";
				try {
					DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
						public void process(PreparedStatement[] pstmt)	throws SQLException {																				
							pstmt[0].addBatch();
						}										
					});										
				}catch (SQLException e) {
				// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			return "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
	}
	
}
