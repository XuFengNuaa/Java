package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Model_In;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class Model_InImpl implements Model_In{

	public JSONObject queryAllModelnum() {
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql = "select modelnum  from t_model";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("modelnum",rs.getString("modelnum"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						jsonarray.put(json);
					}
					try {
						obj.put("filedata",jsonarray);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String model_In_Record(HashMap hashmap) {
		final String modelnum = (String) hashmap.get("modelnum");
		final String incount = (String) hashmap.get("incount");
	    int incount1 = Integer.parseInt(incount);
		final String indate = (String) hashmap.get("indate");
		final String id = UUID.randomUUID().toString();
		final String []count = new String[1];
		String sql1 = "select count(*) as ct from t_model_in_record where modelnum = '" +modelnum + "'";
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						count[0] = rs.getString("ct");
					}
					
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if("0".equals(count[0])){
		String[] sql = new String[1];
		sql[0] = "insert into t_model_in_record (id,modelnum,incount,indate)values(?,?,?,to_date(?,'yyyy/mm/dd'))";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					pstmts[0].setString(1,id);
					pstmts[0].setString(2,modelnum);
					pstmts[0].setString(3, incount);
					pstmts[0].setString(4, indate);
					pstmts[0].addBatch();
					
				}
			});
			return "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		}else{
			String []sql = new String[1];
			sql[0]="update t_model_in_record set incount = incount +"+incount1+" where modelnum = '"+ modelnum+"'";
			try {
				DbUtil.executeBatchs(sql,
						new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)
									throws SQLException {												
								pstmts[0].addBatch();
							}
						});						
		}catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
			return "true";
		}
	}

}
