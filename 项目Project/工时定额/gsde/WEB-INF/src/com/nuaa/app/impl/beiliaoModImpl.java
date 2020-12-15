package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.beiliaoMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class beiliaoModImpl implements beiliaoMod{

	@Override
	public JSONObject queryWeight() {
		// TODO Auto-generated method stub
		//final String feature = hashmap.get("feature").toString();
		String sql = "select * from t_beiliao";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("js1", Double.parseDouble(rs.getString("js1")));
							json.put("js2", Double.parseDouble(rs.getString("js2")));
							json.put("js3", Double.parseDouble(rs.getString("js3")));
							json.put("js4", Double.parseDouble(rs.getString("js4")));
							json.put("mj1", Double.parseDouble(rs.getString("mj1")));
							json.put("mj2", Double.parseDouble(rs.getString("mj2")));
							json.put("mj3", Double.parseDouble(rs.getString("mj3")));
							json.put("mj4", Double.parseDouble(rs.getString("mj4")));
							json.put("xj", Double.parseDouble(rs.getString("xj")));
							json.put("mz", Double.parseDouble(rs.getString("mz")));					
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
		final String js1 = hashmap.get("js1").toString();
		final String js2 = hashmap.get("js2").toString();
		final String js3 = hashmap.get("js3").toString();
		final String js4 = hashmap.get("js4").toString();
		final String mj1 = hashmap.get("mj1").toString();
		final String mj2 = hashmap.get("mj2").toString();
		final String mj3 = hashmap.get("mj3").toString();
		final String mj4 = hashmap.get("mj4").toString();
		final String xj = hashmap.get("xj").toString();
		final String mz = hashmap.get("mz").toString();
		String[] sql = new String[1];
		sql[0] = "update t_beiliao set js1=?,js2=?,js3=?,js4=?,mj1=?,mj2=?,mj3=?,mj4=?,xj=?,mz=?";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,js1);
					pstmts[0].setString(2,js2);
					pstmts[0].setString(3,js3);
					pstmts[0].setString(4,js4);
					pstmts[0].setString(5,mj1);
					pstmts[0].setString(6,mj2);
					pstmts[0].setString(7,mj3);
					pstmts[0].setString(8,mj4);
					pstmts[0].setString(9,xj);
					pstmts[0].setString(10,mz);
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
