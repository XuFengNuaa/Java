package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.shikeMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class shikeModImpl implements shikeMod{

	@Override
	public JSONObject queryWeight() {
		// TODO Auto-generated method stub
		//final String feature = hashmap.get("feature").toString();
		String sql = "select * from t_shike";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("fs1", Double.parseDouble(rs.getString("fs1")));
							json.put("tm1", Double.parseDouble(rs.getString("tm1")));
							json.put("xb1", Double.parseDouble(rs.getString("xb1")));
							json.put("zj1", Double.parseDouble(rs.getString("zj1")));
							json.put("fs2", Double.parseDouble(rs.getString("fs2")));
							json.put("tm2", Double.parseDouble(rs.getString("tm2")));
							json.put("xb2", Double.parseDouble(rs.getString("xb2")));
							json.put("zj2", Double.parseDouble(rs.getString("zj2")));
							json.put("fs3", Double.parseDouble(rs.getString("fs3")));
							json.put("tm3", Double.parseDouble(rs.getString("tm3")));
							json.put("xb3", Double.parseDouble(rs.getString("xb3")));
							json.put("zj3", Double.parseDouble(rs.getString("zj3")));
							json.put("fs4", Double.parseDouble(rs.getString("fs4")));
							json.put("tm4", Double.parseDouble(rs.getString("tm4")));
							json.put("xb4", Double.parseDouble(rs.getString("xb4")));
							json.put("zj4", Double.parseDouble(rs.getString("zj4")));
							json.put("fs5", Double.parseDouble(rs.getString("fs5")));
							json.put("tm5", Double.parseDouble(rs.getString("tm5")));
							json.put("xb5", Double.parseDouble(rs.getString("xb5")));
							json.put("zj5", Double.parseDouble(rs.getString("zj5")));
						}
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
		System.out.println(json);
		return json;
	}

	@Override
	public String editWeight(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String fs1 = hashmap.get("fs1").toString();
		final String tm1 = hashmap.get("tm1").toString();
		final String xb1 = hashmap.get("xb1").toString();
		final String zj1 = hashmap.get("zj1").toString();
		final String fs2 = hashmap.get("fs2").toString();
		final String tm2 = hashmap.get("tm2").toString();
		final String xb2 = hashmap.get("xb2").toString();
		final String zj2 = hashmap.get("zj2").toString();
		final String fs3 = hashmap.get("fs3").toString();
		final String tm3 = hashmap.get("tm3").toString();
		final String xb3 = hashmap.get("xb3").toString();
		final String zj3 = hashmap.get("zj3").toString();
		final String fs4 = hashmap.get("fs4").toString();
		final String tm4 = hashmap.get("tm4").toString();
		final String xb4 = hashmap.get("xb4").toString();
		final String zj4 = hashmap.get("zj4").toString();
		final String fs5 = hashmap.get("fs5").toString();
		final String tm5 = hashmap.get("tm5").toString();
		final String xb5 = hashmap.get("xb5").toString();
		final String zj5 = hashmap.get("zj5").toString();
		String[] sql = new String[1];
		sql[0] = "update t_shike set fs1=?,tm1=?,xb1=?,zj1=?,fs2=?,tm2=?,xb2=?,zj2=?,fs3=?,tm3=?,xb3=?,zj3=?,fs4=?,tm4=?,xb4=?,zj4=?,fs5=?,tm5=?,xb5=?,zj5=?";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,fs1);
					pstmts[0].setString(2,tm1);
					pstmts[0].setString(3,xb1);
					pstmts[0].setString(4,zj1);
					pstmts[0].setString(5,fs2);
					pstmts[0].setString(6,tm2);
					pstmts[0].setString(7,xb2);
					pstmts[0].setString(8,zj2);
					pstmts[0].setString(9,fs3);
					pstmts[0].setString(10,tm3);
					pstmts[0].setString(11,xb3);
					pstmts[0].setString(12,zj3);
					pstmts[0].setString(13,fs4);
					pstmts[0].setString(14,tm4);
					pstmts[0].setString(15,xb4);
					pstmts[0].setString(16,zj4);
					pstmts[0].setString(17,fs5);
					pstmts[0].setString(18,tm5);
					pstmts[0].setString(19,xb5);
					pstmts[0].setString(20,zj5);
					pstmts[0].addBatch();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

}
