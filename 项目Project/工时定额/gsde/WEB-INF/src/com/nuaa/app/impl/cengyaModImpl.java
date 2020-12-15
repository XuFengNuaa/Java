package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.cengyaMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class cengyaModImpl implements cengyaMod{

	@Override
	public JSONObject queryWeight() {
		// TODO Auto-generated method stub
		String sql = "select * from t_cengya";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("jp", Double.parseDouble(rs.getString("jp")));
							json.put("ck", Double.parseDouble(rs.getString("ck")));
							json.put("dp", Double.parseDouble(rs.getString("dp")));
							json.put("cy", Double.parseDouble(rs.getString("cy")));
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
		final String jp = hashmap.get("jp").toString();
		final String ck = hashmap.get("ck").toString();
		final String dp = hashmap.get("dp").toString();
		final String cy = hashmap.get("cy").toString();
		String[] sql = new String[1];
		sql[0] = "update t_cengya set jp=?,ck=?,dp=?,cy=?";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,jp);
					pstmts[0].setString(2,ck);
					pstmts[0].setString(3,dp);
					pstmts[0].setString(4,cy);
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
